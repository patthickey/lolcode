package compiler;

/**
 *
 * @author patthickey
 */
public class LexicalTokens {

	final static String DOC_BEGIN = "#HAI";
	final static String DOC_END = "#KTHXBYE";
	final static String COMMENT_BEGIN = "#OBTW";
	final static String COMMENT_END = "#TLDR";       
	final static String HEAD_BEGIN = "#MAEK HEAD";
        final static String PARA_BEGIN ="#MAEK PARAGRAF";
        final static String LIST_BEGIN = "#MAEK LIST";
    	final static String MAEK_END = "#OIC";      
        final static String TITLE_BEGIN = "#GIMMEH TITLE";
        final static String BOLD_BEGIN = "#GIMMEH BOLD";
        final static String ITALICS_BEGIN = "#GIMMEH ITALICS";        
        final static String ITEM_BEGIN = "#GIMMEH ITEM";       
        final static String SOUND_BEGIN = "#GIMMEH SOUNDZ";
        final static String VIDEO_BEGIN = "#GIMMEH VIDZ";
        final static String NEWLINE = "#GIMMEH NEWLINE";
        final static String GIMMEH_END = "#MKAY";          
        final static String VARIABLE_BEGIN = "#I HAS A";
        final static String VARIABLE_MID = "#ITZ";       
        final static String VARIABLE_CALL = "#VISIBLE";

        private String info;
        
    /**
     * 
     * @param info
     */
    public LexicalTokens(String info)
        {
            this.info = info;
        }
        
    /**
     * 
     * @return info of the token
     */
    public String getToken()
        {
            return info;
        }
}
