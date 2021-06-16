import org.w3c.dom.Document;
import xmlutil.XMLUtil;

public class ValidateSignature {

  static String fileXMLInput = "src/main/resources/PersonSignedWithKeyInfo.xml";

  //================================================================================
  // MAIN
  //================================================================================
  public static void main(String[] args) throws Exception {
    Document document = XMLUtil.readXMLFromFile(fileXMLInput);
    boolean  valid    = XMLUtil.validateSignatureUsingKeyinfo(document);
    System.out.println(valid);
  }

}
