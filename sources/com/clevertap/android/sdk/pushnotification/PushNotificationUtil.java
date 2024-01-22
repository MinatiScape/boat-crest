package com.clevertap.android.sdk.pushnotification;

import android.os.Bundle;
import androidx.annotation.RestrictTo;
import com.clevertap.android.sdk.Constants;
import com.clevertap.android.sdk.pushnotification.PushConstants;
import java.util.ArrayList;
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: classes2.dex */
public class PushNotificationUtil {
    public static String buildPushNotificationRenderedListenerKey(String str, String str2) {
        return str + "_" + str2;
    }

    public static String getAccountIdFromNotificationBundle(Bundle bundle) {
        return bundle != null ? bundle.getString(Constants.WZRK_ACCT_ID_KEY, "") : "";
    }

    public static ArrayList<String> getAll() {
        ArrayList<String> arrayList = new ArrayList<>();
        for (PushConstants.PushType pushType : PushConstants.PushType.values()) {
            arrayList.add(pushType.name());
        }
        return arrayList;
    }

    public static String getPushIdFromNotificationBundle(Bundle bundle) {
        return bundle != null ? bundle.getString(Constants.WZRK_PUSH_ID, "") : "";
    }

    public static PushConstants.PushType[] getPushTypes(ArrayList<String> arrayList) {
        PushConstants.PushType[] pushTypeArr = new PushConstants.PushType[0];
        if (arrayList != null && !arrayList.isEmpty()) {
            pushTypeArr = new PushConstants.PushType[arrayList.size()];
            for (int i = 0; i < arrayList.size(); i++) {
                pushTypeArr[i] = PushConstants.PushType.valueOf(arrayList.get(i));
            }
        }
        return pushTypeArr;
    }
}
