package ir.maktab.hibernate.projects.article.userinterface.functions;

import ir.maktab.hibernate.projects.article.core.AllRoles;
import ir.maktab.hibernate.projects.article.entities.Role;
import ir.maktab.hibernate.projects.article.features.rolemanagement.impls.FindRoleByTitleUseCaseImpl;

import java.util.List;
import java.util.Scanner;

public class Roles {
    private static Scanner in = new Scanner(System.in);

    public static Role choose(List<Role> roles) {
        if (roles.isEmpty()) return null;

        Long id;

        System.out.print("\t\u29bf ID : ");
        id = in.nextLong();
        for (Role role : roles)
            if (id.equals(role.getId()))
                return role;

        System.out.println("\t\u26a0 Invalid ID!\n");
        return null;
    }

    public static Role getAdminRole() {
        return new FindRoleByTitleUseCaseImpl().find(AllRoles.admin.name());
    }

    public static Role getManagerRole() {
        return new FindRoleByTitleUseCaseImpl().find(AllRoles.manager.name());
    }

    public static Role getWriterRole() {
        return new FindRoleByTitleUseCaseImpl().find(AllRoles.writer.name());
    }

    public static void displayAll(List<Role> roles) {
        if (roles.isEmpty()) return;

        System.out.println("------------------------------------------------------------------------------------"
                + "                                All Roles");
        roles.forEach(role -> System.out.println(
                ""
                        + "------------------------------------------------------------------------------------"
                        + "\n ID= " + role.getId()
                        + " , Title= '" + role.getTitle()
                        + "'\n------------------------------------------------------------------------------------"));
    }

}
