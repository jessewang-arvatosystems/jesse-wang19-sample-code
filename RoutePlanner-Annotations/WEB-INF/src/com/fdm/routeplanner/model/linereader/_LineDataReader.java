package com.fdm.routeplanner.model.linereader;

import java.io.File;
import java.io.InputStream;
import java.util.Map;

import org.xml.sax.*;

import com.fdm.routeplanner.exception.InvalidNetWorkException;

/**
 * <h2>LineDataReader</h2>
 * 
 * Simple means of reading-in XML format data files for station listings.
 *
 * Parses either single line of station information (using getLineData()) or
 * complete network data file (using getNetworkData()).
 *
 * @author stuartj
 */
public interface _LineDataReader {

	/**
	 * 
	 * @see org.xml.sax.ContentHandler#startElement(java.lang.String, java.lang.String, java.lang.String, org.xml.sax.Attributes)
	 * 
	 * @param namespaceURI
	 * @param simpleName
	 * @param qualifiedName
	 * @param attrs
	 * @throws SAXException
	 */
	public abstract void startElement(String namespaceURI, String simpleName, String qualifiedName, Attributes attrs) throws SAXException;

	/**
	 * @see org.xml.sax.ContentHandler#characters(char[], int, int)
	 * 
	 * @param buf
	 * @param offset
	 * @param len
	 * @throws SAXException
	 */
	public abstract void characters(char[] buf, int offset, int len) throws SAXException;
	
	/**
	 * @see org.xml.sax.ContentHandler#endElement(java.lang.String, java.lang.String, java.lang.String)
	 * 
	 * @param namespaceURI
	 * @param simpleName
	 * @param qualifiedName
	 * @throws SAXException
	 */
	public abstract void endElement(String namespaceURI, String simpleName, String qualifiedName) throws SAXException;
   
	/**
	 * Parses data file to return information about complete network, as a Map where keys are line names
	 * on network, and corresponding value objects are Lists of strings representing stations on those lines.
	 *
	 * @param s	InputStream from file holding network data (NB: for use when bundling files).
	 * @return Map	the Map of Lists holding station name sequences as Strings.
	 * @throws InvalidNetWorkException
	 */
	@SuppressWarnings("rawtypes")
	public abstract Map getNetworkData(InputStream s) throws InvalidNetWorkException;
	
	/**
     * Parses data file to return information about complete network, as a Map where keys are line names
     * on network, and corresponding value objects are Lists of strings representing stations on those lines.
     *
	 * @param s File holding network data .
	 * @return Map	the Map of Lists holding station name sequences as Strings.
	 * @throws InvalidNetWorkException
	 */
	@SuppressWarnings("rawtypes")
	public abstract Map getNetworkData(File networkFile) throws InvalidNetWorkException;
	
}