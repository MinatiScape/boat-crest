package com.touchgui.sdk.bean;

import androidx.annotation.Nullable;
import java.util.List;
/* loaded from: classes12.dex */
public class TGNFCReadCard {
    private String atqa;
    private int code;
    private String id;
    private boolean isCpu;
    private boolean isEncrypt;
    private String sak;
    @Nullable
    private List<SectorItem> sectors;
    private int type;

    /* loaded from: classes12.dex */
    public static class SectorItem {
        private String blockInfo;
        private int index = 0;
        private boolean isEncrypt;

        public String getBlockInfo() {
            return this.blockInfo;
        }

        public int getIndex() {
            return this.index;
        }

        public boolean isEncrypt() {
            return this.isEncrypt;
        }

        public void setBlockInfo(String str) {
            this.blockInfo = str;
        }

        public void setEncrypt(boolean z) {
            this.isEncrypt = z;
        }

        public void setIndex(int i) {
            this.index = i;
        }
    }

    public String getAtqa() {
        return this.atqa;
    }

    public int getCode() {
        return this.code;
    }

    public String getId() {
        return this.id;
    }

    public String getSak() {
        return this.sak;
    }

    @Nullable
    public List<SectorItem> getSectors() {
        return this.sectors;
    }

    public int getType() {
        return this.type;
    }

    public boolean isCpu() {
        return this.isCpu;
    }

    public boolean isEncrypt() {
        return this.isEncrypt;
    }

    public void setAtqa(String str) {
        this.atqa = str;
    }

    public void setCode(int i) {
        this.code = i;
    }

    public void setCpu(boolean z) {
        this.isCpu = z;
    }

    public void setEncrypt(boolean z) {
        this.isEncrypt = z;
    }

    public void setId(String str) {
        this.id = str;
    }

    public void setSak(String str) {
        this.sak = str;
    }

    public void setSectors(@Nullable List<SectorItem> list) {
        this.sectors = list;
    }

    public void setType(int i) {
        this.type = i;
    }
}
