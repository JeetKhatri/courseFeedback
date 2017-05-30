function changeSubject(ref) {
	//var x = document.getElementById("");
	console.log(ref);
	if(ref=="mscit1"){
		document.getElementById('EC1Name').innerText="IT786 - M.Sc. Elective 1 ";
		document.getElementById('EC2Name').innerText="IT786 - M.Sc. Elective 2 ";
		document.getElementById('EC3Name').innerText="IT786 - M.Sc. Elective 3 ";
		document.getElementById('CC1Name').innerText="IT786 - M.Sc. Core 1 ";
		document.getElementById('CC2Name').innerText="IT786 - M.Sc. Core 2 ";
		document.getElementById('CC3Name').innerText="IT786 - M.Sc. Core 3 ";
	}
	if(ref=="be1"){
		document.getElementById('EC1Name').innerText="IT786 - B.Tech Elective 1 ";
		document.getElementById('EC2Name').innerText="IT786 - B.Tech Elective 2 ";
		document.getElementById('EC3Name').innerText="IT786 - B.Tech Elective 3 ";
		document.getElementById('CC1Name').innerText="IT786 - B.Tech Core 1 ";
		document.getElementById('CC2Name').innerText="IT786 - B.Tech Core 2 ";
		document.getElementById('CC3Name').innerText="IT786 - B.Tech Core 3 ";
	}
 }
