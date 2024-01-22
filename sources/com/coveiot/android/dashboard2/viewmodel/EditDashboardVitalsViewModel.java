package com.coveiot.android.dashboard2.viewmodel;

import android.content.Context;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import com.coveiot.android.dashboard2.Dashboard2PreferenceManager;
import com.coveiot.android.dashboard2.model.FitnessVitalsDataModel;
import com.coveiot.android.dashboard2.model.SelectedFitnessVitalsDataModel;
import com.coveiot.android.dashboard2.util.FitnessVitalsHelper;
import java.util.Collection;
import java.util.List;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes4.dex */
public final class EditDashboardVitalsViewModel extends ViewModel {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f4269a;
    @NotNull
    public final String b;
    @NotNull
    public MutableLiveData<List<FitnessVitalsDataModel>> c;
    @NotNull
    public MutableLiveData<List<FitnessVitalsDataModel>> d;

    @DebugMetadata(c = "com.coveiot.android.dashboard2.viewmodel.EditDashboardVitalsViewModel$getMoreFitnessVitals$1", f = "EditDashboardVitalsViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes4.dex */
    public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public int label;

        public a(Continuation<? super a> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new a(continuation);
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
                List<FitnessVitalsDataModel> moreFitnessVitals = FitnessVitalsHelper.INSTANCE.getMoreFitnessVitals(EditDashboardVitalsViewModel.this.getContext());
                if (!(moreFitnessVitals == null || moreFitnessVitals.isEmpty())) {
                    EditDashboardVitalsViewModel.this.d.postValue(moreFitnessVitals != null ? CollectionsKt___CollectionsKt.toMutableList((Collection) moreFitnessVitals) : null);
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.dashboard2.viewmodel.EditDashboardVitalsViewModel$getSelectedFitnessVitals$1", f = "EditDashboardVitalsViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes4.dex */
    public static final class b extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public int label;

        public b(Continuation<? super b> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new b(continuation);
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
                SelectedFitnessVitalsDataModel selectedFitnessVitals = Dashboard2PreferenceManager.Companion.getInstance(EditDashboardVitalsViewModel.this.getContext()).getSelectedFitnessVitals();
                if (selectedFitnessVitals != null) {
                    List<FitnessVitalsDataModel> fitnessDataModels = selectedFitnessVitals.getFitnessDataModels();
                    if (!(fitnessDataModels == null || fitnessDataModels.isEmpty())) {
                        MutableLiveData mutableLiveData = EditDashboardVitalsViewModel.this.c;
                        List<FitnessVitalsDataModel> fitnessDataModels2 = selectedFitnessVitals.getFitnessDataModels();
                        mutableLiveData.postValue(fitnessDataModels2 != null ? CollectionsKt___CollectionsKt.toMutableList((Collection) fitnessDataModels2) : null);
                    }
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    public EditDashboardVitalsViewModel(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f4269a = context;
        this.b = "EditDashboardVitalsViewModel";
        this.c = new MutableLiveData<>();
        this.d = new MutableLiveData<>();
    }

    @NotNull
    public final Context getContext() {
        return this.f4269a;
    }

    public final void getMoreFitnessVitals() {
        kotlinx.coroutines.e.e(ViewModelKt.getViewModelScope(this), Dispatchers.getIO(), null, new a(null), 2, null);
    }

    @NotNull
    public final MutableLiveData<List<FitnessVitalsDataModel>> getMoreFitnessVitalsLiveData() {
        return this.d;
    }

    public final void getSelectedFitnessVitals() {
        kotlinx.coroutines.e.e(ViewModelKt.getViewModelScope(this), Dispatchers.getIO(), null, new b(null), 2, null);
    }

    @NotNull
    public final MutableLiveData<List<FitnessVitalsDataModel>> getSelectedFitnessVitalsLiveData() {
        return this.c;
    }

    public final void saveEditedVitalsToServer(@Nullable List<FitnessVitalsDataModel> list) {
        kotlinx.coroutines.e.e(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new EditDashboardVitalsViewModel$saveEditedVitalsToServer$1(this, list, null), 2, null);
    }
}
