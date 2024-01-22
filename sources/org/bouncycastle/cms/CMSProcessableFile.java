package org.bouncycastle.cms;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.cms.CMSObjectIdentifiers;
/* loaded from: classes12.dex */
public class CMSProcessableFile implements CMSTypedData, d {

    /* renamed from: a  reason: collision with root package name */
    public final ASN1ObjectIdentifier f14527a;
    public final File b;
    public final byte[] c;

    public CMSProcessableFile(File file) {
        this(file, 32768);
    }

    public CMSProcessableFile(File file, int i) {
        this(new ASN1ObjectIdentifier(CMSObjectIdentifiers.data.getId()), file, i);
    }

    public CMSProcessableFile(ASN1ObjectIdentifier aSN1ObjectIdentifier, File file, int i) {
        this.f14527a = aSN1ObjectIdentifier;
        this.b = file;
        this.c = new byte[i];
    }

    @Override // org.bouncycastle.cms.CMSProcessable
    public Object getContent() {
        return this.b;
    }

    @Override // org.bouncycastle.cms.CMSTypedData
    public ASN1ObjectIdentifier getContentType() {
        return this.f14527a;
    }

    @Override // org.bouncycastle.cms.d
    public InputStream getInputStream() throws IOException, CMSException {
        return new BufferedInputStream(new FileInputStream(this.b), 32768);
    }

    @Override // org.bouncycastle.cms.CMSProcessable
    public void write(OutputStream outputStream) throws IOException, CMSException {
        FileInputStream fileInputStream = new FileInputStream(this.b);
        while (true) {
            byte[] bArr = this.c;
            int read = fileInputStream.read(bArr, 0, bArr.length);
            if (read <= 0) {
                fileInputStream.close();
                return;
            }
            outputStream.write(this.c, 0, read);
        }
    }
}
