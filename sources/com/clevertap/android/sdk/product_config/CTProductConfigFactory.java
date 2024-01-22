package com.clevertap.android.sdk.product_config;

import android.content.Context;
import com.clevertap.android.sdk.BaseAnalyticsManager;
import com.clevertap.android.sdk.BaseCallbackManager;
import com.clevertap.android.sdk.CleverTapInstanceConfig;
import com.clevertap.android.sdk.CoreMetaData;
import com.clevertap.android.sdk.DeviceInfo;
import com.clevertap.android.sdk.utils.FileUtils;
@Deprecated
/* loaded from: classes2.dex */
public class CTProductConfigFactory {
    @Deprecated
    public static CTProductConfigController getInstance(Context context, DeviceInfo deviceInfo, CleverTapInstanceConfig cleverTapInstanceConfig, BaseAnalyticsManager baseAnalyticsManager, CoreMetaData coreMetaData, BaseCallbackManager baseCallbackManager) {
        String deviceID = deviceInfo.getDeviceID();
        FileUtils fileUtils = new FileUtils(context, cleverTapInstanceConfig);
        return new CTProductConfigController(context, cleverTapInstanceConfig, baseAnalyticsManager, coreMetaData, baseCallbackManager, new ProductConfigSettings(deviceID, cleverTapInstanceConfig, fileUtils), fileUtils);
    }
}
