package com.coveiot.android.theme.calendardaterangepicker.models;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import androidx.core.content.ContextCompat;
import com.coveiot.android.theme.R;
import com.coveiot.android.theme.calendardaterangepicker.models.CalendarStyleAttributes;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes7.dex */
public final class CalendarStyleAttrImplNew implements CalendarStyleAttributes {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public Typeface f6096a;
    public int b;
    @Nullable
    public Drawable c;
    public int d;
    public int e;
    public int f;
    public int g;
    public int h;
    public int i;
    public int j;
    public float k;
    public float l;
    public float m;
    public boolean n;
    public int o;
    public boolean p;
    @NotNull
    public CalendarStyleAttributes.DateSelectionMode q;
    public int r;

    /* loaded from: classes7.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final CalendarStyleAttrImplNew getDefAttributes(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            CalendarStyleAttrImplNew calendarStyleAttrImplNew = new CalendarStyleAttrImplNew(context, null, 2, null);
            calendarStyleAttrImplNew.k = context.getResources().getDimension(R.dimen.text_size_title);
            calendarStyleAttrImplNew.l = context.getResources().getDimension(R.dimen.text_size_week);
            calendarStyleAttrImplNew.m = context.getResources().getDimension(R.dimen.text_size_date);
            calendarStyleAttrImplNew.d = ContextCompat.getColor(context, R.color.week_color);
            calendarStyleAttrImplNew.e = ContextCompat.getColor(context, R.color.range_bg_color);
            calendarStyleAttrImplNew.f = ContextCompat.getColor(context, R.color.selected_date_circle_color);
            calendarStyleAttrImplNew.g = ContextCompat.getColor(context, R.color.selected_date_color);
            calendarStyleAttrImplNew.h = ContextCompat.getColor(context, R.color.default_date_color);
            calendarStyleAttrImplNew.j = ContextCompat.getColor(context, R.color.range_date_color);
            calendarStyleAttrImplNew.i = ContextCompat.getColor(context, R.color.disable_date_color);
            return calendarStyleAttrImplNew;
        }
    }

    public CalendarStyleAttrImplNew(@NotNull Context context, @Nullable AttributeSet attributeSet) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.b = ContextCompat.getColor(context, R.color.title_color);
        this.d = ContextCompat.getColor(context, R.color.week_color);
        this.e = ContextCompat.getColor(context, R.color.range_bg_color);
        this.f = ContextCompat.getColor(context, R.color.selected_date_circle_color);
        this.g = ContextCompat.getColor(context, R.color.selected_date_color);
        this.h = ContextCompat.getColor(context, R.color.default_date_color);
        this.i = ContextCompat.getColor(context, R.color.disable_date_color);
        this.j = ContextCompat.getColor(context, R.color.range_date_color);
        this.k = context.getResources().getDimension(R.dimen.text_size_title);
        this.l = context.getResources().getDimension(R.dimen.text_size_week);
        this.m = context.getResources().getDimension(R.dimen.text_size_date);
        this.p = true;
        this.q = CalendarStyleAttributes.DateSelectionMode.FREE_RANGE;
        this.r = 7;
        if (attributeSet != null) {
            a(context, attributeSet);
        }
    }

    public final void a(Context context, AttributeSet attributeSet) {
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.CalendarDateRangeMonthViewNew, 0, 0);
            Intrinsics.checkNotNullExpressionValue(obtainStyledAttributes, "context.obtainStyledAttr…eRangeMonthViewNew, 0, 0)");
            try {
                this.b = obtainStyledAttributes.getColor(R.styleable.CalendarDateRangeMonthViewNew_title_color_new, getTitleColor());
                setHeaderBg(obtainStyledAttributes.getDrawable(R.styleable.CalendarDateRangeMonthViewNew_header_bg_new));
                this.d = obtainStyledAttributes.getColor(R.styleable.CalendarDateRangeMonthViewNew_week_color_new, getWeekColor());
                this.e = obtainStyledAttributes.getColor(R.styleable.CalendarDateRangeMonthViewNew_range_color_new, getRangeStripColor());
                this.f = obtainStyledAttributes.getColor(R.styleable.CalendarDateRangeMonthViewNew_selected_date_circle_color_new, getSelectedDateCircleColor());
                setShouldEnabledTime(obtainStyledAttributes.getBoolean(R.styleable.CalendarDateRangeMonthViewNew_enable_time_selection_new, false));
                setEditable(obtainStyledAttributes.getBoolean(R.styleable.CalendarDateRangeMonthViewNew_editable_new, true));
                this.k = obtainStyledAttributes.getDimension(R.styleable.CalendarDateRangeMonthViewNew_text_size_title_new, getTextSizeTitle());
                this.l = obtainStyledAttributes.getDimension(R.styleable.CalendarDateRangeMonthViewNew_text_size_week_new, getTextSizeWeek());
                this.m = obtainStyledAttributes.getDimension(R.styleable.CalendarDateRangeMonthViewNew_text_size_date_new, getTextSizeDate());
                this.g = obtainStyledAttributes.getColor(R.styleable.CalendarDateRangeMonthViewNew_selected_date_color_new, getSelectedDateColor());
                this.h = obtainStyledAttributes.getColor(R.styleable.CalendarDateRangeMonthViewNew_default_date_color_new, getDefaultDateColor());
                this.j = obtainStyledAttributes.getColor(R.styleable.CalendarDateRangeMonthViewNew_range_date_color_new, getRangeDateColor());
                this.i = obtainStyledAttributes.getColor(R.styleable.CalendarDateRangeMonthViewNew_disable_date_color_new, getDisableDateColor());
                setWeekOffset(obtainStyledAttributes.getColor(R.styleable.CalendarDateRangeMonthViewNew_week_offset_new, 0));
                setDateSelectionMode(CalendarStyleAttributes.DateSelectionMode.values()[obtainStyledAttributes.getInt(R.styleable.CalendarDateRangeMonthViewNew_date_selection_mode_new, 0)]);
            } finally {
                obtainStyledAttributes.recycle();
            }
        }
    }

    @Override // com.coveiot.android.theme.calendardaterangepicker.models.CalendarStyleAttributes
    @NotNull
    public CalendarStyleAttributes.DateSelectionMode getDateSelectionMode() {
        return this.q;
    }

    @Override // com.coveiot.android.theme.calendardaterangepicker.models.CalendarStyleAttributes
    public int getDefaultDateColor() {
        return this.h;
    }

    @Override // com.coveiot.android.theme.calendardaterangepicker.models.CalendarStyleAttributes
    public int getDisableDateColor() {
        return this.i;
    }

    @Override // com.coveiot.android.theme.calendardaterangepicker.models.CalendarStyleAttributes
    public int getFixedDaysSelectionNumber() {
        return this.r;
    }

    @Override // com.coveiot.android.theme.calendardaterangepicker.models.CalendarStyleAttributes
    @Nullable
    public Typeface getFonts() {
        return this.f6096a;
    }

    @Override // com.coveiot.android.theme.calendardaterangepicker.models.CalendarStyleAttributes
    @Nullable
    public Drawable getHeaderBg() {
        return this.c;
    }

    @Override // com.coveiot.android.theme.calendardaterangepicker.models.CalendarStyleAttributes
    public int getRangeDateColor() {
        return this.j;
    }

    @Override // com.coveiot.android.theme.calendardaterangepicker.models.CalendarStyleAttributes
    public int getRangeStripColor() {
        return this.e;
    }

    @Override // com.coveiot.android.theme.calendardaterangepicker.models.CalendarStyleAttributes
    public int getSelectedDateCircleColor() {
        return this.f;
    }

    @Override // com.coveiot.android.theme.calendardaterangepicker.models.CalendarStyleAttributes
    public int getSelectedDateColor() {
        return this.g;
    }

    @Override // com.coveiot.android.theme.calendardaterangepicker.models.CalendarStyleAttributes
    public float getTextSizeDate() {
        return this.m;
    }

    @Override // com.coveiot.android.theme.calendardaterangepicker.models.CalendarStyleAttributes
    public float getTextSizeTitle() {
        return this.k;
    }

    @Override // com.coveiot.android.theme.calendardaterangepicker.models.CalendarStyleAttributes
    public float getTextSizeWeek() {
        return this.l;
    }

    @Override // com.coveiot.android.theme.calendardaterangepicker.models.CalendarStyleAttributes
    public int getTitleColor() {
        return this.b;
    }

    @Override // com.coveiot.android.theme.calendardaterangepicker.models.CalendarStyleAttributes
    public int getWeekColor() {
        return this.d;
    }

    @Override // com.coveiot.android.theme.calendardaterangepicker.models.CalendarStyleAttributes
    public int getWeekOffset() {
        return this.o;
    }

    @Override // com.coveiot.android.theme.calendardaterangepicker.models.CalendarStyleAttributes
    public boolean isEditable() {
        return this.p;
    }

    @Override // com.coveiot.android.theme.calendardaterangepicker.models.CalendarStyleAttributes
    public boolean isShouldEnabledTime() {
        return this.n;
    }

    @Override // com.coveiot.android.theme.calendardaterangepicker.models.CalendarStyleAttributes
    public void setDateSelectionMode(@NotNull CalendarStyleAttributes.DateSelectionMode dateSelectionMode) {
        Intrinsics.checkNotNullParameter(dateSelectionMode, "<set-?>");
        this.q = dateSelectionMode;
    }

    @Override // com.coveiot.android.theme.calendardaterangepicker.models.CalendarStyleAttributes
    public void setEditable(boolean z) {
        this.p = z;
    }

    @Override // com.coveiot.android.theme.calendardaterangepicker.models.CalendarStyleAttributes
    public void setFixedDaysSelectionNumber(int i) {
        if (getDateSelectionMode() != CalendarStyleAttributes.DateSelectionMode.FIXED_RANGE) {
            throw new InvalidCalendarAttributeException("Selected date selection mode is not `fixed_range` for `date_selection_mode` attribute in layout.");
        }
        if (i >= 0 && i <= 365) {
            this.r = i;
            return;
        }
        throw new InvalidCalendarAttributeException("Fixed days can be between 0 to 365.");
    }

    @Override // com.coveiot.android.theme.calendardaterangepicker.models.CalendarStyleAttributes
    public void setFonts(@Nullable Typeface typeface) {
        this.f6096a = typeface;
    }

    @Override // com.coveiot.android.theme.calendardaterangepicker.models.CalendarStyleAttributes
    public void setHeaderBg(@Nullable Drawable drawable) {
        this.c = drawable;
    }

    public void setShouldEnabledTime(boolean z) {
        this.n = z;
    }

    @Override // com.coveiot.android.theme.calendardaterangepicker.models.CalendarStyleAttributes
    public void setWeekOffset(int i) {
        if (i >= 0 && i <= 6) {
            this.o = i;
            return;
        }
        throw new InvalidCalendarAttributeException("Week offset can only be between 0 to 6. 0->Sun, 1->Mon, 2->Tue, 3->Wed, 4->Thu, 5->Fri, 6->Sat");
    }

    public /* synthetic */ CalendarStyleAttrImplNew(Context context, AttributeSet attributeSet, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i & 2) != 0 ? null : attributeSet);
    }
}
