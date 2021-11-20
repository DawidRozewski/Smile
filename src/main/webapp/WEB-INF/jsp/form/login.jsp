<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<h2>Zaloguj</h2>
<body>
<form method="post" action="/app/login">
    <div class="form-group ${error != null ? 'has-error' : ''}">
             <span>${msg}</span>
         E-mail: <input type="text" name="email" placeholder="Podaj email"> <br/>

        Hasło: <input type="password" name="password" placeholder="Podaj hasło"> <br/>
                <span>${errorMsg}</span>
    <input type="submit" value="Zaloguj"> <br/>
    </div>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form>
</body>