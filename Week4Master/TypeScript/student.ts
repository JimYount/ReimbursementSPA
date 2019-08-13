// We have classes
class Student implements Person {
    // We have access modifiers
    // public: same as Java
    // private: same as Java
    // protected: only children

    // default (or no modifier): public
    // Three access levels
    //public firstName: string;
    //lastName: string; // public
    fullName: string;
    private social= '111234444';
    protected haircolor = 'red';

    // Constructors
    // In a constructor, any argument that is given an access modifier
    // will be declared as a object-level variable
    constructor (public firstName: string, protected middleInitial: string,
        public lastName: string) {
            if(middleInitial)
                this.fullName=firstName+' '+middleInitial+' '+lastName;
            else
                this.fullName=firstName+' '+lastName;
        }

        setHairColor(haircolor: string) {
            this.haircolor=haircolor;
        }
}

let stu = new Student('Richard', 'L', 'Orr');
stu.setHairColor('brown');
console.log(stu);
// console.log(stu.social);

// Interfaces!
interface Person {
    firstName: string;
    lastName: string;
}