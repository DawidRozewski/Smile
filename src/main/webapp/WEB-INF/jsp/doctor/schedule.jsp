<%@ page contentType="text/html; charset=UTF-8" %>
<%@include file="temps/header.jsp" %>
<head>
    <title>Schedule</title>
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
        <th>Patient</th>
        <th>Service description</th>
        <th>Price</th>
        <th>Date</th>
        <th>Time</th>
        <th>Show patient details</th>
        <th>Delete visit</th>
    </tr>


        <c:forEach var="a" items="${appointment}">
    <tr>
        <td>${a.patient.fullName}</td>
        <td>${a.serviceDescription}</td>
        <td>${a.price}</td>
        <td>${a.date}</td>
        <td>${a.time}</td>
        <td><a href="/app/doctor/patient/${a.patient.id}">Show</a></td>
        <td><a href="/app/doctor/remove-appointment/${a.id}/${a.patient.id}">Delete</a></td>
    </tr>
        </c:forEach>

</table>
</body>