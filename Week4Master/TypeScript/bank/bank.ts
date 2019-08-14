<<<<<<< HEAD
<<<<<<< HEAD
import { Account } from './account';
import { User } from './user';
=======
import {Account} from './account';
>>>>>>> fc020bd316d0d9ebc1e3773236205ad7d7643c3d

// Here is how to do a user interface in node.js
=======
import { User } from './user';
import { Account } from './account';
// We define our interface here
>>>>>>> ef2e09b3b88613f254d11d9efa67910ccda21b12
const readline = require('readline');

const rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout
});

<<<<<<< HEAD
<<<<<<< HEAD
let u = new User();
let acct = new Account();

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
    new User('Richard', 'pass'),
    new User('Nickolas', 'pass2'),
    new User('Carolyn', 'pass3')
];

users.forEach((user)=>{
    user.accounts = [];
    user.accounts.push(new Account(
        Math.random()*1001, 'Checking'));
    user.accounts.push(new Account(
        Math.random()*100001, 'Savings'));
});

var username;
var password;

var loggedUser;
var account;

>>>>>>> ef2e09b3b88613f254d11d9efa67910ccda21b12
main();

function main () {
    printUsers();
    getUsername();
}
function printUsers() {
    users.forEach((user) => {console.log(user);});
    let maxMoney = 0;
    let maxUser;
    users.forEach((user) => {
        let money = 0;
        //console.log(user);
        user.accounts.forEach((acct) => {money+=acct.balance});
        if (maxMoney < money){
            maxMoney=money;
            maxUser = user.username;
        }
    });
    console.log(`The user with the most money is ${maxUser} with ${maxMoney}`);

<<<<<<< HEAD
function login() {
    rl.question("Input your username: ", (answer) => {
<<<<<<< HEAD
        username = answer;
        getPassword();
    });
}
function getPassword() {
    rl.question("Input your password: ", (answer) => {
        password = answer;
        checkUser();
=======
        username= answer;
        getPassword();
=======
}

function getUsername() {
    rl.question("Input your username: ('q' to quit) ",
     (answer) => {
        if(answer === 'q') {
            exit();
        } else {
            username = answer;
            getPassword();
        }
>>>>>>> ef2e09b3b88613f254d11d9efa67910ccda21b12
    });
}

function getPassword() {
    rl.question("Input your password: ",
     (answer) => {
        password = answer;
<<<<<<< HEAD
        displayUser();
>>>>>>> fc020bd316d0d9ebc1e3773236205ad7d7643c3d
    })
}

function displayUser() {
<<<<<<< HEAD
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
=======
    console.log(username+" "+password);
=======
        login();
    });
}

function login() {
    loggedUser = users.find((user) => user.login(username, password));
    if (loggedUser) {
        options();
    } else {
        getUsername();
    }
}

function options() {
    loggedUser.accounts.forEach((acc) => {
        console.log(acc.type);
    });
    rl.question("Choose an account: ('q' to quit) ", (answer) => {
        if (answer === 'q') {
            exit();
        } else {
            account = loggedUser.accounts.find((acc) => acc.type === answer);
            if (account) {
                operations();
            } else {
                options();
            }
        }
    });
    function operations() {
        console.log("Current Balance: " + account.balance);
        rl.question("'w' for withdraw, 'd' for deposit: ", (answer) => {
            if (answer === 'w') {
                rl.question("Amount: ", (answer) => {
                    account.withdraw(+answer);
                    console.log("Current Balance: " + account.balance);
                    console.log("Logging out");
                    getUsername();
                });
            } else {
                if (answer === 'd') {
                    rl.question("Amount: ", (answer) => {
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
>>>>>>> ef2e09b3b88613f254d11d9efa67910ccda21b12
    process.exit();
}
>>>>>>> fc020bd316d0d9ebc1e3773236205ad7d7643c3d
