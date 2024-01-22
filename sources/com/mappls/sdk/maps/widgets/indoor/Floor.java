package com.mappls.sdk.maps.widgets.indoor;

import java.util.Objects;
/* loaded from: classes11.dex */
public class Floor {

    /* renamed from: a  reason: collision with root package name */
    public Integer f12861a;
    public String b;
    public String c;

    public Floor(Integer num, String str, String str2) {
        this.f12861a = num;
        this.b = str;
        this.c = str2;
    }

    public static boolean areSameFloor(Floor floor, Integer num) {
        return (floor == null && num == null) || (floor != null && floor.getNumber().equals(num));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || Floor.class != obj.getClass()) {
            return false;
        }
        Floor floor = (Floor) obj;
        return this.f12861a.equals(floor.f12861a) && this.b.equals(floor.b);
    }

    public String getInternalName() {
        return this.c;
    }

    public String getName() {
        return this.b;
    }

    public Integer getNumber() {
        return this.f12861a;
    }

    public int hashCode() {
        return Objects.hash(this.f12861a, this.b);
    }

    public static boolean areSameFloor(Integer num, Floor floor) {
        return (num == null && floor == null) || (floor != null && floor.getNumber().equals(num));
    }
}
