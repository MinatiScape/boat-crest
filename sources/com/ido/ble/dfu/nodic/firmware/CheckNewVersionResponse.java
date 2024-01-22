package com.ido.ble.dfu.nodic.firmware;
/* loaded from: classes11.dex */
public class CheckNewVersionResponse {
    public NewVersionInfo data;
    public String message;
    public int resultCode;

    /* loaded from: classes11.dex */
    public static class NewVersionInfo {
        public String descriptionChinese;
        public String descriptionEnglish;
        public boolean forceUpdate;
        public String url;
        public int version;

        public String toString() {
            return "NewVersionInfo{url='" + this.url + "', forceUpdate=" + this.forceUpdate + ", descriptionChinese='" + this.descriptionChinese + "', descriptionEnglish='" + this.descriptionEnglish + "', version=" + this.version + '}';
        }
    }
}
