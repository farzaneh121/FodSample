// Mouse tracking for the eyes (from Irina - https://codepen.io/Irina-T/pen/LLVogK):
  	var eye1 = document.getElementById("eye1_4");
	var eye2 = document.getElementById("eye2_4");
	
	window.onmousemove = function(e) {
	    if (e.clientX > window.innerWidth / 2 + 90) {
	        eye1.style.left = "285px";
	        eye2.style.left = "436px";
	    }
	
	    if (e.clientX < window.innerWidth / 2 - 100) {
	        eye1.style.left = "245px";
	        eye2.style.left = "396px";
	    }
	
	    if (
	        e.clientX <= window.innerWidth / 2 + 90 &&
	        e.clientX >= window.innerWidth / 2 - 100
	    ) {
	        eye1.style.left = "265px";
	        eye2.style.left = "416px";
	    }
	
	    if (e.clientY > 170) {
	        eye1.style.top = "90px";
	        eye2.style.top = "90px";
	    }
	
	    if (e.clientY < 300) {
	        eye1.style.top = "50px";
	        eye2.style.top = "50px";
	    }
	
	    if (e.clientY <= 300 && e.clientY >= 170) {
	        eye1.style.top = "70px";
	        eye2.style.top = "70px";
	    }
	};

