import { User } from './user';
import { Account } from './account';
// We define our interface here
const readline = require('readline');

const rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout
});

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
    });
}

function getPassword() {
    rl.question("Input your password: ",
     (answer) => {
        password = answer;
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
    process.exit();
}