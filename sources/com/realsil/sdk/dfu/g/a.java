package com.realsil.sdk.dfu.g;

import com.realsil.sdk.core.logger.ZLogger;
import com.realsil.sdk.core.utility.DataConverter;
import com.realsil.sdk.dfu.RtkDfu;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
/* loaded from: classes12.dex */
public final class a {

    /* renamed from: a  reason: collision with root package name */
    public boolean f13603a;
    public int b;
    public int c;
    public byte[] d;

    public a(int i, int i2, byte[] bArr) {
        this.f13603a = false;
        this.b = i;
        this.c = i2;
        this.d = bArr;
        this.f13603a = RtkDfu.VDBG;
    }

    public static boolean a(int i) {
        if (i != 32 && i != 34 && i != 254 && i != 1 && i != 2 && i != 3 && i != 4) {
            switch (i) {
                case 17:
                case 18:
                case 19:
                case 20:
                case 21:
                case 22:
                    break;
                default:
                    switch (i) {
                        case 80:
                        case 81:
                        case 82:
                            break;
                        default:
                            return false;
                    }
            }
        }
        return true;
    }

    public byte[] a() {
        return this.d;
    }

    public int b() {
        return this.b;
    }

    public String toString() {
        return String.format(Locale.US, "definedId:0x%04d, data: (%d) %s", Integer.valueOf(this.b), Integer.valueOf(this.c), DataConverter.bytes2Hex(this.d));
    }

    public static boolean a(byte[] bArr, int i, int i2) {
        while (i <= i2) {
            if ((bArr[i] & 255) != 0) {
                return false;
            }
            i++;
        }
        return true;
    }

    public static boolean b(byte[] bArr, int i, int i2) {
        while (i <= i2) {
            if ((bArr[i] & 255) != 255) {
                return false;
            }
            i++;
        }
        return true;
    }

    public static List<a> a(byte[] bArr) {
        int i;
        ArrayList arrayList = new ArrayList();
        int length = bArr.length;
        int i2 = 0;
        int i3 = 1;
        while (i2 < length) {
            int i4 = ((bArr[i2 + 1] << 8) & 65280) | (bArr[i2] & 255);
            int i5 = i2 + 2;
            int i6 = i5 + 1;
            byte b = bArr[i5];
            if (!a(i4)) {
                if (i4 <= 0 || i4 >= 255) {
                    int i7 = length - 1;
                    if (!a(bArr, i6, i7) && !b(bArr, i6, i7)) {
                        ZLogger.d(String.format("invalid type: 0x%04X", Integer.valueOf(i4)));
                        arrayList = null;
                        break;
                    }
                    ZLogger.v(RtkDfu.VDBG, "no more data");
                } else {
                    ZLogger.d(String.format("undefined type: 0x%04X", Integer.valueOf(i4)));
                }
            }
            if (b == 0) {
                i2 = i6;
            } else {
                int i8 = i6 + b;
                if (i8 > length) {
                    ZLogger.d("invalid mp header length");
                    arrayList = null;
                    break;
                }
                try {
                    a aVar = new a(i4, b, Arrays.copyOfRange(bArr, i6, i8));
                    if (i4 == 1) {
                        i = i3 | 1;
                    } else if (i4 == 2) {
                        i = i3 | 2;
                    } else if (i4 == 3) {
                        i = i3 | 4;
                    } else {
                        if (i4 == 4) {
                            i = i3 | 8;
                        }
                        arrayList.add(aVar);
                        i2 = i8;
                    }
                    i3 = i;
                    arrayList.add(aVar);
                    i2 = i8;
                } catch (Exception e) {
                    ZLogger.e(e.toString());
                }
            }
        }
        if (i3 != 15) {
            ZLogger.d(String.format("miss required type 0x%04X", Integer.valueOf(i3)));
            return null;
        }
        ZLogger.v(RtkDfu.VDBG, String.format("requiredIndicator=0x%04X", Integer.valueOf(i3)));
        return arrayList;
    }
}
