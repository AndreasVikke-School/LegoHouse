$(document).ready(function () {

    // Validate Submit for Login Form
    $('#loginForm').submit(function (e) {
        $("#" + this.id + " #errorBox").hide();
        if ($("#" + this.id + " input[name=email]").val() === ""
                || $("#" + this.id + " input[name=password]").val() === "") {
            e.preventDefault();
            $("#" + this.id + " #errorBox").html("Please fill out all fields");
            $("#" + this.id + " #errorBox").show();
        } else {
            e.preventDefault();
            ajax($(this));
        }
    });

    // Validate Submit for Register Form
    $('#registerForm').submit(function (e) {
        $("#" + this.id + " #errorBox").hide();
        if ($("#" + this.id + " input[name=email]").val() === ""
                || $("#" + this.id + " input[name=password1]").val() === ""
                || $("#" + this.id + "  input[name=password2]").val() === "") {
            e.preventDefault();
            $("#" + this.id + " #errorBox").html("Please fill out all fields");
            $("#" + this.id + " #errorBox").show();
        } else {
            e.preventDefault();
            ajax($(this));
        }
    });
    
    // Validate Submit for Register Form
    $('#shopForm').submit(function (e) {
        $("#" + this.id + " #errorBox").hide();
        if ($("#" + this.id + " input[name=length]").val() === ""
                || $("#" + this.id + " input[name=width]").val() === ""
                || $("#" + this.id + " input[name=height]").val() === "") {
            e.preventDefault();
            $("#" + this.id + " #errorBox").html("Please fill out all fields");
            $("#" + this.id + " #errorBox").show();
        } else {
            e.preventDefault();
            ajax($(this));
        }
    });
});

// Ajax Command
function ajax(formObj) {
    $.ajax({
        url: $(formObj).find('button').attr('formaction'),
        data: $(formObj).serialize()
    }).done(function (data, textStatus, request) {
        if (request.getResponseHeader('errormessage') !== null) {
            $("#" + formObj.attr("id") + " #errorBox").html(request.getResponseHeader('errormessage'));
            $("#" + formObj.attr("id") + " #errorBox").show();
            $("#" + formObj.attr("id") + " #successBox").hide();
        } else {
            if (request.getResponseHeader('redirect') !== null) {
                window.location = request.getResponseHeader('redirect');
            } else if(request.getResponseHeader('success') !== null) {
                $("#" + formObj.attr("id") + " #successBox").html(request.getResponseHeader('success'));
                $("#" + formObj.attr("id") + " #successBox").show();
                $("#" + formObj.attr("id") + " #errorBox").hide();
            }
        }
    });
}