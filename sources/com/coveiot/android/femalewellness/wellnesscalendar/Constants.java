package com.coveiot.android.femalewellness.wellnesscalendar;

import android.content.Context;
import android.content.SharedPreferences;
/* loaded from: classes4.dex */
public class Constants {
    public static String CALENDAR_TYPE = "type";
    public static String DATE = "DATE";
    public static String DATE_EXTRA = "date";
    public static String DATE_FORMATE = "type";
    public static final String DATE_PICKER_TAG = "date_picker";
    public static int END_YEAR = 2020;
    public static final String IS_MONTHS_SELECTION = "is_months_selection";
    public static final String MAIN_TAG = "main";
    public static String MONTH_EXTRA = "month";
    public static final String MONTH_PICKER_TAG = "month_picker";
    public static final String PREF_NAME = "calendar_pref";
    public static String SELECTED_MONTH = "selected_month";
    public static String SELECTED_WEEK_END_DATE = "SELECTED_WEEK_END_DATE";
    public static String SELECTED_WEEK_START_DATE = "SELECTED_WEEK_START_DATE";
    public static String SELECTED_YEAR = "selected_year";
    public static final String SELECT_WEEK = "SELECT_WEEK";
    public static int START_YEAR = 1900;
    public static final String WOMEN_WELLNESS_DETAILS = "women_wellness_details";
    public static String YEAR_EXTRA = "year";
    public static final String YEAR_PICKER_TAG = "year_picker";

    /* renamed from: a  reason: collision with root package name */
    public SharedPreferences f4403a;
    public SharedPreferences.Editor b;

    public Constants(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME, 0);
        this.f4403a = sharedPreferences;
        this.b = sharedPreferences.edit();
    }

    public String GetDateFormate() {
        return this.f4403a.getString(DATE_FORMATE, "DATE");
    }

    public String getCalendarType() {
        return this.f4403a.getString(CALENDAR_TYPE, "DATE");
    }

    public String getDate() {
        return this.f4403a.getString(DATE, "DATE");
    }

    public Boolean getIsMonthSelection() {
        return Boolean.valueOf(this.f4403a.getBoolean(IS_MONTHS_SELECTION, false));
    }

    public void setDate(String str) {
        this.b.putString(DATE, str).commit();
    }

    public void setDateFormate(String str) {
        this.b.putString(DATE_FORMATE, str).commit();
    }

    public void setDateType(String str) {
        this.b.putString(CALENDAR_TYPE, str).commit();
    }

    public void setIsMonthSelection(boolean z) {
        this.b.putBoolean(IS_MONTHS_SELECTION, z).commit();
    }
}
