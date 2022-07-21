package me.alexfrocha;

import com.formdev.flatlaf.util.StringUtils;
import org.apache.commons.text.StringEscapeUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.NoSuchFileException;

public class JNotes {
    private static String usuario = System.getProperty("user.name");
    private static String diretorioDoArquivo;
    private static String diretorioDaPlanilha;

    public static void lerArquivo() throws Exception {
        PDDocument documento = PDDocument.load(new File(diretorioDoArquivo));
        PDFTextStripper stripper = new PDFTextStripper();

        String conteudo = stripper.getText(documento);
        String[] linhas = conteudo.split("\n");

        for(int linha = 0; linha < linhas.length; linha++) {
            if(linhas[linha].contains("JNOTES")) {
                linha++;
                while(!linhas[linha].contains("JEND")) {
                    String coluna = linhas[linha].replaceAll(" ", "").replace("|", ",");
                    transferindoDados(coluna);

                    linha++;
                }
            }
        }
        documento.close();
    }

    public static void transferindoDados(String dados) throws Exception {
        diretorioDaPlanilha = diretorioDoArquivo.replace(".pdf", ".csv");
        FileOutputStream fos = new FileOutputStream(diretorioDaPlanilha, true);
        OutputStreamWriter osw = new OutputStreamWriter(fos, StandardCharsets.ISO_8859_1);
        BufferedWriter arquivo = new BufferedWriter(osw);

            arquivo.append(dados);
            arquivo.append("\n");


        arquivo.close();
    }

    public static void selecionarOArquivo(String diretorioDoArquivoSelecionado) throws NoSuchFileException {
        if(diretorioDoArquivoSelecionado == null) throw new NoSuchFileException("Arquivo não encontrado");
        diretorioDoArquivo = diretorioDoArquivoSelecionado;
    }

}
