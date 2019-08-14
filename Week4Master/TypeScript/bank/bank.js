"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
var user_1 = require("./user");
var account_1 = require("./account");
// We define our interface here
var readline = require('readline');
var rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout
});
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
}
