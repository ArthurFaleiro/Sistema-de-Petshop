import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


// Classe: CadastrarPet
// O que faz:
// Cadastra um pet (nome, espécie, e com cpf do dono).
// Ao criar o pet, exibe o ID gerado na mensagem de sucesso.
//
// Observações:
//  - JComboBox<Especies> usa o enum Especies para evitar entrada inválida de espécie.
//  - Se o CPF do dono não existir, o SistemaPetshop lança IllegalArgumentException.

public class CadastrarPet extends JFrame implements ActionListener {
    private SistemaPetshop sistema;
    private JTextField campoNome;
    private JComboBox<Especies> comboEspecie;
    private JTextField campoCpfDono;
    private JButton botaoConfirmar;

    public CadastrarPet(SistemaPetshop sistema) {
        super("Cadastrar Pet");

        this.sistema = sistema;

        JPanel painel = new JPanel();
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));

        JPanel linhaNome = new JPanel();
        linhaNome.add(new JLabel("Nome do pet: "));
        campoNome = new JTextField(20);
        linhaNome.add(campoNome);

        JPanel linhaEspecie = new JPanel();
        linhaEspecie.add(new JLabel("Espécie: "));
        comboEspecie = new JComboBox<>(Especies.values());
        linhaEspecie.add(comboEspecie);

        JPanel linhaCpfDono = new JPanel();
        linhaCpfDono.add(new JLabel("CPF do dono: "));
        campoCpfDono = new JTextField(20);
        linhaCpfDono.add(campoCpfDono);

        botaoConfirmar = new JButton("Confirmar");
        botaoConfirmar.addActionListener(this);

        painel.add(linhaNome);
        painel.add(linhaEspecie);
        painel.add(linhaCpfDono);
        painel.add(botaoConfirmar);

        add(painel);

        setSize(400, 250);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == botaoConfirmar) {
            String nome = campoNome.getText();
            Especies especie = (Especies) comboEspecie.getSelectedItem();
            String cpfDono = campoCpfDono.getText();

            try {
                Pet pet = sistema.cadastrarPet(nome, especie, cpfDono);
                sistema.salvarDados();
                JOptionPane.showMessageDialog(this, "Pet cadastrado com sucesso! ID: " + pet.getId());
                dispose();
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
