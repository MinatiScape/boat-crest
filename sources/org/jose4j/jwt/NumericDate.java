package org.jose4j.jwt;

import java.text.DateFormat;
import java.util.Date;
import org.jose4j.lang.Maths;
/* loaded from: classes13.dex */
public class NumericDate {

    /* renamed from: a  reason: collision with root package name */
    public long f15535a;

    public NumericDate(long j) {
        setValue(j);
    }

    public static NumericDate fromMilliseconds(long j) {
        return fromSeconds(j / 1000);
    }

    public static NumericDate fromSeconds(long j) {
        return new NumericDate(j);
    }

    public static NumericDate now() {
        return fromMilliseconds(System.currentTimeMillis());
    }

    public final boolean a() {
        long value = getValue();
        long j = 1000 * value;
        int i = (value > 0L ? 1 : (value == 0L ? 0 : -1));
        return (i <= 0 || j >= value) && (i >= 0 || j <= value) && (i != 0 || j == 0);
    }

    public void addSeconds(long j) {
        setValue(Maths.add(this.f15535a, j));
    }

    public boolean equals(Object obj) {
        return this == obj || ((obj instanceof NumericDate) && this.f15535a == ((NumericDate) obj).f15535a);
    }

    public long getValue() {
        return this.f15535a;
    }

    public long getValueInMillis() {
        long value = getValue();
        long j = 1000 * value;
        if (a()) {
            return j;
        }
        throw new ArithmeticException("converting " + value + " seconds to milliseconds (x1000) resulted in long integer overflow (" + j + ")");
    }

    public int hashCode() {
        long j = this.f15535a;
        return (int) (j ^ (j >>> 32));
    }

    public boolean isAfter(NumericDate numericDate) {
        return this.f15535a > numericDate.getValue();
    }

    public boolean isBefore(NumericDate numericDate) {
        return this.f15535a < numericDate.getValue();
    }

    public boolean isOnOrAfter(NumericDate numericDate) {
        return !isBefore(numericDate);
    }

    public void setValue(long j) {
        this.f15535a = j;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("NumericDate");
        sb.append("{");
        sb.append(getValue());
        if (a()) {
            DateFormat dateTimeInstance = DateFormat.getDateTimeInstance(2, 1);
            Date date = new Date(getValueInMillis());
            sb.append(" -> ");
            sb.append(dateTimeInstance.format(date));
        }
        sb.append('}');
        return sb.toString();
    }
}
