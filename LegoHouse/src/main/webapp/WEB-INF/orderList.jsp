<%-- 
    Document   : orderList
    Created on : Mar 21, 2019, 2:38:05 PM
    Author     : Andreas Vikke
--%>

<%@page import="data.models.Order"%>
<%@page import="java.util.List"%>
<%
    List<Order> orders = (List<Order>) session.getAttribute("orders");
%>
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
