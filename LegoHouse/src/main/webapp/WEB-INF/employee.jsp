<%-- 
    Document   : employee
    Created on : Mar 20, 2019, 12:49:42 PM
    Author     : Andreas Vikke
--%>

<%@page import="data.models.Order"%>
<%@page import="java.util.List"%>
<%@include file="../header.jsp" %>

<h1>Employee</h1>
<div class="employeeBox" class="table-responsive">
    <%@include file="orderList.jsp" %>
</div> 

<%@include file="../footer.jsp" %>
