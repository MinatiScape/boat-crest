package com.mappls.sdk.maps.location;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.mappls.sdk.maps.Style;
import com.mappls.sdk.maps.style.layers.Layer;
/* loaded from: classes11.dex */
public class k {
    @NonNull

    /* renamed from: a  reason: collision with root package name */
    public final Style f12773a;
    @Nullable
    public String b;
    @Nullable
    public String c;

    public k(@NonNull Style style, @Nullable String str, @Nullable String str2) {
        this.f12773a = style;
        this.b = str;
        this.c = str2;
    }

    public void a(@NonNull Layer layer) {
        String str = this.b;
        if (str != null) {
            this.f12773a.addLayerAbove(layer, str);
            return;
        }
        String str2 = this.c;
        if (str2 != null) {
            this.f12773a.addLayerBelow(layer, str2);
        } else {
            this.f12773a.addLayer(layer);
        }
    }

    public boolean b(@Nullable String str, @Nullable String str2) {
        String str3;
        String str4 = this.b;
        boolean z = (str4 != str && (str4 == null || !str4.equals(str))) || ((str3 = this.c) != str2 && (str3 == null || !str3.equals(str2)));
        this.b = str;
        this.c = str2;
        return z;
    }
}
