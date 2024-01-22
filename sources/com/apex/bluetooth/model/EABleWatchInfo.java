package com.apex.bluetooth.model;
/* loaded from: classes.dex */
public class EABleWatchInfo {
    public long agps_update_timestamp;
    public BindingInfo bindingInfo;
    public String ble_mac_addr;
    public String firmwareVersion;
    public int is_wait_for_binding;
    public int lcd_full_h;
    public int lcd_full_type;
    public int lcd_full_w;
    public int lcd_pixel_type;
    public int lcd_preview_h;
    public int lcd_preview_radius;
    public int lcd_preview_w;
    public int max_watch_size;
    public int not_support_sn;
    public int proj_settings;
    public String userId;
    public String watchId;
    public String watchType;

    /* loaded from: classes.dex */
    public enum BindingInfo {
        bound(0),
        unbound(1);
        
        public int value;

        BindingInfo(int i) {
            this.value = i;
        }

        public int getValue() {
            return this.value;
        }
    }

    public long getAgps_update_timestamp() {
        return this.agps_update_timestamp;
    }

    public BindingInfo getBindingInfo() {
        return this.bindingInfo;
    }

    public String getBle_mac_addr() {
        return this.ble_mac_addr;
    }

    public String getFirmwareVersion() {
        return this.firmwareVersion;
    }

    public int getIs_wait_for_binding() {
        return this.is_wait_for_binding;
    }

    public int getLcd_full_h() {
        return this.lcd_full_h;
    }

    public int getLcd_full_type() {
        return this.lcd_full_type;
    }

    public int getLcd_full_w() {
        return this.lcd_full_w;
    }

    public int getLcd_pixel_type() {
        return this.lcd_pixel_type;
    }

    public int getLcd_preview_h() {
        return this.lcd_preview_h;
    }

    public int getLcd_preview_radius() {
        return this.lcd_preview_radius;
    }

    public int getLcd_preview_w() {
        return this.lcd_preview_w;
    }

    public int getMax_watch_size() {
        return this.max_watch_size;
    }

    public int getNot_support_sn() {
        return this.not_support_sn;
    }

    public int getProj_settings() {
        return this.proj_settings;
    }

    public String getUserId() {
        return this.userId;
    }

    public String getWatchId() {
        return this.watchId;
    }

    public String getWatchType() {
        return this.watchType;
    }

    public void setAgps_update_timestamp(long j) {
        this.agps_update_timestamp = j;
    }

    public void setBindingInfo(BindingInfo bindingInfo) {
        this.bindingInfo = bindingInfo;
    }

    public void setBle_mac_addr(String str) {
        this.ble_mac_addr = str;
    }

    public void setFirmwareVersion(String str) {
        this.firmwareVersion = str;
    }

    public void setIs_wait_for_binding(int i) {
        this.is_wait_for_binding = i;
    }

    public void setLcd_full_h(int i) {
        this.lcd_full_h = i;
    }

    public void setLcd_full_type(int i) {
        this.lcd_full_type = i;
    }

    public void setLcd_full_w(int i) {
        this.lcd_full_w = i;
    }

    public void setLcd_pixel_type(int i) {
        this.lcd_pixel_type = i;
    }

    public void setLcd_preview_h(int i) {
        this.lcd_preview_h = i;
    }

    public void setLcd_preview_radius(int i) {
        this.lcd_preview_radius = i;
    }

    public void setLcd_preview_w(int i) {
        this.lcd_preview_w = i;
    }

    public void setMax_watch_size(int i) {
        this.max_watch_size = i;
    }

    public void setNot_support_sn(int i) {
        this.not_support_sn = i;
    }

    public void setProj_settings(int i) {
        this.proj_settings = i;
    }

    public void setUserId(String str) {
        this.userId = str;
    }

    public void setWatchId(String str) {
        this.watchId = str;
    }

    public void setWatchType(String str) {
        this.watchType = str;
    }

    public String toString() {
        return "EABleWatchInfo{watchId='" + this.watchId + "', watchType='" + this.watchType + "', firmwareVersion='" + this.firmwareVersion + "', bindingInfo=" + this.bindingInfo + ", userId='" + this.userId + "', agps_update_timestamp=" + this.agps_update_timestamp + ", ble_mac_addr='" + this.ble_mac_addr + "', is_wait_for_binding=" + this.is_wait_for_binding + ", proj_settings=" + this.proj_settings + ", lcd_full_w=" + this.lcd_full_w + ", lcd_full_h=" + this.lcd_full_h + ", lcd_full_type=" + this.lcd_full_type + ", lcd_preview_w=" + this.lcd_preview_w + ", lcd_preview_h=" + this.lcd_preview_h + ", lcd_preview_radius=" + this.lcd_preview_radius + ", not_support_sn=" + this.not_support_sn + ", max_watch_size=" + this.max_watch_size + ", lcd_pixel_type=" + this.lcd_pixel_type + '}';
    }
}
