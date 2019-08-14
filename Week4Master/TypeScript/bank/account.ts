export class Account {
<<<<<<< HEAD
<<<<<<< HEAD
    public accounts: Array<number>[] = [[1025],
                                        [3002],
                                        [543090]];
=======
    
>>>>>>> fc020bd316d0d9ebc1e3773236205ad7d7643c3d
=======
    balance: number;
    type: string;

    constructor(balance: number, type: string){
        this.balance = balance;
        this.type=type;
    }

    withdraw(amount: number){
        this.balance = this.balance - amount;
    }
    deposit(amount: number){
        this.balance = +this.balance + +amount;
    }
>>>>>>> ef2e09b3b88613f254d11d9efa67910ccda21b12
}