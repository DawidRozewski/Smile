<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<div id="menu">
    <strong><a href="/app">Strona głowna</a></strong>&nbsp;&nbsp;&nbsp;
    <strong><a href="/app/admin/patients">Pacjenci</a></strong>
    <strong><a href="/app/admin/doctors">Doktorzy</a></strong>&nbsp;&nbsp;&nbsp;
    <strong><a href="/logout">Wyloguj</a></strong>&nbsp;&nbsp;&nbsp;
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
        <th>First name & last name</th>
        <th>Email</th>
        <th>PESEL</th>
        <th>Phone number</th>
    </tr>

    <c:forEach items="${patients}" var="p">
        <tr>
            <td>${p.id}</td>
            <td>${p.fullName}</td>
            <td>${p.email}</td>
            <td>${p.pesel}</td>
            <td>${p.phoneNumber}</td>
        </tr>
    </c:forEach>
</table>
<br/>
