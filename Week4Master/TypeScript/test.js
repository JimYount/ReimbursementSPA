"use strict";
exports.__esModule = true;
var cat_1 = require("./cat");
var cat = new cat_1.Feline('mrr', 'brown');
console.log(cat);
console.log(cat_1.Feline.NUM_LEGS);
console.log(cat.hairball());
var lion = new cat_1.Lion('mrr', 'tan', 'roar');
console.log(lion);
console.log(lion instanceof cat_1.Feline);
cat.speak();
lion.speak();
