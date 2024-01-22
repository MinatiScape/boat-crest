package com.ido.ble.watch.custom.model;
/* loaded from: classes11.dex */
public class PhotoWallpaperOperation {

    /* loaded from: classes11.dex */
    public static class ResponseInfo extends SetParams {
        public int err_code;

        @Override // com.ido.ble.watch.custom.model.PhotoWallpaperOperation.SetParams
        public String toString() {
            return "ResponseInfo{err_code=" + this.err_code + '}';
        }
    }

    /* loaded from: classes11.dex */
    public static class SetParams {
        public static final int OPERATE_DELETE = 2;
        public static final int OPERATE_QUERY = 0;
        public static final int OPERATE_SET = 1;
        public int hide_type;
        public int location;
        public int operate;
        public int time_color;
        public int widget_icon_color;
        public int widget_num_color;
        public int widget_type;

        public String toString() {
            return "SettingParams{operate=" + this.operate + ", location=" + this.location + ", hide_type=" + this.hide_type + ", time_color=" + this.time_color + ", widget_type=" + this.widget_type + ", widget_icon_color=" + this.widget_icon_color + ", widget_num_color=" + this.widget_num_color + '}';
        }
    }
}
