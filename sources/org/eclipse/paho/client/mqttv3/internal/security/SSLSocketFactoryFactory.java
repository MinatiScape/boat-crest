package org.eclipse.paho.client.mqttv3.internal.security;

import com.coveiot.sdk.ble.api.BleUUID;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.Hashtable;
import java.util.Properties;
import java.util.Vector;
import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import org.eclipse.paho.client.mqttv3.MqttSecurityException;
import org.eclipse.paho.client.mqttv3.logging.Logger;
/* loaded from: classes13.dex */
public class SSLSocketFactoryFactory {
    public static final String DEFAULT_PROTOCOL = "TLS";
    public static final String SYSKEYMGRALGO = "ssl.KeyManagerFactory.algorithm";
    public static final String SYSKEYSTORE = "javax.net.ssl.keyStore";
    public static final String SYSKEYSTOREPWD = "javax.net.ssl.keyStorePassword";
    public static final String SYSKEYSTORETYPE = "javax.net.ssl.keyStoreType";
    public static final String SYSTRUSTMGRALGO = "ssl.TrustManagerFactory.algorithm";
    public static final String SYSTRUSTSTORE = "javax.net.ssl.trustStore";
    public static final String SYSTRUSTSTOREPWD = "javax.net.ssl.trustStorePassword";
    public static final String SYSTRUSTSTORETYPE = "javax.net.ssl.trustStoreType";

    /* renamed from: a  reason: collision with root package name */
    public Hashtable f15454a;
    public Properties b;
    public Logger c;
    public static final String SSLPROTOCOL = "com.ibm.ssl.protocol";
    public static final String JSSEPROVIDER = "com.ibm.ssl.contextProvider";
    public static final String KEYSTORE = "com.ibm.ssl.keyStore";
    public static final String KEYSTOREPWD = "com.ibm.ssl.keyStorePassword";
    public static final String KEYSTORETYPE = "com.ibm.ssl.keyStoreType";
    public static final String KEYSTOREPROVIDER = "com.ibm.ssl.keyStoreProvider";
    public static final String KEYSTOREMGR = "com.ibm.ssl.keyManager";
    public static final String TRUSTSTORE = "com.ibm.ssl.trustStore";
    public static final String TRUSTSTOREPWD = "com.ibm.ssl.trustStorePassword";
    public static final String TRUSTSTORETYPE = "com.ibm.ssl.trustStoreType";
    public static final String TRUSTSTOREPROVIDER = "com.ibm.ssl.trustStoreProvider";
    public static final String TRUSTSTOREMGR = "com.ibm.ssl.trustManager";
    public static final String CIPHERSUITES = "com.ibm.ssl.enabledCipherSuites";
    public static final String CLIENTAUTH = "com.ibm.ssl.clientAuthentication";
    public static final String[] d = {SSLPROTOCOL, JSSEPROVIDER, KEYSTORE, KEYSTOREPWD, KEYSTORETYPE, KEYSTOREPROVIDER, KEYSTOREMGR, TRUSTSTORE, TRUSTSTOREPWD, TRUSTSTORETYPE, TRUSTSTOREPROVIDER, TRUSTSTOREMGR, CIPHERSUITES, CLIENTAUTH};
    public static final byte[] e = {-99, -89, -39, Byte.MIN_VALUE, 5, BleUUID.CMD_ID_B8, BleUUID.CMD_ID_89, -100};

    public SSLSocketFactoryFactory() {
        this.c = null;
        this.f15454a = new Hashtable();
    }

    public static char[] deObfuscate(String str) {
        if (str == null) {
            return null;
        }
        try {
            byte[] decode = SimpleBase64Encoder.decode(str.substring(5));
            for (int i = 0; i < decode.length; i++) {
                byte b = decode[i];
                byte[] bArr = e;
                decode[i] = (byte) ((b ^ bArr[i % bArr.length]) & 255);
            }
            return toChar(decode);
        } catch (Exception unused) {
            return null;
        }
    }

    public static boolean isSupportedOnJVM() throws LinkageError, ExceptionInInitializerError {
        try {
            Class.forName("javax.net.ssl.SSLServerSocketFactory");
            return true;
        } catch (ClassNotFoundException unused) {
            return false;
        }
    }

    public static String obfuscate(char[] cArr) {
        if (cArr == null) {
            return null;
        }
        byte[] bArr = toByte(cArr);
        for (int i = 0; i < bArr.length; i++) {
            byte b = bArr[i];
            byte[] bArr2 = e;
            bArr[i] = (byte) ((b ^ bArr2[i % bArr2.length]) & 255);
        }
        return "{xor}" + new String(SimpleBase64Encoder.encode(bArr));
    }

    public static String packCipherSuites(String[] strArr) {
        if (strArr != null) {
            StringBuffer stringBuffer = new StringBuffer();
            for (int i = 0; i < strArr.length; i++) {
                stringBuffer.append(strArr[i]);
                if (i < strArr.length - 1) {
                    stringBuffer.append(',');
                }
            }
            return stringBuffer.toString();
        }
        return null;
    }

    public static byte[] toByte(char[] cArr) {
        if (cArr == null) {
            return null;
        }
        byte[] bArr = new byte[cArr.length * 2];
        int i = 0;
        for (int i2 = 0; i2 < cArr.length; i2++) {
            int i3 = i + 1;
            bArr[i] = (byte) (cArr[i2] & 255);
            i = i3 + 1;
            bArr[i3] = (byte) ((cArr[i2] >> '\b') & 255);
        }
        return bArr;
    }

    public static char[] toChar(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        char[] cArr = new char[bArr.length / 2];
        int i = 0;
        int i2 = 0;
        while (i < bArr.length) {
            int i3 = i + 1;
            cArr[i2] = (char) ((bArr[i] & 255) + ((bArr[i3] & 255) << 8));
            i2++;
            i = i3 + 1;
        }
        return cArr;
    }

    public static String[] unpackCipherSuites(String str) {
        if (str == null) {
            return null;
        }
        Vector vector = new Vector();
        int indexOf = str.indexOf(44);
        int i = 0;
        while (indexOf > -1) {
            vector.add(str.substring(i, indexOf));
            i = indexOf + 1;
            indexOf = str.indexOf(44, i);
        }
        vector.add(str.substring(i));
        String[] strArr = new String[vector.size()];
        vector.toArray(strArr);
        return strArr;
    }

    public final void a(Properties properties) throws IllegalArgumentException {
        for (String str : properties.keySet()) {
            if (!f(str)) {
                throw new IllegalArgumentException(String.valueOf(str) + " is not a valid IBM SSL property key.");
            }
        }
    }

    public final void b(Properties properties) {
        String property = properties.getProperty(KEYSTOREPWD);
        if (property != null && !property.startsWith("{xor}")) {
            properties.put(KEYSTOREPWD, obfuscate(property.toCharArray()));
        }
        String property2 = properties.getProperty(TRUSTSTOREPWD);
        if (property2 == null || property2.startsWith("{xor}")) {
            return;
        }
        properties.put(TRUSTSTOREPWD, obfuscate(property2.toCharArray()));
    }

    public final String c(String str, String str2, String str3) {
        String d2 = d(str, str2);
        return (d2 == null && str3 != null) ? System.getProperty(str3) : d2;
    }

    public SSLSocketFactory createSocketFactory(String str) throws MqttSecurityException {
        SSLContext e2 = e(str);
        Logger logger = this.c;
        if (logger != null) {
            Object[] objArr = new Object[2];
            objArr[0] = str != null ? str : "null (broker defaults)";
            objArr[1] = getEnabledCipherSuites(str) != null ? c(str, CIPHERSUITES, null) : "null (using platform-enabled cipher suites)";
            logger.fine("org.eclipse.paho.client.mqttv3.internal.security.SSLSocketFactoryFactory", "createSocketFactory", "12020", objArr);
        }
        return e2.getSocketFactory();
    }

    public final String d(String str, String str2) {
        String str3 = null;
        Properties properties = str != null ? (Properties) this.f15454a.get(str) : null;
        if (properties == null || (str3 = properties.getProperty(str2)) == null) {
            Properties properties2 = this.b;
            if (properties2 == null || (str3 = properties2.getProperty(str2)) != null) {
            }
            return str3;
        }
        return str3;
    }

    public final SSLContext e(String str) throws MqttSecurityException {
        SSLContext sSLContext;
        KeyManager[] keyManagerArr;
        TrustManager[] trustManagerArr;
        TrustManagerFactory trustManagerFactory;
        KeyManagerFactory keyManagerFactory;
        String str2 = str;
        String sSLProtocol = getSSLProtocol(str);
        if (sSLProtocol == null) {
            sSLProtocol = "TLS";
        }
        Logger logger = this.c;
        if (logger != null) {
            Object[] objArr = new Object[2];
            objArr[0] = str2 != null ? str2 : "null (broker defaults)";
            objArr[1] = sSLProtocol;
            logger.fine("org.eclipse.paho.client.mqttv3.internal.security.SSLSocketFactoryFactory", "getSSLContext", "12000", objArr);
        }
        String jSSEProvider = getJSSEProvider(str);
        try {
            if (jSSEProvider == null) {
                sSLContext = SSLContext.getInstance(sSLProtocol);
            } else {
                sSLContext = SSLContext.getInstance(sSLProtocol, jSSEProvider);
            }
            Logger logger2 = this.c;
            if (logger2 != null) {
                Object[] objArr2 = new Object[2];
                objArr2[0] = str2 != null ? str2 : "null (broker defaults)";
                objArr2[1] = sSLContext.getProvider().getName();
                logger2.fine("org.eclipse.paho.client.mqttv3.internal.security.SSLSocketFactoryFactory", "getSSLContext", "12001", objArr2);
            }
            String c = c(str2, KEYSTORE, null);
            if (c == null) {
                c = c(str2, KEYSTORE, SYSKEYSTORE);
            }
            Logger logger3 = this.c;
            if (logger3 != null) {
                Object[] objArr3 = new Object[2];
                objArr3[0] = str2 != null ? str2 : "null (broker defaults)";
                objArr3[1] = c != null ? c : "null";
                logger3.fine("org.eclipse.paho.client.mqttv3.internal.security.SSLSocketFactoryFactory", "getSSLContext", "12004", objArr3);
            }
            char[] keyStorePassword = getKeyStorePassword(str);
            Logger logger4 = this.c;
            if (logger4 != null) {
                Object[] objArr4 = new Object[2];
                objArr4[0] = str2 != null ? str2 : "null (broker defaults)";
                objArr4[1] = keyStorePassword != null ? obfuscate(keyStorePassword) : "null";
                logger4.fine("org.eclipse.paho.client.mqttv3.internal.security.SSLSocketFactoryFactory", "getSSLContext", "12005", objArr4);
            }
            String keyStoreType = getKeyStoreType(str);
            if (keyStoreType == null) {
                keyStoreType = KeyStore.getDefaultType();
            }
            Logger logger5 = this.c;
            if (logger5 != null) {
                Object[] objArr5 = new Object[2];
                objArr5[0] = str2 != null ? str2 : "null (broker defaults)";
                objArr5[1] = keyStoreType != null ? keyStoreType : "null";
                logger5.fine("org.eclipse.paho.client.mqttv3.internal.security.SSLSocketFactoryFactory", "getSSLContext", "12006", objArr5);
            }
            String defaultAlgorithm = KeyManagerFactory.getDefaultAlgorithm();
            String keyStoreProvider = getKeyStoreProvider(str);
            String keyManager = getKeyManager(str);
            if (keyManager != null) {
                defaultAlgorithm = keyManager;
            }
            if (c == null || keyStoreType == null || defaultAlgorithm == null) {
                keyManagerArr = null;
            } else {
                try {
                    KeyStore keyStore = KeyStore.getInstance(keyStoreType);
                    keyStore.load(new FileInputStream(c), keyStorePassword);
                    if (keyStoreProvider != null) {
                        keyManagerFactory = KeyManagerFactory.getInstance(defaultAlgorithm, keyStoreProvider);
                    } else {
                        keyManagerFactory = KeyManagerFactory.getInstance(defaultAlgorithm);
                    }
                    Logger logger6 = this.c;
                    if (logger6 != null) {
                        Object[] objArr6 = new Object[2];
                        objArr6[0] = str2 != null ? str2 : "null (broker defaults)";
                        objArr6[1] = defaultAlgorithm;
                        logger6.fine("org.eclipse.paho.client.mqttv3.internal.security.SSLSocketFactoryFactory", "getSSLContext", "12010", objArr6);
                        Logger logger7 = this.c;
                        Object[] objArr7 = new Object[2];
                        objArr7[0] = str2 != null ? str2 : "null (broker defaults)";
                        objArr7[1] = keyManagerFactory.getProvider().getName();
                        logger7.fine("org.eclipse.paho.client.mqttv3.internal.security.SSLSocketFactoryFactory", "getSSLContext", "12009", objArr7);
                    }
                    keyManagerFactory.init(keyStore, keyStorePassword);
                    keyManagerArr = keyManagerFactory.getKeyManagers();
                } catch (FileNotFoundException e2) {
                    throw new MqttSecurityException(e2);
                } catch (IOException e3) {
                    throw new MqttSecurityException(e3);
                } catch (KeyStoreException e4) {
                    throw new MqttSecurityException(e4);
                } catch (UnrecoverableKeyException e5) {
                    throw new MqttSecurityException(e5);
                } catch (CertificateException e6) {
                    throw new MqttSecurityException(e6);
                }
            }
            String trustStore = getTrustStore(str);
            Logger logger8 = this.c;
            if (logger8 != null) {
                Object[] objArr8 = new Object[2];
                objArr8[0] = str2 != null ? str2 : "null (broker defaults)";
                objArr8[1] = trustStore != null ? trustStore : "null";
                logger8.fine("org.eclipse.paho.client.mqttv3.internal.security.SSLSocketFactoryFactory", "getSSLContext", "12011", objArr8);
            }
            char[] trustStorePassword = getTrustStorePassword(str);
            Logger logger9 = this.c;
            if (logger9 != null) {
                Object[] objArr9 = new Object[2];
                objArr9[0] = str2 != null ? str2 : "null (broker defaults)";
                objArr9[1] = trustStorePassword != null ? obfuscate(trustStorePassword) : "null";
                logger9.fine("org.eclipse.paho.client.mqttv3.internal.security.SSLSocketFactoryFactory", "getSSLContext", "12012", objArr9);
            }
            String trustStoreType = getTrustStoreType(str);
            if (trustStoreType == null) {
                trustStoreType = KeyStore.getDefaultType();
            }
            Logger logger10 = this.c;
            if (logger10 != null) {
                Object[] objArr10 = new Object[2];
                objArr10[0] = str2 != null ? str2 : "null (broker defaults)";
                objArr10[1] = trustStoreType != null ? trustStoreType : "null";
                logger10.fine("org.eclipse.paho.client.mqttv3.internal.security.SSLSocketFactoryFactory", "getSSLContext", "12013", objArr10);
            }
            String defaultAlgorithm2 = TrustManagerFactory.getDefaultAlgorithm();
            String trustStoreProvider = getTrustStoreProvider(str);
            String trustManager = getTrustManager(str);
            if (trustManager != null) {
                defaultAlgorithm2 = trustManager;
            }
            if (trustStore == null || trustStoreType == null || defaultAlgorithm2 == null) {
                trustManagerArr = null;
            } else {
                try {
                    KeyStore keyStore2 = KeyStore.getInstance(trustStoreType);
                    keyStore2.load(new FileInputStream(trustStore), trustStorePassword);
                    if (trustStoreProvider != null) {
                        trustManagerFactory = TrustManagerFactory.getInstance(defaultAlgorithm2, trustStoreProvider);
                    } else {
                        trustManagerFactory = TrustManagerFactory.getInstance(defaultAlgorithm2);
                    }
                    Logger logger11 = this.c;
                    if (logger11 != null) {
                        Object[] objArr11 = new Object[2];
                        objArr11[0] = str2 != null ? str2 : "null (broker defaults)";
                        objArr11[1] = defaultAlgorithm2;
                        logger11.fine("org.eclipse.paho.client.mqttv3.internal.security.SSLSocketFactoryFactory", "getSSLContext", "12017", objArr11);
                        Logger logger12 = this.c;
                        Object[] objArr12 = new Object[2];
                        if (str2 == null) {
                            str2 = "null (broker defaults)";
                        }
                        objArr12[0] = str2;
                        objArr12[1] = trustManagerFactory.getProvider().getName();
                        logger12.fine("org.eclipse.paho.client.mqttv3.internal.security.SSLSocketFactoryFactory", "getSSLContext", "12016", objArr12);
                    }
                    trustManagerFactory.init(keyStore2);
                    trustManagerArr = trustManagerFactory.getTrustManagers();
                } catch (FileNotFoundException e7) {
                    throw new MqttSecurityException(e7);
                } catch (IOException e8) {
                    throw new MqttSecurityException(e8);
                } catch (KeyStoreException e9) {
                    throw new MqttSecurityException(e9);
                } catch (CertificateException e10) {
                    throw new MqttSecurityException(e10);
                }
            }
            sSLContext.init(keyManagerArr, trustManagerArr, null);
            return sSLContext;
        } catch (KeyManagementException e11) {
            throw new MqttSecurityException(e11);
        } catch (NoSuchAlgorithmException e12) {
            throw new MqttSecurityException(e12);
        } catch (NoSuchProviderException e13) {
            throw new MqttSecurityException(e13);
        }
    }

    public final boolean f(String str) {
        String[] strArr;
        int i = 0;
        while (true) {
            strArr = d;
            if (i < strArr.length && !strArr[i].equals(str)) {
                i++;
            }
        }
        return i < strArr.length;
    }

    public boolean getClientAuthentication(String str) {
        String c = c(str, CLIENTAUTH, null);
        if (c != null) {
            return Boolean.valueOf(c).booleanValue();
        }
        return false;
    }

    public Properties getConfiguration(String str) {
        Object obj;
        if (str == null) {
            obj = this.b;
        } else {
            obj = this.f15454a.get(str);
        }
        return (Properties) obj;
    }

    public String[] getEnabledCipherSuites(String str) {
        return unpackCipherSuites(c(str, CIPHERSUITES, null));
    }

    public String getJSSEProvider(String str) {
        return c(str, JSSEPROVIDER, null);
    }

    public String getKeyManager(String str) {
        return c(str, KEYSTOREMGR, SYSKEYMGRALGO);
    }

    public String getKeyStore(String str) {
        String d2 = d(str, KEYSTORE);
        return d2 != null ? d2 : System.getProperty(SYSKEYSTORE);
    }

    public char[] getKeyStorePassword(String str) {
        String c = c(str, KEYSTOREPWD, SYSKEYSTOREPWD);
        if (c != null) {
            if (c.startsWith("{xor}")) {
                return deObfuscate(c);
            }
            return c.toCharArray();
        }
        return null;
    }

    public String getKeyStoreProvider(String str) {
        return c(str, KEYSTOREPROVIDER, null);
    }

    public String getKeyStoreType(String str) {
        return c(str, KEYSTORETYPE, SYSKEYSTORETYPE);
    }

    public String getSSLProtocol(String str) {
        return c(str, SSLPROTOCOL, null);
    }

    public String getTrustManager(String str) {
        return c(str, TRUSTSTOREMGR, SYSTRUSTMGRALGO);
    }

    public String getTrustStore(String str) {
        String c = c(str, TRUSTSTORE, SYSTRUSTSTORE);
        try {
            return URLDecoder.decode(c, StandardCharsets.UTF_8.name());
        } catch (Exception unused) {
            return c;
        }
    }

    public char[] getTrustStorePassword(String str) {
        String c = c(str, TRUSTSTOREPWD, SYSTRUSTSTOREPWD);
        if (c != null) {
            if (c.startsWith("{xor}")) {
                return deObfuscate(c);
            }
            return c.toCharArray();
        }
        return null;
    }

    public String getTrustStoreProvider(String str) {
        return c(str, TRUSTSTOREPROVIDER, null);
    }

    public String getTrustStoreType(String str) {
        return c(str, TRUSTSTORETYPE, null);
    }

    public void initialize(Properties properties, String str) throws IllegalArgumentException {
        a(properties);
        Properties properties2 = new Properties();
        properties2.putAll(properties);
        b(properties2);
        if (str != null) {
            this.f15454a.put(str, properties2);
        } else {
            this.b = properties2;
        }
    }

    public void merge(Properties properties, String str) throws IllegalArgumentException {
        a(properties);
        Properties properties2 = this.b;
        if (str != null) {
            properties2 = (Properties) this.f15454a.get(str);
        }
        if (properties2 == null) {
            properties2 = new Properties();
        }
        b(properties);
        properties2.putAll(properties);
        if (str != null) {
            this.f15454a.put(str, properties2);
        } else {
            this.b = properties2;
        }
    }

    public boolean remove(String str) {
        if (str != null) {
            if (this.f15454a.remove(str) != null) {
                return true;
            }
        } else if (this.b != null) {
            this.b = null;
            return true;
        }
        return false;
    }

    public SSLSocketFactoryFactory(Logger logger) {
        this();
        this.c = logger;
    }
}
