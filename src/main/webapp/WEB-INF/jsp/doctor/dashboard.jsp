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
        <th>Id</th>
        <th>Imie i nazwisko</th>
        <th>Pesel</th>
        <th>Pokaz dane</th>

    </tr>

    <c:forEach var="p" items="${patients}">
        <tr>
            <td>${p.id}</td>
            <td>${p.fullName}</td>
            <td>${p.pesel}</td>
            <td><a href="/app/doctor/patient/${p.id}">Pokaz</a></td>
        </tr>
    </c:forEach>

</table>

<h2>Zbliżające się wizyty</h2>
<table>
    <tr>
        <th>Id</th>
        <th>Data</th>
        <th>Godzina</th>
        <th>Opis</th>
        <th>Cena</th>
    </tr>

    <c:forEach var="a" items="${appointments}">
        <tr>
            <td>${a.id}</td>
            <td>${a.date}</td>
            <td>${a.time}</td>
            <td>${a.serviceDescription}</td>
            <td>${a.price}</td>
        </tr>
    </c:forEach>
</table>
</body>