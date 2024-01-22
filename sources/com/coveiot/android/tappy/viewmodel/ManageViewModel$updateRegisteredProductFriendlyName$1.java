package com.coveiot.android.tappy.viewmodel;

import androidx.lifecycle.ViewModelKt;
import com.coveiot.android.tappy.R;
import com.coveiot.android.tappy.model.UpdateRegisteredProductResponse;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.tappy.TappyApiManager;
import com.coveiot.coveaccess.tappy.model.SRegisteredProduct;
import com.coveiot.coveaccess.tappy.model.SUpdateRegisteredProductFriendlyNameRequest;
import com.coveiot.utils.utility.LogHelper;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.a;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.e;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@DebugMetadata(c = "com.coveiot.android.tappy.viewmodel.ManageViewModel$updateRegisteredProductFriendlyName$1", f = "ManageViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes7.dex */
public final class ManageViewModel$updateRegisteredProductFriendlyName$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public final /* synthetic */ String $friendlyName;
    public final /* synthetic */ long $productRegistrationId;
    public final /* synthetic */ long $userId;
    public int label;
    public final /* synthetic */ ManageViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ManageViewModel$updateRegisteredProductFriendlyName$1(long j, long j2, String str, ManageViewModel manageViewModel, Continuation<? super ManageViewModel$updateRegisteredProductFriendlyName$1> continuation) {
        super(2, continuation);
        this.$userId = j;
        this.$productRegistrationId = j2;
        this.$friendlyName = str;
        this.this$0 = manageViewModel;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        return new ManageViewModel$updateRegisteredProductFriendlyName$1(this.$userId, this.$productRegistrationId, this.$friendlyName, this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    @Nullable
    public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
        return ((ManageViewModel$updateRegisteredProductFriendlyName$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        a.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            SUpdateRegisteredProductFriendlyNameRequest sUpdateRegisteredProductFriendlyNameRequest = new SUpdateRegisteredProductFriendlyNameRequest();
            sUpdateRegisteredProductFriendlyNameRequest.setUserId(this.$userId);
            sUpdateRegisteredProductFriendlyNameRequest.setProductRegistrationId(this.$productRegistrationId);
            sUpdateRegisteredProductFriendlyNameRequest.setFriendlyName(this.$friendlyName);
            final ManageViewModel manageViewModel = this.this$0;
            TappyApiManager.updateRegisteredProductFriendlyName(sUpdateRegisteredProductFriendlyNameRequest, new CoveApiListener<SRegisteredProduct, CoveApiErrorModel>() { // from class: com.coveiot.android.tappy.viewmodel.ManageViewModel$updateRegisteredProductFriendlyName$1.1

                @DebugMetadata(c = "com.coveiot.android.tappy.viewmodel.ManageViewModel$updateRegisteredProductFriendlyName$1$1$onError$1", f = "ManageViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
                /* renamed from: com.coveiot.android.tappy.viewmodel.ManageViewModel$updateRegisteredProductFriendlyName$1$1$a */
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
                            this.this$0.getUpdateRegisteredProductLiveData().setValue(null);
                            return Unit.INSTANCE;
                        }
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                }

                @DebugMetadata(c = "com.coveiot.android.tappy.viewmodel.ManageViewModel$updateRegisteredProductFriendlyName$1$1$onSuccess$1", f = "ManageViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
                /* renamed from: com.coveiot.android.tappy.viewmodel.ManageViewModel$updateRegisteredProductFriendlyName$1$1$b */
                /* loaded from: classes7.dex */
                public static final class b extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                    public final /* synthetic */ SRegisteredProduct $p0;
                    private /* synthetic */ Object L$0;
                    public int label;
                    public final /* synthetic */ ManageViewModel this$0;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    public b(SRegisteredProduct sRegisteredProduct, ManageViewModel manageViewModel, Continuation<? super b> continuation) {
                        super(2, continuation);
                        this.$p0 = sRegisteredProduct;
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
                            if (this.$p0 != null) {
                                this.this$0.getUpdateRegisteredProductLiveData().setValue(new UpdateRegisteredProductResponse());
                                unit = Unit.INSTANCE;
                            } else {
                                unit = null;
                            }
                            if (unit == null) {
                                this.this$0.getUpdateRegisteredProductLiveData().setValue(null);
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
                    ManageViewModel.this.getMListener().onError(ManageViewModel.this.getContext().getString(R.string.something_went_wrong));
                }

                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onSuccess(@Nullable SRegisteredProduct sRegisteredProduct) {
                    e.e(ViewModelKt.getViewModelScope(ManageViewModel.this), Dispatchers.getMain(), null, new b(sRegisteredProduct, ManageViewModel.this, null), 2, null);
                    ManageViewModel.this.getMListener().onSuccess();
                }
            });
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
