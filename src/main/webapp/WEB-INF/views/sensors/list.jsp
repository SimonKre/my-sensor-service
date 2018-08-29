<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Sensory</title>
    <%@ include file="/WEB-INF/fragments/bootstrap.jspf" %>
    <%@ include file="/WEB-INF/fragments/background.jspf" %>
    <%@ include file="/WEB-INF/fragments/header.jspf" %>
</head>

<body>

<div class="container-fluid">
    <div><h4>Twoje sensory:</h4></div>
    <div class="table-responsive">
        <table class="table table-striped table-hover table-condensed">
            <thead>
            <tr>
                <th>Nazwa sensora</th>
                <th>Typ</th>
                <th>Opis</th>
                <th>Ilość typów danych</th>
                <th>Numer seryjny</th>
                <th>Ilość alertów</th>
                <th>Lokalizacja</th>
                <th>Akcje</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${sensors}" var="sensor">
                <tr>
                    <td>${sensor.name}</td>
                    <td>${sensor.sensorType.name}</td>
                    <td>${sensor.sensorType.description}</td>
                    <td>${sensor.sensorType.sensorDataTypes.size()}</td>
                    <td>${sensor.serialNumber}</td>
                    <td>${sensor.alerts.size()}</td>
                    <td>${sensor.location}</td>
                    <td>
                        <a href="<c:url value='/sensor/${sensor.id}/alerts'/>">Alerty</a>
                        <a href="<c:url value='/sensor/${sensor.id}/chart'/>">Wykres</a>
                        <a href="<c:url value='/sensor/${sensor.id}/delete'/>">Usuń</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

    <form:form class="form" method="post" modelAttribute="sensor">
        <div class="input-group mb-3">

            <div class="input-group-prepend">
                <span class="input-group-text">Dodaj sensor:)</span>
            </div>

            <div class="form-control">

                <form:input type="string" path="name" class="form-control form-control-sm"
                            placeholder="Nazwa"></form:input>
                <form:errors path="name" cssClass="form-text text-danger" element="small"/>
            </div>
            <div class="form-control">

                <form:input type="string" path="serialNumber" class="form-control form-control-sm"
                            placeholder="Numer seryjny"></form:input>
                <form:errors path="serialNumber" cssClass="form-text text-danger" element="small"/>
            </div>
            <div class="input-group-prepend">
                <span class="input-group-text">Typ sensora:</span>
            </div>
            <div class="form-control">
                <form:select type="number" path="sensorType.id"
                              itemLabel="name"
                             class="form-control form-control-sm">
                    <form:option value="0" label="--Select phone"/>
                    <form:options items="${sensorTypes}" itemValue="id"/>
                </form:select>
                <form:errors path="sensorType.id" cssClass="form-text text-danger" element="small"/>
            </div>
            <div class="form-control">

                <form:input type="string" path="location" class="form-control form-control-sm"
                            placeholder="Lokalizacja"></form:input>
                <form:errors path="location" cssClass="form-text text-danger" element="small"/>
            </div>
            <form:input path="id" type="hidden"/>
            <div class="input-group-append">
                <button type="submit" class="btn btn-success">Dodaj</button>
            </div>
        </div>
    </form:form>
</div>
</body>
</html>
