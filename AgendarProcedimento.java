import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


// Classe: AgendarProcedimento
// O que faz:
// Janela gráfica que permite que o usuário insira o ID do pet, escolha o procedimento,
// informe data e hora e tenha a opção de confirmar para criar um novo Agendamento no SistemaPetshop.
//
// Observações:
// JComboBox para listar os Procedimento.values() — assim sempre mostrará os procedimentos
//    disponíveis sem precisar manter a lista manualmente.
// Nesta classe a responsabilidade é só pegar os dados do usuário e chamar sistema.agendarProcedimento.
// Validacões mais complexas (ex.: conflito de agendamento) ficam no SistemaPetshop.

public class AgendarProcedimento extends JFrame implements ActionListener {

    private SistemaPetshop sistema;

    private JTextField campoIdPet;
    private JComboBox<Procedimento> comboProcedimento;
    private JTextField campoData;
    private JTextField campoHora;
    private JButton botaoConfirmar;
    
// Construtor: AgendarProcedimento
// O que faz: contrutor que inicializa o AgendarProcedimento.

    public AgendarProcedimento(SistemaPetshop sistema) {
        super("Agendar Procedimento");

        this.sistema = sistema;

        JPanel painel = new JPanel();
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));

        JPanel linhaPet = new JPanel();
        linhaPet.add(new JLabel("ID do Pet: "));
        campoIdPet = new JTextField(10);
        linhaPet.add(campoIdPet);

        JPanel linhaProcedimento = new JPanel();
        linhaProcedimento.add(new JLabel("Procedimento: "));
        comboProcedimento = new JComboBox<>(Procedimento.values());
        linhaProcedimento.add(comboProcedimento);

        JPanel linhaData = new JPanel();
        linhaData.add(new JLabel("Data (AAAA-MM-DD): "));
        campoData = new JTextField(10);
        linhaData.add(campoData);

        JPanel linhaHora = new JPanel();
        linhaHora.add(new JLabel("Hora (HH:MM): "));
        campoHora = new JTextField(10);
        linhaHora.add(campoHora);

        botaoConfirmar = new JButton("Confirmar Agendamento");
        botaoConfirmar.addActionListener(this);

        painel.add(linhaPet);
        painel.add(linhaProcedimento);
        painel.add(linhaData);
        painel.add(linhaHora);
        painel.add(botaoConfirmar);

        add(painel);

        setSize(800, 450);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override


// Método actionPerformed: trata o clique do botão Confirmar.
//  - converterá os campos de texto para os tipos corretos (long, LocalDate, LocalTime)
//  - chama sistema.agendarProcedimento e salva os dados
//  - mostra mensagem de sucesso ou mensagem de erro e com o motivo do erro

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == botaoConfirmar) {
            try {
                long idPet = Long.parseLong(campoIdPet.getText());
                Procedimento proc = (Procedimento) comboProcedimento.getSelectedItem();

                LocalDate data = LocalDate.parse(campoData.getText());
                LocalTime hora = LocalTime.parse(campoHora.getText());
                LocalDateTime dataHora = LocalDateTime.of(data, hora);

                sistema.agendarProcedimento(idPet, proc, dataHora);
                sistema.salvarDados();

                JOptionPane.showMessageDialog(this, "Agendamento realizado com sucesso!");
                dispose();

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
