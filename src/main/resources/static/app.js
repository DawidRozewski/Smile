document.addEventListener("DOMContentLoaded", function (event) {
    const dateInput = document.getElementById('pick_date');
    const hoursInput = document.getElementById('pick_hour');

    dateInput.addEventListener("change", function (event) {
        const pickedDate = event.target.value;
        fetch("http://localhost:8080/app/example-appointment/available?date=" + pickedDate)
            .then(resp => resp.json())
            .then(data => {
                console.log(data)

                data.forEach(element => {
                    const select = document.createElement("select");
                    select.innerText = element;
                    hoursInput.appendChild(select);
                })
            })
            .catch(error => {
                console.error(error)
            })
    })
});

