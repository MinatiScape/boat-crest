package com.clevertap.android.sdk.login;

import androidx.annotation.NonNull;
import com.clevertap.android.sdk.CleverTapInstanceConfig;
/* loaded from: classes2.dex */
public class LegacyIdentityRepo implements IdentityRepo {

    /* renamed from: a  reason: collision with root package name */
    public IdentitySet f2645a;
    public final CleverTapInstanceConfig b;

    public LegacyIdentityRepo(CleverTapInstanceConfig cleverTapInstanceConfig) {
        this.b = cleverTapInstanceConfig;
        a();
    }

    public final void a() {
        this.f2645a = IdentitySet.d();
        CleverTapInstanceConfig cleverTapInstanceConfig = this.b;
        cleverTapInstanceConfig.log(LoginConstants.LOG_TAG_ON_USER_LOGIN, "LegacyIdentityRepo Setting the default IdentitySet[" + this.f2645a + "]");
    }

    @Override // com.clevertap.android.sdk.login.IdentityRepo
    public IdentitySet getIdentitySet() {
        return this.f2645a;
    }

    @Override // com.clevertap.android.sdk.login.IdentityRepo
    public boolean hasIdentity(@NonNull String str) {
        boolean a2 = this.f2645a.a(str);
        CleverTapInstanceConfig cleverTapInstanceConfig = this.b;
        cleverTapInstanceConfig.log(LoginConstants.LOG_TAG_ON_USER_LOGIN, "isIdentity [Key: " + str + " , Value: " + a2 + "]");
        return a2;
    }
}
