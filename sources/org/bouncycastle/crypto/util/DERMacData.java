package org.bouncycastle.crypto.util;

import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.DERTaggedObject;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Strings;
/* loaded from: classes13.dex */
public final class DERMacData {

    /* renamed from: a  reason: collision with root package name */
    public final byte[] f14875a;

    /* loaded from: classes13.dex */
    public static final class Builder {

        /* renamed from: a  reason: collision with root package name */
        public final Type f14876a;
        public ASN1OctetString b;
        public ASN1OctetString c;
        public ASN1OctetString d;
        public ASN1OctetString e;
        public byte[] f;

        public Builder(Type type, byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4) {
            this.f14876a = type;
            this.b = org.bouncycastle.crypto.util.a.a(bArr);
            this.c = org.bouncycastle.crypto.util.a.a(bArr2);
            this.d = org.bouncycastle.crypto.util.a.a(bArr3);
            this.e = org.bouncycastle.crypto.util.a.a(bArr4);
        }

        public final byte[] a(byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4, byte[] bArr5, byte[] bArr6) {
            return Arrays.concatenate(Arrays.concatenate(bArr, bArr2, bArr3), Arrays.concatenate(bArr4, bArr5, bArr6));
        }

        public DERMacData build() {
            int i = a.f14877a[this.f14876a.ordinal()];
            if (i == 1 || i == 2) {
                return new DERMacData(a(this.f14876a.getHeader(), org.bouncycastle.crypto.util.a.b(this.b), org.bouncycastle.crypto.util.a.b(this.c), org.bouncycastle.crypto.util.a.b(this.d), org.bouncycastle.crypto.util.a.b(this.e), this.f), null);
            }
            if (i == 3 || i == 4) {
                return new DERMacData(a(this.f14876a.getHeader(), org.bouncycastle.crypto.util.a.b(this.c), org.bouncycastle.crypto.util.a.b(this.b), org.bouncycastle.crypto.util.a.b(this.e), org.bouncycastle.crypto.util.a.b(this.d), this.f), null);
            }
            throw new IllegalStateException("Unknown type encountered in build");
        }

        public Builder withText(byte[] bArr) {
            this.f = org.bouncycastle.crypto.util.a.b(new DERTaggedObject(false, 0, org.bouncycastle.crypto.util.a.a(bArr)));
            return this;
        }
    }

    /* loaded from: classes13.dex */
    public enum Type {
        UNILATERALU("KC_1_U"),
        UNILATERALV("KC_1_V"),
        BILATERALU("KC_2_U"),
        BILATERALV("KC_2_V");
        
        private final String enc;

        Type(String str) {
            this.enc = str;
        }

        public byte[] getHeader() {
            return Strings.toByteArray(this.enc);
        }
    }

    /* loaded from: classes13.dex */
    public static /* synthetic */ class a {

        /* renamed from: a  reason: collision with root package name */
        public static final /* synthetic */ int[] f14877a;

        static {
            int[] iArr = new int[Type.values().length];
            f14877a = iArr;
            try {
                iArr[Type.UNILATERALU.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f14877a[Type.BILATERALU.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f14877a[Type.UNILATERALV.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f14877a[Type.BILATERALV.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public DERMacData(byte[] bArr) {
        this.f14875a = bArr;
    }

    public /* synthetic */ DERMacData(byte[] bArr, a aVar) {
        this(bArr);
    }

    public byte[] getMacData() {
        return Arrays.clone(this.f14875a);
    }
}
