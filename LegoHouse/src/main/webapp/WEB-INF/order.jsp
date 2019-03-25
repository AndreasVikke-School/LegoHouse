<%-- 
    Document   : order
    Created on : Mar 20, 2019, 1:36:30 PM
    Author     : Andreas Vikke
--%>


<%@page import="data.models.BrickSide"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="data.models.Order"%>
<%@include file = "../header.jsp" %>

<%
    Order order = (Order) session.getAttribute("order");
    HashMap<String, Integer> map = (HashMap<String, Integer>) session.getAttribute("partList");
%>

<h1>Order #<%= order.getId()%></h1>
<div class="orderBox" class="table-responsive">
    <table class="table">
        <thead class="thead-dark">
            <tr>
                <th scope="col"># Order Id</th>
                <th scope="col">User Id</th>
                <th scope="col">Length</th>
                <th scope="col">Width</th>
                <th scope="col">Height</th>
                <th scope="col">Date</th>
                <th scope="col">Shipped</th>
                <th scope="col">Bound</th>
                <th scope="col">Door</th>
                <th scope="col">Window</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <th scope="row"><%= order.getId()%></th>
                <td><%= order.getUserId()%></td>
                <td><%= order.getLength()%></td>
                <td><%= order.getWidth()%></td>
                <td><%= order.getHeight()%></td>
                <td><%= order.getDate()%></td>
                <% if (order.isShipped()) { %>
                <td><i class="fas fa-check-circle" style="color: green;"></i></td>
                    <% } else { %>
                <td><i class="fas fa-times-circle" style="color: red;"></i></td>
                    <% } %>
                    <% if (order.isBound()) { %>
                <td><i class="fas fa-check-circle" style="color: green;"></i></td>
                    <% } else { %>
                <td><i class="fas fa-times-circle" style="color: red;"></i></td>
                    <% } %>
                    <% if (order.isDoor()) { %>
                <td><i class="fas fa-check-circle" style="color: green;"></i></td>
                    <% } else { %>
                <td><i class="fas fa-times-circle" style="color: red;"></i></td>
                    <% } %>
                    <% if (order.isWindow()) { %>
                <td><i class="fas fa-check-circle" style="color: green;"></i></td>
                    <% } else { %>
                <td><i class="fas fa-times-circle" style="color: red;"></i></td>
                    <% } %>
            </tr>
        </tbody>
    </table>

    <table class="table">
        <thead class="thead-light">
            <tr>
                <th>Type</th>
                <th>Side 1</th>
                <th>Side 2</th>
                <th>Side 3</th>
                <th>Side 4</th>
                <th>Total</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <th scope="row">2x4</th>
                <td><%= map.get("S1-2x4")%></td>
                <td><%= map.get("S2-2x4")%></td>
                <td><%= map.get("S3-2x4")%></td>
                <td><%= map.get("S4-2x4")%></td>
                <td><%= map.get("S1-2x4") + map.get("S2-2x4") + map.get("S3-2x4") + map.get("S4-2x4")%></td>
            </tr>
            <tr>
                <th scope="row">2x2</th>
                <td><%= map.get("S1-2x2")%></td>
                <td><%= map.get("S2-2x2")%></td>
                <td><%= map.get("S3-2x2")%></td>
                <td><%= map.get("S4-2x2")%></td>
                <td><%= map.get("S1-2x2") + map.get("S2-2x2") + map.get("S3-2x2") + map.get("S4-2x2")%></td>
            </tr>
            <tr>
                <th scope="row">2x1</th>
                <td><%= map.get("S1-2x1")%></td>
                <td><%= map.get("S2-2x1")%></td>
                <td><%= map.get("S3-2x1")%></td>
                <td><%= map.get("S4-2x1")%></td>
                <td><%= map.get("S1-2x1") + map.get("S2-2x1") + map.get("S3-2x1") + map.get("S4-2x1")%></td>
            </tr>
        </tbody>
    </table>
    <button class="btn btn-info" id="backButton" name="back" onclick="history.back()">Back</button>
</div>

<%@include file = "../footer.jsp" %>