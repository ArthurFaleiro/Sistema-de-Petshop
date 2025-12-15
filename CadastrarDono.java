import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


// Classe: CadastrarDono
// O que faz:
//  Janela para entrada de CPF, nome e email para criar um novo Dono.
//
// Observações:
//  - Validação do CPF (verificar o tamanho e dígitos) é feita no SistemaPetshop; aqui só armazena os valores.
//  - Ao confirmar, chama sistema.salvarDados() para salvar imediatamente.

public class CadastrarDono extends JFrame implements ActionListener {
    private SistemaPetshop sistema;
    private JTextField campoCpf;
    private JTextField campoNome;
    private JTextField campoEmail;
    private JButton botaoConfirmar;
    
    public CadastrarDono(SistemaPetshop sistema) {
        super("Cadastrar Dono");

        this.sistema = sistema;

        JPanel painel = new JPanel();
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));

        JPanel linhaCpf = new JPanel();
        linhaCpf.add(new JLabel("CPF: "));
        campoCpf = new JTextField(20);
        linhaCpf.add(campoCpf);

        JPanel linhaNome = new JPanel();
        linhaNome.add(new JLabel("Nome: "));
        campoNome = new JTextField(20);
        linhaNome.add(campoNome);

        JPanel linhaEmail = new JPanel();
        linhaEmail.add(new JLabel("Email: "));
        campoEmail = new JTextField(20);
        linhaEmail.add(campoEmail);

        botaoConfirmar = new JButton("Confirmar");
        botaoConfirmar.addActionListener(this);

        painel.add(linhaCpf);
        painel.add(linhaNome);
        painel.add(linhaEmail);
        painel.add(botaoConfirmar);

        add(painel);

        setSize(400, 250);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == botaoConfirmar) {
            String cpf = campoCpf.getText();
            String nome = campoNome.getText();
            String email = campoEmail.getText();

            try {
                sistema.cadastrarDono(cpf, nome, email);
                sistema.salvarDados();
                JOptionPane.showMessageDialog(this, "Dono cadastrado com sucesso!");
                dispose();
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
