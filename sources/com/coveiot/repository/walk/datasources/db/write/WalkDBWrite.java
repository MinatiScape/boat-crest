package com.coveiot.repository.walk.datasources.db.write;

import android.content.Context;
import com.coveiot.covedb.walk.entities.DailyWalkData;
import com.coveiot.covedb.walk.entities.HourlyWalkData;
import com.coveiot.repository.walk.datasources.db.WalkDBRepo;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes9.dex */
public class WalkDBWrite {
    public static WalkDBWrite b;

    /* renamed from: a  reason: collision with root package name */
    public WalkDBRepo f7464a;

    public WalkDBWrite(Context context) {
        this.f7464a = WalkDBRepo.getInstance(context);
    }

    public static WalkDBWrite getInstance(Context context) {
        if (b == null) {
            b = new WalkDBWrite(context);
        }
        return b;
    }

    public void insertDailyData(DailyWalkData dailyWalkData) {
        new InsertWalkDataAsyncTask(this.f7464a.mWalkDao).execute(dailyWalkData);
    }

    public void insertHourlyStepsData(List<HourlyWalkData> list) {
        new InsertWalkDataAsyncTask(this.f7464a.mWalkDao).execute(list);
    }

    public void updateDailyCalorie(int i, String str, String str2) {
        this.f7464a.mWalkDao.updateDailyCalorieValue(i, str, str2);
    }

    public void updateDailyDistance(int i, String str, String str2) {
        this.f7464a.mWalkDao.updateDailyDistanceValue(i, str, str2);
    }

    public void updateDailyTarget(int i, String str, String str2, String str3) {
        this.f7464a.mWalkDao.updateDailyTargetValue(i, str, str2, str3);
    }

    public void updateHourlyCalorieData(ArrayList<Float> arrayList, int i, String str, String str2, String str3, String str4) {
        this.f7464a.mWalkDao.updateHourlyCalorieValue(arrayList, i, str, str2, str3, str4);
    }

    public void updateHourlyDistanceData(ArrayList<Integer> arrayList, int i, String str, String str2, String str3, String str4) {
        this.f7464a.mWalkDao.updateHourlyDistanceValue(arrayList, i, str, str2, str3, str4);
    }
}
