package org.bouncycastle.est;

import androidx.core.app.NotificationCompat;
import com.clevertap.android.sdk.Constants;
import com.google.common.net.HttpHeaders;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;
import org.bouncycastle.asn1.DERNull;
import org.bouncycastle.asn1.nist.NISTObjectIdentifiers;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.cms.CMSAttributeTableGenerator;
import org.bouncycastle.operator.DefaultDigestAlgorithmIdentifierFinder;
import org.bouncycastle.operator.DigestAlgorithmIdentifierFinder;
import org.bouncycastle.operator.DigestCalculator;
import org.bouncycastle.operator.DigestCalculatorProvider;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Strings;
import org.bouncycastle.util.encoders.Base64;
import org.bouncycastle.util.encoders.Hex;
/* loaded from: classes13.dex */
public class HttpAuth implements ESTAuth {
    public static final DigestAlgorithmIdentifierFinder f = new DefaultDigestAlgorithmIdentifierFinder();
    public static final Set<String> g;

    /* renamed from: a  reason: collision with root package name */
    public final String f14912a;
    public final String b;
    public final char[] c;
    public final SecureRandom d;
    public final DigestCalculatorProvider e;

    /* loaded from: classes13.dex */
    public class a implements ESTHijacker {
        public a() {
        }

        @Override // org.bouncycastle.est.ESTHijacker
        public ESTResponse hijack(ESTRequest eSTRequest, Source source) throws IOException {
            ESTResponse eSTResponse = new ESTResponse(eSTRequest, source);
            if (eSTResponse.getStatusCode() == 401) {
                String header = eSTResponse.getHeader(HttpHeaders.WWW_AUTHENTICATE);
                if (header != null) {
                    String lowerCase = Strings.toLowerCase(header);
                    if (lowerCase.startsWith(CMSAttributeTableGenerator.DIGEST)) {
                        return HttpAuth.this.e(eSTResponse);
                    }
                    if (!lowerCase.startsWith("basic")) {
                        throw new ESTException("Unknown auth mode: " + lowerCase);
                    }
                    eSTResponse.close();
                    Map<String, String> c = b.c("Basic", eSTResponse.getHeader(HttpHeaders.WWW_AUTHENTICATE));
                    if (HttpAuth.this.f14912a != null && !HttpAuth.this.f14912a.equals(c.get("realm"))) {
                        throw new ESTException("Supplied realm '" + HttpAuth.this.f14912a + "' does not match server realm '" + c.get("realm") + "'", null, 401, null);
                    }
                    ESTRequestBuilder withHijacker = new ESTRequestBuilder(eSTRequest).withHijacker(null);
                    if (HttpAuth.this.f14912a != null && HttpAuth.this.f14912a.length() > 0) {
                        withHijacker.setHeader(HttpHeaders.WWW_AUTHENTICATE, "Basic realm=\"" + HttpAuth.this.f14912a + "\"");
                    }
                    if (HttpAuth.this.b.contains(":")) {
                        throw new IllegalArgumentException("User must not contain a ':'");
                    }
                    char[] cArr = new char[HttpAuth.this.b.length() + 1 + HttpAuth.this.c.length];
                    System.arraycopy(HttpAuth.this.b.toCharArray(), 0, cArr, 0, HttpAuth.this.b.length());
                    cArr[HttpAuth.this.b.length()] = ':';
                    System.arraycopy(HttpAuth.this.c, 0, cArr, HttpAuth.this.b.length() + 1, HttpAuth.this.c.length);
                    withHijacker.setHeader("Authorization", "Basic " + Base64.toBase64String(Strings.toByteArray(cArr)));
                    ESTResponse doRequest = eSTRequest.getClient().doRequest(withHijacker.build());
                    Arrays.fill(cArr, (char) 0);
                    return doRequest;
                }
                throw new ESTException("Status of 401 but no WWW-Authenticate header");
            }
            return eSTResponse;
        }
    }

    static {
        HashSet hashSet = new HashSet();
        hashSet.add("realm");
        hashSet.add("nonce");
        hashSet.add("opaque");
        hashSet.add("algorithm");
        hashSet.add("qop");
        g = Collections.unmodifiableSet(hashSet);
    }

    public HttpAuth(String str, String str2, char[] cArr) {
        this(str, str2, cArr, null, null);
    }

    public HttpAuth(String str, String str2, char[] cArr, SecureRandom secureRandom, DigestCalculatorProvider digestCalculatorProvider) {
        this.f14912a = str;
        this.b = str2;
        this.c = cArr;
        this.d = secureRandom;
        this.e = digestCalculatorProvider;
    }

    public HttpAuth(String str, char[] cArr) {
        this(null, str, cArr, null, null);
    }

    public HttpAuth(String str, char[] cArr, SecureRandom secureRandom, DigestCalculatorProvider digestCalculatorProvider) {
        this(null, str, cArr, secureRandom, digestCalculatorProvider);
    }

    @Override // org.bouncycastle.est.ESTAuth
    public void applyAuth(ESTRequestBuilder eSTRequestBuilder) {
        eSTRequestBuilder.withHijacker(new a());
    }

    public final ESTResponse e(ESTResponse eSTResponse) throws IOException {
        String str;
        String str2;
        eSTResponse.close();
        ESTRequest originalRequest = eSTResponse.getOriginalRequest();
        try {
            Map<String, String> c = b.c("Digest", eSTResponse.getHeader(HttpHeaders.WWW_AUTHENTICATE));
            try {
                String path = originalRequest.getURL().toURI().getPath();
                for (String str3 : c.keySet()) {
                    if (!g.contains(str3)) {
                        throw new ESTException("Unrecognised entry in WWW-Authenticate header: '" + ((Object) str3) + "'");
                    }
                }
                String method = originalRequest.getMethod();
                String str4 = c.get("realm");
                String str5 = c.get("nonce");
                String str6 = c.get("opaque");
                String str7 = "algorithm";
                String str8 = c.get("algorithm");
                String str9 = "qop";
                String str10 = c.get("qop");
                ArrayList arrayList = new ArrayList();
                String str11 = this.f14912a;
                if (str11 != null && !str11.equals(str4)) {
                    throw new ESTException("Supplied realm '" + this.f14912a + "' does not match server realm '" + str4 + "'", null, 401, null);
                }
                if (str8 == null) {
                    str8 = MessageDigestAlgorithms.MD5;
                }
                if (str8.length() == 0) {
                    throw new ESTException("WWW-Authenticate no algorithm defined.");
                }
                String upperCase = Strings.toUpperCase(str8);
                if (str10 == null) {
                    throw new ESTException("Qop is not defined in WWW-Authenticate header.");
                }
                if (str10.length() == 0) {
                    throw new ESTException("QoP value is empty.");
                }
                String[] split = Strings.toLowerCase(str10).split(Constants.SEPARATOR_COMMA);
                int i = 0;
                while (true) {
                    String str12 = str7;
                    String str13 = str9;
                    if (i == split.length) {
                        AlgorithmIdentifier g2 = g(upperCase);
                        if (g2 == null || g2.getAlgorithm() == null) {
                            throw new IOException("auth digest algorithm unknown: " + upperCase);
                        }
                        DigestCalculator f2 = f(upperCase, g2);
                        OutputStream outputStream = f2.getOutputStream();
                        String h = h(10);
                        i(outputStream, this.b);
                        i(outputStream, ":");
                        i(outputStream, str4);
                        i(outputStream, ":");
                        j(outputStream, this.c);
                        outputStream.close();
                        byte[] digest = f2.getDigest();
                        if (upperCase.endsWith("-SESS")) {
                            DigestCalculator f3 = f(upperCase, g2);
                            OutputStream outputStream2 = f3.getOutputStream();
                            i(outputStream2, Hex.toHexString(digest));
                            i(outputStream2, ":");
                            i(outputStream2, str5);
                            i(outputStream2, ":");
                            i(outputStream2, h);
                            outputStream2.close();
                            digest = f3.getDigest();
                        }
                        String hexString = Hex.toHexString(digest);
                        DigestCalculator f4 = f(upperCase, g2);
                        OutputStream outputStream3 = f4.getOutputStream();
                        if (((String) arrayList.get(0)).equals("auth-int")) {
                            DigestCalculator f5 = f(upperCase, g2);
                            str = "auth-int";
                            OutputStream outputStream4 = f5.getOutputStream();
                            originalRequest.writeData(outputStream4);
                            outputStream4.close();
                            byte[] digest2 = f5.getDigest();
                            i(outputStream3, method);
                            i(outputStream3, ":");
                            i(outputStream3, path);
                            i(outputStream3, ":");
                            i(outputStream3, Hex.toHexString(digest2));
                        } else {
                            str = "auth-int";
                            if (((String) arrayList.get(0)).equals(Constants.AUTH)) {
                                i(outputStream3, method);
                                i(outputStream3, ":");
                                i(outputStream3, path);
                            }
                        }
                        outputStream3.close();
                        String hexString2 = Hex.toHexString(f4.getDigest());
                        DigestCalculator f6 = f(upperCase, g2);
                        OutputStream outputStream5 = f6.getOutputStream();
                        boolean contains = arrayList.contains("missing");
                        i(outputStream5, hexString);
                        i(outputStream5, ":");
                        i(outputStream5, str5);
                        i(outputStream5, ":");
                        if (contains) {
                            i(outputStream5, hexString2);
                            str2 = str;
                        } else {
                            i(outputStream5, "00000001");
                            i(outputStream5, ":");
                            i(outputStream5, h);
                            i(outputStream5, ":");
                            str2 = str;
                            if (((String) arrayList.get(0)).equals(str2)) {
                                i(outputStream5, str2);
                            } else {
                                i(outputStream5, Constants.AUTH);
                            }
                            i(outputStream5, ":");
                            i(outputStream5, hexString2);
                        }
                        outputStream5.close();
                        String hexString3 = Hex.toHexString(f6.getDigest());
                        HashMap hashMap = new HashMap();
                        hashMap.put("username", this.b);
                        hashMap.put("realm", str4);
                        hashMap.put("nonce", str5);
                        hashMap.put(NotificationCompat.MessagingStyle.Message.KEY_DATA_URI, path);
                        hashMap.put("response", hexString3);
                        if (!((String) arrayList.get(0)).equals(str2)) {
                            if (((String) arrayList.get(0)).equals(Constants.AUTH)) {
                                hashMap.put(str13, Constants.AUTH);
                            }
                            hashMap.put(str12, upperCase);
                            if (str6 != null || str6.length() == 0) {
                                hashMap.put("opaque", h(20));
                            }
                            ESTRequestBuilder withHijacker = new ESTRequestBuilder(originalRequest).withHijacker(null);
                            withHijacker.setHeader("Authorization", b.b("Digest", hashMap));
                            return originalRequest.getClient().doRequest(withHijacker.build());
                        }
                        hashMap.put(str13, str2);
                        hashMap.put("nc", "00000001");
                        hashMap.put("cnonce", h);
                        hashMap.put(str12, upperCase);
                        if (str6 != null) {
                        }
                        hashMap.put("opaque", h(20));
                        ESTRequestBuilder withHijacker2 = new ESTRequestBuilder(originalRequest).withHijacker(null);
                        withHijacker2.setHeader("Authorization", b.b("Digest", hashMap));
                        return originalRequest.getClient().doRequest(withHijacker2.build());
                    } else if (!split[i].equals(Constants.AUTH) && !split[i].equals("auth-int")) {
                        throw new ESTException("QoP value unknown: '" + i + "'");
                    } else {
                        String trim = split[i].trim();
                        if (!arrayList.contains(trim)) {
                            arrayList.add(trim);
                        }
                        i++;
                        str7 = str12;
                        str9 = str13;
                    }
                }
            } catch (Exception e) {
                throw new IOException("unable to process URL in request: " + e.getMessage());
            }
        } catch (Throwable th) {
            throw new ESTException("Parsing WWW-Authentication header: " + th.getMessage(), th, eSTResponse.getStatusCode(), new ByteArrayInputStream(eSTResponse.getHeader(HttpHeaders.WWW_AUTHENTICATE).getBytes()));
        }
    }

    public final DigestCalculator f(String str, AlgorithmIdentifier algorithmIdentifier) throws IOException {
        try {
            return this.e.get(algorithmIdentifier);
        } catch (OperatorCreationException e) {
            throw new IOException("cannot create digest calculator for " + str + ": " + e.getMessage());
        }
    }

    public final AlgorithmIdentifier g(String str) {
        if (str.endsWith("-SESS")) {
            str = str.substring(0, str.length() - 5);
        }
        return str.equals("SHA-512-256") ? new AlgorithmIdentifier(NISTObjectIdentifiers.id_sha512_256, DERNull.INSTANCE) : f.find(str);
    }

    public final String h(int i) {
        byte[] bArr = new byte[i];
        this.d.nextBytes(bArr);
        return Hex.toHexString(bArr);
    }

    public final void i(OutputStream outputStream, String str) throws IOException {
        outputStream.write(Strings.toUTF8ByteArray(str));
    }

    public final void j(OutputStream outputStream, char[] cArr) throws IOException {
        outputStream.write(Strings.toUTF8ByteArray(cArr));
    }
}
