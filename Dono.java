import java.util.HashSet;
import java.util.Set;

// Classe: Dono
// O que faz:
// Representa o dono(pessoa) e guarda o as IDs dos pets desse dono.
//
// Observações:
//  - idsPets é um HashSet<Long> porque queremos evitar duplicações de id de pet e buscas/removals rápidos.
//  - usar Set em vez de List diz que a ordem não é importante e duplicatas não são permitidas.

public class Dono extends Pessoa {
    private Set<Long> idsPets = new HashSet<>();
    
    public Dono(String cpf, String nome, String email) {
        super(cpf, nome, email);
    }

/**
 * Método: getIdsPets
 * O que faz: Retorna o valor do atributo 'idsPets'
 */
    public Set<Long> getIdsPets() {
        return idsPets;
    }

    public void adicionarPet(long idPet) {
        idsPets.add(idPet);
    }
/**
 * Método: removerPet
 * O que faz: Remove um elemento/registro correspondente aos critérios fornecidos.
 */
    public void removerPet(long idPet) {
        idsPets.remove(idPet);
    }
}
