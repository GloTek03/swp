<%-- 
    Document   : login
    Created on : Sep 20, 2023, 10:37:08 AM
    Author     : Nguyen Quoc Trumg
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Login</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.3.0/css/all.min.css"
              integrity="sha512-SzlrxWUlpfuzQ+pcUCosxcglQRNAq/DZjVsC0lE40xsADsfeQoEypE+enwcOiGjk/bSuGGKHEyjSoQ1zVisanQ=="
              crossorigin="anonymous" referrerpolicy="no-referrer" />
        <link rel="stylesheet" href="css/loginV2.css?version=2">
        <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    </head>
    <body style="display:flex; align-items:center; justify-content:center;">
        <div class="login-page">
            <div class="form">
                <c:if test="${not empty Error}">
                    <span class="form-signup" style="color:red;">
                        ${Error}
                    </span>
                </c:if>
                <form class="login-form" action="login" method="post">
                    <h2><i class="fas fa-lock"></i> Login</h2>
                    <input type="text" placeholder="Username" name="username" value="${requestScope.username}"  required />
                    <input type="password" placeholder="password" name="password" value="${requestScope.password}" required/>
                    <button type="submit" name="send2">Login</button>
                    <button class="gg">
                        <a  href="https://accounts.google.com/o/oauth2/auth?scope=email&redirect_uri=http://localhost:9999/prj/LoginGoogleHandler&response_type=code&client_id=281726486919-3bu3jqni42p6l9ekn0q4qpvlhllhigdk.apps.googleusercontent.com&approval_prompt=force">
                            Login With Google</a>
                    </button>
                    <p class="message">Not registered? <a href="register">Create an account</a></p>
                    <p class="message">Forgot Password? <a href="/prj/resetPassword">Reset Password</a></p>
                </form>
            </div>
        </div>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script src="/js/main.js"></script>
    </body>
</html>