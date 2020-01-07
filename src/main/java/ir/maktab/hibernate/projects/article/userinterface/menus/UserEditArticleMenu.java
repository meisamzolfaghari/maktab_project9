package ir.maktab.hibernate.projects.article.userinterface.menus;

import ir.maktab.hibernate.projects.article.core.Actions;
import ir.maktab.hibernate.projects.article.core.AllRoles;
import ir.maktab.hibernate.projects.article.userinterface.functions.Articles;
import ir.maktab.hibernate.projects.article.userinterface.functions.Users;
import ir.maktab.hibernate.projects.article.entities.Article;
import ir.maktab.hibernate.projects.article.features.articlemanagement.impls.*;
import ir.maktab.hibernate.projects.article.features.articlemanagement.usecases.*;
import ir.maktab.hibernate.projects.article.features.rolemanagement.impls.FindRoleByTitleUseCaseImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class UserEditArticleMenu extends Menu {

    private Article articleForEdit;

    public UserEditArticleMenu(Article articleForEdit) {
        super();
        this.articleForEdit = articleForEdit;
    }

    @Override
    public void execute() {

        command = "";

        while (!command.equals(Actions.back.name())) {

            Articles.displayFullVersion(articleForEdit);

            displayMenu();
            command = Users.takeCommand(actions);

            if (command.equals(Actions.exit.name())) {
                exit();
            } else if (command.equals(Actions.back.name())) continue;

            Date currentDate = new Date(System.currentTimeMillis());

            if (command.equals(Actions.title.name())) {
                ChangeArticleTitleUseCase changeArticleTitleUseCase
                        = new ChangeArticleTitleUseCaseImpl();
                changeArticleTitleUseCase
                        .change(articleForEdit, Articles.takeNewTitle(), currentDate);
            } else if (command.equals(Actions.brief.name())) {
                ChangeArticleBriefUseCase changeArticleBriefUseCase
                        = new ChangeArticleBriefUseCaseImpl();
                changeArticleBriefUseCase
                        .change(articleForEdit, Articles.takeNewBrief(), currentDate);
            } else if (command.equals(Actions.content.name())) {
                ChangeArticleContentUseCase changeArticleContentUseCase
                        = new ChangeArticleContentUseCaseImpl();
                changeArticleContentUseCase
                        .change(articleForEdit, Articles.takeNewContent(), currentDate);
            } else if (command.equals(Actions.publish.name())) {
                PublishArticleUseCase publishArticleUseCase
                        = new PublishArticleUseCaseImpl();
                publishArticleUseCase.publish(articleForEdit, currentDate);
            } else if (command.equals(Actions.unpublish.name())) {
                UnPublishArticleUseCase unPublishArticleUseCase
                        = new UnPublishArticleUseCaseImpl();
                unPublishArticleUseCase.unPublish(articleForEdit, currentDate);
            }

        }
    }

    @Override
    protected void displayMenu() {
        System.out.println("\t+---------------------------------------------------------------+");
        System.out.println("\t|                 User Edit Article Menu                        |");
        System.out.println("\t+---------------------------------------------------------------+");
        System.out.println("\t|  title           ---->    Edit Title.                         |");
        System.out.println("\t|  brief           ---->    Edit Brief.                         |");
        System.out.println("\t|  content         ---->    Edit Content.                       |");
        if (loginUser.getRoles()
                .contains(new FindRoleByTitleUseCaseImpl().find(AllRoles.manager.name()))) {
            if (!articleForEdit.isPublished())
                System.out.println("\t|  publish         ---->    Publish Article.                    |");
            else if (articleForEdit.isPublished())
                System.out.println("\t|  unpublish       ---->    UnPublish Article.                  |");
        }
        System.out.println("\t|  back            ---->    Back to Last Menu.                  |");
        System.out.println("\t|  exit            ---->    Exit.                               |");
        System.out.println("\t+---------------------------------------------------------------+");
    }

    @Override
    protected void setActions() {
        actions = new ArrayList<>(
                Arrays.asList(
                        Actions.title.name()
                        , Actions.brief.name()
                        , Actions.content.name()
                        , Actions.back.name()
                        , Actions.exit.name()));
        if (loginUser.getRoles()
                .contains(new FindRoleByTitleUseCaseImpl().find(AllRoles.manager.name()))) {
            if (!articleForEdit.isPublished())
                actions.add(Actions.publish.name());
            else if (articleForEdit.isPublished())
                actions.add(Actions.unpublish.name());
        }
    }
}
