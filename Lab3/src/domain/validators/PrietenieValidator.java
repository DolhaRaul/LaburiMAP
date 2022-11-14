package domain.validators;

import domain.Prietenie;

public class PrietenieValidator implements Validator<Prietenie> {
    @Override
    public void validate(Prietenie entity) throws ValidatorException
    {
        if(entity.getID() < 0)
            throw new ValidatorException("Id-ul prietenie nu poate fi negativ!");
        if(entity.getId_user1() < 0)
            throw new ValidatorException("Id-ul primului user nu poate fi negativ!");
        if(entity.getId_user2() < 0)
            throw new ValidatorException("Id-ul celui de al doilea user nu poate fi negativ!");
        if(entity.getId_user1() == entity.getId_user2())
            throw new ValidatorException("Utilizatorii prieteniei sunt unul si acelasi!");
    }
}
