package repo;

import domain.validators.EntityIsNull;
import domain.validators.ValidatorException;

public interface Repository<ID, E>
{
    /**
     * salvam entitatea in repository
     * @param entity-E
     * @return Entitatea salvata sau null daca nu o putem salva
     */
    E save(E entity) throws IllegalArgumentException, ValidatorException, EntityIsNull;

    /**
     * @param id-ID
     * @return entiatea pe care am sters o sau null daca entitatea de sters nu exista
     */
    E delete(ID id) throws EntityIsNull;

    /**
     * @param id-ID
     * @return entitatea de id dorit sau null in caz contrar
     */
    E findOne(ID id);

    /**
     * @return toate elementele colectiei
     */
    Iterable<E> findAll();

    /**
     * obtinem cate entitati are repo ul pana in momentul de fata
     * @return numarul de entitati
     */
    int size();
}
