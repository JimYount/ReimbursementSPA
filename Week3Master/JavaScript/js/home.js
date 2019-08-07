console.log("Hello World from my home.js file.");
// line comments
/*
block comments
*/

var a = "3";
for( var i = 0; i<=a; i++) {
    console.log(i);
    console.log(i==a); // due to type coercion, the string and number 3 are equal
    console.log(i===a);
}