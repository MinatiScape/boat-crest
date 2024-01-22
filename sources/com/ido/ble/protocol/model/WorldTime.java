package com.ido.ble.protocol.model;

import java.util.ArrayList;
/* loaded from: classes11.dex */
public class WorldTime {
    public ArrayList<Item> items;
    public int items_num;

    /* loaded from: classes11.dex */
    public static class Item {
        public String city_name;
        public int id;
        public int latitude;
        public int latitude_flag;
        public int longitude;
        public int longitude_flag;
        public int min_offset;
        public int sunrise_hour;
        public int sunrise_min;
        public int sunset_hour;
        public int sunset_min;

        public String toString() {
            return "Item{id=" + this.id + ", min_offset=" + this.min_offset + ", city_name='" + this.city_name + "', sunrise_hour=" + this.sunrise_hour + ", sunrise_min=" + this.sunrise_min + ", sunset_hour=" + this.sunset_hour + ", sunset_min=" + this.sunset_min + ", longitude_flag=" + this.longitude_flag + ", longitude=" + this.longitude + ", latitude_flag=" + this.latitude_flag + ", latitude=" + this.latitude + '}';
        }
    }

    public String toString() {
        return "WorldTime{items_num=" + this.items_num + ", items=" + this.items + '}';
    }
}
