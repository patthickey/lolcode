package compiler;

import java.util.LinkedList;

/**
 *
 * @author patthickey
 */
public class MySemanticAnalyzer {

    LinkedList<String> translate = new LinkedList<String>();
    LinkedList assigned = new LinkedList();
    int count=0;
    LexicalTokens currentToken;
    int i;
    
    /**
     *
     * @param code
     * @return translate
     */
    public LinkedList translate(LinkedList code)
    {

        for(i=0; i<code.size(); i++)
        {
            currentToken = (LexicalTokens)code.get(i);

            if(currentToken.getToken().equalsIgnoreCase(LexicalTokens.DOC_BEGIN))
                translate.add("<html>");
            else if(currentToken.getToken().equalsIgnoreCase(LexicalTokens.DOC_END))
                translate.add("</html>");            
            else if(currentToken.getToken().equalsIgnoreCase(LexicalTokens.COMMENT_BEGIN))
                comment(code);
            else if(currentToken.getToken().equalsIgnoreCase(LexicalTokens.HEAD_BEGIN))
                head(code);
            else if(currentToken.getToken().equalsIgnoreCase(LexicalTokens.VARIABLE_BEGIN))
                variable(code);              
            else if(currentToken.getToken().equalsIgnoreCase(LexicalTokens.PARA_BEGIN))
                paragraph(code);              
            else if(currentToken.getToken().equalsIgnoreCase(LexicalTokens.LIST_BEGIN))
                list(code);                 
            else if(currentToken.getToken().equalsIgnoreCase(LexicalTokens.BOLD_BEGIN))
                bold(code);            
            else if(currentToken.getToken().equalsIgnoreCase(LexicalTokens.ITALICS_BEGIN))
                italics(code);                          
            else if(currentToken.getToken().equalsIgnoreCase(LexicalTokens.SOUND_BEGIN))
                sound(code);              
            else if(currentToken.getToken().equalsIgnoreCase(LexicalTokens.VIDEO_BEGIN))
                video(code);               
            else if(currentToken.getToken().equalsIgnoreCase(LexicalTokens.NEWLINE))
                newLine(code);          
        }
        return translate;
    }
    
    /**
     *
     * @param code
     */
    public void comment(LinkedList code)
    {
        translate.add("<!--"); 
        i++;
        currentToken = (LexicalTokens)code.get(i);
        translate.add(currentToken.getToken());
        i++;        
        translate.add("-->"); 
        //i++;
    }
    
    /**
     *
     * @param code
     */
    public void head(LinkedList code) 
    {
        translate.add("<head>");
        i++;
        translate.add("<title>");
        i++;        
        currentToken = (LexicalTokens)code.get(i);
        translate.add(currentToken.getToken());
        i++;          
        translate.add("</title>");
        i++;
        translate.add("</head>");
        //i++;                     
    }           
    
    /**
     *
     * @param code
     */
    public void bold(LinkedList code)
    {
        translate.add("<b>");
        i++;        
        currentToken = (LexicalTokens)code.get(i);
        translate.add(currentToken.getToken());
        i++;          
        translate.add("</b>");
        //i++;        
    }
    
    /**
     *
     * @param code
     */
    public void italics(LinkedList code)
    {
        translate.add("<b>");
        i++;        
        currentToken = (LexicalTokens)code.get(i);
        translate.add(currentToken.getToken());
        i++;          
        translate.add("</b>");
        //i++;        
    }    
    
    /**
     *
     * @param code
     */
    public void sound(LinkedList code)
    {
        translate.add("<audio controls>"); 
        translate.add("<source src=\""); 
        i++;        
        currentToken = (LexicalTokens)code.get(i);
        translate.add(currentToken.getToken());
        i++;          
        translate.add("\">");
        i++;
        translate.add("</audio>");         
    }    

    /**
     *
     * @param code
     */
    public void video(LinkedList code)
    {
        translate.add("<iframe src=\""); 
        i++;        
        currentToken = (LexicalTokens)code.get(i);
        translate.add(currentToken.getToken());
        i++;          
        translate.add("\"/>");
        //i++;        
    }     

    /**
     *
     * @param code
     */
    public void newLine(LinkedList code)
    {
        translate.add("<br>"); 
        //i++;          
    }
    
    /**
     *
     * @param code
     */
    public void paragraph(LinkedList code)
    {
        translate.add("<p>"); 
        i++;          
        currentToken = (LexicalTokens)code.get(i);
        
        if(currentToken.getToken().equalsIgnoreCase(LexicalTokens.VARIABLE_BEGIN))
        {
            variable(code);
            i++;
            currentToken = (LexicalTokens)code.get(i);
            
        }
     
        while(!currentToken.getToken().equalsIgnoreCase(LexicalTokens.MAEK_END))
        {
                if(currentToken.getToken().equalsIgnoreCase(LexicalTokens.NEWLINE))
                {
                    newLine(code);
                }
                else 
                {
                    innerLP(code);
                }
                
                i++;                
                currentToken = (LexicalTokens)code.get(i);

        }       
        translate.add("</p>"); 
        //i++;              
    }
    
    /**
     *
     * @param code
     */
    public void list(LinkedList code)
    {
        translate.add("<ul>"); 
        i++;
        currentToken = (LexicalTokens)code.get(i);
        while(!currentToken.getToken().equalsIgnoreCase(LexicalTokens.MAEK_END))
        {
            item(code);
            i++;
            currentToken = (LexicalTokens)code.get(i);
        }
        translate.add("</ul>");
        //i++;
    }
    
    /**
     *
     * @param code
     */
    public void item(LinkedList code)
    {
        //needs to bed fixed for two innerLP in the same item
        translate.add("<li>"); 
        i++;
        
        
        //this is the part that sorta works
        currentToken = (LexicalTokens)code.get(i);
        //System.out.println(currentToken.getToken());
        innerLP(code);
        i++;
        //this is the part that sorta works 
        

        /*
        //added below    
        currentToken = (LexicalTokens)code.get(i);
        while(!currentToken.getToken().equalsIgnoreCase(LexicalTokens.MAEK_END))
        {
            innerLP(code);
            i++;
            currentToken = (LexicalTokens)code.get(i);
        }
        //added above 
        */
        
        translate.add("</li>");               
    }
    
    /**
     *
     * @param code
     */
    public void variable(LinkedList code)
    {
        LexicalTokens name;
        LexicalTokens info;
        i++;
        name = (LexicalTokens)code.get(i);
        i++;
        i++;
        info = (LexicalTokens)code.get(i);     
        i++;
        assigned.add(new Variables(name.getToken(), info.getToken()));
        count++;
        //i++;
    }
    
    /**
     *
     * @param code
     */
    public void use(LinkedList code)
    {
        i++;
        currentToken = (LexicalTokens)code.get(i);
        Variables temp;
        boolean test = false;    
        
        for(int j = 0; j < assigned.size(); j++)
        {
            temp = (Variables) assigned.get(j);
            if(currentToken.getToken().equals(temp.getName()))
            {
                translate.add(temp.getInfo());
                test = true;
            }
        }
        
        if(test == false)
        {
            System.err.println("SEMANTIC ERROR: variable not found : "
                                + currentToken.getToken());
            System.exit(0);
        }
        
        i++;
    }
    
    /**
     *
     * @param code
     */
    public void innerLP(LinkedList code) {
        if(currentToken.getToken().equalsIgnoreCase(LexicalTokens.BOLD_BEGIN))
            bold(code);
        else if (currentToken.getToken().equalsIgnoreCase(LexicalTokens.ITALICS_BEGIN))
            italics(code);        
        else if (currentToken.getToken().equalsIgnoreCase(LexicalTokens.LIST_BEGIN))
            list(code);
        else innerText(code);  
        }
    
    /**
     *
     * @param code
     */
    public void innerText(LinkedList code){
        if(currentToken.getToken().equalsIgnoreCase(LexicalTokens.VARIABLE_CALL))
            use(code);
        else if (currentToken.getToken().equalsIgnoreCase(LexicalTokens.SOUND_BEGIN))
            sound(code);        
        else if (currentToken.getToken().equalsIgnoreCase(LexicalTokens.VIDEO_BEGIN))
            video(code);
        else if (currentToken.getToken().equalsIgnoreCase(LexicalTokens.NEWLINE))
            newLine(code);
        else 
        {
            translate.add(currentToken.getToken());
            //i++;
        }
            
        }
    
    
    
    
}
