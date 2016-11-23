package Classes;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by adaico on 22.11.16.
 */
public class AirlineMan extends DataManager {

    @Override
    public List parse() throws ParserConfigurationException, SAXException, IOException {
        List list = new ArrayList();
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();

        DefaultHandler sax = new DefaultHandler() {
            private Boolean bName = false;
            private Boolean bFoundDate = false;
            private String name = null;
            private String foundDate = null;

            @Override
            public void startDocument() throws SAXException {}

            @Override
            public void startElement(String namespaceURI, String localName, String qName, Attributes atts) throws SAXException {
                if (qName.equalsIgnoreCase("airline")) {
                    String ID = atts.getValue("id");
                    System.out.println("id : " + ID);
                    name = null;
                    foundDate = null;
                } else if (qName.equalsIgnoreCase("name")) {
                    bName = true;
                } else if (qName.equalsIgnoreCase("founddate")) {
                    bFoundDate = true;
                }
            }

            @Override
            public void endElement(String namespaceURI, String localName, String qName) throws SAXException {
                if (qName.equalsIgnoreCase("airline")) {
                    list.add(new Airline(name, foundDate));
                }
            }

            @Override
            public void characters(char[] ch, int start, int length) throws SAXException {
                if (bName) {
                    name = new String(ch, start, length);
                    System.out.println("Name: " + name);
                    bName = false;
                } else if (bFoundDate) {
                    foundDate = new String(ch, start, length);
                    System.out.println("FoundDate: " + foundDate);
                    bFoundDate = false;
                }
            }
        };
        File inputFile = new File("src/Data/Airlines.xml");

        parser.parse(inputFile, sax);
        return(list);
    }

    @Override
    public Boolean functionList(List list, Scanner in) throws IOException, SAXException, ParserConfigurationException {
        int n;
        String name;
        String foundDate;
        int i = Integer.parseInt(in.next());
        switch (i) {
            case 1:
                System.out.print("Enter name:\n");
                name = in.next();
                System.out.print("Enter found date:\n");
                foundDate = in.next();
                list.add(new Airline(name, foundDate));
                return true;
            case 2:
                System.out.print("Enter node number:\n");
                n = Integer.parseInt(in.next());
                System.out.print("Enter name:\n");
                name = in.next();
                System.out.print("Enter found date:\n");
                foundDate = in.next();
                list.add(n - 1, new Airline(name, foundDate));
                return true;
            case 3:
                System.out.print("Enter node number:\n");
                n = Integer.parseInt(in.next());
                list.remove(n - 1);
                return true;
            case 4:
                return false;
            default:
                System.out.print("Wrong value!\n");
                return false;
        }
    }

    @Override
    public void commit(List list) throws ParserConfigurationException, TransformerException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        String[] fields = {"name", "foundDate"};
        String[][] val = new String[list.size()][fields.length];
        for (int n = 0; n < list.size(); n++) {
            val[n] = ((Airline)list.get(n)).getFieldsVal();
        }
        DOMSource source = new DOMSource(createDoc("Airlines", "Airline", fields, val));
        StreamResult result = new StreamResult(new File("src/Data/Airlines2.xml"));
        transformer.transform(source, result);
    }


    public class Airline {
        private String name;
        private String foundDate;

        public Airline(String name, String foundDate) {
            this.name = name;
            this.foundDate = foundDate;
        }

//    Methods *************************************************************************

        public String[] getFieldsVal() {
            String[] val = {name, foundDate};
            return val;
        }

    }
}
