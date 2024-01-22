package org.bouncycastle.est.jcajce;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import org.bouncycastle.est.ESTClientSourceProvider;
import org.bouncycastle.est.Source;
import org.bouncycastle.util.Strings;
/* loaded from: classes13.dex */
public class b implements ESTClientSourceProvider {

    /* renamed from: a  reason: collision with root package name */
    public final SSLSocketFactory f14919a;
    public final JsseHostnameAuthorizer b;
    public final int c;
    public final ChannelBindingProvider d;
    public final Set<String> e;
    public final Long f;
    public final boolean g;

    public b(SSLSocketFactory sSLSocketFactory, JsseHostnameAuthorizer jsseHostnameAuthorizer, int i, ChannelBindingProvider channelBindingProvider, Set<String> set, Long l, boolean z) throws GeneralSecurityException {
        this.f14919a = sSLSocketFactory;
        this.b = jsseHostnameAuthorizer;
        this.c = i;
        this.d = channelBindingProvider;
        this.e = set;
        this.f = l;
        this.g = z;
    }

    @Override // org.bouncycastle.est.ESTClientSourceProvider
    public Source makeSource(String str, int i) throws IOException {
        SSLSocket sSLSocket = (SSLSocket) this.f14919a.createSocket(str, i);
        sSLSocket.setSoTimeout(this.c);
        Set<String> set = this.e;
        if (set != null && !set.isEmpty()) {
            if (this.g) {
                HashSet hashSet = new HashSet();
                String[] supportedCipherSuites = sSLSocket.getSupportedCipherSuites();
                for (int i2 = 0; i2 != supportedCipherSuites.length; i2++) {
                    hashSet.add(supportedCipherSuites[i2]);
                }
                ArrayList arrayList = new ArrayList();
                for (String str2 : this.e) {
                    if (hashSet.contains(str2)) {
                        arrayList.add(str2);
                    }
                }
                if (arrayList.isEmpty()) {
                    throw new IllegalStateException("No supplied cipher suite is supported by the provider.");
                }
                sSLSocket.setEnabledCipherSuites((String[]) arrayList.toArray(new String[arrayList.size()]));
            } else {
                Set<String> set2 = this.e;
                sSLSocket.setEnabledCipherSuites((String[]) set2.toArray(new String[set2.size()]));
            }
        }
        sSLSocket.startHandshake();
        JsseHostnameAuthorizer jsseHostnameAuthorizer = this.b;
        if (jsseHostnameAuthorizer == null || jsseHostnameAuthorizer.verified(str, sSLSocket.getSession())) {
            String lowerCase = Strings.toLowerCase(sSLSocket.getSession().getCipherSuite());
            if (lowerCase.contains("_des_") || lowerCase.contains("_des40_") || lowerCase.contains("_3des_")) {
                throw new IOException("EST clients must not use DES ciphers");
            }
            if (Strings.toLowerCase(sSLSocket.getSession().getCipherSuite()).contains("null")) {
                throw new IOException("EST clients must not use NULL ciphers");
            }
            if (Strings.toLowerCase(sSLSocket.getSession().getCipherSuite()).contains("anon")) {
                throw new IOException("EST clients must not use anon ciphers");
            }
            if (Strings.toLowerCase(sSLSocket.getSession().getCipherSuite()).contains("export")) {
                throw new IOException("EST clients must not use export ciphers");
            }
            if (sSLSocket.getSession().getProtocol().equalsIgnoreCase("tlsv1")) {
                try {
                    sSLSocket.close();
                } catch (Exception unused) {
                }
                throw new IOException("EST clients must not use TLSv1");
            }
            JsseHostnameAuthorizer jsseHostnameAuthorizer2 = this.b;
            if (jsseHostnameAuthorizer2 == null || jsseHostnameAuthorizer2.verified(str, sSLSocket.getSession())) {
                return new d(sSLSocket, this.d, this.f);
            }
            throw new IOException("Hostname was not verified: " + str);
        }
        throw new IOException("Host name could not be verified.");
    }
}
