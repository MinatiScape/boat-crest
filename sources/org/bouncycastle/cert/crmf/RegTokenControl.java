package org.bouncycastle.cert.crmf;

import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.DERUTF8String;
import org.bouncycastle.asn1.crmf.CRMFObjectIdentifiers;
/* loaded from: classes12.dex */
public class RegTokenControl implements Control {
    public static final ASN1ObjectIdentifier b = CRMFObjectIdentifiers.id_regCtrl_regToken;

    /* renamed from: a  reason: collision with root package name */
    public final DERUTF8String f14466a;

    public RegTokenControl(String str) {
        this.f14466a = new DERUTF8String(str);
    }

    public RegTokenControl(DERUTF8String dERUTF8String) {
        this.f14466a = dERUTF8String;
    }

    @Override // org.bouncycastle.cert.crmf.Control
    public ASN1ObjectIdentifier getType() {
        return b;
    }

    @Override // org.bouncycastle.cert.crmf.Control
    public ASN1Encodable getValue() {
        return this.f14466a;
    }
}
