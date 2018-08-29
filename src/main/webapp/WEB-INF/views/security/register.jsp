<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <script src="https://code.jquery.com/jquery-3.2.1.min.js"
            integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4="
            crossorigin="anonymous"></script>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
    <%--<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>--%>
    <script src="https://code.jquery.com/jquery-3.2.1.min.js"
            integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4="
            crossorigin="anonymous"></script>
    <Style>
        body, html {
            height: 100%;
            background-image: url("https://edge.alluremedia.com.au/m/g/2015/11/shutterstock_277469792_1080.jpg");
            background-repeat: no-repeat;
            background-position: center;
            background-size: cover;
            background-attachment: fixed;
            padding: 10px;
        }

        .form-heading {
            color: #fff;
            font-size: 23px;
        }

        /*.panel h2{ color:#444444; font-size:18px; margin:0 0 8px 0;}*/
        /*.panel p { color:#777777; font-size:14px; margin-bottom:30px; line-height:24px;}*/
        .login-form .form-control {
            background: #f7f7f7 none repeat scroll 0 0;
            border: 1px solid #d4d4d4;
            border-radius: 4px;
            font-size: 14px;
            height: 50px;
            line-height: 50px;
        }

        .main-div {
            background: #ffffff none repeat scroll 0 0;
            border-radius: 2px;
            margin: 10px auto 10px;
            max-width: 400px;
            padding: 50px 70px 70px 71px;
            -webkit-box-shadow: 0px 0px 141px 12px rgba(0,0,0,0.64);
            -moz-box-shadow: 0px 0px 141px 12px rgba(0,0,0,0.64);
            box-shadow: 0px 0px 141px 12px rgba(0,0,0,0.64);
        }

        .login-form .form-group {
            margin-bottom: 10px;
        }

        .login-form {
            text-align: center;
        }

        .forgot a {
            color: #777777;
            font-size: 14px;
            text-decoration: underline;
        }

        .login-form .btn.btn-primary {
            background: #f0ad4e none repeat scroll 0 0;
            border-color: #f0ad4e;
            color: #ffffff;
            font-size: 14px;
            width: 100%;
            height: 50px;
            line-height: 50px;
            padding: 0;
        }

        .forgot {
            text-align: left;
            margin-bottom: 30px;
        }

        .botto-text {
            color: #ffffff;
            font-size: 14px;
            margin: auto;
        }

        .login-form .btn.btn-primary.reset {
            background: #ff9900 none repeat scroll 0 0;
        }

        .back {
            text-align: left;
            margin-top: 10px;
        }

        .back a {
            color: #444444;
            font-size: 13px;
            text-decoration: none;
        }

    </Style>

</head>
<body>
<p class='error'>${msg}</p>
<div class="container">
    <div class="login-form">
        <div class="main-div">
            <div class="panel">
                <h4>MySensor - Be aware!</h4>
                <p>Your IoT information hub</p>
                <h5>${msg}</h5>
            </div>
            <form:form modelAttribute="user" id="Login" method="post" enctype="utf8">
                <div class="form-group">
                    <form:input path="username" class="form-control" placeholder="Nazwa użytkownika"/>
                </div>
                <div class="form-group">
                    <form:input type="email" path="email" class="form-control" placeholder="E-mail"/>
                </div>
                <div class="form-group">
                    <form:password path="password" class="form-control" placeholder="Hasło"/>
                </div>
                <div class="form-group">
                    <form:password path="matchingPassword" class="form-control" placeholder="Powtórz hasło"/>
                </div>

                <input type="hidden" name="${_csrf.parameterName}"
                       value="${_csrf.token}"/>
                <div class="form-group">
                    <form:errors path="*"/>
                </div>
                <button type="submit" class="btn btn-primary">Zarejestruj</button>
            </form:form>
        </div>
    </div>
</div>
</body>
</html>