package br.com.hkp.showemojis.apps;

import br.com.hkp.showemojis.normalizefilenames.NormalizeFilenames;
import java.io.File;

/******************************************************************************
 *
 * @author "Pedro Reis"
 * @since 29 de outubro de 2020 v1.0
 * @version v1.0
 *****************************************************************************/

public class Normalize
{

   /*[00]---------------------------------------------------------------------
    
    -------------------------------------------------------------------------*/
    /**
     * 
     * @param args n/a
     */
    public static void main(String[] args)
    {
        NormalizeFilenames n = new NormalizeFilenames();
        
        File dir = n.getDir();
        
        if (dir == null) System.exit(0);
        
        n.normalize(dir);
         
    }//main()
    
}//Aplicacao Normalize
