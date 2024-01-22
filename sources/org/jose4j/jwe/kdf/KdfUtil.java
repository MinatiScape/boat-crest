package org.jose4j.jwe.kdf;

import org.jose4j.base64url.Base64Url;
import org.jose4j.lang.ByteUtil;
import org.jose4j.lang.StringUtil;
/* loaded from: classes13.dex */
public class KdfUtil {

    /* renamed from: a  reason: collision with root package name */
    public Base64Url f15521a;
    public ConcatenationKeyDerivationFunctionWithSha256 b;

    public KdfUtil() {
        this(null);
    }

    public byte[] a(String str) {
        return b(this.f15521a.base64UrlDecode(str));
    }

    public byte[] b(byte[] bArr) {
        if (bArr == null) {
            bArr = ByteUtil.EMPTY_BYTES;
        }
        return ByteUtil.concat(ByteUtil.getBytes(bArr.length), bArr);
    }

    public byte[] kdf(byte[] bArr, int i, String str, String str2, String str3) {
        return this.b.kdf(bArr, i, ByteUtil.concat(b(StringUtil.getBytesUtf8(str)), a(str2), a(str3), ByteUtil.getBytes(i), ByteUtil.EMPTY_BYTES));
    }

    public KdfUtil(String str) {
        this.f15521a = new Base64Url();
        this.b = a.a(str);
    }
}
