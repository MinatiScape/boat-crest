package com.coveiot.android.tappy.utils;

import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import kotlin.text.StringsKt___StringsKt;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes7.dex */
public final class CreditCardNumberFormattingTextWatcher implements TextWatcher {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    public static final Regex i = new Regex("[^\\d]");
    @NotNull
    public String h = "";

    /* loaded from: classes7.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Override // android.text.TextWatcher
    public void afterTextChanged(@NotNull Editable s) {
        Intrinsics.checkNotNullParameter(s, "s");
        if (Intrinsics.areEqual(s.toString(), this.h)) {
            return;
        }
        String replace = i.replace(s.toString(), "");
        if (replace.length() <= 16) {
            this.h = CollectionsKt___CollectionsKt.joinToString$default(StringsKt___StringsKt.chunked(replace, 4), HexStringBuilder.DEFAULT_SEPARATOR, null, null, 0, null, null, 62, null);
            s.setFilters(new InputFilter[0]);
        }
        int length = s.length();
        String str = this.h;
        s.replace(0, length, str, 0, str.length());
    }

    @Override // android.text.TextWatcher
    public void beforeTextChanged(@NotNull CharSequence s, int i2, int i3, int i4) {
        Intrinsics.checkNotNullParameter(s, "s");
    }

    @Override // android.text.TextWatcher
    public void onTextChanged(@NotNull CharSequence s, int i2, int i3, int i4) {
        Intrinsics.checkNotNullParameter(s, "s");
    }
}
