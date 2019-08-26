var viewManage = ``;
var statusCircle = `<span class="dot" style="background-color: #000"></span>`;
var passedCircle = `<span class="dot" style="background-color: #000"></span>`;
var eventTypes = [`University Courses`, `Seminars`, `Certification Preparation Classes`, `Certification`, `Technical Training`, `Other`];

var lastviewMan;
var lastNumMan = -1;

var baseURL = "/Reimbursement/";
var dataMan;

function manageSubmissions() {
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = manageSuccess;
    xhttp.open("POST", baseURL + "manage");
    xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    
    xhttp.send("employeeID=" + getEmployeeID());

    function manageSuccess(){
        if (xhttp.readyState === 4 && xhttp.status === 200) {
            dataMan = JSON.parse(xhttp.responseText);

            //console.log(dataMan);
            viewManage = `
            <h1 style="text-align: center">Assigned Reimbursements</h1>
            <br> <br>
            <table border="1" style="width: 80%; margin: auto" class="submission-box">`;

            for(var i = 0; i < dataMan.length; i++) {
                if (dataMan[i].status == 7)
                    statusCircle = `<span class="dot" style="background-color: #00f"></span>`;
                else if (dataMan[i].status >= 0 && dataMan[i].status < 7)
                    statusCircle = `<span class="dot" style="background-color: #ff0"></span>`;
                else if (dataMan[i].status > 7)
                    statusCircle = `<span class="dot" style="background-color: #f00"></span>`;

                if (dataMan[i].passed)
                    passedCircle = `<span class="dot" style="background-color: #00f"></span>`;
                else 
                    passedCircle = `<span class="dot" style="background-color: #f00"></span>`;

                viewManage += 
                `<tr>` +
                    `<td id = "sub` + i + `">` +
                    `<table style="width: 100%; margin: auto" onclick="revealManSubmission(` + i + `)">` +
                    `<tr>` + 
                        `<td style="width: 25%">Date Submitted: <br>` + dataMan[i].apprSubDate.substring(0, 10) + `</td><td style="width: 25%">Event Type: <br>` + eventTypes[dataMan[i].eventTypeID] + 
                        `</td><td style="width: 25%">Reimbursement Amount: <br>$` + dataMan[i].reimAmount + `</td><td style="width: 25%"> Status: <br>` + statusCircle + `</td>` +
                    `</tr><tr>` +
                        `<td style="width: 25%"> <br> City: <br>`  + dataMan[i].eventCity + `</td><td style="width: 25%"> <br> State: <br>` + dataMan[i].eventState + 
                        `</td><td style="width: 25%"> <br> Country: <br>` + dataMan[i].eventCountry + `</td><td style="width: 25%"> <br> Passed: <br>` + passedCircle + `</td>` +
                    `</tr>` + 
                    `</table>` +
                    `</td>` +
                `</tr>`;
            }

            viewManage += `</table>`;

            //console.log(viewManage);

            document.getElementById("interactive-menu").innerHTML = viewManage;
        }
    }
}

function revealManSubmission(subNum){
    //console.log(subNum + ` : ` + subNum)

    if (subNum == lastNumMan){
        document.getElementById("sub" + lastNumMan).innerHTML = lastviewMan;
        lastNumMan = -1;
        return;
    }

    var gradePres = ``;
    var daysMissed = ` `;

    if (dataMan[subNum].optDaysMissed >= 0)
        daysMissed = `` + dataMan[subNum].optDaysMissed;

    if (lastNumMan >= 0 && lastview != null){
        document.getElementById("sub" + lastNumMan).innerHTML = lastviewMan;
    }

    lastNumMan = subNum;
    lastviewMan = document.getElementById("sub" + subNum).innerHTML;

    if (dataMan[subNum].gradePresNum <= 0)
        gradePres = `Presentation`;
    else
        gradePres = `` + dataMan[subNum].gradePresNum;

    if (dataMan[subNum].status == 7)
        statusCircle = `<span class="dot" style="background-color: #00f"></span>`;
    else if (dataMan[subNum].status >= 0 && dataMan[subNum].status < 7)
        statusCircle = `<span class="dot" style="background-color: #ff0"></span>`;
    else if (dataMan[subNum].status < 0)
        statusCircle = `<span class="dot" style="background-color: #f00"></span>`;

    if (dataMan[subNum].passed)
        passedCircle = `<span class="dot" style="background-color: #00f"></span>`;
    else 
        passedCircle = `<span class="dot" style="background-color: #f00"></span>`;


    document.getElementById("sub" + subNum).innerHTML = 
                `<table style="width: 100%; margin: auto" onclick="revealManSubmission(` + subNum + `)">` +
                    `<tr>` + 
                        `<td style="width: 25%">Date Submitted: <br>` + dataMan[subNum].apprSubDate.substring(0, 10) + 
                        `</td><td style="width: 25%">Event Type: <br>` + eventTypes[dataMan[subNum].eventTypeID] + 
                        `</td><td style="width: 25%">Reimbursement Amount: <br>$` + dataMan[subNum].reimAmount + `</td><td style="width: 25%"> Status: <br>` + statusCircle + `</td>` +
                    `</tr><tr>` +
                        `<td style="width: 25%"> <br> City: <br>`  + dataMan[subNum].eventCity + `</td><td style="width: 25%"> <br> State: <br>` + dataMan[subNum].eventState + 
                        `</td><td style="width: 25%"> <br> Country: <br>` + dataMan[subNum].eventCountry + `</td><td style="width: 25%"> <br> Passed: <br>` + passedCircle + `</td>` +
                    `</tr><tr>` +
                        `<td style="width: 25%"> <br> Event Date: <br>`  + dataMan[subNum].eventDate.substring(0, 10) + `</td><td style="width: 25%"> <br> Grade Requirement: <br>` + gradePres + 
                        `</td><td style="width: 25%"> <br> Description: <br>` + dataMan[subNum].description + `</td><td style="width: 25%"> <br> Days Missed: <br>` + daysMissed + `</td>` +
                    `</tr><tr>` +
                `</table>` +
                `<table style="width: 100%; margin: auto" onclick="revealManSubmission(` + subNum + `)">` +
                        `<td style="width: 100%"> <br> Justification: <br>` + dataMan[subNum].justification + `</td>` +
                    `</tr><tr>` +
                        `<td style="width: 100%"> <br> Optional Attachment: <br>` + dataMan[subNum].optEvtAttach + `</td>` +
                    `</tr>` +
                `</table>` +
                `<table style="width: 100%; margin: auto">` +
                    `<tr>` +
                        `<td style="width: 100%" id="commentbtnctr"> <br> <br>` + 
                            `<label for="reqReason">Reason for Choice:</label> <input class="form-control simple-book" type="text"
                            id="reqReason" />` + `</td>` +
                    `</tr>` +
                `</table>` +
                `<table style="width: 100%; margin: auto; text-align: center">` +
                    `<tr>` +
                        `<td style="width: 33%" id="acceptbtnctr"> <br> <br>` + 
                            `<button type="submit" class="btn btn-primary emp-btn" id="acceptbtn"
                            onclick="acceptSubmission(` + subNum +`)">Accept</button>` + `</td>` +
                        `<td style="width: 33%"> <br> <br>` + 
                            `<button type="submit" class="btn btn-primary emp-btn" id="commentbtn"
                            onclick="commentSubmission(`+ subNum +`)">Request Comments</button>` + `</td>` +
                        `<td style="width: 33%" id="rejectbtnctr"> <br> <br>` + 
                            `<button type="submit" class="btn btn-danger" id="rejectbtn"
                            onclick="rejectSubmission(` + subNum + `)">Reject</button>` + `</td>` +
                    `</tr>` +
                `</table>` + 
                `<table style="width: 100%; margin: auto">` +
                    `<tr>` +
                        `<td style="width: 100%" id="commentStatus"> <br> <br>` + `</td>` +
                    `</tr>` +
                `</table>`;
}

function resetLastNumMan(){
    lastNumMan = -1;
}

function commentSubmission(subNum){
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = commentSuccess;
    xhttp.open("POST", baseURL + "comment");
    xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    
    let commentReq = document.getElementById("reqReason").value;
    
    if (commentReq != ``)
        xhttp.send("employeeID=" + getEmployeeID() + "&commentReq=" + commentReq + "&subID=" + dataMan[subNum].submissionID);
    else 
        document.getElementById("commentStatus").innerHTML = `Please give a reason for your comment request.`;

    function commentSuccess(){
        manageSubmissions();
        document.getElementById("commentStatus").innerHTML = `Comment request submitted successfully.`;
    }
}

function acceptSubmission(subNum){
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = acceptSuccess;
    xhttp.open("POST", baseURL + "accept");
    xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    
    xhttp.send("subID=" + dataMan[subNum].submissionID);

    function acceptSuccess(){
        manageSubmissions();
    }
}

function rejectSubmission(subNum){
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = rejectSuccess;
    xhttp.open("POST", baseURL + "reject");
    xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");

    let commentReq = document.getElementById("reqReason").value;
    
    if (commentReq != ``)
        xhttp.send("subID=" + dataMan[subNum].submissionID + "&commentReq=" + commentReq);
    else 
        document.getElementById("commentStatus").innerHTML = `Please give a reason for rejecting the application.`;

    function rejectSuccess(){
        manageSubmissions();
    }
}