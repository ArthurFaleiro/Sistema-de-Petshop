// Enum: Especies
// O que faz:
// Lista de espécies suportadas no sistema, o fromString é para interpretar texto.
//
// Observações:
//  - fromString facilita conversões a partir de CSV ou entrada de texto, tratando acentos/variações.
//  - Se novo tipo aparecer, manter OUTRO como fallback evita erro fatal.


public enum Especies {
    CACHORRO,
    GATO,
    PASSARO,
    COELHO,
    REPTIL,
    OUTRO;

    public static Especies fromString(String texto) {
        if (texto == null) {
            return OUTRO;
        }
        String valor = texto.trim().toUpperCase();

        switch (valor) {
            case "CACHORRO":
                return CACHORRO;
            case "GATO":
                return GATO;
            case "PASSARO":
            case "PÁSSARO":
                return PASSARO;
            case "COELHO":
                return COELHO;
            case "REPTIL":
            case "RÉPTIL":
                return REPTIL;
            default:
                return OUTRO;
        }
    }

    @Override
    public String toString() {
        String nome = name().toLowerCase();
        String primeiraLetra = nome.substring(0, 1).toUpperCase();
        return primeiraLetra + nome.substring(1);
    }
}
