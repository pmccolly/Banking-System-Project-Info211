
public class RegularAccount extends Account {

	public RegularAccount(String number, double balance, Customer customerInfo) {
		super(number, balance, customerInfo);
		// TODO Auto-generated constructor stub
	}

	public void accountWithdraw(Double withdrawNum) {
		setBalance(getBalance() - withdrawNum);

	}

	public void accountDeposit(Double depositNum) {
		setBalance(getBalance() + depositNum);

	}

	public void setInterest() {
		setBalance(getBalance() * 1.06);
	}

	public void fixedCharge() {
		setBalance(getBalance() - 10);
	}

}
