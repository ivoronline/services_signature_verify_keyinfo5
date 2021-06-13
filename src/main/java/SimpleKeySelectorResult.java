import javax.xml.crypto.KeySelectorResult;
import java.security.Key;
import java.security.PublicKey;

public class SimpleKeySelectorResult implements KeySelectorResult {

  //PROPERTIES
  PublicKey pk;

  //CONSTRUCTOR
  public SimpleKeySelectorResult(PublicKey pk) {
    this.pk = pk;
  }

  //GETTER
  @Override
  public Key getKey() {
    return pk;
  }

}
