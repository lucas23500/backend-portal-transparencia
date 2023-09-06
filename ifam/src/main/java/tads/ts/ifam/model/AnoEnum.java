package tads.ts.ifam.model;

public enum AnoEnum {

    JANEIRO(1), FEVEREIRO(2),MARCO(3),
    ABRIL(4),MAIO(5),JUNHO(6),
    JULHO(7),AGOSTO(8),SETEMBRO(9),
    OUTUBRO(10),NOVEMBRO(11),DEZEMBRO(12);





    private final int valor;

    AnoEnum(int valorOpcao){
        valor = valorOpcao;
    }
    public int getValor(){
        return valor;
    }

    public static String anoEnum(int codigo) {
        for (AnoEnum orgao : AnoEnum.values()) {
            if (orgao.getValor() == codigo) {
                return orgao.name();
            }
        }
        throw new IllegalArgumentException("Código não correspondente a nenhuma data: " + codigo);
    }
}
