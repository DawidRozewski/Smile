const dateInput = document.getElementById('pick_date');
const hoursInput = document.getElementById('pick_hour');

dateInput.addEventListener("change", function (event) {
    const pickedDate = event.target.value;

    fetch("http://localhost:8080/app/patient/appointment", pickedDate)
        .then(resp => resp.json())
        .then(data => {
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