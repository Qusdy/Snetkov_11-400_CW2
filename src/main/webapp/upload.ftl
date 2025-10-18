<html lang="en">
<#include "base.ftl">
<#macro  title>
    Upload file
</#macro>

<#macro content>
    <form action="/upload" method="post" enctype="multipart/form-data">
        <input type="file" id="file" name="file">
        <button type="submit" value="upload">Загрузить</button>
    </form>
</#macro>
</html>