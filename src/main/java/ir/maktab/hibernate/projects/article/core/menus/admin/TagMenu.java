package ir.maktab.hibernate.projects.article.core.menus.admin;

import ir.maktab.hibernate.projects.article.core.Actions;
import ir.maktab.hibernate.projects.article.core.functions.Tags;
import ir.maktab.hibernate.projects.article.core.menus.Menu;
import ir.maktab.hibernate.projects.article.entities.Tag;
import ir.maktab.hibernate.projects.article.repositories.TagRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TagMenu extends Menu {
    @Override
    public void execute() {
        command = "";
        while (!command.equals(Actions.back.name())) {

            TagRepository tagRepository = TagRepository.getInstance();

            List<Tag> tags = tagRepository.findAll();

            if (!tags.isEmpty())
                Tags.displayAll(tags);
            else {
                System.out.println("\t\u26A0 Tags List is empty!");
                break;
            }

            takeCommand();

            if (command.equals(Actions.exit.name())) {
                System.out.println("\n bye bye!");
                System.exit(0);
            }


            if (command.equals(Actions.find.name())) {

                new FindTagMenu(Tags.findTitle(tags)).execute();
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
        System.out.println("\t|                    Tag Menu                                   |");
        System.out.println("\t+---------------------------------------------------------------+");
        System.out.println("\t|  find            ---->    Find Tag By Title.                  |");
        System.out.println("\t|  add             ---->    Add a Tag.                          |");
        System.out.println("\t|  delete          ---->    Delete Tag.                         |");
        System.out.println("\t|  back            ---->    Back to Admin Menu.                 |");
        System.out.println("\t|  exit            ---->    Exit.                               |");
        System.out.println("\t+---------------------------------------------------------------+");
    }

    @Override
    protected void setActions() {
        actions = new ArrayList<>(
                Arrays.asList(
                        Actions.find.name()
                        , Actions.add.name()
                        , Actions.delete.name()
                        , Actions.back.name()
                        , Actions.exit.name()));
    }
}
