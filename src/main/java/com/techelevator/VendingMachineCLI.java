package com.techelevator;

import com.techelevator.view.Menu;

public class VendingMachineCLI {

    private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
    private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
    private static final String MAIN_MENU_OPTION_EXIT = "Exit";

    private static final String[] MAIN_MENU_OPTIONS = {
            MAIN_MENU_OPTION_DISPLAY_ITEMS,
            MAIN_MENU_OPTION_PURCHASE,
            MAIN_MENU_OPTION_EXIT
    };

    private static final String PURCHASE_MENU_OPTIONS_FEED_MONEY = "Feed Money";
    private static final String PURCHASE_MENU_SELECT_PRODUCT = "Select Product";
    private static final String PURCHASE_MENU_FINISH_TRANSACTION = "Finish Transaction";

    private static final String[] PURCHASE_MENU_OPTIONS = {
            PURCHASE_MENU_OPTIONS_FEED_MONEY,
            PURCHASE_MENU_SELECT_PRODUCT,
            PURCHASE_MENU_FINISH_TRANSACTION
    };

	private static final String MENU_EXIT_RESET = "Reset options";
	private static final String MENU_EXIT_CLOSE = "Close application";

	private static final String[] MENU_EXIT_OPTIONS = {
            MENU_EXIT_RESET,
            MENU_EXIT_CLOSE
    };

    private Menu menu;
    public VendingMachineCLI(Menu menu) {
        this.menu = menu;
    }

    public void run() {
        menu.printHeader();
        while (true) {
            String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);
            switch (choice) {
                case MAIN_MENU_OPTION_DISPLAY_ITEMS:
                    menu.displayAvailableItems();
                    break;
                case MAIN_MENU_OPTION_PURCHASE:
                    menu.purchaseMenuHeader();
                    while (true) {
                        String purchaseChoice = (String) menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);
                        if (PURCHASE_MENU_OPTIONS_FEED_MONEY.equals(purchaseChoice)) {
                            menu.printFeedMoneyHeader();
                        } else if (PURCHASE_MENU_SELECT_PRODUCT.equals(purchaseChoice)) {
                            menu.printSelectProductHeader();
                        } else if (PURCHASE_MENU_FINISH_TRANSACTION.equals(purchaseChoice)) {
                            menu.printFinishTransactionHeader();
                            break;
                        }
                    }
                case MAIN_MENU_OPTION_EXIT:
					String exitChoice = (String) menu.getChoiceFromOptions(MENU_EXIT_OPTIONS);
					switch (exitChoice) {
						case MENU_EXIT_RESET:
                            menu.printSaleReportMessage();
							run();
						case MENU_EXIT_CLOSE:
                            menu.printExitMessage();
                            menu.printSaleReportMessage();
							System.exit(0);
					}
					break;
            }
        }
    }

    public static void main(String[] args) {
        Menu menu = new Menu(System.in, System.out);
        VendingMachineCLI cli = new VendingMachineCLI(menu);
        cli.run();
    }

}
