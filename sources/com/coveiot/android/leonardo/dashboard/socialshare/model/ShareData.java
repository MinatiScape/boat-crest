package com.coveiot.android.leonardo.dashboard.socialshare.model;

import java.io.Serializable;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes4.dex */
public class ShareData implements Serializable {
    @Nullable
    private String computedValue;
    @Nullable
    private String duration;
    @Nullable
    private Object dynamicImage;
    @Nullable
    private String dynamicImageUrl;
    @Nullable
    private String level;
    @Nullable
    private String max;
    @Nullable
    private String min;
    @Nullable
    private ShareType shareType;
    @Nullable
    private String title;
    @Nullable
    private String value;

    /* loaded from: classes4.dex */
    public enum ShareType {
        HR,
        HRV,
        SPO2,
        SLEEP,
        STRESS,
        BADGES,
        WORKOUT,
        ENERGY,
        STEPS
    }

    @Nullable
    public final String getComputedValue() {
        return this.computedValue;
    }

    @Nullable
    public final String getDuration() {
        return this.duration;
    }

    @Nullable
    public final Object getDynamicImage() {
        return this.dynamicImage;
    }

    @Nullable
    public final String getDynamicImageUrl() {
        return this.dynamicImageUrl;
    }

    @Nullable
    public final String getLevel() {
        return this.level;
    }

    @Nullable
    public final String getMax() {
        return this.max;
    }

    @Nullable
    public final String getMin() {
        return this.min;
    }

    @Nullable
    public final ShareType getShareType() {
        return this.shareType;
    }

    @Nullable
    public final String getTitle() {
        return this.title;
    }

    @Nullable
    public final String getValue() {
        return this.value;
    }

    public final void setComputedValue(@Nullable String str) {
        this.computedValue = str;
    }

    public final void setDuration(@Nullable String str) {
        this.duration = str;
    }

    public final void setDynamicImage(@Nullable Object obj) {
        this.dynamicImage = obj;
    }

    public final void setDynamicImageUrl(@Nullable String str) {
        this.dynamicImageUrl = str;
    }

    public final void setLevel(@Nullable String str) {
        this.level = str;
    }

    public final void setMax(@Nullable String str) {
        this.max = str;
    }

    public final void setMin(@Nullable String str) {
        this.min = str;
    }

    public final void setShareType(@Nullable ShareType shareType) {
        this.shareType = shareType;
    }

    public final void setTitle(@Nullable String str) {
        this.title = str;
    }

    public final void setValue(@Nullable String str) {
        this.value = str;
    }
}
