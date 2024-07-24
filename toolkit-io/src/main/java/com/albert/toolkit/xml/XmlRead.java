package com.albert.toolkit.xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.io.IOException;

/**
 * 读取xml文件
 *
 * @author hoyo
 * @since 2024-07-20
 */
public class XmlRead {

    /**
     * DOM方式读取xml
     * @param xmlFile xml文件绝对路径
     * @return
     * @throws ParserConfigurationException
     * @throws IOException
     * @throws SAXException
     */
    public Document getDocument(String xmlFile) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        return documentBuilder.parse(new File(xmlFile));
    }


    /**
     * 根据节点名称和节点值的确定节点的索引
     * @param document
     * @param xPathElementName 节点名称
     * @return
     */
    public int getNodeXPathIndex(Document document, String xPathElementName) {
        NodeList nodeList = document.getElementsByTagName("fieldname");
        int xPathIndex = -1;

        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (xPathElementName.equals(node.getTextContent())) {
                xPathIndex = i + 1;
                break;
            }
        }

        return xPathIndex;
    }

    /**
     * 使用DOM读取xml后使用SAX修改xml文件
     * @param document
     * @param xPathIndex
     * @param newTextContent
     */
    public void fixTextContent(Document document, int xPathIndex, String newTextContent) {
        XPathFactory xPathFactory = XPathFactory.newInstance();
        XPath xPath = xPathFactory.newXPath();
        Element element = document.getDocumentElement();
    }
}
