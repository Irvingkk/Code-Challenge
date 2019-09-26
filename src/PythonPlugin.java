/**
 * this is a plugin for Java, JavaScript, or C++ program
 */
public class PythonPlugin extends Plugin{
    boolean poundInCurLine = false;

    /*
    poundInFormerLine state:
    0: pound sign doesn't lead the formerline
    1: pound sign lead the formerline at the first time
    2: pound sign lead the formerline at two or more continuous time
     */
    int poundInFormerLine = 0;

    @Override
    public void CountLine(String[][] textfile) {
        for(String[] line: textfile){
            for(int i =0; i< line.length; ++i){
                String word = line[i];

                /* implement A DFA(Deterministic finite automaton) to validate the multi-line Comment and block Number
                 * check the DFA.pdf for details
                 */
                if(i == 0){
                    if(word.contains("#")){
                        if(poundInFormerLine == 0){
                            poundInFormerLine = 1;
                        } else if(poundInFormerLine ==1){
                            poundInFormerLine = 2;
                            MultiComNum +=2;
                            BlockNum++;
                        } else {
                            MultiComNum +=1;
                        }

                        // check if the comment contains TODO
                        if(word.contains("TODO") && ((word.indexOf("#") + 1 == word.indexOf("TODO:") || word.endsWith("#TODO")))){
                            TODONum++;
                        } else if(i != line.length -1 && (line[i+1] == "TODO" || line[i+1].indexOf("TODO:") == 0)) {
                            TODONum++;
                        }
                        break;
                    } else{
                        if(poundInFormerLine == 1){
                            poundInFormerLine =0;
                            SingleComNum++;
                        } else if(poundInFormerLine == 2){
                            poundInFormerLine = 0;
                        }
                    }
                }

                // "#" appear in this line at the first time
                if(word.contains("#") && !poundInCurLine){
                    // "TODO" appear in the beginning of comment
                    if((word.indexOf("#") + 1 == word.indexOf("TODO:") || word.endsWith("#TODO"))){
                        TODONum++;
                    }
                    poundInCurLine = true;
                    SingleComNum++;
                }
                // "TODO" appear in the middle of comment
                if(poundInCurLine && (word.startsWith("TODO:") || word == "TODO")){
                    TODONum++;
                }
            }
            poundInCurLine =false;
        }
    }
}
