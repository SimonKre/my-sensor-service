<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/WEB-INF/fragments/login-css.jspf" %>
    <script src="https://code.jquery.com/jquery-3.2.1.min.js"
            integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4="
            crossorigin="anonymous"></script>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
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