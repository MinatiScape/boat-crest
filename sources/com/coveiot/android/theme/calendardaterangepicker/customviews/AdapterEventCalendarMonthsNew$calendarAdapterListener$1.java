package com.coveiot.android.theme.calendardaterangepicker.customviews;

import android.os.Handler;
import java.util.Calendar;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes7.dex */
public final class AdapterEventCalendarMonthsNew$calendarAdapterListener$1 implements CalendarListenerNew {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ AdapterEventCalendarMonthsNew f6089a;

    public AdapterEventCalendarMonthsNew$calendarAdapterListener$1(AdapterEventCalendarMonthsNew adapterEventCalendarMonthsNew) {
        this.f6089a = adapterEventCalendarMonthsNew;
    }

    public static final void c(AdapterEventCalendarMonthsNew this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.notifyDataSetChanged();
    }

    public static final void d(AdapterEventCalendarMonthsNew this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.notifyDataSetChanged();
    }

    @Override // com.coveiot.android.theme.calendardaterangepicker.customviews.CalendarListenerNew
    public void onDateRangeSelected(@NotNull Calendar startDate, @NotNull Calendar endDate) {
        Handler handler;
        CalendarListenerNew calendarListenerNew;
        CalendarListenerNew calendarListenerNew2;
        Intrinsics.checkNotNullParameter(startDate, "startDate");
        Intrinsics.checkNotNullParameter(endDate, "endDate");
        handler = this.f6089a.g;
        final AdapterEventCalendarMonthsNew adapterEventCalendarMonthsNew = this.f6089a;
        handler.postDelayed(new Runnable() { // from class: com.coveiot.android.theme.calendardaterangepicker.customviews.c
            @Override // java.lang.Runnable
            public final void run() {
                AdapterEventCalendarMonthsNew$calendarAdapterListener$1.c(AdapterEventCalendarMonthsNew.this);
            }
        }, 50L);
        calendarListenerNew = this.f6089a.e;
        if (calendarListenerNew != null) {
            calendarListenerNew2 = this.f6089a.e;
            Intrinsics.checkNotNull(calendarListenerNew2);
            calendarListenerNew2.onDateRangeSelected(startDate, endDate);
        }
    }

    @Override // com.coveiot.android.theme.calendardaterangepicker.customviews.CalendarListenerNew
    public void onFirstDateSelected(@NotNull Calendar startDate) {
        Handler handler;
        CalendarListenerNew calendarListenerNew;
        CalendarListenerNew calendarListenerNew2;
        Intrinsics.checkNotNullParameter(startDate, "startDate");
        handler = this.f6089a.g;
        final AdapterEventCalendarMonthsNew adapterEventCalendarMonthsNew = this.f6089a;
        handler.postDelayed(new Runnable() { // from class: com.coveiot.android.theme.calendardaterangepicker.customviews.b
            @Override // java.lang.Runnable
            public final void run() {
                AdapterEventCalendarMonthsNew$calendarAdapterListener$1.d(AdapterEventCalendarMonthsNew.this);
            }
        }, 50L);
        calendarListenerNew = this.f6089a.e;
        if (calendarListenerNew != null) {
            calendarListenerNew2 = this.f6089a.e;
            Intrinsics.checkNotNull(calendarListenerNew2);
            calendarListenerNew2.onFirstDateSelected(startDate);
        }
    }
}
