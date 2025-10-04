<html lang="en">
<#include "base.ftl">
<#macro title>Sign Up</#macro>
<#macro content>
    <h2>Registration</h2>
    <form method="post" action="/sign_up">
        Login:<br>
        <input type="text" name="login" placeholder="enter login" maxlength="31">
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

        <input type="submit" value="Sign Up">
    </form>
</#macro>
</html>