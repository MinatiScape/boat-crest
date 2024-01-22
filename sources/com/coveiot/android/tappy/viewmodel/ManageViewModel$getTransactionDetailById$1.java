package com.coveiot.android.tappy.viewmodel;

import androidx.lifecycle.ViewModelKt;
import com.coveiot.android.tappy.model.GetTransactionDetailsByIdResponse;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.tappy.TappyApiManager;
import com.coveiot.coveaccess.tappy.model.SGetTransactionDetailsByTransactionIdRequest;
import com.coveiot.coveaccess.tappy.model.SGetTransactionDetailsByTransactionIdResponse;
import com.coveiot.utils.utility.LogHelper;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.a;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.e;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@DebugMetadata(c = "com.coveiot.android.tappy.viewmodel.ManageViewModel$getTransactionDetailById$1", f = "ManageViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes7.dex */
public final class ManageViewModel$getTransactionDetailById$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public final /* synthetic */ long $paymentInstrumentTokenId;
    public final /* synthetic */ long $transactionId;
    public final /* synthetic */ long $userId;
    public int label;
    public final /* synthetic */ ManageViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ManageViewModel$getTransactionDetailById$1(long j, long j2, long j3, ManageViewModel manageViewModel, Continuation<? super ManageViewModel$getTransactionDetailById$1> continuation) {
        super(2, continuation);
        this.$userId = j;
        this.$paymentInstrumentTokenId = j2;
        this.$transactionId = j3;
        this.this$0 = manageViewModel;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        return new ManageViewModel$getTransactionDetailById$1(this.$userId, this.$paymentInstrumentTokenId, this.$transactionId, this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    @Nullable
    public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
        return ((ManageViewModel$getTransactionDetailById$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        a.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            SGetTransactionDetailsByTransactionIdRequest sGetTransactionDetailsByTransactionIdRequest = new SGetTransactionDetailsByTransactionIdRequest();
            sGetTransactionDetailsByTransactionIdRequest.setEndUserId(this.$userId);
            sGetTransactionDetailsByTransactionIdRequest.setPaymentInstrumentTokenId(this.$paymentInstrumentTokenId);
            sGetTransactionDetailsByTransactionIdRequest.setTransactionId(this.$transactionId);
            final ManageViewModel manageViewModel = this.this$0;
            TappyApiManager.getTransactionDetailsByTransactionId(sGetTransactionDetailsByTransactionIdRequest, new CoveApiListener<SGetTransactionDetailsByTransactionIdResponse, CoveApiErrorModel>() { // from class: com.coveiot.android.tappy.viewmodel.ManageViewModel$getTransactionDetailById$1.1

                @DebugMetadata(c = "com.coveiot.android.tappy.viewmodel.ManageViewModel$getTransactionDetailById$1$1$onError$1", f = "ManageViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
                /* renamed from: com.coveiot.android.tappy.viewmodel.ManageViewModel$getTransactionDetailById$1$1$a */
                /* loaded from: classes7.dex */
                public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                    public int label;
                    public final /* synthetic */ ManageViewModel this$0;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    public a(ManageViewModel manageViewModel, Continuation<? super a> continuation) {
                        super(2, continuation);
                        this.this$0 = manageViewModel;
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
                            this.this$0.getGetTransactionDetailsByIdLiveData().setValue(null);
                            return Unit.INSTANCE;
                        }
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                }

                @DebugMetadata(c = "com.coveiot.android.tappy.viewmodel.ManageViewModel$getTransactionDetailById$1$1$onSuccess$1", f = "ManageViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
                /* renamed from: com.coveiot.android.tappy.viewmodel.ManageViewModel$getTransactionDetailById$1$1$b */
                /* loaded from: classes7.dex */
                public static final class b extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                    public final /* synthetic */ SGetTransactionDetailsByTransactionIdResponse $p0;
                    private /* synthetic */ Object L$0;
                    public int label;
                    public final /* synthetic */ ManageViewModel this$0;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    public b(SGetTransactionDetailsByTransactionIdResponse sGetTransactionDetailsByTransactionIdResponse, ManageViewModel manageViewModel, Continuation<? super b> continuation) {
                        super(2, continuation);
                        this.$p0 = sGetTransactionDetailsByTransactionIdResponse;
                        this.this$0 = manageViewModel;
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
                            SGetTransactionDetailsByTransactionIdResponse sGetTransactionDetailsByTransactionIdResponse = this.$p0;
                            if (sGetTransactionDetailsByTransactionIdResponse != null) {
                                ManageViewModel manageViewModel = this.this$0;
                                GetTransactionDetailsByIdResponse getTransactionDetailsByIdResponse = new GetTransactionDetailsByIdResponse();
                                getTransactionDetailsByIdResponse.setTransactionID(sGetTransactionDetailsByTransactionIdResponse.getTransactionID());
                                getTransactionDetailsByIdResponse.setTransactionStatus(sGetTransactionDetailsByTransactionIdResponse.getTransactionStatus());
                                getTransactionDetailsByIdResponse.setTransactionDate(sGetTransactionDetailsByTransactionIdResponse.getTransactionDate());
                                getTransactionDetailsByIdResponse.setTransactionType(sGetTransactionDetailsByTransactionIdResponse.getTransactionType());
                                getTransactionDetailsByIdResponse.setAmount(sGetTransactionDetailsByTransactionIdResponse.getAmount());
                                getTransactionDetailsByIdResponse.setCurrency(sGetTransactionDetailsByTransactionIdResponse.getCurrency());
                                getTransactionDetailsByIdResponse.setIndustryCategoryCode(sGetTransactionDetailsByTransactionIdResponse.getIndustryCategoryCode());
                                getTransactionDetailsByIdResponse.setIndustryCategoryName(sGetTransactionDetailsByTransactionIdResponse.getIndustryCategoryName());
                                getTransactionDetailsByIdResponse.setIndustryCode(sGetTransactionDetailsByTransactionIdResponse.getIndustryCode());
                                getTransactionDetailsByIdResponse.setIndustryName(sGetTransactionDetailsByTransactionIdResponse.getIndustryName());
                                getTransactionDetailsByIdResponse.setMerchantName(sGetTransactionDetailsByTransactionIdResponse.getMerchantName());
                                getTransactionDetailsByIdResponse.setMerchantCity(sGetTransactionDetailsByTransactionIdResponse.getMerchantCity());
                                getTransactionDetailsByIdResponse.setMerchantZipCode(sGetTransactionDetailsByTransactionIdResponse.getMerchantZipCode());
                                getTransactionDetailsByIdResponse.setMerchantCountry(sGetTransactionDetailsByTransactionIdResponse.getMerchantCountry());
                                getTransactionDetailsByIdResponse.setMerchantLatitude(Boxing.boxDouble(sGetTransactionDetailsByTransactionIdResponse.getMerchantLatitude()));
                                getTransactionDetailsByIdResponse.setMerchantLongitude(Boxing.boxDouble(sGetTransactionDetailsByTransactionIdResponse.getMerchantLongitude()));
                                getTransactionDetailsByIdResponse.setCannotBeGeocoded(sGetTransactionDetailsByTransactionIdResponse.isCannotBeGeocoded());
                                manageViewModel.getGetTransactionDetailsByIdLiveData().setValue(getTransactionDetailsByIdResponse);
                                unit = Unit.INSTANCE;
                            } else {
                                unit = null;
                            }
                            if (unit == null) {
                                this.this$0.getGetTransactionDetailsByIdLiveData().setValue(null);
                            }
                            return Unit.INSTANCE;
                        }
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                }

                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                    LogHelper.e(ManageViewModel.this.getTAG(), coveApiErrorModel != null ? coveApiErrorModel.getMsg() : null);
                    e.e(ViewModelKt.getViewModelScope(ManageViewModel.this), Dispatchers.getMain(), null, new a(ManageViewModel.this, null), 2, null);
                }

                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onSuccess(@Nullable SGetTransactionDetailsByTransactionIdResponse sGetTransactionDetailsByTransactionIdResponse) {
                    e.e(ViewModelKt.getViewModelScope(ManageViewModel.this), Dispatchers.getMain(), null, new b(sGetTransactionDetailsByTransactionIdResponse, ManageViewModel.this, null), 2, null);
                }
            });
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
