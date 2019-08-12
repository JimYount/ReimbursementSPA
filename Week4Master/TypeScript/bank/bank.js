"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
// Here is how to do a user interface in node.js
var readline = require('readline');
var rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout
});
var username;
var password;
main();
function main() {
    login();
}
function login() {
    rl.question("Input your username: ", function (answer) {
        username = answer;
        getPassword();
    });
}
function getPassword() {
    rl.question("Input your password: ", function (answer) {
        password = answer;
        displayUser();
    });
}
function displayUser() {
    console.log(username + " " + password);
    process.exit();
}
