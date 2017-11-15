#-------------------------------------------------------------------------------
# Licensed Materials - Property of IBM
# (C) Copyright IBM Corp. 2017
# US Government Users Restricted Rights - Use, duplication or disclosure
# restricted by GSA ADP Schedule Contract with IBM Corp.
#-------------------------------------------------------------------------------
require 'zip'
require 'fileutils'
require 'digest'
require 'nokogiri'
Zip.warn_invalid_date = false

# Constant variables for src and dest folders
WORKING_DIR = File.dirname(__FILE__)

APK_FILE_PATH = ENV['apk_file_path']
RES_FOLDER_PATH = ENV['res_folder_path']

DESTINATION_DIR = File.join(WORKING_DIR, '/images')
TEMP_DIR = './tmp'
APK_FOLDER_PATTERN = '/drawable**/*.{png,9.png,jpg,gif,webp,bmp}'

desc 'Unzip the APK'
task :unpack_apk do
  apk_file_path = ENV['apk_file_path'].nil? ? '' : ENV['apk_file_path']

  raise ArgumentError, 'Invalid APK file:  ' + apk_file_path unless File.exist?(apk_file_path)
  puts "\n*** Starting to extract image resources from the APK: #{apk_file_path}  ... ***\n\n"

  unzip_file(apk_file_path, TEMP_DIR)
end


desc 'Copy apk resources to a folder'
task :copy_apk_resource do
  res_path = File.join(TEMP_DIR, '/res')

  #
  # Copy image files that starts with drawable under res folder from the unzipped APK file
  #
  Dir.glob(res_path + APK_FOLDER_PATTERN) do |fPath|
    dir = File.dirname(fPath)
    filename = File.basename(fPath)
    dest = File.join(DESTINATION_DIR, dir)
    dest = dest.sub('./tmp', '')
    unless File.directory?(dest)
      FileUtils.mkdir_p(dest)
    end

    FileUtils.copy_file(fPath, File.join(dest, filename))
  end

  puts 'Destination folder: ' + DESTINATION_DIR
  unless File.directory?(DESTINATION_DIR)
    puts 'Creating directory:  ' + DESTINATION_DIR
    FileUtils.mkdir_p(DESTINATION_DIR)
  end

  #
  # Copy the file using the MD5 checksum filename format, and put under the images/ folder
  # For each file under the copied folder, create MD5 checksum file name using base64 hash.
  # Glob all files that starts with the folder string pattern, and associated extensions.
  #
  # puts "Number of files matched: #{Dir.glob(res_path + APK_FOLDER_PATTERN)}"
  Dir.glob(res_path + APK_FOLDER_PATTERN) do |fPath|
    extension = File.extname(fPath)
    md5_checksum = Digest::MD5.file fPath
    md5_checksum = md5_checksum.to_s.upcase

    dPath = "#{DESTINATION_DIR}/#{md5_checksum}#{extension}"

    if File.exist?(dPath) && FileUtils.compare_file(fPath, dPath)
      puts 'File already exists, skipped: ' + File.basename(fPath, '.*')
    else
      FileUtils.cp fPath, dPath
    end
  end
end

task :update_layout_xml do
  res_path = ENV['res_folder_path'].nil? ? '' : ENV['res_folder_path']
  raise ArgumentError, 'Please specify a valid resource folder (res) path.  Ex:  rake res_folder_path={../YOUR/PROJECT/RES/PATH}' unless Dir.exist?(res_path)

  puts "\n\n*** Update XML layout resource files ***\n\n"
  print 'Automatically find and update the layout xml files with attributes src, foreground, background? [Y/N]: '
  answer = STDIN.getc.chr
  if answer.downcase == 'y' || answer == "\n"
    # Create a backup of the project
    if File.exist?('Backup')
      FileUtils.remove_dir 'Backup'
    end
    FileUtils.copy_entry res_path, 'Backup'
    puts 'A backup of the folder is created at:  ' + WORKING_DIR + '/Backup' + "\n\n"

    pattern = File.join(res_path, 'layout**/*.xml')

    # Find all xml files in the res/layout folder, then update resource info using the 'tag' object
    Dir.glob(File.expand_path(pattern)) do |fPath|
      doc = Nokogiri::XML(File.open(fPath))

      isModified = false
      fileName = File.basename(fPath, '.*') + '.xml'

      doc.collect_namespaces.each { |ns|
        # Use xmlns defined in the files
        namespace = ns.first.to_s[6..-1]

        begin
          # Use Xpath to search of matching attributes
          nodesFound = doc.xpath("//*[@#{namespace}:src]", "//*[@#{namespace}:background]", "//*[@#{namespace}:foreground]")
        rescue
          #puts 'Current file has no match found for the namespace:  ' + namespace
          next
        end

        nodesFound.each { |e|
          # If the tag attribute wasn't used already, set it to support image replay on the server
          namespaceTag = namespace + ':tag'
          # unless !e.attribute('tag').nil?
          if !e.attribute('src').nil? & !e.attribute('src').to_s.start_with?('@color') & !e.attribute('src').to_s.start_with?('#') & !e.attribute('src').to_s.start_with?('@android:color')
            e.set_attribute(namespaceTag, e.attribute('src'))
            isModified = true
            puts 'File updated with:  ' + namespaceTag + e.attribute('src') + ' -> ' + fileName
          elsif !e.attribute('foreground').nil? & !e.attribute('foreground').to_s.start_with?('@color') & !e.attribute('foreground').to_s.start_with?('#') & !e.attribute('foreground').to_s.start_with?('@android:color')
            e.set_attribute(namespaceTag, e.attribute('foreground'))
            isModified = true
            puts 'File updated with:  ' + namespaceTag + e.attribute('foreground') + ' -> ' + fileName
          elsif !e.attribute('background').nil? & !e.attribute('background').to_s.start_with?('@color') & !e.attribute('background').to_s.start_with?('#') & !e.attribute('background').to_s.start_with?('@android:color')
            e.set_attribute(namespaceTag, e.attribute('background'))
            isModified = true
            puts 'File updated with:  ' + namespaceTag + e.attribute('background') + ' -> ' + fileName
          end
          # else
          #   puts 'A tag attribute already exists in the node:  ' + fPath + ':  ' + namespaceTag + '=' + e.attribute('tag')
          # end
        }

        # Save the changes back to file
        if isModified
          File.open(fPath, 'w+') { |f|
            f.puts doc.to_xml
          }
        end
      }
    end
  end
end

desc 'House Cleaning'
task :clean_up do
  FileUtils.rm_rf(TEMP_DIR)
end

def unzip_file (file, destination)
  aFile = nil

  if RUBY_PLATFORM.downcase =~ /darwin/ then
    #MAC OS
    aFile = Zip::File
  elsif RUBY_PLATFORM.downcase =~ /mingw/ then
    #Windows
    aFile = Zip::File
  else
    puts 'We only support MAC and Windows platforms: ' + RUBY_PLATFORM.downcase
    return
  end

  aFile.open(file) { |zip_file|
    zip_file.each { |f|
      f_path = File.join(destination, f.name)
      FileUtils.mkdir_p(File.dirname(f_path))
      zip_file.extract(f, f_path) unless File.exist?(f_path)
    }
  }
end

desc 'Rake file version'
task :version do
  puts "\nAndroid Image Extraction Tool Version => 1.5.0'"
end

desc 'Default Rake task driver'
task :default => [:unpack_apk, :copy_apk_resource, :update_layout_xml, :clean_up] do
  # This is called after pre-requisite tasks are executed
  puts "\n\n*** Done. ***\n\n"
end
