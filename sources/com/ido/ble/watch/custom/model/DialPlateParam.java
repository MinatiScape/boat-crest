package com.ido.ble.watch.custom.model;

import java.util.List;
/* loaded from: classes11.dex */
public class DialPlateParam {
    public int cloud_watch_num;
    public int file_max_size;
    public List<PlateFileInfo> item;
    public int list_item_numb;
    public int local_watch_num;
    public String now_show_watch_name;
    public int usable_max_download_space_size;
    public int user_cloud_watch_num;
    public int user_wallpaper_watch_num;
    public int user_watch_capacity_size;
    public int wallpaper_watch_num;
    public int watch_capacity_size;
    public int watch_frame_main_version;

    /* loaded from: classes11.dex */
    public static class PlateFileInfo {
        public static final int TYPE_CLOUD = 3;
        public static final int TYPE_NORMAL = 1;
        public static final int TYPE_WRAPPER = 2;
        public String name;
        public int size;
        public int sort_number;
        public int type;
        public int watch_version;

        public String toString() {
            return "PlateFileInfo{name='" + this.name + "', type=" + this.type + ", watch_version=" + this.watch_version + ", sort_number=" + this.sort_number + ", size=" + this.size + '}';
        }
    }

    public String toString() {
        return "DialPlateParam{cloud_watch_num=" + this.cloud_watch_num + ", file_max_size=" + this.file_max_size + ", list_item_numb=" + this.list_item_numb + ", local_watch_num=" + this.local_watch_num + ", now_show_watch_name='" + this.now_show_watch_name + "', user_cloud_watch_num=" + this.user_cloud_watch_num + ", user_wallpaper_watch_num=" + this.user_wallpaper_watch_num + ", wallpaper_watch_num=" + this.wallpaper_watch_num + ", watch_frame_main_version=" + this.watch_frame_main_version + ", watch_capacity_size=" + this.watch_capacity_size + ", user_watch_capacity_size=" + this.user_watch_capacity_size + ", usable_max_download_space_size=" + this.usable_max_download_space_size + ", item=" + this.item + '}';
    }
}
