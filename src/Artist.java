/**
 * Created by Nate Holloway on 3/19/2016.
 * This is the Artist class for project 2.
 */
public class Artist {
    private int artistID;
    private String artistName;
    private Boolean delete;
    private int next;


    public Artist(int artistID, String artistName) {

        this.artistID = artistID;
        this.artistName = artistName;
    }

    public Artist(int artistID, String artistName, int nextPosition) {
        setID(artistID);
        setName(artistName);
        setNext(nextPosition);
    }

    public Artist(int artistID, String artistName, Boolean delete) {
        setID(artistID);
        setName(artistName);
        setDelete(delete);
    }

    public int getID() {
        return artistID;
    }

    public void setID(int artistID) {
        this.artistID = artistID;
    }

    public String getName() {
        return artistName;
    }

    public void setName(String artistName) {
        this.artistName = artistName;
    }

    public String getNameById(int artistID) {
        return this.artistName;
    }

    //@Override
    public String toString() {

            return getID() + "\t" + getName() + "\t" + getDelete() + "\t" + getNext();

    }


    public Boolean getDelete() {
        return delete;
    }

    public void setDelete(Boolean delete) {
        this.delete = delete;
    }

    public int getNext() {
        return next;
    }

    public void setNext(int next) {
        this.next = next;
    }

}


