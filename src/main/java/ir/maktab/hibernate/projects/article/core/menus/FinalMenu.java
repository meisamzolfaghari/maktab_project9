package ir.maktab.hibernate.projects.article.core.menus;

import ir.maktab.hibernate.projects.article.core.Actions;

import java.util.ArrayList;
import java.util.Arrays;

public class FinalMenu extends Menu {
    @Override
    public void execute() {
        command = "";

        while (!command.equals(Actions.back.name())) {

            takeCommand();

            if (command.equals(Actions.exit.name())) {
                System.out.println("\n bye bye!");
                System.exit(0);
            }
        }
    }

    @Override
    protected void displayMenu() {
        System.out.println("\t+---------------------------------------------------------------+");
        System.out.println("\t|  back            ---->    Back to last Menu.                  |");
        System.out.println("\t|  exit            ---->    Exit.                               |");
        System.out.println("\t+---------------------------------------------------------------+");
    }

    @Override
    protected void setActions() {
        actions = new ArrayList<>(
                Arrays.asList(
                        Actions.back.name()
                        , Actions.exit.name()));
    }
}
