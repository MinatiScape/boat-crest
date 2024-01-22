package com.airbnb.lottie.parser;

import android.graphics.PointF;
import com.airbnb.lottie.model.DocumentData;
import com.airbnb.lottie.parser.moshi.JsonReader;
import com.clevertap.android.sdk.Constants;
import java.io.IOException;
import org.jose4j.jwk.RsaJsonWebKey;
/* loaded from: classes.dex */
public class DocumentDataParser implements v<DocumentData> {
    public static final DocumentDataParser INSTANCE = new DocumentDataParser();

    /* renamed from: a  reason: collision with root package name */
    public static final JsonReader.Options f2064a = JsonReader.Options.of(RsaJsonWebKey.FACTOR_CRT_COEFFICIENT, "f", "s", "j", "tr", "lh", "ls", "fc", Constants.INAPP_NOTIF_SHOW_CLOSE, "sw", "of", "ps", "sz");

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.airbnb.lottie.parser.v
    public DocumentData parse(JsonReader jsonReader, float f) throws IOException {
        DocumentData.Justification justification = DocumentData.Justification.CENTER;
        jsonReader.beginObject();
        DocumentData.Justification justification2 = justification;
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        float f2 = 0.0f;
        float f3 = 0.0f;
        float f4 = 0.0f;
        float f5 = 0.0f;
        String str = null;
        String str2 = null;
        PointF pointF = null;
        PointF pointF2 = null;
        boolean z = true;
        while (jsonReader.hasNext()) {
            switch (jsonReader.selectName(f2064a)) {
                case 0:
                    str = jsonReader.nextString();
                    break;
                case 1:
                    str2 = jsonReader.nextString();
                    break;
                case 2:
                    f2 = (float) jsonReader.nextDouble();
                    break;
                case 3:
                    int nextInt = jsonReader.nextInt();
                    justification2 = DocumentData.Justification.CENTER;
                    if (nextInt <= justification2.ordinal() && nextInt >= 0) {
                        justification2 = DocumentData.Justification.values()[nextInt];
                        break;
                    }
                    break;
                case 4:
                    i = jsonReader.nextInt();
                    break;
                case 5:
                    f3 = (float) jsonReader.nextDouble();
                    break;
                case 6:
                    f4 = (float) jsonReader.nextDouble();
                    break;
                case 7:
                    i2 = h.d(jsonReader);
                    break;
                case 8:
                    i3 = h.d(jsonReader);
                    break;
                case 9:
                    f5 = (float) jsonReader.nextDouble();
                    break;
                case 10:
                    z = jsonReader.nextBoolean();
                    break;
                case 11:
                    jsonReader.beginArray();
                    PointF pointF3 = new PointF(((float) jsonReader.nextDouble()) * f, ((float) jsonReader.nextDouble()) * f);
                    jsonReader.endArray();
                    pointF = pointF3;
                    break;
                case 12:
                    jsonReader.beginArray();
                    PointF pointF4 = new PointF(((float) jsonReader.nextDouble()) * f, ((float) jsonReader.nextDouble()) * f);
                    jsonReader.endArray();
                    pointF2 = pointF4;
                    break;
                default:
                    jsonReader.skipName();
                    jsonReader.skipValue();
                    break;
            }
        }
        jsonReader.endObject();
        return new DocumentData(str, str2, f2, justification2, i, f3, f4, i2, i3, f5, z, pointF, pointF2);
    }
}
