initialize();

function initialize() {
	hideDetails();
	detailsButtonsListeners();
	adjustVacStatusStyle();
}

function hideDetails() {
	var spans = document.querySelectorAll(".details");
	changeElementsDisplay(spans, "none");
}

function detailsButtonsListeners() {
	var buttons = document.querySelectorAll("table button");
	for(var i=0; i<buttons.length; i++) {
		buttons[i].addEventListener("click", function(event) {
			var buttonId = event.target.id;
			var spans = document.querySelectorAll("." + buttonId);

			if(spans[0].style.display === "none") {
				changeElementsDisplay(spans, "")
			} else {
				changeElementsDisplay(spans, "none");
			}
		});
	}
}

function changeElementsDisplay(elements, value) {
	for(var j=0; j<elements.length; j++) {
		elements[j].style.display = value;
	}
}

function adjustVacStatusStyle() {
	var statuses = document.querySelectorAll(".vacstatus");
	for(var i=0; i<statuses.length; i++) {
		if(statuses[i].innerText === "Clear") {
			statuses[i].classList.toggle("clear");
		} else {
			statuses[i].classList.toggle("banned");
		}
	}
}