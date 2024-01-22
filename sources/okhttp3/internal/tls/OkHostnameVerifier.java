package okhttp3.internal.tls;

import java.security.cert.Certificate;
import java.security.cert.CertificateParsingException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;
import kotlin.text.m;
import okhttp3.internal.HostnamesKt;
import okhttp3.internal.Util;
import okio.Utf8;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Marker;
/* loaded from: classes12.dex */
public final class OkHostnameVerifier implements HostnameVerifier {
    @NotNull
    public static final OkHostnameVerifier INSTANCE = new OkHostnameVerifier();

    public final String a(String str) {
        if (c(str)) {
            Locale US = Locale.US;
            Intrinsics.checkNotNullExpressionValue(US, "US");
            String lowerCase = str.toLowerCase(US);
            Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase(locale)");
            return lowerCase;
        }
        return str;
    }

    @NotNull
    public final List<String> allSubjectAltNames(@NotNull X509Certificate certificate) {
        Intrinsics.checkNotNullParameter(certificate, "certificate");
        return CollectionsKt___CollectionsKt.plus((Collection) b(certificate, 7), (Iterable) b(certificate, 2));
    }

    public final List<String> b(X509Certificate x509Certificate, int i) {
        Object obj;
        try {
            Collection<List<?>> subjectAlternativeNames = x509Certificate.getSubjectAlternativeNames();
            if (subjectAlternativeNames == null) {
                return CollectionsKt__CollectionsKt.emptyList();
            }
            ArrayList arrayList = new ArrayList();
            for (List<?> list : subjectAlternativeNames) {
                if (list != null && list.size() >= 2 && Intrinsics.areEqual(list.get(0), Integer.valueOf(i)) && (obj = list.get(1)) != null) {
                    arrayList.add((String) obj);
                }
            }
            return arrayList;
        } catch (CertificateParsingException unused) {
            return CollectionsKt__CollectionsKt.emptyList();
        }
    }

    public final boolean c(String str) {
        return str.length() == ((int) Utf8.size$default(str, 0, 0, 3, null));
    }

    public final boolean d(String str, String str2) {
        if (!(str == null || str.length() == 0) && !m.startsWith$default(str, ".", false, 2, null) && !m.endsWith$default(str, "..", false, 2, null)) {
            if (!(str2 == null || str2.length() == 0) && !m.startsWith$default(str2, ".", false, 2, null) && !m.endsWith$default(str2, "..", false, 2, null)) {
                if (!m.endsWith$default(str, ".", false, 2, null)) {
                    str = Intrinsics.stringPlus(str, ".");
                }
                String str3 = str;
                if (!m.endsWith$default(str2, ".", false, 2, null)) {
                    str2 = Intrinsics.stringPlus(str2, ".");
                }
                String a2 = a(str2);
                if (!StringsKt__StringsKt.contains$default((CharSequence) a2, (CharSequence) Marker.ANY_MARKER, false, 2, (Object) null)) {
                    return Intrinsics.areEqual(str3, a2);
                }
                if (!m.startsWith$default(a2, "*.", false, 2, null) || StringsKt__StringsKt.indexOf$default((CharSequence) a2, '*', 1, false, 4, (Object) null) != -1 || str3.length() < a2.length() || Intrinsics.areEqual("*.", a2)) {
                    return false;
                }
                String substring = a2.substring(1);
                Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String).substring(startIndex)");
                if (m.endsWith$default(str3, substring, false, 2, null)) {
                    int length = str3.length() - substring.length();
                    return length <= 0 || StringsKt__StringsKt.lastIndexOf$default((CharSequence) str3, '.', length + (-1), false, 4, (Object) null) == -1;
                }
                return false;
            }
        }
        return false;
    }

    public final boolean e(String str, X509Certificate x509Certificate) {
        String a2 = a(str);
        List<String> b = b(x509Certificate, 2);
        if ((b instanceof Collection) && b.isEmpty()) {
            return false;
        }
        for (String str2 : b) {
            if (INSTANCE.d(a2, str2)) {
                return true;
            }
        }
        return false;
    }

    public final boolean f(String str, X509Certificate x509Certificate) {
        String canonicalHost = HostnamesKt.toCanonicalHost(str);
        List<String> b = b(x509Certificate, 7);
        if ((b instanceof Collection) && b.isEmpty()) {
            return false;
        }
        for (String str2 : b) {
            if (Intrinsics.areEqual(canonicalHost, HostnamesKt.toCanonicalHost(str2))) {
                return true;
            }
        }
        return false;
    }

    @Override // javax.net.ssl.HostnameVerifier
    public boolean verify(@NotNull String host, @NotNull SSLSession session) {
        Certificate certificate;
        Intrinsics.checkNotNullParameter(host, "host");
        Intrinsics.checkNotNullParameter(session, "session");
        if (c(host)) {
            try {
                certificate = session.getPeerCertificates()[0];
                if (certificate == null) {
                    throw new NullPointerException("null cannot be cast to non-null type java.security.cert.X509Certificate");
                }
            } catch (SSLException unused) {
                return false;
            }
        }
        return verify(host, (X509Certificate) certificate);
    }

    public final boolean verify(@NotNull String host, @NotNull X509Certificate certificate) {
        Intrinsics.checkNotNullParameter(host, "host");
        Intrinsics.checkNotNullParameter(certificate, "certificate");
        return Util.canParseAsIpAddress(host) ? f(host, certificate) : e(host, certificate);
    }
}
