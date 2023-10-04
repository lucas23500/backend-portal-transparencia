package tads.ts.ifam.model;

import java.util.HashMap;
import java.util.Map;

public enum OrgaoEnum {

    ADAF(158), ADS(117), APOSENTADOS_EXECUTIVO(31),
    APOSENTADOS_ALEAM(70), APOSENTADOS_PGJ(312), APOSENTADOS_TCE(342),
    TJA(303), ARSEPAM(115), CASA_CIVIL_DO_GOVERNO(159),
    CASA_MILITAR(94), CB_CIVIS(113), CBMAM(91),
    CETAM(150), CGE(154), CSC(111),
    DETRAN(63), ERGSP(27), FAPEAM(95),
    FCECON(34), FEH(1111111111), FEI(171),
    FHAJ(122), FHEMOAM(37), FMT_AM(86),
    FUHAM(32), FUNDACAO_AMAZONPREV(233), FUNDACAO_VILA_OLIMPICA(22222222),
    FUNTEC(93), FVS(124), IDAM(43),
    JUCEA(74), OUVIDORIA_GERAL(333333333), PENSIONISTAS_EXECUTIVO(231),
    PENSIONISTAS_ALEAM(231070), PENSIONISTAS_PGJ(373), PENSIONISTAS_TCE(358),
    PENSIONISTAS_TJA(306), PGE(163), PM_ATIVOS(88),
    PM_CIVIS(85), POLICIA_CIVIL(23), PROCON(168),
    PRODAM(600), SEAD(13), SEAD_PENSAO_ESPECIA_I(501),
    SEAD_PENSAO_ESPECIA_II(503), IPAAM(73), IPEM_AM(39),
    SEAD_PENSAO_HANSENIANOS(502), SEAP(128), SEAS(60),
    SEC(44), SECOM(126), SECT(61),
    SEDECTI(10), SEDEL(234), SEDUC(25),
    SEFAZ(7), SEIND(44444444), SEINFRA(59),
    SEJEL(55555555), SEJUSC(8), SEMA(65),
    SEPED(66666666), SEPROR(66), SERFI(170),
    SERGB(77777777), SES(2), SETRAB(88888888),
    SGVG(161), SNPH(118), SRMM(9999999),
    SSP(45), SUHAB(96), UEA(120),
    UGPE(121);


    private final int valor;
    private static final Map<Integer, OrgaoEnum> codigoParaOrgao = new HashMap<>();

    static {
        for (OrgaoEnum orgao : OrgaoEnum.values()) {
            codigoParaOrgao.put(orgao.getValor(), orgao);
        }
    }

    OrgaoEnum(int valorOpcao) {
        valor = valorOpcao;
    }

    public int getValor() {
        return valor;
    }

    public static String fromCodigo(int codigo) {
        String orgao = String.valueOf(codigoParaOrgao.get(codigo));
        if (orgao == null) {
            throw new IllegalArgumentException("Código não correspondente a nenhum órgão: " + codigo);
        }
        return orgao;
    }

}
