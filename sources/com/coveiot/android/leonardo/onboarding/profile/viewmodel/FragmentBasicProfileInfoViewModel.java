package com.coveiot.android.leonardo.onboarding.profile.viewmodel;

import android.content.Context;
import androidx.lifecycle.ViewModel;
import com.coveiot.android.leonardo.onboarding.profile.listeners.ContractorBasicProfileInfo;
import com.google.android.gms.analytics.ecommerce.Promotion;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class FragmentBasicProfileInfoViewModel extends ViewModel {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f5329a;
    @Nullable
    public String b;
    @Nullable
    public String c;
    @Nullable
    public String d;
    @Nullable
    public String e;
    @Nullable
    public String f;
    public ContractorBasicProfileInfo view;

    public FragmentBasicProfileInfoViewModel(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f5329a = context;
    }

    @NotNull
    public final Context getContext() {
        return this.f5329a;
    }

    @Nullable
    public final String getEmail() {
        return this.c;
    }

    @Nullable
    public final String getMobileNumber() {
        return this.e;
    }

    @Nullable
    public final String getName() {
        return this.b;
    }

    @Nullable
    public final String getProfilePic() {
        return this.d;
    }

    @Nullable
    public final String getSocialMediaId() {
        return this.f;
    }

    @NotNull
    public final ContractorBasicProfileInfo getView() {
        ContractorBasicProfileInfo contractorBasicProfileInfo = this.view;
        if (contractorBasicProfileInfo != null) {
            return contractorBasicProfileInfo;
        }
        Intrinsics.throwUninitializedPropertyAccessException(Promotion.ACTION_VIEW);
        return null;
    }

    /* JADX WARN: Code restructure failed: missing block: B:23:0x0058, code lost:
        if (r0.subSequence(r4, r3 + 1).toString().length() == 0) goto L69;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void onSubmitClicked() {
        /*
            Method dump skipped, instructions count: 277
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.leonardo.onboarding.profile.viewmodel.FragmentBasicProfileInfoViewModel.onSubmitClicked():void");
    }

    public final void setContractInterface(@NotNull ContractorBasicProfileInfo contractorBasicProfileInfo) {
        Intrinsics.checkNotNullParameter(contractorBasicProfileInfo, "contractorBasicProfileInfo");
        setView(contractorBasicProfileInfo);
    }

    public final void setEmail(@Nullable String str) {
        this.c = str;
    }

    public final void setMobileNumber(@NotNull String mobile) {
        Intrinsics.checkNotNullParameter(mobile, "mobile");
        this.e = mobile;
    }

    public final void setName(@Nullable String str) {
        this.b = str;
    }

    public final void setProfilePic(@NotNull String profile_pic) {
        Intrinsics.checkNotNullParameter(profile_pic, "profile_pic");
        this.d = profile_pic;
    }

    public final void setSocialMediaId(@Nullable String str) {
        this.f = str;
    }

    public final void setView(@NotNull ContractorBasicProfileInfo contractorBasicProfileInfo) {
        Intrinsics.checkNotNullParameter(contractorBasicProfileInfo, "<set-?>");
        this.view = contractorBasicProfileInfo;
    }
}
