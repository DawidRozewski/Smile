<h2>Czy chcesz usunac doktora ${doctor.fullName} ?</h2>

<form method="post">
    <input type="hidden" name="id" value="${doctor.id}">
    <button type="submit" value="yes" name="confirmed">Tak</button>
    <button type="submit" value="no" name="confirmed">Nie</button>
</form>