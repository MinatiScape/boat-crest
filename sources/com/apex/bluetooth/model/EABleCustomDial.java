package com.apex.bluetooth.model;

import java.util.List;
/* loaded from: classes.dex */
public class EABleCustomDial {
    public int crc;
    public int current_size;
    public List<EABleUserImage> s_index;
    public int wait_bytes;

    /* loaded from: classes.dex */
    public class EABleUserImage {
        public int built_in_id;
        public int colour;
        public ImageType e_type;
        public int font_id;
        public int h;
        public int offset_address;
        public int size;
        public int unit_size;
        public int w;
        public int x;
        public int y;

        public EABleUserImage() {
        }

        public int getBuilt_in_id() {
            return this.built_in_id;
        }

        public int getColour() {
            return this.colour;
        }

        public ImageType getE_type() {
            return this.e_type;
        }

        public int getFont_id() {
            return this.font_id;
        }

        public int getH() {
            return this.h;
        }

        public int getOffset_address() {
            return this.offset_address;
        }

        public int getSize() {
            return this.size;
        }

        public int getUnit_size() {
            return this.unit_size;
        }

        public int getW() {
            return this.w;
        }

        public int getX() {
            return this.x;
        }

        public int getY() {
            return this.y;
        }

        public void setBuilt_in_id(int i) {
            this.built_in_id = i;
        }

        public void setColour(int i) {
            this.colour = i;
        }

        public void setE_type(ImageType imageType) {
            this.e_type = imageType;
        }

        public void setFont_id(int i) {
            this.font_id = i;
        }

        public void setH(int i) {
            this.h = i;
        }

        public void setOffset_address(int i) {
            this.offset_address = i;
        }

        public void setSize(int i) {
            this.size = i;
        }

        public void setUnit_size(int i) {
            this.unit_size = i;
        }

        public void setW(int i) {
            this.w = i;
        }

        public void setX(int i) {
            this.x = i;
        }

        public void setY(int i) {
            this.y = i;
        }
    }

    /* loaded from: classes.dex */
    public enum ImageType {
        unknow(0),
        background(1),
        number_font(2),
        year(3),
        month(4),
        day(5),
        hour(6),
        minute(7),
        second(8),
        hour_hand(9),
        minute_hand(10),
        second_hand(11),
        home_hour_hand(12),
        home_minute_hand(13),
        home_second_hand(14),
        steps(15),
        steps_number_font(16),
        calorie(17),
        calorie_number_font(18),
        distance(19),
        distance_number_font(20),
        duration(21),
        duration_number_font(22),
        heart_rate(23),
        heart_rate_number_font(24),
        weather(25),
        battery(26);
        
        public int value;

        ImageType(int i) {
            this.value = i;
        }

        public int getValue() {
            return this.value;
        }
    }

    public int getCrc() {
        return this.crc;
    }

    public int getCurrent_size() {
        return this.current_size;
    }

    public List<EABleUserImage> getS_index() {
        return this.s_index;
    }

    public int getWait_bytes() {
        return this.wait_bytes;
    }

    public void setCrc(int i) {
        this.crc = i;
    }

    public void setCurrent_size(int i) {
        this.current_size = i;
    }

    public void setS_index(List<EABleUserImage> list) {
        this.s_index = list;
    }

    public void setWait_bytes(int i) {
        this.wait_bytes = i;
    }
}
