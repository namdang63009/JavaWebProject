<%-- 
    Document   : productIsOutOfStock
    Created on : Oct 28, 2018, 10:19:51 PM
    Author     : DELL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Out Of Stock Page</title>
    </head>
    <body>
        <h1>Some Products are out of stock</h1>
        <font color="red">
        Welcome, ${sessionScope.USERNAME}

        </font><br/>
        <form action="logout">
            <input type="submit" value="Logout" />
        </form><br/>
        <c:set var="listSize" value="${requestScope.LIST_SIZE}"/>
        <c:set var="result" value="${requestScope.OUTOFSTOCK}" />
        <c:if test="${not empty result}">
            <table border="1">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>Description</th>
                        <th>Quantity</th>
                        <th>Price</th>
                        <th>Size</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="dto" items="${result}" varStatus="counter">
                    <form action="updateShoe">

                        <tr>
                            <td>
                                ${counter.count}
                            </td>
                            <td>
                                ${dto.description}
                            </td>
                            <td>
                                <input type="number" name="txtQuantity" value="" />
                            </td>
                            <td>

                                <input type="number" name="txtPrice" value="" />
                                
                                <input type="hidden" name="txtShoeID" value="${dto.shoeID}" />
                                <input type="hidden" name="currentSizeID" value="${dto.sizesID}" />
                                
                            </td>
                            <td>
                                <c:if test="${not empty listSize}">
                                    <select name="cboSize">
                                        <%----%>
                                        <c:forEach var="size" items="${listSize}">
                                            <option value="${size.id}">${size.sizes}</option>
                                        </c:forEach>

                                    </select>
                                </c:if>

                            </td>
                            <td>
                                <input type="submit" value="Import" />
                            </td>
                        </tr>
                    </form>
                </c:forEach>
            </tbody>
        </table>

    </c:if>
    <c:if test="${empty result}">
        <h2>There are no any shoes OUT OF STOCK !!</h2>
    </c:if>
    <br/>
    Click <a href="searchShoe.jsp">Here</a> to Back Search Shoe Page.<br/>
<!--    Click <a href="searchResult.jsp">Here</a> to Back Search Shoe Result Page.-->
</body>
</html>
