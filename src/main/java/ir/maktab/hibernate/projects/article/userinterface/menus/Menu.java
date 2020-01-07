package ir.maktab.hibernate.projects.article.userinterface.menus;

import ir.maktab.hibernate.projects.article.core.share.AuthenticationService;
import ir.maktab.hibernate.projects.article.entities.User;

import java.util.List;

public abstract class Menu {
    protected User loginUser;
    protected String command;
    protected List<String> actions;

    public Menu(){
        loginUser = AuthenticationService.getInstance().getLoginUser();
        setActions();
    }

    public abstract void execute();

    protected abstract void displayMenu();

    protected abstract void setActions();

    protected void exit(){
        System.out.println("\n bye bye!");
        System.exit(0);
    }
}
