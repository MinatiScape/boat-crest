package org.bouncycastle.x509.util;

import com.jstyle.blesdk1860.constant.BleConst;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.Principal;
import java.security.cert.CertificateParsingException;
import java.security.cert.X509CRL;
import java.security.cert.X509Certificate;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.security.auth.x500.X500Principal;
import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.x509.Certificate;
import org.bouncycastle.asn1.x509.CertificatePair;
import org.bouncycastle.jce.X509LDAPCertStoreParameters;
import org.bouncycastle.jce.provider.X509AttrCertParser;
import org.bouncycastle.jce.provider.X509CRLParser;
import org.bouncycastle.jce.provider.X509CertPairParser;
import org.bouncycastle.jce.provider.X509CertParser;
import org.bouncycastle.util.StoreException;
import org.bouncycastle.x509.X509AttributeCertStoreSelector;
import org.bouncycastle.x509.X509AttributeCertificate;
import org.bouncycastle.x509.X509CRLStoreSelector;
import org.bouncycastle.x509.X509CertPairStoreSelector;
import org.bouncycastle.x509.X509CertStoreSelector;
import org.bouncycastle.x509.X509CertificatePair;
import org.slf4j.Marker;
/* loaded from: classes13.dex */
public class LDAPStoreHelper {
    public static String c = "com.sun.jndi.ldap.LdapCtxFactory";
    public static String d = "ignore";
    public static int e = 32;
    public static long f = 60000;

    /* renamed from: a  reason: collision with root package name */
    public X509LDAPCertStoreParameters f15421a;
    public Map b = new HashMap(e);

    public LDAPStoreHelper(X509LDAPCertStoreParameters x509LDAPCertStoreParameters) {
        this.f15421a = x509LDAPCertStoreParameters;
    }

    public final synchronized void a(String str, List list) {
        Map map;
        Date date = new Date(System.currentTimeMillis());
        ArrayList arrayList = new ArrayList();
        arrayList.add(date);
        arrayList.add(list);
        if (this.b.containsKey(str)) {
            map = this.b;
        } else {
            if (this.b.size() >= e) {
                long time = date.getTime();
                Object obj = null;
                for (Map.Entry entry : this.b.entrySet()) {
                    long time2 = ((Date) ((List) entry.getValue()).get(0)).getTime();
                    if (time2 < time) {
                        obj = entry.getKey();
                        time = time2;
                    }
                }
                this.b.remove(obj);
            }
            map = this.b;
        }
        map.put(str, arrayList);
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0044  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0070  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x008d  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x009c A[LOOP:0: B:28:0x009c->B:30:0x009f, LOOP_START, PHI: r4 
      PHI: (r4v5 int) = (r4v1 int), (r4v6 int) binds: [B:27:0x009a, B:30:0x009f] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:38:0x00d9 A[LOOP:1: B:36:0x00d3->B:38:0x00d9, LOOP_END] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.util.List b(org.bouncycastle.x509.X509AttributeCertStoreSelector r7, java.lang.String[] r8, java.lang.String[] r9, java.lang.String[] r10) throws org.bouncycastle.util.StoreException {
        /*
            r6 = this;
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            java.util.HashSet r1 = new java.util.HashSet
            r1.<init>()
            org.bouncycastle.x509.AttributeCertificateHolder r2 = r7.getHolder()
            r3 = 0
            if (r2 == 0) goto L3d
            org.bouncycastle.x509.AttributeCertificateHolder r2 = r7.getHolder()
            java.math.BigInteger r2 = r2.getSerialNumber()
            if (r2 == 0) goto L2a
            org.bouncycastle.x509.AttributeCertificateHolder r2 = r7.getHolder()
            java.math.BigInteger r2 = r2.getSerialNumber()
            java.lang.String r2 = r2.toString()
            r1.add(r2)
        L2a:
            org.bouncycastle.x509.AttributeCertificateHolder r2 = r7.getHolder()
            java.security.Principal[] r2 = r2.getEntityNames()
            if (r2 == 0) goto L3d
            org.bouncycastle.x509.AttributeCertificateHolder r2 = r7.getHolder()
            java.security.Principal[] r2 = r2.getEntityNames()
            goto L3e
        L3d:
            r2 = r3
        L3e:
            org.bouncycastle.x509.X509AttributeCertificate r4 = r7.getAttributeCert()
            if (r4 == 0) goto L6d
            org.bouncycastle.x509.X509AttributeCertificate r4 = r7.getAttributeCert()
            org.bouncycastle.x509.AttributeCertificateHolder r4 = r4.getHolder()
            java.security.Principal[] r4 = r4.getEntityNames()
            if (r4 == 0) goto L5e
            org.bouncycastle.x509.X509AttributeCertificate r2 = r7.getAttributeCert()
            org.bouncycastle.x509.AttributeCertificateHolder r2 = r2.getHolder()
            java.security.Principal[] r2 = r2.getEntityNames()
        L5e:
            org.bouncycastle.x509.X509AttributeCertificate r4 = r7.getAttributeCert()
            java.math.BigInteger r4 = r4.getSerialNumber()
            java.lang.String r4 = r4.toString()
            r1.add(r4)
        L6d:
            r4 = 0
            if (r2 == 0) goto L87
            r3 = r2[r4]
            boolean r3 = r3 instanceof javax.security.auth.x500.X500Principal
            if (r3 == 0) goto L81
            r2 = r2[r4]
            javax.security.auth.x500.X500Principal r2 = (javax.security.auth.x500.X500Principal) r2
            java.lang.String r3 = "RFC1779"
            java.lang.String r3 = r2.getName(r3)
            goto L87
        L81:
            r2 = r2[r4]
            java.lang.String r3 = r2.getName()
        L87:
            java.math.BigInteger r2 = r7.getSerialNumber()
            if (r2 == 0) goto L98
            java.math.BigInteger r7 = r7.getSerialNumber()
            java.lang.String r7 = r7.toString()
            r1.add(r7)
        L98:
            java.lang.String r7 = "*"
            if (r3 == 0) goto Lc1
        L9c:
            int r2 = r10.length
            if (r4 >= r2) goto Lc1
            r2 = r10[r4]
            java.lang.String r2 = r6.n(r3, r2)
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            r5.append(r7)
            r5.append(r2)
            r5.append(r7)
            java.lang.String r2 = r5.toString()
            java.util.List r2 = r6.o(r9, r2, r8)
            r0.addAll(r2)
            int r4 = r4 + 1
            goto L9c
        Lc1:
            int r10 = r1.size()
            if (r10 <= 0) goto Lf1
            org.bouncycastle.jce.X509LDAPCertStoreParameters r10 = r6.f15421a
            java.lang.String r10 = r10.getSearchForSerialNumberIn()
            if (r10 == 0) goto Lf1
            java.util.Iterator r10 = r1.iterator()
        Ld3:
            boolean r2 = r10.hasNext()
            if (r2 == 0) goto Lf1
            java.lang.Object r2 = r10.next()
            java.lang.String r2 = (java.lang.String) r2
            org.bouncycastle.jce.X509LDAPCertStoreParameters r4 = r6.f15421a
            java.lang.String r4 = r4.getSearchForSerialNumberIn()
            java.lang.String[] r4 = r6.p(r4)
            java.util.List r2 = r6.o(r4, r2, r8)
            r0.addAll(r2)
            goto Ld3
        Lf1:
            int r10 = r1.size()
            if (r10 != 0) goto L100
            if (r3 != 0) goto L100
            java.util.List r7 = r6.o(r9, r7, r8)
            r0.addAll(r7)
        L100:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.x509.util.LDAPStoreHelper.b(org.bouncycastle.x509.X509AttributeCertStoreSelector, java.lang.String[], java.lang.String[], java.lang.String[]):java.util.List");
    }

    public final List c(X509CRLStoreSelector x509CRLStoreSelector, String[] strArr, String[] strArr2, String[] strArr3) throws StoreException {
        ArrayList arrayList = new ArrayList();
        HashSet<X500Principal> hashSet = new HashSet();
        if (x509CRLStoreSelector.getIssuers() != null) {
            hashSet.addAll(x509CRLStoreSelector.getIssuers());
        }
        if (x509CRLStoreSelector.getCertificateChecking() != null) {
            hashSet.add(k(x509CRLStoreSelector.getCertificateChecking()));
        }
        if (x509CRLStoreSelector.getAttrCertificateChecking() != null) {
            Principal[] principals = x509CRLStoreSelector.getAttrCertificateChecking().getIssuer().getPrincipals();
            for (int i = 0; i < principals.length; i++) {
                if (principals[i] instanceof X500Principal) {
                    hashSet.add(principals[i]);
                }
            }
        }
        String str = null;
        for (X500Principal x500Principal : hashSet) {
            str = x500Principal.getName("RFC1779");
            for (String str2 : strArr3) {
                arrayList.addAll(o(strArr2, Marker.ANY_MARKER + n(str, str2) + Marker.ANY_MARKER, strArr));
            }
        }
        if (str == null) {
            arrayList.addAll(o(strArr2, Marker.ANY_MARKER, strArr));
        }
        return arrayList;
    }

    public final List d(X509CertStoreSelector x509CertStoreSelector, String[] strArr, String[] strArr2, String[] strArr3) throws StoreException {
        ArrayList arrayList = new ArrayList();
        String m = m(x509CertStoreSelector);
        String bigInteger = x509CertStoreSelector.getSerialNumber() != null ? x509CertStoreSelector.getSerialNumber().toString() : null;
        if (x509CertStoreSelector.getCertificate() != null) {
            m = x509CertStoreSelector.getCertificate().getSubjectX500Principal().getName("RFC1779");
            bigInteger = x509CertStoreSelector.getCertificate().getSerialNumber().toString();
        }
        if (m != null) {
            for (String str : strArr3) {
                arrayList.addAll(o(strArr2, Marker.ANY_MARKER + n(m, str) + Marker.ANY_MARKER, strArr));
            }
        }
        if (bigInteger != null && this.f15421a.getSearchForSerialNumberIn() != null) {
            arrayList.addAll(o(p(this.f15421a.getSearchForSerialNumberIn()), bigInteger, strArr));
        }
        if (bigInteger == null && m == null) {
            arrayList.addAll(o(strArr2, Marker.ANY_MARKER, strArr));
        }
        return arrayList;
    }

    public final DirContext e() throws NamingException {
        Properties properties = new Properties();
        properties.setProperty("java.naming.factory.initial", c);
        properties.setProperty("java.naming.batchsize", BleConst.GetDeviceTime);
        properties.setProperty("java.naming.provider.url", this.f15421a.getLdapURL());
        properties.setProperty("java.naming.factory.url.pkgs", "com.sun.jndi.url");
        properties.setProperty("java.naming.referral", d);
        properties.setProperty("java.naming.security.authentication", "none");
        return new InitialDirContext(properties);
    }

    public final Set f(List list, X509AttributeCertStoreSelector x509AttributeCertStoreSelector) throws StoreException {
        HashSet hashSet = new HashSet();
        Iterator it = list.iterator();
        X509AttrCertParser x509AttrCertParser = new X509AttrCertParser();
        while (it.hasNext()) {
            try {
                x509AttrCertParser.engineInit(new ByteArrayInputStream((byte[]) it.next()));
                X509AttributeCertificate x509AttributeCertificate = (X509AttributeCertificate) x509AttrCertParser.engineRead();
                if (x509AttributeCertStoreSelector.match(x509AttributeCertificate)) {
                    hashSet.add(x509AttributeCertificate);
                }
            } catch (StreamParsingException unused) {
            }
        }
        return hashSet;
    }

    public final Set g(List list, X509CRLStoreSelector x509CRLStoreSelector) throws StoreException {
        HashSet hashSet = new HashSet();
        X509CRLParser x509CRLParser = new X509CRLParser();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            try {
                x509CRLParser.engineInit(new ByteArrayInputStream((byte[]) it.next()));
                X509CRL x509crl = (X509CRL) x509CRLParser.engineRead();
                if (x509CRLStoreSelector.match((Object) x509crl)) {
                    hashSet.add(x509crl);
                }
            } catch (StreamParsingException unused) {
            }
        }
        return hashSet;
    }

    public Collection getAACertificates(X509AttributeCertStoreSelector x509AttributeCertStoreSelector) throws StoreException {
        String[] p = p(this.f15421a.getAACertificateAttribute());
        String[] p2 = p(this.f15421a.getLdapAACertificateAttributeName());
        String[] p3 = p(this.f15421a.getAACertificateSubjectAttributeName());
        Set f2 = f(b(x509AttributeCertStoreSelector, p, p2, p3), x509AttributeCertStoreSelector);
        if (f2.size() == 0) {
            f2.addAll(f(b(new X509AttributeCertStoreSelector(), p, p2, p3), x509AttributeCertStoreSelector));
        }
        return f2;
    }

    public Collection getAttributeAuthorityRevocationLists(X509CRLStoreSelector x509CRLStoreSelector) throws StoreException {
        String[] p = p(this.f15421a.getAttributeAuthorityRevocationListAttribute());
        String[] p2 = p(this.f15421a.getLdapAttributeAuthorityRevocationListAttributeName());
        String[] p3 = p(this.f15421a.getAttributeAuthorityRevocationListIssuerAttributeName());
        Set g = g(c(x509CRLStoreSelector, p, p2, p3), x509CRLStoreSelector);
        if (g.size() == 0) {
            g.addAll(g(c(new X509CRLStoreSelector(), p, p2, p3), x509CRLStoreSelector));
        }
        return g;
    }

    public Collection getAttributeCertificateAttributes(X509AttributeCertStoreSelector x509AttributeCertStoreSelector) throws StoreException {
        String[] p = p(this.f15421a.getAttributeCertificateAttributeAttribute());
        String[] p2 = p(this.f15421a.getLdapAttributeCertificateAttributeAttributeName());
        String[] p3 = p(this.f15421a.getAttributeCertificateAttributeSubjectAttributeName());
        Set f2 = f(b(x509AttributeCertStoreSelector, p, p2, p3), x509AttributeCertStoreSelector);
        if (f2.size() == 0) {
            f2.addAll(f(b(new X509AttributeCertStoreSelector(), p, p2, p3), x509AttributeCertStoreSelector));
        }
        return f2;
    }

    public Collection getAttributeCertificateRevocationLists(X509CRLStoreSelector x509CRLStoreSelector) throws StoreException {
        String[] p = p(this.f15421a.getAttributeCertificateRevocationListAttribute());
        String[] p2 = p(this.f15421a.getLdapAttributeCertificateRevocationListAttributeName());
        String[] p3 = p(this.f15421a.getAttributeCertificateRevocationListIssuerAttributeName());
        Set g = g(c(x509CRLStoreSelector, p, p2, p3), x509CRLStoreSelector);
        if (g.size() == 0) {
            g.addAll(g(c(new X509CRLStoreSelector(), p, p2, p3), x509CRLStoreSelector));
        }
        return g;
    }

    public Collection getAttributeDescriptorCertificates(X509AttributeCertStoreSelector x509AttributeCertStoreSelector) throws StoreException {
        String[] p = p(this.f15421a.getAttributeDescriptorCertificateAttribute());
        String[] p2 = p(this.f15421a.getLdapAttributeDescriptorCertificateAttributeName());
        String[] p3 = p(this.f15421a.getAttributeDescriptorCertificateSubjectAttributeName());
        Set f2 = f(b(x509AttributeCertStoreSelector, p, p2, p3), x509AttributeCertStoreSelector);
        if (f2.size() == 0) {
            f2.addAll(f(b(new X509AttributeCertStoreSelector(), p, p2, p3), x509AttributeCertStoreSelector));
        }
        return f2;
    }

    public Collection getAuthorityRevocationLists(X509CRLStoreSelector x509CRLStoreSelector) throws StoreException {
        String[] p = p(this.f15421a.getAuthorityRevocationListAttribute());
        String[] p2 = p(this.f15421a.getLdapAuthorityRevocationListAttributeName());
        String[] p3 = p(this.f15421a.getAuthorityRevocationListIssuerAttributeName());
        Set g = g(c(x509CRLStoreSelector, p, p2, p3), x509CRLStoreSelector);
        if (g.size() == 0) {
            g.addAll(g(c(new X509CRLStoreSelector(), p, p2, p3), x509CRLStoreSelector));
        }
        return g;
    }

    public Collection getCACertificates(X509CertStoreSelector x509CertStoreSelector) throws StoreException {
        String[] p = p(this.f15421a.getCACertificateAttribute());
        String[] p2 = p(this.f15421a.getLdapCACertificateAttributeName());
        String[] p3 = p(this.f15421a.getCACertificateSubjectAttributeName());
        Set h = h(d(x509CertStoreSelector, p, p2, p3), x509CertStoreSelector);
        if (h.size() == 0) {
            h.addAll(h(d(new X509CertStoreSelector(), p, p2, p3), x509CertStoreSelector));
        }
        return h;
    }

    public Collection getCertificateRevocationLists(X509CRLStoreSelector x509CRLStoreSelector) throws StoreException {
        String[] p = p(this.f15421a.getCertificateRevocationListAttribute());
        String[] p2 = p(this.f15421a.getLdapCertificateRevocationListAttributeName());
        String[] p3 = p(this.f15421a.getCertificateRevocationListIssuerAttributeName());
        Set g = g(c(x509CRLStoreSelector, p, p2, p3), x509CRLStoreSelector);
        if (g.size() == 0) {
            g.addAll(g(c(new X509CRLStoreSelector(), p, p2, p3), x509CRLStoreSelector));
        }
        return g;
    }

    public Collection getCrossCertificatePairs(X509CertPairStoreSelector x509CertPairStoreSelector) throws StoreException {
        String[] p = p(this.f15421a.getCrossCertificateAttribute());
        String[] p2 = p(this.f15421a.getLdapCrossCertificateAttributeName());
        String[] p3 = p(this.f15421a.getCrossCertificateSubjectAttributeName());
        Set i = i(j(x509CertPairStoreSelector, p, p2, p3), x509CertPairStoreSelector);
        if (i.size() == 0) {
            X509CertStoreSelector x509CertStoreSelector = new X509CertStoreSelector();
            X509CertPairStoreSelector x509CertPairStoreSelector2 = new X509CertPairStoreSelector();
            x509CertPairStoreSelector2.setForwardSelector(x509CertStoreSelector);
            x509CertPairStoreSelector2.setReverseSelector(x509CertStoreSelector);
            i.addAll(i(j(x509CertPairStoreSelector2, p, p2, p3), x509CertPairStoreSelector));
        }
        return i;
    }

    public Collection getDeltaCertificateRevocationLists(X509CRLStoreSelector x509CRLStoreSelector) throws StoreException {
        String[] p = p(this.f15421a.getDeltaRevocationListAttribute());
        String[] p2 = p(this.f15421a.getLdapDeltaRevocationListAttributeName());
        String[] p3 = p(this.f15421a.getDeltaRevocationListIssuerAttributeName());
        Set g = g(c(x509CRLStoreSelector, p, p2, p3), x509CRLStoreSelector);
        if (g.size() == 0) {
            g.addAll(g(c(new X509CRLStoreSelector(), p, p2, p3), x509CRLStoreSelector));
        }
        return g;
    }

    public Collection getUserCertificates(X509CertStoreSelector x509CertStoreSelector) throws StoreException {
        String[] p = p(this.f15421a.getUserCertificateAttribute());
        String[] p2 = p(this.f15421a.getLdapUserCertificateAttributeName());
        String[] p3 = p(this.f15421a.getUserCertificateSubjectAttributeName());
        Set h = h(d(x509CertStoreSelector, p, p2, p3), x509CertStoreSelector);
        if (h.size() == 0) {
            h.addAll(h(d(new X509CertStoreSelector(), p, p2, p3), x509CertStoreSelector));
        }
        return h;
    }

    public final Set h(List list, X509CertStoreSelector x509CertStoreSelector) throws StoreException {
        HashSet hashSet = new HashSet();
        Iterator it = list.iterator();
        X509CertParser x509CertParser = new X509CertParser();
        while (it.hasNext()) {
            try {
                x509CertParser.engineInit(new ByteArrayInputStream((byte[]) it.next()));
                X509Certificate x509Certificate = (X509Certificate) x509CertParser.engineRead();
                if (x509CertStoreSelector.match((Object) x509Certificate)) {
                    hashSet.add(x509Certificate);
                }
            } catch (Exception unused) {
            }
        }
        return hashSet;
    }

    public final Set i(List list, X509CertPairStoreSelector x509CertPairStoreSelector) throws StoreException {
        X509CertificatePair x509CertificatePair;
        HashSet hashSet = new HashSet();
        int i = 0;
        while (i < list.size()) {
            try {
                try {
                    X509CertPairParser x509CertPairParser = new X509CertPairParser();
                    x509CertPairParser.engineInit(new ByteArrayInputStream((byte[]) list.get(i)));
                    x509CertificatePair = (X509CertificatePair) x509CertPairParser.engineRead();
                } catch (StreamParsingException unused) {
                    int i2 = i + 1;
                    i = i2;
                    x509CertificatePair = new X509CertificatePair(new CertificatePair(Certificate.getInstance(new ASN1InputStream((byte[]) list.get(i)).readObject()), Certificate.getInstance(new ASN1InputStream((byte[]) list.get(i2)).readObject())));
                }
                if (x509CertPairStoreSelector.match(x509CertificatePair)) {
                    hashSet.add(x509CertificatePair);
                }
            } catch (IOException | CertificateParsingException unused2) {
            }
            i++;
        }
        return hashSet;
    }

    public final List j(X509CertPairStoreSelector x509CertPairStoreSelector, String[] strArr, String[] strArr2, String[] strArr3) throws StoreException {
        ArrayList arrayList = new ArrayList();
        String m = x509CertPairStoreSelector.getForwardSelector() != null ? m(x509CertPairStoreSelector.getForwardSelector()) : null;
        if (x509CertPairStoreSelector.getCertPair() != null && x509CertPairStoreSelector.getCertPair().getForward() != null) {
            m = x509CertPairStoreSelector.getCertPair().getForward().getSubjectX500Principal().getName("RFC1779");
        }
        if (m != null) {
            for (String str : strArr3) {
                arrayList.addAll(o(strArr2, Marker.ANY_MARKER + n(m, str) + Marker.ANY_MARKER, strArr));
            }
        }
        if (m == null) {
            arrayList.addAll(o(strArr2, Marker.ANY_MARKER, strArr));
        }
        return arrayList;
    }

    public final X500Principal k(X509Certificate x509Certificate) {
        return x509Certificate.getIssuerX500Principal();
    }

    public final List l(String str) {
        List list = (List) this.b.get(str);
        long currentTimeMillis = System.currentTimeMillis();
        if (list == null || ((Date) list.get(0)).getTime() < currentTimeMillis - f) {
            return null;
        }
        return (List) list.get(1);
    }

    public final String m(X509CertStoreSelector x509CertStoreSelector) {
        try {
            byte[] subjectAsBytes = x509CertStoreSelector.getSubjectAsBytes();
            if (subjectAsBytes != null) {
                return new X500Principal(subjectAsBytes).getName("RFC1779");
            }
            return null;
        } catch (IOException e2) {
            throw new StoreException("exception processing name: " + e2.getMessage(), e2);
        }
    }

    public final String n(String str, String str2) {
        String lowerCase = str.toLowerCase();
        int indexOf = lowerCase.indexOf(str2.toLowerCase() + "=");
        if (indexOf == -1) {
            return "";
        }
        String substring = str.substring(indexOf + str2.length());
        int indexOf2 = substring.indexOf(44);
        if (indexOf2 == -1) {
            indexOf2 = substring.length();
        }
        while (substring.charAt(indexOf2 - 1) == '\\') {
            indexOf2 = substring.indexOf(44, indexOf2 + 1);
            if (indexOf2 == -1) {
                indexOf2 = substring.length();
            }
        }
        String substring2 = substring.substring(0, indexOf2);
        String substring3 = substring2.substring(substring2.indexOf(61) + 1);
        if (substring3.charAt(0) == ' ') {
            substring3 = substring3.substring(1);
        }
        if (substring3.startsWith("\"")) {
            substring3 = substring3.substring(1);
        }
        return substring3.endsWith("\"") ? substring3.substring(0, substring3.length() - 1) : substring3;
    }

    /* JADX WARN: Code restructure failed: missing block: B:39:0x0106, code lost:
        if (r3 != null) goto L28;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.util.List o(java.lang.String[] r10, java.lang.String r11, java.lang.String[] r12) throws org.bouncycastle.util.StoreException {
        /*
            Method dump skipped, instructions count: 266
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.x509.util.LDAPStoreHelper.o(java.lang.String[], java.lang.String, java.lang.String[]):java.util.List");
    }

    public final String[] p(String str) {
        return str.split("\\s+");
    }
}
