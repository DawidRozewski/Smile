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
<h2>Dane pacjenta</h2>
<table>
    <tr>
        <th>Id</th>
        <th>Imie i nazwisko</th>
        <th>Pesel</th>
        <th>Email</th>
        <th>Nr. kontaktowy</th>
        <th>Historia wizyt</th>
    </tr>
    <tr>
        <td>${patient.id}</td>
        <td>${patient.fullName}</td>
        <td>${patient.pesel}</td>
        <td>${patient.email}</td>
        <td>${patient.phoneNumber}</td>
        <td><a href="/app/doctor/history/${patient.id}">Pokaż</a></td>
    </tr>
</table>


<h2>Plan leczenia</h2>
<table>
    <tr>
        <th>Numer wizyty</th>
        <th>Opis</th>
        <th>Data wizyty</th>
        <th>Cena</th>
        <th>Edytuj</th>
        <th>Usuń</th>
    </tr>
    <c:forEach var="t" items="${treatmentList}">
        <tr>
            <td>${t.visitNumber}</td>
            <td>${t.description}</td>
            <td>${t.visitDate}</td>
            <td>${t.price}</td>
            <td><a href="/app/doctor/edit-treatment/${patient.id}/${t.id}">Edytuj</a></td>
            <td><a href="/app/doctor/remove/${patient.id}/${t.id}">Usuń</a></td>
        </tr>
    </c:forEach>
</table>
<h4><a href="/app/doctor/add-treatment/${patient.id}">Dodaj plan</a></h4><br/>

<h2>Nadchodzące wizyty</h2>
<table>
    <tr>
        <th>Data wizyty</th>
        <th>Godzina</th>
        <th>Opis</th>
        <th>Cena</th>
        <th>Pliki</th>
        <th>Zakończ</th>
    </tr>
    <c:forEach var="a" items="${appointments}">
        <tr>
            <td hidden="${a.id}"></td>
            <td>${a.date}</td>
            <td>${a.time}</td>
            <td>${a.serviceDescription}</td>
            <td>${a.price}</td>
            <td><a href="/app/doctor/uploadFiles/${a.id}/${patient.id}">Dodaj</a></td>
            <td><a href="/app/doctor/endVisit/${a.id}">Zakończ wizytę</a></td>
        </tr>
    </c:forEach>
</table>

</body>