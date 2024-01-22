package com.coveiot.android.tappy.viewmodel;

import androidx.lifecycle.ViewModelKt;
import com.coveiot.android.tappy.model.ApduCommand;
import com.coveiot.android.tappy.model.InstallFoundationData;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.tappy.TappyApiManager;
import com.coveiot.coveaccess.tappy.model.SApduCommand;
import com.coveiot.coveaccess.tappy.model.SInstallFoundationToSecureElementRequest;
import com.coveiot.coveaccess.tappy.model.SInstallFoundationToSecureElementResponse;
import com.coveiot.utils.utility.LogHelper;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.a;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.e;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@DebugMetadata(c = "com.coveiot.android.tappy.viewmodel.NfcStrapViewModel$installFoundationToSecureElement$1", f = "NfcStrapViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes7.dex */
public final class NfcStrapViewModel$installFoundationToSecureElement$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public final /* synthetic */ int $commandSetIndex;
    public final /* synthetic */ long $endUserId;
    public final /* synthetic */ long $endUserProductRegistrationId;
    public final /* synthetic */ String $initUpdateResponse;
    public final /* synthetic */ int $paymentNetworkID;
    public int label;
    public final /* synthetic */ NfcStrapViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public NfcStrapViewModel$installFoundationToSecureElement$1(long j, long j2, int i, String str, int i2, NfcStrapViewModel nfcStrapViewModel, Continuation<? super NfcStrapViewModel$installFoundationToSecureElement$1> continuation) {
        super(2, continuation);
        this.$endUserId = j;
        this.$endUserProductRegistrationId = j2;
        this.$paymentNetworkID = i;
        this.$initUpdateResponse = str;
        this.$commandSetIndex = i2;
        this.this$0 = nfcStrapViewModel;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        return new NfcStrapViewModel$installFoundationToSecureElement$1(this.$endUserId, this.$endUserProductRegistrationId, this.$paymentNetworkID, this.$initUpdateResponse, this.$commandSetIndex, this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    @Nullable
    public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
        return ((NfcStrapViewModel$installFoundationToSecureElement$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        a.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            SInstallFoundationToSecureElementRequest sInstallFoundationToSecureElementRequest = new SInstallFoundationToSecureElementRequest();
            sInstallFoundationToSecureElementRequest.setEndUserID(this.$endUserId);
            sInstallFoundationToSecureElementRequest.setEndUserProductRegistrationId(this.$endUserProductRegistrationId);
            sInstallFoundationToSecureElementRequest.setPaymentNetworkID(this.$paymentNetworkID);
            sInstallFoundationToSecureElementRequest.setInitUpdateResponse(this.$initUpdateResponse);
            sInstallFoundationToSecureElementRequest.setCommandSetIndex(this.$commandSetIndex);
            final NfcStrapViewModel nfcStrapViewModel = this.this$0;
            TappyApiManager.installFoundationToSecureElement(sInstallFoundationToSecureElementRequest, new CoveApiListener<SInstallFoundationToSecureElementResponse, CoveApiErrorModel>() { // from class: com.coveiot.android.tappy.viewmodel.NfcStrapViewModel$installFoundationToSecureElement$1.1

                @DebugMetadata(c = "com.coveiot.android.tappy.viewmodel.NfcStrapViewModel$installFoundationToSecureElement$1$1$onError$1", f = "NfcStrapViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
                /* renamed from: com.coveiot.android.tappy.viewmodel.NfcStrapViewModel$installFoundationToSecureElement$1$1$a */
                /* loaded from: classes7.dex */
                public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                    public int label;
                    public final /* synthetic */ NfcStrapViewModel this$0;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    public a(NfcStrapViewModel nfcStrapViewModel, Continuation<? super a> continuation) {
                        super(2, continuation);
                        this.this$0 = nfcStrapViewModel;
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
                            this.this$0.getInstallFoundationToSecureElementLiveData().setValue(null);
                            return Unit.INSTANCE;
                        }
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                }

                @DebugMetadata(c = "com.coveiot.android.tappy.viewmodel.NfcStrapViewModel$installFoundationToSecureElement$1$1$onSuccess$1", f = "NfcStrapViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
                /* renamed from: com.coveiot.android.tappy.viewmodel.NfcStrapViewModel$installFoundationToSecureElement$1$1$b */
                /* loaded from: classes7.dex */
                public static final class b extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                    public final /* synthetic */ SInstallFoundationToSecureElementResponse $p0;
                    private /* synthetic */ Object L$0;
                    public int label;
                    public final /* synthetic */ NfcStrapViewModel this$0;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    public b(SInstallFoundationToSecureElementResponse sInstallFoundationToSecureElementResponse, NfcStrapViewModel nfcStrapViewModel, Continuation<? super b> continuation) {
                        super(2, continuation);
                        this.$p0 = sInstallFoundationToSecureElementResponse;
                        this.this$0 = nfcStrapViewModel;
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    @NotNull
                    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                        b bVar = new b(this.$p0, this.this$0, continuation);
                        bVar.L$0 = obj;
                        return bVar;
                    }

                    @Override // kotlin.jvm.functions.Function2
                    @Nullable
                    public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
                        return ((b) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    @Nullable
                    public final Object invokeSuspend(@NotNull Object obj) {
                        Unit unit;
                        Unit unit2;
                        kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                        if (this.label == 0) {
                            ResultKt.throwOnFailure(obj);
                            CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
                            SInstallFoundationToSecureElementResponse sInstallFoundationToSecureElementResponse = this.$p0;
                            if (sInstallFoundationToSecureElementResponse != null) {
                                NfcStrapViewModel nfcStrapViewModel = this.this$0;
                                InstallFoundationData installFoundationData = new InstallFoundationData();
                                installFoundationData.setNextCommandSetIndex(Boxing.boxInt(sInstallFoundationToSecureElementResponse.getNextCommandSetIndex()));
                                installFoundationData.setPaymentApplicationAID(sInstallFoundationToSecureElementResponse.getPaymentApplicationAID());
                                installFoundationData.setNextSecureChannelSelectAIDCommand(sInstallFoundationToSecureElementResponse.getNextSecureChannelSelectAIDCommand());
                                ArrayList<SApduCommand> apduCommands = sInstallFoundationToSecureElementResponse.getApduCommands();
                                if (apduCommands != null) {
                                    Intrinsics.checkNotNullExpressionValue(apduCommands, "apduCommands");
                                    installFoundationData.setApduCommands(new ArrayList());
                                    Iterator<SApduCommand> it = apduCommands.iterator();
                                    while (it.hasNext()) {
                                        SApduCommand next = it.next();
                                        ApduCommand apduCommand = new ApduCommand();
                                        apduCommand.setApdu(next.getApdu());
                                        apduCommand.setName(next.getName());
                                        apduCommand.setIgnoreFailureResponse(Boxing.boxBoolean(next.isIgnoreFailureResponse()));
                                        apduCommand.setSaveResponseData(Boxing.boxBoolean(next.isSaveResponseData()));
                                        List<ApduCommand> apduCommands2 = installFoundationData.getApduCommands();
                                        Intrinsics.checkNotNull(apduCommands2);
                                        apduCommands2.add(apduCommand);
                                    }
                                    nfcStrapViewModel.getInstallFoundationToSecureElementLiveData().setValue(installFoundationData);
                                    unit2 = Unit.INSTANCE;
                                } else {
                                    unit2 = null;
                                }
                                if (unit2 == null) {
                                    nfcStrapViewModel.getInstallFoundationToSecureElementLiveData().setValue(null);
                                }
                                unit = Unit.INSTANCE;
                            } else {
                                unit = null;
                            }
                            if (unit == null) {
                                this.this$0.getInstallFoundationToSecureElementLiveData().setValue(null);
                            }
                            return Unit.INSTANCE;
                        }
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                }

                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                    LogHelper.e(NfcStrapViewModel.this.getTAG(), coveApiErrorModel != null ? coveApiErrorModel.getMsg() : null);
                    e.e(ViewModelKt.getViewModelScope(NfcStrapViewModel.this), Dispatchers.getMain(), null, new a(NfcStrapViewModel.this, null), 2, null);
                }

                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onSuccess(@Nullable SInstallFoundationToSecureElementResponse sInstallFoundationToSecureElementResponse) {
                    e.e(ViewModelKt.getViewModelScope(NfcStrapViewModel.this), Dispatchers.getMain(), null, new b(sInstallFoundationToSecureElementResponse, NfcStrapViewModel.this, null), 2, null);
                }
            });
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
