package com.ido.ble.protocol.model;
/* loaded from: classes11.dex */
public class IconPara {

    /* loaded from: classes11.dex */
    public static class Get {
        public int evt_type;
        public int sport_type;
        public int type;

        public String toString() {
            return "Get{type=" + this.type + ", evt_type=" + this.evt_type + ", sport_type=" + this.sport_type + '}';
        }
    }

    /* loaded from: classes11.dex */
    public static class Response {
        public int block_size;
        public int err_code;
        public int evt_type;
        public int format;
        public int icon_height;
        public int icon_width;
        public int medium_num;
        public int min_samll_num;
        public int sport_type;
        public int type;

        public String toString() {
            return "Response{block_size=" + this.block_size + ", err_code=" + this.err_code + ", evt_type=" + this.evt_type + ", format=" + this.format + ", icon_height=" + this.icon_height + ", icon_width=" + this.icon_width + ", sport_type=" + this.sport_type + ", type=" + this.type + ", min_samll_num=" + this.min_samll_num + ", medium_num=" + this.medium_num + '}';
        }
    }
}
