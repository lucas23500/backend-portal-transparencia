package tads.ts.ifam.model;

import java.util.HashMap;
import java.util.Map;

public enum MesEnum {

    JANEIRO(1), FEVEREIRO(2), MARCO(3),
    ABRIL(4), MAIO(5), JUNHO(6),
    JULHO(7), AGOSTO(8), SETEMBRO(9),
    OUTUBRO(10), NOVEMBRO(11), DEZEMBRO(12);

    private final int valor;
    private static final Map<Integer, MesEnum> codigoParaMes = new HashMap<>();

    static {
        for (MesEnum mes : MesEnum.values()) {
            codigoParaMes.put(mes.getValor(), mes);
        }
    }

    MesEnum(int valorOpcao) {
        valor = valorOpcao;
    }

    public int getValor() {
        return valor;
    }

    public static String fromCodigo(int codigo) {
        String mes = String.valueOf(codigoParaMes.get(codigo));
        if (mes == null) {
            throw new IllegalArgumentException("Código não correspondente a nenhum mês: " + codigo);
        }
        return mes;
    }
}
