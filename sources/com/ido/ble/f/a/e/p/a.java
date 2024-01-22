package com.ido.ble.f.a.e.p;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import com.ido.ble.data.manage.database.DaoSession;
import com.ido.ble.f.a.b;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
/* loaded from: classes11.dex */
public class a {
    private String a(int i, int i2, int i3, boolean z) {
        StringBuilder sb;
        String format;
        if (z) {
            sb = new StringBuilder();
            sb.append(String.format("%04d", Integer.valueOf(i)));
            sb.append("-");
            sb.append(String.format("%02d", Integer.valueOf(i2)));
            sb.append("-");
            sb.append(String.format("%02d", Integer.valueOf(i3)));
            sb.append(HexStringBuilder.DEFAULT_SEPARATOR);
            sb.append(String.format("%02d", 0));
            sb.append(":");
            sb.append(String.format("%02d", 0));
            sb.append(":");
            format = String.format("%02d", 0);
        } else {
            sb = new StringBuilder();
            sb.append(String.format("%04d", Integer.valueOf(i)));
            sb.append("-");
            sb.append(String.format("%02d", Integer.valueOf(i2)));
            sb.append("-");
            sb.append(String.format("%02d", Integer.valueOf(i3)));
            sb.append(HexStringBuilder.DEFAULT_SEPARATOR);
            sb.append(String.format("%02d", 23));
            sb.append(":");
            sb.append(String.format("%02d", 59));
            sb.append(":");
            format = String.format("%02d", 59);
        }
        sb.append(format);
        return sb.toString();
    }

    public DaoSession a() {
        return b.d().b();
    }

    public Date a(int i, int i2) {
        int abs = Math.abs(i);
        Calendar calendar = Calendar.getInstance();
        int i3 = calendar.get(7) - i2;
        if (i3 == 0) {
            i3 = 7;
        }
        calendar.add(5, ((-i3) + 1) - (abs * 7));
        calendar.add(5, i3 + i2);
        return a(calendar.get(1), calendar.get(2) + 1, calendar.get(5));
    }

    public Date a(int i, int i2, int i3) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(a(i, i2, i3, false));
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Date b(int i, int i2) {
        int abs = Math.abs(i);
        Calendar calendar = Calendar.getInstance();
        int i3 = calendar.get(7) - i2;
        if (i3 == 0) {
            i3 = 7;
        }
        calendar.add(5, ((-i3) + 1) - (abs * 7));
        return b(calendar.get(1), calendar.get(2) + 1, calendar.get(5));
    }

    public Date b(int i, int i2, int i3) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(a(i, i2, i3, true));
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
