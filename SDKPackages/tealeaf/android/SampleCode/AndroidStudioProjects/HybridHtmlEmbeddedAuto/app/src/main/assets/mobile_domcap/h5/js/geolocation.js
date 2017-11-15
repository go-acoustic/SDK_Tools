function success(position) {
  var s = document.getElementById('status8');
  
  if (s.className == 'success') {
    // not sure why we're hitting this twice in FF, I think it's to do with a cached result coming back    
    return;
  }
  
  s.innerHTML = "found you!";
  s.className = 'success';
  
  var mapcanvas = document.createElement('div');
  mapcanvas.id = 'mapcanvas';
  mapcanvas.style.height = '400px';
  mapcanvas.style.width = '560px';
    
  document.querySelector('#geo8').appendChild(mapcanvas);
  
  var latlng = new google.maps.LatLng(position.coords.latitude, position.coords.longitude);
  var myOptions = {
    zoom: 15,
    center: latlng,
    mapTypeControl: false,
    navigationControlOptions: {style: google.maps.NavigationControlStyle.SMALL},
    mapTypeId: google.maps.MapTypeId.ROADMAP
  };
  var map = new google.maps.Map(document.getElementById("mapcanvas"), myOptions);
  
  var marker = new google.maps.Marker({
      position: latlng, 
      map: map, 
      title:"You are here! (at least within a "+position.coords.accuracy+" meter radius)"
  });
  
}


function error(msg) {
  var s = document.getElementById('status8');
  s.innerHTML = typeof msg == 'string' ? msg : "failed";
  s.className = 'fail';
  
  // console.log(arguments);
}

function localStorage1(){
	var d=new Date();
	if (typeof(localStorage) == 'undefined' ) {
		alert('Your browser does not support HTML5 localStorage. Try upgrading.');
		} else {
			count = localStorage.getItem("visit");
			try {
				if(count == null || count == "null"){
					count=1;
					localStorage.setItem("origDate", d); //saves to the database, "key", "value"
				}else{
					count = parseInt(count);
					count++;
					alert(  "ALERT: [Please clear your browser cache for better capture.]\n" +
							"YOUR VISIT #: [" + count + "]" +
							"\nFIRST VISIT DATE/TIME: ["+localStorage.getItem("origDate") + "]" +
							"\nINFO: [Data obtained using localStorage.]"
							); 
				}
				localStorage.setItem("visit", count); //saves to the database, "key", "value"
			} catch (e) {
			 	 if (e == QUOTA_EXCEEDED_ERR) {
			 	 	 alert('Quota exceeded!'); //data wasn't successfully saved due to quota exceed so throw an error
				}
			}
			
			//localStorage.removeItem("visit"); //deletes the matching item from the database
		}
	
}
function getGeolocation(){
	if (navigator.geolocation) {
		navigator.geolocation.getCurrentPosition(success, error);
		}else {
			error('not supported');
			}
}


