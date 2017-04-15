package emsalafacil.emsalafacildroid.enumeradores;

/**
 * Created by camil on 15/04/2017.
 */

public enum TipoSala
{
    SALA('S', "Sala"),
    LABORATORIO('L', "Laboratorio"),
    AUDITORIO('A', "Auditorio");

    /* Atributos normais */
    public char    codigo;
    private String descricao;

    /* Construtores da classe */
    private TipoSala(char pCodigo, String pDescricao)
    {
        codigo = pCodigo;
        descricao = pDescricao;
    }

    /* Método estático de conversão de caractere para enumerado */
    public static TipoSala fromChar(char pValor)
    {
        switch (pValor)
        {
            case 'S':
                return SALA;
            case 'L':
                return LABORATORIO;
            case 'A':
                return AUDITORIO;
            default:
                return null;
        }
    }

    /* Método de conversão do enumerado para caractere */
    public char toChar()
    {
        return codigo;
    }

    /* Métodos da classe Object */
    @Override
    public String toString()
    {
        return descricao;
    }
}
