package com.coveiot.android.tappy.viewmodel;

import androidx.lifecycle.ViewModelKt;
import com.coveiot.android.tappy.R;
import com.coveiot.android.tappy.model.DeleteRegisteredProductResponse;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.tappy.TappyApiManager;
import com.coveiot.coveaccess.tappy.model.SDeleteRegisteredProductRequest;
import com.coveiot.coveaccess.tappy.model.SDeleteRegisteredProductResponse;
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
@DebugMetadata(c = "com.coveiot.android.tappy.viewmodel.ManageViewModel$deleteRegisteredProduct$1", f = "ManageViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes7.dex */
public final class ManageViewModel$deleteRegisteredProduct$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public final /* synthetic */ long $productRegistrationId;
    public final /* synthetic */ long $userId;
    public int label;
    public final /* synthetic */ ManageViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ManageViewModel$deleteRegisteredProduct$1(long j, long j2, ManageViewModel manageViewModel, Continuation<? super ManageViewModel$deleteRegisteredProduct$1> continuation) {
        super(2, continuation);
        this.$userId = j;
        this.$productRegistrationId = j2;
        this.this$0 = manageViewModel;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        return new ManageViewModel$deleteRegisteredProduct$1(this.$userId, this.$productRegistrationId, this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    @Nullable
    public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
        return ((ManageViewModel$deleteRegisteredProduct$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        a.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            SDeleteRegisteredProductRequest sDeleteRegisteredProductRequest = new SDeleteRegisteredProductRequest();
            sDeleteRegisteredProductRequest.setUserId(this.$userId);
            sDeleteRegisteredProductRequest.setProductRegistrationId(this.$productRegistrationId);
            final ManageViewModel manageViewModel = this.this$0;
            TappyApiManager.deleteRegisteredProduct(sDeleteRegisteredProductRequest, new CoveApiListener<SDeleteRegisteredProductResponse, CoveApiErrorModel>() { // from class: com.coveiot.android.tappy.viewmodel.ManageViewModel$deleteRegisteredProduct$1.1

                @DebugMetadata(c = "com.coveiot.android.tappy.viewmodel.ManageViewModel$deleteRegisteredProduct$1$1$onError$1", f = "ManageViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
                /* renamed from: com.coveiot.android.tappy.viewmodel.ManageViewModel$deleteRegisteredProduct$1$1$a */
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
                            this.this$0.getDeleteRegisteredProductLiveData().setValue(null);
                            this.this$0.getMListener().onError(this.this$0.getContext().getString(R.string.something_went_wrong));
                            return Unit.INSTANCE;
                        }
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                }

                @DebugMetadata(c = "com.coveiot.android.tappy.viewmodel.ManageViewModel$deleteRegisteredProduct$1$1$onSuccess$1", f = "ManageViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
                /* renamed from: com.coveiot.android.tappy.viewmodel.ManageViewModel$deleteRegisteredProduct$1$1$b */
                /* loaded from: classes7.dex */
                public static final class b extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                    public int label;
                    public final /* synthetic */ ManageViewModel this$0;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    public b(ManageViewModel manageViewModel, Continuation<? super b> continuation) {
                        super(2, continuation);
                        this.this$0 = manageViewModel;
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
                            this.this$0.getDeleteRegisteredProductLiveData().setValue(new DeleteRegisteredProductResponse());
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
                public void onSuccess(@Nullable SDeleteRegisteredProductResponse sDeleteRegisteredProductResponse) {
                    ManageViewModel.this.getMListener().onSuccess();
                    e.e(ViewModelKt.getViewModelScope(ManageViewModel.this), Dispatchers.getMain(), null, new b(ManageViewModel.this, null), 2, null);
                }
            });
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
