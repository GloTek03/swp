<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Confirm OTP</title>
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
                    <h2>Confirm OTP</h2>
                    <br>
                    <% 
                        String username = request.getAttribute("username") != null ? (String) request.getAttribute("username") : "";
                    %>
                    <form action="resetPassword" method="post">
                        <input type="hidden" name="action" value="confirm">
                        <input type="hidden" name="username" value="<%= username%>">
                        <div class="form-group">
                            <label for="otp">Vui lòng nhập OTP trong Email của bạn:</label><br>
                            <input type="text" id="otp" name="otp" required class="form-control"><br>
                        </div>
                        <button type='submit' class='btn btn-primary btn-block'>Submit</button> 
                    </form>

                    <% if (request.getAttribute("error") != null) { %>

                    <div class='alert alert-danger mt-3'>
                        <%= request.getAttribute("error") %>
                    </div>

                    <% } %>

                </div>

            </div>

        </div>

        <script src='https://code.jquery.com/jquery-1.10.2.min.js'></script>
        <script src='https://cdn.jsdelivr.net/npm/bootstrap@4.1.1/dist/js/bootstrap.bundle.min.js'></script>

    </body>
</html>
