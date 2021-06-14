import org.w3c.dom.Document;
import xmlutil.XMLUtil;

public class ValidateSignature {

  static String fileXMLInput1 = "src/main/resources/PersonSigned.xml";
  static String fileXMLInput2 = "src/main/resources/InvoiceResponse.xml";

  //================================================================================
  // MAIN
  //================================================================================
  public static void main(String[] args) throws Exception {
    Document document = XMLUtil.readXMLFromFile(fileXMLInput1);
    boolean  valid    = XMLUtil.validateSignatureUsingKeyinfo(document);
    System.out.println(valid);
  }

}
