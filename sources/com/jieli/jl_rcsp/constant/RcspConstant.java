package com.jieli.jl_rcsp.constant;

import com.mappls.sdk.navigation.NavigationConstants;
/* loaded from: classes11.dex */
public class RcspConstant {
    public static final int ADV_OP_CLOSE_NOTIFY = 0;
    public static final int ADV_OP_OPEN_NOTIFY = 1;
    public static final int ADV_REQUEST_OP_RECONNECT_DEVICE = 3;
    public static final int ADV_REQUEST_OP_SYNC_DEVICE_INFO = 4;
    public static final int ADV_REQUEST_OP_SYNC_TIME = 2;
    public static final int ADV_REQUEST_OP_UPDATE_AFTER_REBOOT = 1;
    public static final int ADV_REQUEST_OP_UPDATE_CONFIGURE = 0;
    public static final int BLE_MTU_MAX = 509;
    public static final int BLE_MTU_MIN = 20;
    public static final int DATA_TRANSFER_VERSION = 0;
    public static final String DEBUG_LOG_TAG = "jl_rcsp";
    public static int[] DEFAULT_EQ_FREQS = {31, 63, 125, 250, 500, 1000, 2000, NavigationConstants.UI_HANDLER_MAP_CONTROLS, 8000, 16000};
    public static final int DEFAULT_PROTOCOL_MTU = 530;
    public static final int DEFAULT_SEND_CMD_TIMEOUT = 2000;
    public static final int DEVICE_CLOSE = 1;
    public static final int DEVICE_REBOOT = 0;
    public static final byte END_FLAG = -17;
    public static final int EXPAND_MODE_NONE = 0;
    public static final int EXPAND_MODE_RES_OTA = 1;
    public static final int FILE_TRANSFER_ERR_CRC = 3;
    public static final int FILE_TRANSFER_ERR_NONE = 0;
    public static final int FILE_TRANSFER_ERR_OVER_LIMIT = 2;
    public static final int FILE_TRANSFER_ERR_SPACE_NOT_ENOUGH = 4;
    public static final int FILE_TRANSFER_ERR_WRITE = 1;
    public static final int FILE_TRANSFER_OP_PAUSE = 2;
    public static final int FILE_TRANSFER_OP_START = 1;
    public static final int FILE_TRANSFER_OP_STOP = 0;
    public static final int FLAG_LOADER = 1;
    public static final int FLAG_MANDATORY_UPGRADE = 1;
    public static final int FLAG_NORMAL_UPGRADE = 0;
    public static final int FLAG_SDK = 0;
    public static final int HEALTH_DATA_VERSION_0 = 0;
    public static final int HEALTH_TYPE_GPS = 0;
    public static final int HEALTH_TYPE_NOTIFY_MSG = 2;
    public static final int HEALTH_TYPE_REMOVE_MSG = 3;
    public static final int HEALTH_TYPE_WEATHER = 1;
    public static final String JL_RCSP_LIB = "jl_auth";
    public static final int MIN_TIMEOUT = 500;
    public static final int MSG_INFO_TYPE_APP_FLAG = 2;
    public static final int MSG_INFO_TYPE_CONTENT = 4;
    public static final int MSG_INFO_TYPE_PACKAGE_NAME = 1;
    public static final int MSG_INFO_TYPE_TIME = 0;
    public static final int MSG_INFO_TYPE_TITLE = 3;
    public static final int NFC_ATTR_NICKNAME = 1;
    public static final int NFC_ATTR_TIME = 0;
    public static final int NFC_FLAG_GET_DEFAULT_MSG = 0;
    public static final int NFC_FLAG_NOTIFY_DEFAULT_MSG = 2;
    public static final int NFC_FLAG_SET_DEFAULT_MSG = 1;
    public static final int NFC_OP_DEFAULT_MSG = 5;
    public static final int NFC_OP_DELETE_MSG = 3;
    public static final int NFC_OP_INSERT_FILE_END = 7;
    public static final int NFC_OP_INSERT_FILE_START = 6;
    public static final int NFC_OP_MODIFY_MSG = 2;
    public static final int NFC_OP_NOTIFY_MSG = 4;
    public static final int NFC_OP_SYNC_START = 0;
    public static final int NFC_OP_SYNC_STOP = 1;
    public static final int OP_TRANSFER_DATA = 1;
    public static final int OP_TRANSFER_PARAMS = 0;
    public static final byte PLATFORM_ANDROID = 0;
    public static final byte PLATFORM_IOS = 1;
    public static final byte PLATFORM_WEB_APP = 2;
    public static final byte PREFIX_FLAG_FIRST = -2;
    public static final byte PREFIX_FLAG_SECOND = -36;
    public static final byte PREFIX_FLAG_THIRD = -70;
    public static final int PROTOCOL_TYPE_BLE = 0;
    public static final int PROTOCOL_TYPE_SPP = 1;
    public static final int REASON_DEVICE_BUSY = 3;
    public static final int REASON_FILE_DATA_SEND_ERR = 4;
    public static final int REASON_PLAY_FILE_SUCCESS = 2;
    public static final int REASON_REQUEST_HANDLE_SUCCESS = 0;
    public static final int REASON_REQUEST_HANDLE_SUCCESS_AND_END = 1;
    public static final int RESULT_CRC_ERROR = 1;
    public static final int RESULT_DATA_LEN_ERROR = 5;
    public static final int RESULT_IO_EXCEPTION = 6;
    public static final int RESULT_MISS_CONFIGURE = 7;
    public static final int RESULT_SEQ_ERROR = 2;
    public static final int RESULT_SUCCESS = 0;
    public static final int RESULT_TYPE_ERROR = 3;
    public static final int RESULT_UNKNOWN = 255;
    public static final int RESULT_WAY_ERROR = 4;
    public static final int RING_OP_CLOSE = 0;
    public static final int RING_OP_OPEN = 1;
    public static final int RING_PLAYER_APP = 0;
    public static final int RING_PLAYER_DEVICE = 1;
    public static final int RING_WAY_ALL = 0;
    public static final int RING_WAY_LEFT = 1;
    public static final int RING_WAY_RIGHT = 2;
    public static final int SEARCH_TYPE_DEVICE = 1;
    public static final int SEARCH_TYPE_PHONE = 0;
    public static final int SINGLE_BACKUP_OTA_WAY_BLE = 1;
    public static final int SINGLE_BACKUP_OTA_WAY_NONE = 0;
    public static final int SINGLE_BACKUP_OTA_WAY_SPP = 2;
    public static final int STATUS_CLASSIC_BLUETOOTH_CONNECTED = 1;
    public static final int STATUS_CLASSIC_BLUETOOTH_DISCONNECTED = 0;
    public static final int TYPE_ALI_DATA = 1;
    public static final int TYPE_CHECK_FILE = 0;
    public static final int TYPE_FIRMWARE_UPGRADE = 1;
    public static final int TYPE_RAW_DATA = 0;
    public static final int WAY_READ_DATA = 0;
    public static final int WAY_SEND_DATA = 1;
}
