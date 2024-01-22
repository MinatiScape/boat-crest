package org.bouncycastle.jce.provider;

import com.jstyle.blesdk1860.constant.BleConst;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.cert.CRL;
import java.security.cert.CRLSelector;
import java.security.cert.CertSelector;
import java.security.cert.CertStoreException;
import java.security.cert.CertStoreParameters;
import java.security.cert.CertStoreSpi;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.security.cert.X509CRLSelector;
import java.security.cert.X509CertSelector;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.security.auth.x500.X500Principal;
import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.x509.CertificatePair;
import org.bouncycastle.jce.X509LDAPCertStoreParameters;
import org.slf4j.Marker;
/* loaded from: classes13.dex */
public class X509LDAPCertStoreSpi extends CertStoreSpi {
    public static String b = "com.sun.jndi.ldap.LdapCtxFactory";
    public static String c = "ignore";

    /* renamed from: a  reason: collision with root package name */
    public X509LDAPCertStoreParameters f15099a;

    public X509LDAPCertStoreSpi(CertStoreParameters certStoreParameters) throws InvalidAlgorithmParameterException {
        super(certStoreParameters);
        if (certStoreParameters instanceof X509LDAPCertStoreParameters) {
            this.f15099a = (X509LDAPCertStoreParameters) certStoreParameters;
            return;
        }
        throw new InvalidAlgorithmParameterException(X509LDAPCertStoreSpi.class.getName() + ": parameter must be a " + X509LDAPCertStoreParameters.class.getName() + " object\n" + certStoreParameters.toString());
    }

    public final Set a(X509CertSelector x509CertSelector, String[] strArr, String str, String str2) throws CertStoreException {
        String name;
        String str3;
        Set g;
        HashSet hashSet = new HashSet();
        try {
            if (x509CertSelector.getSubjectAsBytes() == null && x509CertSelector.getSubjectAsString() == null && x509CertSelector.getCertificate() == null) {
                g = g(str, Marker.ANY_MARKER, strArr);
                hashSet.addAll(g);
                return hashSet;
            }
            if (x509CertSelector.getCertificate() != null) {
                name = x509CertSelector.getCertificate().getSubjectX500Principal().getName("RFC1779");
                str3 = x509CertSelector.getCertificate().getSerialNumber().toString();
            } else {
                name = x509CertSelector.getSubjectAsBytes() != null ? new X500Principal(x509CertSelector.getSubjectAsBytes()).getName("RFC1779") : x509CertSelector.getSubjectAsString();
                str3 = null;
            }
            hashSet.addAll(g(str, Marker.ANY_MARKER + f(name, str2) + Marker.ANY_MARKER, strArr));
            if (str3 != null && this.f15099a.getSearchForSerialNumberIn() != null) {
                g = g(this.f15099a.getSearchForSerialNumberIn(), Marker.ANY_MARKER + str3 + Marker.ANY_MARKER, strArr);
                hashSet.addAll(g);
            }
            return hashSet;
        } catch (IOException e) {
            throw new CertStoreException("exception processing selector: " + e);
        }
    }

    public final DirContext b() throws NamingException {
        Properties properties = new Properties();
        properties.setProperty("java.naming.factory.initial", b);
        properties.setProperty("java.naming.batchsize", BleConst.GetDeviceTime);
        properties.setProperty("java.naming.provider.url", this.f15099a.getLdapURL());
        properties.setProperty("java.naming.factory.url.pkgs", "com.sun.jndi.url");
        properties.setProperty("java.naming.referral", c);
        properties.setProperty("java.naming.security.authentication", "none");
        return new InitialDirContext(properties);
    }

    public final Set c(X509CertSelector x509CertSelector) throws CertStoreException {
        String[] strArr = {this.f15099a.getCACertificateAttribute()};
        Set a2 = a(x509CertSelector, strArr, this.f15099a.getLdapCACertificateAttributeName(), this.f15099a.getCACertificateSubjectAttributeName());
        if (a2.isEmpty()) {
            a2.addAll(g(null, Marker.ANY_MARKER, strArr));
        }
        return a2;
    }

    public final Set d(X509CertSelector x509CertSelector) throws CertStoreException {
        String[] strArr = {this.f15099a.getCrossCertificateAttribute()};
        Set a2 = a(x509CertSelector, strArr, this.f15099a.getLdapCrossCertificateAttributeName(), this.f15099a.getCrossCertificateSubjectAttributeName());
        if (a2.isEmpty()) {
            a2.addAll(g(null, Marker.ANY_MARKER, strArr));
        }
        return a2;
    }

    public final Set e(X509CertSelector x509CertSelector) throws CertStoreException {
        return a(x509CertSelector, new String[]{this.f15099a.getUserCertificateAttribute()}, this.f15099a.getLdapUserCertificateAttributeName(), this.f15099a.getUserCertificateSubjectAttributeName());
    }

    @Override // java.security.cert.CertStoreSpi
    public Collection engineGetCRLs(CRLSelector cRLSelector) throws CertStoreException {
        String certificateRevocationListIssuerAttributeName;
        String name;
        String[] strArr = {this.f15099a.getCertificateRevocationListAttribute()};
        if (cRLSelector instanceof X509CRLSelector) {
            X509CRLSelector x509CRLSelector = (X509CRLSelector) cRLSelector;
            HashSet hashSet = new HashSet();
            String ldapCertificateRevocationListAttributeName = this.f15099a.getLdapCertificateRevocationListAttributeName();
            HashSet<byte[]> hashSet2 = new HashSet();
            if (x509CRLSelector.getIssuerNames() != null) {
                for (Object obj : x509CRLSelector.getIssuerNames()) {
                    if (obj instanceof String) {
                        certificateRevocationListIssuerAttributeName = this.f15099a.getCertificateRevocationListIssuerAttributeName();
                        name = (String) obj;
                    } else {
                        certificateRevocationListIssuerAttributeName = this.f15099a.getCertificateRevocationListIssuerAttributeName();
                        name = new X500Principal((byte[]) obj).getName("RFC1779");
                    }
                    String f = f(name, certificateRevocationListIssuerAttributeName);
                    hashSet2.addAll(g(ldapCertificateRevocationListAttributeName, Marker.ANY_MARKER + f + Marker.ANY_MARKER, strArr));
                }
            } else {
                hashSet2.addAll(g(ldapCertificateRevocationListAttributeName, Marker.ANY_MARKER, strArr));
            }
            hashSet2.addAll(g(null, Marker.ANY_MARKER, strArr));
            try {
                CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509", BouncyCastleProvider.PROVIDER_NAME);
                for (byte[] bArr : hashSet2) {
                    CRL generateCRL = certificateFactory.generateCRL(new ByteArrayInputStream(bArr));
                    if (x509CRLSelector.match(generateCRL)) {
                        hashSet.add(generateCRL);
                    }
                }
                return hashSet;
            } catch (Exception e) {
                throw new CertStoreException("CRL cannot be constructed from LDAP result " + e);
            }
        }
        throw new CertStoreException("selector is not a X509CRLSelector");
    }

    @Override // java.security.cert.CertStoreSpi
    public Collection engineGetCertificates(CertSelector certSelector) throws CertStoreException {
        if (certSelector instanceof X509CertSelector) {
            X509CertSelector x509CertSelector = (X509CertSelector) certSelector;
            HashSet hashSet = new HashSet();
            Set<byte[]> e = e(x509CertSelector);
            e.addAll(c(x509CertSelector));
            e.addAll(d(x509CertSelector));
            try {
                CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509", BouncyCastleProvider.PROVIDER_NAME);
                for (byte[] bArr : e) {
                    if (bArr != null && bArr.length != 0) {
                        ArrayList<byte[]> arrayList = new ArrayList();
                        arrayList.add(bArr);
                        try {
                            CertificatePair certificatePair = CertificatePair.getInstance(new ASN1InputStream(bArr).readObject());
                            arrayList.clear();
                            if (certificatePair.getForward() != null) {
                                arrayList.add(certificatePair.getForward().getEncoded());
                            }
                            if (certificatePair.getReverse() != null) {
                                arrayList.add(certificatePair.getReverse().getEncoded());
                            }
                        } catch (IOException | IllegalArgumentException unused) {
                        }
                        for (byte[] bArr2 : arrayList) {
                            try {
                                Certificate generateCertificate = certificateFactory.generateCertificate(new ByteArrayInputStream(bArr2));
                                if (x509CertSelector.match(generateCertificate)) {
                                    hashSet.add(generateCertificate);
                                }
                            } catch (Exception unused2) {
                            }
                        }
                    }
                }
                return hashSet;
            } catch (Exception e2) {
                throw new CertStoreException("certificate cannot be constructed from LDAP result: " + e2);
            }
        }
        throw new CertStoreException("selector is not a X509CertSelector");
    }

    public final String f(String str, String str2) {
        String substring = str.substring(str.toLowerCase().indexOf(str2.toLowerCase()) + str2.length());
        int indexOf = substring.indexOf(44);
        if (indexOf == -1) {
            indexOf = substring.length();
        }
        while (substring.charAt(indexOf - 1) == '\\') {
            indexOf = substring.indexOf(44, indexOf + 1);
            if (indexOf == -1) {
                indexOf = substring.length();
            }
        }
        String substring2 = substring.substring(0, indexOf);
        String substring3 = substring2.substring(substring2.indexOf(61) + 1);
        if (substring3.charAt(0) == ' ') {
            substring3 = substring3.substring(1);
        }
        if (substring3.startsWith("\"")) {
            substring3 = substring3.substring(1);
        }
        return substring3.endsWith("\"") ? substring3.substring(0, substring3.length() - 1) : substring3;
    }

    public final Set g(String str, String str2, String[] strArr) throws CertStoreException {
        String[] strArr2;
        String str3 = str + "=" + str2;
        DirContext dirContext = null;
        if (str == null) {
            str3 = null;
        }
        HashSet hashSet = new HashSet();
        try {
            try {
                dirContext = b();
                SearchControls searchControls = new SearchControls();
                searchControls.setSearchScope(2);
                searchControls.setCountLimit(0L);
                for (int i = 0; i < strArr.length; i++) {
                    searchControls.setReturningAttributes(new String[]{strArr[i]});
                    String str4 = "(&(" + str3 + ")(" + strArr2[0] + "=*))";
                    if (str3 == null) {
                        str4 = "(" + strArr2[0] + "=*)";
                    }
                    NamingEnumeration search = dirContext.search(this.f15099a.getBaseDN(), str4, searchControls);
                    while (search.hasMoreElements()) {
                        NamingEnumeration all = ((Attribute) ((SearchResult) search.next()).getAttributes().getAll().next()).getAll();
                        while (all.hasMore()) {
                            hashSet.add(all.next());
                        }
                    }
                }
                if (dirContext != null) {
                    try {
                        dirContext.close();
                    } catch (Exception unused) {
                    }
                }
                return hashSet;
            } catch (Exception e) {
                throw new CertStoreException("Error getting results from LDAP directory " + e);
            }
        } catch (Throwable th) {
            if (dirContext != null) {
                try {
                    dirContext.close();
                } catch (Exception unused2) {
                }
            }
            throw th;
        }
    }
}
