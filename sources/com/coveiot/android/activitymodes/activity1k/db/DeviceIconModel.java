package com.coveiot.android.activitymodes.activity1k.db;

import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class DeviceIconModel {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public String f2746a;
    @Nullable
    public Integer b;
    @Nullable
    public String c;
    @Nullable
    public String d;
    @Nullable
    public List<Integer> e;
    @Nullable
    public Boolean f;
    @Nullable
    public Boolean g;

    public DeviceIconModel() {
        this(null, null, null, null, null, null, null, 127, null);
    }

    public DeviceIconModel(@Nullable String str, @Nullable Integer num, @Nullable String str2, @Nullable String str3, @Nullable List<Integer> list, @Nullable Boolean bool, @Nullable Boolean bool2) {
        this.f2746a = str;
        this.b = num;
        this.c = str2;
        this.d = str3;
        this.e = list;
        this.f = bool;
        this.g = bool2;
    }

    @Nullable
    public final Boolean getCompressed() {
        return this.g;
    }

    @Nullable
    public final Integer getRefId() {
        return this.b;
    }

    @Nullable
    public final List<Integer> getSize() {
        return this.e;
    }

    @Nullable
    public final Boolean getTransparent() {
        return this.f;
    }

    @Nullable
    public final String getType() {
        return this.f2746a;
    }

    @Nullable
    public final String getUrl() {
        return this.c;
    }

    @Nullable
    public final String getUse() {
        return this.d;
    }

    public final void setCompressed(@Nullable Boolean bool) {
        this.g = bool;
    }

    public final void setRefId(@Nullable Integer num) {
        this.b = num;
    }

    public final void setSize(@Nullable List<Integer> list) {
        this.e = list;
    }

    public final void setTransparent(@Nullable Boolean bool) {
        this.f = bool;
    }

    public final void setType(@Nullable String str) {
        this.f2746a = str;
    }

    public final void setUrl(@Nullable String str) {
        this.c = str;
    }

    public final void setUse(@Nullable String str) {
        this.d = str;
    }

    public /* synthetic */ DeviceIconModel(String str, Integer num, String str2, String str3, List list, Boolean bool, Boolean bool2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : num, (i & 4) != 0 ? null : str2, (i & 8) != 0 ? null : str3, (i & 16) != 0 ? null : list, (i & 32) != 0 ? null : bool, (i & 64) != 0 ? null : bool2);
    }
}
