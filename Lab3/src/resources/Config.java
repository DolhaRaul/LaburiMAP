package resources;

import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.Properties;

public class Config {
    /**
     * clasa Config se va incarca cu datele in package ul "resources"(package ul unde ne aflam acum), cu datele din fisierul
     * "config.properties", deci path ul e "resources/config.properties"
     */
    public static final String CONFIG_LOCATION = Config.class.getClassLoader().
            getResource("resources/config.properties").getFile();

    /**
     * Obtinem proprietatile generale
     * @return Proprietatile clasei Config
     */
    public static Properties getProperties()
    {
        Properties properties = new Properties();
        try {
            properties.load(new FileReader(CONFIG_LOCATION));
        }
        catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Eroare la citirea fisierului de configurare!");

        }
        return properties;
    }
}
