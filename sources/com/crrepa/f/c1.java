package com.crrepa.f;

import android.text.TextUtils;
import com.crrepa.ble.conn.bean.CRPFutureWeatherInfo;
import com.crrepa.ble.conn.bean.CRPTodayWeatherInfo;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.io.UnsupportedEncodingException;
import java.util.List;
/* loaded from: classes9.dex */
public class c1 {
    public static String a(String str, int i) {
        StringBuilder sb = new StringBuilder();
        for (int length = !TextUtils.isEmpty(str) ? str.length() : 0; length < i; length++) {
            sb.append(HexStringBuilder.DEFAULT_SEPARATOR);
        }
        if (!TextUtils.isEmpty(str)) {
            sb.append(str);
        }
        return sb.toString();
    }

    public static byte[] a(CRPFutureWeatherInfo cRPFutureWeatherInfo) {
        if (cRPFutureWeatherInfo == null || cRPFutureWeatherInfo.getFuture() == null) {
            return null;
        }
        List<CRPFutureWeatherInfo.FutureBean> future = cRPFutureWeatherInfo.getFuture();
        byte[] bArr = new byte[21];
        int i = 0;
        int i2 = 0;
        while (i < future.size() && i2 < 21) {
            CRPFutureWeatherInfo.FutureBean futureBean = future.get(i);
            int lowTemperature = futureBean.getLowTemperature();
            int highTemperature = futureBean.getHighTemperature();
            int i3 = i2 + 1;
            bArr[i2] = (byte) futureBean.getWeatherId();
            int i4 = i3 + 1;
            bArr[i3] = (byte) lowTemperature;
            bArr[i4] = (byte) highTemperature;
            i++;
            i2 = i4 + 1;
        }
        return d1.a(66, bArr);
    }

    public static byte[] a(CRPTodayWeatherInfo cRPTodayWeatherInfo) {
        byte[] bArr;
        if (cRPTodayWeatherInfo == null) {
            return null;
        }
        String city = cRPTodayWeatherInfo.getCity();
        String festival = cRPTodayWeatherInfo.getFestival();
        String lunar = cRPTodayWeatherInfo.getLunar();
        int pm25 = cRPTodayWeatherInfo.getPm25();
        int temp = cRPTodayWeatherInfo.getTemp();
        int weatherId = cRPTodayWeatherInfo.getWeatherId();
        if (TextUtils.isEmpty(city)) {
            return null;
        }
        byte[] c = c(lunar, festival);
        byte[] b = b(city);
        int i = 3;
        if (pm25 <= 0) {
            bArr = new byte[b.length + c.length + 3];
            bArr[0] = 0;
        } else {
            byte[] bArr2 = new byte[b.length + c.length + 5];
            bArr2[0] = 1;
            System.arraycopy(com.crrepa.i0.e.a(pm25), 0, bArr2, 3, 2);
            i = 5;
            bArr = bArr2;
        }
        bArr[1] = (byte) weatherId;
        bArr[2] = (byte) temp;
        System.arraycopy(c, 0, bArr, i, c.length);
        System.arraycopy(b, 0, bArr, c.length + i, b.length);
        return d1.a(67, bArr);
    }

    public static byte[] b(String str) {
        if (str.endsWith("市")) {
            str = str.substring(0, str.length() - 1);
        }
        if (str.length() > 30) {
            str = str.substring(0, 30);
        }
        try {
            return str.getBytes("unicodebigunmarked");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static byte[] c(String str, String str2) {
        if (!TextUtils.isEmpty(str2)) {
            str = str2;
        }
        if (!TextUtils.isEmpty(str) && str.length() > 4) {
            str = str.substring(0, 4);
        }
        try {
            return a(str, 4).getBytes("unicodebigunmarked");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
