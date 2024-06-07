package com.metaxiii.fr;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class App {

  public static final String FRUIT_XML =
    """
          <fruit>
                  <name>Apple</name>
                  <color>Red</color>
                  <weight unit="grams">150</weight>
                  <sweetness>7</sweetness>
              </fruit>""";

  public static Document getDocument() throws SAXException, IOException, ParserConfigurationException {
    final DocumentBuilderFactory factory = DocumentBuilderFactory.newDefaultInstance();
    return factory.newDocumentBuilder().parse(new InputSource(new StringReader(FRUIT_XML)));
  }

  public static String toString(final Document document) throws TransformerException {
    final TransformerFactory transformerFactory = TransformerFactory.newDefaultInstance();
    final Transformer transformer = transformerFactory.newTransformer();
    final StringWriter stringWriter = new StringWriter();
    transformer.transform(new DOMSource(document), new StreamResult(stringWriter));
    return stringWriter.toString().replace("\r\n", "\n");
  }

  public static String toStringWithOptions(final Document document) throws TransformerException {
    final Transformer transformer = getTransformer();
    transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
    transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

    final StringWriter stringWriter = new StringWriter();
    transformer.transform(new DOMSource(document), new StreamResult(stringWriter));
    return stringWriter.toString().replace("\r\n", "\n");
  }

  private static Transformer getTransformer() throws TransformerConfigurationException {
    final TransformerFactory transformerFactory = TransformerFactory.newDefaultInstance();
    return transformerFactory.newTransformer();
  }
}
