"use strict";
<<<<<<< HEAD
exports.__esModule = true;
var account_1 = require("./account");
var user_1 = require("./user");
=======
Object.defineProperty(exports, "__esModule", { value: true });
<<<<<<< HEAD
>>>>>>> fc020bd316d0d9ebc1e3773236205ad7d7643c3d
// Here is how to do a user interface in node.js
=======
var user_1 = require("./user");
var account_1 = require("./account");
// We define our interface here
>>>>>>> ef2e09b3b88613f254d11d9efa67910ccda21b12
var readline = require('readline');
var rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout
});
<<<<<<< HEAD
<<<<<<< HEAD
var u = new user_1.User();
var acct = new account_1.Account();
var username;
var password;
var index;
var input;
=======
var username;
var password;
>>>>>>> fc020bd316d0d9ebc1e3773236205ad7d7643c3d
=======
// Set up
var users = [
    new user_1.User('Richard', 'pass'),
    new user_1.User('Nickolas', 'pass2'),
    new user_1.User('Carolyn', 'pass3')
];
users.forEach(function (user) {
    user.accounts = [];
    user.accounts.push(new account_1.Account(Math.random() * 1001, 'Checking'));
    user.accounts.push(new account_1.Account(Math.random() * 100001, 'Savings'));
});
var username;
var password;
var loggedUser;
var account;
>>>>>>> ef2e09b3b88613f254d11d9efa67910ccda21b12
main();
function main() {
    printUsers();
    getUsername();
}
function printUsers() {
    users.forEach(function (user) { console.log(user); });
    var maxMoney = 0;
    var maxUser;
    users.forEach(function (user) {
        var money = 0;
        //console.log(user);
        user.accounts.forEach(function (acct) { money += acct.balance; });
        if (maxMoney < money) {
            maxMoney = money;
            maxUser = user.username;
        }
    });
    console.log("The user with the most money is " + maxUser + " with " + maxMoney);
}
function getUsername() {
    rl.question("Input your username: ('q' to quit) ", function (answer) {
        if (answer === 'q') {
            exit();
        }
        else {
            username = answer;
            getPassword();
        }
    });
}
function getPassword() {
    rl.question("Input your password: ", function (answer) {
        password = answer;
<<<<<<< HEAD
<<<<<<< HEAD
        checkUser();
    });
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
    console.log("Press 1 to deposit");
    console.log("Press 2 to withdraw");
    console.log("Press 9 to log out");
    console.log("Press 0 to exit");
    rl.question("Please Select an Option ", function (answer) {
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
                rl.question("How Much? ", function (answer) {
                    input = answer;
                    console.log("");
                    acct.accounts[index][0] += +input;
                    manageAccount();
                });
                break;
            case 2:
                rl.question("How Much? ", function (answer) {
                    input = answer;
                    console.log("");
                    if (+input <= acct.accounts[index][0])
                        acct.accounts[index][0] -= +input;
                    else {
                        console.log("You don't have that much money.");
                        console.log("");
                    }
                    manageAccount();
                });
                break;
            default:
                console.log("Invalid Entry");
                console.log("");
                manageAccount();
                break;
        }
    });
=======
        displayUser();
=======
        login();
    });
}
function login() {
    loggedUser = users.find(function (user) { return user.login(username, password); });
    if (loggedUser) {
        options();
    }
    else {
        getUsername();
    }
}
function options() {
    loggedUser.accounts.forEach(function (acc) {
        console.log(acc.type);
    });
    rl.question("Choose an account: ('q' to quit) ", function (answer) {
        if (answer === 'q') {
            exit();
        }
        else {
            account = loggedUser.accounts.find(function (acc) { return acc.type === answer; });
            if (account) {
                operations();
            }
            else {
                options();
            }
        }
>>>>>>> ef2e09b3b88613f254d11d9efa67910ccda21b12
    });
    function operations() {
        console.log("Current Balance: " + account.balance);
        rl.question("'w' for withdraw, 'd' for deposit: ", function (answer) {
            if (answer === 'w') {
                rl.question("Amount: ", function (answer) {
                    account.withdraw(+answer);
                    console.log("Current Balance: " + account.balance);
                    console.log("Logging out");
                    getUsername();
                });
            }
            else {
                if (answer === 'd') {
                    rl.question("Amount: ", function (answer) {
                        account.deposit(+answer);
                        console.log("Current Balance: " + account.balance);
                        console.log("Logging out");
                        getUsername();
                    });
                }
                else {
                    operations();
                }
            }
        });
    }
}
function exit() {
    rl.close();
    process.exit();
>>>>>>> fc020bd316d0d9ebc1e3773236205ad7d7643c3d
}
