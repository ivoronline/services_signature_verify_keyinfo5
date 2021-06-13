import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.crypto.MarshalException;
import javax.xml.crypto.dsig.XMLSignature;
import javax.xml.crypto.dsig.XMLSignatureException;
import javax.xml.crypto.dsig.XMLSignatureFactory;
import javax.xml.crypto.dsig.dom.DOMValidateContext;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.FileInputStream;

public class ValidateSignature {

  static String fileXMLInput = "src/main/resources/PersonSigned.xml";

  //================================================================================
  // MAIN
  //================================================================================
  public static void main(String[] args) throws Exception {

    //READ XML FROM FILE
    Document document = readXMLFromFile(fileXMLInput);

    //VALIDATE XML
    boolean valid = validateSignature(document, "Person");

    //DISPLAY RESULT
    System.out.println(valid);

  }

  //================================================================================
  // VALIDATE SIGNATURE
  //================================================================================
  private static boolean validateSignature(Document document, String elementName) throws Exception  {

    //GET SIGNATURE NODE
    Node                signatureNode = document.getElementsByTagNameNS(XMLSignature.XMLNS, "Signature").item(0);

    //VALIDATE SIGNATURE
    Element             element    = (Element) document.getElementsByTagName(elementName).item(0);     //FIX
    DOMValidateContext  valContext = new DOMValidateContext(new KeyValueKeySelector(), signatureNode);
    valContext.setIdAttributeNS((Element) signatureNode.getParentNode(), null, "Id");                  //FIX
    XMLSignatureFactory factory    = XMLSignatureFactory.getInstance("DOM");
    XMLSignature        signature  = factory.unmarshalXMLSignature(valContext);
    boolean             valid      = signature.validate(valContext);

    //RETURN RESULT
    return valid;

  }

  //================================================================================
  // READ XML FROM FILE
  //================================================================================
  // Document document = readXMLFromFile(fileXMLInput);
  private static Document readXMLFromFile(String fileName) throws Exception {
    DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
    documentFactory.setNamespaceAware(true);
    Document document = documentFactory.newDocumentBuilder().parse(new FileInputStream(fileName));
    return document;
  }

}