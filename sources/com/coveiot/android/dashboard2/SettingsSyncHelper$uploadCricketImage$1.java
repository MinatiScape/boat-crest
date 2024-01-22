package com.coveiot.android.dashboard2;

import android.content.Context;
import android.os.Handler;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.listeners.DataResultListener;
import com.coveiot.android.bleabstract.models.ProgressData;
import com.coveiot.android.bleabstract.request.SendImageRequest;
import com.coveiot.android.bleabstract.response.BleBaseError;
import com.coveiot.android.bleabstract.response.BleBaseResponse;
import com.coveiot.android.bleabstract.response.GetImageListResponse;
import com.coveiot.android.dashboard2.SettingsSyncHelper;
import com.coveiot.android.devicemodels.DeviceUtils;
import com.coveiot.android.sportsnotification.SportsPreference;
import com.coveiot.android.sportsnotification.SportsType;
import com.coveiot.android.sportsnotification.SportsUtils;
import com.coveiot.android.sportsnotification.model.SportsPreferenceModel;
import com.coveiot.android.sportsnotification.model.WatchCricketUIModel;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.utils.utility.LogHelper;
import java.io.File;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes4.dex */
public final class SettingsSyncHelper$uploadCricketImage$1 implements DataResultListener {
    public final /* synthetic */ SettingsSyncHelper h;
    public final /* synthetic */ SettingsSyncHelper.SettingsSyncListner i;

    public SettingsSyncHelper$uploadCricketImage$1(SettingsSyncHelper settingsSyncHelper, SettingsSyncHelper.SettingsSyncListner settingsSyncListner) {
        this.h = settingsSyncHelper;
        this.i = settingsSyncListner;
    }

    public static final void c(final SettingsSyncHelper this$0, Ref.IntRef element, final SettingsSyncHelper.SettingsSyncListner settingsSyncListner) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(element, "$element");
        Intrinsics.checkNotNullParameter(settingsSyncListner, "$settingsSyncListner");
        final Context context = this$0.getContext();
        String absolutePath = context.getFilesDir().getAbsolutePath();
        Intrinsics.checkNotNullExpressionValue(absolutePath, "context.filesDir.absolutePath");
        WatchCricketUIModel watchFootballUIPreference = SportsUtils.INSTANCE.getWatchFootballUIPreference(context);
        File file = new File(absolutePath + watchFootballUIPreference.getBackGroundFileName());
        if (!file.exists()) {
            this$0.b(context, absolutePath, watchFootballUIPreference.getBackGroundFileName());
        }
        BleApiManager.getInstance(context).getBleApi().getData(new SendImageRequest(element.element, file, 0, 0, 0, 0, watchFootballUIPreference.getBackgroundFileHeight(), watchFootballUIPreference.getBackgroundFileWidth()), new DataResultListener() { // from class: com.coveiot.android.dashboard2.SettingsSyncHelper$uploadCricketImage$1$onDataResponse$1$1
            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onDataError(@NotNull BleBaseError error) {
                Intrinsics.checkNotNullParameter(error, "error");
                settingsSyncListner.onSettingSyncError();
            }

            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onDataResponse(@NotNull BleBaseResponse response) {
                int i;
                int i2;
                String str;
                Intrinsics.checkNotNullParameter(response, "response");
                SessionManager.getInstance(context).setIsCricketImageUploaded(Boolean.TRUE);
                SettingsSyncHelper settingsSyncHelper = this$0;
                i = settingsSyncHelper.f;
                settingsSyncHelper.f = i - 1;
                i2 = this$0.f;
                if (i2 == 0) {
                    str = this$0.d;
                    LogHelper.d(str, "SendImageRequest Success.");
                    SettingsSyncHelper.SettingsSyncListner settingsSyncListner2 = settingsSyncListner;
                    Intrinsics.checkNotNull(settingsSyncListner2);
                    settingsSyncListner2.onSettingsSyncCompleted();
                }
            }

            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onProgressUpdate(@NotNull ProgressData progress) {
                Intrinsics.checkNotNullParameter(progress, "progress");
                settingsSyncListner.onProgressUpdate(progress.getProgress());
            }
        });
    }

    public static final void d(final SettingsSyncHelper this$0, final SettingsSyncHelper.SettingsSyncListner settingsSyncListner) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(settingsSyncListner, "$settingsSyncListner");
        final Context context = this$0.getContext();
        String absolutePath = context.getFilesDir().getAbsolutePath();
        Intrinsics.checkNotNullExpressionValue(absolutePath, "context.filesDir.absolutePath");
        WatchCricketUIModel watchCricketUIPreference = SportsUtils.INSTANCE.getWatchCricketUIPreference(context);
        File file = new File(absolutePath + watchCricketUIPreference.getBackGroundFileName());
        if (!file.exists()) {
            this$0.b(context, absolutePath, watchCricketUIPreference.getBackGroundFileName());
        }
        BleApiManager.getInstance(context).getBleApi().getData(new SendImageRequest(997, file, 0, 0, 0, 0, watchCricketUIPreference.getBackgroundFileHeight(), watchCricketUIPreference.getBackgroundFileWidth()), new DataResultListener() { // from class: com.coveiot.android.dashboard2.SettingsSyncHelper$uploadCricketImage$1$onDataResponse$2$1
            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onDataError(@NotNull BleBaseError error) {
                Intrinsics.checkNotNullParameter(error, "error");
                settingsSyncListner.onSettingSyncError();
            }

            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onDataResponse(@NotNull BleBaseResponse response) {
                int i;
                int i2;
                String str;
                Intrinsics.checkNotNullParameter(response, "response");
                SessionManager.getInstance(context).setIsCricketImageUploaded(Boolean.TRUE);
                SettingsSyncHelper settingsSyncHelper = this$0;
                i = settingsSyncHelper.f;
                settingsSyncHelper.f = i - 1;
                i2 = this$0.f;
                if (i2 == 0) {
                    str = this$0.d;
                    LogHelper.d(str, "SendImageRequest Success.");
                    SettingsSyncHelper.SettingsSyncListner settingsSyncListner2 = settingsSyncListner;
                    Intrinsics.checkNotNull(settingsSyncListner2);
                    settingsSyncListner2.onSettingsSyncCompleted();
                }
            }

            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onProgressUpdate(@NotNull ProgressData progress) {
                Intrinsics.checkNotNullParameter(progress, "progress");
                settingsSyncListner.onProgressUpdate(progress.getProgress());
            }
        });
    }

    @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
    public void onDataError(@NotNull BleBaseError error) {
        Intrinsics.checkNotNullParameter(error, "error");
    }

    @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
    public void onDataResponse(@NotNull BleBaseResponse response) {
        int i;
        int i2;
        int i3;
        int i4;
        Intrinsics.checkNotNullParameter(response, "response");
        if (response.getResponseData() instanceof GetImageListResponse) {
            Object responseData = response.getResponseData();
            Intrinsics.checkNotNull(responseData);
            List<Integer> imageIdList = ((GetImageListResponse) responseData).getImageIdList();
            SportsPreference.Companion companion = SportsPreference.Companion;
            if (companion.getSportsNotification(this.h.getContext()) != null) {
                SportsPreferenceModel sportsNotification = companion.getSportsNotification(this.h.getContext());
                String sport = sportsNotification != null ? sportsNotification.getSport() : null;
                Intrinsics.checkNotNull(sport);
                if (sport.equals(SportsType.Football.name())) {
                    final Ref.IntRef intRef = new Ref.IntRef();
                    intRef.element = 996;
                    if (DeviceUtils.Companion.isCYDevice(this.h.getContext())) {
                        intRef.element = 996;
                    }
                    Intrinsics.checkNotNull(imageIdList);
                    if (!imageIdList.contains(Integer.valueOf(intRef.element))) {
                        Handler handler = new Handler();
                        final SettingsSyncHelper settingsSyncHelper = this.h;
                        final SettingsSyncHelper.SettingsSyncListner settingsSyncListner = this.i;
                        handler.postDelayed(new Runnable() { // from class: com.coveiot.android.dashboard2.c
                            @Override // java.lang.Runnable
                            public final void run() {
                                SettingsSyncHelper$uploadCricketImage$1.c(SettingsSyncHelper.this, intRef, settingsSyncListner);
                            }
                        }, 1000L);
                        return;
                    }
                    SettingsSyncHelper settingsSyncHelper2 = this.h;
                    i3 = settingsSyncHelper2.f;
                    settingsSyncHelper2.f = i3 - 1;
                    i4 = this.h.f;
                    if (i4 == 0) {
                        SettingsSyncHelper.SettingsSyncListner settingsSyncListner2 = this.i;
                        Intrinsics.checkNotNull(settingsSyncListner2);
                        settingsSyncListner2.onSettingsSyncCompleted();
                        return;
                    }
                    return;
                }
            }
            Intrinsics.checkNotNull(imageIdList);
            if (!imageIdList.contains(997)) {
                Handler handler2 = new Handler();
                final SettingsSyncHelper settingsSyncHelper3 = this.h;
                final SettingsSyncHelper.SettingsSyncListner settingsSyncListner3 = this.i;
                handler2.postDelayed(new Runnable() { // from class: com.coveiot.android.dashboard2.b
                    @Override // java.lang.Runnable
                    public final void run() {
                        SettingsSyncHelper$uploadCricketImage$1.d(SettingsSyncHelper.this, settingsSyncListner3);
                    }
                }, 1000L);
                return;
            }
            SettingsSyncHelper settingsSyncHelper4 = this.h;
            i = settingsSyncHelper4.f;
            settingsSyncHelper4.f = i - 1;
            i2 = this.h.f;
            if (i2 == 0) {
                SettingsSyncHelper.SettingsSyncListner settingsSyncListner4 = this.i;
                Intrinsics.checkNotNull(settingsSyncListner4);
                settingsSyncListner4.onSettingsSyncCompleted();
            }
        }
    }

    @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
    public void onProgressUpdate(@NotNull ProgressData progress) {
        Intrinsics.checkNotNullParameter(progress, "progress");
    }
}
