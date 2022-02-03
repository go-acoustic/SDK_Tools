# README
This folder contains the release package for the [Acoustic Experience Analytics (Tealeaf) UI Capture SDK](https://developer.goacoustic.com/acoustic-exp-analytics/docs/tealeaf-ui-capture-overview)

## Release package contents
* README.txt - Document describing package contents.
* defaultconfiguration.js - Default configuration file which can be used as an example or starting point.
* tealeaf.js - Base UIC SDK. defaultconfiguration.js should be appended to this file.
* tealeaf.min.js - Minified base UIC SDK. defaultconfiguration.js should be appended to this file.
* Optional
  * tealeaf.gestures.js - Optional Gestures module for touch enabled applications. See [Gestures configuration](https://developer.goacoustic.com/acoustic-exp-analytics/docs/gestures-configuration-for-ui-capture) in the documentation for additional information.
  * tealeaf.frame.js - Optional script for cross-domain hosting of the Tealeaf Target page. See [Cross-domain communication](https://developer.goacoustic.com/acoustic-exp-analytics/docs/cross-domain-communication) in the documentation for additional information.
  * tltWorker.js - Optional script for improving application performance by using the Web Worker. See [Implementing the Tealeaf Web Worker](https://developer.goacoustic.com/acoustic-exp-analytics/docs/implementing-the-acoustic-tealeaf-web-worker-script) in the documentation for additional information.
* Targets - Only applicable for Experience Analytics (Tealeaf) On-premises.
  * README - Read prior to using any sample target page implementation.
  * TealeafTarget.aspx - Sample target page implementation for an ASPX application server.
  * TealeafTarget.jsp - Sample target page implementation for a JSP application server.
  * TealeafTarget.php - Sample target page implementation for a PHP application server.
  
## Related links
[Release Notes](https://developer.goacoustic.com/acoustic-exp-analytics/docs/uic-release-notes)

[Getting Started with the UI Capture SDK](https://developer.goacoustic.com/acoustic-exp-analytics/docs/getting-started-with-the-ui-capture-sdk)
