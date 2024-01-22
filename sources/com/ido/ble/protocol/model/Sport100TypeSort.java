package com.ido.ble.protocol.model;

import java.util.List;
/* loaded from: classes11.dex */
public class Sport100TypeSort extends Sport100Type {
    public static final int OPERATE_QUERY = 1;
    public static final int OPERATE_SET = 2;
    public int all_num;
    public int err_code;
    public int flag;
    public List<Sport100TypeItem> items;
    public List<Integer> items_set;
    public int max_show_num;
    public int min_show_num;
    public int now_user_location;
    public int operate;
    public int version;

    public String toString() {
        return "Sport100TypeSort{version=" + this.version + ", all_num=" + this.all_num + ", now_user_location=" + this.now_user_location + ", min_show_num=" + this.min_show_num + ", max_show_num=" + this.max_show_num + ", err_code=" + this.err_code + ", operate=" + this.operate + ", items_set=" + this.items_set + ", items=" + this.items + '}';
    }
}
