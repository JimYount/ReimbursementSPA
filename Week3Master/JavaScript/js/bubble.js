// global variables
// a-e the divs we wish to observe
// cap a variable that when true, enables capturing
// button will swap cap true/false

var cap = false;
var a = document.getElementById("A");
var b = document.getElementById("B");
var c = document.getElementById("C");
var d = document.getElementById("D");
var e = document.getElementById("E");
var button = document.getElementById("button");

window.onload=function() {
    button.innerHTML="Capture?";
    this.addEventListenersToDivs();
    button.addEventListener("click", switchCapture);
}

function switchCapture(){
    removeEventListenersFromDivs();
    if(!cap){
        cap=!cap;
        console.log("set to capture");
        button.innerHTML="Bubble?";
    } else {
        cap= !cap;
        console.log("set to bubble");
        button.innerHTML="Capture?";
    }
    addEventListenersToDivs();
}
function addEventListenersToDivs(){
    a.addEventListener("mouseover",selectWrapper,cap);
    a.addEventListener("mouseout",unSelectWrapper,cap);
    b.addEventListener("mouseover",selectWrapper,cap);
    b.addEventListener("mouseout",unSelectWrapper,cap);
    c.addEventListener("mouseover",selectWrapper,cap);
    c.addEventListener("mouseout",unSelectWrapper,cap);
    d.addEventListener("mouseover",selectWrapper,cap);
    d.addEventListener("mouseout",unSelectWrapper,cap);
    e.addEventListener("mouseover",selectWrapper,cap);
    e.addEventListener("mouseout",unSelectWrapper,cap);
}
function removeEventListenersFromDivs(){
    a.removeEventListener("mouseover",selectWrapper,cap);
    a.removeEventListener("mouseout",unSelectWrapper,cap);
    b.removeEventListener("mouseover",selectWrapper,cap);
    b.removeEventListener("mouseout",unSelectWrapper,cap);
    c.removeEventListener("mouseover",selectWrapper,cap);
    c.removeEventListener("mouseout",unSelectWrapper,cap);
    d.removeEventListener("mouseover",selectWrapper,cap);
    d.removeEventListener("mouseout",unSelectWrapper,cap);
    e.removeEventListener("mouseover",selectWrapper,cap);
    e.removeEventListener("mouseout",unSelectWrapper,cap);
}

function selectWrapper(){
    select(this);
}
function unSelectWrapper(){
    unSelect(this);
}

function select(element) {
    //event.stopPropagation();
    element.style.backgroundColor='Purple';
    console.log(element.id);
}
function unSelect(element) {
    element.style.backgroundColor='Orange';
}