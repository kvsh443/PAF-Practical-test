//Response-Alert  
$(document).ready(function() {
    if ($("#alertSuccess").text().trim() == "") {
        $("#alertSuccess").hide();
    }
    $("#alertError").hide();
});
//Valid Input Checker
function validateDocForm() {

    var phone_regx = /^[+]*[(]{0,1}[0-9]{1,3}[)]{0,1}[-\s\./0-9]*$/g;

    if ($("#DocNameF").val().trim() == "") {
        return "Doctor Must Have A First Name";
    }
    if ($("#DocNameL").val().trim() == "") {
        return "Doctor Must Have A Last Name";
    }
    if ($("#DocNumber").val().trim() == "") {
        return "Doctor Must Have A Phone Number";
    }
    if (!phone_regx.test($("#DocNumber").val().trim())) {
        return "Incorrect Phone Number Format";
    }
    if ($("#DocStatus").val().trim() == "") {
        return "Status Can't be Empty";
    }
    
    return true;
}
//Save
$(document).on("click", "#btnSave", function(event) {
    //Clear alerts
    $("#alertSuccess").text("");
    $("#alertError").text("");
    $("#alertSuccess").hide();
    $("alertError").hide();
    //Form validation
    var status = validateDocForm();
    if (status != true) {
        $("#alertError").text(status);
        $("#alertError").show();
        return;
    }

    var method = ($("#DocIdSave").val() == "") ? "POST" : "PUT";
    
    $.ajax({
        url: "DoctorAPI",
        type: method,
        data: $("#formDoc").serialize(),
        dataType: "text",
        complete: function(response, status) {
        	
            onDoctorSaveSuccess(response.responseText, status);
        }
    });
});

function onDoctorSaveSuccess(response, status) {
    if (status == "success") {
    	
        var resultset = JSON.parse(response);
        
        if (resultset.status.trim() == "success") {
            $("#alertSuccess").text("Saved Successfully!");
            $("#alertSuccess").show;
            $("#divDocGrid").html(resultset.data);

        } else if (resultset.status.trim() == "error") {
            $("#alertError").text(resultset.data);
            $("#alertError").show();
        }
    } else if (status == "error") {
        $("#alertError").text("Saving Error!");
        $("#alertError").show();
    } else {
        $("#alertError").text("Unkown Error while Saving!");
        $("#alertError").show();
    }
    $("#DocIdSave").val("");
    $("#formDoc")[0].reset();
}
//Update Doctor
$(document).on("click", "#btnUpdate", function(event) {
	console.log("Update Button Invoked");
    $("#DocIdSave").val($(this).closest("tr").find('#hiddendocIDUpdate').val());
    $("#DocNameF").val($(this).closest("tr").find('td:eq(0)').text());
    $("#DocNameM").val($(this).closest("tr").find('td:eq(1)').text());
    $("#DocNameL").val($(this).closest("tr").find('td:eq(2)').text());
    $("#DocNumber").val($(this).closest("tr").find('td:eq(3)').text());
    $("#DocStatus").val($(this).closest("tr").find('td:eq(4)').text());
});
//Remove Doctor
$(document).on("click", "#btnRemove", function(event) {
    $.ajax({
        url: "DoctorAPI",
        type: "DELETE",
        data: "doc_id=" + $(this).data("docid"),
        dataType: "text",
        complete: function(response, status) {
            onDoctorDeleteSuccess(response.responseText, status);
        }
    })
});

function onDoctorDeleteSuccess(response, status) {
    if (status == "success") {
        var resultset = JSON.parse(response);
        if (resultset.status.trim() == "success") {
            $("#alertSuccess").text("Deleted Successfully!");
            $("#alertSuccess").show;
            $("#divDocGrid").html(resultset.data);

        } else if (resultset.status.trim() == "error") {
            $("#alertError").text(resultset.data);
            $("#alertError").show();
        }
    } else if (status == "error") {
        $("#alertError").text("Deleting Error!");
        $("#alertError").show();
    } else {
        $("#alertError").text("Unkown Error while Deleting!");
        $("#alertError").show();
    }
}