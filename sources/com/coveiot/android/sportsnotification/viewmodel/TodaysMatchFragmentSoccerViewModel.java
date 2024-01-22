package com.coveiot.android.sportsnotification.viewmodel;

import android.app.Application;
import android.content.Context;
import androidx.lifecycle.AndroidViewModel;
import com.coveiot.android.sportsnotification.Constants;
import com.coveiot.android.sportsnotification.SportsPreference;
import com.coveiot.android.sportsnotification.SportsUtils;
import com.coveiot.android.sportsnotification.model.CoinNotifications;
import com.coveiot.android.sportsnotification.model.MatchListModel;
import com.coveiot.android.sportsnotification.model.Options;
import com.coveiot.android.sportsnotification.model.SoccerFilterConfig;
import com.coveiot.android.sportsnotification.model.SportsPreferenceModel;
import com.coveiot.android.sportsnotification.viewmodel.TodaysMatchFragmentSoccerViewModel;
import com.coveiot.android.sportsnotificationsdk.SportsNotificationApiManager;
import com.coveiot.android.sportsnotificationsdk.network.SportsApiErrorModel;
import com.coveiot.android.sportsnotificationsdk.network.SportsApiListener;
import com.coveiot.android.sportsnotificationsdk.soccer.models.common.Team;
import com.coveiot.android.sportsnotificationsdk.soccer.models.matchlist.Items;
import com.coveiot.android.sportsnotificationsdk.soccer.models.matchlist.SMatchListResponse;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.CoveSports;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.prefs.PreferenceManager;
import com.coveiot.coveaccess.sports.Cricket;
import com.coveiot.coveaccess.sports.Data;
import com.coveiot.coveaccess.sports.MatchUpdateRequest;
import com.coveiot.coveaccess.sports.SGetSportsUserPrefRes;
import com.coveiot.coveaccess.sports.Soccer;
import com.coveiot.coveaccess.sports.SportsAuthTokenRequest;
import com.coveiot.coveaccess.sports.SportsTokenRes;
import com.coveiot.coveaccess.sports.SportsUserPrefRequest;
import com.coveiot.coveaccess.sports.UpdateConfigsBean;
import com.coveiot.coveaccess.sports.VibrationConfigBean;
import com.coveiot.utils.utility.AppUtils;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.TimeZone;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.h;
import kotlin.comparisons.f;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.StringsKt__StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import retrofit2.Response;
/* loaded from: classes7.dex */
public final class TodaysMatchFragmentSoccerViewModel extends AndroidViewModel {
    @NotNull
    public ArrayList<Integer> d;
    @NotNull
    public ArrayList<String> e;
    @NotNull
    public ArrayList<String> f;
    @Nullable
    public List<? extends SGetSportsUserPrefRes.Data.CoinNotifications> g;
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
    public TodaysMatchFragmentSoccerViewModel(@NotNull Application application) {
        super(application);
        Intrinsics.checkNotNullParameter(application, "application");
        this.d = CollectionsKt__CollectionsKt.arrayListOf(1, 3, 6, 7, 8);
        this.e = CollectionsKt__CollectionsKt.arrayListOf("IND", "IND-W");
        this.f = CollectionsKt__CollectionsKt.arrayListOf("IPL");
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void a(final String str, final String str2, final int i, final int i2, ArrayList<MatchListModel> arrayList, final SoccerFilterConfig soccerFilterConfig) {
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = arrayList;
        SportsNotificationApiManager.Companion.getSoccerMatchList(str, str2, i, i2, new SportsApiListener<Response<SMatchListResponse>, SportsApiErrorModel>() { // from class: com.coveiot.android.sportsnotification.viewmodel.TodaysMatchFragmentSoccerViewModel$callSoccerMatchListAPIInRecurssion$1
            @Override // com.coveiot.android.sportsnotificationsdk.network.SportsApiListener
            public void onError(@NotNull SportsApiErrorModel obj) {
                Intrinsics.checkNotNullParameter(obj, "obj");
                if (obj.getCode() == 401) {
                    TodaysMatchFragmentSoccerViewModel.this.getAccessToken();
                } else {
                    TodaysMatchFragmentSoccerViewModel.this.getMatchListListener().onMatchListFailed();
                }
            }

            /* JADX WARN: Type inference failed for: r0v13, types: [T, java.util.ArrayList] */
            /* JADX WARN: Type inference failed for: r10v19, types: [T, java.util.ArrayList] */
            @Override // com.coveiot.android.sportsnotificationsdk.network.SportsApiListener
            public void onSuccess(@NotNull Response<SMatchListResponse> res) {
                ?? e;
                com.coveiot.android.sportsnotificationsdk.soccer.models.matchlist.Response response;
                Intrinsics.checkNotNullParameter(res, "res");
                if (res.body() != null) {
                    SMatchListResponse body = res.body();
                    Intrinsics.checkNotNull(body);
                    if (body.getResponse() != null) {
                        SMatchListResponse body2 = res.body();
                        Intrinsics.checkNotNull(body2);
                        com.coveiot.android.sportsnotificationsdk.soccer.models.matchlist.Response response2 = body2.getResponse();
                        Intrinsics.checkNotNull(response2);
                        List<Items> items = response2.getItems();
                        Intrinsics.checkNotNull(items);
                        for (Items item : items) {
                            TodaysMatchFragmentSoccerViewModel todaysMatchFragmentSoccerViewModel = TodaysMatchFragmentSoccerViewModel.this;
                            Intrinsics.checkNotNullExpressionValue(item, "item");
                            todaysMatchFragmentSoccerViewModel.b(item, objectRef.element);
                        }
                        int i3 = i2;
                        SMatchListResponse body3 = res.body();
                        Integer totalPages = (body3 == null || (response = body3.getResponse()) == null) ? null : response.getTotalPages();
                        Intrinsics.checkNotNull(totalPages);
                        if (i3 < totalPages.intValue()) {
                            TodaysMatchFragmentSoccerViewModel.this.a(str, str2, i, i2 + 1, objectRef.element, soccerFilterConfig);
                            return;
                        }
                        ArrayList<MatchListModel> arrayList2 = objectRef.element;
                        if (arrayList2.size() > 1) {
                            h.sortWith(arrayList2, new Comparator() { // from class: com.coveiot.android.sportsnotification.viewmodel.TodaysMatchFragmentSoccerViewModel$callSoccerMatchListAPIInRecurssion$1$onSuccess$$inlined$sortBy$1
                                @Override // java.util.Comparator
                                public final int compare(T t, T t2) {
                                    return f.compareValues(((MatchListModel) t).getDate(), ((MatchListModel) t2).getDate());
                                }
                            });
                        }
                        SoccerFilterConfig soccerFilterConfig2 = soccerFilterConfig;
                        if (soccerFilterConfig2 != null) {
                            Boolean shouldFilterOriginalMatchList = soccerFilterConfig2.getFilters().get(0).getShouldFilterOriginalMatchList();
                            Intrinsics.checkNotNullExpressionValue(shouldFilterOriginalMatchList, "soccerConfig.filters[0].…ldFilterOriginalMatchList");
                            if (shouldFilterOriginalMatchList.booleanValue()) {
                                ArrayList<Integer> arrayList3 = new ArrayList<>();
                                for (Options options : soccerFilterConfig.getFilters().get(0).getOptions()) {
                                    arrayList3.add(options.getOptionId());
                                }
                                Ref.ObjectRef<ArrayList<MatchListModel>> objectRef2 = objectRef;
                                objectRef2.element = TodaysMatchFragmentSoccerViewModel.this.filterMatches(soccerFilterConfig, arrayList3, objectRef2.element);
                            }
                        }
                        Ref.ObjectRef<ArrayList<MatchListModel>> objectRef3 = objectRef;
                        e = TodaysMatchFragmentSoccerViewModel.this.e(objectRef3.element);
                        objectRef3.element = e;
                        TodaysMatchFragmentSoccerViewModel.this.getMatchListListener().onMatchListUpdate(objectRef.element);
                    }
                }
            }
        });
    }

    public final void b(Items items, ArrayList<MatchListModel> arrayList) {
        if (Intrinsics.areEqual(items.getStatus(), "3") || Intrinsics.areEqual(items.getStatus(), "1") || Intrinsics.areEqual(items.getStatus(), "2")) {
            MatchListModel matchListModel = new MatchListModel();
            String mid = items.getMid();
            Intrinsics.checkNotNullExpressionValue(mid, "item.mid");
            matchListModel.setMatchId(Integer.valueOf(Integer.parseInt(mid)));
            Team home = items.getTeams().getHome();
            Intrinsics.checkNotNull(home);
            matchListModel.setTeamA(home.getFullname());
            Team home2 = items.getTeams().getHome();
            Intrinsics.checkNotNull(home2);
            matchListModel.setTeamAShortName(home2.getAbbr());
            Team home3 = items.getTeams().getHome();
            Intrinsics.checkNotNull(home3);
            matchListModel.setIconTeamA(home3.getLogo());
            Team home4 = items.getTeams().getHome();
            Intrinsics.checkNotNull(home4);
            String tid = home4.getTid();
            Intrinsics.checkNotNullExpressionValue(tid, "item.teams.home!!.tid");
            matchListModel.setTeamAId(Integer.valueOf(Integer.parseInt(tid)));
            Team away = items.getTeams().getAway();
            Intrinsics.checkNotNull(away);
            matchListModel.setTeamB(away.getFullname());
            Team away2 = items.getTeams().getAway();
            Intrinsics.checkNotNull(away2);
            matchListModel.setTeamBShortName(away2.getAbbr());
            Team away3 = items.getTeams().getAway();
            Intrinsics.checkNotNull(away3);
            matchListModel.setIconTeamB(away3.getLogo());
            Team away4 = items.getTeams().getAway();
            Intrinsics.checkNotNull(away4);
            String tid2 = away4.getTid();
            Intrinsics.checkNotNullExpressionValue(tid2, "item.teams.away!!.tid");
            matchListModel.setTeamBId(Integer.valueOf(Integer.parseInt(tid2)));
            matchListModel.setTitle(items.getCompetition().getCname());
            String datestart = items.getDatestart();
            Intrinsics.checkNotNull(datestart);
            matchListModel.setDate(datestart);
            String dateend = items.getDateend();
            Intrinsics.checkNotNull(dateend);
            matchListModel.setFormattedTime(dateend);
            String datestart2 = items.getDatestart();
            Intrinsics.checkNotNull(datestart2);
            matchListModel.setFormattedTime(d(datestart2));
            matchListModel.setLeagueName(items.getCompetition().getCname());
            matchListModel.setCatergaory(items.getCompetition().getCategory());
            String cid = items.getCompetition().getCid();
            matchListModel.setCid(cid != null ? Integer.valueOf(Integer.parseInt(cid)) : null);
            String status = items.getStatus();
            Intrinsics.checkNotNullExpressionValue(status, "item.status");
            matchListModel.setMatchStatus(Integer.valueOf(Integer.parseInt(status)));
            String datestart3 = items.getDatestart();
            Intrinsics.checkNotNullExpressionValue(datestart3, "item.datestart");
            Calendar c = c(datestart3);
            if (c != null && c.get(6) == Calendar.getInstance().get(6)) {
                arrayList.add(matchListModel);
                return;
            }
            String datestart4 = items.getDatestart();
            Intrinsics.checkNotNullExpressionValue(datestart4, "item.datestart");
            Calendar c2 = c(datestart4);
            Integer valueOf = c2 != null ? Integer.valueOf(c2.get(6)) : null;
            Intrinsics.checkNotNull(valueOf);
            if (valueOf.intValue() < Calendar.getInstance().get(6)) {
                String status2 = items.getStatus();
                Intrinsics.checkNotNullExpressionValue(status2, "item.status");
                if (Integer.parseInt(status2) == 3) {
                    arrayList.add(matchListModel);
                    return;
                }
                return;
            }
            String datestart5 = items.getDatestart();
            Intrinsics.checkNotNullExpressionValue(datestart5, "item.datestart");
            Calendar c3 = c(datestart5);
            Integer valueOf2 = c3 != null ? Integer.valueOf(c3.get(6)) : null;
            Intrinsics.checkNotNull(valueOf2);
            if (valueOf2.intValue() > Calendar.getInstance().get(6)) {
                Calendar calendar = Calendar.getInstance();
                calendar.add(6, 1);
                calendar.set(11, 6);
                calendar.set(12, 0);
                calendar.set(13, 0);
                String datestart6 = items.getDatestart();
                Intrinsics.checkNotNullExpressionValue(datestart6, "item.datestart");
                Calendar c4 = c(datestart6);
                Intrinsics.checkNotNull(c4);
                if (c4.getTimeInMillis() <= calendar.getTimeInMillis()) {
                    arrayList.add(matchListModel);
                }
            }
        }
    }

    public final Calendar c(String str) {
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTimeZone(TimeZone.getDefault());
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
            calendar.setTime(simpleDateFormat.parse(str));
            calendar.get(11);
            int i = calendar.get(12);
            int i2 = calendar.get(5);
            int i3 = calendar.get(2);
            if (i >= 10) {
                Integer.toString(i);
            } else {
                StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                Intrinsics.checkNotNullExpressionValue(String.format("0%s", Arrays.copyOf(new Object[]{Integer.toString(i)}, 1)), "format(format, *args)");
            }
            SportsUtils sportsUtils = SportsUtils.INSTANCE;
            sportsUtils.getDayOfMonthSuffix(i2);
            sportsUtils.convertMonthNumberToName(i3);
            return calendar;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public final String d(String str) {
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

    @NotNull
    public final ArrayList<MatchListModel> filterMatches(@Nullable SoccerFilterConfig soccerFilterConfig, @NotNull ArrayList<Integer> optionIds, @NotNull ArrayList<MatchListModel> matchListFromSever) {
        Intrinsics.checkNotNullParameter(optionIds, "optionIds");
        Intrinsics.checkNotNullParameter(matchListFromSever, "matchListFromSever");
        if (soccerFilterConfig == null || optionIds.size() <= 0) {
            return matchListFromSever;
        }
        String filterBy = soccerFilterConfig.getFilters().get(0).getFilterBy();
        ArrayList<MatchListModel> arrayList = new ArrayList<>();
        if (filterBy.equals(AppMeasurementSdk.ConditionalUserProperty.NAME)) {
            Object clone = matchListFromSever.clone();
            Intrinsics.checkNotNull(clone, "null cannot be cast to non-null type java.util.ArrayList<com.coveiot.android.sportsnotification.model.MatchListModel>");
            ArrayList arrayList2 = (ArrayList) clone;
            ArrayList arrayList3 = new ArrayList();
            for (Options options : soccerFilterConfig.getFilters().get(0).getOptions()) {
                if (optionIds.contains(options.getOptionId())) {
                    arrayList3.add(options);
                }
            }
            Iterator it = arrayList2.iterator();
            while (it.hasNext()) {
                MatchListModel matchListModel = (MatchListModel) it.next();
                Iterator it2 = arrayList3.iterator();
                while (it2.hasNext()) {
                    Options options2 = (Options) it2.next();
                    String leagueName = matchListModel.getLeagueName();
                    Intrinsics.checkNotNull(leagueName);
                    String name = options2.getName();
                    Intrinsics.checkNotNullExpressionValue(name, "option.name");
                    if (StringsKt__StringsKt.contains$default((CharSequence) leagueName, (CharSequence) name, false, 2, (Object) null) && options2.getCategory().equals(matchListModel.getCatergaory())) {
                        arrayList.add(matchListModel);
                    }
                }
            }
        }
        if (filterBy.equals("cid")) {
            Object clone2 = matchListFromSever.clone();
            Intrinsics.checkNotNull(clone2, "null cannot be cast to non-null type java.util.ArrayList<com.coveiot.android.sportsnotification.model.MatchListModel>");
            ArrayList arrayList4 = (ArrayList) clone2;
            ArrayList arrayList5 = new ArrayList();
            for (Options options3 : soccerFilterConfig.getFilters().get(0).getOptions()) {
                if (optionIds.contains(options3.getOptionId())) {
                    arrayList5.add(options3.getCid());
                }
            }
            Iterator it3 = arrayList4.iterator();
            while (it3.hasNext()) {
                MatchListModel matchListModel2 = (MatchListModel) it3.next();
                Iterator it4 = arrayList5.iterator();
                while (it4.hasNext()) {
                    Integer num = (Integer) it4.next();
                    Integer cid = matchListModel2.getCid();
                    Intrinsics.checkNotNull(cid);
                    int intValue = cid.intValue();
                    if (num != null && num.intValue() == intValue) {
                        arrayList.add(matchListModel2);
                    }
                }
            }
        }
        return arrayList;
    }

    public final void getAccessToken() {
        SportsAuthTokenRequest sportsAuthTokenRequest = new SportsAuthTokenRequest();
        sportsAuthTokenRequest.setExtend(4);
        sportsAuthTokenRequest.setCtx("entitysport");
        sportsAuthTokenRequest.setSport("SOCCER");
        CoveSports.getSportsToken(sportsAuthTokenRequest, new CoveApiListener<SportsTokenRes, CoveApiErrorModel>() { // from class: com.coveiot.android.sportsnotification.viewmodel.TodaysMatchFragmentSoccerViewModel$getAccessToken$1
            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                TodaysMatchFragmentSoccerViewModel.this.getMatchListListener().onAccessTokenFailed();
            }

            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onSuccess(@Nullable SportsTokenRes sportsTokenRes) {
                TodaysMatchFragmentSoccerViewModel.MatchListListener matchListListener = TodaysMatchFragmentSoccerViewModel.this.getMatchListListener();
                Intrinsics.checkNotNull(sportsTokenRes);
                Data data = sportsTokenRes.getData();
                Intrinsics.checkNotNull(data);
                com.coveiot.coveaccess.sports.Response response = data.getResponse();
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

    @Nullable
    public final List<SGetSportsUserPrefRes.Data.CoinNotifications> getObj() {
        return this.g;
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

    public final void getScorecardList(@Nullable SoccerFilterConfig soccerFilterConfig, boolean z, boolean z2, @NotNull String date, @NotNull String token, int i, int i2) {
        Intrinsics.checkNotNullParameter(date, "date");
        Intrinsics.checkNotNullParameter(token, "token");
        a(date, token, i, i2, new ArrayList<>(), soccerFilterConfig);
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

    public final void setObj(@Nullable List<? extends SGetSportsUserPrefRes.Data.CoinNotifications> list) {
        this.g = list;
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
        Soccer soccer = new Soccer();
        ArrayList arrayList = new ArrayList();
        MatchUpdateRequest matchUpdateRequest = new MatchUpdateRequest();
        Constants.Companion companion2 = Constants.Companion;
        matchUpdateRequest.setCtx(companion2.getENTITY_SPORT());
        matchUpdateRequest.setMatchId(selectedMatchListModel.getMatchId());
        arrayList.add(matchUpdateRequest);
        soccer.setEnable(Boolean.TRUE);
        soccer.setMatchUpdates(arrayList);
        sportsUserPrefRequest.setSoccer(soccer);
        Cricket cricket = new Cricket();
        MatchListModel selectedMatch = companion.getSelectedMatch(context);
        if (selectedMatch != null) {
            ArrayList arrayList2 = new ArrayList();
            MatchUpdateRequest matchUpdateRequest2 = new MatchUpdateRequest();
            matchUpdateRequest2.setCtx(companion2.getENTITY_SPORT());
            matchUpdateRequest2.setMatchId(selectedMatch.getMatchId());
            arrayList2.add(matchUpdateRequest2);
            cricket.setMatchUpdates(arrayList2);
        }
        cricket.setEnable(Boolean.FALSE);
        UpdateConfigsBean updateConfigsBean = new UpdateConfigsBean();
        updateConfigsBean.setInterval(Integer.valueOf(companion.getODIInterval(context) * 60));
        updateConfigsBean.setMatchFormat("ODI");
        UpdateConfigsBean updateConfigsBean2 = new UpdateConfigsBean();
        updateConfigsBean2.setInterval(Integer.valueOf(companion.getT20Interval(context) * 60));
        updateConfigsBean2.setMatchFormat("T20");
        cricket.setUpdateConfigs(CollectionsKt__CollectionsKt.arrayListOf(updateConfigsBean, updateConfigsBean2));
        sportsUserPrefRequest.setCricket(cricket);
        CoveSports.postSportsUserPref(sportsUserPrefRequest, new CoveApiListener<SGetSportsUserPrefRes, CoveApiErrorModel>() { // from class: com.coveiot.android.sportsnotification.viewmodel.TodaysMatchFragmentSoccerViewModel$updateSportsSettingToServer$1
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
                throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.sportsnotification.viewmodel.TodaysMatchFragmentSoccerViewModel$updateSportsSettingToServer$1.onSuccess(com.coveiot.coveaccess.sports.SGetSportsUserPrefRes):void");
            }
        });
    }
}
