package com.ido.ble.protocol.model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
/* loaded from: classes11.dex */
public class MusicOperate {
    public MusicFolder folder_items;
    public int folder_operate;
    public MusicFile music_items;
    public int music_operate;

    /* loaded from: classes11.dex */
    public static class MusicAndFolderInfo {
        public long all_memory;
        public List<MusicFolder> folder_items;
        public int folder_num;
        public List<MusicFile> music_items;
        public int music_num;
        public long used_memory;
        public long useful_memory;

        public String toString() {
            return "MusicInfo{all_memory=" + this.all_memory + ", useful_memory=" + this.useful_memory + ", used_memory=" + this.used_memory + ", folder_num=" + this.folder_num + ", music_num=" + this.music_num + ", folder_items=" + this.folder_items + ", music_items=" + this.music_items + '}';
        }
    }

    /* loaded from: classes11.dex */
    public static class MusicFile implements Serializable {
        private static final long serialVersionUID = 1;
        public int music_id;
        public long music_memory;
        public String music_name;
        public String singer_name;

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof MusicFile) && this.music_id == ((MusicFile) obj).music_id;
        }

        public int hashCode() {
            return Objects.hash(Integer.valueOf(this.music_id));
        }

        public String toString() {
            return "MusicFile{music_id=" + this.music_id + ", music_memory=" + this.music_memory + ", music_name='" + this.music_name + "', singer_name=" + this.singer_name + '}';
        }
    }

    /* loaded from: classes11.dex */
    public static class MusicFolder implements Serializable {
        private static final long serialVersionUID = 1;
        public int folder_id;
        public String folder_name;
        public List<Integer> music_index;
        public int music_num;

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof MusicFolder) && this.folder_id == ((MusicFolder) obj).folder_id;
        }

        public int hashCode() {
            return Objects.hash(Integer.valueOf(this.folder_id));
        }

        public String toString() {
            return "MusicFolder{folder_id=" + this.folder_id + ", folder_name='" + this.folder_name + "', music_num=" + this.music_num + ", music_index=" + this.music_index + '}';
        }
    }

    /* loaded from: classes11.dex */
    public static class OperateResponse {
        public int err_code;
        public int music_id;
        public int operate_type;

        public String toString() {
            return "OperateResponse{err_code=" + this.err_code + ", operate_type=" + this.operate_type + ", music_id=" + this.music_id + '}';
        }
    }

    public String toString() {
        return "MusicOperate{music_operate=" + this.music_operate + ", folder_operate=" + this.folder_operate + ", folder_items=" + this.folder_items + ", music_items=" + this.music_items + '}';
    }
}
