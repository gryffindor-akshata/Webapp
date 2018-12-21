$(document).ready(function () {

 $('#submit').click(function () {
 var displayResources = $('#display-resources');
 
 displayResources.text('Loading data....');
 document.getElementById("demo").innerHTML = " ";
 //document.getElementById("addr").value = " "

// displayResources.text('Loading data....');

 var address = document.getElementById("addr").value;
 
 function ValidateIPaddress(address) {  

	 if (/^(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$/.test(address)) {  
    //alert('cool');
	return (true); //ipv4
}
		 
	 else if (/^(([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])\.){3}([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])$|^(([a-zA-Z]|[a-zA-Z][a-zA-Z0-9\-]*[a-zA-Z0-9])\.)*$|^\s*((([0-9A-Fa-f]{1,4}:){7}([0-9A-Fa-f]{1,4}|:))|(([0-9A-Fa-f]{1,4}:){6}(:[0-9A-Fa-f]{1,4}|((25[0-5]|2[0-4]\d|1\d\d|[1-9]?\d)(\.(25[0-5]|2[0-4]\d|1\d\d|[1-9]?\d)){3})|:))|(([0-9A-Fa-f]{1,4}:){5}(((:[0-9A-Fa-f]{1,4}){1,2})|:((25[0-5]|2[0-4]\d|1\d\d|[1-9]?\d)(\.(25[0-5]|2[0-4]\d|1\d\d|[1-9]?\d)){3})|:))|(([0-9A-Fa-f]{1,4}:){4}(((:[0-9A-Fa-f]{1,4}){1,3})|((:[0-9A-Fa-f]{1,4})?:((25[0-5]|2[0-4]\d|1\d\d|[1-9]?\d)(\.(25[0-5]|2[0-4]\d|1\d\d|[1-9]?\d)){3}))|:))|(([0-9A-Fa-f]{1,4}:){3}(((:[0-9A-Fa-f]{1,4}){1,4})|((:[0-9A-Fa-f]{1,4}){0,2}:((25[0-5]|2[0-4]\d|1\d\d|[1-9]?\d)(\.(25[0-5]|2[0-4]\d|1\d\d|[1-9]?\d)){3}))|:))|(([0-9A-Fa-f]{1,4}:){2}(((:[0-9A-Fa-f]{1,4}){1,5})|((:[0-9A-Fa-f]{1,4}){0,3}:((25[0-5]|2[0-4]\d|1\d\d|[1-9]?\d)(\.(25[0-5]|2[0-4]\d|1\d\d|[1-9]?\d)){3}))|:))|(([0-9A-Fa-f]{1,4}:){1}(((:[0-9A-Fa-f]{1,4}){1,6})|((:[0-9A-Fa-f]{1,4}){0,4}:((25[0-5]|2[0-4]\d|1\d\d|[1-9]?\d)(\.(25[0-5]|2[0-4]\d|1\d\d|[1-9]?\d)){3}))|:))|(:(((:[0-9A-Fa-f]{1,4}){1,7})|((:[0-9A-Fa-f]{1,4}){0,5}:((25[0-5]|2[0-4]\d|1\d\d|[1-9]?\d)(\.(25[0-5]|2[0-4]\d|1\d\d|[1-9]?\d)){3}))|:)))(%.+)?\s*$/.test(address)) {
	     //alert('cool6');    
	return (true); //ipv6
	  }  
	  //alert("You have entered an invalid IP address!")  
	 
else {
	  return (false);
	}  
 }


 if (document.getElementById("addr").value=="" || !(ValidateIPaddress(address)))
	 {
	   displayResources.text(" ");
	   document.getElementById("demo").innerHTML = " ";
	   document.getElementById("addr").value = " "
	   alert('Enter a valid IP Address');
	   return null;
	 }
 
 else if (document.getElementById("Ping").checked)
	 {
	     //alert(address);
	    var url = `WebApp/welcome/ping/ip?addr=` + address;
	    address = "";
	 }
 
 else if (document.getElementById("Traceroute").checked)
	 { 
	 
	 
	    var url = `WebApp/welcome/traceroute/ip?addr=` + address;
	    address = "";

	 }
 else if (document.getElementById("MTR").checked)
 {
 
    var url = `WebApp/welcome/mtr/ip?addr=` + address;
    address = "";

 }
 else if (document.getElementById("Telnet").checked)
 {
	 var port = prompt("Please enter a port number:");
	    if (port == null || port == "  ") {
	    	displayResources.text(" ");
	 	   document.getElementById("demo").innerHTML = " ";
	    	var txt = 'Please enter a valid port number and try again!'
	        alert(txt);
	        return null;

	    } 
	    else {
	        //alert(port);

		    var url = `WebApp/welcome/telnet/ip/?addr=` + address + `&pr=` + port;
		    

	    }

 }
 
 var request = new XMLHttpRequest();
 request.open("GET", url, true);
 request.setRequestHeader("Accept", "application/json");
 request.setRequestHeader("Content-Type", "application/json;charset=utf-8");


 request.onreadystatechange = function() {
   if (request.readyState === 4 && request.status === 200) {
     var response = JSON.parse(request.responseText);
     //data = JSON.stringify({ a: 2 }, null, " ");   // '{\n "a": 2\n}'

     //getElements(response);
     var msg = response.message.replace(/\r\n|\n|\r/g, '<br />');
    
     document.getElementById("demo").innerHTML = msg;
     
     displayResources.text('Data Loaded');

   }
 }

 
 request.send();
 
 


 });
});