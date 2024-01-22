package com.coveiot.android.leonardo.sensai.model.feedback;

import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class OptionModel {
    @Nullable
    private String activeIconUrl;
    @Nullable
    private String iconUrl;
    @Nullable
    private String inactiveIconUrl;
    @Nullable
    private String optionId;
    @Nullable
    private String text;

    @Nullable
    public final String getActiveIconUrl() {
        return this.activeIconUrl;
    }

    @Nullable
    public final String getIconUrl() {
        return this.iconUrl;
    }

    @Nullable
    public final String getInactiveIconUrl() {
        return this.inactiveIconUrl;
    }

    @Nullable
    public final String getOptionId() {
        return this.optionId;
    }

    @Nullable
    public final String getText() {
        return this.text;
    }

    public final void setActiveIconUrl(@Nullable String str) {
        this.activeIconUrl = str;
    }

    public final void setIconUrl(@Nullable String str) {
        this.iconUrl = str;
    }

    public final void setInactiveIconUrl(@Nullable String str) {
        this.inactiveIconUrl = str;
    }

    public final void setOptionId(@Nullable String str) {
        this.optionId = str;
    }

    public final void setText(@Nullable String str) {
        this.text = str;
    }
}
