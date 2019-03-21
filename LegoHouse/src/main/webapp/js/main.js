$(document).ready(function () {

    // Validate Submit for Login Form
    $('#loginForm').submit(function (e) {
        $("#loginForm #errorBox").hide();
        if ($("#loginForm input[name=email]").val() === ""
                || $("#loginForm input[name=password]").val() === "") {
            e.preventDefault();
            $("#errorBox").html("Please fill out all fields");
            $("#errorBox").show();
        } else {
            e.preventDefault();
            ajax($(this));
        }
    });

    // Validate Submit for Register Form
    $('#registerForm').submit(function (e) {
        $("#registerForm #errorBox").hide();
        if ($("#registerForm input[name=email]").val() === ""
                || $("#registerForm input[name=password1]").val() === ""
                || $("#registerForm input[name=password2]").val() === "") {
            e.preventDefault();
            $("#errorBox").html("Please fill out all fields");
            $("#errorBox").show();
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
            $("#errorBox").html(request.getResponseHeader('errormessage'));
            $("#errorBox").show();
            $("#successBox").hide();
        } else {
            if (request.getResponseHeader('redirect') !== null) {
                window.location = request.getResponseHeader('redirect');
            } else if(request.getResponseHeader('success') !== null) {
                $("#successBox").html(request.getResponseHeader('success'));
                $("#successBox").show();
                $("#errorBox").hide();
            }
        }
    });
}