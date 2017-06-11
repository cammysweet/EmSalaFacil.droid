package emsalafacil.emsalafacildroid.enumeradores;

/**
 * Created by camil on 15/04/2017.
 */

public enum Status
{
    ATIVO('A', "Ativo"),
    INATIVO('I', "Inativo");

    /* Atributos normais */
    public char    codigo;
    private String descricao;

    /* Construtores da classe */
    private Status(char pCodigo, String pDescricao)
    {
        codigo = pCodigo;
        descricao = pDescricao;
    }

    /* Método estático de conversão de caractere para enumerado */
    public static Status fromChar(char pValor)
    {
        switch (pValor)
        {
            case 'A':
                return ATIVO;
            case 'I':
                return INATIVO;
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
