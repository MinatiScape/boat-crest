package com.mappls.sdk.maps.location;

import android.graphics.Bitmap;
import androidx.annotation.Nullable;
import com.mappls.sdk.maps.Style;
import com.mappls.sdk.maps.geometry.LatLng;
import com.mappls.sdk.maps.style.expressions.Expression;
/* loaded from: classes11.dex */
public interface m {
    void a(int i, @Nullable Bitmap bitmap, Bitmap bitmap2, Bitmap bitmap3, Bitmap bitmap4, Bitmap bitmap5, Bitmap bitmap6);

    void b(boolean z);

    void c(Expression expression);

    void d(Float f);

    void e(double d);

    void f(Float f);

    void g(int i, boolean z);

    void h(double d);

    void hide();

    void i(float f, @Nullable Float f2);

    void j(boolean z, int i);

    void k(float f, int i);

    void l(LocationComponentOptions locationComponentOptions);

    void m(Style style);

    void n(LatLng latLng);

    void o();

    void p(Float f);

    void q(k kVar);

    void r(String str, String str2, String str3, String str4, String str5);
}
