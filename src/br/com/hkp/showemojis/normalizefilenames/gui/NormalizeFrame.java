package br.com.hkp.showemojis.normalizefilenames.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

/******************************************************************************
 *
 * @author "Pedro Reis"
 * @since 29 de outubro de 2020 v1.0
 * @version v1.0
 *****************************************************************************/

public final class NormalizeFrame extends JFrame
{
    private final JTextArea jTextArea;
    private final JScrollPane jScrollPane;
    private final JProgressBar jProgressBar;
    
    /*[00]---------------------------------------------------------------------
    
    -------------------------------------------------------------------------*/
    /**
     * 
     */
    public NormalizeFrame()
    {
        super("Normalizando...");
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        setSize(700, 450);
        
        setLayout(new BorderLayout());
        
        jTextArea = new JTextArea();
        
        jTextArea.setEditable(false);
        
        jScrollPane = new JScrollPane(jTextArea);
        
        jScrollPane.setVerticalScrollBarPolicy
        (
            ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED
        );
        
        jScrollPane.setHorizontalScrollBarPolicy
        (
            ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED
        );
        
        jProgressBar = new JProgressBar();
        jProgressBar.setVisible(false);
        
        add(jScrollPane, BorderLayout.CENTER);
        add(jProgressBar, BorderLayout.SOUTH);
        
        setVisible(false);
       
    }//construtor
    
       
    /*[02]---------------------------------------------------------------------
    
    -------------------------------------------------------------------------*/
    /**
     * 
     * @param value 
     */
    public void setProgressBarValue(int value)
    {
        jProgressBar.setValue(value);
        jProgressBar.setString(value + " arquivos");
    }//setProgressBarValue()
    
    /*[03]---------------------------------------------------------------------
    
    -------------------------------------------------------------------------*/
    /**
     * 
     * @param value 
     */
    public void setProgressBarVisible(int maximumValue)
    {
        jProgressBar.setStringPainted(true);
        jProgressBar.setForeground(Color.BLACK);
        jProgressBar.setValue(0);
        jProgressBar.setMaximum(maximumValue);
        jProgressBar.setVisible(true);
    }//setProgressBarVisible()
    
    /*[03]---------------------------------------------------------------------
    
    -------------------------------------------------------------------------*/
    /**
     * 
     * @param s 
     */
    public void println(String s)
    {
        jTextArea.append(s + "\n\n");
        jTextArea.setCaretPosition(jTextArea.getText().length()); 
    }//println()
      
    
}//classe NormalizeFrame


 
        
        
