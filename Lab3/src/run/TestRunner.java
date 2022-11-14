package run;

import domain.validators.EntityIsNull;
import domain.validators.PrietenieValidator;
import domain.validators.UserValidator;
import domain.validators.ValidatorException;
import repo.PrietenieFileRepository;
import repo.UserFileRepository;
import resources.Config;
import service.ServicePrietenii;
import service.ServiceUsers;

import java.io.IOException;

public class TestRunner
{
    public static void test_repo_users() throws ValidatorException, EntityIsNull {
        String usersFileName = Config.getProperties().getProperty("Users");
        UserFileRepository users_repo = new UserFileRepository(new UserValidator(), usersFileName);
        users_repo.findAll().forEach(x->System.out.println(x.toString()));
    }
    public static void test_repo_friendships() throws ValidatorException, IOException, EntityIsNull {
        String friendshipsFileName = Config.getProperties().getProperty("Prietenii");
        PrietenieFileRepository prietenii_repo = new PrietenieFileRepository(new PrietenieValidator(), friendshipsFileName);
        prietenii_repo.findAll().forEach(x->System.out.println(x.toString()));
    }
    public static void test_service_users() throws Exception {
        String usersFileName = Config.getProperties().getProperty("Users");
        String friendshipsFileName = Config.getProperties().getProperty("Prietenii");
        UserFileRepository users_repo = new UserFileRepository(new UserValidator(), usersFileName);
        PrietenieFileRepository prietenii_repo = new PrietenieFileRepository(new PrietenieValidator(), friendshipsFileName);
        ServiceUsers users_service = new ServiceUsers(users_repo, prietenii_repo);
        users_service.add_user(10, "Mihai", "Muntean", "Beclean");
        users_service.add_user(11, "Mihai", "Muntean", "Beclean");
        users_service.delete_user(11);
        users_service.findAll().forEach(x->System.out.println(x.toString()));
        try{
            users_service.delete_user(11);///nu exista nici un user de id dorit
            assert(false);
        }
        catch(Exception e)
        {
        }
    }

    public static void test_service_prietenii() throws Exception {
        String usersFileName = Config.getProperties().getProperty("Users");
        String friendshipsFileName = Config.getProperties().getProperty("Prietenii");
        UserFileRepository users_repo = new UserFileRepository(new UserValidator(), usersFileName);
        PrietenieFileRepository prietenii_repo = new PrietenieFileRepository(new PrietenieValidator(), friendshipsFileName);
        ServicePrietenii prietenii_service = new ServicePrietenii(users_repo, prietenii_repo);
        prietenii_service.add_prietenie(7, 2, 4);
        prietenii_service.findAll().forEach(x->System.out.println(x.toString()));
        try{
            prietenii_service.delete_prietenie(12);///nu exista nici un user de id dorit
            assert(false);
        }
        catch(Exception e)
        {
            ///nu exista prietenia de sters
        }
        try{
            prietenii_service.add_prietenie(8, 2, 2);
            assert(false);
        }
        catch(Exception e)
        {
            ///utlizatorii relatiei sunt unul si acelasi
        }
    }
}
