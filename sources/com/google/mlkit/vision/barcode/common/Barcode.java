package com.google.mlkit.vision.barcode.common;

import android.graphics.Matrix;
import android.graphics.Point;
import android.graphics.Rect;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
import com.google.mlkit.vision.barcode.common.internal.BarcodeSource;
import com.google.mlkit.vision.common.internal.CommonConvertUtils;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Arrays;
import java.util.List;
/* loaded from: classes10.dex */
public class Barcode {
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

    /* renamed from: a  reason: collision with root package name */
    public final BarcodeSource f11611a;
    @Nullable
    public final Rect b;
    @Nullable
    public final Point[] c;

    /* loaded from: classes10.dex */
    public static class Address {
        public static final int TYPE_HOME = 2;
        public static final int TYPE_UNKNOWN = 0;
        public static final int TYPE_WORK = 1;

        /* renamed from: a  reason: collision with root package name */
        public final int f11612a;
        public final String[] b;

        @Retention(RetentionPolicy.CLASS)
        /* loaded from: classes10.dex */
        public @interface AddressType {
        }

        @KeepForSdk
        public Address(int i, @NonNull String[] strArr) {
            this.f11612a = i;
            this.b = strArr;
        }

        @NonNull
        public String[] getAddressLines() {
            return this.b;
        }

        @AddressType
        public int getType() {
            return this.f11612a;
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
        public final int f11613a;
        public final int b;
        public final int c;
        public final int d;
        public final int e;
        public final int f;
        public final boolean g;
        @Nullable
        public final String h;

        @KeepForSdk
        public CalendarDateTime(int i, int i2, int i3, int i4, int i5, int i6, boolean z, @Nullable String str) {
            this.f11613a = i;
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
            return this.f11613a;
        }

        public boolean isUtc() {
            return this.g;
        }
    }

    /* loaded from: classes10.dex */
    public static class CalendarEvent {
        @Nullable

        /* renamed from: a  reason: collision with root package name */
        public final String f11614a;
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

        @KeepForSdk
        public CalendarEvent(@Nullable String str, @Nullable String str2, @Nullable String str3, @Nullable String str4, @Nullable String str5, @Nullable CalendarDateTime calendarDateTime, @Nullable CalendarDateTime calendarDateTime2) {
            this.f11614a = str;
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
            return this.f11614a;
        }
    }

    /* loaded from: classes10.dex */
    public static class ContactInfo {
        @Nullable

        /* renamed from: a  reason: collision with root package name */
        public final PersonName f11615a;
        @Nullable
        public final String b;
        @Nullable
        public final String c;
        public final List d;
        public final List e;
        public final List f;
        public final List g;

        @KeepForSdk
        public ContactInfo(@Nullable PersonName personName, @Nullable String str, @Nullable String str2, @NonNull List<Phone> list, @NonNull List<Email> list2, @NonNull List<String> list3, @NonNull List<Address> list4) {
            this.f11615a = personName;
            this.b = str;
            this.c = str2;
            this.d = list;
            this.e = list2;
            this.f = list3;
            this.g = list4;
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
            return this.f11615a;
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

        @NonNull
        public List<String> getUrls() {
            return this.f;
        }
    }

    /* loaded from: classes10.dex */
    public static class DriverLicense {
        @Nullable

        /* renamed from: a  reason: collision with root package name */
        public final String f11616a;
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

        @KeepForSdk
        public DriverLicense(@Nullable String str, @Nullable String str2, @Nullable String str3, @Nullable String str4, @Nullable String str5, @Nullable String str6, @Nullable String str7, @Nullable String str8, @Nullable String str9, @Nullable String str10, @Nullable String str11, @Nullable String str12, @Nullable String str13, @Nullable String str14) {
            this.f11616a = str;
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
            return this.f11616a;
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
        public final int f11617a;
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

        @KeepForSdk
        public Email(int i, @Nullable String str, @Nullable String str2, @Nullable String str3) {
            this.f11617a = i;
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
            return this.f11617a;
        }
    }

    /* loaded from: classes10.dex */
    public static class GeoPoint {

        /* renamed from: a  reason: collision with root package name */
        public final double f11618a;
        public final double b;

        @KeepForSdk
        public GeoPoint(double d, double d2) {
            this.f11618a = d;
            this.b = d2;
        }

        public double getLat() {
            return this.f11618a;
        }

        public double getLng() {
            return this.b;
        }
    }

    /* loaded from: classes10.dex */
    public static class PersonName {
        @Nullable

        /* renamed from: a  reason: collision with root package name */
        public final String f11619a;
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

        @KeepForSdk
        public PersonName(@Nullable String str, @Nullable String str2, @Nullable String str3, @Nullable String str4, @Nullable String str5, @Nullable String str6, @Nullable String str7) {
            this.f11619a = str;
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
            return this.f11619a;
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
        public final String f11620a;
        public final int b;

        @Retention(RetentionPolicy.CLASS)
        /* loaded from: classes10.dex */
        public @interface FormatType {
        }

        @KeepForSdk
        public Phone(@Nullable String str, int i) {
            this.f11620a = str;
            this.b = i;
        }

        @Nullable
        public String getNumber() {
            return this.f11620a;
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
        public final String f11621a;
        @Nullable
        public final String b;

        @KeepForSdk
        public Sms(@Nullable String str, @Nullable String str2) {
            this.f11621a = str;
            this.b = str2;
        }

        @Nullable
        public String getMessage() {
            return this.f11621a;
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
        public final String f11622a;
        @Nullable
        public final String b;

        @KeepForSdk
        public UrlBookmark(@Nullable String str, @Nullable String str2) {
            this.f11622a = str;
            this.b = str2;
        }

        @Nullable
        public String getTitle() {
            return this.f11622a;
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
        public final String f11623a;
        @Nullable
        public final String b;
        public final int c;

        @Retention(RetentionPolicy.CLASS)
        /* loaded from: classes10.dex */
        public @interface EncryptionType {
        }

        @KeepForSdk
        public WiFi(@Nullable String str, @Nullable String str2, int i) {
            this.f11623a = str;
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
            return this.f11623a;
        }
    }

    @KeepForSdk
    public Barcode(@NonNull BarcodeSource barcodeSource) {
        this(barcodeSource, null);
    }

    @Nullable
    public Rect getBoundingBox() {
        return this.b;
    }

    @Nullable
    public CalendarEvent getCalendarEvent() {
        return this.f11611a.getCalendarEvent();
    }

    @Nullable
    public ContactInfo getContactInfo() {
        return this.f11611a.getContactInfo();
    }

    @Nullable
    public Point[] getCornerPoints() {
        return this.c;
    }

    @Nullable
    public String getDisplayValue() {
        return this.f11611a.getDisplayValue();
    }

    @Nullable
    public DriverLicense getDriverLicense() {
        return this.f11611a.getDriverLicense();
    }

    @Nullable
    public Email getEmail() {
        return this.f11611a.getEmail();
    }

    @BarcodeFormat
    public int getFormat() {
        int format = this.f11611a.getFormat();
        if (format > 4096 || format == 0) {
            return -1;
        }
        return format;
    }

    @Nullable
    public GeoPoint getGeoPoint() {
        return this.f11611a.getGeoPoint();
    }

    @Nullable
    public Phone getPhone() {
        return this.f11611a.getPhone();
    }

    @Nullable
    public byte[] getRawBytes() {
        byte[] rawBytes = this.f11611a.getRawBytes();
        if (rawBytes != null) {
            return Arrays.copyOf(rawBytes, rawBytes.length);
        }
        return null;
    }

    @Nullable
    public String getRawValue() {
        return this.f11611a.getRawValue();
    }

    @Nullable
    public Sms getSms() {
        return this.f11611a.getSms();
    }

    @Nullable
    public UrlBookmark getUrl() {
        return this.f11611a.getUrl();
    }

    @BarcodeValueType
    public int getValueType() {
        return this.f11611a.getValueType();
    }

    @Nullable
    public WiFi getWifi() {
        return this.f11611a.getWifi();
    }

    @KeepForSdk
    public Barcode(@NonNull BarcodeSource barcodeSource, @Nullable Matrix matrix) {
        this.f11611a = (BarcodeSource) Preconditions.checkNotNull(barcodeSource);
        Rect boundingBox = barcodeSource.getBoundingBox();
        if (boundingBox != null && matrix != null) {
            CommonConvertUtils.transformRect(boundingBox, matrix);
        }
        this.b = boundingBox;
        Point[] cornerPoints = barcodeSource.getCornerPoints();
        if (cornerPoints != null && matrix != null) {
            CommonConvertUtils.transformPointArray(cornerPoints, matrix);
        }
        this.c = cornerPoints;
    }
}
