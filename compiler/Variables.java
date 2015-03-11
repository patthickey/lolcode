
package compiler;

 // @author patthickey

/**
 *
 * @author patthickey
 */
 
public class Variables {
    
    String name;
    String info;
    
    /**
     *
     * @param name
     * @param info
     */
    public Variables(String name, String info)
    {
        this.name = name;
        this.info = info;
    }
    
    /**
     *
     * @return
     */
    public String getName()
    {
        return name;
    }

    /**
     *
     * @return
     */
    public String getInfo()
    {
        return info;
    }        
}
