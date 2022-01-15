<%@ page contentType="text/html; charset=UTF-8" %>
<%@include file="temps/header.jsp" %>
<head>
    <title>My treatment plan</title>
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
        <th>Order of visits</th>
        <th>Service description</th>
        <th>Date</th>
        <th>Price</th>
        <th>Book an appointment</th>
    </tr>

    <c:forEach var="t" items="${treatmentPlan}">
        <tr>
            <td>${t.visitNumber}</td>
            <td>${t.description}</td>
            <td>${t.visitDate}</td>
            <td>${t.price}</td>
            <td><a href="/app/patient/appointment-by-plan?planID=${t.id}">Book now</a></td>
        </tr>
    </c:forEach>
</table>

</body>