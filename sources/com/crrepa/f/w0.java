package com.crrepa.f;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
/* loaded from: classes9.dex */
public class w0 {
    public static int a(byte[] bArr) {
        if (com.crrepa.i0.e.e(bArr)) {
            return -1;
        }
        return bArr[0];
    }

    public static long a(long j) {
        TimeZone timeZone = TimeZone.getDefault();
        int rawOffset = timeZone.getRawOffset();
        int dSTSavings = timeZone.getDSTSavings();
        boolean inDaylightTime = timeZone.inDaylightTime(new Date(j));
        long rawOffset2 = j - (rawOffset - TimeZone.getTimeZone("GMT+8").getRawOffset());
        return inDaylightTime ? rawOffset2 - dSTSavings : rawOffset2;
    }

    public static byte[] a() {
        return d1.a(39, null);
    }

    public static byte[] a(int i) {
        return d1.a(23, new byte[]{(byte) i});
    }

    public static byte[] b() {
        Date date;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = simpleDateFormat.format(new Date());
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        try {
            date = simpleDateFormat.parse(format);
        } catch (ParseException e) {
            e.printStackTrace();
            date = null;
        }
        if (date != null) {
            byte[] bArr = new byte[5];
            System.arraycopy(com.crrepa.i0.e.a(date.getTime() / 1000), 0, bArr, 0, 4);
            bArr[4] = 8;
            return d1.a(49, bArr);
        }
        return null;
    }
}
