<<<<<<< HEAD
export class User {
    public users: Array<string>[] = [["jayount", "MyPa55", "Jim", "Yount"],
                                    ["jDean", "cool", "James", "Dean"],
                                    ["Lupin3rd", "$$$$$", "Arsène", "Lupin"]];
=======
import { Account } from './account';

export class User {
    username: string;
    password: string;
    accounts: Account[];

    constructor(user: string, pass: string){
        this.username=user;
        this.password=pass;
    }

    login(user: string, pass: string): boolean {
        return this.username===user && this.password === pass
    }

    getAccount(type: string): Account{
        return this.accounts.find((account)=>account.type===type);
    }
>>>>>>> ef2e09b3b88613f254d11d9efa67910ccda21b12
}