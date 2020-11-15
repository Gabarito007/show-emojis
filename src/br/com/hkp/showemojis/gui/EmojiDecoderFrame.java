package br.com.hkp.showemojis.gui;

import br.com.hkp.showemojis.WhatsAppEditor;
import static br.com.hkp.showemojis.global.Global.EMOJIS_DIRNAME;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

/******************************************************************************
 * @author "Pedro Reis"
 * @since 3 de novembro de 2020 v1.0
 * @version v1.0
 *****************************************************************************/
public final class EmojiDecoderFrame extends JFrame
{
    private final JTextField inputEmoji;
    private final JTextField showFileName;
    
    /*[00]---------------------------------------------------------------------
    
    -------------------------------------------------------------------------*/
    public EmojiDecoderFrame()
    {
        super("");
        setLayout(new BorderLayout());
        setSize(520, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        showFileName = new JTextField(" "); 
        showFileName.setBorder(new TitledBorder("Nome do arquivo:"));
        showFileName.setEditable(false);
        showFileName.setToolTipText("Use o CTRL + C");
        
        JLabel fieldLabel = 
            new JLabel(" Cole aqui o c\u00f3digo UTF-8 do emoji: ");
        fieldLabel.setToolTipText("Use o CTRL + V");
        
        inputEmoji = new JTextField("");
        inputEmoji.setColumns(3);
        inputEmoji.setToolTipText("Use CTRL + V");
        
        JButton jButton = new JButton();
        jButton.setText(" \u27a4 Decodificar");
        jButton.addActionListener(new ButtonListener());
        
        JTextArea warning = new JTextArea();
        warning.setEditable(false);
        warning.setBorder(new TitledBorder(""));
        warning.setBackground(Color.yellow); 
        warning.setFont(new Font(Font.MONOSPACED, 0, 13));
        
        warning.setText
        (
              "\n"
            + "   Se a p\u00e1gina n\u00e3o renderizar o emoji pode ser  que"
            + " n\u00e3o exista\n"
            + " um  arquivo  de imagem  correspondente na pasta /"  
            +  EMOJIS_DIRNAME +"\n"
            + " ou que o programa tenha falhado ao nomear o arquivo.\n\n"
            + "   Nesse caso voc\u00ea deve  procurar  o   nome  do emoji no "
            + " site\n da emojipedia  e  checar no TXT /"+ EMOJIS_DIRNAME 
            +"/_emoji-list.txt\n"
            + " se  um arquivo  de imagem foi  baixado para este emoji e qual\n"
            + " arquivo \u00e9.\n\n"
            + "   Basta  ent\u00e3o  renomear  este arquivo com o nome obtido "
            + "aqui\n"
            + " para que o emoji passe a ser renderizado.\n\n" 
            + "   No  entanto  se o  nome  do  emoji n\u00e3o estiver  listado "
            + " em\n"
            + " _emoji-list.txt \u00e9  porque um  arquivo PNG n\u00e3o foi "
            + "obtido para\n este emoji.\n\n"
            + "   Ser\u00e1 necess\u00e1rio obter o arquivo na emojipedia,"
            + " renomear com\n"
            + " o nome obtido aqui e copia-lo para a pasta /" + EMOJIS_DIRNAME 
            + ".\n"
        );
        
        add(showFileName, BorderLayout.NORTH);
        add(fieldLabel, BorderLayout.WEST);
        add(inputEmoji, BorderLayout.CENTER);
        add(jButton, BorderLayout.EAST);
        add(warning, BorderLayout.SOUTH);
           
    }//construtor
     
     
    /*=========================================================================
                         classe interna privada
    ==========================================================================*/
    private class ButtonListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e)
        {
            String emoji = inputEmoji.getText();
            showFileName.setText(" " + WhatsAppEditor.utf8EmojiToFilename(emoji));
        }//actionPerformed()
        
    }//classe ButtonListener
   
    
}//classe EmojiDecoderFrame
