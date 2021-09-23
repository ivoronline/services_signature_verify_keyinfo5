import org.w3c.dom.Document;
import xmlutil.UtilSignature;
import xmlutil.UtilXML;

public class ValidateSignature {

  static String fileXMLInput1 = "/PersonSignedKey.xml";
  static String fileXMLInput2 = "/PersonSignedWithKeyInfo.xml";

  //================================================================================
  // MAIN
  //================================================================================
  public static void main(String[] args) throws Exception {
    Document document = UtilXML.fileToDocument(fileXMLInput1);
    boolean  valid    = UtilSignature.validateSignatureUsingKeyinfo(document);
    System.out.println(valid);
  }

}
