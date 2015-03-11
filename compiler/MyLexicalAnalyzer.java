package compiler;

import interfaces.LexicalAnalyzer;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author patthickey
 */
public class MyLexicalAnalyzer implements LexicalAnalyzer {


        char nextChar;        
        String nextToken = "";
        LexicalTokens token;
        int position = 0;        
        String currentLine;
        Scanner file;

    /**
     *
     * @param name
     * @throws FileNotFoundException
     */
    public MyLexicalAnalyzer(String name) throws FileNotFoundException
        {
            file = new Scanner(new File(name));
            currentLine = file.nextLine();
            while(file.hasNextLine())
            {
                currentLine = currentLine + file.nextLine();
            }
            nextChar = getCharacter();
        }
        
               
	@Override
	public String getNextToken() {
                nextToken = "";               
                
                while(isSpace(nextChar)){               
                    nextChar = getCharacter();                   
                }
                
                        
                if (!isHash(nextChar)){
                        while(!isHash(nextChar))
                        {
                            addCharacter();
                            nextChar = getCharacter();
                        }
                        //System.out.println("text only : " + nextToken);
                        return nextToken;                        
                }
                
                else {
                    while(!isSpace(nextChar)) 
                            {   
                                addCharacter();
                                nextChar = getCharacter();   
                            }
                    if(nextToken.equalsIgnoreCase("#GIMMEH") || nextToken.equalsIgnoreCase("#MAEK") || nextToken.equalsIgnoreCase("#I"))
                    {
                        addCharacter();
                        nextChar = getCharacter();
                            while(!isSpace(nextChar))
                            {
                                addCharacter();
                                nextChar = getCharacter();
                            }
                    }         
                    if(nextToken.equalsIgnoreCase("#I HAS"))
                    {
                        addCharacter();
                        nextChar = getCharacter();
                            while(!isSpace(nextChar))
                            {
                                addCharacter();
                                nextChar = getCharacter();
                            }
                    }

                    if(lookupToken(nextToken))
                    {   
                        //System.out.println("nextToken : " + nextToken);
                        return nextToken;
                    }
                    else{
                        System.err.println("SYNTAX ERROR: not a real token: " + nextToken);
			System.exit(0);
                    }                    
                    
                    
                }
                return "error";               
	}

	@Override
	public char getCharacter() {
            char temp = currentLine.charAt(position);
            position++;
            return temp;
	}

	@Override
	public void addCharacter() {             
               nextToken = nextToken + nextChar;
	}

	@Override
	public boolean isSpace(char c) {
                return ((c == ' ')||(c == '\t'));                                 
	}

    /**
     *
     * @param c
     * @return boolean
     */
    public boolean isHash(char c) {
                return(c == '#');                                 
	}

        
        @Override
	public boolean lookupToken(String nextToken) {
            return (nextToken.equalsIgnoreCase(LexicalTokens.DOC_BEGIN)) || (nextToken.equalsIgnoreCase(LexicalTokens.DOC_END)) || (nextToken.equalsIgnoreCase(LexicalTokens.COMMENT_BEGIN)) || (nextToken.equalsIgnoreCase(LexicalTokens.COMMENT_END)) ||
               (nextToken.equalsIgnoreCase(LexicalTokens.HEAD_BEGIN)) || (nextToken.equalsIgnoreCase(LexicalTokens.PARA_BEGIN)) || (nextToken.equalsIgnoreCase(LexicalTokens.LIST_BEGIN)) || (nextToken.equalsIgnoreCase(LexicalTokens.MAEK_END)) || 
               (nextToken.equalsIgnoreCase(LexicalTokens.TITLE_BEGIN)) || (nextToken.equalsIgnoreCase(LexicalTokens.BOLD_BEGIN)) || (nextToken.equalsIgnoreCase(LexicalTokens.ITALICS_BEGIN)) || (nextToken.equalsIgnoreCase(LexicalTokens.ITEM_BEGIN)) ||
               (nextToken.equalsIgnoreCase(LexicalTokens.SOUND_BEGIN)) || (nextToken.equalsIgnoreCase(LexicalTokens.VIDEO_BEGIN)) || (nextToken.equalsIgnoreCase(LexicalTokens.NEWLINE)) || (nextToken.equalsIgnoreCase(LexicalTokens.GIMMEH_END)) ||
               (nextToken.equalsIgnoreCase(LexicalTokens.VARIABLE_BEGIN)) || (nextToken.equalsIgnoreCase(LexicalTokens.VARIABLE_MID)) || (nextToken.equalsIgnoreCase(LexicalTokens.VARIABLE_CALL));
                    
	}       
        
    /**
     *
     * @return boolean
     */
    public boolean moreChar()
        {        
            return (currentLine.length() > position);
        }
        
    /**
     *
     */
    public void close()
        {
            file.close();
        }

}


