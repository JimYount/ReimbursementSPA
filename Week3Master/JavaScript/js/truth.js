// Truthy vs Falsey values

// Truthy = A value which is considered to be boolean true
// Falsey = A value which is considered to be boolean false

// truthy values in js = non-zero numbers that are not NaN,
// non-empty strings, non-null objects, and true.

// falsey values in js = zero, NaN, empty strings, null, and false

if(10) {
    console.log("10 is true");
} else {
    console.log("10 is false");
}
if(-10) {
    console.log("-10 is true");
} else {
    console.log("-10 is false");
}
if(true) {
    console.log("true is true");
} else {
    console.log("true is false");
}
if("true") {
    console.log("\"true\" is true");
} else {
    console.log("\"true\" is false");
}
if(0) {
    console.log("0 is true");
} else {
    console.log("0 is false");
}
if(false) {
    console.log("false is true");
} else {
    console.log("false is false");
}
if("false") {
    console.log("\"false\" is true");
} else {
    console.log("\"false\" is false");
}
if(""&&''){
    console.log("'' and \"\" are true")
} else {
    console.log("'' and \"\" are false")
}
if(null){
    console.log("null is true");
} else {
    console.log("null is false");
}
if([]) {
    console.log("[] is true");
} else {
    console.log("[] is false");
}
if({}) {
    console.log("{} is true");
} else {
    console.log("{} is false");
}
if((0/0)) {
    console.log(`${0/0} is true`);
} else {
    console.log(`${0/0} is false`);
    var a = 0/0;
    console.log(typeof a);
}

var a = 5;
var b = 6;

for(var c = 0; c<b; c++){
    a = 1+a*b;
}

if(a=b) {
    console.log(a);
    console.log("success");
} else {
    console.log(a);
    console.log("failure");
}