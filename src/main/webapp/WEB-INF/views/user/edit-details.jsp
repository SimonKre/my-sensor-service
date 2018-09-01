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
    <%@ include file="/WEB-INF/fragments/login-css.jspf" %>
    <%@ include file="/WEB-INF/fragments/bootstrap.jspf" %>
    <%@ include file="/WEB-INF/fragments/header.jspf" %>
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
                                placeholder="ex. 48303404505" id="phone-number"></form:input>
                    <button id="request-verification-btn" class="btn btn-outline-info">Wyślij sms weryfikacyjny</button>
                    <form:errors path="phone" cssClass="form-text text-danger" element="small"/>
                </div>
                <div class="form-group" id="verify-group" style="display:none">
                    <input type="number" class="form-control form-control-sm"
                           placeholder="Verification Code" id="verification-code"/>
                    <button class="btn btn-outline-info" id="verify-btn">Weryfikuj</button>
                </div>
                <div class="form-group" id="verification-status" style="display:none">
                    <h4>fill in jsp</h4>
                </div>

                <form:input path="id" type="hidden"/>

                <button type="submit" class="btn btn-outline-primary">Zapisz</button>

            </form:form>
        </div>
    </div>

</div>
<script src="<c:url value="/js/phoneVerification.js" />"></script>
</body>
</html>
