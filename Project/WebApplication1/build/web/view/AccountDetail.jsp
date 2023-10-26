<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.User" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Account Details</title>
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
            .card {
                border: none;
                box-shadow: 0 0 15px 0 rgba(0,0,0,0.1);
            }
            .card-body {
                position: relative;
                padding: 30px;
            }
            .btn-back {
                width: 80px; /* Adjust as needed */
            }
            .btn-margin {
                margin-bottom: 20px; /* Adjust as needed */
            }
            .hidden-message {
                visibility: hidden;
            }
        </style>
    </head>
    <body>
        <%@include file = "menu.jsp" %>
        <%@include file = "navbar.jsp" %>
        <%
    User account = (User) request.getAttribute("accountDetail");
    String errorMessage = request.getAttribute("errorMessage") != null ? (String) request.getAttribute("errorMessage") : null;
    String successMessage = request.getAttribute("successMessage") != null ? (String) request.getAttribute("successMessage") : null;
    if (account != null) {
        %>
        <div class="container">
            <div class="row justify-content-center">
                <div class="col-md-6">
                    <div class="card mt-5">
                        <div class="card-body">
                            <h1 class="mb-4">Account Details</h1>

                            <p><strong>Role:</strong> <%= account.getRole() %></p>
                            <p><strong>Full Name:</strong> <%= account.getFullname() %></p>
                            <p><strong>Email:</strong> <%= account.getEmail() %></p>
                            <p><strong>Gender:</strong> <%= account.getGender() %></p>
                            <p><strong>Mobile Number:</strong> <%= account.getMobile() %></p>
                            <p><strong>Date of Birth:</strong> <%= account.getDob() %></p>
                            <br>
                            <form action='accountController'>
                                <input type='hidden' name='username' value='<%=account.getUsername()%>' />
                                <input type='hidden' name='action' value='edit'>
                                <button type='submit' class='btn btn-primary btn-block mt-3'>Edit Account</button> 
                            </form>
                            <br>
                            <div class="d-flex justify-content-center mt-3 btn-margin">
                                <a href="prj/DashboardController" class="btn btn-secondary btn-sm btn-back">Back</a><br>
                            </div>
                            <% if (errorMessage != null) { %>

                            <div class="alert alert-danger mt-3">
                                <%= errorMessage %>
                            </div>

                            <% } else { %>

                            <div class="alert alert-danger mt-3 hidden-message"></div>

                            <% } if (successMessage != null) { %>

                            <div class="alert alert-success mt-3">
                                <%= successMessage %>

                                <% } else { %>

                                <div class="alert alert-success mt-3 hidden-message"></div>

                                <% } %>   
                            </div>

                            <% } else { %>
                            <p>No account details available.</p>

                            <% } %>

                        </div>

                    </div>

                </div>

            </div>
            <%@include file = "footer.jsp" %>
    </body>
    <script src='https://code.jquery.com/jquery-1.10.2.min.js'></script>
    <script src='https://cdn.jsdelivr.net/npm/bootstrap@4.1.1/dist/js/bootstrap.bundle.min.js'></script>

</html>
