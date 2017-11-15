var swipe1 = document.getElementById("swipe1"),
	swipe2 = document.getElementById("swipe2"),
	pinch1 = document.getElementById("pinch1"),
	tap1 = document.getElementById("tap1"),
	tap2 = document.getElementById("tap2"),
	tap3 = document.getElementById("tap3"),
	tap4 = document.getElementById("tap4"),
	tap5 = document.getElementById("tap5"),
	sandbox = document.getElementById("sandbox"),
	eventString = "";

//SWIPE
var swipeOptions = {
  
};
var hammertime1 = new Hammer(swipe1, swipeOptions);
hammertime1.on("dragstart dragend", function(ev){
	eventString = "There was a" + ev.type + "event";
	console.log(eventString);
});
var hammertime2 = new Hammer(swipe2, swipeOptions);
hammertime2.on("dragstart dragend", function(ev){
	eventString = "There was a" + ev.type + "event";
	console.log(eventString);
});

//PINCH
var pinchOptions = {
 
};
var hammertime3 = new Hammer(pinch1, pinchOptions);
hammertime3.on("pinch", function(ev){ 
	eventString = "There was a" + ev.type + "event";
	console.log(eventString);
});

//TAP
var tapOptions = {
 
};
var hammertime4 = new Hammer(tap1, tapOptions);
hammertime4.on("tap doubletap hold", function(ev) {
	eventString = "There was a" + ev.type + "event";
	console.log(eventString);
});
var hammertime5 = new Hammer(tap2, tapOptions);
hammertime5.on("tap doubletap hold", function(ev) {
	eventString = "There was a" + ev.type + "event";
	console.log(eventString);
});
var hammertime6 = new Hammer(tap3, tapOptions);
hammertime6.on("tap doubletap hold", function(ev) {
	eventString = "There was a" + ev.type + "event";
	console.log(eventString);
});
var hammertime7 = new Hammer(tap4, tapOptions);
hammertime7.on("tap doubletap hold", function(ev) {
	eventString = "There was a" + ev.type + "event";
	console.log(eventString);
});
var hammertime8 = new Hammer(tap5, tapOptions);
hammertime8.on("tap doubletap hold", function(ev) {
	eventString = "There was a" + ev.type + "event";
	console.log(eventString);
});

//SANDBOX
var sandboxOptions = {
  
};
hammertime9 = new Hammer(sandbox);

hammertime9.on("tap doubletap hold dragstart dragend pinch", function(ev){ 
	eventString = "There was a" + ev.type + "event";
	console.log(eventString);
});