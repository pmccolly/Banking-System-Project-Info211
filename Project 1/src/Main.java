import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	static ArrayList<Account> accounts = new ArrayList<Account>();
	public static void main(String[] args){

	Scanner input = new Scanner(System.in);

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
						System.out.println("Test");
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
		System.out.println(" 1.  Menu Item");
		System.out.println(" 2.  Menu Item");
		System.out.println(" 3.  Menu Item");
		System.out.println(" 4.  Exit");
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
		double checking = 0;
		double gold = 0;
		double regular = 0;
		String name;
		for (Account e : accounts) {
			if (e instanceof CheckingAccount) {

				checking += e.getBalance();
				
			}
			if (e instanceof GoldAccount) {

				gold += e.getBalance();

			}
			if (e instanceof RegularAccount) {
				
				regular += e.getBalance();
			}

		}
		System.out.println(checking);
		System.out.println(gold);
		System.out.println(regular);
	}

}