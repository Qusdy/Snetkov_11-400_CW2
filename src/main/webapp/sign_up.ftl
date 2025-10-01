<html lang="en">
<#include "base.ftl">
<#macro title>sign up</#macro>
<#macro content>
    <h2>Registration</h2>
    <form method="post" action="/sign_up">
        Login:
        <input type="text" name="login" placeholder="choose your login">
        <br>
        Password:
        <input type="password" name="password" placeholder="choose your password">
        <br>
        <input type="submit" value="Sign Up">
    </form>
</#macro>

</html>