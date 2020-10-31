package br.com.hkp.showemojis;

import java.io.IOException;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/******************************************************************************
 * Um objeto desta classe coleta todas as tags que inserem emojis em um arquivo
 * HTML de uma pagina salva do WhatsApp Web.
 * 
 * 
 * @author "Pedro Reis"
 * @since 31 de outubro de 2020 v1.0
 * @version v1.0
 *****************************************************************************/
public class CollectTags
{
    private final HashSet<String> tags;
    private final StringBuffer htmlContent;
     
    /*[00]---------------------------------------------------------------------
    
    -------------------------------------------------------------------------*/
    public CollectTags(final String fileName) throws IOException
    {

        HtmlFileHandler htmlFileHandler = new HtmlFileHandler(fileName);
        htmlContent = htmlFileHandler.readFile();
        Pattern pattern = 
            Pattern.compile
            (
                "<img src=\"d5fceb6532643d0d84ffe09c40c481ecdf59e15a\\.gif.+?>"
            );
        Matcher matcher = pattern.matcher(htmlContent);
        
        tags = new HashSet<String>();
        
        while (matcher.find()) tags.add(matcher.group());
   
    }//construtor
    
    /*[00]---------------------------------------------------------------------
    
    -------------------------------------------------------------------------*/
    public HashSet<String> getSetOfTags()
    {
        System.out.println("rodou");
        return tags;
    }//getSetOfTags()
    
    public static void main(String[] args)
    {
        try
        {
            CollectTags c = new CollectTags("index.html");
            for (String tag : c.getSetOfTags())
                System.out.println(tag);
        }
        catch (IOException ex)
        {
           System.err.println(ex);
        }
    }
    
}//classe CollectTags
