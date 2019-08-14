"use strict";
<<<<<<< HEAD
exports.__esModule = true;
var Account = /** @class */ (function () {
    function Account() {
        this.accounts = [[1025],
            [3002],
            [543090]];
=======
Object.defineProperty(exports, "__esModule", { value: true });
var Account = /** @class */ (function () {
<<<<<<< HEAD
    function Account() {
>>>>>>> fc020bd316d0d9ebc1e3773236205ad7d7643c3d
=======
    function Account(balance, type) {
        this.balance = balance;
        this.type = type;
>>>>>>> ef2e09b3b88613f254d11d9efa67910ccda21b12
    }
    Account.prototype.withdraw = function (amount) {
        this.balance = this.balance - amount;
    };
    Account.prototype.deposit = function (amount) {
        this.balance = +this.balance + +amount;
    };
    return Account;
}());
exports.Account = Account;
