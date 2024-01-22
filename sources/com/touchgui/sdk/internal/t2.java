package com.touchgui.sdk.internal;

import android.graphics.Bitmap;
import java.io.File;
import java.util.ArrayList;
/* loaded from: classes12.dex */
public interface t2 {
    default int a() {
        return 0;
    }

    default void a(int i) {
    }

    default void a(int i, int i2) {
    }

    void a(Bitmap bitmap);

    default void a(String str) {
    }

    default void a(ArrayList arrayList) {
        if (arrayList == null || arrayList.size() < 1) {
            return;
        }
        a((Bitmap) arrayList.get(0));
    }

    boolean a(File file);

    default void b() {
    }

    void b(int i);

    default void b(int i, int i2) {
    }

    boolean b(File file);

    default void c(int i) {
    }
}
