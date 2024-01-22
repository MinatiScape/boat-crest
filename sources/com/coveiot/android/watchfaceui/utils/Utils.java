package com.coveiot.android.watchfaceui.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.models.DeviceType;
import com.coveiot.android.devicemodels.DeviceUtils;
import com.coveiot.android.devicemodels.ModelNameList;
import com.coveiot.android.watchfaceui.R;
import com.coveiot.coveaccess.device.model.BleDeviceInfo;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.utils.utility.AppUtils;
import com.coveiot.utils.utility.LogHelper;
import com.crrepa.r.a;
import com.google.gson.Gson;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;
import com.yalantis.ucrop.UCrop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.m;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes8.dex */
public final class Utils {
    @NotNull
    public static final Utils INSTANCE = new Utils();

    /* renamed from: a  reason: collision with root package name */
    public static final String f6143a = Utils.class.getSimpleName();

    /* loaded from: classes8.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[DeviceType.values().length];
            try {
                iArr[DeviceType.jstyle1810G.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[DeviceType.jstyle1790.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[DeviceType.jstyle1963D.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[DeviceType.jstyle1963YH.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[DeviceType.jstyle1860.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr[DeviceType.smaF2.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                iArr[DeviceType.smaR9.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                iArr[DeviceType.kh17.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                iArr[DeviceType.matrix.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                iArr[DeviceType.crpGPF5.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                iArr[DeviceType.CZ0.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                iArr[DeviceType.CZ3.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                iArr[DeviceType.wavePrime.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                iArr[DeviceType.CA0.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                iArr[DeviceType.CA3.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                iArr[DeviceType.CA2.ordinal()] = 16;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                iArr[DeviceType.CA3_BT_CALL.ordinal()] = 17;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                iArr[DeviceType.CY1_PRIMIA_VOICE.ordinal()] = 18;
            } catch (NoSuchFieldError unused18) {
            }
            try {
                iArr[DeviceType.CY1_LOOP_CALL_PRO.ordinal()] = 19;
            } catch (NoSuchFieldError unused19) {
            }
            try {
                iArr[DeviceType.CY1_LOOP_CONNECT_PRO.ordinal()] = 20;
            } catch (NoSuchFieldError unused20) {
            }
            try {
                iArr[DeviceType.IDO_SELECT.ordinal()] = 21;
            } catch (NoSuchFieldError unused21) {
            }
            try {
                iArr[DeviceType.CA5_WAVE_STYLE.ordinal()] = 22;
            } catch (NoSuchFieldError unused22) {
            }
            try {
                iArr[DeviceType.CA5_WAVE_PLAY.ordinal()] = 23;
            } catch (NoSuchFieldError unused23) {
            }
            try {
                iArr[DeviceType.CA5_WAVE_BEAT.ordinal()] = 24;
            } catch (NoSuchFieldError unused24) {
            }
            try {
                iArr[DeviceType.ULC3_WAVE_SMART.ordinal()] = 25;
            } catch (NoSuchFieldError unused25) {
            }
            try {
                iArr[DeviceType.ULC2_WAVE_BEAT_PLUS.ordinal()] = 26;
            } catch (NoSuchFieldError unused26) {
            }
            try {
                iArr[DeviceType.ULC2_WAVE_STYLE_PLUS.ordinal()] = 27;
            } catch (NoSuchFieldError unused27) {
            }
            try {
                iArr[DeviceType.ULC2_WAVE_SMART_PLUS.ordinal()] = 28;
            } catch (NoSuchFieldError unused28) {
            }
            try {
                iArr[DeviceType.ULC2_WAVE_LYNC.ordinal()] = 29;
            } catch (NoSuchFieldError unused29) {
            }
            try {
                iArr[DeviceType.CA3_WAVE_COSMOS.ordinal()] = 30;
            } catch (NoSuchFieldError unused30) {
            }
            try {
                iArr[DeviceType.CA3_BT_STORM_PRO_CALL.ordinal()] = 31;
            } catch (NoSuchFieldError unused31) {
            }
            try {
                iArr[DeviceType.CA3_BT_WAVE_COSMOS_PRO.ordinal()] = 32;
            } catch (NoSuchFieldError unused32) {
            }
            try {
                iArr[DeviceType.WAVE_ELITE.ordinal()] = 33;
            } catch (NoSuchFieldError unused33) {
            }
            try {
                iArr[DeviceType.IDO_CONNECT.ordinal()] = 34;
            } catch (NoSuchFieldError unused34) {
            }
            try {
                iArr[DeviceType.TOUCH_WAVE_CALL_PLUS.ordinal()] = 35;
            } catch (NoSuchFieldError unused35) {
            }
            try {
                iArr[DeviceType.TOUCH_WAVE_CONNECT_PLUS.ordinal()] = 36;
            } catch (NoSuchFieldError unused36) {
            }
            try {
                iArr[DeviceType.TOUCH_LUNAR_CALL.ordinal()] = 37;
            } catch (NoSuchFieldError unused37) {
            }
            try {
                iArr[DeviceType.TOUCH_LUNAR_CONNECT.ordinal()] = 38;
            } catch (NoSuchFieldError unused38) {
            }
            try {
                iArr[DeviceType.TOUCH_LUNAR_CALL_PLUS.ordinal()] = 39;
            } catch (NoSuchFieldError unused39) {
            }
            try {
                iArr[DeviceType.TOUCH_LUNAR_CONNECT_PLUS.ordinal()] = 40;
            } catch (NoSuchFieldError unused40) {
            }
            try {
                iArr[DeviceType.TOUCH_XTEND_CALL_PLUS.ordinal()] = 41;
            } catch (NoSuchFieldError unused41) {
            }
            try {
                iArr[DeviceType.TOUCH_STORM_CONNECT_PLUS.ordinal()] = 42;
            } catch (NoSuchFieldError unused42) {
            }
            try {
                iArr[DeviceType.ULC5_ULTIMA_CALL.ordinal()] = 43;
            } catch (NoSuchFieldError unused43) {
            }
            try {
                iArr[DeviceType.ULC5_ULTIMA_CONNECT.ordinal()] = 44;
            } catch (NoSuchFieldError unused44) {
            }
            try {
                iArr[DeviceType.WAVEFORCE.ordinal()] = 45;
            } catch (NoSuchFieldError unused45) {
            }
            try {
                iArr[DeviceType.WAVEARMOUR.ordinal()] = 46;
            } catch (NoSuchFieldError unused46) {
            }
            try {
                iArr[DeviceType.EA_LEAP_CALL.ordinal()] = 47;
            } catch (NoSuchFieldError unused47) {
            }
            try {
                iArr[DeviceType.EA_FLEX_CONNECT.ordinal()] = 48;
            } catch (NoSuchFieldError unused48) {
            }
            try {
                iArr[DeviceType.EA_STRIDE_VOICE.ordinal()] = 49;
            } catch (NoSuchFieldError unused49) {
            }
            try {
                iArr[DeviceType.EA_XTEND_PLUS.ordinal()] = 50;
            } catch (NoSuchFieldError unused50) {
            }
            try {
                iArr[DeviceType.EA_STORM_PLUS.ordinal()] = 51;
            } catch (NoSuchFieldError unused51) {
            }
            try {
                iArr[DeviceType.EA_COSMOS_PLUS.ordinal()] = 52;
            } catch (NoSuchFieldError unused52) {
            }
            try {
                iArr[DeviceType.EA_LUNAR_CALL_ACE.ordinal()] = 53;
            } catch (NoSuchFieldError unused53) {
            }
            try {
                iArr[DeviceType.EA_LUNAR_CONNECT_ACE.ordinal()] = 54;
            } catch (NoSuchFieldError unused54) {
            }
            try {
                iArr[DeviceType.EA_PRIMIA_ACE.ordinal()] = 55;
            } catch (NoSuchFieldError unused55) {
            }
            try {
                iArr[DeviceType.LUNARFIT.ordinal()] = 56;
            } catch (NoSuchFieldError unused56) {
            }
            try {
                iArr[DeviceType.WAVEARMOUR2.ordinal()] = 57;
            } catch (NoSuchFieldError unused57) {
            }
            try {
                iArr[DeviceType.WAVEFORCE2.ordinal()] = 58;
            } catch (NoSuchFieldError unused58) {
            }
            try {
                iArr[DeviceType.TOUCH_WAVE_NEO.ordinal()] = 59;
            } catch (NoSuchFieldError unused59) {
            }
            try {
                iArr[DeviceType.SMA_WAVE_GENESIS_PRO.ordinal()] = 60;
            } catch (NoSuchFieldError unused60) {
            }
            try {
                iArr[DeviceType.SMA_WAVE_ELEVATE_PRO.ordinal()] = 61;
            } catch (NoSuchFieldError unused61) {
            }
            try {
                iArr[DeviceType.SMA_WAVE_GLORY_PRO.ordinal()] = 62;
            } catch (NoSuchFieldError unused62) {
            }
            try {
                iArr[DeviceType.SMA_ULTIMA_VOGUE.ordinal()] = 63;
            } catch (NoSuchFieldError unused63) {
            }
            try {
                iArr[DeviceType.SMA_LUNAR_SEEK.ordinal()] = 64;
            } catch (NoSuchFieldError unused64) {
            }
            try {
                iArr[DeviceType.SMA_LUNAR_COMET.ordinal()] = 65;
            } catch (NoSuchFieldError unused65) {
            }
            try {
                iArr[DeviceType.SMA_LUNAR_VELOCITY.ordinal()] = 66;
            } catch (NoSuchFieldError unused66) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    @JvmStatic
    public static final boolean isCYDevice(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.cy1_primia_voice), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.cy1_loop_call_pro), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.cy1_loop_connect_pro), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.lunar_call_pro_2), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.lunar_connect_pro_2), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.wave_primia_talk_2), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.ultima_call_pro), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.ultima_connect_pro), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.wave_cosmos_talk), false);
    }

    public final File a(Context context) throws IOException {
        String formatDate = AppUtils.formatDate(new Date(), "yyyyMMdd_HHmmss");
        File image = File.createTempFile("JPEG_" + formatDate + '_', ".jpg", context.getExternalFilesDir(Environment.DIRECTORY_PICTURES));
        Intrinsics.checkNotNullExpressionValue(image, "image");
        return image;
    }

    public final File b(Context context, String str, int i) {
        File file = new File(context.getFilesDir() + File.separator + str + ".bin");
        try {
            InputStream openRawResource = context.getResources().openRawResource(i);
            Intrinsics.checkNotNullExpressionValue(openRawResource, "context.resources.openRawResource(resourceId)");
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            byte[] bArr = new byte[1024];
            while (true) {
                int read = openRawResource.read(bArr);
                if (read > 0) {
                    fileOutputStream.write(bArr, 0, read);
                } else {
                    fileOutputStream.close();
                    openRawResource.close();
                    return file;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Nullable
    public final String calculateMD5(@NotNull File updateFile) {
        Intrinsics.checkNotNullParameter(updateFile, "updateFile");
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(MessageDigestAlgorithms.MD5);
            Intrinsics.checkNotNullExpressionValue(messageDigest, "getInstance(\"MD5\")");
            try {
                FileInputStream fileInputStream = new FileInputStream(updateFile);
                byte[] bArr = new byte[8192];
                while (true) {
                    try {
                        try {
                            int read = fileInputStream.read(bArr);
                            if (read <= 0) {
                                break;
                            }
                            messageDigest.update(bArr, 0, read);
                        } catch (IOException e) {
                            throw new RuntimeException("Unable to process file for MD5", e);
                        }
                    } catch (Throwable th) {
                        try {
                            fileInputStream.close();
                        } catch (IOException e2) {
                            Log.e(f6143a, "Exception on closing MD5 input stream", e2);
                        }
                        throw th;
                    }
                }
                String bigInteger = new BigInteger(1, messageDigest.digest()).toString(16);
                StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                String format = String.format(Locale.ENGLISH, "%32s", Arrays.copyOf(new Object[]{bigInteger}, 1));
                Intrinsics.checkNotNullExpressionValue(format, "format(locale, format, *args)");
                String replace$default = m.replace$default(format, ' ', '0', false, 4, (Object) null);
                try {
                    fileInputStream.close();
                } catch (IOException e3) {
                    Log.e(f6143a, "Exception on closing MD5 input stream", e3);
                }
                return replace$default;
            } catch (FileNotFoundException e4) {
                Log.e(f6143a, "Exception while getting FileInputStream", e4);
                return null;
            }
        } catch (NoSuchAlgorithmException e5) {
            Log.e(f6143a, "Exception while getting digest", e5);
            return null;
        }
    }

    public final boolean canDeviceHandleGalleryExternal(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return new Intent("android.intent.action.PICK", MediaStore.Images.Media.EXTERNAL_CONTENT_URI).resolveActivity(context.getPackageManager()) != null;
    }

    public final boolean canDeviceHandleGalleryInternal(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return new Intent("android.intent.action.PICK", MediaStore.Images.Media.INTERNAL_CONTENT_URI).resolveActivity(context.getPackageManager()) != null;
    }

    public final int dpToPixels(@NotNull Context context, int i) {
        Intrinsics.checkNotNullParameter(context, "context");
        return (int) (i * context.getResources().getDisplayMetrics().density);
    }

    @Nullable
    public final File getBasicBinFilesForMatrixWatchfaces(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        DeviceType deviceType = BleApiManager.getInstance(context).getDeviceType();
        int i = deviceType == null ? -1 : WhenMappings.$EnumSwitchMapping$0[deviceType.ordinal()];
        if (i != 9) {
            if (i != 45 && i != 46) {
                switch (i) {
                    case 56:
                        return b(context, "lunar_fit_dial_style", R.raw.lunar_fit_dial_style);
                    case 57:
                    case 58:
                        break;
                    default:
                        return b(context, "matrix_dial_style1", R.raw.matrix_dial_style1);
                }
            }
            return b(context, "rugged_dial_style", R.raw.rugged_dial_style);
        }
        return b(context, "matrix_dial_style1", R.raw.matrix_dial_style1);
    }

    public final int getBatteryPercentageForMatrix(int i) {
        return (i / 10) * 10;
    }

    @NotNull
    public final Intent getGalleryIntent() {
        Intent intent = new Intent();
        intent.setType(a.d);
        intent.setAction("android.intent.action.GET_CONTENT");
        return intent;
    }

    public final int getMinBatteryPerForWatchFaceUpload(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        if (SessionManager.getInstance(context).getDeviceModelBean() == null || SessionManager.getInstance(context).getDeviceModelBean().getMinBatteryPerForWatchFaceUpload() == null) {
            return 5;
        }
        Integer minBatteryPerForWatchFaceUpload = SessionManager.getInstance(context).getDeviceModelBean().getMinBatteryPerForWatchFaceUpload();
        Intrinsics.checkNotNullExpressionValue(minBatteryPerForWatchFaceUpload, "getInstance(context).dev…teryPerForWatchFaceUpload");
        return minBatteryPerForWatchFaceUpload.intValue();
    }

    @Nullable
    public final String getModelNumber(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        DeviceType deviceType = BleApiManager.getInstance(context).getDeviceType();
        switch (deviceType == null ? -1 : WhenMappings.$EnumSwitchMapping$0[deviceType.ordinal()]) {
            case 1:
                return ModelNameList.MODEL_NUMBER_COVE9.getValue();
            case 2:
                return ModelNameList.MODEL_NUMBER_BA1009V11.getValue();
            case 3:
                return ModelNameList.MODEL_NUMBER_WA1002V11.getValue();
            case 4:
                return ModelNameList.MODEL_NUMBER_TWTXW1.getValue();
            case 5:
                return ModelNameList.MODEL_NUMBER_WANDERER.getValue();
            case 6:
                return ModelNameList.MODEL_NUMBER_MERCURY.getValue();
            case 7:
                return ModelNameList.MODEL_NUMBER_PRIMIA.getValue();
            case 8:
                return ModelNameList.MODEL_NUMBER_VERTEX.getValue();
            case 9:
                return ModelNameList.MODEL_NUMBER_MATRIX.getValue();
            case 10:
                return ModelNameList.MODEL_NUMBER_WAVEFIT.getValue();
            case 11:
                return ModelNameList.MODEL_NUMBER_WAVEPRO.getValue();
            case 12:
                return ModelNameList.MODEL_NUMBER_XTEND_SPORT.getValue();
            case 13:
                return ModelNameList.MODEL_NUMBER_WAVEPRIME.getValue();
            case 14:
                return ModelNameList.MODEL_NUMBER_CA0.getValue();
            case 15:
                return ModelNameList.MODEL_NUMBER_STORMPRO.getValue();
            case 16:
                return ModelNameList.MODEL_NUMBER_WAVEACTIVE.getValue();
            case 17:
                return ModelNameList.MODEL_NUMBER_XTENDPRO.getValue();
            case 18:
                return ModelNameList.MODEL_NUMBER_PRIMIA_VOICE.getValue();
            case 19:
                return ModelNameList.MODEL_NUMBER_LOOP_CALL_PRO.getValue();
            case 20:
                return ModelNameList.MODEL_NUMBER_LOOP_CONNECT_PRO.getValue();
            case 21:
                return ModelNameList.MODEL_NUMBER_IDO_SELECT.getValue();
            case 22:
                return ModelNameList.MODEL_NUMBER_WAVE_STYLE.getValue();
            case 23:
                return ModelNameList.MODEL_NUMBER_WAVE_PLAY.getValue();
            case 24:
                return ModelNameList.MODEL_NUMBER_WAVE_BEAT.getValue();
            case 25:
                return ModelNameList.MODEL_NUMBER_ULC3_WAVE_SMART.getValue();
            case 26:
                return ModelNameList.MODEL_NUMBER_ULC2_BEAT_PLUS.getValue();
            case 27:
                return ModelNameList.MODEL_NUMBER_ULC2_STYLE_PLUS.getValue();
            case 28:
                return ModelNameList.MODEL_NUMBER_ULC2_SMART_PLUS.getValue();
            case 29:
                return ModelNameList.MODEL_NUMBER_ULC2_LYNC.getValue();
            case 30:
                return ModelNameList.MODEL_NUMBER_WAVE_COSMOS.getValue();
            case 31:
                return ModelNameList.MODEL_NUMBER_STORMPRO_CALL.getValue();
            case 32:
                return ModelNameList.MODEL_NUMBER_WAVE_COSMOSPRO.getValue();
            case 33:
                return ModelNameList.MODEL_NUMBER_WAVEELITE.getValue();
            case 34:
                return ModelNameList.MODEL_NUMBER_IDO_CONNECT.getValue();
            case 35:
                return ModelNameList.MODEL_NUMBER_WAVE_CALL_PLUS.getValue();
            case 36:
                return ModelNameList.MODEL_NUMBER_WAVE_CONNECT_PLUS.getValue();
            case 37:
                return ModelNameList.MODEL_NUMBER_LUNAR_CALL.getValue();
            case 38:
                return ModelNameList.MODEL_NUMBER_LUNAR_CONNECT.getValue();
            case 39:
                return ModelNameList.MODEL_NUMBER_LUNAR_CALL_PLUS.getValue();
            case 40:
                return ModelNameList.MODEL_NUMBER_LUNAR_CONNECT_PLUS.getValue();
            case 41:
                return ModelNameList.MODEL_NUMBER_XTEND_CALL_PLUS.getValue();
            case 42:
                return ModelNameList.MODEL_NUMBER_STORM_CONNECT_PLUS.getValue();
            case 43:
                return ModelNameList.MODEL_NUMBER_ULTIMA_CALL.getValue();
            case 44:
                return ModelNameList.MODEL_NUMBER_ULTIMA_CONNECT.getValue();
            case 45:
                return ModelNameList.MODEL_NUMBER_WAVE_FORCE.getValue();
            case 46:
                return ModelNameList.MODEL_NUMBER_WAVE_ARMOUR.getValue();
            case 47:
                return ModelNameList.MODEL_NUMBER_LEAP_CALL.getValue();
            case 48:
                return ModelNameList.MODEL_NUMBER_FLEX_CONNECT.getValue();
            case 49:
                return ModelNameList.MODEL_NUMBER_STRIDE_VOICE.getValue();
            case 50:
                return ModelNameList.MODEL_NUMBER_XTEND_PLUS.getValue();
            case 51:
                return ModelNameList.MODEL_NUMBER_STORM_PLUS.getValue();
            case 52:
                return ModelNameList.MODEL_NUMBER_COSMOS_PLUS.getValue();
            case 53:
                return ModelNameList.MODEL_NUMBER_LUNAR_CALL_ACE.getValue();
            case 54:
                return ModelNameList.MODEL_NUMBER_LUNAR_CONNECT_ACE.getValue();
            case 55:
                return ModelNameList.MODEL_NUMBER_PRIMIA_ACE.getValue();
            case 56:
                return ModelNameList.MODEL_NUMBER_LUNAR_FIT.getValue();
            case 57:
                return ModelNameList.MODEL_NUMBER_WAVE_ARMOUR_2.getValue();
            case 58:
                return ModelNameList.MODEL_NUMBER_WAVE_FORCE_2.getValue();
            case 59:
                return ModelNameList.MODEL_NUMBER_WAVE_NEO.getValue();
            case 60:
                return ModelNameList.MODEL_NUMBER_WAVE_GENESIS_PRO.getValue();
            case 61:
                return ModelNameList.MODEL_NUMBER_WAVE_ELEVATE_PRO.getValue();
            case 62:
                return ModelNameList.MODEL_NUMBER_WAVE_GLORY_PRO.getValue();
            case 63:
                return ModelNameList.MODEL_NUMBER_ULTIMA_VOGUE.getValue();
            case 64:
                return ModelNameList.MODEL_NUMBER_LUNAR_SEEK.getValue();
            case 65:
                return ModelNameList.MODEL_NUMBER_LUNAR_COMET.getValue();
            case 66:
                return ModelNameList.MODEL_NUMBER_LUNAR_VELOCITY.getValue();
            default:
                return null;
        }
    }

    @Nullable
    public final Bitmap getStyleBitmapsForMatrixWatchfaces(@NotNull Context context, int i) {
        Intrinsics.checkNotNullParameter(context, "context");
        DeviceType deviceType = BleApiManager.getInstance(context).getDeviceType();
        DeviceType deviceType2 = DeviceType.matrix;
        if (deviceType == deviceType2 && i == 1) {
            Bitmap decodeResource = BitmapFactory.decodeResource(context.getResources(), R.drawable.matrix_dial_style1);
            Intrinsics.checkNotNullExpressionValue(decodeResource, "{\n                Bitmap…ial_style1)\n            }");
            return decodeResource;
        } else if (BleApiManager.getInstance(context).getDeviceType() == deviceType2 && i == 2) {
            Bitmap decodeResource2 = BitmapFactory.decodeResource(context.getResources(), R.drawable.matrix_dial_style2);
            Intrinsics.checkNotNullExpressionValue(decodeResource2, "{\n                Bitmap…ial_style2)\n            }");
            return decodeResource2;
        } else if (BleApiManager.getInstance(context).getDeviceType() == deviceType2 && i == 3) {
            Bitmap decodeResource3 = BitmapFactory.decodeResource(context.getResources(), R.drawable.matrix_dial_style3);
            Intrinsics.checkNotNullExpressionValue(decodeResource3, "{\n                Bitmap…ial_style3)\n            }");
            return decodeResource3;
        } else if (BleApiManager.getInstance(context).getDeviceType() == deviceType2 && i == 4) {
            Bitmap decodeResource4 = BitmapFactory.decodeResource(context.getResources(), R.drawable.matrix_dial_style4);
            Intrinsics.checkNotNullExpressionValue(decodeResource4, "{\n                Bitmap…ial_style4)\n            }");
            return decodeResource4;
        } else if (BleApiManager.getInstance(context).getDeviceType() == deviceType2 && i == 5) {
            Bitmap decodeResource5 = BitmapFactory.decodeResource(context.getResources(), R.drawable.matrix_dial_style5);
            Intrinsics.checkNotNullExpressionValue(decodeResource5, "{\n                Bitmap…ial_style5)\n            }");
            return decodeResource5;
        } else {
            DeviceType deviceType3 = BleApiManager.getInstance(context).getDeviceType();
            DeviceType deviceType4 = DeviceType.WAVEARMOUR;
            if (deviceType3 == deviceType4 && i == 1) {
                Bitmap decodeResource6 = BitmapFactory.decodeResource(context.getResources(), R.drawable.rugged_dial_style1);
                Intrinsics.checkNotNullExpressionValue(decodeResource6, "{\n                Bitmap…ial_style1)\n            }");
                return decodeResource6;
            } else if (BleApiManager.getInstance(context).getDeviceType() == deviceType4 && i == 2) {
                Bitmap decodeResource7 = BitmapFactory.decodeResource(context.getResources(), R.drawable.rugged_dial_style2);
                Intrinsics.checkNotNullExpressionValue(decodeResource7, "{\n                Bitmap…ial_style2)\n            }");
                return decodeResource7;
            } else if (BleApiManager.getInstance(context).getDeviceType() == deviceType4 && i == 3) {
                Bitmap decodeResource8 = BitmapFactory.decodeResource(context.getResources(), R.drawable.rugged_dial_style3);
                Intrinsics.checkNotNullExpressionValue(decodeResource8, "{\n                Bitmap…ial_style3)\n            }");
                return decodeResource8;
            } else if (BleApiManager.getInstance(context).getDeviceType() == deviceType4 && i == 4) {
                Bitmap decodeResource9 = BitmapFactory.decodeResource(context.getResources(), R.drawable.rugged_dial_style4);
                Intrinsics.checkNotNullExpressionValue(decodeResource9, "{\n                Bitmap…ial_style4)\n            }");
                return decodeResource9;
            } else if (BleApiManager.getInstance(context).getDeviceType() == deviceType4 && i == 5) {
                Bitmap decodeResource10 = BitmapFactory.decodeResource(context.getResources(), R.drawable.rugged_dial_style5);
                Intrinsics.checkNotNullExpressionValue(decodeResource10, "{\n                Bitmap…ial_style5)\n            }");
                return decodeResource10;
            } else {
                DeviceType deviceType5 = BleApiManager.getInstance(context).getDeviceType();
                DeviceType deviceType6 = DeviceType.WAVEFORCE;
                if (deviceType5 == deviceType6 && i == 1) {
                    Bitmap decodeResource11 = BitmapFactory.decodeResource(context.getResources(), R.drawable.rugged_dial_style1);
                    Intrinsics.checkNotNullExpressionValue(decodeResource11, "{\n                Bitmap…ial_style1)\n            }");
                    return decodeResource11;
                } else if (BleApiManager.getInstance(context).getDeviceType() == deviceType6 && i == 2) {
                    Bitmap decodeResource12 = BitmapFactory.decodeResource(context.getResources(), R.drawable.rugged_dial_style2);
                    Intrinsics.checkNotNullExpressionValue(decodeResource12, "{\n                Bitmap…ial_style2)\n            }");
                    return decodeResource12;
                } else if (BleApiManager.getInstance(context).getDeviceType() == deviceType6 && i == 3) {
                    Bitmap decodeResource13 = BitmapFactory.decodeResource(context.getResources(), R.drawable.rugged_dial_style3);
                    Intrinsics.checkNotNullExpressionValue(decodeResource13, "{\n                Bitmap…ial_style3)\n            }");
                    return decodeResource13;
                } else if (BleApiManager.getInstance(context).getDeviceType() == deviceType6 && i == 4) {
                    Bitmap decodeResource14 = BitmapFactory.decodeResource(context.getResources(), R.drawable.rugged_dial_style4);
                    Intrinsics.checkNotNullExpressionValue(decodeResource14, "{\n                Bitmap…ial_style4)\n            }");
                    return decodeResource14;
                } else if (BleApiManager.getInstance(context).getDeviceType() == deviceType6 && i == 5) {
                    Bitmap decodeResource15 = BitmapFactory.decodeResource(context.getResources(), R.drawable.rugged_dial_style5);
                    Intrinsics.checkNotNullExpressionValue(decodeResource15, "{\n                Bitmap…ial_style5)\n            }");
                    return decodeResource15;
                } else {
                    DeviceType deviceType7 = BleApiManager.getInstance(context).getDeviceType();
                    DeviceType deviceType8 = DeviceType.WAVEARMOUR2;
                    if (deviceType7 == deviceType8 && i == 1) {
                        Bitmap decodeResource16 = BitmapFactory.decodeResource(context.getResources(), R.drawable.rugged_dial_style1);
                        Intrinsics.checkNotNullExpressionValue(decodeResource16, "{\n                Bitmap…ial_style1)\n            }");
                        return decodeResource16;
                    } else if (BleApiManager.getInstance(context).getDeviceType() == deviceType8 && i == 2) {
                        Bitmap decodeResource17 = BitmapFactory.decodeResource(context.getResources(), R.drawable.rugged_dial_style2);
                        Intrinsics.checkNotNullExpressionValue(decodeResource17, "{\n                Bitmap…ial_style2)\n            }");
                        return decodeResource17;
                    } else if (BleApiManager.getInstance(context).getDeviceType() == deviceType8 && i == 3) {
                        Bitmap decodeResource18 = BitmapFactory.decodeResource(context.getResources(), R.drawable.rugged_dial_style3);
                        Intrinsics.checkNotNullExpressionValue(decodeResource18, "{\n                Bitmap…ial_style3)\n            }");
                        return decodeResource18;
                    } else if (BleApiManager.getInstance(context).getDeviceType() == deviceType8 && i == 4) {
                        Bitmap decodeResource19 = BitmapFactory.decodeResource(context.getResources(), R.drawable.rugged_dial_style4);
                        Intrinsics.checkNotNullExpressionValue(decodeResource19, "{\n                Bitmap…ial_style4)\n            }");
                        return decodeResource19;
                    } else if (BleApiManager.getInstance(context).getDeviceType() == deviceType8 && i == 5) {
                        Bitmap decodeResource20 = BitmapFactory.decodeResource(context.getResources(), R.drawable.rugged_dial_style5);
                        Intrinsics.checkNotNullExpressionValue(decodeResource20, "{\n                Bitmap…ial_style5)\n            }");
                        return decodeResource20;
                    } else {
                        DeviceType deviceType9 = BleApiManager.getInstance(context).getDeviceType();
                        DeviceType deviceType10 = DeviceType.WAVEFORCE2;
                        if (deviceType9 == deviceType10 && i == 1) {
                            Bitmap decodeResource21 = BitmapFactory.decodeResource(context.getResources(), R.drawable.rugged_dial_style1);
                            Intrinsics.checkNotNullExpressionValue(decodeResource21, "{\n                Bitmap…ial_style1)\n            }");
                            return decodeResource21;
                        } else if (BleApiManager.getInstance(context).getDeviceType() == deviceType10 && i == 2) {
                            Bitmap decodeResource22 = BitmapFactory.decodeResource(context.getResources(), R.drawable.rugged_dial_style2);
                            Intrinsics.checkNotNullExpressionValue(decodeResource22, "{\n                Bitmap…ial_style2)\n            }");
                            return decodeResource22;
                        } else if (BleApiManager.getInstance(context).getDeviceType() == deviceType10 && i == 3) {
                            Bitmap decodeResource23 = BitmapFactory.decodeResource(context.getResources(), R.drawable.rugged_dial_style3);
                            Intrinsics.checkNotNullExpressionValue(decodeResource23, "{\n                Bitmap…ial_style3)\n            }");
                            return decodeResource23;
                        } else if (BleApiManager.getInstance(context).getDeviceType() == deviceType10 && i == 4) {
                            Bitmap decodeResource24 = BitmapFactory.decodeResource(context.getResources(), R.drawable.rugged_dial_style4);
                            Intrinsics.checkNotNullExpressionValue(decodeResource24, "{\n                Bitmap…ial_style4)\n            }");
                            return decodeResource24;
                        } else if (BleApiManager.getInstance(context).getDeviceType() == deviceType10 && i == 5) {
                            Bitmap decodeResource25 = BitmapFactory.decodeResource(context.getResources(), R.drawable.rugged_dial_style5);
                            Intrinsics.checkNotNullExpressionValue(decodeResource25, "{\n                Bitmap…ial_style5)\n            }");
                            return decodeResource25;
                        } else {
                            DeviceType deviceType11 = BleApiManager.getInstance(context).getDeviceType();
                            DeviceType deviceType12 = DeviceType.LUNARFIT;
                            if (deviceType11 == deviceType12 && i == 1) {
                                Bitmap decodeResource26 = BitmapFactory.decodeResource(context.getResources(), R.drawable.lunar_fit_dial_style1);
                                Intrinsics.checkNotNullExpressionValue(decodeResource26, "{\n                Bitmap…ial_style1)\n            }");
                                return decodeResource26;
                            } else if (BleApiManager.getInstance(context).getDeviceType() == deviceType12 && i == 2) {
                                Bitmap decodeResource27 = BitmapFactory.decodeResource(context.getResources(), R.drawable.lunar_fit_dial_style2);
                                Intrinsics.checkNotNullExpressionValue(decodeResource27, "{\n                Bitmap…ial_style2)\n            }");
                                return decodeResource27;
                            } else if (BleApiManager.getInstance(context).getDeviceType() == deviceType12 && i == 3) {
                                Bitmap decodeResource28 = BitmapFactory.decodeResource(context.getResources(), R.drawable.lunar_fit_dial_style3);
                                Intrinsics.checkNotNullExpressionValue(decodeResource28, "{\n                Bitmap…ial_style3)\n            }");
                                return decodeResource28;
                            } else if (BleApiManager.getInstance(context).getDeviceType() == deviceType12 && i == 4) {
                                Bitmap decodeResource29 = BitmapFactory.decodeResource(context.getResources(), R.drawable.lunar_fit_dial_style4);
                                Intrinsics.checkNotNullExpressionValue(decodeResource29, "{\n                Bitmap…ial_style4)\n            }");
                                return decodeResource29;
                            } else if (BleApiManager.getInstance(context).getDeviceType() == deviceType12 && i == 5) {
                                Bitmap decodeResource30 = BitmapFactory.decodeResource(context.getResources(), R.drawable.lunar_fit_dial_style5);
                                Intrinsics.checkNotNullExpressionValue(decodeResource30, "{\n                Bitmap…ial_style5)\n            }");
                                return decodeResource30;
                            } else {
                                Bitmap decodeResource31 = BitmapFactory.decodeResource(context.getResources(), R.drawable.matrix_dial_style1);
                                Intrinsics.checkNotNullExpressionValue(decodeResource31, "{\n                Bitmap…ial_style1)\n            }");
                                return decodeResource31;
                            }
                        }
                    }
                }
            }
        }
    }

    public final String getTAG() {
        return f6143a;
    }

    public final boolean isCADevice(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.cove_ca0), false) || SessionManager.getInstance(context).getDeviceType().equals(context.getResources().getString(R.string.cove_ca3)) || SessionManager.getInstance(context).getDeviceType().equals(context.getResources().getString(R.string.cove_ca3_bt)) || SessionManager.getInstance(context).getDeviceType().equals(context.getResources().getString(R.string.cove_ca2)) || SessionManager.getInstance(context).getDeviceType().equals(context.getResources().getString(R.string.cove_ca5_wave_style)) || SessionManager.getInstance(context).getDeviceType().equals(context.getResources().getString(R.string.cove_ca5_wave_play)) || SessionManager.getInstance(context).getDeviceType().equals(context.getResources().getString(R.string.cove_ca5_wave_beat)) || SessionManager.getInstance(context).getDeviceType().equals(context.getResources().getString(R.string.cove_ca3_wave_cosmos)) || SessionManager.getInstance(context).getDeviceType().equals(context.getResources().getString(R.string.cove_ca3_bt_wave_cosmsos_pro)) || SessionManager.getInstance(context).getDeviceType().equals(context.getResources().getString(R.string.cove_ca3_bt_stormpro_call)) || SessionManager.getInstance(context).getDeviceType().equals(context.getResources().getString(R.string.cove_ulc3_wave_smart)) || SessionManager.getInstance(context).getDeviceType().equals(context.getResources().getString(R.string.cove_ulc2_wave_beat_plus)) || SessionManager.getInstance(context).getDeviceType().equals(context.getResources().getString(R.string.cove_ulc2_wave_style_plus)) || SessionManager.getInstance(context).getDeviceType().equals(context.getResources().getString(R.string.cove_ulc2_wave_smart_plus)) || SessionManager.getInstance(context).getDeviceType().equals(context.getResources().getString(R.string.cove_ulc2_wave_lync)) || SessionManager.getInstance(context).getDeviceType().equals(context.getResources().getString(R.string.ultima_call)) || SessionManager.getInstance(context).getDeviceType().equals(context.getResources().getString(R.string.ultima_connect)) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.wave_call_2), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.storm_call_2), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.wave_astra), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.wave_sigma), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.wave_neo_plus), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.wave_active), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.ultima_prism), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.ultima_chronos), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.wave_convex), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.lunar_orb), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.lunar_prime), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.primia_curv), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.xtend_pro_2), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.storm_pro_call_2), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.wave_call_3), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.storm_call_3), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.wave_astra_2), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.wave_sigma_3), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.wave_chase), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.wave_regal), false);
    }

    public final boolean isCAULCDevice(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return SessionManager.getInstance(context).getDeviceType().equals(context.getResources().getString(R.string.cove_ca5_wave_style)) || SessionManager.getInstance(context).getDeviceType().equals(context.getResources().getString(R.string.cove_ca5_wave_play)) || SessionManager.getInstance(context).getDeviceType().equals(context.getResources().getString(R.string.cove_ca5_wave_beat)) || SessionManager.getInstance(context).getDeviceType().equals(context.getResources().getString(R.string.cove_ulc3_wave_smart)) || SessionManager.getInstance(context).getDeviceType().equals(context.getResources().getString(R.string.cove_ulc2_wave_beat_plus)) || SessionManager.getInstance(context).getDeviceType().equals(context.getResources().getString(R.string.cove_ulc2_wave_style_plus)) || SessionManager.getInstance(context).getDeviceType().equals(context.getResources().getString(R.string.cove_ulc2_wave_smart_plus)) || SessionManager.getInstance(context).getDeviceType().equals(context.getResources().getString(R.string.cove_ulc2_wave_lync)) || SessionManager.getInstance(context).getDeviceType().equals(context.getResources().getString(R.string.ultima_call)) || SessionManager.getInstance(context).getDeviceType().equals(context.getResources().getString(R.string.ultima_connect)) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.wave_call_2), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.storm_call_2), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.wave_astra), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.wave_sigma), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.wave_neo_plus), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.wave_active), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.ultima_prism), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.ultima_chronos), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.wave_convex), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.lunar_orb), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.lunar_prime), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.primia_curv), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.xtend_pro_2), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.storm_pro_call_2), false);
    }

    public final boolean isCZDevice(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.cove_cz1), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.cove_cz2), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.cove_cz3), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.cove_wave_prime), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.cove_wave_elite), false);
    }

    public final boolean isDefaultWatchfaceNotSupports(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        DeviceUtils.Companion companion = DeviceUtils.Companion;
        return companion.isMatrixDevice(context) || companion.isSmaDevice(context) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(com.coveiot.android.devicemodels.R.string.wave_neo), false);
    }

    public final boolean isIDODevice(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.ido_select), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.ido_connect), false);
    }

    public final boolean isMatrixDevice(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.matrix_device), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.wave_force), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.wave_armour), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.lunar_fit), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.wave_armour2), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.wave_force2), false);
    }

    public final boolean isRuggedDevice(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.wave_force), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.wave_armour), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.lunar_fit), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.wave_armour2), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.wave_force2), false);
    }

    public final boolean isTouchElxDevice(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.wave_call_plus), false);
    }

    public final boolean isWaveBeatCallGujaratTitansFirmware(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        if (BleApiManager.getInstance(context) == null || BleApiManager.getInstance(context).getDeviceType() == null || BleApiManager.getInstance(context).getDeviceType() != DeviceType.ULC2_WAVE_BEAT_PLUS) {
            return false;
        }
        BleDeviceInfo bleDeviceInfo = (BleDeviceInfo) new Gson().fromJson(SessionManager.getInstance(context).getBleDeviceInfo(), (Class<Object>) BleDeviceInfo.class);
        String firmwareRevision = bleDeviceInfo != null ? bleDeviceInfo.getFirmwareRevision() : null;
        LogHelper.i("WatchFacePagingAdapter", "firmWareVersion -- " + firmwareRevision);
        return firmwareRevision != null && m.equals(firmwareRevision, "v0.00.22", true);
    }

    public final boolean isWaveStyleCallTCEFirmware(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        if (BleApiManager.getInstance(context) == null || BleApiManager.getInstance(context).getDeviceType() == null || BleApiManager.getInstance(context).getDeviceType() != DeviceType.ULC2_WAVE_STYLE_PLUS) {
            return false;
        }
        BleDeviceInfo bleDeviceInfo = (BleDeviceInfo) new Gson().fromJson(SessionManager.getInstance(context).getBleDeviceInfo(), (Class<Object>) BleDeviceInfo.class);
        String firmwareRevision = bleDeviceInfo != null ? bleDeviceInfo.getFirmwareRevision() : null;
        LogHelper.i("WatchFacePagingAdapter", "firmWareVersion -- " + firmwareRevision);
        return firmwareRevision != null && m.equals(firmwareRevision, "v0.00.11", true);
    }

    public final void startUCrop(@NotNull Activity activity, @Nullable Fragment fragment, @Nullable Uri uri, int i) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Uri fromFile = Uri.fromFile(new File(activity.getCacheDir(), "watch_face.jpg"));
        UCrop.Options options = new UCrop.Options();
        options.setCircleDimmedLayer(i == 0);
        options.setShowCropGrid(false);
        options.setShowCropFrame(i != 0);
        options.setHideBottomControls(true);
        options.setCropGridColumnCount(0);
        options.setCropGridRowCount(0);
        if (1 == i) {
            options.setCompressionFormat(Bitmap.CompressFormat.JPEG);
        }
        options.setMaxBitmapSize(1024);
        if (uri != null) {
            if (fragment == null) {
                UCrop.of(uri, fromFile).withOptions(options).withAspectRatio(1.0f, 1.0f).start(activity);
            } else {
                UCrop.of(uri, fromFile).withOptions(options).withAspectRatio(1.0f, 1.0f).start(activity, fragment);
            }
        }
    }

    public final void startZoomCrop(@Nullable Uri uri, int i, int i2, @NotNull Fragment fragment, @NotNull Context context) {
        Intrinsics.checkNotNullParameter(fragment, "fragment");
        Intrinsics.checkNotNullParameter(context, "context");
        CropImage.activity(uri).setGuidelines(CropImageView.Guidelines.ON).setRequestedSize(i2, i, CropImageView.RequestSizeOptions.RESIZE_EXACT).setMinCropResultSize(i2, i).setAspectRatio(i2, i).start(context, fragment);
    }

    @Nullable
    public final Uri takeCameraPictureFromActivity(@NotNull Activity activity) {
        File file;
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        if (intent.resolveActivity(activity.getPackageManager()) != null) {
            try {
                file = a(activity);
            } catch (IOException unused) {
                file = null;
            }
            if (file != null) {
                Uri uriForFile = FileProvider.getUriForFile(activity, activity.getPackageName() + ".provider", file);
                intent.putExtra("output", uriForFile);
                activity.startActivityForResult(intent, 1001);
                return uriForFile;
            }
            return null;
        }
        return null;
    }

    @Nullable
    public final Uri takeCameraPictureFromFragment(@NotNull Fragment fragment) {
        File file;
        Intrinsics.checkNotNullParameter(fragment, "fragment");
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        if (intent.resolveActivity(fragment.requireActivity().getPackageManager()) != null) {
            try {
                FragmentActivity requireActivity = fragment.requireActivity();
                Intrinsics.checkNotNullExpressionValue(requireActivity, "fragment.requireActivity()");
                file = a(requireActivity);
            } catch (IOException unused) {
                file = null;
            }
            if (file != null) {
                Uri uriForFile = FileProvider.getUriForFile(fragment.requireActivity(), fragment.requireActivity().getPackageName() + ".provider", file);
                intent.putExtra("output", uriForFile);
                fragment.startActivityForResult(intent, 1001);
                return uriForFile;
            }
            return null;
        }
        return null;
    }

    public final void startUCrop(@NotNull Activity activity, @Nullable Fragment fragment, @Nullable Uri uri, int i, int i2, int i3, float f, float f2, boolean z) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Uri fromFile = Uri.fromFile(new File(activity.getCacheDir(), "watch_face.jpg"));
        UCrop.Options options = new UCrop.Options();
        options.setCircleDimmedLayer(i3 == 0);
        options.setShowCropGrid(false);
        options.setShowCropFrame(i3 != 0);
        options.setHideBottomControls(true);
        options.setCropGridColumnCount(3);
        options.setAllowedGestures(0, 0, 0);
        options.setMaxScaleMultiplier(1.0001f);
        options.setShowCropFrame(true);
        options.setCropGridRowCount(0);
        if (1 == i3) {
            options.setCompressionFormat(Bitmap.CompressFormat.JPEG);
        }
        if (z) {
            options.setMaxBitmapSize(1024);
        }
        if (uri != null) {
            if (fragment == null) {
                UCrop.of(uri, fromFile).withOptions(options).withAspectRatio(f, f2).withMaxResultSize(i2, i).start(activity);
            } else {
                UCrop.of(uri, fromFile).withOptions(options).withAspectRatio(f, f2).withMaxResultSize(i2, i).start(activity, fragment);
            }
        }
    }
}
