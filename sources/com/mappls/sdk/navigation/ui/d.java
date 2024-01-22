package com.mappls.sdk.navigation.ui;

import android.graphics.Bitmap;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes11.dex */
public class d {
    public static final d b = new d();

    /* renamed from: a  reason: collision with root package name */
    public Map<String, Bitmap> f12971a = new HashMap();

    public static d a() {
        return b;
    }

    public Bitmap a(String str) {
        if (this.f12971a.containsKey(str)) {
            return this.f12971a.get(str);
        }
        return null;
    }

    public void a(String str, Bitmap bitmap) {
        this.f12971a.put(str, bitmap);
    }
}
