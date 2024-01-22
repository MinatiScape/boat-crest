package com.coveiot.android.bleabstract.response;

import com.coveiot.utils.utility.AppUtils;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Locale;
/* loaded from: classes2.dex */
public class BloodPressureMeasurement implements Serializable {

    /* renamed from: a  reason: collision with root package name */
    public Integer f3583a;
    public Integer b;
    public Integer c;
    public Integer cuffstatus;
    public Integer d;
    public String dates;
    public Float diastolic;
    public Integer e;
    public Integer f;
    public String isMMHG;
    public Calendar mCalendar;
    public Float meanArterialPressure;
    public Float pulseRate;
    public Float systolic;
    public Integer userID;

    public BloodPressureMeasurement(byte[] bArr) {
        String valueOf = String.valueOf((int) bArr[0]);
        int i = bArr[16] & 255;
        this.systolic = Float.valueOf((bArr[1] & 255) | ((bArr[2] & 255) << 8));
        this.diastolic = Float.valueOf((bArr[3] & 255) | ((bArr[4] & 255) << 8));
        this.meanArterialPressure = Float.valueOf((bArr[5] & 255) | ((bArr[6] & 255) << 8));
        this.f3583a = Integer.valueOf((bArr[7] & 255) | ((bArr[8] & 255) << 8));
        this.b = Integer.valueOf(Integer.valueOf(bArr[9] & 255).intValue() - 1);
        this.c = Integer.valueOf(bArr[10] & 255);
        this.d = Integer.valueOf(bArr[11] & 255);
        this.e = Integer.valueOf(bArr[12] & 255);
        this.f = Integer.valueOf(bArr[13] & 255);
        this.pulseRate = Float.valueOf((bArr[14] & 255) | ((bArr[15] & 255) << 8));
        this.userID = Integer.valueOf(i);
        this.cuffstatus = Integer.valueOf((bArr[17] & 255) | ((bArr[18] & 255) << 8));
        this.dates = this.f3583a + "-" + this.b + "-" + this.c + HexStringBuilder.DEFAULT_SEPARATOR + this.d + "-" + this.e + "-" + this.f;
        this.isMMHG = valueOf;
        Calendar calendar = Calendar.getInstance();
        calendar.set(1, this.f3583a.intValue());
        calendar.set(2, this.b.intValue());
        calendar.set(5, this.c.intValue());
        calendar.set(11, this.d.intValue());
        calendar.set(12, this.e.intValue());
        calendar.set(13, this.f.intValue());
        calendar.set(14, 0);
        this.mCalendar = calendar;
    }

    public String toString() {
        return String.format(Locale.ENGLISH, "%.0f/%.0f %s, MAP %.0f, %.0f bpm, user %d at (%s)", this.systolic, this.diastolic, this.isMMHG, this.meanArterialPressure, this.pulseRate, this.userID, AppUtils.formatDate(this.mCalendar.getTime(), "yyyy-MM-dd HH:mm:ss"));
    }
}
