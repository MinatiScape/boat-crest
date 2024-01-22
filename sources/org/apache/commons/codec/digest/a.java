package org.apache.commons.codec.digest;

import java.security.SecureRandom;
import java.util.Random;
/* loaded from: classes12.dex */
public class a {

    /* renamed from: a  reason: collision with root package name */
    public static final char[] f14345a = "./0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".toCharArray();

    public static void a(byte b, byte b2, byte b3, int i, StringBuilder sb) {
        int i2 = ((b << 16) & 16777215) | ((b2 << 8) & 65535) | (b3 & 255);
        while (true) {
            int i3 = i - 1;
            if (i <= 0) {
                return;
            }
            sb.append(f14345a[i2 & 63]);
            i2 >>= 6;
            i = i3;
        }
    }

    public static String b(int i) {
        return c(i, new SecureRandom());
    }

    public static String c(int i, Random random) {
        StringBuilder sb = new StringBuilder(i);
        for (int i2 = 1; i2 <= i; i2++) {
            sb.append("./0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".charAt(random.nextInt(64)));
        }
        return sb.toString();
    }
}
