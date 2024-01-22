package com.coveiot.android.tappy.viewmodel;

import androidx.lifecycle.ViewModelKt;
import com.coveiot.android.tappy.model.APDUResponse;
import com.coveiot.android.tappy.model.ApduCommand;
import com.coveiot.android.tappy.model.ConfirmProvisioningRequest;
import com.coveiot.android.tappy.model.ConfirmProvisioningResponseData;
import com.coveiot.android.tappy.model.PaymentInstrumentData;
import com.coveiot.android.tappy.model.PaymentInstrumentTokenData;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.tappy.TappyApiManager;
import com.coveiot.coveaccess.tappy.model.SApduCommand;
import com.coveiot.coveaccess.tappy.model.SConfirmProvisioningRequest;
import com.coveiot.coveaccess.tappy.model.SConfirmProvisioningResponse;
import com.coveiot.coveaccess.tappy.model.SPaymentInstrument;
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
@DebugMetadata(c = "com.coveiot.android.tappy.viewmodel.NfcStrapViewModel$confirmProvisioning$1", f = "NfcStrapViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes7.dex */
public final class NfcStrapViewModel$confirmProvisioning$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public final /* synthetic */ ConfirmProvisioningRequest $confirmProvisioningRequest;
    public final /* synthetic */ long $paymentInstrumentTokenId;
    public final /* synthetic */ long $userId;
    public int label;
    public final /* synthetic */ NfcStrapViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public NfcStrapViewModel$confirmProvisioning$1(long j, long j2, ConfirmProvisioningRequest confirmProvisioningRequest, NfcStrapViewModel nfcStrapViewModel, Continuation<? super NfcStrapViewModel$confirmProvisioning$1> continuation) {
        super(2, continuation);
        this.$userId = j;
        this.$paymentInstrumentTokenId = j2;
        this.$confirmProvisioningRequest = confirmProvisioningRequest;
        this.this$0 = nfcStrapViewModel;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        return new NfcStrapViewModel$confirmProvisioning$1(this.$userId, this.$paymentInstrumentTokenId, this.$confirmProvisioningRequest, this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    @Nullable
    public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
        return ((NfcStrapViewModel$confirmProvisioning$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        a.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            SConfirmProvisioningRequest sConfirmProvisioningRequest = new SConfirmProvisioningRequest();
            sConfirmProvisioningRequest.setEndUserId(this.$userId);
            sConfirmProvisioningRequest.setPaymentInstrumentTokenId(this.$paymentInstrumentTokenId);
            sConfirmProvisioningRequest.setInitUpdateResponse(this.$confirmProvisioningRequest.getInitUpdateResponse());
            sConfirmProvisioningRequest.setFailureReason(this.$confirmProvisioningRequest.getFailureReason());
            sConfirmProvisioningRequest.setHasSucceeded(this.$confirmProvisioningRequest.getHasSucceeded());
            sConfirmProvisioningRequest.setaPDUResponses(new ArrayList<>());
            List<APDUResponse> apduResponses = this.$confirmProvisioningRequest.getApduResponses();
            Intrinsics.checkNotNull(apduResponses);
            for (APDUResponse aPDUResponse : apduResponses) {
                SConfirmProvisioningRequest.APDUResponse aPDUResponse2 = new SConfirmProvisioningRequest.APDUResponse();
                aPDUResponse2.setaPDUID(aPDUResponse.getApduId());
                aPDUResponse2.setaPDUResponse(aPDUResponse.getApduResponse());
                aPDUResponse2.setaPDUReasonCode(aPDUResponse.getApduReasonCode());
                sConfirmProvisioningRequest.getaPDUResponses().add(aPDUResponse2);
            }
            final NfcStrapViewModel nfcStrapViewModel = this.this$0;
            TappyApiManager.confirmProvisioning(sConfirmProvisioningRequest, new CoveApiListener<SConfirmProvisioningResponse, CoveApiErrorModel>() { // from class: com.coveiot.android.tappy.viewmodel.NfcStrapViewModel$confirmProvisioning$1.1

                @DebugMetadata(c = "com.coveiot.android.tappy.viewmodel.NfcStrapViewModel$confirmProvisioning$1$1$onError$1", f = "NfcStrapViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
                /* renamed from: com.coveiot.android.tappy.viewmodel.NfcStrapViewModel$confirmProvisioning$1$1$a */
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
                            this.this$0.getConfirmProvisioningLiveData().setValue(null);
                            return Unit.INSTANCE;
                        }
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                }

                @DebugMetadata(c = "com.coveiot.android.tappy.viewmodel.NfcStrapViewModel$confirmProvisioning$1$1$onSuccess$1", f = "NfcStrapViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
                /* renamed from: com.coveiot.android.tappy.viewmodel.NfcStrapViewModel$confirmProvisioning$1$1$b */
                /* loaded from: classes7.dex */
                public static final class b extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                    public final /* synthetic */ SConfirmProvisioningResponse $p0;
                    private /* synthetic */ Object L$0;
                    public int label;
                    public final /* synthetic */ NfcStrapViewModel this$0;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    public b(SConfirmProvisioningResponse sConfirmProvisioningResponse, NfcStrapViewModel nfcStrapViewModel, Continuation<? super b> continuation) {
                        super(2, continuation);
                        this.$p0 = sConfirmProvisioningResponse;
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
                        kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                        if (this.label == 0) {
                            ResultKt.throwOnFailure(obj);
                            CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
                            SConfirmProvisioningResponse sConfirmProvisioningResponse = this.$p0;
                            if (sConfirmProvisioningResponse != null) {
                                NfcStrapViewModel nfcStrapViewModel = this.this$0;
                                ConfirmProvisioningResponseData confirmProvisioningResponseData = new ConfirmProvisioningResponseData();
                                if (sConfirmProvisioningResponse.getPaymentInstrumentToken() != null) {
                                    PaymentInstrumentTokenData paymentInstrumentTokenData = new PaymentInstrumentTokenData();
                                    paymentInstrumentTokenData.setPaymentInstrumentTokenID(Boxing.boxLong(sConfirmProvisioningResponse.getPaymentInstrumentToken().getPaymentInstrumentTokenID()));
                                    paymentInstrumentTokenData.setExpiryDate(sConfirmProvisioningResponse.getPaymentInstrumentToken().getExpiryDate());
                                    paymentInstrumentTokenData.setLast4(sConfirmProvisioningResponse.getPaymentInstrumentToken().getLast4());
                                    paymentInstrumentTokenData.setStatus(sConfirmProvisioningResponse.getPaymentInstrumentToken().getStatus());
                                    paymentInstrumentTokenData.setFriendlyName(sConfirmProvisioningResponse.getPaymentInstrumentToken().getFriendlyName());
                                    paymentInstrumentTokenData.setProvisioningStatus(sConfirmProvisioningResponse.getPaymentInstrumentToken().getProvisioningStatus());
                                    PaymentInstrumentData paymentInstrumentData = new PaymentInstrumentData();
                                    SPaymentInstrument paymentInstrument = sConfirmProvisioningResponse.getPaymentInstrumentToken().getPaymentInstrument();
                                    paymentInstrumentData.setPaymentInstrumentID(paymentInstrument != null ? Boxing.boxLong(paymentInstrument.getPaymentInstrumentID()) : null);
                                    SPaymentInstrument paymentInstrument2 = sConfirmProvisioningResponse.getPaymentInstrumentToken().getPaymentInstrument();
                                    paymentInstrumentData.setLast4(paymentInstrument2 != null ? paymentInstrument2.getLast4() : null);
                                    SPaymentInstrument paymentInstrument3 = sConfirmProvisioningResponse.getPaymentInstrumentToken().getPaymentInstrument();
                                    paymentInstrumentData.setFriendlyName(paymentInstrument3 != null ? paymentInstrument3.getFriendlyName() : null);
                                    SPaymentInstrument paymentInstrument4 = sConfirmProvisioningResponse.getPaymentInstrumentToken().getPaymentInstrument();
                                    paymentInstrumentData.setBackgroundColor(paymentInstrument4 != null ? paymentInstrument4.getBackgroundColor() : null);
                                    SPaymentInstrument paymentInstrument5 = sConfirmProvisioningResponse.getPaymentInstrumentToken().getPaymentInstrument();
                                    paymentInstrumentData.setCardArtImageUrl(paymentInstrument5 != null ? paymentInstrument5.getCardArtImageUrl() : null);
                                    SPaymentInstrument paymentInstrument6 = sConfirmProvisioningResponse.getPaymentInstrumentToken().getPaymentInstrument();
                                    paymentInstrumentData.setCardSymbolImageUrl(paymentInstrument6 != null ? paymentInstrument6.getCardSymbolImageUrl() : null);
                                    SPaymentInstrument paymentInstrument7 = sConfirmProvisioningResponse.getPaymentInstrumentToken().getPaymentInstrument();
                                    paymentInstrumentData.setContactEmail(paymentInstrument7 != null ? paymentInstrument7.getContactEmail() : null);
                                    SPaymentInstrument paymentInstrument8 = sConfirmProvisioningResponse.getPaymentInstrumentToken().getPaymentInstrument();
                                    paymentInstrumentData.setContactName(paymentInstrument8 != null ? paymentInstrument8.getContactName() : null);
                                    SPaymentInstrument paymentInstrument9 = sConfirmProvisioningResponse.getPaymentInstrumentToken().getPaymentInstrument();
                                    paymentInstrumentData.setContactNumber(paymentInstrument9 != null ? paymentInstrument9.getContactNumber() : null);
                                    SPaymentInstrument paymentInstrument10 = sConfirmProvisioningResponse.getPaymentInstrumentToken().getPaymentInstrument();
                                    paymentInstrumentData.setContactWebSite(paymentInstrument10 != null ? paymentInstrument10.getContactWebSite() : null);
                                    SPaymentInstrument paymentInstrument11 = sConfirmProvisioningResponse.getPaymentInstrumentToken().getPaymentInstrument();
                                    paymentInstrumentData.setForegroundColor(paymentInstrument11 != null ? paymentInstrument11.getForegroundColor() : null);
                                    SPaymentInstrument paymentInstrument12 = sConfirmProvisioningResponse.getPaymentInstrumentToken().getPaymentInstrument();
                                    paymentInstrumentData.setDeleted(paymentInstrument12 != null ? Boxing.boxBoolean(paymentInstrument12.isDeleted()) : null);
                                    SPaymentInstrument paymentInstrument13 = sConfirmProvisioningResponse.getPaymentInstrumentToken().getPaymentInstrument();
                                    paymentInstrumentData.setPIFinalized(paymentInstrument13 != null ? Boxing.boxBoolean(paymentInstrument13.isPIFinalized()) : null);
                                    SPaymentInstrument paymentInstrument14 = sConfirmProvisioningResponse.getPaymentInstrumentToken().getPaymentInstrument();
                                    paymentInstrumentData.setIssuerType(paymentInstrument14 != null ? paymentInstrument14.getIssuerType() : null);
                                    SPaymentInstrument paymentInstrument15 = sConfirmProvisioningResponse.getPaymentInstrumentToken().getPaymentInstrument();
                                    paymentInstrumentData.setLabelColor(paymentInstrument15 != null ? paymentInstrument15.getLabelColor() : null);
                                    SPaymentInstrument paymentInstrument16 = sConfirmProvisioningResponse.getPaymentInstrumentToken().getPaymentInstrument();
                                    paymentInstrumentData.setPaymentNetworkID(paymentInstrument16 != null ? Boxing.boxInt(paymentInstrument16.getPaymentNetworkID()) : null);
                                    SPaymentInstrument paymentInstrument17 = sConfirmProvisioningResponse.getPaymentInstrumentToken().getPaymentInstrument();
                                    paymentInstrumentData.setPaymentNetworkName(paymentInstrument17 != null ? paymentInstrument17.getPaymentNetworkName() : null);
                                    SPaymentInstrument paymentInstrument18 = sConfirmProvisioningResponse.getPaymentInstrumentToken().getPaymentInstrument();
                                    paymentInstrumentData.setTermsAndConditionsID(paymentInstrument18 != null ? paymentInstrument18.getTermsAndConditionsID() : null);
                                    SPaymentInstrument paymentInstrument19 = sConfirmProvisioningResponse.getPaymentInstrumentToken().getPaymentInstrument();
                                    paymentInstrumentData.setTermsAndConditionsFileUrl(paymentInstrument19 != null ? paymentInstrument19.getTermsAndConditionsFileUrl() : null);
                                    SPaymentInstrument paymentInstrument20 = sConfirmProvisioningResponse.getPaymentInstrumentToken().getPaymentInstrument();
                                    paymentInstrumentData.setTermsAndConditionsUrl(paymentInstrument20 != null ? paymentInstrument20.getTermsAndConditionsUrl() : null);
                                    SPaymentInstrument paymentInstrument21 = sConfirmProvisioningResponse.getPaymentInstrumentToken().getPaymentInstrument();
                                    paymentInstrumentData.setPrivacyPolicyUrl(paymentInstrument21 != null ? paymentInstrument21.getPrivacyPolicyUrl() : null);
                                    paymentInstrumentTokenData.setPaymentInstrument(paymentInstrumentData);
                                    confirmProvisioningResponseData.setPaymentInstrumentToken(paymentInstrumentTokenData);
                                }
                                ArrayList<SApduCommand> arrayList = sConfirmProvisioningResponse.getaPDUCommands();
                                if (!(arrayList == null || arrayList.isEmpty())) {
                                    ArrayList arrayList2 = new ArrayList();
                                    Iterator<SApduCommand> it = sConfirmProvisioningResponse.getaPDUCommands().iterator();
                                    while (it.hasNext()) {
                                        SApduCommand next = it.next();
                                        ApduCommand apduCommand = new ApduCommand();
                                        apduCommand.setName(next.getName());
                                        apduCommand.setSaveResponseData(Boxing.boxBoolean(next.isSaveResponseData()));
                                        apduCommand.setApdu(next.getApdu());
                                        apduCommand.setIgnoreFailureResponse(Boxing.boxBoolean(next.isIgnoreFailureResponse()));
                                        arrayList2.add(apduCommand);
                                    }
                                    confirmProvisioningResponseData.setApduCommands(arrayList2);
                                }
                                nfcStrapViewModel.getConfirmProvisioningLiveData().setValue(confirmProvisioningResponseData);
                                unit = Unit.INSTANCE;
                            } else {
                                unit = null;
                            }
                            if (unit == null) {
                                this.this$0.getConfirmProvisioningLiveData().setValue(null);
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
                public void onSuccess(@Nullable SConfirmProvisioningResponse sConfirmProvisioningResponse) {
                    e.e(ViewModelKt.getViewModelScope(NfcStrapViewModel.this), Dispatchers.getMain(), null, new b(sConfirmProvisioningResponse, NfcStrapViewModel.this, null), 2, null);
                }
            });
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
