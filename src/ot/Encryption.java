package oneType;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import java.io.UnsupportedEncodingException;
import java.io.IOException;

public class Encryption {
private final static String secret = "12FEG1ER";
private final byte[] secretkey = getUTF8Bytes(secret);

private byte[] getUTF8Bytes(String x) {
	byte[] r;
	try {
		r = x.getBytes("UTF-8");
	}

	catch(UnsupportedEncodingException uee) {
		r = x.getBytes();
	}
	return r;
}

private String getUTF8String(byte[] x) {
	String r;
	try {
		r = new String(x,"UTF8");
	}

	catch(UnsupportedEncodingException uee) {
		r = new String(x);
	}
	return r;
}

public String encrypt(String lxpassword) {
	if(lxpassword == null)
		return null;

	byte[] lxpwba = getUTF8Bytes(lxpassword); //data to encrypt

	try {
		SecretKey myTripleDesKey = new SecretKeySpec(secretkey,"DES");
		Cipher c =
				Cipher.getInstance("DES/ECB/PKCS5Padding"); // The object to perform encryption
		c.init(Cipher.ENCRYPT_MODE,myTripleDesKey); // Initialize it
		//		Now encrypt data
		byte[] ciphertext = c.doFinal(lxpwba);

		// Encode bytes to base64 to get a string
		return new sun.misc.BASE64Encoder().encode(ciphertext);
	} catch(NoSuchPaddingException ns) {
		if(OTNSp.getDebug())OTNSp.buzz(ns.getMessage());
	} catch(InvalidKeyException ike) {
		if(OTNSp.getDebug())OTNSp.buzz(ike.getMessage());
	} catch(NoSuchAlgorithmException e2) {
		if(OTNSp.getDebug())OTNSp.buzz(e2.getMessage());
	} catch(BadPaddingException e3) {
		if(OTNSp.getDebug())OTNSp.buzz(e3.getMessage());
	} catch(IllegalBlockSizeException e4) {
		if(OTNSp.getDebug())OTNSp.buzz(e4.getMessage());
	}

	return lxpassword;
}

public String decrypt(String str) {
	// Decode base64 to get bytes
	byte[] dec = null;
	String r = str;
	try {
		dec = new sun.misc.BASE64Decoder().decodeBuffer(str);
		r = decrypt(dec);
	} catch(IOException io) {
		;
	}
	return r;
}

public String decrypt(byte[] encmessages) {
	if(encmessages == null)
		return null;
	try {
		SecretKey myTripleDesKey = new SecretKeySpec(secretkey,"DES");
		Cipher c =
				Cipher.getInstance("DES/ECB/PKCS5Padding"); // The object to perform encryption
		c.init(Cipher.DECRYPT_MODE,myTripleDesKey);
		byte[] decryptedMessage = c.doFinal(encmessages);
		// Decode using utf-8
		return getUTF8String(decryptedMessage);
	} catch(NoSuchPaddingException ns) {
		if(OTNSp.getDebug())OTNSp.buzz(ns.getMessage());
	} catch(InvalidKeyException e) {
		if(OTNSp.getDebug())OTNSp.buzz(e.getMessage());
	} catch(NoSuchAlgorithmException e2) {
		if(OTNSp.getDebug())OTNSp.buzz(e2.getMessage());
	} catch(BadPaddingException e3) {
		if(OTNSp.getDebug())OTNSp.buzz(e3.getMessage());
	} catch(IllegalBlockSizeException e4) {
		if(OTNSp.getDebug())OTNSp.buzz(e4.getMessage());
	}
	return new String(encmessages);
}

}
