package xmlutil;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import javax.xml.crypto.dsig.XMLSignature;
import javax.xml.crypto.dsig.XMLSignatureFactory;
import javax.xml.crypto.dsig.dom.DOMValidateContext;

public class UtilSignature {

  //================================================================================
  // VALIDATE SIGNATURE USING KEY INFO VALUE
  //================================================================================
  // boolean valid = XMLUtil.validateSignatureUsingKeyInfoValue(document, "Person");
  public static boolean validateSignatureUsingKeyInfoValue(Document document) throws Exception  {

    //VALIDATE SIGNATURE USING KeyValue FROM <KeyInfo>
    Node                signatureNode = document.getElementsByTagNameNS(XMLSignature.XMLNS, "Signature").item(0);
    DOMValidateContext  valContext    = new DOMValidateContext(new KeyValueKeySelector(), signatureNode);
                        valContext.setIdAttributeNS((Element) signatureNode.getParentNode(),null,"Id"); //FIX
    XMLSignatureFactory factory       = XMLSignatureFactory.getInstance("DOM");
    XMLSignature        signature     = factory.unmarshalXMLSignature(valContext);
    boolean             valid         = signature.validate(valContext);

    //RETURN RESULT
    return valid;

  }


}
