import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	static Scanner input = new Scanner(System.in);
	static ArrayList<Account> accounts = new ArrayList<Account>();
	public static void main(String[] args){

	

			boolean finished = false;

			while (finished == false)
			{
				// Menu Display and Get user input
				int inputInt = 0;
				while (inputInt == 0)
				{
					inputInt = displayMenuAndGetInput();

					// if the input is out of range
					if ((inputInt < 1) || (inputInt > 10))
					{
						System.out.println("\nThe input is out of range!");
						System.out.println();
						inputInt = 0;
					}
				} //end while

				// switch to correspondence function
				switch (inputInt)
				{
					case 1:
						//Creates a new customer
						Customer cust1 = new Customer(null, null);
						System.out.println("Please input the customer name: ");
						String customerInfo = input.next();
						cust1.setCustomerName(customerInfo);
						System.out.println("Please input the customer ID: ");
						String customerID = input.next();
						cust1.setCustomerID(customerID);
						//Creates a new account
						Account ac1 = new CheckingAccount(null, 0, cust1);
						System.out.println("Please input the account number :");
						String number = input.next();
						ac1.setNumber(number);
						addAccount(ac1);
						
						break;
					case 2:
						//Creates a new customer
						Customer cust2 = new Customer(null, null);
						System.out.println("Please input the customer name: ");
						String customerInfo2 = input.next();
						cust2.setCustomerName(customerInfo2);
						System.out.println("Please input the customer ID: ");
						String customerID2 = input.next();
						cust2.setCustomerID(customerID2);
						//Creates a new account
						Account ac2 = new GoldAccount(null, 0, cust2);
						System.out.println("Please input the account number :");
						String number2 = input.next();
						ac2.setNumber(number2);
						addAccount(ac2);
						
						break;
					case 3:
						Customer cust3 = new Customer(null, null);
						System.out.println("Please input the customer name: ");
						String customerInfo3 = input.next();
						cust3.setCustomerName(customerInfo3);
						System.out.println("Please input the customer ID: ");
						String customerID3 = input.next();
						cust3.setCustomerID(customerID3);
						//Creates a new account
						Account ac3 = new RegularAccount(null, 0, cust3);
						System.out.println("Please input the account number :");
						String number3 = input.next();
						ac3.setNumber(number3);
						addAccount(ac3);
						
						break;
					case 4: 
						System.out.println("Hello");
						break;
					case 5: 
						break;
					case 6: 
						accountInfo();
						break;
					case 7: 
						break;
					case 8: 
						break;
					case 9: 
						break;
						

					case 10:
						// exit
						finished = true;
						break;
					default:
						System.out.println("Invalid Input!");
						System.out.println("");
						break;
				} // end switch
			} // end while

	}
//--------------------------------------------------------------------------------------------
	
	

	public static int displayMenuAndGetInput()
	{
		int inputInt ;

		// Menu Display
		System.out.println("");
		System.out.println("     Menu");
		System.out.println(" =====================");
		System.out.println(" 1.  Create Checking Account");
		System.out.println(" 2.  Create Gold Account");
		System.out.println(" 3.  Create Regular Account");
		System.out.println(" 4.  Deposit");
		System.out.println(" 5.  Withdraw");
		System.out.println(" 6.  Display Account Info.");
		System.out.println(" 7.  Remove an Account");
		System.out.println(" 8.  Apply end of month");
		System.out.println(" 9.  Display Bank Statistics");
		System.out.println(" 10. Exit");
		System.out.println("");

		// Get the input from the user
		System.out.print("Please input your choice (1-4): ");

		Scanner input = new Scanner( System.in );

		inputInt = input.nextInt();

		return inputInt;
	}
	public static void addAccount(Account Account) {
		accounts.add(Account);
	}
	
	public static void accountInfo() {
		System.out.println("Please input account number ");
		String accountNumber = input.next();
		for (Account e : accounts) {
			if (accountNumber.equals(e.getNumber())) {
				System.out.print(e);
			}
		}
	}
 public static void accountTest(){
	for (Account e : accounts) {
		if (e instanceof Account) {
		System.out.print(e);
		}
	}
}
}