package org.bouncycastle.voms;

import java.util.ArrayList;
import java.util.List;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.DERIA5String;
import org.bouncycastle.asn1.x509.Attribute;
import org.bouncycastle.asn1.x509.IetfAttrSyntax;
import org.bouncycastle.cert.X509AttributeCertificateHolder;
import org.eclipse.paho.client.mqttv3.MqttTopic;
/* loaded from: classes13.dex */
public class VOMSAttribute {
    public static final String VOMS_ATTR_OID = "1.3.6.1.4.1.8005.100.100.4";

    /* renamed from: a  reason: collision with root package name */
    public X509AttributeCertificateHolder f15411a;
    public String b;
    public String c;
    public List d = new ArrayList();
    public List e = new ArrayList();

    /* loaded from: classes13.dex */
    public class FQAN {

        /* renamed from: a  reason: collision with root package name */
        public String f15412a;
        public String b;
        public String c;
        public String d;

        public FQAN(VOMSAttribute vOMSAttribute, String str) {
            this.f15412a = str;
        }

        public FQAN(VOMSAttribute vOMSAttribute, String str, String str2, String str3) {
            this.b = str;
            this.c = str2;
            this.d = str3;
        }

        public String getCapability() {
            if (this.b == null && this.f15412a != null) {
                split();
            }
            return this.d;
        }

        public String getFQAN() {
            String str = this.f15412a;
            if (str != null) {
                return str;
            }
            StringBuilder sb = new StringBuilder();
            sb.append(this.b);
            sb.append("/Role=");
            String str2 = this.c;
            String str3 = "";
            if (str2 == null) {
                str2 = "";
            }
            sb.append(str2);
            if (this.d != null) {
                str3 = "/Capability=" + this.d;
            }
            sb.append(str3);
            String sb2 = sb.toString();
            this.f15412a = sb2;
            return sb2;
        }

        public String getGroup() {
            if (this.b == null && this.f15412a != null) {
                split();
            }
            return this.b;
        }

        public String getRole() {
            if (this.b == null && this.f15412a != null) {
                split();
            }
            return this.c;
        }

        public void split() {
            this.f15412a.length();
            int indexOf = this.f15412a.indexOf("/Role=");
            if (indexOf < 0) {
                return;
            }
            this.b = this.f15412a.substring(0, indexOf);
            int i = indexOf + 6;
            int indexOf2 = this.f15412a.indexOf("/Capability=", i);
            String str = this.f15412a;
            String substring = indexOf2 < 0 ? str.substring(i) : str.substring(i, indexOf2);
            String str2 = null;
            if (substring.length() == 0) {
                substring = null;
            }
            this.c = substring;
            String substring2 = indexOf2 < 0 ? null : this.f15412a.substring(indexOf2 + 12);
            if (substring2 != null && substring2.length() != 0) {
                str2 = substring2;
            }
            this.d = str2;
        }

        public String toString() {
            return getFQAN();
        }
    }

    public VOMSAttribute(X509AttributeCertificateHolder x509AttributeCertificateHolder) {
        if (x509AttributeCertificateHolder == null) {
            throw new IllegalArgumentException("VOMSAttribute: AttributeCertificate is NULL");
        }
        this.f15411a = x509AttributeCertificateHolder;
        Attribute[] attributes = x509AttributeCertificateHolder.getAttributes(new ASN1ObjectIdentifier(VOMS_ATTR_OID));
        if (attributes == null) {
            return;
        }
        for (int i = 0; i != attributes.length; i++) {
            try {
                IetfAttrSyntax ietfAttrSyntax = IetfAttrSyntax.getInstance(attributes[i].getAttributeValues()[0]);
                String string = ((DERIA5String) ietfAttrSyntax.getPolicyAuthority().getNames()[0].getName()).getString();
                int indexOf = string.indexOf("://");
                if (indexOf < 0 || indexOf == string.length() - 1) {
                    throw new IllegalArgumentException("Bad encoding of VOMS policyAuthority : [" + string + "]");
                }
                this.c = string.substring(0, indexOf);
                this.b = string.substring(indexOf + 3);
                if (ietfAttrSyntax.getValueType() != 1) {
                    throw new IllegalArgumentException("VOMS attribute values are not encoded as octet strings, policyAuthority = " + string);
                }
                ASN1OctetString[] aSN1OctetStringArr = (ASN1OctetString[]) ietfAttrSyntax.getValues();
                for (int i2 = 0; i2 != aSN1OctetStringArr.length; i2++) {
                    String str = new String(aSN1OctetStringArr[i2].getOctets());
                    FQAN fqan = new FQAN(this, str);
                    if (!this.d.contains(str)) {
                        if (str.startsWith(MqttTopic.TOPIC_LEVEL_SEPARATOR + this.c + MqttTopic.TOPIC_LEVEL_SEPARATOR)) {
                            this.d.add(str);
                            this.e.add(fqan);
                        }
                    }
                }
            } catch (IllegalArgumentException e) {
                throw e;
            } catch (Exception unused) {
                throw new IllegalArgumentException("Badly encoded VOMS extension in AC issued by " + x509AttributeCertificateHolder.getIssuer());
            }
        }
    }

    public X509AttributeCertificateHolder getAC() {
        return this.f15411a;
    }

    public List getFullyQualifiedAttributes() {
        return this.d;
    }

    public String getHostPort() {
        return this.b;
    }

    public List getListOfFQAN() {
        return this.e;
    }

    public String getVO() {
        return this.c;
    }

    public String toString() {
        return "VO      :" + this.c + "\nHostPort:" + this.b + "\nFQANs   :" + this.e;
    }
}
