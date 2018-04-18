
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class MainGui extends Application {

	static Scanner input = new Scanner(System.in);
	static ArrayList<Account> accounts = new ArrayList<Account>();
	private Stage stage;
	TextArea outputArea = new TextArea();
	String outputAreaText = "";
	int accountSwitch = 0;

	public static void main(String[] args) {
		launch(args);
	}

	// ===========================================
	@Override
	public void start(Stage primaryStage) {

		Scene scene = new Scene(getPane(), 600, 500, Color.WHITE);
		primaryStage.setTitle("Conversion Formulas");
		primaryStage.setScene(scene);
		primaryStage.show();
		this.stage = primaryStage;
	}

	public void setScene(Stage primaryStage) {
		Scene scene = new Scene(getAccountPane(), 600, 500, Color.WHITE);

		stage.setScene(scene);
		stage.show();
	}

	protected BorderPane getPane() {
		BorderPane mainPane = new BorderPane();
		MenuBar menuBar = new MenuBar();
		menuBar.setPrefWidth(300);
		mainPane.setTop(menuBar);

		// File menu - new, save, exit
		Menu fileMenu = new Menu("File");
		MenuItem newMenuItem = new MenuItem("New");
		MenuItem saveMenuItem = new MenuItem("Save");
		MenuItem exitMenuItem = new MenuItem("Exit");
		exitMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.X, KeyCombination.CONTROL_DOWN));

		fileMenu.getItems().addAll(newMenuItem, saveMenuItem, new SeparatorMenuItem(), exitMenuItem);

		// CheckMenuItem: A MenuItem that can be toggled between selected and unselected
		// states.
		Menu operatorMenu = new Menu("Create Accounts");
		MenuItem createChecking = new MenuItem("Checking");
		MenuItem createGold = new MenuItem("Gold");
		MenuItem createRegular = new MenuItem("Regular");

		operatorMenu.getItems().addAll(createChecking, createGold, createRegular);

		Menu operatorTools = new Menu("Tools");
		MenuItem depositTool = new MenuItem("Deposit");
		MenuItem withdrawTool = new MenuItem("Withdraw");
		MenuItem infoTool = new MenuItem("Information");
		MenuItem removeTool = new MenuItem("Remove Account");

		operatorTools.getItems().addAll(depositTool, withdrawTool, infoTool, removeTool);

		
		outputArea.setPrefRowCount(4);
		outputArea.setPrefColumnCount(8);
		outputArea.setEditable(false);
		mainPane.setCenter(outputArea);

		// Handling Events:
		exitMenuItem.setOnAction(actionEvent -> {
			Platform.exit();
		});

		createChecking.setOnAction(actionEvent -> {
			setScene(stage);
			accountSwitch = 1;
		});

		createGold.setOnAction(actionEvent -> {
			setScene(stage);
			accountSwitch = 2;
		});

		createRegular.setOnAction(actionEvent -> {
			setScene(stage);
			accountSwitch = 3;
		});

		menuBar.getMenus().addAll(fileMenu, operatorMenu, operatorTools);

		return mainPane;
	}

	protected BorderPane getAccountPane() {
		BorderPane mainPane = new BorderPane();
		MenuBar menuBar = new MenuBar();
		menuBar.setPrefWidth(300);
		mainPane.setTop(menuBar);

		// File menu - new, save, exit
		Menu fileMenu = new Menu("File");
		MenuItem newMenuItem = new MenuItem("New");
		MenuItem saveMenuItem = new MenuItem("Save");
		MenuItem exitMenuItem = new MenuItem("Exit");
		exitMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.X, KeyCombination.CONTROL_DOWN));

		fileMenu.getItems().addAll(newMenuItem, saveMenuItem, new SeparatorMenuItem(), exitMenuItem);

		// CheckMenuItem: A MenuItem that can be toggled between selected and unselected
		// states.
		Menu operatorMenu = new Menu("Create Accounts");
		MenuItem createChecking = new MenuItem("Checking");
		MenuItem createGold = new MenuItem("Gold");
		MenuItem createRegular = new MenuItem("Regular");

		operatorMenu.getItems().addAll(createChecking, createGold, createRegular);

		Menu operatorTools = new Menu("Tools");
		MenuItem depositTool = new MenuItem("Deposit");
		MenuItem withdrawTool = new MenuItem("Withdraw");
		MenuItem infoTool = new MenuItem("Information");
		MenuItem removeTool = new MenuItem("Remove Account");

		operatorTools.getItems().addAll(depositTool, withdrawTool, infoTool, removeTool);

		GridPane accountLayout = new GridPane();
		mainPane.setCenter(accountLayout);
		accountLayout.setVgap(5.5);
		accountLayout.setHgap(5.5);

		TextField firstName = new TextField();
		accountLayout.add(new Label("First Name "), 1, 1);
		accountLayout.add(firstName, 2, 1);

		TextField lastName = new TextField();
		accountLayout.add(new Label("Last Name "), 1, 2);
		accountLayout.add(lastName, 2, 2);

		TextField accountId = new TextField();
		accountLayout.add(new Label("Account ID"), 1, 3);
		accountLayout.add(accountId, 2, 3);

		TextField accountNumber = new TextField();
		accountLayout.add(new Label("Account Number"), 1, 4);
		accountLayout.add(accountNumber, 2, 4);

		Button createAccountBtn = new Button("Create Account");
		accountLayout.add(createAccountBtn, 2, 5);

		// Handling Events:
		exitMenuItem.setOnAction(actionEvent -> {
			Platform.exit();
		});

		createChecking.setOnAction(actionEvent -> {
			setScene(stage);
			accountSwitch = 1;
		});

		createAccountBtn.setOnAction(actionEvent -> {
			if (accountSwitch == 1) {
				
			
			createCheckingAccount(firstName.getText() + " " + lastName.getText(), accountId.getText(),
					accountNumber.getText());

			start(stage);
			outputArea.setText(outputAreaText += "Checking Account was created \n");
			}
			if (accountSwitch == 2) {

				createGoldAccount(firstName.getText() + " " + lastName.getText(), accountId.getText(),
						accountNumber.getText());

				start(stage);
				outputArea.setText(outputAreaText += "Gold Account was created \n");
			}
			if (accountSwitch == 3) {

				createRegularAccount(firstName.getText() + " " + lastName.getText(), accountId.getText(),
						accountNumber.getText());

				start(stage);
				outputArea.setText(outputAreaText += "Regular Account was created \n");
			}
			
			
		});

		menuBar.getMenus().addAll(fileMenu, operatorMenu, operatorTools);

		return mainPane;

	}

	// A method that creates a new checking a customer through user inputs
	public static void createCheckingAccount(String name, String accId, String accNumber) {
		Customer cust1 = new Customer(null, null);

		String customerInfo = name;
		cust1.setCustomerName(customerInfo);

		String customerID = accId;
		cust1.setCustomerID(customerID);
		// Creates a new account
		Account ac1 = new CheckingAccount(null, 0, cust1);

		String number = accNumber;
		ac1.setNumber(number);
		addAccount(ac1);
	}

	// A method that creates a Gold Account through user inputs
	public static void createGoldAccount(String name, String accId, String accNumber) {
		Customer cust1 = new Customer(null, null);

		String customerInfo = name;
		cust1.setCustomerName(customerInfo);

		String customerID = accId;
		cust1.setCustomerID(customerID);
		// Creates a new account
		Account ac1 = new CheckingAccount(null, 0, cust1);

		String number = accNumber;
		ac1.setNumber(number);
		addAccount(ac1);
	}

	// Creates a Regular Account through user inputs
	public static void createRegularAccount(String name, String accId, String accNumber) {
		Customer cust1 = new Customer(null, null);

		String customerInfo = name;
		cust1.setCustomerName(customerInfo);

		String customerID = accId;
		cust1.setCustomerID(customerID);
		// Creates a new account
		Account ac1 = new CheckingAccount(null, 0, cust1);

		String number = accNumber;
		ac1.setNumber(number);
		addAccount(ac1);
	}

	// Adds an account to the accounts array list
	public static void addAccount(Account Account) {
		accounts.add(Account);
	}

	// Removes an account from the array list
	public static void removeAccount() {

		System.out.println("Please input the account number: ");
		String accountNumber = input.next();
		for (int i = 0; i < accounts.size(); i++) {
			if (accountNumber.equals(accounts.get(i).getNumber())) {
				accounts.remove(i);
			}
		}
	}

	// Displays the accounts user information
	public static void accountInfo() {
		System.out.println("Please input account number: ");
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
		System.out.println("Please input account number: ");
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
				if (ce.chargeNumber > 2)
					ce.setBalance((ce.getBalance() - ((ce.chargeNumber - 2) * 3)));

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