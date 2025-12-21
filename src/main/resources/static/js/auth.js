const BASE_URL = "http://localhost:8080";

function showSpinner() {
    document.getElementById("spinner").classList.add("show");
}

function hideSpinner() {
    document.getElementById("spinner").classList.remove("show");
}


async function registerUser(event) {
    event.preventDefault();

    showSpinner();
    // Get HTML values
    const role = document.getElementById("roleSelect").value;
    const name = document.getElementById("name").value;
    const email = document.getElementById("email").value;
    const phone = document.getElementById("phone").value;
    const password = document.getElementById("password").value;

    if (!role || !name || !email || !phone || !password) {
    hideSpinner();
    alert("All fields are required");
    return;
  }

    // Choose API endpoint based on role
    let endpoint = "";

    if (role === "SUPPLIER") {
        endpoint = "/auth/register/supplier";
    } else {
        endpoint = "/auth/register/consumer";
    }

    // Prepare data object
    const data = {
        name: name,
        email: email,
        phone: phone,
        password: password
    };

    // Send request
    let response = await fetch(BASE_URL + endpoint, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(data)
    });

    let result;
    try {
        result = await response.json();
    } catch {
        result = {};
    }

    hideSpinner();

    // Show message
    const resultElement = document.getElementById("result");
if (resultElement) {
    resultElement.innerText = "Account created successfully for " + role + "!";
}

    console.log(result);

    setTimeout(() => {
        window.location.href = "/login.html";
    }, 1500);
}

async function loginUser(event) {
event.preventDefault();
    showSpinner();

    const data = {
        email: document.getElementById("email").value,
        password: document.getElementById("password").value
    };

    let res = await fetch(BASE_URL + "/auth/login", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(data)
    });

    let user = await res.json();

    hideSpinner();

    if (user.message === "Invalid email or password") {
        alert("Wrong email or password");
        return;
    }

    // save user session
    localStorage.setItem("loggedInUser", JSON.stringify(user));

    // redirect based on role
    if (user.role === "ADMIN") {
    window.location.href = "admin-dashboard.html";
} else if (user.role === "SUPPLIER") {
    window.location.href = "supplier-dashboard.html";
} else if (user.role === "CONSUMER") {
    window.location.href = "consumer-dashboard.html";
}

}

