package domain;

import java.util.Date;

public class Prietenie extends Entity<Integer>
{
    private int id_user1;
    private int id_user2;
    ///private Date date; eventual daca vrem sa adaugam si data

    /**
     * constructor cu parametrii pentru o relatie de prietenie
     * @param id_prietenie-Integer
     * @param id_user1-Integer
     * @param id_user2-Integer
     */
    public Prietenie(Integer id_prietenie, Integer id_user1, Integer id_user2)
    {
        super(id_prietenie);
        this.id_user1 = id_user1;
        this.id_user2 = id_user2;
    }
    /**
     @return id-ul primului utilizator
     */
    public int getId_user1() {
        return id_user1;
    }

    /**
     * @return id ul celui de al doilea user
     */
    public int getId_user2() {
        return id_user2;
    }
    @Override
    public String toString()
    {
        return "ID_pritenie:" + this.id + "; " + "ID_user1:" + this.id_user1 + "; " + "ID_user2:" + this.id_user2;
    }
}
