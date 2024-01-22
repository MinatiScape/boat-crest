package com.crrepa.k;

import com.crrepa.ble.conn.type.CRPEcgMeasureType;
/* loaded from: classes9.dex */
public class b {

    /* loaded from: classes9.dex */
    public static /* synthetic */ class a {

        /* renamed from: a  reason: collision with root package name */
        public static final /* synthetic */ int[] f7741a;

        static {
            int[] iArr = new int[CRPEcgMeasureType.values().length];
            f7741a = iArr;
            try {
                iArr[CRPEcgMeasureType.TYHX.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f7741a[CRPEcgMeasureType.TI.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    public com.crrepa.k.a a(CRPEcgMeasureType cRPEcgMeasureType) {
        int i = a.f7741a[cRPEcgMeasureType.ordinal()];
        if (i != 1) {
            if (i != 2) {
                return null;
            }
            return new c();
        }
        return new d();
    }
}
