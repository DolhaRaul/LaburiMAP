package service;

import domain.User;
import domain.validators.EntityIsNull;
import domain.validators.EntityNotFound;
import domain.validators.ValidatorException;
import repo.PrietenieFileRepository;
import repo.UserFileRepository;

public class ServiceUsers
{
    private UserFileRepository users_repo;
    private PrietenieFileRepository friends_repo;

    /**
     * constructor cu parametrii, in care intializam datele membre cu insatnte de tipul UsersFileRepository, respectiv
     * PrietenieFileRepository; Datele in fisiere vor fi incarcate automat tinand, in constructorii claselor de tip repo
     * @param users_repo-UserFileRepository
     * @param friends_repo-PrietenieFileRepository
     */
    public ServiceUsers(UserFileRepository users_repo, PrietenieFileRepository friends_repo)
    {
        this.users_repo = users_repo;
        this.friends_repo = friends_repo;
    }

    /**
     * construim un obiect de tip User(daca se poate) si il salvam in colectia de utilizatori(colectie de tip HashMap)
     * @param id-Integer
     * @param nume-String
     * @param prenume-String
     * @param oras-String
     * @return obiectul de tip User ce va fi construit(daca se poate) pe baza datelor primite
     * @throws ValidatorException-Exception
     */
    public User add_user(int id, String nume, String prenume, String oras) throws ValidatorException, EntityIsNull {
        User user = new User(id, nume, prenume, oras);
        return this.users_repo.save(user);
    }

    /**
     * Stergem un utilizator pe baza unui id primit. Daca nu gasim nici un utilizator pentru un id primit, atunci vom
     * genera o eroare
     * @param id-Integer
     * @return Utilizatorul pe care l-am sters pe baza id-ului ce l-am primit ca si parametru
     * @throws IllegalArgumentException-user ul nu a fost gasit
     */
    public User delete_user(int id) throws EntityIsNull, EntityNotFound {
        User user = this.users_repo.delete(id);
        if(user == null)
            throw new EntityNotFound("Utilizatorul de sters nu a fost gasit!");
        return user;
    }

    /**
     * Metoda ne obtine colectie(iterabila) de utilizatori
     * @return colectia noastra de utilizatori
     */
    public Iterable<User> findAll()
    {
        return this.users_repo.findAll();
    }
}
