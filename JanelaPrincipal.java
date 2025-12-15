import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;


// Classe: JanelaPrincipal
// O que faz:
// Tela principal do aplicativo com botões para abrir as janelas de cadastro/agendamento/relatórios.
//
// Observações:
//  - Define layout e listeners, cada botão cria uma nova janela(ex: new CadastrarPet(sistema)).
//  - É a classe que o usuário normalmente abre para começar a usar o sistema(na Main).

public class JanelaPrincipal extends JFrame implements ActionListener {
    private SistemaPetshop sistema;
    private JButton botaoCadastrarDono;
    private JButton botaoCadastrarPet;
    private JButton botaoAgendarProcedimento;
    private JButton botaoRelatorios;

    public JanelaPrincipal(SistemaPetshop sistema) {
        super("Sistema Petshop");

        this.sistema = sistema;

        // Layout em grade: 2 linhas, 2 colunas, espaçamento de 20px
        setLayout(new GridLayout(2, 2, 20, 20));

        // Criando botões com tamanho e fonte maiores
        Font fonteGrande = new Font("Arial", Font.BOLD, 18);
        Dimension tamanhoBotao = new Dimension(200, 60);

        botaoCadastrarDono = new JButton("Cadastrar Dono");
        botaoCadastrarDono.setFont(fonteGrande);
        botaoCadastrarDono.setPreferredSize(tamanhoBotao);

        botaoCadastrarPet = new JButton("Cadastrar Pet");
        botaoCadastrarPet.setFont(fonteGrande);
        botaoCadastrarPet.setPreferredSize(tamanhoBotao);

        botaoAgendarProcedimento = new JButton("Agendar Procedimento");
        botaoAgendarProcedimento.setFont(fonteGrande);
        botaoAgendarProcedimento.setPreferredSize(tamanhoBotao);

        botaoRelatorios = new JButton("Relatórios");
        botaoRelatorios.setFont(fonteGrande);
        botaoRelatorios.setPreferredSize(tamanhoBotao);

        // Adicionando listeners
        botaoCadastrarDono.addActionListener(this);
        botaoCadastrarPet.addActionListener(this);
        botaoAgendarProcedimento.addActionListener(this);
        botaoRelatorios.addActionListener(this);

        // Adicionando botões ao frame
        add(botaoCadastrarDono);
        add(botaoCadastrarPet);
        add(botaoAgendarProcedimento);
        add(botaoRelatorios);

        // Configurações da janela
        setSize(800, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
//  Método acionado para tratar os eventos dos botões
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == botaoCadastrarDono) {
            new CadastrarDono(sistema);
        } else if (e.getSource() == botaoCadastrarPet) {
            new CadastrarPet(sistema);
        } else if (e.getSource() == botaoAgendarProcedimento) {
            new AgendarProcedimento(sistema);
        } else if (e.getSource() == botaoRelatorios) {
            new Relatorios(sistema);
        }
    }
}