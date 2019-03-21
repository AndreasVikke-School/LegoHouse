<%-- 
    Document   : error
    Created on : Mar 21, 2019, 12:32:58 PM
    Author     : Andreas Vikke
--%>

<%@include file="header.jsp" %>

<%
    String errorMessage = "Error occurred...";
    if(response.getHeader("errormessage") != null) {
        errorMessage = response.getHeader("errormessage");
    }
%>
    
<h1>Error:</h1>
<p id="errorMessage"><%= errorMessage %></p>

<%@include file="footer.jsp" %>
