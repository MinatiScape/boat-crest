package com.abupdate.mqtt_libs.mqttv3.a.a;

import com.abupdate.mqtt_libs.mqttv3.m;
import com.coveiot.sdk.ble.api.BleUUID;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
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
import org.eclipse.paho.client.mqttv3.internal.security.SSLSocketFactoryFactory;
/* loaded from: classes.dex */
public class a {
    public static final String[] c = {SSLSocketFactoryFactory.SSLPROTOCOL, SSLSocketFactoryFactory.JSSEPROVIDER, SSLSocketFactoryFactory.KEYSTORE, SSLSocketFactoryFactory.KEYSTOREPWD, SSLSocketFactoryFactory.KEYSTORETYPE, SSLSocketFactoryFactory.KEYSTOREPROVIDER, SSLSocketFactoryFactory.KEYSTOREMGR, SSLSocketFactoryFactory.TRUSTSTORE, SSLSocketFactoryFactory.TRUSTSTOREPWD, SSLSocketFactoryFactory.TRUSTSTORETYPE, SSLSocketFactoryFactory.TRUSTSTOREPROVIDER, SSLSocketFactoryFactory.TRUSTSTOREMGR, SSLSocketFactoryFactory.CIPHERSUITES, SSLSocketFactoryFactory.CLIENTAUTH};
    public static final byte[] d = {-99, -89, -39, Byte.MIN_VALUE, 5, BleUUID.CMD_ID_B8, BleUUID.CMD_ID_89, -100};

    /* renamed from: a  reason: collision with root package name */
    public Hashtable f1942a = new Hashtable();
    public Properties b;

    public static char[] a(byte[] bArr) {
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

    public static String b(char[] cArr) {
        if (cArr == null) {
            return null;
        }
        byte[] a2 = a(cArr);
        for (int i = 0; i < a2.length; i++) {
            byte b = a2[i];
            byte[] bArr = d;
            a2[i] = (byte) ((b ^ bArr[i % bArr.length]) & 255);
        }
        return "{xor}" + new String(b.a(a2));
    }

    public final void c(Properties properties) throws IllegalArgumentException {
        for (String str : properties.keySet()) {
            if (!p(str)) {
                throw new IllegalArgumentException(str + " is not a valid IBM SSL property key.");
            }
        }
    }

    public final void d(Properties properties) {
        String property = properties.getProperty(SSLSocketFactoryFactory.KEYSTOREPWD);
        if (property != null && !property.startsWith("{xor}")) {
            properties.put(SSLSocketFactoryFactory.KEYSTOREPWD, b(property.toCharArray()));
        }
        String property2 = properties.getProperty(SSLSocketFactoryFactory.TRUSTSTOREPWD);
        if (property2 == null || property2.startsWith("{xor}")) {
            return;
        }
        properties.put(SSLSocketFactoryFactory.TRUSTSTOREPWD, b(property2.toCharArray()));
    }

    public char[] e(String str) {
        String b = b(str, SSLSocketFactoryFactory.KEYSTOREPWD, SSLSocketFactoryFactory.SYSKEYSTOREPWD);
        if (b != null) {
            if (b.startsWith("{xor}")) {
                return a(b);
            }
            return b.toCharArray();
        }
        return null;
    }

    public String f(String str) {
        return b(str, SSLSocketFactoryFactory.KEYSTORETYPE, SSLSocketFactoryFactory.SYSKEYSTORETYPE);
    }

    public String g(String str) {
        return b(str, SSLSocketFactoryFactory.KEYSTOREPROVIDER, null);
    }

    public String h(String str) {
        return b(str, SSLSocketFactoryFactory.KEYSTOREMGR, SSLSocketFactoryFactory.SYSKEYMGRALGO);
    }

    public String i(String str) {
        return b(str, SSLSocketFactoryFactory.TRUSTSTORE, SSLSocketFactoryFactory.SYSTRUSTSTORE);
    }

    public char[] j(String str) {
        String b = b(str, SSLSocketFactoryFactory.TRUSTSTOREPWD, SSLSocketFactoryFactory.SYSTRUSTSTOREPWD);
        if (b != null) {
            if (b.startsWith("{xor}")) {
                return a(b);
            }
            return b.toCharArray();
        }
        return null;
    }

    public String k(String str) {
        return b(str, SSLSocketFactoryFactory.TRUSTSTORETYPE, null);
    }

    public String l(String str) {
        return b(str, SSLSocketFactoryFactory.TRUSTSTOREPROVIDER, null);
    }

    public String m(String str) {
        return b(str, SSLSocketFactoryFactory.TRUSTSTOREMGR, SSLSocketFactoryFactory.SYSTRUSTMGRALGO);
    }

    public String[] n(String str) {
        return b(b(str, SSLSocketFactoryFactory.CIPHERSUITES, null));
    }

    public SSLSocketFactory o(String str) throws m {
        return q(str).getSocketFactory();
    }

    public final boolean p(String str) {
        String[] strArr;
        int i = 0;
        while (true) {
            strArr = c;
            if (i >= strArr.length || strArr[i].equals(str)) {
                break;
            }
            i++;
        }
        return i < strArr.length;
    }

    public final SSLContext q(String str) throws m {
        SSLContext sSLContext;
        KeyManager[] keyManagerArr;
        TrustManager[] trustManagerArr;
        TrustManagerFactory trustManagerFactory;
        KeyManagerFactory keyManagerFactory;
        String c2 = c(str);
        if (c2 == null) {
            c2 = "TLS";
        }
        String d2 = d(str);
        try {
            if (d2 == null) {
                sSLContext = SSLContext.getInstance(c2);
            } else {
                sSLContext = SSLContext.getInstance(c2, d2);
            }
            String b = b(str, SSLSocketFactoryFactory.KEYSTORE, null);
            if (b == null) {
                b = b(str, SSLSocketFactoryFactory.KEYSTORE, SSLSocketFactoryFactory.SYSKEYSTORE);
            }
            char[] e = e(str);
            String f = f(str);
            if (f == null) {
                f = KeyStore.getDefaultType();
            }
            String defaultAlgorithm = KeyManagerFactory.getDefaultAlgorithm();
            String g = g(str);
            String h = h(str);
            if (h != null) {
                defaultAlgorithm = h;
            }
            if (b == null || f == null || defaultAlgorithm == null) {
                keyManagerArr = null;
            } else {
                try {
                    KeyStore keyStore = KeyStore.getInstance(f);
                    keyStore.load(new FileInputStream(b), e);
                    if (g != null) {
                        keyManagerFactory = KeyManagerFactory.getInstance(defaultAlgorithm, g);
                    } else {
                        keyManagerFactory = KeyManagerFactory.getInstance(defaultAlgorithm);
                    }
                    keyManagerFactory.init(keyStore, e);
                    keyManagerArr = keyManagerFactory.getKeyManagers();
                } catch (FileNotFoundException e2) {
                    throw new m(e2);
                } catch (IOException e3) {
                    throw new m(e3);
                } catch (KeyStoreException e4) {
                    throw new m(e4);
                } catch (UnrecoverableKeyException e5) {
                    throw new m(e5);
                } catch (CertificateException e6) {
                    throw new m(e6);
                }
            }
            String i = i(str);
            char[] j = j(str);
            String k = k(str);
            if (k == null) {
                k = KeyStore.getDefaultType();
            }
            String defaultAlgorithm2 = TrustManagerFactory.getDefaultAlgorithm();
            String l = l(str);
            String m = m(str);
            if (m != null) {
                defaultAlgorithm2 = m;
            }
            if (i == null || k == null || defaultAlgorithm2 == null) {
                trustManagerArr = null;
            } else {
                try {
                    KeyStore keyStore2 = KeyStore.getInstance(k);
                    keyStore2.load(new FileInputStream(i), j);
                    if (l != null) {
                        trustManagerFactory = TrustManagerFactory.getInstance(defaultAlgorithm2, l);
                    } else {
                        trustManagerFactory = TrustManagerFactory.getInstance(defaultAlgorithm2);
                    }
                    trustManagerFactory.init(keyStore2);
                    trustManagerArr = trustManagerFactory.getTrustManagers();
                } catch (FileNotFoundException e7) {
                    throw new m(e7);
                } catch (IOException e8) {
                    throw new m(e8);
                } catch (KeyStoreException e9) {
                    throw new m(e9);
                } catch (CertificateException e10) {
                    throw new m(e10);
                }
            }
            sSLContext.init(keyManagerArr, trustManagerArr, null);
            return sSLContext;
        } catch (KeyManagementException e11) {
            throw new m(e11);
        } catch (NoSuchAlgorithmException e12) {
            throw new m(e12);
        } catch (NoSuchProviderException e13) {
            throw new m(e13);
        }
    }

    public static byte[] a(char[] cArr) {
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

    public static String[] b(String str) {
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

    public String c(String str) {
        return b(str, SSLSocketFactoryFactory.SSLPROTOCOL, null);
    }

    public static char[] a(String str) {
        if (str == null) {
            return null;
        }
        try {
            byte[] a2 = b.a(str.substring(5));
            for (int i = 0; i < a2.length; i++) {
                byte b = a2[i];
                byte[] bArr = d;
                a2[i] = (byte) ((b ^ bArr[i % bArr.length]) & 255);
            }
            return a(a2);
        } catch (Exception unused) {
            return null;
        }
    }

    public String d(String str) {
        return b(str, SSLSocketFactoryFactory.JSSEPROVIDER, null);
    }

    public void a(Properties properties, String str) throws IllegalArgumentException {
        c(properties);
        Properties properties2 = new Properties();
        properties2.putAll(properties);
        d(properties2);
        if (str != null) {
            this.f1942a.put(str, properties2);
        } else {
            this.b = properties2;
        }
    }

    public final String b(String str, String str2, String str3) {
        String a2 = a(str, str2);
        return (a2 == null && str3 != null) ? System.getProperty(str3) : a2;
    }

    public final String a(String str, String str2) {
        String str3 = null;
        Properties properties = str != null ? (Properties) this.f1942a.get(str) : null;
        if (properties == null || (str3 = properties.getProperty(str2)) == null) {
            Properties properties2 = this.b;
            if (properties2 == null || (str3 = properties2.getProperty(str2)) != null) {
            }
            return str3;
        }
        return str3;
    }
}
