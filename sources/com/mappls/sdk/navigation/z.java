package com.mappls.sdk.navigation;
/* loaded from: classes11.dex */
public final class z {
    public static z b;

    /* renamed from: a  reason: collision with root package name */
    public final String f13053a;

    public z(NavigationApplication navigationApplication) {
        this.f13053a = navigationApplication.getString(R.string.mappls_shared_string_navigation);
    }

    public static String a(NavigationApplication navigationApplication) {
        if (b == null) {
            b = new z(navigationApplication);
        }
        return b.f13053a;
    }
}
