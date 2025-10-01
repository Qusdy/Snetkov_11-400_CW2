<html lang="en">
<#include "base.ftl">

<#macro title>main</#macro>

<#macro content>
    <h3>
        Hi, ${user}!
        <br>
        Session ID: ${sessionId}
        <br>
        Cookie user: ${cookieUser}
    </h3>
</#macro>
</html>