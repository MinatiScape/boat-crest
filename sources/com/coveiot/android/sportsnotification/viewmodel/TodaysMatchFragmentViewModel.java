package com.coveiot.android.sportsnotification.viewmodel;

import android.app.Application;
import android.content.Context;
import androidx.lifecycle.AndroidViewModel;
import com.coveiot.android.sportsnotification.Constants;
import com.coveiot.android.sportsnotification.SportsPreference;
import com.coveiot.android.sportsnotification.model.CoinNotifications;
import com.coveiot.android.sportsnotification.model.CricketConfiguration;
import com.coveiot.android.sportsnotification.model.MatchListModel;
import com.coveiot.android.sportsnotification.model.SportsPreferenceModel;
import com.coveiot.android.sportsnotification.viewmodel.TodaysMatchFragmentViewModel;
import com.coveiot.android.sportsnotificationsdk.SportsNotificationApiManager;
import com.coveiot.android.sportsnotificationsdk.models.matchlist.GetMatchListRes;
import com.coveiot.android.sportsnotificationsdk.models.matchlist.Item;
import com.coveiot.android.sportsnotificationsdk.models.matchlist.MListCompetition;
import com.coveiot.android.sportsnotificationsdk.models.matchlist.MListTeama;
import com.coveiot.android.sportsnotificationsdk.models.matchlist.MListTeamb;
import com.coveiot.android.sportsnotificationsdk.network.SportsApiErrorModel;
import com.coveiot.android.sportsnotificationsdk.network.SportsApiListener;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.CoveSports;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.prefs.PreferenceManager;
import com.coveiot.coveaccess.sports.Cricket;
import com.coveiot.coveaccess.sports.Data;
import com.coveiot.coveaccess.sports.MatchUpdateRequest;
import com.coveiot.coveaccess.sports.Response;
import com.coveiot.coveaccess.sports.SGetSportsUserPrefRes;
import com.coveiot.coveaccess.sports.Soccer;
import com.coveiot.coveaccess.sports.SportsAuthTokenRequest;
import com.coveiot.coveaccess.sports.SportsTokenRes;
import com.coveiot.coveaccess.sports.SportsUserPrefRequest;
import com.coveiot.coveaccess.sports.UpdateConfigsBean;
import com.coveiot.coveaccess.sports.VibrationConfigBean;
import com.coveiot.utils.utility.AppUtils;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.TimeZone;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.StringsKt__StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes7.dex */
public final class TodaysMatchFragmentViewModel extends AndroidViewModel {
    @NotNull
    public ArrayList<Integer> d;
    @NotNull
    public ArrayList<String> e;
    @NotNull
    public ArrayList<String> f;
    public MatchListListener matchListListener;

    /* loaded from: classes7.dex */
    public interface MatchListListener {
        void onAccessTokenFailed();

        void onAccessTokenRecieved(@NotNull String str);

        void onMatchListFailed();

        void onMatchListUpdate(@NotNull ArrayList<MatchListModel> arrayList);

        void onSettingsSavedToServer(boolean z, @Nullable List<CoinNotifications> list);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TodaysMatchFragmentViewModel(@NotNull Application application) {
        super(application);
        Intrinsics.checkNotNullParameter(application, "application");
        this.d = CollectionsKt__CollectionsKt.arrayListOf(1, 3, 6, 7, 8);
        this.e = CollectionsKt__CollectionsKt.arrayListOf("IND", "IND-W");
        this.f = CollectionsKt__CollectionsKt.arrayListOf("IPL");
    }

    public final void a(Item item, ArrayList<MatchListModel> arrayList) {
        Integer status;
        Integer status2;
        Integer status3 = item.getStatus();
        if ((status3 != null && status3.intValue() == 3) || (((status = item.getStatus()) != null && status.intValue() == 1) || ((status2 = item.getStatus()) != null && status2.intValue() == 2))) {
            MatchListModel matchListModel = new MatchListModel();
            matchListModel.setMatchId(item.getMatchId());
            MListTeama teama = item.getTeama();
            Intrinsics.checkNotNull(teama);
            matchListModel.setTeamA(teama.getName());
            MListTeama teama2 = item.getTeama();
            Intrinsics.checkNotNull(teama2);
            matchListModel.setTeamAShortName(teama2.getShortName());
            MListTeama teama3 = item.getTeama();
            Intrinsics.checkNotNull(teama3);
            matchListModel.setIconTeamA(teama3.getLogoUrl());
            MListTeama teama4 = item.getTeama();
            Intrinsics.checkNotNull(teama4);
            matchListModel.setTeamAId(teama4.getTeamId());
            MListTeamb teamb = item.getTeamb();
            Intrinsics.checkNotNull(teamb);
            matchListModel.setTeamB(teamb.getName());
            MListTeamb teamb2 = item.getTeamb();
            Intrinsics.checkNotNull(teamb2);
            matchListModel.setTeamBShortName(teamb2.getShortName());
            MListTeamb teamb3 = item.getTeamb();
            Intrinsics.checkNotNull(teamb3);
            matchListModel.setIconTeamB(teamb3.getLogoUrl());
            MListTeamb teamb4 = item.getTeamb();
            Intrinsics.checkNotNull(teamb4);
            matchListModel.setTeamBId(teamb4.getTeamId());
            matchListModel.setTitle(item.getTitle());
            String dateStart = item.getDateStart();
            Intrinsics.checkNotNull(dateStart);
            matchListModel.setDate(dateStart);
            String dateStart2 = item.getDateStart();
            Intrinsics.checkNotNull(dateStart2);
            matchListModel.setFormattedTime(dateStart2);
            String dateStart3 = item.getDateStart();
            Intrinsics.checkNotNull(dateStart3);
            matchListModel.setFormattedTime(c(dateStart3));
            matchListModel.setMatchFormat(item.getFormat());
            matchListModel.setMatchStatus(item.getStatus());
            MListCompetition competition = item.getCompetition();
            matchListModel.setLeagueName(competition != null ? competition.getTitle() : null);
            arrayList.add(matchListModel);
        }
    }

    public final String c(String str) {
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeZone(TimeZone.getDefault());
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
            calendar.setTime(simpleDateFormat.parse(str));
            calendar.get(11);
            int i = calendar.get(12);
            calendar.get(5);
            calendar.get(2);
            if (i >= 10) {
                Integer.toString(i);
            } else {
                StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                Intrinsics.checkNotNullExpressionValue(String.format("0%s", Arrays.copyOf(new Object[]{Integer.toString(i)}, 1)), "format(format, *args)");
            }
            return AppUtils.formatDate(calendar.getTime(), "dd MMM yyyy, h:mm a");
        } catch (Exception e) {
            return e.toString();
        }
    }

    public final boolean d(String str) {
        Boolean bool;
        Iterator<String> it = this.f.iterator();
        while (it.hasNext()) {
            String domasticMatch = it.next();
            if (str != null) {
                Intrinsics.checkNotNullExpressionValue(domasticMatch, "domasticMatch");
                bool = Boolean.valueOf(StringsKt__StringsKt.contains((CharSequence) str, (CharSequence) domasticMatch, true));
            } else {
                bool = null;
            }
            Intrinsics.checkNotNull(bool);
            if (bool.booleanValue()) {
                return true;
            }
        }
        return false;
    }

    public final ArrayList<MatchListModel> e(ArrayList<MatchListModel> arrayList) {
        ArrayList arrayList2 = new ArrayList();
        Iterator<MatchListModel> it = arrayList.iterator();
        while (it.hasNext()) {
            MatchListModel next = it.next();
            Integer matchStatus = next.getMatchStatus();
            if (matchStatus != null && matchStatus.intValue() == 2) {
                arrayList2.add(next);
            }
        }
        Object clone = arrayList.clone();
        Intrinsics.checkNotNull(clone, "null cannot be cast to non-null type java.util.ArrayList<com.coveiot.android.sportsnotification.model.MatchListModel>");
        ArrayList<MatchListModel> arrayList3 = (ArrayList) clone;
        arrayList3.removeAll(arrayList2);
        arrayList3.addAll(arrayList2);
        return arrayList3;
    }

    public final void getAccessToken() {
        SportsAuthTokenRequest sportsAuthTokenRequest = new SportsAuthTokenRequest();
        sportsAuthTokenRequest.setExtend(4);
        sportsAuthTokenRequest.setCtx("entitysport");
        sportsAuthTokenRequest.setSport("CRICKET");
        CoveSports.getSportsToken(sportsAuthTokenRequest, new CoveApiListener<SportsTokenRes, CoveApiErrorModel>() { // from class: com.coveiot.android.sportsnotification.viewmodel.TodaysMatchFragmentViewModel$getAccessToken$1
            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                TodaysMatchFragmentViewModel.this.getMatchListListener().onAccessTokenFailed();
            }

            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onSuccess(@Nullable SportsTokenRes sportsTokenRes) {
                TodaysMatchFragmentViewModel.MatchListListener matchListListener = TodaysMatchFragmentViewModel.this.getMatchListListener();
                Intrinsics.checkNotNull(sportsTokenRes);
                Data data = sportsTokenRes.getData();
                Intrinsics.checkNotNull(data);
                Response response = data.getResponse();
                Intrinsics.checkNotNull(response);
                String token = response.getToken();
                Intrinsics.checkNotNull(token);
                matchListListener.onAccessTokenRecieved(token);
            }
        });
    }

    @NotNull
    public final ArrayList<Integer> getMatchFormats() {
        return this.d;
    }

    @NotNull
    public final MatchListListener getMatchListListener() {
        MatchListListener matchListListener = this.matchListListener;
        if (matchListListener != null) {
            return matchListListener;
        }
        Intrinsics.throwUninitializedPropertyAccessException("matchListListener");
        return null;
    }

    public final int getPositionOfSelectedMatch(@Nullable MatchListModel matchListModel, @NotNull ArrayList<MatchListModel> matchList) {
        Intrinsics.checkNotNullParameter(matchList, "matchList");
        int i = -1;
        if (!AppUtils.isEmpty(matchList)) {
            Iterator<MatchListModel> it = matchList.iterator();
            while (it.hasNext()) {
                MatchListModel next = it.next();
                if (matchListModel != null && Intrinsics.areEqual(matchListModel.getMatchId(), next.getMatchId())) {
                    i = matchList.indexOf(next);
                }
            }
        }
        return i;
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [T, java.util.ArrayList] */
    public final void getScorecardList(@Nullable CricketConfiguration cricketConfiguration, final boolean z, final boolean z2, @NotNull String date, @NotNull String token, int i, int i2) {
        Intrinsics.checkNotNullParameter(date, "date");
        Intrinsics.checkNotNullParameter(token, "token");
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = new ArrayList();
        if (cricketConfiguration != null && cricketConfiguration.getSupportedMatchFormats() != null) {
            List<Integer> supportedMatchFormats = cricketConfiguration.getSupportedMatchFormats();
            Intrinsics.checkNotNull(supportedMatchFormats, "null cannot be cast to non-null type java.util.ArrayList<kotlin.Int>");
            this.d = (ArrayList) supportedMatchFormats;
        }
        if (cricketConfiguration != null && cricketConfiguration.getSupportedTeams() != null) {
            List<String> supportedTeams = cricketConfiguration.getSupportedTeams();
            Intrinsics.checkNotNull(supportedTeams, "null cannot be cast to non-null type java.util.ArrayList<kotlin.String>");
            this.e = (ArrayList) supportedTeams;
        }
        if (cricketConfiguration != null && cricketConfiguration.getSupportedDomesticMatches() != null) {
            List<String> supportedDomesticMatches = cricketConfiguration.getSupportedDomesticMatches();
            Intrinsics.checkNotNull(supportedDomesticMatches, "null cannot be cast to non-null type java.util.ArrayList<kotlin.String>");
            this.f = (ArrayList) supportedDomesticMatches;
        }
        SportsNotificationApiManager.Companion.getMatchList(date, token, i, i2, new SportsApiListener<retrofit2.Response<GetMatchListRes>, SportsApiErrorModel>() { // from class: com.coveiot.android.sportsnotification.viewmodel.TodaysMatchFragmentViewModel$getScorecardList$1
            @Override // com.coveiot.android.sportsnotificationsdk.network.SportsApiListener
            public void onError(@NotNull SportsApiErrorModel obj) {
                Intrinsics.checkNotNullParameter(obj, "obj");
                if (obj.getCode() == 401) {
                    TodaysMatchFragmentViewModel.this.getAccessToken();
                } else {
                    TodaysMatchFragmentViewModel.this.getMatchListListener().onMatchListFailed();
                }
            }

            /* JADX WARN: Removed duplicated region for block: B:67:0x00ef A[SYNTHETIC] */
            /* JADX WARN: Removed duplicated region for block: B:78:0x0035 A[SYNTHETIC] */
            /* JADX WARN: Type inference failed for: r0v7, types: [T, java.util.ArrayList] */
            @Override // com.coveiot.android.sportsnotificationsdk.network.SportsApiListener
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public void onSuccess(@org.jetbrains.annotations.NotNull retrofit2.Response<com.coveiot.android.sportsnotificationsdk.models.matchlist.GetMatchListRes> r4) {
                /*
                    Method dump skipped, instructions count: 295
                    To view this dump add '--comments-level debug' option
                */
                throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.sportsnotification.viewmodel.TodaysMatchFragmentViewModel$getScorecardList$1.onSuccess(retrofit2.Response):void");
            }
        });
    }

    @NotNull
    public final ArrayList<String> getSupportedDomesticMatchAbrr() {
        return this.f;
    }

    @NotNull
    public final ArrayList<String> getSupportedTeams() {
        return this.e;
    }

    public final void setMatchFormats(@NotNull ArrayList<Integer> arrayList) {
        Intrinsics.checkNotNullParameter(arrayList, "<set-?>");
        this.d = arrayList;
    }

    public final void setMatchListListener(@NotNull MatchListListener matchListListener) {
        Intrinsics.checkNotNullParameter(matchListListener, "<set-?>");
        this.matchListListener = matchListListener;
    }

    public final void setSupportedDomesticMatchAbrr(@NotNull ArrayList<String> arrayList) {
        Intrinsics.checkNotNullParameter(arrayList, "<set-?>");
        this.f = arrayList;
    }

    public final void setSupportedTeams(@NotNull ArrayList<String> arrayList) {
        Intrinsics.checkNotNullParameter(arrayList, "<set-?>");
        this.e = arrayList;
    }

    public final void updateSportsSettingToServer(@NotNull final Context context, @NotNull final MatchListModel selectedMatchListModel) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(selectedMatchListModel, "selectedMatchListModel");
        SportsUserPrefRequest sportsUserPrefRequest = new SportsUserPrefRequest();
        SportsPreference.Companion companion = SportsPreference.Companion;
        SportsPreferenceModel sportsNotification = companion.getSportsNotification(context);
        sportsUserPrefRequest.setPushToDevice(sportsNotification != null ? sportsNotification.isEnable() : null);
        VibrationConfigBean vibrationConfigBean = new VibrationConfigBean();
        vibrationConfigBean.setActive(Boolean.valueOf(companion.isVibrationEnabled(context)));
        sportsUserPrefRequest.setVibrationConfig(vibrationConfigBean);
        String userDeviceID = PreferenceManager.getInstance().getUserDeviceID();
        Intrinsics.checkNotNullExpressionValue(userDeviceID, "getInstance().userDeviceID");
        sportsUserPrefRequest.setUserDeviceId(Integer.valueOf(Integer.parseInt(userDeviceID)));
        Cricket cricket = new Cricket();
        ArrayList arrayList = new ArrayList();
        MatchUpdateRequest matchUpdateRequest = new MatchUpdateRequest();
        Constants.Companion companion2 = Constants.Companion;
        matchUpdateRequest.setCtx(companion2.getENTITY_SPORT());
        matchUpdateRequest.setMatchId(selectedMatchListModel.getMatchId());
        arrayList.add(matchUpdateRequest);
        cricket.setEnable(Boolean.TRUE);
        UpdateConfigsBean updateConfigsBean = new UpdateConfigsBean();
        updateConfigsBean.setInterval(Integer.valueOf(companion.getODIInterval(context) * 60));
        updateConfigsBean.setMatchFormat("ODI");
        UpdateConfigsBean updateConfigsBean2 = new UpdateConfigsBean();
        updateConfigsBean2.setInterval(Integer.valueOf(companion.getT20Interval(context) * 60));
        updateConfigsBean2.setMatchFormat("T20");
        cricket.setUpdateConfigs(CollectionsKt__CollectionsKt.arrayListOf(updateConfigsBean, updateConfigsBean2));
        cricket.setMatchUpdates(arrayList);
        sportsUserPrefRequest.setCricket(cricket);
        Soccer soccer = new Soccer();
        MatchListModel selectedSoccerMatch = companion.getSelectedSoccerMatch(context);
        if (selectedSoccerMatch != null) {
            ArrayList arrayList2 = new ArrayList();
            MatchUpdateRequest matchUpdateRequest2 = new MatchUpdateRequest();
            matchUpdateRequest2.setCtx(companion2.getENTITY_SPORT());
            matchUpdateRequest2.setMatchId(selectedSoccerMatch.getMatchId());
            arrayList2.add(matchUpdateRequest2);
            soccer.setMatchUpdates(arrayList2);
        }
        soccer.setEnable(Boolean.FALSE);
        sportsUserPrefRequest.setSoccer(soccer);
        CoveSports.postSportsUserPref(sportsUserPrefRequest, new CoveApiListener<SGetSportsUserPrefRes, CoveApiErrorModel>() { // from class: com.coveiot.android.sportsnotification.viewmodel.TodaysMatchFragmentViewModel$updateSportsSettingToServer$1
            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                this.getMatchListListener().onSettingsSavedToServer(false, null);
            }

            /* JADX WARN: Code restructure failed: missing block: B:62:0x00a4, code lost:
                if (r5 != false) goto L51;
             */
            @Override // com.coveiot.coveaccess.CoveApiListener
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public void onSuccess(@org.jetbrains.annotations.Nullable com.coveiot.coveaccess.sports.SGetSportsUserPrefRes r13) {
                /*
                    Method dump skipped, instructions count: 307
                    To view this dump add '--comments-level debug' option
                */
                throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.sportsnotification.viewmodel.TodaysMatchFragmentViewModel$updateSportsSettingToServer$1.onSuccess(com.coveiot.coveaccess.sports.SGetSportsUserPrefRes):void");
            }
        });
    }
}
