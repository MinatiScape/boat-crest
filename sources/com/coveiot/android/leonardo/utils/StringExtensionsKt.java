package com.coveiot.android.leonardo.utils;

import android.text.SpannableString;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.text.style.UnderlineSpan;
import android.view.View;
import java.util.Iterator;
import java.util.List;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes5.dex */
public final class StringExtensionsKt {
    public static final boolean containsList(@NotNull String str, @NotNull List<String> list) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        Intrinsics.checkNotNullParameter(list, "list");
        Iterator<String> it = list.iterator();
        boolean z = false;
        while (it.hasNext() && !(z = StringsKt__StringsKt.contains((CharSequence) str, (CharSequence) it.next(), true))) {
        }
        return z;
    }

    @NotNull
    public static final SpannableString makeUnderlineClickable(@NotNull CharSequence charSequence, @NotNull final Function1<? super Integer, Unit> listener) {
        Intrinsics.checkNotNullParameter(charSequence, "<this>");
        Intrinsics.checkNotNullParameter(listener, "listener");
        SpannableString spannableString = new SpannableString(charSequence);
        int i = 0;
        UnderlineSpan[] underlineSpanArr = (UnderlineSpan[]) spannableString.getSpans(0, charSequence.length(), UnderlineSpan.class);
        if (underlineSpanArr != null) {
            int length = underlineSpanArr.length;
            final int i2 = 0;
            while (i < length) {
                UnderlineSpan underlineSpan = underlineSpanArr[i];
                spannableString.setSpan(new ClickableSpan() { // from class: com.coveiot.android.leonardo.utils.StringExtensionsKt$makeUnderlineClickable$1$clickableSpan$1
                    @Override // android.text.style.ClickableSpan
                    public void onClick(@NotNull View widget) {
                        Intrinsics.checkNotNullParameter(widget, "widget");
                        listener.invoke(Integer.valueOf(i2));
                    }

                    @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
                    public void updateDrawState(@NotNull TextPaint ds) {
                        Intrinsics.checkNotNullParameter(ds, "ds");
                        ds.setUnderlineText(true);
                    }
                }, spannableString.getSpanStart(underlineSpan), spannableString.getSpanEnd(underlineSpan), 33);
                i++;
                i2++;
            }
        }
        return spannableString;
    }
}
