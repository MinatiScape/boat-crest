package com.mappls.sdk.plugins.places.common.utils;

import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
/* loaded from: classes10.dex */
public final class c {
    public static void a(ViewGroup viewGroup) {
        ((InputMethodManager) viewGroup.getContext().getSystemService("input_method")).hideSoftInputFromWindow(viewGroup.getWindowToken(), 0);
    }

    public static void a(EditText editText) {
        ((InputMethodManager) editText.getContext().getSystemService("input_method")).showSoftInput(editText, 0);
    }
}
