package run;

import domain.User;
import domain.validators.*;
import repo.*;
import resources.Config;
import service.ServicePrietenii;
import service.ServiceUsers;
import ui.UserInterface;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws ValidatorException, IOException, EntityIsNull {
//        try {
////            System.out.println("Teste repo useri:");
////            TestRunner.test_repo_users();
////            System.out.println();
////            System.out.println("Teste repo prietenii:");
////            TestRunner.test_repo_friendships();
////            System.out.println();
//            System.out.println("Teste in memory repositories:");
//            TestRunner.test_in_memories_repos();
//            System.out.println("Tesetele pentru in memory repositories sunt bune!");
//            System.out.println("Test service useri:");
//            TestRunner.test_service_users();
//            System.out.println("Tesetele pentru service useri sunt bune!");
//            System.out.println("Teste service prietenii:");
//            TestRunner.test_service_prietenii();
//            System.out.println("Tesetele pentru service useri sunt bune!");
//        }
//        catch(Exception e)
//        {
//            System.err.println("Fisier corupt sau eroare la validare!");
//            e.printStackTrace();
//        }
//        String usersFileName = Config.getProperties().getProperty("Users");
//        String friendshipsFileName = Config.getProperties().getProperty("Prietenii");
//        UserFileRepository users_repo = new UserFileRepository(new UserValidator(), usersFileName);
//        PrietenieFileRepository prietenii_repo = new PrietenieFileRepository(new PrietenieValidator(), friendshipsFileName);
//        ServiceUsers users_service = new ServiceUsers(users_repo, prietenii_repo);
//        ServicePrietenii prietenii_service = new ServicePrietenii(users_repo, prietenii_repo);
//        UserInterface ui = new UserInterface(users_service, prietenii_service);
//        ui.run_menu();
        final String url = "jdbc:postgresql://localhost:5432/postgres";
        final String username = "postgres";
        final String password = "Rauldolha1";
        Repository<Integer, User> repoDB = new UtilizatorDBRepository(url, username, password, new UserValidator());
        repoDB.findAll().forEach(System.out::println);
    }
}