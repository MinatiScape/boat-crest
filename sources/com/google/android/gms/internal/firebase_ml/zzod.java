package com.google.android.gms.internal.firebase_ml;

import com.goodix.ble.libcomx.task.ITask;
import com.google.mlkit.common.MlKitException;
import com.jieli.jl_rcsp.constant.Command;
import com.realsil.sdk.dfu.DfuException;
import com.touchgui.sdk.TGEventListener;
import kotlin.text.Typography;
/* loaded from: classes7.dex */
public enum zzod implements zzxc {
    UNKNOWN_EVENT(0),
    ON_DEVICE_FACE_DETECT(1),
    ON_DEVICE_FACE_CREATE(2),
    ON_DEVICE_FACE_CLOSE(3),
    ON_DEVICE_TEXT_DETECT(11),
    ON_DEVICE_TEXT_CREATE(12),
    ON_DEVICE_TEXT_CLOSE(13),
    ON_DEVICE_BARCODE_DETECT(21),
    ON_DEVICE_BARCODE_CREATE(22),
    ON_DEVICE_BARCODE_CLOSE(23),
    ON_DEVICE_IMAGE_LABEL_DETECT(141),
    ON_DEVICE_IMAGE_LABEL_CREATE(142),
    ON_DEVICE_IMAGE_LABEL_CLOSE(143),
    ON_DEVICE_SMART_REPLY_DETECT(151),
    ON_DEVICE_SMART_REPLY_CREATE(152),
    ON_DEVICE_SMART_REPLY_CLOSE(153),
    ON_DEVICE_SMART_REPLY_BLACKLIST_UPDATE(154),
    ON_DEVICE_LANGUAGE_IDENTIFICATION_DETECT(161),
    ON_DEVICE_LANGUAGE_IDENTIFICATION_CREATE(162),
    ON_DEVICE_LANGUAGE_IDENTIFICATION_LOAD(164),
    ON_DEVICE_LANGUAGE_IDENTIFICATION_CLOSE(163),
    ON_DEVICE_TRANSLATOR_TRANSLATE(171),
    ON_DEVICE_TRANSLATOR_CREATE(172),
    ON_DEVICE_TRANSLATOR_LOAD(173),
    ON_DEVICE_TRANSLATOR_CLOSE(174),
    ON_DEVICE_TRANSLATOR_DOWNLOAD(175),
    ON_DEVICE_TC_ANNOTATE(Command.CMD_PHONE_NUMBER_PLAY_MODE),
    ON_DEVICE_TC_CREATE(242),
    ON_DEVICE_TC_LOAD(243),
    ON_DEVICE_TC_CLOSE(244),
    ON_DEVICE_TC_DOWNLOAD(245),
    ON_DEVICE_OBJECT_CREATE(191),
    ON_DEVICE_OBJECT_LOAD(192),
    ON_DEVICE_OBJECT_INFERENCE(193),
    ON_DEVICE_OBJECT_CLOSE(194),
    ON_DEVICE_DI_CREATE(311),
    ON_DEVICE_DI_LOAD(312),
    ON_DEVICE_DI_DOWNLOAD(313),
    ON_DEVICE_DI_RECOGNIZE(314),
    ON_DEVICE_DI_CLOSE(315),
    ON_DEVICE_POSE_CREATE(com.veryfit.multi.nativeprotocol.b.f1),
    ON_DEVICE_POSE_LOAD(com.veryfit.multi.nativeprotocol.b.g1),
    ON_DEVICE_POSE_INFERENCE(com.veryfit.multi.nativeprotocol.b.h1),
    ON_DEVICE_POSE_CLOSE(324),
    ON_DEVICE_SEGMENTATION_CREATE(com.veryfit.multi.nativeprotocol.b.l1),
    ON_DEVICE_SEGMENTATION_LOAD(com.veryfit.multi.nativeprotocol.b.m1),
    ON_DEVICE_SEGMENTATION_INFERENCE(com.veryfit.multi.nativeprotocol.b.n1),
    ON_DEVICE_SEGMENTATION_CLOSE(com.veryfit.multi.nativeprotocol.b.o1),
    CUSTOM_OBJECT_CREATE(341),
    CUSTOM_OBJECT_LOAD(ITask.EVT_FINISH),
    CUSTOM_OBJECT_INFERENCE(343),
    CUSTOM_OBJECT_CLOSE(344),
    CUSTOM_IMAGE_LABEL_CREATE(351),
    CUSTOM_IMAGE_LABEL_LOAD(com.veryfit.multi.nativeprotocol.b.s1),
    CUSTOM_IMAGE_LABEL_DETECT(com.veryfit.multi.nativeprotocol.b.t1),
    CUSTOM_IMAGE_LABEL_CLOSE(354),
    CLOUD_FACE_DETECT(31),
    CLOUD_FACE_CREATE(32),
    CLOUD_FACE_CLOSE(33),
    CLOUD_CROP_HINTS_CREATE(41),
    CLOUD_CROP_HINTS_DETECT(42),
    CLOUD_CROP_HINTS_CLOSE(43),
    CLOUD_DOCUMENT_TEXT_CREATE(51),
    CLOUD_DOCUMENT_TEXT_DETECT(52),
    CLOUD_DOCUMENT_TEXT_CLOSE(53),
    CLOUD_IMAGE_PROPERTIES_CREATE(61),
    CLOUD_IMAGE_PROPERTIES_DETECT(62),
    CLOUD_IMAGE_PROPERTIES_CLOSE(63),
    CLOUD_IMAGE_LABEL_CREATE(71),
    CLOUD_IMAGE_LABEL_DETECT(72),
    CLOUD_IMAGE_LABEL_CLOSE(73),
    CLOUD_LANDMARK_CREATE(81),
    CLOUD_LANDMARK_DETECT(82),
    CLOUD_LANDMARK_CLOSE(83),
    CLOUD_LOGO_CREATE(91),
    CLOUD_LOGO_DETECT(92),
    CLOUD_LOGO_CLOSE(93),
    CLOUD_SAFE_SEARCH_CREATE(111),
    CLOUD_SAFE_SEARCH_DETECT(112),
    CLOUD_SAFE_SEARCH_CLOSE(113),
    CLOUD_TEXT_CREATE(121),
    CLOUD_TEXT_DETECT(122),
    CLOUD_TEXT_CLOSE(123),
    CLOUD_WEB_SEARCH_CREATE(131),
    CLOUD_WEB_SEARCH_DETECT(132),
    CLOUD_WEB_SEARCH_CLOSE(133),
    CUSTOM_MODEL_RUN(102),
    CUSTOM_MODEL_CREATE(103),
    CUSTOM_MODEL_CLOSE(104),
    CUSTOM_MODEL_LOAD(105),
    AUTOML_IMAGE_LABELING_RUN(181),
    AUTOML_IMAGE_LABELING_CREATE(182),
    AUTOML_IMAGE_LABELING_CLOSE(183),
    AUTOML_IMAGE_LABELING_LOAD(184),
    MODEL_DOWNLOAD(100),
    MODEL_UPDATE(101),
    REMOTE_MODEL_IS_DOWNLOADED(251),
    REMOTE_MODEL_DELETE_ON_DEVICE(252),
    ACCELERATION_ANALYTICS(260),
    AGGREGATED_AUTO_ML_IMAGE_LABELING_INFERENCE(200),
    AGGREGATED_CUSTOM_MODEL_INFERENCE(201),
    AGGREGATED_ON_DEVICE_BARCODE_DETECTION(202),
    AGGREGATED_ON_DEVICE_FACE_DETECTION(203),
    AGGREGATED_ON_DEVICE_IMAGE_LABEL_DETECTION(204),
    AGGREGATED_ON_DEVICE_OBJECT_INFERENCE(205),
    AGGREGATED_ON_DEVICE_TEXT_DETECTION(206),
    AGGREGATED_ON_DEVICE_POSE_DETECTION(MlKitException.CODE_SCANNER_GOOGLE_PLAY_SERVICES_VERSION_TOO_OLD),
    AGGREGATED_ON_DEVICE_SEGMENTATION(Command.CMD_NOTIFY_DEVICE_APP_INFO),
    AGGREGATED_CUSTOM_OBJECT_INFERENCE(209),
    AGGREGATED_CUSTOM_IMAGE_LABEL_DETECTION(Command.CMD_RECEIVE_SPEECH_CANCEL),
    REMOTE_CONFIG_FETCH(DfuException.ERROR_READ_APP_INFO_ERROR),
    REMOTE_CONFIG_ACTIVATE(272),
    REMOTE_CONFIG_FRC_FETCH(DfuException.ERROR_DFU_SPP_OTA_NOT_SUPPORTED),
    INSTALLATION_ID_INIT(TGEventListener.SLEEP_STOP),
    INSTALLATION_ID_REGISTER_NEW_ID(TGEventListener.OTA_COMPLETED),
    INSTALLATION_ID_REFRESH_TEMPORARY_TOKEN(TGEventListener.WATCH_FACE_INSTALLED),
    INSTALLATION_ID_FIS_CREATE_INSTALLATION(301),
    INSTALLATION_ID_FIS_GENERATE_AUTH_TOKEN(302);
    
    private static final zzxf<zzod> zzac = new zzxf<zzod>() { // from class: com.google.android.gms.internal.firebase_ml.x2
    };
    private final int value;

    zzod(int i) {
        this.value = i;
    }

    public static zzod zzaw(int i) {
        switch (i) {
            case 0:
                return UNKNOWN_EVENT;
            case 1:
                return ON_DEVICE_FACE_DETECT;
            case 2:
                return ON_DEVICE_FACE_CREATE;
            case 3:
                return ON_DEVICE_FACE_CLOSE;
            case 11:
                return ON_DEVICE_TEXT_DETECT;
            case 12:
                return ON_DEVICE_TEXT_CREATE;
            case 13:
                return ON_DEVICE_TEXT_CLOSE;
            case 21:
                return ON_DEVICE_BARCODE_DETECT;
            case 22:
                return ON_DEVICE_BARCODE_CREATE;
            case 23:
                return ON_DEVICE_BARCODE_CLOSE;
            case 31:
                return CLOUD_FACE_DETECT;
            case 32:
                return CLOUD_FACE_CREATE;
            case 33:
                return CLOUD_FACE_CLOSE;
            case 41:
                return CLOUD_CROP_HINTS_CREATE;
            case 42:
                return CLOUD_CROP_HINTS_DETECT;
            case 43:
                return CLOUD_CROP_HINTS_CLOSE;
            case 51:
                return CLOUD_DOCUMENT_TEXT_CREATE;
            case 52:
                return CLOUD_DOCUMENT_TEXT_DETECT;
            case 53:
                return CLOUD_DOCUMENT_TEXT_CLOSE;
            case 61:
                return CLOUD_IMAGE_PROPERTIES_CREATE;
            case 62:
                return CLOUD_IMAGE_PROPERTIES_DETECT;
            case 63:
                return CLOUD_IMAGE_PROPERTIES_CLOSE;
            case 71:
                return CLOUD_IMAGE_LABEL_CREATE;
            case 72:
                return CLOUD_IMAGE_LABEL_DETECT;
            case 73:
                return CLOUD_IMAGE_LABEL_CLOSE;
            case 81:
                return CLOUD_LANDMARK_CREATE;
            case 82:
                return CLOUD_LANDMARK_DETECT;
            case 83:
                return CLOUD_LANDMARK_CLOSE;
            case 91:
                return CLOUD_LOGO_CREATE;
            case 92:
                return CLOUD_LOGO_DETECT;
            case 93:
                return CLOUD_LOGO_CLOSE;
            case 100:
                return MODEL_DOWNLOAD;
            case 101:
                return MODEL_UPDATE;
            case 102:
                return CUSTOM_MODEL_RUN;
            case 103:
                return CUSTOM_MODEL_CREATE;
            case 104:
                return CUSTOM_MODEL_CLOSE;
            case 105:
                return CUSTOM_MODEL_LOAD;
            case 111:
                return CLOUD_SAFE_SEARCH_CREATE;
            case 112:
                return CLOUD_SAFE_SEARCH_DETECT;
            case 113:
                return CLOUD_SAFE_SEARCH_CLOSE;
            case 121:
                return CLOUD_TEXT_CREATE;
            case 122:
                return CLOUD_TEXT_DETECT;
            case 123:
                return CLOUD_TEXT_CLOSE;
            case 131:
                return CLOUD_WEB_SEARCH_CREATE;
            case 132:
                return CLOUD_WEB_SEARCH_DETECT;
            case 133:
                return CLOUD_WEB_SEARCH_CLOSE;
            case 141:
                return ON_DEVICE_IMAGE_LABEL_DETECT;
            case 142:
                return ON_DEVICE_IMAGE_LABEL_CREATE;
            case 143:
                return ON_DEVICE_IMAGE_LABEL_CLOSE;
            case 151:
                return ON_DEVICE_SMART_REPLY_DETECT;
            case 152:
                return ON_DEVICE_SMART_REPLY_CREATE;
            case 153:
                return ON_DEVICE_SMART_REPLY_CLOSE;
            case 154:
                return ON_DEVICE_SMART_REPLY_BLACKLIST_UPDATE;
            case 161:
                return ON_DEVICE_LANGUAGE_IDENTIFICATION_DETECT;
            case 162:
                return ON_DEVICE_LANGUAGE_IDENTIFICATION_CREATE;
            case 163:
                return ON_DEVICE_LANGUAGE_IDENTIFICATION_CLOSE;
            case 164:
                return ON_DEVICE_LANGUAGE_IDENTIFICATION_LOAD;
            case 171:
                return ON_DEVICE_TRANSLATOR_TRANSLATE;
            case 172:
                return ON_DEVICE_TRANSLATOR_CREATE;
            case 173:
                return ON_DEVICE_TRANSLATOR_LOAD;
            case 174:
                return ON_DEVICE_TRANSLATOR_CLOSE;
            case 175:
                return ON_DEVICE_TRANSLATOR_DOWNLOAD;
            case 181:
                return AUTOML_IMAGE_LABELING_RUN;
            case 182:
                return AUTOML_IMAGE_LABELING_CREATE;
            case 183:
                return AUTOML_IMAGE_LABELING_CLOSE;
            case 184:
                return AUTOML_IMAGE_LABELING_LOAD;
            case 191:
                return ON_DEVICE_OBJECT_CREATE;
            case 192:
                return ON_DEVICE_OBJECT_LOAD;
            case 193:
                return ON_DEVICE_OBJECT_INFERENCE;
            case 194:
                return ON_DEVICE_OBJECT_CLOSE;
            case 200:
                return AGGREGATED_AUTO_ML_IMAGE_LABELING_INFERENCE;
            case 201:
                return AGGREGATED_CUSTOM_MODEL_INFERENCE;
            case 202:
                return AGGREGATED_ON_DEVICE_BARCODE_DETECTION;
            case 203:
                return AGGREGATED_ON_DEVICE_FACE_DETECTION;
            case 204:
                return AGGREGATED_ON_DEVICE_IMAGE_LABEL_DETECTION;
            case 205:
                return AGGREGATED_ON_DEVICE_OBJECT_INFERENCE;
            case 206:
                return AGGREGATED_ON_DEVICE_TEXT_DETECTION;
            case MlKitException.CODE_SCANNER_GOOGLE_PLAY_SERVICES_VERSION_TOO_OLD /* 207 */:
                return AGGREGATED_ON_DEVICE_POSE_DETECTION;
            case Command.CMD_NOTIFY_DEVICE_APP_INFO /* 208 */:
                return AGGREGATED_ON_DEVICE_SEGMENTATION;
            case 209:
                return AGGREGATED_CUSTOM_OBJECT_INFERENCE;
            case Command.CMD_RECEIVE_SPEECH_CANCEL /* 210 */:
                return AGGREGATED_CUSTOM_IMAGE_LABEL_DETECTION;
            case Command.CMD_PHONE_NUMBER_PLAY_MODE /* 241 */:
                return ON_DEVICE_TC_ANNOTATE;
            case 242:
                return ON_DEVICE_TC_CREATE;
            case 243:
                return ON_DEVICE_TC_LOAD;
            case 244:
                return ON_DEVICE_TC_CLOSE;
            case 245:
                return ON_DEVICE_TC_DOWNLOAD;
            case 251:
                return REMOTE_MODEL_IS_DOWNLOADED;
            case 252:
                return REMOTE_MODEL_DELETE_ON_DEVICE;
            case 260:
                return ACCELERATION_ANALYTICS;
            case DfuException.ERROR_READ_APP_INFO_ERROR /* 271 */:
                return REMOTE_CONFIG_FETCH;
            case 272:
                return REMOTE_CONFIG_ACTIVATE;
            case DfuException.ERROR_DFU_SPP_OTA_NOT_SUPPORTED /* 281 */:
                return REMOTE_CONFIG_FRC_FETCH;
            case TGEventListener.SLEEP_STOP /* 291 */:
                return INSTALLATION_ID_INIT;
            case TGEventListener.OTA_COMPLETED /* 292 */:
                return INSTALLATION_ID_REGISTER_NEW_ID;
            case TGEventListener.WATCH_FACE_INSTALLED /* 293 */:
                return INSTALLATION_ID_REFRESH_TEMPORARY_TOKEN;
            case 301:
                return INSTALLATION_ID_FIS_CREATE_INSTALLATION;
            case 302:
                return INSTALLATION_ID_FIS_GENERATE_AUTH_TOKEN;
            case 311:
                return ON_DEVICE_DI_CREATE;
            case 312:
                return ON_DEVICE_DI_LOAD;
            case 313:
                return ON_DEVICE_DI_DOWNLOAD;
            case 314:
                return ON_DEVICE_DI_RECOGNIZE;
            case 315:
                return ON_DEVICE_DI_CLOSE;
            case com.veryfit.multi.nativeprotocol.b.f1 /* 321 */:
                return ON_DEVICE_POSE_CREATE;
            case com.veryfit.multi.nativeprotocol.b.g1 /* 322 */:
                return ON_DEVICE_POSE_LOAD;
            case com.veryfit.multi.nativeprotocol.b.h1 /* 323 */:
                return ON_DEVICE_POSE_INFERENCE;
            case 324:
                return ON_DEVICE_POSE_CLOSE;
            case com.veryfit.multi.nativeprotocol.b.l1 /* 331 */:
                return ON_DEVICE_SEGMENTATION_CREATE;
            case com.veryfit.multi.nativeprotocol.b.m1 /* 332 */:
                return ON_DEVICE_SEGMENTATION_LOAD;
            case com.veryfit.multi.nativeprotocol.b.n1 /* 333 */:
                return ON_DEVICE_SEGMENTATION_INFERENCE;
            case com.veryfit.multi.nativeprotocol.b.o1 /* 334 */:
                return ON_DEVICE_SEGMENTATION_CLOSE;
            case 341:
                return CUSTOM_OBJECT_CREATE;
            case ITask.EVT_FINISH /* 342 */:
                return CUSTOM_OBJECT_LOAD;
            case 343:
                return CUSTOM_OBJECT_INFERENCE;
            case 344:
                return CUSTOM_OBJECT_CLOSE;
            case 351:
                return CUSTOM_IMAGE_LABEL_CREATE;
            case com.veryfit.multi.nativeprotocol.b.s1 /* 352 */:
                return CUSTOM_IMAGE_LABEL_LOAD;
            case com.veryfit.multi.nativeprotocol.b.t1 /* 353 */:
                return CUSTOM_IMAGE_LABEL_DETECT;
            case 354:
                return CUSTOM_IMAGE_LABEL_CLOSE;
            default:
                return null;
        }
    }

    public static zzxe zzf() {
        return w2.f8746a;
    }

    @Override // java.lang.Enum
    public final String toString() {
        return "<" + zzod.class.getName() + '@' + Integer.toHexString(System.identityHashCode(this)) + " number=" + this.value + " name=" + name() + Typography.greater;
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzxc
    public final int zzd() {
        return this.value;
    }
}
