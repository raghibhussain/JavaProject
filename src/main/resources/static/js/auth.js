const BASE_URL = "http://localhost:8080";

/* ================= SPINNER ================= */

function showSpinner() {
    document.getElementById("spinner")?.classList.add("show");
}

function hideSpinner() {
    document.getElementById("spinner")?.classList.remove("show");
}

/* ================= REGISTER ================= */

document.addEventListener("DOMContentLoaded", () => {

    const registerForm = document.getElementById("register-form");
    const loginForm = document.getElementById("login-form");

    if (registerForm) {
        registerForm.addEventListener("submit", registerUser);
    }

    if (loginForm) {
        loginForm.addEventListener("submit", loginUser);
    }
});

async function registerUser(event) {
    event.preventDefault();

    const form = event.target;

    /* 🔥 Let browser validation run first */
    if (!form.checkValidity()) {
        return; // browser shows native validation UI
    }

    showSpinner();

    const role = document.getElementById("roleSelect").value;
    const name = document.getElementById("name").value.trim();
    const email = document.getElementById("email").value.trim();
    const phone = document.getElementById("phone").value.trim();
    const password = document.getElementById("password").value;

    const endpoint =
        role === "SUPPLIER"
            ? "/auth/register/supplier"
            : "/auth/register/consumer";

    const data = { name, email, phone, password };

    try {
        const response = await fetch(BASE_URL + endpoint, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(data)
        });

        if (!response.ok) {
            throw new Error("Registration failed");
        }

        await response.json();
        hideSpinner();

        const resultElement = document.getElementById("result");
        if (resultElement) {
            resultElement.innerText = "Account created successfully!";
        }

        setTimeout(() => {
            window.location.href = "/login.html";
        }, 1500);

    } catch (error) {
        hideSpinner();
        console.error("Register error:", error);
    }
}

/* ================= LOGIN ================= */

async function loginUser(event) {
    event.preventDefault();

    const form = event.target;

    /* 🔥 Browser validation first */
    if (!form.checkValidity()) {
        return;
    }

    showSpinner();

    const email = document.getElementById("email").value.trim();
    const password = document.getElementById("password").value;

    try {
        const res = await fetch(BASE_URL + "/auth/login", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({ email, password })
        });

        if (!res.ok) {
            throw new Error("Login failed");
        }

        const user = await res.json();
        hideSpinner();

        if (user.message === "Invalid email or password") {
            return;
        }

        localStorage.setItem("loggedInUser", JSON.stringify(user));

        if (user.role === "ADMIN") {
            window.location.href = "admin-dashboard.html";
        } else if (user.role === "SUPPLIER") {
            window.location.href = "supplier-dashboard.html";
        } else if (user.role === "CONSUMER") {
            window.location.href = "consumer-dashboard.html";
        }

    } catch (error) {
        hideSpinner();
        console.error("Login error:", error);
    }
}
