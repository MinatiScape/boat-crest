package com.google.firebase.ml.vision.barcode.internal;

import android.graphics.Point;
import android.graphics.Rect;
import androidx.annotation.Nullable;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.firebase.ml.vision.barcode.FirebaseVisionBarcode;
import java.util.ArrayList;
/* loaded from: classes10.dex */
public final class zzf implements zzd {

    /* renamed from: a  reason: collision with root package name */
    public final Barcode f11434a;

    public zzf(Barcode barcode) {
        this.f11434a = barcode;
    }

    @Nullable
    public static FirebaseVisionBarcode.CalendarDateTime a(@Nullable Barcode.CalendarDateTime calendarDateTime) {
        if (calendarDateTime == null) {
            return null;
        }
        return new FirebaseVisionBarcode.CalendarDateTime(calendarDateTime.year, calendarDateTime.month, calendarDateTime.day, calendarDateTime.hours, calendarDateTime.minutes, calendarDateTime.seconds, calendarDateTime.isUtc, calendarDateTime.rawValue);
    }

    @Override // com.google.firebase.ml.vision.barcode.internal.zzd
    @Nullable
    public final Rect getBoundingBox() {
        return this.f11434a.getBoundingBox();
    }

    @Override // com.google.firebase.ml.vision.barcode.internal.zzd
    @Nullable
    public final FirebaseVisionBarcode.CalendarEvent getCalendarEvent() {
        Barcode.CalendarEvent calendarEvent = this.f11434a.calendarEvent;
        if (calendarEvent == null) {
            return null;
        }
        return new FirebaseVisionBarcode.CalendarEvent(calendarEvent.summary, calendarEvent.description, calendarEvent.location, calendarEvent.organizer, calendarEvent.status, a(calendarEvent.start), a(calendarEvent.end));
    }

    @Override // com.google.firebase.ml.vision.barcode.internal.zzd
    @Nullable
    public final FirebaseVisionBarcode.ContactInfo getContactInfo() {
        Barcode.ContactInfo contactInfo = this.f11434a.contactInfo;
        if (contactInfo == null) {
            return null;
        }
        Barcode.PersonName personName = contactInfo.name;
        FirebaseVisionBarcode.PersonName personName2 = personName != null ? new FirebaseVisionBarcode.PersonName(personName.formattedName, personName.pronunciation, personName.prefix, personName.first, personName.middle, personName.last, personName.suffix) : null;
        String str = contactInfo.organization;
        String str2 = contactInfo.title;
        Barcode.Phone[] phoneArr = contactInfo.phones;
        ArrayList arrayList = new ArrayList();
        if (phoneArr != null) {
            for (Barcode.Phone phone : phoneArr) {
                if (phone != null) {
                    arrayList.add(new FirebaseVisionBarcode.Phone(phone.number, phone.type));
                }
            }
        }
        Barcode.Email[] emailArr = contactInfo.emails;
        ArrayList arrayList2 = new ArrayList();
        if (emailArr != null) {
            for (Barcode.Email email : emailArr) {
                if (email != null) {
                    arrayList2.add(new FirebaseVisionBarcode.Email(email.type, email.address, email.subject, email.body));
                }
            }
        }
        String[] strArr = contactInfo.urls;
        Barcode.Address[] addressArr = contactInfo.addresses;
        ArrayList arrayList3 = new ArrayList();
        if (addressArr != null) {
            for (Barcode.Address address : addressArr) {
                if (address != null) {
                    arrayList3.add(new FirebaseVisionBarcode.Address(address.type, address.addressLines));
                }
            }
        }
        return new FirebaseVisionBarcode.ContactInfo(personName2, str, str2, arrayList, arrayList2, strArr, arrayList3);
    }

    @Override // com.google.firebase.ml.vision.barcode.internal.zzd
    @Nullable
    public final Point[] getCornerPoints() {
        return this.f11434a.cornerPoints;
    }

    @Override // com.google.firebase.ml.vision.barcode.internal.zzd
    @Nullable
    public final String getDisplayValue() {
        return this.f11434a.displayValue;
    }

    @Override // com.google.firebase.ml.vision.barcode.internal.zzd
    @Nullable
    public final FirebaseVisionBarcode.DriverLicense getDriverLicense() {
        Barcode.DriverLicense driverLicense = this.f11434a.driverLicense;
        if (driverLicense == null) {
            return null;
        }
        return new FirebaseVisionBarcode.DriverLicense(driverLicense.documentType, driverLicense.firstName, driverLicense.middleName, driverLicense.lastName, driverLicense.gender, driverLicense.addressStreet, driverLicense.addressCity, driverLicense.addressState, driverLicense.addressZip, driverLicense.licenseNumber, driverLicense.issueDate, driverLicense.expiryDate, driverLicense.birthDate, driverLicense.issuingCountry);
    }

    @Override // com.google.firebase.ml.vision.barcode.internal.zzd
    @Nullable
    public final FirebaseVisionBarcode.Email getEmail() {
        Barcode.Email email = this.f11434a.email;
        if (email != null) {
            return new FirebaseVisionBarcode.Email(email.type, email.address, email.subject, email.body);
        }
        return null;
    }

    @Override // com.google.firebase.ml.vision.barcode.internal.zzd
    public final int getFormat() {
        return this.f11434a.format;
    }

    @Override // com.google.firebase.ml.vision.barcode.internal.zzd
    @Nullable
    public final FirebaseVisionBarcode.GeoPoint getGeoPoint() {
        Barcode.GeoPoint geoPoint = this.f11434a.geoPoint;
        if (geoPoint != null) {
            return new FirebaseVisionBarcode.GeoPoint(geoPoint.lat, geoPoint.lng);
        }
        return null;
    }

    @Override // com.google.firebase.ml.vision.barcode.internal.zzd
    @Nullable
    public final FirebaseVisionBarcode.Phone getPhone() {
        Barcode.Phone phone = this.f11434a.phone;
        if (phone != null) {
            return new FirebaseVisionBarcode.Phone(phone.number, phone.type);
        }
        return null;
    }

    @Override // com.google.firebase.ml.vision.barcode.internal.zzd
    @Nullable
    public final byte[] getRawBytes() {
        return this.f11434a.rawBytes;
    }

    @Override // com.google.firebase.ml.vision.barcode.internal.zzd
    @Nullable
    public final String getRawValue() {
        return this.f11434a.rawValue;
    }

    @Override // com.google.firebase.ml.vision.barcode.internal.zzd
    @Nullable
    public final FirebaseVisionBarcode.Sms getSms() {
        Barcode.Sms sms = this.f11434a.sms;
        if (sms != null) {
            return new FirebaseVisionBarcode.Sms(sms.message, sms.phoneNumber);
        }
        return null;
    }

    @Override // com.google.firebase.ml.vision.barcode.internal.zzd
    @Nullable
    public final FirebaseVisionBarcode.UrlBookmark getUrl() {
        Barcode.UrlBookmark urlBookmark = this.f11434a.url;
        if (urlBookmark != null) {
            return new FirebaseVisionBarcode.UrlBookmark(urlBookmark.title, urlBookmark.url);
        }
        return null;
    }

    @Override // com.google.firebase.ml.vision.barcode.internal.zzd
    public final int getValueType() {
        return this.f11434a.valueFormat;
    }

    @Override // com.google.firebase.ml.vision.barcode.internal.zzd
    @Nullable
    public final FirebaseVisionBarcode.WiFi getWifi() {
        Barcode.WiFi wiFi = this.f11434a.wifi;
        if (wiFi != null) {
            return new FirebaseVisionBarcode.WiFi(wiFi.ssid, wiFi.password, wiFi.encryptionType);
        }
        return null;
    }
}
