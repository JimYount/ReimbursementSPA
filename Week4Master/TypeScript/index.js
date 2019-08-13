window.onload = test;
function test() {
    var input = document.getElementById("one");
    console.log(input);
    console.log(input.value);
    typeTest(+input.value);
}
function typeTest(number) {
    document.getElementById("two").innerHTML = String(number);
}
