package com.szabh.androiddfu.mtk;

import com.abupdate.iot_libs.OtaAgentPolicy;
import com.abupdate.iot_libs.info.CustomDeviceInfo;
import com.clevertap.android.sdk.Constants;
import java.util.LinkedHashMap;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes12.dex */
public final class MtkOtaHelper {
    @NotNull
    public static final MtkOtaHelper INSTANCE = new MtkOtaHelper();

    public final boolean prepare(@NotNull String otaMeta, @NotNull String path) {
        String str;
        String str2;
        String str3;
        String str4;
        String str5;
        String str6;
        String str7;
        Intrinsics.checkNotNullParameter(otaMeta, "otaMeta");
        Intrinsics.checkNotNullParameter(path, "path");
        try {
            List<String> split$default = StringsKt__StringsKt.split$default((CharSequence) otaMeta, new String[]{";"}, false, 0, 6, (Object) null);
            if (split$default.size() < 8) {
                return false;
            }
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            for (String str8 : split$default) {
                List split$default2 = StringsKt__StringsKt.split$default((CharSequence) str8, new String[]{"="}, false, 0, 6, (Object) null);
                if (split$default2.size() == 2) {
                    linkedHashMap.put(split$default2.get(0), split$default2.get(1));
                }
            }
            String str9 = (String) linkedHashMap.get("mid");
            if (str9 == null || (str = (String) linkedHashMap.get("mod")) == null || (str2 = (String) linkedHashMap.get("oem")) == null || (str3 = (String) linkedHashMap.get(Constants.PING_FREQUENCY)) == null || (str4 = (String) linkedHashMap.get("p_id")) == null || (str5 = (String) linkedHashMap.get("p_sec")) == null || (str6 = (String) linkedHashMap.get("ver")) == null || (str7 = (String) linkedHashMap.get("d_ty")) == null) {
                return false;
            }
            CustomDeviceInfo customDeviceInfo = new CustomDeviceInfo();
            customDeviceInfo.setMid(str9);
            customDeviceInfo.setModels(str);
            customDeviceInfo.setOem(str2);
            customDeviceInfo.setPlatform(str3);
            customDeviceInfo.setProductId(str4);
            customDeviceInfo.setProduct_secret(str5);
            customDeviceInfo.setVersion(str6);
            customDeviceInfo.setDeviceType(str7);
            OtaAgentPolicy.getConfig().setUpdatePath(path).setCustomDeviceInfo(customDeviceInfo).commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
