package com.coveiot.android.sportsnotification.viewmodel;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.widget.Toast;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import com.coveiot.android.bleabstract.api.BleApi;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.listeners.DataResultListener;
import com.coveiot.android.bleabstract.listeners.SettingsResultListener;
import com.coveiot.android.bleabstract.models.ConnectionStatus;
import com.coveiot.android.bleabstract.models.ProgressData;
import com.coveiot.android.bleabstract.request.GetImageIdListRequest;
import com.coveiot.android.bleabstract.request.SendImageRequest;
import com.coveiot.android.bleabstract.request.SportNotificationControlRequest;
import com.coveiot.android.bleabstract.response.BleBaseError;
import com.coveiot.android.bleabstract.response.BleBaseResponse;
import com.coveiot.android.sportsnotification.Constants;
import com.coveiot.android.sportsnotification.R;
import com.coveiot.android.sportsnotification.SoccerSportsServiceNew;
import com.coveiot.android.sportsnotification.SportsPreference;
import com.coveiot.android.sportsnotification.SportsService;
import com.coveiot.android.sportsnotification.SportsType;
import com.coveiot.android.sportsnotification.SportsUtils;
import com.coveiot.android.sportsnotification.model.CoinNotifications;
import com.coveiot.android.sportsnotification.model.MatchListModel;
import com.coveiot.android.sportsnotification.model.SettingsModel;
import com.coveiot.android.sportsnotification.model.SportsPreferenceModel;
import com.coveiot.android.sportsnotification.model.WatchCricketUIModel;
import com.coveiot.android.theme.BaseFragment;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.CoveSports;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.prefs.PreferenceManager;
import com.coveiot.coveaccess.sports.Cricket;
import com.coveiot.coveaccess.sports.MatchUpdateRequest;
import com.coveiot.coveaccess.sports.SGetSportsUserPrefRes;
import com.coveiot.coveaccess.sports.Soccer;
import com.coveiot.coveaccess.sports.SportsUserPrefRequest;
import com.coveiot.coveaccess.sports.UpdateConfigsBean;
import com.coveiot.coveaccess.sports.VibrationConfigBean;
import com.coveiot.utils.utility.LogHelper;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.m;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes7.dex */
public final class SportsSettingFragmentViewModelNew extends AndroidViewModel {
    @NotNull
    public MutableLiveData<SportsType> d;
    @NotNull
    public MutableLiveData<String> e;
    @NotNull
    public MutableLiveData<Integer> f;
    @NotNull
    public MutableLiveData<Integer> g;
    @NotNull
    public Integer[] h;
    @NotNull
    public Integer[] i;
    @NotNull
    public final String j;
    public SettingsUpdateListener listener;

    /* loaded from: classes7.dex */
    public interface SettingsUpdateListener {
        void onSettingsSaved(boolean z, @Nullable List<CoinNotifications> list);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SportsSettingFragmentViewModelNew(@NotNull Application application) {
        super(application);
        Intrinsics.checkNotNullParameter(application, "application");
        this.d = new MutableLiveData<>();
        this.e = new MutableLiveData<>();
        this.f = new MutableLiveData<>();
        this.g = new MutableLiveData<>();
        this.h = new Integer[]{2, 10, 15, 30};
        this.i = new Integer[]{10, 15, 30, 60};
        this.d.setValue(SportsType.Cricket);
        this.e.setValue("T20");
        String simpleName = SportsSettingFragmentViewModelNew.class.getSimpleName();
        Intrinsics.checkNotNullExpressionValue(simpleName, "javaClass.simpleName");
        this.j = simpleName;
    }

    public static final void d(SportsSettingFragmentViewModelNew this$0, Context mcontext, Integer num, Integer num2, boolean z, SportsPreferenceModel sportsPreferenceModel, BaseFragment.ProgressListener listener) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(mcontext, "$mcontext");
        Intrinsics.checkNotNullParameter(sportsPreferenceModel, "$sportsPreferenceModel");
        Intrinsics.checkNotNullParameter(listener, "$listener");
        this$0.j(mcontext, num, num2, z, sportsPreferenceModel, listener);
    }

    public static final void e(SportsSettingFragmentViewModelNew this$0, Context mcontext, Integer num, Integer num2, boolean z, SportsPreferenceModel sportsPreferenceModel, BaseFragment.ProgressListener listener) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(mcontext, "$mcontext");
        Intrinsics.checkNotNullParameter(sportsPreferenceModel, "$sportsPreferenceModel");
        Intrinsics.checkNotNullParameter(listener, "$listener");
        this$0.k(mcontext, num, num2, z, sportsPreferenceModel, listener);
    }

    public final void c(List<Integer> list, final Context context, final Integer num, final Integer num2, final SportsPreferenceModel sportsPreferenceModel, final boolean z, final BaseFragment.ProgressListener progressListener) {
        if (SportsUtils.INSTANCE.isPS1Device(context)) {
            h(sportsPreferenceModel, z, num, num2, context);
            progressListener.onProgressChanged(100);
        } else if (m.equals$default(sportsPreferenceModel.getSport(), "Cricket", false, 2, null)) {
            Intrinsics.checkNotNull(list);
            if (!list.contains(997)) {
                new Handler().postDelayed(new Runnable() { // from class: com.coveiot.android.sportsnotification.viewmodel.b
                    @Override // java.lang.Runnable
                    public final void run() {
                        SportsSettingFragmentViewModelNew.d(SportsSettingFragmentViewModelNew.this, context, num, num2, z, sportsPreferenceModel, progressListener);
                    }
                }, 1000L);
                return;
            }
            h(sportsPreferenceModel, z, num, num2, context);
            progressListener.onProgressChanged(100);
        } else {
            SportsUtils.isCYDevice(context);
            Intrinsics.checkNotNull(list);
            if (!list.contains(996)) {
                new Handler().postDelayed(new Runnable() { // from class: com.coveiot.android.sportsnotification.viewmodel.a
                    @Override // java.lang.Runnable
                    public final void run() {
                        SportsSettingFragmentViewModelNew.e(SportsSettingFragmentViewModelNew.this, context, num, num2, z, sportsPreferenceModel, progressListener);
                    }
                }, 1000L);
                return;
            }
            h(sportsPreferenceModel, z, num, num2, context);
            progressListener.onProgressChanged(100);
        }
    }

    public final void f(Context context, Intent intent) {
        LogHelper.d(this.j, "checkAndStartService-> service is not running ++ ");
        i(context, intent);
    }

    public final void g(Context context, String str, String str2) {
        try {
            InputStream open = context.getAssets().open(str2);
            Intrinsics.checkNotNullExpressionValue(open, "assetManager.open(fileName)");
            FileOutputStream fileOutputStream = new FileOutputStream(str + str2);
            byte[] bArr = new byte[1024];
            for (int read = open.read(bArr); read != -1; read = open.read(bArr)) {
                fileOutputStream.write(bArr, 0, read);
            }
        } catch (Exception e) {
            e.getMessage();
        }
    }

    @NotNull
    public final ArrayList<SettingsModel> getIntervalList(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        ArrayList<SettingsModel> arrayList = new ArrayList<>();
        String string = context.getString(R.string._2_min);
        Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string._2_min)");
        new SettingsModel(string, false, 2);
        String string2 = context.getString(R.string._10_min);
        Intrinsics.checkNotNullExpressionValue(string2, "context.getString(R.string._10_min)");
        arrayList.add(new SettingsModel(string2, false, 10));
        String string3 = context.getString(R.string._15_min);
        Intrinsics.checkNotNullExpressionValue(string3, "context.getString(R.string._15_min)");
        arrayList.add(new SettingsModel(string3, false, 15));
        String string4 = context.getString(R.string._30_min);
        Intrinsics.checkNotNullExpressionValue(string4, "context.getString(R.string._30_min)");
        arrayList.add(new SettingsModel(string4, false, 30));
        String string5 = context.getString(R.string._1_hr);
        Intrinsics.checkNotNullExpressionValue(string5, "context.getString(R.string._1_hr)");
        arrayList.add(new SettingsModel(string5, false, 60));
        return arrayList;
    }

    @NotNull
    public final SettingsUpdateListener getListener() {
        SettingsUpdateListener settingsUpdateListener = this.listener;
        if (settingsUpdateListener != null) {
            return settingsUpdateListener;
        }
        Intrinsics.throwUninitializedPropertyAccessException(ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        return null;
    }

    @NotNull
    public final Integer[] getOdiIntervalList() {
        return this.i;
    }

    @NotNull
    public final MutableLiveData<String> getSelectedCricketMatchFormat() {
        return this.e;
    }

    @NotNull
    public final MutableLiveData<Integer> getSelectedODIInterval() {
        return this.f;
    }

    @NotNull
    public final MutableLiveData<SportsType> getSelectedSportType() {
        return this.d;
    }

    @NotNull
    public final MutableLiveData<Integer> getSelectedT20Interval() {
        return this.g;
    }

    @NotNull
    public final ArrayList<SettingsModel> getSportsList() {
        ArrayList<SettingsModel> arrayList = new ArrayList<>();
        SettingsModel settingsModel = new SettingsModel("Cricket", false, 0);
        SettingsModel settingsModel2 = new SettingsModel("Football", false, 0);
        arrayList.add(settingsModel);
        arrayList.add(settingsModel2);
        return arrayList;
    }

    @Nullable
    public final SportsPreferenceModel getSportsPreference(@NotNull Context mcontext) {
        Intrinsics.checkNotNullParameter(mcontext, "mcontext");
        return SportsPreference.Companion.getSportsNotification(mcontext);
    }

    @NotNull
    public final Integer[] getT20IntervalList() {
        return this.h;
    }

    public final void h(final SportsPreferenceModel sportsPreferenceModel, final boolean z, final Integer num, final Integer num2, final Context context) {
        Boolean isEnable = sportsPreferenceModel.isEnable();
        Intrinsics.checkNotNull(isEnable);
        SportNotificationControlRequest sportsNotificationRequest = new SportNotificationControlRequest.Builder(isEnable.booleanValue()).build();
        BleApi bleApi = BleApiManager.getInstance(context).getBleApi();
        Intrinsics.checkNotNullExpressionValue(sportsNotificationRequest, "sportsNotificationRequest");
        bleApi.setUserSettings(sportsNotificationRequest, new SettingsResultListener() { // from class: com.coveiot.android.sportsnotification.viewmodel.SportsSettingFragmentViewModelNew$enableSportNotificationOnTheWatch$1
            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsError(@NotNull BleBaseError error) {
                String str;
                Intrinsics.checkNotNullParameter(error, "error");
                str = SportsSettingFragmentViewModelNew.this.j;
                LogHelper.e(str, "Error in enabling cricket score");
                SportsSettingFragmentViewModelNew.this.getListener().onSettingsSaved(false, null);
            }

            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsResponse(@NotNull BleBaseResponse response) {
                Intrinsics.checkNotNullParameter(response, "response");
                SportsSettingFragmentViewModelNew.this.updateSportsSettingToServer(context, num, num2, sportsPreferenceModel, z);
            }
        });
    }

    public final void i(Context context, Intent intent) {
        if (Build.VERSION.SDK_INT >= 26) {
            context.startForegroundService(intent);
        } else {
            context.startService(intent);
        }
    }

    public final void j(final Context context, final Integer num, final Integer num2, final boolean z, final SportsPreferenceModel sportsPreferenceModel, final BaseFragment.ProgressListener progressListener) {
        String absolutePath = context.getFilesDir().getAbsolutePath();
        Intrinsics.checkNotNullExpressionValue(absolutePath, "mcontext.filesDir.absolutePath");
        WatchCricketUIModel watchCricketUIPreference = SportsUtils.INSTANCE.getWatchCricketUIPreference(context);
        File file = new File(absolutePath + watchCricketUIPreference.getBackGroundFileName());
        if (!file.exists()) {
            g(context, absolutePath, watchCricketUIPreference.getBackGroundFileName());
        }
        BleApiManager.getInstance(context).getBleApi().getData(new SendImageRequest(997, file, 0, 0, 0, 0, watchCricketUIPreference.getBackgroundFileHeight(), watchCricketUIPreference.getBackgroundFileWidth()), new DataResultListener() { // from class: com.coveiot.android.sportsnotification.viewmodel.SportsSettingFragmentViewModelNew$uploadCricketImage$1
            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onDataError(@NotNull BleBaseError error) {
                String str;
                Intrinsics.checkNotNullParameter(error, "error");
                str = SportsSettingFragmentViewModelNew.this.j;
                LogHelper.e(str, "Error in uploading cricket image");
                SportsSettingFragmentViewModelNew.this.getListener().onSettingsSaved(false, null);
            }

            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onDataResponse(@NotNull BleBaseResponse response) {
                Intrinsics.checkNotNullParameter(response, "response");
                SportsSettingFragmentViewModelNew.this.h(sportsPreferenceModel, z, num, num2, context);
                SportsPreference.Companion.setIsCricketImageUploaded(context, true);
            }

            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onProgressUpdate(@NotNull ProgressData progress) {
                Intrinsics.checkNotNullParameter(progress, "progress");
                progressListener.onProgressChanged(progress.getProgress());
            }
        });
    }

    public final void k(final Context context, final Integer num, final Integer num2, final boolean z, final SportsPreferenceModel sportsPreferenceModel, final BaseFragment.ProgressListener progressListener) {
        String absolutePath = context.getFilesDir().getAbsolutePath();
        Intrinsics.checkNotNullExpressionValue(absolutePath, "mcontext.filesDir.absolutePath");
        WatchCricketUIModel watchFootballUIPreference = SportsUtils.INSTANCE.getWatchFootballUIPreference(context);
        File file = new File(absolutePath + watchFootballUIPreference.getBackGroundFileName());
        if (!file.exists()) {
            g(context, absolutePath, watchFootballUIPreference.getBackGroundFileName());
        }
        SportsUtils.isCYDevice(context);
        BleApiManager.getInstance(context).getBleApi().getData(new SendImageRequest(996, file, 0, 0, 0, 0, watchFootballUIPreference.getBackgroundFileHeight(), watchFootballUIPreference.getBackgroundFileWidth()), new DataResultListener() { // from class: com.coveiot.android.sportsnotification.viewmodel.SportsSettingFragmentViewModelNew$uploadSoccerImage$1
            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onDataError(@NotNull BleBaseError error) {
                String str;
                Intrinsics.checkNotNullParameter(error, "error");
                str = SportsSettingFragmentViewModelNew.this.j;
                LogHelper.e(str, "Error in uploading cricket image");
                SportsSettingFragmentViewModelNew.this.getListener().onSettingsSaved(false, null);
            }

            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onDataResponse(@NotNull BleBaseResponse response) {
                Intrinsics.checkNotNullParameter(response, "response");
                SportsSettingFragmentViewModelNew.this.h(sportsPreferenceModel, z, num, num2, context);
                SportsPreference.Companion.setIsSoccerImageUploaded(context, true);
            }

            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onProgressUpdate(@NotNull ProgressData progress) {
                Intrinsics.checkNotNullParameter(progress, "progress");
                progressListener.onProgressChanged(progress.getProgress());
            }
        });
    }

    /* JADX WARN: Type inference failed for: r1v5, types: [com.coveiot.android.sportsnotification.model.SportsPreferenceModel, T] */
    public final void saveSportsPreference(@NotNull Context mcontext, @Nullable Integer num, @Nullable Integer num2, @Nullable String str, boolean z, boolean z2, @NotNull BaseFragment.ProgressListener listener) {
        int i;
        Intrinsics.checkNotNullParameter(mcontext, "mcontext");
        Intrinsics.checkNotNullParameter(listener, "listener");
        boolean z3 = true;
        if (BleApiManager.getInstance(mcontext).getBleApi().getConnectionStatus() == ConnectionStatus.CONNECTED) {
            Ref.ObjectRef objectRef = new Ref.ObjectRef();
            objectRef.element = new SportsPreferenceModel();
            MatchListModel selectedMatch = SportsPreference.Companion.getSelectedMatch(mcontext);
            if (selectedMatch != null) {
                Integer matchFormat = selectedMatch.getMatchFormat();
                if (!(matchFormat != null && matchFormat.intValue() == 1)) {
                    Integer matchFormat2 = selectedMatch.getMatchFormat();
                    if (!(matchFormat2 != null && matchFormat2.intValue() == 7)) {
                        Integer matchFormat3 = selectedMatch.getMatchFormat();
                        if (!(matchFormat3 != null && matchFormat3.intValue() == 9)) {
                            Integer matchFormat4 = selectedMatch.getMatchFormat();
                            if (!(matchFormat4 != null && matchFormat4.intValue() == 3)) {
                                Integer matchFormat5 = selectedMatch.getMatchFormat();
                                if (!(matchFormat5 != null && matchFormat5.intValue() == 6)) {
                                    Integer matchFormat6 = selectedMatch.getMatchFormat();
                                    if (matchFormat6 == null || matchFormat6.intValue() != 8) {
                                        z3 = false;
                                    }
                                    if (!z3) {
                                        Intrinsics.checkNotNull(num);
                                        i = num.intValue();
                                    }
                                }
                            }
                            Intrinsics.checkNotNull(num2);
                            i = num2.intValue();
                        }
                    }
                }
                Intrinsics.checkNotNull(num);
                i = num.intValue();
            } else {
                i = 0;
            }
            if (z) {
                ((SportsPreferenceModel) objectRef.element).setInterval(Integer.valueOf(i));
                ((SportsPreferenceModel) objectRef.element).setEnable(Boolean.TRUE);
                ((SportsPreferenceModel) objectRef.element).setSport(str);
            } else {
                ((SportsPreferenceModel) objectRef.element).setInterval(Integer.valueOf(i));
                ((SportsPreferenceModel) objectRef.element).setEnable(Boolean.FALSE);
                ((SportsPreferenceModel) objectRef.element).setSport(str);
            }
            if (z) {
                if (m.equals$default(str, "Cricket", false, 2, null)) {
                    f(mcontext, new Intent(mcontext, SportsService.class));
                } else {
                    f(mcontext, new Intent(mcontext, SoccerSportsServiceNew.class));
                }
            }
            BleApiManager.getInstance(mcontext).getBleApi().getData(new GetImageIdListRequest(), new SportsSettingFragmentViewModelNew$saveSportsPreference$1(this, mcontext, num, num2, objectRef, z2, listener));
            return;
        }
        Toast.makeText(mcontext, R.string.bluetooth_off_message, 1).show();
    }

    public final void setListener(@NotNull SettingsUpdateListener settingsUpdateListener) {
        Intrinsics.checkNotNullParameter(settingsUpdateListener, "<set-?>");
        this.listener = settingsUpdateListener;
    }

    public final void setOdiIntervalList(@NotNull Integer[] numArr) {
        Intrinsics.checkNotNullParameter(numArr, "<set-?>");
        this.i = numArr;
    }

    public final void setSelectedCricketMatchFormat(@NotNull MutableLiveData<String> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.e = mutableLiveData;
    }

    public final void setSelectedODIInterval(@NotNull MutableLiveData<Integer> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.f = mutableLiveData;
    }

    public final void setSelectedSportType(@NotNull MutableLiveData<SportsType> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.d = mutableLiveData;
    }

    public final void setSelectedT20Interval(@NotNull MutableLiveData<Integer> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.g = mutableLiveData;
    }

    public final void setT20IntervalList(@NotNull Integer[] numArr) {
        Intrinsics.checkNotNullParameter(numArr, "<set-?>");
        this.h = numArr;
    }

    public final void updateSportsSettingToServer(@NotNull final Context mContext, @Nullable final Integer num, @Nullable final Integer num2, @NotNull final SportsPreferenceModel sportsPreferenceModel, final boolean z) {
        Intrinsics.checkNotNullParameter(mContext, "mContext");
        Intrinsics.checkNotNullParameter(sportsPreferenceModel, "sportsPreferenceModel");
        SportsPreference.Companion companion = SportsPreference.Companion;
        MatchListModel selectedMatch = companion.getSelectedMatch(mContext);
        MatchListModel selectedSoccerMatch = companion.getSelectedSoccerMatch(mContext);
        SportsUserPrefRequest sportsUserPrefRequest = new SportsUserPrefRequest();
        VibrationConfigBean vibrationConfigBean = new VibrationConfigBean();
        vibrationConfigBean.setActive(Boolean.valueOf(z));
        sportsUserPrefRequest.setVibrationConfig(vibrationConfigBean);
        sportsUserPrefRequest.setPushToDevice(sportsPreferenceModel.isEnable());
        Cricket cricket = new Cricket();
        Soccer soccer = new Soccer();
        if (m.equals$default(sportsPreferenceModel.getSport(), "Cricket", false, 2, null)) {
            MatchUpdateRequest matchUpdateRequest = new MatchUpdateRequest();
            ArrayList arrayList = new ArrayList();
            if (selectedMatch != null && selectedMatch.getMatchId() != null) {
                matchUpdateRequest.setCtx(Constants.Companion.getENTITY_SPORT());
                matchUpdateRequest.setMatchId(selectedMatch.getMatchId());
                arrayList.add(matchUpdateRequest);
                cricket.setMatchUpdates(arrayList);
            }
            cricket.setEnable(Boolean.valueOf(m.equals$default(sportsPreferenceModel.getSport(), "Cricket", false, 2, null)));
            UpdateConfigsBean updateConfigsBean = new UpdateConfigsBean();
            Intrinsics.checkNotNull(num);
            updateConfigsBean.setInterval(Integer.valueOf(num.intValue() * 60));
            updateConfigsBean.setMatchFormat("ODI");
            UpdateConfigsBean updateConfigsBean2 = new UpdateConfigsBean();
            Intrinsics.checkNotNull(num2);
            updateConfigsBean2.setInterval(Integer.valueOf(num2.intValue() * 60));
            updateConfigsBean2.setMatchFormat("T20");
            cricket.setUpdateConfigs(CollectionsKt__CollectionsKt.arrayListOf(updateConfigsBean, updateConfigsBean2));
            String userDeviceID = PreferenceManager.getInstance().getUserDeviceID();
            Intrinsics.checkNotNullExpressionValue(userDeviceID, "getInstance().userDeviceID");
            sportsUserPrefRequest.setUserDeviceId(Integer.valueOf(Integer.parseInt(userDeviceID)));
            sportsUserPrefRequest.setCricket(cricket);
            soccer.setEnable(Boolean.FALSE);
            MatchUpdateRequest matchUpdateRequest2 = new MatchUpdateRequest();
            ArrayList arrayList2 = new ArrayList();
            if (selectedSoccerMatch != null && selectedSoccerMatch.getMatchId() != null) {
                matchUpdateRequest2.setCtx(Constants.Companion.getENTITY_SPORT());
                matchUpdateRequest2.setMatchId(selectedSoccerMatch.getMatchId());
                arrayList2.add(matchUpdateRequest2);
                soccer.setMatchUpdates(arrayList2);
            }
            sportsUserPrefRequest.setSoccer(soccer);
        } else {
            soccer.setEnable(Boolean.TRUE);
            MatchUpdateRequest matchUpdateRequest3 = new MatchUpdateRequest();
            ArrayList arrayList3 = new ArrayList();
            if (selectedSoccerMatch != null && selectedSoccerMatch.getMatchId() != null) {
                matchUpdateRequest3.setCtx(Constants.Companion.getENTITY_SPORT());
                matchUpdateRequest3.setMatchId(selectedSoccerMatch.getMatchId());
                arrayList3.add(matchUpdateRequest3);
                soccer.setMatchUpdates(arrayList3);
            }
            sportsUserPrefRequest.setSoccer(soccer);
            MatchUpdateRequest matchUpdateRequest4 = new MatchUpdateRequest();
            ArrayList arrayList4 = new ArrayList();
            if (selectedMatch != null && selectedMatch.getMatchId() != null) {
                matchUpdateRequest4.setCtx(Constants.Companion.getENTITY_SPORT());
                matchUpdateRequest4.setMatchId(selectedMatch.getMatchId());
                arrayList4.add(matchUpdateRequest4);
                cricket.setMatchUpdates(arrayList4);
            }
            cricket.setEnable(Boolean.FALSE);
            UpdateConfigsBean updateConfigsBean3 = new UpdateConfigsBean();
            Intrinsics.checkNotNull(num);
            updateConfigsBean3.setInterval(Integer.valueOf(num.intValue() * 60));
            updateConfigsBean3.setMatchFormat("ODI");
            UpdateConfigsBean updateConfigsBean4 = new UpdateConfigsBean();
            Intrinsics.checkNotNull(num2);
            updateConfigsBean4.setInterval(Integer.valueOf(num2.intValue() * 60));
            updateConfigsBean4.setMatchFormat("T20");
            cricket.setUpdateConfigs(CollectionsKt__CollectionsKt.arrayListOf(updateConfigsBean3, updateConfigsBean4));
            String userDeviceID2 = PreferenceManager.getInstance().getUserDeviceID();
            Intrinsics.checkNotNullExpressionValue(userDeviceID2, "getInstance().userDeviceID");
            sportsUserPrefRequest.setUserDeviceId(Integer.valueOf(Integer.parseInt(userDeviceID2)));
            sportsUserPrefRequest.setCricket(cricket);
        }
        CoveSports.postSportsUserPref(sportsUserPrefRequest, new CoveApiListener<SGetSportsUserPrefRes, CoveApiErrorModel>() { // from class: com.coveiot.android.sportsnotification.viewmodel.SportsSettingFragmentViewModelNew$updateSportsSettingToServer$1
            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                String str;
                str = this.j;
                LogHelper.e(str, "Error in saving setting to server");
                this.getListener().onSettingsSaved(false, null);
            }

            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onSuccess(@Nullable SGetSportsUserPrefRes sGetSportsUserPrefRes) {
                SGetSportsUserPrefRes.Data data;
                SportsPreference.Companion companion2 = SportsPreference.Companion;
                Context context = mContext;
                Integer num3 = num;
                Intrinsics.checkNotNull(num3);
                companion2.saveODIInterval(context, num3.intValue());
                Context context2 = mContext;
                Integer num4 = num2;
                Intrinsics.checkNotNull(num4);
                companion2.saveT20Interval(context2, num4.intValue());
                companion2.saveSportsNotification(mContext, sportsPreferenceModel);
                Context context3 = mContext;
                Boolean isEnable = sportsPreferenceModel.isEnable();
                Intrinsics.checkNotNull(isEnable);
                companion2.saveNotificationEnabled(context3, isEnable.booleanValue());
                companion2.saveVibrationEnabled(mContext, z);
                Boolean isEnable2 = sportsPreferenceModel.isEnable();
                Intrinsics.checkNotNull(isEnable2);
                ArrayList arrayList5 = null;
                if (isEnable2.booleanValue()) {
                    if (m.equals$default(sportsPreferenceModel.getSport(), "Cricket", false, 2, null)) {
                        this.f(mContext, new Intent(mContext, SportsService.class));
                    } else {
                        this.f(mContext, new Intent(mContext, SoccerSportsServiceNew.class));
                    }
                }
                if (sGetSportsUserPrefRes != null && (data = sGetSportsUserPrefRes.data) != null) {
                    Intrinsics.checkNotNullExpressionValue(data, "data");
                    List<SGetSportsUserPrefRes.Data.CoinNotifications> coinsNotifications = data.getCoinsNotifications();
                    if (coinsNotifications != null) {
                        Intrinsics.checkNotNullExpressionValue(coinsNotifications, "coinsNotifications");
                        arrayList5 = new ArrayList();
                        for (SGetSportsUserPrefRes.Data.CoinNotifications coinNotifications : coinsNotifications) {
                            CoinNotifications coinNotifications2 = new CoinNotifications(null, null, null, null, 15, null);
                            coinNotifications2.setDescription(coinNotifications.getDescription());
                            coinNotifications2.setNotificationUrl(coinNotifications.getNotificationUrl());
                            coinNotifications2.setEarnedCoins(coinNotifications.getEarnedCoins());
                            coinNotifications2.setTitle(coinNotifications.getTitle());
                            arrayList5.add(coinNotifications2);
                        }
                    }
                }
                this.getListener().onSettingsSaved(true, arrayList5);
            }
        });
    }
}
