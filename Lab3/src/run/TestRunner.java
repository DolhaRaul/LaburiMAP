package run;

import domain.Prietenie;
import domain.User;
import domain.validators.*;
import repo.InMemoryRepository;
import repo.PrietenieFileRepository;
import repo.UserFileRepository;
import resources.Config;
import service.ServicePrietenii;
import service.ServiceUsers;

import java.io.IOException;

public class TestRunner
{
    public static void test_in_memories_repos() throws EntityIsNull, ValidatorException {
        User user1 = new User(1, "Dolha", "Raul", "BN");
        User user2 = new User(2, "Mihai", "Muntean", "BN");
        Validator<User> validator = new UserValidator();
        InMemoryRepository<Integer, User> repo_users = new InMemoryRepository<>(validator);
        assert(user1.equals(repo_users.save(user1)));
        assert(user2.equals(repo_users.save(user2)));
        assert(repo_users.size() == 2);

        Validator<Prietenie> validator_p = new PrietenieValidator();
        InMemoryRepository<Integer, Prietenie> repo_prietenii = new InMemoryRepository<>(validator_p);
        Prietenie prietenie1 = new Prietenie(1, 1, 2);
        assert(prietenie1.equals(repo_prietenii.save(prietenie1)));
        assert(repo_prietenii.size() == 1);
    }
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
        ///users_service.findAll().forEach(x->System.out.println(x.toString()));
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
        assert(prietenii_service.size() == 5);
        ///aceasta prietenie exista deja, deci nu o mai putem adauga
        assert(prietenii_service.findOne(7).equals(prietenii_service.add_prietenie(8, 2, 4)));
        assert(prietenii_service.size() == 5);
        ///prietenii_service.findAll().forEach(x->System.out.println(x.toString()));
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
