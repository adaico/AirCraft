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
public class PilotMan extends DataManager {

    @Override
    public List parse() throws ParserConfigurationException, SAXException, IOException {
        List list = new ArrayList();
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();

        DefaultHandler sax = new DefaultHandler() {
            private Boolean bFirstName = false;
            private Boolean bLastName = false;
            private Boolean bBirthDate = false;
            private String firstName = null;
            private String lastName = null;
            private String birthDate = null;

            @Override
            public void startDocument() throws SAXException {}

            @Override
            public void startElement(String namespaceURI, String localName, String qName, Attributes atts) throws SAXException {
                if (qName.equalsIgnoreCase("pilot")) {
                    String ID = atts.getValue("id");
                    System.out.println("id : " + ID);
                    firstName = null;
                    lastName = null;
                    birthDate = null;
                } else if (qName.equalsIgnoreCase("firstname")) {
                    bFirstName = true;
                } else if (qName.equalsIgnoreCase("lastname")) {
                    bLastName = true;
                } else if (qName.equalsIgnoreCase("birthdate")) {
                    bBirthDate = true;
                }
            }

            @Override
            public void endElement(String namespaceURI, String localName, String qName) throws SAXException {
                if (qName.equalsIgnoreCase("pilot")) {
                    list.add(new Pilot(firstName, lastName, birthDate));
                }
            }

            @Override
            public void characters(char[] ch, int start, int length) throws SAXException {
                if (bFirstName) {
                    firstName = new String(ch, start, length);
                    System.out.println("FirstName: " + firstName);
                    bFirstName = false;
                } else if (bLastName) {
                    lastName = new String(ch, start, length);
                    System.out.println("LastName: " + lastName);
                    bLastName = false;
                } else if (bBirthDate) {
                    birthDate = new String(ch, start, length);
                    System.out.println("BirthDate: " + birthDate);
                    bBirthDate = false;
                }
            }
        };
        File inputFile = new File("src/Data/Pilots.xml");

        parser.parse(inputFile, sax);
        return(list);
    }

    @Override
    public Boolean functionList(List list, Scanner in) throws IOException, SAXException, ParserConfigurationException {
        int n;
        String firstName;
        String lastName;
        String birthDate;
        int i = Integer.parseInt(in.next());
        switch (i) {
            case 1:
                System.out.print("Enter first name\n");
                firstName = in.next();
                System.out.print("Enter last name\n");
                lastName = in.next();
                System.out.print("Enter birth date\n");
                birthDate = in.next();
                list.add(new Pilot(firstName, lastName, birthDate));
                return true;
            case 2:
                System.out.print("Enter node number:\n");
                n = Integer.parseInt(in.next());
                System.out.print("Enter first name\n");
                firstName = in.next();
                System.out.print("Enter last name\n");
                lastName = in.next();
                System.out.print("Enter birth date\n");
                birthDate = in.next();
                list.add(new Pilot(firstName, lastName, birthDate));
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

        String[] fields = {"firstName", "lastName", "birthDate"};
        String[][] val = new String[list.size()][fields.length];
        for (int n = 0; n < list.size(); n++) {
            val[n] = ((Pilot)list.get(n)).getFieldsVal();
        }
        DOMSource source = new DOMSource(createDoc("Pilots", "Pilot", fields, val));
        StreamResult result = new StreamResult(new File("src/Data/Pilots2.xml"));
        transformer.transform(source, result);
    }


    public class Pilot {
        private String firstName;
        private String lastName;
        private String birthDate;

        public Pilot(String firstName, String lastName, String birthDate) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.birthDate = birthDate;
        }

//    Methods *************************************************************************

        public String[] getFieldsVal() {
            String[] val = {firstName, lastName, birthDate};
            return val;
        }
    }
}
