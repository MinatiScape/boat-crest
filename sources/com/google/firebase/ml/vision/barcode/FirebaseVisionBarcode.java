package com.google.firebase.ml.vision.barcode;

import android.graphics.Point;
import android.graphics.Rect;
import android.util.SparseArray;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.firebase_ml.zzns;
import com.google.firebase.ml.vision.barcode.internal.zzd;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Arrays;
import java.util.List;
/* loaded from: classes10.dex */
public class FirebaseVisionBarcode {
    public static final int FORMAT_ALL_FORMATS = 0;
    public static final int FORMAT_AZTEC = 4096;
    public static final int FORMAT_CODABAR = 8;
    public static final int FORMAT_CODE_128 = 1;
    public static final int FORMAT_CODE_39 = 2;
    public static final int FORMAT_CODE_93 = 4;
    public static final int FORMAT_DATA_MATRIX = 16;
    public static final int FORMAT_EAN_13 = 32;
    public static final int FORMAT_EAN_8 = 64;
    public static final int FORMAT_ITF = 128;
    public static final int FORMAT_PDF417 = 2048;
    public static final int FORMAT_QR_CODE = 256;
    public static final int FORMAT_UNKNOWN = -1;
    public static final int FORMAT_UPC_A = 512;
    public static final int FORMAT_UPC_E = 1024;
    public static final int TYPE_CALENDAR_EVENT = 11;
    public static final int TYPE_CONTACT_INFO = 1;
    public static final int TYPE_DRIVER_LICENSE = 12;
    public static final int TYPE_EMAIL = 2;
    public static final int TYPE_GEO = 10;
    public static final int TYPE_ISBN = 3;
    public static final int TYPE_PHONE = 4;
    public static final int TYPE_PRODUCT = 5;
    public static final int TYPE_SMS = 6;
    public static final int TYPE_TEXT = 7;
    public static final int TYPE_UNKNOWN = 0;
    public static final int TYPE_URL = 8;
    public static final int TYPE_WIFI = 9;
    public static final SparseArray<zzns.zzam.zza> b;
    public static final SparseArray<zzns.zzam.zzb> c;

    /* renamed from: a  reason: collision with root package name */
    public final zzd f11417a;

    /* loaded from: classes10.dex */
    public static class Address {
        public static final int TYPE_HOME = 2;
        public static final int TYPE_UNKNOWN = 0;
        public static final int TYPE_WORK = 1;

        /* renamed from: a  reason: collision with root package name */
        public final int f11418a;
        public final String[] b;

        @Retention(RetentionPolicy.CLASS)
        /* loaded from: classes10.dex */
        public @interface AddressType {
        }

        public Address(int i, String[] strArr) {
            this.f11418a = i;
            this.b = strArr;
        }

        @NonNull
        public String[] getAddressLines() {
            return this.b;
        }

        @AddressType
        public int getType() {
            return this.f11418a;
        }
    }

    @Retention(RetentionPolicy.CLASS)
    /* loaded from: classes10.dex */
    public @interface BarcodeFormat {
    }

    @Retention(RetentionPolicy.CLASS)
    /* loaded from: classes10.dex */
    public @interface BarcodeValueType {
    }

    /* loaded from: classes10.dex */
    public static class CalendarDateTime {

        /* renamed from: a  reason: collision with root package name */
        public final int f11419a;
        public final int b;
        public final int c;
        public final int d;
        public final int e;
        public final int f;
        public final boolean g;
        @Nullable
        public final String h;

        public CalendarDateTime(int i, int i2, int i3, int i4, int i5, int i6, boolean z, @Nullable String str) {
            this.f11419a = i;
            this.b = i2;
            this.c = i3;
            this.d = i4;
            this.e = i5;
            this.f = i6;
            this.g = z;
            this.h = str;
        }

        public int getDay() {
            return this.c;
        }

        public int getHours() {
            return this.d;
        }

        public int getMinutes() {
            return this.e;
        }

        public int getMonth() {
            return this.b;
        }

        @Nullable
        public String getRawValue() {
            return this.h;
        }

        public int getSeconds() {
            return this.f;
        }

        public int getYear() {
            return this.f11419a;
        }

        public boolean isUtc() {
            return this.g;
        }
    }

    /* loaded from: classes10.dex */
    public static class CalendarEvent {
        @Nullable

        /* renamed from: a  reason: collision with root package name */
        public final String f11420a;
        @Nullable
        public final String b;
        @Nullable
        public final String c;
        @Nullable
        public final String d;
        @Nullable
        public final String e;
        @Nullable
        public final CalendarDateTime f;
        @Nullable
        public final CalendarDateTime g;

        public CalendarEvent(@Nullable String str, @Nullable String str2, @Nullable String str3, @Nullable String str4, @Nullable String str5, @Nullable CalendarDateTime calendarDateTime, @Nullable CalendarDateTime calendarDateTime2) {
            this.f11420a = str;
            this.b = str2;
            this.c = str3;
            this.d = str4;
            this.e = str5;
            this.f = calendarDateTime;
            this.g = calendarDateTime2;
        }

        @Nullable
        public String getDescription() {
            return this.b;
        }

        @Nullable
        public CalendarDateTime getEnd() {
            return this.g;
        }

        @Nullable
        public String getLocation() {
            return this.c;
        }

        @Nullable
        public String getOrganizer() {
            return this.d;
        }

        @Nullable
        public CalendarDateTime getStart() {
            return this.f;
        }

        @Nullable
        public String getStatus() {
            return this.e;
        }

        @Nullable
        public String getSummary() {
            return this.f11420a;
        }
    }

    /* loaded from: classes10.dex */
    public static class ContactInfo {
        @Nullable

        /* renamed from: a  reason: collision with root package name */
        public final PersonName f11421a;
        @Nullable
        public final String b;
        @Nullable
        public final String c;
        public final List<Phone> d;
        public final List<Email> e;
        @Nullable
        public final String[] f;
        public final List<Address> g;

        public ContactInfo(@Nullable PersonName personName, @Nullable String str, @Nullable String str2, @NonNull List<Phone> list, @NonNull List<Email> list2, @Nullable String[] strArr, @NonNull List<Address> list3) {
            this.f11421a = personName;
            this.b = str;
            this.c = str2;
            this.d = list;
            this.e = list2;
            this.f = strArr;
            this.g = list3;
        }

        @NonNull
        public List<Address> getAddresses() {
            return this.g;
        }

        @NonNull
        public List<Email> getEmails() {
            return this.e;
        }

        @Nullable
        public PersonName getName() {
            return this.f11421a;
        }

        @Nullable
        public String getOrganization() {
            return this.b;
        }

        @NonNull
        public List<Phone> getPhones() {
            return this.d;
        }

        @Nullable
        public String getTitle() {
            return this.c;
        }

        @Nullable
        public String[] getUrls() {
            return this.f;
        }
    }

    /* loaded from: classes10.dex */
    public static class DriverLicense {
        @Nullable

        /* renamed from: a  reason: collision with root package name */
        public final String f11422a;
        @Nullable
        public final String b;
        @Nullable
        public final String c;
        @Nullable
        public final String d;
        @Nullable
        public final String e;
        @Nullable
        public final String f;
        @Nullable
        public final String g;
        @Nullable
        public final String h;
        @Nullable
        public final String i;
        @Nullable
        public final String j;
        @Nullable
        public final String k;
        @Nullable
        public final String l;
        @Nullable
        public final String m;
        @Nullable
        public final String n;

        public DriverLicense(@Nullable String str, @Nullable String str2, @Nullable String str3, @Nullable String str4, @Nullable String str5, @Nullable String str6, @Nullable String str7, @Nullable String str8, @Nullable String str9, @Nullable String str10, @Nullable String str11, @Nullable String str12, @Nullable String str13, @Nullable String str14) {
            this.f11422a = str;
            this.b = str2;
            this.c = str3;
            this.d = str4;
            this.e = str5;
            this.f = str6;
            this.g = str7;
            this.h = str8;
            this.i = str9;
            this.j = str10;
            this.k = str11;
            this.l = str12;
            this.m = str13;
            this.n = str14;
        }

        @Nullable
        public String getAddressCity() {
            return this.g;
        }

        @Nullable
        public String getAddressState() {
            return this.h;
        }

        @Nullable
        public String getAddressStreet() {
            return this.f;
        }

        @Nullable
        public String getAddressZip() {
            return this.i;
        }

        @Nullable
        public String getBirthDate() {
            return this.m;
        }

        @Nullable
        public String getDocumentType() {
            return this.f11422a;
        }

        @Nullable
        public String getExpiryDate() {
            return this.l;
        }

        @Nullable
        public String getFirstName() {
            return this.b;
        }

        @Nullable
        public String getGender() {
            return this.e;
        }

        @Nullable
        public String getIssueDate() {
            return this.k;
        }

        @Nullable
        public String getIssuingCountry() {
            return this.n;
        }

        @Nullable
        public String getLastName() {
            return this.d;
        }

        @Nullable
        public String getLicenseNumber() {
            return this.j;
        }

        @Nullable
        public String getMiddleName() {
            return this.c;
        }
    }

    /* loaded from: classes10.dex */
    public static class Email {
        public static final int TYPE_HOME = 2;
        public static final int TYPE_UNKNOWN = 0;
        public static final int TYPE_WORK = 1;

        /* renamed from: a  reason: collision with root package name */
        public final int f11423a;
        @Nullable
        public final String b;
        @Nullable
        public final String c;
        @Nullable
        public final String d;

        @Retention(RetentionPolicy.CLASS)
        /* loaded from: classes10.dex */
        public @interface FormatType {
        }

        public Email(int i, @Nullable String str, @Nullable String str2, @Nullable String str3) {
            this.f11423a = i;
            this.b = str;
            this.c = str2;
            this.d = str3;
        }

        @Nullable
        public String getAddress() {
            return this.b;
        }

        @Nullable
        public String getBody() {
            return this.d;
        }

        @Nullable
        public String getSubject() {
            return this.c;
        }

        @FormatType
        public int getType() {
            return this.f11423a;
        }
    }

    /* loaded from: classes10.dex */
    public static class GeoPoint {

        /* renamed from: a  reason: collision with root package name */
        public final double f11424a;
        public final double b;

        public GeoPoint(double d, double d2) {
            this.f11424a = d;
            this.b = d2;
        }

        public double getLat() {
            return this.f11424a;
        }

        public double getLng() {
            return this.b;
        }
    }

    /* loaded from: classes10.dex */
    public static class PersonName {
        @Nullable

        /* renamed from: a  reason: collision with root package name */
        public final String f11425a;
        @Nullable
        public final String b;
        @Nullable
        public final String c;
        @Nullable
        public final String d;
        @Nullable
        public final String e;
        @Nullable
        public final String f;
        @Nullable
        public final String g;

        public PersonName(@Nullable String str, @Nullable String str2, @Nullable String str3, @Nullable String str4, @Nullable String str5, @Nullable String str6, @Nullable String str7) {
            this.f11425a = str;
            this.b = str2;
            this.c = str3;
            this.d = str4;
            this.e = str5;
            this.f = str6;
            this.g = str7;
        }

        @Nullable
        public String getFirst() {
            return this.d;
        }

        @Nullable
        public String getFormattedName() {
            return this.f11425a;
        }

        @Nullable
        public String getLast() {
            return this.f;
        }

        @Nullable
        public String getMiddle() {
            return this.e;
        }

        @Nullable
        public String getPrefix() {
            return this.c;
        }

        @Nullable
        public String getPronunciation() {
            return this.b;
        }

        @Nullable
        public String getSuffix() {
            return this.g;
        }
    }

    /* loaded from: classes10.dex */
    public static class Phone {
        public static final int TYPE_FAX = 3;
        public static final int TYPE_HOME = 2;
        public static final int TYPE_MOBILE = 4;
        public static final int TYPE_UNKNOWN = 0;
        public static final int TYPE_WORK = 1;
        @Nullable

        /* renamed from: a  reason: collision with root package name */
        public final String f11426a;
        public final int b;

        @Retention(RetentionPolicy.CLASS)
        /* loaded from: classes10.dex */
        public @interface FormatType {
        }

        public Phone(@Nullable String str, int i) {
            this.f11426a = str;
            this.b = i;
        }

        @Nullable
        public String getNumber() {
            return this.f11426a;
        }

        @FormatType
        public int getType() {
            return this.b;
        }
    }

    /* loaded from: classes10.dex */
    public static class Sms {
        @Nullable

        /* renamed from: a  reason: collision with root package name */
        public final String f11427a;
        @Nullable
        public final String b;

        public Sms(@Nullable String str, @Nullable String str2) {
            this.f11427a = str;
            this.b = str2;
        }

        @Nullable
        public String getMessage() {
            return this.f11427a;
        }

        @Nullable
        public String getPhoneNumber() {
            return this.b;
        }
    }

    /* loaded from: classes10.dex */
    public static class UrlBookmark {
        @Nullable

        /* renamed from: a  reason: collision with root package name */
        public final String f11428a;
        @Nullable
        public final String b;

        public UrlBookmark(@Nullable String str, @Nullable String str2) {
            this.f11428a = str;
            this.b = str2;
        }

        @Nullable
        public String getTitle() {
            return this.f11428a;
        }

        @Nullable
        public String getUrl() {
            return this.b;
        }
    }

    /* loaded from: classes10.dex */
    public static class WiFi {
        public static final int TYPE_OPEN = 1;
        public static final int TYPE_WEP = 3;
        public static final int TYPE_WPA = 2;
        @Nullable

        /* renamed from: a  reason: collision with root package name */
        public final String f11429a;
        @Nullable
        public final String b;
        public final int c;

        @Retention(RetentionPolicy.CLASS)
        /* loaded from: classes10.dex */
        public @interface EncryptionType {
        }

        public WiFi(@Nullable String str, @Nullable String str2, int i) {
            this.f11429a = str;
            this.b = str2;
            this.c = i;
        }

        @EncryptionType
        public int getEncryptionType() {
            return this.c;
        }

        @Nullable
        public String getPassword() {
            return this.b;
        }

        @Nullable
        public String getSsid() {
            return this.f11429a;
        }
    }

    static {
        SparseArray<zzns.zzam.zza> sparseArray = new SparseArray<>();
        b = sparseArray;
        SparseArray<zzns.zzam.zzb> sparseArray2 = new SparseArray<>();
        c = sparseArray2;
        sparseArray.put(-1, zzns.zzam.zza.FORMAT_UNKNOWN);
        sparseArray.put(1, zzns.zzam.zza.FORMAT_CODE_128);
        sparseArray.put(2, zzns.zzam.zza.FORMAT_CODE_39);
        sparseArray.put(4, zzns.zzam.zza.FORMAT_CODE_93);
        sparseArray.put(8, zzns.zzam.zza.FORMAT_CODABAR);
        sparseArray.put(16, zzns.zzam.zza.FORMAT_DATA_MATRIX);
        sparseArray.put(32, zzns.zzam.zza.FORMAT_EAN_13);
        sparseArray.put(64, zzns.zzam.zza.FORMAT_EAN_8);
        sparseArray.put(128, zzns.zzam.zza.FORMAT_ITF);
        sparseArray.put(256, zzns.zzam.zza.FORMAT_QR_CODE);
        sparseArray.put(512, zzns.zzam.zza.FORMAT_UPC_A);
        sparseArray.put(1024, zzns.zzam.zza.FORMAT_UPC_E);
        sparseArray.put(2048, zzns.zzam.zza.FORMAT_PDF417);
        sparseArray.put(4096, zzns.zzam.zza.FORMAT_AZTEC);
        sparseArray2.put(0, zzns.zzam.zzb.TYPE_UNKNOWN);
        sparseArray2.put(1, zzns.zzam.zzb.TYPE_CONTACT_INFO);
        sparseArray2.put(2, zzns.zzam.zzb.TYPE_EMAIL);
        sparseArray2.put(3, zzns.zzam.zzb.TYPE_ISBN);
        sparseArray2.put(4, zzns.zzam.zzb.TYPE_PHONE);
        sparseArray2.put(5, zzns.zzam.zzb.TYPE_PRODUCT);
        sparseArray2.put(6, zzns.zzam.zzb.TYPE_SMS);
        sparseArray2.put(7, zzns.zzam.zzb.TYPE_TEXT);
        sparseArray2.put(8, zzns.zzam.zzb.TYPE_URL);
        sparseArray2.put(9, zzns.zzam.zzb.TYPE_WIFI);
        sparseArray2.put(10, zzns.zzam.zzb.TYPE_GEO);
        sparseArray2.put(11, zzns.zzam.zzb.TYPE_CALENDAR_EVENT);
        sparseArray2.put(12, zzns.zzam.zzb.TYPE_DRIVER_LICENSE);
    }

    public FirebaseVisionBarcode(@NonNull zzd zzdVar) {
        this.f11417a = (zzd) Preconditions.checkNotNull(zzdVar);
    }

    @Nullable
    public Rect getBoundingBox() {
        return this.f11417a.getBoundingBox();
    }

    @Nullable
    public CalendarEvent getCalendarEvent() {
        return this.f11417a.getCalendarEvent();
    }

    @Nullable
    public ContactInfo getContactInfo() {
        return this.f11417a.getContactInfo();
    }

    @Nullable
    public Point[] getCornerPoints() {
        return this.f11417a.getCornerPoints();
    }

    @Nullable
    public String getDisplayValue() {
        return this.f11417a.getDisplayValue();
    }

    @Nullable
    public DriverLicense getDriverLicense() {
        return this.f11417a.getDriverLicense();
    }

    @Nullable
    public Email getEmail() {
        return this.f11417a.getEmail();
    }

    @BarcodeFormat
    public int getFormat() {
        int format = this.f11417a.getFormat();
        if (format > 4096 || format == 0) {
            return -1;
        }
        return format;
    }

    @Nullable
    public GeoPoint getGeoPoint() {
        return this.f11417a.getGeoPoint();
    }

    @Nullable
    public Phone getPhone() {
        return this.f11417a.getPhone();
    }

    @Nullable
    public byte[] getRawBytes() {
        byte[] rawBytes = this.f11417a.getRawBytes();
        if (rawBytes != null) {
            return Arrays.copyOf(rawBytes, rawBytes.length);
        }
        return null;
    }

    @Nullable
    public String getRawValue() {
        return this.f11417a.getRawValue();
    }

    @Nullable
    public Sms getSms() {
        return this.f11417a.getSms();
    }

    @Nullable
    public UrlBookmark getUrl() {
        return this.f11417a.getUrl();
    }

    @BarcodeValueType
    public int getValueType() {
        return this.f11417a.getValueType();
    }

    @Nullable
    public WiFi getWifi() {
        return this.f11417a.getWifi();
    }

    public final zzns.zzam.zza zzqf() {
        zzns.zzam.zza zzaVar = b.get(getFormat());
        return zzaVar == null ? zzns.zzam.zza.FORMAT_UNKNOWN : zzaVar;
    }

    public final zzns.zzam.zzb zzqg() {
        zzns.zzam.zzb zzbVar = c.get(getValueType());
        return zzbVar == null ? zzns.zzam.zzb.TYPE_UNKNOWN : zzbVar;
    }
}
