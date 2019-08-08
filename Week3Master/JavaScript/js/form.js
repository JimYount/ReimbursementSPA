window.onload=()=>{
    console.log("hello");
    // get an element by id
    var id = document.getElementById("t_id");
    var applePie = document.getElementById("t_name");
    var major = document.getElementById("t_major");
    console.log(id);
    console.log(applePie);
    console.log(major);

    console.log(id.value);

    // get an element by tag name
    var header = document.getElementsByTagName("h1");
    console.log(header);

    // get an element by class name
    var labs = document.getElementsByClassName("lab");
    console.log(labs);

    var paragraph = document.createElement("p");
    paragraph.innerHTML="Hello my name is JavaScript";
    let comp = document.getElementById("William's computer");
    comp.append(paragraph);

    b = document.getElementsByTagName("body")[0];
    console.log(b.innerHTML);
    let button = document.getElementById("button");
    button.addEventListener("click", updateMajor);
};

function updateMajor(){
    console.log("callback function");
    // Callback function: A function that I can give to another function
    // so that the other function can call it.
    var id = document.getElementById("t_id");
    var applePie = document.getElementById("t_name");
    var major = document.getElementById("t_major");

    var string = `${id.value}: ${applePie.value}, ${major.value}`;
    document.getElementById("me").innerHTML=string;
}