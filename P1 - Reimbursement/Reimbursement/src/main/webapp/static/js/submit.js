var form = `
<h1 style="text-align: center">Training Reimbursement</h1>
<br> <br>
<table style="width: 80%; margin: auto">

<tr>
<td>
Event Date:
</td>
</tr>

<tr>
<td class="formgroup">
        <label for="eventMonth">Month: </label>
	<select class="form-control" id="eventMonth">
		<option selected="true">Choose an option</option>
		<option value="01">January</option>
		<option value="02">February</option>
		<option value="03">March</option>
		<option value="04">April</option>
		<option value="05">May</option>
		<option value="06">June</option>
		<option value="07">July</option>
		<option value="08">August</option>
		<option value="09">September</option>
		<option value="10">October</option>
		<option value="11">November</option>
		<option value="12">December</option>
	</select>
</td>
<td class="formgroup">
	<label for="eventDay">Day: </label>
	<select class="form-control" id="eventDay">
		<option selected="true">Choose an option</option>
		<option value="01">1st</option>
		<option value="02">2nd</option>
		<option value="03">3rd</option>
		<option value="04">4th</option>
		<option value="05">5th</option>
		<option value="06">6th</option>
		<option value="07">7th</option>
		<option value="08">8th</option>
		<option value="09">9th</option>
		<option value="10">10th</option>
		<option value="11">11th</option>
		<option value="12">12th</option>
		<option value="13">13th</option>
		<option value="14">14th</option>
		<option value="15">15th</option>
		<option value="16">16th</option>
		<option value="17">17th</option>
		<option value="18">18th</option>
		<option value="19">19th</option>
		<option value="20">20th</option>
		<option value="21">21st</option>
		<option value="22">22nd</option>
		<option value="23">23rd</option>
		<option value="24">24th</option>
		<option value="25">25th</option>
		<option value="26">26th</option>
		<option value="27">27th</option>
		<option value="28">28th</option>
		<option value="29">29th</option>
		<option value="30">30th</option>
		<option value="31">31st</option>
	</select>
</td>
<td class="formgroup" style="width: 25%">
        <br>
        <label for="eventYear">Year:</label> <input class="form-control simple-book" type="number"
        id="eventYear" /> <br>
</td>
</tr>

<tr>
<td>
<br>
Location:
<br>
</td>
</tr>

<tr>
<td class="formgroup" style="width: 25%">
    <label for="eventCountry">Country:</label> <input class="form-control simple-book" type="text"
        id="eventCountry" /> <br>
</td>
<td class="formgroup" style="width: 25%">
    <label for="eventState">State:</label> <input class="form-control simple-book" type="text"
        id="eventState" /> <br>
</td>
<td class="formgroup" style="width: 25%">
    <label for="eventCity">City:</label> <input class="form-control simple-book" type="text"
        id="eventCity" /> <br>
</td>
</tr>

<tr>
<td class="formgroup">
	<label for="eventType">Event Type: </label>
	<select class="form-control" id="eventType">
		<option selected="true">Choose an option</option>
		<option value="1">University Courses</option>
		<option value="2">Seminars</option>
		<option value="3">Certification Preparation Classes</option>
		<option value="4">Certification</option>
		<option value="5">Technical Training</option>
		<option value="6">Other</option>
	</select>
</td>
<td class="formgroup" style="width: 25%">
        <br>
    <label for="reimAmount">Amount Requested:</label> <input class="form-control simple-book" type="number"
        id="reimAmount" /> <br>
</td>
<td class="formgroup">
	<label for="gradePresent">Evaluation Type: </label>
	<select class="form-control" id="gradePresent">
		<option selected="true" value="1">Grade</option>
		<option value="2">Presentation</option>
	</select>
</td>
<td class="formgroup" style="width: 25%">
        <br>
    <label for="gradePresNum">Grade Requirement:</label> <input class="form-control simple-book" type="number"
        id="gradePresNum" /> <br>
</td>
</tr>

</table>

<table style="width: 80%; margin: auto">

<tr>
<td class="formgroup" style="width: 50%">
    <label for="description">Event Description:</label> <input class="form-control simple-book" type="text"
        id="description" /> <br>
</td>
<td class="formgroup" style="width: 50%">
    <label for="justification">Funding Justification:</label> <input class="form-control simple-book" type="text"
        id="justification" /> <br>
</td>
</tr>

</table>

<table style="width: 80%; margin: auto">

<tr>
<td>
<br>
Optional:
<br>
</td>
</tr>

<tr>
<td class="formgroup" style="width: 33%">
    <label for="optEvtAttach">Event Attachment (opt):</label> <input class="form-control simple-book" type="text"
        id="optEvtAttach" /> <br>
</td>
<td class="formgroup" style="width: 33%">
    <label for="optDaysMissed">Days Missed (opt):</label> <input class="form-control simple-book" type="number"
        id="optDaysMissed" /> <br>
</td>
<td class="formgroup" style="width: 33%">
    <label for="preApproval">Pre-approvals (opt):</label> <input class="form-control simple-book" type="number"
        id="preApproval" /> <br>
</td>
</tr>

</table>

<table style="width: 80%; margin: auto">

<tr>
<td>
</td>
<td>
</td>
<td>
</td>
<td style="width: 25%; margin: auto">
        <br>
    <button type="submit" class="btn btn-primary emp-btn" id="submitbtn"
        onclick="submitForm()">Submit</button>
        <br>
</td>
</tr>

<tr>
<td>
</td>
<td>
</td>
<td>
</td>
<td id = "succeedFail"  style="width: 25%; margin: auto">

</td>
</tr>

</table>`;
var baseURL = "/Reimbursement/";

function showSubmitForm() {
        document.getElementById("interactive-menu").innerHTML = form;
        document.getElementById("succeedFail").innerHTML = ``;

        document.getElementById("eventType").onkeydown = checkForm;
        document.getElementById("eventMonth").onkeydown = checkForm;
        document.getElementById("eventDay").onkeydown = checkForm;
        document.getElementById("eventYear").onkeydown = checkForm;
        document.getElementById("eventCountry").onkeydown = checkForm;
        document.getElementById("eventState").onkeydown = checkForm;
        document.getElementById("eventCity").onkeydown = checkForm;
        document.getElementById("reimAmount").onkeydown = checkForm;
        document.getElementById("description").onkeydown = checkForm;
        document.getElementById("gradePresent").onkeydown = checkForm;
        document.getElementById("gradePresNum").onkeydown = checkForm;
        document.getElementById("justification").onkeydown = checkForm;
        document.getElementById("optEvtAttach").onkeydown = checkForm;
        document.getElementById("optDaysMissed").onkeydown = checkForm;
        document.getElementById("preApproval").onkeydown = checkForm;
}

function checkForm() {
        if (event.which === 13)
                submitForm();
}

function submitForm() {
        var xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = submitSuccess;
        xhttp.open("POST", baseURL + "submit");
        xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        let employeeID = getEmployeeID();
        let eventType = document.getElementById("eventType").value;
        let eventMonth = document.getElementById("eventMonth").value;
        let eventDay = document.getElementById("eventDay").value;
        let eventYear = document.getElementById("eventYear").value;
        let eventCountry = document.getElementById("eventCountry").value;
        let eventState = document.getElementById("eventState").value;
        let eventCity = document.getElementById("eventCity").value;
        let description = document.getElementById("description").value;
        let reimAmount = document.getElementById("reimAmount").value;
        let gradePresent = document.getElementById("gradePresent").value;
        let gradePresNum = document.getElementById("gradePresNum").value;
        let justification = document.getElementById("justification").value;
        let optEvtAttach = document.getElementById("optEvtAttach").value;
        let optDaysMissed = document.getElementById("optDaysMissed").value;
        let preApproval = document.getElementById("preApproval").value;

        if (employeeID != `` && eventType != `` && eventMonth != `` && eventDay != `` && eventYear != `` && eventCountry != `` && eventCity != `` 
                && reimAmount != `` && description != `` && (gradePresent == 2 || gradePresNum != ``) && justification != ``){
                xhttp.send("employeeID=" + employeeID + "&eventType=" + eventType + "&eventMonth=" + eventMonth + "&eventDay=" + eventDay 
                        + "&eventYear=" + eventYear + "&eventCountry=" + eventCountry + "&eventState=" + eventState + "&eventCity=" + eventCity
                        + "&reimAmount=" + reimAmount + "&description=" + description + "&gradePresent" + gradePresent 
                        + "&gradePresNum=" + gradePresNum + "&justification=" + justification + "&optEvtAttach=" + optEvtAttach
                         + "&optDaysMissed=" + optDaysMissed + "&preApproval=" + preApproval);

                document.getElementById("succeedFail").innerHTML = `Submitting form.`;
        }
        else {
                document.getElementById("succeedFail").innerHTML = `Please fill out all necessary fields before submitting.`;
        }

        function submitSuccess() {
                if (xhttp.readyState === 4 && xhttp.status === 200) {
                        // Clear forms
                        showSubmitForm();
                        document.getElementById("succeedFail").innerHTML = `Request submitted successfully.`;
                }
        }
}