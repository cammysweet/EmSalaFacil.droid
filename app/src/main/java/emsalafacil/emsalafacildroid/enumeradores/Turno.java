package emsalafacil.emsalafacildroid.enumeradores;

/**
 * Created by camil on 10/04/2017.
 */

public enum  Turno
{
    /* Turnos possíveis de uma turma */
    MATUTINO  ('M', "Matutino"),
    VESPERTINO('V', "Vespertino"),
    NOTURNO   ('N', "Noturno");

    /* Atributos normais */
    public char    codigo;
    private String descricao;

    /* Construtores da classe */
    private Turno(char pCodigo, String pDescricao)
    {
        codigo = pCodigo;
        descricao = pDescricao;
    }

    /* Método estático de conversão de caractere para enumerado */
    public static Turno fromChar(char pValor)
    {
        switch (pValor)
        {
            case 'M':
                return MATUTINO;
            case 'V':
                return VESPERTINO;
            case 'N':
                return NOTURNO;
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
