package com.clevertap.android.sdk.cryption;

import android.content.Context;
import com.clevertap.android.sdk.CleverTapInstanceConfig;
import com.clevertap.android.sdk.Constants;
import com.clevertap.android.sdk.Logger;
import com.clevertap.android.sdk.StorageHelper;
import com.clevertap.android.sdk.db.DBAdapter;
import com.clevertap.android.sdk.utils.CTJsonConverter;
import java.util.Iterator;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;
/* loaded from: classes2.dex */
public final class CryptUtils {
    @NotNull
    public static final CryptUtils INSTANCE = new CryptUtils();

    @JvmStatic
    public static final void migrateEncryptionLevel(@NotNull Context context, @NotNull CleverTapInstanceConfig config, @NotNull CryptHandler cryptHandler, @NotNull DBAdapter dbAdapter) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(config, "config");
        Intrinsics.checkNotNullParameter(cryptHandler, "cryptHandler");
        Intrinsics.checkNotNullParameter(dbAdapter, "dbAdapter");
        int encryptionLevel = config.getEncryptionLevel();
        int i = StorageHelper.getInt(context, StorageHelper.storageKeyWithSuffix(config, Constants.KEY_ENCRYPTION_LEVEL), -1);
        if (i == -1 && encryptionLevel == 0) {
            return;
        }
        int i2 = i != encryptionLevel ? 0 : StorageHelper.getInt(context, StorageHelper.storageKeyWithSuffix(config, Constants.KEY_ENCRYPTION_FLAG_STATUS), 0);
        StorageHelper.putInt(context, StorageHelper.storageKeyWithSuffix(config, Constants.KEY_ENCRYPTION_LEVEL), encryptionLevel);
        if (i2 == 3) {
            config.getLogger().verbose(config.getAccountId(), "Encryption flag status is 100% success, no need to migrate");
            cryptHandler.setEncryptionFlagStatus(3);
            return;
        }
        Logger logger = config.getLogger();
        String accountId = config.getAccountId();
        logger.verbose(accountId, "Migrating encryption level from " + i + " to " + encryptionLevel + " with current flag status " + i2);
        INSTANCE.c(encryptionLevel == 1, context, config, cryptHandler, i2, dbAdapter);
    }

    @JvmStatic
    public static final void updateEncryptionFlagOnFailure(@NotNull Context context, @NotNull CleverTapInstanceConfig config, int i, @NotNull CryptHandler cryptHandler) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(config, "config");
        Intrinsics.checkNotNullParameter(cryptHandler, "cryptHandler");
        int encryptionFlagStatus = (cryptHandler.getEncryptionFlagStatus() ^ i) & cryptHandler.getEncryptionFlagStatus();
        Logger logger = config.getLogger();
        String accountId = config.getAccountId();
        logger.verbose(accountId, "Updating encryption flag status after error in " + i + " to " + encryptionFlagStatus);
        StorageHelper.putInt(context, StorageHelper.storageKeyWithSuffix(config, Constants.KEY_ENCRYPTION_FLAG_STATUS), encryptionFlagStatus);
        cryptHandler.setEncryptionFlagStatus(encryptionFlagStatus);
    }

    public final int a(boolean z, CleverTapInstanceConfig cleverTapInstanceConfig, Context context, CryptHandler cryptHandler) {
        String decrypt;
        cleverTapInstanceConfig.getLogger().verbose(cleverTapInstanceConfig.getAccountId(), "Migrating encryption level for cachedGUIDsKey prefs");
        String str = null;
        JSONObject jsonObject = CTJsonConverter.toJsonObject(StorageHelper.getStringFromPrefs(context, cleverTapInstanceConfig, Constants.CACHED_GUIDS_KEY, null), cleverTapInstanceConfig.getLogger(), cleverTapInstanceConfig.getAccountId());
        JSONObject jSONObject = new JSONObject();
        try {
            Iterator<String> keys = jsonObject.keys();
            int i = 1;
            while (keys.hasNext()) {
                String nextJSONObjKey = keys.next();
                Intrinsics.checkNotNullExpressionValue(nextJSONObjKey, "nextJSONObjKey");
                String substringBefore$default = StringsKt__StringsKt.substringBefore$default(nextJSONObjKey, "_", str, 2, str);
                String substringAfter$default = StringsKt__StringsKt.substringAfter$default(nextJSONObjKey, "_", str, 2, str);
                if (z) {
                    decrypt = cryptHandler.encrypt(substringAfter$default, substringBefore$default);
                } else {
                    decrypt = cryptHandler.decrypt(substringAfter$default, Constants.KEY_ENCRYPTION_MIGRATION);
                }
                if (decrypt == null) {
                    Logger logger = cleverTapInstanceConfig.getLogger();
                    String accountId = cleverTapInstanceConfig.getAccountId();
                    logger.verbose(accountId, "Error migrating " + substringAfter$default + " in Cached Guid Key Pref");
                    i = 0;
                } else {
                    substringAfter$default = decrypt;
                }
                jSONObject.put(substringBefore$default + '_' + substringAfter$default, jsonObject.get(nextJSONObjKey));
                str = null;
            }
            if (jsonObject.length() > 0) {
                String jSONObject2 = jSONObject.toString();
                Intrinsics.checkNotNullExpressionValue(jSONObject2, "newGuidJsonObj.toString()");
                StorageHelper.putString(context, StorageHelper.storageKeyWithSuffix(cleverTapInstanceConfig, Constants.CACHED_GUIDS_KEY), jSONObject2);
                Logger logger2 = cleverTapInstanceConfig.getLogger();
                String accountId2 = cleverTapInstanceConfig.getAccountId();
                logger2.verbose(accountId2, "setCachedGUIDs after migration:[" + jSONObject2 + ']');
            }
            return i;
        } catch (Throwable th) {
            Logger logger3 = cleverTapInstanceConfig.getLogger();
            String accountId3 = cleverTapInstanceConfig.getAccountId();
            logger3.verbose(accountId3, "Error migrating cached guids: " + th);
            return 0;
        }
    }

    public final int b(boolean z, CleverTapInstanceConfig cleverTapInstanceConfig, CryptHandler cryptHandler, DBAdapter dBAdapter) {
        String decrypt;
        cleverTapInstanceConfig.getLogger().verbose(cleverTapInstanceConfig.getAccountId(), "Migrating encryption level for user profile in DB");
        JSONObject fetchUserProfileById = dBAdapter.fetchUserProfileById(cleverTapInstanceConfig.getAccountId());
        int i = 2;
        if (fetchUserProfileById == null) {
            return 2;
        }
        try {
            Iterator<String> it = Constants.piiDBKeys.iterator();
            while (it.hasNext()) {
                String piiKey = it.next();
                if (fetchUserProfileById.has(piiKey)) {
                    Object obj = fetchUserProfileById.get(piiKey);
                    if (obj instanceof String) {
                        if (z) {
                            Intrinsics.checkNotNullExpressionValue(piiKey, "piiKey");
                            decrypt = cryptHandler.encrypt((String) obj, piiKey);
                        } else {
                            decrypt = cryptHandler.decrypt((String) obj, Constants.KEY_ENCRYPTION_MIGRATION);
                        }
                        if (decrypt == null) {
                            Logger logger = cleverTapInstanceConfig.getLogger();
                            String accountId = cleverTapInstanceConfig.getAccountId();
                            logger.verbose(accountId, "Error migrating " + piiKey + " entry in db profile");
                            decrypt = (String) obj;
                            i = 0;
                        }
                        fetchUserProfileById.put(piiKey, decrypt);
                    }
                }
            }
            if (dBAdapter.storeUserProfile(cleverTapInstanceConfig.getAccountId(), fetchUserProfileById) == -1) {
                return 0;
            }
            return i;
        } catch (Exception e) {
            Logger logger2 = cleverTapInstanceConfig.getLogger();
            String accountId2 = cleverTapInstanceConfig.getAccountId();
            logger2.verbose(accountId2, "Error migrating local DB profile: " + e);
            return 0;
        }
    }

    public final void c(boolean z, Context context, CleverTapInstanceConfig cleverTapInstanceConfig, CryptHandler cryptHandler, int i, DBAdapter dBAdapter) {
        int i2 = i & 1;
        if (i2 == 0) {
            i2 = a(z, cleverTapInstanceConfig, context, cryptHandler);
        }
        int i3 = i & 2;
        if (i3 == 0) {
            i3 = b(z, cleverTapInstanceConfig, cryptHandler, dBAdapter);
        }
        int i4 = i2 | i3;
        Logger logger = cleverTapInstanceConfig.getLogger();
        String accountId = cleverTapInstanceConfig.getAccountId();
        logger.verbose(accountId, "Updating encryption flag status to " + i4);
        StorageHelper.putInt(context, StorageHelper.storageKeyWithSuffix(cleverTapInstanceConfig, Constants.KEY_ENCRYPTION_FLAG_STATUS), i4);
        cryptHandler.setEncryptionFlagStatus(i4);
    }
}
