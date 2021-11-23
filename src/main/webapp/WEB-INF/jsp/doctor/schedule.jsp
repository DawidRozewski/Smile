<%@ page contentType="text/html; charset=UTF-8" %>
<%@include file="temps/header.jsp" %>
<head>
    <title>Harmonogram pacjentow</title>
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
        <th>Data wizyty</th>
        <th>Imie i nazwisko</th>
        <th>Opis zabiegu</th>
        <th>Czas</th>
        <th>Przypomnij</th>
    </tr>


    <%--    <c:forEach var="" items="${}">--%>
    <tr>
        <td>14.11.2021 16:00</td>
        <td>Jan Kowalski</td>
        <td>Usuwanie kamienia</td>
        <td>60 minut</td>
        <td><a href="">SMS</a></td>
    </tr>
    <%--    </c:forEach>--%>

</table>
<p>Posortowane od najblizszych od dnia dzisiejszego.</p>
</body>