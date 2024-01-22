package com.coveiot.android.tappy.utils;

import android.content.Context;
import android.net.Uri;
import com.coveiot.android.ocr.R;
import com.coveiot.android.tappy.model.CardOcrResponse;
import com.coveiot.leaderboard.utils.LeaderboardConstants;
import com.coveiot.textrecognition.base.BaseOcrHelper;
import com.coveiot.textrecognition.base.BaseOcrResponse;
import com.coveiot.textrecognition.base.OcrError;
import com.coveiot.textrecognition.base.ResponseCallback;
import com.coveiot.utils.utility.LogHelper;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.text.Text;
import com.google.mlkit.vision.text.TextRecognition;
import com.google.mlkit.vision.text.TextRecognizer;
import com.google.mlkit.vision.text.latin.TextRecognizerOptions;
import java.io.File;
import java.util.List;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.text.Regex;
import kotlin.text.StringsKt__StringsKt;
import kotlin.text.m;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes7.dex */
public final class CardReaderHelper extends BaseOcrHelper {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    public final Context b;
    @NotNull
    public final String c;

    /* loaded from: classes7.dex */
    public static final class Companion extends com.coveiot.android.ocr.utils.SingletonHolder<CardReaderHelper, Context> {

        /* loaded from: classes7.dex */
        public /* synthetic */ class a extends FunctionReferenceImpl implements Function1<Context, CardReaderHelper> {
            public static final a INSTANCE = new a();

            public a() {
                super(1, CardReaderHelper.class, "<init>", "<init>(Landroid/content/Context;)V", 0);
            }

            @Override // kotlin.jvm.functions.Function1
            @NotNull
            public final CardReaderHelper invoke(@NotNull Context p0) {
                Intrinsics.checkNotNullParameter(p0, "p0");
                return new CardReaderHelper(p0, null);
            }
        }

        public Companion() {
            super(a.INSTANCE);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* loaded from: classes7.dex */
    public static final class a extends Lambda implements Function1<Text, Unit> {
        public a() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Text text) {
            invoke2(text);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(Text visionText) {
            List<Text.TextBlock> textBlocks = visionText.getTextBlocks();
            if (textBlocks == null || textBlocks.isEmpty()) {
                ResponseCallback ocrResponseCallback = CardReaderHelper.this.getOcrResponseCallback();
                if (ocrResponseCallback != null) {
                    String string = CardReaderHelper.this.getContext().getString(R.string.scan_failed);
                    Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.scan_failed)");
                    ocrResponseCallback.onFailure(new OcrError(string));
                    return;
                }
                return;
            }
            CardOcrResponse cardOcrResponse = new CardOcrResponse();
            CardReaderHelper cardReaderHelper = CardReaderHelper.this;
            Intrinsics.checkNotNullExpressionValue(visionText, "visionText");
            cardReaderHelper.e(visionText, cardOcrResponse);
            ResponseCallback ocrResponseCallback2 = CardReaderHelper.this.getOcrResponseCallback();
            if (ocrResponseCallback2 != null) {
                ocrResponseCallback2.onSuccess(cardOcrResponse);
            }
        }
    }

    public CardReaderHelper(Context context) {
        this.b = context;
        this.c = "CardReaderHelper";
    }

    public /* synthetic */ CardReaderHelper(Context context, DefaultConstructorMarker defaultConstructorMarker) {
        this(context);
    }

    public static final void c(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void d(CardReaderHelper this$0, Exception e) {
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
        Context context = this.b;
        Uri fromFile = Uri.fromFile(new File(imagePath));
        Intrinsics.checkNotNullExpressionValue(fromFile, "fromFile(this)");
        InputImage fromFilePath = InputImage.fromFilePath(context, fromFile);
        Intrinsics.checkNotNullExpressionValue(fromFilePath, "fromFilePath(context, File(imagePath).toUri())");
        TextRecognizer client = TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS);
        Intrinsics.checkNotNullExpressionValue(client, "getClient(TextRecognizerOptions.DEFAULT_OPTIONS)");
        Task<Text> process = client.process(fromFilePath);
        final a aVar = new a();
        Intrinsics.checkNotNullExpressionValue(process.addOnSuccessListener(new OnSuccessListener() { // from class: com.coveiot.android.tappy.utils.b
            @Override // com.google.android.gms.tasks.OnSuccessListener
            public final void onSuccess(Object obj) {
                CardReaderHelper.c(Function1.this, obj);
            }
        }).addOnFailureListener(new OnFailureListener() { // from class: com.coveiot.android.tappy.utils.a
            @Override // com.google.android.gms.tasks.OnFailureListener
            public final void onFailure(Exception exc) {
                CardReaderHelper.d(CardReaderHelper.this, exc);
            }
        }), "override fun detectText(…        )\n        }\n    }");
    }

    public final void e(Text text, CardOcrResponse cardOcrResponse) {
        boolean z;
        boolean z2;
        try {
            z = false;
            z2 = false;
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        for (Text.TextBlock textBlock : text.getTextBlocks()) {
            String text2 = textBlock.getText();
            Intrinsics.checkNotNullExpressionValue(text2, "block.text");
            LogHelper.d(this.c, "BlockText:" + text2);
            boolean z3 = z2;
            boolean z4 = z;
            boolean z5 = z3;
            for (Text.Line line : textBlock.getLines()) {
                String text3 = line.getText();
                Intrinsics.checkNotNullExpressionValue(text3, "line.text");
                String replace$default = m.replace$default(text3, HexStringBuilder.DEFAULT_SEPARATOR, "", false, 4, (Object) null);
                LogHelper.d(this.c, "LineText:" + text3);
                if (new Regex("^(?:4[0-9]{12}(?:[0-9]{3})?|[25][1-7][0-9]{14}|6(?:011|5[0-9][0-9])[0-9]{12}|3[47][0-9]{13}|3(?:0[0-5]|[68][0-9])[0-9]{11}|(?:2131|1800|35\\d{3})\\d{11})$").matches(replace$default)) {
                    cardOcrResponse.setCardNumber(text3);
                    z4 = true;
                } else if (StringsKt__StringsKt.contains$default((CharSequence) text3, (CharSequence) MqttTopic.TOPIC_LEVEL_SEPARATOR, false, 2, (Object) null)) {
                    List split$default = StringsKt__StringsKt.split$default((CharSequence) text3, new String[]{HexStringBuilder.DEFAULT_SEPARATOR}, false, 0, 6, (Object) null);
                    List split$default2 = StringsKt__StringsKt.split$default((CharSequence) ((String) split$default.get(split$default.size() - 1)), new String[]{MqttTopic.TOPIC_LEVEL_SEPARATOR}, false, 0, 6, (Object) null);
                    if (split$default2.size() == 2) {
                        try {
                            cardOcrResponse.setExpiryMonth(String.valueOf(Integer.parseInt((String) split$default2.get(0))));
                            cardOcrResponse.setExpiryYear(String.valueOf(Integer.parseInt((String) split$default2.get(1))));
                        } catch (Exception e2) {
                            cardOcrResponse.setExpiryMonth(null);
                            cardOcrResponse.setExpiryYear(null);
                            e2.printStackTrace();
                        }
                        z5 = true;
                    } else {
                        if (split$default2.size() == 3) {
                            try {
                                if (((String) split$default2.get(1)).length() == 4) {
                                    StringBuilder sb = new StringBuilder();
                                    char[] charArray = ((String) split$default2.get(1)).toCharArray();
                                    Intrinsics.checkNotNullExpressionValue(charArray, "this as java.lang.String).toCharArray()");
                                    sb.append(charArray[2]);
                                    char[] charArray2 = ((String) split$default2.get(1)).toCharArray();
                                    Intrinsics.checkNotNullExpressionValue(charArray2, "this as java.lang.String).toCharArray()");
                                    sb.append(charArray2[3]);
                                    cardOcrResponse.setExpiryMonth(String.valueOf(Integer.parseInt(sb.toString())));
                                }
                                cardOcrResponse.setExpiryYear(String.valueOf(Integer.parseInt((String) split$default2.get(2))));
                            } catch (Exception e3) {
                                cardOcrResponse.setExpiryMonth(null);
                                cardOcrResponse.setExpiryYear(null);
                                e3.printStackTrace();
                            }
                        }
                        z5 = true;
                    }
                    e.printStackTrace();
                    return;
                } else {
                    if (!z4 && !z5) {
                    }
                    if (new Regex("^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$").matches(text3) && cardOcrResponse.getCardHolderName() == null && !Intrinsics.areEqual(text3, "VISA") && !Intrinsics.areEqual(text3, LeaderboardConstants.GOLD) && !Intrinsics.areEqual(text3, "Platinum") && !Intrinsics.areEqual(text3, "VALID") && !Intrinsics.areEqual(text3, "FROM") && !Intrinsics.areEqual(text3, "THRU") && !Intrinsics.areEqual(text3, "VALID FROM") && !Intrinsics.areEqual(text3, "VALID THRU")) {
                        cardOcrResponse.setCardHolderName(m.replace(m.replace(m.replace(text3, "VISA", "", true), "Master Card", "", true), Constants.MASTERCARD, "", true));
                    } else {
                        LogHelper.d(this.c, "name not matched: " + text3);
                    }
                }
            }
            boolean z6 = z4;
            z2 = z5;
            z = z6;
        }
    }

    @NotNull
    public final Context getContext() {
        return this.b;
    }
}
