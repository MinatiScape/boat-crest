package com.coveiot.android.activitymodes.activity1k.model;

import com.coveiot.android.activitymodes.activity1k.db.DeviceIconModel;
import java.io.Serializable;
import java.util.List;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class ActivitiesListModel implements Serializable {
    @Nullable
    private String activityCode;
    @Nullable
    private Integer activityId;
    @Nullable
    private String cal_func;
    @Nullable
    private Integer categoryId;
    @Nullable
    private String cpaCode;
    @Nullable
    private Integer defaultActivityIcon;
    @Nullable
    private String defaultActivityName;
    @Nullable
    private Integer defaultCategoryIcon;
    @Nullable
    private Double defaultMets;
    @Nullable
    private String descInImperial;
    @Nullable
    private String descInMetric;
    @Nullable
    private List<DeviceIconModel> deviceIconModels;
    @Nullable
    private List<String> dvcTitleInImperial;
    @Nullable
    private List<String> dvcTitleInMetric;
    private int fwActId = -1;
    @Nullable
    private String iconUrl;
    private boolean inbuilt;
    @Nullable
    private List<String> metrics;
    @Nullable
    private String shortTitle;
    @Nullable
    private String titleInImperial;
    @Nullable
    private String titleInMetric;

    @Nullable
    public final String getActivityCode() {
        return this.activityCode;
    }

    @Nullable
    public final Integer getActivityId() {
        return this.activityId;
    }

    @Nullable
    public final String getCal_func() {
        return this.cal_func;
    }

    @Nullable
    public final Integer getCategoryId() {
        return this.categoryId;
    }

    @Nullable
    public final String getCpaCode() {
        return this.cpaCode;
    }

    @Nullable
    public final Integer getDefaultActivityIcon() {
        return this.defaultActivityIcon;
    }

    @Nullable
    public final String getDefaultActivityName() {
        return this.defaultActivityName;
    }

    @Nullable
    public final Integer getDefaultCategoryIcon() {
        return this.defaultCategoryIcon;
    }

    @Nullable
    public final Double getDefaultMets() {
        return this.defaultMets;
    }

    @Nullable
    public final String getDescInImperial() {
        return this.descInImperial;
    }

    @Nullable
    public final String getDescInMetric() {
        return this.descInMetric;
    }

    @Nullable
    public final List<DeviceIconModel> getDeviceIconModels() {
        return this.deviceIconModels;
    }

    @Nullable
    public final List<String> getDvcTitleInImperial() {
        return this.dvcTitleInImperial;
    }

    @Nullable
    public final List<String> getDvcTitleInMetric() {
        return this.dvcTitleInMetric;
    }

    public final int getFwActId() {
        return this.fwActId;
    }

    @Nullable
    public final String getIconUrl() {
        return this.iconUrl;
    }

    public final boolean getInbuilt() {
        return this.inbuilt;
    }

    @Nullable
    public final List<String> getMetrics() {
        return this.metrics;
    }

    @Nullable
    public final String getShortTitle() {
        return this.shortTitle;
    }

    @Nullable
    public final String getTitleInImperial() {
        return this.titleInImperial;
    }

    @Nullable
    public final String getTitleInMetric() {
        return this.titleInMetric;
    }

    public final void setActivityCode(@Nullable String str) {
        this.activityCode = str;
    }

    public final void setActivityId(@Nullable Integer num) {
        this.activityId = num;
    }

    public final void setCal_func(@Nullable String str) {
        this.cal_func = str;
    }

    public final void setCategoryId(@Nullable Integer num) {
        this.categoryId = num;
    }

    public final void setCpaCode(@Nullable String str) {
        this.cpaCode = str;
    }

    public final void setDefaultActivityIcon(@Nullable Integer num) {
        this.defaultActivityIcon = num;
    }

    public final void setDefaultActivityName(@Nullable String str) {
        this.defaultActivityName = str;
    }

    public final void setDefaultCategoryIcon(@Nullable Integer num) {
        this.defaultCategoryIcon = num;
    }

    public final void setDefaultMets(@Nullable Double d) {
        this.defaultMets = d;
    }

    public final void setDescInImperial(@Nullable String str) {
        this.descInImperial = str;
    }

    public final void setDescInMetric(@Nullable String str) {
        this.descInMetric = str;
    }

    public final void setDeviceIconModels(@Nullable List<DeviceIconModel> list) {
        this.deviceIconModels = list;
    }

    public final void setDvcTitleInImperial(@Nullable List<String> list) {
        this.dvcTitleInImperial = list;
    }

    public final void setDvcTitleInMetric(@Nullable List<String> list) {
        this.dvcTitleInMetric = list;
    }

    public final void setFwActId(int i) {
        this.fwActId = i;
    }

    public final void setIconUrl(@Nullable String str) {
        this.iconUrl = str;
    }

    public final void setInbuilt(boolean z) {
        this.inbuilt = z;
    }

    public final void setMetrics(@Nullable List<String> list) {
        this.metrics = list;
    }

    public final void setShortTitle(@Nullable String str) {
        this.shortTitle = str;
    }

    public final void setTitleInImperial(@Nullable String str) {
        this.titleInImperial = str;
    }

    public final void setTitleInMetric(@Nullable String str) {
        this.titleInMetric = str;
    }
}
