package com.mappls.sdk.navigation.util;

import androidx.annotation.Keep;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
@Keep
/* loaded from: classes11.dex */
public class SunriseSunset {
    private Date dateInput;
    private Date dateSunrise;
    private Date dateSunset;
    private double dfA;
    private double dfA0;
    private double dfA2;
    private double dfA5;
    private double dfC0;
    private double dfCosLat;
    private double dfD0;
    private double dfD1;
    private double dfD2;
    private double dfD5;
    private double dfDA;
    private double dfDD;
    private double dfH0;
    private double dfH1;
    private double dfH2;
    private int dfHourRise;
    private int dfHourSet;
    private double dfJ;
    private double dfJ3;
    private double dfK1;
    private double dfL0;
    private double dfL2;
    private double dfLat;
    private double dfLon;
    private int dfMinRise;
    private int dfMinSet;
    private double dfP;
    private double dfSinLat;
    private double dfT;
    private double dfT0;
    private double dfTT;
    private double dfTimeZone;
    private double dfV0;
    private double dfV1;
    private double dfV2;
    private double dfZenith;
    private int iCount;
    private int iDay;
    private int iJulian;
    private int iMonth;
    private int iSign;
    private int iYear;
    private boolean bSunriseToday = false;
    private boolean bSunsetToday = false;
    private boolean bSunUpAllDay = false;
    private boolean bSunDownAllDay = false;
    private boolean bDaytime = false;
    private boolean bSunrise = false;
    private boolean bSunset = false;
    private boolean bGregorian = false;
    private double dfAA1 = 0.0d;
    private double dfAA2 = 0.0d;
    private double dfDD1 = 0.0d;
    private double dfDD2 = 0.0d;

    public SunriseSunset(double d, double d2, Date date, TimeZone timeZone) {
        this.dfLat = d;
        this.dfLon = d2;
        this.dateInput = date;
        this.dfTimeZone = (timeZone.getOffset(date.getTime()) * 1.0d) / 3600000.0d;
        doCalculations();
    }

    private void doCalculations() {
        boolean z;
        double d;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(this.dateInput);
        this.iYear = calendar.get(1);
        this.iMonth = calendar.get(2) + 1;
        this.iDay = calendar.get(5);
        this.dfTimeZone = -(this.dfTimeZone / 24.0d);
        this.dfLon /= 360.0d;
        if (this.iYear >= 1583) {
            this.bGregorian = true;
        }
        this.dfJ = (this.iYear * 367.0d) + Math.floor((this.iMonth * 275.0d) / 9.0d) + (-Math.floor(((Math.floor((this.iMonth + 9.0d) / 12.0d) + this.iYear) * 7.0d) / 4.0d)) + this.iDay + 1721027.0d;
        double d2 = 1.0d;
        double d3 = 2.0d;
        if (this.bGregorian) {
            double d4 = this.iMonth - 9.0d;
            if (d4 < 0.0d) {
                this.iSign = -1;
            } else {
                this.iSign = 1;
            }
            double abs = Math.abs(d4);
            this.dfA = abs;
            double d5 = -Math.floor((Math.floor(Math.floor((Math.floor(abs / 7.0d) * this.iSign) + this.iYear) / 100.0d) + 1.0d) * 0.75d);
            this.dfJ3 = d5;
            this.dfJ = this.dfJ + d5 + 2.0d;
        }
        int i = ((int) this.dfJ) - 1;
        this.iJulian = i;
        double d6 = (i - 2451545.0d) + 0.5d;
        this.dfT = d6;
        this.dfTT = (d6 / 36525.0d) + 1.0d;
        double d7 = ((this.dfLon * 86400.0d) + ((this.dfTimeZone * 86636.6d) + (((d6 * 8640184.813d) / 36525.0d) + 24110.5d))) / 86400.0d;
        this.dfT0 = d7;
        this.dfT0 = (d7 - Math.floor(d7)) * 2.0d * 3.141592653589793d;
        this.dfT += this.dfTimeZone;
        this.iCount = 0;
        for (int i2 = 1; this.iCount <= i2; i2 = 1) {
            double d8 = (this.dfT * 0.00273790931d) + 0.779072d;
            double floor = (d8 - Math.floor(d8)) * 2.0d * 3.141592653589793d;
            double d9 = (this.dfT * 0.0027377785d) + 0.993126d;
            double floor2 = (d9 - Math.floor(d9)) * 2.0d * 3.141592653589793d;
            double sin = ((Math.sin(floor + floor2) * 0.00333d) + ((Math.sin(floor) * 0.39785d) - (Math.sin(floor - floor2) * 0.01d))) - ((Math.sin(floor) * 2.1E-4d) * this.dfTT);
            double d10 = floor * 2.0d;
            double cos = (Math.cos(floor) * 8.0E-5d) + ((1.0d - (Math.cos(floor2) * 0.03349d)) - (Math.cos(d10) * 1.4E-4d));
            double sin2 = (((((Math.sin(floor2) * 0.03211d) + ((-1.0E-4d) - (Math.sin(d10) * 0.04129d))) - (Math.sin(d10 - floor2) * 0.00104d)) - (Math.sin(d10 + floor2) * 3.5E-4d)) - ((Math.sin(floor2) * 8.0E-5d) * this.dfTT)) / Math.sqrt(cos - (sin * sin));
            this.dfA5 = Math.atan(sin2 / Math.sqrt(1.0d - (sin2 * sin2))) + floor;
            double sqrt = sin / Math.sqrt(cos);
            double atan = Math.atan(sqrt / Math.sqrt(1.0d - (sqrt * sqrt)));
            this.dfD5 = atan;
            int i3 = this.iCount;
            double d11 = this.dfA5;
            if (i3 == 0) {
                this.dfAA1 = d11;
                this.dfDD1 = atan;
            } else {
                this.dfAA2 = d11;
                this.dfDD2 = atan;
            }
            this.dfT += 1.0d;
            this.iCount = i3 + 1;
        }
        double d12 = this.dfAA2;
        if (d12 < this.dfAA1) {
            this.dfAA2 = d12 + 6.283185307179586d;
        }
        this.dfZenith = 1.5853349194640092d;
        this.dfSinLat = Math.sin((this.dfLat * 3.141592653589793d) / 180.0d);
        this.dfCosLat = Math.cos((this.dfLat * 3.141592653589793d) / 180.0d);
        double d13 = this.dfAA1;
        this.dfA0 = d13;
        double d14 = this.dfDD1;
        this.dfD0 = d14;
        this.dfDA = this.dfAA2 - d13;
        this.dfDD = this.dfDD2 - d14;
        this.dfK1 = 0.26251616834300473d;
        this.dfHourRise = 99;
        this.dfMinRise = 99;
        this.dfHourSet = 99;
        this.dfMinSet = 99;
        this.dfV0 = 0.0d;
        this.dfV2 = 0.0d;
        this.iCount = 0;
        while (true) {
            int i4 = this.iCount;
            if (i4 >= 24) {
                break;
            }
            double d15 = i4;
            this.dfC0 = d15;
            double d16 = (d15 + d2) / 24.0d;
            this.dfP = d16;
            double d17 = (this.dfDA * d16) + this.dfAA1;
            this.dfA2 = d17;
            double d18 = (d16 * this.dfDD) + this.dfDD1;
            this.dfD2 = d18;
            double d19 = this.dfT0;
            double d20 = this.dfK1;
            double d21 = (d15 * d20) + d19;
            this.dfL0 = d21;
            double d22 = d20 + d21;
            this.dfL2 = d22;
            double d23 = d21 - this.dfA0;
            this.dfH0 = d23;
            double d24 = d22 - d17;
            this.dfH2 = d24;
            this.dfH1 = (d24 + d23) / d3;
            double d25 = this.dfD0;
            this.dfD1 = (d18 + d25) / d3;
            if (i4 == 0) {
                this.dfV0 = ((Math.cos(this.dfH0) * (Math.cos(this.dfD0) * this.dfCosLat)) + (Math.sin(d25) * this.dfSinLat)) - Math.cos(this.dfZenith);
            } else {
                this.dfV0 = this.dfV2;
            }
            double cos2 = ((Math.cos(this.dfH2) * (Math.cos(this.dfD2) * this.dfCosLat)) + (Math.sin(this.dfD2) * this.dfSinLat)) - Math.cos(this.dfZenith);
            this.dfV2 = cos2;
            double d26 = this.dfV0;
            if ((d26 < 0.0d || cos2 < 0.0d) && (d26 >= 0.0d || cos2 >= 0.0d)) {
                double cos3 = ((Math.cos(this.dfH1) * (Math.cos(this.dfD1) * this.dfCosLat)) + (Math.sin(this.dfD1) * this.dfSinLat)) - Math.cos(this.dfZenith);
                this.dfV1 = cos3;
                double d27 = this.dfV2;
                double d28 = cos3 * 4.0d;
                double d29 = this.dfV0;
                double d30 = (d29 * d3) + ((d27 * d3) - d28);
                double d31 = (d28 - (3.0d * d29)) - d27;
                double d32 = (d31 * d31) - ((d30 * 4.0d) * d29);
                if (d32 >= 0.0d) {
                    double sqrt2 = Math.sqrt(d32);
                    this.bSunrise = false;
                    this.bSunset = false;
                    double d33 = this.dfV0;
                    if (d33 >= 0.0d || this.dfV2 <= 0.0d) {
                        z = true;
                    } else {
                        z = true;
                        this.bSunrise = true;
                        this.bSunriseToday = true;
                    }
                    if (d33 > 0.0d && this.dfV2 < 0.0d) {
                        this.bSunset = z;
                        this.bSunsetToday = z;
                    }
                    double d34 = d30 * d3;
                    double d35 = (sqrt2 - d31) / d34;
                    d = 1.0d;
                    if (d35 > 1.0d || d35 < 0.0d) {
                        d35 = ((-sqrt2) - d31) / d34;
                    }
                    if (this.bSunrise) {
                        double d36 = this.dfC0 + d35 + 0.008333333333333333d;
                        int i5 = (int) d36;
                        this.dfHourRise = i5;
                        this.dfMinRise = (int) ((d36 - i5) * 60.0d);
                    }
                    if (this.bSunset) {
                        double d37 = this.dfC0 + d35 + 0.008333333333333333d;
                        int i6 = (int) d37;
                        this.dfHourSet = i6;
                        this.dfMinSet = (int) ((d37 - i6) * 60.0d);
                    }
                    this.dfA0 = this.dfA2;
                    this.dfD0 = this.dfD2;
                    this.iCount++;
                    d2 = d;
                    d3 = 2.0d;
                }
            }
            d = 1.0d;
            this.dfA0 = this.dfA2;
            this.dfD0 = this.dfD2;
            this.iCount++;
            d2 = d;
            d3 = 2.0d;
        }
        boolean z2 = this.bSunriseToday;
        if (!z2 && !this.bSunsetToday) {
            if (this.dfV2 < 0.0d) {
                this.bSunDownAllDay = true;
            } else {
                this.bSunUpAllDay = true;
            }
        }
        if (z2) {
            Calendar calendar2 = Calendar.getInstance();
            calendar2.set(1, this.iYear);
            calendar2.set(2, this.iMonth - 1);
            calendar2.set(5, this.iDay);
            calendar2.set(11, this.dfHourRise);
            calendar2.set(12, this.dfMinRise);
            this.dateSunrise = calendar2.getTime();
        }
        if (this.bSunsetToday) {
            Calendar calendar3 = Calendar.getInstance();
            calendar3.set(1, this.iYear);
            calendar3.set(2, this.iMonth - 1);
            calendar3.set(5, this.iDay);
            calendar3.set(11, this.dfHourSet);
            calendar3.set(12, this.dfMinSet);
            this.dateSunset = calendar3.getTime();
        }
    }

    public Date getSunrise() {
        if (this.bSunriseToday) {
            return this.dateSunrise;
        }
        return null;
    }

    public Date getSunset() {
        if (this.bSunsetToday) {
            return this.dateSunset;
        }
        return null;
    }

    public boolean isDaytime() {
        boolean before;
        boolean z = this.bSunriseToday;
        boolean z2 = false;
        if (!z || !this.bSunsetToday) {
            if (this.bSunUpAllDay) {
                this.bDaytime = true;
            } else if (!this.bSunDownAllDay) {
                if (z) {
                    before = !this.dateInput.before(this.dateSunrise);
                } else if (this.bSunsetToday) {
                    before = this.dateInput.before(this.dateSunset);
                }
                this.bDaytime = before;
            }
            return this.bDaytime;
        } else if (!this.dateSunrise.before(this.dateSunset) ? this.dateInput.after(this.dateSunrise) || this.dateInput.equals(this.dateSunrise) || this.dateInput.before(this.dateSunset) : (this.dateInput.after(this.dateSunrise) || this.dateInput.equals(this.dateSunrise)) && this.dateInput.before(this.dateSunset)) {
            z2 = true;
        }
        this.bDaytime = z2;
        return this.bDaytime;
    }

    public boolean isSunDown() {
        return this.bSunDownAllDay;
    }

    public boolean isSunUp() {
        return this.bSunUpAllDay;
    }

    public boolean isSunrise() {
        return this.bSunriseToday;
    }

    public boolean isSunset() {
        return this.bSunsetToday;
    }
}
