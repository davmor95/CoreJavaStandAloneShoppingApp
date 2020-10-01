import exceptions.*;
import model.Customer;
import model.CustomerOrder;
import repo.CustomerOrderRepo;
import repo.CustomerRepo;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class MainRunner {
    public static final CustomerRepo service = new CustomerRepo();
    public static final CustomerOrderRepo customerService = new CustomerOrderRepo();
//    public static boolean loggedIn;
    public static Customer currentUser = null;
    public static List<CustomerOrder> customerOrderList = new ArrayList<CustomerOrder>();
    public static void main(String[] args) throws InvalidLoginEmailException, InvalidLoginPasswordException {
        Scanner sc = null;
        int choice;
        while(true) {
            System.out.println("----------------------------------------------------------------------");
            System.out.println("Current User: " + currentUser);
            choice = getChoice(sc);
            switch (choice) {
                case 1:
                    register(sc);
                    break;
                case 2:
                    login(sc);
                    break;
                case 3:
                    buyItem(sc);
                    break;
                case 4:
                    replaceItem(sc);
                    break;
                case 5:
                    logout();
                    break;
                case 6:
                    showOrders();
                case 7:
                    System.out.println("Thank you for shopping with us!");
                    System.exit(0);
            }
        }
    }

    private static void showOrders() {
    }

    private static void logout() {
        if(currentUser == null) {
            System.out.println("Not currently logged in therefore you can't log out.");
            return;
        } else {
            currentUser = null;
            customerOrderList.clear();
            System.out.println("Successfully logged out!");
        }
    }

    private static void replaceItem(Scanner sc) {
        sc = new Scanner(System.in);
        // how to replace an item
        //first we ne nee
    }

    private static void buyItem(Scanner sc) {
        sc = new Scanner(System.in);
        boolean stillShopping = true;
        int choice;
        if(currentUser == null) {
            System.out.println("Please log in to make a purchase");
            return;
        }
        else {
            System.out.println("     Standalone Ecommerce App            ");
            System.out.println("+========================================+");
            System.out.println("+  Items          Item Code         Price+");
            System.out.println("+  1. Jacket      Ja1               $20  +" );
            System.out.println("+  2. Jeans       Je1               $10  +");
            System.out.println("+  3. Shirt       Sh1               $5   +");
            System.out.println("+  4. Exit                               +");
            System.out.println("+========================================+");

            try{
                if(sc.hasNextInt()) {
                    choice = sc.nextInt();
                } else {
                    throw new InvalidChoiceBuyItemException("Invalid choice, please enter a whole number.");
                }
                switch (choice) {
                    case 1:
                        CustomerOrder jacketOrder = new CustomerOrder("Ja1", "Jacket", 20.00, currentUser.getEmail());
                        customerService.addOrder(jacketOrder);
                        break;
                    case 2:
                        CustomerOrder jeanOrder = new CustomerOrder("Je1", "Jeans", 10.00, currentUser.getEmail());
                        customerService.addOrder(jeanOrder);
                    case 3:
                        CustomerOrder shirtOrder = new CustomerOrder("Sh1", "Shirt", 5.00, currentUser.getEmail());
                        customerService.addOrder(shirtOrder);

                }
                
            } catch (InvalidChoiceBuyItemException e) {
                System.out.println(e);
            }
        }
    }

    private static void login(Scanner sc) throws InvalidLoginEmailException, InvalidLoginPasswordException {
        String email;
        String password;
        sc = new Scanner(System.in);
        boolean valid = true;
        if(currentUser != null) {
            System.out.println("Someone is currently logged in! Please wait for someone to logout. Thank you!");
            return;
        }

        while (valid) {
            System.out.println("             Login     ");
            System.out.println("+============================+");
            System.out.println("+ Please enter email:        +");
            if(sc.hasNextLine()) {
                email = sc.nextLine();
            } else {
                throw new InvalidLoginEmailException("Did not enter a proper email address.");
            }
            System.out.println("+ Please enter password:     +");
            if(sc.hasNextLine()) {
                password = sc.nextLine();
            } else {
                throw new InvalidLoginPasswordException("Did not enter a proper password");
            }

            Customer found = service.getCustomerByEmail(email);
            try {
                if(found != null) {
                    //check if the passwords match
                    try {
                        if(!password.equals(found.getPassword())) {
                            throw new PasswordDoesNotMatchException("Password does not match");
                        }

                        System.out.println("Login Successful!!");
                        valid = false;
                        currentUser = found;

                    } catch (PasswordDoesNotMatchException e) {
                        System.out.println(e);
                        valid = false;
//                        loggedIn = true;
                    }
                } else {
                    throw new EmailNotFoundException("Email not found.");
                }
            } catch (EmailNotFoundException e) {
                System.out.println(e);
            }

        }
    }


    private static boolean verifyPattern(Pattern p, String s) {
        Matcher matcher = p.matcher(s);
        if(matcher.matches() == true) {
            return true;
        }
        return false;
    }

    private static void register(Scanner sc) {
        Pattern emailPattern = Pattern.compile("^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$");
        Pattern passwordPattern = Pattern.compile("(?=.*[a-z])(?=.*[@#$%!^&])(?=.*[A-Z]).{8}");
        try {
            sc = new Scanner(System.in);
            String name = "";
            String email = "";
            String password = "";

            System.out.println("Customer name: ");
            if(sc.hasNextLine()) {
                name = sc.nextLine();
            } else {
                throw new InvalidNameException("Unable to process name.");
            }
            System.out.println("Email: ");
            if(sc.hasNextLine()) {
                email = sc.nextLine();
                if(!verifyPattern(emailPattern, email)) {
                    throw new InvalidEmailPatternException("Email pattern does not satisfy as a pattern for an email. An example of a correct email patter: fdjakl@ucdavis.edu");
                }
            } else {
                throw new InvalidEmailException("Unable to process email.");
            }

            System.out.println("Password: Must contain a characters with a lower, an upper and a special.");
            if(sc.hasNextLine()) {
                password = sc.nextLine();
                if(!verifyPattern(passwordPattern, password)) {
                    throw new InvalidPasswordPatternException("Your password does not contain 8 characeters with a  lower, upper and special.");
                }

            } else {
                throw new InvalidPasswordInputException("Unable to process password.");
            }
            Customer customer = new Customer(name, email, password);
            service.addCustomer(customer);
            System.out.println("Registered!!");
        } catch (InvalidNameException e) {
            System.out.println(e);
        } catch (InvalidEmailException e) {
            System.out.println(e);
        } catch (InvalidPasswordInputException e) {
            System.out.println(e);
        } catch (InvalidPasswordPatternException e) {
            System.out.println(e);
        } catch (InvalidEmailPatternException e) {
            e.printStackTrace();
        }

    }

    private static int getChoice(Scanner sc) {
        int choice;
        sc = new Scanner(System.in);
        try {

            System.out.println("    Standalone Ecommerce App");
            System.out.println("+==============================+");
            System.out.println("+   1.Register                 +");
            System.out.println("+   2.Login                    +");
            System.out.println("+   3.Buy an item              +");
            System.out.println("+   4.Replace an item          +");
            System.out.println("+   5.Logout                   +");
            System.out.println("+   6.Show orders              +");
            System.out.println("+   7.Exit                     +");
            System.out.println("+==============================+");

            choice = sc.nextInt();

            return choice;
        } catch (InputMismatchException e) {
            System.out.println("Thats not an Integer. Please try again.");
            return 0;
        }
    }
}
