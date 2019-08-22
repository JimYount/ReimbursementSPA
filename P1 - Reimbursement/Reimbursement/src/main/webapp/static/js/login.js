/*
This file's job is going to be to make sure that someone is logged in on every page.
It should also, allow people to log in.
*/
var navbar = `<nav class="navbar navbar-expand-lg navbar-light" id="authent">
</nav>`;
var unauthenticated = ``;
var authenticated = `
<table>
    <tr class="nav-item">
        <td>
            <span id="authUserName"></span> 
        </td>
    </tr>
    <tr class="nav-item">
        <td>
            <button class="btn btn-danger" id="logout">Logout</button>
        </td>
    </tr>
</table>`;
var loginString = `<br> <br> <br> <br>
<h1 style="text-align: center">Login</h1>
<br> <br>
<div class="formgroup" style="width: 15%; margin: auto">
    <label for="username">Username:</label> <input class="form-control simple-book" type="text"
        id="username" /> <br>
</div>
<div class="formgroup" style="width: 15%; margin: auto">
    <label for="password">Password:</label> <input class="form-control simple-book" type="text"
        id="password" /> <br>
</div>
<div style="width: 15%; margin: auto">
    <button type="submit" class="btn btn-primary emp-btn" id="submitbtn"
        onclick="authenticate()">Submit</button>
</div>`;

var employee = null;
var customer = null;
var baseURL = "/Reimbursement/";

window.onload=()=>{
    addNavBar();
    showLogin();
}

function addNavBar() {
    let div = document.createElement("div");
    div.innerHTML=navbar;
    let body = document.getElementsByTagName("body")[0];
    body.insertBefore(div, body.childNodes[0]);
    document.getElementById("authent").innerHTML=unauthenticated;
}
function showLogin(){
    document.getElementById("mainContent").innerHTML=loginString;

    // add event listeners
    document.getElementById("submitbtn").addEventListener("click", authenticate);
    document.getElementById("password").onkeydown=checkPasswordEnter;
    authenticate();
}
function checkPasswordEnter(){
    if(event.which===13)
        authenticate();
}
function authenticate(){
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange=loginSuccess;
    xhttp.open("POST", baseURL+"login");
    xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    let user = document.getElementById("username").value;
    let pass = document.getElementById("password").value;
    xhttp.send("user="+user+"&pass="+pass);

    function loginSuccess() {
        if(xhttp.readyState===4 && xhttp.status===200) {
            //console.log(xhttp.responseText);
            var data = JSON.parse(xhttp.responseText);
            console.log(data);

            employee = data.employee;
            
            document.getElementById("mainContent").innerHTML=``;
            document.getElementById("authent").innerHTML=authenticated;
            document.getElementById("logout").addEventListener("click", logout);
            if(employee) {
                document.getElementById("authUserName").innerHTML=employee.firstName+" "+
                    employee.lastName + "      ";
                let btns = document.getElementsByClassName("emp-btn");
                for(let i = 0; i< btns.length; i++) {
                    btns[i].disabled = false;
                }
                showMenu();
            }
        }
    }
}

function logout(){
	console.log("logging out");
	var xhttp=new XMLHttpRequest();
	xhttp.onreadystatechange=finish;
	xhttp.open("DELETE", baseURL+"login");
	xhttp.send();
	
	function finish(){
		if(xhttp.readyState===4){
			document.getElementById("authent").innerHTML=unauthenticated;
			let btns = document.getElementsByClassName("emp-btn");
			for(let i = 0; i<btns.length; i++){
				btns[i].disabled=true;
			}
			showLogin();
		}
	}
}