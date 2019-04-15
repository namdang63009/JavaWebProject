<%-- 
    Document   : searcShoe
    Created on : Oct 25, 2018, 3:58:09 PM
    Author     : DELL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search Shoes Page</title>
    </head>
    <body>
        <h1>Search Results</h1>
        <font color="red">
        Welcome, ${sessionScope.USERNAME}
        </font>
        <form action="logout">
            <input type="submit" value="Logout" />
        </form><br/>

        <h1>This is a fragment page of Search</h1>
        <h2>Detail</h2>
        <c:set var="description" value="${param.txtDescription}"/>
        <c:set var="sizes" value="${param.txtSize}"/>
        <c:if test="${not empty description or not empty sizes}">
            <c:set var="result" value="${requestScope.SEARCH_RESULT}"/>

            <c:if test="${not empty result}">
                <table border="1">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>Description</th>
                            <th>Quantity</th>
                            <th>Size</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                    <form action="disable">

                        <c:forEach var="dto" items="${result}" varStatus="counter">
                            <tr>
                                <td>
                                    ${counter.count}
                                </td>
                                <td>
                                    ${dto.description}
                                </td>
                                <td>

                                    ${dto.quantityOfShoe}
                                </td>
                                <td>
                                    ${dto.sizes}
                                </td>
                                <td>
                                    <input type="checkbox" name="chkDisable" value="${dto.shoeID}"
                                           <c:if test="${dto.disable eq true}">
                                               checked="checked" disabled
                                           </c:if>
                                           />
                                    <input type="hidden" name="desValue" value="${description}" />
                                    <input type="hidden" name="sizeValue" value="${sizes}" />
                                </td>
                            </tr>


                        </c:forEach>
                        <tr>
                            <td colspan="5">
                                <input type="submit" value="Disable" />

                            </td>

                        </tr>
                    </form>
                </tbody>
            </table>

        </c:if>
        <c:if test="${empty result}">
            <h2>
                <font colot="red">No Shoes Has Found !!</font>
            </h2>
        </c:if>
    </c:if>
        <br/>
    <a href="searchOutOfStock">Searching Product is out of stock</a><br/> 
    Click <a href="searchShoe.jsp">Here</a> to Back Search Shoe Page.
</body>
</html>
