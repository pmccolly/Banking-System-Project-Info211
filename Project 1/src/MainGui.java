
import java.io.File;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.ParsePosition;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

// Title: Project Part 2: Bank GUI
// Semester: Info 211 Spring 2018
// Authors: Phil McColly, Gavin LaDue
public class MainGui extends Application implements Serializable {

	List<Account> accounts = new ArrayList<Account>();
	Stage stage;
	TextArea outputArea = new TextArea();
	
	// String to add all necessary text to (similar to a log)
	String outputAreaText = "";
	
	int accountSwitch = 0;

	public static void main(String[] args) {

		launch(args);
	}

	// ===========================================
	@Override
	public void start(Stage primaryStage) {
		try {
			openFile();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Scene scene = new Scene(getMainTextArea(), 400, 300, Color.WHITE);
		primaryStage.setTitle("Bank of McColly and LaDue");
		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.getIcons().add(new Image("file:src/icon.jpg"));
		this.stage = primaryStage;

	}
	// Method to change scenes using panes
	public void setScenes(BorderPane pane) {
		Scene scene = new Scene(pane, 400, 300, Color.WHITE);

		stage.setScene(scene);
		stage.show();
	}
	// Adds a TextArea to the default pane
	public BorderPane getMainTextArea() {
		outputArea.setPrefRowCount(4);
		outputArea.setPrefColumnCount(8);
		outputArea.setEditable(false);

		BorderPane mainPane = getDefaultPane();
		mainPane.setCenter(outputArea);
		return mainPane;
	}
	// Creates a template to base all other panes off of
	public BorderPane getDefaultPane() {
		BorderPane mainPane = new BorderPane();
		MenuBar menuBar = new MenuBar();
		menuBar.setPrefWidth(300);
		mainPane.setTop(menuBar);

		// File menu - new, save, exit
		Menu fileMenu = new Menu("File");

		MenuItem saveMenuItem = new MenuItem("Save");
		MenuItem exitMenuItem = new MenuItem("Exit");
		exitMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.X, KeyCombination.CONTROL_DOWN));

		fileMenu.getItems().addAll(saveMenuItem, new SeparatorMenuItem(), exitMenuItem);

		// CheckMenuItem: A MenuItem that can be toggled between selected and unselected
		// states.
		Menu operatorMenu = new Menu("Create Accounts");
		MenuItem createChecking = new MenuItem("Checking");
		MenuItem createGold = new MenuItem("Gold");
		MenuItem createRegular = new MenuItem("Regular");

		operatorMenu.getItems().addAll(createChecking, createGold, createRegular);

		Menu operatorInfo = new Menu("Information");
		MenuItem statsTool = new MenuItem("Bank Statistics");
		MenuItem endOfMonthTool = new MenuItem("Apply End of Month");
		MenuItem infoTool = new MenuItem("Account Information");

		operatorInfo.getItems().addAll(infoTool, statsTool, endOfMonthTool);

		Menu operatorTools = new Menu("Tools");
		MenuItem removeTool = new MenuItem("Remove Account");
		MenuItem depositTool = new MenuItem("Deposit");
		MenuItem withdrawTool = new MenuItem("Withdraw");

		operatorTools.getItems().addAll(depositTool, withdrawTool, removeTool);

		// Handling Events:

		exitMenuItem.setOnAction(actionEvent -> {
			Platform.exit();
		});

		createChecking.setOnAction(actionEvent -> {
			accountSwitch = 1;
			setScenes(getAccountPane());
		});

		createGold.setOnAction(actionEvent -> {
			accountSwitch = 2;
			setScenes(getAccountPane());
		});

		createRegular.setOnAction(actionEvent -> {
			accountSwitch = 3;
			setScenes(getAccountPane());
		});
		statsTool.setOnAction(actionEvent -> {
			setScenes(getMainTextArea());
			displayStatistics();
		});
		endOfMonthTool.setOnAction(actionEvent -> {
			setScenes(getMainTextArea());
			endOfMonth();
		});
		endOfMonthTool.setOnAction(actionEvent -> {
			setScenes(getMainTextArea());
			endOfMonth();
		});
		depositTool.setOnAction(actionEvent -> {
			setScenes(getDepositPane());
		});
		withdrawTool.setOnAction(actionEvent -> {
			setScenes(getWithdrawPane());
		});
		removeTool.setOnAction(actionEvent -> {
			setScenes(getRemoveAccountsPane());
		});
		infoTool.setOnAction(actionEvent -> {
			setScenes(getAccountInfoPane());
		});
		saveMenuItem.setOnAction(actionEvent -> {
			save();
		});

		menuBar.getMenus().addAll(fileMenu, operatorMenu, operatorTools, operatorInfo);

		return mainPane;
	}

	// Method to create the create accounts pane
	public BorderPane getAccountPane() {
		GridPane accountLayout = new GridPane();
		BorderPane mainPane = getDefaultPane();
		mainPane.setCenter(accountLayout);
		accountLayout.setVgap(5.5);
		accountLayout.setHgap(5.5);

		if (accountSwitch == 1) {
			accountLayout.add(new Label("Checking Account "), 2, 1);
			;

		}
		if (accountSwitch == 2) {
			accountLayout.add(new Label("Gold Account"), 2, 1);

		}
		if (accountSwitch == 3) {

			accountLayout.add(new Label("Regular Account"), 2, 1);
		}

		TextField firstName = new TextField();
		accountLayout.add(new Label("First Name "), 1, 2);
		accountLayout.add(firstName, 2, 2);

		TextField lastName = new TextField();
		accountLayout.add(new Label("Last Name "), 1, 3);
		accountLayout.add(lastName, 2, 3);

		TextField accountId = new TextField();
		numericTextField(accountId);
		accountLayout.add(new Label("Account ID"), 1, 4);
		accountLayout.add(accountId, 2, 4);

		TextField accountNumber = new TextField();
		numericTextField(accountNumber);
		accountLayout.add(new Label("Account Number"), 1, 5);
		accountLayout.add(accountNumber, 2, 5);

		Button createAccountBtn = new Button("Create Account");
		accountLayout.add(createAccountBtn, 2, 6);

		// This determines which kind of account was created by assigning each kind of
		// account to a value and then using
		// if statements
		createAccountBtn.setOnAction(actionEvent -> {
			if (accountSwitch == 1) {

				createCheckingAccount(firstName.getText() + " " + lastName.getText(), accountId.getText(),
						accountNumber.getText());

				setScenes(getMainTextArea());
				outputArea.setText(outputAreaText += "Checking Account was created \n");
			}
			if (accountSwitch == 2) {

				createGoldAccount(firstName.getText() + " " + lastName.getText(), accountId.getText(),
						accountNumber.getText());

				setScenes(getMainTextArea());
				outputArea.setText(outputAreaText += "Gold Account was created \n");
			}
			if (accountSwitch == 3) {

				createRegularAccount(firstName.getText() + " " + lastName.getText(), accountId.getText(),
						accountNumber.getText());

				setScenes(getMainTextArea());
				outputArea.setText(outputAreaText += "Regular Account was created \n");
			}

		});

		return mainPane;

	}

	// toolbar deposit function
	public BorderPane getDepositPane() {
		GridPane accountLayout = new GridPane();
		BorderPane mainPane = getDefaultPane();
		mainPane.setCenter(accountLayout);
		accountLayout.setVgap(5.5);
		accountLayout.setHgap(5.5);

		TextField accountIDText = new TextField();
		numericTextField(accountIDText);
		accountLayout.add(new Label("Enter Account Number "), 1, 1);
		accountLayout.add(accountIDText, 2, 1);

		TextField depositText = new TextField();
		numericTextField(depositText);
		accountLayout.add(new Label("Deposit "), 1, 2);
		accountLayout.add(depositText, 2, 2);

		Button depositBtn = new Button("Submit");
		accountLayout.add(depositBtn, 2, 4);

		depositBtn.setOnAction(actionEvent -> {
			if (depositText.getText().length() > 0) {
				deposit(accountIDText.getText(), Double.parseDouble(depositText.getText()));

			}

			setScenes(getMainTextArea());
		});

		return mainPane;
	}

	// Toolbar withdraw option
	public BorderPane getWithdrawPane() {
		GridPane accountLayout = new GridPane();
		BorderPane mainPane = getDefaultPane();
		mainPane.setCenter(accountLayout);
		accountLayout.setVgap(5.5);
		accountLayout.setHgap(5.5);

		TextField accountIDText = new TextField();
		numericTextField(accountIDText);
		accountLayout.add(new Label("Enter Account Number "), 1, 1);
		accountLayout.add(accountIDText, 2, 1);

		TextField withDrawText = new TextField();
		numericTextField(withDrawText);
		accountLayout.add(new Label("Deposit "), 1, 2);
		accountLayout.add(withDrawText, 2, 2);

		Button withdrawBtn = new Button("Submit");
		accountLayout.add(withdrawBtn, 2, 4);

		withdrawBtn.setOnAction(actionEvent -> {

			if (withDrawText.getText().length() > 0) {
				withdraw(accountIDText.getText(), Double.parseDouble(withDrawText.getText()));

			}

			setScenes(getMainTextArea());
		});

		return mainPane;
	}

	// Creates the account info scene
	public BorderPane getAccountInfoPane() {
		GridPane accountLayout = new GridPane();
		BorderPane mainPane = getDefaultPane();
		mainPane.setCenter(accountLayout);
		accountLayout.setVgap(5.5);
		accountLayout.setHgap(5.5);

		TextField accountIDText = new TextField();
		accountLayout.add(new Label("Enter Account Number "), 1, 1);
		accountLayout.add(accountIDText, 2, 1);

		Button submitBtn = new Button("Submit");
		accountLayout.add(submitBtn, 2, 4);

		submitBtn.setOnAction(actionEvent -> {
			accountInfo(accountIDText.getText());
			setScenes(getMainTextArea());
		});

		return mainPane;
	}

	// Creates the remove account scene
	public BorderPane getRemoveAccountsPane() {
		GridPane accountLayout = new GridPane();
		BorderPane mainPane = getDefaultPane();
		mainPane.setCenter(accountLayout);
		accountLayout.setVgap(5.5);
		accountLayout.setHgap(5.5);

		TextField accountIDText = new TextField();
		numericTextField(accountIDText);
		accountLayout.add(new Label("Enter Account Number "), 1, 1);
		accountLayout.add(accountIDText, 2, 1);

		Button depositWithdrawBtn = new Button("Submit");
		accountLayout.add(depositWithdrawBtn, 2, 4);

		depositWithdrawBtn.setOnAction(actionEvent -> {

			removeAccount(accountIDText.getText());

			setScenes(getMainTextArea());
		});

		return mainPane;
	}

	// A method that creates a new checking a customer through user inputs
	public void createCheckingAccount(String name, String accId, String accNumber) {
		Customer customer = new Customer(null, null);

		String customerInfo = name;
		customer.setCustomerName(customerInfo);

		String customerID = accId;
		customer.setCustomerID(customerID);
		// Creates a new account
		Account ac1 = new CheckingAccount(null, 0, customer);

		String number = accNumber;
		ac1.setNumber(number);
		addAccount(ac1);
	}

	// A method that creates a Gold Account through user inputs
	public void createGoldAccount(String name, String accId, String accNumber) {
		Customer customer = new Customer(null, null);

		String customerInfo = name;
		customer.setCustomerName(customerInfo);

		String customerID = accId;
		customer.setCustomerID(customerID);
		// Creates a new account
		Account ac1 = new CheckingAccount(null, 0, customer);

		String number = accNumber;
		ac1.setNumber(number);
		addAccount(ac1);
	}

	// Creates a Regular Account through user inputs
	public void createRegularAccount(String name, String accId, String accNumber) {
		Customer customer = new Customer(null, null);

		String customerInfo = name;
		customer.setCustomerName(customerInfo);

		String customerID = accId;
		customer.setCustomerID(customerID);
		// Creates a new account
		Account ac1 = new CheckingAccount(null, 0, customer);

		String number = accNumber;
		ac1.setNumber(number);
		addAccount(ac1);
	}

	// Adds an account to the accounts array list
	public void addAccount(Account Account) {
		accounts.add(Account);
	}

	// Removes an account from the array list
	public void removeAccount(String accountNumber) {
		for (int i = 0; i < accounts.size(); i++) {
			if (accountNumber.equals(accounts.get(i).getNumber())) {
				accounts.remove(i);
			}
		}
	}

	// Displays the accounts user information
	public void accountInfo(String accountNumber) {

		for (Account e : accounts) {
			if (accountNumber.equals(e.getNumber())) {
				outputArea.setText(e.toString() + "\n");
			}
		}

	}

	// Method used to add to the total balance
	public void deposit(String accountNumber, double depositNum) {

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
		outputArea.setText(outputAreaText += ("$" + depositNum + " has been deposited from account number "
				+ accountNumber + "\n"));
	}

	// Method used to deduct from the total balance
	public void withdraw(String accountNumber, double withdrawNum) {

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
		outputArea.setText(outputAreaText += ("$" + withdrawNum + " has been withdrawn from account number "
				+ accountNumber + "\n"));
	}

	// Method to apply end of month updates to user accounts
	public void endOfMonth() {
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
		outputArea.setText(outputAreaText += ("End of month updates have been applied." + "\n"));
	}

	// Method to display Statistics of bank, total sum of all accounts in the
	// bank, number of zero-balance accounts, average balance of accounts, the
	// account with largest balance
	public void displayStatistics() {
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
		outputArea.setText(outputAreaText += ("=======================" + "\n"));
		outputArea.setText(outputAreaText += ("The sum of all accounts is $" + sumOfAccounts + "\n"));
		outputArea.setText(outputAreaText += ("The number of zero balance accounts is " + numOfZeroBalance + "\n"));
		outputArea.setText(outputAreaText += ("The average balance of all accounts is $" + averageBalance + "\n"));
		outputArea.setText(outputAreaText += ("The largest account is " + largestAccount + "\n"));
		outputArea.setText(outputAreaText += ("=======================" + "\n"));

	}

	// Method to open file with accounts Array Object
	public void openFile() throws ClassNotFoundException {
		// Pulls information from the file upon opening the program
		File f = new File("accountsdata.ser");
		if (f.exists()) {

			try {
				ObjectInputStream input = new ObjectInputStream(new FileInputStream(f));

				accounts = (ArrayList<Account>) input.readObject();
				System.out.println("opens");

			} catch (IOException ioException) {
				System.err.println("Error opening file.");
				// ioException.printStackTrace();
				System.out.println();
			}
		}
	}

	// Saves the program
	public void save() {

		File f = new File("accountsdata.ser");
		try {
			ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(f));
			output.writeObject(accounts);
			System.out.println("Saved");
		} catch (IOException ioException) {
			System.err.println("Error opening file.");
			// ioException.printStackTrace();
			System.out.println();
		}

	}

	// Method to make textfields only enter double format
	// Reference: https://stackoverflow.com/questions/31039449/java-8-u40-textformatter-javafx-to-restrict-user-input-only-for-decimal-number
	// Author: Uluk Biy
	public TextField numericTextField(TextField field) {
		DecimalFormat format = new DecimalFormat("#.0");

		field.setTextFormatter(new TextFormatter<>(c -> {
			if (c.getControlNewText().isEmpty()) {
				return c;
			}

			ParsePosition parsePosition = new ParsePosition(0);
			Object object = format.parse(c.getControlNewText(), parsePosition);

			if (object == null || parsePosition.getIndex() < c.getControlNewText().length()) {
				return null;
			} else {
				return c;
			}
		}));
		return field;
	}

}