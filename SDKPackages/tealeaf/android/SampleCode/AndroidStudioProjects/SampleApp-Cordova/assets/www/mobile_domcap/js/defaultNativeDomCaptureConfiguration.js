/**
 * Licensed Materials - Property of IBM
 * © Copyright IBM Corp. 2015
 * US Government Users Restricted Rights - Use, duplication or disclosure restricted by GSA ADP Schedule Contract with IBM Corp.
 */

// Default configuration
(function () {
 "use strict";
 /**
  * Due to issue with lack of change event propagation on legacy IE (W3C version of UIC)
  * its mandatory to provide more specific configuration on IE6, IE7, IE8 and IE9 in legacy
  * compatibility mode. For other browsers changeTarget can remain undefined as it is
  * sufficient to listen to the change event at the document level.
  */
 var changeTarget;
 if (TLT.getFlavor() === "w3c" && TLT.utils.isLegacyIE) {
 changeTarget = "input, select, textarea, button";
 }

 window.TLT.init({
                 core: {
                 moduleBase: 'intermediate/modules/',
                 // WARNING: For advanced users only. Modifying the modules section may lead to unexpected behavior and or performance issues.
                 modules: {
                 overstat: {
                 events: [
                          { name: "click", recurseFrames: true },
                          { name: "mousemove", recurseFrames: true },
                          { name: "mouseout", recurseFrames: true }
                          ]
                 },
                 performance: {
                 events: [
                          { name: "load", target: window },
                          { name: "unload", target: window }
                          ]
                 },
                 replay: {
                 events: [
                          { name: "change", target: changeTarget, recurseFrames: true },
                          { name: "click", recurseFrames: true },
                          { name: "hashchange", target: window },
                          { name: "focus", target: "input, select, textarea, button", recurseFrames: true },
                          { name: "blur", target: "input, select, textarea, button", recurseFrames: true },
                          { name: "load", target: window},
                          { name: "unload", target: window},
                          { name: "resize", target: window},
                          { name: "scroll", target: window},
                         // { name: "orientationchange", target: window},
                          { name: "touchend" },
                          { name: "touchstart" }
                          ]
                 },
                 TLCookie: {
                 enabled: true
                 }
                 },
                 // Set the sessionDataEnabled flag to true only if it's OK to expose Tealeaf session data to 3rd party scripts.
                 sessionDataEnabled: false,
                 sessionData: {
                 // Set this flag if the session value needs to be hashed to derive the Tealeaf session ID
                 sessionValueNeedsHashing: true,

                 // Specify sessionQueryName only if the session id is derived from a query parameter.
                 sessionQueryName: "sessionID",
                 sessionQueryDelim: ";",

                 // sessionQueryName, if specified, takes precedence over sessionCookieName.
                 sessionCookieName: "jsessionid"
                 },
                 // list of ignored frames pointed by css selector (top level only)
                 framesBlacklist: [
                                   "#iframe1"
                                   ]
                 },
                 services: {
                 queue: {
                 // WARNING: Enabling asynchronous request on unload may result in incomplete or missing data
                 asyncReqOnUnload: false,
                 queues: [
                          {
                          qid: "DEFAULT",
                          endpoint: "/TealeafTarget.php",
                          maxEvents: 50,
                          timerInterval: 300000,
                          maxSize: 500,
                          checkEndpoint: false,
                          endpointCheckTimeout: 3000
                          }
                          ]
                 },
                 message: {
                 privacy: [
                           {
                           targets: [
                                     // CSS Selector: All password input fields
                                     "input[type=password]"
                                     ],
                           "maskType": 3
                           }
                           ]
                 },
                 serializer: {
                 json: {
                 defaultToBuiltin: true,
                 parsers: [ "JSON.parse" ],
                 stringifiers: [ "JSON.stringify" ]
                 }
                 },
                 encoder: {
                 gzip: {
                 /**
                  * The encode function should return encoded data in an object like this:
                  * {
                  *     buffer: "encoded data"
                  * }
                  */
                 encode: "window.pako.gzip",
                 defaultEncoding: "gzip"
                 }
                 },
                 domCapture: {
                 diffEnabled: false,
                 // DOM Capture options
                 options: {
                 maxMutations: 100,       // If this threshold is met or exceeded, a full DOM is captured instead of a diff.
                 maxLength: 1000000,      // If this threshold is exceeded, the snapshot will not be sent
                 captureFrames: true,     // Should child frames/iframes be captured
                 removeScripts: true      // Should script tags be removed from the captured snapshot
                 }
                 },
                 browser: {
                 useCapture: true,
                 sizzleObject: "window.Sizzle",
                 jQueryObject: "window.jQuery"
                 }
                 },
                 modules: {
                 overstat: {
                 hoverThreshold: 1000
                 },
                 performance: {
                 calculateRenderTime: true,
                 renderTimeThreshold: 600000,
                 filter: {
                 navigationStart: true,
                 unloadEventStart: true,
                 unloadEventEnd: true,
                 redirectStart: true,
                 redirectEnd: true,
                 fetchStart: true,
                 domainLookupStart: true,
                 domainLookupEnd: true,
                 connectStart: true,
                 connectEnd: true,
                 secureConnectionStart: true,
                 requestStart: true,
                 responseStart: true,
                 responseEnd: true,
                 domLoading: true,
                 domInteractive: true,
                 domContentLoadedEventStart: true,
                 domContentLoadedEventEnd: true,
                 domComplete: true,
                 loadEventStart: true,
                 loadEventEnd: true
                 }
                 },
                 replay: {
                 // Geolocation configuration
                 geolocation: {
                 enabled: false,
                 triggers: [
                            {
                            event: "load"
                            }
                            ]
                 },
                 // DOM Capture configuration
                 domCapture: {
                 /**
                  * NOTE: Enabling DOM Capture has significant implications on data transmission and infrastructure.
                  * Hence this feature should be enabled judiciously. If enabled, it requires further configuration
                  * to only perform the DOM Capture based on specific events and elements. Please refer to the
                  * documentation for more details.
                  */
                 enabled: true,
                 /**
                  * The rules for triggering DOM Snapshots are similar to the Privacy configuration.
                  * It accepts a mandatory "event" followed by one or more optional targets
                  * as well as an optional delay after which to take the DOM snapshot.
                  *
                  * The default configuration below will capture a full DOM snapshot for each and every click, change
                  * action as well as for all screenview load and unloads. Please refer to the documentation for
                  * details on fine tuning this configuration to specific elements and screenviews.
                  */
                 triggers: [

                            {
                            event: "load",
                            delay: 500
                            }

                            ]
                 }
                 },
                 TLCookie: {
                 appCookieWhitelist:
                 [
                  { regex: ".*" }
                  ],
                 tlAppKey: "",
                 sessionizationCookieName: "TLTSID"
                 }
                 }
                 });
 }());
