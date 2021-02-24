let login_form = document.getElementById('login-validation');

login_form.addEventListener('submit', async (e) => {
    e.preventDefault();
    e.stopPropagation();
    if (login_form.checkValidity() === true) {
        document.getElementById("submit-button").style.display = "none";
        document.getElementById("spinner-button").style.display = "block";
        let response = await fetch('api/employee/login', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json;charset=utf-8'
            },
            body: JSON.stringify({
                email: document.getElementById('email').value,
            })
        });

        try
        {
            let result = await response.json();
            console.log(result);
            document.getElementById("submit-button").style.display = "block";
            document.getElementById("spinner-button").style.display = "none";
            sessionStorage.setItem('fname', result["firstname"]);
            sessionStorage.setItem('lname', result["lastname"]);
            sessionStorage.setItem('emailid', result["email"]);
            sessionStorage.setItem('eid', result["employeeid"]);
            sessionStorage.setItem('image',result['image_path']);
            console.log(sessionStorage.getItem("image"));
            location.href = "dashboard.html";
        }
        catch(e)
        {
            document.getElementById("submit-button").style.display = "block";
            document.getElementById("spinner-button").style.display = "none";
            document.getElementById("login-alert").style.display = "block";
        }

    }
});