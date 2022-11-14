package repo;

import java.util.*;
import domain.Entity;
import domain.validators.EntityIsNull;
import domain.validators.EntityNotFound;
import domain.validators.Validator;
import domain.validators.ValidatorException;

import java.util.HashMap;

public class AbstractRepo<ID, E extends Entity<ID>> implements Repository<ID, E>
{
    private Map<ID, E> items;///elementele repo ului

    private Validator<E> validator;///un validator care valideaza elementele de tip E

    public AbstractRepo(Validator<E> validator)
    {
        this.items = new HashMap<>();
        this.validator = validator;
    }
    @Override
    public E save(E entity) throws IllegalArgumentException, ValidatorException, EntityIsNull {
        /*
        OBSERVATIE!!!MEREU SA TINEM CONT CA EXCEPTIILE RunTimeError, Error si CeleDerivate din acestea NU VOR APAREA AUTOMAT
        IN DEFINITIA FUNCTIEI(la fel si pentru functiile in care aceasta este folosita, nu se propaga fiindca ELE SUNT DE TIPUL
        UNCHECKED(pot aparea si fara sa fie mentionate), asa ca trebuie apelate explicit
        De ex, IllegalArgumentException(pe care am inlocuit o cu EntityIsNull pentru ca aceasta,facuta de noi, E CHECKED)
        IlleagalArgumentException nu e folosita
         */
            if (entity == null)
                throw new EntityIsNull("Entitatea nu poate fi nula");
            if (items.containsKey(entity.getID()))///exista deja entitatea pe care vrem sa o aduagam
                return entity;
            ///suntem in cazul in care entitatea nu se afla in colectia noastra de entitati
            validator.validate(entity);
            items.put(entity.getID(), entity);
            return entity;
    }
    @Override
    public E delete(ID id) throws EntityIsNull
    {
        if(id == null)
            throw new EntityIsNull("Id ul nu poate fi vid");
        E entity = null;///initial entity e null(e o referinta catre o zona unde nu avem nimic)
        if(items.containsKey(id))
        {
            entity = items.get(id);///exista obiectul
            items.remove(id);
        }
        return entity;
    }

    @Override
    public E findOne(ID id) {
        if(items.containsKey(id))
            return items.get(id);
        return null;
    }

    @Override
    public Iterable<E> findAll() {
        return items.values();
    }
}
