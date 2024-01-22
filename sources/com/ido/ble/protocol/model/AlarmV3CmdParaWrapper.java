package com.ido.ble.protocol.model;

import java.util.List;
/* loaded from: classes11.dex */
public class AlarmV3CmdParaWrapper {

    /* loaded from: classes11.dex */
    public static class AlarmGet {
        public int flag;

        public String toString() {
            return "AlarmGet{flag=" + this.flag + '}';
        }
    }

    /* loaded from: classes11.dex */
    public static class AlarmGetResponse {
        public List<AlarmV3> item;
        public int num;
        public int version;

        public String toString() {
            return "AlarmSet{version=" + this.version + ", num=" + this.num + ", item=" + this.item + '}';
        }
    }

    /* loaded from: classes11.dex */
    public static class AlarmSet {
        public List<AlarmV3> item;
        public int num;
        public int version;

        public String toString() {
            return "AlarmSet{version=" + this.version + ", num=" + this.num + ", item=" + this.item + '}';
        }
    }

    /* loaded from: classes11.dex */
    public static class AlarmSetResponse {
        public static final int FAILED = 1;
        public static final int SUCCESS = 0;
        public int status;

        public String toString() {
            return "AlarmSetResponse{status=" + this.status + '}';
        }
    }
}
