package com.coveiot.android.activitymodes.activity1k.db.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import com.coveiot.android.activitymodes.activity1k.db.DeviceIconModel;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import com.mappls.sdk.navigation.util.SavingTrackHelper;
import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Entity(primaryKeys = {"categoryId"}, tableName = "entity_activity_category")
/* loaded from: classes2.dex */
public final class EntityActivityCategory {
    @ColumnInfo(name = "categoryId")
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public Integer f2750a;
    @ColumnInfo(name = "title")
    @Nullable
    public String b;
    @ColumnInfo(name = SavingTrackHelper.POINT_COL_DESCRIPTION)
    @Nullable
    public String c;
    @ColumnInfo(name = "iconUrl")
    @Nullable
    public String d;
    @ColumnInfo(name = "deviceIconModels")
    @Nullable
    public List<DeviceIconModel> e;

    public EntityActivityCategory() {
        this(null, null, null, null, null, 31, null);
    }

    public EntityActivityCategory(@Nullable Integer num, @Nullable String str, @Nullable String str2, @Nullable String str3, @Nullable List<DeviceIconModel> list) {
        this.f2750a = num;
        this.b = str;
        this.c = str2;
        this.d = str3;
        this.e = list;
    }

    public static /* synthetic */ EntityActivityCategory copy$default(EntityActivityCategory entityActivityCategory, Integer num, String str, String str2, String str3, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            num = entityActivityCategory.f2750a;
        }
        if ((i & 2) != 0) {
            str = entityActivityCategory.b;
        }
        String str4 = str;
        if ((i & 4) != 0) {
            str2 = entityActivityCategory.c;
        }
        String str5 = str2;
        if ((i & 8) != 0) {
            str3 = entityActivityCategory.d;
        }
        String str6 = str3;
        List<DeviceIconModel> list2 = list;
        if ((i & 16) != 0) {
            list2 = entityActivityCategory.e;
        }
        return entityActivityCategory.copy(num, str4, str5, str6, list2);
    }

    @Nullable
    public final Integer component1() {
        return this.f2750a;
    }

    @Nullable
    public final String component2() {
        return this.b;
    }

    @Nullable
    public final String component3() {
        return this.c;
    }

    @Nullable
    public final String component4() {
        return this.d;
    }

    @Nullable
    public final List<DeviceIconModel> component5() {
        return this.e;
    }

    @NotNull
    public final EntityActivityCategory copy(@Nullable Integer num, @Nullable String str, @Nullable String str2, @Nullable String str3, @Nullable List<DeviceIconModel> list) {
        return new EntityActivityCategory(num, str, str2, str3, list);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof EntityActivityCategory) {
            EntityActivityCategory entityActivityCategory = (EntityActivityCategory) obj;
            return Intrinsics.areEqual(this.f2750a, entityActivityCategory.f2750a) && Intrinsics.areEqual(this.b, entityActivityCategory.b) && Intrinsics.areEqual(this.c, entityActivityCategory.c) && Intrinsics.areEqual(this.d, entityActivityCategory.d) && Intrinsics.areEqual(this.e, entityActivityCategory.e);
        }
        return false;
    }

    @Nullable
    public final Integer getCategoryId() {
        return this.f2750a;
    }

    @Nullable
    public final String getDescription() {
        return this.c;
    }

    @Nullable
    public final List<DeviceIconModel> getDeviceIconModels() {
        return this.e;
    }

    @Nullable
    public final String getIconUrl() {
        return this.d;
    }

    @Nullable
    public final String getTitle() {
        return this.b;
    }

    public int hashCode() {
        Integer num = this.f2750a;
        int hashCode = (num == null ? 0 : num.hashCode()) * 31;
        String str = this.b;
        int hashCode2 = (hashCode + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.c;
        int hashCode3 = (hashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.d;
        int hashCode4 = (hashCode3 + (str3 == null ? 0 : str3.hashCode())) * 31;
        List<DeviceIconModel> list = this.e;
        return hashCode4 + (list != null ? list.hashCode() : 0);
    }

    public final void setCategoryId(@Nullable Integer num) {
        this.f2750a = num;
    }

    public final void setDescription(@Nullable String str) {
        this.c = str;
    }

    public final void setDeviceIconModels(@Nullable List<DeviceIconModel> list) {
        this.e = list;
    }

    public final void setIconUrl(@Nullable String str) {
        this.d = str;
    }

    public final void setTitle(@Nullable String str) {
        this.b = str;
    }

    @NotNull
    public String toString() {
        return "EntityActivityCategory(categoryId=" + this.f2750a + ", title=" + this.b + ", description=" + this.c + ", iconUrl=" + this.d + ", deviceIconModels=" + this.e + HexStringBuilder.COMMENT_END_CHAR;
    }

    public /* synthetic */ EntityActivityCategory(Integer num, String str, String str2, String str3, List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : num, (i & 2) != 0 ? null : str, (i & 4) != 0 ? null : str2, (i & 8) != 0 ? null : str3, (i & 16) != 0 ? null : list);
    }
}
