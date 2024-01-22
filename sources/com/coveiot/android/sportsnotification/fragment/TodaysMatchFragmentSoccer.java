package com.coveiot.android.sportsnotification.fragment;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.text.Html;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.clevertap.android.sdk.Constants;
import com.coveiot.analytics.AnalyticsLog;
import com.coveiot.analytics.CoveAnalyticsManager;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.models.ConnectionStatus;
import com.coveiot.android.sportsnotification.AccessTokenPreference;
import com.coveiot.android.sportsnotification.BottomSheetSportsFilter;
import com.coveiot.android.sportsnotification.R;
import com.coveiot.android.sportsnotification.SoccerSportsServiceNew;
import com.coveiot.android.sportsnotification.SportsDisclaimerActivity;
import com.coveiot.android.sportsnotification.SportsNotificationActivity;
import com.coveiot.android.sportsnotification.SportsPreference;
import com.coveiot.android.sportsnotification.SportsUtils;
import com.coveiot.android.sportsnotification.adapter.TodaysMatchAdapterSoccer;
import com.coveiot.android.sportsnotification.filters.SportsFilterActivity;
import com.coveiot.android.sportsnotification.fragment.SportsSettingsFragmentNew;
import com.coveiot.android.sportsnotification.model.CoinNotifications;
import com.coveiot.android.sportsnotification.model.CricketConfiguration;
import com.coveiot.android.sportsnotification.model.MatchListModel;
import com.coveiot.android.sportsnotification.model.SoccerFilterConfig;
import com.coveiot.android.sportsnotification.viewmodel.TodaysMatchFragmentSoccerViewModel;
import com.coveiot.android.sportsnotificationsdk.network.SportType;
import com.coveiot.android.sportsnotificationsdk.network.SportsApiClient;
import com.coveiot.android.theme.BaseFragment;
import com.coveiot.android.theme.BottomSheetDialogOneButtonTitleMessage;
import com.coveiot.android.theme.FirebaseEventParams;
import com.coveiot.utils.utility.AppUtils;
import com.coveiot.utils.utility.LogHelper;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.ktx.Firebase;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.ktx.RemoteConfigKt;
import com.google.gson.Gson;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes7.dex */
public final class TodaysMatchFragmentSoccer extends BaseFragment implements TodaysMatchFragmentSoccerViewModel.MatchListListener, TodaysMatchAdapterSoccer.ItemClickListener, View.OnClickListener {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @Nullable
    public ArrayList<Integer> B;
    @Nullable
    public BottomSheetDialogOneButtonTitleMessage m;
    @Nullable
    public BottomSheetSportsFilter n;
    @Nullable
    public TodaysMatchFragmentSoccerViewModel o;
    @Nullable
    public MatchListModel q;
    public TodaysMatchAdapterSoccer r;
    public int s;
    @Nullable
    public Context u;
    @Nullable
    public SoccerSportsServiceNew w;
    public boolean x;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @NotNull
    public ArrayList<MatchListModel> p = new ArrayList<>();
    @NotNull
    public SimpleDateFormat t = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
    @NotNull
    public final String v = "TodaysMatchFragment";
    public int y = -1;
    public final int z = 201;
    @NotNull
    public ArrayList<MatchListModel> A = new ArrayList<>();
    @NotNull
    public final ServiceConnection C = new ServiceConnection() { // from class: com.coveiot.android.sportsnotification.fragment.TodaysMatchFragmentSoccer$mConnection$1
        @Override // android.content.ServiceConnection
        public void onServiceConnected(@NotNull ComponentName className, @NotNull IBinder service) {
            Intrinsics.checkNotNullParameter(className, "className");
            Intrinsics.checkNotNullParameter(service, "service");
            if (service instanceof SoccerSportsServiceNew.BtServiceBinder) {
                TodaysMatchFragmentSoccer.this.w = ((SoccerSportsServiceNew.BtServiceBinder) service).getService();
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(@NotNull ComponentName className) {
            Intrinsics.checkNotNullParameter(className, "className");
            TodaysMatchFragmentSoccer.this.w = null;
            TodaysMatchFragmentSoccer.this.setBound(false);
        }
    };

    /* loaded from: classes7.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final TodaysMatchFragmentSoccer newInstance() {
            return new TodaysMatchFragmentSoccer();
        }
    }

    public static final void A(FirebaseRemoteConfig remoteConfig, TodaysMatchFragmentSoccer this$0, String date, String accessToken, Void r12, Task it) {
        Intrinsics.checkNotNullParameter(remoteConfig, "$remoteConfig");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(date, "$date");
        Intrinsics.checkNotNullParameter(accessToken, "$accessToken");
        Intrinsics.checkNotNullParameter(it, "it");
        boolean z = remoteConfig.getBoolean("cricket_filter");
        boolean z2 = remoteConfig.getBoolean("match_list_filter");
        String string = remoteConfig.getString("cricket_configuration");
        Intrinsics.checkNotNullExpressionValue(string, "remoteConfig.getString(C…ts.CRICKET_CONFIGURATION)");
        CricketConfiguration cricketConfiguration = (CricketConfiguration) new Gson().fromJson(string, (Class<Object>) CricketConfiguration.class);
        String string2 = remoteConfig.getString("soccer_filte_config");
        Intrinsics.checkNotNullExpressionValue(string2, "remoteConfig.getString(C…CER_FILTER_CONFIGURATION)");
        SoccerFilterConfig soccerFilterConfigData = (SoccerFilterConfig) new Gson().fromJson(string2, (Class<Object>) SoccerFilterConfig.class);
        SportsPreference.Companion companion = SportsPreference.Companion;
        Context requireContext = this$0.requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        Intrinsics.checkNotNullExpressionValue(soccerFilterConfigData, "soccerFilterConfigData");
        companion.saveSoccerConfig(requireContext, soccerFilterConfigData);
        TodaysMatchFragmentSoccerViewModel todaysMatchFragmentSoccerViewModel = this$0.o;
        Intrinsics.checkNotNull(todaysMatchFragmentSoccerViewModel);
        todaysMatchFragmentSoccerViewModel.getScorecardList(soccerFilterConfigData, z, z2, date, accessToken, 50, 1);
        String str = this$0.v;
        LogHelper.d(str, "Config params updated: " + r12 + z);
    }

    public static final void B(TodaysMatchFragmentSoccer this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.replaceFragment();
    }

    public static final void C(TodaysMatchFragmentSoccer this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.replaceFragment();
    }

    public static final void D(TodaysMatchFragmentSoccer this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (!AppUtils.isNetConnected(this$0.u)) {
            Toast.makeText(this$0.u, R.string.please_check_network, 1).show();
        } else if (BleApiManager.getInstance(this$0.u).getBleApi().getConnectionStatus() == ConnectionStatus.CONNECTED) {
            if (this$0.q != null) {
                BaseFragment.showProgress$default(this$0, false, 1, null);
                AnalyticsLog analyticsLog = new AnalyticsLog();
                analyticsLog.setEventName(FirebaseEventParams.EventName.CRICKET_MATCH_SAVE.getValue());
                analyticsLog.setCVPrevScreenName(FirebaseEventParams.ScreenName.MAIN_HOME_DASH.getValue());
                analyticsLog.setCVScreenName(FirebaseEventParams.ScreenName.SELECT_MATCH_SCREEN.getValue());
                analyticsLog.setCTX("entitysport");
                MatchListModel matchListModel = this$0.q;
                String lowerCase = String.valueOf(matchListModel != null ? matchListModel.getMatchId() : null).toLowerCase();
                Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase()");
                analyticsLog.setMatchID(lowerCase);
                MatchListModel matchListModel2 = this$0.q;
                String lowerCase2 = String.valueOf(matchListModel2 != null ? matchListModel2.getDate() : null).toLowerCase();
                Intrinsics.checkNotNullExpressionValue(lowerCase2, "this as java.lang.String).toLowerCase()");
                analyticsLog.setMatchDate((String) StringsKt__StringsKt.split$default((CharSequence) StringsKt__StringsKt.trim(lowerCase2).toString(), new String[]{HexStringBuilder.DEFAULT_SEPARATOR}, false, 0, 6, (Object) null).get(0));
                MatchListModel matchListModel3 = this$0.q;
                String lowerCase3 = String.valueOf(matchListModel3 != null ? matchListModel3.getTeamA() : null).toLowerCase();
                Intrinsics.checkNotNullExpressionValue(lowerCase3, "this as java.lang.String).toLowerCase()");
                analyticsLog.setCricketTeam1(lowerCase3);
                MatchListModel matchListModel4 = this$0.q;
                String lowerCase4 = String.valueOf(matchListModel4 != null ? matchListModel4.getTeamB() : null).toLowerCase();
                Intrinsics.checkNotNullExpressionValue(lowerCase4, "this as java.lang.String).toLowerCase()");
                analyticsLog.setCricketTeam2(lowerCase4);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm");
                MatchListModel matchListModel5 = this$0.q;
                String lowerCase5 = String.valueOf(matchListModel5 != null ? matchListModel5.getFormattedTime() : null).toLowerCase();
                Intrinsics.checkNotNullExpressionValue(lowerCase5, "this as java.lang.String).toLowerCase()");
                Date parse = simpleDateFormat.parse(StringsKt__StringsKt.substringAfter$default(lowerCase5, Constants.SEPARATOR_COMMA, (String) null, 2, (Object) null));
                analyticsLog.setMatchStartTime(simpleDateFormat.format(parse) + ":00");
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put(FirebaseEventParams.MetaData.CV_SPORT_TYPE.getValue(), "football");
                String value = FirebaseEventParams.MetaData.CV_LEAGUE_NAME.getValue();
                MatchListModel matchListModel6 = this$0.q;
                Intrinsics.checkNotNull(matchListModel6);
                String lowerCase6 = String.valueOf(matchListModel6.getTitle()).toLowerCase();
                Intrinsics.checkNotNullExpressionValue(lowerCase6, "this as java.lang.String).toLowerCase()");
                hashMap.put(value, lowerCase6);
                analyticsLog.setMapData(hashMap);
                CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
                TodaysMatchFragmentSoccerViewModel todaysMatchFragmentSoccerViewModel = this$0.o;
                Intrinsics.checkNotNull(todaysMatchFragmentSoccerViewModel);
                Context context = this$0.u;
                Intrinsics.checkNotNull(context);
                MatchListModel matchListModel7 = this$0.q;
                Intrinsics.checkNotNull(matchListModel7);
                todaysMatchFragmentSoccerViewModel.updateSportsSettingToServer(context, matchListModel7);
            } else {
                Toast.makeText(this$0.requireContext(), R.string.no_match_selected, 1).show();
            }
            LogHelper.d("selected match", new Gson().toJson(this$0.q));
        } else {
            Toast.makeText(this$0.u, R.string.band_not_connected, 1).show();
        }
    }

    public static final void E(TodaysMatchFragmentSoccer this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.openBottomSheet();
    }

    public static final void F(TodaysMatchFragmentSoccer this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.startActivity(new Intent(this$0.getContext(), SportsDisclaimerActivity.class));
    }

    public static final void G(TodaysMatchFragmentSoccer this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intent intent = new Intent(this$0.getContext(), SportsFilterActivity.class);
        ArrayList<Integer> arrayList = this$0.B;
        if (arrayList != null) {
            intent.putExtra("extra_selected_filter_options", arrayList);
        }
        this$0.requireActivity().startActivityFromFragment(this$0, intent, this$0.z);
    }

    public static final void H(TodaysMatchFragmentSoccer this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (AppUtils.isEmpty(this$0.A)) {
            return;
        }
        ArrayList<Integer> arrayList = this$0.B;
        if (arrayList != null) {
            if (arrayList != null) {
                arrayList.clear();
            }
            int i = R.id.tv_filters_applied;
            ((TextView) this$0._$_findCachedViewById(i)).setText("0 Filters Applied");
            ((TextView) this$0._$_findCachedViewById(i)).setTextColor(this$0.requireContext().getColor(R.color.secondary_text_color));
        }
        this$0.y(this$0.A);
    }

    public static final void I(TodaysMatchFragmentSoccer this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        FragmentActivity requireActivity = this$0.requireActivity();
        Intrinsics.checkNotNull(requireActivity, "null cannot be cast to non-null type com.coveiot.android.sportsnotification.SportsNotificationActivity");
        ((SportsNotificationActivity) requireActivity).onBackPressed();
    }

    public static final void J(TodaysMatchFragmentSoccer this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.replaceFragment();
    }

    public static final void K(TodaysMatchFragmentSoccer this$0, boolean z, List list, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage = this$0.m;
        if (bottomSheetDialogOneButtonTitleMessage != null) {
            Intrinsics.checkNotNull(bottomSheetDialogOneButtonTitleMessage);
            if (bottomSheetDialogOneButtonTitleMessage.isShowing()) {
                BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage2 = this$0.m;
                Intrinsics.checkNotNull(bottomSheetDialogOneButtonTitleMessage2);
                bottomSheetDialogOneButtonTitleMessage2.dismiss();
            }
        }
        Context context = this$0.u;
        Intrinsics.checkNotNull(context);
        SportsUtils.isBoatCoinsEnabled(context, new TodaysMatchFragmentSoccer$showResultDialog$1$1(z, list, this$0));
    }

    public static final void z(final FirebaseRemoteConfig remoteConfig, final TodaysMatchFragmentSoccer this$0, final String date, final String accessToken, Task task) {
        Intrinsics.checkNotNullParameter(remoteConfig, "$remoteConfig");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(date, "$date");
        Intrinsics.checkNotNullParameter(accessToken, "$accessToken");
        Intrinsics.checkNotNullParameter(task, "task");
        if (task.isSuccessful()) {
            final Void r5 = (Void) task.getResult();
            remoteConfig.activate().addOnCompleteListener(new OnCompleteListener() { // from class: com.coveiot.android.sportsnotification.fragment.p0
                @Override // com.google.android.gms.tasks.OnCompleteListener
                public final void onComplete(Task task2) {
                    TodaysMatchFragmentSoccer.A(FirebaseRemoteConfig.this, this$0, date, accessToken, r5, task2);
                }
            });
            return;
        }
        boolean z = remoteConfig.getBoolean("cricket_filter");
        boolean z2 = remoteConfig.getBoolean("match_list_filter");
        String string = remoteConfig.getString("cricket_configuration");
        Intrinsics.checkNotNullExpressionValue(string, "remoteConfig.getString(C…ts.CRICKET_CONFIGURATION)");
        CricketConfiguration cricketConfiguration = (CricketConfiguration) new Gson().fromJson(string, (Class<Object>) CricketConfiguration.class);
        String string2 = remoteConfig.getString("soccer_filte_config");
        Intrinsics.checkNotNullExpressionValue(string2, "remoteConfig.getString(C…CER_FILTER_CONFIGURATION)");
        SoccerFilterConfig soccerFilterConfigData = (SoccerFilterConfig) new Gson().fromJson(string2, (Class<Object>) SoccerFilterConfig.class);
        SportsPreference.Companion companion = SportsPreference.Companion;
        Context requireContext = this$0.requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        Intrinsics.checkNotNullExpressionValue(soccerFilterConfigData, "soccerFilterConfigData");
        companion.saveSoccerConfig(requireContext, soccerFilterConfigData);
        TodaysMatchFragmentSoccerViewModel todaysMatchFragmentSoccerViewModel = this$0.o;
        Intrinsics.checkNotNull(todaysMatchFragmentSoccerViewModel);
        todaysMatchFragmentSoccerViewModel.getScorecardList(soccerFilterConfigData, z, z2, date, accessToken, 50, 1);
        LogHelper.e(this$0.v, "Remote Config Failed");
    }

    @Override // com.coveiot.android.theme.BaseFragment
    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    @Override // com.coveiot.android.theme.BaseFragment
    @Nullable
    public View _$_findCachedViewById(int i) {
        View findViewById;
        Map<Integer, View> map = this._$_findViewCache;
        View view = map.get(Integer.valueOf(i));
        if (view == null) {
            View view2 = getView();
            if (view2 == null || (findViewById = view2.findViewById(i)) == null) {
                return null;
            }
            map.put(Integer.valueOf(i), findViewById);
            return findViewById;
        }
        return view;
    }

    @NotNull
    public final SimpleDateFormat getDateFormat() {
        return this.t;
    }

    public final int getFILTERS_REQUEST_CODE() {
        return this.z;
    }

    @Nullable
    public final BottomSheetSportsFilter getFilterMatchDialog() {
        return this.n;
    }

    @Nullable
    public final Context getMContext() {
        return this.u;
    }

    @NotNull
    public final ArrayList<MatchListModel> getMatchListFromSever() {
        return this.A;
    }

    @Nullable
    public final ArrayList<Integer> getSelectedFilters() {
        return this.B;
    }

    public final int getSelectedMatchPos() {
        return this.y;
    }

    @Nullable
    public final BottomSheetDialogOneButtonTitleMessage getSettingSavedDialog() {
        return this.m;
    }

    @NotNull
    public final String getTAG() {
        return this.v;
    }

    @Nullable
    public final TodaysMatchFragmentSoccerViewModel getViewModel() {
        return this.o;
    }

    public final boolean isBound() {
        return this.x;
    }

    @Override // com.coveiot.android.sportsnotification.viewmodel.TodaysMatchFragmentSoccerViewModel.MatchListListener
    public void onAccessTokenFailed() {
        if (!isAdded() || getView() == null) {
            return;
        }
        ConstraintLayout constraintLayout = (ConstraintLayout) _$_findCachedViewById(R.id.ll_save);
        if (constraintLayout != null) {
            constraintLayout.setVisibility(8);
        }
        LinearLayout linearLayout = (LinearLayout) _$_findCachedViewById(R.id.ll_mathces);
        if (linearLayout != null) {
            linearLayout.setVisibility(8);
        }
        ImageView imageView = (ImageView) _$_findCachedViewById(R.id.img_no_match);
        if (imageView != null) {
            imageView.setVisibility(0);
        }
        LinearLayout linearLayout2 = (LinearLayout) _$_findCachedViewById(R.id.ll_enable_settings);
        if (linearLayout2 != null) {
            linearLayout2.setVisibility(8);
        }
        Toast.makeText(requireContext(), R.string.please_check_network_try_again, 1).show();
        dismissProgress();
    }

    @Override // com.coveiot.android.sportsnotification.viewmodel.TodaysMatchFragmentSoccerViewModel.MatchListListener
    public void onAccessTokenRecieved(@NotNull final String accessToken) {
        Intrinsics.checkNotNullParameter(accessToken, "accessToken");
        if (isAdded()) {
            dismissProgress();
            BaseFragment.showProgress$default(this, false, 1, null);
            new Date();
            Calendar calendar = Calendar.getInstance();
            calendar.add(5, 1);
            Calendar calendar2 = Calendar.getInstance();
            calendar2.add(5, -1);
            final String str = this.t.format(calendar2.getTime()) + '_' + this.t.format(calendar.getTime());
            Context requireContext = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
            new AccessTokenPreference(requireContext).saveAccessToken(accessToken);
            final FirebaseRemoteConfig remoteConfig = RemoteConfigKt.getRemoteConfig(Firebase.INSTANCE);
            remoteConfig.setDefaultsAsync(R.xml.remote_config_details);
            remoteConfig.fetch(10L).addOnCompleteListener(new OnCompleteListener() { // from class: com.coveiot.android.sportsnotification.fragment.o0
                @Override // com.google.android.gms.tasks.OnCompleteListener
                public final void onComplete(Task task) {
                    TodaysMatchFragmentSoccer.z(FirebaseRemoteConfig.this, this, str, accessToken, task);
                }
            });
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int i, int i2, @Nullable Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == this.z && i2 == -1) {
            if (intent != null) {
                if (intent.hasExtra("extra_selected_filter_options")) {
                    Bundle extras = intent.getExtras();
                    if ((extras != null ? extras.getSerializable("extra_selected_filter_options") : null) != null) {
                        Bundle extras2 = intent.getExtras();
                        Serializable serializable = extras2 != null ? extras2.getSerializable("extra_selected_filter_options") : null;
                        Intrinsics.checkNotNull(serializable, "null cannot be cast to non-null type java.util.ArrayList<kotlin.Int>{ kotlin.collections.TypeAliasesKt.ArrayList<kotlin.Int> }");
                        ArrayList<Integer> arrayList = (ArrayList) serializable;
                        SportsPreference.Companion companion = SportsPreference.Companion;
                        Context requireContext = requireContext();
                        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
                        SoccerFilterConfig soccerConfig = companion.getSoccerConfig(requireContext);
                        this.B = arrayList;
                        boolean z = true;
                        if (arrayList == null || arrayList.size() != 1) {
                            z = false;
                        }
                        if (z) {
                            TextView textView = (TextView) _$_findCachedViewById(R.id.tv_filters_applied);
                            StringBuilder sb = new StringBuilder();
                            ArrayList<Integer> arrayList2 = this.B;
                            sb.append(arrayList2 != null ? Integer.valueOf(arrayList2.size()) : null);
                            sb.append(" Filter Applied");
                            textView.setText(sb.toString());
                        } else {
                            TextView textView2 = (TextView) _$_findCachedViewById(R.id.tv_filters_applied);
                            StringBuilder sb2 = new StringBuilder();
                            ArrayList<Integer> arrayList3 = this.B;
                            sb2.append(arrayList3 != null ? Integer.valueOf(arrayList3.size()) : null);
                            sb2.append(" Filters Applied");
                            textView2.setText(sb2.toString());
                        }
                        ArrayList<Integer> arrayList4 = this.B;
                        Integer valueOf = arrayList4 != null ? Integer.valueOf(arrayList4.size()) : null;
                        Intrinsics.checkNotNull(valueOf);
                        if (valueOf.intValue() > 0) {
                            ((TextView) _$_findCachedViewById(R.id.tv_filters_applied)).setTextColor(requireContext().getColor(R.color.text_color_primary));
                        } else {
                            ((TextView) _$_findCachedViewById(R.id.tv_filters_applied)).setTextColor(requireContext().getColor(R.color.secondary_text_color));
                        }
                        TodaysMatchFragmentSoccerViewModel todaysMatchFragmentSoccerViewModel = this.o;
                        ArrayList<MatchListModel> filterMatches = todaysMatchFragmentSoccerViewModel != null ? todaysMatchFragmentSoccerViewModel.filterMatches(soccerConfig, arrayList, this.A) : null;
                        Intrinsics.checkNotNull(filterMatches);
                        y(filterMatches);
                        return;
                    }
                }
                int i3 = R.id.tv_filters_applied;
                ((TextView) _$_findCachedViewById(i3)).setText("0 Filters Applied");
                ((TextView) _$_findCachedViewById(i3)).setTextColor(requireContext().getColor(R.color.secondary_text_color));
                return;
            }
            int i4 = R.id.tv_filters_applied;
            ((TextView) _$_findCachedViewById(i4)).setText("0 Filters Applied");
            ((TextView) _$_findCachedViewById(i4)).setTextColor(requireContext().getColor(R.color.secondary_text_color));
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(@Nullable View view) {
        Intrinsics.checkNotNull(view);
        int id = view.getId();
        TodaysMatchAdapterSoccer todaysMatchAdapterSoccer = null;
        if (id == R.id.btn_reset) {
            this.s = 0;
            BottomSheetSportsFilter bottomSheetSportsFilter = this.n;
            Intrinsics.checkNotNull(bottomSheetSportsFilter);
            bottomSheetSportsFilter.getImgTest().setVisibility(8);
            BottomSheetSportsFilter bottomSheetSportsFilter2 = this.n;
            Intrinsics.checkNotNull(bottomSheetSportsFilter2);
            bottomSheetSportsFilter2.getImgOdi().setVisibility(8);
            BottomSheetSportsFilter bottomSheetSportsFilter3 = this.n;
            Intrinsics.checkNotNull(bottomSheetSportsFilter3);
            bottomSheetSportsFilter3.getImgT20().setVisibility(8);
            BottomSheetSportsFilter bottomSheetSportsFilter4 = this.n;
            Intrinsics.checkNotNull(bottomSheetSportsFilter4);
            bottomSheetSportsFilter4.dismiss();
            TodaysMatchAdapterSoccer todaysMatchAdapterSoccer2 = this.r;
            if (todaysMatchAdapterSoccer2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("todaysMatchAdapter");
            } else {
                todaysMatchAdapterSoccer = todaysMatchAdapterSoccer2;
            }
            todaysMatchAdapterSoccer.filterMatchFormat(this.s);
        } else if (id == R.id.btn_apply) {
            BottomSheetSportsFilter bottomSheetSportsFilter5 = this.n;
            Intrinsics.checkNotNull(bottomSheetSportsFilter5);
            bottomSheetSportsFilter5.dismiss();
            TodaysMatchAdapterSoccer todaysMatchAdapterSoccer3 = this.r;
            if (todaysMatchAdapterSoccer3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("todaysMatchAdapter");
            } else {
                todaysMatchAdapterSoccer = todaysMatchAdapterSoccer3;
            }
            todaysMatchAdapterSoccer.filterMatchFormat(this.s);
        } else if (id == R.id.img_close) {
            BottomSheetSportsFilter bottomSheetSportsFilter6 = this.n;
            Intrinsics.checkNotNull(bottomSheetSportsFilter6);
            bottomSheetSportsFilter6.dismiss();
        }
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        SportsApiClient.Companion.resetSportsApi(requireContext().getApplicationContext(), SportType.SOCCER);
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        return inflater.inflate(R.layout.fragment_todays_match_soccer, viewGroup, false);
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        dismissProgress();
        x();
        BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage = this.m;
        if (bottomSheetDialogOneButtonTitleMessage != null) {
            Intrinsics.checkNotNull(bottomSheetDialogOneButtonTitleMessage);
            bottomSheetDialogOneButtonTitleMessage.dismiss();
        }
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // com.coveiot.android.sportsnotification.adapter.TodaysMatchAdapterSoccer.ItemClickListener
    public void onItemClick(@NotNull MatchListModel matchListModel, int i) {
        Intrinsics.checkNotNullParameter(matchListModel, "matchListModel");
        TodaysMatchAdapterSoccer todaysMatchAdapterSoccer = this.r;
        if (todaysMatchAdapterSoccer == null) {
            Intrinsics.throwUninitializedPropertyAccessException("todaysMatchAdapter");
            todaysMatchAdapterSoccer = null;
        }
        todaysMatchAdapterSoccer.notifyAdapter(i);
        this.q = matchListModel;
        AnalyticsLog analyticsLog = new AnalyticsLog();
        analyticsLog.setEventName(FirebaseEventParams.EventName.CRICKET_MATCH_SELECT.getValue());
        analyticsLog.setCVPrevScreenName(FirebaseEventParams.ScreenName.MAIN_HOME_DASH.getValue());
        analyticsLog.setCVScreenName(FirebaseEventParams.ScreenName.SELECT_MATCH_SCREEN.getValue());
        analyticsLog.setCTX("entitysport");
        MatchListModel matchListModel2 = this.q;
        String lowerCase = String.valueOf(matchListModel2 != null ? matchListModel2.getMatchId() : null).toLowerCase();
        Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase()");
        analyticsLog.setMatchID(lowerCase);
        MatchListModel matchListModel3 = this.q;
        String lowerCase2 = String.valueOf(matchListModel3 != null ? matchListModel3.getDate() : null).toLowerCase();
        Intrinsics.checkNotNullExpressionValue(lowerCase2, "this as java.lang.String).toLowerCase()");
        analyticsLog.setMatchDate((String) StringsKt__StringsKt.split$default((CharSequence) StringsKt__StringsKt.trim(lowerCase2).toString(), new String[]{HexStringBuilder.DEFAULT_SEPARATOR}, false, 0, 6, (Object) null).get(0));
        MatchListModel matchListModel4 = this.q;
        String lowerCase3 = String.valueOf(matchListModel4 != null ? matchListModel4.getTeamA() : null).toLowerCase();
        Intrinsics.checkNotNullExpressionValue(lowerCase3, "this as java.lang.String).toLowerCase()");
        analyticsLog.setCricketTeam1(lowerCase3);
        MatchListModel matchListModel5 = this.q;
        String lowerCase4 = String.valueOf(matchListModel5 != null ? matchListModel5.getTeamB() : null).toLowerCase();
        Intrinsics.checkNotNullExpressionValue(lowerCase4, "this as java.lang.String).toLowerCase()");
        analyticsLog.setCricketTeam2(lowerCase4);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm");
        String str = this.v;
        StringBuilder sb = new StringBuilder();
        sb.append("dateformattedby: ");
        MatchListModel matchListModel6 = this.q;
        String lowerCase5 = String.valueOf(matchListModel6 != null ? matchListModel6.getFormattedTime() : null).toLowerCase();
        Intrinsics.checkNotNullExpressionValue(lowerCase5, "this as java.lang.String).toLowerCase()");
        sb.append(StringsKt__StringsKt.trim((String) StringsKt__StringsKt.split$default((CharSequence) lowerCase5, new String[]{Constants.SEPARATOR_COMMA}, false, 0, 6, (Object) null).get(0)).toString());
        Log.d(str, sb.toString());
        String str2 = this.v;
        StringBuilder sb2 = new StringBuilder();
        sb2.append("dateformattedbytime: ");
        MatchListModel matchListModel7 = this.q;
        String lowerCase6 = String.valueOf(matchListModel7 != null ? matchListModel7.getFormattedTime() : null).toLowerCase();
        Intrinsics.checkNotNullExpressionValue(lowerCase6, "this as java.lang.String).toLowerCase()");
        sb2.append(StringsKt__StringsKt.substringAfter$default(lowerCase6, Constants.SEPARATOR_COMMA, (String) null, 2, (Object) null));
        Log.d(str2, sb2.toString());
        MatchListModel matchListModel8 = this.q;
        String lowerCase7 = String.valueOf(matchListModel8 != null ? matchListModel8.getFormattedTime() : null).toLowerCase();
        Intrinsics.checkNotNullExpressionValue(lowerCase7, "this as java.lang.String).toLowerCase()");
        StringsKt__StringsKt.trim((String) StringsKt__StringsKt.split$default((CharSequence) lowerCase7, new String[]{Constants.SEPARATOR_COMMA}, false, 0, 6, (Object) null).get(0)).toString();
        MatchListModel matchListModel9 = this.q;
        String lowerCase8 = String.valueOf(matchListModel9 != null ? matchListModel9.getFormattedTime() : null).toLowerCase();
        Intrinsics.checkNotNullExpressionValue(lowerCase8, "this as java.lang.String).toLowerCase()");
        Date parse = simpleDateFormat.parse(StringsKt__StringsKt.substringAfter$default(lowerCase8, Constants.SEPARATOR_COMMA, (String) null, 2, (Object) null));
        analyticsLog.setMatchStartTime(simpleDateFormat.format(parse) + ":00");
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put(FirebaseEventParams.MetaData.CV_SPORT_TYPE.getValue(), "football");
        String value = FirebaseEventParams.MetaData.CV_LEAGUE_NAME.getValue();
        MatchListModel matchListModel10 = this.q;
        Intrinsics.checkNotNull(matchListModel10);
        String lowerCase9 = String.valueOf(matchListModel10.getTitle()).toLowerCase();
        Intrinsics.checkNotNullExpressionValue(lowerCase9, "this as java.lang.String).toLowerCase()");
        hashMap.put(value, lowerCase9);
        analyticsLog.setMapData(hashMap);
        CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
        if (this.y == i) {
            ((Button) _$_findCachedViewById(R.id.btn_save)).setEnabled(false);
        } else {
            ((Button) _$_findCachedViewById(R.id.btn_save)).setEnabled(true);
        }
    }

    @Override // com.coveiot.android.sportsnotification.viewmodel.TodaysMatchFragmentSoccerViewModel.MatchListListener
    public void onMatchListFailed() {
        if (!isAdded() || getView() == null) {
            return;
        }
        ConstraintLayout constraintLayout = (ConstraintLayout) _$_findCachedViewById(R.id.ll_save);
        if (constraintLayout != null) {
            constraintLayout.setVisibility(8);
        }
        LinearLayout linearLayout = (LinearLayout) _$_findCachedViewById(R.id.ll_mathces);
        if (linearLayout != null) {
            linearLayout.setVisibility(8);
        }
        ImageView imageView = (ImageView) _$_findCachedViewById(R.id.img_no_match);
        if (imageView != null) {
            imageView.setVisibility(0);
        }
        Toast.makeText(requireContext(), R.string.please_check_network_try_again, 1).show();
        dismissProgress();
    }

    @Override // com.coveiot.android.sportsnotification.viewmodel.TodaysMatchFragmentSoccerViewModel.MatchListListener
    public void onMatchListUpdate(@NotNull ArrayList<MatchListModel> matchList) {
        Intrinsics.checkNotNullParameter(matchList, "matchList");
        this.A = matchList;
        y(matchList);
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        logScreenViewEvent(FirebaseEventParams.ScreenName.SELECT_MATCH_SCREEN.getValue());
    }

    @Override // com.coveiot.android.sportsnotification.viewmodel.TodaysMatchFragmentSoccerViewModel.MatchListListener
    public void onSettingsSavedToServer(boolean z, @Nullable List<CoinNotifications> list) {
        if (isAdded()) {
            dismissProgress();
            if (z) {
                if (this.w != null) {
                    SportsPreference.Companion companion = SportsPreference.Companion;
                    Context requireContext = requireContext();
                    Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
                    companion.clearSelectedMatchLastState(requireContext);
                    SoccerSportsServiceNew soccerSportsServiceNew = this.w;
                    Intrinsics.checkNotNull(soccerSportsServiceNew);
                    soccerSportsServiceNew.resetUpdates();
                }
                String string = getString(R.string.setting_saved_successfully);
                Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.setting_saved_successfully)");
                String string2 = requireActivity().getResources().getString(R.string.selected_score_available_in_watch);
                Intrinsics.checkNotNullExpressionValue(string2, "requireActivity().resour…score_available_in_watch)");
                showResultDialog(string, string2, z, list);
                return;
            }
            String string3 = getString(R.string.setting_could_not_be_saved);
            Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.setting_could_not_be_saved)");
            showResultDialog(string3, "", z, list);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        this.u = requireContext();
        TodaysMatchFragmentSoccerViewModel todaysMatchFragmentSoccerViewModel = (TodaysMatchFragmentSoccerViewModel) ViewModelProviders.of(this).get(TodaysMatchFragmentSoccerViewModel.class);
        this.o = todaysMatchFragmentSoccerViewModel;
        Intrinsics.checkNotNull(todaysMatchFragmentSoccerViewModel);
        todaysMatchFragmentSoccerViewModel.setMatchListListener(this);
        new ContextThemeWrapper(this.u, R.style.ButtonThemeEnabledMonk);
        SportsApiClient.Companion.resetSportsApi(requireContext(), SportType.SOCCER);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        int i = R.id.match_rv;
        RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(i);
        Intrinsics.checkNotNull(recyclerView);
        recyclerView.setLayoutManager(linearLayoutManager);
        this.r = new TodaysMatchAdapterSoccer(getContext(), this.p, this);
        RecyclerView recyclerView2 = (RecyclerView) _$_findCachedViewById(i);
        Intrinsics.checkNotNull(recyclerView2);
        TodaysMatchAdapterSoccer todaysMatchAdapterSoccer = this.r;
        if (todaysMatchAdapterSoccer == null) {
            Intrinsics.throwUninitializedPropertyAccessException("todaysMatchAdapter");
            todaysMatchAdapterSoccer = null;
        }
        recyclerView2.setAdapter(todaysMatchAdapterSoccer);
        BaseFragment.showProgress$default(this, false, 1, null);
        Calendar calendar = Calendar.getInstance();
        Date date = new Date();
        calendar.add(5, 1);
        this.t.format(date);
        this.t.format(calendar.getTime());
        TodaysMatchFragmentSoccerViewModel todaysMatchFragmentSoccerViewModel2 = this.o;
        Intrinsics.checkNotNull(todaysMatchFragmentSoccerViewModel2);
        todaysMatchFragmentSoccerViewModel2.getAccessToken();
        w();
        int i2 = R.id.toolbar;
        ((TextView) _$_findCachedViewById(i2).findViewById(R.id.toolbar_title)).setText(getString(R.string.sport_scores));
        View _$_findCachedViewById = _$_findCachedViewById(i2);
        int i3 = R.id.share_iv;
        ImageView imageView = (ImageView) _$_findCachedViewById.findViewById(i3);
        ((ImageView) _$_findCachedViewById(i2).findViewById(i3)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.sportsnotification.fragment.u0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                TodaysMatchFragmentSoccer.B(TodaysMatchFragmentSoccer.this, view2);
            }
        });
        ((TextView) _$_findCachedViewById(R.id.tv_settings)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.sportsnotification.fragment.v0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                TodaysMatchFragmentSoccer.C(TodaysMatchFragmentSoccer.this, view2);
            }
        });
        int i4 = R.id.btn_save;
        ((Button) _$_findCachedViewById(i4)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.sportsnotification.fragment.n0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                TodaysMatchFragmentSoccer.D(TodaysMatchFragmentSoccer.this, view2);
            }
        });
        EditText editText = (EditText) _$_findCachedViewById(R.id.edt_search);
        if (editText != null) {
            editText.addTextChangedListener(new TodaysMatchFragmentSoccer$onViewCreated$4(this));
        }
        ((ImageView) _$_findCachedViewById(R.id.img_filter)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.sportsnotification.fragment.q0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                TodaysMatchFragmentSoccer.E(TodaysMatchFragmentSoccer.this, view2);
            }
        });
        int i5 = R.id.tvDisclaimer;
        ((TextView) _$_findCachedViewById(i5)).setText(Html.fromHtml(getString(R.string.disclaimer_underscore)));
        ((TextView) _$_findCachedViewById(i5)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.sportsnotification.fragment.x0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                TodaysMatchFragmentSoccer.F(TodaysMatchFragmentSoccer.this, view2);
            }
        });
        ((TextView) _$_findCachedViewById(R.id.btn_filter)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.sportsnotification.fragment.s0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                TodaysMatchFragmentSoccer.G(TodaysMatchFragmentSoccer.this, view2);
            }
        });
        ((TextView) _$_findCachedViewById(R.id.btn_reset)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.sportsnotification.fragment.w0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                TodaysMatchFragmentSoccer.H(TodaysMatchFragmentSoccer.this, view2);
            }
        });
        ((TextView) _$_findCachedViewById(i2).findViewById(R.id.toolbar_back_arrow)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.sportsnotification.fragment.t0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                TodaysMatchFragmentSoccer.I(TodaysMatchFragmentSoccer.this, view2);
            }
        });
        SportsPreference.Companion companion = SportsPreference.Companion;
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        if (companion.isNotificationEnabled(requireContext)) {
            ((Button) _$_findCachedViewById(i4)).setVisibility(0);
            ((Button) _$_findCachedViewById(i4)).setEnabled(true);
            ((LinearLayout) _$_findCachedViewById(R.id.ll_enable_settings)).setVisibility(8);
        } else {
            ((Button) _$_findCachedViewById(i4)).setVisibility(4);
            ((Button) _$_findCachedViewById(i4)).setEnabled(false);
            ((LinearLayout) _$_findCachedViewById(R.id.ll_enable_settings)).setVisibility(0);
        }
        ((LinearLayout) _$_findCachedViewById(R.id.ll_enable_settings)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.sportsnotification.fragment.r0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                TodaysMatchFragmentSoccer.J(TodaysMatchFragmentSoccer.this, view2);
            }
        });
    }

    public final void openBottomSheet() {
        if (this.n == null) {
            Context requireContext = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
            this.n = new BottomSheetSportsFilter(requireContext, this);
        }
        BottomSheetSportsFilter bottomSheetSportsFilter = this.n;
        Intrinsics.checkNotNull(bottomSheetSportsFilter);
        if (bottomSheetSportsFilter.isShowing()) {
            return;
        }
        BottomSheetSportsFilter bottomSheetSportsFilter2 = this.n;
        Intrinsics.checkNotNull(bottomSheetSportsFilter2);
        bottomSheetSportsFilter2.show();
    }

    public final void replaceFragment() {
        FragmentTransaction beginTransaction = requireActivity().getSupportFragmentManager().beginTransaction();
        int i = R.id.container;
        SportsSettingsFragmentNew.Companion companion = SportsSettingsFragmentNew.Companion;
        beginTransaction.replace(i, companion.newInstance()).addToBackStack(companion.getClass().getCanonicalName()).commit();
    }

    public final void setBound(boolean z) {
        this.x = z;
    }

    public final void setDateFormat(@NotNull SimpleDateFormat simpleDateFormat) {
        Intrinsics.checkNotNullParameter(simpleDateFormat, "<set-?>");
        this.t = simpleDateFormat;
    }

    public final void setFilterMatchDialog(@Nullable BottomSheetSportsFilter bottomSheetSportsFilter) {
        this.n = bottomSheetSportsFilter;
    }

    public final void setMContext(@Nullable Context context) {
        this.u = context;
    }

    public final void setMatchListFromSever(@NotNull ArrayList<MatchListModel> arrayList) {
        Intrinsics.checkNotNullParameter(arrayList, "<set-?>");
        this.A = arrayList;
    }

    public final void setSelectedFilters(@Nullable ArrayList<Integer> arrayList) {
        this.B = arrayList;
    }

    public final void setSelectedMatchPos(int i) {
        this.y = i;
    }

    public final void setSettingSavedDialog(@Nullable BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage) {
        this.m = bottomSheetDialogOneButtonTitleMessage;
    }

    public final void setViewModel(@Nullable TodaysMatchFragmentSoccerViewModel todaysMatchFragmentSoccerViewModel) {
        this.o = todaysMatchFragmentSoccerViewModel;
    }

    public final void showResultDialog(@NotNull String title, @NotNull String message, final boolean z, @Nullable final List<CoinNotifications> list) {
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(message, "message");
        if (this.m == null) {
            Context requireContext = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
            BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage = new BottomSheetDialogOneButtonTitleMessage(requireContext, title, message);
            this.m = bottomSheetDialogOneButtonTitleMessage;
            Intrinsics.checkNotNull(bottomSheetDialogOneButtonTitleMessage);
            String string = getString(com.coveiot.android.theme.R.string.ok);
            Intrinsics.checkNotNullExpressionValue(string, "getString(com.coveiot.android.theme.R.string.ok)");
            bottomSheetDialogOneButtonTitleMessage.setPositiveButton(string, new View.OnClickListener() { // from class: com.coveiot.android.sportsnotification.fragment.y0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    TodaysMatchFragmentSoccer.K(TodaysMatchFragmentSoccer.this, z, list, view);
                }
            });
            BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage2 = this.m;
            Intrinsics.checkNotNull(bottomSheetDialogOneButtonTitleMessage2);
            bottomSheetDialogOneButtonTitleMessage2.setCancelable(false);
            BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage3 = this.m;
            Intrinsics.checkNotNull(bottomSheetDialogOneButtonTitleMessage3);
            bottomSheetDialogOneButtonTitleMessage3.setCancelableOutside(false);
        }
        if (isAdded()) {
            BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage4 = this.m;
            Intrinsics.checkNotNull(bottomSheetDialogOneButtonTitleMessage4);
            if (bottomSheetDialogOneButtonTitleMessage4.isShowing()) {
                return;
            }
            BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage5 = this.m;
            Intrinsics.checkNotNull(bottomSheetDialogOneButtonTitleMessage5);
            bottomSheetDialogOneButtonTitleMessage5.show();
        }
    }

    public final void w() {
        Intent intent = new Intent(requireContext(), SoccerSportsServiceNew.class);
        Context requireContext = requireContext();
        ServiceConnection serviceConnection = this.C;
        Intrinsics.checkNotNull(serviceConnection);
        requireContext.bindService(intent, serviceConnection, 1);
        this.x = true;
    }

    public final void x() {
        try {
            if (this.x) {
                requireContext().unbindService(this.C);
                this.x = false;
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    public final void y(ArrayList<MatchListModel> arrayList) {
        Calendar calendar;
        if (isAdded()) {
            this.p = arrayList;
            if (arrayList.size() > 0) {
                ConstraintLayout constraintLayout = (ConstraintLayout) _$_findCachedViewById(R.id.ll_save);
                if (constraintLayout != null) {
                    constraintLayout.setVisibility(0);
                }
                RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(R.id.match_rv);
                if (recyclerView != null) {
                    recyclerView.setVisibility(0);
                }
                ImageView imageView = (ImageView) _$_findCachedViewById(R.id.img_no_match);
                if (imageView != null) {
                    imageView.setVisibility(8);
                }
                TextView textView = (TextView) _$_findCachedViewById(R.id.txt_no_match);
                if (textView != null) {
                    textView.setVisibility(8);
                }
            } else {
                ConstraintLayout constraintLayout2 = (ConstraintLayout) _$_findCachedViewById(R.id.ll_save);
                if (constraintLayout2 != null) {
                    constraintLayout2.setVisibility(8);
                }
                RecyclerView recyclerView2 = (RecyclerView) _$_findCachedViewById(R.id.match_rv);
                if (recyclerView2 != null) {
                    recyclerView2.setVisibility(8);
                }
                ImageView imageView2 = (ImageView) _$_findCachedViewById(R.id.img_no_match);
                if (imageView2 != null) {
                    imageView2.setVisibility(0);
                }
                TextView textView2 = (TextView) _$_findCachedViewById(R.id.txt_no_match);
                if (textView2 != null) {
                    textView2.setVisibility(0);
                }
            }
            dismissProgress();
            TodaysMatchAdapterSoccer todaysMatchAdapterSoccer = this.r;
            if (todaysMatchAdapterSoccer == null) {
                Intrinsics.throwUninitializedPropertyAccessException("todaysMatchAdapter");
                todaysMatchAdapterSoccer = null;
            }
            todaysMatchAdapterSoccer.notifyData(arrayList);
            SportsPreference.Companion companion = SportsPreference.Companion;
            Context requireContext = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
            MatchListModel selectedSoccerMatch = companion.getSelectedSoccerMatch(requireContext);
            if (selectedSoccerMatch != null) {
                TodaysMatchFragmentSoccerViewModel todaysMatchFragmentSoccerViewModel = this.o;
                Intrinsics.checkNotNull(todaysMatchFragmentSoccerViewModel);
                int positionOfSelectedMatch = todaysMatchFragmentSoccerViewModel.getPositionOfSelectedMatch(selectedSoccerMatch, arrayList);
                this.y = positionOfSelectedMatch;
                if (positionOfSelectedMatch != -1) {
                    TodaysMatchAdapterSoccer todaysMatchAdapterSoccer2 = this.r;
                    if (todaysMatchAdapterSoccer2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("todaysMatchAdapter");
                        todaysMatchAdapterSoccer2 = null;
                    }
                    todaysMatchAdapterSoccer2.notifyAdapter(this.y);
                } else {
                    Context requireContext2 = requireContext();
                    Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
                    companion.clearSelectedMatch(requireContext2);
                }
            }
            int size = arrayList.size();
            int i = 0;
            while (true) {
                if (i >= size) {
                    i = 0;
                    break;
                }
                MatchListModel matchListModel = arrayList.get(i);
                Intrinsics.checkNotNullExpressionValue(matchListModel, "matchList.get(i)");
                SportsUtils sportsUtils = SportsUtils.INSTANCE;
                String date = matchListModel.getDate();
                Intrinsics.checkNotNull(date);
                Calendar startTime = sportsUtils.getStartTime(date);
                if (i != 0) {
                    String date2 = arrayList.get(i - 1).getDate();
                    Intrinsics.checkNotNull(date2);
                    calendar = sportsUtils.getStartTime(date2);
                } else {
                    calendar = null;
                }
                if (Calendar.getInstance().getTimeInMillis() > startTime.getTimeInMillis()) {
                    i++;
                } else if (calendar != null && Calendar.getInstance().getTimeInMillis() - calendar.getTimeInMillis() < startTime.getTimeInMillis() - Calendar.getInstance().getTimeInMillis()) {
                    i--;
                }
            }
            ((RecyclerView) _$_findCachedViewById(R.id.match_rv)).scrollToPosition(i);
            Button button = (Button) _$_findCachedViewById(R.id.btn_save);
            if (button == null) {
                return;
            }
            button.setEnabled(false);
        }
    }
}
