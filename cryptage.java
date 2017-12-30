import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class cryptage {
	private static String storePassword;
	private static SecretKey storeKey;	

	public static void main(String[] args) throws GeneralSecurityException {
		// TODO Auto-generated method stub
		HashMap<String,String>logs=new HashMap<String,String>();
		SecretKey key=generateKey();
		String mail="thomas.blangero@uha.fr";
		String message="getRekt";
		String messageCrypter=encrypt(message,key.toString());
		String messageDecrypter=decrypt(messageCrypter,key.toString());
		System.out.println(messageCrypter);
		if(message.equals(messageDecrypter))
			System.out.println("c'est réussi");
		System.out.println(message);
		System.out.println(messageDecrypter);
		logs.put(mail,messageCrypter);
		System.out.println(logs);
		storeKey=generateKey();
		try {
			generateKeystore("test");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			saveKey(key,mail,storeKey.toString());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			System.out.println(retrieveKey(mail,storeKey.toString()).toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void generateKeystore(String mdp) throws KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException {
		KeyStore ks = KeyStore.getInstance("jks");
		char[] password = mdp.toCharArray();
		ks.load(null, password);
		// Store away the keystore.
		FileOutputStream fos = new FileOutputStream("storeKey");
		ks.store(fos, password);
		fos.close();
	}
	
	public static SecretKey generateKey() throws GeneralSecurityException {
		// Obtenir une instance d'un générateur de clés secrètes pour l'AES
		   KeyGenerator kg = KeyGenerator.getInstance("AES");
		   // Spécifier la longueur de la clé (pour l'AES : 128, 192, 256)
		   //remarque : l'implémentation courante par le JCE de l'AES 
		   // ne supporte que les clés de 128 bits
		   kg.init(128);
		   // Générer la clé
		   SecretKey key = kg.generateKey();
		   return key;
	}
	
	public static void saveKey(SecretKey Skey,String id,String storeKey) throws NoSuchAlgorithmException, CertificateException, FileNotFoundException, IOException, KeyStoreException {
		KeyStore ks = KeyStore.getInstance("jks");
		OutputStream fos=new FileOutputStream("storeKey");
		char[] password=decrypt(storePassword,storeKey).toCharArray();
		ks.load(new FileInputStream("storeKey"),password);
		ks.store(fos, id.toCharArray());
		fos.close();		
	}
	
	public static SecretKey retrieveKey(String id,String storeKey) throws KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException, UnrecoverableKeyException {
		KeyStore ks = KeyStore.getInstance("jks");
		char[] password=decrypt(storePassword,storeKey).toCharArray();
		ks.load(new FileInputStream("storeKey"),password);
		return (SecretKey) ks.getKey(id, password);
	}
	 
	private static SecretKeySpec secretKey;
    private static byte[] key;
 
    public static void setKey(String myKey)
    {
        MessageDigest sha = null;
        try {
            key = myKey.getBytes("UTF-8");
            sha = MessageDigest.getInstance("SHA-1");
            key = sha.digest(key);
            key = Arrays.copyOf(key, 16);
            secretKey = new SecretKeySpec(key, "AES");
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
 
    public static String encrypt(String strToEncrypt, String secret)
    {
        try
        {
            setKey(secret);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
        }
        catch (Exception e)
        {
            System.out.println("Error while encrypting: " + e.toString());
        }
        return null;
    }
 
    public static String decrypt(String strToDecrypt, String secret)
    {
        try
        {
            setKey(secret);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
        }
        catch (Exception e)
        {
            System.out.println("Error while decrypting: " + e.toString());
        }
        return null;
    }

}
