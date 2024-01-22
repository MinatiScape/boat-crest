package com.mappls.sdk.direction.ui.common;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import com.jstyle.blesdk1860.constant.DeviceKey;
import com.mappls.sdk.direction.ui.R;
import com.mappls.sdk.direction.ui.model.StopModel;
import com.mappls.sdk.maps.geometry.LatLng;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import org.eclipse.paho.client.mqttv3.MqttTopic;
/* loaded from: classes11.dex */
public final class d {
    public static int a(List list) {
        if (list == null || list.isEmpty()) {
            return R.color.mappls_direction_eta_text_color_with_out_traffic;
        }
        List arrayList = list.isEmpty() ? new ArrayList() : list.subList(0, list.size());
        int i = 0;
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            if (((String) arrayList.get(i2)).equals("heavy") || ((String) arrayList.get(i2)).equals("moderate") || ((String) arrayList.get(i2)).equals("severe")) {
                i++;
            }
        }
        int size = !arrayList.isEmpty() ? (i * 100) / arrayList.size() : 1;
        return size <= 10 ? R.color.mappls_direction_eta_text_color_with_out_traffic : size <= 25 ? R.color.mappls_direction_eta_text_color_with_low_traffic : R.color.mappls_direction_eta_text_color_with_traffic;
    }

    public static String a(double d) {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(12, (int) ((d % 3600.0d) / 60.0d));
        calendar.add(10, (int) ((d % 86400.0d) / 3600.0d));
        calendar.add(5, (int) (d / 86400.0d));
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm a", Locale.US);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Calcutta"));
        return "Arrival: " + simpleDateFormat.format(new Date(calendar.getTimeInMillis()));
    }

    public static boolean a(StopModel stopModel, StopModel stopModel2) {
        if (stopModel != null && stopModel2 != null) {
            if (stopModel.getLocation() != null && stopModel2.getLocation() != null) {
                return new LatLng(stopModel.getLocation().latitude(), stopModel.getLocation().longitude()).distanceTo(new LatLng(stopModel2.getLocation().latitude(), stopModel2.getLocation().longitude())) < 10.0d;
            } else if (stopModel.getMapplsPin() != null && stopModel2.getMapplsPin() != null) {
                return stopModel.getMapplsPin().equalsIgnoreCase(stopModel2.getMapplsPin());
            }
        }
        return false;
    }

    public static String b(double d) {
        if (d / 1000.0d < 1.0d) {
            return ((int) (d + 0.5d)) + " m";
        } else if (d >= 100000.0d) {
            DecimalFormat decimalFormat = new DecimalFormat(MqttTopic.MULTI_LEVEL_WILDCARD);
            return decimalFormat.format((int) ((((float) d) / 1000.0f) + 0.5d)) + " Km";
        } else if (d >= 10000.0d) {
            DecimalFormat decimalFormat2 = new DecimalFormat("#.#");
            return decimalFormat2.format(((float) d) / 1000.0f) + " Km";
        } else {
            DecimalFormat decimalFormat3 = new DecimalFormat("#.##");
            return decimalFormat3.format(((float) d) / 1000.0f) + " Km";
        }
    }

    public static String c(double d) {
        StringBuilder sb;
        long j = (long) ((d % 3600.0d) / 60.0d);
        long j2 = (long) ((d % 86400.0d) / 3600.0d);
        long j3 = (long) (d / 86400.0d);
        String str = "";
        if (j3 > 0) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append(j3);
            sb2.append(HexStringBuilder.DEFAULT_SEPARATOR);
            sb2.append(j3 > 1 ? DeviceKey.Days : "Day");
            sb2.append(HexStringBuilder.DEFAULT_SEPARATOR);
            sb2.append(j2);
            sb2.append(" hr");
            if (j > 0) {
                str = HexStringBuilder.DEFAULT_SEPARATOR + j + " min";
            }
            sb2.append(str);
            return sb2.toString();
        }
        if (j2 > 0) {
            sb = new StringBuilder();
            sb.append(j2);
            sb.append(" hr");
            if (j > 0) {
                str = HexStringBuilder.DEFAULT_SEPARATOR + j + " min";
            }
            sb.append(str);
        } else {
            sb = new StringBuilder();
            sb.append(j);
            sb.append(" min");
        }
        return sb.toString();
    }
}
