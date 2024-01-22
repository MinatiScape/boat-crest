package com.ido.ble.protocol.model;

import android.text.TextUtils;
import com.google.gson.JsonParseException;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.jstyle.blesdk1860.constant.BleConst;
import java.io.Serializable;
import java.util.Arrays;
/* loaded from: classes11.dex */
public class DrinkWaterReminder implements Serializable {
    public static final int STATUS_OFF = 0;
    public static final int STATUS_ON = 1;
    private static final long serialVersionUID = 1;
    private int endHour;
    private int endMinute;
    private int interval;
    public int notifyFlag;
    @JsonAdapter(BooleanTypeAdapter.class)
    private boolean onOff;
    private int repeat;
    private int startHour;
    private int startMinute;
    private boolean[] weeks = new boolean[7];

    /* renamed from: com.ido.ble.protocol.model.DrinkWaterReminder$1  reason: invalid class name */
    /* loaded from: classes11.dex */
    public static /* synthetic */ class AnonymousClass1 {
        public static final /* synthetic */ int[] $SwitchMap$com$google$gson$stream$JsonToken;

        static {
            int[] iArr = new int[JsonToken.values().length];
            $SwitchMap$com$google$gson$stream$JsonToken = iArr;
            try {
                iArr[JsonToken.BOOLEAN.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$gson$stream$JsonToken[JsonToken.NULL.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$google$gson$stream$JsonToken[JsonToken.NUMBER.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$google$gson$stream$JsonToken[JsonToken.STRING.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    /* loaded from: classes11.dex */
    public static class BooleanTypeAdapter extends TypeAdapter<Boolean> {
        public static boolean toBoolean(String str) {
            return !TextUtils.isEmpty(str) && (str.equalsIgnoreCase("true") || !str.equals(BleConst.GetDeviceTime));
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        public Boolean read(JsonReader jsonReader) {
            JsonToken peek = jsonReader.peek();
            int i = AnonymousClass1.$SwitchMap$com$google$gson$stream$JsonToken[peek.ordinal()];
            if (i != 1) {
                if (i == 2) {
                    jsonReader.nextNull();
                    return null;
                } else if (i == 3) {
                    return Boolean.valueOf(jsonReader.nextInt() != 0);
                } else if (i == 4) {
                    return Boolean.valueOf(toBoolean(jsonReader.nextString()));
                } else {
                    throw new JsonParseException("Expected BOOLEAN or NUMBER but was " + peek);
                }
            }
            return Boolean.valueOf(jsonReader.nextBoolean());
        }

        @Override // com.google.gson.TypeAdapter
        public void write(JsonWriter jsonWriter, Boolean bool) {
            if (bool == null) {
                jsonWriter.nullValue();
            } else {
                jsonWriter.value(bool.booleanValue() ? 1L : 0L);
            }
        }
    }

    private void byteToWeekRepeat() {
        this.weeks = new boolean[7];
        int i = 0;
        while (i < 7) {
            int i2 = i + 1;
            if ((this.repeat & (1 << i2)) != 0) {
                this.weeks[i] = true;
            } else {
                this.weeks[i] = false;
            }
            i = i2;
        }
        if ((this.repeat & 1) == 1) {
            this.onOff = true;
        } else {
            this.onOff = false;
        }
    }

    private int toByte(boolean[] zArr, boolean z) {
        int i = 0;
        for (int i2 = 0; i2 < 7; i2++) {
            if (zArr[i2]) {
                i |= 1 << (i2 + 1);
            }
        }
        return z ? i | 1 : i;
    }

    public int getEndHour() {
        return this.endHour;
    }

    public int getEndMinute() {
        return this.endMinute;
    }

    public int getInterval() {
        return this.interval;
    }

    public int getStartHour() {
        return this.startHour;
    }

    public int getStartMinute() {
        return this.startMinute;
    }

    public boolean[] getWeekRepeat() {
        byteToWeekRepeat();
        return this.weeks;
    }

    public boolean[] getWeeks() {
        return this.weeks;
    }

    public boolean isOnOff() {
        return this.onOff;
    }

    public void setEndHour(int i) {
        this.endHour = i;
    }

    public void setEndMinute(int i) {
        this.endMinute = i;
    }

    public void setInterval(int i) {
        this.interval = i;
    }

    public void setOnOff(boolean z) {
        this.repeat = toByte(this.weeks, z);
        this.onOff = z;
    }

    public void setStartHour(int i) {
        this.startHour = i;
    }

    public void setStartMinute(int i) {
        this.startMinute = i;
    }

    public void setWeeks(boolean[] zArr) {
        this.weeks = zArr;
        this.repeat = toByte(zArr, this.onOff);
    }

    public String toString() {
        return "DrinkWaterReminder{startHour=" + this.startHour + ", startMinute=" + this.startMinute + ", endHour=" + this.endHour + ", endMinute=" + this.endMinute + ", interval=" + this.interval + ", repeat=" + this.repeat + ", notifyFlag=" + this.notifyFlag + ", onOff=" + this.onOff + ", weeks=" + Arrays.toString(this.weeks) + '}';
    }
}
