import { User } from './user';

export class Employee extends User {
  supervisor: Employee;
  title: string;
}
