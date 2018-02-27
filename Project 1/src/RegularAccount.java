
public class RegularAccount extends Account {

	public RegularAccount(String number, double balance, Customer customerInfo) {
		super(number, balance, customerInfo);
		// TODO Auto-generated constructor stub
	}

	public void accountWithdraw(Double withdrawNum) {
		if (withdrawNum<=getBalance())
		setBalance(getBalance() - withdrawNum);
		else
			setBalance(getBalance()-getBalance());

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
	@Override
	public String toString() {
		return "RegularAccount [balance=" + balance + ", customerInfo=" + customerInfo + "]";
	}
}
