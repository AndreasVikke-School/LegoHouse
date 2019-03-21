<%-- 
    Document   : login
    Created on : Mar 20, 2019, 12:12:46 PM
    Author     : Andreas Vikke
--%>

<%@include file="header.jsp" %>

<h1>Login</h1>
<form id="loginForm" method="POST">
    <div id="errorBox" class="alert alert-danger" role="alert"></div>
    <div class="form-group">
        <label>Email</label>
        <input type="text" class="form-control" name="email" placeholder="Email..." maxlength="25">
    </div>
    <div class="form-group">
        <label>Password</label>
        <input type="password" class="form-control" name="password" placeholder="Password..."maxlength="25">
    </div><br />
    <button type="submit" class="btn btn-primary" formaction="CommandServlet?command=login">Login</button>
</form>

<%@include file="footer.jsp" %>
