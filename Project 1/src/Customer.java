import java.io.Serializable;

public class Customer implements Serializable {

	// customer information (customer ID, customer name, etc)

	protected String customerID;
	protected String customerName;

	public Customer(String customerID, String customerName) {
		super();
		this.customerID = customerID;
		this.customerName = customerName;
	}

	public String getCustomerID() {
		return customerID;
	}

	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	@Override
	public String toString() {
		return "Customer [customerID=" + customerID + ", customerName: : " + customerName + "]";
	}

}
