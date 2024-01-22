package org.bouncycastle.dvcs;

import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.cms.ContentInfo;
/* loaded from: classes13.dex */
public abstract class DVCSMessage {

    /* renamed from: a  reason: collision with root package name */
    public final ContentInfo f14884a;

    public DVCSMessage(ContentInfo contentInfo) {
        this.f14884a = contentInfo;
    }

    public abstract ASN1Encodable getContent();

    public ASN1ObjectIdentifier getContentType() {
        return this.f14884a.getContentType();
    }
}
