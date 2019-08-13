// A callback function is a function we define to be
// called by another function and is passed as a
// parameter to that function

// fat arrow notation
window.onload = () => {
    console.log("we started the thing.");
}

// with no curly braces, the one line gets returned
var a = (a,b) => a+b;
console.log(a(5,6));

// functions like forEach(), sort(), any of the array functions
// take in a callback function
var arr=[1,2,3,4,5];
arr.forEach((item)=>{console.log(item)});

// Fat arrow functions behave differently from normal funcitons
// this is because they relate to the 'this' keyword differently

// read about the "this" methods
//bind()
//apply()
//call()