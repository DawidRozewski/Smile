<%@ page contentType="text/html; charset=UTF-8" %>
<h2>Do you want to delete the ${appointment.date} appointment for the patient ${patient}?</h2>

<form method="post">
    <input type="hidden" name="id" value="${appointment.id}">
    <button type="submit" value="yes" name="confirmed">Yes</button>
    <button type="submit" value="no" name="confirmed">No</button>
</form>