package domain;

import java.io.Serializable;

public class Entity<ID> implements Serializable
{
    private final static long serialVersionUID = 11122L;
    protected ID id;

    public Entity(ID id)
    {
        this.id = id;
    }

    public ID getID() {
        return id;
    }

    public void setID(ID id) {
        this.id = id;
    }
}
