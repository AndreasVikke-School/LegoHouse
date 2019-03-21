<%-- 
    Document   : shop
    Created on : Mar 20, 2019, 1:03:19 PM
    Author     : Andreas Vikke
--%>

<%@include file="../header.jsp" %>

<h1>Shop</h1>
<form id="shopForm" method="POST">
    <div id="errorBox" class="alert alert-danger" role="alert"></div>
    <div class="form-group">
        <label>Length</label>
        <input type="number" class="form-control" name="length" placeholder="Length in dots..." maxlength="25">
    </div>
    <div class="form-group">
        <label>Width</label>
        <input type="number" class="form-control" name="width" placeholder="Width in dots..."maxlength="25">
    </div>
    <div class="form-group">
        <label>Height</label>
        <input type="number" class="form-control" name="height" placeholder="Hight in bricks..."maxlength="25">
    </div><br />
    <button type="submit" class="btn btn-primary" formaction="CommandServlet?command=placeOrder">Place Order</button>
</form>

<%@include file="../footer.jsp" %>
