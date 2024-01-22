package com.mappls.sdk.direction.ui.utils;

import android.view.View;
import android.view.inputmethod.InputMethodManager;
/* loaded from: classes11.dex */
public final class a {
    public static void a(View view) {
        ((InputMethodManager) view.getContext().getSystemService("input_method")).hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
