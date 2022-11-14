package service;

import domain.Prietenie;
import domain.User;
import domain.validators.EntityIsNull;
import domain.validators.EntityNotFound;
import domain.validators.ValidatorException;
import repo.PrietenieFileRepository;
import repo.UserFileRepository;

public class ServicePrietenii
{
    UserFileRepository users_repo;
    PrietenieFileRepository prietenii_repo;

    /**
     * constructor cu parametrii, in care intializam datele membre cu instante de tipul UserFileRepository, respectiv
     * PrietenieFileRepository
     * @param users_repo-UserFileRepository
     * @param prietenii_repo-PrietenieFileRepository
     */
    public ServicePrietenii(UserFileRepository users_repo, PrietenieFileRepository prietenii_repo)
    {
        this.users_repo = users_repo;
        this.prietenii_repo = prietenii_repo;
    }

    /**
     * Cream o prietenie si o returnam(daca aceasta exista deja, doar o returnam)
     * @param id-Integer
     * @param id_user1-Integer
     * @param id_user2-Integer
     * @return Prietenia creata pe baza datelor primite ca parametru
     * @throws ValidatorException
     */
    public Prietenie add_prietenie(int id, int id_user1, int id_user2) throws ValidatorException, EntityIsNull {
        /*
        OBSERVATIE!!!MEREU SA TINEM CONT CA EXCEPTIILE RunTimeError, Error si CeleDerivate din acestea NU VOR APAREA AUTOMAT
        IN DEFINITIA FUNCTIEI(la fel si pentru functiile in care aceasta este folosita, nu se propaga fiindca ELE SUNT DE TIPUL
        UNCHECKED(pot aparea si fara sa fie mentionate), asa ca trebuie apelate explicit
         */
        Prietenie friendship = new Prietenie(id, id_user1, id_user2);
        this.prietenii_repo.save(friendship);
        return friendship;
    }

    public Prietenie delete_prietenie(int id) throws EntityIsNull, EntityNotFound {
        Prietenie friendship = this.prietenii_repo.delete(id);
        if(friendship == null)///prietenia de sters nu a fost gasita
            throw new EntityNotFound("Prietenia de sters nu exista!");
        return friendship;
    }

    /**
     * Metoda ne obtine colectie(iterabila) de prietenii
     * @return colectia noastra de prietenii
     */
    public Iterable<Prietenie> findAll()
    {
        return this.prietenii_repo.findAll();
    }
}
