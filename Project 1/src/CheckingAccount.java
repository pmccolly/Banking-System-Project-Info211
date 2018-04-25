import java.io.Serializable;

public class CheckingAccount extends Account implements Serializable {
	int chargeNumber = 0;

	public CheckingAccount(String number, double balance, Customer customerInfo) {
		super(number, balance, customerInfo);
		// TODO Auto-generated constructor stub
	}

	// Withdraw and Deposit for Checking account have first 2 exchanges free then
	// charge $3
	public void accountWithdraw(Double withdrawNum) {
		
		if (withdrawNum>getBalance()) {
			withdrawNum = getBalance();
			setBalance(getBalance()-withdrawNum);
		}
			else
				setBalance(getBalance()-withdrawNum);
		chargeCounter();
	}

	public void accountDeposit(Double depositNum) {
		
		setBalance(getBalance() + depositNum);
		chargeCounter();
			
	}

	public void chargeCounter() {
		chargeNumber++;
		

	}

	@Override
	public String toString() {
		return "CheckingAccount [balance=" + balance + ", customerInfo=" + customerInfo + "]";
	}



	

}