<%-- 
    Document   : order
    Created on : Mar 20, 2019, 1:36:30 PM
    Author     : Andreas Vikke
--%>


<%@page import="data.models.BrickSide"%>
<%@page import="data.models.BrickLayer"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="data.models.Order"%>
<%@include file = "../header.jsp" %>
<%
    Order order = (Order) session.getAttribute("order");
    List<BrickLayer> brickLayers = (List<BrickLayer>) session.getAttribute("partList");
%>

<div class="orderBox">
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
            <%
                HashMap<String, Integer> map = new HashMap();
                for (int i = 0; i < brickLayers.size(); i++) {
                    map.put("S1-2x4", map.getOrDefault("S1-2x4", 0) + brickLayers.get(i).getSides().get(0).getBricks2x4());
                    map.put("S2-2x4", map.getOrDefault("S2-2x4", 0) + brickLayers.get(i).getSides().get(1).getBricks2x4());
                    map.put("S3-2x4", map.getOrDefault("S3-2x4", 0) + brickLayers.get(i).getSides().get(2).getBricks2x4());
                    map.put("S4-2x4", map.getOrDefault("S4-2x4", 0) + brickLayers.get(i).getSides().get(3).getBricks2x4());

                    map.put("S1-2x2", map.getOrDefault("S1-2x2", 0) + brickLayers.get(i).getSides().get(0).getBricks2x2());
                    map.put("S2-2x2", map.getOrDefault("S2-2x2", 0) + brickLayers.get(i).getSides().get(1).getBricks2x2());
                    map.put("S3-2x2", map.getOrDefault("S3-2x2", 0) + brickLayers.get(i).getSides().get(2).getBricks2x2());
                    map.put("S4-2x2", map.getOrDefault("S4-2x2", 0) + brickLayers.get(i).getSides().get(3).getBricks2x2());

                    map.put("S1-2x1", map.getOrDefault("S1-2x1", 0) + brickLayers.get(i).getSides().get(0).getBricks2x1());
                    map.put("S2-2x1", map.getOrDefault("S2-2x1", 0) + brickLayers.get(i).getSides().get(1).getBricks2x1());
                    map.put("S3-2x1", map.getOrDefault("S3-2x1", 0) + brickLayers.get(i).getSides().get(2).getBricks2x1());
                    map.put("S4-2x1", map.getOrDefault("S4-2x1", 0) + brickLayers.get(i).getSides().get(3).getBricks2x1());
                }%>
            <tr>
                <th scope="row">2x4</th>
                <td><%= map.get("S1-2x4") %></td>
                <td><%= map.get("S2-2x4") %></td>
                <td><%= map.get("S3-2x4") %></td>
                <td><%= map.get("S4-2x4") %></td>
                <td><%= map.get("S1-2x4") + map.get("S2-2x4") + map.get("S3-2x4") + map.get("S4-2x4") %></td>
            </tr>
            <tr>
                <th scope="row">2x2</th>
                <td><%= map.get("S1-2x2") %></td>
                <td><%= map.get("S2-2x2") %></td>
                <td><%= map.get("S3-2x2") %></td>
                <td><%= map.get("S4-2x2") %></td>
                <td><%= map.get("S1-2x2") + map.get("S2-2x2") + map.get("S3-2x2") + map.get("S4-2x2") %></td>
            </tr>
            <tr>
                <th scope="row">2x1</th>
                <td><%= map.get("S1-2x1") %></td>
                <td><%= map.get("S2-2x1") %></td>
                <td><%= map.get("S3-2x1") %></td>
                <td><%= map.get("S4-2x1") %></td>
                <td><%= map.get("S1-2x1") + map.get("S2-2x1") + map.get("S3-2x1") + map.get("S4-2x1") %></td>
            </tr>
        </tbody>
    </table>
    <button class="btn btn-info" name="back" onclick="history.back()">Back</button>
</div>

<%@include file = "../footer.jsp" %>