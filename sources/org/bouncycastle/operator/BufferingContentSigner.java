package org.bouncycastle.operator;

import java.io.OutputStream;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.util.io.BufferingOutputStream;
/* loaded from: classes13.dex */
public class BufferingContentSigner implements ContentSigner {

    /* renamed from: a  reason: collision with root package name */
    public final ContentSigner f15221a;
    public final OutputStream b;

    public BufferingContentSigner(ContentSigner contentSigner) {
        this.f15221a = contentSigner;
        this.b = new BufferingOutputStream(contentSigner.getOutputStream());
    }

    public BufferingContentSigner(ContentSigner contentSigner, int i) {
        this.f15221a = contentSigner;
        this.b = new BufferingOutputStream(contentSigner.getOutputStream(), i);
    }

    @Override // org.bouncycastle.operator.ContentSigner
    public AlgorithmIdentifier getAlgorithmIdentifier() {
        return this.f15221a.getAlgorithmIdentifier();
    }

    @Override // org.bouncycastle.operator.ContentSigner
    public OutputStream getOutputStream() {
        return this.b;
    }

    @Override // org.bouncycastle.operator.ContentSigner
    public byte[] getSignature() {
        return this.f15221a.getSignature();
    }
}
