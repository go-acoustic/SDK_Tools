This folder is for Native Replay templates.

If you iOS application uses any UIPIckerView or UIDatePicker controls. You will need to patch your ReplaySpliceStart_00XX.js file.

Requirements:

- You must have updated your system up to 902 FP6, which can be found in fix central.
- You should have latest TLT folder from the latest Mobile SDK package

1.- Go to <IBM Location>\IBM\IBM Tealeaf CX\System directory.
2.- Look for a latest version of the file that starts with ReplaySpliceStart_00 and create a back up copy.
3.- Perform "Stop Tealeaf Services" and make sure all TL services stopped.
4.- Edit ReplaySpliceStart_00XX.js file and look for the following location:

                // iOS native UIPageControl
                if (node.hasAttribute("templatetype") && node.getAttribute("templatetype") == "pageControl") {
                    // Reset all the styles
                    var iObjects = node.getElementsByTagName("i");
                    for (i = 0; i < iObjects.length; i++) {
                        iObjects[i].className = "fa fa-circle tlPageControlIcon tlPageControlColor"
                    }

                    if (curState && typeof curState.currentPage != 'undefined' && curState.currentPage > -1) {
                        iObjects[curState.currentPage].className = "fa fa-circle tlPageControlIcon tlPageControlSelectedColor"
                    }
                }

5.- At the end of "if" statement for "iOS native UIPageControl" add the following code:

                // iOS native UIPickerView
                if (node.hasAttribute("templatetype") && node.getAttribute("templatetype") == "pickerView") {
                    // Update the text on the selected column - standard is only 5 visible rows
                    if (curState && typeof curState.selectedColumn != 'undefined'
                        && curState.updatedVisibleColData != 'undefined') {
                        for (i = 0; i < curState.updatedVisibleColData.length; i++) {
                            var pickerLabel = document.getElementById(node.id + ",[colNo," + curState.selectedColumn + "],[rowNo," + i + "]");
                            if (pickerLabel) {
                                pickerLabel.innerHTML = curState.updatedVisibleColData[i];
                            }
                        }
                    }
                }

                // iOS native UIDatePicker
                if (node.hasAttribute("templatetype") && node.getAttribute("templatetype") == "datePicker") {
                    if (curState && curState.updatedVisibleDatePicData != 'undefined'
                        && curState.numberOfColumns != 'undefined') {
                        for (colNo = 0; colNo < curState.numberOfColumns; colNo++) {
                            for (rowNo = 0; rowNo < curState.updatedVisibleDatePicData[colNo].length; rowNo++) {
                                if (colNo > 0) {
                                    var pickerLabel = document.getElementById(node.id + ",[colNo," + colNo + "],[rowNo," + rowNo + "]");
                                    if (pickerLabel) {
                                        pickerLabel.innerHTML = curState.updatedVisibleDatePicData[colNo][rowNo];
                                    }
                                } else if (colNo == 0) {
                                    var lblData = (curState.updatedVisibleDatePicData[colNo][rowNo]).split('.');
                                    for (lblNo = 0; lblNo < lblData.length; lblNo++) {
                                        var pickerLabel = document.getElementById(node.id + ",[colNo," + colNo + "],[rowNo," + rowNo + "],[lblNo," + lblNo + "]");
                                        if (pickerLabel) {
                                            pickerLabel.innerHTML = lblData[lblNo];
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

6.- After change, you should have a file with the following changes:

                // iOS native UIPageControl
                if (node.hasAttribute("templatetype") && node.getAttribute("templatetype") == "pageControl") {
                    // Reset all the styles
                    var iObjects = node.getElementsByTagName("i");
                    for (i = 0; i < iObjects.length; i++) {
                        iObjects[i].className = "fa fa-circle tlPageControlIcon tlPageControlColor"
                    }

                    if (curState && typeof curState.currentPage != 'undefined' && curState.currentPage > -1) {
                        iObjects[curState.currentPage].className = "fa fa-circle tlPageControlIcon tlPageControlSelectedColor"
                    }
                }

                // iOS native UIPickerView
                if (node.hasAttribute("templatetype") && node.getAttribute("templatetype") == "pickerView") {
                    // Update the text on the selected column - standard is only 5 visible rows
                    if (curState && typeof curState.selectedColumn != 'undefined'
                        && curState.updatedVisibleColData != 'undefined') {
                        for (i = 0; i < curState.updatedVisibleColData.length; i++) {
                            var pickerLabel = document.getElementById(node.id + ",[colNo," + curState.selectedColumn + "],[rowNo," + i + "]");
                            if (pickerLabel) {
                                pickerLabel.innerHTML = curState.updatedVisibleColData[i];
                            }
                        }
                    }
                }

                // iOS native UIDatePicker
                if (node.hasAttribute("templatetype") && node.getAttribute("templatetype") == "datePicker") {
                    if (curState && curState.updatedVisibleDatePicData != 'undefined'
                        && curState.numberOfColumns != 'undefined') {
                        for (colNo = 0; colNo < curState.numberOfColumns; colNo++) {
                            for (rowNo = 0; rowNo < curState.updatedVisibleDatePicData[colNo].length; rowNo++) {
                                if (colNo > 0) {
                                    var pickerLabel = document.getElementById(node.id + ",[colNo," + colNo + "],[rowNo," + rowNo + "]");
                                    if (pickerLabel) {
                                        pickerLabel.innerHTML = curState.updatedVisibleDatePicData[colNo][rowNo];
                                    }
                                } else if (colNo == 0) {
                                    var lblData = (curState.updatedVisibleDatePicData[colNo][rowNo]).split('.');
                                    for (lblNo = 0; lblNo < lblData.length; lblNo++) {
                                        var pickerLabel = document.getElementById(node.id + ",[colNo," + colNo + "],[rowNo," + rowNo + "],[lblNo," + lblNo + "]");
                                        if (pickerLabel) {
                                            pickerLabel.innerHTML = lblData[lblNo];
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

                // Handle scroll events. Scroll coordinates are in clientX/clientY.
                if (subType == "scroll") {
                    window.scroll(clientX, clientY);
                }

7.- Perform "Start Tealeaf Services"




*******************************************************************************************************************************************

If you Android application uses any Switch control. You will need to patch your ReplaySpliceStart_00XX.js file.

Requirements:

- You must have updated your system up to 902 FP6, which can be found in fix central.
- You should have latest TLT folder from the latest Mobile SDK package

1.- Go to <IBM Location>\IBM\IBM Tealeaf CX\System directory.
2.- Look for a latest version of the file that starts with ReplaySpliceStart_00 and create a back up copy.
3.- Perform "Stop Tealeaf Services" and make sure all TL services stopped.
4.- Edit ReplaySpliceStart_00XX.js file and look for the following location:

                // Android native replay for RatingBar
                if (subType == "RatingChanged") {
                    var idSubFix = elementValue / (node.getAttribute("maxvalue") / node.getAttribute("numstars"));
                    var ratingInput = document.getElementById(node.id + idSubFix);
                    if (ratingInput) {
                        var inputs = node.getElementsByTagName('input');
                        for (index = 0; index < inputs.length; ++index) {
                            inputs[index].checked = 0;
                            ReplaySpliceJS.fireEvent(inputs[index], 'change');
                        }
                        ratingInput.checked = 1;
                        ReplaySpliceJS.fireEvent(ratingInput, 'change');
                    }
                }

5.- At the end of "if" statement for "Android native replay for RatingBar" add the following code:

                // Android native replay for Switch
                if (subType == "click" && node.hasAttribute("templatetype") && node.getAttribute("templatetype") == "switch") {
                    var switchThumb = document.getElementById(node.id + "-thumb");
                    if (switchThumb && curState && typeof curState.thumbText != 'undefined') {
                        switchThumb.innerHTML = curState.thumbText;
                    }
                    if (switchThumb && curState && typeof curState.isToggled != 'undefined') {
                        if (curState.isToggled) {
                            switchThumb.style.float = "right";
                        } else {
                            switchThumb.style.float = "left";
                        }
                    }
                }

6.- After change, you should have a file with the following changes:

                // Android native replay for RatingBar
                if (subType == "RatingChanged") {
                    var idSubFix = elementValue / (node.getAttribute("maxvalue") / node.getAttribute("numstars"));
                    var ratingInput = document.getElementById(node.id + idSubFix);
                    if (ratingInput) {
                        var inputs = node.getElementsByTagName('input');
                        for (index = 0; index < inputs.length; ++index) {
                            inputs[index].checked = 0;
                            ReplaySpliceJS.fireEvent(inputs[index], 'change');
                        }
                        ratingInput.checked = 1;
                        ReplaySpliceJS.fireEvent(ratingInput, 'change');
                    }
                }
                // Android native replay for Switch
                if (subType == "click" && node.hasAttribute("templatetype") && node.getAttribute("templatetype") == "switch") {
                    var switchThumb = document.getElementById(node.id + "-thumb");
                    if (switchThumb && curState && typeof curState.thumbText != 'undefined') {
                        switchThumb.innerHTML = curState.thumbText;
                    }
                    if (switchThumb && curState && typeof curState.isToggled != 'undefined') {
                        if (curState.isToggled) {
                            switchThumb.style.float = "right";
                        } else {
                            switchThumb.style.float = "left";
                        }
                    }
                }

7.- Perform "Start Tealeaf Services"