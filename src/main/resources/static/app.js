document.addEventListener("DOMContentLoaded", function (event) {
    const dateInput = document.getElementById('pick_date');
    const hoursInput = document.getElementById('pick_hour');

    dateInput.addEventListener("change", function (event) {
        const pickedDate = event.target.value;

        fetch("http://localhost:8080/app/example-appointment/available?date=" + pickedDate)
            .then(resp => resp.json())
            .then(data => {
                hoursInput.length = 0;
                const formattedHours = data.map(hour => hour.slice(0, -3));
                console.log(formattedHours);

                formattedHours.forEach(element => {
                    const option = document.createElement("option");
                    option.textContent = element;
                    hoursInput.appendChild(option);
                })
            })
            .catch(error => {
                console.error(error)
            })
    })
})


