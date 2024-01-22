package com.coveiot.android.tappy.utils;

import androidx.annotation.RequiresApi;
import com.coveiot.utils.utility.LogHelper;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.security.spec.MGF1ParameterSpec;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.OAEPParameterSpec;
import javax.crypto.spec.PSource;
import org.jose4j.jwe.ContentEncryptionAlgorithmIdentifiers;
import org.jose4j.jwe.JsonWebEncryption;
import org.jose4j.jwe.KeyManagementAlgorithmIdentifiers;
import org.jose4j.jwk.JsonWebKey;
import org.jose4j.jwt.ReservedClaimNames;
import org.jose4j.keys.AesKey;
import org.json.JSONObject;
/* loaded from: classes7.dex */
public class EncryptionUtil {
    static {
        new SecureRandom();
    }

    @RequiresApi(api = 26)
    public static String encryptPanMasterCard(String str, String str2) {
        String str3 = null;
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance(AesKey.ALGORITHM);
            keyGenerator.init(128);
            SecretKey generateKey = keyGenerator.generateKey();
            byte[] bytes = str.getBytes(StandardCharsets.UTF_8);
            SecretKey generateKey2 = keyGenerator.generateKey();
            String encodeToString = Base64.getUrlEncoder().withoutPadding().encodeToString(generateKey2.getEncoded());
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            IvParameterSpec ivParameterSpec = new IvParameterSpec(generateKey2.getEncoded());
            cipher.init(1, generateKey, ivParameterSpec);
            String encodeToString2 = Base64.getUrlEncoder().withoutPadding().encodeToString(cipher.doFinal(bytes));
            Cipher cipher2 = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher2.init(2, generateKey, ivParameterSpec);
            byte[] doFinal = cipher2.doFinal(Base64.getUrlDecoder().decode(encodeToString2.getBytes(StandardCharsets.UTF_8)));
            LogHelper.i("EncryptionUtil", "Data after Decryption: " + new String(doFinal));
            JsonWebKey newJwk = JsonWebKey.Factory.newJwk(new JSONObject(str2).toString());
            Cipher cipher3 = Cipher.getInstance("RSA/ECB/OAEPPadding");
            cipher3.init(1, newJwk.getKey(), new OAEPParameterSpec("SHA-256", "MGF1", MGF1ParameterSpec.SHA256, PSource.PSpecified.DEFAULT));
            str3 = Base64.getUrlEncoder().withoutPadding().encodeToString(cipher3.doFinal(generateKey.getEncoded())) + "." + encodeToString + "." + encodeToString2;
            LogHelper.i("EncryptionUtil", "final encrypted Data : " + str3);
            return str3;
        } catch (Exception e) {
            e.printStackTrace();
            return str3;
        }
    }

    @RequiresApi(api = 26)
    public static String encryptPanVisaCard(String str, String str2, String str3, String str4) {
        String str5 = null;
        try {
            JsonWebEncryption jsonWebEncryption = new JsonWebEncryption();
            jsonWebEncryption.setPayload(str3);
            jsonWebEncryption.setKeyIdHeaderValue(str + "_" + str2);
            jsonWebEncryption.setHeader(ReservedClaimNames.ISSUED_AT, Long.valueOf(System.currentTimeMillis() / 1000));
            jsonWebEncryption.setHeader("typ", "JOSE");
            jsonWebEncryption.setHeader("channelSecurityContext", "RSA_PKI2");
            jsonWebEncryption.setHeader("alg", KeyManagementAlgorithmIdentifiers.RSA_OAEP_256);
            jsonWebEncryption.setHeader("enc", ContentEncryptionAlgorithmIdentifiers.AES_256_GCM);
            jsonWebEncryption.setKey(JsonWebKey.Factory.newJwk(str4).getKey());
            str5 = jsonWebEncryption.getCompactSerialization();
            LogHelper.i("EncryptionUtil", "final encrypted Data : " + str5);
            return str5;
        } catch (Exception e) {
            e.printStackTrace();
            return str5;
        }
    }
}
