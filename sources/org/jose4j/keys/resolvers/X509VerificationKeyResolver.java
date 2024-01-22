package org.jose4j.keys.resolvers;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.security.Key;
import java.security.PublicKey;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.jwx.JsonWebStructure;
import org.jose4j.keys.X509Util;
import org.jose4j.lang.ExceptionHelp;
import org.jose4j.lang.JoseException;
import org.jose4j.lang.UncheckedJoseException;
import org.jose4j.lang.UnresolvableKeyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/* loaded from: classes13.dex */
public class X509VerificationKeyResolver implements VerificationKeyResolver {
    public static final Logger d = LoggerFactory.getLogger(X509VerificationKeyResolver.class);

    /* renamed from: a  reason: collision with root package name */
    public Map<String, X509Certificate> f15558a;
    public Map<String, X509Certificate> b;
    public boolean c;

    public X509VerificationKeyResolver(List<X509Certificate> list) {
        this.f15558a = new LinkedHashMap();
        this.b = new LinkedHashMap();
        for (X509Certificate x509Certificate : list) {
            try {
                this.f15558a.put(X509Util.x5t(x509Certificate), x509Certificate);
                this.b.put(X509Util.x5tS256(x509Certificate), x509Certificate);
            } catch (UncheckedJoseException e) {
                d.warn("Unable to get certificate thumbprint.", (Throwable) e);
            }
        }
    }

    public final Key a(JsonWebSignature jsonWebSignature) throws UnresolvableKeyException {
        for (X509Certificate x509Certificate : this.f15558a.values()) {
            PublicKey publicKey = x509Certificate.getPublicKey();
            jsonWebSignature.setKey(publicKey);
            try {
            } catch (JoseException e) {
                d.debug("Verify signature didn't work: {}", ExceptionHelp.toStringWithCauses(e));
            }
            if (jsonWebSignature.verifySignature()) {
                return publicKey;
            }
        }
        throw new UnresolvableKeyException("Unable to verify the signature with any of the provided keys - SHA-1 thumbs of provided certificates: " + this.f15558a.keySet() + ".");
    }

    @Override // org.jose4j.keys.resolvers.VerificationKeyResolver
    public Key resolveKey(JsonWebSignature jsonWebSignature, List<JsonWebStructure> list) throws UnresolvableKeyException {
        String x509CertSha1ThumbprintHeaderValue = jsonWebSignature.getX509CertSha1ThumbprintHeaderValue();
        String x509CertSha256ThumbprintHeaderValue = jsonWebSignature.getX509CertSha256ThumbprintHeaderValue();
        if (x509CertSha1ThumbprintHeaderValue == null && x509CertSha256ThumbprintHeaderValue == null) {
            if (this.c) {
                return a(jsonWebSignature);
            }
            throw new UnresolvableKeyException("Neither the x5t header nor the x5t#S256 header are present in the JWS.");
        }
        X509Certificate x509Certificate = this.f15558a.get(x509CertSha1ThumbprintHeaderValue);
        if (x509Certificate == null) {
            x509Certificate = this.b.get(x509CertSha256ThumbprintHeaderValue);
        }
        if (x509Certificate == null) {
            StringBuilder sb = new StringBuilder();
            sb.append("The X.509 Certificate Thumbprint header(s) in the JWS do not identify any of the provided Certificates -");
            if (x509CertSha1ThumbprintHeaderValue != null) {
                sb.append(HexStringBuilder.DEFAULT_SEPARATOR);
                sb.append("x5t");
                sb.append("=");
                sb.append(x509CertSha1ThumbprintHeaderValue);
                sb.append(" vs. SHA-1 thumbs:");
                sb.append(this.f15558a.keySet());
            }
            if (x509CertSha256ThumbprintHeaderValue != null) {
                sb.append(HexStringBuilder.DEFAULT_SEPARATOR);
                sb.append("x5t#S256");
                sb.append("=");
                sb.append(x509CertSha256ThumbprintHeaderValue);
                sb.append(" vs. SHA-256 thumbs:");
                sb.append(this.b.keySet());
            }
            sb.append(".");
            throw new UnresolvableKeyException(sb.toString());
        }
        return x509Certificate.getPublicKey();
    }

    public void setTryAllOnNoThumbHeader(boolean z) {
        this.c = z;
    }

    public X509VerificationKeyResolver(X509Certificate... x509CertificateArr) {
        this(Arrays.asList(x509CertificateArr));
    }
}
