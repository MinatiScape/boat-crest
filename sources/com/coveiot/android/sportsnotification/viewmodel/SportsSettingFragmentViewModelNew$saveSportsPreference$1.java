package com.coveiot.android.sportsnotification.viewmodel;

import android.content.Context;
import android.os.Handler;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.listeners.DataResultListener;
import com.coveiot.android.bleabstract.listeners.SettingsResultListener;
import com.coveiot.android.bleabstract.models.ProgressData;
import com.coveiot.android.bleabstract.request.DeleteImageRequest;
import com.coveiot.android.bleabstract.response.BleBaseError;
import com.coveiot.android.bleabstract.response.BleBaseResponse;
import com.coveiot.android.bleabstract.response.GetImageListResponse;
import com.coveiot.android.sportsnotification.model.SportsPreferenceModel;
import com.coveiot.android.theme.BaseFragment;
import com.coveiot.utils.utility.LogHelper;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes7.dex */
public final class SportsSettingFragmentViewModelNew$saveSportsPreference$1 implements DataResultListener {
    public final /* synthetic */ SportsSettingFragmentViewModelNew h;
    public final /* synthetic */ Context i;
    public final /* synthetic */ Integer j;
    public final /* synthetic */ Integer k;
    public final /* synthetic */ Ref.ObjectRef<SportsPreferenceModel> l;
    public final /* synthetic */ boolean m;
    public final /* synthetic */ BaseFragment.ProgressListener n;

    public SportsSettingFragmentViewModelNew$saveSportsPreference$1(SportsSettingFragmentViewModelNew sportsSettingFragmentViewModelNew, Context context, Integer num, Integer num2, Ref.ObjectRef<SportsPreferenceModel> objectRef, boolean z, BaseFragment.ProgressListener progressListener) {
        this.h = sportsSettingFragmentViewModelNew;
        this.i = context;
        this.j = num;
        this.k = num2;
        this.l = objectRef;
        this.m = z;
        this.n = progressListener;
    }

    public static final void b(final Context mcontext, final SportsSettingFragmentViewModelNew this$0, final List list, final Integer num, final Integer num2, final Ref.ObjectRef sportsPreferenceModel, final boolean z, final BaseFragment.ProgressListener listener) {
        Intrinsics.checkNotNullParameter(mcontext, "$mcontext");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(sportsPreferenceModel, "$sportsPreferenceModel");
        Intrinsics.checkNotNullParameter(listener, "$listener");
        BleApiManager.getInstance(mcontext).getBleApi().setUserSettings(new DeleteImageRequest(998), new SettingsResultListener() { // from class: com.coveiot.android.sportsnotification.viewmodel.SportsSettingFragmentViewModelNew$saveSportsPreference$1$onDataResponse$1$1
            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsError(@NotNull BleBaseError error) {
                String str;
                Intrinsics.checkNotNullParameter(error, "error");
                str = SportsSettingFragmentViewModelNew.this.j;
                LogHelper.d(str, "DeleteImageRequest-> Failed ");
                SportsSettingFragmentViewModelNew.this.getListener().onSettingsSaved(false, null);
            }

            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsResponse(@NotNull BleBaseResponse response) {
                String str;
                Intrinsics.checkNotNullParameter(response, "response");
                str = SportsSettingFragmentViewModelNew.this.j;
                LogHelper.d(str, "DeleteImageRequest-> Success ");
                SportsSettingFragmentViewModelNew.this.c(list, mcontext, num, num2, sportsPreferenceModel.element, z, listener);
            }
        });
    }

    @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
    public void onDataError(@NotNull BleBaseError error) {
        String str;
        Intrinsics.checkNotNullParameter(error, "error");
        str = this.h.j;
        LogHelper.e(str, "Error in getting image list");
        this.h.getListener().onSettingsSaved(false, null);
    }

    @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
    public void onDataResponse(@NotNull BleBaseResponse response) {
        Intrinsics.checkNotNullParameter(response, "response");
        if (response.getResponseData() instanceof GetImageListResponse) {
            Object responseData = response.getResponseData();
            Intrinsics.checkNotNull(responseData);
            final List<Integer> imageIdList = ((GetImageListResponse) responseData).getImageIdList();
            Intrinsics.checkNotNull(imageIdList);
            if (!imageIdList.contains(998)) {
                this.h.c(imageIdList, this.i, this.j, this.k, this.l.element, this.m, this.n);
                return;
            }
            Handler handler = new Handler();
            final Context context = this.i;
            final SportsSettingFragmentViewModelNew sportsSettingFragmentViewModelNew = this.h;
            final Integer num = this.j;
            final Integer num2 = this.k;
            final Ref.ObjectRef<SportsPreferenceModel> objectRef = this.l;
            final boolean z = this.m;
            final BaseFragment.ProgressListener progressListener = this.n;
            handler.postDelayed(new Runnable() { // from class: com.coveiot.android.sportsnotification.viewmodel.c
                @Override // java.lang.Runnable
                public final void run() {
                    SportsSettingFragmentViewModelNew$saveSportsPreference$1.b(context, sportsSettingFragmentViewModelNew, imageIdList, num, num2, objectRef, z, progressListener);
                }
            }, 1000L);
        }
    }

    @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
    public void onProgressUpdate(@NotNull ProgressData progress) {
        Intrinsics.checkNotNullParameter(progress, "progress");
    }
}
