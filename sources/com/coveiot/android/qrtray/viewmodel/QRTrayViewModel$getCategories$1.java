package com.coveiot.android.qrtray.viewmodel;

import android.content.Context;
import com.coveiot.android.qrtray.R;
import com.coveiot.android.qrtray.viewmodel.QRTrayViewModel;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.qrtray.CoveQRTrayApi;
import com.coveiot.coveaccess.qrtray.model.QRTrayCategoriesRes;
import java.util.ArrayList;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.a;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@DebugMetadata(c = "com.coveiot.android.qrtray.viewmodel.QRTrayViewModel$getCategories$1", f = "QRTrayViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes5.dex */
public final class QRTrayViewModel$getCategories$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public int label;
    public final /* synthetic */ QRTrayViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public QRTrayViewModel$getCategories$1(QRTrayViewModel qRTrayViewModel, Continuation<? super QRTrayViewModel$getCategories$1> continuation) {
        super(2, continuation);
        this.this$0 = qRTrayViewModel;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        return new QRTrayViewModel$getCategories$1(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    @Nullable
    public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
        return ((QRTrayViewModel$getCategories$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        a.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            final QRTrayViewModel qRTrayViewModel = this.this$0;
            CoveQRTrayApi.getQRTrayCategories(new CoveApiListener<QRTrayCategoriesRes, CoveApiErrorModel>() { // from class: com.coveiot.android.qrtray.viewmodel.QRTrayViewModel$getCategories$1.1
                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onError(@NotNull CoveApiErrorModel object) {
                    Intrinsics.checkNotNullParameter(object, "object");
                    QRTrayViewModel.QRTrayViewModelContract listener = QRTrayViewModel.this.getListener();
                    if (listener != null) {
                        listener.onFailure(object.getMsg());
                    }
                }

                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onSuccess(@Nullable QRTrayCategoriesRes qRTrayCategoriesRes) {
                    Context context;
                    if (qRTrayCategoriesRes != null) {
                        if (qRTrayCategoriesRes.getQrItemsList() != null) {
                            QRTrayViewModel.this.getCategoryMutableList().postValue((ArrayList) qRTrayCategoriesRes.getQrItemsList());
                            return;
                        } else {
                            QRTrayViewModel.this.getCategoryMutableList().postValue((ArrayList) new QRTrayCategoriesRes.QRItem[0]);
                            return;
                        }
                    }
                    QRTrayViewModel.QRTrayViewModelContract listener = QRTrayViewModel.this.getListener();
                    if (listener != null) {
                        context = QRTrayViewModel.this.f;
                        listener.onFailure(context.getString(R.string.something_went_wrong));
                    }
                }
            });
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
