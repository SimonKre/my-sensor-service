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
    <div><h4>Typy sensorów:</h4></div>
    <div class="table-responsive">
        <table class="table table-striped table-hover table-condensed">
            <thead>
            <tr>
                <th>Nazwa</th>
                <th>Opis</th>
                <th>Ilość typów danych</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${sensorTypes}" var="sensorT">
                <tr>
                    <td>${sensorT.name}</td>
                    <td>${sensorT.description}</td>
                    <td>${sensorT.sensorDataTypes.size()}</td>

                    <td>
                        <a href="<c:url value='/admin/sensor-types/${sensorT.id}/edit'/>">Edytuj</a>
                        <a href="<c:url value='/admin/sensor-types/${sensorT.id}/delete'/>">Usuń</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

    <form:form class="form" method="post" modelAttribute="sensorType">
        <div class="input-group mb-3">

            <div class="input-group-prepend">
                <span class="input-group-text">Dodaj typ sensora:)</span>
            </div>

            <div class="form-control">

                <form:input type="string" path="name" class="form-control form-control-sm"
                            placeholder="Nazwa"></form:input>
                <form:errors path="name" cssClass="form-text text-danger" element="small"/>
            </div>
            <div class="form-control">

                <form:input type="string" path="description" class="form-control form-control-sm"
                            placeholder="Opis typu"></form:input>
                <form:errors path="description" cssClass="form-text text-danger" element="small"/>
            </div>
            <div class="input-group-prepend">
                <span class="input-group-text">Dane (wielokrotne zaznaczenie):</span>
            </div>
            <div class="form-control">
                <form:select path="sensorDataTypes" items="${sensorDataTypes}"
                             itemValue="id" itemLabel="description"
                             class="form-control form-control-sm" multiple="true">
                </form:select>
                <form:errors path="sensorDataTypes" cssClass="form-text text-danger" element="small"/>
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
