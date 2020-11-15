package br.com.hkp.showemojis.apps;

import br.com.hkp.showemojis.gui.EmojiDecoderFrame;

/******************************************************************************
 * Aplicacao que converte um emoji codificado em uma string UTF8 para o nome do 
 * arquivo PNG que contem a imagem deste emoji.
 * 
 * @author "Pedro Reis"
 * @since 3 de novembro de 2020 v1.0
 * @version v1.0
 *****************************************************************************/
public final class EmojiDecoder
{
    public static void main(String[] args)
    {
        EmojiDecoderFrame emojiDecoderFrame = new EmojiDecoderFrame();
        emojiDecoderFrame.setVisible(true);
    }
    
}//classe EmojiDecoder
