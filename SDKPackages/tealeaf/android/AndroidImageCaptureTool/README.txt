#-------------------------------------------------------------------------------
# Licensed Materials - Property of IBM
# (C) Copyright IBM Corp. 2016
# US Government Users Restricted Rights - Use, duplication or disclosure
# restricted by GSA ADP Schedule Contract with IBM Corp.
#-------------------------------------------------------------------------------

Android Image Capture Tool


What is this?
    This README file is created for the AndroidImageCaptureTool, which contains instructions on how to setup Ruby and
    Rake tool environment to run the rake task to extract image resources from an Android APK file.
    Also, the tool can search and update your XML layout files for image related resources to enhance Native Replay
    Experience.

    Common use case for this tool is when you have an Android app where most of the image resources are bundled within
    the app, and the tool will extract and create appropriate folder structure and file formats to support Native Replay
    Experience.

Pre-requisites
    1) Install latest Android Tealeaf SDK eocore.jar and tealeafMod.jar libraries, version >= 10.1.0
    2) Install teacuts.jar library for Auto Instrumentation, Check DisableAutoInstrumentation=false in the
    TealeafBasicConfig.properties file.
    3) Build/run your Android project to generate an APK file

How to install?
    MAC OS

    1) Following Ruby installation guide to install Ruby version 2.1.2:
	https://www.ruby-lang.org/en/documentation/installation/
    2) Run terminal command:
        xcode-select --install
    3) Install below required Ruby gems by running the below command:

        a) gem install rubyzip '1.1.7'
        b) gem install nokogiri '1.6.7.2'

	Windows OS

    1) Following Ruby installation guide to install Ruby version 1.9.3-p551:
        http://dl.bintray.com/oneclick/rubyinstaller/rubyinstaller-1.9.3-p551.exe
    2) Install below required Ruby gems by running the below command:

        a) gem install zip '1.1.7'
        b) gem install nokogiri '1.6.7.2'

How to run it?
    1) Unzip the file named “AndroidImageCaptureTool” to your local path
    2) Open a terminal or command line prompt
    3) Go to the root folder of the unzipped file:  AndroidImageCaptureTool/
    4) Type in below command, then press enter key to execute the Rake task where apk_file_path is your APK file, and
       res_folder_path is the project's resource folder

	rake apk_file_path={YOUR/PROJECT/APK/PATH} res_folder_path={YOUR/PROJECT/RES/PATH}

    5) Follow print out path for the extracted image files under the "images" folder
    6) At the input prompt, type 'y' and Enter to automatically update all the XML layout files
       (Note: For Android Studio, You can reformat any updated XML files by clicking on "layout->Reformat Code"
       For Eclipse, click on "layout->source->format")
    7) Copy the contents of the image resources to the destination folder Tealeaf Replay Server.
       Typically it’s installed under the below path (NOTE:  This folder is for both IOS and Android images:

	C:\Tealeaf\Replay Server\Images