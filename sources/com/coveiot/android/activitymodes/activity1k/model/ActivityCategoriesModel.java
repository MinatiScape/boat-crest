package com.coveiot.android.activitymodes.activity1k.model;

import com.coveiot.android.activitymodes.activity1k.db.DeviceIconModel;
import java.io.Serializable;
import java.util.List;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class ActivityCategoriesModel implements Serializable {
    @Nullable
    private Integer categoryId;
    @Nullable
    private String description;
    @Nullable
    private List<DeviceIconModel> deviceIconModels;
    @Nullable
    private String iconUrl;
    @Nullable
    private String title;

    @Nullable
    public final Integer getCategoryId() {
        return this.categoryId;
    }

    @Nullable
    public final String getDescription() {
        return this.description;
    }

    @Nullable
    public final List<DeviceIconModel> getDeviceIconModels() {
        return this.deviceIconModels;
    }

    @Nullable
    public final String getIconUrl() {
        return this.iconUrl;
    }

    @Nullable
    public final String getTitle() {
        return this.title;
    }

    public final void setCategoryId(@Nullable Integer num) {
        this.categoryId = num;
    }

    public final void setDescription(@Nullable String str) {
        this.description = str;
    }

    public final void setDeviceIconModels(@Nullable List<DeviceIconModel> list) {
        this.deviceIconModels = list;
    }

    public final void setIconUrl(@Nullable String str) {
        this.iconUrl = str;
    }

    public final void setTitle(@Nullable String str) {
        this.title = str;
    }
}
