package com.google.i18n.phonenumbers;

import java.io.Serializable;
import java.util.Objects;
/* loaded from: classes10.dex */
public final class Phonenumber {

    /* loaded from: classes10.dex */
    public static class PhoneNumber implements Serializable {
        private static final long serialVersionUID = 1;
        private boolean hasCountryCode;
        private boolean hasCountryCodeSource;
        private boolean hasExtension;
        private boolean hasItalianLeadingZero;
        private boolean hasNationalNumber;
        private boolean hasNumberOfLeadingZeros;
        private boolean hasPreferredDomesticCarrierCode;
        private boolean hasRawInput;
        private int countryCode_ = 0;
        private long nationalNumber_ = 0;
        private String extension_ = "";
        private boolean italianLeadingZero_ = false;
        private int numberOfLeadingZeros_ = 1;
        private String rawInput_ = "";
        private String preferredDomesticCarrierCode_ = "";
        private CountryCodeSource countryCodeSource_ = CountryCodeSource.FROM_NUMBER_WITH_PLUS_SIGN;

        /* loaded from: classes10.dex */
        public enum CountryCodeSource {
            FROM_NUMBER_WITH_PLUS_SIGN,
            FROM_NUMBER_WITH_IDD,
            FROM_NUMBER_WITHOUT_PLUS_SIGN,
            FROM_DEFAULT_COUNTRY
        }

        public final PhoneNumber clear() {
            clearCountryCode();
            clearNationalNumber();
            clearExtension();
            clearItalianLeadingZero();
            clearNumberOfLeadingZeros();
            clearRawInput();
            clearCountryCodeSource();
            clearPreferredDomesticCarrierCode();
            return this;
        }

        public PhoneNumber clearCountryCode() {
            this.hasCountryCode = false;
            this.countryCode_ = 0;
            return this;
        }

        public PhoneNumber clearCountryCodeSource() {
            this.hasCountryCodeSource = false;
            this.countryCodeSource_ = CountryCodeSource.FROM_NUMBER_WITH_PLUS_SIGN;
            return this;
        }

        public PhoneNumber clearExtension() {
            this.hasExtension = false;
            this.extension_ = "";
            return this;
        }

        public PhoneNumber clearItalianLeadingZero() {
            this.hasItalianLeadingZero = false;
            this.italianLeadingZero_ = false;
            return this;
        }

        public PhoneNumber clearNationalNumber() {
            this.hasNationalNumber = false;
            this.nationalNumber_ = 0L;
            return this;
        }

        public PhoneNumber clearNumberOfLeadingZeros() {
            this.hasNumberOfLeadingZeros = false;
            this.numberOfLeadingZeros_ = 1;
            return this;
        }

        public PhoneNumber clearPreferredDomesticCarrierCode() {
            this.hasPreferredDomesticCarrierCode = false;
            this.preferredDomesticCarrierCode_ = "";
            return this;
        }

        public PhoneNumber clearRawInput() {
            this.hasRawInput = false;
            this.rawInput_ = "";
            return this;
        }

        public boolean equals(Object obj) {
            return (obj instanceof PhoneNumber) && exactlySameAs((PhoneNumber) obj);
        }

        public boolean exactlySameAs(PhoneNumber phoneNumber) {
            if (phoneNumber == null) {
                return false;
            }
            if (this == phoneNumber) {
                return true;
            }
            return this.countryCode_ == phoneNumber.countryCode_ && this.nationalNumber_ == phoneNumber.nationalNumber_ && this.extension_.equals(phoneNumber.extension_) && this.italianLeadingZero_ == phoneNumber.italianLeadingZero_ && this.numberOfLeadingZeros_ == phoneNumber.numberOfLeadingZeros_ && this.rawInput_.equals(phoneNumber.rawInput_) && this.countryCodeSource_ == phoneNumber.countryCodeSource_ && this.preferredDomesticCarrierCode_.equals(phoneNumber.preferredDomesticCarrierCode_) && hasPreferredDomesticCarrierCode() == phoneNumber.hasPreferredDomesticCarrierCode();
        }

        public int getCountryCode() {
            return this.countryCode_;
        }

        public CountryCodeSource getCountryCodeSource() {
            return this.countryCodeSource_;
        }

        public String getExtension() {
            return this.extension_;
        }

        public long getNationalNumber() {
            return this.nationalNumber_;
        }

        public int getNumberOfLeadingZeros() {
            return this.numberOfLeadingZeros_;
        }

        public String getPreferredDomesticCarrierCode() {
            return this.preferredDomesticCarrierCode_;
        }

        public String getRawInput() {
            return this.rawInput_;
        }

        public boolean hasCountryCode() {
            return this.hasCountryCode;
        }

        public boolean hasCountryCodeSource() {
            return this.hasCountryCodeSource;
        }

        public boolean hasExtension() {
            return this.hasExtension;
        }

        public boolean hasItalianLeadingZero() {
            return this.hasItalianLeadingZero;
        }

        public boolean hasNationalNumber() {
            return this.hasNationalNumber;
        }

        public boolean hasNumberOfLeadingZeros() {
            return this.hasNumberOfLeadingZeros;
        }

        public boolean hasPreferredDomesticCarrierCode() {
            return this.hasPreferredDomesticCarrierCode;
        }

        public boolean hasRawInput() {
            return this.hasRawInput;
        }

        public int hashCode() {
            return ((((((((((((((((2173 + getCountryCode()) * 53) + Long.valueOf(getNationalNumber()).hashCode()) * 53) + getExtension().hashCode()) * 53) + (isItalianLeadingZero() ? 1231 : 1237)) * 53) + getNumberOfLeadingZeros()) * 53) + getRawInput().hashCode()) * 53) + getCountryCodeSource().hashCode()) * 53) + getPreferredDomesticCarrierCode().hashCode()) * 53) + (hasPreferredDomesticCarrierCode() ? 1231 : 1237);
        }

        public boolean isItalianLeadingZero() {
            return this.italianLeadingZero_;
        }

        public PhoneNumber mergeFrom(PhoneNumber phoneNumber) {
            if (phoneNumber.hasCountryCode()) {
                setCountryCode(phoneNumber.getCountryCode());
            }
            if (phoneNumber.hasNationalNumber()) {
                setNationalNumber(phoneNumber.getNationalNumber());
            }
            if (phoneNumber.hasExtension()) {
                setExtension(phoneNumber.getExtension());
            }
            if (phoneNumber.hasItalianLeadingZero()) {
                setItalianLeadingZero(phoneNumber.isItalianLeadingZero());
            }
            if (phoneNumber.hasNumberOfLeadingZeros()) {
                setNumberOfLeadingZeros(phoneNumber.getNumberOfLeadingZeros());
            }
            if (phoneNumber.hasRawInput()) {
                setRawInput(phoneNumber.getRawInput());
            }
            if (phoneNumber.hasCountryCodeSource()) {
                setCountryCodeSource(phoneNumber.getCountryCodeSource());
            }
            if (phoneNumber.hasPreferredDomesticCarrierCode()) {
                setPreferredDomesticCarrierCode(phoneNumber.getPreferredDomesticCarrierCode());
            }
            return this;
        }

        public PhoneNumber setCountryCode(int i) {
            this.hasCountryCode = true;
            this.countryCode_ = i;
            return this;
        }

        public PhoneNumber setCountryCodeSource(CountryCodeSource countryCodeSource) {
            Objects.requireNonNull(countryCodeSource);
            this.hasCountryCodeSource = true;
            this.countryCodeSource_ = countryCodeSource;
            return this;
        }

        public PhoneNumber setExtension(String str) {
            Objects.requireNonNull(str);
            this.hasExtension = true;
            this.extension_ = str;
            return this;
        }

        public PhoneNumber setItalianLeadingZero(boolean z) {
            this.hasItalianLeadingZero = true;
            this.italianLeadingZero_ = z;
            return this;
        }

        public PhoneNumber setNationalNumber(long j) {
            this.hasNationalNumber = true;
            this.nationalNumber_ = j;
            return this;
        }

        public PhoneNumber setNumberOfLeadingZeros(int i) {
            this.hasNumberOfLeadingZeros = true;
            this.numberOfLeadingZeros_ = i;
            return this;
        }

        public PhoneNumber setPreferredDomesticCarrierCode(String str) {
            Objects.requireNonNull(str);
            this.hasPreferredDomesticCarrierCode = true;
            this.preferredDomesticCarrierCode_ = str;
            return this;
        }

        public PhoneNumber setRawInput(String str) {
            Objects.requireNonNull(str);
            this.hasRawInput = true;
            this.rawInput_ = str;
            return this;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("Country Code: ");
            sb.append(this.countryCode_);
            sb.append(" National Number: ");
            sb.append(this.nationalNumber_);
            if (hasItalianLeadingZero() && isItalianLeadingZero()) {
                sb.append(" Leading Zero(s): true");
            }
            if (hasNumberOfLeadingZeros()) {
                sb.append(" Number of leading zeros: ");
                sb.append(this.numberOfLeadingZeros_);
            }
            if (hasExtension()) {
                sb.append(" Extension: ");
                sb.append(this.extension_);
            }
            if (hasCountryCodeSource()) {
                sb.append(" Country Code Source: ");
                sb.append(this.countryCodeSource_);
            }
            if (hasPreferredDomesticCarrierCode()) {
                sb.append(" Preferred Domestic Carrier Code: ");
                sb.append(this.preferredDomesticCarrierCode_);
            }
            return sb.toString();
        }
    }
}
