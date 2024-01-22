package com.coveiot.coveaccess.utils;

import android.os.Bundle;
import com.coveiot.coveaccess.CoveApi;
import com.coveiot.coveaccess.SetupException;
import com.coveiot.coveaccess.constants.CoveApiConstants;
import com.coveiot.coveaccess.constants.ErrorConstants;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.utils.utility.LogHelper;
import java.util.HashMap;
import java.util.List;
import java.util.TimeZone;
import retrofit2.Response;
/* loaded from: classes8.dex */
public class CoveUtil {
    public static String a(String str) {
        return str.equals(CoveApiConstants.COVE_API_METADATA_KEY) ? ErrorConstants.SETUP_ERR_MALFORMED_API_KEY : str.equals(CoveApiConstants.COVE_API_METADATA_CLIENT_ID) ? ErrorConstants.SETUP_ERR_MALFORMED_CLIENT_ID : str.equals(CoveApiConstants.COVE_API_METADATA_APP_NAME) ? ErrorConstants.SETUP_ERR_MALFORMED_APP_NAME : ErrorConstants.SETUP_ERR_MISSING_DATA;
    }

    public static String b(String str) {
        return str.equals(CoveApiConstants.COVE_API_METADATA_KEY) ? ErrorConstants.SETUP_ERR_MISSING_API_KEY : str.equals(CoveApiConstants.COVE_API_METADATA_CLIENT_ID) ? ErrorConstants.SETUP_ERR_MISSING_CLIENT_ID : str.equals(CoveApiConstants.COVE_API_METADATA_APP_NAME) ? ErrorConstants.SETUP_ERR_MISSING_APP_NAME : ErrorConstants.SETUP_ERR_MISSING_DATA;
    }

    public static CoveApiErrorModel buildErrorObject(Throwable th) {
        return new CoveApiErrorModel(th.getMessage() != null ? th.getMessage() : ErrorConstants.GENERIC_ERROR);
    }

    public static boolean c(String str) {
        return !CoveApi.immutableHeaders.contains(str);
    }

    public static HashMap<String, String> createHeaderCopy(HashMap<String, String> hashMap) {
        if (hashMap == null || hashMap.isEmpty()) {
            return null;
        }
        HashMap<String, String> hashMap2 = new HashMap<>();
        for (String str : hashMap.keySet()) {
            hashMap2.put(str, hashMap.get(str));
        }
        return hashMap2;
    }

    public static String getCurrentTimezone() {
        return TimeZone.getDefault().getRawOffset() + "";
    }

    public static String getErrorMessage(int i) {
        return i != 401 ? ErrorConstants.API_ERR_GENERIC : ErrorConstants.API_ERR_OTP_INVALID;
    }

    public static String getMetaData(Bundle bundle, String str) {
        if (bundle.containsKey(str)) {
            String string = bundle.getString(str);
            if (string == null || string.length() <= 0) {
                throw new SetupException(a(str));
            }
            return string;
        }
        throw new SetupException(b(str));
    }

    public static HashMap<String, String> getRevisedHeaders(HashMap<String, String> hashMap) {
        HashMap<String, String> customHeaders = CoveApi.getCustomHeaders();
        for (String str : hashMap.keySet()) {
            if (c(str)) {
                customHeaders.put(str, hashMap.get(str));
            } else {
                LogHelper.e(CoveApi.f6257a, String.format("immutable header %s found in data hash map so ignoring that value", str));
            }
        }
        return customHeaders;
    }

    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    public static boolean isSuccessResponse(Response response) {
        return response.isSuccessful() && response.body() != null;
    }

    public static boolean isEmpty(List<?> list) {
        return list == null || list.size() == 0;
    }
}
