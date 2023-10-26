<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Change Password</title>
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
        </style>
    </head>
    <body>
        <%@include file = "menu.jsp" %>
        <%@include file = "navbar.jsp" %>
        <div class="container">
            <div class="row justify-content-center">
                <div class="col-md-6">
                    <h2>Change Password</h2>
                    <br>
                    <% 
                        String username = request.getAttribute("username") != null ? (String) request.getAttribute("username") : "";
                    %>
                    <form action="changePassword" method="post">
                        <input type="hidden" name="username" value="<%= username %>" />
                        <div class="form-group">
                            <label for="old_password">Mật khẩu hiện tại:</label><br>
                            <input type="password" id="old_password" name="old_password" class="form-control"><br>
                        </div>
                        <div class="form-group">
                            <label for="new_password">Mật khẩu mới:</label><br>
                            <input type="password" id="new_password" name="new_password" class="form-control"><br>
                        </div>
                        <div class="form-group">
                            <label for="confirm_new_password">Xác nhận mật khẩu mới:</label><br>
                            <input type="password" id="confirm_new_password" name="confirm_new_password" class="form-control"><br>
                        </div>
                        <button type='submit' class='btn btn-primary btn-block'>Change Password</button> 
                    </form>

                    <% String message = (String) request.getAttribute("message"); %>

                    <% if (message != null) { %>

                    <div class='alert alert-info mt-3'>
                        <%= message %>
                    </div>

                    <% } %>

                </div>

            </div>

        </div>
         <%@include file = "footer.jsp" %>           
        <script src='https://code.jquery.com/jquery-1.10.2.min.js'></script>
        <script src='https://cdn.jsdelivr.net/npm/bootstrap@4.1.1/dist/js/bootstrap.bundle.min.js'></script>

    </body>
</html>
