<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@include file="temps/header.jsp"%>
<head>
    <title>Lista plików</title>
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

<h2>Dodaj pliki dla pacjenta ${patient.fullName}</h2>
<form method="post" action="/app/doctor/uploadFiles/${appointment.id}/${patient.id}" enctype="multipart/form-data">
    <input type="file" name="files" multiple required>
    <button type="submit">Dodaj</button>
</form><br/>

<h2>Lista plików do wizyty z dnia ${appointment.date}</h2>
<div>
    <table>
        <tr>
            <th>ID</th>
            <th>Nazwa pliku</th>
            <th>Link</th>
            <th>Usuń</th>
        </tr>

        <c:forEach var="d" items="${documents}">
            <tr>
                <td>${d.id}</td>
                <td>${d.name}</td>
                <td><a href="/app/file/download/${d.id}">Pobierz</a></td>
                <td><a href="/app/file/remove/${appointment.id}/${d.id}">Usuń</a></td>
            </tr>

        </c:forEach>
    </table>
</div>
