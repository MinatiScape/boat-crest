package com.ido.ble.protocol.model;

import java.util.List;
/* loaded from: classes11.dex */
public class NotificationPara {
    public int app_items_len;
    public String contact;
    public int evt_type;
    public List<AppNames> items;
    public int msg_ID;
    public String msg_data;
    public int notify_type;
    private int os_platform = 1;
    public String phone_number;

    /* loaded from: classes11.dex */
    public static class AppNames {
        public int language;
        public String name;

        public String toString() {
            return "AppNames{language=" + this.language + ", name='" + this.name + "'}";
        }
    }

    public String toString() {
        return "NotificationPara{os_platform=" + this.os_platform + ", evt_type=" + this.evt_type + ", notify_type=" + this.notify_type + ", msg_ID=" + this.msg_ID + ", items=" + this.items + ", contact='" + this.contact + "', phone_number='" + this.phone_number + "'}";
    }
}
