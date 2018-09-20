var failed = document.querySelectorAll(".failed");
var isFailed = false;
var steamIdInput = document.querySelector("#steamIdInput");

for(var i=0; i<failed.length; i++) {
	if(failed[i].innerText.length > 0) {
		isFailed = true;
	}
}

if(!isFailed && steamIdInput.value.length > 0) {
	steamIdInput.classList.toggle("disabled");
}