<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<head>
    <style>
        .test {
            border: blue 2px;
        }
    </style>
</head>

<div id="menu">
    <strong><a href="/app/admin/patients">Patients</a></strong>&nbsp;&nbsp;&nbsp;
    <strong><a href="/app/admin/doctors">Doctors</a></strong>&nbsp;&nbsp;&nbsp;
    <strong><a href="/logout">Logout</a></strong>&nbsp;&nbsp;&nbsp;
</div>
<br/>
<hr/>
<br/>
<h3><a href="/app/admin/doctors/add" class="test">Add doctor</a><br/></h3>
<head>
    <title>Doctors</title>
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
        <th>Phone number</th>
    </tr>
    <c:forEach items="${doctors}" var="d">
        <tr>
            <td>${d.id}</td>
            <td>${d.fullName}</td>
            <td>${d.email}</td>
            <td>${d.phoneNumber}</td>
        </tr>
    </c:forEach>
</table>
<br/>
