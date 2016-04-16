import java.util.ArrayList;

/**
 * Created by Nate Holloway on 4/14/2016.
 * This program will test method two of
 * adding and deleting data. *
 */
public class TestProjectTwo_MethodTwo {

    public static void main(String[] args) {

    String artistFile = "p1artists.txt";
    String changesFile = "p2changes.txt";

    ArrayList<Artist> artistTable = new ArrayList<Artist>();


    CreateFile fileB = new CreateFile();



    fileB.openFile("p2artists2b.txt");




    LineReader reader3 = new LineReader(artistFile);
    LineReader reader4 = new LineReader(changesFile);

    //***THE READER IS CLOSED DURING THIS METHOD***//
    long startTime = System.nanoTime();
    readFile(reader3, artistTable);       //create the Artist objects
    readFile(reader4, artistTable);       //add or delete objects
    long endTime = System.nanoTime();
    long duration = (endTime - startTime);


    //**********CREATE FILE B*************
    makeFile(artistTable, fileB);
    fileB.addRecords("\n"+"Duration of method two: " + duration + " nano seconds.");
    fileB.closeFile();
    //********FILE B IS CLOSED************

}


    public static void readFile(LineReader reader, ArrayList<Artist> artistTable){

        Artist catalog;
        String line;
        int index = (artistTable.size());
        int ID;
        int position;
        int deletions = 0;

        while ((line = reader.readLine()) != null) {
            //split each line at the tabbed space and place in and array
            String splitArray[] = line.split("\t");
            String firstEntry = splitArray[0];

            //this block handles the changes
            if (firstEntry.equalsIgnoreCase("a") || firstEntry.equalsIgnoreCase("D")) {
                String value = splitArray[1];

                //firstEntry is the action, either add or delete
                if (firstEntry.equalsIgnoreCase("a")) {         //***add section
                    ID = index + 1;
                    catalog = new Artist(ID, value, false);
                    artistTable.add(catalog);
                    index++;
                }

             else if (firstEntry.equalsIgnoreCase("d")) {  //**delete section
                    ID = Integer.valueOf(value);
                    position = (ID - 1);

                    artistTable.get(position).setDelete(true);
                    artistTable.remove(position - deletions);
                    deletions++;
                }
        }
            //this block handles the creation of the artist objects
            else {
                try {
                    //convert the string to an integer
                    ID = (Integer.valueOf(firstEntry));
                    String artistName = splitArray[1];
                    catalog = createArtist(ID, artistName, false); //create object with delete field
                    artistTable.add(catalog);

                } catch (NumberFormatException ex) {
                    ex.printStackTrace();
                }

            }
        }

        //close reader
        reader.close();



    }



    private static Artist createArtist(int ID, String name, Boolean delete){

        return(new Artist(ID, name, delete));

    }


    private static void makeFile(ArrayList<Artist> aList, CreateFile aFile)
    {
        String records;

        for (Object artist : aList) {
                records = (artist.toString() + "\n");
                System.out.print(records);
                aFile.addRecords(records);

        }

    }
}
