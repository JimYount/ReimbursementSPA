export class Feline {
    // two non-access modifiers
    public static readonly NUM_LEGS=4;
    meow: string;
    fur: string;

    constructor(purr: string, fur: string) {
        this.meow = purr;
        this.fur = fur;
    }

    speak(): void {
        console.log(this.meow);
    }
    hairball(): number {
        return 1;
    }
}

export class Lion extends Feline {
    public static origin = "Africa";
    roar: string;
    constructor(purr: string, fur: string, roar: string) {
        super(purr, fur);
        this.roar = roar;
    }
    speak() {
        console.log(this.roar);
    }
}