import {Account} from './account';

// Here is how to do a user interface in node.js
const readline = require('readline');

const rl = readline.createInterface({
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
    rl.question("Input your username: ", (answer) => {
        username= answer;
        getPassword();
    });
}
function getPassword(){
    rl.question("Input your password: ", (answer) =>{
        password = answer;
        displayUser();
    })
}

function displayUser() {
    console.log(username+" "+password);
    process.exit();
}