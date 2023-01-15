import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;

public class ImportyXML {
//    private static final String FILENAME = "/users/mkyong/staff.xml";
    private static final String FILENAME = "c:\\pracovny\\cocacola2020-2022TipTop_1904120738_2020-01-01.xml ";
    public static void main(String args[]) {
        System.out.println("main method incoked");
        importCoccaCola();
    }
    private static void importCoccaCola(){
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {

            dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

            DocumentBuilder db = dbf.newDocumentBuilder();

            Document doc = db.parse(new File(FILENAME));
            doc.getDocumentElement().normalize();

            System.out.println("Root Element :" + doc.getDocumentElement().getNodeName());
            System.out.println("------");

            // get <staff>
            NodeList list = doc.getElementsByTagName("StockEntry");
            BigDecimal sum=new BigDecimal(0);
            for (int temp = 0; temp < list.getLength(); temp++) {

                Node node = list.item(temp);
                Element element = (Element) node;
                // get text
                String productNumber = element.getElementsByTagName("ProductNumber").item(0).getTextContent();
                String quantity = element.getElementsByTagName("Quantity").item(0).getTextContent();
                BigDecimal obj = new BigDecimal(quantity);
                System.out.println("Current Element :" + node.getNodeName()+productNumber+"  "+quantity+"  "+obj);
                sum=sum.add(obj);

            }
            System.out.println("Sum :" + sum);

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }

        // parse XML file
    }
}
