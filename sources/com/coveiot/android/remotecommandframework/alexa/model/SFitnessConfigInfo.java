package com.coveiot.android.remotecommandframework.alexa.model;

import com.google.gson.annotations.SerializedName;
import com.mappls.sdk.maps.style.layers.Property;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes6.dex */
public final class SFitnessConfigInfo extends SCommandInfo {
    @SerializedName(Property.ICON_TEXT_FIT_HEIGHT)
    @Nullable
    private String height;
    private transient boolean isDistanceInMile;
    @SerializedName("weight")
    @Nullable
    private String weight;

    @Nullable
    public final String getHeight() {
        return this.height;
    }

    @Nullable
    public final String getWeight() {
        return this.weight;
    }

    public final boolean isDistanceInMile() {
        return this.isDistanceInMile;
    }

    public final void setDistanceInMile(boolean z) {
        this.isDistanceInMile = z;
    }

    public final void setHeight(@Nullable String str) {
        this.height = str;
    }

    public final void setWeight(@Nullable String str) {
        this.weight = str;
    }
}
