let user = null;

window.onload = () => {

  user = JSON.parse(localStorage.getItem("loggedInUser"));
  const bookingList = document.getElementById("bookingList");

  if (!user || !bookingList) {
    console.error("User or bookingList missing");
    return;
  }

  let url = "";

  if (user.role === "CONSUMER") {
    url = `http://localhost:8080/bookings/consumer/${user.id}`;
  } 
  else if (user.role === "SUPPLIER") {
    url = `http://localhost:8080/bookings/supplier/${user.id}`;
  }

  fetch(url)
    .then(res => res.json())
    .then(bookings => {
      console.log("BOOKINGS:", bookings);
      renderBookings(bookings);
    })
    .catch(err => console.error(err));
};


/* =========================
   RENDER BOOKINGS
========================= */
function renderBookings(bookings) {

  const bookingList = document.getElementById("bookingList");
  bookingList.innerHTML = "";

  if (!bookings || bookings.length === 0) {
    bookingList.innerHTML = "<p>No bookings found.</p>";
    return;
  }

  bookings.forEach(b => {

    let contactHtml = "";

    if (b.status === "ACCEPTED" || b.status === "COMPLETED") {
      contactHtml = user.role === "SUPPLIER"
        ? `
          <hr>
          <p><strong>Phone:</strong> ${b.consumer.phone}</p>
          <p><strong>Email:</strong> ${b.consumer.email}</p>
        `
        : `
          <hr>
          <p><strong>Phone:</strong> ${b.supplier.phone}</p>
          <p><strong>Email:</strong> ${b.supplier.email}</p>
        `;
    }

    bookingList.innerHTML += `
      <div class="card p-3 mb-3 shadow-sm">

        ${
          user.role === "SUPPLIER"
            ? `<p><strong>Consumer:</strong> ${b.consumer.name}</p>`
            : `<p><strong>Supplier:</strong> ${b.supplier.companyName}</p>`
        }

        <p><strong>Status:</strong>
          <span class="badge ${
            b.status === "PENDING" ? "bg-warning" :
            b.status === "ACCEPTED" ? "bg-success" :
            b.status === "REJECTED" ? "bg-danger" :
            "bg-secondary"
          }">${b.status}</span>
        </p>

        <p><strong>Date:</strong> ${new Date(b.bookingDate).toLocaleString()}</p>
        <p><strong>Location:</strong> ${b.consumer.address}</p>

        ${contactHtml}

        ${
          user.role === "SUPPLIER" && b.status === "PENDING"
            ? `
              <button class="btn btn-sm btn-success"
                onclick="updateBooking(${b.id}, 'accept')">
                Accept
              </button>

              <button class="btn btn-sm btn-danger mt-2"
                onclick="updateBooking(${b.id}, 'reject')">
                Reject
              </button>
            `
            : ""
        }
      </div>
    `;
  });
}


/* =========================
   UPDATE BOOKING STATUS
========================= */
function updateBooking(id, action) {
  fetch(`http://localhost:8080/bookings/${id}/${action}?supplierId=${user.id}`, {
    method: "PUT"
  })
  .then(res => res.json())
  .then(() => location.reload())
  .catch(err => console.error(err));
}
