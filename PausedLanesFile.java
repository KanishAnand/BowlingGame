import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

public class PausedLanesFile implements Serializable {

    public static void addPausedLane(HashMap scores, Party party) throws IOException, ClassNotFoundException {
//        String cwd = new File("").getAbsolutePath();
        String filepath = "FLAG.DAT";
        FileInputStream fileIn = new FileInputStream(filepath);
        ObjectInputStream objectIn = new ObjectInputStream(fileIn);
        Vector<Boolean> storedFlagVector = (Vector<Boolean>) objectIn.readObject();
        objectIn.close();
        storedFlagVector.add(true);
        FileOutputStream fileOut = new FileOutputStream(filepath);
        ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
        objectOut.writeObject(storedFlagVector);
        objectOut.close();

        filepath = "PARTY.DAT";
        fileIn = new FileInputStream(filepath);
        objectIn = new ObjectInputStream(fileIn);
        Vector<Party> storedPartyVector = (Vector<Party>) objectIn.readObject();
        objectIn.close();
        storedPartyVector.add(party);
        fileOut = new FileOutputStream(filepath);
        objectOut = new ObjectOutputStream(fileOut);
        objectOut.writeObject(storedPartyVector);
        objectOut.close();

        filepath = "SCORES.DAT";
        fileIn = new FileInputStream(filepath);
        objectIn = new ObjectInputStream(fileIn);
        Vector<HashMap> storedScoresVector = (Vector<HashMap>) objectIn.readObject();
        objectIn.close();
        storedScoresVector.add(scores);
        fileOut = new FileOutputStream(filepath);
        objectOut = new ObjectOutputStream(fileOut);
        objectOut.writeObject(storedScoresVector);
        objectOut.close();
    }

    public static Vector<Boolean> readPausedLanesFlag() throws IOException, ClassNotFoundException {
        String filepath = "FLAG.DAT";
        FileInputStream fileIn = new FileInputStream(filepath);
        ObjectInputStream objectIn = new ObjectInputStream(fileIn);
        Vector<Boolean> storedFlagVector = (Vector<Boolean>) objectIn.readObject();
        objectIn.close();
        return storedFlagVector;
    }

    public static Vector<Party> readPausedLanesParty() throws IOException, ClassNotFoundException {
        String filepath = "PARTY.DAT";
        FileInputStream fileIn = new FileInputStream(filepath);
        ObjectInputStream objectIn = new ObjectInputStream(fileIn);
        Vector<Party> storedPartyVector = (Vector<Party>) objectIn.readObject();
        objectIn.close();
        return storedPartyVector;
    }

    public static Vector<HashMap> readPausedLanesHashMap() throws IOException, ClassNotFoundException {
        String filepath = "SCORES.DAT";
        FileInputStream fileIn = new FileInputStream(filepath);
        ObjectInputStream objectIn = new ObjectInputStream(fileIn);
        Vector<HashMap> storedScoresVector = (Vector<HashMap>) objectIn.readObject();
        objectIn.close();
        return storedScoresVector;
    }

    public static void resumePausedLane(int index) throws IOException, ClassNotFoundException {
        String filepath = "FLAG.DAT";
        FileInputStream fileIn = new FileInputStream(filepath);
        ObjectInputStream objectIn = new ObjectInputStream(fileIn);
        Vector<Boolean> storedFlagVector = (Vector<Boolean>) objectIn.readObject();
        storedFlagVector.set(index, false);
        objectIn.close();
    }



//        Iterator objIt = obj.iterator();
//        while (objIt.hasNext()) {
//            Party objj = (Party) objIt.next();
//            for (Object o: objj.getMembers())
//            {
//                Bowler a = (Bowler) o;
//                System.out.print(a.getNick() + " ");
//            }
//        }
//
//        for (HashMap o: obj){
//            Iterator hmIterator = o.entrySet().iterator();
//            while (hmIterator.hasNext()) {
//                Object nick = hmIterator.next();
//                int [] marks = (int []) obj.get(nick);
//                System.out.println(mapElement.getKey() + " : " + marks);
//            }
//            System.out.print(o);
//            System.out.print(" ");
//        }
//        System.out.println();
//        for (Object o : party.getMembers()) {
//            int [] scoresArray;
//            scoresArray = (int[]) scores.get(o);
//            for (int i=0; i<scoresArray.length; i++)
//            {
//                System.out.print(scoresArray[i]);
//                System.out.print(" ");
//            }
//            System.out.println();
//
//
//        }
//        Bowler bowler = (Bowler) bowlersIt.next();
//        System.out.println(bowler.getNick());


}

