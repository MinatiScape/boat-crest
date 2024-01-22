package org.bouncycastle.asn1.util;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Enumeration;
import org.bouncycastle.asn1.ASN1ApplicationSpecific;
import org.bouncycastle.asn1.ASN1Boolean;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1Encoding;
import org.bouncycastle.asn1.ASN1Enumerated;
import org.bouncycastle.asn1.ASN1GeneralizedTime;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1Set;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.ASN1UTCTime;
import org.bouncycastle.asn1.BERApplicationSpecific;
import org.bouncycastle.asn1.BEROctetString;
import org.bouncycastle.asn1.BERSequence;
import org.bouncycastle.asn1.BERSet;
import org.bouncycastle.asn1.BERTaggedObject;
import org.bouncycastle.asn1.DERApplicationSpecific;
import org.bouncycastle.asn1.DERBMPString;
import org.bouncycastle.asn1.DERBitString;
import org.bouncycastle.asn1.DERExternal;
import org.bouncycastle.asn1.DERGraphicString;
import org.bouncycastle.asn1.DERIA5String;
import org.bouncycastle.asn1.DERNull;
import org.bouncycastle.asn1.DERPrintableString;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.DERT61String;
import org.bouncycastle.asn1.DERUTF8String;
import org.bouncycastle.asn1.DERVideotexString;
import org.bouncycastle.asn1.DERVisibleString;
import org.bouncycastle.util.Strings;
import org.bouncycastle.util.encoders.Hex;
/* loaded from: classes12.dex */
public class ASN1Dump {
    public static void a(String str, boolean z, ASN1Primitive aSN1Primitive, StringBuffer stringBuffer) {
        StringBuilder sb;
        BigInteger value;
        String str2;
        String d;
        String time;
        StringBuilder sb2;
        int length;
        String lineSeparator = Strings.lineSeparator();
        if (aSN1Primitive instanceof ASN1Sequence) {
            Enumeration objects = ((ASN1Sequence) aSN1Primitive).getObjects();
            String str3 = str + "    ";
            stringBuffer.append(str);
            stringBuffer.append(aSN1Primitive instanceof BERSequence ? "BER Sequence" : aSN1Primitive instanceof DERSequence ? "DER Sequence" : "Sequence");
            while (true) {
                stringBuffer.append(lineSeparator);
                while (objects.hasMoreElements()) {
                    Object nextElement = objects.nextElement();
                    if (nextElement == null || nextElement.equals(DERNull.INSTANCE)) {
                        stringBuffer.append(str3);
                        stringBuffer.append("NULL");
                    } else {
                        a(str3, z, nextElement instanceof ASN1Primitive ? (ASN1Primitive) nextElement : ((ASN1Encodable) nextElement).toASN1Primitive(), stringBuffer);
                    }
                }
                return;
            }
        }
        if (aSN1Primitive instanceof ASN1TaggedObject) {
            String str4 = str + "    ";
            stringBuffer.append(str);
            stringBuffer.append(aSN1Primitive instanceof BERTaggedObject ? "BER Tagged [" : "Tagged [");
            ASN1TaggedObject aSN1TaggedObject = (ASN1TaggedObject) aSN1Primitive;
            stringBuffer.append(Integer.toString(aSN1TaggedObject.getTagNo()));
            stringBuffer.append(']');
            if (!aSN1TaggedObject.isExplicit()) {
                stringBuffer.append(" IMPLICIT ");
            }
            stringBuffer.append(lineSeparator);
            if (!aSN1TaggedObject.isEmpty()) {
                a(str4, z, aSN1TaggedObject.getObject(), stringBuffer);
                return;
            } else {
                stringBuffer.append(str4);
                stringBuffer.append("EMPTY");
            }
        } else if (aSN1Primitive instanceof ASN1Set) {
            Enumeration objects2 = ((ASN1Set) aSN1Primitive).getObjects();
            String str5 = str + "    ";
            stringBuffer.append(str);
            stringBuffer.append(aSN1Primitive instanceof BERSet ? "BER Set" : "DER Set");
            while (true) {
                stringBuffer.append(lineSeparator);
                while (objects2.hasMoreElements()) {
                    Object nextElement2 = objects2.nextElement();
                    if (nextElement2 == null) {
                        break;
                    }
                    a(str5, z, nextElement2 instanceof ASN1Primitive ? (ASN1Primitive) nextElement2 : ((ASN1Encodable) nextElement2).toASN1Primitive(), stringBuffer);
                }
                return;
                stringBuffer.append(str5);
                stringBuffer.append("NULL");
            }
        } else if (!(aSN1Primitive instanceof ASN1OctetString)) {
            if (aSN1Primitive instanceof ASN1ObjectIdentifier) {
                sb = new StringBuilder();
                sb.append(str);
                sb.append("ObjectIdentifier(");
                sb.append(((ASN1ObjectIdentifier) aSN1Primitive).getId());
            } else if (aSN1Primitive instanceof ASN1Boolean) {
                sb = new StringBuilder();
                sb.append(str);
                sb.append("Boolean(");
                sb.append(((ASN1Boolean) aSN1Primitive).isTrue());
            } else {
                if (!(aSN1Primitive instanceof ASN1Integer)) {
                    if (aSN1Primitive instanceof DERBitString) {
                        DERBitString dERBitString = (DERBitString) aSN1Primitive;
                        stringBuffer.append(str + "DER Bit String[" + dERBitString.getBytes().length + ", " + dERBitString.getPadBits() + "] ");
                        if (z) {
                            d = c(str, dERBitString.getBytes());
                        }
                    } else {
                        if (aSN1Primitive instanceof DERIA5String) {
                            sb = new StringBuilder();
                            sb.append(str);
                            sb.append("IA5String(");
                            time = ((DERIA5String) aSN1Primitive).getString();
                        } else if (aSN1Primitive instanceof DERUTF8String) {
                            sb = new StringBuilder();
                            sb.append(str);
                            sb.append("UTF8String(");
                            time = ((DERUTF8String) aSN1Primitive).getString();
                        } else if (aSN1Primitive instanceof DERPrintableString) {
                            sb = new StringBuilder();
                            sb.append(str);
                            sb.append("PrintableString(");
                            time = ((DERPrintableString) aSN1Primitive).getString();
                        } else if (aSN1Primitive instanceof DERVisibleString) {
                            sb = new StringBuilder();
                            sb.append(str);
                            sb.append("VisibleString(");
                            time = ((DERVisibleString) aSN1Primitive).getString();
                        } else if (aSN1Primitive instanceof DERBMPString) {
                            sb = new StringBuilder();
                            sb.append(str);
                            sb.append("BMPString(");
                            time = ((DERBMPString) aSN1Primitive).getString();
                        } else if (aSN1Primitive instanceof DERT61String) {
                            sb = new StringBuilder();
                            sb.append(str);
                            sb.append("T61String(");
                            time = ((DERT61String) aSN1Primitive).getString();
                        } else if (aSN1Primitive instanceof DERGraphicString) {
                            sb = new StringBuilder();
                            sb.append(str);
                            sb.append("GraphicString(");
                            time = ((DERGraphicString) aSN1Primitive).getString();
                        } else if (aSN1Primitive instanceof DERVideotexString) {
                            sb = new StringBuilder();
                            sb.append(str);
                            sb.append("VideotexString(");
                            time = ((DERVideotexString) aSN1Primitive).getString();
                        } else if (aSN1Primitive instanceof ASN1UTCTime) {
                            sb = new StringBuilder();
                            sb.append(str);
                            sb.append("UTCTime(");
                            time = ((ASN1UTCTime) aSN1Primitive).getTime();
                        } else if (aSN1Primitive instanceof ASN1GeneralizedTime) {
                            sb = new StringBuilder();
                            sb.append(str);
                            sb.append("GeneralizedTime(");
                            time = ((ASN1GeneralizedTime) aSN1Primitive).getTime();
                        } else {
                            if (aSN1Primitive instanceof BERApplicationSpecific) {
                                str2 = ASN1Encoding.BER;
                            } else if (aSN1Primitive instanceof DERApplicationSpecific) {
                                str2 = ASN1Encoding.DER;
                            } else if (aSN1Primitive instanceof ASN1Enumerated) {
                                sb = new StringBuilder();
                                sb.append(str);
                                sb.append("DER Enumerated(");
                                value = ((ASN1Enumerated) aSN1Primitive).getValue();
                            } else if (aSN1Primitive instanceof DERExternal) {
                                DERExternal dERExternal = (DERExternal) aSN1Primitive;
                                stringBuffer.append(str + "External " + lineSeparator);
                                StringBuilder sb3 = new StringBuilder();
                                sb3.append(str);
                                sb3.append("    ");
                                String sb4 = sb3.toString();
                                if (dERExternal.getDirectReference() != null) {
                                    stringBuffer.append(sb4 + "Direct Reference: " + dERExternal.getDirectReference().getId() + lineSeparator);
                                }
                                if (dERExternal.getIndirectReference() != null) {
                                    stringBuffer.append(sb4 + "Indirect Reference: " + dERExternal.getIndirectReference().toString() + lineSeparator);
                                }
                                if (dERExternal.getDataValueDescriptor() != null) {
                                    a(sb4, z, dERExternal.getDataValueDescriptor(), stringBuffer);
                                }
                                stringBuffer.append(sb4 + "Encoding: " + dERExternal.getEncoding() + lineSeparator);
                                a(sb4, z, dERExternal.getExternalContent(), stringBuffer);
                                return;
                            } else {
                                sb = new StringBuilder();
                                sb.append(str);
                                sb.append(aSN1Primitive.toString());
                                sb.append(lineSeparator);
                                d = sb.toString();
                            }
                            d = d(str2, str, z, aSN1Primitive, lineSeparator);
                        }
                        sb.append(time);
                        sb.append(") ");
                        sb.append(lineSeparator);
                        d = sb.toString();
                    }
                    stringBuffer.append(d);
                    return;
                }
                sb = new StringBuilder();
                sb.append(str);
                sb.append("Integer(");
                value = ((ASN1Integer) aSN1Primitive).getValue();
                sb.append(value);
            }
            sb.append(")");
            sb.append(lineSeparator);
            d = sb.toString();
            stringBuffer.append(d);
            return;
        } else {
            ASN1OctetString aSN1OctetString = (ASN1OctetString) aSN1Primitive;
            if (aSN1Primitive instanceof BEROctetString) {
                sb2 = new StringBuilder();
                sb2.append(str);
                sb2.append("BER Constructed Octet String[");
                length = aSN1OctetString.getOctets().length;
            } else {
                sb2 = new StringBuilder();
                sb2.append(str);
                sb2.append("DER Octet String[");
                length = aSN1OctetString.getOctets().length;
            }
            sb2.append(length);
            sb2.append("] ");
            stringBuffer.append(sb2.toString());
            if (z) {
                d = c(str, aSN1OctetString.getOctets());
                stringBuffer.append(d);
                return;
            }
        }
        stringBuffer.append(lineSeparator);
    }

    public static String b(byte[] bArr, int i, int i2) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i3 = i; i3 != i + i2; i3++) {
            if (bArr[i3] >= 32 && bArr[i3] <= 126) {
                stringBuffer.append((char) bArr[i3]);
            }
        }
        return stringBuffer.toString();
    }

    public static String c(String str, byte[] bArr) {
        String b;
        String lineSeparator = Strings.lineSeparator();
        StringBuffer stringBuffer = new StringBuffer();
        String str2 = str + "    ";
        stringBuffer.append(lineSeparator);
        for (int i = 0; i < bArr.length; i += 32) {
            int length = bArr.length - i;
            stringBuffer.append(str2);
            if (length > 32) {
                stringBuffer.append(Strings.fromByteArray(Hex.encode(bArr, i, 32)));
                stringBuffer.append("    ");
                b = b(bArr, i, 32);
            } else {
                stringBuffer.append(Strings.fromByteArray(Hex.encode(bArr, i, bArr.length - i)));
                for (int length2 = bArr.length - i; length2 != 32; length2++) {
                    stringBuffer.append("  ");
                }
                stringBuffer.append("    ");
                b = b(bArr, i, bArr.length - i);
            }
            stringBuffer.append(b);
            stringBuffer.append(lineSeparator);
        }
        return stringBuffer.toString();
    }

    public static String d(String str, String str2, boolean z, ASN1Primitive aSN1Primitive, String str3) {
        ASN1ApplicationSpecific aSN1ApplicationSpecific = ASN1ApplicationSpecific.getInstance(aSN1Primitive);
        StringBuffer stringBuffer = new StringBuffer();
        if (!aSN1ApplicationSpecific.isConstructed()) {
            return str2 + str + " ApplicationSpecific[" + aSN1ApplicationSpecific.getApplicationTag() + "] (" + Strings.fromByteArray(Hex.encode(aSN1ApplicationSpecific.getContents())) + ")" + str3;
        }
        try {
            ASN1Sequence aSN1Sequence = ASN1Sequence.getInstance(aSN1ApplicationSpecific.getObject(16));
            stringBuffer.append(str2 + str + " ApplicationSpecific[" + aSN1ApplicationSpecific.getApplicationTag() + "]" + str3);
            Enumeration objects = aSN1Sequence.getObjects();
            while (objects.hasMoreElements()) {
                a(str2 + "    ", z, (ASN1Primitive) objects.nextElement(), stringBuffer);
            }
        } catch (IOException e) {
            stringBuffer.append(e);
        }
        return stringBuffer.toString();
    }

    public static String dumpAsString(Object obj) {
        return dumpAsString(obj, false);
    }

    public static String dumpAsString(Object obj, boolean z) {
        ASN1Primitive aSN1Primitive;
        StringBuffer stringBuffer = new StringBuffer();
        if (obj instanceof ASN1Primitive) {
            aSN1Primitive = (ASN1Primitive) obj;
        } else if (!(obj instanceof ASN1Encodable)) {
            return "unknown object type " + obj.toString();
        } else {
            aSN1Primitive = ((ASN1Encodable) obj).toASN1Primitive();
        }
        a("", z, aSN1Primitive, stringBuffer);
        return stringBuffer.toString();
    }
}
