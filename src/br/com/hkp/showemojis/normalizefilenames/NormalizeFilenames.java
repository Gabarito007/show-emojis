package br.com.hkp.showemojis.normalizefilenames;

import br.com.hkp.showemojis.global.Global;
import static br.com.hkp.showemojis.global.Global.EMOJIS_DIRNAME;
import br.com.hkp.showemojis.normalizefilenames.gui.NormalizeFrame;
import java.io.File;
import java.io.FilenameFilter;
import javax.swing.JFileChooser;

/******************************************************************************
 *
 * @author "Pedro Reis"
 * @since 29 de outubro de 2020 v1.0
 * @version v1.0
 *****************************************************************************/

public final class NormalizeFilenames
{
    
    
    /*[00]---------------------------------------------------------------------
        Configura o objeto FileChooser para exibir texto em Portugues
    -------------------------------------------------------------------------*/
    public NormalizeFilenames()
    {
        Global.fileChooserSettings();
    }//construtor
    
    /*[01]---------------------------------------------------------------------
       Obtem o diretorio onde estao os arquivos PNG com as imagens dos emojis
    -------------------------------------------------------------------------*/
    public File getDir()
    {
        JFileChooser fc = new JFileChooser();

        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        int res = fc.showOpenDialog(null);

        if(res == JFileChooser.APPROVE_OPTION)
       
            return fc.getSelectedFile();
        else
            return null;
        
    }//getDir()
    
    /*[02]---------------------------------------------------------------------
       Normaliza os nomes dos arquivos PNG e os move para uma pasta propria
    -------------------------------------------------------------------------*/
    public void normalize(final File dir)
    {
        NormalizeFrame normalizeFrame = new NormalizeFrame();
        
        File[] fileList = dir.listFiles(new EmojiFileFilter());
        
        normalizeFrame.setVisible(true);
        
        if (fileList.length == 0)
        {
            normalizeFrame.setTitle("Arquivos n\u00e3o encontrados");
            normalizeFrame.setSize(375, 120);
            normalizeFrame.println
            (
                "\n\n      Nenhum arquivo com imagem de emoji encontrado!"
            );
            
            return;
        }
        
        normalizeFrame.setProgressBarVisible(fileList.length);
            
          
        /*--------------------------------------------------------------------
        Cria um novo diretorio, de nome emoji-images, dentro do diretorio onde
        estao os arquivos PNG com as imagens do emojis.
        ---------------------------------------------------------------------*/
        File newDir = new File(dir.getAbsolutePath()+ EMOJIS_DIRNAME);
        
        String newDirName = newDir.getAbsolutePath();
        
        if (!newDir.exists()) newDir.mkdir();
        /*--------------------------------------------------------------------*/
        
        int barValue = 0;
        
        /*--------------------------------------------------------------------
         Normaliza os nomes dos arquivos PNG e os move para o diretorio recem-
         criado
        ---------------------------------------------------------------------*/ 
        for (File file: fileList)
        {
               
            String filename = file.getName();
            
            /*
            prefix recebe o nome do arquivo sem a string de codepoints e a 
            extensao do arquivo
            */
            String prefix = filename.replaceFirst("_[0-9a-f_\\-]+\\.png", "");
            
            /*
            sufix recebe a string de codepoints seguida da extensao .png e 
            normaliza esta string
            */
            String sufix = 
                filename.replaceFirst(prefix, "").
                replaceFirst("_","").
                replaceAll("_[0-9a-f]+","").
                replaceAll("-fe0f","");
            
            /*-----------------------------------------------------------------
            O arquivo eh renomeado fazendo com que seja movido para o diretorio
            recem criado
            ------------------------------------------------------------------*/
            File newName = new File(newDirName + "/" + sufix);
            
            normalizeFrame.println
            (
                filename + " \u21e8 " + EMOJIS_DIRNAME + "/" + newName.getName() 
            );
        
                        
            file.renameTo(newName); 
                                                                     
            normalizeFrame.setProgressBarValue(++barValue);
            /*----------------------------------------------------------------*/
            
        }//fim do for 
        
        normalizeFrame.setTitle(barValue + " arquivos normalizados");
         
    }//normalize()
      
    /*=========================================================================
                             Classe interna
    =========================================================================*/
    private final class EmojiFileFilter implements FilenameFilter
    {

        @Override
        public boolean accept(File dir, String filename)
        {
            return filename.matches(".+_[0-9a-f\\-_]+\\.png");
        }//accept()

    }//classe EmojiFileFilter
    
}//classe NormalizeFileNames
    
    


