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
    <div><h4>Typy danych:</h4></div>
    <div class="table-responsive">
        <table class="table table-striped table-hover table-condensed">
            <thead>
            <tr>
                <th>Nazwa parametru</th>
                <th>Opis</th>
                <th>Komunikat za nisko</th>
                <th>Komunikat za wysoko</th>
                <th>Histereza</th>
                <th>Jednostka</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${sensorDataTypes}" var="sensorDataT">
                <tr>
                    <td>${sensorDataT.type}</td>
                    <td>${sensorDataT.description}</td>
                    <td>${sensorDataT.dataLowAlert}</td>
                    <td>${sensorDataT.dataHighAlert}</td>
                    <td>${sensorDataT.histeresis}</td>
                    <td>${sensorDataT.unit}</td>

                    <td>
                        <a href="<c:url value='/admin/sensor-data-types/${sensorDataT.id}/edit'/>">Edytuj</a>
                        <a href="<c:url value='/admin/sensor-data-types/${sensorDataT.id}/delete'/>">Usuń</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

    <form:form class="form" method="post" modelAttribute="sensorDataType">
        <div class="input-group mb-3">

            <div class="input-group-prepend">
                <span class="input-group-text">Dodaj typ sensora:)</span>
            </div>

            <div class="form-control">
                <form:input type="string" path="type" class="form-control form-control-sm"
                            placeholder="Nazwa parametru"></form:input>
                <form:errors path="type" cssClass="form-text text-danger" element="small"/>
            </div>
            <div class="form-control">
                <form:input type="string" path="description" class="form-control form-control-sm"
                            placeholder="Opis typu"></form:input>
                <form:errors path="description" cssClass="form-text text-danger" element="small"/>
            </div>
            <div class="form-control">
                <form:input type="string" path="dataLowAlert" class="form-control form-control-sm"
                            placeholder="Komunikat za nisko"></form:input>
                <form:errors path="dataLowAlert" cssClass="form-text text-danger" element="small"/>
            </div>
            <div class="form-control">
                <form:input type="string" path="dataHighAlert" class="form-control form-control-sm"
                            placeholder="Komunikat za wysoko"></form:input>
                <form:errors path="dataHighAlert" cssClass="form-text text-danger" element="small"/>
            </div>
            <div class="form-control">
                <form:input type="number" step=".001" path="histeresis" class="form-control form-control-sm"
                            placeholder="Histereza"></form:input>
                <form:errors path="histeresis" cssClass="form-text text-danger" element="small"/>
            </div>
            <div class="form-control">
                <form:input type="string" path="unit" class="form-control form-control-sm"
                            placeholder="Jednostka"></form:input>
                <form:errors path="unit" cssClass="form-text text-danger" element="small"/>
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
