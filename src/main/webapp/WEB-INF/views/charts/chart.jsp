    <%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Wykresy danych:</title>
    <%@ include file="/WEB-INF/fragments/bootstrap.jspf" %>
    <%@ include file="/WEB-INF/fragments/background.jspf" %>
    <%@ include file="/WEB-INF/fragments/header.jspf" %>


<%--<script type="text/javascript" src="https://www.google.com/jsapi"></script>--%>
    <%--<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>--%>
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript">
        // setTimeout("location.reload(true);", 10000);
        // Load the Visualization API.
        google.charts.load('current', {'packages':['corechart']});


        // Set a callback to run when the Google Visualization API is loaded.
        google.charts.setOnLoadCallback(drawChart);
        //google.setOnLoadCallback(drawChart);

        function drawChart() {

            // Create our data table out of JSON data loaded from server.
            var data = new google.visualization.DataTable(${jsonData});
            var options = {
                title: 'Sensor: ${sensor.name}',
                curveType: 'function',
                //animation: { startup: 'true' },
                legend: { position: 'top' },
                // Gives each series an axis that matches the vAxes number below.
                timeline: {
                    groupByRowLabel: true
                },
                series: {
                    0: {targetAxisIndex: 2},
                    1: {targetAxisIndex: 0},
                    2: {targetAxisIndex: 1}
                },
                vAxes: {
                    // Adds titles to each axis.
                    0: {title: 'Hum'},
                    1: {title: 'Ciśnienie', minValue: 950,},
                    2: {title: 'Temp', minValue: 0, textPosition: 'in'}
                },
                vAxis: {
                    viewWindow: {

                    },
                    //gridlines: {count: 40},
                    //minValue: 10,
                    //title: 'C'
                },

                hAxis: {
                    //gridlines: {count: 24},
                    //viewWindowMode: 'pretty',
                    format: 'kk:mm',
                }
            };
            // Instantiate and draw our chart, passing in some options.
            // Do not forget to check your div ID
            var chart = new google.visualization.LineChart(document.getElementById('curve_chart'));
            chart.draw(data, options);
        }
    </script>
</head>
<body>

<div class="container-fluid">
    <div><h4>Wykres danych z sensora: ${sensor.name}</h4></div>
        <div id="curve_chart" style="height: 80%; width: 100%"></div>
</div>
</body>
</html>
