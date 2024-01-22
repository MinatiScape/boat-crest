package org.bouncycastle.jcajce.spec;

import javax.crypto.spec.IvParameterSpec;
import org.bouncycastle.util.Arrays;
/* loaded from: classes13.dex */
public class AEADParameterSpec extends IvParameterSpec {

    /* renamed from: a  reason: collision with root package name */
    public final byte[] f15069a;
    public final int b;

    public AEADParameterSpec(byte[] bArr, int i) {
        this(bArr, i, null);
    }

    public AEADParameterSpec(byte[] bArr, int i, byte[] bArr2) {
        super(bArr);
        this.b = i;
        this.f15069a = Arrays.clone(bArr2);
    }

    public byte[] getAssociatedData() {
        return Arrays.clone(this.f15069a);
    }

    public int getMacSizeInBits() {
        return this.b;
    }

    public byte[] getNonce() {
        return getIV();
    }
}
