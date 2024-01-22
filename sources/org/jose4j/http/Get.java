package org.jose4j.http;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import com.google.common.net.HttpHeaders;
import com.szabh.smable3.component.BleMessenger;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.Collection;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import org.jose4j.lang.UncheckedJoseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/* loaded from: classes13.dex */
public class Get implements SimpleGet {
    public static final Logger j = LoggerFactory.getLogger(Get.class);
    public SSLSocketFactory f;
    public HostnameVerifier g;
    public Proxy i;

    /* renamed from: a  reason: collision with root package name */
    public int f15498a = 20000;
    public int b = 20000;
    public int c = 3;
    public long d = 180;
    public boolean e = true;
    public int h = 524288;

    /* loaded from: classes13.dex */
    public static class a extends IOException {
        public a(String str) {
            super(str);
        }
    }

    public final String a(URLConnection uRLConnection, String str) throws IOException {
        StringWriter stringWriter = new StringWriter();
        InputStream inputStream = uRLConnection.getInputStream();
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, str);
            char[] cArr = new char[1024];
            int i = 0;
            while (true) {
                int read = inputStreamReader.read(cArr);
                if (-1 != read) {
                    stringWriter.write(cArr, 0, read);
                    i += read;
                    int i2 = this.h;
                    if (i2 > 0 && i > i2) {
                        throw new a("More than " + this.h + " characters have been read from the response body.");
                    }
                } else {
                    j.debug("read {} characters", Integer.valueOf(i));
                    inputStreamReader.close();
                    if (inputStream != null) {
                        inputStream.close();
                    }
                    return stringWriter.toString();
                }
            }
        } catch (Throwable th) {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    public final String b(URLConnection uRLConnection) {
        String str;
        String headerField = uRLConnection.getHeaderField("Content-Type");
        if (headerField != null) {
            try {
                String[] split = headerField.replace(HexStringBuilder.DEFAULT_SEPARATOR, "").split(";");
                int length = split.length;
                int i = 0;
                while (true) {
                    if (i >= length) {
                        str = "UTF-8";
                        break;
                    }
                    String str2 = split[i];
                    if (str2.startsWith("charset=")) {
                        str = str2.substring(8);
                        break;
                    }
                    i++;
                }
                Charset.forName(str);
                return str;
            } catch (Exception e) {
                j.debug("Unexpected problem attempted to determine the charset from the Content-Type ({}) so will default to using UTF8: {}", headerField, e);
                return "UTF-8";
            }
        }
        return "UTF-8";
    }

    public final long c(int i) {
        if (this.e) {
            return Math.min((long) (Math.pow(2.0d, i - 1) * this.d), (long) BleMessenger.TIMEOUT);
        }
        return this.d;
    }

    public final void d(URLConnection uRLConnection) {
        uRLConnection.setUseCaches(false);
        uRLConnection.setRequestProperty(HttpHeaders.CACHE_CONTROL, "no-cache");
    }

    public final void e(URLConnection uRLConnection) {
        if (uRLConnection instanceof HttpsURLConnection) {
            HttpsURLConnection httpsURLConnection = (HttpsURLConnection) uRLConnection;
            SSLSocketFactory sSLSocketFactory = this.f;
            if (sSLSocketFactory != null) {
                httpsURLConnection.setSSLSocketFactory(sSLSocketFactory);
            }
            HostnameVerifier hostnameVerifier = this.g;
            if (hostnameVerifier != null) {
                httpsURLConnection.setHostnameVerifier(hostnameVerifier);
            }
        }
    }

    @Override // org.jose4j.http.SimpleGet
    public SimpleResponse get(String str) throws IOException {
        j.debug("HTTP GET of {}", str);
        URL url = new URL(str);
        int i = 0;
        while (true) {
            try {
                Proxy proxy = this.i;
                URLConnection openConnection = proxy == null ? url.openConnection() : url.openConnection(proxy);
                openConnection.setConnectTimeout(this.f15498a);
                openConnection.setReadTimeout(this.b);
                d(openConnection);
                e(openConnection);
                HttpURLConnection httpURLConnection = (HttpURLConnection) openConnection;
                int responseCode = httpURLConnection.getResponseCode();
                String responseMessage = httpURLConnection.getResponseMessage();
                if (responseCode == 200) {
                    Response response = new Response(responseCode, responseMessage, httpURLConnection.getHeaderFields(), a(openConnection, b(openConnection)));
                    j.debug("HTTP GET of {} returned {}", url, response);
                    return response;
                }
                throw new IOException("Non 200 status code (" + responseCode + HexStringBuilder.DEFAULT_SEPARATOR + responseMessage + ") returned from " + url);
            } catch (FileNotFoundException e) {
                throw e;
            } catch (SSLHandshakeException e2) {
                throw e2;
            } catch (SSLPeerUnverifiedException e3) {
                throw e3;
            } catch (a e4) {
                throw e4;
            } catch (IOException e5) {
                i++;
                if (i <= this.c) {
                    long c = c(i);
                    j.debug("Waiting {}ms before retrying ({} of {}) HTTP GET of {} after failed attempt: {}", Long.valueOf(c), Integer.valueOf(i), Integer.valueOf(this.c), url, e5);
                    try {
                        Thread.sleep(c);
                    } catch (InterruptedException unused) {
                    }
                } else {
                    throw e5;
                }
            }
        }
    }

    public void setConnectTimeout(int i) {
        this.f15498a = i;
    }

    public void setHostnameVerifier(HostnameVerifier hostnameVerifier) {
        this.g = hostnameVerifier;
    }

    public void setHttpProxy(Proxy proxy) {
        this.i = proxy;
    }

    public void setInitialRetryWaitTime(long j2) {
        this.d = j2;
    }

    public void setProgressiveRetryWait(boolean z) {
        this.e = z;
    }

    public void setReadTimeout(int i) {
        this.b = i;
    }

    public void setResponseBodySizeLimit(int i) {
        this.h = i;
    }

    public void setRetries(int i) {
        this.c = i;
    }

    public void setSslSocketFactory(SSLSocketFactory sSLSocketFactory) {
        this.f = sSLSocketFactory;
    }

    public void setTrustedCertificates(X509Certificate... x509CertificateArr) {
        setTrustedCertificates(Arrays.asList(x509CertificateArr));
    }

    public void setTrustedCertificates(Collection<X509Certificate> collection) {
        try {
            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance("PKIX");
            KeyStore keyStore = KeyStore.getInstance("jks");
            keyStore.load(null, null);
            int i = 0;
            for (X509Certificate x509Certificate : collection) {
                StringBuilder sb = new StringBuilder();
                sb.append("alias");
                int i2 = i + 1;
                sb.append(i);
                keyStore.setCertificateEntry(sb.toString(), x509Certificate);
                i = i2;
            }
            trustManagerFactory.init(keyStore);
            TrustManager[] trustManagers = trustManagerFactory.getTrustManagers();
            SSLContext sSLContext = SSLContext.getInstance("TLS");
            sSLContext.init(null, trustManagers, null);
            this.f = sSLContext.getSocketFactory();
        } catch (IOException | KeyManagementException | KeyStoreException | NoSuchAlgorithmException | CertificateException e) {
            throw new UncheckedJoseException("Unable to initialize socket factory with custom trusted  certificates.", e);
        }
    }
}
