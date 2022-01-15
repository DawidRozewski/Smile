<%@ page contentType="text/html; charset=UTF-8" %>
<%@include file="temps/header.jsp" %>
<head>
    <title>Services</title>
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
        <th>ID</th>
        <th>Description</th>
        <th>RTG</th>
        <th>Price</th>
        <th>Book an appointment</th>
    </tr>

    <c:forEach var="s" items="${services}">
        <tr>
            <td>${s.id}</td>
            <td>${s.description}</td>
            <td>${s.RTG}</td>
            <td>${s.amount}</td>
            <td><a href="/app/patient/appointment?serviceID=${s.id}">Book now</a></td>
        </tr>
    </c:forEach>
</table>
<br/>
