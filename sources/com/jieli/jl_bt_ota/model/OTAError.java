package com.jieli.jl_bt_ota.model;

import com.jieli.jl_bt_ota.constant.ErrorCode;
import com.jieli.jl_bt_ota.model.base.BaseError;
/* loaded from: classes11.dex */
public class OTAError {
    public static BaseError buildError(int i) {
        return new BaseError(i, getErrorDesc(i, -1, null));
    }

    private static String getErrorDesc(int i, int i2, String str) {
        String str2;
        if (i == 12295) {
            str2 = "Command waiting for the device to reply timed out.";
        } else if (i == 12296) {
            str2 = "Device returned the failed result.";
        } else if (i == 12298) {
            str2 = "Device returned an error result.";
        } else if (i == 12299) {
            str2 = "Waiting command timeout.";
        } else if (i == 0) {
            str2 = "Success.";
        } else if (i == 4097) {
            str2 = "Parameter error.";
        } else if (i == 4099) {
            str2 = "Bluetooth is close.";
        } else if (i == 4114) {
            str2 = "Bluetooth device is disconnected.";
        } else if (i == 8194) {
            str2 = "Searching for a Device Exception.";
        } else if (i != 20481) {
            switch (i) {
                case 12290:
                    str2 = "Failed to send data.";
                    break;
                case 12291:
                    str2 = "System is busy.";
                    break;
                case 12292:
                    str2 = "Abnormal data format.";
                    break;
                case 12293:
                    str2 = "Failed to parse data.";
                    break;
                default:
                    switch (i) {
                        case ErrorCode.SUB_ERR_OTA_FAILED /* 16385 */:
                            str2 = "Update failed.";
                            break;
                        case 16386:
                            str2 = "Device is in low charge.";
                            break;
                        case ErrorCode.SUB_ERR_CHECK_UPGRADE_FILE /* 16387 */:
                            str2 = "Corrupted upgrade file.";
                            break;
                        case ErrorCode.SUB_ERR_OFFSET_OVER /* 16388 */:
                            str2 = "Data offset error.";
                            break;
                        case ErrorCode.SUB_ERR_CHECK_RECEIVED_DATA_FAILED /* 16389 */:
                            str2 = "Data verification failure.";
                            break;
                        case ErrorCode.SUB_ERR_UPGRADE_KEY_NOT_MATCH /* 16390 */:
                            str2 = "Key of the upgrade file does not match.";
                            break;
                        case ErrorCode.SUB_ERR_UPGRADE_TYPE_NOT_MATCH /* 16391 */:
                            str2 = "The type of the upgrade file is incorrect.";
                            break;
                        case ErrorCode.SUB_ERR_OTA_IN_HANDLE /* 16392 */:
                            str2 = "OTA is in progress.";
                            break;
                        case ErrorCode.SUB_ERR_UPGRADE_DATA_LEN /* 16393 */:
                            str2 = "Data length error.";
                            break;
                        case ErrorCode.SUB_ERR_UPGRADE_FLASH_READ /* 16394 */:
                            str2 = "Flash IO exception.";
                            break;
                        case ErrorCode.SUB_ERR_UPGRADE_CMD_TIMEOUT /* 16395 */:
                            str2 = "Command processing timeout.";
                            break;
                        case ErrorCode.SUB_ERR_UPGRADE_FILE_VERSION_SAME /* 16396 */:
                            str2 = "File version is the same as the device version.";
                            break;
                        case ErrorCode.SUB_ERR_TWS_NOT_CONNECT /* 16397 */:
                            str2 = "The TWS is not connected.";
                            break;
                        case ErrorCode.SUB_ERR_HEADSET_NOT_IN_CHARGING_BIN /* 16398 */:
                            str2 = "The headphones are not in the charging bin.";
                            break;
                        case ErrorCode.SUB_ERR_UPGRADE_SAME_FILE /* 16399 */:
                            str2 = "Same upgrade file.";
                            break;
                        case ErrorCode.SUB_ERR_UPGRADE_UNKNOWN /* 16400 */:
                            str2 = "Unknown error code.";
                            break;
                        case ErrorCode.SUB_ERR_RECONNECT_TIMEOUT /* 16401 */:
                            str2 = "Reconnecting to the device timed out.";
                            break;
                        case ErrorCode.SUB_ERR_RECONNECT_FAILED /* 16402 */:
                            str2 = "Failed to reconnect the device.";
                            break;
                        default:
                            switch (i) {
                                case ErrorCode.SUB_ERR_FILE_NOT_FOUND /* 20484 */:
                                    str2 = "Not found file.";
                                    break;
                                case ErrorCode.SUB_ERR_DATA_NOT_FOUND /* 20485 */:
                                    str2 = "No upgrade data found.";
                                    break;
                                case ErrorCode.SUB_ERR_IO_EXCEPTION /* 20486 */:
                                    str2 = "I/O exception.";
                                    break;
                                default:
                                    str2 = "Unknown Error";
                                    break;
                            }
                    }
            }
        } else {
            str2 = "Device authentication fails.";
        }
        return new ErrorMsg(i, str2, i2, str).toString();
    }

    public static BaseError buildError(int i, String str) {
        return new BaseError(i, getErrorDesc(i, -1, str));
    }

    public static BaseError buildError(int i, int i2, String str) {
        return new BaseError(i, getErrorDesc(i, i2, str));
    }
}
