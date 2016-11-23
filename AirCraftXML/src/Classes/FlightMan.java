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
public class FlightMan extends DataManager {

    @Override
    public List parse() throws ParserConfigurationException, SAXException, IOException {
        List list = new ArrayList();
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();

        DefaultHandler sax = new DefaultHandler() {
            private Boolean bDurationM = false;
            private Boolean bDepTime = false;
            private String durationM = null;
            private String depTime = null;

            @Override
            public void startDocument() throws SAXException {}

            @Override
            public void startElement(String namespaceURI, String localName, String qName, Attributes atts) throws SAXException {
                if (qName.equalsIgnoreCase("flight")) {
                    String ID = atts.getValue("id");
                    System.out.println("id : " + ID);
                    durationM = null;
                    depTime = null;
                } else if (qName.equalsIgnoreCase("durationm")) {
                    bDurationM = true;
                } else if (qName.equalsIgnoreCase("deptime")) {
                    bDepTime = true;
                }
            }

            @Override
            public void endElement(String namespaceURI, String localName, String qName) throws SAXException {
                if (qName.equalsIgnoreCase("flight")) {
                    list.add(new Flight(durationM, depTime));
                }
            }

            @Override
            public void characters(char[] ch, int start, int length) throws SAXException {
                if (bDurationM) {
                    durationM = new String(ch, start, length);
                    System.out.println("DurationM: " + durationM);
                    bDurationM = false;
                } else if (bDepTime) {
                    depTime = new String(ch, start, length);
                    System.out.println("DepTime: " + depTime);
                    bDepTime = false;
                }
            }
        };
        File inputFile = new File("src/Data/Flights.xml");

        parser.parse(inputFile, sax);
        return(list);
    }

    @Override
    public Boolean functionList(List list, Scanner in) throws IOException, SAXException, ParserConfigurationException {
        int n;
        String durationM;
        String depTime;
        int i = Integer.parseInt(in.next());
        switch (i) {
            case 1:
                System.out.print("Enter duration min:\n");
                durationM = in.next();
                System.out.print("Enter dep time:\n");
                depTime = in.next();
                list.add(new Flight(durationM, depTime));
                return true;
            case 2:
                System.out.print("Enter node number:\n");
                n = Integer.parseInt(in.next());
                System.out.print("Enter duration min:\n");
                durationM = in.next();
                System.out.print("Enter dep time:\n");
                depTime = in.next();
                list.add(new Flight(durationM, depTime));
                return true;
            case 3:
                System.out.print("Enter node number:\n");
                n = Integer.parseInt(in.next());
                list.remove(n - 1);
                return true;
            case 4:
                this.choice(in);
                return true;
            default:
                System.out.print("Wrong value!\n");
                return false;
        }
    }

    @Override
    public void commit(List list) throws ParserConfigurationException, TransformerException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();

        String[] fields = {"durationM", "depTime"};
        String[][] val = new String[list.size()][fields.length];
        for (int n = 0; n < list.size(); n++) {
            val[n] = ((Flight)list.get(n)).getFieldsVal();
        }
        DOMSource source = new DOMSource(createDoc("Flights", "Flight", fields, val));
        StreamResult result = new StreamResult(new File("src/Data/Flights2.xml"));
        transformer.transform(source, result);
    }


    public class Flight {
        private String durationM;
        private String depTime;

        public Flight(String durationM, String depTime) {
            this.durationM = durationM;
            this.depTime = depTime;
        }

//    Methods *************************************************************************

        public String[] getFieldsVal() {
            String[] val = {durationM, depTime};
            return val;
        }
    }
}
