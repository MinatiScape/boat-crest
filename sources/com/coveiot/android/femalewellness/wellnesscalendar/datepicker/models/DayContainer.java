package com.coveiot.android.femalewellness.wellnesscalendar.datepicker.models;

import android.view.View;
import android.widget.RelativeLayout;
import com.coveiot.android.femalewellness.wellnesscalendar.datepicker.customviews.CustomTextView;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
/* loaded from: classes4.dex */
public class DayContainer {

    /* renamed from: a  reason: collision with root package name */
    public static final SimpleDateFormat f4409a = new SimpleDateFormat("yyyyMMdd", Locale.ENGLISH);
    public RelativeLayout rootView;
    public View strip;
    public CustomTextView tvDate;

    public DayContainer(RelativeLayout relativeLayout) {
        this.rootView = relativeLayout;
        this.strip = relativeLayout.getChildAt(0);
        this.tvDate = (CustomTextView) relativeLayout.getChildAt(1);
    }

    public static int GetContainerKey(Calendar calendar) {
        return Integer.valueOf(f4409a.format(calendar.getTime())).intValue();
    }
}
