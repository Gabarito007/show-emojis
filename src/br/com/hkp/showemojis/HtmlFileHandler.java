package br.com.hkp.showemojis;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;


/******************************************************************************
 * A finalidade desta classe eh fornecer meios para a aplicacao ler um arquivo
 * HTML para uma StringBuffer, obter este objeto StringBuffer para edicao por 
 * metodos de outras classes e poder gravar o objeto StringBuffer jah editado
 * em um novo arquivo HTML que sera nomeado de acordo com o nome do arquivo 
 * que foi originalmente lido para o objeto StringBuffer.
 * 
 * 
 * @author "Pedro Reis"
 * @since 31 de outubro de 2020 v1.0
 * @version v1.0
 *****************************************************************************/
public final class HtmlFileHandler
{
    private final int buffer;
    private final StringBuffer stringBuffer;
    private final File inputFile;
    private final File outputFile;
    
    /*[00]---------------------------------------------------------------------
    
    -------------------------------------------------------------------------*/
    /**
     * 
     * @param fileName 
     */
    public HtmlFileHandler(final String fileName)
    {
        inputFile = new File(fileName);
        String absoluteFileName = inputFile.getAbsolutePath();
        buffer = (int)inputFile.length();
        stringBuffer = new StringBuffer(buffer / 2);
        outputFile = 
            new File(absoluteFileName.replace(".html","") + ".showemojis.html");
    }//construtor
    
    /*[01]---------------------------------------------------------------------
    
    -------------------------------------------------------------------------*/
    /**
     * 
     * @return
     * @throws IOException 
     */
    @SuppressWarnings("ConvertToTryWithResources")
    public StringBuffer readFile() throws IOException
    {
           
        BufferedReader htmlFile = 
            new BufferedReader
            (
                new FileReader(inputFile, StandardCharsets.UTF_8), buffer
            );
        
        String line;
        
        while ((line = htmlFile.readLine()) != null)
            stringBuffer.append(line).append("\n");
        
        htmlFile.close();
       
        return stringBuffer;
    }//readFile()
    
    /*[02]---------------------------------------------------------------------
    
    -------------------------------------------------------------------------*/
    /**
     * 
     * @throws IOException 
     */
    public void writeFile() throws IOException
    {
        BufferedWriter htmlFile = 
            new BufferedWriter
            (
                new FileWriter(outputFile, StandardCharsets.UTF_8), buffer
            );
        htmlFile.write(stringBuffer.toString());
        htmlFile.close();
        
    }//writeFile()
    
    /*[03]---------------------------------------------------------------------
    
    -------------------------------------------------------------------------*/
    /**
     * 
     * @param args 
     */
    public static void main(String[] args)
    {
        try
        {
            HtmlFileHandler h = new HtmlFileHandler("index.html");
            StringBuffer sb = h.readFile();
            sb.append("ULTIMA LINHA");
            h.writeFile();
        }
        catch (IOException ex)
        {
            System.err.println(ex);
        }
    }//main()
    
    
}//Classe HtmlFileHandler
