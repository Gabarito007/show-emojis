package br.com.hkp.showemojis;

/******************************************************************************
 * A finalidade desta classe eh fornecer metodos para converter um emoji 
 * codificado como uma string UTF-8 para uma string de codepoints representados
 * em notacao hexadecimal.
 * <p>
 * A string de codepoints serah normalizada de acordo com o padrao utilizado 
 * por esta aplicacao para normalizar nomes de arquivos png que contem a imagem 
 * destes respectivos emojis.
 * <p>
 * O objetivo eh que, dado um determinado emoji codificado em utf8, se possa 
 * obter o nome do arquivo PNG com a figura referente a este emoji. Para isto o
 * nome do arquivo serah normalizado - segundo determinado padrao - para a 
 * string de codepoints que representa o emoji. Portanto metodos desta classe
 * devem converter um emoji codificado como uma string utf8 em uma string de
 * codepoints normalizada com o mesmo padrao destes nomes de arquivos.
 * <p>
 * Para que se possa relacionar um emoji codificado em utf8 com o nome do 
 * arquivo png que deve conter uma figura representando este mesmo emoji.
 * Permitindo que atraves deste emoji-utf8 se possa buscar no disco o arquivo 
 * com a figura referente a ele.
 * <p>
 * Os arquivos com as imagens de emojis utilizados neste projeto foram obtidos 
 * inicialmente na pagina https://emojipedia.org/whatsapp/2.19.352/ 
 * 
 * @author "Pedro Reis"
 * @since 29 de outubro de 2020 v1.0
 * @version v1.0
 *****************************************************************************/

public final class Utf8ToCodepoints
{
    private final String utf8Emoji;
    
    /*[00]---------------------------------------------------------------------
    
    -------------------------------------------------------------------------*/
    /**
     * Construtor da classe.
     * 
     * @param utf8Emoji Um emoji codificado como string utf8
     */
    public Utf8ToCodepoints(final String utf8Emoji)
    {
        this.utf8Emoji = utf8Emoji;
    }//construtor
    
    /*[01]---------------------------------------------------------------------
    
    -------------------------------------------------------------------------*/
    /**
     * Recebe um emoji codificado como string utf8 e retorna uma representacao
     * normalizada deste emoji como uma string de codepoints.
     * 
     * @param utf8Emoji O emoji codificado como string utf8
     * 
     * @return Representacao normalizada de string de codepoints
     */
    public static String utf8ToCodepoints(final String utf8Emoji)
    {
        StringBuilder result = new StringBuilder(32);
        
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
                        
            result.append
            (
                String.format
                (
                  position == 0 ? "%x" : "-%x", 
                  Character.codePointAt(utf8Emoji, position)
                )
            );
            
        }//for
        
        return result.toString();
    }//utf8ToCodepoints()
    
    /*[02]---------------------------------------------------------------------
    
    -------------------------------------------------------------------------*/
    /**
     * Recebe um emoji codificado como string utf8 e retorna o nome do arquivo
     * PNG que deve conter a figura correspondente a este emoji.
     * 
     * @param utf8Emoji O emoji codificado como string utf8
     * 
     * @return O nome do arquivo PNG com a imagem referente a este emoji
     */
    public static String utf8ToFilename(final String utf8Emoji)
    {
        return utf8ToCodepoints(utf8Emoji) + ".png";
    }//utf8ToFilename()
    
    /*[03]---------------------------------------------------------------------
    
    -------------------------------------------------------------------------*/
    /**
     * Representacao textual da string utf8
     * 
     * @return Uma descricao textual de um objeto dessa classe
     */
    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder(512);
        sb.append(utf8Emoji).append("\n");
        
        for (int pos = 0;  pos < utf8Emoji.length(); pos++)
        {
            char c = utf8Emoji.charAt((pos));
            
           
            sb.append
            (
                String.format
                (
                   "%06x : %s \n",
                    Character.codePointAt(utf8Emoji, pos),
                    Character.getName(c)
                )
            );
           
        }//for
        
        return sb.toString();
     
    }//toString()
    
    /*[04]---------------------------------------------------------------------
    
    -------------------------------------------------------------------------*/
    /**
     * Metodo para exemplificar uso da classe
     * 
     * @param args n/a
     */
    public static void main(String[] args)
    {
        Utf8ToCodepoints u = new Utf8ToCodepoints("👎️");
        
        System.out.println(u);
        System.out.println("");
        
        System.out.println(utf8ToFilename("👎️"));
      
    }//main()
    
}//classe Utf8ToCodepoints

