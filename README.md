Linguagem: Java

Tecnologias: Java Swing (GUI), Arquivamento (I/O)

Conceito: CRUD (Create, Read, Update, Delete) com persistência em arquivo.

Um sistema Desktop robusto para gestão de serviços veterinários e estética animal.

📂 Arquitetura do Projeto
Plaintext
Sistema-de-Petshop/
├── Main.java                
# Inicialização do sistema
├── SistemaPetshop.java      
# Lógica de controle e listas
├── JanelaPrincipal.java     
# JFrame (Interface Visual)
├── Agendamento.java         
# Modelo de dados do serviço
├── Pessoa.java              
# Superclasse (Abstração)
├── Dono.java                
# Subclasse de Pessoa (Herança)
├── Pet.java                 
# Modelo do Animal
├── Procedimento.java        
# Tipos de serviço (Banho, Tosa, etc)
└── ArquivoUtil.java         
# Persistência de dados (TXT/DAT)

🛠️ Diferenciais Técnicos
Herança: A classe Dono estende Pessoa, demonstrando reutilização de código.

Interface Gráfica: Uso de JTable para exibição da agenda e JOptionPane para interação com o usuário.

Persistência: Implementação de leitura e escrita de arquivos para que os dados não sejam perdidos ao fechar o programa.
---
Desenvolvido por [Arthur Faleiro](https://github.com/ArthurFaleiro)
