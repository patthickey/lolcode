package compiler;

import java.io.FileNotFoundException;
import java.io.OutputStreamWriter;
import java.io.FileOutputStream;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.LinkedList;

/**
 *
 * @author patthickey
 */
public class MyCompiler {

    /**
     *
     */
    public static String token;
	static MyLexicalAnalyzer lexer;
	static MySyntaxAnalyzer parser;
	static MySemanticAnalyzer generator;
        private static LinkedList<String> code = new LinkedList<String>();
        private static LinkedList codeT = new LinkedList();
        
    /**
     *
     * @param args
     */
    public static void main(String[] args) {
			
		token = "";
                String fileName = args[0];

		//checks if there is only one 1 file
		if(args.length != 1) {
			System.err.println("ERROR: more than one file found");
			System.exit(0);			
		}
                
                if(!fileName.endsWith(".lol"))
                {
			System.err.println("ERROR: file is not in '.lol' format");
			System.exit(0);	                    
                }
                
                
                
		try{	
                    lexer = new MyLexicalAnalyzer(fileName);
                }
                catch(FileNotFoundException ex) 
                {
                    System.out.println("File not found.");	
                }
                
                
		token = lexer.getNextToken();

		parser = new MySyntaxAnalyzer();
		parser.lolcode(code);

		generator = new MySemanticAnalyzer(); // still need to add this
		codeT = generator.translate(code);


                Writer writer = null;
                try 
                {
                    writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream("output.html"), "utf-8"));
                    printCode(writer, codeT);
                } catch (IOException ex) {
                    System.out.println("Error printing file");
                } finally {
                   try {writer.close();} catch (Exception ex) {}
                }
                
                lexer.close();
		
	}
         
    /**
     *
     * @param writer
     * @param code
     * @throws IOException
     */
    public static void printCode(Writer writer, LinkedList code) throws IOException
    {   
        String head;
        for(int i=0; i<code.size(); i++)
        {
            head = (String)code.get(i);
            //System.out.println(head);
            writer.write(head + "\n");            
        }
    }
        
}
