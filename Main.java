// Classe: Main
// O que faz:
// Entrada do programa: cria uma instância de SistemaPetshop e abre a JanelaPrincipal

public class Main {
/**
 * Método: main
 * O que faz: Executa as operações
 */
    public static void main(String[] args) {

        SistemaPetshop sistema = new SistemaPetshop();

        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            
            public void run() {
                new JanelaPrincipal(sistema);
            }
        });
    }
}
