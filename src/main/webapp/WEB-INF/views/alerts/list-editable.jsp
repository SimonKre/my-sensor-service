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
    <title>Alerty</title>
    <%@ include file="/WEB-INF/fragments/bootstrap.jspf" %>
    <%@ include file="/WEB-INF/fragments/background.jspf" %>
    <%@ include file="/WEB-INF/fragments/header.jspf" %>

</head>
<body>

<div class="container-fluid">
    <div><h4>Alerty dla sensora ${sensor.name} (${sensor.serialNumber})</h4></div>
    <div class="table-responsive">
        <table class="table table-striped table-hover table-condensed">
            <thead>
            <tr>
                <th>Nazwa</th>
                <th>Typ danych</th>
                <th>Alert gdy:</th>
                <th>Wartość graniczna</th>
                <th>Tolerancja</th>
                <th>Sensor</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${sensor.alerts}" var="alert">
                <tr>
                    <td>${alert.name}</td>
                    <td>${alert.sensorDataType.description}</td>
                    <td>${alert.isOverThresholdAlert ? alert.sensorDataType.dataHighAlert : alert.sensorDataType.dataLowAlert}</td>
                    <td>${alert.threshold}</td>
                    <td>+/- ${alert.sensorDataType.histeresis}</td>
                    <td>${alert.sensor.id}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

        <c:forEach items="${sensor.alerts}" var="alert">
            <form:form class="form" method="post" modelAttribute="alert">
                <div class="input-group mb-3">

                    <div class="input-group-prepend">
                        <span class="input-group-text">Alert</span>
                    </div>
                        <%--<form:label path="name">Nazwa</form:label>--%>
                    <div class="form-control">
                        <form:select path="isOverThresholdAlert" class="form-control form-control-sm" placeholder="Zakres powyżej" value="${alert.isOverThresholdAlert}">
                            <form:option value="true" label="powyżej"/>
                            <form:option value="false" label="poniżej"/>
                        </form:select>
                        <form:errors path="isOverThresholdAlert" cssClass="form-text text-danger" element="small"/>
                    </div>

                    <div class="form-control">
                        <form:input type="string" path="name" class="form-control form-control-sm" placeholder="Nazwa"
                                    value="${alert.name}"></form:input>
                            <%--<small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>--%>
                        <form:errors path="name" cssClass="form-text text-danger" element="small"/>
                    </div>

                    <div class="form-control">
                            <%--<form:label path="description">Opis</form:label>--%>
                        <form:input type="number" path="threshold" class="form-control form-control-sm"
                                    placeholder="Próg zadziałania" value="${alert.threshold}"></form:input>
                        <form:errors path="threshold" cssClass="form-text text-danger" element="small"/>
                            <%--<small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>--%>
                    </div>
                        <%--<form:input path="id" value="${alert.id}" type="hidden"/>--%>
                    <form:input path="sensor.id" value="${alert.sensor.id}" type="hidden"/>
                    <form:input path="id" value="${alert.id}" type="hidden"/>
                    <form:input path="sensorDataType.id" value="${alert.sensorDataType.id}" type="hidden"/>
                    <div class="input-group-append">
                    <button type="submit" class="btn btn-success">Zapisz</button>
                    </div>
                </div>
            </form:form>
        </c:forEach>
    </div>
</div>
</body>
</html>
