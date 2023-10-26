<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Set New Password</title>
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
        <div class="container">
            <div class="row justify-content-center">
                <div class="col-md-6">
                    <h2>Set New Password</h2>
                    <% 
                        String username = request.getAttribute("username") != null ? (String) request.getAttribute("username") : "";
                    %>        
                    <form action="resetPassword" method="post">
                        <input type="hidden" name="action" value="setNewPass">
                        <input type="hidden" name="username" value="<%= username%>">
                        <div class="form-group">
                            <label for="otp">Nhập mật khẩu mới:</label><br>
                            <input type="password" id="newPass" name="newPass" required class="form-control"><br>
                        </div>
                        <button type='submit' class='btn btn-primary btn-block'>Xác nhận</button> 
                    </form>

                    <% if (request.getAttribute("message") != null) { %>

                    <div class='alert alert-info mt-3'>
                        <%= request.getAttribute("message") %>
                    </div>

                    <% } %>

                    <div class='row'>
                        <div class='col-md-12'>
                            <a href='view/login.jsp' class='btn btn-secondary btn-block mt-3'>Quay lại</a>
                        </div>
                    </div>

                </div>

            </div>

        </div>

        <script src='https://code.jquery.com/jquery-1.10.2.min.js'></script>
        <script src='https://cdn.jsdelivr.net/npm/bootstrap@4.1.1/dist/js/bootstrap.bundle.min.js'></script>

    </body>
</html>

