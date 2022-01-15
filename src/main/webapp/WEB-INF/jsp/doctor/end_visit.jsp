<%@ page contentType="text/html; charset=UTF-8" %>
<h2>Are you sure you want to end visit?</h2>

<form method="post">
    <input type="hidden" name="id" value="${appointment.id}">
    <button type="submit" value="yes" name="confirmed">Yes</button>
    <button type="submit" value="no" name="confirmed" class="btn btn-outline-danger">No</button>
</form>