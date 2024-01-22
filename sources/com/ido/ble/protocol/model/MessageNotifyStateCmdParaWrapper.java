package com.ido.ble.protocol.model;

import java.util.List;
/* loaded from: classes11.dex */
public class MessageNotifyStateCmdParaWrapper {
    public static final int OPERATE_ADD = 1;
    public static final int OPERATE_MODIFY = 2;
    public static final int OPERATE_QUERY = 3;

    /* loaded from: classes11.dex */
    public static class Add {
        public int all_on_off;
        public List<MessageNotifyState> items;
        public int items_num;
        public int version = 0;
        public int all_send_num = 0;
        public int now_send_index = 0;
        public int operat = 1;

        public String toString() {
            return "Add{version=" + this.version + ", all_send_num=" + this.all_send_num + ", now_send_index=" + this.now_send_index + ", operat=" + this.operat + ", all_on_off=" + this.all_on_off + ", items_num=" + this.items_num + ", items=" + this.items + '}';
        }
    }

    /* loaded from: classes11.dex */
    public static class Modify {
        public List<MessageNotifyState> items;
        public int items_num;
        public int version = 0;
        public int all_send_num = 0;
        public int now_send_index = 0;
        public int operat = 2;

        public String toString() {
            return "Modify{version=" + this.version + ", all_send_num=" + this.all_send_num + ", now_send_index=" + this.now_send_index + ", operat=" + this.operat + ", items_num=" + this.items_num + ", items=" + this.items + '}';
        }
    }

    /* loaded from: classes11.dex */
    public static class Query {
        public int operat = 3;

        public String toString() {
            return "Query{operat=" + this.operat + '}';
        }
    }

    /* loaded from: classes11.dex */
    public static class Response {
        public static final int CODE_SUCCESS = 0;
        public int all_on_off;
        public int err_code;
        public List<MessageNotifyState> items;
        public int items_num;
        public int operat;

        public String toString() {
            return "Response{err_code=" + this.err_code + ", operat=" + this.operat + ", items_num=" + this.items_num + ", all_on_off=" + this.all_on_off + ", items=" + this.items + '}';
        }
    }
}
