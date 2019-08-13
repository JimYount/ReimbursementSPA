/*
JavaScript Types
    Number
    String
    Symbol
    Boolean
    Null
    Undefined

    Object (Arrays and Functions)
*/

a = "ten"; // String
b = 10; // number
c = true; // boolean
d = {}; // object
e = null; // null
f = ""; // string
g = (0/0); // NaN - Not A Number - Number
h = []; // array
i = function(){}; // function
//j; // undefined

var list = [a,b,c,d,e,f,g,h,i];

// Standard for loop
// for(var k = 0; k < list.length; k++){
//     console.log(list[k]);
// }

// Enhanced For Loop
// for( element in list ) {
//     // An enhanced for loop iterates through the keys of an object
//     console.log(element);
//     console.log(list[element]);
// }

// For each function in the array prototype
list.forEach(
    // For each function takes in a callback function
        // A Callback function is a function that will be called
        // by the function that takes it in as a parameter
    // The For Each function calls the given function for every
    // element in the array.
    function(value, index) {
        console.log(index+" "+value);
    }
)