<%-- 
    Document   : landingPage
    Created on : Sep 13, 2023, 4:29:51 PM
    Author     : acer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f7f7f7;
                margin: 0;
                padding: 0;
            }

            .header {
                background-color: #3498db;
                color: #fff;
                text-align: center;
                padding: 80px 0;
            }

            .header h1 {
                font-size: 36px;
            }

            .header p {
                font-size: 18px;
            }

            .cta-button {
                display: inline-block;
                padding: 15px 30px;
                background-color: #e74c3c;
                color: #fff;
                text-decoration: none;
                font-size: 20px;
                margin-top: 20px;
                border-radius: 5px;
            }

            .cta-button:hover {
                background-color: #c0392b;
            }

            .features {
                background-color: #fff;
                padding: 60px 0;
                text-align: center;
            }

            .feature {
                margin: 0 20px;
                flex: 1;
            }

            .feature h2 {
                font-size: 24px;
                margin-bottom: 10px;
            }

            .feature p {
                font-size: 16px;
                color: #777;
            }

            .footer-main{
                bottom: 0;
                left: 50%;
                text-align: center;
                padding: 20px 0;
                background-color: #3498db;
                color: #fff;
            }
        </style>
    </head>
    <body>
        <div class="header">
            <div class="container">
                <h1 class="display-4">LMS - Học mọi lúc, mọi nơi</h1>
                <p class="lead">Giải pháp học trực tuyến chất lượng cao cho bạn</p>
                <!--<a class="btn btn-primary btn-lg" href="#signup">Đăng ký ngay</a>-->
                <a class="btn btn-success btn-lg" href="login">Đăng Nhập ngay</a>
                <a class="btn btn-success btn-lg" href="login.jsp" style="background: #e74c3c">Đăng Ký Tại Đây</a>
            </div>
        </div>

        <div class="container features">
            <div class="row">
                <div class="col-md-4">
                    <h2>Đa dạng môn học</h2>
                    <p>Hơn 100 khóa học từ các lĩnh vực khác nhau.</p>
                </div>
                <div class="col-md-4">
                    <h2>Học mọi lúc, mọi nơi</h2>
                    <p>Truy cập từ máy tính, điện thoại hoặc máy tính bảng.</p>
                </div>
                <div class="col-md-4">
                    <h2>Hỗ trợ 24/7</h2>
                    <p>Chúng tôi luôn sẵn sàng hỗ trợ bạn mọi lúc.</p>
                </div>
            </div>
        </div>

        <div class="footer-main">
            <div class="container">
                &copy; Director, 2014
            </div>
        </div>

        <footer class="bg-dark text-white text-center py-4">
            <div class="container">
                <div class="row">
                    <div class="col-md-6">
                        <h4>Liên hệ</h4>
                        <p>Địa chỉ: Khu Giáo dục và Đào tạo Khu Công nghệ cao Hòa Lạc Km29, Đại lộ Thăng Long, Thạch Hoà, Thạch Thất, Hà Nội 13100</p>
                        <p>Email: trungnqhe172809@fpt.edu.vn</p>
                        <p>Điện thoại: 0394672294</p>
                    </div>
                    <div class="col-md-6">
                        <h4>Liên kết nhanh</h4>
                        <ul class="list-unstyled">
                            <li><a href="login.jsp">Trang chủ</a></li>
                            <li><a href="#">Khóa học</a></li>
                            <li><a href="#">Về chúng tôi</a></li>
                            <li><a href="#">Liên hệ</a></li>
                        </ul>
                    </div>
                </div>
            </div>
        </footer>

        <!-- Bootstrap JS and jQuery -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    </body>
</html>
