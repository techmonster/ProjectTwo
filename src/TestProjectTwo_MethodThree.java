import java.util.ArrayList;

/**
 * Created by Nate Holloway on 4/14/2016.
 * This program will test method three of
 * adding and deleting data.
 */
public class TestProjectTwo_MethodThree {

    public static void main(String[] args) {

    String artistFile = "p1artists.txt";
    String changesFile = "p2changes.txt";

    ArrayList<Artist> artistTable = new ArrayList<Artist>();

    CreateFile fileC = new CreateFile();


    fileC.openFile("p2artists2c.txt");


    LineReader reader5 = new LineReader(artistFile);
    LineReader reader6 = new LineReader(changesFile);

    //***THE READER IS CLOSED DURING THIS METHOD***//
    long startTime = System.nanoTime();
    readFile(reader5, artistTable);
    readFile(reader6, artistTable);
    long endTime = System.nanoTime();
    long duration = (endTime-startTime);

    //**********CREATE FILE C*************
    makeFile(artistTable, fileC);
    fileC.addRecords("\n"+"Duration of method three: " + duration + " nano seconds.");
    fileC.closeFile();
    //********FILE C IS CLOSED************








}


    public static void readFile(LineReader reader, ArrayList<Artist> artistTable){

        Artist catalog;
        String line;
        int index;
        index = (artistTable.size());
        int ID;
        int position;
        int next;
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
                    next = (ID + 1);
                    catalog = new Artist(ID, value, next);
                    artistTable.add(catalog);
                    index++;
                }
                // System.out.println("Artist Added: " + catalog.toString());
                else if (firstEntry.equalsIgnoreCase("d")) {  //**delete section
                    ID = Integer.valueOf(value);
                    position = (ID - 1);
                    next = (ID + 1);
                    artistTable.get(position - 1).setNext(next);
                    artistTable.get(position).setNext(0);
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
                    next = (ID + 1);
                    catalog = createArtist(ID,artistName, next); //create object with hasNext field
                    artistTable.add(catalog);

                } catch (NumberFormatException ex) {
                    ex.printStackTrace();
                }

            }
        }

        //close reader
        reader.close();


                }


    private static Artist createArtist(int ID, String name, int next){

        return(new Artist(ID, name, next));

    }

    private static void makeFile(ArrayList<Artist> aList, CreateFile aFile)
    {
        String records;

        Object[] listArray = aList.toArray();
        for (Object aListArray : listArray) {
            records = (aListArray.toString()+ " \n");

            System.out.print(records);
            aFile.addRecords(records);

        }

    }
}
