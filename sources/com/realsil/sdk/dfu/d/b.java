package com.realsil.sdk.dfu.d;
/* loaded from: classes12.dex */
public final class b {
    public static int a(int i) {
        switch (i) {
            case 10128:
                return 0;
            case 10129:
                return 1;
            case 10130:
                return 2;
            case 10131:
                return 3;
            case 10132:
                return 4;
            case 10133:
                return 5;
            case 10134:
                return 6;
            case 10135:
                return 7;
            case 10136:
                return 8;
            default:
                return 254;
        }
    }

    public static int a(int i, int i2) {
        if (i > i2) {
            return 1;
        }
        return i == i2 ? 0 : -1;
    }
}
