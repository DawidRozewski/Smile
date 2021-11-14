<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@include file="dashboard.jsp"%>
<hr>
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
        <th>Pokaz dane</th>
        <th>Historia leczenia</th>
        <th>Kolejna wizyta</th>
        <th>Usun</th>
    </tr>


    <%--    <c:forEach var="" items="${}">--%>
    <tr>
        <td>1</td>
        <td>Jan Kowalski</td>
        <td><a href="">Pokaz</a> </td>
        <td><a href="">Pokaz historie</a> </td>
        <td>20.11.2020 17:00</td>
        <td><a href="">Usuń</a> </td>
    </tr>
    <%--    </c:forEach>--%>

</table>

</body>