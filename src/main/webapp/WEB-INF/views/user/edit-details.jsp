<%--
  Created by IntelliJ IDEA.
  User: szymon
  Date: 06.08.18
  Time: 22:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Dane użytkownika</title>
    <%@ include file="/WEB-INF/fragments/bootstrap.jspf" %>
    <%@ include file="/WEB-INF/fragments/header.jspf" %>
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

        .login-form .btn.btn-success.reset {
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

<div class="container">
    <div class="login-form">
        <div class="main-div">
            <div class="panel">
                <h4>MySensor - Be aware!</h4>
                <p>Your IoT information hub</p>
                <h5>Cześć ${userName}!</h5>
                <p>Uzupełnij swoje dane:</p>
            </div>

            <form:form class="form" method="post" modelAttribute="userDetails">


                <div class="form-group">
                    <form:input type="string" path="name" class="form-control form-control-sm"
                                placeholder="Imię"></form:input>
                    <form:errors path="name" cssClass="form-text text-danger" element="small"/>
                </div>
                <div class="form-group">
                    <form:input type="string" path="surname" class="form-control form-control-sm"
                                placeholder="Nazwisko"></form:input>
                    <form:errors path="surname" cssClass="form-text text-danger" element="small"/>
                </div>
                <p>Telefon (z kodem kraju):</p>
                <div class="form-group">
                    <form:input type="number" path="phone" class="form-control form-control-sm"
                                placeholder="ex. 48303404505"></form:input>
                    <form:errors path="phone" cssClass="form-text text-danger" element="small"/>
                </div>

                <form:input path="id" type="hidden"/>

                    <button type="submit" class="btn btn-primary">Zapisz</button>

            </form:form>
        </div>
    </div>

</div>
</body>
</html>
