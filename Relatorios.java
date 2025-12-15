import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;


// Classe: Relatorios
// O que faz:
// Janela que mostra relatórios: listar pets, agendamentos de hoje/futuros,
// total vendido(soma dos preços dos procedimentos) e lista de procedimentos realizados.
//
// Observações:
//  - Usa o método listarTodosPets e listarTodosAgendamentos do SistemaPetshop para obter os dados.
//  - Relatorios só apresenta e o SistemaPetshop só fornece os dados.


public class Relatorios extends JFrame implements ActionListener {
    private SistemaPetshop sistema;
    private JTextArea areaTexto;
    private JButton botaoPets;
    private JButton botaoAgendamentos;
    private JButton botaoTotalVendido;
    private JButton botaoProcedimentos;

    public Relatorios(SistemaPetshop sistema) {
        super("Relatórios");

        this.sistema = sistema;

        setLayout(new BorderLayout());
        
        JPanel painelBotoes = new JPanel();

        botaoPets = new JButton("Listar Pets");
        botaoAgendamentos = new JButton("Agendamentos");
        botaoTotalVendido = new JButton("Total Vendido");
        botaoProcedimentos = new JButton("Procedimentos Feitos");

        botaoPets.addActionListener(this);
        botaoAgendamentos.addActionListener(this);
        botaoTotalVendido.addActionListener(this);
        botaoProcedimentos.addActionListener(this);

        painelBotoes.add(botaoPets);
        painelBotoes.add(botaoAgendamentos);
        painelBotoes.add(botaoTotalVendido);
        painelBotoes.add(botaoProcedimentos);

        areaTexto = new JTextArea(20, 70);
        areaTexto.setEditable(false);

        JScrollPane scroll = new JScrollPane(areaTexto);

        add(painelBotoes, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);

        setSize(800, 450);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == botaoPets) {
            areaTexto.setText("=== Pets Cadastrados ===\n\n");
            for (Pet p : sistema.listarTodosPets()) {
                areaTexto.append(p.toString() + "\n");
            }

        } else if (e.getSource() == botaoAgendamentos) {

            areaTexto.setText("=== Agendamentos ===\n\n");

            LocalDate hoje = LocalDate.now();

            boolean temHoje = false;
            boolean temFuturos = false;

            areaTexto.append("--- Hoje ---\n");
            for (Agendamento a : sistema.listarTodosAgendamentos()) {
                if (a.getDataHora().toLocalDate().equals(hoje)) {
                    areaTexto.append(a.toString() + "\n");
                    temHoje = true;
                }
            }
            if (!temHoje) {
                areaTexto.append("(Nenhum agendamento hoje)\n");
            }

            areaTexto.append("\n--- A seguir ---\n");
            for (Agendamento a : sistema.listarTodosAgendamentos()) {
                if (a.getDataHora().toLocalDate().isAfter(hoje)) {
                    areaTexto.append(a.toString() + "\n");
                    temFuturos = true;
                }
            }
            if (!temFuturos) {
                areaTexto.append("(Nenhum agendamento futuro)\n");
            }

        } else if (e.getSource() == botaoTotalVendido) {
            areaTexto.setText("=== TOTAL VENDIDO ===\n\n");
            double total = sistema.calcularTotalVendido();
            areaTexto.append("Total faturado: R$ " + total);

        } else if (e.getSource() == botaoProcedimentos) {
            areaTexto.setText("=== Procedimentos Realizados ===\n\n");
            for (Agendamento a : sistema.listarTodosAgendamentos()) {
                areaTexto.append(a.getProcedimento() + "\n");
            }
        }
    }
}
