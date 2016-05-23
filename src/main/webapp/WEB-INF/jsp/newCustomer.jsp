<%-- 
    Document   : newCustomer
    Created on : May 23, 2016, 5:05:56 PM
    Author     : Andrii_Kozak1
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="addNewCustomer" method="Post">
            Name: <input type="text" name="name"/><br/>
            Address: <input type="text" name="address"/><br/>
            <input type="submit" value="register"/><br/>
        </form>
    </body>
</html>
