<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<div id="menu">
    <strong><a href="/admin/patients">Pacjenci</a></strong>
    <strong><a href="/admin/doctors">Doktorzy</a></strong>
    <strong><a href="/admin/logout">Wyloguj</a></strong>
</div>
<br/>
<hr/>
<br/>
<head>
    <title>Patients</title>
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
        <th>PESEL</th>
        <th>Nr.kontaktowy</th>
        <th>Usuń</th>
    </tr>

    <c:forEach items="${patients}" var="p">
        <tr>
            <td>${p.id}</td>
            <td>${p.fullName}</td>
            <td>${p.email}</td>
            <td>${p.pesel}</td>
            <td>${p.phoneNumber}</td>
            <td><a href="remove/${p.id}">Usuń</a></td>
        </tr>
    </c:forEach>
</table>
<br/>
<%--onclick="return confirm('Na pewno chcesz usunąć tego pacjenta?')--%>
</body>