<%@include file="temps/header.jsp" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<head>
    <title>Dashboard</title>
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
<h2>Upcoming visits</h2>
<table>
    <tr>
        <th>Date</th>
        <th>Time</th>
        <th>Service description</th>
        <th>Price</th>
    </tr>
    <c:forEach var="a" items="${appointments}">
        <tr>
            <td hidden="${a.id}"></td>
            <td>${a.date}</td>
            <td>${a.time}</td>
            <td>${a.serviceDescription}</td>
            <td>${a.price}</td>
        </tr>
    </c:forEach>

</table>