package com.coveiot.android.theme.utils;

import android.graphics.Color;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;
/* loaded from: classes7.dex */
public class MySpannable extends ClickableSpan {
    public boolean h;
    public OnTextClickListener i;
    public String j;

    /* loaded from: classes7.dex */
    public interface OnTextClickListener {
        void onTextClicked(String str);
    }

    public MySpannable(OnTextClickListener onTextClickListener, String str, boolean z) {
        this.h = false;
        this.i = null;
        this.j = null;
        this.i = onTextClickListener;
        this.j = str;
        this.h = z;
    }

    @Override // android.text.style.ClickableSpan
    public void onClick(View view) {
        OnTextClickListener onTextClickListener = this.i;
        if (onTextClickListener != null) {
            onTextClickListener.onTextClicked(this.j);
        }
    }

    @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
    public void updateDrawState(TextPaint textPaint) {
        textPaint.setUnderlineText(this.h);
        textPaint.setColor(Color.parseColor("#ed1c24"));
    }
}
