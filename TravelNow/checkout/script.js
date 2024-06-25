//https://dribbble.com/shots/2291259-Credit-Card-Checkout
//https://images.unsplash.com/photo-1507038772120-7fff76f79d79?auto=format&fit=crop&w=1867&q=80

// This part is for the checkout page logic

var model = document.querySelector(".model");
function fadeIn () {
  console.log('fadein')
  model.className += " fadeIn";
}

// This part is for the receipt page logic
document.addEventListener('DOMContentLoaded', function() {
    const params = new URLSearchParams(window.location.search);
    const name = params.get('name');
    const startDate = params.get('startDate');
    const endDate = params.get('endDate');
    const pax = params.get('pax');

    // Display these values in HTML
    document.querySelector('.text-cover').innerHTML += `<p>Booked by: ${name}</p>`;
    document.querySelector('.text-cover').innerHTML += `<p>From: ${startDate} to ${endDate}</p>`;
});

// Package prices in RM
const packagePrices = {
  "United Kingdom": 8000,
  "Belgium": 9000,
  "Turkey": 5000,
  "Italy": 8500,
  "Singapore": 1500,
  "Indonesia": 2500,
  "France": 8000,
  "Spain": 8500,
  "United States": 10500,
  "China": 6500,
  "Germany": 8000,
  "Norway": 12500,
  "Japan": 4500,
  "Thailand": 2500,
  "India": 5000,
  "Switzerland": 10500,
  "Brazil": 7000,
  "Canada": 9500,
};

// Function to calculate the number of nights between two dates
  function getNumberOfNights(startDate, endDate) {
    const start = new Date(startDate);
    const end = new Date(endDate);
    const diffTime = Math.abs(end - start);
    const diffDays = Math.ceil(diffTime / (1000 * 60 * 60 * 24));

    return diffDays;
  }

  // Function to update the receipt with the correct values
function updateReceipt() {
  const urlParams = new URLSearchParams(window.location.search);
  const destination = urlParams.get('destination');
  const startDate = urlParams.get('startDate');
  const endDate = urlParams.get('endDate');
  const pax = urlParams.get('pax'); // Retrieve the number of passengers from the URL
  const nights = getNumberOfNights(startDate, endDate);
  const pricePerNight = packagePrices[destination] / nights;
  const tax = 80; // Fixed tax
  const totalPrice = pricePerNight * nights * pax;
  const sum = totalPrice + tax;

  // Update the country name and "trip" on the heading
  document.querySelector('.text-cover h1').textContent = `${destination} Trip`;

  // Update the 'Entire trip for x person(s)' text
  const personText = pax === "1" ? "person" : "people"; // Handle singular vs plural
  document.querySelector('.text-cover p').textContent = `Entire trip for ${pax} ${personText}`;

  // Update the rest of the receipt summary
  document.getElementById('stayDates').textContent = `From: ${startDate} to ${endDate}`;
  document.getElementById('nightsSummary').innerHTML = `<strong>${destination}</strong> trip for ${nights} nights`;
  document.getElementById('totalPrice').textContent = `${totalPrice.toFixed(2)} RM`;
  document.getElementById('tax').textContent = `${tax.toFixed(2)} RM`;
  document.getElementById('sum').textContent = `${sum.toFixed(2)} RM`;
}

// Call the function when the page loads
window.onload = updateReceipt;
