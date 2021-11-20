<h2>Czy chcesz usunac pacjenta ${patient.fullName} ?</h2>

<form method="post">
    <input type="hidden" name="id" value="${patient.id}">
    <button type="submit" value="yes" name="confirmed">Tak</button>
    <button type="submit" value="no" name="confirmed">Nie</button>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form>