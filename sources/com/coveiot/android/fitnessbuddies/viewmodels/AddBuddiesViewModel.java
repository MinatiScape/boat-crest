package com.coveiot.android.fitnessbuddies.viewmodels;

import android.content.Context;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.coveiot.android.theme.SuccessResultListener;
import com.coveiot.utils.model.CoveContact;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.e;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes4.dex */
public final class AddBuddiesViewModel extends ViewModel {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f4475a;
    @NotNull
    public MutableLiveData<List<CoveContact>> b;
    @NotNull
    public MutableLiveData<List<CoveContact>> c;
    public SuccessResultListener mListener;

    public AddBuddiesViewModel(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f4475a = context;
        this.b = new MutableLiveData<>();
        this.c = new MutableLiveData<>();
    }

    @NotNull
    public final Context getContext() {
        return this.f4475a;
    }

    @NotNull
    public final MutableLiveData<List<CoveContact>> getGetActiveContacts() {
        return this.b;
    }

    @NotNull
    public final MutableLiveData<List<CoveContact>> getGetInActiveContacts() {
        return this.c;
    }

    @NotNull
    public final SuccessResultListener getMListener() {
        SuccessResultListener successResultListener = this.mListener;
        if (successResultListener != null) {
            return successResultListener;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mListener");
        return null;
    }

    public final void loadCoveBuddies(@NotNull List<CoveContact> coveContactList) {
        Intrinsics.checkNotNullParameter(coveContactList, "coveContactList");
        e.e(CoroutineScopeKt.CoroutineScope(Dispatchers.getIO()), null, null, new AddBuddiesViewModel$loadCoveBuddies$1(coveContactList, this, null), 3, null);
    }

    public final void setGetActiveContacts(@NotNull MutableLiveData<List<CoveContact>> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.b = mutableLiveData;
    }

    public final void setGetInActiveContacts(@NotNull MutableLiveData<List<CoveContact>> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.c = mutableLiveData;
    }

    public final void setMListener(@NotNull SuccessResultListener successResultListener) {
        Intrinsics.checkNotNullParameter(successResultListener, "<set-?>");
        this.mListener = successResultListener;
    }
}
