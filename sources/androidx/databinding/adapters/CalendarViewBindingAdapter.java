package androidx.databinding.adapters;

import android.widget.CalendarView;
import androidx.annotation.RestrictTo;
import androidx.databinding.BindingAdapter;
import androidx.databinding.InverseBindingListener;
import androidx.databinding.InverseBindingMethod;
import androidx.databinding.InverseBindingMethods;
@InverseBindingMethods({@InverseBindingMethod(attribute = "android:date", type = CalendarView.class)})
@RestrictTo({RestrictTo.Scope.LIBRARY})
/* loaded from: classes.dex */
public class CalendarViewBindingAdapter {

    /* loaded from: classes.dex */
    public class a implements CalendarView.OnDateChangeListener {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CalendarView.OnDateChangeListener f1222a;
        public final /* synthetic */ InverseBindingListener b;

        public a(CalendarView.OnDateChangeListener onDateChangeListener, InverseBindingListener inverseBindingListener) {
            this.f1222a = onDateChangeListener;
            this.b = inverseBindingListener;
        }

        @Override // android.widget.CalendarView.OnDateChangeListener
        public void onSelectedDayChange(CalendarView calendarView, int i, int i2, int i3) {
            CalendarView.OnDateChangeListener onDateChangeListener = this.f1222a;
            if (onDateChangeListener != null) {
                onDateChangeListener.onSelectedDayChange(calendarView, i, i2, i3);
            }
            this.b.onChange();
        }
    }

    @BindingAdapter({"android:date"})
    public static void setDate(CalendarView calendarView, long j) {
        if (calendarView.getDate() != j) {
            calendarView.setDate(j);
        }
    }

    @BindingAdapter(requireAll = false, value = {"android:onSelectedDayChange", "android:dateAttrChanged"})
    public static void setListeners(CalendarView calendarView, CalendarView.OnDateChangeListener onDateChangeListener, InverseBindingListener inverseBindingListener) {
        if (inverseBindingListener == null) {
            calendarView.setOnDateChangeListener(onDateChangeListener);
        } else {
            calendarView.setOnDateChangeListener(new a(onDateChangeListener, inverseBindingListener));
        }
    }
}
