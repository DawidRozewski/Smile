<%@ page contentType="text/html; charset=UTF-8" %>
<h2>Czy chcesz zakończyć wizytę ?</h2>

<form method="post">
    <input type="hidden" name="id" value="${appointment.id}">
    <button type="submit" value="yes" name="confirmed">Tak</button>
    <button type="submit" value="no" name="confirmed" class="btn btn-outline-danger">Nie</button>
</form>