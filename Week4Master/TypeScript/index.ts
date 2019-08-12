window.onload=test;

function test() {
    let input = <HTMLInputElement>document.getElementById("one");
    console.log(input);
    console.log(input.value);
    typeTest(+input.value);
}

function typeTest(number: Number) {
    document.getElementById("two").innerHTML=String(number);
}