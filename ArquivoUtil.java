import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


// Classe: ArquivoUtil
// O que faz:
//  Métodos estáticos para ler e escrever linhas em arquivos texto.
//  Evitar repetir código em SistemaPetshop.
//
// Observações:
//  - lerLinhas: retorna lista vazia se o arquivo não existir.
//  - escreverLinhas: sobrescreve o arquivo com as linhas fornecidas.
//  - Não lança exceções para o chamador(apenas imprime erro).

public class ArquivoUtil {

    public static List<String> lerLinhas(String nomeArquivo) {
        List<String> linhas = new ArrayList<>();
        Path caminho = Paths.get(nomeArquivo);

        if (!Files.exists(caminho)) {
            return linhas;
        }

        try {
            linhas = Files.readAllLines(caminho);
        } catch (IOException e) {
            System.out.println("Erro ao ler arquivo " + nomeArquivo + ": " + e.getMessage());
        }

        return linhas;
    }

    public static void escreverLinhas(String nomeArquivo, List<String> linhas) {
        Path caminho = Paths.get(nomeArquivo);

        try {
            Files.write(caminho, linhas);
        } catch (IOException e) {
            System.out.println("Erro ao escrever arquivo " + nomeArquivo + ": " + e.getMessage());
        }
    }
}
