package com.coveiot.android.leonardo.onboarding.profile.viewmodel;

import android.content.Context;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.coveiot.android.dashboard2.util.Dashboard2Constants;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class FragmentGenderViewModel extends ViewModel {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f5333a;
    @Nullable
    public MutableLiveData<String> b;

    public FragmentGenderViewModel(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f5333a = context;
        this.b = new MutableLiveData<>();
    }

    @NotNull
    public final Context getContext() {
        return this.f5333a;
    }

    @Nullable
    public final MutableLiveData<String> getGender() {
        return this.b;
    }

    public final void onFemaleSelected() {
        MutableLiveData<String> mutableLiveData = this.b;
        if (mutableLiveData != null) {
            Intrinsics.checkNotNull(mutableLiveData);
            if (String.valueOf(mutableLiveData.getValue()).equals(Dashboard2Constants.FEMALE)) {
                MutableLiveData<String> mutableLiveData2 = this.b;
                Intrinsics.checkNotNull(mutableLiveData2);
                mutableLiveData2.setValue(null);
                return;
            }
            MutableLiveData<String> mutableLiveData3 = this.b;
            Intrinsics.checkNotNull(mutableLiveData3);
            mutableLiveData3.setValue(Dashboard2Constants.FEMALE);
            return;
        }
        Intrinsics.checkNotNull(mutableLiveData);
        mutableLiveData.setValue(Dashboard2Constants.FEMALE);
    }

    public final void onMaleSlected() {
        MutableLiveData<String> mutableLiveData = this.b;
        if (mutableLiveData != null) {
            Intrinsics.checkNotNull(mutableLiveData);
            if (String.valueOf(mutableLiveData.getValue()).equals("Male")) {
                MutableLiveData<String> mutableLiveData2 = this.b;
                Intrinsics.checkNotNull(mutableLiveData2);
                mutableLiveData2.setValue(null);
                return;
            }
            MutableLiveData<String> mutableLiveData3 = this.b;
            Intrinsics.checkNotNull(mutableLiveData3);
            mutableLiveData3.setValue("Male");
            return;
        }
        Intrinsics.checkNotNull(mutableLiveData);
        mutableLiveData.setValue("Male");
    }

    public final void setGender(@Nullable MutableLiveData<String> mutableLiveData) {
        this.b = mutableLiveData;
    }

    @Nullable
    /* renamed from: getGender  reason: collision with other method in class */
    public final String m107getGender() {
        MutableLiveData<String> mutableLiveData = this.b;
        Intrinsics.checkNotNull(mutableLiveData);
        return mutableLiveData.getValue();
    }
}
