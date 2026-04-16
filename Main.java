import service.LibraryService;
import java.util.*;

public class Main {

    static Scanner sc = new Scanner(System.in);
    static LibraryService service = new LibraryService();

    public static void main(String[] args) {

        while (true) {
            System.out.println("\n1.Login\n2.Exit");
            int choice = sc.nextInt();

            if (choice == 1) login();
            else break;
        }
    }

static void login() {
    System.out.print("Enter type (admin/user): ");
    String type = sc.next();

    if (!type.equals("admin") && !type.equals("user")) {
        System.out.println("ERROR: Invalid login type");
        return;
    }

    if (type.equals("admin")) adminMenu();
    else userMenu();
}

    static void adminMenu() {
        while (true) {
            System.out.println("\nADMIN MENU");
            System.out.println("1.Add Book\n2.Update Book\n3.View Books\n4.Add User\n5.Add Membership\n6.Logout");

            int ch = sc.nextInt();

            switch (ch) {
                case 1: service.addBook(); break;
                case 2: service.updateBook(); break;
                case 3: service.viewBooks(); break;
                case 4: service.addUser(); break;
                case 5: service.addMembership(); break;
                case 6: return;
            }
        }
    }

    static void userMenu() {
        while (true) {
            System.out.println("\nUSER MENU");
            System.out.println("1.Search Book\n2.Issue Book\n3.Return Book\n4.Pay Fine\n5.Logout");

            int ch = sc.nextInt();

            switch (ch) {
                case 1: service.searchBook(); break;
                case 2: service.issueBook(); break;
                case 3: service.returnBook(); break;
                case 4: service.payFine(); break;
                case 5: return;
            }
        }
    }
}