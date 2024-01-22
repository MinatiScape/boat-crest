package com.touchgui.sdk;

import java.util.HashMap;
import java.util.Map;
/* loaded from: classes12.dex */
public class TGErrorCode {
    public static final int ERROR_AGPS_POLLING_CAN_WRITE = 40001;
    public static final int ERROR_AGPS_POLLING_WRITE_RESULT = 40002;
    public static final int ERROR_AGPS_UNZIP_FILE = 40003;
    public static final int ERROR_AUTH_FAIL = 10016;
    public static final int ERROR_CHECK_CRC = 10010;
    public static final int ERROR_CHECK_MD5 = 30019;
    public static final int ERROR_COMMAND_CANCELED = 10009;
    public static final int ERROR_COMMAND_TIMEOUT = 10008;
    public static final int ERROR_CONNECTED_INIT_FAILURE = 10013;
    public static final int ERROR_DEVICE_BUSY = 30017;
    public static final int ERROR_DEVICE_NOT_RESPONSE = 10007;
    public static final int ERROR_FILE_NOT_FOUND = 30005;
    public static final int ERROR_FUNC_NOT_SUPPORTED = 10006;
    public static final int ERROR_HEALTH_DATA_COMPLETED = 50003;
    public static final int ERROR_HEALTH_DATA_NOT_SUPPORT = 50004;
    public static final int ERROR_HEALTH_DATA_SPO2 = 50002;
    public static final int ERROR_HEALTH_DATA_START = 50001;
    public static final int ERROR_INVALID_DATA = 30011;
    public static final int ERROR_INVALID_FLAG = 30010;
    public static final int ERROR_INVALID_LENGTH = 30009;
    public static final int ERROR_INVALID_STATE = 30008;
    public static final int ERROR_LOAD_WATCH_DIAL = 60001;
    public static final int ERROR_LOW_POWER = 30018;
    public static final int ERROR_NOT_CONNECTED = 10004;
    public static final int ERROR_NOT_ENOUGH_MEMORY = 30004;
    public static final int ERROR_NOT_READY = 10012;
    public static final int ERROR_OPERATION_NOT_SUPPORTED = 30006;
    public static final int ERROR_OPERATION_RESTRICTIONS = 30016;
    public static final int ERROR_OPERATION_TIMEOUT = 30013;
    public static final int ERROR_OTA = 30000;
    public static final int ERROR_OTA_CHECK_SIZE = 30258;
    public static final int ERROR_OTA_CHECK_SUM = 30257;
    public static final int ERROR_PACKET_LENGTH = 30012;
    public static final int ERROR_PARAMETER = 30007;
    public static final int ERROR_READY_TIMEOUT = 10015;
    public static final int ERROR_REQUEST_MTU_FAILURE = 10001;
    public static final int ERROR_SEND_DATA_EMPTY = 10014;
    public static final int ERROR_SEND_DATA_FAILURE = 10005;
    public static final int ERROR_SERVICES_EMPTY = 10002;
    public static final int ERROR_SUCCESS = 0;
    public static final int ERROR_SYNC_WATCH_DIAL = 60000;
    public static final int ERROR_WORKOUT_DEVICE_BUSY = 20004;
    public static final int ERROR_WORKOUT_NOT_SUPPORT = 20001;
    public static final int ERROR_WORKOUT_QUERY_DATA_SIZE = 20005;
    public static final int ERROR_WORKOUT_QUERY_RECORD_COUNT = 20002;
    public static final int ERROR_WORKOUT_SYNC_COMPLETED = 20006;
    public static final int ERROR_WORKOUT_SYNC_DETAIL = 20003;
    public static final int ERROR_WRITE_DESCRIPTOR_FAILURE = 10003;
    public static final Map<Integer, String> message;

    static {
        HashMap hashMap = new HashMap();
        message = hashMap;
        hashMap.put(Integer.valueOf((int) ERROR_CHECK_CRC), "Long packet data verification failed");
    }

    public static String getMessage(int i) {
        String str = message.get(Integer.valueOf(i));
        return str != null ? str : "unknown";
    }
}
