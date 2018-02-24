
public class CheckingAccount extends Account {

	public CheckingAccount(String number, double balance, Customer customerInfo) {
		super(number, balance, customerInfo);
		// TODO Auto-generated constructor stub
	}

	// Withdraw and Deposit for Checking account have first 2 exchanges free then
	// charge $3
	public void accountWithdraw(Double withdrawNum) {
		chargeCounter();
		setBalance(getBalance() - withdrawNum);
		if (chargeCounter() > 2)
			setBalance(getBalance() - 3);
	}

	public void accountDeposit(Double depositNum) {
		chargeCounter();
		setBalance(getBalance() + depositNum);
		if (chargeCounter() > 2)
			setBalance(getBalance() - 3);
	}

	public int chargeCounter() {
		int counter = 0;
		counter++;
		return counter;

	}

	@Override
	public String toString() {
		return "CheckingAccount [balance=" + balance + ", customerInfo=" + customerInfo + "]";
	}



	

}
