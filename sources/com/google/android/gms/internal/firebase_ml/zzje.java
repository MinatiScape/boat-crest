package com.google.android.gms.internal.firebase_ml;

import com.clevertap.android.sdk.Constants;
import java.io.Serializable;
import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.codec.language.Soundex;
import org.bouncycastle.pqc.math.linearalgebra.Matrix;
/* loaded from: classes7.dex */
public final class zzje implements Serializable {
    private static final TimeZone GMT = TimeZone.getTimeZone("GMT");
    private static final Pattern zzahq = Pattern.compile("^(\\d{4})-(\\d{2})-(\\d{2})([Tt](\\d{2}):(\\d{2}):(\\d{2})(\\.\\d+)?)?([Zz]|([+-])(\\d{2}):(\\d{2}))?");
    private final long value;
    private final boolean zzahr;
    private final int zzahs;

    public zzje(long j) {
        this(false, 0L, null);
    }

    private static void zza(StringBuilder sb, int i, int i2) {
        if (i < 0) {
            sb.append(Soundex.SILENT_MARKER);
            i = -i;
        }
        int i3 = i;
        while (i3 > 0) {
            i3 /= 10;
            i2--;
        }
        for (int i4 = 0; i4 < i2; i4++) {
            sb.append('0');
        }
        if (i != 0) {
            sb.append(i);
        }
    }

    public static zzje zzap(String str) throws NumberFormatException {
        boolean z;
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        Matcher matcher = zzahq.matcher(str);
        if (!matcher.matches()) {
            String valueOf = String.valueOf(str);
            throw new NumberFormatException(valueOf.length() != 0 ? "Invalid date/time format: ".concat(valueOf) : new String("Invalid date/time format: "));
        }
        int parseInt = Integer.parseInt(matcher.group(1));
        int parseInt2 = Integer.parseInt(matcher.group(2)) - 1;
        int parseInt3 = Integer.parseInt(matcher.group(3));
        boolean z2 = matcher.group(4) != null;
        String group = matcher.group(9);
        boolean z3 = group != null;
        Integer num = null;
        if (z3 && !z2) {
            String valueOf2 = String.valueOf(str);
            throw new NumberFormatException(valueOf2.length() != 0 ? "Invalid date/time format, cannot specify time zone shift without specifying time: ".concat(valueOf2) : new String("Invalid date/time format, cannot specify time zone shift without specifying time: "));
        }
        if (z2) {
            int parseInt4 = Integer.parseInt(matcher.group(5));
            int parseInt5 = Integer.parseInt(matcher.group(6));
            int parseInt6 = Integer.parseInt(matcher.group(7));
            if (matcher.group(8) != null) {
                z = z2;
                i = (int) (Integer.parseInt(matcher.group(8).substring(1)) / Math.pow(10.0d, matcher.group(8).substring(1).length() - 3));
                i3 = parseInt5;
                i4 = parseInt6;
            } else {
                z = z2;
                i3 = parseInt5;
                i4 = parseInt6;
                i = 0;
            }
            i2 = parseInt4;
        } else {
            z = z2;
            i = 0;
            i2 = 0;
            i3 = 0;
            i4 = 0;
        }
        GregorianCalendar gregorianCalendar = new GregorianCalendar(GMT);
        gregorianCalendar.set(parseInt, parseInt2, parseInt3, i2, i3, i4);
        gregorianCalendar.set(14, i);
        long timeInMillis = gregorianCalendar.getTimeInMillis();
        if (z && z3) {
            if (Character.toUpperCase(group.charAt(0)) == 'Z') {
                i5 = 0;
            } else {
                int parseInt7 = (Integer.parseInt(matcher.group(11)) * 60) + Integer.parseInt(matcher.group(12));
                i5 = matcher.group(10).charAt(0) == '-' ? -parseInt7 : parseInt7;
                timeInMillis -= i5 * Constants.ONE_MIN_IN_MILLIS;
            }
            num = Integer.valueOf(i5);
        }
        return new zzje(!z, timeInMillis, num);
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof zzje) {
            zzje zzjeVar = (zzje) obj;
            return this.zzahr == zzjeVar.zzahr && this.value == zzjeVar.value && this.zzahs == zzjeVar.zzahs;
        }
        return false;
    }

    public final int hashCode() {
        long[] jArr = new long[3];
        jArr[0] = this.value;
        jArr[1] = this.zzahr ? 1L : 0L;
        jArr[2] = this.zzahs;
        return Arrays.hashCode(jArr);
    }

    public final String toString() {
        return zzid();
    }

    public final String zzid() {
        StringBuilder sb = new StringBuilder();
        GregorianCalendar gregorianCalendar = new GregorianCalendar(GMT);
        gregorianCalendar.setTimeInMillis(this.value + (this.zzahs * Constants.ONE_MIN_IN_MILLIS));
        zza(sb, gregorianCalendar.get(1), 4);
        sb.append(Soundex.SILENT_MARKER);
        zza(sb, gregorianCalendar.get(2) + 1, 2);
        sb.append(Soundex.SILENT_MARKER);
        zza(sb, gregorianCalendar.get(5), 2);
        if (!this.zzahr) {
            sb.append('T');
            zza(sb, gregorianCalendar.get(11), 2);
            sb.append(':');
            zza(sb, gregorianCalendar.get(12), 2);
            sb.append(':');
            zza(sb, gregorianCalendar.get(13), 2);
            if (gregorianCalendar.isSet(14)) {
                sb.append('.');
                zza(sb, gregorianCalendar.get(14), 3);
            }
            int i = this.zzahs;
            if (i == 0) {
                sb.append(Matrix.MATRIX_TYPE_ZERO);
            } else {
                if (i > 0) {
                    sb.append('+');
                } else {
                    sb.append(Soundex.SILENT_MARKER);
                    i = -i;
                }
                zza(sb, i / 60, 2);
                sb.append(':');
                zza(sb, i % 60, 2);
            }
        }
        return sb.toString();
    }

    private zzje(boolean z, long j, Integer num) {
        int offset;
        this.zzahr = z;
        this.value = j;
        if (z) {
            offset = 0;
        } else {
            offset = num == null ? TimeZone.getDefault().getOffset(j) / 60000 : num.intValue();
        }
        this.zzahs = offset;
    }
}
