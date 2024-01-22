package com.coveiot.android.fitnesschallenges.preference;

import android.content.Context;
import android.content.SharedPreferences;
import com.coveiot.coveaccess.fitnesschallenge.model.GetChallengeStartNEndDateRes;
import com.google.gson.Gson;
import java.util.ArrayList;
/* loaded from: classes2.dex */
public class FitnessChallengeSessionManager {
    public static FitnessChallengeSessionManager d;

    /* renamed from: a  reason: collision with root package name */
    public SharedPreferences f4531a;
    public SharedPreferences.Editor b;
    public int c = 0;

    public FitnessChallengeSessionManager(Context context) {
        new ArrayList();
        SharedPreferences sharedPreferences = context.getSharedPreferences("fitness_challenge", this.c);
        this.f4531a = sharedPreferences;
        this.b = sharedPreferences.edit();
    }

    public static FitnessChallengeSessionManager getInstance(Context context) {
        if (d == null) {
            d = new FitnessChallengeSessionManager(context);
        }
        return d;
    }

    public void clearPreferences(Context context) {
        for (String str : this.f4531a.getAll().keySet()) {
            remove(context, str);
        }
    }

    public GetChallengeStartNEndDateRes getFitnessChallengeActiveDateRange() {
        GetChallengeStartNEndDateRes getChallengeStartNEndDateRes = (GetChallengeStartNEndDateRes) new Gson().fromJson(this.f4531a.getString("key_fitness_challenge_active_date_range", null), (Class<Object>) GetChallengeStartNEndDateRes.class);
        return getChallengeStartNEndDateRes == null ? new GetChallengeStartNEndDateRes() : getChallengeStartNEndDateRes;
    }

    public String getFitnessChallengeLastSyncDate() {
        return this.f4531a.getString("key_fitness_challenge_last_sync_date", null);
    }

    public FitnessChallengeRemoteConfiguration getFitnessChallengeRemoteConfig() {
        FitnessChallengeRemoteConfiguration fitnessChallengeRemoteConfiguration = (FitnessChallengeRemoteConfiguration) new Gson().fromJson(this.f4531a.getString("key_fitness_challenge_remote_config", null), (Class<Object>) FitnessChallengeRemoteConfiguration.class);
        return fitnessChallengeRemoteConfiguration == null ? new FitnessChallengeRemoteConfiguration() : fitnessChallengeRemoteConfiguration;
    }

    public void remove(Context context, String str) {
        if (this.f4531a.contains(str)) {
            this.b.remove(str);
            this.b.commit();
        }
    }

    public void saveFitnessChallengeActiveDateRange(GetChallengeStartNEndDateRes getChallengeStartNEndDateRes) {
        this.b.putString("key_fitness_challenge_active_date_range", new Gson().toJson(getChallengeStartNEndDateRes));
        this.b.commit();
    }

    public void saveFitnessChallengeLastSyncDate(String str) {
        this.b.putString("key_fitness_challenge_last_sync_date", str);
        this.b.commit();
    }

    public void saveFitnessChallengeRemoteConfig(FitnessChallengeRemoteConfiguration fitnessChallengeRemoteConfiguration) {
        this.b.putString("key_fitness_challenge_remote_config", new Gson().toJson(fitnessChallengeRemoteConfiguration));
        this.b.commit();
    }
}
