<%-- 
    Document   : customer
    Created on : Mar 20, 2019, 12:47:59 PM
    Author     : Andreas Vikke
--%>
<%@page import="java.util.List"%>
<%@page import="data.models.Order"%>
<%@include file="../header.jsp" %>
<%
    List<Order> orders = (List<Order>) session.getAttribute("orders");
%>

<div class="customerBox">
    <table class="table">
        <thead class="thead-dark">
            <tr>
                <th scope="col"># Order Id</th>
                <th scope="col">User Id</th>
                <th scope="col">Length</th>
                <th scope="col">Width</th>
                <th scope="col">Height</th>
                <th scope="col"></th>
            </tr>
        </thead>
        <tbody>
            <% for (Order o : orders) {%>
                <tr>
                    <th scope="row"><%= o.getId()%></th>
                    <td><%= o.getUserId()%></td>
                    <td><%= o.getLength()%></td>
                    <td><%= o.getWidth()%></td>
                    <td><%= o.getHeight()%></td>
                    <td><a href="order?orderId=<%= o.getId() %>"><button class="btn btn-info">Show Order</button></a></td>
                </tr>
            <% }%>
        </tbody>
    </table>
</div>

<%@include file="../footer.jsp" %>
