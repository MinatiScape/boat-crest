package com.coveiot.textrecognition;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import com.coveiot.android.ocr.R;
import com.coveiot.android.ocr.utils.SingletonHolder;
import com.coveiot.textrecognition.base.BaseOcrHelper;
import com.coveiot.textrecognition.base.BaseOcrResponse;
import com.coveiot.textrecognition.base.OcrError;
import com.coveiot.textrecognition.base.ResponseCallback;
import com.coveiot.textrecognition.model.OxymeterResponse;
import com.coveiot.utils.utility.LogHelper;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import com.google.firebase.ml.vision.text.FirebaseVisionText;
import com.google.firebase.ml.vision.text.FirebaseVisionTextRecognizer;
import java.util.Arrays;
import java.util.Locale;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.Regex;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes9.dex */
public final class OxymeterHelper extends BaseOcrHelper {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    public final Context b;
    @NotNull
    public final String c;
    public final double d;
    public final double e;

    /* loaded from: classes9.dex */
    public static final class Companion extends SingletonHolder<OxymeterHelper, Context> {

        /* loaded from: classes9.dex */
        public /* synthetic */ class a extends FunctionReferenceImpl implements Function1<Context, OxymeterHelper> {
            public static final a INSTANCE = new a();

            public a() {
                super(1, OxymeterHelper.class, "<init>", "<init>(Landroid/content/Context;)V", 0);
            }

            @Override // kotlin.jvm.functions.Function1
            @NotNull
            public final OxymeterHelper invoke(@NotNull Context p0) {
                Intrinsics.checkNotNullParameter(p0, "p0");
                return new OxymeterHelper(p0, null);
            }
        }

        public Companion() {
            super(a.INSTANCE);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* loaded from: classes9.dex */
    public static final class a extends Lambda implements Function1<FirebaseVisionText, Unit> {
        public a() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(FirebaseVisionText firebaseVisionText) {
            invoke2(firebaseVisionText);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(FirebaseVisionText firebaseVisionText) {
            String text = firebaseVisionText.getText();
            Intrinsics.checkNotNullExpressionValue(text, "firebaseVisionText.text");
            if (text.length() == 0) {
                ResponseCallback ocrResponseCallback = OxymeterHelper.this.getOcrResponseCallback();
                if (ocrResponseCallback != null) {
                    String string = OxymeterHelper.this.getContext().getString(R.string.scan_failed);
                    Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.scan_failed)");
                    ocrResponseCallback.onFailure(new OcrError(string));
                    return;
                }
                return;
            }
            for (FirebaseVisionText.TextBlock textBlock : firebaseVisionText.getTextBlocks()) {
                String str = OxymeterHelper.this.c;
                LogHelper.d(str, "number of lines: " + textBlock.getLines().size());
                for (FirebaseVisionText.Line line : textBlock.getLines()) {
                    String text2 = line.getText();
                    Intrinsics.checkNotNullExpressionValue(text2, "line.text");
                    if (new Regex("-?\\d+(\\.\\d+)?").matches(text2)) {
                        String text3 = line.getText();
                        double parseDouble = Double.parseDouble(text3);
                        String str2 = OxymeterHelper.this.c;
                        LogHelper.d(str2, "spo2: " + text3);
                        if (!(parseDouble <= OxymeterHelper.this.e && OxymeterHelper.this.d <= parseDouble)) {
                            ResponseCallback ocrResponseCallback2 = OxymeterHelper.this.getOcrResponseCallback();
                            if (ocrResponseCallback2 != null) {
                                StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                                Locale locale = Locale.ENGLISH;
                                String string2 = OxymeterHelper.this.getContext().getResources().getString(R.string.spo2_range_error_msg);
                                Intrinsics.checkNotNullExpressionValue(string2, "context.resources.getStr…ing.spo2_range_error_msg)");
                                String format = String.format(locale, string2, Arrays.copyOf(new Object[]{text3}, 1));
                                Intrinsics.checkNotNullExpressionValue(format, "format(locale, format, *args)");
                                ocrResponseCallback2.onFailure(new OcrError(format));
                                return;
                            }
                            return;
                        }
                        OxymeterResponse oxymeterResponse = new OxymeterResponse();
                        oxymeterResponse.setSpo2(text3);
                        ResponseCallback ocrResponseCallback3 = OxymeterHelper.this.getOcrResponseCallback();
                        if (ocrResponseCallback3 != null) {
                            ocrResponseCallback3.onSuccess(oxymeterResponse);
                            return;
                        }
                        return;
                    }
                }
            }
            ResponseCallback ocrResponseCallback4 = OxymeterHelper.this.getOcrResponseCallback();
            if (ocrResponseCallback4 != null) {
                String string3 = OxymeterHelper.this.getContext().getString(R.string.scan_failed);
                Intrinsics.checkNotNullExpressionValue(string3, "context.getString(R.string.scan_failed)");
                ocrResponseCallback4.onFailure(new OcrError(string3));
            }
        }
    }

    public OxymeterHelper(Context context) {
        this.b = context;
        this.c = "OxymeterHelper";
        this.d = 70.0d;
        this.e = 99.0d;
    }

    public /* synthetic */ OxymeterHelper(Context context, DefaultConstructorMarker defaultConstructorMarker) {
        this(context);
    }

    public static final void c(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void d(OxymeterHelper this$0, Exception e) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(e, "e");
        LogHelper.d(this$0.c, "detectText: text detection failed");
        ResponseCallback<BaseOcrResponse> ocrResponseCallback = this$0.getOcrResponseCallback();
        if (ocrResponseCallback != null) {
            String string = this$0.b.getString(R.string.scan_failed);
            Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.scan_failed)");
            ocrResponseCallback.onFailure(new OcrError(string));
        }
    }

    @Override // com.coveiot.textrecognition.base.BaseOcrHelper
    public void detectText(@NotNull String imagePath) {
        Intrinsics.checkNotNullParameter(imagePath, "imagePath");
        Bitmap e = e(imagePath);
        if (e != null) {
            FirebaseVisionImage fromBitmap = FirebaseVisionImage.fromBitmap(e);
            Intrinsics.checkNotNullExpressionValue(fromBitmap, "fromBitmap(bitmap)");
            FirebaseVisionTextRecognizer onDeviceTextRecognizer = FirebaseVision.getInstance().getOnDeviceTextRecognizer();
            Intrinsics.checkNotNullExpressionValue(onDeviceTextRecognizer, "getInstance()\n          …  .onDeviceTextRecognizer");
            Task<FirebaseVisionText> processImage = onDeviceTextRecognizer.processImage(fromBitmap);
            final a aVar = new a();
            processImage.addOnSuccessListener(new OnSuccessListener() { // from class: com.coveiot.textrecognition.b
                @Override // com.google.android.gms.tasks.OnSuccessListener
                public final void onSuccess(Object obj) {
                    OxymeterHelper.c(Function1.this, obj);
                }
            }).addOnFailureListener(new OnFailureListener() { // from class: com.coveiot.textrecognition.a
                @Override // com.google.android.gms.tasks.OnFailureListener
                public final void onFailure(Exception exc) {
                    OxymeterHelper.d(OxymeterHelper.this, exc);
                }
            });
            return;
        }
        ResponseCallback<BaseOcrResponse> ocrResponseCallback = getOcrResponseCallback();
        if (ocrResponseCallback != null) {
            String string = this.b.getString(R.string.scan_failed);
            Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.scan_failed)");
            ocrResponseCallback.onFailure(new OcrError(string));
        }
    }

    public final Bitmap e(String str) {
        Bitmap decodeFile = BitmapFactory.decodeFile(str);
        try {
            int attributeInt = new ExifInterface(str).getAttributeInt(androidx.exifinterface.media.ExifInterface.TAG_ORIENTATION, 1);
            LogHelper.d(this.c, "Exif: " + attributeInt);
            Matrix matrix = new Matrix();
            if (attributeInt == 3) {
                matrix.postRotate(180.0f);
            } else if (attributeInt == 6) {
                matrix.postRotate(90.0f);
            } else if (attributeInt == 8) {
                matrix.postRotate(270.0f);
            }
            decodeFile = Bitmap.createBitmap(decodeFile, 0, 0, decodeFile.getWidth(), decodeFile.getHeight(), matrix, true);
        } catch (Exception unused) {
            LogHelper.d(this.c, "onCreate: Error rotating bitmap");
            ResponseCallback<BaseOcrResponse> ocrResponseCallback = getOcrResponseCallback();
            if (ocrResponseCallback != null) {
                String string = this.b.getString(R.string.scan_failed);
                Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.scan_failed)");
                ocrResponseCallback.onFailure(new OcrError(string));
            }
        }
        double d = 2;
        return Bitmap.createBitmap(decodeFile, (int) ((decodeFile.getWidth() / 2) - ((decodeFile.getWidth() * 0.35d) / d)), (int) ((decodeFile.getHeight() / 2) - ((decodeFile.getHeight() * 0.35d) / d)), (int) (decodeFile.getWidth() * 0.35d), (int) (decodeFile.getHeight() * 0.35d));
    }

    @NotNull
    public final Context getContext() {
        return this.b;
    }
}
