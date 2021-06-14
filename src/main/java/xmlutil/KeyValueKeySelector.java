package xmlutil;

import javax.xml.crypto.AlgorithmMethod;
import javax.xml.crypto.KeySelector;
import javax.xml.crypto.KeySelectorException;
import javax.xml.crypto.KeySelectorResult;
import javax.xml.crypto.XMLCryptoContext;
import javax.xml.crypto.XMLStructure;
import javax.xml.crypto.dsig.SignatureMethod;
import javax.xml.crypto.dsig.keyinfo.KeyInfo;
import javax.xml.crypto.dsig.keyinfo.KeyValue;
import java.security.KeyException;
import java.security.PublicKey;
import java.util.List;

//This Class is used when validating with <KeyInfo>
public class KeyValueKeySelector extends KeySelector {

  public KeySelectorResult select(KeyInfo keyInfo, Purpose purpose, AlgorithmMethod method, XMLCryptoContext context) throws KeySelectorException {

    //PREPARE VARIABLES
    SignatureMethod sm  = (SignatureMethod) method;
    List           list = keyInfo.getContent();
    PublicKey      pk   = null;

    //LOAD PUBLIC KEY FROM <KeyValue>
    for (int i = 0; i < list.size(); i++) {
      XMLStructure xmlStructure = (XMLStructure) list.get(i);
      if (xmlStructure instanceof KeyValue) {
        try                     { pk = ((KeyValue) xmlStructure).getPublicKey();                         }
        catch (KeyException ke) { System.out.println("Exception 1"); throw new KeySelectorException(ke); }
        break;
      }
    }

    //RETURN PUBLIC KEY
    return new SimpleKeySelectorResult(pk);

  }

}