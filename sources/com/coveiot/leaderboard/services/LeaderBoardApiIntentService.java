package com.coveiot.leaderboard.services;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import androidx.annotation.Nullable;
import androidx.exifinterface.media.ExifInterface;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.leaderboard.CoveLeaderboardApi;
import com.coveiot.coveaccess.leaderboard.model.AllBadgesModel;
import com.coveiot.coveaccess.leaderboard.model.MyBadgesModel;
import com.coveiot.coveaccess.leaderboard.model.MyRankModel;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.leaderboard.R;
import com.coveiot.leaderboard.utils.LeaderBoardDataUtiils;
import com.coveiot.utils.utility.AppUtils;
import com.google.gson.Gson;
import com.mappls.sdk.services.api.geocoding.GeoCodingCriteria;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;
/* loaded from: classes9.dex */
public class LeaderBoardApiIntentService extends IntentService {

    /* loaded from: classes9.dex */
    public class a implements CoveApiListener<AllBadgesModel, CoveApiErrorModel> {
        public a() {
        }

        @Override // com.coveiot.coveaccess.CoveApiListener
        /* renamed from: a */
        public void onError(CoveApiErrorModel coveApiErrorModel) {
        }

        @Override // com.coveiot.coveaccess.CoveApiListener
        /* renamed from: b */
        public void onSuccess(AllBadgesModel allBadgesModel) {
            LeaderBoardDataUtiils.saveAllBadges(LeaderBoardApiIntentService.this, new Gson().toJson(allBadgesModel));
        }
    }

    /* loaded from: classes9.dex */
    public class b implements CoveApiListener<AllBadgesModel, CoveApiErrorModel> {
        public b() {
        }

        @Override // com.coveiot.coveaccess.CoveApiListener
        /* renamed from: a */
        public void onError(CoveApiErrorModel coveApiErrorModel) {
        }

        @Override // com.coveiot.coveaccess.CoveApiListener
        /* renamed from: b */
        public void onSuccess(AllBadgesModel allBadgesModel) {
            LeaderBoardDataUtiils.saveSpecialBadges(LeaderBoardApiIntentService.this, new Gson().toJson(allBadgesModel));
        }
    }

    /* loaded from: classes9.dex */
    public class c implements CoveApiListener<AllBadgesModel, CoveApiErrorModel> {
        public c() {
        }

        @Override // com.coveiot.coveaccess.CoveApiListener
        /* renamed from: a */
        public void onError(CoveApiErrorModel coveApiErrorModel) {
        }

        @Override // com.coveiot.coveaccess.CoveApiListener
        /* renamed from: b */
        public void onSuccess(AllBadgesModel allBadgesModel) {
            LeaderBoardDataUtiils.saveDailyBadges(LeaderBoardApiIntentService.this, new Gson().toJson(allBadgesModel));
        }
    }

    /* loaded from: classes9.dex */
    public class d implements CoveApiListener<MyBadgesModel, CoveApiErrorModel> {
        public d() {
        }

        @Override // com.coveiot.coveaccess.CoveApiListener
        /* renamed from: a */
        public void onError(CoveApiErrorModel coveApiErrorModel) {
        }

        @Override // com.coveiot.coveaccess.CoveApiListener
        /* renamed from: b */
        public void onSuccess(MyBadgesModel myBadgesModel) {
            HashMap hashMap = new HashMap();
            LeaderBoardDataUtiils.saveMyBadges(LeaderBoardApiIntentService.this, new Gson().toJson(myBadgesModel));
            for (int i = 0; i < myBadgesModel.getData().getBadges().size(); i++) {
                if (myBadgesModel.getData().getBadges().get(i).getBadgeLevels().get(0).getObtainedDate() != null) {
                    String str = myBadgesModel.getData().getBadges().get(i).getBadgeLevels().get(0).getObtainedDate().split(ExifInterface.GPS_DIRECTION_TRUE)[0];
                    try {
                        AppUtils.getSimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(str + " 23:59:59").getTime();
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
                for (int i2 = 0; i2 < myBadgesModel.getData().getBadges().get(i).getBadgeLevels().size(); i2++) {
                    if (myBadgesModel.getData().getBadges().get(i).getBadgeLevels().get(i2).getObtainedDate() != null) {
                        hashMap.put("" + myBadgesModel.getData().getBadges().get(i).getBadgeLevels().get(i2).getLevelId(), myBadgesModel.getData().getBadges().get(i).getBadgeLevels().get(i2).getObtainedDate().toString());
                    }
                }
            }
            LeaderBoardDataUtiils.saveBadgeLevels(LeaderBoardApiIntentService.this, new JSONObject((Map<?, ?>) hashMap).toString());
        }
    }

    /* loaded from: classes9.dex */
    public class e implements CoveApiListener<MyRankModel, CoveApiErrorModel> {
        public e() {
        }

        @Override // com.coveiot.coveaccess.CoveApiListener
        /* renamed from: a */
        public void onError(CoveApiErrorModel coveApiErrorModel) {
        }

        @Override // com.coveiot.coveaccess.CoveApiListener
        /* renamed from: b */
        public void onSuccess(MyRankModel myRankModel) {
            LeaderBoardDataUtiils.saveMyRank(LeaderBoardApiIntentService.this, new Gson().toJson(myRankModel));
        }
    }

    public LeaderBoardApiIntentService() {
        super("LeaderBoardApiIntentService");
    }

    public final void a() {
        try {
            if (Build.VERSION.SDK_INT >= 26) {
                Context applicationContext = getApplicationContext();
                int i = R.string.app_name;
                NotificationChannel notificationChannel = new NotificationChannel("101", applicationContext.getString(i), 2);
                notificationChannel.enableLights(false);
                ((NotificationManager) getSystemService("notification")).createNotificationChannel(notificationChannel);
                Notification.Builder builder = new Notification.Builder(this, "101");
                startForeground(1, builder.setContentTitle(getApplicationContext().getString(i) + " is running").setSmallIcon(R.mipmap.ic_launcher).build());
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public final void b() {
        CoveLeaderboardApi.getAllBadges(new a());
    }

    public final void c() {
        CoveLeaderboardApi.getAllBadgesByCategory(new c(), "DAILY");
    }

    public final void d() {
        CoveLeaderboardApi.getMyBadges(new d());
    }

    public final void e() {
        CoveLeaderboardApi.getAllBadgesByCategory(new b(), "SPECIAL");
    }

    public final void f() {
        CoveLeaderboardApi.getMyRank(GeoCodingCriteria.POD_CITY, new e());
    }

    public final void g() {
        b();
        c();
        e();
        d();
        f();
    }

    @Override // android.app.IntentService, android.app.Service
    public void onCreate() {
        super.onCreate();
        a();
    }

    @Override // android.app.IntentService
    public void onHandleIntent(Intent intent) {
        if (intent != null) {
            g();
        }
    }

    @Override // android.app.IntentService, android.app.Service
    public int onStartCommand(@Nullable Intent intent, int i, int i2) {
        return super.onStartCommand(intent, i, i2);
    }
}
