package repo;

import domain.Prietenie;
import domain.validators.EntityIsNull;
import domain.validators.Validator;
import domain.validators.ValidatorException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class PrietenieFileRepository extends AbstractRepo<Integer, Prietenie>
{
    private String filename;

    /**
     * constructor cu parametrii
     * @param validator-Validator
     * @param filename-String
     */
    public PrietenieFileRepository(Validator<Prietenie> validator, String filename) throws ValidatorException, EntityIsNull {
        super(validator);
        this.filename = filename;
        load();
    }

    /**
     * incarcam toate datele datele din fisierul "filename" in colectiile noastre
     * @throws ValidatorException-aruncam exceptie cand fisierul "este corupt"(nu putem extrage datele corespunzator)
     */
    public void load() throws ValidatorException, EntityIsNull {
        try {
            Path path = Paths.get(filename);
            List<String> lines = Files.readAllLines(path);///
            for (String linie : lines) {
                String[] words = linie.split(";");
                if (words.length != 3)///nu avem 3 string uri, unul corespunzator pentru fiecare data membra
                    throw new IOException("Fisierul este corupt!");
                /*
                Daca primim in fisier un sir de forma, de ex:3;3;4 4, atunci vom avea 3 string uri, dar ultimul va pusca
                pe metoda Integer.parseint() si nu avem ce ii face
                 */
                Prietenie relationship = new Prietenie(Integer.parseInt(words[0]), Integer.parseInt(words[1]),
                        Integer.parseInt(words[2]));
                super.save(relationship);///salvam entitatea in buffer ul din PrietenieFileRepository(care e de tip HashMap)
            }
        }
        catch(IOException e)
        {
            System.err.println("Eroare la citirea fisierului!!!");
        }
    }
}
