package Classes;

import java.util.*;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.*;

import javax.xml.parsers.*;
import javax.xml.transform.TransformerException;
import java.io.*;

abstract public class  DataManager {

    public DataManager() {}

    public static DataManager choice(Scanner in) {
        System.out.print("Cities - \"1\"\t Airports - \"2\"\n" +
                "Pilots - \"3\"\t Planes - \"4\"\n" +
                "Airlines - \"5\"\t Flights - \"6\"\n");
        int i = Integer.parseInt(in.next());
        DataManager dm;
        switch (i) {
            case 1:
                dm = new CityMan();
                break;
            case 2:
                dm = new AirportMan();
                break;
            case 3:
                dm = new PilotMan();
                break;
            case 4:
                dm = new PlaneMan();
                break;
            case 5:
                dm = new AirlineMan();
                break;
            case 6:
                dm = new FlightMan();
                break;
            default:
                dm = null;
        }
        return dm;
    }

    public abstract List parse() throws ParserConfigurationException, SAXException, IOException;

    public static void functions() {
        System.out.println("\nWhat do you wont?\n" +
                "add - \"1\"\t" +
                "edit - \"2\"\t" +
                "delete - \"3\"\t" +
                "nothing - \"4\"");
    }

    public abstract Boolean functionList(List list, Scanner in) throws IOException, SAXException, ParserConfigurationException;

    public abstract void commit(List list) throws ParserConfigurationException, TransformerException;

    public Document createDoc(String tableName, String elName, String[] fields, String[][] val) throws ParserConfigurationException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.newDocument();
        Element rootElement = doc.createElement(tableName);
        doc.appendChild(rootElement);
        for (int j = 0; j < val.length; j++) {
            Element node = doc.createElement(elName);
            rootElement.appendChild(node);
            Attr attr = doc.createAttribute("id");
            attr.setValue(Integer.toString(j+1));
            node.setAttributeNode(attr);
            for (int k = 0; k <fields.length; k++) {
                Element field = doc.createElement(fields[k]);
                field.appendChild(doc.createTextNode(val[j][k]));
                node.appendChild(field);
            }
        }
        return doc;
    }


    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException, TransformerException {
        Scanner in = new Scanner(System.in);

        DataManager dm = choice(in);
        List objects = dm.parse();

        functions();

        if (dm.functionList(objects, in)) {
            dm.commit(objects);
        }
    }
}
