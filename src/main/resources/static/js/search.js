function searchSuppliers() {
  const area = document.getElementById("searchArea").value.trim();

  if (!area) {
    alert("Please enter an area");
    return;
  }

  fetch(`http://localhost:8080/supplier/search/area/${area}`)
    .then(response => {
      if (!response.ok) {
        throw new Error("API error");
      }
      return response.json();
    })
    .then(data => {
      const container = document.getElementById("supplierResults");
      container.innerHTML = "";

      if (data.length === 0) {
        container.innerHTML = "<p>No suppliers found for this location </p>";
        return;
      }

      data.forEach(supplier => {
        container.innerHTML += `
          <div class="card p-3 mb-2">
            <h5>${supplier.companyName}</h5>
            <p>Area: ${supplier.serviceArea}</p>
            <p>Phone: ${supplier.phone}</p>
          </div>
        `;
      });
    })
    .catch(error => {
      console.error(error);
      alert("Error fetching suppliers");
    });
}
function displaySuppliers(list) {
  const container = document.getElementById("supplierList");
  container.innerHTML = "";

  list.forEach(s => {
    container.innerHTML += `
      <div class="card">
        <h5>${s.companyName}</h5>
        <p>${s.serviceArea}</p>
      </div>
    `;
  });
}
window.onload = () => {
  const area = localStorage.getItem("consumerAddress");
  if (area) {
    fetch(`/supplier/recommend?consumerArea=${area}`)
      .then(res => res.json())
      .then(displaySuppliers);
  }
};

function searchConsumers() {

  const area = document.getElementById("consumerArea").value.trim();
  const resultsDiv = document.getElementById("consumerResults");

  // Clear old results
  resultsDiv.innerHTML = "";

  if (area === "") {
    alert("Please enter an area");
    return;
  }

  fetch(`http://localhost:8080/consumers/search/address/${area}`)
    .then(response => {
      if (!response.ok) {
        throw new Error("Failed to fetch consumers");
      }
      return response.json();
    })
    .then(consumers => {

      if (consumers.length === 0) {
        resultsDiv.innerHTML = `<p class="text-danger">No consumer found currently in this area.</p>`;
        return;
      }

      consumers.forEach(consumer => {
        const card = `
          <div class="card mb-2 p-3 shadow-sm">
            <h6>${consumer.fullName || consumer.name}</h6>
            <p><strong>Address:</strong> ${consumer.address}</p>
            <p><strong>Phone:</strong> ${consumer.phone}</p>
          </div>
        `;
        resultsDiv.innerHTML += card;
      });
    })
    .catch(error => {
      console.error(error);
      alert("Error fetching consumers");
    });
}