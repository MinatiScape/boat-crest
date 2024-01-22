package com.coveiot.android.femalewellness.wellnesscalendar;

import android.content.Context;
import android.content.SharedPreferences;
import com.coveiot.android.femalewellness.wellnesscalendar.model.WomenWellnessDetails;
import com.google.gson.Gson;
/* loaded from: classes4.dex */
public class AppPreferenceManager {
    public static AppPreferenceManager d;

    /* renamed from: a  reason: collision with root package name */
    public SharedPreferences f4402a;
    public SharedPreferences.Editor b;
    public int c = 0;

    public AppPreferenceManager(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("WELLNESS_SETTINGS", 0);
        this.f4402a = sharedPreferences;
        this.b = sharedPreferences.edit();
    }

    public static AppPreferenceManager getInstance(Context context) {
        if (d == null) {
            d = new AppPreferenceManager(context);
        }
        return d;
    }

    public void clearPreferences(Context context) {
        for (String str : this.f4402a.getAll().keySet()) {
            remove(context, str);
        }
    }

    public String getSelectedDate() {
        return this.f4402a.getString("SELECTED_DATE", "");
    }

    public String getSymptomDate() {
        return this.f4402a.getString("SYMPTOM_DATE", null);
    }

    public WomenWellnessDetails getWomenWellnessData() {
        return (WomenWellnessDetails) new Gson().fromJson(this.f4402a.getString(Constants.WOMEN_WELLNESS_DETAILS, null), (Class<Object>) WomenWellnessDetails.class);
    }

    public boolean isKeyFirstTime() {
        return this.f4402a.getBoolean("KEY_FIRST_TIME", false);
    }

    public void remove(Context context, String str) {
        if (this.f4402a.contains(str)) {
            this.b.remove(str);
            this.b.commit();
        }
    }

    public void saveWomenWellnessSettings(WomenWellnessDetails womenWellnessDetails) {
        this.b.putString(Constants.WOMEN_WELLNESS_DETAILS, new Gson().toJson(womenWellnessDetails));
        this.b.commit();
    }

    public void setKeyFirstTime(Boolean bool) {
        this.b.putBoolean("KEY_FIRST_TIME", bool.booleanValue());
        this.b.commit();
    }

    public void setSelectedDate(String str) {
        this.b.putString("SELECTED_DATE", str);
        this.b.commit();
    }

    public void setSymptomDate(String str) {
        this.b.putString("SYMPTOM_DATE", str);
        this.b.commit();
    }
}
