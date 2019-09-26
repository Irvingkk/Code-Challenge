/**
 * Abstract plugin class inherited by all the child plugins
 */
public abstract class Plugin {
    // declaration
    public int BlockNum;
    public int SingleComNum;
    public int MultiComNum;
    public int TODONum;

    // method
    public abstract void CountLine(String[][] textfile);
    public void PrintResult(){
        System.out.println("Total # of comment lines: " + (SingleComNum+MultiComNum));
        System.out.println("Total # of single line comments: "+ SingleComNum);
        System.out.println("Total # of comment lines within block comments: " + MultiComNum);
        System.out.println("Total # of block line comments: "+ BlockNum);
        System.out.println("Total # of TODO’s: "+ TODONum);
    };
}
