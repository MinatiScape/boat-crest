package com.coveiot.android.sportsnotification.fragment;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.text.Html;
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
import com.coveiot.android.sportsnotification.SportsDisclaimerActivity;
import com.coveiot.android.sportsnotification.SportsNotificationActivity;
import com.coveiot.android.sportsnotification.SportsPreference;
import com.coveiot.android.sportsnotification.SportsService;
import com.coveiot.android.sportsnotification.SportsUtils;
import com.coveiot.android.sportsnotification.adapter.TodaysMatchAdapter;
import com.coveiot.android.sportsnotification.fragment.SportsSettingsFragmentNew;
import com.coveiot.android.sportsnotification.model.CoinNotifications;
import com.coveiot.android.sportsnotification.model.CricketConfiguration;
import com.coveiot.android.sportsnotification.model.MatchListModel;
import com.coveiot.android.sportsnotification.viewmodel.TodaysMatchFragmentViewModel;
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
public final class TodaysMatchFragment extends BaseFragment implements TodaysMatchFragmentViewModel.MatchListListener, TodaysMatchAdapter.ItemClickListener, View.OnClickListener {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @Nullable
    public BottomSheetDialogOneButtonTitleMessage m;
    @Nullable
    public BottomSheetSportsFilter n;
    @Nullable
    public TodaysMatchFragmentViewModel o;
    @Nullable
    public MatchListModel q;
    public TodaysMatchAdapter r;
    public int s;
    @Nullable
    public Context u;
    @Nullable
    public SportsService w;
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
    @NotNull
    public final ServiceConnection z = new ServiceConnection() { // from class: com.coveiot.android.sportsnotification.fragment.TodaysMatchFragment$mConnection$1
        @Override // android.content.ServiceConnection
        public void onServiceConnected(@NotNull ComponentName className, @NotNull IBinder service) {
            Intrinsics.checkNotNullParameter(className, "className");
            Intrinsics.checkNotNullParameter(service, "service");
            if (service instanceof SportsService.BtServiceBinder) {
                TodaysMatchFragment.this.w = ((SportsService.BtServiceBinder) service).getService();
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(@NotNull ComponentName className) {
            Intrinsics.checkNotNullParameter(className, "className");
            TodaysMatchFragment.this.w = null;
            TodaysMatchFragment.this.setBound(false);
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
        public final TodaysMatchFragment newInstance() {
            return new TodaysMatchFragment();
        }
    }

    public static final void A(TodaysMatchFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.replaceFragment();
    }

    public static final void B(TodaysMatchFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ((TextView) this$0._$_findCachedViewById(R.id.tv_settings)).performClick();
    }

    public static final void C(TodaysMatchFragment this$0, View view) {
        String leagueName;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (!AppUtils.isNetConnected(this$0.u)) {
            Toast.makeText(this$0.u, R.string.please_check_network, 1).show();
        } else if (BleApiManager.getInstance(this$0.u).getBleApi().getConnectionStatus() == ConnectionStatus.CONNECTED) {
            if (this$0.q != null) {
                String str = null;
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
                SportsUtils sportsUtils = SportsUtils.INSTANCE;
                MatchListModel matchListModel5 = this$0.q;
                Integer matchFormat = matchListModel5 != null ? matchListModel5.getMatchFormat() : null;
                Intrinsics.checkNotNull(matchFormat);
                String lowerCase5 = sportsUtils.getMatchFormat(matchFormat.intValue()).toLowerCase();
                Intrinsics.checkNotNullExpressionValue(lowerCase5, "this as java.lang.String).toLowerCase()");
                analyticsLog.setMatchType(lowerCase5);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm");
                MatchListModel matchListModel6 = this$0.q;
                String lowerCase6 = String.valueOf(matchListModel6 != null ? matchListModel6.getFormattedTime() : null).toLowerCase();
                Intrinsics.checkNotNullExpressionValue(lowerCase6, "this as java.lang.String).toLowerCase()");
                Date parse = simpleDateFormat.parse(StringsKt__StringsKt.substringAfter$default(lowerCase6, Constants.SEPARATOR_COMMA, (String) null, 2, (Object) null));
                analyticsLog.setMatchStartTime(simpleDateFormat.format(parse) + ":00");
                HashMap<String, String> hashMap = new HashMap<>();
                String value = FirebaseEventParams.MetaData.CV_SPORT_TYPE.getValue();
                String lowerCase7 = SportType.CRICKET.toString().toLowerCase();
                Intrinsics.checkNotNullExpressionValue(lowerCase7, "this as java.lang.String).toLowerCase()");
                hashMap.put(value, lowerCase7);
                String value2 = FirebaseEventParams.MetaData.CV_LEAGUE_NAME.getValue();
                MatchListModel matchListModel7 = this$0.q;
                if (matchListModel7 != null && (leagueName = matchListModel7.getLeagueName()) != null) {
                    str = leagueName.toLowerCase();
                    Intrinsics.checkNotNullExpressionValue(str, "this as java.lang.String).toLowerCase()");
                }
                Intrinsics.checkNotNull(str);
                hashMap.put(value2, str);
                analyticsLog.setMapData(hashMap);
                CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
                TodaysMatchFragmentViewModel todaysMatchFragmentViewModel = this$0.o;
                Intrinsics.checkNotNull(todaysMatchFragmentViewModel);
                Context context = this$0.u;
                Intrinsics.checkNotNull(context);
                MatchListModel matchListModel8 = this$0.q;
                Intrinsics.checkNotNull(matchListModel8);
                todaysMatchFragmentViewModel.updateSportsSettingToServer(context, matchListModel8);
            } else {
                Toast.makeText(this$0.requireContext(), R.string.no_match_selected, 1).show();
            }
            LogHelper.d("selected match", new Gson().toJson(this$0.q));
        } else {
            Toast.makeText(this$0.u, R.string.band_not_connected, 1).show();
        }
    }

    public static final void D(TodaysMatchFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.openBottomSheet();
    }

    public static final void E(TodaysMatchFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.startActivity(new Intent(this$0.getContext(), SportsDisclaimerActivity.class));
    }

    public static final void F(TodaysMatchFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        FragmentActivity requireActivity = this$0.requireActivity();
        Intrinsics.checkNotNull(requireActivity, "null cannot be cast to non-null type com.coveiot.android.sportsnotification.SportsNotificationActivity");
        ((SportsNotificationActivity) requireActivity).onBackPressed();
    }

    public static final void G(TodaysMatchFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.replaceFragment();
    }

    public static final void H(TodaysMatchFragment this$0, boolean z, List list, View view) {
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
        SportsUtils.isBoatCoinsEnabled(context, new TodaysMatchFragment$showResultDialog$1$1(z, list, this$0));
    }

    public static final void x(final FirebaseRemoteConfig remoteConfig, final TodaysMatchFragment this$0, final String date, final String accessToken, Task task) {
        Intrinsics.checkNotNullParameter(remoteConfig, "$remoteConfig");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(date, "$date");
        Intrinsics.checkNotNullParameter(accessToken, "$accessToken");
        Intrinsics.checkNotNullParameter(task, "task");
        if (task.isSuccessful()) {
            final Void r5 = (Void) task.getResult();
            remoteConfig.activate().addOnCompleteListener(new OnCompleteListener() { // from class: com.coveiot.android.sportsnotification.fragment.b0
                @Override // com.google.android.gms.tasks.OnCompleteListener
                public final void onComplete(Task task2) {
                    TodaysMatchFragment.y(FirebaseRemoteConfig.this, this$0, date, accessToken, r5, task2);
                }
            });
            return;
        }
        boolean z = remoteConfig.getBoolean("cricket_filter");
        boolean z2 = remoteConfig.getBoolean("match_list_filter");
        String string = remoteConfig.getString("cricket_configuration");
        Intrinsics.checkNotNullExpressionValue(string, "remoteConfig.getString(C…ts.CRICKET_CONFIGURATION)");
        TodaysMatchFragmentViewModel todaysMatchFragmentViewModel = this$0.o;
        Intrinsics.checkNotNull(todaysMatchFragmentViewModel);
        todaysMatchFragmentViewModel.getScorecardList((CricketConfiguration) new Gson().fromJson(string, (Class<Object>) CricketConfiguration.class), z, z2, date, accessToken, 50, 1);
        LogHelper.e(this$0.v, "Remote Config Failed");
    }

    public static final void y(FirebaseRemoteConfig remoteConfig, TodaysMatchFragment this$0, String date, String accessToken, Void r12, Task it) {
        Intrinsics.checkNotNullParameter(remoteConfig, "$remoteConfig");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(date, "$date");
        Intrinsics.checkNotNullParameter(accessToken, "$accessToken");
        Intrinsics.checkNotNullParameter(it, "it");
        boolean z = remoteConfig.getBoolean("cricket_filter");
        boolean z2 = remoteConfig.getBoolean("match_list_filter");
        String string = remoteConfig.getString("cricket_configuration");
        Intrinsics.checkNotNullExpressionValue(string, "remoteConfig.getString(C…ts.CRICKET_CONFIGURATION)");
        TodaysMatchFragmentViewModel todaysMatchFragmentViewModel = this$0.o;
        Intrinsics.checkNotNull(todaysMatchFragmentViewModel);
        todaysMatchFragmentViewModel.getScorecardList((CricketConfiguration) new Gson().fromJson(string, (Class<Object>) CricketConfiguration.class), z, z2, date, accessToken, 50, 1);
        String str = this$0.v;
        LogHelper.d(str, "Config params updated: " + r12 + z);
    }

    public static final void z(TodaysMatchFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.replaceFragment();
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

    @Nullable
    public final BottomSheetSportsFilter getFilterMatchDialog() {
        return this.n;
    }

    @Nullable
    public final Context getMContext() {
        return this.u;
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
    public final TodaysMatchFragmentViewModel getViewModel() {
        return this.o;
    }

    public final boolean isBound() {
        return this.x;
    }

    @Override // com.coveiot.android.sportsnotification.viewmodel.TodaysMatchFragmentViewModel.MatchListListener
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

    @Override // com.coveiot.android.sportsnotification.viewmodel.TodaysMatchFragmentViewModel.MatchListListener
    public void onAccessTokenRecieved(@NotNull final String accessToken) {
        Intrinsics.checkNotNullParameter(accessToken, "accessToken");
        if (isAdded()) {
            dismissProgress();
            BaseFragment.showProgress$default(this, false, 1, null);
            Date date = new Date();
            Calendar calendar = Calendar.getInstance();
            calendar.add(5, 1);
            final String str = this.t.format(date) + '_' + this.t.format(calendar.getTime());
            Context requireContext = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
            new AccessTokenPreference(requireContext).saveAccessToken(accessToken);
            final FirebaseRemoteConfig remoteConfig = RemoteConfigKt.getRemoteConfig(Firebase.INSTANCE);
            remoteConfig.setDefaultsAsync(R.xml.remote_config_details);
            remoteConfig.fetch(10L).addOnCompleteListener(new OnCompleteListener() { // from class: com.coveiot.android.sportsnotification.fragment.k0
                @Override // com.google.android.gms.tasks.OnCompleteListener
                public final void onComplete(Task task) {
                    TodaysMatchFragment.x(FirebaseRemoteConfig.this, this, str, accessToken, task);
                }
            });
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(@Nullable View view) {
        Intrinsics.checkNotNull(view);
        int id = view.getId();
        if (id == R.id.rl_test) {
            this.s = 2;
            BottomSheetSportsFilter bottomSheetSportsFilter = this.n;
            Intrinsics.checkNotNull(bottomSheetSportsFilter);
            bottomSheetSportsFilter.getImgTest().setVisibility(0);
            BottomSheetSportsFilter bottomSheetSportsFilter2 = this.n;
            Intrinsics.checkNotNull(bottomSheetSportsFilter2);
            bottomSheetSportsFilter2.getImgOdi().setVisibility(8);
            BottomSheetSportsFilter bottomSheetSportsFilter3 = this.n;
            Intrinsics.checkNotNull(bottomSheetSportsFilter3);
            bottomSheetSportsFilter3.getImgT20().setVisibility(8);
        } else if (id == R.id.rl_odi) {
            this.s = 1;
            BottomSheetSportsFilter bottomSheetSportsFilter4 = this.n;
            Intrinsics.checkNotNull(bottomSheetSportsFilter4);
            bottomSheetSportsFilter4.getImgTest().setVisibility(8);
            BottomSheetSportsFilter bottomSheetSportsFilter5 = this.n;
            Intrinsics.checkNotNull(bottomSheetSportsFilter5);
            bottomSheetSportsFilter5.getImgOdi().setVisibility(0);
            BottomSheetSportsFilter bottomSheetSportsFilter6 = this.n;
            Intrinsics.checkNotNull(bottomSheetSportsFilter6);
            bottomSheetSportsFilter6.getImgT20().setVisibility(8);
        } else if (id == R.id.rl_t20) {
            this.s = 3;
            BottomSheetSportsFilter bottomSheetSportsFilter7 = this.n;
            Intrinsics.checkNotNull(bottomSheetSportsFilter7);
            bottomSheetSportsFilter7.getImgTest().setVisibility(8);
            BottomSheetSportsFilter bottomSheetSportsFilter8 = this.n;
            Intrinsics.checkNotNull(bottomSheetSportsFilter8);
            bottomSheetSportsFilter8.getImgOdi().setVisibility(8);
            BottomSheetSportsFilter bottomSheetSportsFilter9 = this.n;
            Intrinsics.checkNotNull(bottomSheetSportsFilter9);
            bottomSheetSportsFilter9.getImgT20().setVisibility(0);
        } else {
            TodaysMatchAdapter todaysMatchAdapter = null;
            if (id == R.id.btn_reset) {
                ((EditText) _$_findCachedViewById(R.id.edt_search)).setText("");
                this.s = 0;
                BottomSheetSportsFilter bottomSheetSportsFilter10 = this.n;
                Intrinsics.checkNotNull(bottomSheetSportsFilter10);
                bottomSheetSportsFilter10.getImgTest().setVisibility(8);
                BottomSheetSportsFilter bottomSheetSportsFilter11 = this.n;
                Intrinsics.checkNotNull(bottomSheetSportsFilter11);
                bottomSheetSportsFilter11.getImgOdi().setVisibility(8);
                BottomSheetSportsFilter bottomSheetSportsFilter12 = this.n;
                Intrinsics.checkNotNull(bottomSheetSportsFilter12);
                bottomSheetSportsFilter12.getImgT20().setVisibility(8);
                BottomSheetSportsFilter bottomSheetSportsFilter13 = this.n;
                Intrinsics.checkNotNull(bottomSheetSportsFilter13);
                bottomSheetSportsFilter13.dismiss();
                TodaysMatchAdapter todaysMatchAdapter2 = this.r;
                if (todaysMatchAdapter2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("todaysMatchAdapter");
                } else {
                    todaysMatchAdapter = todaysMatchAdapter2;
                }
                todaysMatchAdapter.filterMatchFormat(this.s);
            } else if (id == R.id.btn_apply) {
                ((EditText) _$_findCachedViewById(R.id.edt_search)).setText("");
                BottomSheetSportsFilter bottomSheetSportsFilter14 = this.n;
                Intrinsics.checkNotNull(bottomSheetSportsFilter14);
                bottomSheetSportsFilter14.dismiss();
                TodaysMatchAdapter todaysMatchAdapter3 = this.r;
                if (todaysMatchAdapter3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("todaysMatchAdapter");
                } else {
                    todaysMatchAdapter = todaysMatchAdapter3;
                }
                todaysMatchAdapter.filterMatchFormat(this.s);
            } else if (id == R.id.img_close) {
                BottomSheetSportsFilter bottomSheetSportsFilter15 = this.n;
                Intrinsics.checkNotNull(bottomSheetSportsFilter15);
                bottomSheetSportsFilter15.dismiss();
            }
        }
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        SportsApiClient.Companion.resetSportsApi(requireContext().getApplicationContext(), SportType.CRICKET);
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        return inflater.inflate(R.layout.fragment_todays_match, viewGroup, false);
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        dismissProgress();
        w();
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

    @Override // com.coveiot.android.sportsnotification.adapter.TodaysMatchAdapter.ItemClickListener
    public void onItemClick(@NotNull MatchListModel matchListModel, int i) {
        String leagueName;
        Intrinsics.checkNotNullParameter(matchListModel, "matchListModel");
        TodaysMatchAdapter todaysMatchAdapter = this.r;
        String str = null;
        if (todaysMatchAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("todaysMatchAdapter");
            todaysMatchAdapter = null;
        }
        todaysMatchAdapter.notifyAdapter(i);
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
        SportsUtils sportsUtils = SportsUtils.INSTANCE;
        MatchListModel matchListModel6 = this.q;
        Integer matchFormat = matchListModel6 != null ? matchListModel6.getMatchFormat() : null;
        Intrinsics.checkNotNull(matchFormat);
        String lowerCase5 = sportsUtils.getMatchFormat(matchFormat.intValue()).toLowerCase();
        Intrinsics.checkNotNullExpressionValue(lowerCase5, "this as java.lang.String).toLowerCase()");
        analyticsLog.setMatchType(lowerCase5);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm");
        MatchListModel matchListModel7 = this.q;
        String lowerCase6 = String.valueOf(matchListModel7 != null ? matchListModel7.getFormattedTime() : null).toLowerCase();
        Intrinsics.checkNotNullExpressionValue(lowerCase6, "this as java.lang.String).toLowerCase()");
        Date parse = simpleDateFormat.parse(StringsKt__StringsKt.substringAfter$default(lowerCase6, Constants.SEPARATOR_COMMA, (String) null, 2, (Object) null));
        analyticsLog.setMatchStartTime(simpleDateFormat.format(parse) + ":00");
        HashMap<String, String> hashMap = new HashMap<>();
        String value = FirebaseEventParams.MetaData.CV_SPORT_TYPE.getValue();
        String lowerCase7 = SportType.CRICKET.toString().toLowerCase();
        Intrinsics.checkNotNullExpressionValue(lowerCase7, "this as java.lang.String).toLowerCase()");
        hashMap.put(value, lowerCase7);
        String value2 = FirebaseEventParams.MetaData.CV_LEAGUE_NAME.getValue();
        MatchListModel matchListModel8 = this.q;
        if (matchListModel8 != null && (leagueName = matchListModel8.getLeagueName()) != null) {
            str = leagueName.toLowerCase();
            Intrinsics.checkNotNullExpressionValue(str, "this as java.lang.String).toLowerCase()");
        }
        Intrinsics.checkNotNull(str);
        hashMap.put(value2, str);
        analyticsLog.setMapData(hashMap);
        CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
        if (this.y == i) {
            ((Button) _$_findCachedViewById(R.id.btn_save)).setEnabled(false);
        } else {
            ((Button) _$_findCachedViewById(R.id.btn_save)).setEnabled(true);
        }
    }

    @Override // com.coveiot.android.sportsnotification.viewmodel.TodaysMatchFragmentViewModel.MatchListListener
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

    @Override // com.coveiot.android.sportsnotification.viewmodel.TodaysMatchFragmentViewModel.MatchListListener
    public void onMatchListUpdate(@NotNull ArrayList<MatchListModel> matchList) {
        Intrinsics.checkNotNullParameter(matchList, "matchList");
        if (isAdded()) {
            this.p = matchList;
            if (matchList.size() > 0) {
                ConstraintLayout constraintLayout = (ConstraintLayout) _$_findCachedViewById(R.id.ll_save);
                if (constraintLayout != null) {
                    constraintLayout.setVisibility(0);
                }
                LinearLayout linearLayout = (LinearLayout) _$_findCachedViewById(R.id.ll_mathces);
                if (linearLayout != null) {
                    linearLayout.setVisibility(0);
                }
                ConstraintLayout constraintLayout2 = (ConstraintLayout) _$_findCachedViewById(R.id.settings_toolbar);
                if (constraintLayout2 != null) {
                    constraintLayout2.setVisibility(0);
                }
                Button button = (Button) _$_findCachedViewById(R.id.btn_view_settings);
                if (button != null) {
                    button.setVisibility(8);
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
                ConstraintLayout constraintLayout3 = (ConstraintLayout) _$_findCachedViewById(R.id.ll_save);
                if (constraintLayout3 != null) {
                    constraintLayout3.setVisibility(8);
                }
                LinearLayout linearLayout2 = (LinearLayout) _$_findCachedViewById(R.id.ll_mathces);
                if (linearLayout2 != null) {
                    linearLayout2.setVisibility(8);
                }
                ConstraintLayout constraintLayout4 = (ConstraintLayout) _$_findCachedViewById(R.id.settings_toolbar);
                if (constraintLayout4 != null) {
                    constraintLayout4.setVisibility(8);
                }
                Button button2 = (Button) _$_findCachedViewById(R.id.btn_view_settings);
                if (button2 != null) {
                    button2.setVisibility(0);
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
            TodaysMatchAdapter todaysMatchAdapter = this.r;
            TodaysMatchAdapter todaysMatchAdapter2 = null;
            if (todaysMatchAdapter == null) {
                Intrinsics.throwUninitializedPropertyAccessException("todaysMatchAdapter");
                todaysMatchAdapter = null;
            }
            todaysMatchAdapter.notifyData(matchList);
            SportsPreference.Companion companion = SportsPreference.Companion;
            Context requireContext = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
            MatchListModel selectedMatch = companion.getSelectedMatch(requireContext);
            if (selectedMatch != null) {
                TodaysMatchFragmentViewModel todaysMatchFragmentViewModel = this.o;
                Intrinsics.checkNotNull(todaysMatchFragmentViewModel);
                int positionOfSelectedMatch = todaysMatchFragmentViewModel.getPositionOfSelectedMatch(selectedMatch, matchList);
                this.y = positionOfSelectedMatch;
                if (positionOfSelectedMatch != -1) {
                    TodaysMatchAdapter todaysMatchAdapter3 = this.r;
                    if (todaysMatchAdapter3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("todaysMatchAdapter");
                    } else {
                        todaysMatchAdapter2 = todaysMatchAdapter3;
                    }
                    todaysMatchAdapter2.notifyAdapter(this.y);
                } else {
                    Context requireContext2 = requireContext();
                    Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
                    companion.clearSelectedMatch(requireContext2);
                }
            }
            Button button3 = (Button) _$_findCachedViewById(R.id.btn_save);
            if (button3 == null) {
                return;
            }
            button3.setEnabled(false);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        logScreenViewEvent(FirebaseEventParams.ScreenName.SELECT_MATCH_SCREEN.getValue());
    }

    @Override // com.coveiot.android.sportsnotification.viewmodel.TodaysMatchFragmentViewModel.MatchListListener
    public void onSettingsSavedToServer(boolean z, @Nullable List<CoinNotifications> list) {
        if (isAdded()) {
            dismissProgress();
            if (z) {
                if (this.w != null) {
                    SportsPreference.Companion companion = SportsPreference.Companion;
                    Context requireContext = requireContext();
                    Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
                    companion.clearSelectedMatchLastState(requireContext);
                    SportsService sportsService = this.w;
                    Intrinsics.checkNotNull(sportsService);
                    sportsService.resetUpdates();
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
        TodaysMatchFragmentViewModel todaysMatchFragmentViewModel = (TodaysMatchFragmentViewModel) ViewModelProviders.of(this).get(TodaysMatchFragmentViewModel.class);
        this.o = todaysMatchFragmentViewModel;
        Intrinsics.checkNotNull(todaysMatchFragmentViewModel);
        todaysMatchFragmentViewModel.setMatchListListener(this);
        new ContextThemeWrapper(this.u, R.style.ButtonThemeEnabledMonk);
        SportsApiClient.Companion.resetSportsApi(requireContext(), SportType.CRICKET);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        int i = R.id.match_rv;
        RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(i);
        Intrinsics.checkNotNull(recyclerView);
        recyclerView.setLayoutManager(linearLayoutManager);
        this.r = new TodaysMatchAdapter(getContext(), this.p, this);
        RecyclerView recyclerView2 = (RecyclerView) _$_findCachedViewById(i);
        Intrinsics.checkNotNull(recyclerView2);
        TodaysMatchAdapter todaysMatchAdapter = this.r;
        if (todaysMatchAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("todaysMatchAdapter");
            todaysMatchAdapter = null;
        }
        recyclerView2.setAdapter(todaysMatchAdapter);
        BaseFragment.showProgress$default(this, false, 1, null);
        Calendar calendar = Calendar.getInstance();
        Date date = new Date();
        calendar.add(5, 1);
        this.t.format(date);
        this.t.format(calendar.getTime());
        TodaysMatchFragmentViewModel todaysMatchFragmentViewModel2 = this.o;
        Intrinsics.checkNotNull(todaysMatchFragmentViewModel2);
        todaysMatchFragmentViewModel2.getAccessToken();
        v();
        SportsUtils sportsUtils = SportsUtils.INSTANCE;
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        if (!sportsUtils.isDeviceSupportsSoccerSportsSettings(requireContext)) {
            ((ImageView) _$_findCachedViewById(R.id.img_no_match)).setImageResource(R.drawable.img_no_matches_cricket);
        } else {
            ((ImageView) _$_findCachedViewById(R.id.img_no_match)).setImageResource(R.drawable.img_no_matches);
        }
        int i2 = R.id.toolbar;
        ((TextView) _$_findCachedViewById(i2).findViewById(R.id.toolbar_title)).setText(getString(R.string.sports_score));
        ((ImageView) _$_findCachedViewById(i2).findViewById(R.id.ivButton)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.sportsnotification.fragment.f0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                TodaysMatchFragment.z(TodaysMatchFragment.this, view2);
            }
        });
        ((TextView) _$_findCachedViewById(R.id.tv_settings)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.sportsnotification.fragment.h0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                TodaysMatchFragment.A(TodaysMatchFragment.this, view2);
            }
        });
        ((Button) _$_findCachedViewById(R.id.btn_view_settings)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.sportsnotification.fragment.a0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                TodaysMatchFragment.B(TodaysMatchFragment.this, view2);
            }
        });
        int i3 = R.id.btn_save;
        ((Button) _$_findCachedViewById(i3)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.sportsnotification.fragment.d0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                TodaysMatchFragment.C(TodaysMatchFragment.this, view2);
            }
        });
        EditText editText = (EditText) _$_findCachedViewById(R.id.edt_search);
        if (editText != null) {
            editText.addTextChangedListener(new TodaysMatchFragment$onViewCreated$5(this));
        }
        ((ImageView) _$_findCachedViewById(R.id.img_filter)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.sportsnotification.fragment.g0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                TodaysMatchFragment.D(TodaysMatchFragment.this, view2);
            }
        });
        int i4 = R.id.tvDisclaimer;
        ((TextView) _$_findCachedViewById(i4)).setText(Html.fromHtml(getString(R.string.disclaimer_underscore)));
        ((TextView) _$_findCachedViewById(i4)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.sportsnotification.fragment.e0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                TodaysMatchFragment.E(TodaysMatchFragment.this, view2);
            }
        });
        ((TextView) _$_findCachedViewById(i2).findViewById(R.id.toolbar_back_arrow)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.sportsnotification.fragment.c0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                TodaysMatchFragment.F(TodaysMatchFragment.this, view2);
            }
        });
        SportsPreference.Companion companion = SportsPreference.Companion;
        Context requireContext2 = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
        if (companion.isNotificationEnabled(requireContext2)) {
            ((Button) _$_findCachedViewById(i3)).setVisibility(0);
            ((TextView) _$_findCachedViewById(R.id.tv_slect_one_match_info)).setVisibility(0);
            ((LinearLayout) _$_findCachedViewById(R.id.ll_enable_settings)).setVisibility(8);
        } else {
            ((Button) _$_findCachedViewById(i3)).setVisibility(8);
            ((TextView) _$_findCachedViewById(R.id.tv_slect_one_match_info)).setVisibility(8);
            ((LinearLayout) _$_findCachedViewById(R.id.ll_enable_settings)).setVisibility(0);
        }
        ((LinearLayout) _$_findCachedViewById(R.id.ll_enable_settings)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.sportsnotification.fragment.i0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                TodaysMatchFragment.G(TodaysMatchFragment.this, view2);
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

    public final void setSelectedMatchPos(int i) {
        this.y = i;
    }

    public final void setSettingSavedDialog(@Nullable BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage) {
        this.m = bottomSheetDialogOneButtonTitleMessage;
    }

    public final void setViewModel(@Nullable TodaysMatchFragmentViewModel todaysMatchFragmentViewModel) {
        this.o = todaysMatchFragmentViewModel;
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
            bottomSheetDialogOneButtonTitleMessage.setPositiveButton(string, new View.OnClickListener() { // from class: com.coveiot.android.sportsnotification.fragment.j0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    TodaysMatchFragment.H(TodaysMatchFragment.this, z, list, view);
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

    public final void v() {
        Intent intent = new Intent(requireContext(), SportsService.class);
        Context requireContext = requireContext();
        ServiceConnection serviceConnection = this.z;
        Intrinsics.checkNotNull(serviceConnection);
        requireContext.bindService(intent, serviceConnection, 1);
        this.x = true;
    }

    public final void w() {
        try {
            if (this.x) {
                requireContext().unbindService(this.z);
                this.x = false;
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }
}
