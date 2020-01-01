package ir.maktab.hibernate.projects.article.core.menus;

import java.util.List;
import java.util.Scanner;

public abstract class Menu {
    protected String command;
    protected List<String> actions;

    public abstract void execute();
    protected abstract void displayMenu();

    protected void takeCommand(){
        Scanner in = new Scanner(System.in);
        while (true) {

            displayMenu();

            System.out.print("\u29bf Command >> ");
            command = in.nextLine();

            if (!actions.contains(command))
                System.out.println("\t\u26a0 Invalid Command!");
            else
                break;
        }
    }

    protected abstract void setActions();
}
