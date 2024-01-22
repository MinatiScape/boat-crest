package com.mappls.sdk.navigation.util;

import android.graphics.drawable.Drawable;
/* loaded from: classes11.dex */
public class ManeuverInfo {

    /* renamed from: a  reason: collision with root package name */
    public String f13042a = "";
    public Drawable b = null;

    public Drawable getIcon() {
        return this.b;
    }

    public String getInfoText() {
        return this.f13042a;
    }

    public void setIcon(Drawable drawable) {
        this.b = drawable;
    }

    public void setInfoText(String str) {
        this.f13042a = str;
    }
}
