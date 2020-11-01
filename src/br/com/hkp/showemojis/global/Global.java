package br.com.hkp.showemojis.global;

import javax.swing.UIManager;

/******************************************************************************
 * A funcao desta classe eh fornecer metodos e campos que sejam de acesso para 
 * todas as outras classes do projeto.
 * 
 * @author "Pedro Reis"
 * @since 29 de outubro de 2020 v1.0
 * @version v1.0
 *****************************************************************************/

public final class Global
{
    /**
     * Eh o nome do diretorio para onde serao movidos os arquivos PNG de imagem
     * de emojis.
     */
    public static final String EMOJIS_DIRNAME = "emoji-images";
    
    /**
     * Esta string eh acrescentada a um arquivo que seja copia do original.
     * Se o original for, por exemplo, index.html, sua copia editada serah
     * nomeada como index.showemojis.html
     */
    public static final String SHOW_EMOJIS = ".showemojis";
    
    /*[00]---------------------------------------------------------------------
    
    -------------------------------------------------------------------------*/
    /**
     * Configura os textos do objeto FileChooser para Portugues
     * 
     * @param title Um titulo para a janela
     */
    public static void fileChooserSettings(String title)
    {
        UIManager.put("FileChooser.openDialogTitleText", title);
        UIManager.put("FileChooser.lookInLabelText", "Selecionar"); 
        UIManager.put("FileChooser.openButtonText", "Abrir"); 
        UIManager.put("FileChooser.cancelButtonText", "Cancelar");
        UIManager.put("FileChooser.fileNameLabelText", "Nome do Arquivo"); 
        UIManager.put("FileChooser.filesOfTypeLabelText", "Tipo de Arquivo"); 
        UIManager.put("FileChooser.folderNameLabelText", "Selecionado"); 
        UIManager.put
        (
            "FileChooser.openButtonToolTipText", "Abrir o Arquivo Selecionado"
        ); 
        UIManager.put("FileChooser.cancelButtonToolTipText","Cancelar"); 
        UIManager.put("FileChooser.fileNameHeaderText","Nome"); 
        UIManager.put("FileChooser.upFolderToolTipText", "Acima");
        UIManager.put
        (
            "FileChooser.homeFolderToolTipText",
            "\u00c1rea de Trabalho"
        ); 
        UIManager.put("FileChooser.newFolderToolTipText","Nova Pasta");
        UIManager.put("FileChooser.listViewButtonToolTipText","Lista"); 
        UIManager.put("FileChooser.newFolderButtonText","Nova Pasta"); 
        UIManager.put("FileChooser.renameFileButtonText", "Renomear");
        UIManager.put("FileChooser.deleteFileButtonText", "Eliminar");
        UIManager.put("FileChooser.filterLabelText", "Tipo");
        UIManager.put("FileChooser.detailsViewButtonToolTipText", "Detalhes");
        UIManager.put("FileChooser.fileSizeHeaderText","Tamanho"); 
        UIManager.put
        (
            "FileChooser.fileDateHeaderText", 
            "Data de Altera\u00e7\u00e3o.");
        UIManager.put
        (
            "FileChooser.acceptAllFileFilterText", "Diret\u00f3rio"
        );
    }//FileChooserSettings()
    
}//classe Global
