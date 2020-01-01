package ir.maktab.hibernate.projects.article.core.functions;

import ir.maktab.hibernate.projects.article.core.AllRoles;
import ir.maktab.hibernate.projects.article.core.share.AuthenticationService;
import ir.maktab.hibernate.projects.article.entities.Role;
import ir.maktab.hibernate.projects.article.entities.User;
import ir.maktab.hibernate.projects.article.repositories.RoleRepository;
import ir.maktab.hibernate.projects.article.repositories.UserRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class Users {

    public static User choose(List<User> users) {

        if (users.isEmpty()) return null;

        Scanner in = new Scanner(System.in);

        Long id;

        do {
            System.out.print("\t\u29bf ID : ");
            id = in.nextLong();
            for (User user : users)
                if (id.equals(user.getId()))
                    return user;

            System.out.println("\t\u26a0 Invalid ID! Try again...\n");

        } while (true);
    }

    public static void displayAll(List<User> users) {

        if (users.isEmpty()) return;

        System.out.println("------------------------------------------------------------------------------------"
                + "                                Users");
        users.forEach(user -> System.out.println(
                ""
                        + "------------------------------------------------------------------------------------"
                        + "\n ID= " + user.getId()
                        + " , Username= '" + user.getUsername()
                        + "' , User AllRoles = " + user.getRoles()
                        + "\n------------------------------------------------------------------------------------"));
    }

    public static void display(User user) {

        if (user == null) return;

        System.out.println(
                ""
                        + "------------------------------------------------------------------------------------"
                        + "\n    ID= " + user.getId()
                        + "\n    Username= '" + user.getUsername()
                        + "'\n    National Code= '" + user.getNationalCode()
                        + "'\n    Birth Day= '" + new SimpleDateFormat("dd-MM-yyyy").format(user.getBirthDay())
                        + "'\n    User AllRoles = '" + user.getRoles()
                        + "'\n------------------------------------------------------------------------------------");
    }

    public static void addRole(User user) {
        if (user == null) return;

        List<Role> userRoles = user.getRoles();

        RoleRepository roleRepository = RoleRepository.getInstance();
        List<Role> roles = roleRepository.findAll().stream()
                .filter(role -> !userRoles.contains(role) &&
                        role.getTitle().equals(AllRoles.admin.name()))
                .collect(Collectors.toList());

        if (roles.isEmpty()) {
            System.out.println("\t\u274c Add Role Failed! This User Already has All the Roles.\n");
            return;
        }

        Roles.displayAll(roles);
        Role role = Roles.choose(roles);

        if (!userRoles.contains(role)) {
            userRoles.add(role);
            user.setRoles(userRoles);
            UserRepository userRepository = UserRepository.getInstance();

            userRepository.update(user);

            if (userRepository.findById(user.getId()).getRoles().contains(role))
                System.out.println("\t\u2714 Role successfully Added to User.\n");
            else
                System.out.println("\t\u274c Failed to Add Role!\n");
        }
        else
            System.out.println("\t\u274c Add Role Failed! This User Already is a "+ role.getTitle() +".\n");

    }

    public static void deleteRole(User user) {
        if (user == null) return;

        List<Role> userRoles = user.getRoles();

        RoleRepository roleRepository = RoleRepository.getInstance();
        List<Role> roles = roleRepository.findAll().stream()
                .filter(role -> userRoles.contains(role) &&
                        role.getTitle().equals(AllRoles.admin.name()))
                .collect(Collectors.toList());

        if (roles.isEmpty()) {
            System.out.println("\t\u274c Delete Role Failed! This User Already has no Roles.\n");
            return;
        }

        Roles.displayAll(roles);
        Role role = Roles.choose(roles);
        if (roles.contains(role)) {
            roles.remove(role);
            user.setRoles(roles);
            UserRepository userRepository = UserRepository.getInstance();

            userRepository.update(user);

            if (!userRepository.findById(user.getId()).getRoles().contains(role))
                System.out.println("\t\u2714 Role successfully deleted from User.\n");
            else
                System.out.println("\t\u274c Failed to delete Role!\n");
        }
        else
            System.out.println("\t\u274c Add Role Failed! This User is not a "+ role.getTitle() +".\n");
    }

    public static void delete(User user) {
        UserRepository userRepository = UserRepository.getInstance();

        userRepository.remove(user);
        if (userRepository.findById(user.getId()) == null)
            System.out.println("\t\u2714 Article successfully Deleted.\n");
        else
            System.out.println("\t\u274c Delete Article Failed!\n");
    }

    public static void add(){
        Scanner in = new Scanner(System.in);

        UserRepository userRepository = UserRepository.getInstance();

        boolean finished;
        String username;
        String nationalCode;
        String tempBirthDay;
        Date birthDay = null;
        User userToAdd = new User();
        do {
            System.out.print("\t\u29bf Username : ");
            username = in.next();
            if (!isUniqUsername(username))
                System.out.println("\t\u26a0 Sorry! username is Owned by someone else! try another username...");
            else break;
        } while (true);

        System.out.print("\t\u29bf National Code : ");
        nationalCode = in.next();

        //taking a date and validation...
        do {
            finished = true;
            try {
                System.out.print("\t\u29bf Birth Day (dd-MM-yyyy) : ");
                tempBirthDay = in.next();
                birthDay = new SimpleDateFormat("dd-MM-yyyy").parse(tempBirthDay);
            } catch (ParseException e) {
                finished = false;
                System.out.println("\t\u274c Invalid Date! Try again...\n");
            }
        }while (!finished);

        userToAdd.setUsername(username);userToAdd.setNationalCode(nationalCode);
        userToAdd.setBirthDay(birthDay);userToAdd.setPassword(nationalCode);
        userToAdd.setRoles(new ArrayList<>(Arrays.asList(Roles.getRole(AllRoles.writer.name()))));

        userRepository.save(userToAdd);
        User registeredUser = userRepository.findById(userToAdd.getId());
        if (registeredUser != null)
            System.out.println("\t\u2714 User successfully Registered.\n");
        else
            System.out.println("\t\u274c Register Failed!\n");
    }

    private static boolean isUniqUsername(String username){
        UserRepository userRepository = UserRepository.getInstance();
        for (User user : userRepository.findAll())
            if (user.getUsername().equals(username))
                return false;
        return true;
    }

    public static void login(){
        Scanner in = new Scanner(System.in);
        User user = new User();
        System.out.print("\t\u29bf Username : ");
        user.setUsername(in.next());

        //taking password from user
        System.out.print("\t\u29bf Password : ");
        user.setPassword(in.next());

        user = ValidUser(user);
        if (user != null)
            AuthenticationService.getInstance().setLoginUser(user);

    }

    private static User ValidUser(User user){
        UserRepository userRepository = UserRepository.getInstance();
        for (User tempUser : userRepository.findAll())
            if (tempUser.getUsername().equals(user.getUsername())
                    && tempUser.getPassword().equals(user.getPassword()) )
                return tempUser;
        return null;
    }

    public static void ChangeUsername(){

        Scanner in = new Scanner(System.in);

        UserRepository userRepository = UserRepository.getInstance();
        User loginUser = AuthenticationService.getInstance().getLoginUser();
        if (loginUser == null) return;

        System.out.print("\t\u29bf Last Username : " + loginUser.getUsername());
        String username;
        do {
            System.out.print("\t\u29bf New Username >>> ");
            username = in.next();
            if (!isUniqUsername(username))
                System.out.println("\t\u26a0 Sorry! username is Owned by someone else! try another username...");
            else break;
        } while (true);

        loginUser.setUsername(username);

        userRepository.update(loginUser);
        User editedLoginUser = userRepository.findById(loginUser.getId());

        if (editedLoginUser.getUsername().equals(loginUser.getUsername())) {
            AuthenticationService.getInstance().setLoginUser(editedLoginUser);
            System.out.println("\t\u2714 Username successfully Edited.\n");
        } else
            System.out.println("\t\u274c Failed to Edit Username!\n");

    }

    public static void ChangeNationalCode(){
        Scanner in = new Scanner(System.in);

        UserRepository userRepository = UserRepository.getInstance();
        User loginUser = AuthenticationService.getInstance().getLoginUser();
        if (loginUser == null) return;

        System.out.print("\t\u29bf Last National Code : " + loginUser.getNationalCode());
        System.out.print("\t\u29bf New National Code >>> ");
        String nationalCode = in.next();

        loginUser.setNationalCode(nationalCode);

        userRepository.update(loginUser);
        User editedLoginUser = userRepository.findById(loginUser.getId());

        if (editedLoginUser.getNationalCode().equals(loginUser.getNationalCode())) {
            AuthenticationService.getInstance().setLoginUser(editedLoginUser);
            System.out.println("\t\u2714 National Code successfully Edited.\n");
        } else
            System.out.println("\t\u274c Failed to Edit National Code!\n");
    }

    public static void ChangeBirthDay(){
        Scanner in = new Scanner(System.in);

        UserRepository userRepository = UserRepository.getInstance();
        User loginUser = AuthenticationService.getInstance().getLoginUser();
        if (loginUser == null) return;

        System.out.print("\t\u29bf Last Birth Day : "
                + new SimpleDateFormat("dd-MM-yyyy").format(loginUser.getBirthDay()));
        boolean finished;
        Date birthDay = null;
        do {
            finished = true;
            try {
                System.out.print("\t\u29bf New Birth Day (dd-MM-yyyy) : ");
                birthDay = new SimpleDateFormat("dd-MM-yyyy").parse(in.next());
            } catch (ParseException e) {
                finished = false;
                System.out.println("\t\u274c Invalid Date! Try again...\n");
            }
        } while (!finished);

        loginUser.setBirthDay(birthDay);

        userRepository.update(loginUser);
        User editedLoginUser = userRepository.findById(loginUser.getId());

        if (editedLoginUser.getBirthDay().equals(loginUser.getBirthDay())) {
            AuthenticationService.getInstance().setLoginUser(editedLoginUser);
            System.out.println("\t\u2714 Birth Day successfully Edited.\n");
        } else
            System.out.println("\t\u274c Failed to Edit Birth Day!\n");
    }

    public static void ChangePassword(){
        Scanner in = new Scanner(System.in);

        UserRepository userRepository = UserRepository.getInstance();
        User loginUser = AuthenticationService.getInstance().getLoginUser();
        if (loginUser == null) return;

        String password;
        while (true) {
            System.out.print("\t\u29bf New Password >>> ");
            password = in.next();
            System.out.println("\t\u29bf Confirm New Password >>> ");
            if (in.next().equals(password)) {
                loginUser.setPassword(password);

                userRepository.update(loginUser);
                User editedLoginUser = userRepository.findById(loginUser.getId());

                if (editedLoginUser.getPassword().equals(loginUser.getPassword())) {
                    AuthenticationService.getInstance().setLoginUser(editedLoginUser);
                    System.out.println("\t\u2714 Password successfully Changed.\n");
                } else
                    System.out.println("\t\u274c Failed to Change Password!\n");
                break;
            } else
                System.out.println("\t\u274c Passwords are not match! Try again....\n");
        }
    }

}

