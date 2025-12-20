const user = JSON.parse(localStorage.getItem("loggedInUser"));
const bookingList = document.getElementById("bookingList");

// ---------------- FETCH BOOKINGS ----------------
function loadBookings() {
  let url = "";

  if (user.role === "CONSUMER") {
    url = `http://localhost:8080/bookings/consumer/${user.id}`;
  } else {
    url = `http://localhost:8080/bookings/supplier/${user.id}`;
  }

  fetch(url)
    .then(res => res.json())
    .then(renderBookings)
    .catch(err => console.error(err));
}

// ---------------- RENDER BOOKINGS ----------------
function renderBookings(bookings) {
  bookingList.innerHTML = "";

  if (bookings.length === 0) {
    bookingList.innerHTML = "<p>No bookings found.</p>";
    return;
  }

  bookings.forEach(b => {
    bookingList.innerHTML += `
      <div class="card p-3 mb-3 shadow-sm">
        <h5>Quantity: ${b.quantity}</h5>
        <p>Status: <strong>${b.status}</strong></p>
        <p>Date: ${b.bookingDate}</p>

        ${user.role === "SUPPLIER" && b.status === "PENDING" ? `
          <button class="btn btn-success btn-sm" onclick="updateStatus(${b.id}, 'accept')">Accept</button>
          <button class="btn btn-danger btn-sm" onclick="updateStatus(${b.id}, 'reject')">Reject</button>
        ` : ""}

        ${user.role === "SUPPLIER" && b.status === "ACCEPTED" ? `
          <button class="btn btn-primary btn-sm" onclick="updateStatus(${b.id}, 'complete')">Complete</button>
        ` : ""}
      </div>
    `;
  });
}

// ---------------- UPDATE STATUS ----------------
function updateStatus(bookingId, action) {
  fetch(`http://localhost:8080/bookings/${bookingId}/${action}?supplierId=${user.id}`, {
    method: "PUT"
  })
  .then(res => res.json())
  .then(loadBookings)
  .catch(err => alert("Action failed"));
}

loadBookings();
