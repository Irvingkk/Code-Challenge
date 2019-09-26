import java.util.ArrayList;

/**
 * this is a plugin for Java, JavaScript, or C++ program
 */
public class JavaOrJSOrCPlusPlugin extends Plugin{
    // initialization
    public boolean SlashStar =false;
    public boolean DoubleSlash = false;

    JavaOrJSOrCPlusPlugin(){}

    @Override
    public void CountLine(String[][] textfile){
        for(String[] line: textfile){
            if(SlashStar == true) MultiComNum++; //current line is inside the block comment
            for(int i =0; i< line.length; ++i){
                String word = line[i];

                // "//" appear in this line at the first time
                if(word.contains("//") && DoubleSlash ==false){
                    // "TODO" appear in the beginning of comment
                    if((word.indexOf("//") + 2 == word.indexOf("TODO:") || word.endsWith("//TODO"))){
                        TODONum++;
                    }
                    DoubleSlash = true;
                    SingleComNum++;
                }
                // "TODO" appear in the middle of comment
                if(DoubleSlash && (word.startsWith("TODO:") || word == "TODO")){
                    TODONum++;
                }

                // indicate the beginning of a block comment
                if(word.contains("/*") || word.contains("/**")){
                    // only if /* or /** is not a part of block comment content, we add MultiComNum; Otherwise this line will be counted twice.
                    if(SlashStar == false) MultiComNum++;
                    SlashStar =true;
                }
                // indicate the ending of the block comment
                if(word.contains("*/") && SlashStar ==true) {
                    BlockNum++;
                    SlashStar = false;
                }

            }
            DoubleSlash = false; //reset the sign for the new line
        }
    }

}
