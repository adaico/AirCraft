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
public class PlaneMan extends DataManager {

    @Override
    public List parse() throws ParserConfigurationException, SAXException, IOException {
        List list = new ArrayList();
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();

        DefaultHandler sax = new DefaultHandler() {
            private Boolean bModel = false;
            private Boolean bYearOfIssue = false;
            private String model = null;
            private String yearOfIssue = null;

            @Override
            public void startDocument() throws SAXException {}

            @Override
            public void startElement(String namespaceURI, String localName, String qName, Attributes atts) throws SAXException {
                if (qName.equalsIgnoreCase("airline")) {
                    String ID = atts.getValue("id");
                    System.out.println("id : " + ID);
                    model = null;
                    yearOfIssue = null;
                } else if (qName.equalsIgnoreCase("model")) {
                    bModel = true;
                } else if (qName.equalsIgnoreCase("yearofissue")) {
                    bYearOfIssue = true;
                }
            }

            @Override
            public void endElement(String namespaceURI, String localName, String qName) throws SAXException {
                if (qName.equalsIgnoreCase("airline")) {
                    list.add(new Plane(model, yearOfIssue));
                }
            }

            @Override
            public void characters(char[] ch, int start, int length) throws SAXException {
                if (bModel) {
                    model = new String(ch, start, length);
                    System.out.println("Model: " + model);
                    bModel = false;
                } else if (bYearOfIssue) {
                    yearOfIssue = new String(ch, start, length);
                    System.out.println("YearOfIssue: " + yearOfIssue);
                    bYearOfIssue = false;
                }
            }
        };
        File inputFile = new File("src/Data/Planes.xml");

        parser.parse(inputFile, sax);
        return(list);
    }

    @Override
    public Boolean functionList(List list, Scanner in) throws IOException, SAXException, ParserConfigurationException {
        int n;
        String model;
        String dateOfIssue;
        int i = Integer.parseInt(in.next());
        switch (i) {
            case 1:
                System.out.print("model:\n");
                model = in.next();
                System.out.print("date of issue:\n");
                dateOfIssue = in.next();
                list.add(new Plane(model, dateOfIssue));
                return true;
            case 2:
                System.out.print("Enter node number:\n");
                n = Integer.parseInt(in.next());
                System.out.print("model:\n");
                model = in.next();
                System.out.print("date of issue:\n");
                dateOfIssue = in.next();
                list.add(new Plane(model, dateOfIssue));
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

        String[] fields = {"model", "yearOfIssue"};
        String[][] val = new String[list.size()][fields.length];
        for (int n = 0; n < list.size(); n++) {
            val[n] = ((Plane)list.get(n)).getFieldsVal();
        }
        DOMSource source = new DOMSource(createDoc("Planes", "Plane", fields, val));
        StreamResult result = new StreamResult(new File("src/Data/Planes2.xml"));
        transformer.transform(source, result);
    }


    public class Plane {
        private String model;
        private String yearOfIssue;

        public Plane(String model, String yearOfIssue) {
            this.model = model;
            this.yearOfIssue = yearOfIssue;
        }

//    Methods *************************************************************************

        public String[] getFieldsVal() {
            String[] val = {model, yearOfIssue};
            return val;
        }
    }

}
