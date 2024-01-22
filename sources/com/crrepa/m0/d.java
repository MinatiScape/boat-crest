package com.crrepa.m0;

import android.os.Message;
/* loaded from: classes9.dex */
public class d {

    /* renamed from: a  reason: collision with root package name */
    public boolean f7760a = true;

    public final int a(int i, byte[] bArr, int i2, int i3) {
        byte[] bArr2 = new byte[i3];
        System.arraycopy(bArr, i2, bArr2, 0, i3);
        if (i != 0) {
            byte[] a2 = e.a(bArr2, 0, i3);
            int i4 = i3 + i2;
            if (((a2[1] << 8) | a2[0]) == (bArr[i4 + 1] | (bArr[i4] << 8))) {
                return 1;
            }
        } else {
            byte b = 0;
            for (int i5 = 0; i5 < i3; i5++) {
                b = (byte) (b + bArr2[i5]);
            }
            if (b == bArr2[i3 + i2]) {
                return 1;
            }
        }
        return 0;
    }

    public int a(byte[] bArr, int i, h hVar) {
        int i2;
        byte b;
        int i3;
        byte[] a2;
        new Message();
        byte[] bArr2 = new byte[1030];
        byte[] bArr3 = new byte[1];
        byte b2 = 0;
        boolean z = true;
        int i4 = 0;
        boolean z2 = false;
        byte b3 = 0;
        while (i4 < 16) {
            byte[] a3 = hVar.a(1);
            if (a3 != null && (b3 = a3[0]) >= 0) {
                int i5 = b3 & 255;
                if (i5 != 21) {
                    if (i5 == 24) {
                        byte[] a4 = hVar.a(1);
                        if (a4 != null && (b3 = a4[0]) == 24) {
                            bArr3[0] = 6;
                            hVar.c(bArr3, 1);
                            hVar.a();
                            return -1;
                        }
                    } else if (i5 == 67) {
                        z2 = true;
                        z = true;
                    }
                    z2 = false;
                } else {
                    z2 = true;
                    z = false;
                }
                i4 = 16;
            }
            i4++;
        }
        if (!z2) {
            bArr3[0] = 24;
            hVar.c(bArr3, 1);
            hVar.c(bArr3, 1);
            hVar.c(bArr3, 1);
            hVar.a();
            return -2;
        }
        byte b4 = 1;
        int i6 = 0;
        int i7 = 0;
        while (true) {
            if (this.f7760a) {
                bArr2[b2] = 2;
                i2 = 1024;
            } else {
                bArr2[b2] = 1;
                i2 = 128;
            }
            bArr2[1] = b4;
            bArr2[2] = (byte) (~b4);
            int i8 = i - i6;
            if (i8 > i2) {
                i8 = i2;
            }
            if (i8 <= 0) {
                byte b5 = b2;
                int i9 = b5;
                while (true) {
                    if (i9 >= 10) {
                        b = 6;
                        break;
                    }
                    bArr3[b5] = 4;
                    hVar.c(bArr3, 1);
                    byte[] a5 = hVar.a(1);
                    if (a5 != null && (b3 = a5[b5]) > 0) {
                        b = 6;
                        if (b3 == 6) {
                            break;
                        }
                    }
                    i9++;
                }
                hVar.a();
                if (b3 == b) {
                    return i6;
                }
                return -5;
            }
            int i10 = 3;
            b(bArr2, 3, b2, i8);
            System.arraycopy(bArr, i7, bArr2, 3, i8);
            i7 += i8;
            if (i8 < i2) {
                bArr2[i8 + 3] = 26;
            }
            if (z) {
                byte[] a6 = e.a(bArr2, 3, i2);
                bArr2[i2 + 3] = a6[1];
                bArr2[i2 + 4] = a6[b2];
            } else {
                byte b6 = b2;
                while (true) {
                    i3 = i2 + 3;
                    if (i10 >= i3) {
                        break;
                    }
                    b6 = (byte) (b6 + bArr2[i10]);
                    i10++;
                }
                bArr2[i3] = b6;
            }
            int i11 = i6;
            int i12 = 0;
            while (i12 < 10) {
                try {
                    hVar.f7764a.acquire(hVar.c.size());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                hVar.c.clear();
                hVar.c(bArr2, i2 + 4 + (z ? 1 : 0));
                byte[] a7 = hVar.a(1);
                if (a7 != null && (b3 = a7[0]) >= 0) {
                    int i13 = b3 & 255;
                    if (i13 == 6) {
                        i11 += i2;
                        b4 = (byte) (b4 + 1);
                        z2 = true;
                        i12 = 10;
                    } else if (i13 == 24 && (a2 = hVar.a(1)) != null && (b3 = a2[0]) > 0 && b3 == 24) {
                        bArr3[0] = 6;
                        hVar.c(bArr3, 1);
                        hVar.a();
                        return -1;
                    } else {
                        z2 = false;
                    }
                }
                i12++;
            }
            if (!z2) {
                bArr3[0] = 24;
                hVar.c(bArr3, 1);
                hVar.c(bArr3, 1);
                hVar.c(bArr3, 1);
                hVar.a();
                return -4;
            }
            i6 = i11;
            b2 = 0;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0033, code lost:
        r10 = 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:5:0x0021, code lost:
        if ((r2 % 1024) == 0) goto L126;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x002f, code lost:
        if ((r2 % 128) == 0) goto L126;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0031, code lost:
        r10 = 0;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public int a(byte[] r22, int r23, com.crrepa.m0.h r24, android.os.Handler r25) {
        /*
            Method dump skipped, instructions count: 461
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.crrepa.m0.d.a(byte[], int, com.crrepa.m0.h, android.os.Handler):int");
    }

    public byte[] a(int i, h hVar) {
        int i2;
        int i3;
        int i4;
        byte[] bArr = new byte[i];
        byte[] bArr2 = new byte[1030];
        byte[] bArr3 = new byte[1];
        int i5 = 0;
        byte[] bArr4 = {67};
        int i6 = 10;
        byte b = 1;
        int i7 = 0;
        int i8 = 0;
        int i9 = 0;
        byte b2 = 0;
        int i10 = 67;
        while (true) {
            int i11 = i5;
            int i12 = i11;
            while (i11 < 16) {
                if (i10 != 0) {
                    hVar.c(bArr4, 1);
                }
                byte[] a2 = hVar.a(1);
                if (a2 == null || (b2 = a2[0]) < 0) {
                    i3 = 1;
                } else {
                    int i13 = b2 & 255;
                    if (i13 == 1) {
                        i3 = 1;
                        i4 = 128;
                    } else if (i13 == 2) {
                        i3 = 1;
                        i4 = 1024;
                    } else if (i13 == 4) {
                        hVar.a();
                        bArr3[0] = 6;
                        hVar.c(bArr3, 1);
                        return bArr;
                    } else if (i13 != 24) {
                        i3 = 1;
                    } else {
                        i3 = 1;
                        byte[] a3 = hVar.a(1);
                        if (a3 != null && (b2 = a3[0]) > 0 && b2 == 24) {
                            hVar.a();
                            bArr3[0] = 6;
                            hVar.c(bArr3, 1);
                            return null;
                        }
                    }
                    i12 = i3;
                    i8 = i4;
                    i11 = 16;
                }
                i11 += i3;
            }
            if (i12 != 0) {
                if (i10 == 67) {
                    i9 = 1;
                }
                bArr2[0] = b2;
                int i14 = i8 + i9 + 3;
                byte[] a4 = hVar.a(i14);
                if (a4 == null) {
                    i5 = 0;
                    i2 = 1;
                } else {
                    System.arraycopy(a4, 0, bArr2, 1, i14);
                    if (bArr2[1] == (~bArr2[2]) && (bArr2[1] == b || bArr2[1] == b - 1)) {
                        if (a(i9, bArr2, 3, i8) != 0) {
                            if (bArr2[1] == b) {
                                int i15 = i - i7;
                                if (i15 > i8) {
                                    i15 = i8;
                                }
                                if (i15 > 0) {
                                    System.arraycopy(bArr2, 3, bArr, i7, i15);
                                    i7 += i15;
                                }
                                i6 = 11;
                                b = (byte) (b + 1);
                            }
                            i6--;
                            if (i6 <= 0) {
                                hVar.a();
                                bArr3[0] = 24;
                                hVar.c(bArr3, 1);
                                hVar.c(bArr3, 1);
                                hVar.c(bArr3, 1);
                                return null;
                            }
                            i5 = 0;
                            i2 = 1;
                            bArr3[0] = 6;
                            hVar.c(bArr3, i2);
                            i10 = i5;
                        }
                    }
                    i2 = 1;
                    i5 = 0;
                }
                hVar.a();
                bArr3[i5] = 21;
                hVar.c(bArr3, i2);
                i10 = i5;
            } else if (i10 != 67) {
                hVar.a();
                bArr3[0] = 24;
                hVar.c(bArr3, 1);
                hVar.c(bArr3, 1);
                hVar.c(bArr3, 1);
                return null;
            } else {
                i5 = 0;
                i10 = 21;
            }
        }
    }

    public final void b(byte[] bArr, int i, byte b, int i2) {
        if (bArr.length < i + i2) {
            return;
        }
        while (i < i2) {
            bArr[i] = 0;
            i++;
        }
    }
}
