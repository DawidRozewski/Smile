<%@ page contentType="text/html; charset=UTF-8" %>
<%@include file="temps/header.jsp" %>
<head>
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
<h2>Patient details</h2>
<table>
    <tr>
        <th>ID</th>
        <th>First name & last name</th>
        <th>PESEL</th>
        <th>Email</th>
        <th>Phone number</th>
        <th>History</th>
    </tr>
    <tr>
        <td>${patient.id}</td>
        <td>${patient.fullName}</td>
        <td>${patient.pesel}</td>
        <td>${patient.email}</td>
        <td>${patient.phoneNumber}</td>
        <td><a href="/app/doctor/history/${patient.id}">Show more</a></td>
    </tr>
</table>


<h2>Treatment plan</h2>
<table>
    <tr>
        <th>Visit number</th>
        <th>Description</th>
        <th>Date</th>
        <th>Price</th>
        <th>Edit visit</th>
        <th>Delete visit</th>
    </tr>
    <c:forEach var="t" items="${treatmentList}">
        <tr>
            <td>${t.visitNumber}</td>
            <td>${t.description}</td>
            <td>${t.visitDate}</td>
            <td>${t.price}</td>
            <td><a href="/app/doctor/edit-treatment/${patient.id}/${t.id}">Edit</a></td>
            <td><a href="/app/doctor/remove/${patient.id}/${t.id}">Delete</a></td>
        </tr>
    </c:forEach>
</table>
<h4><a href="/app/doctor/add-treatment/${patient.id}">Add a treatment plan</a></h4><br/>

<h2>Upcoming visits</h2>
<table>
    <tr>
        <th>Date</th>
        <th>Time</th>
        <th>Description</th>
        <th>Price</th>
        <th>Files</th>
        <th>End the patient visit</th>
    </tr>
    <c:forEach var="a" items="${appointments}">
        <tr>
            <td hidden="${a.id}"></td>
            <td>${a.date}</td>
            <td>${a.time}</td>
            <td>${a.serviceDescription}</td>
            <td>${a.price}</td>
            <td><a href="/app/doctor/uploadFiles/${a.id}/${patient.id}">Add file</a></td>
            <td><a href="/app/doctor/endVisit/${a.id}">End visit</a></td>
        </tr>
    </c:forEach>
</table>

</body>