package androidx.camera.core.impl.utils;

import android.location.Location;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.camera.core.Logger;
import androidx.exifinterface.media.ExifInterface;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import com.realsil.sdk.dfu.DfuException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
/* loaded from: classes.dex */
public final class Exif {
    public static final long INVALID_TIMESTAMP = -1;
    public static final String c = "Exif";
    public static final ThreadLocal<SimpleDateFormat> d = new a();
    public static final ThreadLocal<SimpleDateFormat> e = new b();
    public static final ThreadLocal<SimpleDateFormat> f = new c();

    /* renamed from: a  reason: collision with root package name */
    public final ExifInterface f732a;
    public boolean b = false;

    /* loaded from: classes.dex */
    public class a extends ThreadLocal<SimpleDateFormat> {
        @Override // java.lang.ThreadLocal
        /* renamed from: a */
        public SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy:MM:dd", Locale.US);
        }
    }

    /* loaded from: classes.dex */
    public class b extends ThreadLocal<SimpleDateFormat> {
        @Override // java.lang.ThreadLocal
        /* renamed from: a */
        public SimpleDateFormat initialValue() {
            return new SimpleDateFormat("HH:mm:ss", Locale.US);
        }
    }

    /* loaded from: classes.dex */
    public class c extends ThreadLocal<SimpleDateFormat> {
        @Override // java.lang.ThreadLocal
        /* renamed from: a */
        public SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy:MM:dd HH:mm:ss", Locale.US);
        }
    }

    /* loaded from: classes.dex */
    public static final class d {

        /* loaded from: classes.dex */
        public static final class a {

            /* renamed from: a  reason: collision with root package name */
            public final double f733a;

            public a(double d) {
                this.f733a = d;
            }

            public double a() {
                return this.f733a / 2.23694d;
            }
        }

        public static a a(double d) {
            return new a(d * 0.621371d);
        }

        public static a b(double d) {
            return new a(d * 1.15078d);
        }

        public static a c(double d) {
            return new a(d);
        }
    }

    public Exif(ExifInterface exifInterface) {
        this.f732a = exifInterface;
    }

    public static Date b(String str) throws ParseException {
        return d.get().parse(str);
    }

    public static Date c(String str) throws ParseException {
        return f.get().parse(str);
    }

    @NonNull
    public static Exif createFromFile(@NonNull File file) throws IOException {
        return createFromFileString(file.toString());
    }

    @NonNull
    public static Exif createFromFileString(@NonNull String str) throws IOException {
        return new Exif(new ExifInterface(str));
    }

    @NonNull
    public static Exif createFromInputStream(@NonNull InputStream inputStream) throws IOException {
        return new Exif(new ExifInterface(inputStream));
    }

    public static Date d(String str) throws ParseException {
        return e.get().parse(str);
    }

    public static String e(long j) {
        return f.get().format(new Date(j));
    }

    public final void a() {
        long currentTimeMillis = System.currentTimeMillis();
        String e2 = e(currentTimeMillis);
        this.f732a.setAttribute(ExifInterface.TAG_DATETIME, e2);
        try {
            this.f732a.setAttribute(ExifInterface.TAG_SUBSEC_TIME, Long.toString(currentTimeMillis - c(e2).getTime()));
        } catch (ParseException unused) {
        }
    }

    public void attachLocation(@NonNull Location location) {
        this.f732a.setGpsInfo(location);
    }

    public void attachTimestamp() {
        long currentTimeMillis = System.currentTimeMillis();
        String e2 = e(currentTimeMillis);
        this.f732a.setAttribute(ExifInterface.TAG_DATETIME_ORIGINAL, e2);
        this.f732a.setAttribute(ExifInterface.TAG_DATETIME_DIGITIZED, e2);
        try {
            String l = Long.toString(currentTimeMillis - c(e2).getTime());
            this.f732a.setAttribute(ExifInterface.TAG_SUBSEC_TIME_ORIGINAL, l);
            this.f732a.setAttribute(ExifInterface.TAG_SUBSEC_TIME_DIGITIZED, l);
        } catch (ParseException unused) {
        }
        this.b = false;
    }

    public final long f(@Nullable String str) {
        if (str == null) {
            return -1L;
        }
        try {
            return c(str).getTime();
        } catch (ParseException unused) {
            return -1L;
        }
    }

    public void flipHorizontally() {
        int i;
        switch (getOrientation()) {
            case 2:
                i = 1;
                break;
            case 3:
                i = 4;
                break;
            case 4:
                i = 3;
                break;
            case 5:
                i = 6;
                break;
            case 6:
                i = 5;
                break;
            case 7:
                i = 8;
                break;
            case 8:
                i = 7;
                break;
            default:
                i = 2;
                break;
        }
        this.f732a.setAttribute(ExifInterface.TAG_ORIENTATION, String.valueOf(i));
    }

    public void flipVertically() {
        int i;
        switch (getOrientation()) {
            case 2:
                i = 3;
                break;
            case 3:
                i = 2;
                break;
            case 4:
                i = 1;
                break;
            case 5:
                i = 8;
                break;
            case 6:
                i = 7;
                break;
            case 7:
                i = 6;
                break;
            case 8:
                i = 5;
                break;
            default:
                i = 4;
                break;
        }
        this.f732a.setAttribute(ExifInterface.TAG_ORIENTATION, String.valueOf(i));
    }

    public final long g(@Nullable String str, @Nullable String str2) {
        if (str == null && str2 == null) {
            return -1L;
        }
        if (str2 == null) {
            try {
                return b(str).getTime();
            } catch (ParseException unused) {
                return -1L;
            }
        } else if (str == null) {
            try {
                return d(str2).getTime();
            } catch (ParseException unused2) {
                return -1L;
            }
        } else {
            return f(str + HexStringBuilder.DEFAULT_SEPARATOR + str2);
        }
    }

    @Nullable
    public String getDescription() {
        return this.f732a.getAttribute(ExifInterface.TAG_IMAGE_DESCRIPTION);
    }

    public int getHeight() {
        return this.f732a.getAttributeInt(ExifInterface.TAG_IMAGE_LENGTH, 0);
    }

    public long getLastModifiedTimestamp() {
        long f2 = f(this.f732a.getAttribute(ExifInterface.TAG_DATETIME));
        if (f2 == -1) {
            return -1L;
        }
        String attribute = this.f732a.getAttribute(ExifInterface.TAG_SUBSEC_TIME);
        if (attribute != null) {
            try {
                long parseLong = Long.parseLong(attribute);
                while (parseLong > 1000) {
                    parseLong /= 10;
                }
                return f2 + parseLong;
            } catch (NumberFormatException unused) {
                return f2;
            }
        }
        return f2;
    }

    @Nullable
    public Location getLocation() {
        double a2;
        String attribute = this.f732a.getAttribute(ExifInterface.TAG_GPS_PROCESSING_METHOD);
        double[] latLong = this.f732a.getLatLong();
        double altitude = this.f732a.getAltitude(0.0d);
        double attributeDouble = this.f732a.getAttributeDouble(ExifInterface.TAG_GPS_SPEED, 0.0d);
        String attribute2 = this.f732a.getAttribute(ExifInterface.TAG_GPS_SPEED_REF);
        if (attribute2 == null) {
            attribute2 = "K";
        }
        long g = g(this.f732a.getAttribute(ExifInterface.TAG_GPS_DATESTAMP), this.f732a.getAttribute(ExifInterface.TAG_GPS_TIMESTAMP));
        if (latLong == null) {
            return null;
        }
        if (attribute == null) {
            attribute = c;
        }
        Location location = new Location(attribute);
        location.setLatitude(latLong[0]);
        location.setLongitude(latLong[1]);
        if (altitude != 0.0d) {
            location.setAltitude(altitude);
        }
        if (attributeDouble != 0.0d) {
            char c2 = 65535;
            int hashCode = attribute2.hashCode();
            if (hashCode != 75) {
                if (hashCode != 77) {
                    if (hashCode == 78 && attribute2.equals("N")) {
                        c2 = 1;
                    }
                } else if (attribute2.equals("M")) {
                    c2 = 0;
                }
            } else if (attribute2.equals("K")) {
                c2 = 2;
            }
            if (c2 == 0) {
                a2 = d.c(attributeDouble).a();
            } else if (c2 != 1) {
                a2 = d.a(attributeDouble).a();
            } else {
                a2 = d.b(attributeDouble).a();
            }
            location.setSpeed((float) a2);
        }
        if (g != -1) {
            location.setTime(g);
        }
        return location;
    }

    public int getOrientation() {
        return this.f732a.getAttributeInt(ExifInterface.TAG_ORIENTATION, 0);
    }

    public int getRotation() {
        switch (getOrientation()) {
            case 3:
            case 4:
                return 180;
            case 5:
                return DfuException.ERROR_READ_DEVICE_INFO_ERROR;
            case 6:
            case 7:
                return 90;
            case 8:
                return DfuException.ERROR_READ_DEVICE_INFO_ERROR;
            default:
                return 0;
        }
    }

    public long getTimestamp() {
        long f2 = f(this.f732a.getAttribute(ExifInterface.TAG_DATETIME_ORIGINAL));
        if (f2 == -1) {
            return -1L;
        }
        String attribute = this.f732a.getAttribute(ExifInterface.TAG_SUBSEC_TIME_ORIGINAL);
        if (attribute != null) {
            try {
                long parseLong = Long.parseLong(attribute);
                while (parseLong > 1000) {
                    parseLong /= 10;
                }
                return f2 + parseLong;
            } catch (NumberFormatException unused) {
                return f2;
            }
        }
        return f2;
    }

    public int getWidth() {
        return this.f732a.getAttributeInt(ExifInterface.TAG_IMAGE_WIDTH, 0);
    }

    public boolean isFlippedHorizontally() {
        return getOrientation() == 2;
    }

    public boolean isFlippedVertically() {
        int orientation = getOrientation();
        return orientation == 4 || orientation == 5 || orientation == 7;
    }

    public void removeLocation() {
        this.f732a.setAttribute(ExifInterface.TAG_GPS_PROCESSING_METHOD, null);
        this.f732a.setAttribute(ExifInterface.TAG_GPS_LATITUDE, null);
        this.f732a.setAttribute(ExifInterface.TAG_GPS_LATITUDE_REF, null);
        this.f732a.setAttribute(ExifInterface.TAG_GPS_LONGITUDE, null);
        this.f732a.setAttribute(ExifInterface.TAG_GPS_LONGITUDE_REF, null);
        this.f732a.setAttribute(ExifInterface.TAG_GPS_ALTITUDE, null);
        this.f732a.setAttribute(ExifInterface.TAG_GPS_ALTITUDE_REF, null);
        this.f732a.setAttribute(ExifInterface.TAG_GPS_SPEED, null);
        this.f732a.setAttribute(ExifInterface.TAG_GPS_SPEED_REF, null);
        this.f732a.setAttribute(ExifInterface.TAG_GPS_DATESTAMP, null);
        this.f732a.setAttribute(ExifInterface.TAG_GPS_TIMESTAMP, null);
    }

    public void removeTimestamp() {
        this.f732a.setAttribute(ExifInterface.TAG_DATETIME, null);
        this.f732a.setAttribute(ExifInterface.TAG_DATETIME_ORIGINAL, null);
        this.f732a.setAttribute(ExifInterface.TAG_DATETIME_DIGITIZED, null);
        this.f732a.setAttribute(ExifInterface.TAG_SUBSEC_TIME, null);
        this.f732a.setAttribute(ExifInterface.TAG_SUBSEC_TIME_ORIGINAL, null);
        this.f732a.setAttribute(ExifInterface.TAG_SUBSEC_TIME_DIGITIZED, null);
        this.b = true;
    }

    public void rotate(int i) {
        if (i % 90 != 0) {
            Logger.w(c, String.format(Locale.US, "Can only rotate in right angles (eg. 0, 90, 180, 270). %d is unsupported.", Integer.valueOf(i)));
            this.f732a.setAttribute(ExifInterface.TAG_ORIENTATION, String.valueOf(0));
            return;
        }
        int i2 = i % 360;
        int orientation = getOrientation();
        while (i2 < 0) {
            i2 += 90;
            switch (orientation) {
                case 2:
                    orientation = 5;
                    break;
                case 3:
                case 8:
                    orientation = 6;
                    break;
                case 4:
                    orientation = 7;
                    break;
                case 5:
                    orientation = 4;
                    break;
                case 6:
                    orientation = 1;
                    break;
                case 7:
                    orientation = 2;
                    break;
                default:
                    orientation = 8;
                    break;
            }
        }
        while (i2 > 0) {
            i2 -= 90;
            switch (orientation) {
                case 2:
                    orientation = 7;
                    break;
                case 3:
                    orientation = 8;
                    break;
                case 4:
                    orientation = 5;
                    break;
                case 5:
                    orientation = 2;
                    break;
                case 6:
                    orientation = 3;
                    break;
                case 7:
                    orientation = 4;
                    break;
                case 8:
                    orientation = 1;
                    break;
                default:
                    orientation = 6;
                    break;
            }
        }
        this.f732a.setAttribute(ExifInterface.TAG_ORIENTATION, String.valueOf(orientation));
    }

    public void save() throws IOException {
        if (!this.b) {
            a();
        }
        this.f732a.saveAttributes();
    }

    public void setDescription(@Nullable String str) {
        this.f732a.setAttribute(ExifInterface.TAG_IMAGE_DESCRIPTION, str);
    }

    public void setOrientation(int i) {
        this.f732a.setAttribute(ExifInterface.TAG_ORIENTATION, String.valueOf(i));
    }

    public String toString() {
        return String.format(Locale.ENGLISH, "Exif{width=%s, height=%s, rotation=%d, isFlippedVertically=%s, isFlippedHorizontally=%s, location=%s, timestamp=%s, description=%s}", Integer.valueOf(getWidth()), Integer.valueOf(getHeight()), Integer.valueOf(getRotation()), Boolean.valueOf(isFlippedVertically()), Boolean.valueOf(isFlippedHorizontally()), getLocation(), Long.valueOf(getTimestamp()), getDescription());
    }
}
