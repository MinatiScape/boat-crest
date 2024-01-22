package com.coveiot.android.leonardo.more.viewmodel;

import android.content.Context;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.listeners.DataResultListener;
import com.coveiot.android.bleabstract.listeners.SettingsResultListener;
import com.coveiot.android.bleabstract.models.ConnectionStatus;
import com.coveiot.android.bleabstract.models.ProgressData;
import com.coveiot.android.bleabstract.request.GetButtonFunctionRequest;
import com.coveiot.android.bleabstract.request.SetButtonFunctionRequest;
import com.coveiot.android.bleabstract.response.BleBaseError;
import com.coveiot.android.bleabstract.response.BleBaseResponse;
import com.coveiot.android.bleabstract.response.GetButtonFunctionResponse;
import com.coveiot.android.boat.R;
import com.coveiot.android.dashboard2.listener.ViewModelListener;
import com.coveiot.android.leonardo.more.listeners.GenericBandAppCoveApiListener;
import com.coveiot.android.leonardo.more.models.Customise4hButtonData;
import com.coveiot.android.leonardo.utils.AppConstants;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.CoveUserDeviceSettings;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.model.server.GetAppConfigRes;
import com.coveiot.coveaccess.userdevicesetting.GetAllUserDeviceSettingRes;
import com.coveiot.coveaccess.userdevicesetting.Save4hButtonActionItemsReq;
import com.coveiot.coveaccess.userdevicesetting.Save4hButtonActionsRes;
import com.coveiot.coveaccess.userdevicesetting.model.Button4hActionItems;
import com.coveiot.utils.utility.LogHelper;
import com.google.gson.Gson;
import java.util.ArrayList;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class ActivityCustomise4hButtonViewModel extends ViewModel {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f5145a;
    public final String b;
    @Nullable
    public GetButtonFunctionResponse c;
    public GenericBandAppCoveApiListener mListener;
    public ViewModelListener saveActionsListener;
    public GetAppConfigRes.ActionItemsData supportedItems;

    public ActivityCustomise4hButtonViewModel(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f5145a = context;
        this.b = ActivityCustomise4hButtonViewModel.class.getSimpleName();
    }

    public final void a(Customise4hButtonData customise4hButtonData, Customise4hButtonData customise4hButtonData2, int i) {
        Button4hActionItems button4hActionItems = new Button4hActionItems();
        ArrayList arrayList = new ArrayList();
        Button4hActionItems.ActionItem actionItem = new Button4hActionItems.ActionItem();
        actionItem.setEvent(AppConstants.SHORT_PRESS.getValue());
        actionItem.setActionId(customise4hButtonData.getActionId());
        Button4hActionItems.ActionItem actionItem2 = new Button4hActionItems.ActionItem();
        actionItem2.setEvent(AppConstants.LONG_PRESS.getValue());
        actionItem2.setActionId(customise4hButtonData2.getActionId());
        arrayList.add(actionItem);
        arrayList.add(actionItem2);
        Button4hActionItems.Button4h button4h = new Button4hActionItems.Button4h();
        button4h.setActions(arrayList);
        button4h.setActionVersion(i);
        Button4hActionItems.ButtonC11n buttonC11n = new Button4hActionItems.ButtonC11n();
        buttonC11n.setButton4h(button4h);
        button4hActionItems.setButtonC11n(buttonC11n);
        Save4hButtonActionItemsReq save4hButtonActionItemsReq = new Save4hButtonActionItemsReq();
        save4hButtonActionItemsReq.setButton4hActionItems(button4hActionItems);
        CoveUserDeviceSettings.save4hButtonActions(save4hButtonActionItemsReq, new CoveApiListener<Save4hButtonActionsRes, CoveApiErrorModel>() { // from class: com.coveiot.android.leonardo.more.viewmodel.ActivityCustomise4hButtonViewModel$saveButtonActionOnServer$1
            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                String str;
                ActivityCustomise4hButtonViewModel.this.getSaveActionsListener().onDataFailure("");
                str = ActivityCustomise4hButtonViewModel.this.b;
                LogHelper.d(str, "Save CustomMessage error");
            }

            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onSuccess(@NotNull Save4hButtonActionsRes object) {
                String str;
                String str2;
                Intrinsics.checkNotNullParameter(object, "object");
                if (object.getCode() == 200) {
                    ActivityCustomise4hButtonViewModel.this.getSaveActionsListener().onSuccess();
                    str2 = ActivityCustomise4hButtonViewModel.this.b;
                    LogHelper.d(str2, "save4hButtonActionOnServer " + new Gson().toJson(object));
                    return;
                }
                ActivityCustomise4hButtonViewModel.this.getSaveActionsListener().onDataFailure("");
                str = ActivityCustomise4hButtonViewModel.this.b;
                LogHelper.d(str, "save4hButtonActionOnServer " + object.getCode());
            }
        });
    }

    public final void getButtonActionSupportedItems(@NotNull String version) {
        Intrinsics.checkNotNullParameter(version, "version");
        CoveUserDeviceSettings.get4hButtonSupportedItems(new CoveApiListener<GetAppConfigRes, CoveApiErrorModel>() { // from class: com.coveiot.android.leonardo.more.viewmodel.ActivityCustomise4hButtonViewModel$getButtonActionSupportedItems$1
            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                GenericBandAppCoveApiListener mListener = ActivityCustomise4hButtonViewModel.this.getMListener();
                String string = ActivityCustomise4hButtonViewModel.this.getContext().getResources().getString(R.string.somethings_went_wrong);
                Intrinsics.checkNotNullExpressionValue(string, "context.resources.getStr…ng.somethings_went_wrong)");
                mListener.onFailure(string);
            }

            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onSuccess(@Nullable GetAppConfigRes getAppConfigRes) {
                String str;
                if (getAppConfigRes != null) {
                    if (getAppConfigRes.getData() != null) {
                        ActivityCustomise4hButtonViewModel activityCustomise4hButtonViewModel = ActivityCustomise4hButtonViewModel.this;
                        GetAppConfigRes.ActionItemsData data = getAppConfigRes.getData();
                        Intrinsics.checkNotNullExpressionValue(data, "response.data");
                        activityCustomise4hButtonViewModel.setSupportedItems(data);
                        str = ActivityCustomise4hButtonViewModel.this.b;
                        LogHelper.d(str, "get4HButtonSupportedActionItems " + new Gson().toJson(ActivityCustomise4hButtonViewModel.this.getSupportedItems()));
                        ActivityCustomise4hButtonViewModel.this.getMListener().onSuccess(getAppConfigRes.getData());
                        return;
                    }
                    GenericBandAppCoveApiListener mListener = ActivityCustomise4hButtonViewModel.this.getMListener();
                    String string = ActivityCustomise4hButtonViewModel.this.getContext().getResources().getString(R.string.somethings_went_wrong);
                    Intrinsics.checkNotNullExpressionValue(string, "context.resources.getStr…ng.somethings_went_wrong)");
                    mListener.onFailure(string);
                }
            }
        }, AppConstants.BUTTON_C11N.getValue(), version);
    }

    public final void getButtonActionsFromBand(@NotNull final Function1<? super GetButtonFunctionResponse, Unit> result) {
        Intrinsics.checkNotNullParameter(result, "result");
        if (BleApiManager.getInstance(this.f5145a).getBleApi().getDeviceSupportedFeatures().is4hButtonFunctionSupported() && BleApiManager.getInstance(this.f5145a).getBleApi().getConnectionStatus() == ConnectionStatus.CONNECTED) {
            BleApiManager.getInstance(this.f5145a).getBleApi().getData(new GetButtonFunctionRequest(Integer.parseInt(AppConstants.BUTTON_4H_POSITION.getValue())), new DataResultListener() { // from class: com.coveiot.android.leonardo.more.viewmodel.ActivityCustomise4hButtonViewModel$getButtonActionsFromBand$1
                @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
                public void onDataError(@NotNull BleBaseError error) {
                    String str;
                    Intrinsics.checkNotNullParameter(error, "error");
                    str = ActivityCustomise4hButtonViewModel.this.b;
                    LogHelper.d(str, "getButtonActionsFromBand error " + error.getErrorMsg());
                    result.invoke(null);
                }

                @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
                public void onDataResponse(@NotNull BleBaseResponse response) {
                    String str;
                    Intrinsics.checkNotNullParameter(response, "response");
                    Object responseData = response.getResponseData();
                    Intrinsics.checkNotNull(responseData, "null cannot be cast to non-null type com.coveiot.android.bleabstract.response.GetButtonFunctionResponse");
                    GetButtonFunctionResponse getButtonFunctionResponse = (GetButtonFunctionResponse) responseData;
                    ActivityCustomise4hButtonViewModel.this.c = getButtonFunctionResponse;
                    result.invoke(getButtonFunctionResponse);
                    str = ActivityCustomise4hButtonViewModel.this.b;
                    LogHelper.d(str, "Get4hButtonFunctionsRequest onDataResponse versionNumber " + getButtonFunctionResponse.getVersionNumber() + " position " + getButtonFunctionResponse.getPosition() + " shortPressFunction " + getButtonFunctionResponse.getShortPressFunction() + " longPressFunction " + getButtonFunctionResponse.getLongPressFunction());
                }

                @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
                public void onProgressUpdate(@NotNull ProgressData progress) {
                    Intrinsics.checkNotNullParameter(progress, "progress");
                }
            });
            return;
        }
        result.invoke(null);
    }

    @NotNull
    public final Context getContext() {
        return this.f5145a;
    }

    @NotNull
    public final GenericBandAppCoveApiListener getMListener() {
        GenericBandAppCoveApiListener genericBandAppCoveApiListener = this.mListener;
        if (genericBandAppCoveApiListener != null) {
            return genericBandAppCoveApiListener;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mListener");
        return null;
    }

    public final void getPreviouslySavedDataFromServer(@NotNull final Function1<? super Button4hActionItems, Unit> result) {
        Intrinsics.checkNotNullParameter(result, "result");
        CoveUserDeviceSettings.getAllUserDeviceSettings(new CoveApiListener<GetAllUserDeviceSettingRes, CoveApiErrorModel>() { // from class: com.coveiot.android.leonardo.more.viewmodel.ActivityCustomise4hButtonViewModel$getPreviouslySavedDataFromServer$1

            @DebugMetadata(c = "com.coveiot.android.leonardo.more.viewmodel.ActivityCustomise4hButtonViewModel$getPreviouslySavedDataFromServer$1$onSuccess$1", f = "ActivityCustomise4hButtonViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
            /* loaded from: classes5.dex */
            public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                public final /* synthetic */ GetAllUserDeviceSettingRes $p0;
                public final /* synthetic */ Function1<Button4hActionItems, Unit> $result;
                public int label;
                public final /* synthetic */ ActivityCustomise4hButtonViewModel this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                public a(GetAllUserDeviceSettingRes getAllUserDeviceSettingRes, Function1<? super Button4hActionItems, Unit> function1, ActivityCustomise4hButtonViewModel activityCustomise4hButtonViewModel, Continuation<? super a> continuation) {
                    super(2, continuation);
                    this.$p0 = getAllUserDeviceSettingRes;
                    this.$result = function1;
                    this.this$0 = activityCustomise4hButtonViewModel;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @NotNull
                public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                    return new a(this.$p0, this.$result, this.this$0, continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                @Nullable
                public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
                    return ((a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @Nullable
                public final Object invokeSuspend(@NotNull Object obj) {
                    String str;
                    GetAllUserDeviceSettingRes.DataBean dataBean;
                    GetAllUserDeviceSettingRes.DataBean dataBean2;
                    GetAllUserDeviceSettingRes.DataBean dataBean3;
                    kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                    if (this.label == 0) {
                        ResultKt.throwOnFailure(obj);
                        GetAllUserDeviceSettingRes getAllUserDeviceSettingRes = this.$p0;
                        Button4hActionItems button4hActionItems = null;
                        if (getAllUserDeviceSettingRes != null) {
                            if ((getAllUserDeviceSettingRes != null ? getAllUserDeviceSettingRes.getDataBean() : null) != null) {
                                GetAllUserDeviceSettingRes getAllUserDeviceSettingRes2 = this.$p0;
                                if (((getAllUserDeviceSettingRes2 == null || (dataBean3 = getAllUserDeviceSettingRes2.getDataBean()) == null) ? null : dataBean3.getButtonC11n()) != null) {
                                    Function1<Button4hActionItems, Unit> function1 = this.$result;
                                    GetAllUserDeviceSettingRes getAllUserDeviceSettingRes3 = this.$p0;
                                    function1.invoke((getAllUserDeviceSettingRes3 == null || (dataBean2 = getAllUserDeviceSettingRes3.getDataBean()) == null) ? null : dataBean2.getButtonC11n());
                                    str = this.this$0.b;
                                    StringBuilder sb = new StringBuilder();
                                    sb.append("getPreviouslySavedDataFromServer ");
                                    Gson gson = new Gson();
                                    GetAllUserDeviceSettingRes getAllUserDeviceSettingRes4 = this.$p0;
                                    if (getAllUserDeviceSettingRes4 != null && (dataBean = getAllUserDeviceSettingRes4.getDataBean()) != null) {
                                        button4hActionItems = dataBean.getButtonC11n();
                                    }
                                    sb.append(gson.toJson(button4hActionItems));
                                    LogHelper.d(str, sb.toString());
                                    return Unit.INSTANCE;
                                }
                            }
                        }
                        this.$result.invoke(null);
                        return Unit.INSTANCE;
                    }
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }

            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                result.invoke(null);
            }

            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onSuccess(@Nullable GetAllUserDeviceSettingRes getAllUserDeviceSettingRes) {
                kotlinx.coroutines.e.e(ViewModelKt.getViewModelScope(this), Dispatchers.getIO(), null, new a(getAllUserDeviceSettingRes, result, this, null), 2, null);
            }
        });
    }

    @NotNull
    public final ViewModelListener getSaveActionsListener() {
        ViewModelListener viewModelListener = this.saveActionsListener;
        if (viewModelListener != null) {
            return viewModelListener;
        }
        Intrinsics.throwUninitializedPropertyAccessException("saveActionsListener");
        return null;
    }

    @NotNull
    public final GetAppConfigRes.ActionItemsData getSupportedItems() {
        GetAppConfigRes.ActionItemsData actionItemsData = this.supportedItems;
        if (actionItemsData != null) {
            return actionItemsData;
        }
        Intrinsics.throwUninitializedPropertyAccessException("supportedItems");
        return null;
    }

    public final void saveButtonActionsToBand(@NotNull final Customise4hButtonData shortPressSelectedItem, @NotNull final Customise4hButtonData longPressSelectedItem, final int i) {
        Intrinsics.checkNotNullParameter(shortPressSelectedItem, "shortPressSelectedItem");
        Intrinsics.checkNotNullParameter(longPressSelectedItem, "longPressSelectedItem");
        if (BleApiManager.getInstance(this.f5145a).getBleApi().getDeviceSupportedFeatures().is4hButtonFunctionSupported() && BleApiManager.getInstance(this.f5145a).getBleApi().getConnectionStatus() == ConnectionStatus.CONNECTED) {
            BleApiManager.getInstance(this.f5145a).getBleApi().setUserSettings(new SetButtonFunctionRequest(i, Integer.parseInt(AppConstants.BUTTON_4H_POSITION.getValue()), shortPressSelectedItem.getFwCode(), longPressSelectedItem.getFwCode()), new SettingsResultListener() { // from class: com.coveiot.android.leonardo.more.viewmodel.ActivityCustomise4hButtonViewModel$saveButtonActionsToBand$1
                @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                public void onSettingsError(@NotNull BleBaseError error) {
                    String str;
                    Intrinsics.checkNotNullParameter(error, "error");
                    ActivityCustomise4hButtonViewModel.this.getSaveActionsListener().onDataFailure(error.getErrorMsg());
                    str = ActivityCustomise4hButtonViewModel.this.b;
                    LogHelper.d(str, "saveActionsToBand onSettingsError");
                }

                @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                public void onSettingsResponse(@NotNull BleBaseResponse response) {
                    String str;
                    Intrinsics.checkNotNullParameter(response, "response");
                    ActivityCustomise4hButtonViewModel.this.a(shortPressSelectedItem, longPressSelectedItem, i);
                    str = ActivityCustomise4hButtonViewModel.this.b;
                    LogHelper.d(str, "saveActionsToBand onSettingsResponse");
                }
            });
            return;
        }
        getSaveActionsListener().onDataFailure("");
    }

    public final void setMListener(@NotNull GenericBandAppCoveApiListener genericBandAppCoveApiListener) {
        Intrinsics.checkNotNullParameter(genericBandAppCoveApiListener, "<set-?>");
        this.mListener = genericBandAppCoveApiListener;
    }

    public final void setSaveActionsListener(@NotNull ViewModelListener viewModelListener) {
        Intrinsics.checkNotNullParameter(viewModelListener, "<set-?>");
        this.saveActionsListener = viewModelListener;
    }

    public final void setSupportedItems(@NotNull GetAppConfigRes.ActionItemsData actionItemsData) {
        Intrinsics.checkNotNullParameter(actionItemsData, "<set-?>");
        this.supportedItems = actionItemsData;
    }
}
