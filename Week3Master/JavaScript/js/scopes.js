/*
There are 3 scopes in JS
Global
Function
Block (es6)
*/
var a = "ten"; // the function that this
        // var is in is the global func.
h = "fifteen"; // global
console.log(h);
console.log(a);

for(i = 0; i<5; i++) {
    const block = "hello"; //block scoped
    console.log(i);
    console.log(block);
} 
console.log("outside of block");
//console.log(block);
console.log(i);

let g = 3; // global block
function fun() {
    console.log(b);
    global = "hello";
    var b = 6;
    console.log("Inside the function");
    console.log("global:"+global);
    console.log("a="+a);
    console.log("i="+i);
    console.log("g="+g);
    console.log("b="+b);
    return;
}
console.log("Calling fun");
fun();
console.log("Outside the function")
console.log("global:"+global);
console.log("a="+a);
console.log("i="+i);
console.log("g="+g);
//console.log("b="+b);

console.log("-------------");
var h = 3;
function bar() {
    console.log("bar h = "+h);
    var h = 5;
    console.log("bar h = "+h);
}
bar();
console.log(h);
console.log("----------------");

/* Closure
An inner function has access to the 
scope of an outer function, even after
the scope of the outer function has "ended".
*/
// in JS, closure can mimic encapsulation
function foo() {
    this.hidden = 6;
}
var h = new foo();
console.log(h);

function encapsulatedFoo(){
    var hidden = 6;
    return {
        h: 5,
        getHidden: function() {
            return hidden;
        },
        setHidden: function(num) {
            hidden = num;
        }
    };
}
h = encapsulatedFoo();
console.log(h);
console.log(h.getHidden());

// Prototyping
/*
In JS, an object has a reference to
another object to which it can delegate.
This object is called the prototype.
Whenever an object is asked for a field
the object's keys are searched. If no 
match is found, the keys of the prototype
are then searched and so on.
*/

var bean = function() {
    this.name = "Bean";
    this.type= "Green";
    this.grow = function(){
        return "I grew 3 inches";
    };
}
console.log(bean);
var greenBean = new bean();
console.log(greenBean);

console.log(greenBean.grow());

var gmoBean = function() {
    this.name = "GMOBean";
    this.grow = function() {
        return "I grew 5 inches";
    }
};
gmoBean.prototype = new bean();
gmoBean.prototype.constructor=gmoBean;

var b = new gmoBean();
console.log(b);
console.log(b.grow());
console.log(b.__proto__.grow());

console.log(b instanceof bean);
console.log(b instanceof gmoBean);
console.log(b instanceof {}.constructor);
console.log(b instanceof [].constructor);