package com.crrepa.m0;

import com.google.android.gms.common.ConnectionResult;
/* loaded from: classes9.dex */
public class l {

    /* renamed from: a  reason: collision with root package name */
    public byte[] f7769a = new byte[ConnectionResult.DRIVE_EXTERNAL_STORAGE_REQUIRED];
    public byte[] b = new byte[ConnectionResult.DRIVE_EXTERNAL_STORAGE_REQUIRED];
    public int c = 0;
    public int d;

    public int a(h hVar) {
        int i;
        int c = hVar.c(new byte[]{-37, -34}, 2);
        try {
            Thread.sleep(1500L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        byte[] bArr = this.f7769a;
        bArr[0] = 0;
        bArr[1] = 1;
        bArr[2] = 1;
        if (c == 0) {
            c = b(hVar, 3, bArr);
        }
        if (c == 0) {
            byte[] bArr2 = this.b;
            i = c(hVar, bArr2.length, bArr2, 10000);
        } else {
            i = 0;
        }
        return i == 3 ? 0 : -1;
    }

    public final int b(h hVar, int i, byte[] bArr) {
        byte[] bArr2 = new byte[ConnectionResult.DRIVE_EXTERNAL_STORAGE_REQUIRED];
        byte b = 0;
        bArr2[0] = 16;
        int i2 = 2;
        bArr2[1] = 2;
        int i3 = 0;
        while (i != 0) {
            i--;
            int i4 = i3 + 1;
            byte b2 = bArr[i3];
            int i5 = i2 + 1;
            bArr2[i2] = b2;
            b = (byte) (b ^ b2);
            if (b2 == 16) {
                i2 = i5 + 1;
                bArr2[i5] = 16;
                i3 = i4;
            } else {
                i3 = i4;
                i2 = i5;
            }
        }
        int i6 = i2 + 1;
        bArr2[i2] = 16;
        int i7 = i6 + 1;
        bArr2[i6] = 3;
        int i8 = i7 + 1;
        bArr2[i7] = b;
        if (b == 16) {
            bArr2[i8] = 16;
            i8++;
        }
        return hVar.c(bArr2, i8);
    }

    public final int c(h hVar, int i, byte[] bArr, int i2) {
        while (true) {
            byte[] b = hVar.b(1, i2);
            if (b == null) {
                return -1002;
            }
            if (b[0] == 16) {
                byte[] b2 = hVar.b(1, i2);
                if (b2 == null) {
                    return -1002;
                }
                if (b2[0] == 2) {
                    int i3 = 0;
                    byte b3 = 0;
                    int i4 = 0;
                    while (true) {
                        byte[] b4 = hVar.b(1, i2);
                        if (b4 == null) {
                            return -1;
                        }
                        byte b5 = b4[0];
                        if (b5 == 16) {
                            byte[] b6 = hVar.b(1, i2);
                            if (b6 == null) {
                                return -1;
                            }
                            b5 = b6[0];
                            if (b5 == 3) {
                                byte[] b7 = hVar.b(1, i2);
                                if (b7 == null) {
                                    return -1;
                                }
                                byte b8 = b7[0];
                                if (b8 == 16) {
                                    byte[] b9 = hVar.b(1, i2);
                                    if (b9 == null) {
                                        return -1;
                                    }
                                    b8 = b9[0];
                                    if (b8 != 16) {
                                        return -1004;
                                    }
                                }
                                if (b8 == b3) {
                                    return i4;
                                }
                                return -1005;
                            } else if (b5 != 16) {
                                return -1004;
                            }
                        }
                        if (i4 >= i) {
                            return -1006;
                        }
                        b3 = (byte) (b3 ^ b5);
                        bArr[i3] = b5;
                        i4++;
                        i3++;
                    }
                }
            }
        }
    }

    public int d(h hVar, int i, byte[] bArr, int i2, byte[] bArr2, int i3) {
        return e(hVar, i, bArr, i2, bArr2, i3);
    }

    public final int e(h hVar, int i, byte[] bArr, int i2, byte[] bArr2, int i3) {
        byte[] bArr3 = this.f7769a;
        int i4 = 2;
        if (i > bArr3.length - 2) {
            return b.o;
        }
        int i5 = i + 2;
        int i6 = this.c;
        this.c = i6 + 1;
        int i7 = 0;
        bArr3[0] = (byte) (((byte) ((i >> 8) | 16)) | (i6 << 6));
        bArr3[1] = (byte) i;
        int i8 = 2;
        int i9 = 0;
        while (i != 0) {
            bArr3[i8] = bArr[i9];
            i--;
            i8++;
            i9++;
        }
        this.d = 0;
        while (this.d < 3 && !b.y) {
            hVar.a();
            int b = b(hVar, i5, this.f7769a);
            this.d++;
            if (b == 0) {
                byte[] bArr4 = this.b;
                int c = c(hVar, bArr4.length, bArr4, i3);
                if (c > 0) {
                    int i10 = c - 2;
                    byte[] bArr5 = this.b;
                    int i11 = ((bArr5[0] & 7) * 256) + bArr5[1];
                    if (i10 == i11) {
                        if (i10 > i2) {
                            return -1204;
                        }
                        while (i10 != 0) {
                            bArr2[i7] = this.b[i4];
                            i10--;
                            i7++;
                            i4++;
                        }
                        return i11;
                    }
                } else {
                    continue;
                }
            }
        }
        return b.n;
    }
}
