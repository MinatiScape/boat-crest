package com.coveiot.android.femalewellness.wellnesscalendar;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.Toast;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import com.coveiot.android.femalewellness.R;
import com.coveiot.android.femalewellness.wellnesscalendar.datepicker.customviews.DateRangeCalendarView;
import com.coveiot.android.femalewellness.wellnesscalendar.model.WomenWellnessDetails;
import com.jstyle.blesdk1860.constant.BleConst;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
/* loaded from: classes4.dex */
public class DatePickerFragment extends Fragment {
    public DateRangeCalendarView h;
    public int i;
    public OnDateItemClickListener onDateItemClickListener;

    /* loaded from: classes4.dex */
    public interface OnDateItemClickListener {
        void isInPeriodOvulationSlot(Calendar calendar, Calendar calendar2, Calendar calendar3);

        void onDateClick(Calendar calendar);
    }

    /* loaded from: classes4.dex */
    public class a implements DateRangeCalendarView.CalendarListener {
        public a() {
        }

        @Override // com.coveiot.android.femalewellness.wellnesscalendar.datepicker.customviews.DateRangeCalendarView.CalendarListener
        public void onDateRangeSelected(Calendar calendar, Calendar calendar2) {
        }

        @Override // com.coveiot.android.femalewellness.wellnesscalendar.datepicker.customviews.DateRangeCalendarView.CalendarListener
        public void onFirstDateSelected(Calendar calendar) {
            Log.i("selected Date frag", "" + calendar.getTime());
            if (calendar.getTime().getDate() <= Calendar.getInstance().get(5) && calendar.getTime().getMonth() <= Calendar.getInstance().get(2) && calendar.getWeekYear() <= Calendar.getInstance().get(1)) {
                DatePickerFragment.this.onDateItemClickListener.onDateClick(calendar);
                return;
            }
            DatePickerFragment.this.onDateItemClickListener.onDateClick(null);
            Toast.makeText(DatePickerFragment.this.requireContext(), "Cannot select future date", 0).show();
        }
    }

    public DatePickerFragment() {
        new SimpleDateFormat("dd MMM, yyyy");
        new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        this.i = 1000;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_datepicker, viewGroup, false);
        this.h = (DateRangeCalendarView) inflate.findViewById(R.id.calendar);
        WomenWellnessDetails womenWellnessDetails = new WomenWellnessDetails();
        womenWellnessDetails.setmUserSetPeriodYear(getArguments().getString("WOMEN_WELLNESS_LAST_MENSTRUAL_YEAR"));
        if (Integer.parseInt(getArguments().getString("WOMEN_WELLNESS_LAST_MENSTRUAL_MONTH")) < 10) {
            womenWellnessDetails.setmUserSetPeriodMonth(BleConst.GetDeviceTime + getArguments().getString("WOMEN_WELLNESS_LAST_MENSTRUAL_MONTH"));
        } else {
            womenWellnessDetails.setmUserSetPeriodMonth(getArguments().getString("WOMEN_WELLNESS_LAST_MENSTRUAL_MONTH"));
        }
        if (Integer.parseInt(getArguments().getString("WOMEN_WELLNESS_LAST_MENSTRUAL_DATE")) < 10) {
            womenWellnessDetails.setmUserSetPeriodDay(BleConst.GetDeviceTime + getArguments().getString("WOMEN_WELLNESS_LAST_MENSTRUAL_DATE"));
        } else {
            womenWellnessDetails.setmUserSetPeriodDay(getArguments().getString("WOMEN_WELLNESS_LAST_MENSTRUAL_DATE"));
        }
        womenWellnessDetails.setmMenstruationCycleLength(getArguments().getInt("WOMEN_WELLNESS_CYCLE_LENGTH"));
        womenWellnessDetails.setmMenstruationPeriodLength(getArguments().getInt("WOMEN_WELLNESS_PERIOD_LENGTH"));
        AppPreferenceManager.getInstance(getContext()).saveWomenWellnessSettings(womenWellnessDetails);
        AppPreferenceManager.getInstance(getContext()).setKeyFirstTime(Boolean.TRUE);
        new Constants(getContext());
        this.h.setSelectionType("WEEK");
        this.onDateItemClickListener = (OnDateItemClickListener) getContext();
        if (getTag().equals(Constants.MAIN_TAG)) {
            Calendar calendar = Calendar.getInstance();
            Calendar calendar2 = (Calendar) calendar.clone();
            calendar2.add(2, -12);
            Calendar calendar3 = (Calendar) calendar.clone();
            calendar3.add(2, 12);
            this.h.setVisibleMonthRange(calendar2, calendar3);
            Calendar calendar4 = Calendar.getInstance();
            calendar4.add(2, 0);
            ((Calendar) calendar4.clone()).add(5, 40);
        }
        this.onDateItemClickListener.onDateClick(Calendar.getInstance());
        this.h.setCalendarListener(new a());
        this.h.setNavLeftImage(ContextCompat.getDrawable(getContext(), R.drawable.ic_female_wellness_leftarrow_grey));
        this.h.setNavRightImage(ContextCompat.getDrawable(getContext(), R.drawable.ic_female_wellness_rightarrow_white));
        return inflate;
    }

    public void scaleView(View view, float f, float f2) {
        view.setVisibility(0);
        Animation scaleAnimation = new ScaleAnimation(1.0f, 1.0f, f, f2, 1, 0.0f, 1, 0.0f);
        scaleAnimation.setFillAfter(true);
        scaleAnimation.setInterpolator(new LinearInterpolator());
        scaleAnimation.setDuration(this.i);
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
        alphaAnimation.setDuration(this.i);
        alphaAnimation.setFillAfter(true);
        view.startAnimation(alphaAnimation);
        view.startAnimation(scaleAnimation);
    }
}
