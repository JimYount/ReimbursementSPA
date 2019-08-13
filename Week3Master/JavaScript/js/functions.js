// Unhoisted variables

// globally defined variable
a = 5;

// block scoped (es6+)
let c = 6;
const E = 5;

console.log(a);
console.log(b);
console.log(c);
console.log(E);

b = 5;

// Hoisted Variable
// Function scoped
// Vars are found and declared at the top
// of their scope by the interpreter
// They are not defined.

var b = 6;

function add(first, second) {
    console.log(first);
    console.log(second);
    return first+second;
}

console.log(add(5,6));
console.log(add(5));
console.log(add(5,5,5,5,5,5,5));

function add(first, second) {
    //console.log(arguments);
    let sum = 0;
    for(elements in arguments) {
        sum += arguments[elements];
    }
    return sum;
}

function add(first, second) {
    if(arguments.length === 1) {
        var total = first;
        function innerAdd(num) {
            if(num!== undefined) {
                total += num;
                return innerAdd;
            } else {
                return total;
            }
        }
        return innerAdd;
    } else {
        let sum = 0;
        for (elements in arguments) {
            sum += arguments[elements];
        }
        return sum;
    }
}

console.log(add(5)(6)(4)(3)(6)()); //24
