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
        <label>Length (Min 12)</label>
        <input type="number" class="form-control" name="length" placeholder="Length in dots..." min="12" value="12" maxlength="25">
    </div>
    <div class="form-group">
        <label>Width (Min 6)</label>
        <input type="number" class="form-control" name="width" placeholder="Width in dots..." min="6" value="6" maxlength="25">
    </div>
    <div class="form-group">
        <label>Height  (Min 1)</label>
        <input type="number" class="form-control" name="height" placeholder="Hight in bricks..." min="1" value="1" maxlength="25">
    </div>
    <div class="form-group">
        <label>Bound</label>
        <input type="checkbox" class="form-control" name="bound">
        <label>Door</label>
        <input type="checkbox" class="form-control" name="door">
        <label>Window</label>
        <input type="checkbox" class="form-control" name="window">
    </div>
    <button type="submit" class="btn btn-primary" formaction="CommandServlet?command=placeOrder">Place Order</button>
</form>

<%@include file="../footer.jsp" %>
