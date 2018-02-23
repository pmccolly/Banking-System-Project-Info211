
public class GoldAccount extends Account {

	public GoldAccount(String number, double balance, Customer customerInfo) {
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
		setBalance(getBalance() * 1.05);
	}
	
	@Override
	public String toString() {
		return "GoldAccount [balance=" + balance + ", customerInfo=" + customerInfo + "]";
	}
	


}
