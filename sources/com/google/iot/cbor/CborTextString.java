package com.google.iot.cbor;

import java.net.URI;
import java.net.URISyntaxException;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.json.JSONObject;
/* loaded from: classes10.dex */
public abstract class CborTextString extends CborObject {
    public static CborTextString create(byte[] bArr, int i, int i2, int i3) {
        return new i(bArr, i, i2, i3);
    }

    public abstract byte[] byteArrayValue();

    @Override // com.google.iot.cbor.CborObject
    public CborTextString copy() {
        return this;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof CborTextString) {
            CborTextString cborTextString = (CborTextString) obj;
            return getTag() == cborTextString.getTag() && stringValue().equals(cborTextString.stringValue());
        }
        return false;
    }

    @Override // com.google.iot.cbor.CborObject
    public int getAdditionalInformation() {
        return CborInteger.a(byteArrayValue().length);
    }

    @Override // com.google.iot.cbor.CborObject
    public final int getMajorType() {
        return 3;
    }

    public int hashCode() {
        return ((getTag() + 1) * 1337) + stringValue().hashCode();
    }

    @Override // com.google.iot.cbor.CborObject
    public final boolean isValidJson() {
        return true;
    }

    public abstract String stringValue();

    @Override // com.google.iot.cbor.CborObject
    public final String toJsonString() {
        return JSONObject.quote(stringValue()).replaceAll("\\\\/", MqttTopic.TOPIC_LEVEL_SEPARATOR);
    }

    @Override // com.google.iot.cbor.CborObject
    public String toString(int i) {
        return toString();
    }

    public static CborTextString create(byte[] bArr, int i, int i2) {
        return new i(bArr, i, i2, -1);
    }

    @Override // com.google.iot.cbor.CborObject
    public String toJavaObject() {
        return stringValue();
    }

    @Override // com.google.iot.cbor.CborObject
    public String toString() {
        String jsonString = toJsonString();
        int tag = getTag();
        if (tag == -1) {
            return jsonString;
        }
        return tag + "(" + jsonString + ")";
    }

    public static CborTextString create(byte[] bArr) {
        return create(bArr, 0, bArr.length);
    }

    @Override // com.google.iot.cbor.CborObject
    public <T> T toJavaObject(Class<T> cls) throws CborConversionException {
        CborConversionException cborConversionException;
        if (getTag() == 32 && cls.isAssignableFrom(URI.class)) {
            try {
                return cls.cast(new URI(stringValue()));
            } catch (URISyntaxException e) {
                cborConversionException = new CborConversionException(e);
            }
        } else {
            cborConversionException = null;
        }
        if (cls.isAssignableFrom(String.class)) {
            return cls.cast(stringValue());
        }
        if (cborConversionException != null) {
            throw cborConversionException;
        }
        throw new CborConversionException(cls + " is not assignable from a text string");
    }

    public static CborTextString create(String str, int i) {
        return new i(str, i);
    }

    public static CborTextString create(String str) {
        return new i(str, -1);
    }
}
