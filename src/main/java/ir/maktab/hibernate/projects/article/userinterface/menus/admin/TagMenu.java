package ir.maktab.hibernate.projects.article.userinterface.menus.admin;

import ir.maktab.hibernate.projects.article.core.Actions;
import ir.maktab.hibernate.projects.article.userinterface.functions.Tags;
import ir.maktab.hibernate.projects.article.userinterface.functions.Users;
import ir.maktab.hibernate.projects.article.userinterface.menus.Menu;
import ir.maktab.hibernate.projects.article.entities.Tag;
import ir.maktab.hibernate.projects.article.features.tagmanagement.impls.AddTagUseCaseImpl;
import ir.maktab.hibernate.projects.article.features.tagmanagement.impls.DeleteTagUseCaseImpl;
import ir.maktab.hibernate.projects.article.features.tagmanagement.impls.FindAllTagUseCaseImpl;
import ir.maktab.hibernate.projects.article.features.tagmanagement.impls.FindTagByTitleUseCaseImpl;
import ir.maktab.hibernate.projects.article.features.tagmanagement.usecases.AddTagUseCase;
import ir.maktab.hibernate.projects.article.features.tagmanagement.usecases.DeleteTagUseCase;
import ir.maktab.hibernate.projects.article.features.tagmanagement.usecases.FindAllTagUseCase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TagMenu extends Menu {

    public TagMenu() {
        super();
    }

    @Override
    public void execute() {
        command = "";
        while (!command.equals(Actions.back.name())) {

            FindAllTagUseCase findAllTagUseCase = new FindAllTagUseCaseImpl();

            List<Tag> tags = findAllTagUseCase.list();

            if (!tags.isEmpty())
                Tags.displayAll(tags);
            else {
                System.out.println("\t\u26A0 Tags List is empty!");
                break;
            }

            displayMenu();
            command = Users.takeCommand(actions);

            if (command.equals(Actions.exit.name())) {
                exit();
            }

            if (command.equals(Actions.find.name())) {
                Menu menu = new FindTagMenu(new FindTagByTitleUseCaseImpl().list(Tags.takeTitle()));
                menu.execute();
            } else if (command.equals(Actions.add.name())) {
                AddTagUseCase addTagUseCase = new AddTagUseCaseImpl();
                addTagUseCase.add(Tags.takeTitle());
            } else if (command.equals(Actions.delete.name())) {
                Tag chosenTag = Tags.choose(tags);
                if (chosenTag != null) {
                    DeleteTagUseCase deleteTagUseCase
                            = new DeleteTagUseCaseImpl();
                    deleteTagUseCase.delete(chosenTag);
                }
            }

        }
    }

    @Override
    protected void displayMenu() {
        System.out.println("\t+---------------------------------------------------------------+");
        System.out.println("\t|                    Tag Menu                                   |");
        System.out.println("\t+---------------------------------------------------------------+");
        System.out.println("\t|  find            ---->    Find Tag By Title.                  |");
        System.out.println("\t|  add             ---->    Add Tag.                            |");
        System.out.println("\t|  delete          ---->    Delete Tag.                         |");
        System.out.println("\t|  back            ---->    Back to Last Menu.                  |");
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
