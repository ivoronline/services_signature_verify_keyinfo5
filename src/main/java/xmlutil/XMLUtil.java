package xmlutil;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import javax.xml.crypto.dsig.XMLSignature;
import javax.xml.crypto.dsig.XMLSignatureFactory;
import javax.xml.crypto.dsig.dom.DOMValidateContext;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.FileInputStream;

public class XMLUtil {

  //================================================================================
  // READ XML FROM FILE
  //================================================================================
  // Document document = readXMLFromFile(fileXMLInput);
  public static Document readXMLFromFile(String fileName) throws Exception {
    DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
                           documentFactory.setNamespaceAware(true);
    Document document = documentFactory.newDocumentBuilder().parse(new FileInputStream(fileName));
    return   document;
  }

  //================================================================================
  // VALIDATE SIGNATURE USING KEY INFO
  //================================================================================
  // boolean valid = XMLUtil.validateSignatureUsingKeyinfo(document, "Person");
  public static boolean validateSignatureUsingKeyinfo(Document document) throws Exception  {

    //VALIDATE SIGNATURE USING KEY INFO
    Node                signatureNode = document.getElementsByTagNameNS(XMLSignature.XMLNS, "Signature").item(0);
    DOMValidateContext  valContext    = new DOMValidateContext(new KeyValueKeySelector(), signatureNode);
                        valContext.setIdAttributeNS((Element) signatureNode.getParentNode(), null, "Id");  //FIX
    XMLSignatureFactory factory       = XMLSignatureFactory.getInstance("DOM");
    XMLSignature        signature     = factory.unmarshalXMLSignature(valContext);
    boolean             valid         = signature.validate(valContext);

    //RETURN RESULT
    return valid;

  }

}
