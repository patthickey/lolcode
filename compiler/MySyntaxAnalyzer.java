package compiler;

import interfaces.SyntaxAnalyzer;
import java.util.LinkedList;

/**
 *
 * @author patthickey
 */
public class MySyntaxAnalyzer implements SyntaxAnalyzer {

        String currentToken;
    
	@Override
        //#HAI head? body? #KTHXBYE
	public void lolcode(LinkedList code) {
		
                if(!MyCompiler.token.equalsIgnoreCase(LexicalTokens.DOC_BEGIN)) {
			System.err.println("SYNTAX ERROR: not correct token"
								+ MyCompiler.token + "when " + LexicalTokens.DOC_BEGIN
								+ " was expected.");
			System.exit(0);
		}
                code.add(new LexicalTokens(MyCompiler.token));
                
                
		currentToken = MyCompiler.lexer.getNextToken();	
                
                if (currentToken.equalsIgnoreCase(LexicalTokens.VARIABLE_BEGIN))
                {
                            define(code);
                            currentToken = MyCompiler.lexer.getNextToken(); 
                }
                
                if (currentToken.equalsIgnoreCase(LexicalTokens.COMMENT_BEGIN))
                {
                            comment(code);
                            currentToken = MyCompiler.lexer.getNextToken(); 
                }
                
		if(currentToken.equalsIgnoreCase(LexicalTokens.HEAD_BEGIN))
                {
			head(code);
                        currentToken = MyCompiler.lexer.getNextToken(); 
                }
		
                
                while(!currentToken.equalsIgnoreCase(LexicalTokens.DOC_END)){
                    
                    if(currentToken.equalsIgnoreCase(LexicalTokens.COMMENT_BEGIN))
                        comment(code);
                    else if (currentToken.equalsIgnoreCase(LexicalTokens.PARA_BEGIN))
                        paragraph(code);
                    else if (currentToken.equalsIgnoreCase(LexicalTokens.BOLD_BEGIN))
                        bold(code);
                    else if (currentToken.equalsIgnoreCase(LexicalTokens.ITALICS_BEGIN))
                        italics(code);
                    else if (currentToken.equalsIgnoreCase(LexicalTokens.LIST_BEGIN))
                        list(code);
                    else
                        innerText(code);               
                    currentToken = MyCompiler.lexer.getNextToken();
                }               
		if(!currentToken.equalsIgnoreCase(LexicalTokens.DOC_END)) {
			System.err.println("SYNTAX ERROR: not correct token"
								+ MyCompiler.token + "when " + LexicalTokens.DOC_END
								+ " was expected.");
			System.exit(0);
		}
                code.add(new LexicalTokens(currentToken));
                
                if(MyCompiler.lexer.moreChar())
                {
                   System.err.println("There is more after " + LexicalTokens.DOC_END);
                   System.exit(0);
                }
		//check to make sure there is nothing else after	
                        
	}

	@Override
	public void comment(LinkedList code) {

            code.add(new LexicalTokens(currentToken));
            
            currentToken = MyCompiler.lexer.getNextToken();
            code.add(new LexicalTokens(currentToken));
            
            currentToken = MyCompiler.lexer.getNextToken(); 
            if(!currentToken.equalsIgnoreCase(LexicalTokens.COMMENT_END)) {
		System.err.println("SYNTAX ERROR: not correct token '"
					+ currentToken + "' when " + LexicalTokens.COMMENT_END
					+ " was expected.");
			System.exit(0);
            }
            code.add(new LexicalTokens(currentToken));    
	}

	@Override
	public void head(LinkedList code) {
            code.add(new LexicalTokens(currentToken));
            
            currentToken = MyCompiler.lexer.getNextToken();
            if(!currentToken.equalsIgnoreCase(LexicalTokens.TITLE_BEGIN)) {
		System.err.println("SYNTAX ERROR: not correct token "
					+ currentToken + " when " + LexicalTokens.TITLE_BEGIN
					+ " was expected.");
		System.exit(0);
            }
            
            title(code);

            currentToken = MyCompiler.lexer.getNextToken();
            if(!currentToken.equalsIgnoreCase(LexicalTokens.MAEK_END)) {
		System.err.println("SYNTAX ERROR: not correct token"
					+ currentToken + "when " + LexicalTokens.MAEK_END
					+ " was expected.");
		System.exit(0);
            }
            code.add(new LexicalTokens(currentToken));
	}

	@Override
	public void title(LinkedList code) {
            code.add(new LexicalTokens(currentToken));
            
            currentToken = MyCompiler.lexer.getNextToken();
            code.add(new LexicalTokens(currentToken));
            
            currentToken = MyCompiler.lexer.getNextToken(); 
            if(!currentToken.equalsIgnoreCase(LexicalTokens.GIMMEH_END)) {
		System.err.println("SYNTAX ERROR: not correct token '"
					+ currentToken + "' when " + LexicalTokens.GIMMEH_END
					+ " was expected.");
		System.exit(0);
            }
            code.add(new LexicalTokens(currentToken));
	}

	@Override
	public void paragraph(LinkedList code) {
            code.add(new LexicalTokens(currentToken));
            currentToken = MyCompiler.lexer.getNextToken();
            
            if(currentToken.equalsIgnoreCase(LexicalTokens.VARIABLE_BEGIN))
                define(code);

            while(!currentToken.equalsIgnoreCase(LexicalTokens.MAEK_END)){

                if(currentToken.equalsIgnoreCase(LexicalTokens.NEWLINE))
                    code.add(new LexicalTokens(currentToken));
                else innerLP(code);
                currentToken = MyCompiler.lexer.getNextToken();                
            } 
            code.add(new LexicalTokens(currentToken));
	}

	@Override
	public void bold(LinkedList code) {
            code.add(new LexicalTokens(currentToken));
            
            currentToken = MyCompiler.lexer.getNextToken();
            
            if(currentToken.equalsIgnoreCase(LexicalTokens.VARIABLE_BEGIN))
                define(code);
            else innerText(code);
            
            currentToken = MyCompiler.lexer.getNextToken(); 
            if(!currentToken.equalsIgnoreCase(LexicalTokens.GIMMEH_END)) {
		System.err.println("SYNTAX ERROR: not correct token '"
					+ currentToken + "' when " + LexicalTokens.GIMMEH_END
					+ " was expected.");
		System.exit(0);
            }
            code.add(new LexicalTokens(currentToken));
	}

	@Override
	public void italics(LinkedList code) {
            code.add(new LexicalTokens(currentToken));
            
            currentToken = MyCompiler.lexer.getNextToken();
            
            if(currentToken.equalsIgnoreCase(LexicalTokens.VARIABLE_BEGIN))
                define(code);
            else innerText(code);
            
            currentToken = MyCompiler.lexer.getNextToken(); 
            if(!currentToken.equalsIgnoreCase(LexicalTokens.GIMMEH_END)) {
		System.err.println("SYNTAX ERROR: not correct token '"
					+ currentToken + "' when " + LexicalTokens.GIMMEH_END
					+ " was expected.");
		System.exit(0);
            }
            code.add(new LexicalTokens(currentToken));
	}

	@Override
	public void list(LinkedList code) {           
            code.add(new LexicalTokens(currentToken));
            currentToken = MyCompiler.lexer.getNextToken();           
            while(!currentToken.equalsIgnoreCase(LexicalTokens.MAEK_END)){

                if(!currentToken.equalsIgnoreCase(LexicalTokens.ITEM_BEGIN)) {
                    System.err.println("SYNTAX ERROR: not correct token '"
                                            + currentToken + "' when " + LexicalTokens.ITEM_BEGIN
                                            + " was expected.");
                    System.exit(0);
                }           
                item(code);
                currentToken = MyCompiler.lexer.getNextToken();                
            } 
            code.add(new LexicalTokens(currentToken));
	}

	@Override
	public void item(LinkedList code) {
            code.add(new LexicalTokens(currentToken));

            currentToken = MyCompiler.lexer.getNextToken();           
            while(!currentToken.equalsIgnoreCase(LexicalTokens.GIMMEH_END))
            {
                innerLP(code);
                currentToken = MyCompiler.lexer.getNextToken();
            }
            code.add(new LexicalTokens(currentToken));
	}

	@Override
	public void audio(LinkedList code) {
            code.add(new LexicalTokens(currentToken));
            
            currentToken = MyCompiler.lexer.getNextToken();
            code.add(new LexicalTokens(currentToken));
            
            currentToken = MyCompiler.lexer.getNextToken(); 
            if(!currentToken.equalsIgnoreCase(LexicalTokens.GIMMEH_END)) {
		System.err.println("SYNTAX ERROR: not correct token '"
					+ currentToken + "' when " + LexicalTokens.GIMMEH_END
					+ " was expected.");
		System.exit(0);
            }
            code.add(new LexicalTokens(currentToken));
	}

	@Override
	public void video(LinkedList code) {
            code.add(new LexicalTokens(currentToken));
            
            currentToken = MyCompiler.lexer.getNextToken();
            code.add(new LexicalTokens(currentToken));
            
            currentToken = MyCompiler.lexer.getNextToken(); 
            if(!currentToken.equalsIgnoreCase(LexicalTokens.GIMMEH_END)) {
		System.err.println("SYNTAX ERROR: not correct token '"
					+ currentToken + "' when " + LexicalTokens.GIMMEH_END
					+ " was expected.");
		System.exit(0);
            }
            code.add(new LexicalTokens(currentToken));
	}

	@Override
	public void define(LinkedList code) {
            code.add(new LexicalTokens(currentToken));
            
            currentToken = MyCompiler.lexer.getNextToken();
            code.add(new LexicalTokens(currentToken));
            
            currentToken = MyCompiler.lexer.getNextToken(); 
            if(!currentToken.equalsIgnoreCase(LexicalTokens.VARIABLE_MID)) {
		System.err.println("SYNTAX ERROR: not correct token '"
					+ currentToken + "' when " + LexicalTokens.VARIABLE_MID
					+ " was expected.");
		System.exit(0);
            }
            code.add(new LexicalTokens(currentToken));
            
            currentToken = MyCompiler.lexer.getNextToken();
            code.add(new LexicalTokens(currentToken));

            currentToken = MyCompiler.lexer.getNextToken();
            if(!currentToken.equalsIgnoreCase(LexicalTokens.GIMMEH_END)) {
		System.err.println("SYNTAX ERROR: not correct token '"
					+ currentToken + "' when " + LexicalTokens.GIMMEH_END
					+ " was expected.");
		System.exit(0);
            }
            code.add(new LexicalTokens(currentToken));
	}

	@Override
	public void use(LinkedList code) {
            code.add(new LexicalTokens(currentToken));
            
            currentToken = MyCompiler.lexer.getNextToken();
            code.add(new LexicalTokens(currentToken));
            
            currentToken = MyCompiler.lexer.getNextToken(); 
            if(!currentToken.equalsIgnoreCase(LexicalTokens.GIMMEH_END)) {
		System.err.println("SYNTAX ERROR: not correct token '"
					+ currentToken + "' when " + LexicalTokens.GIMMEH_END
					+ " was expected.");
		System.exit(0);
            }
            code.add(new LexicalTokens(currentToken));
	}

    /**
     *
     * @param code
     */
    public void innerLP(LinkedList code) {
            if(currentToken.equalsIgnoreCase(LexicalTokens.BOLD_BEGIN))
                bold(code);
            else if (currentToken.equalsIgnoreCase(LexicalTokens.ITALICS_BEGIN))
                italics(code);        
            else if (currentToken.equalsIgnoreCase(LexicalTokens.LIST_BEGIN))
                list(code);
            else innerText(code);  
        }
        
    /**
     *
     * @param code
     */
    public void innerText(LinkedList code){
            if(currentToken.equalsIgnoreCase(LexicalTokens.VARIABLE_CALL))
                use(code);
            else if (currentToken.equalsIgnoreCase(LexicalTokens.SOUND_BEGIN))
                audio(code);        
            else if (currentToken.equalsIgnoreCase(LexicalTokens.VIDEO_BEGIN))
                video(code);
            else if (currentToken.equalsIgnoreCase(LexicalTokens.NEWLINE))
                code.add(new LexicalTokens(currentToken));
            else //needs to varify that it is plain text
                code.add(new LexicalTokens(currentToken));
            //this could be where the error breaks
        }
        
        
}
