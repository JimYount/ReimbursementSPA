"use strict";
<<<<<<< HEAD
exports.__esModule = true;
var User = /** @class */ (function () {
    function User() {
        this.users = [["jayount", "MyPa55", "Jim", "Yount"],
            ["jDean", "cool", "James", "Dean"],
            ["Lupin3rd", "$$$$$", "Arsène", "Lupin"]];
    }
=======
Object.defineProperty(exports, "__esModule", { value: true });
var User = /** @class */ (function () {
    function User(user, pass) {
        this.username = user;
        this.password = pass;
    }
    User.prototype.login = function (user, pass) {
        return this.username === user && this.password === pass;
    };
    User.prototype.getAccount = function (type) {
        return this.accounts.find(function (account) { return account.type === type; });
    };
>>>>>>> ef2e09b3b88613f254d11d9efa67910ccda21b12
    return User;
}());
exports.User = User;
