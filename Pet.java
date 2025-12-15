// Classe: Pet
// O que faz:
// Representa um pet, com id, nome, espécie e referência ao Dono.
//
// Observações:
//  - O atributo Dono já armazena os pets do dono(ids), então a relação é dupla: 
// Pet tem Dono, e Dono tem id dos pets. 
//
// Isso facilitará a busca do dono a partir do pet e listar pets de um dono.


public class Pet {
    private long id;             
    private String nome;
    private Especies especie;
    private Dono dono;

/**
 * Construtor: Pet
 * O que faz: Construtor que inicializa a instância de Pet.
 */
    public Pet(long id, String nome, Especies especie, Dono dono) {
        this.id = id;
        this.nome = nome;
        this.especie = especie;
        this.dono = dono;
    }

/**
 * Método: getId
 * O que faz: Retorna o valor do atributo 'id'
 */
    public long getId() {
        return id;
    }
/**
 * Método: getNome
 * O que faz: Retorna o valor do atributo 'nome'
 */
    public String getNome() {
        return nome;
    }
/**
 * Método: getEspecie
 * O que faz: Retorna o valor do atributo 'especie'
 */
    public Especies getEspecie() {
        return especie;
    }
/**
 * Método: getDono
 * O que faz: Retorna o valor do atributo 'dono'
 */
    public Dono getDono() {
        return dono;
    }

    @Override
    public String toString() {
        return "ID " + id + " - " + nome + " (" + especie + ") - Dono: " + dono.getNome();
    }
}
