import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class CountLines {
    private static String filename;
    private static int lineNum = 0;
    private static String[][] TextFile = null;
    private static String extension;

    CountLines(){}

    public static void readFile(){
        ArrayList<ArrayList<String>> textfile = new ArrayList<>();
        Scanner scanner = null;
        int lineCounter = 0;
        File file = null;

        try {
            file = new File("InputFile/"+filename);
            scanner = new Scanner(file);
        } catch(FileNotFoundException e){
            e.printStackTrace();
        }

        // find the extension of input file
        int index = file.getPath().lastIndexOf(".");
        if(index > 0){
            extension = file.getPath().substring(index+1);
        } else {
            System.err.println("no extension file, reject!");
            System.exit(1);
        }

        // scan the whole text file; then convert it to a ArrayList of ArrayList of words(String). Words are seperated by space.
        while(scanner.hasNextLine()){
            lineCounter++;
            String line = scanner.nextLine();
            Scanner lineScanner = new Scanner(line);
            ArrayList<String> lineArray = new ArrayList<>();

            // when this line is empty
            if (!lineScanner.hasNext()){
                lineArray.add("");
                textfile.add(lineArray);
                continue;
            }
            while(lineScanner.hasNext()){
                String word = lineScanner.next();
                lineArray.add(word);
            }
            textfile.add(lineArray);
        }

        // convert the ArrayList of ArrayList to the array of array
        TextFile = new String[textfile.size()][];
        for(int i =0; i< textfile.size(); ++i){
            ArrayList<String> row = textfile.get(i);
            TextFile[i] = row.toArray(new String[row.size()]);
        }

        lineNum = lineCounter;
    }



    public static void main(String[] args){
        // validate the argements
        if(args.length != 1){
            System.err.println("the number of the input filename is not 1");
            System.exit(1);
        }
        filename = args[0];

        // read input file
        readFile();

        // apply plugin of different language
        Plugin plugin;
        switch (extension){
            case "py":
                plugin = new PythonPlugin();
                break;
            case "java":
                plugin = new JavaOrJSOrCPlusPlugin();
                break;
            case "cc":
                plugin = new JavaOrJSOrCPlusPlugin();
                break;
            case "cpp":
                plugin = new JavaOrJSOrCPlusPlugin();
                break;
            case "js":
                plugin = new JavaOrJSOrCPlusPlugin();
                break;
            default:
                plugin = null;
                System.err.println("Can't handle this file type");
                System.exit(1);
        }
        plugin.CountLine(TextFile);

        //print results
        System.out.println("Total # of lines: " + lineNum);
        plugin.PrintResult();
    }
}
