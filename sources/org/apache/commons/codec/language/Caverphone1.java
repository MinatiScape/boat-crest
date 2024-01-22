package org.apache.commons.codec.language;

import androidx.exifinterface.media.ExifInterface;
import com.clevertap.android.sdk.Constants;
import com.clevertap.android.sdk.product_config.CTProductConfigConstants;
import com.google.android.material.color.c;
import com.jstyle.blesdk1860.constant.BleConst;
import com.mappls.sdk.services.api.weather.WeatherCriteria;
import com.szabh.smable3.entity.BleDeviceInfo;
import java.util.Locale;
import org.jose4j.jwk.EllipticCurveJsonWebKey;
import org.jose4j.jwk.OctetSequenceJsonWebKey;
import org.jose4j.jwk.RsaJsonWebKey;
/* loaded from: classes12.dex */
public class Caverphone1 extends AbstractCaverphone {
    @Override // org.apache.commons.codec.StringEncoder
    public String encode(String str) {
        if (str == null || str.length() == 0) {
            return com.crrepa.i0.a.f7727a;
        }
        String replaceAll = str.toLowerCase(Locale.ENGLISH).replaceAll("[^a-z]", "").replaceAll("^cough", "cou2f").replaceAll("^rough", "rou2f").replaceAll("^tough", "tou2f").replaceAll("^enough", "enou2f").replaceAll("^gn", "2n").replaceAll("mb$", "m2").replaceAll("cq", "2q").replaceAll("ci", "si").replaceAll("ce", "se").replaceAll("cy", "sy").replaceAll("tch", "2ch").replaceAll(c.f10260a, OctetSequenceJsonWebKey.KEY_VALUE_MEMBER_NAME).replaceAll(RsaJsonWebKey.SECOND_PRIME_FACTOR_MEMBER_NAME, OctetSequenceJsonWebKey.KEY_VALUE_MEMBER_NAME).replaceAll("x", OctetSequenceJsonWebKey.KEY_VALUE_MEMBER_NAME).replaceAll(CTProductConfigConstants.PRODUCT_CONFIG_JSON_KEY_FOR_VALUE, "f").replaceAll("dg", "2g").replaceAll("tio", "sio").replaceAll("tia", "sia").replaceAll("d", RsaJsonWebKey.FACTOR_CRT_COEFFICIENT).replaceAll("ph", "fh").replaceAll("b", RsaJsonWebKey.FIRST_PRIME_FACTOR_MEMBER_NAME).replaceAll("sh", "s2").replaceAll("z", "s").replaceAll("^[aeiou]", ExifInterface.GPS_MEASUREMENT_IN_PROGRESS).replaceAll("[aeiou]", "3").replaceAll("3gh3", "3kh3").replaceAll("gh", BleConst.SetSedentaryReminder).replaceAll("g", OctetSequenceJsonWebKey.KEY_VALUE_MEMBER_NAME).replaceAll("s+", ExifInterface.LATITUDE_SOUTH).replaceAll("t+", ExifInterface.GPS_DIRECTION_TRUE).replaceAll("p+", "P").replaceAll("k+", "K").replaceAll("f+", WeatherCriteria.UNIT_FARENHEIT).replaceAll("m+", "M").replaceAll("n+", "N").replaceAll("w3", "W3").replaceAll("wy", "Wy").replaceAll("wh3", "Wh3").replaceAll("why", "Why").replaceAll(Constants.INAPP_WINDOW, "2").replaceAll("^h", ExifInterface.GPS_MEASUREMENT_IN_PROGRESS).replaceAll("h", "2").replaceAll("r3", "R3").replaceAll("ry", "Ry").replaceAll(RsaJsonWebKey.PRIME_FACTOR_OTHER_MEMBER_NAME, "2").replaceAll("l3", "L3").replaceAll("ly", "Ly").replaceAll("l", "2").replaceAll("j", EllipticCurveJsonWebKey.Y_MEMBER_NAME).replaceAll("y3", BleDeviceInfo.PROTOTYPE_Y3).replaceAll(EllipticCurveJsonWebKey.Y_MEMBER_NAME, "2").replaceAll("2", "").replaceAll("3", "");
        return (replaceAll + com.crrepa.i0.a.f7727a).substring(0, 6);
    }
}
