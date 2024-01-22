package com.touchgui.sdk.bean;

import androidx.annotation.Nullable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;
/* loaded from: classes12.dex */
public class TGMyDials {
    public static final int TYPE_DYNAMIC = 2;
    public static final int TYPE_NONE = 0;
    public static final int TYPE_PHOTO = 3;
    public static final int TYPE_STATIC = 1;
    @Nullable
    private List<ItemBean> items;

    /* loaded from: classes12.dex */
    public static class ItemBean {
        private int dialId;
        private int flag;

        public int getDialId() {
            return this.dialId;
        }

        public int getFlag() {
            return this.flag;
        }

        public int getType() {
            return (this.flag & 12) >> 2;
        }

        public boolean isCanDelete() {
            return (this.flag & 1) == 1;
        }

        public boolean isCurrentDial() {
            return (this.flag & 2) == 2;
        }

        public void setCanDelete(boolean z) {
            this.flag = z ? this.flag | 1 : this.flag & (-2);
        }

        public void setCurrentDial(boolean z) {
            this.flag = z ? this.flag | 2 : this.flag & (-3);
        }

        public void setDialId(int i) {
            this.dialId = i;
        }

        public void setFlag(int i) {
            this.flag = i;
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes12.dex */
    public @interface TGDialType {
    }

    public TGMyDials() {
    }

    public TGMyDials(@Nullable List<ItemBean> list) {
        this.items = list;
    }

    @Nullable
    public List<ItemBean> getItems() {
        return this.items;
    }

    public void setItems(@Nullable List<ItemBean> list) {
        this.items = list;
    }
}
