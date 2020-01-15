package ir.maktab.hibernate.projects.article.userinterface.menus.admin;

import ir.maktab.hibernate.projects.article.core.Actions;
import ir.maktab.hibernate.projects.article.core.share.AuthenticationService;
import ir.maktab.hibernate.projects.article.userinterface.functions.Tags;
import ir.maktab.hibernate.projects.article.userinterface.functions.Users;
import ir.maktab.hibernate.projects.article.userinterface.menus.Menu;
import ir.maktab.hibernate.projects.article.entities.Tag;
import ir.maktab.hibernate.projects.article.features.tagmanagement.impls.DeleteTagUseCaseImpl;
import ir.maktab.hibernate.projects.article.features.tagmanagement.usecases.DeleteTagUseCase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FindTagMenu extends Menu {
    private List<Tag> tags;

    public FindTagMenu(List<Tag> tags) {
        this.tags = tags;
        setActions();
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

            displayMenu();
            command = Users.takeCommand(actions);

            if (command.equals(Actions.exit.name())) {
                exit();
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
        System.out.println("\t|                    Find Tag Menu                              |");
        System.out.println("\t+---------------------------------------------------------------+");
        System.out.println("\t|  delete          ---->    Delete Tag.                         |");
        System.out.println("\t|  back            ---->    Back to Last Menu.                  |");
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
