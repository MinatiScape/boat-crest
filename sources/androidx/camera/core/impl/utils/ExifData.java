package androidx.camera.core.impl.utils;

import android.os.Build;
import android.util.Pair;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.camera.core.Logger;
import androidx.camera.core.impl.CameraCaptureMetaData;
import androidx.core.util.Preconditions;
import androidx.exifinterface.media.ExifInterface;
import com.clevertap.android.sdk.Constants;
import com.realsil.sdk.dfu.DfuException;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;
import okhttp3.internal.ws.WebSocketProtocol;
import org.eclipse.paho.client.mqttv3.MqttTopic;
/* loaded from: classes.dex */
public class ExifData {
    public static final e[] c;
    public static final e[] d;
    public static final e[] e;
    public static final e[] f;
    public static final e[] g;
    public static final e[][] h;
    public static final HashSet<String> i;

    /* renamed from: a  reason: collision with root package name */
    public final List<Map<String, d>> f734a;
    public final ByteOrder b;

    /* loaded from: classes.dex */
    public static final class Builder {
        public static final Pattern c = Pattern.compile("^(\\d{2}):(\\d{2}):(\\d{2})$");
        public static final Pattern d = Pattern.compile("^(\\d{4}):(\\d{2}):(\\d{2})\\s(\\d{2}):(\\d{2}):(\\d{2})$");
        public static final Pattern e = Pattern.compile("^(\\d{4})-(\\d{2})-(\\d{2})\\s(\\d{2}):(\\d{2}):(\\d{2})$");
        public static final List<HashMap<String, e>> f = Collections.list(new a());

        /* renamed from: a  reason: collision with root package name */
        public final List<Map<String, d>> f735a = Collections.list(new b(this));
        public final ByteOrder b;

        /* loaded from: classes.dex */
        public class a implements Enumeration<HashMap<String, e>> {

            /* renamed from: a  reason: collision with root package name */
            public int f736a = 0;

            @Override // java.util.Enumeration
            /* renamed from: a */
            public HashMap<String, e> nextElement() {
                e[] eVarArr;
                HashMap<String, e> hashMap = new HashMap<>();
                for (e eVar : ExifData.h[this.f736a]) {
                    hashMap.put(eVar.b, eVar);
                }
                this.f736a++;
                return hashMap;
            }

            @Override // java.util.Enumeration
            public boolean hasMoreElements() {
                return this.f736a < ExifData.h.length;
            }
        }

        /* loaded from: classes.dex */
        public class b implements Enumeration<Map<String, d>> {

            /* renamed from: a  reason: collision with root package name */
            public int f737a = 0;

            public b(Builder builder) {
            }

            @Override // java.util.Enumeration
            /* renamed from: a */
            public Map<String, d> nextElement() {
                this.f737a++;
                return new HashMap();
            }

            @Override // java.util.Enumeration
            public boolean hasMoreElements() {
                return this.f737a < ExifData.h.length;
            }
        }

        /* loaded from: classes.dex */
        public class c implements Enumeration<Map<String, d>> {

            /* renamed from: a  reason: collision with root package name */
            public final Enumeration<Map<String, d>> f738a;

            public c() {
                this.f738a = Collections.enumeration(Builder.this.f735a);
            }

            @Override // java.util.Enumeration
            /* renamed from: a */
            public Map<String, d> nextElement() {
                return new HashMap(this.f738a.nextElement());
            }

            @Override // java.util.Enumeration
            public boolean hasMoreElements() {
                return this.f738a.hasMoreElements();
            }
        }

        public Builder(@NonNull ByteOrder byteOrder) {
            this.b = byteOrder;
        }

        public static Pair<Integer, Integer> a(String str) {
            if (str.contains(Constants.SEPARATOR_COMMA)) {
                String[] split = str.split(Constants.SEPARATOR_COMMA, -1);
                Pair<Integer, Integer> a2 = a(split[0]);
                if (((Integer) a2.first).intValue() == 2) {
                    return a2;
                }
                for (int i = 1; i < split.length; i++) {
                    Pair<Integer, Integer> a3 = a(split[i]);
                    int intValue = (((Integer) a3.first).equals(a2.first) || ((Integer) a3.second).equals(a2.first)) ? ((Integer) a2.first).intValue() : -1;
                    int intValue2 = (((Integer) a2.second).intValue() == -1 || !(((Integer) a3.first).equals(a2.second) || ((Integer) a3.second).equals(a2.second))) ? -1 : ((Integer) a2.second).intValue();
                    if (intValue == -1 && intValue2 == -1) {
                        return new Pair<>(2, -1);
                    }
                    if (intValue == -1) {
                        a2 = new Pair<>(Integer.valueOf(intValue2), -1);
                    } else if (intValue2 == -1) {
                        a2 = new Pair<>(Integer.valueOf(intValue), -1);
                    }
                }
                return a2;
            } else if (str.contains(MqttTopic.TOPIC_LEVEL_SEPARATOR)) {
                String[] split2 = str.split(MqttTopic.TOPIC_LEVEL_SEPARATOR, -1);
                if (split2.length == 2) {
                    try {
                        long parseDouble = (long) Double.parseDouble(split2[0]);
                        long parseDouble2 = (long) Double.parseDouble(split2[1]);
                        if (parseDouble >= 0 && parseDouble2 >= 0) {
                            if (parseDouble <= 2147483647L && parseDouble2 <= 2147483647L) {
                                return new Pair<>(10, 5);
                            }
                            return new Pair<>(5, -1);
                        }
                        return new Pair<>(10, -1);
                    } catch (NumberFormatException unused) {
                    }
                }
                return new Pair<>(2, -1);
            } else {
                try {
                    try {
                        long parseLong = Long.parseLong(str);
                        int i2 = (parseLong > 0L ? 1 : (parseLong == 0L ? 0 : -1));
                        if (i2 < 0 || parseLong > WebSocketProtocol.PAYLOAD_SHORT_MAX) {
                            if (i2 < 0) {
                                return new Pair<>(9, -1);
                            }
                            return new Pair<>(4, -1);
                        }
                        return new Pair<>(3, 4);
                    } catch (NumberFormatException unused2) {
                        return new Pair<>(2, -1);
                    }
                } catch (NumberFormatException unused3) {
                    Double.parseDouble(str);
                    return new Pair<>(12, -1);
                }
            }
        }

        public final void b(@NonNull String str, @NonNull String str2, @NonNull List<Map<String, d>> list) {
            for (Map<String, d> map : list) {
                if (map.containsKey(str)) {
                    return;
                }
            }
            c(str, str2, list);
        }

        @NonNull
        public ExifData build() {
            ArrayList list = Collections.list(new c());
            if (!list.get(1).isEmpty()) {
                b(ExifInterface.TAG_EXPOSURE_PROGRAM, String.valueOf(0), list);
                b(ExifInterface.TAG_EXIF_VERSION, "0230", list);
                b(ExifInterface.TAG_COMPONENTS_CONFIGURATION, "1,2,3,0", list);
                b(ExifInterface.TAG_METERING_MODE, String.valueOf(0), list);
                b(ExifInterface.TAG_LIGHT_SOURCE, String.valueOf(0), list);
                b(ExifInterface.TAG_FLASHPIX_VERSION, "0100", list);
                b(ExifInterface.TAG_FOCAL_PLANE_RESOLUTION_UNIT, String.valueOf(2), list);
                b(ExifInterface.TAG_FILE_SOURCE, String.valueOf(3), list);
                b(ExifInterface.TAG_SCENE_TYPE, String.valueOf(1), list);
                b(ExifInterface.TAG_CUSTOM_RENDERED, String.valueOf(0), list);
                b(ExifInterface.TAG_SCENE_CAPTURE_TYPE, String.valueOf(0), list);
                b(ExifInterface.TAG_CONTRAST, String.valueOf(0), list);
                b(ExifInterface.TAG_SATURATION, String.valueOf(0), list);
                b(ExifInterface.TAG_SHARPNESS, String.valueOf(0), list);
            }
            if (!list.get(2).isEmpty()) {
                b(ExifInterface.TAG_GPS_VERSION_ID, "2300", list);
                b(ExifInterface.TAG_GPS_SPEED_REF, "K", list);
                b(ExifInterface.TAG_GPS_TRACK_REF, ExifInterface.GPS_DIRECTION_TRUE, list);
                b(ExifInterface.TAG_GPS_IMG_DIRECTION_REF, ExifInterface.GPS_DIRECTION_TRUE, list);
                b(ExifInterface.TAG_GPS_DEST_BEARING_REF, ExifInterface.GPS_DIRECTION_TRUE, list);
                b(ExifInterface.TAG_GPS_DEST_DISTANCE_REF, "K", list);
            }
            return new ExifData(this.b, list);
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        /* JADX WARN: Code restructure failed: missing block: B:60:0x018a, code lost:
            if (r7 != r0) goto L100;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final void c(@androidx.annotation.NonNull java.lang.String r18, @androidx.annotation.Nullable java.lang.String r19, @androidx.annotation.NonNull java.util.List<java.util.Map<java.lang.String, androidx.camera.core.impl.utils.d>> r20) {
            /*
                Method dump skipped, instructions count: 772
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.camera.core.impl.utils.ExifData.Builder.c(java.lang.String, java.lang.String, java.util.List):void");
        }

        @NonNull
        public Builder removeAttribute(@NonNull String str) {
            c(str, null, this.f735a);
            return this;
        }

        @NonNull
        public Builder setAttribute(@NonNull String str, @NonNull String str2) {
            c(str, str2, this.f735a);
            return this;
        }

        @NonNull
        public Builder setExposureTimeNanos(long j) {
            return setAttribute(ExifInterface.TAG_EXPOSURE_TIME, String.valueOf(j / TimeUnit.SECONDS.toNanos(1L)));
        }

        @NonNull
        public Builder setFlashState(@NonNull CameraCaptureMetaData.FlashState flashState) {
            int i;
            if (flashState == CameraCaptureMetaData.FlashState.UNKNOWN) {
                return this;
            }
            int i2 = a.f739a[flashState.ordinal()];
            if (i2 == 1) {
                i = 0;
            } else if (i2 == 2) {
                i = 32;
            } else if (i2 != 3) {
                Logger.w("ExifData", "Unknown flash state: " + flashState);
                return this;
            } else {
                i = 1;
            }
            if ((i & 1) == 1) {
                setAttribute(ExifInterface.TAG_LIGHT_SOURCE, String.valueOf(4));
            }
            return setAttribute(ExifInterface.TAG_FLASH, String.valueOf(i));
        }

        @NonNull
        public Builder setFocalLength(float f2) {
            return setAttribute(ExifInterface.TAG_FOCAL_LENGTH, new f(f2 * 1000.0f, 1000L).toString());
        }

        @NonNull
        public Builder setImageHeight(int i) {
            return setAttribute(ExifInterface.TAG_IMAGE_LENGTH, String.valueOf(i));
        }

        @NonNull
        public Builder setImageWidth(int i) {
            return setAttribute(ExifInterface.TAG_IMAGE_WIDTH, String.valueOf(i));
        }

        @NonNull
        public Builder setIso(int i) {
            return setAttribute(ExifInterface.TAG_SENSITIVITY_TYPE, String.valueOf(3)).setAttribute(ExifInterface.TAG_PHOTOGRAPHIC_SENSITIVITY, String.valueOf(Math.min(65535, i)));
        }

        @NonNull
        public Builder setLensFNumber(float f2) {
            return setAttribute(ExifInterface.TAG_F_NUMBER, String.valueOf(f2));
        }

        @NonNull
        public Builder setOrientationDegrees(int i) {
            int i2;
            if (i == 0) {
                i2 = 1;
            } else if (i == 90) {
                i2 = 6;
            } else if (i == 180) {
                i2 = 3;
            } else if (i != 270) {
                Logger.w("ExifData", "Unexpected orientation value: " + i + ". Must be one of 0, 90, 180, 270.");
                i2 = 0;
            } else {
                i2 = 8;
            }
            return setAttribute(ExifInterface.TAG_ORIENTATION, String.valueOf(i2));
        }

        @NonNull
        public Builder setWhiteBalanceMode(@NonNull WhiteBalanceMode whiteBalanceMode) {
            String valueOf;
            int i = a.b[whiteBalanceMode.ordinal()];
            if (i != 1) {
                valueOf = i != 2 ? null : String.valueOf(1);
            } else {
                valueOf = String.valueOf(0);
            }
            return setAttribute(ExifInterface.TAG_WHITE_BALANCE, valueOf);
        }
    }

    /* loaded from: classes.dex */
    public enum WhiteBalanceMode {
        AUTO,
        MANUAL
    }

    /* loaded from: classes.dex */
    public static /* synthetic */ class a {

        /* renamed from: a  reason: collision with root package name */
        public static final /* synthetic */ int[] f739a;
        public static final /* synthetic */ int[] b;

        static {
            int[] iArr = new int[WhiteBalanceMode.values().length];
            b = iArr;
            try {
                iArr[WhiteBalanceMode.AUTO.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                b[WhiteBalanceMode.MANUAL.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            int[] iArr2 = new int[CameraCaptureMetaData.FlashState.values().length];
            f739a = iArr2;
            try {
                iArr2[CameraCaptureMetaData.FlashState.READY.ordinal()] = 1;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f739a[CameraCaptureMetaData.FlashState.NONE.ordinal()] = 2;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f739a[CameraCaptureMetaData.FlashState.FIRED.ordinal()] = 3;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    static {
        e[] eVarArr = {new e(ExifInterface.TAG_IMAGE_WIDTH, 256, 3, 4), new e(ExifInterface.TAG_IMAGE_LENGTH, 257, 3, 4), new e(ExifInterface.TAG_MAKE, DfuException.ERROR_READ_APP_INFO_ERROR, 2), new e(ExifInterface.TAG_MODEL, 272, 2), new e(ExifInterface.TAG_ORIENTATION, 274, 3), new e(ExifInterface.TAG_X_RESOLUTION, DfuException.ERROR_DFU_SPP_RWS_NOT_READY, 5), new e(ExifInterface.TAG_Y_RESOLUTION, 283, 5), new e(ExifInterface.TAG_RESOLUTION_UNIT, 296, 3), new e(ExifInterface.TAG_SOFTWARE, 305, 2), new e(ExifInterface.TAG_DATETIME, 306, 2), new e(ExifInterface.TAG_Y_CB_CR_POSITIONING, 531, 3), new e("SubIFDPointer", 330, 4), new e("ExifIFDPointer", 34665, 4), new e("GPSInfoIFDPointer", 34853, 4)};
        c = eVarArr;
        e[] eVarArr2 = {new e(ExifInterface.TAG_EXPOSURE_TIME, 33434, 5), new e(ExifInterface.TAG_F_NUMBER, 33437, 5), new e(ExifInterface.TAG_EXPOSURE_PROGRAM, 34850, 3), new e(ExifInterface.TAG_PHOTOGRAPHIC_SENSITIVITY, 34855, 3), new e(ExifInterface.TAG_SENSITIVITY_TYPE, 34864, 3), new e(ExifInterface.TAG_EXIF_VERSION, 36864, 2), new e(ExifInterface.TAG_DATETIME_ORIGINAL, 36867, 2), new e(ExifInterface.TAG_DATETIME_DIGITIZED, 36868, 2), new e(ExifInterface.TAG_COMPONENTS_CONFIGURATION, 37121, 7), new e(ExifInterface.TAG_SHUTTER_SPEED_VALUE, 37377, 10), new e(ExifInterface.TAG_APERTURE_VALUE, 37378, 5), new e(ExifInterface.TAG_BRIGHTNESS_VALUE, 37379, 10), new e(ExifInterface.TAG_EXPOSURE_BIAS_VALUE, 37380, 10), new e(ExifInterface.TAG_MAX_APERTURE_VALUE, 37381, 5), new e(ExifInterface.TAG_METERING_MODE, 37383, 3), new e(ExifInterface.TAG_LIGHT_SOURCE, 37384, 3), new e(ExifInterface.TAG_FLASH, 37385, 3), new e(ExifInterface.TAG_FOCAL_LENGTH, 37386, 5), new e(ExifInterface.TAG_SUBSEC_TIME, 37520, 2), new e(ExifInterface.TAG_SUBSEC_TIME_ORIGINAL, 37521, 2), new e(ExifInterface.TAG_SUBSEC_TIME_DIGITIZED, 37522, 2), new e(ExifInterface.TAG_FLASHPIX_VERSION, 40960, 7), new e(ExifInterface.TAG_COLOR_SPACE, 40961, 3), new e(ExifInterface.TAG_PIXEL_X_DIMENSION, 40962, 3, 4), new e(ExifInterface.TAG_PIXEL_Y_DIMENSION, 40963, 3, 4), new e("InteroperabilityIFDPointer", 40965, 4), new e(ExifInterface.TAG_FOCAL_PLANE_RESOLUTION_UNIT, 41488, 3), new e(ExifInterface.TAG_SENSING_METHOD, 41495, 3), new e(ExifInterface.TAG_FILE_SOURCE, 41728, 7), new e(ExifInterface.TAG_SCENE_TYPE, 41729, 7), new e(ExifInterface.TAG_CUSTOM_RENDERED, 41985, 3), new e(ExifInterface.TAG_EXPOSURE_MODE, 41986, 3), new e(ExifInterface.TAG_WHITE_BALANCE, 41987, 3), new e(ExifInterface.TAG_SCENE_CAPTURE_TYPE, 41990, 3), new e(ExifInterface.TAG_CONTRAST, 41992, 3), new e(ExifInterface.TAG_SATURATION, 41993, 3), new e(ExifInterface.TAG_SHARPNESS, 41994, 3)};
        d = eVarArr2;
        e[] eVarArr3 = {new e(ExifInterface.TAG_GPS_VERSION_ID, 0, 1), new e(ExifInterface.TAG_GPS_LATITUDE_REF, 1, 2), new e(ExifInterface.TAG_GPS_LATITUDE, 2, 5, 10), new e(ExifInterface.TAG_GPS_LONGITUDE_REF, 3, 2), new e(ExifInterface.TAG_GPS_LONGITUDE, 4, 5, 10), new e(ExifInterface.TAG_GPS_ALTITUDE_REF, 5, 1), new e(ExifInterface.TAG_GPS_ALTITUDE, 6, 5), new e(ExifInterface.TAG_GPS_TIMESTAMP, 7, 5), new e(ExifInterface.TAG_GPS_SPEED_REF, 12, 2), new e(ExifInterface.TAG_GPS_TRACK_REF, 14, 2), new e(ExifInterface.TAG_GPS_IMG_DIRECTION_REF, 16, 2), new e(ExifInterface.TAG_GPS_DEST_BEARING_REF, 23, 2), new e(ExifInterface.TAG_GPS_DEST_DISTANCE_REF, 25, 2)};
        e = eVarArr3;
        f = new e[]{new e("SubIFDPointer", 330, 4), new e("ExifIFDPointer", 34665, 4), new e("GPSInfoIFDPointer", 34853, 4), new e("InteroperabilityIFDPointer", 40965, 4)};
        e[] eVarArr4 = {new e(ExifInterface.TAG_INTEROPERABILITY_INDEX, 1, 2)};
        g = eVarArr4;
        h = new e[][]{eVarArr, eVarArr2, eVarArr3, eVarArr4};
        i = new HashSet<>(Arrays.asList(ExifInterface.TAG_F_NUMBER, ExifInterface.TAG_EXPOSURE_TIME, ExifInterface.TAG_GPS_TIMESTAMP));
    }

    public ExifData(ByteOrder byteOrder, List<Map<String, d>> list) {
        Preconditions.checkState(list.size() == h.length, "Malformed attributes list. Number of IFDs mismatch.");
        this.b = byteOrder;
        this.f734a = list;
    }

    @NonNull
    public static Builder builderForDevice() {
        return new Builder(ByteOrder.BIG_ENDIAN).setAttribute(ExifInterface.TAG_ORIENTATION, String.valueOf(1)).setAttribute(ExifInterface.TAG_X_RESOLUTION, "72/1").setAttribute(ExifInterface.TAG_Y_RESOLUTION, "72/1").setAttribute(ExifInterface.TAG_RESOLUTION_UNIT, String.valueOf(2)).setAttribute(ExifInterface.TAG_Y_CB_CR_POSITIONING, String.valueOf(1)).setAttribute(ExifInterface.TAG_MAKE, Build.MANUFACTURER).setAttribute(ExifInterface.TAG_MODEL, Build.MODEL);
    }

    @NonNull
    public Map<String, d> a(int i2) {
        int length = h.length;
        Preconditions.checkArgumentInRange(i2, 0, length, "Invalid IFD index: " + i2 + ". Index should be between [0, EXIF_TAGS.length] ");
        return this.f734a.get(i2);
    }

    @Nullable
    public final d b(@NonNull String str) {
        if (ExifInterface.TAG_ISO_SPEED_RATINGS.equals(str)) {
            str = ExifInterface.TAG_PHOTOGRAPHIC_SENSITIVITY;
        }
        for (int i2 = 0; i2 < h.length; i2++) {
            d dVar = this.f734a.get(i2).get(str);
            if (dVar != null) {
                return dVar;
            }
        }
        return null;
    }

    @Nullable
    public String getAttribute(@NonNull String str) {
        d b = b(str);
        if (b != null) {
            if (!i.contains(str)) {
                return b.k(this.b);
            }
            if (str.equals(ExifInterface.TAG_GPS_TIMESTAMP)) {
                int i2 = b.f741a;
                if (i2 != 5 && i2 != 10) {
                    Logger.w("ExifData", "GPS Timestamp format is not rational. format=" + b.f741a);
                    return null;
                }
                f[] fVarArr = (f[]) b.l(this.b);
                if (fVarArr != null && fVarArr.length == 3) {
                    return String.format(Locale.US, "%02d:%02d:%02d", Integer.valueOf((int) (((float) fVarArr[0].b()) / ((float) fVarArr[0].a()))), Integer.valueOf((int) (((float) fVarArr[1].b()) / ((float) fVarArr[1].a()))), Integer.valueOf((int) (((float) fVarArr[2].b()) / ((float) fVarArr[2].a()))));
                }
                Logger.w("ExifData", "Invalid GPS Timestamp array. array=" + Arrays.toString(fVarArr));
                return null;
            }
            try {
                return Double.toString(b.j(this.b));
            } catch (NumberFormatException unused) {
            }
        }
        return null;
    }

    @NonNull
    public ByteOrder getByteOrder() {
        return this.b;
    }
}
