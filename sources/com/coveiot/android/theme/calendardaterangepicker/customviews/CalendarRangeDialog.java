package com.coveiot.android.theme.calendardaterangepicker.customviews;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import androidx.fragment.app.DialogFragment;
import com.coveiot.android.theme.databinding.DialogCalendarRangeDateBinding;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Pair;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes7.dex */
public final class CalendarRangeDialog extends DialogFragment {
    @NotNull
    public Map<Integer, View> _$_findViewCache;
    public final boolean h;
    @NotNull
    public final Pair<Calendar, Calendar> i;
    @NotNull
    public final OnCalendarRangeSelector j;
    public DialogCalendarRangeDateBinding k;
    public Calendar l;
    public Calendar m;

    /* loaded from: classes7.dex */
    public interface OnCalendarRangeSelector {
        void onCalendarRangeSelected(@NotNull Calendar calendar, @NotNull Calendar calendar2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public CalendarRangeDialog(boolean z, @NotNull Pair<? extends Calendar, ? extends Calendar> mSelectedRange, @NotNull OnCalendarRangeSelector onCalendarRangeSelector) {
        Intrinsics.checkNotNullParameter(mSelectedRange, "mSelectedRange");
        Intrinsics.checkNotNullParameter(onCalendarRangeSelector, "onCalendarRangeSelector");
        this._$_findViewCache = new LinkedHashMap();
        this.h = z;
        this.i = mSelectedRange;
        this.j = onCalendarRangeSelector;
    }

    public static final void d(CalendarRangeDialog this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        OnCalendarRangeSelector onCalendarRangeSelector = this$0.j;
        Calendar calendar = this$0.l;
        Calendar calendar2 = null;
        if (calendar == null) {
            Intrinsics.throwUninitializedPropertyAccessException("start");
            calendar = null;
        }
        Calendar calendar3 = this$0.m;
        if (calendar3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("end");
        } else {
            calendar2 = calendar3;
        }
        onCalendarRangeSelector.onCalendarRangeSelected(calendar, calendar2);
        this$0.dismiss();
    }

    public static final void e(CalendarRangeDialog this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.dismiss();
    }

    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    @Nullable
    public View _$_findCachedViewById(int i) {
        View findViewById;
        Map<Integer, View> map = this._$_findViewCache;
        View view = map.get(Integer.valueOf(i));
        if (view == null) {
            View view2 = getView();
            if (view2 == null || (findViewById = view2.findViewById(i)) == null) {
                return null;
            }
            map.put(Integer.valueOf(i), findViewById);
            return findViewById;
        }
        return view;
    }

    public final DialogCalendarRangeDateBinding c() {
        DialogCalendarRangeDateBinding dialogCalendarRangeDateBinding = this.k;
        if (dialogCalendarRangeDateBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("_binding");
            return null;
        }
        return dialogCalendarRangeDateBinding;
    }

    @NotNull
    public final Pair<Calendar, Calendar> getMSelectedRange() {
        return this.i;
    }

    @NotNull
    public final OnCalendarRangeSelector getOnCalendarRangeSelector() {
        return this.j;
    }

    public final boolean isWeek() {
        return this.h;
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        DialogCalendarRangeDateBinding inflate = DialogCalendarRangeDateBinding.inflate(inflater, viewGroup, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(inflater,container,false)");
        this.k = inflate;
        return c().getRoot();
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onStart() {
        Window window;
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog == null || (window = dialog.getWindow()) == null) {
            return;
        }
        window.setLayout(-1, -2);
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        c().calendar.setSelectedDateRange(this.i.getFirst(), this.i.getSecond());
        this.l = this.i.getFirst();
        this.m = this.i.getSecond();
        c().calendar.setCalendarListener(new CalendarListenerNew() { // from class: com.coveiot.android.theme.calendardaterangepicker.customviews.CalendarRangeDialog$onViewCreated$1
            @Override // com.coveiot.android.theme.calendardaterangepicker.customviews.CalendarListenerNew
            public void onDateRangeSelected(@NotNull Calendar startDate, @NotNull Calendar endDate) {
                Intrinsics.checkNotNullParameter(startDate, "startDate");
                Intrinsics.checkNotNullParameter(endDate, "endDate");
            }

            @Override // com.coveiot.android.theme.calendardaterangepicker.customviews.CalendarListenerNew
            public void onFirstDateSelected(@NotNull Calendar startDate) {
                DialogCalendarRangeDateBinding c;
                DialogCalendarRangeDateBinding c2;
                Intrinsics.checkNotNullParameter(startDate, "startDate");
                if (CalendarRangeDialog.this.isWeek()) {
                    Object clone = startDate.clone();
                    Intrinsics.checkNotNull(clone, "null cannot be cast to non-null type java.util.Calendar");
                    Calendar calendar = (Calendar) clone;
                    calendar.add(5, 6);
                    c2 = CalendarRangeDialog.this.c();
                    c2.calendar.setSelectedDateRange(startDate, calendar);
                    CalendarRangeDialog.this.l = startDate;
                    CalendarRangeDialog.this.m = calendar;
                    return;
                }
                Object clone2 = startDate.clone();
                Intrinsics.checkNotNull(clone2, "null cannot be cast to non-null type java.util.Calendar");
                Calendar calendar2 = (Calendar) clone2;
                calendar2.add(5, 30);
                c = CalendarRangeDialog.this.c();
                c.calendar.setSelectedDateRange(startDate, calendar2);
                CalendarRangeDialog.this.l = startDate;
                CalendarRangeDialog.this.m = calendar2;
            }
        });
        c().okPopup.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.theme.calendardaterangepicker.customviews.e
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                CalendarRangeDialog.d(CalendarRangeDialog.this, view2);
            }
        });
        c().cancelPopup.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.theme.calendardaterangepicker.customviews.d
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                CalendarRangeDialog.e(CalendarRangeDialog.this, view2);
            }
        });
    }
}
