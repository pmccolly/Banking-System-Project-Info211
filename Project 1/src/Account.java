import java.io.Serializable;

public abstract class Account implements Serializable {

	// account number, balance, and customer information
	protected String number;
	protected double balance;
	protected Customer customerInfo;

	public Account(String number, double balance, Customer customerInfo) {
		super();
		this.number = number;
		this.balance = balance;
		this.customerInfo = customerInfo;

	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public Customer getCustomerInfo() {
		return customerInfo;
	}

	public void setCustomerInfo(Customer customerInfo) {
		this.customerInfo = customerInfo;
	}

	public abstract void accountWithdraw(Double withdrawNum);{
	}

	public abstract void accountDeposit(Double depositNum);{
	}

	@Override
	public String toString() {
		return "Account [number=" + number + ", balance=" + balance + ", customerInfo=" + customerInfo + "]";
	}
	
}
