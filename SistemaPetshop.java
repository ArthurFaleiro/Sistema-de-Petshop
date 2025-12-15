import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;



// Classe: SistemaPetshop
// O que faz:
// Sistema: guarda collections de pets, donos e agendamentos, gera ids.
// Contém toda a lógica: cadastro, busca, agendamento, cálculo de faturamento, persistência em arquivos.
//
// Observações e justificativas de implementação:
//  - pets é um ArrayList: usamos ArrayList porque precisamos de uma lista ordenada e fácil de iterar.
//    Para buscas pontuais por id o loop é suficiente.
//
//  - donos é um HashMap<String, Dono> mapeando cpf->Dono: buscar dono por CPF.
//
//  - agendamentos é um ArrayList: mantém ordem de inserção e é fácil de salvar/listar.
//
//  - proximoIdPet & proximoIdAgendamento geram ids sequenciais simples e são atualizados ao carregar do arquivo.
//
//  - buscarPetsPorNome usa streams + lambda: isso deixa o código curto e legível
//    Exemplo: pets.stream().filter(...).collect(...) — a lambda aqui é só uma forma enxuta de escrever o filtro.

public class SistemaPetshop {
    private List<Pet> pets = new ArrayList<>();
    private Map<String, Dono> donos = new HashMap<>(); 
    private List<Agendamento> agendamentos = new ArrayList<>();
    private long proximoIdPet = 1;
    private long proximoIdAgendamento = 1;
    private static final String ARQ_DONOS = "donos.csv";
    private static final String ARQ_PETS = "pets.csv";
    private static final String ARQ_AGENDAMENTOS = "agendamentos.csv";


/**
 * Construtor: SistemaPetshop
 * O que faz: Construtor que inicializa a instância de SistemaPetshop.
 */
    public SistemaPetshop() {
        carregarDados();
    }



// cadastrarDono: valida cpf (tamanho e apenas dígitos) e adiciona ao map.
// Lança IllegalArgumentException se algo estiver errado


    public void cadastrarDono(String cpf, String nome, String email) {
        if (cpf == null || cpf.isEmpty()) {
            throw new IllegalArgumentException("CPF não pode ser vazio.");
        }
        if (cpf.length() != 11) {
            throw new IllegalArgumentException("CPF deve conter exatamente 11 dígitos numéricos.");
        }
        for (int i = 0; i < cpf.length(); i++) {
            if (!Character.isDigit(cpf.charAt(i))) {
                throw new IllegalArgumentException("CPF deve conter apenas números (sem pontos ou traços).");
            }
        }
        if (donos.containsKey(cpf)) {
            throw new IllegalArgumentException("Já existe um dono com este CPF.");
        }
        Dono d = new Dono(cpf, nome, email);
        donos.put(cpf, d);
    }


// cadastrarPet: verifica se o dono existe(por CPF) e cria um Pet com id sequencial.
// Adiciona o id ao Dono(dono.adicionarPet) para manter a consistência.

    public Pet cadastrarPet(String nome, Especies especie, String cpfDono) {
        Dono dono = donos.get(cpfDono);
        if (dono == null) {
            throw new IllegalArgumentException("Dono com este CPF não encontrado.");
        }
        Pet pet = new Pet(proximoIdPet, nome, especie, dono);
        pets.add(pet);
        dono.adicionarPet(proximoIdPet);
        proximoIdPet++;
        return pet;
    }


// agendarProcedimento: faz várias validações:
//  - garante que o pet exista
//  - evita duplicação do mesmo procedimento para o mesmo pet
//  - evita conflito de horário (mesma dataHora já ocupada)
//  Depois disso, cria e adiciona o agendamento à lista.

    public Agendamento agendarProcedimento(long idPet, Procedimento procedimento, LocalDateTime dataHora) {
        Pet pet = buscarPetPorId(idPet);
        if (pet == null) {
            throw new IllegalArgumentException("Pet com este ID não encontrado.");
        }
        for (Agendamento a : agendamentos) {
            if (a.getIdPet() == idPet && a.getProcedimento() == procedimento) {
                throw new IllegalArgumentException("Este pet já possui esse procedimento agendado.");
            }
        }
        for (Agendamento a : agendamentos) {
            if (a.getDataHora().equals(dataHora)) {
                throw new IllegalArgumentException("Já existe um agendamento neste horário.");
            }
        }
        Agendamento agendamento = new Agendamento(proximoIdAgendamento, idPet, procedimento, dataHora);
        agendamentos.add(agendamento);
        proximoIdAgendamento++;
        return agendamento;
    }

    public Pet buscarPetPorId(long id) {
        for (Pet p : pets) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }
    public List<Pet> buscarPetsPorNome(String nome) {
        return pets.stream()
                .filter(p -> p.getNome().equalsIgnoreCase(nome))
                .collect(Collectors.toList());
    }
    public List<Pet> listarTodosPets() {
        return new ArrayList<>(pets);
    }
    public List<Agendamento> listarTodosAgendamentos() {
        return new ArrayList<>(agendamentos);
    }

// listarAgendamentosDoDia: percorre a lista e retorna só os que têm a data informada.
    public List<Agendamento> listarAgendamentosDoDia(LocalDate data) {
        List<Agendamento> resultado = new ArrayList<>();
        for (Agendamento a : agendamentos) {
            if (a.getDataHora().toLocalDate().equals(data)) {
                resultado.add(a);
            }
        }
        return resultado;
    }


/**
 * Método: calcularTotalVendido
 * O que faz: Realiza o cálculo e retorna o resultado.
 */
    public double calcularTotalVendido() {
        double total = 0.0;
        for (Agendamento a : agendamentos) {
            total += a.getProcedimento().getPreco();
        }
        return total;
    }

/**
 * Método: salvarDados
 * O que faz: Executa operações sem retornar valor.
 */
    public void salvarDados() {
        salvarDonos();
        salvarPets();
        salvarAgendamentos();
    }

    private void salvarDonos() {
        List<String> linhas = new ArrayList<>();
        for (Dono d : donos.values()) {
            String linha = d.getCpf() + ";" + d.getNome() + ";" + d.getEmail();
            linhas.add(linha);
        }
        ArquivoUtil.escreverLinhas(ARQ_DONOS, linhas);
    }

    private void salvarPets() {
        List<String> linhas = new ArrayList<>();
        for (Pet p : pets) {
            String linha = p.getId() + ";" + p.getNome() + ";" + p.getEspecie().name() + ";" + p.getDono().getCpf();
            linhas.add(linha);
        }
        ArquivoUtil.escreverLinhas(ARQ_PETS, linhas);
    }

    private void salvarAgendamentos() {
        List<String> linhas = new ArrayList<>();
        for (Agendamento a : agendamentos) {
            String linha = a.getId()
                    + ";" + a.getIdPet()
                    + ";" + a.getProcedimento().name()
                    + ";" + a.getDataHora().toString();
            linhas.add(linha);
        }
        ArquivoUtil.escreverLinhas(ARQ_AGENDAMENTOS, linhas);
    }

    private void carregarDados() {
        carregarDonos();
        carregarPets();
        carregarAgendamentos();
    }

    private void carregarDonos() {
        List<String> linhas = ArquivoUtil.lerLinhas(ARQ_DONOS);
        for (String linha : linhas) {
            String[] partes = linha.split(";");
            if (partes.length >= 3) {
                String cpf = partes[0];
                String nome = partes[1];
                String email = partes[2];
                Dono d = new Dono(cpf, nome, email);
                donos.put(cpf, d);
            }
        }
    }

    private void carregarPets() {
        List<String> linhas = ArquivoUtil.lerLinhas(ARQ_PETS);
        long maiorId = 0;
        for (String linha : linhas) {
            String[] partes = linha.split(";");
            if (partes.length >= 4) {
                long id = Long.parseLong(partes[0]);
                String nome = partes[1];
                Especies especie = Especies.valueOf(partes[2]);
                String cpfDono = partes[3];
                Dono dono = donos.get(cpfDono);
                if (dono != null) {
                    Pet p = new Pet(id, nome, especie, dono);
                    pets.add(p);
                    dono.adicionarPet(id);
                    if (id > maiorId) {
                        maiorId = id;
                    }
                }
            }
        }

        proximoIdPet = maiorId + 1;
    }

    private void carregarAgendamentos() {
        List<String> linhas = ArquivoUtil.lerLinhas(ARQ_AGENDAMENTOS);
        long maiorId = 0;
        for (String linha : linhas) {
            String[] partes = linha.split(";");
            if (partes.length >= 4) {
                long id = Long.parseLong(partes[0]);
                long idPet = Long.parseLong(partes[1]);
                Procedimento procedimento = Procedimento.valueOf(partes[2]);
                LocalDateTime dataHora = LocalDateTime.parse(partes[3]);
                Agendamento a = new Agendamento(id, idPet, procedimento, dataHora);
                agendamentos.add(a);
                if (id > maiorId) {
                    maiorId = id;
                }
            }
        }
        proximoIdAgendamento = maiorId + 1;
    }
}
