package com.coveiot.android.theme.calendardaterangepicker.customviews;

import android.graphics.Typeface;
import android.view.View;
import com.coveiot.android.theme.calendardaterangepicker.models.CalendarStyleAttributes;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes7.dex */
public interface DateView {
    @NotNull
    public static final Companion Companion = Companion.f6095a;

    /* loaded from: classes7.dex */
    public static final class Companion {

        /* renamed from: a  reason: collision with root package name */
        public static final /* synthetic */ Companion f6095a = new Companion();

        public final long getContainerKey(@NotNull Calendar cal) {
            Intrinsics.checkNotNullParameter(cal, "cal");
            String str = new SimpleDateFormat("yyyyMMddHHmm", Locale.getDefault()).format(cal.getTime());
            Intrinsics.checkNotNullExpressionValue(str, "str");
            return Long.parseLong(str);
        }
    }

    /* loaded from: classes7.dex */
    public enum DateState {
        HIDDEN,
        DISABLE,
        SELECTABLE,
        START,
        END,
        MIDDLE,
        START_END_SAME
    }

    /* loaded from: classes7.dex */
    public interface OnDateClickListener {
        void onDateClicked(@NotNull View view, @NotNull Calendar calendar);
    }

    float getDateTextSize();

    int getDefaultDateColor();

    int getDisableDateColor();

    int getRangeDateColor();

    int getSelectedDateCircleColor();

    int getSelectedDateColor();

    int getStripColor();

    void refreshLayout();

    void setDateClickListener(@NotNull OnDateClickListener onDateClickListener);

    void setDateStyleAttributes(@NotNull CalendarStyleAttributes calendarStyleAttributes);

    void setDateTag(@NotNull Calendar calendar);

    void setDateText(@NotNull String str);

    void setDateTextSize(float f);

    void setDefaultDateColor(int i);

    void setDisableDateColor(int i);

    void setRangeDateColor(int i);

    void setSelectedDateCircleColor(int i);

    void setSelectedDateColor(int i);

    void setStripColor(int i);

    void setTypeface(@NotNull Typeface typeface);

    void updateDateBackground(@NotNull DateState dateState);
}
