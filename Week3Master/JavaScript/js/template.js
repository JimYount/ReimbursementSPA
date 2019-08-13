//Template literal
// ES6 spec: enables expressions and returns
// inside of strings using the ` instead of ' or "

// returns in string
let oldString = "Hello, my name is Richard \n"+
" and I like \n cheese.";
console.log(oldString);
let templateLiteral = `Hello, my name is Richard
and I like
cheese.`;
console.log(templateLiteral);

let template = `
<table id = "data">
    <tr>
        <th> Name </th>
    </tr>
</table>
`;
oldString="<table id=\"data\"><tr><th>Name</th></tr></table>";

// Expressions = ${expression}

let name = "Richard";
console.log("Hi, my name is "+name);
console.log(`Hi, my name is ${name}`);

let price = 6.5;
let tax = 0.3;
console.log(`The price of your ${price} book is ${price*(1+tax)} dollars with tax.`);


// FLOATING POINT ARITHMETIC DOES NOT WORK IN JS
console.log(.1+.1+.1);
console.log(.3+.3+.3);
console.log(.1+.1);
console.log(.2+.1);
// EITHER USE A LIBRARY FOR FLOATING POINT OR SEND IT TO JAVA(WHICH CAN HANDLE IT)


// semi-colons are not strictly necessary
// they are there, though, whether you put them there or not.
// JavaScript performs semi-colon injection
function add() {
    return {
        h:5
    };
}

var h = add();
console.log(h);

