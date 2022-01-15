<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<head>
    <title>My files</title>
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

<h2>List of files for the visit of ${appointment.date}</h2>
<div>
    <table>
        <tr>
            <th>ID</th>
            <th>Filename</th>
            <th>Link</th>
        </tr>

        <c:forEach var="d" items="${documents}">
            <tr>
                <td>${d.id}</td>
                <td>${d.name}</td>
                <td><a href="/app/file/download/${d.id}">Download</a></td>
            </tr>
        </c:forEach>
    </table>
</div>
