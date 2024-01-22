package com.coveiot.android.femalewellness.wellnesscalendar.datepicker.customviews;

import android.content.Context;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatTextView;
/* loaded from: classes4.dex */
public class CustomTextView extends AppCompatTextView {
    public CustomTextView(Context context) {
        super(context);
        n(context, null, 0);
    }

    public final void n(Context context, AttributeSet attributeSet, int i) {
        isInEditMode();
    }

    public CustomTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        n(context, attributeSet, 0);
    }

    public CustomTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        n(context, attributeSet, i);
    }
}
