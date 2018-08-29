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
    <div><h4>Edycja Alertu sensora: ${sensor.name} (${sensor.serialNumber})</h4></div>
    <div class="table-responsive">

        <form:form class="form" method="post" modelAttribute="alert">
            <div class="input-group mb-3">

                <div class="input-group-prepend">
                    <span class="input-group-text">Alert (sensor: ${sensor.name})</span>
                </div>

                <div class="form-control">

                    <form:input type="string" path="name" class="form-control form-control-sm"
                                placeholder="Nazwa"></form:input>
                        <%--<small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>--%>
                    <form:errors path="name" cssClass="form-text text-danger" element="small"/>
                </div>
                <div class="input-group-prepend">
                    <span class="input-group-text">Ostrzegaj gdy:</span>
                </div>
                <div class="form-control">
                    <form:select path="sensorDataType.id" items="${sensor.sensorType.sensorDataTypes}"
                                 itemValue="id" itemLabel="description"
                                 class="form-control form-control-sm"></form:select>
                    <form:errors path="sensorDataType.id" cssClass="form-text text-danger" element="small"/>
                </div>

                <div class="form-control">
                    <form:select path="overThresholdAlert" class="form-control form-control-sm">
                        <form:option value="true" label="przekroczy:"/>
                        <form:option value="false" label="spadnie poniżej:"/>
                    </form:select>
                    <form:errors path="overThresholdAlert" cssClass="form-text text-danger" element="small"/>
                </div>

                <div class="form-control">
                        <%--<form:label path="description">Opis</form:label>--%>
                    <form:input type="number" step="0.01" path="threshold" class="form-control form-control-sm"
                                placeholder="Próg zadziałania"></form:input>
                    <form:errors path="threshold" cssClass="form-text text-danger" element="small"/>
                        <%--<small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>--%>
                </div>
                <div class="input-group-prepend">
                    <span class="input-group-text">Aktywny:</span>
                </div>
                <div class="form-control">
                    <form:select path="enabled" class="form-control form-control-sm">
                        <form:option value="true" label="TAK"/>
                        <form:option value="false" label="NIE"/>
                    </form:select>
                    <form:errors path="enabled" cssClass="form-text text-danger" element="small"/>
                </div>
                    <%--<div class="input-group-prepend">--%>
                    <%--<span class="input-group-text">${alert.sensorDataType.unit} (+/- ${alert.sensorDataType.histeresis}${alert.sensorDataType.unit})</span>--%>
                    <%--</div>--%>

                <form:input path="id" type="hidden"/>
                <form:input path="sensor.id" type="hidden"/>
                <div class="input-group-append">
                    <button type="submit" class="btn btn-success">Zapisz</button>
                </div>
            </div>
        </form:form>
    </div>
</div>
</body>
</html>
