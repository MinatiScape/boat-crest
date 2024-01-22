package com.coveiot.android.theme.compundview;

import android.graphics.Color;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;
import com.clevertap.android.sdk.Constants;
/* loaded from: classes7.dex */
public class MySpannable extends ClickableSpan {
    public boolean h;

    public MySpannable(boolean z) {
        this.h = false;
        this.h = z;
    }

    @Override // android.text.style.ClickableSpan
    public void onClick(View view) {
    }

    @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
    public void updateDrawState(TextPaint textPaint) {
        textPaint.setUnderlineText(this.h);
        textPaint.setColor(Color.parseColor(Constants.WHITE));
    }
}
