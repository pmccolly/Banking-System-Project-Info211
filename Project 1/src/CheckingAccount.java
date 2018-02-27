
public class CheckingAccount extends Account {

	public CheckingAccount(String number, double balance, Customer customerInfo) {
		super(number, balance, customerInfo);
		// TODO Auto-generated constructor stub
	}

	// Withdraw and Deposit for Checking account have first 2 exchanges free then
	// charge $3
	public void accountWithdraw(Double withdrawNum) {
		
		if (withdrawNum<=getBalance())
			setBalance(getBalance() - withdrawNum);
			else
				setBalance(getBalance()-getBalance());
		chargeCounter();
	}

	public void accountDeposit(Double depositNum) {
		
		setBalance(getBalance() + depositNum);
		chargeCounter();
			
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
