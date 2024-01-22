package com.coveiot.android.leonardo.quickreply.viewmodel;

import android.content.Context;
import androidx.lifecycle.ViewModel;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.listeners.SettingsResultListener;
import com.coveiot.android.bleabstract.models.ConnectionStatus;
import com.coveiot.android.bleabstract.request.SetQuickReplyRequest;
import com.coveiot.android.bleabstract.response.BleBaseError;
import com.coveiot.android.bleabstract.response.BleBaseResponse;
import com.coveiot.android.boat.R;
import com.coveiot.android.dashboard2.listener.ViewModelListener;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.CoveUserAppSettings;
import com.coveiot.coveaccess.constants.ErrorConstants;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.userappsetting.GetAllUserAppSettingsRes;
import com.coveiot.coveaccess.userappsetting.SaveQuickReplySettingsReq;
import com.coveiot.coveaccess.userappsetting.SaveQuickReplySettingsRes;
import com.coveiot.covepreferences.UserDataManager;
import com.coveiot.covepreferences.data.QuickReplyListModel;
import com.coveiot.sdk.ble.api.model.BleQuickReplyModel;
import com.coveiot.utils.utility.AppUtils;
import com.coveiot.utils.utility.LogHelper;
import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class QuickReplySettingsViewModel extends ViewModel {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f5350a;
    @NotNull
    public final String b;
    public boolean c;
    public ViewModelListener mListener;

    public QuickReplySettingsViewModel(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f5350a = context;
        this.b = "Quick Reply Settings";
    }

    @NotNull
    public final Context getContext() {
        return this.f5350a;
    }

    @NotNull
    public final ViewModelListener getMListener() {
        ViewModelListener viewModelListener = this.mListener;
        if (viewModelListener != null) {
            return viewModelListener;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mListener");
        return null;
    }

    public final boolean getQuickReplyIsEnabled() {
        QuickReplyListModel quickReplyListFromPref = UserDataManager.getInstance(this.f5350a).getQuickReplyListFromPref();
        if (quickReplyListFromPref != null) {
            this.c = quickReplyListFromPref.isQuickReplyEnabled();
        } else {
            getQuickReplyIsEnabledData();
        }
        return this.c;
    }

    public final void getQuickReplyIsEnabledData() {
        if (AppUtils.isNetConnected(this.f5350a) && BleApiManager.getInstance(this.f5350a).getBleApi().getDeviceSupportedFeatures().isQuickReplySupported() && BleApiManager.getInstance(this.f5350a).getBleApi().getConnectionStatus() == ConnectionStatus.CONNECTED) {
            CoveUserAppSettings.getAllUserAppSettings(new CoveApiListener<GetAllUserAppSettingsRes, CoveApiErrorModel>() { // from class: com.coveiot.android.leonardo.quickreply.viewmodel.QuickReplySettingsViewModel$getQuickReplyIsEnabledData$1
                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                }

                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onSuccess(@Nullable GetAllUserAppSettingsRes getAllUserAppSettingsRes) {
                    if (getAllUserAppSettingsRes == null || getAllUserAppSettingsRes.getCallQuickReplies() == null) {
                        return;
                    }
                    if (getAllUserAppSettingsRes.getCallQuickReplies() != null) {
                        Boolean active = getAllUserAppSettingsRes.getCallQuickReplies().getActive();
                        Intrinsics.checkNotNullExpressionValue(active, "response.callQuickReplies.active");
                        boolean booleanValue = active.booleanValue();
                        List<String> messages = getAllUserAppSettingsRes.getCallQuickReplies().getMessages();
                        Intrinsics.checkNotNullExpressionValue(messages, "response.callQuickReplies.messages");
                        UserDataManager.getInstance(QuickReplySettingsViewModel.this.getContext()).saveQuickReplyListData(new QuickReplyListModel(booleanValue, messages));
                    }
                    QuickReplySettingsViewModel quickReplySettingsViewModel = QuickReplySettingsViewModel.this;
                    Boolean active2 = getAllUserAppSettingsRes.getCallQuickReplies().getActive();
                    Intrinsics.checkNotNullExpressionValue(active2, "response.callQuickReplies.active");
                    quickReplySettingsViewModel.c = active2.booleanValue();
                }
            });
        }
    }

    @NotNull
    public final String getTAG() {
        return this.b;
    }

    public final void saveQuickReplySettings(@NotNull final List<String> quickReplyStringList, final boolean z) {
        Intrinsics.checkNotNullParameter(quickReplyStringList, "quickReplyStringList");
        SaveQuickReplySettingsReq saveQuickReplySettingsReq = new SaveQuickReplySettingsReq();
        saveQuickReplySettingsReq.setActive(z);
        saveQuickReplySettingsReq.setQuickReplyMessages(quickReplyStringList);
        CoveUserAppSettings.saveQuickReplySettings(saveQuickReplySettingsReq, new CoveApiListener<SaveQuickReplySettingsRes, CoveApiErrorModel>() { // from class: com.coveiot.android.leonardo.quickreply.viewmodel.QuickReplySettingsViewModel$saveQuickReplySettings$1
            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                LogHelper.d(QuickReplySettingsViewModel.this.getTAG(), ErrorConstants.GENERIC_ERROR);
            }

            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onSuccess(@NotNull SaveQuickReplySettingsRes saveQuickReplySettingsRes) {
                Intrinsics.checkNotNullParameter(saveQuickReplySettingsRes, "saveQuickReplySettingsRes");
                LogHelper.d(QuickReplySettingsViewModel.this.getTAG(), "Success");
                UserDataManager.getInstance(QuickReplySettingsViewModel.this.getContext()).saveQuickReplyListData(new QuickReplyListModel(z, quickReplyStringList));
                QuickReplySettingsViewModel.this.getMListener().onSuccess();
            }
        });
    }

    public final void setMListener(@NotNull ViewModelListener viewModelListener) {
        Intrinsics.checkNotNullParameter(viewModelListener, "<set-?>");
        this.mListener = viewModelListener;
    }

    public final void setQuickReplyList(final boolean z) {
        String string;
        String string2;
        String string3;
        String string4;
        String string5;
        if (AppUtils.isNetConnected(this.f5350a)) {
            if (BleApiManager.getInstance(this.f5350a).getBleApi().getDeviceSupportedFeatures().isQuickReplySupported()) {
                if (BleApiManager.getInstance(this.f5350a).getBleApi().getConnectionStatus() == ConnectionStatus.CONNECTED) {
                    QuickReplyListModel quickReplyListFromPref = UserDataManager.getInstance(this.f5350a).getQuickReplyListFromPref();
                    if (quickReplyListFromPref != null) {
                        string = quickReplyListFromPref.getQuickReplyMessage().get(0);
                        string2 = quickReplyListFromPref.getQuickReplyMessage().get(1);
                        string3 = quickReplyListFromPref.getQuickReplyMessage().get(2);
                        string4 = quickReplyListFromPref.getQuickReplyMessage().get(3);
                        string5 = quickReplyListFromPref.getQuickReplyMessage().get(4);
                    } else {
                        string = this.f5350a.getString(R.string.quick_reply_message_1);
                        Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.quick_reply_message_1)");
                        string2 = this.f5350a.getString(R.string.quick_reply_message_2);
                        Intrinsics.checkNotNullExpressionValue(string2, "context.getString(R.string.quick_reply_message_2)");
                        string3 = this.f5350a.getString(R.string.quick_reply_message_3);
                        Intrinsics.checkNotNullExpressionValue(string3, "context.getString(R.string.quick_reply_message_3)");
                        string4 = this.f5350a.getString(R.string.quick_reply_message_4);
                        Intrinsics.checkNotNullExpressionValue(string4, "context.getString(R.string.quick_reply_message_4)");
                        string5 = this.f5350a.getString(R.string.quick_reply_message_5);
                        Intrinsics.checkNotNullExpressionValue(string5, "context.getString(R.string.quick_reply_message_5)");
                    }
                    final ArrayList arrayList = new ArrayList();
                    arrayList.clear();
                    arrayList.add(string);
                    arrayList.add(string2);
                    arrayList.add(string3);
                    arrayList.add(string4);
                    arrayList.add(string5);
                    ArrayList arrayList2 = new ArrayList();
                    arrayList2.clear();
                    arrayList2.add(new BleQuickReplyModel(string));
                    arrayList2.add(new BleQuickReplyModel(string2));
                    arrayList2.add(new BleQuickReplyModel(string3));
                    arrayList2.add(new BleQuickReplyModel(string4));
                    arrayList2.add(new BleQuickReplyModel(string5));
                    BleApiManager.getInstance(this.f5350a).getBleApi().setUserSettings(new SetQuickReplyRequest.Builder().setQuickReply(z, arrayList2).build(), new SettingsResultListener() { // from class: com.coveiot.android.leonardo.quickreply.viewmodel.QuickReplySettingsViewModel$setQuickReplyList$1
                        @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                        public void onSettingsError(@NotNull BleBaseError error) {
                            Intrinsics.checkNotNullParameter(error, "error");
                            LogHelper.d(QuickReplySettingsViewModel.this.getTAG(), ErrorConstants.GENERIC_ERROR);
                        }

                        @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                        public void onSettingsResponse(@NotNull BleBaseResponse response) {
                            Intrinsics.checkNotNullParameter(response, "response");
                            LogHelper.d(QuickReplySettingsViewModel.this.getTAG(), "Success");
                            QuickReplySettingsViewModel.this.saveQuickReplySettings(arrayList, z);
                        }
                    });
                    return;
                }
                ViewModelListener mListener = getMListener();
                String string6 = this.f5350a.getResources().getString(R.string.band_disconnected);
                Intrinsics.checkNotNullExpressionValue(string6, "context.resources.getStr…string.band_disconnected)");
                mListener.onDataFailure(string6);
                return;
            }
            return;
        }
        ViewModelListener mListener2 = getMListener();
        String string7 = this.f5350a.getResources().getString(R.string.please_check_your_internet);
        Intrinsics.checkNotNullExpressionValue(string7, "context.resources.getStr…ease_check_your_internet)");
        mListener2.onDataFailure(string7);
    }
}
