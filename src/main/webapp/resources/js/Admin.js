function searchbar() {
	$(document).ready(function(){
		$("#search").on("keyup", function() {
			var value = $(this).val().toLowerCase();
		    	$("#myTable tr").filter(function() {
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
