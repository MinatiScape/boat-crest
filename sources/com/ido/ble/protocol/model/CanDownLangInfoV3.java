package com.ido.ble.protocol.model;

import java.io.Serializable;
import java.util.List;
/* loaded from: classes11.dex */
public class CanDownLangInfoV3 implements Serializable {
    public int default_lang;
    public int fixed_lang;
    public List<Item> items;
    public int items_len;
    public List<Item> items_user;
    public int max_storage_lang;
    public int use_lang;
    public int user_len;

    /* loaded from: classes11.dex */
    public static class Item {
        public int language_type;
        public int language_version;

        public String toString() {
            return "Item{language_type=" + this.language_type + ", language_version=" + this.language_version + '}';
        }
    }

    public String toString() {
        return "CanDownLangInfoV3{use_lang=" + this.use_lang + ", default_lang=" + this.default_lang + ", fixed_lang=" + this.fixed_lang + ", max_storage_lang=" + this.max_storage_lang + ", items_len=" + this.items_len + ", user_len=" + this.user_len + ", items=" + this.items + ", items_user=" + this.items_user + '}';
    }
}
