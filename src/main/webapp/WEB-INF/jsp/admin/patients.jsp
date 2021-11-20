<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<div id="menu">
    <strong><a href="/app/admin/patients">Pacjenci</a></strong>&nbsp;&nbsp;&nbsp;
    <strong><a href="/app/admin/doctors">Doktorzy</a></strong>&nbsp;&nbsp;&nbsp;
    <strong><a href="/logout">Wyloguj</a></strong>&nbsp;&nbsp;&nbsp;
</div>
<br/>
<hr/>
<br/>
<head>
    <title>Pacjenci</title>
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
            <td><a href="/app/admin/patients/remove/${p.id}">Usuń</a></td>
        </tr>
    </c:forEach>
</table>
<br/>

<sec:authorize access="isAuthenticated()">
    <p>Zalogowany jako: <sec:authentication property="principal.username"/></p>
    <p>Posiada role: <sec:authentication property="authorities"/></p>
</sec:authorize>

</body>