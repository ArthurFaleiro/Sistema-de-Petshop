// Classe: Pessoa
// O que faz:
//  Superclasse abstrata para Dono(ou outras pessoas se necessário).
//  Guarda cpf, nome e email em campos protegidos para serem usados pelas subclasses.
//
// Observações:
//  - A classe abstrata aqui evita duplicação de código para atributos básicos de pessoa.

public abstract class Pessoa {
    protected String cpf;   
    protected String nome;
    protected String email;

    public Pessoa(String cpf, String nome, String email) {
        this.cpf = cpf;
        this.nome = nome;
        this.email = email;
    }


/**
 * Método: getCpf
 * O que faz: Retorna o valor do atributo 'cpf'
 */
    public String getCpf() {
        return cpf;
    }
/**
 * Método: getNome
 * O que faz: Retorna o valor do atributo 'nome'
 */
    public String getNome() {
        return nome;
    }
/**
 * Método: getEmail
 * O que faz: Retorna o valor do atributo 'email'
 */
    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return nome + " (CPF: " + cpf + ", email: " + email + ")";
    }
}
