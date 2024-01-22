package com.mappls.sdk.navigation.ui.map.route;

import android.graphics.Bitmap;
import androidx.annotation.NonNull;
import com.mappls.sdk.maps.Style;
/* loaded from: classes11.dex */
public class k implements Style.OnStyleLoaded {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ String f12985a;
    public final /* synthetic */ Bitmap b;

    public k(l lVar, String str, Bitmap bitmap) {
        this.f12985a = str;
        this.b = bitmap;
    }

    @Override // com.mappls.sdk.maps.Style.OnStyleLoaded
    public void onStyleLoaded(@NonNull Style style) {
        style.addImageAsync(this.f12985a, this.b);
    }
}
