package com.ido.ble.protocol.model;

import java.util.List;
/* loaded from: classes11.dex */
public class ScheduleReminderV3CmdParaWrapper {
    public static final int OPERATE_ADD = 1;
    public static final int OPERATE_DELETE = 2;
    public static final int OPERATE_MODIFY = 4;
    public static final int OPERATE_QUERY = 3;

    /* loaded from: classes11.dex */
    public static class Add {
        public List<ScheduleReminderV3> items;
        public int num;
        public int version = 0;
        public int operate = 1;

        public String toString() {
            return "Add{version=" + this.version + ", operate=" + this.operate + ", num=" + this.num + ", items=" + this.items + '}';
        }
    }

    /* loaded from: classes11.dex */
    public static class Delete {
        public List<ScheduleReminderV3> items;
        public int num;
        public int version = 0;
        public int operate = 2;

        public String toString() {
            return "Delete{version=" + this.version + ", operate=" + this.operate + ", num=" + this.num + ", items=" + this.items + '}';
        }
    }

    /* loaded from: classes11.dex */
    public static class Modify {
        public List<ScheduleReminderV3> items;
        public int num;
        public int version = 0;
        public int operate = 4;

        public String toString() {
            return "Modify{version=" + this.version + ", operate=" + this.operate + ", num=" + this.num + ", items=" + this.items + '}';
        }
    }

    /* loaded from: classes11.dex */
    public static class Query {
        public int operate = 3;

        public String toString() {
            return "Query{operate=" + this.operate + '}';
        }
    }

    /* loaded from: classes11.dex */
    public static class Response {
        public static final int CODE_SUCCESS = 0;
        public int err_code;
        public List<ScheduleReminderV3> items;
        public int operate;

        public String toString() {
            return "Response{err_code=" + this.err_code + ", operate=" + this.operate + ", items=" + this.items + '}';
        }
    }
}
