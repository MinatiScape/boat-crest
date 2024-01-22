package com.coveiot.android.theme.calendardaterangepicker.customviews;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.core.content.ContextCompat;
import com.coveiot.android.theme.R;
import com.coveiot.android.theme.calendardaterangepicker.customviews.DateView;
import com.coveiot.android.theme.calendardaterangepicker.models.CalendarStyleAttrImplNew;
import com.coveiot.android.theme.calendardaterangepicker.models.CalendarStyleAttributes;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes7.dex */
public final class CustomDateViewNew extends FrameLayout implements DateView {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    public Map<Integer, View> _$_findViewCache;
    @NotNull
    public final CalendarCustomTextView h;
    @NotNull
    public final View i;
    @NotNull
    public final SimpleDateFormat j;
    @NotNull
    public final PorterDuff.Mode k;
    @Nullable
    public DateView.OnDateClickListener l;
    @NotNull
    public DateView.DateState m;
    public final boolean n;
    @NotNull
    public final CalendarStyleAttrImplNew o;
    public float p;
    public int q;
    public int r;
    public int s;
    public int t;
    public int u;
    public int v;
    @NotNull
    public final View.OnClickListener w;

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
            int[] iArr = new int[DateView.DateState.values().length];
            try {
                iArr[DateView.DateState.START.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[DateView.DateState.END.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[DateView.DateState.START_END_SAME.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[DateView.DateState.HIDDEN.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[DateView.DateState.SELECTABLE.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr[DateView.DateState.DISABLE.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                iArr[DateView.DateState.MIDDLE.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public CustomDateViewNew(@NotNull Context context) {
        this(context, null, 0, 6, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public CustomDateViewNew(@NotNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public CustomDateViewNew(@NotNull Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkNotNullParameter(context, "context");
        this._$_findViewCache = new LinkedHashMap();
        this.j = new SimpleDateFormat("yyyyMMddHHmm", Locale.getDefault());
        this.k = PorterDuff.Mode.SRC_IN;
        this.n = getResources().getBoolean(R.bool.cdr_is_right_to_left);
        Object systemService = context.getSystemService("layout_inflater");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.view.LayoutInflater");
        ((LayoutInflater) systemService).inflate(R.layout.layout_calendar_day_new, (ViewGroup) this, true);
        View findViewById = findViewById(R.id.dayOfMonthText);
        Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(R.id.dayOfMonthText)");
        this.h = (CalendarCustomTextView) findViewById;
        View findViewById2 = findViewById(R.id.viewStrip);
        Intrinsics.checkNotNullExpressionValue(findViewById2, "findViewById(R.id.viewStrip)");
        this.i = findViewById2;
        this.m = DateView.DateState.SELECTABLE;
        if (!isInEditMode()) {
            setDateStyleAttributes(CalendarStyleAttrImplNew.Companion.getDefAttributes(context));
            updateDateBackground(this.m);
        }
        CalendarStyleAttrImplNew defAttributes = CalendarStyleAttrImplNew.Companion.getDefAttributes(context);
        this.o = defAttributes;
        this.p = defAttributes.getTextSizeDate();
        this.q = defAttributes.getDefaultDateColor();
        this.r = defAttributes.getDisableDateColor();
        this.s = defAttributes.getSelectedDateCircleColor();
        this.t = defAttributes.getSelectedDateColor();
        this.u = defAttributes.getRangeDateColor();
        this.v = defAttributes.getRangeStripColor();
        this.w = new View.OnClickListener() { // from class: com.coveiot.android.theme.calendardaterangepicker.customviews.f
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CustomDateViewNew.e(CustomDateViewNew.this, view);
            }
        };
    }

    public static final void e(CustomDateViewNew this$0, View it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Object tag = it.getTag();
        Intrinsics.checkNotNull(tag, "null cannot be cast to non-null type kotlin.Long");
        long longValue = ((Long) tag).longValue();
        if (this$0.l != null) {
            Calendar selectedCal = Calendar.getInstance();
            Date date = new Date();
            try {
                Date parse = this$0.j.parse(String.valueOf(longValue));
                Intrinsics.checkNotNullExpressionValue(parse, "simpleDateFormat.parse(key.toString())");
                date = parse;
            } catch (ParseException e) {
                e.printStackTrace();
            }
            selectedCal.setTime(date);
            DateView.OnDateClickListener onDateClickListener = this$0.l;
            if (onDateClickListener != null) {
                Intrinsics.checkNotNullExpressionValue(it, "it");
                Intrinsics.checkNotNullExpressionValue(selectedCal, "selectedCal");
                onDateClickListener.onDateClicked(it, selectedCal);
            }
        }
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

    public final void b() {
        this.h.setBackgroundColor(0);
        this.i.setBackgroundColor(0);
        setBackgroundColor(0);
        this.h.setTextColor(getDisableDateColor());
        setVisibility(0);
        setOnClickListener(null);
    }

    public final void c() {
        this.h.setBackgroundColor(0);
        this.i.setBackgroundColor(0);
        setBackgroundColor(0);
        this.h.setTextColor(getDefaultDateColor());
        setVisibility(0);
        setOnClickListener(this.w);
    }

    public final void d() {
        this.h.setText("");
        this.h.setBackgroundColor(0);
        this.i.setBackgroundColor(0);
        setBackgroundColor(0);
        setVisibility(4);
        setOnClickListener(null);
    }

    public final void f() {
        this.h.setBackgroundColor(0);
        Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable.range_bg);
        Intrinsics.checkNotNull(drawable);
        drawable.setColorFilter(new PorterDuffColorFilter(getStripColor(), this.k));
        this.i.setBackground(drawable);
        setBackgroundColor(0);
        this.h.setTextColor(getRangeDateColor());
        setVisibility(0);
        ViewGroup.LayoutParams layoutParams = this.i.getLayoutParams();
        Intrinsics.checkNotNull(layoutParams, "null cannot be cast to non-null type android.widget.FrameLayout.LayoutParams");
        FrameLayout.LayoutParams layoutParams2 = (FrameLayout.LayoutParams) layoutParams;
        layoutParams2.setMargins(0, 0, 0, 0);
        this.i.setLayoutParams(layoutParams2);
        setOnClickListener(this.w);
    }

    public final void g(DateView.DateState dateState) {
        int i = WhenMappings.$EnumSwitchMapping$0[dateState.ordinal()];
        if (i != 1) {
            if (i != 2) {
                if (i == 3) {
                    ViewGroup.LayoutParams layoutParams = this.i.getLayoutParams();
                    Intrinsics.checkNotNull(layoutParams, "null cannot be cast to non-null type android.widget.FrameLayout.LayoutParams");
                    FrameLayout.LayoutParams layoutParams2 = (FrameLayout.LayoutParams) layoutParams;
                    this.i.setBackgroundColor(0);
                    layoutParams2.setMargins(0, 0, 0, 0);
                    this.i.setLayoutParams(layoutParams2);
                } else {
                    throw new IllegalArgumentException(dateState + " is an invalid state.");
                }
            } else if (this.n) {
                h();
            } else {
                i();
            }
        } else if (this.n) {
            i();
        } else {
            h();
        }
        Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable.green_circle);
        Intrinsics.checkNotNull(drawable);
        drawable.setColorFilter(new PorterDuffColorFilter(getSelectedDateCircleColor(), this.k));
        this.h.setBackground(drawable);
        setBackgroundColor(0);
        this.h.setTextColor(getSelectedDateColor());
        setVisibility(0);
        setOnClickListener(this.w);
    }

    @Override // com.coveiot.android.theme.calendardaterangepicker.customviews.DateView
    public float getDateTextSize() {
        return this.p;
    }

    @Override // com.coveiot.android.theme.calendardaterangepicker.customviews.DateView
    public int getDefaultDateColor() {
        return this.q;
    }

    @Override // com.coveiot.android.theme.calendardaterangepicker.customviews.DateView
    public int getDisableDateColor() {
        return this.r;
    }

    @Override // com.coveiot.android.theme.calendardaterangepicker.customviews.DateView
    public int getRangeDateColor() {
        return this.u;
    }

    @Override // com.coveiot.android.theme.calendardaterangepicker.customviews.DateView
    public int getSelectedDateCircleColor() {
        return this.s;
    }

    @Override // com.coveiot.android.theme.calendardaterangepicker.customviews.DateView
    public int getSelectedDateColor() {
        return this.t;
    }

    @Override // com.coveiot.android.theme.calendardaterangepicker.customviews.DateView
    public int getStripColor() {
        return this.v;
    }

    public final void h() {
        ViewGroup.LayoutParams layoutParams = this.i.getLayoutParams();
        Intrinsics.checkNotNull(layoutParams, "null cannot be cast to non-null type android.widget.FrameLayout.LayoutParams");
        FrameLayout.LayoutParams layoutParams2 = (FrameLayout.LayoutParams) layoutParams;
        Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable.range_bg_left);
        Intrinsics.checkNotNull(drawable);
        drawable.setColorFilter(new PorterDuffColorFilter(getStripColor(), this.k));
        this.i.setBackground(drawable);
        layoutParams2.setMargins(20, 0, 0, 0);
        this.i.setLayoutParams(layoutParams2);
    }

    public final void i() {
        ViewGroup.LayoutParams layoutParams = this.i.getLayoutParams();
        Intrinsics.checkNotNull(layoutParams, "null cannot be cast to non-null type android.widget.FrameLayout.LayoutParams");
        FrameLayout.LayoutParams layoutParams2 = (FrameLayout.LayoutParams) layoutParams;
        Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable.range_bg_right);
        Intrinsics.checkNotNull(drawable);
        drawable.setColorFilter(new PorterDuffColorFilter(getStripColor(), this.k));
        this.i.setBackground(drawable);
        layoutParams2.setMargins(0, 0, 20, 0);
        this.i.setLayoutParams(layoutParams2);
    }

    @Override // com.coveiot.android.theme.calendardaterangepicker.customviews.DateView
    public void refreshLayout() {
        this.h.setTextSize(0, getDateTextSize());
    }

    @Override // com.coveiot.android.theme.calendardaterangepicker.customviews.DateView
    public void setDateClickListener(@NotNull DateView.OnDateClickListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.l = listener;
    }

    @Override // com.coveiot.android.theme.calendardaterangepicker.customviews.DateView
    public void setDateStyleAttributes(@NotNull CalendarStyleAttributes attr) {
        Intrinsics.checkNotNullParameter(attr, "attr");
        setDisableDateColor(attr.getDisableDateColor());
        setDefaultDateColor(attr.getDefaultDateColor());
        setSelectedDateCircleColor(attr.getSelectedDateCircleColor());
        setSelectedDateColor(attr.getSelectedDateColor());
        setStripColor(attr.getRangeStripColor());
        setRangeDateColor(attr.getRangeDateColor());
        this.h.setTextSize(attr.getTextSizeDate());
        refreshLayout();
    }

    @Override // com.coveiot.android.theme.calendardaterangepicker.customviews.DateView
    public void setDateTag(@NotNull Calendar date) {
        Intrinsics.checkNotNullParameter(date, "date");
        setTag(Long.valueOf(DateView.Companion.getContainerKey(date)));
    }

    @Override // com.coveiot.android.theme.calendardaterangepicker.customviews.DateView
    public void setDateText(@NotNull String date) {
        Intrinsics.checkNotNullParameter(date, "date");
        this.h.setText(date);
    }

    @Override // com.coveiot.android.theme.calendardaterangepicker.customviews.DateView
    public void setDateTextSize(float f) {
        this.p = f;
    }

    @Override // com.coveiot.android.theme.calendardaterangepicker.customviews.DateView
    public void setDefaultDateColor(int i) {
        this.q = i;
    }

    @Override // com.coveiot.android.theme.calendardaterangepicker.customviews.DateView
    public void setDisableDateColor(int i) {
        this.r = i;
    }

    @Override // com.coveiot.android.theme.calendardaterangepicker.customviews.DateView
    public void setRangeDateColor(int i) {
        this.u = i;
    }

    @Override // com.coveiot.android.theme.calendardaterangepicker.customviews.DateView
    public void setSelectedDateCircleColor(int i) {
        this.s = i;
    }

    @Override // com.coveiot.android.theme.calendardaterangepicker.customviews.DateView
    public void setSelectedDateColor(int i) {
        this.t = i;
    }

    @Override // com.coveiot.android.theme.calendardaterangepicker.customviews.DateView
    public void setStripColor(int i) {
        this.v = i;
    }

    @Override // com.coveiot.android.theme.calendardaterangepicker.customviews.DateView
    public void setTypeface(@NotNull Typeface typeface) {
        Intrinsics.checkNotNullParameter(typeface, "typeface");
        this.h.setTypeface(typeface);
    }

    @Override // com.coveiot.android.theme.calendardaterangepicker.customviews.DateView
    public void updateDateBackground(@NotNull DateView.DateState dateState) {
        Intrinsics.checkNotNullParameter(dateState, "dateState");
        this.m = dateState;
        switch (WhenMappings.$EnumSwitchMapping$0[dateState.ordinal()]) {
            case 1:
            case 2:
            case 3:
                g(dateState);
                return;
            case 4:
                d();
                return;
            case 5:
                c();
                return;
            case 6:
                b();
                return;
            case 7:
                f();
                return;
            default:
                return;
        }
    }

    public /* synthetic */ CustomDateViewNew(Context context, AttributeSet attributeSet, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? null : attributeSet, (i2 & 4) != 0 ? 0 : i);
    }
}
