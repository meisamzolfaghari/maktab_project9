package ir.maktab.hibernate.projects.article.core.menus.admin;

import ir.maktab.hibernate.projects.article.core.Actions;
import ir.maktab.hibernate.projects.article.core.functions.Tags;
import ir.maktab.hibernate.projects.article.core.functions.Users;
import ir.maktab.hibernate.projects.article.core.menus.Menu;
import ir.maktab.hibernate.projects.article.entities.Tag;
import ir.maktab.hibernate.projects.article.repositories.TagRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FindTagMenu extends Menu {
    private List<Tag> tags;

    public FindTagMenu(List<Tag> tags) {
        this.tags = tags;
    }


    @Override
    public void execute() {
        command = "";
        while (!command.equals(Actions.back.name())) {

            if (!tags.isEmpty())
                Tags.displayAll(tags);
            else {
                System.out.println("\t\u26A0 Tag Title not Found!");
                break;
            }

            takeCommand();

            if (command.equals(Actions.exit.name())) {
                System.out.println("\n bye bye!");
                System.exit(0);
            }
            else if (command.equals(Actions.add.name())) {
                Tags.add();
            }
            else if (command.equals(Actions.delete.name())) {
                Tag chosenTag = Tags.choose(tags);
                Tags.delete(chosenTag);
            }
        }
    }

    @Override
    protected void displayMenu() {
        System.out.println("\t+---------------------------------------------------------------+");
        System.out.println("\t|                    Find Tag Menu                              |");
        System.out.println("\t+---------------------------------------------------------------+");
        System.out.println("\t|  delete          ---->    Delete Tag.                         |");
        System.out.println("\t|  back            ---->    Back to Tag Menu.                   |");
        System.out.println("\t|  exit            ---->    Exit.                               |");
        System.out.println("\t+---------------------------------------------------------------+");
    }

    @Override
    protected void setActions() {
        actions = new ArrayList<>(
                Arrays.asList(
                        Actions.delete.name()
                        , Actions.back.name()
                        , Actions.exit.name()));
    }
}
