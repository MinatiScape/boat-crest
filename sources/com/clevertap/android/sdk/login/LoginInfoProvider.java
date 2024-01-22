package com.clevertap.android.sdk.login;

import android.content.Context;
import android.text.TextUtils;
import androidx.annotation.RestrictTo;
import com.clevertap.android.sdk.CleverTapInstanceConfig;
import com.clevertap.android.sdk.Constants;
import com.clevertap.android.sdk.DeviceInfo;
import com.clevertap.android.sdk.Logger;
import com.clevertap.android.sdk.StorageHelper;
import com.clevertap.android.sdk.cryption.CryptHandler;
import com.clevertap.android.sdk.cryption.CryptUtils;
import com.clevertap.android.sdk.utils.CTJsonConverter;
import java.util.Iterator;
import java.util.Objects;
import org.json.JSONObject;
@RestrictTo({RestrictTo.Scope.LIBRARY})
/* loaded from: classes2.dex */
public class LoginInfoProvider {

    /* renamed from: a  reason: collision with root package name */
    public final CleverTapInstanceConfig f2647a;
    public final Context b;
    public final DeviceInfo c;
    public CryptHandler d;

    public LoginInfoProvider(Context context, CleverTapInstanceConfig cleverTapInstanceConfig, DeviceInfo deviceInfo) {
        this.b = context;
        this.f2647a = cleverTapInstanceConfig;
        this.c = deviceInfo;
    }

    public final boolean a() {
        boolean isErrorDeviceId = this.c.isErrorDeviceId();
        CleverTapInstanceConfig cleverTapInstanceConfig = this.f2647a;
        cleverTapInstanceConfig.log(LoginConstants.LOG_TAG_ON_USER_LOGIN, "isErrorDeviceId:[" + isErrorDeviceId + "]");
        return isErrorDeviceId;
    }

    public void cacheGUIDForIdentifier(String str, String str2, String str3) {
        if (a() || str == null || str2 == null || str3 == null) {
            return;
        }
        String encrypt = this.d.encrypt(str3, str2);
        if (encrypt == null) {
            CryptUtils.updateEncryptionFlagOnFailure(this.b, this.f2647a, 1, this.d);
        } else {
            str3 = encrypt;
        }
        String str4 = str2 + "_" + str3;
        JSONObject cachedGUIDs = getCachedGUIDs();
        try {
            cachedGUIDs.put(str4, str);
            setCachedGUIDs(cachedGUIDs);
        } catch (Throwable th) {
            this.f2647a.getLogger().verbose(this.f2647a.getAccountId(), "Error caching guid: " + th);
        }
    }

    public boolean deviceIsMultiUser() {
        boolean z = getCachedGUIDs().length() > 1;
        CleverTapInstanceConfig cleverTapInstanceConfig = this.f2647a;
        cleverTapInstanceConfig.log(LoginConstants.LOG_TAG_ON_USER_LOGIN, "deviceIsMultiUser:[" + z + "]");
        return z;
    }

    public JSONObject getCachedGUIDs() {
        String stringFromPrefs = StorageHelper.getStringFromPrefs(this.b, this.f2647a, Constants.CACHED_GUIDS_KEY, null);
        CleverTapInstanceConfig cleverTapInstanceConfig = this.f2647a;
        cleverTapInstanceConfig.log(LoginConstants.LOG_TAG_ON_USER_LOGIN, "getCachedGUIDs:[" + stringFromPrefs + "]");
        return CTJsonConverter.toJsonObject(stringFromPrefs, this.f2647a.getLogger(), this.f2647a.getAccountId());
    }

    public String getCachedIdentityKeysForAccount() {
        String stringFromPrefs = StorageHelper.getStringFromPrefs(this.b, this.f2647a, Constants.SP_KEY_PROFILE_IDENTITIES, "");
        CleverTapInstanceConfig cleverTapInstanceConfig = this.f2647a;
        cleverTapInstanceConfig.log(LoginConstants.LOG_TAG_ON_USER_LOGIN, "getCachedIdentityKeysForAccount:" + stringFromPrefs);
        return stringFromPrefs;
    }

    public String getGUIDForIdentifier(String str, String str2) {
        if (str != null && str2 != null) {
            String encrypt = this.d.encrypt(str2, str);
            String str3 = str + "_" + encrypt;
            JSONObject cachedGUIDs = getCachedGUIDs();
            try {
                String string = cachedGUIDs.getString(str3);
                this.f2647a.log(LoginConstants.LOG_TAG_ON_USER_LOGIN, "getGUIDForIdentifier:[Key:" + str + ", value:" + string + "]");
                return string;
            } catch (Throwable th) {
                this.f2647a.getLogger().verbose(this.f2647a.getAccountId(), "Error reading guid cache: " + th);
                if (Objects.equals(encrypt, str2)) {
                    return null;
                }
                try {
                    String string2 = cachedGUIDs.getString(str + "_" + str2);
                    this.f2647a.log(LoginConstants.LOG_TAG_ON_USER_LOGIN, "getGUIDForIdentifier:[Key:" + str + ", value:" + string2 + "] after retry");
                    return string2;
                } catch (Throwable th2) {
                    this.f2647a.getLogger().verbose(this.f2647a.getAccountId(), "Error reading guid cache after retry: " + th2);
                }
            }
        }
        return null;
    }

    public boolean isAnonymousDevice() {
        boolean z = getCachedGUIDs().length() <= 0;
        CleverTapInstanceConfig cleverTapInstanceConfig = this.f2647a;
        cleverTapInstanceConfig.log(LoginConstants.LOG_TAG_ON_USER_LOGIN, "isAnonymousDevice:[" + z + "]");
        return z;
    }

    public boolean isLegacyProfileLoggedIn() {
        JSONObject cachedGUIDs = getCachedGUIDs();
        boolean z = cachedGUIDs != null && cachedGUIDs.length() > 0 && TextUtils.isEmpty(getCachedIdentityKeysForAccount());
        CleverTapInstanceConfig cleverTapInstanceConfig = this.f2647a;
        cleverTapInstanceConfig.log(LoginConstants.LOG_TAG_ON_USER_LOGIN, "isLegacyProfileLoggedIn:" + z);
        return z;
    }

    public void removeCachedGuidFromSharedPrefs() {
        try {
            StorageHelper.remove(this.b, StorageHelper.storageKeyWithSuffix(this.f2647a, Constants.CACHED_GUIDS_KEY));
            this.f2647a.log(LoginConstants.LOG_TAG_ON_USER_LOGIN, "removeCachedGUIDs:[]");
        } catch (Throwable th) {
            Logger logger = this.f2647a.getLogger();
            String accountId = this.f2647a.getAccountId();
            logger.verbose(accountId, "Error removing guid cache: " + th);
        }
    }

    public void removeValueFromCachedGUIDForIdentifier(String str, String str2) {
        if (a() || str == null || str2 == null) {
            return;
        }
        JSONObject cachedGUIDs = getCachedGUIDs();
        try {
            Iterator<String> keys = cachedGUIDs.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                if (next.toLowerCase().contains(str2.toLowerCase()) && cachedGUIDs.getString(next).equals(str)) {
                    cachedGUIDs.remove(next);
                    if (cachedGUIDs.length() == 0) {
                        removeCachedGuidFromSharedPrefs();
                    } else {
                        setCachedGUIDs(cachedGUIDs);
                    }
                }
            }
        } catch (Throwable th) {
            Logger logger = this.f2647a.getLogger();
            String accountId = this.f2647a.getAccountId();
            logger.verbose(accountId, "Error removing cached key: " + th);
        }
    }

    public void saveIdentityKeysForAccount(String str) {
        StorageHelper.putString(this.b, this.f2647a, Constants.SP_KEY_PROFILE_IDENTITIES, str);
        CleverTapInstanceConfig cleverTapInstanceConfig = this.f2647a;
        cleverTapInstanceConfig.log(LoginConstants.LOG_TAG_ON_USER_LOGIN, "saveIdentityKeysForAccount:" + str);
    }

    public void setCachedGUIDs(JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        try {
            String jSONObject2 = jSONObject.toString();
            StorageHelper.putString(this.b, StorageHelper.storageKeyWithSuffix(this.f2647a, Constants.CACHED_GUIDS_KEY), jSONObject2);
            CleverTapInstanceConfig cleverTapInstanceConfig = this.f2647a;
            cleverTapInstanceConfig.log(LoginConstants.LOG_TAG_ON_USER_LOGIN, "setCachedGUIDs:[" + jSONObject2 + "]");
        } catch (Throwable th) {
            Logger logger = this.f2647a.getLogger();
            String accountId = this.f2647a.getAccountId();
            logger.verbose(accountId, "Error persisting guid cache: " + th);
        }
    }

    public LoginInfoProvider(Context context, CleverTapInstanceConfig cleverTapInstanceConfig, DeviceInfo deviceInfo, CryptHandler cryptHandler) {
        this.b = context;
        this.f2647a = cleverTapInstanceConfig;
        this.c = deviceInfo;
        this.d = cryptHandler;
    }
}
