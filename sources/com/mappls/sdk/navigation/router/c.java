package com.mappls.sdk.navigation.router;

import com.clevertap.android.sdk.Constants;
import com.mappls.sdk.navigation.h;
import com.mappls.sdk.navigation.o;
/* loaded from: classes11.dex */
public final class c {

    /* renamed from: a  reason: collision with root package name */
    public final int f12941a;
    public int b;
    public float c;
    public int[] d;

    public c(int i) {
        this.f12941a = i;
    }

    public static c a(int i, float f, boolean z) {
        c a2 = a(68, z);
        a2.b = i;
        a2.c = f;
        return a2;
    }

    public static c a(int i, boolean z) {
        if (i == 6 && z) {
            i = 41;
        } else if (i == 68 && z) {
            i = 69;
        }
        return new c(i);
    }

    /* JADX WARN: Removed duplicated region for block: B:52:0x00c1  */
    /* JADX WARN: Removed duplicated region for block: B:56:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static com.mappls.sdk.navigation.router.c a(java.lang.String r3, boolean r4) {
        /*
            java.lang.String r0 = "CONTINUE"
            boolean r0 = r0.equalsIgnoreCase(r3)
            if (r0 == 0) goto Lf
            r3 = 7
        L9:
            com.mappls.sdk.navigation.router.c r3 = a(r3, r4)
            goto Lbf
        Lf:
            java.lang.String r0 = "TURN_LEFT"
            boolean r0 = r0.equalsIgnoreCase(r3)
            if (r0 == 0) goto L19
            r3 = 0
            goto L9
        L19:
            java.lang.String r0 = "TURN_SLIGHTLY_LEFT"
            boolean r0 = r0.equalsIgnoreCase(r3)
            if (r0 == 0) goto L23
            r3 = 2
            goto L9
        L23:
            java.lang.String r0 = "TURN_SHARPLY_LEFT"
            boolean r0 = r0.equalsIgnoreCase(r3)
            if (r0 == 0) goto L2d
            r3 = 1
            goto L9
        L2d:
            java.lang.String r0 = "TURN_RIGHT"
            boolean r0 = r0.equalsIgnoreCase(r3)
            if (r0 == 0) goto L37
            r3 = 3
            goto L9
        L37:
            java.lang.String r0 = "TURN_SLIGHTLY_RIGHT"
            boolean r0 = r0.equalsIgnoreCase(r3)
            if (r0 == 0) goto L41
            r3 = 5
            goto L9
        L41:
            java.lang.String r0 = "TURN_SHARPLY_RIGHT"
            boolean r0 = r0.equalsIgnoreCase(r3)
            r1 = 4
            if (r0 == 0) goto L50
            com.mappls.sdk.navigation.router.c r3 = a(r1, r4)
            goto Lbf
        L50:
            java.lang.String r0 = "KEEP_LEFT"
            boolean r0 = r0.equalsIgnoreCase(r3)
            if (r0 == 0) goto L5b
            r3 = 9
            goto L9
        L5b:
            java.lang.String r0 = "KEEP_RIGHT"
            boolean r0 = r0.equalsIgnoreCase(r3)
            if (r0 == 0) goto L66
            r3 = 10
            goto L9
        L66:
            java.lang.String r0 = "U_TURN"
            boolean r0 = r0.equalsIgnoreCase(r3)
            if (r0 == 0) goto L70
            r3 = 6
            goto L9
        L70:
            java.lang.String r0 = "RIGHT_U_TURN"
            boolean r0 = r0.equalsIgnoreCase(r3)
            if (r0 == 0) goto L7b
            r3 = 41
            goto L9
        L7b:
            java.lang.String r0 = "OFF_ROUTE"
            boolean r0 = r0.equalsIgnoreCase(r3)
            if (r0 == 0) goto L86
            r3 = 12
            goto L9
        L86:
            if (r3 == 0) goto Lbe
            java.lang.String r0 = r3.toUpperCase()
            java.lang.String r2 = "EXIT"
            boolean r0 = r0.startsWith(r2)
            if (r0 != 0) goto Lac
            java.lang.String r0 = r3.toUpperCase()
            java.lang.String r2 = "ROUNDABOUT"
            boolean r0 = r0.startsWith(r2)
            if (r0 != 0) goto Lac
            java.lang.String r0 = r3.toUpperCase()
            java.lang.String r2 = "ROUNDABOUT_LEFT"
            boolean r0 = r0.startsWith(r2)
            if (r0 == 0) goto Lbe
        Lac:
            java.lang.String r3 = r3.substring(r1)     // Catch: java.lang.NumberFormatException -> Lba
            int r3 = java.lang.Integer.parseInt(r3)     // Catch: java.lang.NumberFormatException -> Lba
            r0 = 0
            com.mappls.sdk.navigation.router.c r3 = a(r3, r0, r4)     // Catch: java.lang.NumberFormatException -> Lba
            goto Lbf
        Lba:
            r3 = move-exception
            com.mappls.sdk.navigation.apis.NavigationLogger.d(r3)
        Lbe:
            r3 = 0
        Lbf:
            if (r3 != 0) goto Lc5
            com.mappls.sdk.navigation.router.c r3 = h()
        Lc5:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mappls.sdk.navigation.router.c.a(java.lang.String, boolean):com.mappls.sdk.navigation.router.c");
    }

    public static c h() {
        return a(7, false);
    }

    public final int a() {
        return this.b;
    }

    public final void a(float f) {
        this.c = f;
    }

    public final void a(int[] iArr) {
        this.d = iArr;
    }

    public final float b() {
        return this.c;
    }

    public final int c() {
        return this.f12941a;
    }

    public final boolean d() {
        return this.f12941a == 7;
    }

    public final boolean e() {
        int i = this.f12941a;
        return i == 68 || i == 69;
    }

    public final boolean f() {
        return this.f12941a == 9;
    }

    public final boolean g() {
        return this.f12941a == 10;
    }

    public final String i() {
        int i = this.f12941a;
        if (i != 9) {
            if (i != 10) {
                if (i != 12) {
                    if (i != 41) {
                        if (i == 68) {
                            StringBuilder a2 = h.a("ROUNDABOUT");
                            a2.append(this.b);
                            return a2.toString();
                        } else if (i == 69) {
                            StringBuilder a3 = h.a("ROUNDABOUT_LEFT");
                            a3.append(this.b);
                            return a3.toString();
                        } else {
                            switch (i) {
                                case 0:
                                    return "TURN_LEFT";
                                case 1:
                                    return "TURN_SHARPLY_LEFT";
                                case 2:
                                    return "TURN_SLIGHTLY_LEFT";
                                case 3:
                                    return "TURN_RIGHT";
                                case 4:
                                    return "TURN_SHARPLY_RIGHT";
                                case 5:
                                    return "TURN_SLIGHTLY_RIGHT";
                                case 6:
                                    return "U_TURN";
                                default:
                                    return "CONTINUE";
                            }
                        }
                    }
                    return "RIGHT_U_TURN";
                }
                return "OFF_ROUTE";
            }
            return "KEEP_RIGHT";
        }
        return "KEEP_LEFT";
    }

    public final String toString() {
        String str;
        int i;
        int i2;
        if (e()) {
            StringBuilder a2 = h.a("Take ");
            a2.append(this.b);
            a2.append(" exit");
            str = a2.toString();
        } else {
            int i3 = this.f12941a;
            str = i3 == 7 ? "Go ahead" : i3 == 2 ? "Turn slightly left" : i3 == 0 ? "Turn left" : i3 == 1 ? "Turn sharply left" : i3 == 5 ? "Turn slightly right" : i3 == 3 ? "Turn right" : i3 == 4 ? "Turn sharply right" : (i3 == 6 || i3 == 41) ? "Make uturn" : i3 == 9 ? "Keep left" : i3 == 10 ? "Keep right" : i3 == 12 ? "Off route" : null;
        }
        if (str != null) {
            if (this.d != null) {
                StringBuilder sb = new StringBuilder();
                sb.append(str);
                sb.append("(");
                int[] iArr = this.d;
                String str2 = "";
                for (int i4 = 0; i4 < iArr.length; i4++) {
                    if (i4 > 0) {
                        str2 = o.a(str2, "|");
                    }
                    if (iArr[i4] % 2 == 1) {
                        str2 = o.a(str2, "+");
                    }
                    int i5 = (iArr[i4] >> 1) & 15;
                    if (i5 == 0) {
                        i5 = 1;
                    }
                    StringBuilder a3 = h.a(str2);
                    a3.append(a(i5, false).i());
                    str2 = a3.toString();
                    if (((iArr[i4] >> 5) & 31) != 0) {
                        str2 = str2 + Constants.SEPARATOR_COMMA + a(i, false).i();
                    }
                    if ((iArr[i4] >> 10) != 0) {
                        str2 = str2 + Constants.SEPARATOR_COMMA + a(i2, false).i();
                    }
                }
                sb.append(str2 + "");
                sb.append(")");
                return sb.toString();
            }
            return str;
        }
        return super.toString();
    }
}
