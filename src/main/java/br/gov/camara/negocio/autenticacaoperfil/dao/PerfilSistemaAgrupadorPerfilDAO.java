/****
 * Copyright 2000, 2001, 2002, 2003, 2004, 2005, 2006, 2007, 2008 C�mara dos Deputados, Brasil
 * 
 * Este arquivo � parte do programa TALENTOS - Banco de Talentos
 *
 * O TALENTOS � um software livre; voc� pode redistribu�-lo e/ou modific�-lo dentro dos termos da Licen�a P�blica Geral GNU como publicada pela Funda��o do Software Livre (FSF); na vers�o 2 da Licen�a.
 *
 * Este programa � distribu�do na esperan�a que possa ser �til, mas SEM NENHUMA GARANTIA; sem uma garantia impl�cita de ADEQUA��O a qualquer MERCADO ou APLICA��O EM PARTICULAR. Veja a Licen�a P�blica Geral GNU para maiores detalhes.
 * 
 * Voc� deve ter recebido uma c�pia da Licen�a P�blica Geral GNU, sob o t�tulo "LICENCA.txt", junto com este programa, se n�o, escreva para a Funda��o do Software Livre(FSF) Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 ***/

package br.gov.camara.negocio.autenticacaoperfil.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import br.gov.camara.exception.CDException;
import br.gov.camara.negocio.DAO;
import br.gov.camara.negocio.autenticacaoperfil.pojo.AgrupadorPerfil;
import br.gov.camara.negocio.autenticacaoperfil.pojo.PerfilSistema;
import br.gov.camara.negocio.autenticacaoperfil.pojo.PerfilSistemaAgrupadorPerfil;
import br.gov.camara.negocio.autenticacaoperfil.pojo.UsuarioSistema;
import br.gov.camara.negocio.exception.DAOException;
import br.gov.camara.negocio.util.Consulta;

/**
 * Classe DAO para a tabela Grupo
 */

public class PerfilSistemaAgrupadorPerfilDAO extends DAO
{
    // Inicializa o log
    private static Log log = LogFactory.getLog(PerfilSistemaAgrupadorPerfilDAO.class);

    /**
     * Construtor sem argumentos
     *
     */
    public PerfilSistemaAgrupadorPerfilDAO()
    {
        super("PerfilSistemaAgrupadorPerfil");
    }

    /**
     * Construtor sem argumentos
     *
     */
    public PerfilSistemaAgrupadorPerfilDAO(String nomeConexao)
    {
        super("PerfilSistemaAgrupadorPerfil", nomeConexao);
    }

    /**
     * Obt�m todos os registros
     * 
     * @return List Contendo os POJOs representando os registro obtidos
     *
     * @throws DAOException se ocorrer algum erro relacionado com o acesso a banco de dados
     * 
     */
    public List obterTodos() throws DAOException
    {
        if (log.isDebugEnabled())
        {
            log.debug("Entrada no metodo");
        }

        // Monta query
        StringBuffer strbConsulta = new StringBuffer();

        strbConsulta.append(" FROM");
        strbConsulta.append(" PerfilSistemaAgrupadorPerfil perfilSistemaAgrupadorPerfil");
        strbConsulta.append(" ORDER BY perfilSistemaAgrupadorPerfil.identificador ASC");

        return super.obter(strbConsulta.toString());
    }

    /**
     * Obt�m um registro a partir da chave
     *
     * @param strChave Chave do registro a ser obtido
     * 
     * @return Object POJO representando o registro obtido
     *
     * @throws DAOException se ocorrer algum erro relacionado com o acesso a banco de dados
     * 
     */
    public Object obterPelaChave(String strChave) throws DAOException
    {
        if (log.isDebugEnabled())
        {
            log.debug("Entrada no metodo");
        }

        // Monta query
        StringBuffer strbConsulta = new StringBuffer();

        strbConsulta.append(" FROM");
        strbConsulta.append(" PerfilSistemaAgrupadorPerfil perfilSistemaAgrupadorPerfil");
        strbConsulta.append(" WHERE");
        strbConsulta.append(" perfilSistemaAgrupadorPerfil.identificador = ");
        strbConsulta.append(strChave);

        return (PerfilSistemaAgrupadorPerfil) obter(strbConsulta.toString()).get(0);
    }

    /**
     * Obt�m o total de registros
     * 
     * @return List Contendo os POJOs representando os registro obtidos
     *
     * @throws DAOException Se ocorrer algum erro relacionado com o acesso a banco de dados
     * 
     */
    public int obterTotalRegistros() throws DAOException
    {
        if (log.isDebugEnabled())
        {
            log.debug("Entrada no metodo");
        }

        // Monta query
        StringBuffer strbConsulta = new StringBuffer();

        strbConsulta.append(" SELECT COUNT(*)");
        strbConsulta.append(" FROM");
        strbConsulta.append(" PerfilSistemaAgrupadorPerfil perfilSistemaAgrupadorPerfil");

        // Recupera os dados
        return obterTotalRegistros(strbConsulta.toString());
    }

    /**
     * Obt�m os registros de determinada p�gina
     * 
     * @return List Contendo os POJOs representando os registro obtidos
     *
     * @throws DAOException Se ocorrer algum erro relacionado com o acesso a banco de dados
     * 
     */
    public List obterPorPagina(int intNumeroPagina, int intMaximoPagina) throws DAOException
    {
        // Monta query
        StringBuffer strbConsulta = new StringBuffer();

        strbConsulta.append(" FROM");
        strbConsulta.append(" PerfilSistemaAgrupadorPerfil perfilSistemaAgrupadorPerfil");
        strbConsulta.append(" ORDER BY perfilSistemaAgrupadorPerfil.identificador ASC");

        // Recupera os dados
        return obterPorPagina(intNumeroPagina, intMaximoPagina, strbConsulta.toString());
    }

    /**
     * Obt�m total de registros da consulta
     *
     * @param Consulta objeto contendo os filtros para consulta
     *  
     * @return List contendo o resultado da pesquisa
     * 
     * @throws DAOException Se ocorrer algum erro relacionado com o acesso a banco de dados
     * 
     */
    public int obterTotalRegistros(Consulta objConsulta) throws DAOException
    {
        if (log.isDebugEnabled())
        {
            log.debug("Entrada no metodo");
        }
        // Declara��es
        int intRetorno = 0;

        try
        {
            String strFiltro = objConsulta.montarFiltro();

            // Monta query
            StringBuffer strbConsulta = new StringBuffer();

            strbConsulta.append(" SELECT COUNT(*)");
            strbConsulta.append(" FROM");
            strbConsulta.append(" PerfilSistemaAgrupadorPerfil perfilSistemaAgrupadorPerfil");

            if (!strFiltro.trim().equals(""))
            {
                strbConsulta.append(" WHERE");
                strbConsulta.append(strFiltro);
            }

            // Recupera os dados
            intRetorno = obterTotalRegistros(strbConsulta.toString());

            // Retorna
            return intRetorno;
        }
        catch (CDException cde)
        {
            throw new DAOException("Ocorreu um erro ao consultar registros na classe " + strNomeClasse, cde);
        }
    }

    /**
     * Exclui um registro
     *
     * @param Object POJO representando o objeto a ser exclu�do
     * 
     * @return String Contendo a consulta de exclus�o 
     * 
     * @throws DAOException Se ocorrer algum erro relacionado com o acesso a banco de dados
     * 
     */
    public String excluirImpl(Object objPerfilSistemaAgrupadorPerfil) throws DAOException
    {
        if (log.isDebugEnabled())
        {
            log.debug("Entrada no metodo");
        }

        // Monta query
        StringBuffer strbConsulta = new StringBuffer();

        strbConsulta.append(" FROM");
        strbConsulta.append(" PerfilSistemaAgrupadorPerfil perfilSistemaAgrupadorPerfil");
        strbConsulta.append(" WHERE perfilSistemaAgrupadorPerfil.perfilSistema.identificador = ");
        strbConsulta.append(((PerfilSistemaAgrupadorPerfil) objPerfilSistemaAgrupadorPerfil).getPerfilSistema().getIdentificador().toString());
        strbConsulta.append(" AND perfilSistemaAgrupadorPerfil.agrupadorPerfil.identificador = ");
        strbConsulta.append(((PerfilSistemaAgrupadorPerfil) objPerfilSistemaAgrupadorPerfil).getAgrupadorPerfil().getIdentificador().toString());

        return strbConsulta.toString();
    }

    /**
     * Obt�m total de registros de perfis disponiveis
     *
     * @return List contendo o resultado da pesquisa
     * 
     * @throws DAOException Se ocorrer algum erro relacionado com o acesso a banco de dados
     * 
     */
    public int obterTotalRegistrosPerfisDisponiveis(PerfilSistemaAgrupadorPerfil objPerfilSistemaAgrupadorPerfil, UsuarioSistema objUsuarioSistema) throws DAOException
    {
        if (log.isDebugEnabled())
        {
            log.debug("Entrada no metodo");
        }
        // Declara��es
        int intRetorno = 0;

        try
        {
            StringBuffer strbConsulta = new StringBuffer();
            strbConsulta.append(" SELECT COUNT(*) ");
            strbConsulta.append(obterHQLPerfisDisponiveis(objPerfilSistemaAgrupadorPerfil, objUsuarioSistema));

            // Recupera os dados
            intRetorno = obterTotalRegistros(strbConsulta.toString());

            // Retorna
            return intRetorno;
        }
        catch (CDException cde)
        {
            throw new DAOException("Ocorreu um erro ao consultar registros na classe " + strNomeClasse, cde);
        }
    }

    public List obterPerfisDisponiveis(PerfilSistemaAgrupadorPerfil objPerfilSistemaAgrupadorPerfil, UsuarioSistema objUsuarioSistema) throws DAOException
    {
        if (log.isDebugEnabled())
        {
            log.debug("Entrada no metodo");
        }

        // Recupera os dados
        StringBuffer strbConsulta = new StringBuffer();

        strbConsulta.append(obterHQLPerfisDisponiveis(objPerfilSistemaAgrupadorPerfil, objUsuarioSistema));
        strbConsulta.append(" ORDER BY perfilSistema.nome ASC");

        return obter(strbConsulta.toString());
    }

    public List obterPerfisDisponiveisPorPagina(PerfilSistemaAgrupadorPerfil objPerfilSistemaAgrupadorPerfil, UsuarioSistema objUsuarioSistema, int intPagina, int intTotalRegistros) throws DAOException
    {
        if (log.isDebugEnabled())
        {
            log.debug("Entrada no metodo");
        }

        StringBuffer strbConsulta = new StringBuffer();

        strbConsulta.append(obterHQLPerfisDisponiveis(objPerfilSistemaAgrupadorPerfil, objUsuarioSistema));
        strbConsulta.append(" ORDER BY perfilSistema.nome ASC");

        return obterPorPagina(intPagina, intTotalRegistros, strbConsulta.toString());
    }

    private String obterHQLPerfisDisponiveis(PerfilSistemaAgrupadorPerfil objPerfilSistemaAgrupadorPerfil, UsuarioSistema objUsuarioSistema)
    {
        if (log.isDebugEnabled())
        {
            log.debug("Entrada no metodo");
        }

        // Monta query
        StringBuffer strbConsulta = new StringBuffer();

        strbConsulta.append(" FROM PerfilSistema perfilSistema");
        strbConsulta.append(" WHERE perfilSistema.sistema.identificador = ");
        strbConsulta.append(objPerfilSistemaAgrupadorPerfil.getPerfilSistema().getSistema().getIdentificador().toString());
        strbConsulta.append(" AND perfilSistema.identificador NOT IN ( ");
        strbConsulta.append("   SELECT perfilSistemaAgrupadorPerfil.perfilSistema.identificador ");
        strbConsulta.append("   FROM PerfilSistemaAgrupadorPerfil perfilSistemaAgrupadorPerfil ");
        strbConsulta.append("   WHERE perfilSistemaAgrupadorPerfil.agrupadorPerfil.identificador = ");
        strbConsulta.append(objPerfilSistemaAgrupadorPerfil.getAgrupadorPerfil().getIdentificador().toString());
        strbConsulta.append(" ) ");

        // Parte do HQL que filtra os sistemas em dos gestores
        /*strbConsulta.append(" AND ( ");
        strbConsulta.append("    perfilSistema.sistema.identificador IN ( ");
        strbConsulta.append("       SELECT gestorSistema.sistema.identificador ");
        strbConsulta.append("       FROM GestorSistema gestorSistema ");
        strbConsulta.append("       WHERE gestorSistema.usuarioSistema.identificador =  ");
        strbConsulta.append(objUsuarioSistema.getIdentificador().intValue());
        strbConsulta.append("    ) ");
        strbConsulta.append("    OR perfilSistema.identificador IN ( ");
        strbConsulta.append("       SELECT usuarioPerfilSistema.perfilSistema.identificador ");
        strbConsulta.append("       FROM UsuarioPerfilSistema usuarioPerfilSistema ");
        strbConsulta.append("       WHERE usuarioPerfilSistema.usuarioSistema.identificador = ");
        strbConsulta.append(objUsuarioSistema.getIdentificador().intValue());
        strbConsulta.append("       AND usuarioPerfilSistema.indicativoGestor = 'S' ");
        strbConsulta.append("    ) ");
        strbConsulta.append(" ) ");*/

        return strbConsulta.toString();
    }

    /**
     * Obt�m total de registros de perfis selecionados
     *
     * @return List contendo o resultado da pesquisa
     * 
     * @throws DAOException Se ocorrer algum erro relacionado com o acesso a banco de dados
     * 
     */
    public int obterTotalRegistrosPerfisSelecionados(AgrupadorPerfil objAgrupadorPerfil) throws DAOException
    {
        if (log.isDebugEnabled())
        {
            log.debug("Entrada no metodo");
        }
        // Declara��es
        int intRetorno = 0;

        try
        {
            StringBuffer strbConsulta = new StringBuffer();
            strbConsulta.append(" SELECT COUNT(*) ");
            strbConsulta.append("   FROM PerfilSistemaAgrupadorPerfil perfilSistemaAgrupadorPerfil ");
            strbConsulta.append("   WHERE perfilSistemaAgrupadorPerfil.agrupadorPerfil.identificador = ");
            strbConsulta.append(objAgrupadorPerfil.getIdentificador().toString());

            // Recupera os dados
            intRetorno = obterTotalRegistros(strbConsulta.toString());

            // Retorna
            return intRetorno;
        }
        catch (CDException cde)
        {
            throw new DAOException("Ocorreu um erro ao consultar registros na classe " + strNomeClasse, cde);
        }
    }

    public List obterPerfisSelecionadosPorPagina(AgrupadorPerfil objAgrupadorPerfil, int intPagina, int intTotalRegistros) throws DAOException
    {
        if (log.isDebugEnabled())
        {
            log.debug("Entrada no metodo");
        }

        // Monta query
        StringBuffer strbConsulta = new StringBuffer();

        strbConsulta.append("   SELECT perfilSistemaAgrupadorPerfil.perfilSistema ");
        strbConsulta.append("   FROM PerfilSistemaAgrupadorPerfil perfilSistemaAgrupadorPerfil ");
        strbConsulta.append("   WHERE perfilSistemaAgrupadorPerfil.agrupadorPerfil.identificador = ");
        strbConsulta.append(objAgrupadorPerfil.getIdentificador().toString());
        strbConsulta.append(" ORDER BY perfilSistemaAgrupadorPerfil.perfilSistema.sistema.sigla, perfilSistemaAgrupadorPerfil.perfilSistema.nome");

        // Recupera os dados
        return obterPorPagina(intPagina, intTotalRegistros, strbConsulta.toString());
    }

    public List obterPeloPerfil(PerfilSistema objPerfilSistema) throws DAOException
    {
        if (log.isDebugEnabled())
        {
            log.debug("Entrada no metodo");
        }

        // Monta query
        StringBuffer strbConsulta = new StringBuffer();

        strbConsulta.append("   SELECT perfilSistemaAgrupadorPerfil ");
        strbConsulta.append("   FROM PerfilSistemaAgrupadorPerfil perfilSistemaAgrupadorPerfil ");
        strbConsulta.append("   WHERE perfilSistemaAgrupadorPerfil.perfilSistema.identificador = ");
        strbConsulta.append(objPerfilSistema.getIdentificador().toString());
        strbConsulta.append(" ORDER BY perfilSistemaAgrupadorPerfil.agrupadorPerfil.nome");

        // Recupera os dados
        return obter(strbConsulta.toString());
    }

}
