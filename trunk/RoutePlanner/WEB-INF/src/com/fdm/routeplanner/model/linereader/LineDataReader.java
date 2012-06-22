package com.fdm.routeplanner.model.linereader;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.springframework.stereotype.Service;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.fdm.routeplanner.exception.InvalidNetWorkException;

@Service("lineDataReader")
final class LineDataReader extends DefaultHandler implements _LineDataReader {

	@SuppressWarnings("rawtypes")
	private List stations;
    private StringBuffer textBuffer;

    /**
	 * gets list of stations for given file of Underground line data.
	 *
	 * @param fileName	pathname of data file to parse
	 * @return List 	list of station names
	 */
	@SuppressWarnings("rawtypes")
	public final List getLineData(String fileName) {
        try {
        	File file = new File(fileName);
        	getLineData(file);
        } catch (Throwable t) {
            throw new IllegalArgumentException(fileName + " caused " + t.toString());
        }
        return stations;
    }

	/**
	 * gets list of stations for given file of Underground line data.
	 *
	 * @param file		File to pass
	 * @return List	list of station names
	 */
	@SuppressWarnings("rawtypes")
	public final List getLineData(File file) {
		SAXParserFactory factory = SAXParserFactory.newInstance();
		try {
			SAXParser saxParser = factory.newSAXParser();
			saxParser.parse(file, this);
		} catch (Throwable t) {
			throw new IllegalArgumentException(file.getName() + " caused " + t.toString());
		}
		return stations;
	}

    
	@SuppressWarnings("rawtypes")
	public final void startElement(String namespaceURI, String simpleName,
        String qualifiedName, Attributes attrs) throws SAXException {
        if (qualifiedName == "line") {
            stations = new ArrayList();
        }
    }


	public final void characters(char[] buf, int offset, int len)
        throws SAXException {
        String s = new String(buf, offset, len);
        if (textBuffer == null) {
            textBuffer = new StringBuffer(s);
        } else {
            textBuffer.append(s);
        }
    }

	
	@SuppressWarnings("unchecked")
	public final void endElement(String namespaceURI, String simpleName,
        String qualifiedName) throws SAXException {
        if (qualifiedName == "station") {
            stations.add(new String(textBuffer).trim());
            textBuffer = null;
        }
    }

	
    @SuppressWarnings("rawtypes")
	public final Map getNetworkData(InputStream s) throws InvalidNetWorkException
    {
	    SAXBuilder saxbuilder = new SAXBuilder();
		try {
			return generateNetwork( saxbuilder.build(s) );
		} catch (JDOMException e) {
			throw new InvalidNetWorkException("The NetWork File is Invalid");
		} catch (IOException e) {
			throw new InvalidNetWorkException("The NetWork File is Invalid");
		}   
    }

    
	@SuppressWarnings("rawtypes")
	public final Map getNetworkData(File networkFile) throws InvalidNetWorkException
    {
		SAXBuilder saxbuilder = new SAXBuilder();
		try {
			return generateNetwork( saxbuilder.build(networkFile) );
		} catch (JDOMException e) {
			throw new InvalidNetWorkException(e,"The NetWork File is Invalid");
		} catch (IOException e) {
			throw new InvalidNetWorkException(e,"The NetWork File is Invalid");
		}
    }

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private final Map generateNetwork(Document doc) {
		Map map = new HashMap();
		List lines, stations = null;
		String currentLineName, currentStationName = null;
		Element currentLine = null, currentStation = null;
		Element root = doc.getRootElement();
		lines = root.getChildren();

		for (Iterator iter = lines.iterator(); iter.hasNext();) {
			currentLine = (Element) iter.next();
			currentLineName = currentLine.getAttribute("name").getValue();
			stations = currentLine.getChildren();
			List stationNames = new ArrayList();
			for (Iterator iterator = stations.iterator();
				iterator.hasNext();
				) {
				currentStation = (Element) iterator.next();
				currentStationName = currentStation.getText();
				stationNames.add(currentStationName);
			}
			map.put(currentLineName, stationNames);
		}

		return map;
	}
}
