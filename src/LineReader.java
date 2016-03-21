import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by delog on 3/19/2016.
 * This class will read a file given the file path.
 */
public class LineReader {

    BufferedReader bufferedReader;

    LineReader(String file){
        FileReader reader;
        try{reader = new FileReader(file);
            bufferedReader = new BufferedReader(reader);
        }
        catch (FileNotFoundException e){
            System.err.println("LineReader can't find input file: "+ file);
            e.printStackTrace();
        }

    }

    String readLine(){
        try {
            return bufferedReader.readLine();
        }
        catch (IOException e){
            e.printStackTrace();
        }

        return null;
    }

    void close(){
        try {
            bufferedReader.close();
        }
        catch (IOException e){
            e.printStackTrace();

        }

    }
}
