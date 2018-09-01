<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/WEB-INF/fragments/login-css.jspf" %>
    <%--<link type="text/css" rel="stylesheet" href="<c:url value="/css/login.css"/>"  id="login-css">--%>

    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="https://code.jquery.com/jquery-3.2.1.min.js"
            integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4="
            crossorigin="anonymous"></script>
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
            <form id="Login" method="post">

                <div class="form-group">

                    <input type="text" name="username" class="form-control" placeholder="Nazwa użytkownika">

                </div>

                <div class="form-group">

                    <input type="password" name="password" class="form-control" placeholder="Hasło">

                </div>
                <div class="forgot">
                    <security:authorize access='!isAuthenticated()'>
                        <p>
                            <a href='register'>Zarejestruj</a>
                        </p>
                    </security:authorize>
                    <security:authorize access='isAuthenticated()'>
                        <p>
                            Jesteś zalogowany jako: <security:authentication property='principal.username'/>
                            <br><a href='/logout'> Wyloguj </a>
                        </p>
                    </security:authorize>
                </div>
                <input type="hidden" name="${_csrf.parameterName}"
                       value="${_csrf.token}"/>
                <button type="submit" class="btn btn-primary">Zaloguj</button>

            </form>
        </div>
    </div>
</div>

</body>
</html>