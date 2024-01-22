package com.coveiot.android.bleabstract.services;
/* loaded from: classes2.dex */
public enum FirebaseEventParams {
    temp;

    /* loaded from: classes2.dex */
    public enum EventName {
        CV_BT_CONNECT("cv_bt_connect"),
        CV_SCAN_INTERRUPT_SYSTEM("cv_scan_interrupt_system"),
        CV_COMMAND_REQUEST_TYPE("cv_command_request_type"),
        CV_LOCATION_PERMISSIONS("cv_location_permissions"),
        CV_SCAN_FAILED("cv_scan_failed");
        
        public String value;

        EventName(String str) {
            this.value = str;
        }
    }

    /* loaded from: classes2.dex */
    public enum MetaDataKeyNames {
        CV_STATUS("cv_status"),
        CV_APP_PROCESS_STATUS("cv_app_process_status"),
        CV_PHONE_BATTERY_LEVEL("cv_phone_battery_level"),
        CV_TIME_SPENT_MILLS("cv_time_spent_mills"),
        CV_TIME_LATENCY("cv_time_latency"),
        CV_REQUEST_CMD_NAME("cv_request_cmd_name"),
        CV_CODE("cv_code");
        
        public String value;

        MetaDataKeyNames(String str) {
            this.value = str;
        }
    }
}
