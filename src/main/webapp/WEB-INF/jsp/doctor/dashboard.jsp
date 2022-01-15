<%@ page contentType="text/html; charset=UTF-8" %>
<%@include file="temps/header.jsp"%>
<head>
    <title>Treatment plan</title>
    <style>
        table, th, td {
            border-collapse: collapse;
            border: 1px solid black;
            padding: 5px;
        }

        th {
            background-color: #ccc;
        }
    </style>
    <script type="text/javascript">
        function clearSearch() {
            window.location = "https://just-smile.herokuapp.com/app/doctor/dashboard";
        }
    </script>
</head>

<h3>Find a patient: </h3>
<form method="get" action="/app/doctor/dashboard">
    PESEL: <input type="text" name="pesel"  size="15" value="${pesel}"/>
    <input type="submit" value="Search" />
    <input type="button" value="Clear"  onclick="clearSearch()" />
</form>

<table>
    <tr>
        <th>ID</th>
        <th>Patient</th>
        <th>PESEL</th>
        <th>Details</th>

    </tr>

    <c:forEach var="p" items="${patients}">
        <tr>
            <td>${p.id}</td>
            <td>${p.fullName}</td>
            <td>${p.pesel}</td>
            <td><a href="/app/doctor/patient/${p.id}">Show details</a></td>
        </tr>
    </c:forEach>

</table>

</body>