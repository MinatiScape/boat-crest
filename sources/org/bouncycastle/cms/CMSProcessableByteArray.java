package org.bouncycastle.cms;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.cms.CMSObjectIdentifiers;
import org.bouncycastle.util.Arrays;
/* loaded from: classes12.dex */
public class CMSProcessableByteArray implements CMSTypedData, d {

    /* renamed from: a  reason: collision with root package name */
    public final ASN1ObjectIdentifier f14526a;
    public final byte[] b;

    public CMSProcessableByteArray(ASN1ObjectIdentifier aSN1ObjectIdentifier, byte[] bArr) {
        this.f14526a = aSN1ObjectIdentifier;
        this.b = bArr;
    }

    public CMSProcessableByteArray(byte[] bArr) {
        this(new ASN1ObjectIdentifier(CMSObjectIdentifiers.data.getId()), bArr);
    }

    @Override // org.bouncycastle.cms.CMSProcessable
    public Object getContent() {
        return Arrays.clone(this.b);
    }

    @Override // org.bouncycastle.cms.CMSTypedData
    public ASN1ObjectIdentifier getContentType() {
        return this.f14526a;
    }

    @Override // org.bouncycastle.cms.d
    public InputStream getInputStream() {
        return new ByteArrayInputStream(this.b);
    }

    @Override // org.bouncycastle.cms.CMSProcessable
    public void write(OutputStream outputStream) throws IOException, CMSException {
        outputStream.write(this.b);
    }
}
