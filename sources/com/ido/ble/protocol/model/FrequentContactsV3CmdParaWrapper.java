package com.ido.ble.protocol.model;

import java.util.List;
/* loaded from: classes11.dex */
public class FrequentContactsV3CmdParaWrapper {
    public static final int OPERATE_ADD = 100;
    public static final int OPERATE_DELETE = 200;
    public static final int OPERATE_MODIFY = 400;
    public static final int OPERATE_QUERY = 2;
    public static final int OPERATE_SET = 1;

    /* loaded from: classes11.dex */
    public static class Add {
        public List<FrequentContactsV3> items;
        public int items_num;
        public int version = 0;
        public int operate = 100;

        public String toString() {
            return "Add{version=" + this.version + ", operate=" + this.operate + ", items_num=" + this.items_num + ", items=" + this.items + '}';
        }
    }

    /* loaded from: classes11.dex */
    public static class Delete {
        public List<FrequentContactsV3> items;
        public int items_num;
        public int version = 0;
        public int operate = 200;

        public String toString() {
            return "Delete{version=" + this.version + ", operate=" + this.operate + ", items_num=" + this.items_num + ", items=" + this.items + '}';
        }
    }

    /* loaded from: classes11.dex */
    public static class Modify {
        public List<FrequentContactsV3> items;
        public int items_num;
        public int version = 0;
        public int operate = 400;

        public String toString() {
            return "Modify{version=" + this.version + ", operate=" + this.operate + ", items_num=" + this.items_num + ", items=" + this.items + '}';
        }
    }

    /* loaded from: classes11.dex */
    public static class Query {
        public int operat = 2;

        public String toString() {
            return "Query{operat=" + this.operat + '}';
        }
    }

    /* loaded from: classes11.dex */
    public static class Response {
        public static final int CODE_SUCCESS = 0;
        public int err_code;
        public List<FrequentContactsV3> items;
        public int operat;

        public String toString() {
            return "Response{err_code=" + this.err_code + ", operat=" + this.operat + ", items=" + this.items + '}';
        }
    }

    /* loaded from: classes11.dex */
    public static class Set {
        public List<FrequentContactsV3> items;
        public int items_num;
        public int version = 0;
        public int operat = 1;

        public String toString() {
            return "Set{version=" + this.version + ", operat=" + this.operat + ", items_num=" + this.items_num + ", items=" + this.items + '}';
        }
    }
}
