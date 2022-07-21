package me.alexfrocha;


import de.milchreis.uibooster.UiBooster;
import de.milchreis.uibooster.model.UiBoosterOptions;

import java.io.File;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws Exception {
        UiBooster display = new UiBooster( UiBoosterOptions.Theme.LIGHT_THEME );
        try {
            display.showWarningDialog("Caso queira converter o mesmo Arquivo .PDF para .CSV novamente é ncessário que você exclua o Arquivo .CSV existente, para não haver erros.", "JNOTES");
            File fileOrDirectory = display.showFileOrDirectorySelection("Arquivos .PDF", "pdf");
            String diretorio = String.valueOf(fileOrDirectory);

            JNotes.selecionarOArquivo(diretorio);
            JNotes.lerArquivo();
            display.showInfoDialog("O Arquivo .CSV foi salvo no mesmo local do .PDF com o mesmo nome");
        } catch (FileNotFoundException e) {
            display.showErrorDialog("Arquivo ou Diretório não encontrado", "JNOTES");
        }
    }
}
