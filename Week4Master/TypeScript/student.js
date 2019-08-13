// We have classes
var Student = /** @class */ (function () {
    // Constructors
    // In a constructor, any argument that is given an access modifier
    // will be declared as a object-level variable
    function Student(firstName, middleInitial, lastName) {
        this.firstName = firstName;
        this.middleInitial = middleInitial;
        this.lastName = lastName;
        this.social = '111234444';
        this.haircolor = 'red';
        if (middleInitial)
            this.fullName = firstName + ' ' + middleInitial + ' ' + lastName;
        else
            this.fullName = firstName + ' ' + lastName;
    }
    Student.prototype.setHairColor = function (haircolor) {
        this.haircolor = haircolor;
    };
    return Student;
}());
var stu = new Student('Richard', 'L', 'Orr');
stu.setHairColor('brown');
console.log(stu);
