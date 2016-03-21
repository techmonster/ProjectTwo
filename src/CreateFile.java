import java.util.Formatter;

/**
 * Created by delog on 3/19/2016.
 * This class will create a file.
 */
public class CreateFile {

    private Formatter file;


    //creates a file accepting a string representation of the path
    public void openFile(String filePath){
        try {
            file = new Formatter(filePath);
        }
        catch (Exception e){
            System.out.println("An error has occurred.");
        }
    }

    public void addRecords(String record){
        file.format("%S", record);
    }

    public void closeFile(){
        file.close();
    }
}

