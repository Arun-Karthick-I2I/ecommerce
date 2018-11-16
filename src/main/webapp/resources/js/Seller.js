function showProductForm() {
	$('#productModal').modal({
		show : true
	});
}

function checkAllOrderId(checkbox) {
	var checkboxes = document.getElementsByName("orderItemId");
	if (checkbox.checked) {
		for (var i = 0; i < checkboxes.length; i++) {
                checkboxes[i].checked = true;
        }
	} else {
		for (var i = 0; i < checkboxes.length; i++) {
                checkboxes[i].checked = false;
		}
	}
}

function checkOrderIds() {
	var checkboxes = document.getElementsByName("orderItemId");
for (var i = 0; i < checkboxes.length; i++) {
        if(true == checkboxes[i].checked) {
        	return true;
        }
}
showSnackBar();
return false;
}

function showSelectAll() {
	var checkboxes = document.getElementsByName("orderItemId");
	if (0 == checkboxes.length) {
		document.getElementById("selectAll").style.display="none";
		document.getElementById("changeStatusBtn").style.visibility="hidden";
	}
}

function showSnackBar() {
    var snack = document.getElementById("snackbar");
    snack.className = "show";
    setTimeout(function(){ snack.className = snack.className.replace("show", ""); }, 3000);
}
