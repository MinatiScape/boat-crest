package com.coveiot.khjstyledb;

import androidx.room.TypeConverter;
import java.util.Date;
/* loaded from: classes8.dex */
public class TimestampConverter {
    @TypeConverter
    public static Date toDate(Long l) {
        if (l == null) {
            return null;
        }
        return new Date(l.longValue());
    }

    @TypeConverter
    public static Long toLong(Date date) {
        if (date == null) {
            return null;
        }
        return Long.valueOf(date.getTime());
    }
}
