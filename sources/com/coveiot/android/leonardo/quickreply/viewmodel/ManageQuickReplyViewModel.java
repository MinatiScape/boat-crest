package com.coveiot.android.leonardo.quickreply.viewmodel;

import android.content.Context;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.listeners.SettingsResultListener;
import com.coveiot.android.bleabstract.models.ConnectionStatus;
import com.coveiot.android.bleabstract.request.SetQuickReplyRequest;
import com.coveiot.android.bleabstract.response.BleBaseError;
import com.coveiot.android.bleabstract.response.BleBaseResponse;
import com.coveiot.android.boat.R;
import com.coveiot.android.dashboard2.listener.ViewModelListener;
import com.coveiot.android.leonardo.listener.DialogListener;
import com.coveiot.android.leonardo.quickreply.model.QuickReplyModel;
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
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.e;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class ManageQuickReplyViewModel extends ViewModel {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f5347a;
    @NotNull
    public final String b;
    @Nullable
    public MutableLiveData<List<QuickReplyModel>> c;
    public DialogListener dialogListener;
    public ViewModelListener mListener;

    public ManageQuickReplyViewModel(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f5347a = context;
        this.b = "Quick Reply Settings";
    }

    @NotNull
    public final Context getContext() {
        return this.f5347a;
    }

    @NotNull
    public final DialogListener getDialogListener() {
        DialogListener dialogListener = this.dialogListener;
        if (dialogListener != null) {
            return dialogListener;
        }
        Intrinsics.throwUninitializedPropertyAccessException("dialogListener");
        return null;
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

    @Nullable
    public final MutableLiveData<List<QuickReplyModel>> getQuickReplyListData() {
        if (this.c == null) {
            this.c = new MutableLiveData<>();
            QuickReplyListModel quickReplyListFromPref = UserDataManager.getInstance(this.f5347a).getQuickReplyListFromPref();
            if (quickReplyListFromPref == null) {
                h();
            } else {
                ArrayList arrayList = new ArrayList();
                for (String str : quickReplyListFromPref.getQuickReplyMessage()) {
                    arrayList.add(new QuickReplyModel(str));
                }
                MutableLiveData<List<QuickReplyModel>> mutableLiveData = this.c;
                if (mutableLiveData != null) {
                    mutableLiveData.setValue(arrayList);
                }
            }
        }
        return this.c;
    }

    @NotNull
    public final String getTAG() {
        return this.b;
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [T, java.util.ArrayList] */
    public final void h() {
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = new ArrayList();
        if (AppUtils.isNetConnected(this.f5347a) && BleApiManager.getInstance(this.f5347a).getBleApi().getDeviceSupportedFeatures().isQuickReplySupported() && BleApiManager.getInstance(this.f5347a).getBleApi().getConnectionStatus() == ConnectionStatus.CONNECTED) {
            CoveUserAppSettings.getAllUserAppSettings(new CoveApiListener<GetAllUserAppSettingsRes, CoveApiErrorModel>() { // from class: com.coveiot.android.leonardo.quickreply.viewmodel.ManageQuickReplyViewModel$getQuickReplyList$1
                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                    this.setDefaultQuickReplyValues();
                }

                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onSuccess(@Nullable GetAllUserAppSettingsRes getAllUserAppSettingsRes) {
                    MutableLiveData mutableLiveData;
                    if (getAllUserAppSettingsRes != null && getAllUserAppSettingsRes.getCallQuickReplies() != null && getAllUserAppSettingsRes.getCallQuickReplies().getMessages() != null) {
                        List<QuickReplyModel> list = objectRef.element;
                        Intrinsics.checkNotNull(list, "null cannot be cast to non-null type java.util.ArrayList<com.coveiot.android.leonardo.quickreply.model.QuickReplyModel>{ kotlin.collections.TypeAliasesKt.ArrayList<com.coveiot.android.leonardo.quickreply.model.QuickReplyModel> }");
                        ArrayList arrayList = (ArrayList) list;
                        for (String item : getAllUserAppSettingsRes.getCallQuickReplies().getMessages()) {
                            Intrinsics.checkNotNullExpressionValue(item, "item");
                            ((ArrayList) objectRef.element).add(new QuickReplyModel(item));
                        }
                        mutableLiveData = this.c;
                        if (mutableLiveData == null) {
                            return;
                        }
                        mutableLiveData.setValue(objectRef.element);
                        return;
                    }
                    this.setDefaultQuickReplyValues();
                }
            });
        }
    }

    public final void saveQuickReplySettings(@NotNull final List<String> quickReplyList) {
        Intrinsics.checkNotNullParameter(quickReplyList, "quickReplyList");
        SaveQuickReplySettingsReq saveQuickReplySettingsReq = new SaveQuickReplySettingsReq();
        saveQuickReplySettingsReq.setActive(true);
        saveQuickReplySettingsReq.setQuickReplyMessages(quickReplyList);
        CoveUserAppSettings.saveQuickReplySettings(saveQuickReplySettingsReq, new CoveApiListener<SaveQuickReplySettingsRes, CoveApiErrorModel>() { // from class: com.coveiot.android.leonardo.quickreply.viewmodel.ManageQuickReplyViewModel$saveQuickReplySettings$1

            @DebugMetadata(c = "com.coveiot.android.leonardo.quickreply.viewmodel.ManageQuickReplyViewModel$saveQuickReplySettings$1$onError$1", f = "ManageQuickReplyViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
            /* loaded from: classes5.dex */
            public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                public int label;
                public final /* synthetic */ ManageQuickReplyViewModel this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public a(ManageQuickReplyViewModel manageQuickReplyViewModel, Continuation<? super a> continuation) {
                    super(2, continuation);
                    this.this$0 = manageQuickReplyViewModel;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @NotNull
                public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                    return new a(this.this$0, continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                @Nullable
                public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
                    return ((a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @Nullable
                public final Object invokeSuspend(@NotNull Object obj) {
                    kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                    if (this.label == 0) {
                        ResultKt.throwOnFailure(obj);
                        this.this$0.getDialogListener().showErrorDialog();
                        return Unit.INSTANCE;
                    }
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }

            @DebugMetadata(c = "com.coveiot.android.leonardo.quickreply.viewmodel.ManageQuickReplyViewModel$saveQuickReplySettings$1$onSuccess$1", f = "ManageQuickReplyViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
            /* loaded from: classes5.dex */
            public static final class b extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                public int label;
                public final /* synthetic */ ManageQuickReplyViewModel this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public b(ManageQuickReplyViewModel manageQuickReplyViewModel, Continuation<? super b> continuation) {
                    super(2, continuation);
                    this.this$0 = manageQuickReplyViewModel;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @NotNull
                public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                    return new b(this.this$0, continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                @Nullable
                public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
                    return ((b) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @Nullable
                public final Object invokeSuspend(@NotNull Object obj) {
                    kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                    if (this.label == 0) {
                        ResultKt.throwOnFailure(obj);
                        this.this$0.getDialogListener().showSuccessDialog();
                        return Unit.INSTANCE;
                    }
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }

            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                LogHelper.d(ManageQuickReplyViewModel.this.getTAG(), ErrorConstants.GENERIC_ERROR);
                CoroutineScope viewModelScope = ViewModelKt.getViewModelScope(ManageQuickReplyViewModel.this);
                if (viewModelScope != null) {
                    e.e(viewModelScope, Dispatchers.getMain(), null, new a(ManageQuickReplyViewModel.this, null), 2, null);
                }
            }

            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onSuccess(@NotNull SaveQuickReplySettingsRes saveQuickReplySettingsRes) {
                Intrinsics.checkNotNullParameter(saveQuickReplySettingsRes, "saveQuickReplySettingsRes");
                LogHelper.d(ManageQuickReplyViewModel.this.getTAG(), "Success");
                UserDataManager.getInstance(ManageQuickReplyViewModel.this.getContext()).saveQuickReplyListData(new QuickReplyListModel(true, quickReplyList));
                CoroutineScope viewModelScope = ViewModelKt.getViewModelScope(ManageQuickReplyViewModel.this);
                if (viewModelScope != null) {
                    e.e(viewModelScope, Dispatchers.getMain(), null, new b(ManageQuickReplyViewModel.this, null), 2, null);
                }
            }
        });
    }

    public final void setDefaultQuickReplyValues() {
        ArrayList arrayList = new ArrayList();
        String string = this.f5347a.getString(R.string.quick_reply_message_1);
        Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.quick_reply_message_1)");
        String string2 = this.f5347a.getString(R.string.quick_reply_message_2);
        Intrinsics.checkNotNullExpressionValue(string2, "context.getString(R.string.quick_reply_message_2)");
        String string3 = this.f5347a.getString(R.string.quick_reply_message_3);
        Intrinsics.checkNotNullExpressionValue(string3, "context.getString(R.string.quick_reply_message_3)");
        String string4 = this.f5347a.getString(R.string.quick_reply_message_4);
        Intrinsics.checkNotNullExpressionValue(string4, "context.getString(R.string.quick_reply_message_4)");
        String string5 = this.f5347a.getString(R.string.quick_reply_message_5);
        Intrinsics.checkNotNullExpressionValue(string5, "context.getString(R.string.quick_reply_message_5)");
        arrayList.add(new QuickReplyModel(string));
        arrayList.add(new QuickReplyModel(string2));
        arrayList.add(new QuickReplyModel(string3));
        arrayList.add(new QuickReplyModel(string4));
        arrayList.add(new QuickReplyModel(string5));
        MutableLiveData<List<QuickReplyModel>> mutableLiveData = this.c;
        if (mutableLiveData == null) {
            return;
        }
        mutableLiveData.setValue(arrayList);
    }

    public final void setDialogListener(@NotNull DialogListener dialogListener) {
        Intrinsics.checkNotNullParameter(dialogListener, "<set-?>");
        this.dialogListener = dialogListener;
    }

    public final void setMListener(@NotNull ViewModelListener viewModelListener) {
        Intrinsics.checkNotNullParameter(viewModelListener, "<set-?>");
        this.mListener = viewModelListener;
    }

    public final void setQuickReplyList(@NotNull final List<String> quickReplyList) {
        Intrinsics.checkNotNullParameter(quickReplyList, "quickReplyList");
        if (AppUtils.isNetConnected(this.f5347a)) {
            if (BleApiManager.getInstance(this.f5347a).getBleApi().getDeviceSupportedFeatures().isQuickReplySupported()) {
                if (BleApiManager.getInstance(this.f5347a).getBleApi().getConnectionStatus() == ConnectionStatus.CONNECTED) {
                    getDialogListener().onShowProgressDialog();
                    ArrayList arrayList = new ArrayList();
                    arrayList.clear();
                    for (String str : quickReplyList) {
                        arrayList.add(new BleQuickReplyModel(str));
                    }
                    BleApiManager.getInstance(this.f5347a).getBleApi().setUserSettings(new SetQuickReplyRequest.Builder().setQuickReply(true, arrayList).build(), new SettingsResultListener() { // from class: com.coveiot.android.leonardo.quickreply.viewmodel.ManageQuickReplyViewModel$setQuickReplyList$1

                        @DebugMetadata(c = "com.coveiot.android.leonardo.quickreply.viewmodel.ManageQuickReplyViewModel$setQuickReplyList$1$onSettingsError$1", f = "ManageQuickReplyViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
                        /* loaded from: classes5.dex */
                        public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                            public int label;
                            public final /* synthetic */ ManageQuickReplyViewModel this$0;

                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            public a(ManageQuickReplyViewModel manageQuickReplyViewModel, Continuation<? super a> continuation) {
                                super(2, continuation);
                                this.this$0 = manageQuickReplyViewModel;
                            }

                            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                            @NotNull
                            public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                                return new a(this.this$0, continuation);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            @Nullable
                            public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
                                return ((a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                            }

                            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                            @Nullable
                            public final Object invokeSuspend(@NotNull Object obj) {
                                kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                                if (this.label == 0) {
                                    ResultKt.throwOnFailure(obj);
                                    this.this$0.getDialogListener().showErrorDialog();
                                    return Unit.INSTANCE;
                                }
                                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                            }
                        }

                        @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                        public void onSettingsError(@NotNull BleBaseError error) {
                            Intrinsics.checkNotNullParameter(error, "error");
                            LogHelper.d(ManageQuickReplyViewModel.this.getTAG(), ErrorConstants.GENERIC_ERROR);
                            CoroutineScope viewModelScope = ViewModelKt.getViewModelScope(ManageQuickReplyViewModel.this);
                            if (viewModelScope != null) {
                                e.e(viewModelScope, Dispatchers.getMain(), null, new a(ManageQuickReplyViewModel.this, null), 2, null);
                            }
                        }

                        @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                        public void onSettingsResponse(@NotNull BleBaseResponse response) {
                            Intrinsics.checkNotNullParameter(response, "response");
                            LogHelper.d(ManageQuickReplyViewModel.this.getTAG(), "Success");
                            ManageQuickReplyViewModel.this.saveQuickReplySettings(quickReplyList);
                        }
                    });
                    return;
                }
                ViewModelListener mListener = getMListener();
                String string = this.f5347a.getResources().getString(R.string.band_disconnected);
                Intrinsics.checkNotNullExpressionValue(string, "context.resources.getStr…string.band_disconnected)");
                mListener.onDataFailure(string);
                return;
            }
            return;
        }
        ViewModelListener mListener2 = getMListener();
        String string2 = this.f5347a.getResources().getString(R.string.please_check_your_internet);
        Intrinsics.checkNotNullExpressionValue(string2, "context.resources.getStr…ease_check_your_internet)");
        mListener2.onDataFailure(string2);
    }
}
