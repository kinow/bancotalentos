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

package br.gov.camara.biblioteca.util;

import java.math.BigInteger;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <P> Calculates the CRC32 - 32 bit Cyclical Redundancy Check
 * <P> This check is used in numerous systems to verify the integrity
 * of information.  It's also used as a hashing function.  Unlike a regular
 * checksum, it's sensitive to the order of the characters.
 * It produces a 32 bit (Java <CODE>int</CODE>.
 * <P>
 * This Java programme was translated from a C version I had written.
 * <P> This software is in the public domain.
 *
 * <P>
 * When calculating the CRC32 over a number of strings or byte arrays
 * the previously calculated CRC is passed to the next call.  In this
 * way the CRC is built up over a number of items, including a mix of
 * strings and byte arrays.
 * <P>
 * <PRE>
 * CRC32 crc = new CRC32();
 * int crcCalc = crc.crc32("Hello World");
 * crcCalc = crc.crc32("How are you?", crcCalc);
 * crcCalc = crc.crc32("I'm feeling really good, how about you?", crcCalc);
 * </PRE>
 * The line <CODE>int crcCalc = crc.crc32("Hello World");</CODE> is equivalent
 * to <CODE>int crcCalc = crc.crc32("Hello World", -1);</CODE>.
 * When starting a new CRC calculation the "previous crc" is set to
 * 0xFFFFFFFF (or -1).
 * <P>
 * The table only needs to be built once.  You may use it to generate
 * many CRC's.
 * <CODE>
 *
 * @author Michael Lecuyer (mjl@theorem.com)
 *
 * @version 1.1 August 11, 1998
 *
 */
public class CRC32
{
    /**
     * Logger for this class
     */
    private static final Log log = LogFactory.getLog(CRC32.class);

    int CRCTable[]; // CRC Lookup table

    /**
     * Tests CRC32.
     * <BR>Eg: <SAMP> java CRC32 "Howdy, I'm A Cowboy"
     *
     * @param args the string used to calculate the CRC32
     */
    public static void main(String args[])
    {
        if (args.length == 0)
        {
            System.out.println("Usage CRC32 [string to calculate CRC32]");
            System.exit(1);
        }

        System.out.println("CRC for [" + args[0] + "] is " + new CRC32().crc32(args[0]));
    }

    private int crc; // currently calculated crc (used in conversion to byte array)

    /**
     * Constructor constructs the lookup table.
     * 
     */
    public CRC32()
    {
        buildCRCTable();
    }

    /**
     * Just build a plain old fashioned table based on good, old fashioned
     * values like the CRC32_POLYNOMIAL.  The table is of a fixed size.
     */
    private void buildCRCTable()
    {
        final int CRC32_POLYNOMIAL = 0xEDB88320;

        int i, j;
        int crc;

        CRCTable = new int[256];

        for (i = 0; i <= 255; i++)
        {
            crc = i;
            for (j = 8; j > 0; j--)
            {
                if ((crc & 1) == 1)
                    crc = (crc >>> 1) ^ CRC32_POLYNOMIAL;
                else
                    crc >>>= 1;
            }
            CRCTable[i] = crc;
        }
    }

    /**
     * Convenience mithod for generating a CRC from a single <CODE>String</CODE>.
     *
     * @param buffer string to generate the CRC32 
     *
     * @return 32 bit CRC
     */
    public int crc32(String buffer)
    {
        return crc32(buffer, 0xFFFFFFFF);
    }

    /**
     * Convenience method for generating a CRC from a <CODE>byte</CODE> array.
     *
     * @param buffer byte array to generate the CRC32 
     *
     * @return 32 bit CRC
     */
    public int crc32(byte buffer[])
    {
        return crc32(buffer, 0xFFFFFFFF);
    }

    /**
     * Convenience method for generating a CRC from a series of <CODE>String</CODE>'s.
     *
     * @param buffer string to generate the CRC32 
     * @param crc previously generated CRC32.
     *
     * @return 32 bit CRC
     */
    public int crc32(String buffer, int crc)
    {
        return crc32(buffer.getBytes(), crc);
    }

    /**
     * Convenience method for generating a CRC from a series of <CODE>byte</CODE> arrays.
     *
     * @param buffer byte array to generate the CRC32 
     * @param crc previously generated CRC32.
     *
     * @return 32 bit CRC
     */
    public int crc32(byte buffer[], int crc)
    {
        return crc32(buffer, 0, buffer.length, crc);
    }

    /**
     * General CRC generation function.
     *
     * @param buffer byte array to generate the CRC32 
     * @param start byte start position 
     * @param count number of byte's to include in CRC calculation 
     * @param crc previously generated CRC32.
     *
     * @return 32 bit CRC
     */
    public int crc32(byte buffer[], int start, int count, int lastcrc)
    {
        int temp1, temp2;
        int i = start;

        crc = lastcrc;

        while (count-- != 0)
        {
            temp1 = crc >>> 8;
            temp2 = CRCTable[(crc ^ buffer[i++]) & 0xFF];
            crc = temp1 ^ temp2;
        }

        return crc;
    }

    /**
     * Convert CRC to a BigEndian <code>byte</code> byte array (4 bytes)
     *
     * @return 4 bytes of BigEndian CRC.
     */
    public byte[] toBytes()
    {
        return new BigInteger(new Integer(crc).toString()).toByteArray();
    }

    /**
     *  Gera um semi CRC a partir dos bytes recebidos
     * 
     * return long contendo o CRC
     */
    public long semiCRC(byte[] bytSource)
    {
        if (log.isDebugEnabled())
        {
            log.debug("semiCRC(byte[] bytSource=" + bytSource + ") - start");
        }

        long lngCRC = 0;
        long lngTemp;
        long lngSize = bytSource.length;
        final long lngDivisor = 32768;

        for (int i = 0; i <= lngSize; i++)
        {
            if (Math.floor(lngCRC / lngDivisor) > 0)
            {
                lngTemp = 1;
            }
            else
            {
                lngTemp = 0;
            }
            if (i < lngSize)
            {
                lngCRC = (((lngCRC * 2) & 0xffff) + lngTemp) ^ ((int) bytSource[i]);
            }
            else
            {
                lngCRC = (((lngCRC * 2) & 0xffff) + lngTemp) ^ 0;
            }
        }

        if (log.isDebugEnabled())
        {
            log.debug("semiCRC(byte[] bytSource=" + bytSource + ") - end - return value=" + lngCRC);
        }
        return lngCRC;
    }
}