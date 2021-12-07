<%@ page contentType="text/html; charset=UTF-8" %>
<%@include file="temps/header.jsp"%>
<head>
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

<h2>Historia wizyt</h2>
<table>
    <tr>
        <th>Data wizyty</th>
        <th>Godzina</th>
        <th>Opis</th>
        <th>Pliki</th>
    </tr>
    <c:forEach var="a" items="${appointments}">
        <tr>
            <td>${a.date}</td>
            <td>${a.time}</td>
            <td>${a.serviceDescription}</td>
            <td><a href="/app/file/show/${a.id}/${patient.id}">Pokaż</a> </td>
        </tr>
    </c:forEach>
</table>
