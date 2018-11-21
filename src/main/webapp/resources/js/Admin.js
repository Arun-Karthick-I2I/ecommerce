function searchbar() {
	$(document).ready(function(){
		$("#search").on("keyup", function() {
			var value = $(this).val().toLowerCase();
		    	$("#table-body tr").filter(function() {
		    		$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
		    	});
		});
	});
}

function showSnackBar() {
    var snack = document.getElementById("snackbar");
    snack.className = "show";
    setTimeout(function(){ snack.className = snack.className.replace("show", ""); }, 3000);
}

function openNav() {
    document.getElementById("mySidebar").style.width = "160px";
    document.getElementById("main").style.marginLeft = "160px";
}

function closeNav() {
    document.getElementById("mySidebar").style.width = "0";
    document.getElementById("main").style.marginLeft= "0";
}