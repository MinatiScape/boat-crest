package com.coveiot.android.theme.calendardaterangepicker.customviews;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.coveiot.android.theme.R;
import com.coveiot.android.theme.calendardaterangepicker.customviews.CalendarDateRangeManagerNew;
import com.coveiot.android.theme.calendardaterangepicker.customviews.DateView;
import com.coveiot.android.theme.calendardaterangepicker.models.CalendarStyleAttributes;
import com.coveiot.android.theme.calendardaterangepicker.models.DateTimingNew;
import com.coveiot.android.theme.calendardaterangepicker.timepicker.AwesomeTimePickerDialog;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes7.dex */
public final class DateRangeMonthViewNew extends LinearLayout {
    @NotNull
    public static final Companion Companion = new Companion(null);
    public static final String o = DateRangeMonthViewNew.class.getSimpleName();
    @NotNull
    public Map<Integer, View> _$_findViewCache;
    public LinearLayout h;
    public LinearLayout i;
    public Calendar j;
    public CalendarStyleAttributes k;
    @Nullable
    public CalendarListenerNew l;
    public CalendarDateRangeManagerNew m;
    @NotNull
    public final DateView.OnDateClickListener n;

    /* loaded from: classes7.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* loaded from: classes7.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[CalendarStyleAttributes.DateSelectionMode.values().length];
            try {
                iArr[CalendarStyleAttributes.DateSelectionMode.FREE_RANGE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[CalendarStyleAttributes.DateSelectionMode.SINGLE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[CalendarStyleAttributes.DateSelectionMode.FIXED_RANGE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DateRangeMonthViewNew(@NotNull Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        this._$_findViewCache = new LinkedHashMap();
        this.n = new DateView.OnDateClickListener() { // from class: com.coveiot.android.theme.calendardaterangepicker.customviews.DateRangeMonthViewNew$mOnDateClickListener$1
            @Override // com.coveiot.android.theme.calendardaterangepicker.customviews.DateView.OnDateClickListener
            public void onDateClicked(@NotNull View view, @NotNull final Calendar selectedDate) {
                CalendarStyleAttributes calendarStyleAttributes;
                CalendarStyleAttributes calendarStyleAttributes2;
                Intrinsics.checkNotNullParameter(view, "view");
                Intrinsics.checkNotNullParameter(selectedDate, "selectedDate");
                calendarStyleAttributes = DateRangeMonthViewNew.this.k;
                CalendarStyleAttributes calendarStyleAttributes3 = null;
                if (calendarStyleAttributes == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("calendarStyleAttr");
                    calendarStyleAttributes = null;
                }
                if (calendarStyleAttributes.isEditable()) {
                    calendarStyleAttributes2 = DateRangeMonthViewNew.this.k;
                    if (calendarStyleAttributes2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("calendarStyleAttr");
                    } else {
                        calendarStyleAttributes3 = calendarStyleAttributes2;
                    }
                    if (!calendarStyleAttributes3.isShouldEnabledTime()) {
                        DateRangeMonthViewNew.this.setSelectedDate(selectedDate);
                        return;
                    }
                    Context context2 = DateRangeMonthViewNew.this.getContext();
                    Intrinsics.checkNotNullExpressionValue(context2, "context");
                    String string = DateRangeMonthViewNew.this.getContext().getString(R.string.select_time);
                    Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.select_time)");
                    final DateRangeMonthViewNew dateRangeMonthViewNew = DateRangeMonthViewNew.this;
                    new AwesomeTimePickerDialog(context2, string, new AwesomeTimePickerDialog.TimePickerCallback() { // from class: com.coveiot.android.theme.calendardaterangepicker.customviews.DateRangeMonthViewNew$mOnDateClickListener$1$onDateClicked$awesomeTimePickerDialog$1
                        @Override // com.coveiot.android.theme.calendardaterangepicker.timepicker.AwesomeTimePickerDialog.TimePickerCallback
                        public void onCancel() {
                            dateRangeMonthViewNew.resetAllSelectedViews();
                        }

                        @Override // com.coveiot.android.theme.calendardaterangepicker.timepicker.AwesomeTimePickerDialog.TimePickerCallback
                        public void onTimeSelected(int i, int i2) {
                            selectedDate.set(10, i);
                            selectedDate.set(12, i2);
                            dateRangeMonthViewNew.setSelectedDate(selectedDate);
                        }
                    }).showDialog();
                }
            }
        };
        c(context);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setSelectedDate(Calendar calendar) {
        CalendarStyleAttributes calendarStyleAttributes = this.k;
        Calendar calendar2 = null;
        if (calendarStyleAttributes == null) {
            Intrinsics.throwUninitializedPropertyAccessException("calendarStyleAttr");
            calendarStyleAttributes = null;
        }
        CalendarStyleAttributes.DateSelectionMode dateSelectionMode = calendarStyleAttributes.getDateSelectionMode();
        CalendarDateRangeManagerNew calendarDateRangeManagerNew = this.m;
        if (calendarDateRangeManagerNew == null) {
            Intrinsics.throwUninitializedPropertyAccessException("dateRangeCalendarManager");
            calendarDateRangeManagerNew = null;
        }
        Calendar minSelectedDate = calendarDateRangeManagerNew.getMinSelectedDate();
        CalendarDateRangeManagerNew calendarDateRangeManagerNew2 = this.m;
        if (calendarDateRangeManagerNew2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("dateRangeCalendarManager");
            calendarDateRangeManagerNew2 = null;
        }
        Calendar maxSelectedDate = calendarDateRangeManagerNew2.getMaxSelectedDate();
        int i = WhenMappings.$EnumSwitchMapping$0[dateSelectionMode.ordinal()];
        if (i != 1) {
            if (i != 2) {
                if (i == 3) {
                    Object clone = calendar.clone();
                    Intrinsics.checkNotNull(clone, "null cannot be cast to non-null type java.util.Calendar");
                    maxSelectedDate = (Calendar) clone;
                    CalendarStyleAttributes calendarStyleAttributes2 = this.k;
                    if (calendarStyleAttributes2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("calendarStyleAttr");
                        calendarStyleAttributes2 = null;
                    }
                    maxSelectedDate.add(5, calendarStyleAttributes2.getFixedDaysSelectionNumber());
                    minSelectedDate = calendar;
                }
            }
            minSelectedDate = calendar;
            maxSelectedDate = minSelectedDate;
        } else if (minSelectedDate == null || maxSelectedDate != null) {
            if (maxSelectedDate != null) {
                minSelectedDate = calendar;
                maxSelectedDate = null;
            }
            minSelectedDate = calendar;
        } else {
            DateView.Companion companion = DateView.Companion;
            int i2 = (companion.getContainerKey(minSelectedDate) > companion.getContainerKey(calendar) ? 1 : (companion.getContainerKey(minSelectedDate) == companion.getContainerKey(calendar) ? 0 : -1));
            if (i2 != 0) {
                if (i2 > 0) {
                    Object clone2 = minSelectedDate.clone();
                    Intrinsics.checkNotNull(clone2, "null cannot be cast to non-null type java.util.Calendar");
                    maxSelectedDate = (Calendar) clone2;
                    minSelectedDate = calendar;
                } else {
                    maxSelectedDate = calendar;
                }
            }
            minSelectedDate = calendar;
            maxSelectedDate = minSelectedDate;
        }
        CalendarDateRangeManagerNew calendarDateRangeManagerNew3 = this.m;
        if (calendarDateRangeManagerNew3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("dateRangeCalendarManager");
            calendarDateRangeManagerNew3 = null;
        }
        calendarDateRangeManagerNew3.setSelectedDateRange(minSelectedDate, maxSelectedDate);
        Calendar calendar3 = this.j;
        if (calendar3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("currentCalendarMonth");
        } else {
            calendar2 = calendar3;
        }
        a(calendar2);
        Log.i(o, "Time: " + calendar.getTime());
        if (maxSelectedDate != null) {
            CalendarListenerNew calendarListenerNew = this.l;
            Intrinsics.checkNotNull(calendarListenerNew);
            calendarListenerNew.onDateRangeSelected(minSelectedDate, maxSelectedDate);
            return;
        }
        CalendarListenerNew calendarListenerNew2 = this.l;
        Intrinsics.checkNotNull(calendarListenerNew2);
        calendarListenerNew2.onFirstDateSelected(minSelectedDate);
    }

    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    @Nullable
    public View _$_findCachedViewById(int i) {
        Map<Integer, View> map = this._$_findViewCache;
        View view = map.get(Integer.valueOf(i));
        if (view == null) {
            View findViewById = findViewById(i);
            if (findViewById != null) {
                map.put(Integer.valueOf(i), findViewById);
                return findViewById;
            }
            return null;
        }
        return view;
    }

    public final void a(Calendar calendar) {
        d();
        Object clone = calendar.clone();
        Intrinsics.checkNotNull(clone, "null cannot be cast to non-null type java.util.Calendar");
        Calendar calendar2 = (Calendar) clone;
        this.j = calendar2;
        if (calendar2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("currentCalendarMonth");
            calendar2 = null;
        }
        calendar2.set(5, 1);
        Calendar calendar3 = this.j;
        if (calendar3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("currentCalendarMonth");
            calendar3 = null;
        }
        CalendarRangeUtilsKt.resetTime(calendar3, DateTimingNew.NONE);
        String[] stringArray = getContext().getResources().getStringArray(R.array.week_sun_sat);
        Intrinsics.checkNotNullExpressionValue(stringArray, "context.resources.getStr…ray(R.array.week_sun_sat)");
        for (int i = 0; i < 7; i++) {
            LinearLayout linearLayout = this.i;
            if (linearLayout == null) {
                Intrinsics.throwUninitializedPropertyAccessException("llTitleWeekContainer");
                linearLayout = null;
            }
            View childAt = linearLayout.getChildAt(i);
            Intrinsics.checkNotNull(childAt, "null cannot be cast to non-null type com.coveiot.android.theme.calendardaterangepicker.customviews.CalendarCustomTextView");
            CalendarCustomTextView calendarCustomTextView = (CalendarCustomTextView) childAt;
            CalendarStyleAttributes calendarStyleAttributes = this.k;
            if (calendarStyleAttributes == null) {
                Intrinsics.throwUninitializedPropertyAccessException("calendarStyleAttr");
                calendarStyleAttributes = null;
            }
            calendarCustomTextView.setText(stringArray[(calendarStyleAttributes.getWeekOffset() + i) % 7]);
        }
        int i2 = calendar.get(7);
        CalendarStyleAttributes calendarStyleAttributes2 = this.k;
        if (calendarStyleAttributes2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("calendarStyleAttr");
            calendarStyleAttributes2 = null;
        }
        int weekOffset = i2 - calendarStyleAttributes2.getWeekOffset();
        if (weekOffset < 1) {
            weekOffset += 7;
        }
        calendar.add(5, (-weekOffset) + 1);
        LinearLayout linearLayout2 = this.h;
        if (linearLayout2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("llDaysContainer");
            linearLayout2 = null;
        }
        int childCount = linearLayout2.getChildCount();
        for (int i3 = 0; i3 < childCount; i3++) {
            LinearLayout linearLayout3 = this.h;
            if (linearLayout3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("llDaysContainer");
                linearLayout3 = null;
            }
            View childAt2 = linearLayout3.getChildAt(i3);
            Intrinsics.checkNotNull(childAt2, "null cannot be cast to non-null type android.widget.LinearLayout");
            LinearLayout linearLayout4 = (LinearLayout) childAt2;
            for (int i4 = 0; i4 < 7; i4++) {
                View childAt3 = linearLayout4.getChildAt(i4);
                Intrinsics.checkNotNull(childAt3, "null cannot be cast to non-null type com.coveiot.android.theme.calendardaterangepicker.customviews.CustomDateViewNew");
                b((CustomDateViewNew) childAt3, calendar);
                calendar.add(5, 1);
            }
        }
    }

    public final void b(CustomDateViewNew customDateViewNew, Calendar calendar) {
        DateView.DateState dateState;
        customDateViewNew.setDateText(String.valueOf(calendar.get(5)));
        CalendarStyleAttributes calendarStyleAttributes = this.k;
        CalendarDateRangeManagerNew calendarDateRangeManagerNew = null;
        if (calendarStyleAttributes == null) {
            Intrinsics.throwUninitializedPropertyAccessException("calendarStyleAttr");
            calendarStyleAttributes = null;
        }
        customDateViewNew.setDateStyleAttributes(calendarStyleAttributes);
        customDateViewNew.setDateClickListener(this.n);
        CalendarStyleAttributes calendarStyleAttributes2 = this.k;
        if (calendarStyleAttributes2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("calendarStyleAttr");
            calendarStyleAttributes2 = null;
        }
        Typeface fonts = calendarStyleAttributes2.getFonts();
        if (fonts != null) {
            customDateViewNew.setTypeface(fonts);
        }
        Calendar calendar2 = this.j;
        if (calendar2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("currentCalendarMonth");
            calendar2 = null;
        }
        if (calendar2.get(2) != calendar.get(2)) {
            dateState = DateView.DateState.HIDDEN;
        } else {
            CalendarDateRangeManagerNew calendarDateRangeManagerNew2 = this.m;
            if (calendarDateRangeManagerNew2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("dateRangeCalendarManager");
                calendarDateRangeManagerNew2 = null;
            }
            CalendarDateRangeManagerNew.DateSelectionState checkDateRange = calendarDateRangeManagerNew2.checkDateRange(calendar);
            if (checkDateRange == CalendarDateRangeManagerNew.DateSelectionState.START_DATE) {
                dateState = DateView.DateState.START;
            } else if (checkDateRange == CalendarDateRangeManagerNew.DateSelectionState.LAST_DATE) {
                dateState = DateView.DateState.END;
            } else if (checkDateRange == CalendarDateRangeManagerNew.DateSelectionState.START_END_SAME) {
                dateState = DateView.DateState.START_END_SAME;
            } else if (checkDateRange == CalendarDateRangeManagerNew.DateSelectionState.IN_SELECTED_RANGE) {
                dateState = DateView.DateState.MIDDLE;
            } else {
                CalendarDateRangeManagerNew calendarDateRangeManagerNew3 = this.m;
                if (calendarDateRangeManagerNew3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("dateRangeCalendarManager");
                } else {
                    calendarDateRangeManagerNew = calendarDateRangeManagerNew3;
                }
                if (calendarDateRangeManagerNew.isSelectableDate(calendar)) {
                    dateState = DateView.DateState.SELECTABLE;
                } else {
                    dateState = DateView.DateState.DISABLE;
                }
            }
        }
        customDateViewNew.updateDateBackground(dateState);
        customDateViewNew.setTag(Long.valueOf(DateView.Companion.getContainerKey(calendar)));
    }

    public final void c(Context context) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.layout_calendar_month_new, (ViewGroup) this, true);
        Intrinsics.checkNotNull(inflate, "null cannot be cast to non-null type android.widget.LinearLayout");
        LinearLayout linearLayout = (LinearLayout) inflate;
        View findViewById = linearLayout.findViewById(R.id.llDaysContainer);
        Intrinsics.checkNotNullExpressionValue(findViewById, "mainView.findViewById(R.id.llDaysContainer)");
        this.h = (LinearLayout) findViewById;
        View findViewById2 = linearLayout.findViewById(R.id.llTitleWeekContainer);
        Intrinsics.checkNotNullExpressionValue(findViewById2, "mainView.findViewById(R.id.llTitleWeekContainer)");
        this.i = (LinearLayout) findViewById2;
    }

    public final void d() {
        LinearLayout linearLayout = this.i;
        if (linearLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("llTitleWeekContainer");
            linearLayout = null;
        }
        int childCount = linearLayout.getChildCount();
        for (int i = 0; i < childCount; i++) {
            LinearLayout linearLayout2 = this.i;
            if (linearLayout2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("llTitleWeekContainer");
                linearLayout2 = null;
            }
            View childAt = linearLayout2.getChildAt(i);
            Intrinsics.checkNotNull(childAt, "null cannot be cast to non-null type com.coveiot.android.theme.calendardaterangepicker.customviews.CalendarCustomTextView");
            CalendarCustomTextView calendarCustomTextView = (CalendarCustomTextView) childAt;
            CalendarStyleAttributes calendarStyleAttributes = this.k;
            if (calendarStyleAttributes == null) {
                Intrinsics.throwUninitializedPropertyAccessException("calendarStyleAttr");
                calendarStyleAttributes = null;
            }
            calendarCustomTextView.setTypeface(calendarStyleAttributes.getFonts());
            CalendarStyleAttributes calendarStyleAttributes2 = this.k;
            if (calendarStyleAttributes2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("calendarStyleAttr");
                calendarStyleAttributes2 = null;
            }
            calendarCustomTextView.setTextSize(0, calendarStyleAttributes2.getTextSizeWeek());
            CalendarStyleAttributes calendarStyleAttributes3 = this.k;
            if (calendarStyleAttributes3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("calendarStyleAttr");
                calendarStyleAttributes3 = null;
            }
            calendarCustomTextView.setTextColor(calendarStyleAttributes3.getWeekColor());
        }
    }

    public final void drawCalendarForMonth(@NotNull CalendarStyleAttributes calendarStyleAttr, @NotNull Calendar month, @NotNull CalendarDateRangeManagerNew dateRangeCalendarManager) {
        Intrinsics.checkNotNullParameter(calendarStyleAttr, "calendarStyleAttr");
        Intrinsics.checkNotNullParameter(month, "month");
        Intrinsics.checkNotNullParameter(dateRangeCalendarManager, "dateRangeCalendarManager");
        this.k = calendarStyleAttr;
        Object clone = month.clone();
        Intrinsics.checkNotNull(clone, "null cannot be cast to non-null type java.util.Calendar");
        Calendar calendar = (Calendar) clone;
        this.j = calendar;
        this.m = dateRangeCalendarManager;
        if (calendar == null) {
            Intrinsics.throwUninitializedPropertyAccessException("currentCalendarMonth");
            calendar = null;
        }
        a(calendar);
    }

    public final void resetAllSelectedViews() {
        CalendarDateRangeManagerNew calendarDateRangeManagerNew = this.m;
        Calendar calendar = null;
        if (calendarDateRangeManagerNew == null) {
            Intrinsics.throwUninitializedPropertyAccessException("dateRangeCalendarManager");
            calendarDateRangeManagerNew = null;
        }
        calendarDateRangeManagerNew.resetSelectedDateRange();
        Calendar calendar2 = this.j;
        if (calendar2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("currentCalendarMonth");
        } else {
            calendar = calendar2;
        }
        a(calendar);
    }

    public final void setCalendarListener(@Nullable CalendarListenerNew calendarListenerNew) {
        this.l = calendarListenerNew;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DateRangeMonthViewNew(@NotNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        Intrinsics.checkNotNullParameter(context, "context");
        this._$_findViewCache = new LinkedHashMap();
        this.n = new DateView.OnDateClickListener() { // from class: com.coveiot.android.theme.calendardaterangepicker.customviews.DateRangeMonthViewNew$mOnDateClickListener$1
            @Override // com.coveiot.android.theme.calendardaterangepicker.customviews.DateView.OnDateClickListener
            public void onDateClicked(@NotNull View view, @NotNull final Calendar selectedDate) {
                CalendarStyleAttributes calendarStyleAttributes;
                CalendarStyleAttributes calendarStyleAttributes2;
                Intrinsics.checkNotNullParameter(view, "view");
                Intrinsics.checkNotNullParameter(selectedDate, "selectedDate");
                calendarStyleAttributes = DateRangeMonthViewNew.this.k;
                CalendarStyleAttributes calendarStyleAttributes3 = null;
                if (calendarStyleAttributes == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("calendarStyleAttr");
                    calendarStyleAttributes = null;
                }
                if (calendarStyleAttributes.isEditable()) {
                    calendarStyleAttributes2 = DateRangeMonthViewNew.this.k;
                    if (calendarStyleAttributes2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("calendarStyleAttr");
                    } else {
                        calendarStyleAttributes3 = calendarStyleAttributes2;
                    }
                    if (!calendarStyleAttributes3.isShouldEnabledTime()) {
                        DateRangeMonthViewNew.this.setSelectedDate(selectedDate);
                        return;
                    }
                    Context context2 = DateRangeMonthViewNew.this.getContext();
                    Intrinsics.checkNotNullExpressionValue(context2, "context");
                    String string = DateRangeMonthViewNew.this.getContext().getString(R.string.select_time);
                    Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.select_time)");
                    final DateRangeMonthViewNew dateRangeMonthViewNew = DateRangeMonthViewNew.this;
                    new AwesomeTimePickerDialog(context2, string, new AwesomeTimePickerDialog.TimePickerCallback() { // from class: com.coveiot.android.theme.calendardaterangepicker.customviews.DateRangeMonthViewNew$mOnDateClickListener$1$onDateClicked$awesomeTimePickerDialog$1
                        @Override // com.coveiot.android.theme.calendardaterangepicker.timepicker.AwesomeTimePickerDialog.TimePickerCallback
                        public void onCancel() {
                            dateRangeMonthViewNew.resetAllSelectedViews();
                        }

                        @Override // com.coveiot.android.theme.calendardaterangepicker.timepicker.AwesomeTimePickerDialog.TimePickerCallback
                        public void onTimeSelected(int i, int i2) {
                            selectedDate.set(10, i);
                            selectedDate.set(12, i2);
                            dateRangeMonthViewNew.setSelectedDate(selectedDate);
                        }
                    }).showDialog();
                }
            }
        };
        c(context);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DateRangeMonthViewNew(@NotNull Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkNotNullParameter(context, "context");
        this._$_findViewCache = new LinkedHashMap();
        this.n = new DateView.OnDateClickListener() { // from class: com.coveiot.android.theme.calendardaterangepicker.customviews.DateRangeMonthViewNew$mOnDateClickListener$1
            @Override // com.coveiot.android.theme.calendardaterangepicker.customviews.DateView.OnDateClickListener
            public void onDateClicked(@NotNull View view, @NotNull final Calendar selectedDate) {
                CalendarStyleAttributes calendarStyleAttributes;
                CalendarStyleAttributes calendarStyleAttributes2;
                Intrinsics.checkNotNullParameter(view, "view");
                Intrinsics.checkNotNullParameter(selectedDate, "selectedDate");
                calendarStyleAttributes = DateRangeMonthViewNew.this.k;
                CalendarStyleAttributes calendarStyleAttributes3 = null;
                if (calendarStyleAttributes == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("calendarStyleAttr");
                    calendarStyleAttributes = null;
                }
                if (calendarStyleAttributes.isEditable()) {
                    calendarStyleAttributes2 = DateRangeMonthViewNew.this.k;
                    if (calendarStyleAttributes2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("calendarStyleAttr");
                    } else {
                        calendarStyleAttributes3 = calendarStyleAttributes2;
                    }
                    if (!calendarStyleAttributes3.isShouldEnabledTime()) {
                        DateRangeMonthViewNew.this.setSelectedDate(selectedDate);
                        return;
                    }
                    Context context2 = DateRangeMonthViewNew.this.getContext();
                    Intrinsics.checkNotNullExpressionValue(context2, "context");
                    String string = DateRangeMonthViewNew.this.getContext().getString(R.string.select_time);
                    Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.select_time)");
                    final DateRangeMonthViewNew dateRangeMonthViewNew = DateRangeMonthViewNew.this;
                    new AwesomeTimePickerDialog(context2, string, new AwesomeTimePickerDialog.TimePickerCallback() { // from class: com.coveiot.android.theme.calendardaterangepicker.customviews.DateRangeMonthViewNew$mOnDateClickListener$1$onDateClicked$awesomeTimePickerDialog$1
                        @Override // com.coveiot.android.theme.calendardaterangepicker.timepicker.AwesomeTimePickerDialog.TimePickerCallback
                        public void onCancel() {
                            dateRangeMonthViewNew.resetAllSelectedViews();
                        }

                        @Override // com.coveiot.android.theme.calendardaterangepicker.timepicker.AwesomeTimePickerDialog.TimePickerCallback
                        public void onTimeSelected(int i2, int i22) {
                            selectedDate.set(10, i2);
                            selectedDate.set(12, i22);
                            dateRangeMonthViewNew.setSelectedDate(selectedDate);
                        }
                    }).showDialog();
                }
            }
        };
        c(context);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    @TargetApi(21)
    public DateRangeMonthViewNew(@NotNull Context context, @Nullable AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        Intrinsics.checkNotNullParameter(context, "context");
        this._$_findViewCache = new LinkedHashMap();
        this.n = new DateView.OnDateClickListener() { // from class: com.coveiot.android.theme.calendardaterangepicker.customviews.DateRangeMonthViewNew$mOnDateClickListener$1
            @Override // com.coveiot.android.theme.calendardaterangepicker.customviews.DateView.OnDateClickListener
            public void onDateClicked(@NotNull View view, @NotNull final Calendar selectedDate) {
                CalendarStyleAttributes calendarStyleAttributes;
                CalendarStyleAttributes calendarStyleAttributes2;
                Intrinsics.checkNotNullParameter(view, "view");
                Intrinsics.checkNotNullParameter(selectedDate, "selectedDate");
                calendarStyleAttributes = DateRangeMonthViewNew.this.k;
                CalendarStyleAttributes calendarStyleAttributes3 = null;
                if (calendarStyleAttributes == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("calendarStyleAttr");
                    calendarStyleAttributes = null;
                }
                if (calendarStyleAttributes.isEditable()) {
                    calendarStyleAttributes2 = DateRangeMonthViewNew.this.k;
                    if (calendarStyleAttributes2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("calendarStyleAttr");
                    } else {
                        calendarStyleAttributes3 = calendarStyleAttributes2;
                    }
                    if (!calendarStyleAttributes3.isShouldEnabledTime()) {
                        DateRangeMonthViewNew.this.setSelectedDate(selectedDate);
                        return;
                    }
                    Context context2 = DateRangeMonthViewNew.this.getContext();
                    Intrinsics.checkNotNullExpressionValue(context2, "context");
                    String string = DateRangeMonthViewNew.this.getContext().getString(R.string.select_time);
                    Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.select_time)");
                    final DateRangeMonthViewNew dateRangeMonthViewNew = DateRangeMonthViewNew.this;
                    new AwesomeTimePickerDialog(context2, string, new AwesomeTimePickerDialog.TimePickerCallback() { // from class: com.coveiot.android.theme.calendardaterangepicker.customviews.DateRangeMonthViewNew$mOnDateClickListener$1$onDateClicked$awesomeTimePickerDialog$1
                        @Override // com.coveiot.android.theme.calendardaterangepicker.timepicker.AwesomeTimePickerDialog.TimePickerCallback
                        public void onCancel() {
                            dateRangeMonthViewNew.resetAllSelectedViews();
                        }

                        @Override // com.coveiot.android.theme.calendardaterangepicker.timepicker.AwesomeTimePickerDialog.TimePickerCallback
                        public void onTimeSelected(int i22, int i222) {
                            selectedDate.set(10, i22);
                            selectedDate.set(12, i222);
                            dateRangeMonthViewNew.setSelectedDate(selectedDate);
                        }
                    }).showDialog();
                }
            }
        };
        c(context);
    }
}
