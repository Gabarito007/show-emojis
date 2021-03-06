package br.com.hkp.showemojis;

import static br.com.hkp.showemojis.global.Global.EMOJIS_DIRNAME;
import static br.com.hkp.showemojis.global.Global.SHOW_EMOJIS;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



/******************************************************************************
 * Esta classe tem todos os metodos para criar uma copia editada de uma pagina
 * salva com conversas no app WhatsApp Web.
 * <p>
 * Esta copia deve ser capaz de exibir os emojis inseridos pelo usuario. Ao 
 * contrario do que ocorrem com as paginas originalmente salvas.
 * 
 * @author "Pedro Reis"
 * @since 1 de novembro de 2020 v1.0
 * @version v1.0
 *****************************************************************************/
public final class WhatsAppEditor
{
    /*
    Localiza o atributo alt na tag que insere emojis
    */
    private static final Pattern ALT_ATTR_PATTERN = 
        Pattern.compile("alt\\s*=\\s*\".+?\"");
    
    /*
    Localiza tags que inserem emojis
    */
    private static final Pattern EMOJI_TAG_PATTERN = 
        Pattern.compile
        (
            "<img src=\"PastaBase/d5fceb6532643d0d84ffe09c40c481ecdf59e15a\\.gif.+?>"
        );
    
    /*
    Aramazena todo o conteudo de um arquivo HTML
    */
    private final StringBuffer originalHtmlContent;
    
    /*
    buffer para otimizar processos de leitura e gravacao
    */
    private final int buffer;
   
    /*
    Arquivo que serah lido e arquivo que sera gravado
    */
    private final File inputFile;
    private final File outputFile;
    
    /*[00]---------------------------------------------------------------------
    
    -------------------------------------------------------------------------*/
    /**
     * Construtor da classe.
     * 
     * @param file O arquivo que deve ser processado para gerar uma copia com as
     * tags que inserem emojis editadas.
     */
    public WhatsAppEditor(final File file)
    {
        inputFile = file;
        
        String absoluteFileName = inputFile.getAbsolutePath();
        
        buffer = (int)inputFile.length();
        
        originalHtmlContent = new StringBuffer(buffer / 2);
        
        outputFile = 
            new File
                (
                    absoluteFileName.replace(".html","") + 
                    SHOW_EMOJIS +
                    ".html"
                );
    }//construtor
    
    /*[01]---------------------------------------------------------------------
    
    -------------------------------------------------------------------------*/
    /**
     * Le o arquivo e copia seu conteudo para uma variavel interna do objeto.
     * 
     * @throws IOException Em caso de erro de IO.
     */
    public void readFile() throws IOException
    {
           
        BufferedReader htmlFile = 
            new BufferedReader
            (
                new FileReader(inputFile, StandardCharsets.UTF_8), buffer
            );
        
        String line;
        
        while ((line = htmlFile.readLine()) != null)
            originalHtmlContent.append(line).append("\n");
        
        htmlFile.close();
        
    }//readFile()
        
    /*[02]---------------------------------------------------------------------
      
    -------------------------------------------------------------------------*/
    /**
     * Recebe uma string codificada em utf8 que eh o emoji a ser exibido e que
     * foi lida do valor do atributo alt. Na tag que insere emojis na pagina.
     *
     * Esta string eh utilizada para encontrar o nome do arquivo que tem a 
     * figura correspondente a este emoji.
     * 
     * @param utf8Emoji Um emoji codificado em utf8
     * 
     * @return O nome que deve ter o arquivo PNG com a imagem deste emoji
     */
    public static String utf8EmojiToFilename(final String utf8Emoji)
    {
        StringBuilder filename = new StringBuilder(64);
        
        filename.append(EMOJIS_DIRNAME).append("/");
        
        /*
        Le caractere por caractere utf8 e os converte para codepoint quando
        estes devem fazer parte da representacao normalizada do emoji
        */        
        for (int position = 0; position < utf8Emoji.length(); position++)
        {
            char c = utf8Emoji.charAt(position);
            
            /*
            VARIATION SELECTOR-16 e LOW SURROGATES caracteres nao sao inclusos
            na representacao normalizada de codepoints.
            */
            if ((c == '\ufe0f') || (Character.isLowSurrogate(c))) continue;
                        
            filename.append
            (
                String.format
                (
                  position == 0 ? "%x" : "-%x", 
                  Character.codePointAt(utf8Emoji, position)
                )
            );
            
        }//for
        
        filename.append(".png");
        
        return filename.toString();
        
    }//utf8EmojiToFilename()
    
    /*[03]---------------------------------------------------------------------
    A partir da tag que insere emojis no arquivo original, este metodo retorna
    uma outra tag que irah inserir o emoji na copia alterada deste arquivo.
    -------------------------------------------------------------------------*/
    private String getNewTag(final String oldTag)
    {
        Matcher matcher = ALT_ATTR_PATTERN.matcher(oldTag);
        
        if (matcher.find()) 
        {
            String utf8Emoji = matcher.group();
            
            utf8Emoji = utf8Emoji.substring
                        (
                            utf8Emoji.indexOf('"') + 1, utf8Emoji.length() - 1
                        ).trim();
            
            return 
                "<img src=\"" + 
                utf8EmojiToFilename(utf8Emoji) +
                "\" alt=\"" +
                utf8Emoji +
                "\" width=\"20px\" height=\"20px\">";
        } 
        else
            return null;
  
    }//getNewTag()
    
    /*[04]---------------------------------------------------------------------
      Retorna um HashMap associando cada tag que foi encontrada no arquivo
      original a tag que deve substitui-la na copia editada deste arquivo.
    -------------------------------------------------------------------------*/
    private HashMap<String, String> getMap() throws IOException
    {
        Matcher matcher = EMOJI_TAG_PATTERN.matcher(originalHtmlContent);
              
        HashMap<String, String> map = new HashMap<>();
        
        while (matcher.find())
            map.put(matcher.group(), getNewTag(matcher.group()));
       
        return map;
   
    }//getMap()
      
    /*[05]---------------------------------------------------------------------
        
    -------------------------------------------------------------------------*/
    /**
     * Grava um novo arquivo. Neste arquivo os emojis sao renderizados.
     * 
     * @throws IOException Em caso de erro de IO.
     */
    public void createNewFile() throws IOException
    {
        BufferedWriter htmlFile = 
            new BufferedWriter
            (
                new FileWriter(outputFile, StandardCharsets.UTF_8), buffer
            );
        
        String newHtmlContent = originalHtmlContent.toString();
        
        HashMap<String, String> tagMap = getMap();
        
        for(String oldTag: tagMap.keySet())
            newHtmlContent = newHtmlContent.replace(oldTag, tagMap.get(oldTag));
       
        htmlFile.write(newHtmlContent);
        
        htmlFile.close();
        
    }//createNewFile()
    
}//classe WhatsAppEditor
