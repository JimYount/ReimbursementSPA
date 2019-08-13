window.onload=()=>{
    var button = document.getElementById("button");
    button.addEventListener("click",buttonClick,true);
    document.getElementById("div").addEventListener("click",divClick,true);
}

function buttonClick(){
    console.log("Button clicked!");
}

function divClick(){
    event.stopPropagation();
    console.log("Div Clicked!");
}