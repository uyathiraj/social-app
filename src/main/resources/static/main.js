$(document).ready(function(){
	function postData(){
		var messageStr = document.getElementById("exampleFormControlTextarea1").value;
		console.log(messageStr);
		var data = {
			message : messageStr	
		}
		$.ajax({
	        type: "POST",
	        url: "/social/post",
	        data: JSON.stringify(data),
	        headers: {
	            'token':sessionStorage.getItem('token')
	        },
	        contentType: "application/json; charset=utf-8",
	        dataType: "json",
	        success: function (data, status, jqXHR) {

	            alert("Message pposted succesfully.");
	        },

	        error: function (jqXHR, status) {
	        	alert("Message pposted succesfully.");
	        }
	     });
	}	
   
});

