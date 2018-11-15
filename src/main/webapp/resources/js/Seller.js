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
alert("Please Select Atleast One Order to Proceed");
return false;
}

function showSelectAll() {
	var checkboxes = document.getElementsByName("orderItemId");
	if (0 == checkboxes.length) {
		document.getElementById("selectAll").style.display="none";
	}
}