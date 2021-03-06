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

/**
 * 
 */
package br.gov.camara.hibernate.interceptor.session;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import br.gov.camara.hibernate.exception.HibernateExceptionCD;

/**
 * @author P_6414
 *
 */
public abstract class SessionInterceptor
{
    /**
     * Executa este m�todo logo ap�s obter a sess�o do Hibernate
     * @param sessao Sess�o recuperada do SessionFactory
     * @return retorna a sess�o tratada por este m�todo
     * @throws HibernateException
     */
    public Session executarAposObterNovaSessao(Session sessao) throws HibernateExceptionCD
    {
        return sessao;
    }

    /**
     * Executa este m�todo logo antes de fechar a sess�o do Hibernate
     * @param sessao Sess�o que ser� fechada a seguir
     * @return retorna a sess�o tratada por este m�todo
     * @throws HibernateException
     */
    public Session executarAntesFecharSessao(Session sessao) throws HibernateExceptionCD
    {
        return sessao;
    }
}
