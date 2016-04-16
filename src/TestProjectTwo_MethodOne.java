import java.util.ArrayList;

/**
 * Created by Nate Holloway on 3/20/2016.
 * This program will test method one of
 * adding and deleting data.
 */
public class TestProjectTwo_MethodOne {

    public static void main(String[] args) {
        String artistFile = "p1artists.txt";
        String changesFile = "p2changes.txt";


        ArrayList<Artist> artistTable = new ArrayList<Artist>();

        CreateFile fileA = new CreateFile();

        fileA.openFile("p2artists2a.txt");

        LineReader reader = new LineReader(artistFile);
        LineReader reader2 = new LineReader(changesFile);

        //***THE READER IS CLOSED DURING THIS METHOD***//
        long startTime = System.nanoTime();
        readFile(reader, artistTable);
        readFile(reader2, artistTable);
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);

        //**********CREATE FILE A*************
        makeFile(artistTable, fileA);
        fileA.addRecords("\n"+"Duration of method: " + duration + " nano seconds.");
        fileA.closeFile();
        //********FILE A IS CLOSED************

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
                        catalog = new Artist(ID, value);
                        artistTable.add(catalog);
                        index++;
                       // System.out.println("Artist Added: " + catalog.toString());
                    } else if (firstEntry.equalsIgnoreCase("d")) {  //**delete section
                            ID = Integer.valueOf(value);
                            position = (ID - 1);
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
                        //create a new Artist object
                        catalog = createArtist(ID,artistName);
                    artistTable.add(catalog);
                } catch (NumberFormatException ex) {
                    ex.printStackTrace();
                }

            }
        }

        //close reader
        reader.close();
    }


    private static Artist createArtist(int ID, String name){

        return( new Artist(ID, name));

    }

    private static void makeFile(ArrayList<Artist> aList, CreateFile aFile)
    {
        String records;

        Object[] listArray = aList.toArray();
        for (Object aListArray : listArray) {
            records = (aListArray.toString()+ "\n");

            aFile.addRecords(records);

        }

    }
}
