package com.coveiot.android.bleabstract.models;
/* loaded from: classes2.dex */
public enum HButtonType {
    H2(2),
    H3(3),
    H4(4),
    H5(5);
    

    /* renamed from: a  reason: collision with root package name */
    public int f3430a;

    HButtonType(int i) {
        this.f3430a = i;
    }

    public static HButtonType getByPosition(int i) {
        HButtonType hButtonType = H3;
        HButtonType hButtonType2 = H2;
        if (i != hButtonType2.f3430a) {
            if (i == hButtonType.f3430a) {
                return hButtonType;
            }
            hButtonType2 = H4;
            if (i != hButtonType2.f3430a) {
                hButtonType2 = H5;
                if (i != hButtonType2.f3430a) {
                    return hButtonType;
                }
            }
        }
        return hButtonType2;
    }

    public int getPosition() {
        return this.f3430a;
    }
}
