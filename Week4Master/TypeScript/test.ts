import { Feline, Lion } from './cat';

let cat = new Feline ('mrr', 'brown');
console.log(cat);
console.log(Feline.NUM_LEGS);
console.log(cat.hairball());
let lion = new Lion('mrr', 'tan', 'roar');
console.log(lion);
console.log(lion instanceof Feline);
cat.speak();
lion.speak();