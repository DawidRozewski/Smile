<%@ page contentType="text/html; charset=UTF-8" %>
<%@include file="temps/header.jsp" %>
<head>
    <title>Harmonogram pacjentów</title>
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
        <th>Imię i nazwisko</th>
        <th>Opis zabiegu</th>
        <th>Cena</th>
        <th>Data wizyty</th>
        <th>Godzina</th>
        <th>Pokaż dane</th>
        <th>Usuń wizytę</th>
    </tr>


        <c:forEach var="a" items="${appointment}">
    <tr>
        <td>${a.patient.fullName}</td>
        <td>${a.serviceDescription}</td>
        <td>${a.price}</td>
        <td>${a.date}</td>
        <td>${a.time}</td>
        <td><a href="/app/doctor/patient/${a.patient.id}">Pokaż</a></td>
        <td><a href="/app/doctor/remove-appointment/${a.id}/${a.patient.id}">Usuń wizytę</a></td>
    </tr>
        </c:forEach>

</table>
</body>