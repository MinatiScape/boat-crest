package com.coveiot.android.leonardo.boatcoin.viewmodel;

import androidx.lifecycle.ViewModelKt;
import com.coveiot.android.leonardo.boatcoin.model.Recipient;
import com.coveiot.android.leonardo.boatcoin.model.RecipientData;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.CoveSocial;
import com.coveiot.coveaccess.boatcoins.model.CoinsDataRequest;
import com.coveiot.coveaccess.boatcoins.model.CoinsDataRequestResponse;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import java.util.ArrayList;
import java.util.List;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.a;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.e;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@DebugMetadata(c = "com.coveiot.android.leonardo.boatcoin.viewmodel.BoatCoinsContactsViewModel$sendCoins$1", f = "BoatCoinsContactsViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes2.dex */
public final class BoatCoinsContactsViewModel$sendCoins$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public final /* synthetic */ CoinsDataRequest $coinsDataRequest;
    public int label;
    public final /* synthetic */ BoatCoinsContactsViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BoatCoinsContactsViewModel$sendCoins$1(CoinsDataRequest coinsDataRequest, BoatCoinsContactsViewModel boatCoinsContactsViewModel, Continuation<? super BoatCoinsContactsViewModel$sendCoins$1> continuation) {
        super(2, continuation);
        this.$coinsDataRequest = coinsDataRequest;
        this.this$0 = boatCoinsContactsViewModel;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        return new BoatCoinsContactsViewModel$sendCoins$1(this.$coinsDataRequest, this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    @Nullable
    public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
        return ((BoatCoinsContactsViewModel$sendCoins$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        a.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            CoinsDataRequest coinsDataRequest = this.$coinsDataRequest;
            final BoatCoinsContactsViewModel boatCoinsContactsViewModel = this.this$0;
            CoveSocial.sendCoinsData(coinsDataRequest, new CoveApiListener<CoinsDataRequestResponse, CoveApiErrorModel>() { // from class: com.coveiot.android.leonardo.boatcoin.viewmodel.BoatCoinsContactsViewModel$sendCoins$1.1

                @DebugMetadata(c = "com.coveiot.android.leonardo.boatcoin.viewmodel.BoatCoinsContactsViewModel$sendCoins$1$1$onError$1", f = "BoatCoinsContactsViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
                /* renamed from: com.coveiot.android.leonardo.boatcoin.viewmodel.BoatCoinsContactsViewModel$sendCoins$1$1$a */
                /* loaded from: classes2.dex */
                public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                    public int label;
                    public final /* synthetic */ BoatCoinsContactsViewModel this$0;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    public a(BoatCoinsContactsViewModel boatCoinsContactsViewModel, Continuation<? super a> continuation) {
                        super(2, continuation);
                        this.this$0 = boatCoinsContactsViewModel;
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
                            this.this$0.getSendCoinResponseLiveData().postValue(null);
                            return Unit.INSTANCE;
                        }
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                }

                @DebugMetadata(c = "com.coveiot.android.leonardo.boatcoin.viewmodel.BoatCoinsContactsViewModel$sendCoins$1$1$onSuccess$1", f = "BoatCoinsContactsViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
                /* renamed from: com.coveiot.android.leonardo.boatcoin.viewmodel.BoatCoinsContactsViewModel$sendCoins$1$1$b */
                /* loaded from: classes2.dex */
                public static final class b extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                    public final /* synthetic */ List<Recipient> $recipients;
                    public int label;
                    public final /* synthetic */ BoatCoinsContactsViewModel this$0;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    public b(BoatCoinsContactsViewModel boatCoinsContactsViewModel, List<Recipient> list, Continuation<? super b> continuation) {
                        super(2, continuation);
                        this.this$0 = boatCoinsContactsViewModel;
                        this.$recipients = list;
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    @NotNull
                    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                        return new b(this.this$0, this.$recipients, continuation);
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
                            this.this$0.getSendCoinResponseLiveData().postValue(this.$recipients);
                            return Unit.INSTANCE;
                        }
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                }

                @DebugMetadata(c = "com.coveiot.android.leonardo.boatcoin.viewmodel.BoatCoinsContactsViewModel$sendCoins$1$1$onSuccess$2", f = "BoatCoinsContactsViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
                /* renamed from: com.coveiot.android.leonardo.boatcoin.viewmodel.BoatCoinsContactsViewModel$sendCoins$1$1$c */
                /* loaded from: classes2.dex */
                public static final class c extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                    public int label;
                    public final /* synthetic */ BoatCoinsContactsViewModel this$0;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    public c(BoatCoinsContactsViewModel boatCoinsContactsViewModel, Continuation<? super c> continuation) {
                        super(2, continuation);
                        this.this$0 = boatCoinsContactsViewModel;
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    @NotNull
                    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                        return new c(this.this$0, continuation);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    @Nullable
                    public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
                        return ((c) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    @Nullable
                    public final Object invokeSuspend(@NotNull Object obj) {
                        kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
                        if (this.label == 0) {
                            ResultKt.throwOnFailure(obj);
                            this.this$0.getSendCoinResponseLiveData().postValue(null);
                            return Unit.INSTANCE;
                        }
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                }

                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                    e.e(ViewModelKt.getViewModelScope(BoatCoinsContactsViewModel.this), Dispatchers.getMain(), null, new a(BoatCoinsContactsViewModel.this, null), 2, null);
                }

                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onSuccess(@Nullable CoinsDataRequestResponse coinsDataRequestResponse) {
                    CoinsDataRequestResponse.Data data;
                    List<CoinsDataRequestResponse.Data.Recipient> recipients = (coinsDataRequestResponse == null || (data = coinsDataRequestResponse.data) == null) ? null : data.getRecipients();
                    if (recipients == null || recipients.isEmpty()) {
                        e.e(ViewModelKt.getViewModelScope(BoatCoinsContactsViewModel.this), Dispatchers.getMain(), null, new c(BoatCoinsContactsViewModel.this, null), 2, null);
                        return;
                    }
                    ArrayList arrayList = new ArrayList();
                    for (CoinsDataRequestResponse.Data.Recipient recipient : recipients) {
                        Recipient recipient2 = new Recipient(null, null, null, 7, null);
                        recipient2.setStatus(recipient.getStatus());
                        recipient2.setMessage(recipient.getMessage());
                        if (recipient.getData() != null) {
                            RecipientData recipientData = new RecipientData(null, null, null, null, 15, null);
                            CoinsDataRequestResponse.Data.Recipient.RecipientData data2 = recipient.getData();
                            Intrinsics.checkNotNull(data2);
                            recipientData.setCoins(Integer.valueOf(data2.getCoins()));
                            CoinsDataRequestResponse.Data.Recipient.RecipientData data3 = recipient.getData();
                            Intrinsics.checkNotNull(data3);
                            recipientData.setInviteText(data3.getInviteText());
                            CoinsDataRequestResponse.Data.Recipient.RecipientData data4 = recipient.getData();
                            Intrinsics.checkNotNull(data4);
                            recipientData.setName(data4.getName());
                            CoinsDataRequestResponse.Data.Recipient.RecipientData data5 = recipient.getData();
                            Intrinsics.checkNotNull(data5);
                            recipientData.setMobileNumber(data5.getMobileNumber());
                            recipient2.setData(recipientData);
                        }
                        arrayList.add(recipient2);
                    }
                    e.e(ViewModelKt.getViewModelScope(BoatCoinsContactsViewModel.this), Dispatchers.getMain(), null, new b(BoatCoinsContactsViewModel.this, arrayList, null), 2, null);
                }
            });
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
