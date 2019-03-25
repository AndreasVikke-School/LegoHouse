<%-- 
    Document   : orderList
    Created on : Mar 21, 2019, 2:38:05 PM
    Author     : Andreas Vikke
--%>

<%@page import="data.models.RoleEnum"%>
<%@page import="data.models.User"%>
<%@page import="data.models.Order"%>
<%@page import="java.util.List"%>
<%
    List<Order> orders = (List<Order>) session.getAttribute("orders");
    User user = (User) session.getAttribute("user");
%>
<div class="table-responsive">
    <table class="table">
        <thead class="thead-dark">
            <tr>
                <th scope="col"># Order Id</th>
                <th scope="col">User Id</th>
                <th scope="col">Length</th>
                <th scope="col">Width</th>
                <th scope="col">Height</th>
                    <% if (user.getRole() == RoleEnum.EMPLOYEE) { %>
                <th scope="col">Shipped</th>
                    <% } %>
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
                <% if (user.getRole() == RoleEnum.EMPLOYEE) { %>
                <% if (o.isShipped()) { %>
                <td><i class="fas fa-check-circle" style="color: green;"></i></td>
                    <% } else {%>
                <td><a href="CommandServlet?command=shipOrder&orderId=<%= o.getId()%>"><button class="btn btn-info">Ship</button></a></td>
                <% }
                    }%>
                <td><a href="order?orderId=<%= o.getId()%>"><button class="btn btn-info">Show Order</button></a></td>
            </tr>
            <% }%>
        </tbody>
    </table>
</div>
