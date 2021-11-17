<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<div id="menu">
    <strong><a href="/admin/patients">Pacjenci</a></strong>&nbsp;&nbsp;&nbsp;
    <strong><a href="/admin/doctors">Doktorzy</a></strong>&nbsp;&nbsp;&nbsp;
    <strong><a href="/admin/logout">Wyloguj</a></strong>&nbsp;&nbsp;&nbsp;
</div>
<br/>
<hr/>
<br/>
<a href="/admin/doctors/add">Dodaj doktora</a><br/>
<head>
    <title>Doktorzy</title>
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
        <th>Imię i nazwisko</th>
        <th>Email</th>
        <th>Nr.kontaktowy</th>
        <th>Usuń</th>
    </tr>

    <c:forEach items="${doctors}" var="d">
        <tr>
            <td>${d.id}</td>
            <td>${d.fullName}</td>
            <td>${d.email}</td>
            <td>${d.phoneNumber}</td>
            <td><a href="/admin/doctors/remove/${d.id}">Usuń</a></td>
        </tr>
    </c:forEach>
</table>
<br/>
<%--onclick="return confirm('Na pewno chcesz usunąć tego pacjenta?')--%>
</body>