package com.abupdate.iot_libs.constant;
/* loaded from: classes.dex */
public class OtaConstants {
    public static final String DOUBLE_LINE = "==========================";
    public static final int DOWNLOAD_CALLBACK_CANCEL = 3;
    public static final int DOWNLOAD_CALLBACK_FAILED = 5;
    public static final int DOWNLOAD_CALLBACK_PREPARE = 1;
    public static final int DOWNLOAD_CALLBACK_PROGRESS = 2;
    public static final int DOWNLOAD_CALLBACK_SUCCESS = 4;
    public static final String KEY_CHECK_CYCLE = "check_cycle";
    public static final String KEY_DOWNLOAD_FORCE = "download_forceDownload";
    public static final String KEY_DOWNLOAD_STORAGE_PATH = "download_storagePath";
    public static final String KEY_DOWNLOAD_STORAGE_SIZE = "download_storageSize";
    public static final String KEY_DOWNLOAD_WIFI = "download_wifi";
    public static final String KEY_INSTALL_BATTERY = "install_battery";
    public static final String KEY_INSTALL_FORCE = "install_force";
    public static final String KEY_INSTALL_FREE_TIME = "install_freeInstall";
    public static final String KEY_REBOOT_UPDATE_FORCE = "install_rebootUpgrade";
    public static final String KEY_REMIND_CYCLE = "check_remind";
    public static final int MQTT_LOGIN = 1000;
    public static final int MQTT_LOGOUT = 1001;
    public static final int MQTT_REPORT_DEVICE = 1002;
    public static final String SINGLE_LINE = "--------------------------";
    public static final String SPF_STATIC_CHECK_VERSION_CYCLE = "spf_static_check_version_cycle";
    public static final String SPF_STATIC_MQTT_CHECK = "spf_static_mqtt_check";
    public static final long STATIC_CHECK_VERSION_CYCLE = 259200000;
    public static final long STATIC_OTA_CYCLE_TASK = 86400000;

    /* loaded from: classes.dex */
    public enum IntervalTimePolicy {
        type_install_force(OtaConstants.KEY_INSTALL_FORCE),
        type_install_free_time(OtaConstants.KEY_INSTALL_FREE_TIME);
        
        private String type;

        IntervalTimePolicy(String str) {
            this.type = str;
        }

        public String getType() {
            return this.type;
        }
    }

    /* loaded from: classes.dex */
    public enum PolicyType {
        TYPE_DOWNLOAD_REQUEST_WIFI(OtaConstants.KEY_DOWNLOAD_WIFI),
        TYPE_DOWNLOAD_FORCE(OtaConstants.KEY_DOWNLOAD_FORCE),
        TYPE_DOWNLOAD_STORAGE_SIZE(OtaConstants.KEY_DOWNLOAD_STORAGE_SIZE),
        TYPE_INSTALL_BATTERY(OtaConstants.KEY_INSTALL_BATTERY),
        TYPE_INSTALL_FORCE(OtaConstants.KEY_INSTALL_FORCE),
        TYPE_INSTALL_REBOOT_FORCE(OtaConstants.KEY_REBOOT_UPDATE_FORCE),
        TYPE_INSTALL_FREE_TIME(OtaConstants.KEY_INSTALL_FREE_TIME);
        
        public String type;

        PolicyType(String str) {
            this.type = str;
        }

        public String getType() {
            return this.type;
        }
    }
}
