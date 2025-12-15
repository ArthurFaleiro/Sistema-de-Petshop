// Classe: Agendamento
// O que faz:
// Ela representa um agendamento de procedimento para um pet.
// É criado pelo SistemaPetshop quando o usuário marca um horário.

import java.time.LocalDateTime;

public class Agendamento {
    private long id;
    private long idPet;
    private Procedimento procedimento;
    private LocalDateTime dataHora;

/** Construtor: Agendamento
 * O que faz: inicializa um agendamento com todos os dados necessários.
 * Não faz validação complexa aqui; 
 * A validação (pet existe, conflito de horário) é feita em SistemaPetshop.
 */
    public Agendamento(long id, long idPet, Procedimento procedimento, LocalDateTime dataHora) {
        this.id = id;
        this.idPet = idPet;
        this.procedimento = procedimento;
        this.dataHora = dataHora;
    }
    //pega o id do agendamento
    public long getId() {
        return id;
    }
    //pega o id do pet
    public long getIdPet() {
        return idPet;
    }
    //pega o procedimento
    public Procedimento getProcedimento() {
        return procedimento;
    }
    //pega a data e hora do agendamento
    public LocalDateTime getDataHora() {
        return dataHora;
    }

    @Override
    public String toString() {
        return "Agendamento " + id +
               " - Pet ID=" + idPet +
               ", Proc=" + procedimento +
               ", DataHora=" + dataHora.toString();
    }
}
