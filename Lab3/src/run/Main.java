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