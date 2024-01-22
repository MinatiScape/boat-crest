package com.coveiot.android.tappy.viewmodel;

import android.content.Context;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import com.coveiot.android.tappy.model.DeletePaymentInstrumentTokenResponse;
import com.coveiot.android.tappy.model.DeleteRegisteredProductResponse;
import com.coveiot.android.tappy.model.GetTransactionDetailsByIdResponse;
import com.coveiot.android.tappy.model.GetTransactionDetailsResponse;
import com.coveiot.android.tappy.model.ResumePaymentInstrumentTokenResponse;
import com.coveiot.android.tappy.model.SuspendPaymentInstrumentTokenResponse;
import com.coveiot.android.tappy.model.UpdateRegisteredProductResponse;
import com.coveiot.android.theme.SuccessResultListener;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.e;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes7.dex */
public final class ManageViewModel extends ViewModel {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f6019a;
    @NotNull
    public final String b;
    @NotNull
    public final MutableLiveData<UpdateRegisteredProductResponse> c;
    @NotNull
    public final MutableLiveData<DeleteRegisteredProductResponse> d;
    @NotNull
    public final MutableLiveData<GetTransactionDetailsResponse> e;
    @NotNull
    public final MutableLiveData<DeletePaymentInstrumentTokenResponse> f;
    @NotNull
    public final MutableLiveData<SuspendPaymentInstrumentTokenResponse> g;
    @NotNull
    public final MutableLiveData<ResumePaymentInstrumentTokenResponse> h;
    @NotNull
    public final MutableLiveData<GetTransactionDetailsByIdResponse> i;
    public SuccessResultListener mListener;

    public ManageViewModel(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f6019a = context;
        this.b = "ManageViewModel";
        this.c = new MutableLiveData<>();
        this.d = new MutableLiveData<>();
        this.e = new MutableLiveData<>();
        this.f = new MutableLiveData<>();
        this.g = new MutableLiveData<>();
        this.h = new MutableLiveData<>();
        this.i = new MutableLiveData<>();
    }

    public static /* synthetic */ void deletePaymentInstrumentTokens$default(ManageViewModel manageViewModel, long j, long j2, String str, int i, Object obj) {
        if ((i & 4) != 0) {
            str = "Not provided";
        }
        manageViewModel.deletePaymentInstrumentTokens(j, j2, str);
    }

    public static /* synthetic */ void resumePaymentInstrumentTokens$default(ManageViewModel manageViewModel, long j, long j2, String str, int i, Object obj) {
        if ((i & 4) != 0) {
            str = "Not provided";
        }
        manageViewModel.resumePaymentInstrumentTokens(j, j2, str);
    }

    public static /* synthetic */ void suspendPaymentInstrumentTokens$default(ManageViewModel manageViewModel, long j, long j2, String str, int i, Object obj) {
        if ((i & 4) != 0) {
            str = "Not provided";
        }
        manageViewModel.suspendPaymentInstrumentTokens(j, j2, str);
    }

    public final void deletePaymentInstrumentTokens(long j, long j2, @Nullable String str) {
        e.e(ViewModelKt.getViewModelScope(this), Dispatchers.getIO(), null, new ManageViewModel$deletePaymentInstrumentTokens$1(j, j2, str, this, null), 2, null);
    }

    public final void deleteRegisteredProduct(long j, long j2) {
        e.e(ViewModelKt.getViewModelScope(this), Dispatchers.getIO(), null, new ManageViewModel$deleteRegisteredProduct$1(j, j2, this, null), 2, null);
    }

    @NotNull
    public final Context getContext() {
        return this.f6019a;
    }

    @NotNull
    public final MutableLiveData<DeletePaymentInstrumentTokenResponse> getDeletePaymentInstrumentTokenLiveData() {
        return this.f;
    }

    @NotNull
    public final MutableLiveData<DeleteRegisteredProductResponse> getDeleteRegisteredProductLiveData() {
        return this.d;
    }

    @NotNull
    public final MutableLiveData<GetTransactionDetailsByIdResponse> getGetTransactionDetailsByIdLiveData() {
        return this.i;
    }

    @NotNull
    public final MutableLiveData<GetTransactionDetailsResponse> getGetTransactionHistoryLiveData() {
        return this.e;
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

    @NotNull
    public final MutableLiveData<ResumePaymentInstrumentTokenResponse> getResumePaymentInstrumentTokenLiveData() {
        return this.h;
    }

    @NotNull
    public final MutableLiveData<SuspendPaymentInstrumentTokenResponse> getSuspendPaymentInstrumentTokenLiveData() {
        return this.g;
    }

    @NotNull
    public final String getTAG() {
        return this.b;
    }

    public final void getTransactionDetailById(long j, long j2, long j3) {
        e.e(ViewModelKt.getViewModelScope(this), Dispatchers.getIO(), null, new ManageViewModel$getTransactionDetailById$1(j, j2, j3, this, null), 2, null);
    }

    public final void getTransactionHistory(long j, long j2) {
        e.e(ViewModelKt.getViewModelScope(this), Dispatchers.getIO(), null, new ManageViewModel$getTransactionHistory$1(j, j2, this, null), 2, null);
    }

    @NotNull
    public final MutableLiveData<UpdateRegisteredProductResponse> getUpdateRegisteredProductLiveData() {
        return this.c;
    }

    public final void resumePaymentInstrumentTokens(long j, long j2, @Nullable String str) {
        e.e(ViewModelKt.getViewModelScope(this), Dispatchers.getIO(), null, new ManageViewModel$resumePaymentInstrumentTokens$1(j, j2, str, this, null), 2, null);
    }

    public final void setMListener(@NotNull SuccessResultListener successResultListener) {
        Intrinsics.checkNotNullParameter(successResultListener, "<set-?>");
        this.mListener = successResultListener;
    }

    public final void suspendPaymentInstrumentTokens(long j, long j2, @Nullable String str) {
        e.e(ViewModelKt.getViewModelScope(this), Dispatchers.getIO(), null, new ManageViewModel$suspendPaymentInstrumentTokens$1(j, j2, str, this, null), 2, null);
    }

    public final void updateRegisteredProductFriendlyName(@NotNull String friendlyName, long j, long j2) {
        Intrinsics.checkNotNullParameter(friendlyName, "friendlyName");
        e.e(ViewModelKt.getViewModelScope(this), Dispatchers.getIO(), null, new ManageViewModel$updateRegisteredProductFriendlyName$1(j, j2, friendlyName, this, null), 2, null);
    }
}
