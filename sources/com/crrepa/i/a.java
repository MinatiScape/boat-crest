package com.crrepa.i;

import android.text.TextUtils;
import com.crrepa.ble.conn.bean.CRPMessageInfo;
import com.crrepa.f.d1;
import com.crrepa.i0.i;
import java.nio.charset.StandardCharsets;
import kotlin.text.Typography;
/* loaded from: classes9.dex */
public class a {
    public static String a(String str, int i) {
        int i2 = 0;
        int i3 = 0;
        while (i2 < str.length()) {
            int i4 = i2 + 1;
            i -= str.substring(i2, i4).getBytes(StandardCharsets.UTF_8).length;
            if (i <= 0) {
                return (String) TextUtils.concat(str.substring(0, i3), String.valueOf((char) Typography.ellipsis));
            }
            i3++;
            i2 = i4;
        }
        return str;
    }

    public static byte[] a() {
        return d1.a(65, new byte[]{-1});
    }

    public static byte[] a(CRPMessageInfo cRPMessageInfo) {
        if (cRPMessageInfo == null) {
            return null;
        }
        String message = cRPMessageInfo.getMessage();
        int type = cRPMessageInfo.getType();
        if (TextUtils.isEmpty(message) || type < 0) {
            return null;
        }
        int versionCode = cRPMessageInfo.getVersionCode();
        byte[] bytes = b(i.a(message), type, cRPMessageInfo.isHs(), cRPMessageInfo.isSmallScreen()).getBytes(StandardCharsets.UTF_8);
        byte[] bArr = new byte[bytes.length + 1];
        bArr[0] = (byte) (b.a(type, versionCode) & 255);
        System.arraycopy(bytes, 0, bArr, 1, bytes.length);
        return d1.a(65, bArr);
    }

    public static byte[] a(String str) {
        byte[] bytes = a(str, 20).getBytes(StandardCharsets.UTF_8);
        byte[] bArr = new byte[bytes.length + 1];
        bArr[0] = 0;
        System.arraycopy(bytes, 0, bArr, 1, bytes.length);
        return d1.a(65, bArr);
    }

    public static String b(String str, int i, boolean z, boolean z2) {
        return a(str, i == 0 ? 38 : z ? 180 : z2 ? 92 : 230);
    }
}
