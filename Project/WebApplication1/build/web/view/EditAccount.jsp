<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.User" %>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.sql.Date"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Profile Page</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.1.1/dist/css/bootstrap.min.css">
        <style type="text/css">
            body {
                margin-top: 20px;
                background: #f8f8f8;
            }
            .container {
                margin-top: 20px;
            }
            .form-group {
                margin-bottom: 20px;
            }
            .hidden-message {
                display: none;
            }
        </style>
    </head>
    <body>
        <%@include file = "menu.jsp" %>
        <%@include file = "navbar.jsp" %>
        <%
            User account = (User) request.getAttribute("accountDetail");
            if (account == null) {
                response.sendRedirect("view/login.jsp");
                return;
            }
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            java.sql.Date sqlDate = null;
            java.util.Date utilDate = null;
            String dob = "";
            if (account != null) {
                sqlDate = account.getDob();
                if (sqlDate != null) {
                    utilDate = new java.util.Date(sqlDate.getTime());
                    dob = sdf.format(utilDate);
                }
            }
            String errorMessage = request.getAttribute("errorMessage") != null ? (String) request.getAttribute("errorMessage") : "";
            String successMessage = request.getAttribute("successMessage") != null ? (String) request.getAttribute("successMessage") : "";
        %>
        <div class="container">
            <div class="row">
                <div class="col-md-6 offset-md-3">
                    <h2>Edit Profile</h2>
                    <form action="accountController" method="post">
                        <input type="hidden" name="username" value="<%=account.getUsername()%>" />
                        <div class="form-group">
                            <label for="fullname">Full Name</label>
                            <input type="text" class="form-control" id="fullname" name="fullname" value="<%= account.getFullname() %>"/>
                        </div>
                        <div class="form-group">
                            <label for="email">Email</label>
                            <input type="text" class="form-control" id="email" name="email" value="<%= account.getEmail() %>"/>
                        </div>
                        <div class="form-group">
                            <label for="gender">Gender</label>
                            <select class="form-control" id="gender" name="gender">
                                <option value="Male" <%= (account.getGender()).equals("Male") ? "selected" : "" %> >Male</option>
                                <option value="Female" <%= (account.getGender()).equals("Female") ? "selected" : "" %> >Female</option>
                            </select>
                        </div>
                        <div class="form-group">
                            <label for="mobileNumber">Mobile Number</label>
                            <input type="text" class="form-control" id="mobileNumber" name="mobileNumber" value="<%= account.getMobile() %>"/>
                        </div>
                        <div class="form-group">
                            <label for="dob">Date of Birth</label>
                            <input type="date" class="form-control" id="dob" name="dob" value="<%=dob%>"/>
                        </div>
                        <div class='row'>
                            <div class='col-md-6'>
                                <button type='submit' class='btn btn-primary btn-block'>Update Account</button>
                            </div>
                        </div>
                    </form> 
                    <br>
                    <form action='changePassword'>
                        <input type='hidden' name='username' value='<%=account.getUsername()%>' />
                        <div class='row'>
                            <div class='col-md-6'>
                                <button type='submit' class='btn btn-primary btn-block'>Change Password</button>
                            </div>
                        </div>
                    </form>
                    <% if (errorMessage != null) {  %>
                    <div class="alert alert-danger mt-3 hidden-message "> ">
                        <%= errorMessage %>
                    </div>
                    <% } %>
                </div>
            </div>
        </div>
        <script src='https://code.jquery.com/jquery-1.10.2.min.js'></script>
        <script src='https://cdn.jsdelivr.net/npm/bootstrap@4.1.1/dist/js/bootstrap.bundle.min.js'></script>
        <%@include file = "footer.jsp" %>
    </body>
</html>