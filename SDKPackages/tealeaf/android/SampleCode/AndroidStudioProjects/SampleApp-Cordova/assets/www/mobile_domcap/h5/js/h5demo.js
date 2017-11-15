
  var eat = ['yum!', 'gulp', 'burp!', 'nom'];
  var yum = document.createElement('p');
  var msie = /*@cc_on!@*/0;
  yum.style.opacity = 1;


 
  /*
   * IE issues, thus manually add them to list. 
   *var links = document.querySelectorAll('li > a'), el = null;
   */
  	var links = new Array();
  	links[0]  = document.getElementById("one");
  	links[1]  = document.getElementById("two");
  	links[2]  = document.getElementById("three");
  	links[3]  = document.getElementById("four");
  	links[4]  = document.getElementById("five");

  	for (var i = 0; i < links.length; i++) {
    	el = links[i];
  
    el.setAttribute('draggable', 'true');
    
  
    addEvent(el, 'dragstart', function (e) {
      e.dataTransfer.effectAllowed = 'copy'; // only dropEffect='copy' will be dropable
      e.dataTransfer.setData('Text', this.id); // required otherwise doesn't work
    });
  }

  var bin = document.getElementById('bin');

  addEvent(bin, 'dragover', function (e) {
    if (e.preventDefault) e.preventDefault(); // allows us to drop
    this.className = 'over';
    e.dataTransfer.dropEffect = 'copy';
    return false;
  });

  // to get IE to work
  addEvent(bin, 'dragenter', function (e) {
    this.className = 'over';
    return false;
  });

  addEvent(bin, 'dragleave', function () {
    this.className = '';
  });

  addEvent(bin, 'drop', function (e) {
    if (e.stopPropagation) e.stopPropagation(); // stops the browser from redirecting...why???

    var el = document.getElementById(e.dataTransfer.getData('Text'));
    
    el.parentNode.removeChild(el);

    // stupid nom text + fade effect
    bin.className = '';
    yum.innerHTML = eat[parseInt(Math.random() * eat.length)];

    var y = yum.cloneNode(true);
    bin.appendChild(y);

    setTimeout(function () {
      var t = setInterval(function () {
        if (y.style.opacity <= 0) {
          if (msie) { // don't bother with the animation
            y.style.display = 'none';
          }
          clearInterval(t); 
        } else {
          y.style.opacity -= 0.1;
        }
      }, 50);
    }, 250);

    return false;
  });

var $ = function (s) { return document.getElementById(s); },
    state = $('status'),
    lastevent = $('lastevent'),
    urlhistory = $('urlhistory'),
    examples = $('examples'),
    output = $('output'),
    template = '<p>URL: <strong>{url}</strong>, name: <strong>{name}</strong>, location: <strong>{location}</strong></p>',
    data = { // imagine these are ajax requests :)
      first : {
        name: "Remy",
        location: "Brighton, UK"
      },
      second: {
        name: "John",
        location: "San Francisco, USA"
      },
      third: {
        name: "Jeff",
        location: "Vancover, Canada"
      },
      fourth: {
        name: "Simon",
        location: "London, UK"
      }
    };

function reportEvent(event) {
  lastevent.innerHTML = event.type;
}

function reportData(data) {
  output.innerHTML = template.replace(/(:?\{(.*?)\})/g, function (a,b,c) {
    return data[c];
  });
}

if (typeof history.pushState === 'undefined') {
  state.className = 'fail';
} else {
  state.className = 'success';
  state.innerHTML = 'HTML5 History API available';
}

addEvent(examples, 'click', function (event) {
  var title;
  
  event.preventDefault();
  if (event.target.nodeName == 'A') {
    title = event.target.innerHTML;
    data[title].url = event.target.getAttribute('href'); // slightly hacky (the setting), using getAttribute to keep it short
    history.pushState(data[title], title, event.target.href);
    reportData(data[title]);
  }
});

addEvent(window, 'popstate', function (event) {
  var data = event.state;
  reportEvent(event);
  reportData(event.state || { url: "unknown", name: "undefined", location: "undefined" });
});

addEvent(window, 'hashchange', function (event) {
  reportEvent(event);

  // we won't do this for now - let's stay focused on states
  /*
  if (event.newURL) {
    urlhistory.innerHTML = event.oldURL;
  } else {
    urlhistory.innerHTML = "no support for <code>event.newURL/oldURL</code>";
  }
  */
});

addEvent(window, 'pageshow', function (event) {
  reportEvent(event);
});

addEvent(window, 'pagehide', function (event) {
  reportEvent(event);
});

function localStorage2(stuff){
	if (typeof(localStorage) == 'undefined' ) {
		alert('Your browser does not support HTML5 localStorage. Try upgrading.');
	}else
		try{
			localStorage.setItem('contenteditable', stuff);
		}catch (e) {
			if (e == QUOTA_EXCEEDED_ERR) {
				alert('Quota exceeded!'); //data wasn't successfully saved due to quota exceed so throw an error
				}
			}
}

/* editable & localStorage */ 
var editable = document.getElementById('editable');

addEvent(editable, 'blur', function () {
  // lame that we're hooking the blur event	

  localStorage2(this.innerHTML);
  document.designMode = 'off';
});

addEvent(editable, 'focus', function () {
  document.designMode = 'on';
});

addEvent(document.getElementById('clear'), 'click', function () {
	if (typeof(localStorage) == 'undefined' ) {
		alert('Your browser does not support HTML5 localStorage. Try upgrading.');
	}else{
		localStorage.clear();
		window.location = window.location; // refresh
	} 
});

if (localStorage.getItem('contenteditable')) {
  editable.innerHTML = localStorage.getItem('contenteditable');
}





