package f.object.ex2;

public class BankAccount {
	int balance = 0;
	
	public int deposit(int amount) {
		balance += amount;
		return balance;
	}
	
	public int withdraw(int amount) {
		balance -= amount;
		return balance;
	}
	
	public int checkMyBalance() {
		System.out.println("잔액 : " + balance);
		return balance;
	}
	
	public int checkMyBalance(BankAccount ref) {
		System.out.println("잔액 : " + ref.balance);
		ref.balance = -1000;
		return ref.balance;
	}
}
