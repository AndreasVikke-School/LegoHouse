<%-- 
    Document   : register
    Created on : Mar 20, 2019, 12:25:37 PM
    Author     : Andreas Vikke
--%>

<%@include file="header.jsp" %>

<form id="registerForm" method="POST">
    <div id="errorBox" class="alert alert-danger" role="alert"></div>
    <div class="form-group">
        <label>Email</label>
        <input type="email" class="form-control" name="email" placeholder="Email...">
    </div>
    <div class="form-group">
        <label>Password</label>
        <input type="password" class="form-control" name="password1" placeholder="Password...">
    </div>
    <div class="form-group">
        <label>Repeat Password</label>
        <input type="password" class="form-control" name="password2" placeholder="Password...">
    </div>
    <button type="submit" class="btn btn-primary" formaction="CommandServlet?command=register">Register</button>
</form>

<%@include file="footer.jsp" %>