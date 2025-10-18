<html lang="en">
<#include "base.ftl">
<#macro title>Sign Up</#macro>
<#macro content>
    <h2>Registration</h2>
    <form method="post" action="/sign_up" enctype="multipart/form-data">
        Login:<br>
        <input type="text" name="login" id="login" placeholder="enter login" maxlength="31">
        <span id="loginStatus"></span>
        <br><br>

        Password:<br>
        <input type="password" name="password" placeholder="enter password" maxlength="10">
        <br><br>

        Name:<br>
        <input type="text" name="name" value="name" maxlength="31">
        <br><br>

        Lastname:<br>
        <input type="text" name="lastname" value="lastname" maxlength="31">
        <br><br>
        
        photo:<br>
        <input type="file" id="file" name="file" accept="image/*" required>

        <input type="submit" value="Sign Up" id="submitBtn">
    </form>
    <script>
        $(document).on("input", "#login", function () {
            console.log("check");
            var login = $(this).val();
            $.get("/check_existence", {login: login}, function(response) {
                console.log(response);
                if (response === "already registered") {
                    $("#loginStatus").text("Логин занят").css("color", "red");
                    $("#submitBtn").attr('disabled', true);
                } else if (response === "ok") {
                    $("#loginStatus").text("Логин свободен").css("color", "green");
                    $("#submitBtn").attr('disabled', false);
                }
            })
        })
    </script>
</#macro>
</html>