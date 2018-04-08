export interface Transaction {
  id: number;
  date: any;
  description: string;
  type: string;
  status: string;
  amount: number;
  availableBalance: number;
}
