package emsalafacil.emsalafacildroid.enumeradores;

/**
 * Created by camil on 15/04/2017.
 */

public enum TipoUsuario
{
    /* Turnos possíveis de uma turma */
    ALUNO('A', "Aluno"),
    PROFESSOR('P', "Professor"),
    FUNCIONARIO('F', "Funcionario");

    /* Atributos normais */
    public char    codigo;
    private String descricao;

    /* Construtores da classe */
    private TipoUsuario(char pCodigo, String pDescricao)
    {
        codigo = pCodigo;
        descricao = pDescricao;
    }

    /* Método estático de conversão de caractere para enumerado */
    public static TipoUsuario fromChar(char pValor)
    {
        switch (pValor)
        {
            case 'A':
                return ALUNO;
            case 'P':
                return PROFESSOR;
            case 'F':
                return FUNCIONARIO;
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
