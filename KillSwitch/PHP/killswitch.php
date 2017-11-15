<?php  
	$ini_array = parse_ini_file("config.ini", true);
	//print_r($ini_array);
	
	// if sample by percent
	if($ini_array['configtype']['killswitchtype'] === 'percentagesample'){
		$sampleRate = intval($ini_array['percentagesample']['rate']);
		killbysamplerate($sampleRate);
	}
	// if sample by whitelist
	else {
		
	}
	
	
	function killbysamplerate($sampleRate){
		$randomnumber = rand(1,100);
		if($randomnumber <= $sampleRate){
			echo '1';
		}
		else {
			echo '0';
		}
	}
	
	function killbywhitelist($whitelistpath){
	
	}
?>