/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/*
 * creates a new XMLHttpRequest object which is the backbone of AJAX,
 * or returns false if the browser doesn't support it
 */
var   xmlHttpReq;
function getXMLHttpRequest() {
  // to create XMLHttpRequest object in non-Microsoft browsers
  if (window.XMLHttpRequest) {
    xmlHttpReq = new XMLHttpRequest();
  } else if (window.ActiveXObject) {
    try {
      // to create XMLHttpRequest object in later versions
      // of Internet Explorer
      xmlHttpReq = new ActiveXObject("Msxml2.XMLHTTP");
    } catch (exp1) {
      try {
        // to create XMLHttpRequest object in older versions
        // of Internet Explorer
        xmlHttpReq = new ActiveXObject("Microsoft.XMLHTTP");
      } catch (exp2) {
        xmlHttpReq = false;
      }
    }
  }
}
/*
 * AJAX call starts with this function
 */
function makeRequest(type, form) {
  xmlHttpReq.onreadystatechange = getReadyStateHandler(xmlHttpReq);
  var resource = "financial/" + form.name;
  xmlHttpReq.open(type, resource, true);
  var formData;
  if (form != null) {
      formData = new FormData(form);
  }
  else {
      formData = new FormData();
  }

  xmlHttpReq.send(formData);
}
 
/*
 * Returns a function that waits for the state change in XMLHttpRequest
 */
function getReadyStateHandler(xmlHttpRequest) {
 
  // an anonymous function returned
  // it listens to the XMLHttpRequest instance
  return function() {
    if (xmlHttpRequest.readyState == 4) {
      if (xmlHttpRequest.status == 200) {
        document.getElementById("amount").innerHTML = "Payment: " + xmlHttpRequest.responseText;
      } else {
        alert("HTTP error " + xmlHttpRequest.status + ": " + xmlHttpRequest.statusText);
      }
    }
  };
}