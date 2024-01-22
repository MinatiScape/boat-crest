package com.coveiot.covepreferences;

import android.content.Context;
import android.content.SharedPreferences;
import com.coveiot.covepreferences.data.AppReferalData;
import com.google.gson.Gson;
/* loaded from: classes8.dex */
public class FitnessSocialManager {
    public static FitnessSocialManager e;

    /* renamed from: a  reason: collision with root package name */
    public SharedPreferences f7002a;
    public SharedPreferences.Editor b;
    public int c = 0;
    public Gson d = new Gson();

    public FitnessSocialManager(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("FHSCore", this.c);
        this.f7002a = sharedPreferences;
        this.b = sharedPreferences.edit();
    }

    public static FitnessSocialManager getInstance(Context context) {
        if (e == null) {
            e = new FitnessSocialManager(context);
        }
        return e;
    }

    public void clearPreferences(Context context) {
        for (String str : this.f7002a.getAll().keySet()) {
            remove(context, str);
        }
    }

    public AppReferalData getFitnessBuddiesAppReferalData() {
        String string = this.f7002a.getString("key_fitness_buddies_referral_data", "");
        if (string.length() > 0) {
            return (AppReferalData) this.d.fromJson(string, (Class<Object>) AppReferalData.class);
        }
        return null;
    }

    public void remove(Context context, String str) {
        if (this.f7002a.contains(str)) {
            this.b.remove(str);
            this.b.commit();
        }
    }

    public void saveFitnessBuddiesAppReferalData(AppReferalData appReferalData) {
        this.b.putString("key_fitness_buddies_referral_data", this.d.toJson(appReferalData)).commit();
    }
}
