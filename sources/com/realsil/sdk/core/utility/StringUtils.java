package com.realsil.sdk.core.utility;

import java.util.Random;
/* loaded from: classes12.dex */
public final class StringUtils {
    public static String genNonceStringByLength(int i) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i2 = 0; i2 < i; i2++) {
            sb.append("abcdefghijklmnopqrstuvwxyz0123456789".charAt(random.nextInt(36)));
        }
        return sb.toString();
    }

    public static String getNonceDecimalString(int i) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i2 = 0; i2 < i; i2++) {
            sb.append("0123456789".charAt(random.nextInt(10)));
        }
        return sb.toString();
    }

    public static String genNonceStringByLength(long j) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < j; i++) {
            sb.append("abcdefghijklmnopqrstuvwxyz0123456789".charAt(random.nextInt(36)));
        }
        return sb.toString();
    }
}
