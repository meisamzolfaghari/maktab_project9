package ir.maktab.hibernate.projects.article.core.functions;

import ir.maktab.hibernate.projects.article.core.AllRoles;
import ir.maktab.hibernate.projects.article.entities.Role;
import ir.maktab.hibernate.projects.article.repositories.RoleRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Roles {

    public static Role choose(List<Role> roles) {
        if (roles.isEmpty()) return null;

        Scanner in = new Scanner(System.in);

        Long id;

        do {
            System.out.print("\t\u29bf ID : ");
            id = in.nextLong();
            for (Role role : roles)
                if (id.equals(role.getId()))
                    return role;

            System.out.println("\t\u26a0 Invalid ID! Try again...\n");

        } while (true);
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

    public static Role getRole(String roleTitle) {
        RoleRepository roleRepository = RoleRepository.getInstance();
        for (Role role : roleRepository.findAll())
            if (role.getTitle().equals(roleTitle))
                return role;
        return null;
    }
}
