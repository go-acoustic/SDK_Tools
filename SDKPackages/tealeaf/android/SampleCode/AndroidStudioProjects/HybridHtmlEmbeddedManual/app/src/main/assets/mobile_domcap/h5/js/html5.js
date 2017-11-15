
function toggle(obj, linkobj){
    	
	    var div = new Array();
	    div[0]  = document.getElementById("index1");
	    div[1]  = document.getElementById("p2");
	    div[2]  = document.getElementById("p21");
	    div[3]  = document.getElementById("p22");
	    div[4]  = document.getElementById("p23");
	    div[5]  = document.getElementById("p24");
	     
	    if (document.getElementsByClassName) {
	    	 var test = document.getElementsByClassName("active");
		     test[0].className="";
		     linkobj.parentNode.className="active";
	    }
	    
	 	if(obj.id=="p23"){
    		// getGeolocation();	
		}
	    for(n=0; n<div.length; n++){
	    	if(obj.id == div[n].id){
	    		div[n].style.display = "block";	
	    	}
	    	else{
	    		div[n].style.display = "none";
	    	}	
	    }
    	
        if (TLT === undefined) {
            console.log('TLT is undefined!');
        } else { 
        	if (TLT.logScreenviewLoad === undefined) {
        		console.log('Could not invoke TLT.logScreenviewLoad API!');
        	} else {
        		TLT.logScreenviewLoad("root");
        		console.log('logScreenviewLoad:');
        	}
        	
        	if (TLT.logDOMCapture === undefined) {
        		console.log('Could not invoke TLT.logDOMCapture API!');
        	} else {
        		dcid = TLT.logDOMCapture(window.document, {});
        		console.log('logDOMCapture:' + dcid);
        	}
        }
    }

function bindEvent(el, eventName, eventHandler) {
	  if (el.addEventListener){
	    el.addEventListener(eventName, eventHandler, false); 
	  } else if (el.attachEvent){
	    el.attachEvent('on'+eventName, eventHandler);
	  }
	}
function isCanvasSupported(){
	  var elem = document.createElement('canvas');
	  return !!(elem.getContext && elem.getContext('2d'));
	}

function draw(scale, translatePos){
                var canvas = document.getElementById("myCanvas");
                var context = canvas.getContext("2d");
                
                // clear canvas
                context.clearRect(0, 0, canvas.width, canvas.height);
                
                context.save();
                context.translate(translatePos.x, translatePos.y);
                context.scale(scale, scale);
                context.beginPath(); // begin custom shape
                context.moveTo(-119, -20);
                context.bezierCurveTo(-159, 0, -159, 50, -59, 50);
                context.bezierCurveTo(-39, 80, 31, 80, 51, 50);
                context.bezierCurveTo(131, 50, 131, 20, 101, 0);
                context.bezierCurveTo(141, -60, 81, -70, 51, -50);
                context.bezierCurveTo(31, -95, -39, -80, -39, -50);
                context.bezierCurveTo(-89, -95, -139, -80, -119, -20);
                context.closePath(); // complete custom shape
                var grd = context.createLinearGradient(-59, -100, 81, 100);
                grd.addColorStop(0, "#8ED6FF"); // light blue
                grd.addColorStop(1, "#004CB3"); // dark blue
                context.fillStyle = grd;
				context.font="20px Arial";
				context.fillText("Tealeaf in the",-265,1); 
                context.fill();
                
                context.lineWidth = 5;
                context.strokeStyle = "#0000ff";
                context.stroke();
                context.restore();
            }

            
            window.onload = function(){
            	// localStorage1();
            	if(!isCanvasSupported()){
            		alert("This browser does not support Canvas.")
            	}
            	
                var canvas = document.getElementById("myCanvas");
                
                var translatePos = {
                    x: canvas.width / 2,
                    y: canvas.height / 2
                };
                
                var scale = 1.0;
                var scaleMultiplier = 0.8;
                var startDragOffset = {};
                var mouseDown = false;
                
                // add button event listeners
                //document.getElementById("plus").addEventListener("click", function(){
                 //   scale /= scaleMultiplier;
                   // draw(scale, translatePos);
                //}, false);
                
	            bindEvent(document.getElementById("plus"), "click", function() {
	            			scale /= scaleMultiplier;
	            			draw(scale, translatePos);
	            			});
	            bindEvent(document.getElementById("minus"), "click", function() {
	            			scale *= scaleMultiplier;
	            			draw(scale, translatePos);
	            			});
              

                
                // add event listeners to handle screen drag
                bindEvent(document.getElementById("myCanvas"), "mousedown", function(evt){
                    mouseDown = true;
                    startDragOffset.x = evt.clientX - translatePos.x;
                    startDragOffset.y = evt.clientY - translatePos.y;
                });
                
                bindEvent(document.getElementById("myCanvas"), "mouseup", function(evt){
                    mouseDown = false;
                });
                
                bindEvent(document.getElementById("myCanvas"), "mouseover", function(evt){
                    mouseDown = false;
                });
                
                bindEvent(document.getElementById("myCanvas"), "mouseout", function(evt){
                    mouseDown = false;
                });
                
                bindEvent(document.getElementById("myCanvas"), "mousemove", function(evt){
                    if (mouseDown) {
                        translatePos.x = evt.clientX - startDragOffset.x;
                        translatePos.y = evt.clientY - startDragOffset.y;
                        draw(scale, translatePos);
                    }
                });
                
                if(isCanvasSupported()){
                	draw(scale, translatePos);
                }
                
             // define the variables
                var status;

                // when loaded
                	status = document.getElementById('status1');
                	
                	if (navigator.onLine)
                		online();
                	else
                		offline();

                	//window.addEventListener('online',online,false);
                	//window.addEventListener('offline',offline,false);
                	bindEvent(window, 'online',  online);
                	bindEvent(window, 'offline', offline);

                

                function online() {
                	status.setAttribute("class", "online");
                	status.innerHTML = "Online";
                }

                function offline() {
                	status.setAttribute("class", "offline");
                	status.innerHTML = "Offline";
                }
                
            };
            