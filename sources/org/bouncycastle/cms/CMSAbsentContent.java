package org.bouncycastle.cms;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.cms.CMSObjectIdentifiers;
/* loaded from: classes12.dex */
public class CMSAbsentContent implements CMSTypedData, d {

    /* renamed from: a  reason: collision with root package name */
    public final ASN1ObjectIdentifier f14517a;

    public CMSAbsentContent() {
        this(CMSObjectIdentifiers.data);
    }

    public CMSAbsentContent(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        this.f14517a = aSN1ObjectIdentifier;
    }

    @Override // org.bouncycastle.cms.CMSProcessable
    public Object getContent() {
        return null;
    }

    @Override // org.bouncycastle.cms.CMSTypedData
    public ASN1ObjectIdentifier getContentType() {
        return this.f14517a;
    }

    @Override // org.bouncycastle.cms.d
    public InputStream getInputStream() {
        return null;
    }

    @Override // org.bouncycastle.cms.CMSProcessable
    public void write(OutputStream outputStream) throws IOException, CMSException {
    }
}
