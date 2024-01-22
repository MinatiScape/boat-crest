package com.coveiot.android.activitymodes.activity1k.db.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import com.coveiot.android.activitymodes.activity1k.db.DeviceIconModel;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Entity(foreignKeys = {@ForeignKey(childColumns = {"categoryId"}, entity = EntityActivityCategory.class, onDelete = 5, onUpdate = 1, parentColumns = {"categoryId"})}, primaryKeys = {"categoryId", "activityId"}, tableName = "entity_physical_activities")
/* loaded from: classes2.dex */
public final class EntityPhysicalActivities {
    @ColumnInfo(name = "categoryId")

    /* renamed from: a  reason: collision with root package name */
    public int f2751a;
    @ColumnInfo(name = "activityId")
    public int b;
    @ColumnInfo(name = "fwActId")
    public int c;
    @ColumnInfo(name = "cal_func")
    @Nullable
    public String d;
    @ColumnInfo(name = "inbuilt")
    public boolean e;
    @ColumnInfo(name = "activityCode")
    @Nullable
    public String f;
    @ColumnInfo(name = "cpaCode")
    @Nullable
    public String g;
    @ColumnInfo(name = "shortTitle")
    @Nullable
    public String h;
    @ColumnInfo(name = "titleInMetric")
    @Nullable
    public String i;
    @ColumnInfo(name = "titleInImperial")
    @Nullable
    public String j;
    @ColumnInfo(name = "dvcTitleInMetric")
    @Nullable
    public List<String> k;
    @ColumnInfo(name = "dvcTitleInImperial")
    @Nullable
    public List<String> l;
    @ColumnInfo(name = "descInMetric")
    @Nullable
    public String m;
    @ColumnInfo(name = "descInImperial")
    @Nullable
    public String n;
    @ColumnInfo(name = "defaultMets")
    @Nullable
    public Double o;
    @ColumnInfo(name = "metrics")
    @Nullable
    public List<String> p;
    @ColumnInfo(name = "defaultActivityName")
    @Nullable
    public String q;
    @ColumnInfo(name = "defaultCategoryIcon")
    @Nullable
    public Integer r;
    @ColumnInfo(name = "defaultActivityIcon")
    @Nullable
    public Integer s;
    @ColumnInfo(name = "iconUrl")
    @Nullable
    public String t;
    @ColumnInfo(name = "deviceIconModels")
    @Nullable
    public List<DeviceIconModel> u;

    public EntityPhysicalActivities() {
        this(0, 0, 0, null, false, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 2097151, null);
    }

    public EntityPhysicalActivities(int i, int i2, int i3, @Nullable String str, boolean z, @Nullable String str2, @Nullable String str3, @Nullable String str4, @Nullable String str5, @Nullable String str6, @Nullable List<String> list, @Nullable List<String> list2, @Nullable String str7, @Nullable String str8, @Nullable Double d, @Nullable List<String> list3, @Nullable String str9, @Nullable Integer num, @Nullable Integer num2, @Nullable String str10, @Nullable List<DeviceIconModel> list4) {
        this.f2751a = i;
        this.b = i2;
        this.c = i3;
        this.d = str;
        this.e = z;
        this.f = str2;
        this.g = str3;
        this.h = str4;
        this.i = str5;
        this.j = str6;
        this.k = list;
        this.l = list2;
        this.m = str7;
        this.n = str8;
        this.o = d;
        this.p = list3;
        this.q = str9;
        this.r = num;
        this.s = num2;
        this.t = str10;
        this.u = list4;
    }

    public final int component1() {
        return this.f2751a;
    }

    @Nullable
    public final String component10() {
        return this.j;
    }

    @Nullable
    public final List<String> component11() {
        return this.k;
    }

    @Nullable
    public final List<String> component12() {
        return this.l;
    }

    @Nullable
    public final String component13() {
        return this.m;
    }

    @Nullable
    public final String component14() {
        return this.n;
    }

    @Nullable
    public final Double component15() {
        return this.o;
    }

    @Nullable
    public final List<String> component16() {
        return this.p;
    }

    @Nullable
    public final String component17() {
        return this.q;
    }

    @Nullable
    public final Integer component18() {
        return this.r;
    }

    @Nullable
    public final Integer component19() {
        return this.s;
    }

    public final int component2() {
        return this.b;
    }

    @Nullable
    public final String component20() {
        return this.t;
    }

    @Nullable
    public final List<DeviceIconModel> component21() {
        return this.u;
    }

    public final int component3() {
        return this.c;
    }

    @Nullable
    public final String component4() {
        return this.d;
    }

    public final boolean component5() {
        return this.e;
    }

    @Nullable
    public final String component6() {
        return this.f;
    }

    @Nullable
    public final String component7() {
        return this.g;
    }

    @Nullable
    public final String component8() {
        return this.h;
    }

    @Nullable
    public final String component9() {
        return this.i;
    }

    @NotNull
    public final EntityPhysicalActivities copy(int i, int i2, int i3, @Nullable String str, boolean z, @Nullable String str2, @Nullable String str3, @Nullable String str4, @Nullable String str5, @Nullable String str6, @Nullable List<String> list, @Nullable List<String> list2, @Nullable String str7, @Nullable String str8, @Nullable Double d, @Nullable List<String> list3, @Nullable String str9, @Nullable Integer num, @Nullable Integer num2, @Nullable String str10, @Nullable List<DeviceIconModel> list4) {
        return new EntityPhysicalActivities(i, i2, i3, str, z, str2, str3, str4, str5, str6, list, list2, str7, str8, d, list3, str9, num, num2, str10, list4);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof EntityPhysicalActivities) {
            EntityPhysicalActivities entityPhysicalActivities = (EntityPhysicalActivities) obj;
            return this.f2751a == entityPhysicalActivities.f2751a && this.b == entityPhysicalActivities.b && this.c == entityPhysicalActivities.c && Intrinsics.areEqual(this.d, entityPhysicalActivities.d) && this.e == entityPhysicalActivities.e && Intrinsics.areEqual(this.f, entityPhysicalActivities.f) && Intrinsics.areEqual(this.g, entityPhysicalActivities.g) && Intrinsics.areEqual(this.h, entityPhysicalActivities.h) && Intrinsics.areEqual(this.i, entityPhysicalActivities.i) && Intrinsics.areEqual(this.j, entityPhysicalActivities.j) && Intrinsics.areEqual(this.k, entityPhysicalActivities.k) && Intrinsics.areEqual(this.l, entityPhysicalActivities.l) && Intrinsics.areEqual(this.m, entityPhysicalActivities.m) && Intrinsics.areEqual(this.n, entityPhysicalActivities.n) && Intrinsics.areEqual((Object) this.o, (Object) entityPhysicalActivities.o) && Intrinsics.areEqual(this.p, entityPhysicalActivities.p) && Intrinsics.areEqual(this.q, entityPhysicalActivities.q) && Intrinsics.areEqual(this.r, entityPhysicalActivities.r) && Intrinsics.areEqual(this.s, entityPhysicalActivities.s) && Intrinsics.areEqual(this.t, entityPhysicalActivities.t) && Intrinsics.areEqual(this.u, entityPhysicalActivities.u);
        }
        return false;
    }

    @Nullable
    public final String getActivityCode() {
        return this.f;
    }

    public final int getActivityId() {
        return this.b;
    }

    @Nullable
    public final String getCal_func() {
        return this.d;
    }

    public final int getCategoryId() {
        return this.f2751a;
    }

    @Nullable
    public final String getCpaCode() {
        return this.g;
    }

    @Nullable
    public final Integer getDefaultActivityIcon() {
        return this.s;
    }

    @Nullable
    public final String getDefaultActivityName() {
        return this.q;
    }

    @Nullable
    public final Integer getDefaultCategoryIcon() {
        return this.r;
    }

    @Nullable
    public final Double getDefaultMets() {
        return this.o;
    }

    @Nullable
    public final String getDescInImperial() {
        return this.n;
    }

    @Nullable
    public final String getDescInMetric() {
        return this.m;
    }

    @Nullable
    public final List<DeviceIconModel> getDeviceIconModels() {
        return this.u;
    }

    @Nullable
    public final List<String> getDvcTitleInImperial() {
        return this.l;
    }

    @Nullable
    public final List<String> getDvcTitleInMetric() {
        return this.k;
    }

    public final int getFwActId() {
        return this.c;
    }

    @Nullable
    public final String getIconUrl() {
        return this.t;
    }

    public final boolean getInbuilt() {
        return this.e;
    }

    @Nullable
    public final List<String> getMetrics() {
        return this.p;
    }

    @Nullable
    public final String getShortTitle() {
        return this.h;
    }

    @Nullable
    public final String getTitleInImperial() {
        return this.j;
    }

    @Nullable
    public final String getTitleInMetric() {
        return this.i;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int hashCode() {
        int hashCode = ((((Integer.hashCode(this.f2751a) * 31) + Integer.hashCode(this.b)) * 31) + Integer.hashCode(this.c)) * 31;
        String str = this.d;
        int hashCode2 = (hashCode + (str == null ? 0 : str.hashCode())) * 31;
        boolean z = this.e;
        int i = z;
        if (z != 0) {
            i = 1;
        }
        int i2 = (hashCode2 + i) * 31;
        String str2 = this.f;
        int hashCode3 = (i2 + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.g;
        int hashCode4 = (hashCode3 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.h;
        int hashCode5 = (hashCode4 + (str4 == null ? 0 : str4.hashCode())) * 31;
        String str5 = this.i;
        int hashCode6 = (hashCode5 + (str5 == null ? 0 : str5.hashCode())) * 31;
        String str6 = this.j;
        int hashCode7 = (hashCode6 + (str6 == null ? 0 : str6.hashCode())) * 31;
        List<String> list = this.k;
        int hashCode8 = (hashCode7 + (list == null ? 0 : list.hashCode())) * 31;
        List<String> list2 = this.l;
        int hashCode9 = (hashCode8 + (list2 == null ? 0 : list2.hashCode())) * 31;
        String str7 = this.m;
        int hashCode10 = (hashCode9 + (str7 == null ? 0 : str7.hashCode())) * 31;
        String str8 = this.n;
        int hashCode11 = (hashCode10 + (str8 == null ? 0 : str8.hashCode())) * 31;
        Double d = this.o;
        int hashCode12 = (hashCode11 + (d == null ? 0 : d.hashCode())) * 31;
        List<String> list3 = this.p;
        int hashCode13 = (hashCode12 + (list3 == null ? 0 : list3.hashCode())) * 31;
        String str9 = this.q;
        int hashCode14 = (hashCode13 + (str9 == null ? 0 : str9.hashCode())) * 31;
        Integer num = this.r;
        int hashCode15 = (hashCode14 + (num == null ? 0 : num.hashCode())) * 31;
        Integer num2 = this.s;
        int hashCode16 = (hashCode15 + (num2 == null ? 0 : num2.hashCode())) * 31;
        String str10 = this.t;
        int hashCode17 = (hashCode16 + (str10 == null ? 0 : str10.hashCode())) * 31;
        List<DeviceIconModel> list4 = this.u;
        return hashCode17 + (list4 != null ? list4.hashCode() : 0);
    }

    public final void setActivityCode(@Nullable String str) {
        this.f = str;
    }

    public final void setActivityId(int i) {
        this.b = i;
    }

    public final void setCal_func(@Nullable String str) {
        this.d = str;
    }

    public final void setCategoryId(int i) {
        this.f2751a = i;
    }

    public final void setCpaCode(@Nullable String str) {
        this.g = str;
    }

    public final void setDefaultActivityIcon(@Nullable Integer num) {
        this.s = num;
    }

    public final void setDefaultActivityName(@Nullable String str) {
        this.q = str;
    }

    public final void setDefaultCategoryIcon(@Nullable Integer num) {
        this.r = num;
    }

    public final void setDefaultMets(@Nullable Double d) {
        this.o = d;
    }

    public final void setDescInImperial(@Nullable String str) {
        this.n = str;
    }

    public final void setDescInMetric(@Nullable String str) {
        this.m = str;
    }

    public final void setDeviceIconModels(@Nullable List<DeviceIconModel> list) {
        this.u = list;
    }

    public final void setDvcTitleInImperial(@Nullable List<String> list) {
        this.l = list;
    }

    public final void setDvcTitleInMetric(@Nullable List<String> list) {
        this.k = list;
    }

    public final void setFwActId(int i) {
        this.c = i;
    }

    public final void setIconUrl(@Nullable String str) {
        this.t = str;
    }

    public final void setInbuilt(boolean z) {
        this.e = z;
    }

    public final void setMetrics(@Nullable List<String> list) {
        this.p = list;
    }

    public final void setShortTitle(@Nullable String str) {
        this.h = str;
    }

    public final void setTitleInImperial(@Nullable String str) {
        this.j = str;
    }

    public final void setTitleInMetric(@Nullable String str) {
        this.i = str;
    }

    @NotNull
    public String toString() {
        return "EntityPhysicalActivities(categoryId=" + this.f2751a + ", activityId=" + this.b + ", fwActId=" + this.c + ", cal_func=" + this.d + ", inbuilt=" + this.e + ", activityCode=" + this.f + ", cpaCode=" + this.g + ", shortTitle=" + this.h + ", titleInMetric=" + this.i + ", titleInImperial=" + this.j + ", dvcTitleInMetric=" + this.k + ", dvcTitleInImperial=" + this.l + ", descInMetric=" + this.m + ", descInImperial=" + this.n + ", defaultMets=" + this.o + ", metrics=" + this.p + ", defaultActivityName=" + this.q + ", defaultCategoryIcon=" + this.r + ", defaultActivityIcon=" + this.s + ", iconUrl=" + this.t + ", deviceIconModels=" + this.u + HexStringBuilder.COMMENT_END_CHAR;
    }

    public /* synthetic */ EntityPhysicalActivities(int i, int i2, int i3, String str, boolean z, String str2, String str3, String str4, String str5, String str6, List list, List list2, String str7, String str8, Double d, List list3, String str9, Integer num, Integer num2, String str10, List list4, int i4, DefaultConstructorMarker defaultConstructorMarker) {
        this((i4 & 1) != 0 ? -1 : i, (i4 & 2) != 0 ? -1 : i2, (i4 & 4) == 0 ? i3 : -1, (i4 & 8) != 0 ? null : str, (i4 & 16) != 0 ? false : z, (i4 & 32) != 0 ? null : str2, (i4 & 64) != 0 ? null : str3, (i4 & 128) != 0 ? null : str4, (i4 & 256) != 0 ? null : str5, (i4 & 512) != 0 ? null : str6, (i4 & 1024) != 0 ? null : list, (i4 & 2048) != 0 ? null : list2, (i4 & 4096) != 0 ? null : str7, (i4 & 8192) != 0 ? null : str8, (i4 & 16384) != 0 ? null : d, (i4 & 32768) != 0 ? null : list3, (i4 & 65536) != 0 ? null : str9, (i4 & 131072) != 0 ? null : num, (i4 & 262144) != 0 ? null : num2, (i4 & 524288) != 0 ? null : str10, (i4 & 1048576) != 0 ? null : list4);
    }
}
