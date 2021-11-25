<%@ page contentType="text/html; charset=UTF-8" %>
<%@include file="temps/header.jsp"%>
<head>
    <title>Plan leczenia</title>
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
</head>
<table>
    <tr>
        <th>ID</th>
        <th>Imię i nazwisko</th>
        <th>Pesel</th>
        <th>Pokaż dane</th>

    </tr>

    <c:forEach var="p" items="${patients}">
        <tr>
            <td>${p.id}</td>
            <td>${p.fullName}</td>
            <td>${p.pesel}</td>
            <td><a href="/app/doctor/patient/${p.id}">Pokaż</a></td>
        </tr>
    </c:forEach>

</table>

</body>