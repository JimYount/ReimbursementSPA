import { Account } from './account';
import { User } from './user';

// Here is how to do a user interface in node.js
const readline = require('readline');

const rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout
});

let u = new User();
let acct = new Account();

var username;
var password;
var index;
var input;
main();

function main() {
    login();
}

function login() {
    rl.question("Input your username: ", (answer) => {
        username = answer;
        getPassword();
    });
}
function getPassword() {
    rl.question("Input your password: ", (answer) => {
        password = answer;
        checkUser();
    })
}

function displayUser() {
    console.log("");
    console.log("");
    console.log("Welcome, " + u.users[index][2] + " " + u.users[index][3]);
}

function checkUser() {
    var foundUser = false;

    for (var i = 0; i < u.users.length; i++) {
        if (username == u.users[i][0] && password == u.users[i][1]) {
            index = i;
            foundUser = true;
            manageAccount();
        }
    }

    if (!foundUser) {
        console.log("");
        console.log("Incorrect login");
        console.log("");
        console.log("");
        login();
    }
}

function manageAccount() {
    displayUser();
    console.log("");
    console.log("Your balance is: " + acct.accounts[index][0]);
    console.log("Press 1 to deposit")
    console.log("Press 2 to withdraw")
    console.log("Press 9 to log out")
    console.log("Press 0 to exit")

    rl.question("Please Select an Option ", (answer) => {
        input = answer;

        console.log("");
        console.log("");

        switch (+input) {
            case 0:
                process.exit();
                break;
            case 9:
                login();
                break;
            case 1:
                rl.question("How Much? ", (answer) => {
                    input = answer;
                    console.log("");
                    acct.accounts[index][0] += +input;
                    manageAccount();
                })
                break;
            case 2:
                rl.question("How Much? ", (answer) => {
                    input = answer;
                    console.log("");
                    if (+input <= acct.accounts[index][0])
                        acct.accounts[index][0] -= +input;
                    else {
                        console.log("You don't have that much money.");
                        console.log("");
                    }
                    manageAccount();
                })
                break;
            default:
                console.log("Invalid Entry");
                console.log("");
                manageAccount();
                break;

        }
    })

}
