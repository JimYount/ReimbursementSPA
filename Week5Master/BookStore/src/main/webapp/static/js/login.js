/*
This file's job is going to be to make sure that someone is logged in on every page.
It should also, allow people to log in.
*/
var navbar = `
<nav class="navbar navbar-expand-lg navbar-light bg-light">
	<a class="navbar-brand">
        <span class="badge-primary">Richard's Bookstore</span>
    </a>
	<ul class="navbar-nav mr-auto" id="authent">
	</ul>
</nav>`;
var unauthenticated = `
		<li class="nav-item">
			Username: <input type="text" id="username">
		</li>
		<li class="nav-item">
			Password: <input type="password" id="password">
		</li>
		<li class="nav-item">
			<button class="btn btn-primary" id="login">Login</button>
		</li>
        `;
var authenticated = `
		<li class="nav-item">
			Welcome <span id="authUserName"></span> 
		</li>
		<li class="nav-item">
			<button class="btn btn-danger" id="logout">Logout</button>
        </li>`;

var employee = null;
var customer = null;
var baseURL = "/BookStore/";

function addNavBar() {
    let div = document.createElement("div");
    div.innerHTML=navbar;
    let body = document.getElementsByTagName("body")[0];
    body.insertBefore(div, body.childNodes[0]);
    document.getElementById("authent").innerHTML=unauthenticated;

    // add event listeners
    document.getElementById("login").addEventListener("click", authenticate);
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
            var data = JSON.parse(xhttp.responseText);
            console.log(data);
            employee = data.employee;
            customer = data.customer;
            document.getElementById("authent").innerHTML=authenticated;
            document.getElementById("logout").addEventListener("click", logout);
            if(customer) {
                document.getElementById("authUserName").innerHTML=customer.first+" "+customer.last;
            }
            if(employee) {
                document.getElementById("authUserName").innerHTML=employee.first+" "+
                    employee.last+", "+employee.title;
                let btns = document.getElementsByClassName("emp-btn");
                for(let i = 0; i< btns.length; i++) {
                    btns[i].disabled = false;
                }
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
			document.getElementById("login").addEventListener("click",authenticate);
			document.getElementById("password").onkeydown=checkPasswordEnter;
		}
	}
}