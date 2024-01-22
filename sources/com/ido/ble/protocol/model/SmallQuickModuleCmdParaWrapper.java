package com.ido.ble.protocol.model;

import com.ido.ble.protocol.model.SmallQuickModule;
import java.util.List;
/* loaded from: classes11.dex */
public class SmallQuickModuleCmdParaWrapper {
    public static final int OPERATE_QUERY = 1;
    public static final int OPERATE_SET = 2;

    /* loaded from: classes11.dex */
    public static class Query {
        public int operate = 1;

        public String toString() {
            return "Query{operate=" + this.operate + '}';
        }
    }

    /* loaded from: classes11.dex */
    public static class Response {
        public static final int CODE_SUCCESS = 0;
        public int err_code;
        public List<SmallQuickModule> items;
        public int operate;
        public List<SmallQuickModule.SupportInfo> support_items;

        public String toString() {
            return "Response{err_code=" + this.err_code + ", operate=" + this.operate + ", items=" + this.items + ", support_items=" + this.support_items + '}';
        }
    }

    /* loaded from: classes11.dex */
    public static class Set {
        public int all_num;
        public List<SmallQuickModule> items;
        public int operate = 2;
        public int version;

        public String toString() {
            return "Set{version=" + this.version + ", operate=" + this.operate + ", all_num=" + this.all_num + ", items=" + this.items + '}';
        }
    }
}
