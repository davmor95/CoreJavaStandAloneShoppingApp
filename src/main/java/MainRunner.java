import exceptions.*;
import model.Customer;
import repo.CustomerRepo;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainRunner {
    public static final CustomerRepo service = new CustomerRepo();
    public static void main(String[] args) throws InvalidLoginEmailException, InvalidLoginPasswordException {
        Scanner sc = null;
        int choice;
        while(true) {
            System.out.println("----------------------------------------------------------------------");
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
            }
        }
    }

    private static void replaceItem(Scanner sc) {
    }

    private static void buyItem(Scanner sc) {
    }

    private static void login(Scanner sc) throws InvalidLoginEmailException, InvalidLoginPasswordException {
        String email;
        String password;
        sc = new Scanner(System.in);
        boolean valid = true;

        while (valid) {
            System.out.println("             Login     ");
            System.out.println("+============================+");
            System.out.println("+ Please enter email:        +:");
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
            if(found != null) {
                //check if the passwords match
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
            System.out.println("+   5.Exit                     +");
            System.out.println("+==============================+");

            choice = sc.nextInt();

            return choice;
        } catch (InputMismatchException e) {
            System.out.println("Thats not an Integer. Please try again.");
            return 0;
        }
    }
}
