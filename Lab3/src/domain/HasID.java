package domain;

///metodele implicite pentru o clasa care are ID
public interface HasID<ID>
{
    ID getID();
    void setID(ID id);
}
