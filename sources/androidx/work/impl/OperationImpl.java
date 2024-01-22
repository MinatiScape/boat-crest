package androidx.work.impl;

import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.work.Operation;
import androidx.work.impl.utils.futures.SettableFuture;
import com.google.common.util.concurrent.ListenableFuture;
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: classes.dex */
public class OperationImpl implements Operation {

    /* renamed from: a  reason: collision with root package name */
    public final MutableLiveData<Operation.State> f1792a = new MutableLiveData<>();
    public final SettableFuture<Operation.State.SUCCESS> b = SettableFuture.create();

    public OperationImpl() {
        setState(Operation.IN_PROGRESS);
    }

    @Override // androidx.work.Operation
    @NonNull
    public ListenableFuture<Operation.State.SUCCESS> getResult() {
        return this.b;
    }

    @Override // androidx.work.Operation
    @NonNull
    public LiveData<Operation.State> getState() {
        return this.f1792a;
    }

    public void setState(@NonNull Operation.State state) {
        this.f1792a.postValue(state);
        if (state instanceof Operation.State.SUCCESS) {
            this.b.set((Operation.State.SUCCESS) state);
        } else if (state instanceof Operation.State.FAILURE) {
            this.b.setException(((Operation.State.FAILURE) state).getThrowable());
        }
    }
}
