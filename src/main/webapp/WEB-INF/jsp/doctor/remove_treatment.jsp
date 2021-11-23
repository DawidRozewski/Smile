<%@ page contentType="text/html; charset=UTF-8" %>
<h2>Czy chcesz usunac wizytę ${treatment.visitNumber} ?</h2>

<form method="post">
    <input type="hidden" name="id" value="${treatment.id}">
    <button type="submit" value="yes" name="confirmed">Tak</button>
    <button type="submit" value="no" name="confirmed">Nie</button>
</form>