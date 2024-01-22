package com.google.android.gms.internal.mlkit_common;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.goodix.ble.libcomx.task.ITask;
import com.google.mlkit.common.MlKitException;
import com.jieli.jl_rcsp.constant.Command;
import com.realsil.sdk.dfu.DfuConstants;
import com.realsil.sdk.dfu.DfuException;
import com.realsil.sdk.dfu.utils.DfuAdapter;
import com.sifli.ezip.NeuQuant;
import com.touchgui.sdk.TGEventListener;
import org.bouncycastle.math.Primes;
/* loaded from: classes8.dex */
public enum zzln implements zzbm {
    UNKNOWN_EVENT(0),
    ON_DEVICE_FACE_DETECT(1),
    ON_DEVICE_FACE_CREATE(2),
    ON_DEVICE_FACE_CLOSE(3),
    ON_DEVICE_FACE_LOAD(4),
    ON_DEVICE_TEXT_DETECT(11),
    ON_DEVICE_TEXT_CREATE(12),
    ON_DEVICE_TEXT_CLOSE(13),
    ON_DEVICE_TEXT_LOAD(14),
    ON_DEVICE_BARCODE_DETECT(21),
    ON_DEVICE_BARCODE_CREATE(22),
    ON_DEVICE_BARCODE_CLOSE(23),
    ON_DEVICE_BARCODE_LOAD(24),
    ON_DEVICE_IMAGE_LABEL_DETECT(141),
    ON_DEVICE_IMAGE_LABEL_CREATE(142),
    ON_DEVICE_IMAGE_LABEL_CLOSE(143),
    ON_DEVICE_IMAGE_LABEL_LOAD(144),
    ON_DEVICE_SMART_REPLY_DETECT(151),
    ON_DEVICE_SMART_REPLY_CREATE(152),
    ON_DEVICE_SMART_REPLY_CLOSE(153),
    ON_DEVICE_SMART_REPLY_BLACKLIST_UPDATE(154),
    ON_DEVICE_SMART_REPLY_LOAD(155),
    ON_DEVICE_LANGUAGE_IDENTIFICATION_DETECT(161),
    ON_DEVICE_LANGUAGE_IDENTIFICATION_CREATE(162),
    ON_DEVICE_LANGUAGE_IDENTIFICATION_LOAD(164),
    ON_DEVICE_LANGUAGE_IDENTIFICATION_CLOSE(163),
    ON_DEVICE_TRANSLATOR_TRANSLATE(171),
    ON_DEVICE_TRANSLATOR_CREATE(172),
    ON_DEVICE_TRANSLATOR_LOAD(173),
    ON_DEVICE_TRANSLATOR_CLOSE(174),
    ON_DEVICE_TRANSLATOR_DOWNLOAD(175),
    ON_DEVICE_ENTITY_EXTRACTION_ANNOTATE(Command.CMD_PHONE_NUMBER_PLAY_MODE),
    ON_DEVICE_ENTITY_EXTRACTION_CREATE(242),
    ON_DEVICE_ENTITY_EXTRACTION_LOAD(243),
    ON_DEVICE_ENTITY_EXTRACTION_CLOSE(244),
    ON_DEVICE_ENTITY_EXTRACTION_DOWNLOAD(245),
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
    ON_DEVICE_POSE_PRELOAD(com.veryfit.multi.nativeprotocol.b.i1),
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
    PIPELINE_ACCELERATION_ANALYTICS(261),
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
    AGGREGATED_ON_DEVICE_EXPLICIT_CONTENT_DETECTION(Primes.SMALL_FACTOR_LIMIT),
    AGGREGATED_ON_DEVICE_FACE_MESH_DETECTION(212),
    AGGREGATED_ON_DEVICE_IMAGE_QUALITY_ANALYSIS_DETECTION(Command.CMD_GET_LOW_LATENCY_SETTINGS),
    AGGREGATED_ON_DEVICE_IMAGE_CAPTIONING_INFERENCE(Command.CMD_GET_EXTERNAL_FLASH_MSG),
    AGGREGATED_ON_DEVICE_DOCUMENT_DETECT_PROCESS(215),
    AGGREGATED_ON_DEVICE_DOCUMENT_CROP_PROCESS(Command.CMD_SET_DEVICE_STORAGE),
    AGGREGATED_ON_DEVICE_DOCUMENT_ENHANCE_PROCESS(Command.CMD_GET_DEVICE_CONFIG_INFO),
    REMOTE_CONFIG_FETCH(DfuException.ERROR_READ_APP_INFO_ERROR),
    REMOTE_CONFIG_ACTIVATE(272),
    REMOTE_CONFIG_LOAD(273),
    REMOTE_CONFIG_FRC_FETCH(DfuException.ERROR_DFU_SPP_OTA_NOT_SUPPORTED),
    INSTALLATION_ID_INIT(TGEventListener.SLEEP_STOP),
    INSTALLATION_ID_REGISTER_NEW_ID(TGEventListener.OTA_COMPLETED),
    INSTALLATION_ID_REFRESH_TEMPORARY_TOKEN(TGEventListener.WATCH_FACE_INSTALLED),
    INSTALLATION_ID_FIS_CREATE_INSTALLATION(301),
    INSTALLATION_ID_FIS_GENERATE_AUTH_TOKEN(302),
    INPUT_IMAGE_CONSTRUCTION(361),
    HANDLE_LEAKED(371),
    CAMERA_SOURCE(381),
    OPTIONAL_MODULE_IMAGE_LABELING(391),
    OPTIONAL_MODULE_LANGUAGE_ID(401),
    OPTIONAL_MODULE_LANGUAGE_ID_CREATE(402),
    OPTIONAL_MODULE_LANGUAGE_ID_INIT(403),
    OPTIONAL_MODULE_LANGUAGE_ID_INFERENCE(404),
    OPTIONAL_MODULE_LANGUAGE_ID_RELEASE(com.veryfit.multi.nativeprotocol.b.z1),
    OPTIONAL_MODULE_NLCLASSIFIER(com.veryfit.multi.nativeprotocol.b.D1),
    OPTIONAL_MODULE_NLCLASSIFIER_CREATE(com.veryfit.multi.nativeprotocol.b.E1),
    OPTIONAL_MODULE_NLCLASSIFIER_INIT(com.veryfit.multi.nativeprotocol.b.F1),
    OPTIONAL_MODULE_NLCLASSIFIER_INFERENCE(com.veryfit.multi.nativeprotocol.b.G1),
    OPTIONAL_MODULE_NLCLASSIFIER_RELEASE(415),
    NLCLASSIFIER_CLIENT_LIBRARY(421),
    NLCLASSIFIER_CLIENT_LIBRARY_CREATE(TypedValues.CycleType.TYPE_CUSTOM_WAVE_SHAPE),
    NLCLASSIFIER_CLIENT_LIBRARY_CLASSIFY(TypedValues.CycleType.TYPE_WAVE_PERIOD),
    NLCLASSIFIER_CLIENT_LIBRARY_CLOSE(TypedValues.CycleType.TYPE_WAVE_OFFSET),
    OPTIONAL_MODULE_FACE_DETECTION(441),
    OPTIONAL_MODULE_FACE_DETECTION_CREATE(461),
    OPTIONAL_MODULE_FACE_DETECTION_INIT(462),
    OPTIONAL_MODULE_FACE_DETECTION_INFERENCE(463),
    OPTIONAL_MODULE_FACE_DETECTION_RELEASE(464),
    ACCELERATION_ALLOWLIST_GET(431),
    ACCELERATION_ALLOWLIST_FETCH(432),
    ODML_IMAGE(442),
    OPTIONAL_MODULE_BARCODE_DETECTION(443),
    OPTIONAL_MODULE_BARCODE_DETECTION_CREATE(471),
    OPTIONAL_MODULE_BARCODE_DETECTION_INIT(472),
    OPTIONAL_MODULE_BARCODE_DETECTION_INFERENCE(473),
    OPTIONAL_MODULE_BARCODE_DETECTION_RELEASE(474),
    OPTIONAL_MODULE_BARCODE_DETECTION_INFERENCE_AFTER_RELEASE(475),
    TOXICITY_DETECTION_CREATE_EVENT(451),
    TOXICITY_DETECTION_LOAD_EVENT(452),
    TOXICITY_DETECTION_INFERENCE_EVENT(453),
    TOXICITY_DETECTION_DOWNLOAD_EVENT(454),
    OPTIONAL_MODULE_CUSTOM_IMAGE_LABELING_CREATE(481),
    OPTIONAL_MODULE_CUSTOM_IMAGE_LABELING_INIT(482),
    OPTIONAL_MODULE_CUSTOM_IMAGE_LABELING_INFERENCE(483),
    OPTIONAL_MODULE_CUSTOM_IMAGE_LABELING_RELEASE(484),
    CODE_SCANNER_SCAN_API(NeuQuant.prime2),
    CODE_SCANNER_OPTIONAL_MODULE(492),
    ON_DEVICE_EXPLICIT_CONTENT_CREATE(501),
    ON_DEVICE_EXPLICIT_CONTENT_LOAD(502),
    ON_DEVICE_EXPLICIT_CONTENT_DETECT(503),
    ON_DEVICE_EXPLICIT_CONTENT_CLOSE(504),
    ON_DEVICE_FACE_MESH_CREATE(511),
    ON_DEVICE_FACE_MESH_LOAD(512),
    ON_DEVICE_FACE_MESH_DETECT(513),
    ON_DEVICE_FACE_MESH_CLOSE(514),
    OPTIONAL_MODULE_SMART_REPLY_CREATE(DfuConstants.PROGRESS_START_DFU_PROCESS),
    OPTIONAL_MODULE_SMART_REPLY_INIT(DfuConstants.PROGRESS_HAND_OVER_PROCESSING),
    OPTIONAL_MODULE_SMART_REPLY_INFERENCE(DfuConstants.PROGRESS_PENDING_ACTIVE_IMAGE),
    OPTIONAL_MODULE_SMART_REPLY_RELEASE(DfuConstants.PROGRESS_ACTIVE_IMAGE_AND_RESET),
    OPTIONAL_MODULE_TEXT_CREATE(531),
    OPTIONAL_MODULE_TEXT_INIT(532),
    OPTIONAL_MODULE_TEXT_INFERENCE(533),
    OPTIONAL_MODULE_TEXT_RELEASE(DfuAdapter.STATE_PREPARE_PAIRING_REQUEST),
    ON_DEVICE_IMAGE_QUALITY_ANALYSIS_CREATE(DfuAdapter.STATE_READ_IMAGE_INFO),
    ON_DEVICE_IMAGE_QUALITY_ANALYSIS_LOAD(DfuAdapter.STATE_READ_BATTERY_INFO),
    ON_DEVICE_IMAGE_QUALITY_ANALYSIS_DETECT(543),
    ON_DEVICE_IMAGE_QUALITY_ANALYSIS_CLOSE(544),
    OPTIONAL_MODULE_DOCUMENT_DETECT_CREATE(com.veryfit.multi.nativeprotocol.b.V1),
    OPTIONAL_MODULE_DOCUMENT_DETECT_INIT(com.veryfit.multi.nativeprotocol.b.W1),
    OPTIONAL_MODULE_DOCUMENT_DETECT_PROCESS(com.veryfit.multi.nativeprotocol.b.X1),
    OPTIONAL_MODULE_DOCUMENT_DETECT_RELEASE(554),
    OPTIONAL_MODULE_DOCUMENT_CROP_CREATE(com.veryfit.multi.nativeprotocol.b.f2),
    OPTIONAL_MODULE_DOCUMENT_CROP_INIT(com.veryfit.multi.nativeprotocol.b.g2),
    OPTIONAL_MODULE_DOCUMENT_CROP_PROCESS(com.veryfit.multi.nativeprotocol.b.h2),
    OPTIONAL_MODULE_DOCUMENT_CROP_RELEASE(com.veryfit.multi.nativeprotocol.b.i2),
    OPTIONAL_MODULE_DOCUMENT_ENHANCE_CREATE(com.veryfit.multi.nativeprotocol.b.l2),
    OPTIONAL_MODULE_DOCUMENT_ENHANCE_INIT(com.veryfit.multi.nativeprotocol.b.m2),
    OPTIONAL_MODULE_DOCUMENT_ENHANCE_PROCESS(com.veryfit.multi.nativeprotocol.b.n2),
    OPTIONAL_MODULE_DOCUMENT_ENHANCE_RELEASE(com.veryfit.multi.nativeprotocol.b.o2),
    OPTIONAL_MODULE_IMAGE_QUALITY_ANALYSIS_CREATE(com.veryfit.multi.nativeprotocol.b.t2),
    OPTIONAL_MODULE_IMAGE_QUALITY_ANALYSIS_INIT(582),
    OPTIONAL_MODULE_IMAGE_QUALITY_ANALYSIS_INFERENCE(583),
    OPTIONAL_MODULE_IMAGE_QUALITY_ANALYSIS_RELEASE(584),
    OPTIONAL_MODULE_IMAGE_CAPTIONING_CREATE(com.veryfit.multi.nativeprotocol.b.u2),
    OPTIONAL_MODULE_IMAGE_CAPTIONING_INIT(592),
    OPTIONAL_MODULE_IMAGE_CAPTIONING_INFERENCE(593),
    OPTIONAL_MODULE_IMAGE_CAPTIONING_RELEASE(594),
    ON_DEVICE_IMAGE_CAPTIONING_CREATE(601),
    ON_DEVICE_IMAGE_CAPTIONING_LOAD(602),
    ON_DEVICE_IMAGE_CAPTIONING_INFERENCE(603),
    ON_DEVICE_IMAGE_CAPTIONING_CLOSE(604),
    ON_DEVICE_IMAGE_CAPTIONING_MODEL_DOWNLOAD(605),
    ON_DEVICE_DOCUMENT_DETECT_CREATE(611),
    ON_DEVICE_DOCUMENT_DETECT_LOAD(612),
    ON_DEVICE_DOCUMENT_DETECT_PROCESS(com.veryfit.multi.nativeprotocol.b.I2),
    ON_DEVICE_DOCUMENT_DETECT_CLOSE(com.veryfit.multi.nativeprotocol.b.J2),
    ON_DEVICE_DOCUMENT_CROP_CREATE(com.veryfit.multi.nativeprotocol.b.N2),
    ON_DEVICE_DOCUMENT_CROP_LOAD(com.veryfit.multi.nativeprotocol.b.O2),
    ON_DEVICE_DOCUMENT_CROP_PROCESS(com.veryfit.multi.nativeprotocol.b.P2),
    ON_DEVICE_DOCUMENT_CROP_CLOSE(com.veryfit.multi.nativeprotocol.b.Q2),
    ON_DEVICE_DOCUMENT_ENHANCE_CREATE(631),
    ON_DEVICE_DOCUMENT_ENHANCE_LOAD(632),
    ON_DEVICE_DOCUMENT_ENHANCE_PROCESS(633),
    ON_DEVICE_DOCUMENT_ENHANCE_CLOSE(634),
    OPTIONAL_MODULE_IMAGE_LABELING_CREATE(641),
    OPTIONAL_MODULE_IMAGE_LABELING_INIT(642),
    OPTIONAL_MODULE_IMAGE_LABELING_INFERENCE(643),
    OPTIONAL_MODULE_IMAGE_LABELING_RELEASE(644),
    SCANNER_AUTO_ZOOM_START(com.veryfit.multi.nativeprotocol.b.X2),
    SCANNER_AUTO_ZOOM_PAUSE(com.veryfit.multi.nativeprotocol.b.Y2),
    SCANNER_AUTO_ZOOM_RESUME(659),
    SCANNER_AUTO_ZOOM_SCAN_SUCCESS(com.veryfit.multi.nativeprotocol.b.Z2),
    SCANNER_AUTO_ZOOM_SCAN_FAILED(654),
    SCANNER_AUTO_ZOOM_FIRST_ATTEMPT(655),
    SCANNER_AUTO_ZOOM_AUTO_ZOOM(656),
    SCANNER_AUTO_ZOOM_AUTO_RESET(657),
    SCANNER_AUTO_ZOOM_MANUAL_ZOOM(658),
    LOW_LIGHT_BUNDLED_AUTO_EXPOSURE_COMPUTATION(671),
    LOW_LIGHT_BUNDLED_FRAME_PROCESS(672),
    LOW_LIGHT_BUNDLED_SCENE_DETECTION(673);
    
    private final int zzdP;

    zzln(int i) {
        this.zzdP = i;
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzbm
    public final int zza() {
        return this.zzdP;
    }
}
