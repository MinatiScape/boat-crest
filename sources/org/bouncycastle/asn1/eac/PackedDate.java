package org.bouncycastle.asn1.eac;

import com.jstyle.blesdk1860.constant.BleConst;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.SimpleTimeZone;
import org.bouncycastle.util.Arrays;
/* loaded from: classes12.dex */
public class PackedDate {

    /* renamed from: a  reason: collision with root package name */
    public byte[] f14414a;

    public PackedDate(String str) {
        this.f14414a = a(str);
    }

    public PackedDate(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyMMdd'Z'");
        simpleDateFormat.setTimeZone(new SimpleTimeZone(0, "Z"));
        this.f14414a = a(simpleDateFormat.format(date));
    }

    public PackedDate(Date date, Locale locale) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyMMdd'Z'", locale);
        simpleDateFormat.setTimeZone(new SimpleTimeZone(0, "Z"));
        this.f14414a = a(simpleDateFormat.format(date));
    }

    public PackedDate(byte[] bArr) {
        this.f14414a = bArr;
    }

    public final byte[] a(String str) {
        char[] charArray = str.toCharArray();
        byte[] bArr = new byte[6];
        for (int i = 0; i != 6; i++) {
            bArr[i] = (byte) (charArray[i] - '0');
        }
        return bArr;
    }

    public boolean equals(Object obj) {
        if (obj instanceof PackedDate) {
            return Arrays.areEqual(this.f14414a, ((PackedDate) obj).f14414a);
        }
        return false;
    }

    public Date getDate() throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        return simpleDateFormat.parse(BleConst.SetAlarmClockWithAllClock + toString());
    }

    public byte[] getEncoding() {
        return Arrays.clone(this.f14414a);
    }

    public int hashCode() {
        return Arrays.hashCode(this.f14414a);
    }

    public String toString() {
        int length = this.f14414a.length;
        char[] cArr = new char[length];
        for (int i = 0; i != length; i++) {
            cArr[i] = (char) ((this.f14414a[i] & 255) + 48);
        }
        return new String(cArr);
    }
}
