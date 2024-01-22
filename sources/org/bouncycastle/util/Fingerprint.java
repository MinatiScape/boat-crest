package org.bouncycastle.util;

import com.clevertap.android.sdk.Constants;
import org.bouncycastle.crypto.digests.SHA512tDigest;
/* loaded from: classes13.dex */
public class Fingerprint {
    public static char[] b = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', Constants.INAPP_POSITION_BOTTOM, Constants.INAPP_POSITION_CENTER, 'd', 'e', 'f'};

    /* renamed from: a  reason: collision with root package name */
    public final byte[] f15396a;

    public Fingerprint(byte[] bArr) {
        this.f15396a = calculateFingerprint(bArr);
    }

    public static byte[] calculateFingerprint(byte[] bArr) {
        SHA512tDigest sHA512tDigest = new SHA512tDigest(160);
        sHA512tDigest.update(bArr, 0, bArr.length);
        byte[] bArr2 = new byte[sHA512tDigest.getDigestSize()];
        sHA512tDigest.doFinal(bArr2, 0);
        return bArr2;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof Fingerprint) {
            return Arrays.areEqual(((Fingerprint) obj).f15396a, this.f15396a);
        }
        return false;
    }

    public byte[] getFingerprint() {
        return Arrays.clone(this.f15396a);
    }

    public int hashCode() {
        return Arrays.hashCode(this.f15396a);
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i != this.f15396a.length; i++) {
            if (i > 0) {
                stringBuffer.append(":");
            }
            stringBuffer.append(b[(this.f15396a[i] >>> 4) & 15]);
            stringBuffer.append(b[this.f15396a[i] & 15]);
        }
        return stringBuffer.toString();
    }
}
