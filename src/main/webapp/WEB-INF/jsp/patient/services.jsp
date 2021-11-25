<%@ page contentType="text/html; charset=UTF-8" %>
<%@include file="temps/header.jsp" %>
<head>
    <title>Cennik zabiegów</title>
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
        <th>Opis zabiegu</th>
        <th>RTG</th>
        <th>Cena</th>
        <th>Zaplanuj wizyte</th>
    </tr>

    <c:forEach var="s" items="${services}">
        <tr>
            <td>${s.id}</td>
            <td>${s.description}</td>
            <td>${s.RTG}</td>
            <td>${s.amount}</td>
            <td><a href="/app/patient/appointment?serviceID=${s.id}">Zaplanuj wizyte</a></td>
        </tr>
    </c:forEach>
</table>
<br/>
