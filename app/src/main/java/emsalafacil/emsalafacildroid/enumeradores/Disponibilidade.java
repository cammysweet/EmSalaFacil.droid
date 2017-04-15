package emsalafacil.emsalafacildroid.enumeradores;

/**
 * Created by camil on 15/04/2017.
 */

public enum Disponibilidade
{
    SIM('S', "Sim"),
    NAO('N', "Nao");

    /* Atributos normais */
    public char    codigo;
    private String descricao;

    /* Construtores da classe */
    private Disponibilidade(char pCodigo, String pDescricao)
    {
        codigo = pCodigo;
        descricao = pDescricao;
    }

    /* Método estático de conversão de caractere para enumerado */
    public static Disponibilidade fromChar(char pValor)
    {
        switch (pValor)
        {
            case 'S':
                return SIM;
            case 'N':
                return NAO;
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
