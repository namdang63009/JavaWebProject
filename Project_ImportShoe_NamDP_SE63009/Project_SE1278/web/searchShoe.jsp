<%-- 
    Document   : searchShoe
    Created on : Oct 30, 2018, 10:42:27 PM
    Author     : DELL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search Shoe Page</title>
    </head>
    <body>
        <h1>Search Results</h1>
        <font color="red">
        Welcome, ${sessionScope.USERNAME}
        </font>
        <form action="logout">
            <input type="submit" value="Logout" />
        </form><br/>

        <form action="searchShoe">
            Description: <input type="text" name="txtDescription" value="${param.txtDescription}" />appropriate<br/>
            Size       : <input type="text" name="txtSize" value="${param.txtSize}" />( From 39 - 44)<br/>
            <input type="submit" value="Search"/><br/>
        </form><br/>



        <a href="searchOutOfStock">Searching Product is out of stock</a><br/>
    </body>
</html>
