package com.coveiot.android.femalewellness.wellnesscalendar.datepicker;

import android.content.Context;
import com.coveiot.android.femalewellness.Constants;
import com.coveiot.android.femalewellness.R;
import com.coveiot.android.femalewellness.Utils;
import com.coveiot.android.femalewellness.db.FemaleWellnessRepository;
import com.coveiot.android.femalewellness.wellnesscalendar.model.PhaseAndDateModel;
import com.coveiot.covepreferences.UserDataManager;
import com.coveiot.covepreferences.data.WomenWellnessData;
import com.coveiot.utils.utility.LogHelper;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
/* loaded from: classes4.dex */
public class DateRangeCalendarManager {
    public static SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyyMMdd", Locale.ENGLISH);
    public static DateRangeCalendarManager e = null;

    /* renamed from: a  reason: collision with root package name */
    public List<Calendar> f4406a;
    public List<Calendar> b;
    public List<Calendar> c;
    public List<Calendar> d;

    /* loaded from: classes4.dex */
    public @interface RANGE_TYPE {
        public static final int END_DAY = 6;
        public static final int LAST_DATE = 3;
        public static final int LAST_FIRST_DAY = 4;
        public static final int MIDDLE_DATE = 2;
        public static final int NOT_IN_RANGE = 0;
        public static final int START_DATE = 1;
        public static final int START_DAY = 5;
    }

    public DateRangeCalendarManager() {
        new ArrayList();
    }

    public static DateRangeCalendarManager getINSTANCE() {
        if (e == null) {
            e = new DateRangeCalendarManager();
        }
        return e;
    }

    public final int a(List<Calendar> list, Calendar calendar) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).compareTo(calendar) > 0) {
                return i;
            }
        }
        return -1;
    }

    public final int b(List<Calendar> list, Calendar calendar) {
        for (int size = list.size() - 1; size >= 0; size--) {
            if (list.get(size).compareTo(calendar) < 0) {
                return size;
            }
        }
        return -1;
    }

    public Calendar getOvulationDate(Calendar calendar, Context context) {
        long timeInMillis = calendar.getTimeInMillis();
        calendar.set(10, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        calendar.set(14, 0);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        WomenWellnessData womenWellnessData = UserDataManager.getInstance(context).getWomenWellnessData();
        if (womenWellnessData != null && womenWellnessData.getLastPeriodYear() != 0) {
            Calendar calendar2 = Calendar.getInstance();
            calendar2.set(5, womenWellnessData.getLastPeriodDay());
            calendar2.set(1, womenWellnessData.getLastPeriodYear());
            calendar2.set(2, womenWellnessData.getLastPeriodMonth() - 1);
            String format = simpleDateFormat.format(Long.valueOf(calendar2.getTimeInMillis()));
            String format2 = simpleDateFormat.format(Long.valueOf(calendar.getTimeInMillis()));
            List<String> fetchRecordedOvulationDates = FemaleWellnessRepository.Companion.getInstance(context).fetchRecordedOvulationDates(format2, format);
            if (fetchRecordedOvulationDates != null && fetchRecordedOvulationDates.contains(format2)) {
                return calendar;
            }
        }
        if (this.c != null) {
            for (int i = 0; i < this.c.size(); i++) {
                long timeInMillis2 = this.c.get(i).getTimeInMillis();
                long timeInMillis3 = this.d.get(i).getTimeInMillis();
                if (timeInMillis >= timeInMillis2 && timeInMillis < timeInMillis3) {
                    return this.c.get(i);
                }
            }
            return null;
        }
        return null;
    }

    public List<Calendar> getOvulationEndDate() {
        return this.d;
    }

    public List<Calendar> getOvulationStartDate() {
        return this.c;
    }

    public Calendar getPeriodDate(Calendar calendar, Context context) {
        long timeInMillis = calendar.getTimeInMillis();
        calendar.set(10, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        calendar.set(14, 0);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        WomenWellnessData womenWellnessData = UserDataManager.getInstance(context).getWomenWellnessData();
        if (womenWellnessData != null && womenWellnessData.getLastPeriodYear() != 0) {
            Calendar calendar2 = Calendar.getInstance();
            calendar2.set(5, womenWellnessData.getLastPeriodDay());
            calendar2.set(1, womenWellnessData.getLastPeriodYear());
            calendar2.set(2, womenWellnessData.getLastPeriodMonth() - 1);
            String format = simpleDateFormat.format(Long.valueOf(calendar2.getTimeInMillis()));
            String format2 = simpleDateFormat.format(Long.valueOf(calendar.getTimeInMillis()));
            List<String> fetchRecordedPeriodDates = FemaleWellnessRepository.Companion.getInstance(context).fetchRecordedPeriodDates(format2, format);
            if (fetchRecordedPeriodDates != null && fetchRecordedPeriodDates.contains(format2)) {
                return calendar;
            }
        }
        if (this.f4406a != null) {
            for (int i = 0; i < this.f4406a.size(); i++) {
                long timeInMillis2 = this.f4406a.get(i).getTimeInMillis();
                long timeInMillis3 = this.b.get(i).getTimeInMillis();
                if (timeInMillis >= timeInMillis2 && timeInMillis < timeInMillis3) {
                    return this.f4406a.get(i);
                }
            }
            return null;
        }
        return null;
    }

    public List<Calendar> getPeriodEndDate() {
        return this.b;
    }

    public List<Calendar> getPeriodStartDate() {
        return this.f4406a;
    }

    public String getSafePeriodTextAndDays(Context context, Calendar calendar) {
        if (calendar != null) {
            calendar.getTimeInMillis();
            calendar.set(11, 0);
            calendar.set(12, 0);
            calendar.set(13, 0);
            calendar.set(14, 0);
            WomenWellnessData womenWellnessData = UserDataManager.getInstance(context).getWomenWellnessData();
            if (womenWellnessData != null && womenWellnessData.getLastPeriodYear() != 0) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
                Calendar calendar2 = Calendar.getInstance();
                calendar2.setTimeZone(TimeZone.getDefault());
                calendar.setTimeZone(TimeZone.getDefault());
                calendar2.set(5, womenWellnessData.getLastPeriodDay());
                calendar2.set(1, womenWellnessData.getLastPeriodYear());
                calendar2.set(2, womenWellnessData.getLastPeriodMonth() - 1);
                calendar2.set(10, 0);
                calendar2.set(12, 0);
                calendar2.set(13, 0);
                calendar2.set(14, 0);
                String format = simpleDateFormat.format(Long.valueOf(calendar.getTimeInMillis()));
                if (calendar2.compareTo(calendar) > 0) {
                    FemaleWellnessRepository.Companion companion = FemaleWellnessRepository.Companion;
                    PhaseAndDateModel fetchPreviousPeriodOvulationDate = companion.getInstance(context).fetchPreviousPeriodOvulationDate(format);
                    PhaseAndDateModel fetchNextPeriodOvulationDate = companion.getInstance(context).fetchNextPeriodOvulationDate(format);
                    if (fetchNextPeriodOvulationDate != null && fetchPreviousPeriodOvulationDate != null) {
                        String date = fetchPreviousPeriodOvulationDate.getDate();
                        String date2 = fetchNextPeriodOvulationDate.getDate();
                        String phase = fetchNextPeriodOvulationDate.getPhase();
                        Date date3 = new Date();
                        Date date4 = new Date();
                        try {
                            date3 = simpleDateFormat.parse(date);
                            date4 = simpleDateFormat.parse(date2);
                        } catch (ParseException e2) {
                            e2.printStackTrace();
                        }
                        Utils.Companion companion2 = Utils.Companion;
                        int dayDifference = (int) companion2.getDayDifference(calendar.getTime(), date3);
                        int dayDifference2 = (int) companion2.getDayDifference(calendar.getTime(), date4);
                        if (phase.equalsIgnoreCase(Constants.PERIOD.getValue())) {
                            return dayDifference + ";" + dayDifference2 + "; " + context.getResources().getString(R.string.days_to_enter_into_Period);
                        }
                        return dayDifference + ";" + dayDifference2 + "; " + context.getResources().getString(R.string.days_to_enter_into_Ovulation);
                    }
                } else {
                    int b = b(this.f4406a, calendar);
                    int b2 = b(this.c, calendar);
                    if (b2 == -1 && b == -1) {
                        return null;
                    }
                    if (b == -1 && b2 >= 0) {
                        int a2 = a(this.f4406a, calendar);
                        Utils.Companion companion3 = Utils.Companion;
                        int dayDifference3 = (int) companion3.getDayDifference(calendar.getTime(), this.f4406a.get(a2).getTime());
                        return (((int) companion3.getDayDifference(calendar.getTime(), this.d.get(b2).getTime())) + 1) + ";" + dayDifference3 + "; " + context.getResources().getString(R.string.days_to_enter_into_Ovulation);
                    } else if (b >= 0 && b2 == -1) {
                        int a3 = a(this.c, calendar);
                        Utils.Companion companion4 = Utils.Companion;
                        return (((int) companion4.getDayDifference(calendar.getTime(), this.b.get(b).getTime())) + 1) + ";" + ((int) companion4.getDayDifference(calendar.getTime(), this.c.get(a3).getTime())) + "; " + context.getResources().getString(R.string.days_to_enter_into_Ovulation);
                    } else if (b >= 0 && b2 >= 0) {
                        if (this.f4406a.get(b).compareTo(this.c.get(b2)) > 0) {
                            int a4 = a(this.c, calendar);
                            Utils.Companion companion5 = Utils.Companion;
                            return (((int) companion5.getDayDifference(calendar.getTime(), this.b.get(b).getTime())) + 1) + ";" + ((int) companion5.getDayDifference(calendar.getTime(), this.c.get(a4).getTime())) + "; " + context.getResources().getString(R.string.days_to_enter_into_Ovulation);
                        } else if (this.f4406a.get(b).compareTo(this.c.get(b2)) < 0) {
                            int a5 = a(this.f4406a, calendar);
                            Utils.Companion companion6 = Utils.Companion;
                            int dayDifference4 = (int) companion6.getDayDifference(calendar.getTime(), this.f4406a.get(a5).getTime());
                            return (((int) companion6.getDayDifference(calendar.getTime(), this.d.get(b2).getTime())) + 1) + ";" + dayDifference4 + "; " + context.getResources().getString(R.string.days_to_enter_into_Period);
                        }
                    }
                }
            }
        }
        return null;
    }

    public boolean isBeforeCurrentLMD(Calendar calendar, Context context) {
        calendar.set(10, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        calendar.set(14, 0);
        WomenWellnessData womenWellnessData = UserDataManager.getInstance(context).getWomenWellnessData();
        if (womenWellnessData != null && womenWellnessData.getLastPeriodYear() != 0) {
            Calendar calendar2 = (Calendar) Calendar.getInstance().clone();
            calendar2.setTimeZone(TimeZone.getDefault());
            calendar.setTimeZone(TimeZone.getDefault());
            calendar2.set(5, womenWellnessData.getLastPeriodDay());
            calendar2.set(1, womenWellnessData.getLastPeriodYear());
            calendar2.set(2, womenWellnessData.getLastPeriodMonth() - 1);
            calendar2.set(11, 0);
            calendar2.set(12, 0);
            calendar2.set(13, 0);
            calendar2.set(14, 0);
            LogHelper.d("Calndar date ", calendar2.getTime().toString());
            LogHelper.d("selecetd  date ", calendar.getTime().toString());
            if (calendar.compareTo(calendar2) < 0) {
                return true;
            }
        }
        return false;
    }

    public boolean isOvulationDate(Calendar calendar, Context context) {
        calendar.getTimeInMillis();
        calendar.set(10, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        calendar.set(14, 0);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        WomenWellnessData womenWellnessData = UserDataManager.getInstance(context).getWomenWellnessData();
        if (womenWellnessData != null && womenWellnessData.getLastPeriodYear() != 0) {
            Calendar calendar2 = Calendar.getInstance();
            calendar2.set(5, womenWellnessData.getLastPeriodDay());
            calendar2.set(1, womenWellnessData.getLastPeriodYear());
            calendar2.set(2, womenWellnessData.getLastPeriodMonth() - 1);
            List<String> fetchRecordedOvulationDates = FemaleWellnessRepository.Companion.getInstance(context).fetchRecordedOvulationDates(simpleDateFormat.format(Long.valueOf(calendar.getTimeInMillis())), simpleDateFormat.format(Long.valueOf(calendar2.getTimeInMillis())));
            if (fetchRecordedOvulationDates != null && fetchRecordedOvulationDates.size() > 0) {
                return true;
            }
        }
        if (this.c != null) {
            for (int i = 0; i < this.c.size(); i++) {
                Calendar calendar3 = this.d.get(i);
                if (calendar.compareTo(this.c.get(i)) >= 0 && calendar.compareTo(calendar3) < 0) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    public boolean isPeriodDate(Calendar calendar, Context context) {
        calendar.getTimeInMillis();
        calendar.set(10, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        calendar.set(14, 0);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        WomenWellnessData womenWellnessData = UserDataManager.getInstance(context).getWomenWellnessData();
        if (womenWellnessData != null && womenWellnessData.getLastPeriodYear() != 0) {
            Calendar calendar2 = Calendar.getInstance();
            calendar2.set(5, womenWellnessData.getLastPeriodDay());
            calendar2.set(1, womenWellnessData.getLastPeriodYear());
            calendar2.set(2, womenWellnessData.getLastPeriodMonth() - 1);
            List<String> fetchRecordedPeriodDates = FemaleWellnessRepository.Companion.getInstance(context).fetchRecordedPeriodDates(simpleDateFormat.format(Long.valueOf(calendar.getTimeInMillis())), simpleDateFormat.format(Long.valueOf(calendar2.getTimeInMillis())));
            if (fetchRecordedPeriodDates != null && fetchRecordedPeriodDates.size() > 0) {
                return true;
            }
        }
        if (this.f4406a != null) {
            for (int i = 0; i < this.f4406a.size(); i++) {
                Calendar calendar3 = this.b.get(i);
                if (calendar.compareTo(this.f4406a.get(i)) >= 0 && calendar.compareTo(calendar3) < 0) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    public void setOvulationEndDate(List<Calendar> list) {
        this.d = list;
    }

    public void setOvulationStartDate(List<Calendar> list) {
        this.c = list;
    }

    public void setPeriodEndDate(List<Calendar> list) {
        this.b = list;
    }

    public void setPeriodStartDate(List<Calendar> list) {
        this.f4406a = list;
    }
}
