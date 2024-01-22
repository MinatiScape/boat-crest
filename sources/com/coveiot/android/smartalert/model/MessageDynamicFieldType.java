package com.coveiot.android.smartalert.model;

import com.clevertap.android.sdk.variables.CTVariableUtils;
import com.mappls.sdk.maps.style.layers.Property;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes6.dex */
public enum MessageDynamicFieldType {
    TEXT("text"),
    NUMBER(CTVariableUtils.NUMBER),
    CIRCULAR("circle"),
    SQUARE(Property.LINE_CAP_SQUARE),
    RECTANGULAR("rectangular"),
    IMAGE("image"),
    ANIMATION("animation"),
    BUTTON("button"),
    BUTTON_IMG("button_img"),
    BAR("bar");
    
    @NotNull
    private String type;

    MessageDynamicFieldType(String str) {
        this.type = str;
    }

    @NotNull
    public final String getType() {
        return this.type;
    }

    public final void setType(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.type = str;
    }
}
