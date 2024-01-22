package org.jose4j.jwt;

import java.util.Date;
/* loaded from: classes13.dex */
public class IntDate {

    /* renamed from: a  reason: collision with root package name */
    public long f15533a;

    public IntDate(long j) {
        this.f15533a = j;
    }

    public static IntDate fromMillis(long j) {
        return fromSeconds(j / 1000);
    }

    public static IntDate fromSeconds(long j) {
        return new IntDate(j);
    }

    public static IntDate now() {
        return fromMillis(System.currentTimeMillis());
    }

    public void addSeconds(long j) {
        this.f15533a += j;
    }

    public boolean after(IntDate intDate) {
        return this.f15533a > intDate.getValue();
    }

    public boolean before(IntDate intDate) {
        return this.f15533a < intDate.getValue();
    }

    public boolean equals(Object obj) {
        return this == obj || ((obj instanceof IntDate) && this.f15533a == ((IntDate) obj).f15533a);
    }

    public long getValue() {
        return this.f15533a;
    }

    public long getValueInMillis() {
        return getValue() * 1000;
    }

    public int hashCode() {
        long j = this.f15533a;
        return (int) (j ^ (j >>> 32));
    }

    public String toString() {
        return "IntDate{" + getValue() + " --> " + new Date(getValueInMillis()) + '}';
    }

    public void addSeconds(int i) {
        addSeconds(i);
    }
}
