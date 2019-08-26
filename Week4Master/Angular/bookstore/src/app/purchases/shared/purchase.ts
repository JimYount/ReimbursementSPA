import { Customer } from 'src/app/shared/user/customer';
import { InvoiceLine } from './invoice-line';

export class Purchase {
    id: number;
    cust: Customer;
    total: number;
    status: string;
    invoiceLines: InvoiceLine[];
}
