var viewSubmits = ``;
var statusCircle = `<span class="dot" style="background-color: #000"></span>`;
var passedCircle = `<span class="dot" style="background-color: #000"></span>`;
var eventTypes = [`University Courses`, `Seminars`, `Certification Preparation Classes`, `Certification`, `Technical Training`, `Other`];

var lastview;
var lastNum = -1;

var baseURL = "/Reimbursement/";
var data;

function showSubmissions() {
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = viewSuccess;
    xhttp.open("POST", baseURL + "view");
    xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    
    xhttp.send("employeeID=" + getEmployeeID());

    function viewSuccess(){
        if (xhttp.readyState === 4 && xhttp.status === 200) {
            data = JSON.parse(xhttp.responseText);

            //console.log(data);
            viewSubmits = `
            <h1 style="text-align: center">My Reimbursements</h1>
            <br> <br>
            <table border="1" style="width: 80%; margin: auto" class="submission-box">`;

            for(var i = 0; i < data.length; i++) {
                if (data[i].status == 7)
                    statusCircle = `<span class="dot" style="background-color: #00f"></span>`;
                else if (data[i].status >= 0 && data[i].status < 7)
                    statusCircle = `<span class="dot" style="background-color: #ff0"></span>`;
                else if (data[i].status > 7)
                    statusCircle = `<span class="dot" style="background-color: #f00"></span>`;

                if (data[i].passed)
                    passedCircle = `<span class="dot" style="background-color: #00f"></span>`;
                else 
                    passedCircle = `<span class="dot" style="background-color: #f00"></span>`;

                viewSubmits += 
                `<tr onclick="revealSubmission(` + i + `)">` +
                    `<td id = "sub` + i + `">` +
                    `<table style="width: 100%; margin: auto">` +
                    `<tr>` + 
                        `<td style="width: 25%">Date Submitted: <br>` + data[i].apprSubDate.substring(0, 10) + `</td><td style="width: 25%">Event Type: <br>` + eventTypes[data[i].eventTypeID] + 
                        `</td><td style="width: 25%">Reimbursement Amount: <br>$` + data[i].reimAmount + `</td><td style="width: 25%"> Status: <br>` + statusCircle + `</td>` +
                    `</tr><tr>` +
                        `<td style="width: 25%"> <br> City: <br>`  + data[i].eventCity + `</td><td style="width: 25%"> <br> State: <br>` + data[i].eventState + 
                        `</td><td style="width: 25%"> <br> Country: <br>` + data[i].eventCountry + `</td><td style="width: 25%"> <br> Passed: <br>` + passedCircle + `</td>` +
                    `</tr>` + 
                    `</table>` +
                    `</td>` +
                `</tr>`;
            }

            viewSubmits += `</table>`;

            //console.log(viewSubmits);

            document.getElementById("interactive-menu").innerHTML = viewSubmits;
        }
    }
}

function resetLastNum (){
    lastNum = -1;
}

function revealSubmission(subNum){
    //console.log(subNum + ` : ` + subNum)

    if (subNum == lastNum){
        document.getElementById("sub" + lastNum).innerHTML = lastview;
        lastNum = -1;
        return;
    }

    var gradePres = ``;
    var daysMissed = ` `;

    if (data[subNum].optDaysMissed >= 0)
        daysMissed = `` + data[subNum].optDaysMissed;

    if (lastNum >= 0 && lastview != null){
        document.getElementById("sub" + lastNum).innerHTML = lastview;
    }

    lastNum = subNum;
    lastview = document.getElementById("sub" + subNum).innerHTML;

    if (data[subNum].gradePresNum <= 0)
        gradePres = `Presentation`;
    else
        gradePres = `` + data[subNum].gradePresNum;

    if (data[subNum].status == 7)
        statusCircle = `<span class="dot" style="background-color: #00f"></span>`;
    else if (data[subNum].status >= 0 && data[subNum].status < 7)
        statusCircle = `<span class="dot" style="background-color: #ff0"></span>`;
    else if (data[subNum].status < 0)
        statusCircle = `<span class="dot" style="background-color: #f00"></span>`;

    if (data[subNum].passed)
        passedCircle = `<span class="dot" style="background-color: #00f"></span>`;
    else 
        passedCircle = `<span class="dot" style="background-color: #f00"></span>`;


    document.getElementById("sub" + subNum).innerHTML = 
                `<table style="width: 100%; margin: auto">` +
                    `<tr>` + 
                        `<td style="width: 25%">Date Submitted: <br>` + data[subNum].apprSubDate.substring(0, 10) + 
                        `</td><td style="width: 25%">Event Type: <br>` + eventTypes[data[subNum].eventTypeID] + 
                        `</td><td style="width: 25%">Reimbursement Amount: <br>$` + data[subNum].reimAmount + `</td><td style="width: 25%"> Status: <br>` + statusCircle + `</td>` +
                    `</tr><tr>` +
                        `<td style="width: 25%"> <br> City: <br>`  + data[subNum].eventCity + `</td><td style="width: 25%"> <br> State: <br>` + data[subNum].eventState + 
                        `</td><td style="width: 25%"> <br> Country: <br>` + data[subNum].eventCountry + `</td><td style="width: 25%"> <br> Passed: <br>` + passedCircle + `</td>` +
                    `</tr><tr>` +
                        `<td style="width: 25%"> <br> Event Date: <br>`  + data[subNum].eventDate.substring(0, 10) + `</td><td style="width: 25%"> <br> Grade Requirement: <br>` + gradePres + 
                        `</td><td style="width: 25%"> <br> Description: <br>` + data[subNum].description + `</td><td style="width: 25%"> <br> Days Missed: <br>` + daysMissed + `</td>` +
                    `</tr><tr>` +
                `</table>` +
                `<table style="width: 100%; margin: auto">` +
                        `<td style="width: 100%"> <br> Justification: <br>` + data[subNum].justification + `</td>` +
                    `</tr><tr>` +
                        `<td style="width: 100%"> <br> Optional Attachment: <br>` + data[subNum].optEvtAttach + `</td>` +
                    `</tr><tr>` +
                        `<td style="width: 100%"> <br> Reject Reason: <br>` + data[subNum].rejectReason + `</td>` +
                    `</tr>` +
                `</table>`;
}