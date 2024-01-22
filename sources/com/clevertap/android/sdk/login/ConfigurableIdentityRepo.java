package com.clevertap.android.sdk.login;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import com.clevertap.android.sdk.CleverTapInstanceConfig;
import com.clevertap.android.sdk.DeviceInfo;
import com.clevertap.android.sdk.validation.ValidationResultFactory;
import com.clevertap.android.sdk.validation.ValidationResultStack;
@RestrictTo({RestrictTo.Scope.LIBRARY})
/* loaded from: classes2.dex */
public class ConfigurableIdentityRepo implements IdentityRepo {

    /* renamed from: a  reason: collision with root package name */
    public IdentitySet f2643a;
    public final LoginInfoProvider b;
    public final CleverTapInstanceConfig c;
    public final ValidationResultStack d;

    public ConfigurableIdentityRepo(Context context, CleverTapInstanceConfig cleverTapInstanceConfig, DeviceInfo deviceInfo, ValidationResultStack validationResultStack) {
        this(cleverTapInstanceConfig, new LoginInfoProvider(context, cleverTapInstanceConfig, deviceInfo), validationResultStack);
    }

    public final void a(IdentitySet identitySet, IdentitySet identitySet2) {
        if (identitySet.f() && identitySet2.f() && !identitySet.equals(identitySet2)) {
            this.d.pushValidationResult(ValidationResultFactory.create(531));
            CleverTapInstanceConfig cleverTapInstanceConfig = this.c;
            cleverTapInstanceConfig.log(LoginConstants.LOG_TAG_ON_USER_LOGIN, "ConfigurableIdentityRepopushing error due to mismatch [Pref:" + identitySet + "], [Config:" + identitySet2 + "]");
            return;
        }
        CleverTapInstanceConfig cleverTapInstanceConfig2 = this.c;
        cleverTapInstanceConfig2.log(LoginConstants.LOG_TAG_ON_USER_LOGIN, "ConfigurableIdentityRepoNo error found while comparing [Pref:" + identitySet + "], [Config:" + identitySet2 + "]");
    }

    public void b() {
        IdentitySet b = IdentitySet.b(this.b.getCachedIdentityKeysForAccount());
        CleverTapInstanceConfig cleverTapInstanceConfig = this.c;
        cleverTapInstanceConfig.log(LoginConstants.LOG_TAG_ON_USER_LOGIN, "ConfigurableIdentityRepoPrefIdentitySet [" + b + "]");
        IdentitySet c = IdentitySet.c(this.c.getIdentityKeys());
        CleverTapInstanceConfig cleverTapInstanceConfig2 = this.c;
        cleverTapInstanceConfig2.log(LoginConstants.LOG_TAG_ON_USER_LOGIN, "ConfigurableIdentityRepoConfigIdentitySet [" + c + "]");
        a(b, c);
        if (b.f()) {
            this.f2643a = b;
            CleverTapInstanceConfig cleverTapInstanceConfig3 = this.c;
            cleverTapInstanceConfig3.log(LoginConstants.LOG_TAG_ON_USER_LOGIN, "ConfigurableIdentityRepoIdentity Set activated from Pref[" + this.f2643a + "]");
        } else if (c.f()) {
            this.f2643a = c;
            CleverTapInstanceConfig cleverTapInstanceConfig4 = this.c;
            cleverTapInstanceConfig4.log(LoginConstants.LOG_TAG_ON_USER_LOGIN, "ConfigurableIdentityRepoIdentity Set activated from Config[" + this.f2643a + "]");
        } else {
            this.f2643a = IdentitySet.d();
            CleverTapInstanceConfig cleverTapInstanceConfig5 = this.c;
            cleverTapInstanceConfig5.log(LoginConstants.LOG_TAG_ON_USER_LOGIN, "ConfigurableIdentityRepoIdentity Set activated from Default[" + this.f2643a + "]");
        }
        if (b.f()) {
            return;
        }
        String identitySet = this.f2643a.toString();
        this.b.saveIdentityKeysForAccount(identitySet);
        CleverTapInstanceConfig cleverTapInstanceConfig6 = this.c;
        cleverTapInstanceConfig6.log(LoginConstants.LOG_TAG_ON_USER_LOGIN, "ConfigurableIdentityRepoSaving Identity Keys in Pref[" + identitySet + "]");
    }

    @Override // com.clevertap.android.sdk.login.IdentityRepo
    public IdentitySet getIdentitySet() {
        return this.f2643a;
    }

    @Override // com.clevertap.android.sdk.login.IdentityRepo
    public boolean hasIdentity(@NonNull String str) {
        boolean a2 = this.f2643a.a(str);
        CleverTapInstanceConfig cleverTapInstanceConfig = this.c;
        cleverTapInstanceConfig.log(LoginConstants.LOG_TAG_ON_USER_LOGIN, "ConfigurableIdentityRepoisIdentity [Key: " + str + " , Value: " + a2 + "]");
        return a2;
    }

    public ConfigurableIdentityRepo(CleverTapInstanceConfig cleverTapInstanceConfig, LoginInfoProvider loginInfoProvider, ValidationResultStack validationResultStack) {
        this.c = cleverTapInstanceConfig;
        this.b = loginInfoProvider;
        this.d = validationResultStack;
        b();
    }
}
