package org.bouncycastle.pqc.crypto.xmss;

import java.io.Serializable;
/* loaded from: classes13.dex */
public final class XMSSNode implements Serializable {
    private static final long serialVersionUID = 1;
    private final int height;
    private final byte[] value;

    public XMSSNode(int i, byte[] bArr) {
        this.height = i;
        this.value = bArr;
    }

    public XMSSNode clone() {
        return new XMSSNode(getHeight(), getValue());
    }

    public int getHeight() {
        return this.height;
    }

    public byte[] getValue() {
        return XMSSUtil.cloneArray(this.value);
    }
}
