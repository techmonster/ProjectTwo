import java.util.ArrayList;

/**
 * Created by Nate Holloway on 3/20/2016.
 * This program will take three different approaches
 * to adding and deleting data.
 */
public class TestProjectTwo {

    public static void main(String[] args) {
        String artistFile = "p1artists.txt";
        String changesFile = "p2changes.txt";
        int methodOne = 1;
        int methodTwo = 2;
        int methodThree = 3;

        ArrayList<Artist> artistTable = new ArrayList<Artist>();

        CreateFile fileA = new CreateFile();
        CreateFile fileB = new CreateFile();
        CreateFile fileC = new CreateFile();

        fileA.openFile("p2artists2a.txt");
        fileB.openFile("p2artists2b.txt");
        fileC.openFile("p2artists2c.txt");

        LineReader reader = new LineReader(artistFile);
        LineReader reader2 = new LineReader(changesFile);

        //***THE READER IS CLOSED DURING THIS METHOD***//
        long startTime = System.nanoTime();
        readFile(reader, artistTable, methodOne);
        readFile(reader2, artistTable, methodOne);
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);

        //**********CREATE FILE A*************
        makeFile(artistTable, fileA);
        fileA.addRecords("\n"+"Duration of method one: " + duration + " nano seconds.");
        fileA.closeFile();
        //********FILE A IS CLOSED************

        //Clear the array
        artistTable.clear();

        LineReader reader3 = new LineReader(artistFile);
        LineReader reader4 = new LineReader(changesFile);

        //***THE READER IS CLOSED DURING THIS METHOD***//
        startTime = System.nanoTime();
        readFile(reader3, artistTable, methodTwo);
        readFile(reader4, artistTable, methodTwo);
        endTime = System.nanoTime();
        duration = (endTime - startTime);


        //**********CREATE FILE B*************
        makeFile(artistTable, fileB);
        fileB.addRecords("\n"+"Duration of method two: " + duration + " nano seconds.");
        fileB.closeFile();
        //********FILE B IS CLOSED************

        //Clear the array
        artistTable.clear();

        LineReader reader5 = new LineReader(artistFile);
        LineReader reader6 = new LineReader(changesFile);

        //***THE READER IS CLOSED DURING THIS METHOD***//
        startTime = System.nanoTime();
        readFile(reader5, artistTable, methodThree);
        readFile(reader6, artistTable, methodThree);
        endTime = System.nanoTime();
        duration = (endTime-startTime);

        //**********CREATE FILE C*************
        makeFile(artistTable, fileC);
        fileC.addRecords("\n"+"Duration of method three: " + duration + " nano seconds.");
        fileC.closeFile();
        //********FILE C IS CLOSED************








    }


    public static void readFile(LineReader reader, ArrayList<Artist> artistTable,int method){

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
                        if(method == 1) {
                            catalog = new Artist(ID, value);
                        } else if(method == 2){
                            catalog = new Artist(ID, value, false);
                        } else {
                            next = (ID+1);
                            catalog = new Artist(ID, value, next);
                        }
                        artistTable.add(catalog);
                        index++;
                       // System.out.println("Artist Added: " + catalog.toString());
                    } else if (firstEntry.equalsIgnoreCase("d")) {  //**delete section
                            ID = Integer.valueOf(value);
                            position = (ID - 1);
                        if(method == 1) {
                            artistTable.remove(position - deletions);

                            deletions++;
                        } else if(method == 2){
                            artistTable.get(position).setDelete(true);
                        } else {
                            next = (ID +1);
                            artistTable.get(position-1).setNext(next);
                            artistTable.get(position).setNext(0);
                        }
                    }


                }
            //this block handles the creation of the artist objects
            else {
                try {
                    //convert the string to an integer
                    ID = (Integer.valueOf(firstEntry));
                    String artistName = splitArray[1];

                    if (method == 1) {
                        //create a new Artist object
                        catalog = createArtist(ID,artistName);

                    }else if (method == 2){
                        catalog = createArtist(ID, artistName, false);

                    } else {
                        next = (ID + 1);
                        catalog = createArtist(ID,artistName, next);

                    }

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

    private static Artist createArtist(int ID, String name, Boolean delete){

        return(new Artist(ID, name, delete));

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

            aFile.addRecords(records);

        }

    }
}
