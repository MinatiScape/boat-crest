package com.coveiot.android.leonardo.onboarding.paring.viewmodel;

import android.content.Context;
import com.coveiot.android.boat.R;
import com.coveiot.android.sportsnotification.AccessTokenPreference;
import com.coveiot.android.sportsnotification.model.CoinNotifications;
import com.coveiot.android.sportsnotification.model.CricketConfiguration;
import com.coveiot.android.sportsnotification.viewmodel.TodaysMatchFragmentViewModel;
import com.coveiot.coveaccess.sports.Cricket;
import com.coveiot.utils.utility.LogHelper;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.ktx.Firebase;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.ktx.RemoteConfigKt;
import com.google.gson.Gson;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class ActivityPairingViewModel$getSportsSettingFromServer$1$onSuccess$1 implements TodaysMatchFragmentViewModel.MatchListListener {
    public final /* synthetic */ Ref.ObjectRef<Integer> h;
    public final /* synthetic */ Context i;
    public final /* synthetic */ Ref.ObjectRef<Cricket> j;
    public final /* synthetic */ Ref.ObjectRef<SimpleDateFormat> k;
    public final /* synthetic */ TodaysMatchFragmentViewModel l;
    public final /* synthetic */ ActivityPairingViewModel m;

    public ActivityPairingViewModel$getSportsSettingFromServer$1$onSuccess$1(Ref.ObjectRef<Integer> objectRef, Context context, Ref.ObjectRef<Cricket> objectRef2, Ref.ObjectRef<SimpleDateFormat> objectRef3, TodaysMatchFragmentViewModel todaysMatchFragmentViewModel, ActivityPairingViewModel activityPairingViewModel) {
        this.h = objectRef;
        this.i = context;
        this.j = objectRef2;
        this.k = objectRef3;
        this.l = todaysMatchFragmentViewModel;
        this.m = activityPairingViewModel;
    }

    public static final void c(final FirebaseRemoteConfig remoteConfig, final TodaysMatchFragmentViewModel viewModel, final String date, final String accessToken, final ActivityPairingViewModel this$0, Task task) {
        Intrinsics.checkNotNullParameter(remoteConfig, "$remoteConfig");
        Intrinsics.checkNotNullParameter(viewModel, "$viewModel");
        Intrinsics.checkNotNullParameter(date, "$date");
        Intrinsics.checkNotNullParameter(accessToken, "$accessToken");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(task, "task");
        if (task.isSuccessful()) {
            final Void r6 = (Void) task.getResult();
            remoteConfig.activate().addOnCompleteListener(new OnCompleteListener() { // from class: com.coveiot.android.leonardo.onboarding.paring.viewmodel.g
                @Override // com.google.android.gms.tasks.OnCompleteListener
                public final void onComplete(Task task2) {
                    ActivityPairingViewModel$getSportsSettingFromServer$1$onSuccess$1.d(FirebaseRemoteConfig.this, viewModel, date, accessToken, this$0, r6, task2);
                }
            });
            return;
        }
        String string = remoteConfig.getString("cricket_configuration");
        Intrinsics.checkNotNullExpressionValue(string, "remoteConfig.getString(C…ts.CRICKET_CONFIGURATION)");
        viewModel.getScorecardList((CricketConfiguration) new Gson().fromJson(string, (Class<Object>) CricketConfiguration.class), false, true, date, accessToken, 50, 1);
        LogHelper.e(this$0.getTAG(), "Remote Config Failed");
    }

    public static final void d(FirebaseRemoteConfig remoteConfig, TodaysMatchFragmentViewModel viewModel, String date, String accessToken, ActivityPairingViewModel this$0, Void r13, Task it) {
        Intrinsics.checkNotNullParameter(remoteConfig, "$remoteConfig");
        Intrinsics.checkNotNullParameter(viewModel, "$viewModel");
        Intrinsics.checkNotNullParameter(date, "$date");
        Intrinsics.checkNotNullParameter(accessToken, "$accessToken");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(it, "it");
        boolean z = remoteConfig.getBoolean("cricket_filter");
        boolean z2 = remoteConfig.getBoolean("match_list_filter");
        String string = remoteConfig.getString("cricket_configuration");
        Intrinsics.checkNotNullExpressionValue(string, "remoteConfig.getString(C…ts.CRICKET_CONFIGURATION)");
        viewModel.getScorecardList((CricketConfiguration) new Gson().fromJson(string, (Class<Object>) CricketConfiguration.class), z, z2, date, accessToken, 50, 1);
        String tag = this$0.getTAG();
        LogHelper.d(tag, "Config params updated: " + r13 + z);
    }

    @Override // com.coveiot.android.sportsnotification.viewmodel.TodaysMatchFragmentViewModel.MatchListListener
    public void onAccessTokenFailed() {
    }

    @Override // com.coveiot.android.sportsnotification.viewmodel.TodaysMatchFragmentViewModel.MatchListListener
    public void onAccessTokenRecieved(@NotNull final String accessToken) {
        Intrinsics.checkNotNullParameter(accessToken, "accessToken");
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.add(5, 1);
        final String str = this.k.element.format(date) + '_' + this.k.element.format(calendar.getTime());
        new AccessTokenPreference(this.i).saveAccessToken(accessToken);
        final FirebaseRemoteConfig remoteConfig = RemoteConfigKt.getRemoteConfig(Firebase.INSTANCE);
        remoteConfig.setDefaultsAsync(R.xml.remote_config_details);
        Task<Void> fetch = remoteConfig.fetch(10L);
        final TodaysMatchFragmentViewModel todaysMatchFragmentViewModel = this.l;
        final ActivityPairingViewModel activityPairingViewModel = this.m;
        fetch.addOnCompleteListener(new OnCompleteListener() { // from class: com.coveiot.android.leonardo.onboarding.paring.viewmodel.f
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public final void onComplete(Task task) {
                ActivityPairingViewModel$getSportsSettingFromServer$1$onSuccess$1.c(FirebaseRemoteConfig.this, todaysMatchFragmentViewModel, str, accessToken, activityPairingViewModel, task);
            }
        });
    }

    @Override // com.coveiot.android.sportsnotification.viewmodel.TodaysMatchFragmentViewModel.MatchListListener
    public void onMatchListFailed() {
    }

    /* JADX WARN: Removed duplicated region for block: B:75:0x00eb  */
    @Override // com.coveiot.android.sportsnotification.viewmodel.TodaysMatchFragmentViewModel.MatchListListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onMatchListUpdate(@org.jetbrains.annotations.NotNull java.util.ArrayList<com.coveiot.android.sportsnotification.model.MatchListModel> r6) {
        /*
            Method dump skipped, instructions count: 277
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.leonardo.onboarding.paring.viewmodel.ActivityPairingViewModel$getSportsSettingFromServer$1$onSuccess$1.onMatchListUpdate(java.util.ArrayList):void");
    }

    @Override // com.coveiot.android.sportsnotification.viewmodel.TodaysMatchFragmentViewModel.MatchListListener
    public void onSettingsSavedToServer(boolean z, @Nullable List<CoinNotifications> list) {
    }
}
