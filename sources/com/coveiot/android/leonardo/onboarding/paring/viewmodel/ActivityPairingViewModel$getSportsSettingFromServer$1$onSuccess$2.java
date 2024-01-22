package com.coveiot.android.leonardo.onboarding.paring.viewmodel;

import android.content.Context;
import com.coveiot.android.boat.R;
import com.coveiot.android.sportsnotification.AccessTokenPreference;
import com.coveiot.android.sportsnotification.SportsPreference;
import com.coveiot.android.sportsnotification.model.CoinNotifications;
import com.coveiot.android.sportsnotification.model.CricketConfiguration;
import com.coveiot.android.sportsnotification.model.MatchListModel;
import com.coveiot.android.sportsnotification.model.SoccerFilterConfig;
import com.coveiot.android.sportsnotification.viewmodel.TodaysMatchFragmentSoccerViewModel;
import com.coveiot.utils.utility.LogHelper;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.ktx.Firebase;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.ktx.RemoteConfigKt;
import com.google.gson.Gson;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class ActivityPairingViewModel$getSportsSettingFromServer$1$onSuccess$2 implements TodaysMatchFragmentSoccerViewModel.MatchListListener {
    public final /* synthetic */ Ref.ObjectRef<Integer> h;
    public final /* synthetic */ Context i;
    public final /* synthetic */ Ref.ObjectRef<SimpleDateFormat> j;
    public final /* synthetic */ TodaysMatchFragmentSoccerViewModel k;
    public final /* synthetic */ ActivityPairingViewModel l;

    public ActivityPairingViewModel$getSportsSettingFromServer$1$onSuccess$2(Ref.ObjectRef<Integer> objectRef, Context context, Ref.ObjectRef<SimpleDateFormat> objectRef2, TodaysMatchFragmentSoccerViewModel todaysMatchFragmentSoccerViewModel, ActivityPairingViewModel activityPairingViewModel) {
        this.h = objectRef;
        this.i = context;
        this.j = objectRef2;
        this.k = todaysMatchFragmentSoccerViewModel;
        this.l = activityPairingViewModel;
    }

    public static final void c(final FirebaseRemoteConfig remoteConfig, final TodaysMatchFragmentSoccerViewModel soccerViewModel, final String date, final String accessToken, final ActivityPairingViewModel this$0, Task task) {
        Intrinsics.checkNotNullParameter(remoteConfig, "$remoteConfig");
        Intrinsics.checkNotNullParameter(soccerViewModel, "$soccerViewModel");
        Intrinsics.checkNotNullParameter(date, "$date");
        Intrinsics.checkNotNullParameter(accessToken, "$accessToken");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(task, "task");
        if (task.isSuccessful()) {
            final Void r6 = (Void) task.getResult();
            remoteConfig.activate().addOnCompleteListener(new OnCompleteListener() { // from class: com.coveiot.android.leonardo.onboarding.paring.viewmodel.i
                @Override // com.google.android.gms.tasks.OnCompleteListener
                public final void onComplete(Task task2) {
                    ActivityPairingViewModel$getSportsSettingFromServer$1$onSuccess$2.d(FirebaseRemoteConfig.this, soccerViewModel, date, accessToken, this$0, r6, task2);
                }
            });
            return;
        }
        String string = remoteConfig.getString("cricket_configuration");
        Intrinsics.checkNotNullExpressionValue(string, "remoteConfig.getString(C…ts.CRICKET_CONFIGURATION)");
        CricketConfiguration cricketConfiguration = (CricketConfiguration) new Gson().fromJson(string, (Class<Object>) CricketConfiguration.class);
        String string2 = remoteConfig.getString("soccer_filte_config");
        Intrinsics.checkNotNullExpressionValue(string2, "remoteConfig.getString(C…CER_FILTER_CONFIGURATION)");
        soccerViewModel.getScorecardList((SoccerFilterConfig) new Gson().fromJson(string2, (Class<Object>) SoccerFilterConfig.class), false, true, date, accessToken, 50, 1);
        LogHelper.e(this$0.getTAG(), "Remote Config Failed");
    }

    public static final void d(FirebaseRemoteConfig remoteConfig, TodaysMatchFragmentSoccerViewModel soccerViewModel, String date, String accessToken, ActivityPairingViewModel this$0, Void r13, Task it) {
        Intrinsics.checkNotNullParameter(remoteConfig, "$remoteConfig");
        Intrinsics.checkNotNullParameter(soccerViewModel, "$soccerViewModel");
        Intrinsics.checkNotNullParameter(date, "$date");
        Intrinsics.checkNotNullParameter(accessToken, "$accessToken");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(it, "it");
        boolean z = remoteConfig.getBoolean("cricket_filter");
        boolean z2 = remoteConfig.getBoolean("match_list_filter");
        String string = remoteConfig.getString("cricket_configuration");
        Intrinsics.checkNotNullExpressionValue(string, "remoteConfig.getString(C…ts.CRICKET_CONFIGURATION)");
        CricketConfiguration cricketConfiguration = (CricketConfiguration) new Gson().fromJson(string, (Class<Object>) CricketConfiguration.class);
        String string2 = remoteConfig.getString("soccer_filte_config");
        Intrinsics.checkNotNullExpressionValue(string2, "remoteConfig.getString(C…CER_FILTER_CONFIGURATION)");
        soccerViewModel.getScorecardList((SoccerFilterConfig) new Gson().fromJson(string2, (Class<Object>) SoccerFilterConfig.class), z, z2, date, accessToken, 50, 1);
        String tag = this$0.getTAG();
        LogHelper.d(tag, "Config params updated: " + r13 + z);
    }

    @Override // com.coveiot.android.sportsnotification.viewmodel.TodaysMatchFragmentSoccerViewModel.MatchListListener
    public void onAccessTokenFailed() {
    }

    @Override // com.coveiot.android.sportsnotification.viewmodel.TodaysMatchFragmentSoccerViewModel.MatchListListener
    public void onAccessTokenRecieved(@NotNull final String accessToken) {
        Intrinsics.checkNotNullParameter(accessToken, "accessToken");
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.add(5, 1);
        final String str = this.j.element.format(date) + '_' + this.j.element.format(calendar.getTime());
        new AccessTokenPreference(this.i).saveAccessToken(accessToken);
        final FirebaseRemoteConfig remoteConfig = RemoteConfigKt.getRemoteConfig(Firebase.INSTANCE);
        remoteConfig.setDefaultsAsync(R.xml.remote_config_details);
        Task<Void> fetch = remoteConfig.fetch(10L);
        final TodaysMatchFragmentSoccerViewModel todaysMatchFragmentSoccerViewModel = this.k;
        final ActivityPairingViewModel activityPairingViewModel = this.l;
        fetch.addOnCompleteListener(new OnCompleteListener() { // from class: com.coveiot.android.leonardo.onboarding.paring.viewmodel.h
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public final void onComplete(Task task) {
                ActivityPairingViewModel$getSportsSettingFromServer$1$onSuccess$2.c(FirebaseRemoteConfig.this, todaysMatchFragmentSoccerViewModel, str, accessToken, activityPairingViewModel, task);
            }
        });
    }

    @Override // com.coveiot.android.sportsnotification.viewmodel.TodaysMatchFragmentSoccerViewModel.MatchListListener
    public void onMatchListFailed() {
    }

    @Override // com.coveiot.android.sportsnotification.viewmodel.TodaysMatchFragmentSoccerViewModel.MatchListListener
    public void onMatchListUpdate(@NotNull ArrayList<MatchListModel> mathList) {
        Integer matchStatus;
        Intrinsics.checkNotNullParameter(mathList, "mathList");
        Iterator<MatchListModel> it = mathList.iterator();
        while (it.hasNext()) {
            MatchListModel matchListItem = it.next();
            if (this.h.element != null && Intrinsics.areEqual(matchListItem.getMatchId(), this.h.element) && ((matchStatus = matchListItem.getMatchStatus()) == null || matchStatus.intValue() != 2)) {
                Integer matchStatus2 = matchListItem.getMatchStatus();
                if (matchStatus2 == null || matchStatus2.intValue() != 4) {
                    SportsPreference.Companion companion = SportsPreference.Companion;
                    Context context = this.i;
                    Intrinsics.checkNotNullExpressionValue(matchListItem, "matchListItem");
                    companion.saveSelectedSoccerMatch(context, matchListItem);
                }
            }
        }
    }

    @Override // com.coveiot.android.sportsnotification.viewmodel.TodaysMatchFragmentSoccerViewModel.MatchListListener
    public void onSettingsSavedToServer(boolean z, @Nullable List<CoinNotifications> list) {
    }
}
