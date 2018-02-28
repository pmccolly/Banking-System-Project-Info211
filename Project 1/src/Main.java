import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	static Scanner input = new Scanner(System.in);
	static ArrayList<Account> accounts = new ArrayList<Account>();

	public static void main(String[] args) {

		boolean finished = false;

		while (finished == false) {
			// Menu Display and Get user input
			int inputInt = 0;
			while (inputInt == 0) {
				inputInt = displayMenuAndGetInput();

				// if the input is out of range
				if ((inputInt < 1) || (inputInt > 10)) {
					System.out.println("\nThe input is out of range!");
					System.out.println();
					inputInt = 0;
				}
			} // end while

			// switch to correspondence function
			switch (inputInt) {
			case 1:
				// Creates a new checking account
				createCheckingAccount();

				break;
			case 2:
				// Creates a new Gold Account
				createGoldAccount();

				break;
			case 3:
				// Creates a new Regular Account
				createRegularAccount();

				break;
			case 4:
				// Deposits money into selected account
				deposit();
				break;
			case 5:
				// Withdraws money from the selected account
				withdraw();
				break;
			case 6:
				// Displays the account information
				accountInfo();
				break;
			case 7:
				// Removes an account from the array
				removeAccount(null);
				break;
			case 8:
				endOfMonth();
				break;
			case 9:
				displayStatistics();
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
	// --------------------------------------------------------------------------------------------

	public static int displayMenuAndGetInput() {
		int inputInt;

		// Menu Display
		System.out.println("");
		System.out.println("    Bank Menu");
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
		System.out.print("Please input your choice (1-10): ");

		Scanner input = new Scanner(System.in);

		inputInt = input.nextInt();

		return inputInt;
	}

	// A method that creates a new checking a customer through user inputs
	public static void createCheckingAccount() {
		Customer cust1 = new Customer(null, null);
		System.out.println("Please input the customer name: ");
		String customerInfo = input.next();
		cust1.setCustomerName(customerInfo);
		System.out.println("Please input the customer ID: ");
		String customerID = input.next();
		cust1.setCustomerID(customerID);
		// Creates a new account
		Account ac1 = new CheckingAccount(null, 0, cust1);
		System.out.println("Please input the account number :");
		String number = input.next();
		ac1.setNumber(number);
		addAccount(ac1);
	}

	// A method that creates a Gold Account through user inputs
	public static void createGoldAccount() {
		Customer cust2 = new Customer(null, null);
		System.out.println("Please input the customer name: ");
		String customerInfo2 = input.next();
		cust2.setCustomerName(customerInfo2);
		System.out.println("Please input the customer ID: ");
		String customerID2 = input.next();
		cust2.setCustomerID(customerID2);
		// Creates a new account
		Account ac2 = new GoldAccount(null, 0, cust2);
		System.out.println("Please input the account number :");
		String number2 = input.next();
		ac2.setNumber(number2);
		addAccount(ac2);
	}

	// Creates a Regular Account through user inputs
	public static void createRegularAccount() {
		Customer cust3 = new Customer(null, null);
		System.out.println("Please input the customer name: ");
		String customerInfo3 = input.next();
		cust3.setCustomerName(customerInfo3);
		System.out.println("Please input the customer ID: ");
		String customerID3 = input.next();
		cust3.setCustomerID(customerID3);
		// Creates a new account
		Account ac3 = new RegularAccount(null, 0, cust3);
		System.out.println("Please input the account number :");
		String number3 = input.next();
		ac3.setNumber(number3);
		addAccount(ac3);
	}

	// Adds an account to the accounts array list
	public static void addAccount(Account Account) {
		accounts.add(Account);
	}

	// Removes an account from the array list
	public static void removeAccount(Account Account) {

		System.out.println("Please input the account number: ");
		String accountNumber = input.next();
		for (Account e : accounts) {
			if (accountNumber.equals(e.getNumber())) {
				accounts.remove(e);
			}
		}
	}

	// Displays the accounts user information
	public static void accountInfo() {
		System.out.println("Please input account number ");
		String accountNumber = input.next();
		for (Account e : accounts) {
			if (accountNumber.equals(e.getNumber())) {
				System.out.print(e);
			}
		}
	}

	public static void deposit() {
		System.out.println("Please input account number ");
		String accountNumber = input.next();
		System.out.println("Please input the amount you'd like to deposit: ");
		double depositNum = input.nextDouble();

		for (Account e : accounts) {
			if (accountNumber.equals(e.getNumber())) {
				if (e instanceof CheckingAccount) {
					CheckingAccount ce = (CheckingAccount) e;
					ce.accountDeposit(depositNum);

				}
				if (e instanceof GoldAccount) {
					GoldAccount ge = (GoldAccount) e;
					ge.accountDeposit(depositNum);
				}
				if (e instanceof RegularAccount) {
					RegularAccount re = (RegularAccount) e;
					re.accountDeposit(depositNum);

				}
			}
		}
	}

	public static void withdraw() {
		System.out.println("Please input account number ");
		String accountNumber = input.next();
		System.out.println("Please input the amount you'd like to withdraw: ");
		double withdrawNum = input.nextDouble();

		for (Account e : accounts) {
			if (accountNumber.equals(e.getNumber())) {
				if (e instanceof CheckingAccount) {
					CheckingAccount ce = (CheckingAccount) e;
					ce.accountWithdraw(withdrawNum);

				}
				if (e instanceof GoldAccount) {
					GoldAccount ge = (GoldAccount) e;
					ge.accountWithdraw(withdrawNum);
				}
				if (e instanceof RegularAccount) {
					RegularAccount re = (RegularAccount) e;
					re.accountWithdraw(withdrawNum);

				}
			}
		}
	}

	// Method to apply end of month updates to user accounts
	public static void endOfMonth() {
		for (Account e : accounts) {
			if (e instanceof CheckingAccount) {
				CheckingAccount ce = (CheckingAccount) e;
				if (ce.chargeCounter() > 2)
					ce.setBalance((ce.getBalance() - ((ce.chargeCounter() - 2) * 3)));

			}
			if (e instanceof GoldAccount) {
				GoldAccount ge = (GoldAccount) e;
				ge.setInterest();
			}
			if (e instanceof RegularAccount) {
				RegularAccount re = (RegularAccount) e;
				re.setInterest();
				re.fixedCharge();

			}
		}
	}

	// Method to display Statistics of bank, total sum of all accounts in the
	// bank, number of zero-balance accounts, average balance of accounts, the
	// account with largest balance
	public static void displayStatistics() {
		double sumOfAccounts = 0;
		int numOfZeroBalance = 0;
		double averageBalance = 0;
		String largestAccount = "";
		double largestAmount = 0;
		int counter = 0;
		for (Account e : accounts) {
			counter++;
			sumOfAccounts += e.getBalance();
			if (0 == e.getBalance())
				numOfZeroBalance++;
			if (e.getBalance() >= largestAmount) {
				largestAmount = e.getBalance();
				largestAccount = e.getCustomerInfo().getCustomerName();
			}

		}
		averageBalance = sumOfAccounts / counter;
		System.out.println("The sum of all accounts is $" + sumOfAccounts);
		System.out.println("The number of zero balance accounts is " + numOfZeroBalance);
		System.out.println("The average balance of all accounts is $" + averageBalance);
		System.out.println("The largest account is " + largestAccount);

	}
}


