// Zmieniłem położenie tego pliku bo tylko z tego miejsca będzie on serwowan przez spring boota,
// niestety taka charakterystyka ;) tylko dzięki temu że jest w tym miejscu bedzie możliwy do odczytania przez .jsp

// Dla dobreych praktyk dodałem event listenera pilnującego załadowania storny
document.addEventListener("DOMContentLoaded", function (event) {
    const dateInput = document.getElementById('pick_date');
    const hoursInput = document.getElementById('pick_hour');

    dateInput.addEventListener("change", function (event) {

        // odwolanie do akcji w restControllerze
        fetch("http://localhost:8080/app/example-appointment/available")
            .then(resp => resp.json())
            .then(data => {
                // tutaj jak wejdziesz na ta strone po zaaplikowaniu tych zmian i otworzysz sobie konsole JS w przeglądarce
                // zobaczysz, że dostajesz w JSON już godziny z tego endpointa
                console.log(data)
                data.forEach(element => {
                    const select = document.createElement("select");
                    select.value = element;
                    hoursInput.appendChild(select);
                })
            })
            .catch(error => {
                console.error(error)
            })
    })
});
