		var isMobile = {
			Android: function() {
				return navigator.userAgent.match(/Android/i)? true:false;
			},
			iOS : function(){
				return navigator.userAgent.match(/iPhone|iPad|iPod/i)? true:false;
			},	
			Windows: function () {
                    return navigator.userAgent.match(/IEMobile/i) ? true : false;
            }
        };
		var isPlatform = {
			Android: function() {
				return navigator.appVersion.match(/Android/i) ? true : false;
			},
			iOS: function() {
                    return navigator.appVersion.match(/iPhone|iPod/i) ? true : false;
             },
             Windows: function() {
                    return navigator.appVersion.match(/Windows NT/i) ? true : false;
             }
		};	
		if(isMobile.iOS() && isPlatform.iOS())
			document.write('<link rel="stylesheet" href="../css/iphone.css" type="text/css" />');
	    if(isMobile.Android() && isPlatform.Android())
			document.write('<link rel="stylesheet" href="../css/android.css" type="text/css" />');