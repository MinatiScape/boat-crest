package com.abupdate.iot_libs.constant;

import com.realsil.sdk.dfu.DfuConstants;
/* loaded from: classes.dex */
public class Error {
    public static final int CHECK_CURRENT_IS_LAST_VERSION = 2101;
    public static final int CHECK_DEVICE_IS_NOT_REGISTER = 2103;
    public static final int CHECK_VERSION_NOT_CONFIGURE = 2102;
    public static final int DEVICE_INFO_INIT_FAILED = 3001;
    public static final int DOWNLOADING_INVALID_FILE = -1;
    public static final int DOWNLOADING_MEMORY_NOT_ENOUGH = 8010;
    public static final int DOWNLOADING_NET_EXCEPTION = -2;
    public static final int DOWNLOADING_NOT_WIFI = 8009;
    public static final int DOWNLOAD_DELTAID_NOT_EXIST = 2202;
    public static final int DOWNLOAD_STATUS_ILLEGAL = 2201;
    public static final int ERROR = 8003;
    public static final int ERROR_BLOCK_VERIFY_FAIL = -5;
    public static final int ERROR_FETCH_FILE_SIZE = -4;
    public static final int ERROR_FILE_IO_EXCEPTION = -3;
    public static final int JSON_PARSING_EXCEPTION = 1006;
    public static final int OBTAIN_PRODUCT_FAILED = 1009;
    public static final int PARAM_IS_INVALID = 1003;
    public static final int PARAM_IS_LACK = 1004;
    public static final int PARAM_IS_NOT_CONFORM_TO_RULE = 1007;
    public static final int PRODUCTID_IS_INVALID = 1001;
    public static final int PROJECT_NOT_EXIST = 1002;
    public static final int REGISTER_SIGN_ERROR = 2001;
    public static final int RESPONSE_ERROR = 2002;
    public static final int SERVER_DATA_ERROR = 3003;
    public static final int SERVICE_SYSTEM_ERROR = 1005;
    public static final int UPGRADE_BATTERY_NOT_ENOUGH = 7003;
    public static final int UPGRADE_CONDITIONS_IS_NOT_SATISFIED = 7006;
    public static final int UPGRADE_DELTA_NOT_EXIST = 2301;
    public static final int UPGRADE_FILE_NOT_EXIST = 7002;
    public static final int UPGRADE_IOEXCEPTION = 7004;
    public static final int UPGRADE_STATUS_ILLEGAL = 2302;
    public static final int UPGRADE_VALIDATE_FILE_FAIL = 7005;
    public static final int WITHOUT_REGISTER_ERROR = 2003;

    public static String getErrorMessage(int i) {
        if (i != 0) {
            if (i != 1) {
                if (i != -1) {
                    if (i != 2) {
                        if (i != -2) {
                            if (i != 3) {
                                if (i != -3) {
                                    if (i != 4) {
                                        if (i != -4) {
                                            if (i != 5) {
                                                if (i != 2201) {
                                                    if (i != 2202) {
                                                        if (i != 2301) {
                                                            if (i != 2302) {
                                                                if (i != 8009) {
                                                                    if (i != 8010) {
                                                                        switch (i) {
                                                                            case -5:
                                                                                return "download block file verify fail";
                                                                            case 5:
                                                                                return "Not authorized to connect";
                                                                            case 6:
                                                                                return "Unexpected error";
                                                                            case 1009:
                                                                                return "obtain product info failed";
                                                                            case 2001:
                                                                                return "register sign error";
                                                                            case WITHOUT_REGISTER_ERROR /* 2003 */:
                                                                                return "without register!";
                                                                            case 3001:
                                                                                return "device info is not completed!";
                                                                            case 3003:
                                                                                return "network error or server data error";
                                                                            case ERROR /* 8003 */:
                                                                                return "unknown error";
                                                                            default:
                                                                                switch (i) {
                                                                                    case 1001:
                                                                                        return "product id is invalid";
                                                                                    case 1002:
                                                                                        return "project not exist";
                                                                                    case 1003:
                                                                                        return "param is invalid";
                                                                                    case 1004:
                                                                                        return "param is lack";
                                                                                    case 1005:
                                                                                        return "service system error";
                                                                                    case 1006:
                                                                                        return "json parse exception";
                                                                                    case 1007:
                                                                                        return "param is not conform to rule";
                                                                                    default:
                                                                                        switch (i) {
                                                                                            case CHECK_CURRENT_IS_LAST_VERSION /* 2101 */:
                                                                                                return "current version is the last version!";
                                                                                            case CHECK_VERSION_NOT_CONFIGURE /* 2102 */:
                                                                                                return "version not configure";
                                                                                            case CHECK_DEVICE_IS_NOT_REGISTER /* 2103 */:
                                                                                                return "device is not register";
                                                                                            default:
                                                                                                switch (i) {
                                                                                                    case 7002:
                                                                                                        return "update file is not exist!";
                                                                                                    case 7003:
                                                                                                        return "update battery is not enough!";
                                                                                                    case 7004:
                                                                                                        return "file error or Permission denied ";
                                                                                                    case 7005:
                                                                                                        return "update verify file failed";
                                                                                                    default:
                                                                                                        switch (i) {
                                                                                                            case DfuConstants.MAX_CONNECTION_LOCK_TIMEOUT /* 32000 */:
                                                                                                                return "Timed out waiting for a response from the server";
                                                                                                            case 32001:
                                                                                                                return "Internal error, caused by no new message IDs being available";
                                                                                                            case 32002:
                                                                                                                return "Timed out while waiting to write messages to the server";
                                                                                                            default:
                                                                                                                switch (i) {
                                                                                                                    case 32100:
                                                                                                                        return "Client is connected";
                                                                                                                    case 32101:
                                                                                                                        return "Client is disconnected";
                                                                                                                    case 32102:
                                                                                                                        return "Client is currently disconnecting";
                                                                                                                    case 32103:
                                                                                                                        return "Unable to connect to server";
                                                                                                                    case 32104:
                                                                                                                        return "Client is not connected";
                                                                                                                    case 32105:
                                                                                                                        return "The specified SocketFactory type does not match the broker URI";
                                                                                                                    case 32106:
                                                                                                                        return "SSL configuration error";
                                                                                                                    case 32107:
                                                                                                                        return "Disconnecting is not allowed from a callback method";
                                                                                                                    case 32108:
                                                                                                                        return "Unrecognized packet";
                                                                                                                    case 32109:
                                                                                                                        return "Connection lost";
                                                                                                                    case 32110:
                                                                                                                        return "Connect already in progress";
                                                                                                                    case 32111:
                                                                                                                        return "Client is closed";
                                                                                                                    default:
                                                                                                                        switch (i) {
                                                                                                                            case 32200:
                                                                                                                                return "Persistence already in use";
                                                                                                                            case 32201:
                                                                                                                                return "Token already in use";
                                                                                                                            case 32202:
                                                                                                                                return "Too many publishes in progress";
                                                                                                                            default:
                                                                                                                                return "UNKNOWN ERROR";
                                                                                                                        }
                                                                                                                }
                                                                                                        }
                                                                                                }
                                                                                        }
                                                                                }
                                                                        }
                                                                    }
                                                                    return "download memory is not enough!";
                                                                }
                                                                return "download is request wifi!";
                                                            }
                                                            return "report upgrade status illegal";
                                                        }
                                                        return "report update delta not exist";
                                                    }
                                                    return "report download deltaId not exist";
                                                }
                                                return "report download status illegal";
                                            }
                                            return "Not authorized to connect";
                                        }
                                        return "fetch download length fail";
                                    }
                                    return "Bad user name or password";
                                }
                                return "memory is not enough or permission denied";
                            }
                            return "Broker unavailable";
                        }
                        return "download net exception";
                    }
                    return "Invalid client ID";
                }
                return "download finished. but the file is invalid.";
            }
            return "Invalid protocol version";
        }
        return "client exception";
    }
}
