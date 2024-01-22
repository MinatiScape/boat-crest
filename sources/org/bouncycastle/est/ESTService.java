package org.bouncycastle.est;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import com.google.common.net.HttpHeaders;
import com.mappls.sdk.services.api.alongroute.POICriteria;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import java.util.TimeZone;
import java.util.regex.Pattern;
import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERPrintableString;
import org.bouncycastle.asn1.cms.ContentInfo;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.cert.X509CRLHolder;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cmc.CMCException;
import org.bouncycastle.cmc.SimplePKIResponse;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.pkcs.PKCS10CertificationRequest;
import org.bouncycastle.pkcs.PKCS10CertificationRequestBuilder;
import org.bouncycastle.util.Selector;
import org.bouncycastle.util.Store;
import org.bouncycastle.util.encoders.Base64;
import org.eclipse.paho.client.mqttv3.MqttTopic;
/* loaded from: classes13.dex */
public class ESTService {
    public static final String CACERTS = "/cacerts";
    public static final String CSRATTRS = "/csrattrs";
    public static final String FULLCMC = "/fullcmc";
    public static final String SERVERGEN = "/serverkeygen";
    public static final String SIMPLE_ENROLL = "/simpleenroll";
    public static final String SIMPLE_REENROLL = "/simplereenroll";
    public static final Pattern c;
    public static final Set<String> illegalParts;

    /* renamed from: a  reason: collision with root package name */
    public final String f14909a;
    public final ESTClientProvider b;

    /* loaded from: classes13.dex */
    public class a implements ESTSourceConnectionListener {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ PKCS10CertificationRequestBuilder f14910a;
        public final /* synthetic */ ContentSigner b;

        public a(PKCS10CertificationRequestBuilder pKCS10CertificationRequestBuilder, ContentSigner contentSigner) {
            this.f14910a = pKCS10CertificationRequestBuilder;
            this.b = contentSigner;
        }

        @Override // org.bouncycastle.est.ESTSourceConnectionListener
        public ESTRequest onConnection(Source source, ESTRequest eSTRequest) throws IOException {
            if (source instanceof TLSUniqueProvider) {
                TLSUniqueProvider tLSUniqueProvider = (TLSUniqueProvider) source;
                if (tLSUniqueProvider.isTLSUniqueAvailable()) {
                    PKCS10CertificationRequestBuilder pKCS10CertificationRequestBuilder = new PKCS10CertificationRequestBuilder(this.f14910a);
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    pKCS10CertificationRequestBuilder.setAttribute(PKCSObjectIdentifiers.pkcs_9_at_challengePassword, new DERPrintableString(Base64.toBase64String(tLSUniqueProvider.getTLSUnique())));
                    byteArrayOutputStream.write(ESTService.this.b(pKCS10CertificationRequestBuilder.build(this.b).getEncoded()).getBytes());
                    byteArrayOutputStream.flush();
                    ESTRequestBuilder withData = new ESTRequestBuilder(eSTRequest).withData(byteArrayOutputStream.toByteArray());
                    withData.setHeader("Content-Type", "application/pkcs10");
                    withData.setHeader("Content-Transfer-Encoding", POICriteria.GEOMETRY_BASE64);
                    withData.setHeader("Content-Length", Long.toString(byteArrayOutputStream.size()));
                    return withData.build();
                }
            }
            throw new IOException("Source does not supply TLS unique.");
        }
    }

    static {
        HashSet hashSet = new HashSet();
        illegalParts = hashSet;
        hashSet.add("cacerts");
        hashSet.add("simpleenroll");
        hashSet.add("simplereenroll");
        hashSet.add("fullcmc");
        hashSet.add("serverkeygen");
        hashSet.add("csrattrs");
        c = Pattern.compile("^[0-9a-zA-Z_\\-.~!$&'()*+,;=]+");
    }

    public ESTService(String str, String str2, ESTClientProvider eSTClientProvider) {
        String str3;
        String d = d(str);
        if (str2 != null) {
            str3 = "https://" + d + "/.well-known/est/" + c(str2);
        } else {
            str3 = "https://" + d + "/.well-known/est";
        }
        this.f14909a = str3;
        this.b = eSTClientProvider;
    }

    public static X509CertificateHolder[] storeToArray(Store<X509CertificateHolder> store) {
        return storeToArray(store, null);
    }

    public static X509CertificateHolder[] storeToArray(Store<X509CertificateHolder> store, Selector<X509CertificateHolder> selector) {
        Collection<X509CertificateHolder> matches = store.getMatches(selector);
        return (X509CertificateHolder[]) matches.toArray(new X509CertificateHolder[matches.size()]);
    }

    public final String b(byte[] bArr) {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        int i = 0;
        do {
            int i2 = i + 48;
            if (i2 < bArr.length) {
                printWriter.print(Base64.toBase64String(bArr, i, 48));
                i = i2;
            } else {
                printWriter.print(Base64.toBase64String(bArr, i, bArr.length - i));
                i = bArr.length;
            }
            printWriter.print('\n');
        } while (i < bArr.length);
        printWriter.flush();
        return stringWriter.toString();
    }

    public final String c(String str) {
        while (str.endsWith(MqttTopic.TOPIC_LEVEL_SEPARATOR) && str.length() > 0) {
            str = str.substring(0, str.length() - 1);
        }
        while (str.startsWith(MqttTopic.TOPIC_LEVEL_SEPARATOR) && str.length() > 0) {
            str = str.substring(1);
        }
        if (str.length() != 0) {
            if (!c.matcher(str).matches()) {
                throw new IllegalArgumentException("Server path " + str + " contains invalid characters");
            } else if (illegalParts.contains(str)) {
                throw new IllegalArgumentException("Label " + str + " is a reserved path segment.");
            } else {
                return str;
            }
        }
        throw new IllegalArgumentException("Label set but after trimming '/' is not zero length string.");
    }

    public final String d(String str) {
        while (str.endsWith(MqttTopic.TOPIC_LEVEL_SEPARATOR) && str.length() > 0) {
            try {
                str = str.substring(0, str.length() - 1);
            } catch (Exception e) {
                if (e instanceof IllegalArgumentException) {
                    throw ((IllegalArgumentException) e);
                }
                throw new IllegalArgumentException("Scheme and host is invalid: " + e.getMessage(), e);
            }
        }
        if (str.contains("://")) {
            throw new IllegalArgumentException("Server contains scheme, must only be <dnsname/ipaddress>:port, https:// will be added arbitrarily.");
        }
        URL url = new URL("https://" + str);
        if (url.getPath().length() != 0 && !url.getPath().equals(MqttTopic.TOPIC_LEVEL_SEPARATOR)) {
            throw new IllegalArgumentException("Server contains path, must only be <dnsname/ipaddress>:port, a path of '/.well-known/est/<label>' will be added arbitrarily.");
        }
        return str;
    }

    public CACertsResponse getCACerts() throws Exception {
        URL url;
        ESTRequest build;
        ESTResponse doRequest;
        Store<X509CertificateHolder> store;
        Store<X509CRLHolder> store2;
        Store<X509CertificateHolder> store3;
        Store<X509CRLHolder> store4;
        String str;
        ESTResponse eSTResponse = null;
        try {
            url = new URL(this.f14909a + CACERTS);
            ESTClient makeClient = this.b.makeClient();
            build = new ESTRequestBuilder("GET", url).withClient(makeClient).build();
            doRequest = makeClient.doRequest(build);
        } catch (Throwable th) {
            th = th;
        }
        try {
            if (doRequest.getStatusCode() == 200) {
                if (!"application/pkcs7-mime".equals(doRequest.getHeaders().getFirstValue("Content-Type"))) {
                    if (doRequest.getHeaders().getFirstValue("Content-Type") != null) {
                        str = " got " + doRequest.getHeaders().getFirstValue("Content-Type");
                    } else {
                        str = " but was not present.";
                    }
                    throw new ESTException("Response : " + url.toString() + "Expecting application/pkcs7-mime " + str, null, doRequest.getStatusCode(), doRequest.getInputStream());
                }
                if (doRequest.getContentLength() == null || doRequest.getContentLength().longValue() <= 0) {
                    store3 = null;
                    store4 = null;
                } else {
                    SimplePKIResponse simplePKIResponse = new SimplePKIResponse(ContentInfo.getInstance((ASN1Sequence) new ASN1InputStream(doRequest.getInputStream()).readObject()));
                    store3 = simplePKIResponse.getCertificates();
                    store4 = simplePKIResponse.getCRLs();
                }
                store = store3;
                store2 = store4;
            } else if (doRequest.getStatusCode() != 204) {
                throw new ESTException("Get CACerts: " + url.toString(), null, doRequest.getStatusCode(), doRequest.getInputStream());
            } else {
                store = null;
                store2 = null;
            }
            CACertsResponse cACertsResponse = new CACertsResponse(store, store2, build, doRequest.getSource(), this.b.isTrusted());
            try {
                doRequest.close();
                e = null;
            } catch (Exception e) {
                e = e;
            }
            if (e != null) {
                if (e instanceof ESTException) {
                    throw e;
                }
                throw new ESTException("Get CACerts: " + url.toString(), e, doRequest.getStatusCode(), null);
            }
            return cACertsResponse;
        } catch (Throwable th2) {
            th = th2;
            eSTResponse = doRequest;
            try {
                if (th instanceof ESTException) {
                    throw th;
                }
                throw new ESTException(th.getMessage(), th);
            } catch (Throwable th3) {
                if (eSTResponse != null) {
                    try {
                        eSTResponse.close();
                    } catch (Exception unused) {
                    }
                }
                throw th3;
            }
        }
    }

    /* JADX WARN: Can't wrap try/catch for region: R(11:3|4|5|6|7|(2:9|(2:14|15))(2:32|(5:36|18|19|20|(2:22|(2:24|25)(2:26|27))(2:28|29)))|17|18|19|20|(0)(0)) */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x00a5, code lost:
        r1 = e;
     */
    /* JADX WARN: Removed duplicated region for block: B:26:0x00a8  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00bd  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public org.bouncycastle.est.CSRRequestResponse getCSRAttributes() throws org.bouncycastle.est.ESTException {
        /*
            Method dump skipped, instructions count: 285
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.est.ESTService.getCSRAttributes():org.bouncycastle.est.CSRRequestResponse");
    }

    public EnrollmentResponse handleEnrollResponse(ESTResponse eSTResponse) throws IOException {
        long time;
        ESTRequest originalRequest = eSTResponse.getOriginalRequest();
        if (eSTResponse.getStatusCode() != 202) {
            if (eSTResponse.getStatusCode() == 200) {
                try {
                    return new EnrollmentResponse(new SimplePKIResponse(ContentInfo.getInstance(new ASN1InputStream(eSTResponse.getInputStream()).readObject())).getCertificates(), -1L, null, eSTResponse.getSource());
                } catch (CMCException e) {
                    throw new ESTException(e.getMessage(), e.getCause());
                }
            }
            throw new ESTException("Simple Enroll: " + originalRequest.getURL().toString(), null, eSTResponse.getStatusCode(), eSTResponse.getInputStream());
        }
        String header = eSTResponse.getHeader(HttpHeaders.RETRY_AFTER);
        if (header == null) {
            throw new ESTException("Got Status 202 but not Retry-After header from: " + originalRequest.getURL().toString());
        }
        try {
            try {
                time = System.currentTimeMillis() + (Long.parseLong(header) * 1000);
            } catch (NumberFormatException unused) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.US);
                simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
                time = simpleDateFormat.parse(header).getTime();
            }
            return new EnrollmentResponse(null, time, originalRequest, eSTResponse.getSource());
        } catch (Exception e2) {
            throw new ESTException("Unable to parse Retry-After header:" + originalRequest.getURL().toString() + HexStringBuilder.DEFAULT_SEPARATOR + e2.getMessage(), null, eSTResponse.getStatusCode(), eSTResponse.getInputStream());
        }
    }

    public EnrollmentResponse simpleEnroll(EnrollmentResponse enrollmentResponse) throws Exception {
        if (this.b.isTrusted()) {
            ESTResponse eSTResponse = null;
            try {
                ESTClient makeClient = this.b.makeClient();
                eSTResponse = makeClient.doRequest(new ESTRequestBuilder(enrollmentResponse.getRequestToRetry()).withClient(makeClient).build());
                return handleEnrollResponse(eSTResponse);
            } catch (Throwable th) {
                try {
                    if (th instanceof ESTException) {
                        throw th;
                    }
                    throw new ESTException(th.getMessage(), th);
                } finally {
                    if (eSTResponse != null) {
                        eSTResponse.close();
                    }
                }
            }
        }
        throw new IllegalStateException("No trust anchors.");
    }

    public EnrollmentResponse simpleEnroll(boolean z, PKCS10CertificationRequest pKCS10CertificationRequest, ESTAuth eSTAuth) throws IOException {
        if (this.b.isTrusted()) {
            ESTResponse eSTResponse = null;
            try {
                byte[] bytes = b(pKCS10CertificationRequest.getEncoded()).getBytes();
                StringBuilder sb = new StringBuilder();
                sb.append(this.f14909a);
                sb.append(z ? SIMPLE_REENROLL : SIMPLE_ENROLL);
                URL url = new URL(sb.toString());
                ESTClient makeClient = this.b.makeClient();
                ESTRequestBuilder withClient = new ESTRequestBuilder("POST", url).withData(bytes).withClient(makeClient);
                withClient.addHeader("Content-Type", "application/pkcs10");
                withClient.addHeader("Content-Length", "" + bytes.length);
                withClient.addHeader("Content-Transfer-Encoding", POICriteria.GEOMETRY_BASE64);
                if (eSTAuth != null) {
                    eSTAuth.applyAuth(withClient);
                }
                eSTResponse = makeClient.doRequest(withClient.build());
                return handleEnrollResponse(eSTResponse);
            } catch (Throwable th) {
                try {
                    if (th instanceof ESTException) {
                        throw th;
                    }
                    throw new ESTException(th.getMessage(), th);
                } finally {
                    if (eSTResponse != null) {
                        eSTResponse.close();
                    }
                }
            }
        }
        throw new IllegalStateException("No trust anchors.");
    }

    public EnrollmentResponse simpleEnrollPoP(boolean z, PKCS10CertificationRequestBuilder pKCS10CertificationRequestBuilder, ContentSigner contentSigner, ESTAuth eSTAuth) throws IOException {
        if (this.b.isTrusted()) {
            ESTResponse eSTResponse = null;
            try {
                StringBuilder sb = new StringBuilder();
                sb.append(this.f14909a);
                sb.append(z ? SIMPLE_REENROLL : SIMPLE_ENROLL);
                URL url = new URL(sb.toString());
                ESTClient makeClient = this.b.makeClient();
                ESTRequestBuilder withConnectionListener = new ESTRequestBuilder("POST", url).withClient(makeClient).withConnectionListener(new a(pKCS10CertificationRequestBuilder, contentSigner));
                if (eSTAuth != null) {
                    eSTAuth.applyAuth(withConnectionListener);
                }
                eSTResponse = makeClient.doRequest(withConnectionListener.build());
                return handleEnrollResponse(eSTResponse);
            } catch (Throwable th) {
                try {
                    if (th instanceof ESTException) {
                        throw th;
                    }
                    throw new ESTException(th.getMessage(), th);
                } finally {
                    if (eSTResponse != null) {
                        eSTResponse.close();
                    }
                }
            }
        }
        throw new IllegalStateException("No trust anchors.");
    }
}
