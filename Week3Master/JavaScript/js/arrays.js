// All objects in JavaScript consist of key value pairs

var obj = {0: 1, "1": 2, "2": 3};
console.log(obj);
var arr = [1,2,3];
console.log(arr);

// normally to access an object's values
// we just say obj.key
// if they keys are numbers, we have
// to use array notation
console.log(obj[0]);
console.log(arr[0]);
console.log(obj["0"]);
console.log(arr["0"]);

// arr.t = 5;
// console.log(arr);
// console.log(arr.t);
// console.log(arr["t"]);

// ARRAYS ARE JUST OBJECTS
// OBJECTS are key value pairs
// KEYS ARE STRINGs
// values are any type

// Array indices in JS are strings
// when you search an index in an array
// it performs string comparison
// JS does not have access to memory
// JS does not have contiguously addressed memory arrays
console.log(arr.length);
console.log(arr);
arr.length = 5;
console.log(arr);
arr.length = 1;
console.log(arr);

arr.length = 7;
console.log(arr);

arr[4]=6;
console.log(arr);
arr[9] = 5;
console.log(arr);
// arr["t"] = 5;
// console.log(arr);

delete arr[0];
console.log(arr);
console.log(arr[14]);
var arr2 = [1,2,3,4,5];
console.log(arr2);
arr2.splice(3,1);
console.log(arr2);