package androidx.exifinterface.media;

import android.annotation.SuppressLint;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.media.MediaDataSource;
import android.media.MediaMetadataRetriever;
import android.os.Build;
import android.system.OsConstants;
import android.util.Log;
import android.util.Pair;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.exifinterface.media.a;
import com.clevertap.android.sdk.Constants;
import com.coveiot.sdk.ble.api.BleUUID;
import com.ido.ble.protocol.model.V3MessageNotice;
import com.jstyle.blesdk1860.constant.BleConst;
import com.realsil.sdk.dfu.DfuException;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.CRC32;
import okhttp3.internal.ws.WebSocketProtocol;
import org.eclipse.paho.client.mqttv3.MqttTopic;
/* loaded from: classes.dex */
public class ExifInterface {
    public static final short ALTITUDE_ABOVE_SEA_LEVEL = 0;
    public static final short ALTITUDE_BELOW_SEA_LEVEL = 1;
    public static final int COLOR_SPACE_S_RGB = 1;
    public static final int COLOR_SPACE_UNCALIBRATED = 65535;
    public static final short CONTRAST_HARD = 2;
    public static final short CONTRAST_NORMAL = 0;
    public static final short CONTRAST_SOFT = 1;
    public static final int DATA_DEFLATE_ZIP = 8;
    public static final int DATA_HUFFMAN_COMPRESSED = 2;
    public static final int DATA_JPEG = 6;
    public static final int DATA_JPEG_COMPRESSED = 7;
    public static final int DATA_LOSSY_JPEG = 34892;
    public static final int DATA_PACK_BITS_COMPRESSED = 32773;
    public static final int DATA_UNCOMPRESSED = 1;
    public static final short EXPOSURE_MODE_AUTO = 0;
    public static final short EXPOSURE_MODE_AUTO_BRACKET = 2;
    public static final short EXPOSURE_MODE_MANUAL = 1;
    public static final short EXPOSURE_PROGRAM_ACTION = 6;
    public static final short EXPOSURE_PROGRAM_APERTURE_PRIORITY = 3;
    public static final short EXPOSURE_PROGRAM_CREATIVE = 5;
    public static final short EXPOSURE_PROGRAM_LANDSCAPE_MODE = 8;
    public static final short EXPOSURE_PROGRAM_MANUAL = 1;
    public static final short EXPOSURE_PROGRAM_NORMAL = 2;
    public static final short EXPOSURE_PROGRAM_NOT_DEFINED = 0;
    public static final short EXPOSURE_PROGRAM_PORTRAIT_MODE = 7;
    public static final short EXPOSURE_PROGRAM_SHUTTER_PRIORITY = 4;
    public static final short FILE_SOURCE_DSC = 3;
    public static final short FILE_SOURCE_OTHER = 0;
    public static final short FILE_SOURCE_REFLEX_SCANNER = 2;
    public static final short FILE_SOURCE_TRANSPARENT_SCANNER = 1;
    public static final short FLAG_FLASH_FIRED = 1;
    public static final short FLAG_FLASH_MODE_AUTO = 24;
    public static final short FLAG_FLASH_MODE_COMPULSORY_FIRING = 8;
    public static final short FLAG_FLASH_MODE_COMPULSORY_SUPPRESSION = 16;
    public static final short FLAG_FLASH_NO_FLASH_FUNCTION = 32;
    public static final short FLAG_FLASH_RED_EYE_SUPPORTED = 64;
    public static final short FLAG_FLASH_RETURN_LIGHT_DETECTED = 6;
    public static final short FLAG_FLASH_RETURN_LIGHT_NOT_DETECTED = 4;
    public static final short FORMAT_CHUNKY = 1;
    public static final short FORMAT_PLANAR = 2;
    public static final short GAIN_CONTROL_HIGH_GAIN_DOWN = 4;
    public static final short GAIN_CONTROL_HIGH_GAIN_UP = 2;
    public static final short GAIN_CONTROL_LOW_GAIN_DOWN = 3;
    public static final short GAIN_CONTROL_LOW_GAIN_UP = 1;
    public static final short GAIN_CONTROL_NONE = 0;
    public static final String GPS_DIRECTION_MAGNETIC = "M";
    public static final String GPS_DIRECTION_TRUE = "T";
    public static final String GPS_DISTANCE_KILOMETERS = "K";
    public static final String GPS_DISTANCE_MILES = "M";
    public static final String GPS_DISTANCE_NAUTICAL_MILES = "N";
    public static final String GPS_MEASUREMENT_2D = "2";
    public static final String GPS_MEASUREMENT_3D = "3";
    public static final short GPS_MEASUREMENT_DIFFERENTIAL_CORRECTED = 1;
    public static final String GPS_MEASUREMENT_INTERRUPTED = "V";
    public static final String GPS_MEASUREMENT_IN_PROGRESS = "A";
    public static final short GPS_MEASUREMENT_NO_DIFFERENTIAL = 0;
    public static final String GPS_SPEED_KILOMETERS_PER_HOUR = "K";
    public static final String GPS_SPEED_KNOTS = "N";
    public static final String GPS_SPEED_MILES_PER_HOUR = "M";
    public static final String LATITUDE_NORTH = "N";
    public static final String LATITUDE_SOUTH = "S";
    public static final short LIGHT_SOURCE_CLOUDY_WEATHER = 10;
    public static final short LIGHT_SOURCE_COOL_WHITE_FLUORESCENT = 14;
    public static final short LIGHT_SOURCE_D50 = 23;
    public static final short LIGHT_SOURCE_D55 = 20;
    public static final short LIGHT_SOURCE_D65 = 21;
    public static final short LIGHT_SOURCE_D75 = 22;
    public static final short LIGHT_SOURCE_DAYLIGHT = 1;
    public static final short LIGHT_SOURCE_DAYLIGHT_FLUORESCENT = 12;
    public static final short LIGHT_SOURCE_DAY_WHITE_FLUORESCENT = 13;
    public static final short LIGHT_SOURCE_FINE_WEATHER = 9;
    public static final short LIGHT_SOURCE_FLASH = 4;
    public static final short LIGHT_SOURCE_FLUORESCENT = 2;
    public static final short LIGHT_SOURCE_ISO_STUDIO_TUNGSTEN = 24;
    public static final short LIGHT_SOURCE_OTHER = 255;
    public static final short LIGHT_SOURCE_SHADE = 11;
    public static final short LIGHT_SOURCE_STANDARD_LIGHT_A = 17;
    public static final short LIGHT_SOURCE_STANDARD_LIGHT_B = 18;
    public static final short LIGHT_SOURCE_STANDARD_LIGHT_C = 19;
    public static final short LIGHT_SOURCE_TUNGSTEN = 3;
    public static final short LIGHT_SOURCE_UNKNOWN = 0;
    public static final short LIGHT_SOURCE_WARM_WHITE_FLUORESCENT = 16;
    public static final short LIGHT_SOURCE_WHITE_FLUORESCENT = 15;
    public static final String LONGITUDE_EAST = "E";
    public static final String LONGITUDE_WEST = "W";
    public static final short METERING_MODE_AVERAGE = 1;
    public static final short METERING_MODE_CENTER_WEIGHT_AVERAGE = 2;
    public static final short METERING_MODE_MULTI_SPOT = 4;
    public static final short METERING_MODE_OTHER = 255;
    public static final short METERING_MODE_PARTIAL = 6;
    public static final short METERING_MODE_PATTERN = 5;
    public static final short METERING_MODE_SPOT = 3;
    public static final short METERING_MODE_UNKNOWN = 0;
    public static final int ORIENTATION_FLIP_HORIZONTAL = 2;
    public static final int ORIENTATION_FLIP_VERTICAL = 4;
    public static final int ORIENTATION_NORMAL = 1;
    public static final int ORIENTATION_ROTATE_180 = 3;
    public static final int ORIENTATION_ROTATE_270 = 8;
    public static final int ORIENTATION_ROTATE_90 = 6;
    public static final int ORIENTATION_TRANSPOSE = 5;
    public static final int ORIENTATION_TRANSVERSE = 7;
    public static final int ORIENTATION_UNDEFINED = 0;
    public static final int ORIGINAL_RESOLUTION_IMAGE = 0;
    public static final int PHOTOMETRIC_INTERPRETATION_BLACK_IS_ZERO = 1;
    public static final int PHOTOMETRIC_INTERPRETATION_RGB = 2;
    public static final int PHOTOMETRIC_INTERPRETATION_WHITE_IS_ZERO = 0;
    public static final int PHOTOMETRIC_INTERPRETATION_YCBCR = 6;
    public static SimpleDateFormat R = null;
    public static final int REDUCED_RESOLUTION_IMAGE = 1;
    public static final short RENDERED_PROCESS_CUSTOM = 1;
    public static final short RENDERED_PROCESS_NORMAL = 0;
    public static final short RESOLUTION_UNIT_CENTIMETERS = 3;
    public static final short RESOLUTION_UNIT_INCHES = 2;
    public static SimpleDateFormat S = null;
    public static final short SATURATION_HIGH = 0;
    public static final short SATURATION_LOW = 0;
    public static final short SATURATION_NORMAL = 0;
    public static final short SCENE_CAPTURE_TYPE_LANDSCAPE = 1;
    public static final short SCENE_CAPTURE_TYPE_NIGHT = 3;
    public static final short SCENE_CAPTURE_TYPE_PORTRAIT = 2;
    public static final short SCENE_CAPTURE_TYPE_STANDARD = 0;
    public static final short SCENE_TYPE_DIRECTLY_PHOTOGRAPHED = 1;
    public static final short SENSITIVITY_TYPE_ISO_SPEED = 3;
    public static final short SENSITIVITY_TYPE_REI = 2;
    public static final short SENSITIVITY_TYPE_REI_AND_ISO = 6;
    public static final short SENSITIVITY_TYPE_SOS = 1;
    public static final short SENSITIVITY_TYPE_SOS_AND_ISO = 5;
    public static final short SENSITIVITY_TYPE_SOS_AND_REI = 4;
    public static final short SENSITIVITY_TYPE_SOS_AND_REI_AND_ISO = 7;
    public static final short SENSITIVITY_TYPE_UNKNOWN = 0;
    public static final short SENSOR_TYPE_COLOR_SEQUENTIAL = 5;
    public static final short SENSOR_TYPE_COLOR_SEQUENTIAL_LINEAR = 8;
    public static final short SENSOR_TYPE_NOT_DEFINED = 1;
    public static final short SENSOR_TYPE_ONE_CHIP = 2;
    public static final short SENSOR_TYPE_THREE_CHIP = 4;
    public static final short SENSOR_TYPE_TRILINEAR = 7;
    public static final short SENSOR_TYPE_TWO_CHIP = 3;
    public static final short SHARPNESS_HARD = 2;
    public static final short SHARPNESS_NORMAL = 0;
    public static final short SHARPNESS_SOFT = 1;
    public static final int STREAM_TYPE_EXIF_DATA_ONLY = 1;
    public static final int STREAM_TYPE_FULL_IMAGE_DATA = 0;
    public static final short SUBJECT_DISTANCE_RANGE_CLOSE_VIEW = 2;
    public static final short SUBJECT_DISTANCE_RANGE_DISTANT_VIEW = 3;
    public static final short SUBJECT_DISTANCE_RANGE_MACRO = 1;
    public static final short SUBJECT_DISTANCE_RANGE_UNKNOWN = 0;
    public static final String TAG_APERTURE_VALUE = "ApertureValue";
    public static final String TAG_ARTIST = "Artist";
    public static final String TAG_BITS_PER_SAMPLE = "BitsPerSample";
    public static final String TAG_BODY_SERIAL_NUMBER = "BodySerialNumber";
    public static final String TAG_BRIGHTNESS_VALUE = "BrightnessValue";
    @Deprecated
    public static final String TAG_CAMARA_OWNER_NAME = "CameraOwnerName";
    public static final String TAG_CAMERA_OWNER_NAME = "CameraOwnerName";
    public static final String TAG_CFA_PATTERN = "CFAPattern";
    public static final String TAG_COLOR_SPACE = "ColorSpace";
    public static final String TAG_COMPONENTS_CONFIGURATION = "ComponentsConfiguration";
    public static final String TAG_COMPRESSED_BITS_PER_PIXEL = "CompressedBitsPerPixel";
    public static final String TAG_COMPRESSION = "Compression";
    public static final String TAG_CONTRAST = "Contrast";
    public static final String TAG_COPYRIGHT = "Copyright";
    public static final String TAG_CUSTOM_RENDERED = "CustomRendered";
    public static final String TAG_DATETIME = "DateTime";
    public static final String TAG_DATETIME_DIGITIZED = "DateTimeDigitized";
    public static final String TAG_DATETIME_ORIGINAL = "DateTimeOriginal";
    public static final String TAG_DEFAULT_CROP_SIZE = "DefaultCropSize";
    public static final String TAG_DEVICE_SETTING_DESCRIPTION = "DeviceSettingDescription";
    public static final String TAG_DIGITAL_ZOOM_RATIO = "DigitalZoomRatio";
    public static final String TAG_DNG_VERSION = "DNGVersion";
    public static final String TAG_EXIF_VERSION = "ExifVersion";
    public static final String TAG_EXPOSURE_BIAS_VALUE = "ExposureBiasValue";
    public static final String TAG_EXPOSURE_INDEX = "ExposureIndex";
    public static final String TAG_EXPOSURE_MODE = "ExposureMode";
    public static final String TAG_EXPOSURE_PROGRAM = "ExposureProgram";
    public static final String TAG_EXPOSURE_TIME = "ExposureTime";
    public static final String TAG_FILE_SOURCE = "FileSource";
    public static final String TAG_FLASH = "Flash";
    public static final String TAG_FLASHPIX_VERSION = "FlashpixVersion";
    public static final String TAG_FLASH_ENERGY = "FlashEnergy";
    public static final String TAG_FOCAL_LENGTH = "FocalLength";
    public static final String TAG_FOCAL_LENGTH_IN_35MM_FILM = "FocalLengthIn35mmFilm";
    public static final String TAG_FOCAL_PLANE_RESOLUTION_UNIT = "FocalPlaneResolutionUnit";
    public static final String TAG_FOCAL_PLANE_X_RESOLUTION = "FocalPlaneXResolution";
    public static final String TAG_FOCAL_PLANE_Y_RESOLUTION = "FocalPlaneYResolution";
    public static final String TAG_F_NUMBER = "FNumber";
    public static final String TAG_GAIN_CONTROL = "GainControl";
    public static final String TAG_GAMMA = "Gamma";
    public static final String TAG_GPS_ALTITUDE = "GPSAltitude";
    public static final String TAG_GPS_ALTITUDE_REF = "GPSAltitudeRef";
    public static final String TAG_GPS_AREA_INFORMATION = "GPSAreaInformation";
    public static final String TAG_GPS_DATESTAMP = "GPSDateStamp";
    public static final String TAG_GPS_DEST_BEARING = "GPSDestBearing";
    public static final String TAG_GPS_DEST_BEARING_REF = "GPSDestBearingRef";
    public static final String TAG_GPS_DEST_DISTANCE = "GPSDestDistance";
    public static final String TAG_GPS_DEST_DISTANCE_REF = "GPSDestDistanceRef";
    public static final String TAG_GPS_DEST_LATITUDE = "GPSDestLatitude";
    public static final String TAG_GPS_DEST_LATITUDE_REF = "GPSDestLatitudeRef";
    public static final String TAG_GPS_DEST_LONGITUDE = "GPSDestLongitude";
    public static final String TAG_GPS_DEST_LONGITUDE_REF = "GPSDestLongitudeRef";
    public static final String TAG_GPS_DIFFERENTIAL = "GPSDifferential";
    public static final String TAG_GPS_DOP = "GPSDOP";
    public static final String TAG_GPS_H_POSITIONING_ERROR = "GPSHPositioningError";
    public static final String TAG_GPS_IMG_DIRECTION = "GPSImgDirection";
    public static final String TAG_GPS_IMG_DIRECTION_REF = "GPSImgDirectionRef";
    public static final String TAG_GPS_LATITUDE = "GPSLatitude";
    public static final String TAG_GPS_LATITUDE_REF = "GPSLatitudeRef";
    public static final String TAG_GPS_LONGITUDE = "GPSLongitude";
    public static final String TAG_GPS_LONGITUDE_REF = "GPSLongitudeRef";
    public static final String TAG_GPS_MAP_DATUM = "GPSMapDatum";
    public static final String TAG_GPS_MEASURE_MODE = "GPSMeasureMode";
    public static final String TAG_GPS_PROCESSING_METHOD = "GPSProcessingMethod";
    public static final String TAG_GPS_SATELLITES = "GPSSatellites";
    public static final String TAG_GPS_SPEED = "GPSSpeed";
    public static final String TAG_GPS_SPEED_REF = "GPSSpeedRef";
    public static final String TAG_GPS_STATUS = "GPSStatus";
    public static final String TAG_GPS_TIMESTAMP = "GPSTimeStamp";
    public static final String TAG_GPS_TRACK = "GPSTrack";
    public static final String TAG_GPS_TRACK_REF = "GPSTrackRef";
    public static final String TAG_GPS_VERSION_ID = "GPSVersionID";
    public static final String TAG_IMAGE_DESCRIPTION = "ImageDescription";
    public static final String TAG_IMAGE_LENGTH = "ImageLength";
    public static final String TAG_IMAGE_UNIQUE_ID = "ImageUniqueID";
    public static final String TAG_IMAGE_WIDTH = "ImageWidth";
    public static final String TAG_INTEROPERABILITY_INDEX = "InteroperabilityIndex";
    public static final String TAG_ISO_SPEED = "ISOSpeed";
    public static final String TAG_ISO_SPEED_LATITUDE_YYY = "ISOSpeedLatitudeyyy";
    public static final String TAG_ISO_SPEED_LATITUDE_ZZZ = "ISOSpeedLatitudezzz";
    @Deprecated
    public static final String TAG_ISO_SPEED_RATINGS = "ISOSpeedRatings";
    public static final String TAG_JPEG_INTERCHANGE_FORMAT = "JPEGInterchangeFormat";
    public static final String TAG_JPEG_INTERCHANGE_FORMAT_LENGTH = "JPEGInterchangeFormatLength";
    public static final String TAG_LENS_MAKE = "LensMake";
    public static final String TAG_LENS_MODEL = "LensModel";
    public static final String TAG_LENS_SERIAL_NUMBER = "LensSerialNumber";
    public static final String TAG_LENS_SPECIFICATION = "LensSpecification";
    public static final String TAG_LIGHT_SOURCE = "LightSource";
    public static final String TAG_MAKE = "Make";
    public static final String TAG_MAKER_NOTE = "MakerNote";
    public static final String TAG_MAX_APERTURE_VALUE = "MaxApertureValue";
    public static final String TAG_METERING_MODE = "MeteringMode";
    public static final String TAG_MODEL = "Model";
    public static final String TAG_NEW_SUBFILE_TYPE = "NewSubfileType";
    public static final String TAG_OECF = "OECF";
    public static final String TAG_OFFSET_TIME = "OffsetTime";
    public static final String TAG_OFFSET_TIME_DIGITIZED = "OffsetTimeDigitized";
    public static final String TAG_OFFSET_TIME_ORIGINAL = "OffsetTimeOriginal";
    public static final String TAG_ORF_ASPECT_FRAME = "AspectFrame";
    public static final String TAG_ORF_PREVIEW_IMAGE_LENGTH = "PreviewImageLength";
    public static final String TAG_ORF_PREVIEW_IMAGE_START = "PreviewImageStart";
    public static final String TAG_ORF_THUMBNAIL_IMAGE = "ThumbnailImage";
    public static final String TAG_ORIENTATION = "Orientation";
    public static final String TAG_PHOTOGRAPHIC_SENSITIVITY = "PhotographicSensitivity";
    public static final String TAG_PHOTOMETRIC_INTERPRETATION = "PhotometricInterpretation";
    public static final String TAG_PIXEL_X_DIMENSION = "PixelXDimension";
    public static final String TAG_PIXEL_Y_DIMENSION = "PixelYDimension";
    public static final String TAG_PLANAR_CONFIGURATION = "PlanarConfiguration";
    public static final String TAG_PRIMARY_CHROMATICITIES = "PrimaryChromaticities";
    public static final String TAG_RECOMMENDED_EXPOSURE_INDEX = "RecommendedExposureIndex";
    public static final String TAG_REFERENCE_BLACK_WHITE = "ReferenceBlackWhite";
    public static final String TAG_RELATED_SOUND_FILE = "RelatedSoundFile";
    public static final String TAG_RESOLUTION_UNIT = "ResolutionUnit";
    public static final String TAG_ROWS_PER_STRIP = "RowsPerStrip";
    public static final String TAG_RW2_ISO = "ISO";
    public static final String TAG_RW2_JPG_FROM_RAW = "JpgFromRaw";
    public static final String TAG_RW2_SENSOR_BOTTOM_BORDER = "SensorBottomBorder";
    public static final String TAG_RW2_SENSOR_LEFT_BORDER = "SensorLeftBorder";
    public static final String TAG_RW2_SENSOR_RIGHT_BORDER = "SensorRightBorder";
    public static final String TAG_RW2_SENSOR_TOP_BORDER = "SensorTopBorder";
    public static final String TAG_SAMPLES_PER_PIXEL = "SamplesPerPixel";
    public static final String TAG_SATURATION = "Saturation";
    public static final String TAG_SCENE_CAPTURE_TYPE = "SceneCaptureType";
    public static final String TAG_SCENE_TYPE = "SceneType";
    public static final String TAG_SENSING_METHOD = "SensingMethod";
    public static final String TAG_SENSITIVITY_TYPE = "SensitivityType";
    public static final String TAG_SHARPNESS = "Sharpness";
    public static final String TAG_SHUTTER_SPEED_VALUE = "ShutterSpeedValue";
    public static final String TAG_SOFTWARE = "Software";
    public static final String TAG_SPATIAL_FREQUENCY_RESPONSE = "SpatialFrequencyResponse";
    public static final String TAG_SPECTRAL_SENSITIVITY = "SpectralSensitivity";
    public static final String TAG_STANDARD_OUTPUT_SENSITIVITY = "StandardOutputSensitivity";
    public static final String TAG_STRIP_BYTE_COUNTS = "StripByteCounts";
    public static final String TAG_STRIP_OFFSETS = "StripOffsets";
    public static final String TAG_SUBFILE_TYPE = "SubfileType";
    public static final String TAG_SUBJECT_AREA = "SubjectArea";
    public static final String TAG_SUBJECT_DISTANCE = "SubjectDistance";
    public static final String TAG_SUBJECT_DISTANCE_RANGE = "SubjectDistanceRange";
    public static final String TAG_SUBJECT_LOCATION = "SubjectLocation";
    public static final String TAG_SUBSEC_TIME = "SubSecTime";
    public static final String TAG_SUBSEC_TIME_DIGITIZED = "SubSecTimeDigitized";
    public static final String TAG_SUBSEC_TIME_ORIGINAL = "SubSecTimeOriginal";
    public static final String TAG_THUMBNAIL_IMAGE_LENGTH = "ThumbnailImageLength";
    public static final String TAG_THUMBNAIL_IMAGE_WIDTH = "ThumbnailImageWidth";
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public static final String TAG_THUMBNAIL_ORIENTATION = "ThumbnailOrientation";
    public static final String TAG_TRANSFER_FUNCTION = "TransferFunction";
    public static final String TAG_USER_COMMENT = "UserComment";
    public static final String TAG_WHITE_BALANCE = "WhiteBalance";
    public static final String TAG_WHITE_POINT = "WhitePoint";
    public static final String TAG_XMP = "Xmp";
    public static final String TAG_X_RESOLUTION = "XResolution";
    public static final String TAG_Y_CB_CR_COEFFICIENTS = "YCbCrCoefficients";
    public static final String TAG_Y_CB_CR_POSITIONING = "YCbCrPositioning";
    public static final String TAG_Y_CB_CR_SUB_SAMPLING = "YCbCrSubSampling";
    public static final String TAG_Y_RESOLUTION = "YResolution";
    public static final e[] W;
    @Deprecated
    public static final int WHITEBALANCE_AUTO = 0;
    @Deprecated
    public static final int WHITEBALANCE_MANUAL = 1;
    public static final short WHITE_BALANCE_AUTO = 0;
    public static final short WHITE_BALANCE_MANUAL = 1;
    public static final e[] X;
    public static final e[] Y;
    public static final short Y_CB_CR_POSITIONING_CENTERED = 1;
    public static final short Y_CB_CR_POSITIONING_CO_SITED = 2;
    public static final e[] Z;
    public static final e[] a0;
    public static final e b0;
    public static final e[] c0;
    public static final e[] d0;
    public static final e[] e0;
    public static final e[] f0;
    public static final e[][] g0;
    public static final e[] h0;
    public static final HashMap<Integer, e>[] i0;
    public static final HashMap<String, e>[] j0;
    public static final HashSet<String> k0;
    public static final HashMap<Integer, Integer> l0;
    public static final Charset m0;
    public static final byte[] n0;
    public static final byte[] o0;
    public static final Pattern p0;
    public static final Pattern q0;
    public static final Pattern r0;
    public static final Pattern s0;

    /* renamed from: a  reason: collision with root package name */
    public String f1292a;
    public FileDescriptor b;
    public AssetManager.AssetInputStream c;
    public int d;
    public boolean e;
    public final HashMap<String, d>[] f;
    public Set<Integer> g;
    public ByteOrder h;
    public boolean i;
    public boolean j;
    public boolean k;
    public int l;
    public int m;
    public byte[] n;
    public int o;
    public int p;
    public int q;
    public int r;
    public int s;
    public boolean t;
    public boolean u;
    public static final boolean v = Log.isLoggable("ExifInterface", 3);
    public static final List<Integer> w = Arrays.asList(1, 6, 3, 8);
    public static final List<Integer> x = Arrays.asList(2, 7, 4, 5);
    public static final int[] BITS_PER_SAMPLE_RGB = {8, 8, 8};
    public static final int[] BITS_PER_SAMPLE_GREYSCALE_1 = {4};
    public static final int[] BITS_PER_SAMPLE_GREYSCALE_2 = {8};
    public static final byte[] y = {-1, -40, -1};
    public static final byte[] z = {102, 116, com.htsmart.wristband2.a.a.a.U1, com.htsmart.wristband2.a.a.a.J1};
    public static final byte[] A = {109, 105, 102, 49};
    public static final byte[] B = {104, 101, 105, 99};
    public static final byte[] C = {79, com.htsmart.wristband2.a.a.a.a1, 89, 77, com.htsmart.wristband2.a.a.a.d1, 0};
    public static final byte[] D = {79, com.htsmart.wristband2.a.a.a.a1, 89, 77, com.htsmart.wristband2.a.a.a.d1, 85, 83, 0, 73, 73};
    public static final byte[] E = {BleUUID.CMD_ID_89, com.htsmart.wristband2.a.a.a.d1, com.htsmart.wristband2.a.a.a.c1, 71, 13, 10, 26, 10};
    public static final byte[] F = {101, com.htsmart.wristband2.a.a.a.o1, 73, 102};
    public static final byte[] G = {73, com.htsmart.wristband2.a.a.a.W0, 68, 82};
    public static final byte[] H = {73, com.crrepa.c.a.E0, com.htsmart.wristband2.a.a.a.c1, 68};
    public static final byte[] I = {82, 73, com.htsmart.wristband2.a.a.a.U0, com.htsmart.wristband2.a.a.a.U0};
    public static final byte[] J = {87, com.crrepa.c.a.E0, 66, com.htsmart.wristband2.a.a.a.d1};
    public static final byte[] K = {com.crrepa.c.a.E0, com.htsmart.wristband2.a.a.a.o1, 73, com.htsmart.wristband2.a.a.a.U0};
    public static final byte[] L = {-99, 1, 42};
    public static final byte[] M = "VP8X".getBytes(Charset.defaultCharset());
    public static final byte[] N = "VP8L".getBytes(Charset.defaultCharset());
    public static final byte[] O = "VP8 ".getBytes(Charset.defaultCharset());
    public static final byte[] P = "ANIM".getBytes(Charset.defaultCharset());
    public static final byte[] Q = "ANMF".getBytes(Charset.defaultCharset());
    public static final String[] T = {"", "BYTE", "STRING", "USHORT", "ULONG", "URATIONAL", "SBYTE", "UNDEFINED", "SSHORT", "SLONG", "SRATIONAL", "SINGLE", "DOUBLE", "IFD"};
    public static final int[] U = {0, 1, 1, 2, 4, 8, 1, 1, 2, 4, 8, 4, 8, 1};
    public static final byte[] V = {65, 83, 67, 73, 73, 0, 0, 0};

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    /* loaded from: classes.dex */
    public @interface ExifStreamType {
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    /* loaded from: classes.dex */
    public @interface IfdType {
    }

    /* loaded from: classes.dex */
    public class a extends MediaDataSource {
        public long h;
        public final /* synthetic */ g i;

        public a(ExifInterface exifInterface, g gVar) {
            this.i = gVar;
        }

        @Override // java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
        }

        @Override // android.media.MediaDataSource
        public long getSize() throws IOException {
            return -1L;
        }

        @Override // android.media.MediaDataSource
        public int readAt(long j, byte[] bArr, int i, int i2) throws IOException {
            if (i2 == 0) {
                return 0;
            }
            if (j < 0) {
                return -1;
            }
            try {
                long j2 = this.h;
                if (j2 != j) {
                    if (j2 >= 0 && j >= j2 + this.i.available()) {
                        return -1;
                    }
                    this.i.e(j);
                    this.h = j;
                }
                if (i2 > this.i.available()) {
                    i2 = this.i.available();
                }
                int read = this.i.read(bArr, i, i2);
                if (read >= 0) {
                    this.h += read;
                    return read;
                }
            } catch (IOException unused) {
            }
            this.h = -1L;
            return -1;
        }
    }

    /* loaded from: classes.dex */
    public static class b extends InputStream implements DataInput {
        public static final ByteOrder l = ByteOrder.LITTLE_ENDIAN;
        public static final ByteOrder m = ByteOrder.BIG_ENDIAN;
        public final DataInputStream h;
        public ByteOrder i;
        public int j;
        public byte[] k;

        public b(byte[] bArr) throws IOException {
            this(new ByteArrayInputStream(bArr), ByteOrder.BIG_ENDIAN);
        }

        public int a() {
            return this.j;
        }

        @Override // java.io.InputStream
        public int available() throws IOException {
            return this.h.available();
        }

        public long b() throws IOException {
            return readInt() & 4294967295L;
        }

        public void c(ByteOrder byteOrder) {
            this.i = byteOrder;
        }

        public void d(int i) throws IOException {
            int i2 = 0;
            while (i2 < i) {
                int i3 = i - i2;
                int skip = (int) this.h.skip(i3);
                if (skip <= 0) {
                    if (this.k == null) {
                        this.k = new byte[8192];
                    }
                    skip = this.h.read(this.k, 0, Math.min(8192, i3));
                    if (skip == -1) {
                        throw new EOFException("Reached EOF while skipping " + i + " bytes.");
                    }
                }
                i2 += skip;
            }
            this.j += i2;
        }

        @Override // java.io.InputStream
        public void mark(int i) {
            throw new UnsupportedOperationException("Mark is currently unsupported");
        }

        @Override // java.io.InputStream
        public int read() throws IOException {
            this.j++;
            return this.h.read();
        }

        @Override // java.io.DataInput
        public boolean readBoolean() throws IOException {
            this.j++;
            return this.h.readBoolean();
        }

        @Override // java.io.DataInput
        public byte readByte() throws IOException {
            this.j++;
            int read = this.h.read();
            if (read >= 0) {
                return (byte) read;
            }
            throw new EOFException();
        }

        @Override // java.io.DataInput
        public char readChar() throws IOException {
            this.j += 2;
            return this.h.readChar();
        }

        @Override // java.io.DataInput
        public double readDouble() throws IOException {
            return Double.longBitsToDouble(readLong());
        }

        @Override // java.io.DataInput
        public float readFloat() throws IOException {
            return Float.intBitsToFloat(readInt());
        }

        @Override // java.io.DataInput
        public void readFully(byte[] bArr, int i, int i2) throws IOException {
            this.j += i2;
            this.h.readFully(bArr, i, i2);
        }

        @Override // java.io.DataInput
        public int readInt() throws IOException {
            this.j += 4;
            int read = this.h.read();
            int read2 = this.h.read();
            int read3 = this.h.read();
            int read4 = this.h.read();
            if ((read | read2 | read3 | read4) >= 0) {
                ByteOrder byteOrder = this.i;
                if (byteOrder == l) {
                    return (read4 << 24) + (read3 << 16) + (read2 << 8) + read;
                }
                if (byteOrder == m) {
                    return (read << 24) + (read2 << 16) + (read3 << 8) + read4;
                }
                throw new IOException("Invalid byte order: " + this.i);
            }
            throw new EOFException();
        }

        @Override // java.io.DataInput
        public String readLine() throws IOException {
            Log.d("ExifInterface", "Currently unsupported");
            return null;
        }

        @Override // java.io.DataInput
        public long readLong() throws IOException {
            this.j += 8;
            int read = this.h.read();
            int read2 = this.h.read();
            int read3 = this.h.read();
            int read4 = this.h.read();
            int read5 = this.h.read();
            int read6 = this.h.read();
            int read7 = this.h.read();
            int read8 = this.h.read();
            if ((read | read2 | read3 | read4 | read5 | read6 | read7 | read8) >= 0) {
                ByteOrder byteOrder = this.i;
                if (byteOrder == l) {
                    return (read8 << 56) + (read7 << 48) + (read6 << 40) + (read5 << 32) + (read4 << 24) + (read3 << 16) + (read2 << 8) + read;
                }
                if (byteOrder == m) {
                    return (read << 56) + (read2 << 48) + (read3 << 40) + (read4 << 32) + (read5 << 24) + (read6 << 16) + (read7 << 8) + read8;
                }
                throw new IOException("Invalid byte order: " + this.i);
            }
            throw new EOFException();
        }

        @Override // java.io.DataInput
        public short readShort() throws IOException {
            this.j += 2;
            int read = this.h.read();
            int read2 = this.h.read();
            if ((read | read2) >= 0) {
                ByteOrder byteOrder = this.i;
                if (byteOrder == l) {
                    return (short) ((read2 << 8) + read);
                }
                if (byteOrder == m) {
                    return (short) ((read << 8) + read2);
                }
                throw new IOException("Invalid byte order: " + this.i);
            }
            throw new EOFException();
        }

        @Override // java.io.DataInput
        public String readUTF() throws IOException {
            this.j += 2;
            return this.h.readUTF();
        }

        @Override // java.io.DataInput
        public int readUnsignedByte() throws IOException {
            this.j++;
            return this.h.readUnsignedByte();
        }

        @Override // java.io.DataInput
        public int readUnsignedShort() throws IOException {
            this.j += 2;
            int read = this.h.read();
            int read2 = this.h.read();
            if ((read | read2) >= 0) {
                ByteOrder byteOrder = this.i;
                if (byteOrder == l) {
                    return (read2 << 8) + read;
                }
                if (byteOrder == m) {
                    return (read << 8) + read2;
                }
                throw new IOException("Invalid byte order: " + this.i);
            }
            throw new EOFException();
        }

        @Override // java.io.InputStream
        public void reset() {
            throw new UnsupportedOperationException("Reset is currently unsupported");
        }

        @Override // java.io.DataInput
        public int skipBytes(int i) throws IOException {
            throw new UnsupportedOperationException("skipBytes is currently unsupported");
        }

        public b(InputStream inputStream) throws IOException {
            this(inputStream, ByteOrder.BIG_ENDIAN);
        }

        public b(InputStream inputStream, ByteOrder byteOrder) throws IOException {
            this.i = ByteOrder.BIG_ENDIAN;
            DataInputStream dataInputStream = new DataInputStream(inputStream);
            this.h = dataInputStream;
            dataInputStream.mark(0);
            this.j = 0;
            this.i = byteOrder;
        }

        @Override // java.io.InputStream
        public int read(byte[] bArr, int i, int i2) throws IOException {
            int read = this.h.read(bArr, i, i2);
            this.j += read;
            return read;
        }

        @Override // java.io.DataInput
        public void readFully(byte[] bArr) throws IOException {
            this.j += bArr.length;
            this.h.readFully(bArr);
        }
    }

    /* loaded from: classes.dex */
    public static class c extends FilterOutputStream {
        public final OutputStream h;
        public ByteOrder i;

        public c(OutputStream outputStream, ByteOrder byteOrder) {
            super(outputStream);
            this.h = outputStream;
            this.i = byteOrder;
        }

        public void a(ByteOrder byteOrder) {
            this.i = byteOrder;
        }

        public void b(int i) throws IOException {
            this.h.write(i);
        }

        public void c(int i) throws IOException {
            ByteOrder byteOrder = this.i;
            if (byteOrder == ByteOrder.LITTLE_ENDIAN) {
                this.h.write((i >>> 0) & 255);
                this.h.write((i >>> 8) & 255);
                this.h.write((i >>> 16) & 255);
                this.h.write((i >>> 24) & 255);
            } else if (byteOrder == ByteOrder.BIG_ENDIAN) {
                this.h.write((i >>> 24) & 255);
                this.h.write((i >>> 16) & 255);
                this.h.write((i >>> 8) & 255);
                this.h.write((i >>> 0) & 255);
            }
        }

        public void d(short s) throws IOException {
            ByteOrder byteOrder = this.i;
            if (byteOrder == ByteOrder.LITTLE_ENDIAN) {
                this.h.write((s >>> 0) & 255);
                this.h.write((s >>> 8) & 255);
            } else if (byteOrder == ByteOrder.BIG_ENDIAN) {
                this.h.write((s >>> 8) & 255);
                this.h.write((s >>> 0) & 255);
            }
        }

        public void e(long j) throws IOException {
            c((int) j);
        }

        public void f(int i) throws IOException {
            d((short) i);
        }

        @Override // java.io.FilterOutputStream, java.io.OutputStream
        public void write(byte[] bArr) throws IOException {
            this.h.write(bArr);
        }

        @Override // java.io.FilterOutputStream, java.io.OutputStream
        public void write(byte[] bArr, int i, int i2) throws IOException {
            this.h.write(bArr, i, i2);
        }
    }

    /* loaded from: classes.dex */
    public static class d {

        /* renamed from: a  reason: collision with root package name */
        public final int f1293a;
        public final int b;
        public final long c;
        public final byte[] d;

        public d(int i, int i2, byte[] bArr) {
            this(i, i2, -1L, bArr);
        }

        public static d a(String str) {
            if (str.length() == 1 && str.charAt(0) >= '0' && str.charAt(0) <= '1') {
                return new d(1, 1, new byte[]{(byte) (str.charAt(0) - '0')});
            }
            byte[] bytes = str.getBytes(ExifInterface.m0);
            return new d(1, bytes.length, bytes);
        }

        public static d b(double[] dArr, ByteOrder byteOrder) {
            ByteBuffer wrap = ByteBuffer.wrap(new byte[ExifInterface.U[12] * dArr.length]);
            wrap.order(byteOrder);
            for (double d : dArr) {
                wrap.putDouble(d);
            }
            return new d(12, dArr.length, wrap.array());
        }

        public static d c(int[] iArr, ByteOrder byteOrder) {
            ByteBuffer wrap = ByteBuffer.wrap(new byte[ExifInterface.U[9] * iArr.length]);
            wrap.order(byteOrder);
            for (int i : iArr) {
                wrap.putInt(i);
            }
            return new d(9, iArr.length, wrap.array());
        }

        public static d d(f[] fVarArr, ByteOrder byteOrder) {
            ByteBuffer wrap = ByteBuffer.wrap(new byte[ExifInterface.U[10] * fVarArr.length]);
            wrap.order(byteOrder);
            for (f fVar : fVarArr) {
                wrap.putInt((int) fVar.f1295a);
                wrap.putInt((int) fVar.b);
            }
            return new d(10, fVarArr.length, wrap.array());
        }

        public static d e(String str) {
            byte[] bytes = (str + (char) 0).getBytes(ExifInterface.m0);
            return new d(2, bytes.length, bytes);
        }

        public static d f(long j, ByteOrder byteOrder) {
            return g(new long[]{j}, byteOrder);
        }

        public static d g(long[] jArr, ByteOrder byteOrder) {
            ByteBuffer wrap = ByteBuffer.wrap(new byte[ExifInterface.U[4] * jArr.length]);
            wrap.order(byteOrder);
            for (long j : jArr) {
                wrap.putInt((int) j);
            }
            return new d(4, jArr.length, wrap.array());
        }

        public static d h(f fVar, ByteOrder byteOrder) {
            return i(new f[]{fVar}, byteOrder);
        }

        public static d i(f[] fVarArr, ByteOrder byteOrder) {
            ByteBuffer wrap = ByteBuffer.wrap(new byte[ExifInterface.U[5] * fVarArr.length]);
            wrap.order(byteOrder);
            for (f fVar : fVarArr) {
                wrap.putInt((int) fVar.f1295a);
                wrap.putInt((int) fVar.b);
            }
            return new d(5, fVarArr.length, wrap.array());
        }

        public static d j(int i, ByteOrder byteOrder) {
            return k(new int[]{i}, byteOrder);
        }

        public static d k(int[] iArr, ByteOrder byteOrder) {
            ByteBuffer wrap = ByteBuffer.wrap(new byte[ExifInterface.U[3] * iArr.length]);
            wrap.order(byteOrder);
            for (int i : iArr) {
                wrap.putShort((short) i);
            }
            return new d(3, iArr.length, wrap.array());
        }

        public double l(ByteOrder byteOrder) {
            Object o = o(byteOrder);
            if (o != null) {
                if (o instanceof String) {
                    return Double.parseDouble((String) o);
                }
                if (o instanceof long[]) {
                    long[] jArr = (long[]) o;
                    if (jArr.length == 1) {
                        return jArr[0];
                    }
                    throw new NumberFormatException("There are more than one component");
                } else if (o instanceof int[]) {
                    int[] iArr = (int[]) o;
                    if (iArr.length == 1) {
                        return iArr[0];
                    }
                    throw new NumberFormatException("There are more than one component");
                } else if (o instanceof double[]) {
                    double[] dArr = (double[]) o;
                    if (dArr.length == 1) {
                        return dArr[0];
                    }
                    throw new NumberFormatException("There are more than one component");
                } else if (o instanceof f[]) {
                    f[] fVarArr = (f[]) o;
                    if (fVarArr.length == 1) {
                        return fVarArr[0].a();
                    }
                    throw new NumberFormatException("There are more than one component");
                } else {
                    throw new NumberFormatException("Couldn't find a double value");
                }
            }
            throw new NumberFormatException("NULL can't be converted to a double value");
        }

        public int m(ByteOrder byteOrder) {
            Object o = o(byteOrder);
            if (o != null) {
                if (o instanceof String) {
                    return Integer.parseInt((String) o);
                }
                if (o instanceof long[]) {
                    long[] jArr = (long[]) o;
                    if (jArr.length == 1) {
                        return (int) jArr[0];
                    }
                    throw new NumberFormatException("There are more than one component");
                } else if (o instanceof int[]) {
                    int[] iArr = (int[]) o;
                    if (iArr.length == 1) {
                        return iArr[0];
                    }
                    throw new NumberFormatException("There are more than one component");
                } else {
                    throw new NumberFormatException("Couldn't find a integer value");
                }
            }
            throw new NumberFormatException("NULL can't be converted to a integer value");
        }

        public String n(ByteOrder byteOrder) {
            Object o = o(byteOrder);
            if (o == null) {
                return null;
            }
            if (o instanceof String) {
                return (String) o;
            }
            StringBuilder sb = new StringBuilder();
            int i = 0;
            if (o instanceof long[]) {
                long[] jArr = (long[]) o;
                while (i < jArr.length) {
                    sb.append(jArr[i]);
                    i++;
                    if (i != jArr.length) {
                        sb.append(Constants.SEPARATOR_COMMA);
                    }
                }
                return sb.toString();
            } else if (o instanceof int[]) {
                int[] iArr = (int[]) o;
                while (i < iArr.length) {
                    sb.append(iArr[i]);
                    i++;
                    if (i != iArr.length) {
                        sb.append(Constants.SEPARATOR_COMMA);
                    }
                }
                return sb.toString();
            } else if (o instanceof double[]) {
                double[] dArr = (double[]) o;
                while (i < dArr.length) {
                    sb.append(dArr[i]);
                    i++;
                    if (i != dArr.length) {
                        sb.append(Constants.SEPARATOR_COMMA);
                    }
                }
                return sb.toString();
            } else if (o instanceof f[]) {
                f[] fVarArr = (f[]) o;
                while (i < fVarArr.length) {
                    sb.append(fVarArr[i].f1295a);
                    sb.append('/');
                    sb.append(fVarArr[i].b);
                    i++;
                    if (i != fVarArr.length) {
                        sb.append(Constants.SEPARATOR_COMMA);
                    }
                }
                return sb.toString();
            } else {
                return null;
            }
        }

        /* JADX WARN: Not initialized variable reg: 3, insn: 0x019c: MOVE  (r2 I:??[OBJECT, ARRAY]) = (r3 I:??[OBJECT, ARRAY]), block:B:152:0x019c */
        /* JADX WARN: Removed duplicated region for block: B:176:0x019f A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public java.lang.Object o(java.nio.ByteOrder r11) {
            /*
                Method dump skipped, instructions count: 452
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.exifinterface.media.ExifInterface.d.o(java.nio.ByteOrder):java.lang.Object");
        }

        public int p() {
            return ExifInterface.U[this.f1293a] * this.b;
        }

        public String toString() {
            return "(" + ExifInterface.T[this.f1293a] + ", data length:" + this.d.length + ")";
        }

        public d(int i, int i2, long j, byte[] bArr) {
            this.f1293a = i;
            this.b = i2;
            this.c = j;
            this.d = bArr;
        }
    }

    /* loaded from: classes.dex */
    public static class f {

        /* renamed from: a  reason: collision with root package name */
        public final long f1295a;
        public final long b;

        public f(double d) {
            this((long) (d * 10000.0d), 10000L);
        }

        public double a() {
            return this.f1295a / this.b;
        }

        public String toString() {
            return this.f1295a + MqttTopic.TOPIC_LEVEL_SEPARATOR + this.b;
        }

        public f(long j, long j2) {
            if (j2 == 0) {
                this.f1295a = 0L;
                this.b = 1L;
                return;
            }
            this.f1295a = j;
            this.b = j2;
        }
    }

    static {
        e[] eVarArr;
        e[] eVarArr2 = {new e(TAG_NEW_SUBFILE_TYPE, 254, 4), new e(TAG_SUBFILE_TYPE, 255, 4), new e(TAG_IMAGE_WIDTH, 256, 3, 4), new e(TAG_IMAGE_LENGTH, 257, 3, 4), new e(TAG_BITS_PER_SAMPLE, 258, 3), new e(TAG_COMPRESSION, 259, 3), new e(TAG_PHOTOMETRIC_INTERPRETATION, DfuException.ERROR_NO_SERVICE_FOUND_OR_LOSS, 3), new e(TAG_IMAGE_DESCRIPTION, DfuException.ERROR_READ_DEVICE_INFO_ERROR, 2), new e(TAG_MAKE, DfuException.ERROR_READ_APP_INFO_ERROR, 2), new e(TAG_MODEL, 272, 2), new e(TAG_STRIP_OFFSETS, 273, 3, 4), new e(TAG_ORIENTATION, 274, 3), new e(TAG_SAMPLES_PER_PIXEL, DfuException.ERROR_READ_REMOTE_MAC_ADDR, 3), new e(TAG_ROWS_PER_STRIP, 278, 3, 4), new e(TAG_STRIP_BYTE_COUNTS, DfuException.ERROR_SEND_COMMAND_FAIL, 3, 4), new e(TAG_X_RESOLUTION, DfuException.ERROR_DFU_SPP_RWS_NOT_READY, 5), new e(TAG_Y_RESOLUTION, 283, 5), new e(TAG_PLANAR_CONFIGURATION, DfuException.ERROR_DFU_ENABLE_BUFFER_CHECK_NO_RESPONSE, 3), new e(TAG_RESOLUTION_UNIT, 296, 3), new e(TAG_TRANSFER_FUNCTION, 301, 3), new e(TAG_SOFTWARE, 305, 2), new e(TAG_DATETIME, 306, 2), new e(TAG_ARTIST, 315, 2), new e(TAG_WHITE_POINT, TypedValues.AttributesType.TYPE_PIVOT_TARGET, 5), new e(TAG_PRIMARY_CHROMATICITIES, com.veryfit.multi.nativeprotocol.b.e1, 5), new e("SubIFDPointer", 330, 4), new e(TAG_JPEG_INTERCHANGE_FORMAT, 513, 4), new e(TAG_JPEG_INTERCHANGE_FORMAT_LENGTH, 514, 4), new e(TAG_Y_CB_CR_COEFFICIENTS, 529, 5), new e(TAG_Y_CB_CR_SUB_SAMPLING, 530, 3), new e(TAG_Y_CB_CR_POSITIONING, 531, 3), new e(TAG_REFERENCE_BLACK_WHITE, 532, 5), new e(TAG_COPYRIGHT, 33432, 2), new e("ExifIFDPointer", 34665, 4), new e("GPSInfoIFDPointer", 34853, 4), new e(TAG_RW2_SENSOR_TOP_BORDER, 4, 4), new e(TAG_RW2_SENSOR_LEFT_BORDER, 5, 4), new e(TAG_RW2_SENSOR_BOTTOM_BORDER, 6, 4), new e(TAG_RW2_SENSOR_RIGHT_BORDER, 7, 4), new e(TAG_RW2_ISO, 23, 3), new e(TAG_RW2_JPG_FROM_RAW, 46, 7), new e(TAG_XMP, TypedValues.TransitionType.TYPE_DURATION, 1)};
        W = eVarArr2;
        e[] eVarArr3 = {new e(TAG_EXPOSURE_TIME, 33434, 5), new e(TAG_F_NUMBER, 33437, 5), new e(TAG_EXPOSURE_PROGRAM, 34850, 3), new e(TAG_SPECTRAL_SENSITIVITY, 34852, 2), new e(TAG_PHOTOGRAPHIC_SENSITIVITY, 34855, 3), new e(TAG_OECF, 34856, 7), new e(TAG_SENSITIVITY_TYPE, 34864, 3), new e(TAG_STANDARD_OUTPUT_SENSITIVITY, 34865, 4), new e(TAG_RECOMMENDED_EXPOSURE_INDEX, 34866, 4), new e(TAG_ISO_SPEED, 34867, 4), new e(TAG_ISO_SPEED_LATITUDE_YYY, 34868, 4), new e(TAG_ISO_SPEED_LATITUDE_ZZZ, 34869, 4), new e(TAG_EXIF_VERSION, 36864, 2), new e(TAG_DATETIME_ORIGINAL, 36867, 2), new e(TAG_DATETIME_DIGITIZED, 36868, 2), new e(TAG_OFFSET_TIME, 36880, 2), new e(TAG_OFFSET_TIME_ORIGINAL, 36881, 2), new e(TAG_OFFSET_TIME_DIGITIZED, 36882, 2), new e(TAG_COMPONENTS_CONFIGURATION, 37121, 7), new e(TAG_COMPRESSED_BITS_PER_PIXEL, 37122, 5), new e(TAG_SHUTTER_SPEED_VALUE, 37377, 10), new e(TAG_APERTURE_VALUE, 37378, 5), new e(TAG_BRIGHTNESS_VALUE, 37379, 10), new e(TAG_EXPOSURE_BIAS_VALUE, 37380, 10), new e(TAG_MAX_APERTURE_VALUE, 37381, 5), new e(TAG_SUBJECT_DISTANCE, 37382, 5), new e(TAG_METERING_MODE, 37383, 3), new e(TAG_LIGHT_SOURCE, 37384, 3), new e(TAG_FLASH, 37385, 3), new e(TAG_FOCAL_LENGTH, 37386, 5), new e(TAG_SUBJECT_AREA, 37396, 3), new e(TAG_MAKER_NOTE, 37500, 7), new e(TAG_USER_COMMENT, 37510, 7), new e(TAG_SUBSEC_TIME, 37520, 2), new e(TAG_SUBSEC_TIME_ORIGINAL, 37521, 2), new e(TAG_SUBSEC_TIME_DIGITIZED, 37522, 2), new e(TAG_FLASHPIX_VERSION, 40960, 7), new e(TAG_COLOR_SPACE, 40961, 3), new e(TAG_PIXEL_X_DIMENSION, 40962, 3, 4), new e(TAG_PIXEL_Y_DIMENSION, 40963, 3, 4), new e(TAG_RELATED_SOUND_FILE, 40964, 2), new e("InteroperabilityIFDPointer", 40965, 4), new e(TAG_FLASH_ENERGY, 41483, 5), new e(TAG_SPATIAL_FREQUENCY_RESPONSE, 41484, 7), new e(TAG_FOCAL_PLANE_X_RESOLUTION, 41486, 5), new e(TAG_FOCAL_PLANE_Y_RESOLUTION, 41487, 5), new e(TAG_FOCAL_PLANE_RESOLUTION_UNIT, 41488, 3), new e(TAG_SUBJECT_LOCATION, 41492, 3), new e(TAG_EXPOSURE_INDEX, 41493, 5), new e(TAG_SENSING_METHOD, 41495, 3), new e(TAG_FILE_SOURCE, 41728, 7), new e(TAG_SCENE_TYPE, 41729, 7), new e(TAG_CFA_PATTERN, 41730, 7), new e(TAG_CUSTOM_RENDERED, 41985, 3), new e(TAG_EXPOSURE_MODE, 41986, 3), new e(TAG_WHITE_BALANCE, 41987, 3), new e(TAG_DIGITAL_ZOOM_RATIO, 41988, 5), new e(TAG_FOCAL_LENGTH_IN_35MM_FILM, 41989, 3), new e(TAG_SCENE_CAPTURE_TYPE, 41990, 3), new e(TAG_GAIN_CONTROL, 41991, 3), new e(TAG_CONTRAST, 41992, 3), new e(TAG_SATURATION, 41993, 3), new e(TAG_SHARPNESS, 41994, 3), new e(TAG_DEVICE_SETTING_DESCRIPTION, 41995, 7), new e(TAG_SUBJECT_DISTANCE_RANGE, 41996, 3), new e(TAG_IMAGE_UNIQUE_ID, 42016, 2), new e("CameraOwnerName", 42032, 2), new e(TAG_BODY_SERIAL_NUMBER, 42033, 2), new e(TAG_LENS_SPECIFICATION, 42034, 5), new e(TAG_LENS_MAKE, 42035, 2), new e(TAG_LENS_MODEL, 42036, 2), new e(TAG_GAMMA, 42240, 5), new e(TAG_DNG_VERSION, 50706, 1), new e(TAG_DEFAULT_CROP_SIZE, 50720, 3, 4)};
        X = eVarArr3;
        e[] eVarArr4 = {new e(TAG_GPS_VERSION_ID, 0, 1), new e(TAG_GPS_LATITUDE_REF, 1, 2), new e(TAG_GPS_LATITUDE, 2, 5, 10), new e(TAG_GPS_LONGITUDE_REF, 3, 2), new e(TAG_GPS_LONGITUDE, 4, 5, 10), new e(TAG_GPS_ALTITUDE_REF, 5, 1), new e(TAG_GPS_ALTITUDE, 6, 5), new e(TAG_GPS_TIMESTAMP, 7, 5), new e(TAG_GPS_SATELLITES, 8, 2), new e(TAG_GPS_STATUS, 9, 2), new e(TAG_GPS_MEASURE_MODE, 10, 2), new e(TAG_GPS_DOP, 11, 5), new e(TAG_GPS_SPEED_REF, 12, 2), new e(TAG_GPS_SPEED, 13, 5), new e(TAG_GPS_TRACK_REF, 14, 2), new e(TAG_GPS_TRACK, 15, 5), new e(TAG_GPS_IMG_DIRECTION_REF, 16, 2), new e(TAG_GPS_IMG_DIRECTION, 17, 5), new e(TAG_GPS_MAP_DATUM, 18, 2), new e(TAG_GPS_DEST_LATITUDE_REF, 19, 2), new e(TAG_GPS_DEST_LATITUDE, 20, 5), new e(TAG_GPS_DEST_LONGITUDE_REF, 21, 2), new e(TAG_GPS_DEST_LONGITUDE, 22, 5), new e(TAG_GPS_DEST_BEARING_REF, 23, 2), new e(TAG_GPS_DEST_BEARING, 24, 5), new e(TAG_GPS_DEST_DISTANCE_REF, 25, 2), new e(TAG_GPS_DEST_DISTANCE, 26, 5), new e(TAG_GPS_PROCESSING_METHOD, 27, 7), new e(TAG_GPS_AREA_INFORMATION, 28, 7), new e(TAG_GPS_DATESTAMP, 29, 2), new e(TAG_GPS_DIFFERENTIAL, 30, 3), new e(TAG_GPS_H_POSITIONING_ERROR, 31, 5)};
        Y = eVarArr4;
        e[] eVarArr5 = {new e(TAG_INTEROPERABILITY_INDEX, 1, 2)};
        Z = eVarArr5;
        e[] eVarArr6 = {new e(TAG_NEW_SUBFILE_TYPE, 254, 4), new e(TAG_SUBFILE_TYPE, 255, 4), new e(TAG_THUMBNAIL_IMAGE_WIDTH, 256, 3, 4), new e(TAG_THUMBNAIL_IMAGE_LENGTH, 257, 3, 4), new e(TAG_BITS_PER_SAMPLE, 258, 3), new e(TAG_COMPRESSION, 259, 3), new e(TAG_PHOTOMETRIC_INTERPRETATION, DfuException.ERROR_NO_SERVICE_FOUND_OR_LOSS, 3), new e(TAG_IMAGE_DESCRIPTION, DfuException.ERROR_READ_DEVICE_INFO_ERROR, 2), new e(TAG_MAKE, DfuException.ERROR_READ_APP_INFO_ERROR, 2), new e(TAG_MODEL, 272, 2), new e(TAG_STRIP_OFFSETS, 273, 3, 4), new e(TAG_THUMBNAIL_ORIENTATION, 274, 3), new e(TAG_SAMPLES_PER_PIXEL, DfuException.ERROR_READ_REMOTE_MAC_ADDR, 3), new e(TAG_ROWS_PER_STRIP, 278, 3, 4), new e(TAG_STRIP_BYTE_COUNTS, DfuException.ERROR_SEND_COMMAND_FAIL, 3, 4), new e(TAG_X_RESOLUTION, DfuException.ERROR_DFU_SPP_RWS_NOT_READY, 5), new e(TAG_Y_RESOLUTION, 283, 5), new e(TAG_PLANAR_CONFIGURATION, DfuException.ERROR_DFU_ENABLE_BUFFER_CHECK_NO_RESPONSE, 3), new e(TAG_RESOLUTION_UNIT, 296, 3), new e(TAG_TRANSFER_FUNCTION, 301, 3), new e(TAG_SOFTWARE, 305, 2), new e(TAG_DATETIME, 306, 2), new e(TAG_ARTIST, 315, 2), new e(TAG_WHITE_POINT, TypedValues.AttributesType.TYPE_PIVOT_TARGET, 5), new e(TAG_PRIMARY_CHROMATICITIES, com.veryfit.multi.nativeprotocol.b.e1, 5), new e("SubIFDPointer", 330, 4), new e(TAG_JPEG_INTERCHANGE_FORMAT, 513, 4), new e(TAG_JPEG_INTERCHANGE_FORMAT_LENGTH, 514, 4), new e(TAG_Y_CB_CR_COEFFICIENTS, 529, 5), new e(TAG_Y_CB_CR_SUB_SAMPLING, 530, 3), new e(TAG_Y_CB_CR_POSITIONING, 531, 3), new e(TAG_REFERENCE_BLACK_WHITE, 532, 5), new e(TAG_XMP, TypedValues.TransitionType.TYPE_DURATION, 1), new e(TAG_COPYRIGHT, 33432, 2), new e("ExifIFDPointer", 34665, 4), new e("GPSInfoIFDPointer", 34853, 4), new e(TAG_DNG_VERSION, 50706, 1), new e(TAG_DEFAULT_CROP_SIZE, 50720, 3, 4)};
        a0 = eVarArr6;
        b0 = new e(TAG_STRIP_OFFSETS, 273, 3);
        e[] eVarArr7 = {new e(TAG_ORF_THUMBNAIL_IMAGE, 256, 7), new e("CameraSettingsIFDPointer", V3MessageNotice.TYPE_CHATWORK, 4), new e("ImageProcessingIFDPointer", 8256, 4)};
        c0 = eVarArr7;
        e[] eVarArr8 = {new e(TAG_ORF_PREVIEW_IMAGE_START, 257, 4), new e(TAG_ORF_PREVIEW_IMAGE_LENGTH, 258, 4)};
        d0 = eVarArr8;
        e[] eVarArr9 = {new e(TAG_ORF_ASPECT_FRAME, 4371, 3)};
        e0 = eVarArr9;
        e[] eVarArr10 = {new e(TAG_COLOR_SPACE, 55, 3)};
        f0 = eVarArr10;
        e[][] eVarArr11 = {eVarArr2, eVarArr3, eVarArr4, eVarArr5, eVarArr6, eVarArr2, eVarArr7, eVarArr8, eVarArr9, eVarArr10};
        g0 = eVarArr11;
        h0 = new e[]{new e("SubIFDPointer", 330, 4), new e("ExifIFDPointer", 34665, 4), new e("GPSInfoIFDPointer", 34853, 4), new e("InteroperabilityIFDPointer", 40965, 4), new e("CameraSettingsIFDPointer", V3MessageNotice.TYPE_CHATWORK, 1), new e("ImageProcessingIFDPointer", 8256, 1)};
        i0 = new HashMap[eVarArr11.length];
        j0 = new HashMap[eVarArr11.length];
        k0 = new HashSet<>(Arrays.asList(TAG_F_NUMBER, TAG_DIGITAL_ZOOM_RATIO, TAG_EXPOSURE_TIME, TAG_SUBJECT_DISTANCE, TAG_GPS_TIMESTAMP));
        l0 = new HashMap<>();
        Charset forName = Charset.forName("US-ASCII");
        m0 = forName;
        n0 = "Exif\u0000\u0000".getBytes(forName);
        o0 = "http://ns.adobe.com/xap/1.0/\u0000".getBytes(forName);
        Locale locale = Locale.US;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss", locale);
        R = simpleDateFormat;
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", locale);
        S = simpleDateFormat2;
        simpleDateFormat2.setTimeZone(TimeZone.getTimeZone("UTC"));
        int i = 0;
        while (true) {
            e[][] eVarArr12 = g0;
            if (i < eVarArr12.length) {
                i0[i] = new HashMap<>();
                j0[i] = new HashMap<>();
                for (e eVar : eVarArr12[i]) {
                    i0[i].put(Integer.valueOf(eVar.f1294a), eVar);
                    j0[i].put(eVar.b, eVar);
                }
                i++;
            } else {
                HashMap<Integer, Integer> hashMap = l0;
                e[] eVarArr13 = h0;
                hashMap.put(Integer.valueOf(eVarArr13[0].f1294a), 5);
                hashMap.put(Integer.valueOf(eVarArr13[1].f1294a), 1);
                hashMap.put(Integer.valueOf(eVarArr13[2].f1294a), 2);
                hashMap.put(Integer.valueOf(eVarArr13[3].f1294a), 3);
                hashMap.put(Integer.valueOf(eVarArr13[4].f1294a), 7);
                hashMap.put(Integer.valueOf(eVarArr13[5].f1294a), 8);
                p0 = Pattern.compile(".*[1-9].*");
                q0 = Pattern.compile("^(\\d{2}):(\\d{2}):(\\d{2})$");
                r0 = Pattern.compile("^(\\d{4}):(\\d{2}):(\\d{2})\\s(\\d{2}):(\\d{2}):(\\d{2})$");
                s0 = Pattern.compile("^(\\d{4})-(\\d{2})-(\\d{2})\\s(\\d{2}):(\\d{2}):(\\d{2})$");
                return;
            }
        }
    }

    public ExifInterface(@NonNull File file) throws IOException {
        e[][] eVarArr = g0;
        this.f = new HashMap[eVarArr.length];
        this.g = new HashSet(eVarArr.length);
        this.h = ByteOrder.BIG_ENDIAN;
        Objects.requireNonNull(file, "file cannot be null");
        t(file.getAbsolutePath());
    }

    public static boolean B(FileDescriptor fileDescriptor) {
        if (Build.VERSION.SDK_INT >= 21) {
            try {
                a.C0145a.c(fileDescriptor, 0L, OsConstants.SEEK_CUR);
                return true;
            } catch (Exception unused) {
                if (v) {
                    Log.d("ExifInterface", "The file descriptor for the given input is not seekable");
                }
            }
        }
        return false;
    }

    public static boolean D(int i) {
        return i == 4 || i == 13 || i == 14 || i == 3 || i == 0;
    }

    public static Long H(@Nullable String str, @Nullable String str2, @Nullable String str3) {
        if (str != null && p0.matcher(str).matches()) {
            ParsePosition parsePosition = new ParsePosition(0);
            try {
                Date parse = R.parse(str, parsePosition);
                if (parse == null && (parse = S.parse(str, parsePosition)) == null) {
                    return null;
                }
                long time = parse.getTime();
                if (str3 != null) {
                    int i = 1;
                    String substring = str3.substring(0, 1);
                    int parseInt = Integer.parseInt(str3.substring(1, 3));
                    int parseInt2 = Integer.parseInt(str3.substring(4, 6));
                    if (("+".equals(substring) || "-".equals(substring)) && ":".equals(str3.substring(3, 4)) && parseInt <= 14) {
                        int i2 = ((parseInt * 60) + parseInt2) * 60 * 1000;
                        if (!"-".equals(substring)) {
                            i = -1;
                        }
                        time += i2 * i;
                    }
                }
                if (str2 != null) {
                    time += androidx.exifinterface.media.a.g(str2);
                }
                return Long.valueOf(time);
            } catch (IllegalArgumentException unused) {
            }
        }
        return null;
    }

    public static boolean U(int i) {
        return (i == 4 || i == 9 || i == 13 || i == 14) ? false : true;
    }

    public static double c(String str, String str2) {
        try {
            String[] split = str.split(Constants.SEPARATOR_COMMA, -1);
            String[] split2 = split[0].split(MqttTopic.TOPIC_LEVEL_SEPARATOR, -1);
            String[] split3 = split[1].split(MqttTopic.TOPIC_LEVEL_SEPARATOR, -1);
            String[] split4 = split[2].split(MqttTopic.TOPIC_LEVEL_SEPARATOR, -1);
            double parseDouble = (Double.parseDouble(split2[0].trim()) / Double.parseDouble(split2[1].trim())) + ((Double.parseDouble(split3[0].trim()) / Double.parseDouble(split3[1].trim())) / 60.0d) + ((Double.parseDouble(split4[0].trim()) / Double.parseDouble(split4[1].trim())) / 3600.0d);
            if (!str2.equals(LATITUDE_SOUTH) && !str2.equals(LONGITUDE_WEST)) {
                if (!str2.equals("N") && !str2.equals(LONGITUDE_EAST)) {
                    throw new IllegalArgumentException();
                }
                return parseDouble;
            }
            return -parseDouble;
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException unused) {
            throw new IllegalArgumentException();
        }
    }

    public static boolean isSupportedMimeType(@NonNull String str) {
        Objects.requireNonNull(str, "mimeType shouldn't be null");
        String lowerCase = str.toLowerCase(Locale.ROOT);
        lowerCase.hashCode();
        char c2 = 65535;
        switch (lowerCase.hashCode()) {
            case -1875291391:
                if (lowerCase.equals("image/x-fuji-raf")) {
                    c2 = 0;
                    break;
                }
                break;
            case -1635437028:
                if (lowerCase.equals("image/x-samsung-srw")) {
                    c2 = 1;
                    break;
                }
                break;
            case -1594371159:
                if (lowerCase.equals("image/x-sony-arw")) {
                    c2 = 2;
                    break;
                }
                break;
            case -1487464693:
                if (lowerCase.equals("image/heic")) {
                    c2 = 3;
                    break;
                }
                break;
            case -1487464690:
                if (lowerCase.equals("image/heif")) {
                    c2 = 4;
                    break;
                }
                break;
            case -1487394660:
                if (lowerCase.equals("image/jpeg")) {
                    c2 = 5;
                    break;
                }
                break;
            case -1487018032:
                if (lowerCase.equals("image/webp")) {
                    c2 = 6;
                    break;
                }
                break;
            case -1423313290:
                if (lowerCase.equals("image/x-adobe-dng")) {
                    c2 = 7;
                    break;
                }
                break;
            case -985160897:
                if (lowerCase.equals("image/x-panasonic-rw2")) {
                    c2 = '\b';
                    break;
                }
                break;
            case -879258763:
                if (lowerCase.equals("image/png")) {
                    c2 = '\t';
                    break;
                }
                break;
            case -332763809:
                if (lowerCase.equals("image/x-pentax-pef")) {
                    c2 = '\n';
                    break;
                }
                break;
            case 1378106698:
                if (lowerCase.equals("image/x-olympus-orf")) {
                    c2 = 11;
                    break;
                }
                break;
            case 2099152104:
                if (lowerCase.equals("image/x-nikon-nef")) {
                    c2 = '\f';
                    break;
                }
                break;
            case 2099152524:
                if (lowerCase.equals("image/x-nikon-nrw")) {
                    c2 = '\r';
                    break;
                }
                break;
            case 2111234748:
                if (lowerCase.equals("image/x-canon-cr2")) {
                    c2 = 14;
                    break;
                }
                break;
        }
        switch (c2) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case '\b':
            case '\t':
            case '\n':
            case 11:
            case '\f':
            case '\r':
            case 14:
                return true;
            default:
                return false;
        }
    }

    public static Pair<Integer, Integer> q(String str) {
        if (str.contains(Constants.SEPARATOR_COMMA)) {
            String[] split = str.split(Constants.SEPARATOR_COMMA, -1);
            Pair<Integer, Integer> q = q(split[0]);
            if (((Integer) q.first).intValue() == 2) {
                return q;
            }
            for (int i = 1; i < split.length; i++) {
                Pair<Integer, Integer> q2 = q(split[i]);
                int intValue = (((Integer) q2.first).equals(q.first) || ((Integer) q2.second).equals(q.first)) ? ((Integer) q.first).intValue() : -1;
                int intValue2 = (((Integer) q.second).intValue() == -1 || !(((Integer) q2.first).equals(q.second) || ((Integer) q2.second).equals(q.second))) ? -1 : ((Integer) q.second).intValue();
                if (intValue == -1 && intValue2 == -1) {
                    return new Pair<>(2, -1);
                }
                if (intValue == -1) {
                    q = new Pair<>(Integer.valueOf(intValue2), -1);
                } else if (intValue2 == -1) {
                    q = new Pair<>(Integer.valueOf(intValue), -1);
                }
            }
            return q;
        } else if (str.contains(MqttTopic.TOPIC_LEVEL_SEPARATOR)) {
            String[] split2 = str.split(MqttTopic.TOPIC_LEVEL_SEPARATOR, -1);
            if (split2.length == 2) {
                try {
                    long parseDouble = (long) Double.parseDouble(split2[0]);
                    long parseDouble2 = (long) Double.parseDouble(split2[1]);
                    if (parseDouble >= 0 && parseDouble2 >= 0) {
                        if (parseDouble <= 2147483647L && parseDouble2 <= 2147483647L) {
                            return new Pair<>(10, 5);
                        }
                        return new Pair<>(5, -1);
                    }
                    return new Pair<>(10, -1);
                } catch (NumberFormatException unused) {
                }
            }
            return new Pair<>(2, -1);
        } else {
            try {
                try {
                    Long valueOf = Long.valueOf(Long.parseLong(str));
                    if (valueOf.longValue() >= 0 && valueOf.longValue() <= WebSocketProtocol.PAYLOAD_SHORT_MAX) {
                        return new Pair<>(3, 4);
                    }
                    if (valueOf.longValue() < 0) {
                        return new Pair<>(9, -1);
                    }
                    return new Pair<>(4, -1);
                } catch (NumberFormatException unused2) {
                    return new Pair<>(2, -1);
                }
            } catch (NumberFormatException unused3) {
                Double.parseDouble(str);
                return new Pair<>(12, -1);
            }
        }
    }

    public static boolean u(BufferedInputStream bufferedInputStream) throws IOException {
        byte[] bArr = n0;
        bufferedInputStream.mark(bArr.length);
        byte[] bArr2 = new byte[bArr.length];
        bufferedInputStream.read(bArr2);
        bufferedInputStream.reset();
        int i = 0;
        while (true) {
            byte[] bArr3 = n0;
            if (i >= bArr3.length) {
                return true;
            }
            if (bArr2[i] != bArr3[i]) {
                return false;
            }
            i++;
        }
    }

    public static boolean w(byte[] bArr) throws IOException {
        int i = 0;
        while (true) {
            byte[] bArr2 = y;
            if (i >= bArr2.length) {
                return true;
            }
            if (bArr[i] != bArr2[i]) {
                return false;
            }
            i++;
        }
    }

    public final boolean A(byte[] bArr) throws IOException {
        b bVar = null;
        try {
            b bVar2 = new b(bArr);
            try {
                ByteOrder K2 = K(bVar2);
                this.h = K2;
                bVar2.c(K2);
                boolean z2 = bVar2.readShort() == 85;
                bVar2.close();
                return z2;
            } catch (Exception unused) {
                bVar = bVar2;
                if (bVar != null) {
                    bVar.close();
                }
                return false;
            } catch (Throwable th) {
                th = th;
                bVar = bVar2;
                if (bVar != null) {
                    bVar.close();
                }
                throw th;
            }
        } catch (Exception unused2) {
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public final boolean C(HashMap hashMap) throws IOException {
        d dVar;
        int m;
        d dVar2 = (d) hashMap.get(TAG_BITS_PER_SAMPLE);
        if (dVar2 != null) {
            int[] iArr = (int[]) dVar2.o(this.h);
            int[] iArr2 = BITS_PER_SAMPLE_RGB;
            if (Arrays.equals(iArr2, iArr)) {
                return true;
            }
            if (this.d == 3 && (dVar = (d) hashMap.get(TAG_PHOTOMETRIC_INTERPRETATION)) != null && (((m = dVar.m(this.h)) == 1 && Arrays.equals(iArr, BITS_PER_SAMPLE_GREYSCALE_2)) || (m == 6 && Arrays.equals(iArr, iArr2)))) {
                return true;
            }
        }
        if (v) {
            Log.d("ExifInterface", "Unsupported data type value");
            return false;
        }
        return false;
    }

    public final boolean E(HashMap hashMap) throws IOException {
        d dVar = (d) hashMap.get(TAG_IMAGE_LENGTH);
        d dVar2 = (d) hashMap.get(TAG_IMAGE_WIDTH);
        if (dVar == null || dVar2 == null) {
            return false;
        }
        return dVar.m(this.h) <= 512 && dVar2.m(this.h) <= 512;
    }

    public final boolean F(byte[] bArr) throws IOException {
        int i = 0;
        while (true) {
            byte[] bArr2 = I;
            if (i >= bArr2.length) {
                int i2 = 0;
                while (true) {
                    byte[] bArr3 = J;
                    if (i2 >= bArr3.length) {
                        return true;
                    }
                    if (bArr[I.length + i2 + 4] != bArr3[i2]) {
                        return false;
                    }
                    i2++;
                }
            } else if (bArr[i] != bArr2[i]) {
                return false;
            } else {
                i++;
            }
        }
    }

    public final void G(@NonNull InputStream inputStream) {
        Objects.requireNonNull(inputStream, "inputstream shouldn't be null");
        for (int i = 0; i < g0.length; i++) {
            try {
                try {
                    this.f[i] = new HashMap<>();
                } finally {
                    a();
                    if (v) {
                        J();
                    }
                }
            } catch (IOException | UnsupportedOperationException e2) {
                boolean z2 = v;
                if (z2) {
                    Log.w("ExifInterface", "Invalid image: ExifInterface got an unsupported image format file(ExifInterface supports JPEG and some RAW image formats only) or a corrupted JPEG file to ExifInterface.", e2);
                }
                a();
                if (!z2) {
                    return;
                }
            }
        }
        if (!this.e) {
            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream, 5000);
            this.d = i(bufferedInputStream);
            inputStream = bufferedInputStream;
        }
        if (U(this.d)) {
            g gVar = new g(inputStream);
            if (this.e) {
                o(gVar);
            } else {
                int i2 = this.d;
                if (i2 == 12) {
                    g(gVar);
                } else if (i2 == 7) {
                    j(gVar);
                } else if (i2 == 10) {
                    n(gVar);
                } else {
                    m(gVar);
                }
            }
            gVar.e(this.p);
            T(gVar);
        } else {
            b bVar = new b(inputStream);
            int i3 = this.d;
            if (i3 == 4) {
                h(bVar, 0, 0);
            } else if (i3 == 13) {
                k(bVar);
            } else if (i3 == 9) {
                l(bVar);
            } else if (i3 == 14) {
                p(bVar);
            }
        }
    }

    public final void I(b bVar) throws IOException {
        ByteOrder K2 = K(bVar);
        this.h = K2;
        bVar.c(K2);
        int readUnsignedShort = bVar.readUnsignedShort();
        int i = this.d;
        if (i != 7 && i != 10 && readUnsignedShort != 42) {
            throw new IOException("Invalid start code: " + Integer.toHexString(readUnsignedShort));
        }
        int readInt = bVar.readInt();
        if (readInt < 8) {
            throw new IOException("Invalid first Ifd offset: " + readInt);
        }
        int i2 = readInt - 8;
        if (i2 > 0) {
            bVar.d(i2);
        }
    }

    public final void J() {
        for (int i = 0; i < this.f.length; i++) {
            Log.d("ExifInterface", "The size of tag group[" + i + "]: " + this.f[i].size());
            for (Map.Entry<String, d> entry : this.f[i].entrySet()) {
                d value = entry.getValue();
                Log.d("ExifInterface", "tagName: " + entry.getKey() + ", tagType: " + value.toString() + ", tagValue: '" + value.n(this.h) + "'");
            }
        }
    }

    public final ByteOrder K(b bVar) throws IOException {
        short readShort = bVar.readShort();
        if (readShort == 18761) {
            if (v) {
                Log.d("ExifInterface", "readExifSegment: Byte Align II");
            }
            return ByteOrder.LITTLE_ENDIAN;
        } else if (readShort == 19789) {
            if (v) {
                Log.d("ExifInterface", "readExifSegment: Byte Align MM");
            }
            return ByteOrder.BIG_ENDIAN;
        } else {
            throw new IOException("Invalid byte order: " + Integer.toHexString(readShort));
        }
    }

    public final void L(byte[] bArr, int i) throws IOException {
        g gVar = new g(bArr);
        I(gVar);
        M(gVar, i);
    }

    /* JADX WARN: Removed duplicated region for block: B:46:0x0132  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x013a  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x0228  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x0246  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x0282  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void M(androidx.exifinterface.media.ExifInterface.g r30, int r31) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 927
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.exifinterface.media.ExifInterface.M(androidx.exifinterface.media.ExifInterface$g, int):void");
    }

    public final void N(String str) {
        for (int i = 0; i < g0.length; i++) {
            this.f[i].remove(str);
        }
    }

    public final void O(int i, String str, String str2) {
        if (this.f[i].isEmpty() || this.f[i].get(str) == null) {
            return;
        }
        HashMap<String, d>[] hashMapArr = this.f;
        hashMapArr[i].put(str2, hashMapArr[i].get(str));
        this.f[i].remove(str);
    }

    public final void P(g gVar, int i) throws IOException {
        d dVar = this.f[i].get(TAG_IMAGE_LENGTH);
        d dVar2 = this.f[i].get(TAG_IMAGE_WIDTH);
        if (dVar == null || dVar2 == null) {
            d dVar3 = this.f[i].get(TAG_JPEG_INTERCHANGE_FORMAT);
            d dVar4 = this.f[i].get(TAG_JPEG_INTERCHANGE_FORMAT_LENGTH);
            if (dVar3 == null || dVar4 == null) {
                return;
            }
            int m = dVar3.m(this.h);
            int m2 = dVar3.m(this.h);
            gVar.e(m);
            byte[] bArr = new byte[m2];
            gVar.read(bArr);
            h(new b(bArr), m, i);
        }
    }

    public final void Q(InputStream inputStream, OutputStream outputStream) throws IOException {
        if (v) {
            Log.d("ExifInterface", "saveJpegAttributes starting with (inputStream: " + inputStream + ", outputStream: " + outputStream + ")");
        }
        b bVar = new b(inputStream);
        c cVar = new c(outputStream, ByteOrder.BIG_ENDIAN);
        if (bVar.readByte() == -1) {
            cVar.b(-1);
            if (bVar.readByte() == -40) {
                cVar.b(-40);
                d dVar = null;
                if (getAttribute(TAG_XMP) != null && this.u) {
                    dVar = this.f[0].remove(TAG_XMP);
                }
                cVar.b(-1);
                cVar.b(-31);
                Y(cVar);
                if (dVar != null) {
                    this.f[0].put(TAG_XMP, dVar);
                }
                byte[] bArr = new byte[4096];
                while (bVar.readByte() == -1) {
                    byte readByte = bVar.readByte();
                    if (readByte == -39 || readByte == -38) {
                        cVar.b(-1);
                        cVar.b(readByte);
                        androidx.exifinterface.media.a.e(bVar, cVar);
                        return;
                    } else if (readByte != -31) {
                        cVar.b(-1);
                        cVar.b(readByte);
                        int readUnsignedShort = bVar.readUnsignedShort();
                        cVar.f(readUnsignedShort);
                        int i = readUnsignedShort - 2;
                        if (i < 0) {
                            throw new IOException("Invalid length");
                        }
                        while (i > 0) {
                            int read = bVar.read(bArr, 0, Math.min(i, 4096));
                            if (read >= 0) {
                                cVar.write(bArr, 0, read);
                                i -= read;
                            }
                        }
                    } else {
                        int readUnsignedShort2 = bVar.readUnsignedShort() - 2;
                        if (readUnsignedShort2 >= 0) {
                            byte[] bArr2 = new byte[6];
                            if (readUnsignedShort2 >= 6) {
                                if (bVar.read(bArr2) == 6) {
                                    if (Arrays.equals(bArr2, n0)) {
                                        bVar.d(readUnsignedShort2 - 6);
                                    }
                                } else {
                                    throw new IOException("Invalid exif");
                                }
                            }
                            cVar.b(-1);
                            cVar.b(readByte);
                            cVar.f(readUnsignedShort2 + 2);
                            if (readUnsignedShort2 >= 6) {
                                readUnsignedShort2 -= 6;
                                cVar.write(bArr2);
                            }
                            while (readUnsignedShort2 > 0) {
                                int read2 = bVar.read(bArr, 0, Math.min(readUnsignedShort2, 4096));
                                if (read2 >= 0) {
                                    cVar.write(bArr, 0, read2);
                                    readUnsignedShort2 -= read2;
                                }
                            }
                        } else {
                            throw new IOException("Invalid length");
                        }
                    }
                }
                throw new IOException("Invalid marker");
            }
            throw new IOException("Invalid marker");
        }
        throw new IOException("Invalid marker");
    }

    public final void R(InputStream inputStream, OutputStream outputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream;
        if (v) {
            Log.d("ExifInterface", "savePngAttributes starting with (inputStream: " + inputStream + ", outputStream: " + outputStream + ")");
        }
        b bVar = new b(inputStream);
        ByteOrder byteOrder = ByteOrder.BIG_ENDIAN;
        c cVar = new c(outputStream, byteOrder);
        byte[] bArr = E;
        androidx.exifinterface.media.a.f(bVar, cVar, bArr.length);
        int i = this.p;
        if (i == 0) {
            int readInt = bVar.readInt();
            cVar.c(readInt);
            androidx.exifinterface.media.a.f(bVar, cVar, readInt + 4 + 4);
        } else {
            androidx.exifinterface.media.a.f(bVar, cVar, ((i - bArr.length) - 4) - 4);
            bVar.d(bVar.readInt() + 4 + 4);
        }
        ByteArrayOutputStream byteArrayOutputStream2 = null;
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
        } catch (Throwable th) {
            th = th;
        }
        try {
            c cVar2 = new c(byteArrayOutputStream, byteOrder);
            Y(cVar2);
            byte[] byteArray = ((ByteArrayOutputStream) cVar2.h).toByteArray();
            cVar.write(byteArray);
            CRC32 crc32 = new CRC32();
            crc32.update(byteArray, 4, byteArray.length - 4);
            cVar.c((int) crc32.getValue());
            androidx.exifinterface.media.a.c(byteArrayOutputStream);
            androidx.exifinterface.media.a.e(bVar, cVar);
        } catch (Throwable th2) {
            th = th2;
            byteArrayOutputStream2 = byteArrayOutputStream;
            androidx.exifinterface.media.a.c(byteArrayOutputStream2);
            throw th;
        }
    }

    public final void S(InputStream inputStream, OutputStream outputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream;
        int i;
        int i2;
        int i3;
        int i4;
        if (v) {
            Log.d("ExifInterface", "saveWebpAttributes starting with (inputStream: " + inputStream + ", outputStream: " + outputStream + ")");
        }
        ByteOrder byteOrder = ByteOrder.LITTLE_ENDIAN;
        b bVar = new b(inputStream, byteOrder);
        c cVar = new c(outputStream, byteOrder);
        byte[] bArr = I;
        androidx.exifinterface.media.a.f(bVar, cVar, bArr.length);
        byte[] bArr2 = J;
        bVar.d(bArr2.length + 4);
        ByteArrayOutputStream byteArrayOutputStream2 = null;
        try {
            try {
                byteArrayOutputStream = new ByteArrayOutputStream();
            } catch (Throwable th) {
                th = th;
            }
        } catch (Exception e2) {
            e = e2;
        }
        try {
            c cVar2 = new c(byteArrayOutputStream, byteOrder);
            int i5 = this.p;
            if (i5 != 0) {
                androidx.exifinterface.media.a.f(bVar, cVar2, ((i5 - ((bArr.length + 4) + bArr2.length)) - 4) - 4);
                bVar.d(4);
                bVar.d(bVar.readInt());
                Y(cVar2);
            } else {
                byte[] bArr3 = new byte[4];
                if (bVar.read(bArr3) == 4) {
                    byte[] bArr4 = M;
                    boolean z2 = true;
                    if (Arrays.equals(bArr3, bArr4)) {
                        int readInt = bVar.readInt();
                        byte[] bArr5 = new byte[readInt % 2 == 1 ? readInt + 1 : readInt];
                        bVar.read(bArr5);
                        bArr5[0] = (byte) (8 | bArr5[0]);
                        if (((bArr5[0] >> 1) & 1) != 1) {
                            z2 = false;
                        }
                        cVar2.write(bArr4);
                        cVar2.c(readInt);
                        cVar2.write(bArr5);
                        if (z2) {
                            d(bVar, cVar2, P, null);
                            while (true) {
                                byte[] bArr6 = new byte[4];
                                inputStream.read(bArr6);
                                if (!Arrays.equals(bArr6, Q)) {
                                    break;
                                }
                                e(bVar, cVar2, bArr6);
                            }
                            Y(cVar2);
                        } else {
                            d(bVar, cVar2, O, N);
                            Y(cVar2);
                        }
                    } else {
                        byte[] bArr7 = O;
                        if (Arrays.equals(bArr3, bArr7) || Arrays.equals(bArr3, N)) {
                            int readInt2 = bVar.readInt();
                            int i6 = readInt2 % 2 == 1 ? readInt2 + 1 : readInt2;
                            byte[] bArr8 = new byte[3];
                            if (Arrays.equals(bArr3, bArr7)) {
                                bVar.read(bArr8);
                                byte[] bArr9 = new byte[3];
                                if (bVar.read(bArr9) == 3 && Arrays.equals(L, bArr9)) {
                                    i = bVar.readInt();
                                    i2 = (i << 18) >> 18;
                                    i3 = (i << 2) >> 18;
                                    i6 -= 10;
                                    i4 = 0;
                                } else {
                                    throw new IOException("Encountered error while checking VP8 signature");
                                }
                            } else if (!Arrays.equals(bArr3, N)) {
                                i = 0;
                                i2 = 0;
                                i3 = 0;
                                i4 = 0;
                            } else if (bVar.readByte() == 47) {
                                i = bVar.readInt();
                                i4 = i & 8;
                                i6 -= 5;
                                i3 = ((i << 4) >> 18) + 1;
                                i2 = ((i << 18) >> 18) + 1;
                            } else {
                                throw new IOException("Encountered error while checking VP8L signature");
                            }
                            cVar2.write(bArr4);
                            cVar2.c(10);
                            byte[] bArr10 = new byte[10];
                            bArr10[0] = (byte) (bArr10[0] | 8);
                            bArr10[0] = (byte) (bArr10[0] | (i4 << 4));
                            int i7 = i2 - 1;
                            int i8 = i3 - 1;
                            bArr10[4] = (byte) i7;
                            bArr10[5] = (byte) (i7 >> 8);
                            bArr10[6] = (byte) (i7 >> 16);
                            bArr10[7] = (byte) i8;
                            bArr10[8] = (byte) (i8 >> 8);
                            bArr10[9] = (byte) (i8 >> 16);
                            cVar2.write(bArr10);
                            cVar2.write(bArr3);
                            cVar2.c(readInt2);
                            if (Arrays.equals(bArr3, bArr7)) {
                                cVar2.write(bArr8);
                                cVar2.write(L);
                                cVar2.c(i);
                            } else if (Arrays.equals(bArr3, N)) {
                                cVar2.write(47);
                                cVar2.c(i);
                            }
                            androidx.exifinterface.media.a.f(bVar, cVar2, i6);
                            Y(cVar2);
                        }
                    }
                } else {
                    throw new IOException("Encountered invalid length while parsing WebP chunk type");
                }
            }
            androidx.exifinterface.media.a.e(bVar, cVar2);
            int size = byteArrayOutputStream.size();
            byte[] bArr11 = J;
            cVar.c(size + bArr11.length);
            cVar.write(bArr11);
            byteArrayOutputStream.writeTo(cVar);
            androidx.exifinterface.media.a.c(byteArrayOutputStream);
        } catch (Exception e3) {
            e = e3;
            throw new IOException("Failed to save WebP file", e);
        } catch (Throwable th2) {
            th = th2;
            byteArrayOutputStream2 = byteArrayOutputStream;
            androidx.exifinterface.media.a.c(byteArrayOutputStream2);
            throw th;
        }
    }

    public final void T(b bVar) throws IOException {
        HashMap<String, d> hashMap = this.f[4];
        d dVar = hashMap.get(TAG_COMPRESSION);
        if (dVar != null) {
            int m = dVar.m(this.h);
            this.o = m;
            if (m != 1) {
                if (m == 6) {
                    r(bVar, hashMap);
                    return;
                } else if (m != 7) {
                    return;
                }
            }
            if (C(hashMap)) {
                s(bVar, hashMap);
                return;
            }
            return;
        }
        this.o = 6;
        r(bVar, hashMap);
    }

    public final void V(int i, int i2) throws IOException {
        if (!this.f[i].isEmpty() && !this.f[i2].isEmpty()) {
            d dVar = this.f[i].get(TAG_IMAGE_LENGTH);
            d dVar2 = this.f[i].get(TAG_IMAGE_WIDTH);
            d dVar3 = this.f[i2].get(TAG_IMAGE_LENGTH);
            d dVar4 = this.f[i2].get(TAG_IMAGE_WIDTH);
            if (dVar == null || dVar2 == null) {
                if (v) {
                    Log.d("ExifInterface", "First image does not contain valid size information");
                }
            } else if (dVar3 != null && dVar4 != null) {
                int m = dVar.m(this.h);
                int m2 = dVar2.m(this.h);
                int m3 = dVar3.m(this.h);
                int m4 = dVar4.m(this.h);
                if (m >= m3 || m2 >= m4) {
                    return;
                }
                HashMap<String, d>[] hashMapArr = this.f;
                HashMap<String, d> hashMap = hashMapArr[i];
                hashMapArr[i] = hashMapArr[i2];
                hashMapArr[i2] = hashMap;
            } else if (v) {
                Log.d("ExifInterface", "Second image does not contain valid size information");
            }
        } else if (v) {
            Log.d("ExifInterface", "Cannot perform swap since only one image data exists");
        }
    }

    public final void W(g gVar, int i) throws IOException {
        d j;
        d j2;
        d dVar = this.f[i].get(TAG_DEFAULT_CROP_SIZE);
        d dVar2 = this.f[i].get(TAG_RW2_SENSOR_TOP_BORDER);
        d dVar3 = this.f[i].get(TAG_RW2_SENSOR_LEFT_BORDER);
        d dVar4 = this.f[i].get(TAG_RW2_SENSOR_BOTTOM_BORDER);
        d dVar5 = this.f[i].get(TAG_RW2_SENSOR_RIGHT_BORDER);
        if (dVar == null) {
            if (dVar2 != null && dVar3 != null && dVar4 != null && dVar5 != null) {
                int m = dVar2.m(this.h);
                int m2 = dVar4.m(this.h);
                int m3 = dVar5.m(this.h);
                int m4 = dVar3.m(this.h);
                if (m2 <= m || m3 <= m4) {
                    return;
                }
                d j3 = d.j(m2 - m, this.h);
                d j4 = d.j(m3 - m4, this.h);
                this.f[i].put(TAG_IMAGE_LENGTH, j3);
                this.f[i].put(TAG_IMAGE_WIDTH, j4);
                return;
            }
            P(gVar, i);
            return;
        }
        if (dVar.f1293a == 5) {
            f[] fVarArr = (f[]) dVar.o(this.h);
            if (fVarArr != null && fVarArr.length == 2) {
                j = d.h(fVarArr[0], this.h);
                j2 = d.h(fVarArr[1], this.h);
            } else {
                Log.w("ExifInterface", "Invalid crop size values. cropSize=" + Arrays.toString(fVarArr));
                return;
            }
        } else {
            int[] iArr = (int[]) dVar.o(this.h);
            if (iArr != null && iArr.length == 2) {
                j = d.j(iArr[0], this.h);
                j2 = d.j(iArr[1], this.h);
            } else {
                Log.w("ExifInterface", "Invalid crop size values. cropSize=" + Arrays.toString(iArr));
                return;
            }
        }
        this.f[i].put(TAG_IMAGE_WIDTH, j);
        this.f[i].put(TAG_IMAGE_LENGTH, j2);
    }

    public final void X() throws IOException {
        V(0, 5);
        V(0, 4);
        V(5, 4);
        d dVar = this.f[1].get(TAG_PIXEL_X_DIMENSION);
        d dVar2 = this.f[1].get(TAG_PIXEL_Y_DIMENSION);
        if (dVar != null && dVar2 != null) {
            this.f[0].put(TAG_IMAGE_WIDTH, dVar);
            this.f[0].put(TAG_IMAGE_LENGTH, dVar2);
        }
        if (this.f[4].isEmpty() && E(this.f[5])) {
            HashMap<String, d>[] hashMapArr = this.f;
            hashMapArr[4] = hashMapArr[5];
            hashMapArr[5] = new HashMap<>();
        }
        if (!E(this.f[4])) {
            Log.d("ExifInterface", "No image meets the size requirements of a thumbnail image.");
        }
        O(0, TAG_THUMBNAIL_ORIENTATION, TAG_ORIENTATION);
        O(0, TAG_THUMBNAIL_IMAGE_LENGTH, TAG_IMAGE_LENGTH);
        O(0, TAG_THUMBNAIL_IMAGE_WIDTH, TAG_IMAGE_WIDTH);
        O(5, TAG_THUMBNAIL_ORIENTATION, TAG_ORIENTATION);
        O(5, TAG_THUMBNAIL_IMAGE_LENGTH, TAG_IMAGE_LENGTH);
        O(5, TAG_THUMBNAIL_IMAGE_WIDTH, TAG_IMAGE_WIDTH);
        O(4, TAG_ORIENTATION, TAG_THUMBNAIL_ORIENTATION);
        O(4, TAG_IMAGE_LENGTH, TAG_THUMBNAIL_IMAGE_LENGTH);
        O(4, TAG_IMAGE_WIDTH, TAG_THUMBNAIL_IMAGE_WIDTH);
    }

    public final int Y(c cVar) throws IOException {
        e[][] eVarArr = g0;
        int[] iArr = new int[eVarArr.length];
        int[] iArr2 = new int[eVarArr.length];
        for (e eVar : h0) {
            N(eVar.b);
        }
        if (this.i) {
            if (this.j) {
                N(TAG_STRIP_OFFSETS);
                N(TAG_STRIP_BYTE_COUNTS);
            } else {
                N(TAG_JPEG_INTERCHANGE_FORMAT);
                N(TAG_JPEG_INTERCHANGE_FORMAT_LENGTH);
            }
        }
        for (int i = 0; i < g0.length; i++) {
            for (Object obj : this.f[i].entrySet().toArray()) {
                Map.Entry entry = (Map.Entry) obj;
                if (entry.getValue() == null) {
                    this.f[i].remove(entry.getKey());
                }
            }
        }
        if (!this.f[1].isEmpty()) {
            this.f[0].put(h0[1].b, d.f(0L, this.h));
        }
        if (!this.f[2].isEmpty()) {
            this.f[0].put(h0[2].b, d.f(0L, this.h));
        }
        if (!this.f[3].isEmpty()) {
            this.f[1].put(h0[3].b, d.f(0L, this.h));
        }
        if (this.i) {
            if (this.j) {
                this.f[4].put(TAG_STRIP_OFFSETS, d.j(0, this.h));
                this.f[4].put(TAG_STRIP_BYTE_COUNTS, d.j(this.m, this.h));
            } else {
                this.f[4].put(TAG_JPEG_INTERCHANGE_FORMAT, d.f(0L, this.h));
                this.f[4].put(TAG_JPEG_INTERCHANGE_FORMAT_LENGTH, d.f(this.m, this.h));
            }
        }
        for (int i2 = 0; i2 < g0.length; i2++) {
            int i3 = 0;
            for (Map.Entry<String, d> entry2 : this.f[i2].entrySet()) {
                int p = entry2.getValue().p();
                if (p > 4) {
                    i3 += p;
                }
            }
            iArr2[i2] = iArr2[i2] + i3;
        }
        int i4 = 8;
        for (int i5 = 0; i5 < g0.length; i5++) {
            if (!this.f[i5].isEmpty()) {
                iArr[i5] = i4;
                i4 += (this.f[i5].size() * 12) + 2 + 4 + iArr2[i5];
            }
        }
        if (this.i) {
            if (this.j) {
                this.f[4].put(TAG_STRIP_OFFSETS, d.j(i4, this.h));
            } else {
                this.f[4].put(TAG_JPEG_INTERCHANGE_FORMAT, d.f(i4, this.h));
            }
            this.l = i4;
            i4 += this.m;
        }
        if (this.d == 4) {
            i4 += 8;
        }
        if (v) {
            for (int i6 = 0; i6 < g0.length; i6++) {
                Log.d("ExifInterface", String.format("index: %d, offsets: %d, tag count: %d, data sizes: %d, total size: %d", Integer.valueOf(i6), Integer.valueOf(iArr[i6]), Integer.valueOf(this.f[i6].size()), Integer.valueOf(iArr2[i6]), Integer.valueOf(i4)));
            }
        }
        if (!this.f[1].isEmpty()) {
            this.f[0].put(h0[1].b, d.f(iArr[1], this.h));
        }
        if (!this.f[2].isEmpty()) {
            this.f[0].put(h0[2].b, d.f(iArr[2], this.h));
        }
        if (!this.f[3].isEmpty()) {
            this.f[1].put(h0[3].b, d.f(iArr[3], this.h));
        }
        int i7 = this.d;
        if (i7 == 4) {
            cVar.f(i4);
            cVar.write(n0);
        } else if (i7 == 13) {
            cVar.c(i4);
            cVar.write(F);
        } else if (i7 == 14) {
            cVar.write(K);
            cVar.c(i4);
        }
        cVar.d(this.h == ByteOrder.BIG_ENDIAN ? (short) 19789 : (short) 18761);
        cVar.a(this.h);
        cVar.f(42);
        cVar.e(8L);
        for (int i8 = 0; i8 < g0.length; i8++) {
            if (!this.f[i8].isEmpty()) {
                cVar.f(this.f[i8].size());
                int size = iArr[i8] + 2 + (this.f[i8].size() * 12) + 4;
                for (Map.Entry<String, d> entry3 : this.f[i8].entrySet()) {
                    int i9 = j0[i8].get(entry3.getKey()).f1294a;
                    d value = entry3.getValue();
                    int p2 = value.p();
                    cVar.f(i9);
                    cVar.f(value.f1293a);
                    cVar.c(value.b);
                    if (p2 > 4) {
                        cVar.e(size);
                        size += p2;
                    } else {
                        cVar.write(value.d);
                        if (p2 < 4) {
                            while (p2 < 4) {
                                cVar.b(0);
                                p2++;
                            }
                        }
                    }
                }
                if (i8 == 0 && !this.f[4].isEmpty()) {
                    cVar.e(iArr[4]);
                } else {
                    cVar.e(0L);
                }
                for (Map.Entry<String, d> entry4 : this.f[i8].entrySet()) {
                    byte[] bArr = entry4.getValue().d;
                    if (bArr.length > 4) {
                        cVar.write(bArr, 0, bArr.length);
                    }
                }
            }
        }
        if (this.i) {
            cVar.write(getThumbnailBytes());
        }
        if (this.d == 14 && i4 % 2 == 1) {
            cVar.b(0);
        }
        cVar.a(ByteOrder.BIG_ENDIAN);
        return i4;
    }

    public final void a() {
        String attribute = getAttribute(TAG_DATETIME_ORIGINAL);
        if (attribute != null && getAttribute(TAG_DATETIME) == null) {
            this.f[0].put(TAG_DATETIME, d.e(attribute));
        }
        if (getAttribute(TAG_IMAGE_WIDTH) == null) {
            this.f[0].put(TAG_IMAGE_WIDTH, d.f(0L, this.h));
        }
        if (getAttribute(TAG_IMAGE_LENGTH) == null) {
            this.f[0].put(TAG_IMAGE_LENGTH, d.f(0L, this.h));
        }
        if (getAttribute(TAG_ORIENTATION) == null) {
            this.f[0].put(TAG_ORIENTATION, d.f(0L, this.h));
        }
        if (getAttribute(TAG_LIGHT_SOURCE) == null) {
            this.f[1].put(TAG_LIGHT_SOURCE, d.f(0L, this.h));
        }
    }

    public final String b(double d2) {
        long j = (long) d2;
        double d3 = d2 - j;
        long j2 = (long) (d3 * 60.0d);
        long round = Math.round((d3 - (j2 / 60.0d)) * 3600.0d * 1.0E7d);
        return j + "/1," + j2 + "/1," + round + "/10000000";
    }

    public final void d(b bVar, c cVar, byte[] bArr, byte[] bArr2) throws IOException {
        Charset charset;
        String str;
        while (true) {
            byte[] bArr3 = new byte[4];
            if (bVar.read(bArr3) != 4) {
                StringBuilder sb = new StringBuilder();
                sb.append("Encountered invalid length while copying WebP chunks up tochunk type ");
                sb.append(new String(bArr, m0));
                if (bArr2 == null) {
                    str = "";
                } else {
                    str = " or " + new String(bArr2, charset);
                }
                sb.append(str);
                throw new IOException(sb.toString());
            }
            e(bVar, cVar, bArr3);
            if (Arrays.equals(bArr3, bArr)) {
                return;
            }
            if (bArr2 != null && Arrays.equals(bArr3, bArr2)) {
                return;
            }
        }
    }

    public final void e(b bVar, c cVar, byte[] bArr) throws IOException {
        int readInt = bVar.readInt();
        cVar.write(bArr);
        cVar.c(readInt);
        if (readInt % 2 == 1) {
            readInt++;
        }
        androidx.exifinterface.media.a.f(bVar, cVar, readInt);
    }

    @Nullable
    public final d f(@NonNull String str) {
        Objects.requireNonNull(str, "tag shouldn't be null");
        if (TAG_ISO_SPEED_RATINGS.equals(str)) {
            if (v) {
                Log.d("ExifInterface", "getExifAttribute: Replacing TAG_ISO_SPEED_RATINGS with TAG_PHOTOGRAPHIC_SENSITIVITY.");
            }
            str = TAG_PHOTOGRAPHIC_SENSITIVITY;
        }
        for (int i = 0; i < g0.length; i++) {
            d dVar = this.f[i].get(str);
            if (dVar != null) {
                return dVar;
            }
        }
        return null;
    }

    public void flipHorizontally() {
        int i = 1;
        switch (getAttributeInt(TAG_ORIENTATION, 1)) {
            case 1:
                i = 2;
                break;
            case 2:
                break;
            case 3:
                i = 4;
                break;
            case 4:
                i = 3;
                break;
            case 5:
                i = 6;
                break;
            case 6:
                i = 5;
                break;
            case 7:
                i = 8;
                break;
            case 8:
                i = 7;
                break;
            default:
                i = 0;
                break;
        }
        setAttribute(TAG_ORIENTATION, Integer.toString(i));
    }

    public void flipVertically() {
        int i = 1;
        switch (getAttributeInt(TAG_ORIENTATION, 1)) {
            case 1:
                i = 4;
                break;
            case 2:
                i = 3;
                break;
            case 3:
                i = 2;
                break;
            case 4:
                break;
            case 5:
                i = 8;
                break;
            case 6:
                i = 7;
                break;
            case 7:
                i = 6;
                break;
            case 8:
                i = 5;
                break;
            default:
                i = 0;
                break;
        }
        setAttribute(TAG_ORIENTATION, Integer.toString(i));
    }

    public final void g(g gVar) throws IOException {
        String str;
        String str2;
        if (Build.VERSION.SDK_INT >= 28) {
            MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
            try {
                try {
                    a.b.a(mediaMetadataRetriever, new a(this, gVar));
                    String extractMetadata = mediaMetadataRetriever.extractMetadata(33);
                    String extractMetadata2 = mediaMetadataRetriever.extractMetadata(34);
                    String extractMetadata3 = mediaMetadataRetriever.extractMetadata(26);
                    String extractMetadata4 = mediaMetadataRetriever.extractMetadata(17);
                    String str3 = null;
                    if ("yes".equals(extractMetadata3)) {
                        str3 = mediaMetadataRetriever.extractMetadata(29);
                        str = mediaMetadataRetriever.extractMetadata(30);
                        str2 = mediaMetadataRetriever.extractMetadata(31);
                    } else if ("yes".equals(extractMetadata4)) {
                        str3 = mediaMetadataRetriever.extractMetadata(18);
                        str = mediaMetadataRetriever.extractMetadata(19);
                        str2 = mediaMetadataRetriever.extractMetadata(24);
                    } else {
                        str = null;
                        str2 = null;
                    }
                    if (str3 != null) {
                        this.f[0].put(TAG_IMAGE_WIDTH, d.j(Integer.parseInt(str3), this.h));
                    }
                    if (str != null) {
                        this.f[0].put(TAG_IMAGE_LENGTH, d.j(Integer.parseInt(str), this.h));
                    }
                    if (str2 != null) {
                        int i = 1;
                        int parseInt = Integer.parseInt(str2);
                        if (parseInt == 90) {
                            i = 6;
                        } else if (parseInt == 180) {
                            i = 3;
                        } else if (parseInt == 270) {
                            i = 8;
                        }
                        this.f[0].put(TAG_ORIENTATION, d.j(i, this.h));
                    }
                    if (extractMetadata != null && extractMetadata2 != null) {
                        int parseInt2 = Integer.parseInt(extractMetadata);
                        int parseInt3 = Integer.parseInt(extractMetadata2);
                        if (parseInt3 > 6) {
                            gVar.e(parseInt2);
                            byte[] bArr = new byte[6];
                            if (gVar.read(bArr) == 6) {
                                int i2 = parseInt2 + 6;
                                int i3 = parseInt3 - 6;
                                if (Arrays.equals(bArr, n0)) {
                                    byte[] bArr2 = new byte[i3];
                                    if (gVar.read(bArr2) == i3) {
                                        this.p = i2;
                                        L(bArr2, 0);
                                    } else {
                                        throw new IOException("Can't read exif");
                                    }
                                } else {
                                    throw new IOException("Invalid identifier");
                                }
                            } else {
                                throw new IOException("Can't read identifier");
                            }
                        } else {
                            throw new IOException("Invalid exif length");
                        }
                    }
                    if (v) {
                        Log.d("ExifInterface", "Heif meta: " + str3 + "x" + str + ", rotation " + str2);
                    }
                    return;
                } catch (RuntimeException unused) {
                    throw new UnsupportedOperationException("Failed to read EXIF from HEIF file. Given stream is either malformed or unsupported.");
                }
            } finally {
                mediaMetadataRetriever.release();
            }
        }
        throw new UnsupportedOperationException("Reading EXIF from HEIF files is supported from SDK 28 and above");
    }

    public double getAltitude(double d2) {
        double attributeDouble = getAttributeDouble(TAG_GPS_ALTITUDE, -1.0d);
        int attributeInt = getAttributeInt(TAG_GPS_ALTITUDE_REF, -1);
        if (attributeDouble < 0.0d || attributeInt < 0) {
            return d2;
        }
        return attributeDouble * (attributeInt != 1 ? 1 : -1);
    }

    @Nullable
    public String getAttribute(@NonNull String str) {
        Objects.requireNonNull(str, "tag shouldn't be null");
        d f2 = f(str);
        if (f2 != null) {
            if (!k0.contains(str)) {
                return f2.n(this.h);
            }
            if (str.equals(TAG_GPS_TIMESTAMP)) {
                int i = f2.f1293a;
                if (i != 5 && i != 10) {
                    Log.w("ExifInterface", "GPS Timestamp format is not rational. format=" + f2.f1293a);
                    return null;
                }
                f[] fVarArr = (f[]) f2.o(this.h);
                if (fVarArr != null && fVarArr.length == 3) {
                    return String.format("%02d:%02d:%02d", Integer.valueOf((int) (((float) fVarArr[0].f1295a) / ((float) fVarArr[0].b))), Integer.valueOf((int) (((float) fVarArr[1].f1295a) / ((float) fVarArr[1].b))), Integer.valueOf((int) (((float) fVarArr[2].f1295a) / ((float) fVarArr[2].b))));
                }
                Log.w("ExifInterface", "Invalid GPS Timestamp array. array=" + Arrays.toString(fVarArr));
                return null;
            }
            try {
                return Double.toString(f2.l(this.h));
            } catch (NumberFormatException unused) {
            }
        }
        return null;
    }

    @Nullable
    public byte[] getAttributeBytes(@NonNull String str) {
        Objects.requireNonNull(str, "tag shouldn't be null");
        d f2 = f(str);
        if (f2 != null) {
            return f2.d;
        }
        return null;
    }

    public double getAttributeDouble(@NonNull String str, double d2) {
        Objects.requireNonNull(str, "tag shouldn't be null");
        d f2 = f(str);
        if (f2 == null) {
            return d2;
        }
        try {
            return f2.l(this.h);
        } catch (NumberFormatException unused) {
            return d2;
        }
    }

    public int getAttributeInt(@NonNull String str, int i) {
        Objects.requireNonNull(str, "tag shouldn't be null");
        d f2 = f(str);
        if (f2 == null) {
            return i;
        }
        try {
            return f2.m(this.h);
        } catch (NumberFormatException unused) {
            return i;
        }
    }

    @Nullable
    public long[] getAttributeRange(@NonNull String str) {
        Objects.requireNonNull(str, "tag shouldn't be null");
        if (!this.t) {
            d f2 = f(str);
            if (f2 != null) {
                return new long[]{f2.c, f2.d.length};
            }
            return null;
        }
        throw new IllegalStateException("The underlying file has been modified since being parsed");
    }

    @Nullable
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public Long getDateTime() {
        return H(getAttribute(TAG_DATETIME), getAttribute(TAG_SUBSEC_TIME), getAttribute(TAG_OFFSET_TIME));
    }

    @Nullable
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public Long getDateTimeDigitized() {
        return H(getAttribute(TAG_DATETIME_DIGITIZED), getAttribute(TAG_SUBSEC_TIME_DIGITIZED), getAttribute(TAG_OFFSET_TIME_DIGITIZED));
    }

    @Nullable
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public Long getDateTimeOriginal() {
        return H(getAttribute(TAG_DATETIME_ORIGINAL), getAttribute(TAG_SUBSEC_TIME_ORIGINAL), getAttribute(TAG_OFFSET_TIME_ORIGINAL));
    }

    @Nullable
    @SuppressLint({"AutoBoxing"})
    public Long getGpsDateTime() {
        String attribute = getAttribute(TAG_GPS_DATESTAMP);
        String attribute2 = getAttribute(TAG_GPS_TIMESTAMP);
        if (attribute != null && attribute2 != null) {
            Pattern pattern = p0;
            if (pattern.matcher(attribute).matches() || pattern.matcher(attribute2).matches()) {
                String str = attribute + ' ' + attribute2;
                ParsePosition parsePosition = new ParsePosition(0);
                try {
                    Date parse = R.parse(str, parsePosition);
                    if (parse == null && (parse = S.parse(str, parsePosition)) == null) {
                        return null;
                    }
                    return Long.valueOf(parse.getTime());
                } catch (IllegalArgumentException unused) {
                }
            }
        }
        return null;
    }

    @Deprecated
    public boolean getLatLong(float[] fArr) {
        double[] latLong = getLatLong();
        if (latLong == null) {
            return false;
        }
        fArr[0] = (float) latLong[0];
        fArr[1] = (float) latLong[1];
        return true;
    }

    public int getRotationDegrees() {
        switch (getAttributeInt(TAG_ORIENTATION, 1)) {
            case 3:
            case 4:
                return 180;
            case 5:
            case 8:
                return DfuException.ERROR_READ_DEVICE_INFO_ERROR;
            case 6:
            case 7:
                return 90;
            default:
                return 0;
        }
    }

    @Nullable
    public byte[] getThumbnail() {
        int i = this.o;
        if (i == 6 || i == 7) {
            return getThumbnailBytes();
        }
        return null;
    }

    @Nullable
    public Bitmap getThumbnailBitmap() {
        if (this.i) {
            if (this.n == null) {
                this.n = getThumbnailBytes();
            }
            int i = this.o;
            if (i == 6 || i == 7) {
                return BitmapFactory.decodeByteArray(this.n, 0, this.m);
            }
            if (i == 1) {
                int length = this.n.length / 3;
                int[] iArr = new int[length];
                for (int i2 = 0; i2 < length; i2++) {
                    byte[] bArr = this.n;
                    int i3 = i2 * 3;
                    iArr[i2] = (bArr[i3] << 16) + 0 + (bArr[i3 + 1] << 8) + bArr[i3 + 2];
                }
                d dVar = this.f[4].get(TAG_THUMBNAIL_IMAGE_LENGTH);
                d dVar2 = this.f[4].get(TAG_THUMBNAIL_IMAGE_WIDTH);
                if (dVar != null && dVar2 != null) {
                    return Bitmap.createBitmap(iArr, dVar2.m(this.h), dVar.m(this.h), Bitmap.Config.ARGB_8888);
                }
            }
            return null;
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0063 A[Catch: Exception -> 0x009c, all -> 0x00b9, TRY_ENTER, TRY_LEAVE, TryCatch #0 {Exception -> 0x009c, blocks: (B:36:0x0063, B:39:0x0079, B:41:0x0085, B:46:0x0090, B:47:0x0095, B:48:0x0096, B:49:0x009b, B:52:0x009e, B:53:0x00a3), top: B:69:0x0061 }] */
    /* JADX WARN: Removed duplicated region for block: B:52:0x009e A[Catch: Exception -> 0x009c, all -> 0x00b9, TryCatch #0 {Exception -> 0x009c, blocks: (B:36:0x0063, B:39:0x0079, B:41:0x0085, B:46:0x0090, B:47:0x0095, B:48:0x0096, B:49:0x009b, B:52:0x009e, B:53:0x00a3), top: B:69:0x0061 }] */
    /* JADX WARN: Removed duplicated region for block: B:61:0x00b5  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x00c0  */
    /* JADX WARN: Type inference failed for: r1v1, types: [byte[]] */
    /* JADX WARN: Type inference failed for: r1v2 */
    /* JADX WARN: Type inference failed for: r1v5, types: [android.content.res.AssetManager$AssetInputStream, java.io.Closeable, java.io.InputStream] */
    @androidx.annotation.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public byte[] getThumbnailBytes() {
        /*
            Method dump skipped, instructions count: 196
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.exifinterface.media.ExifInterface.getThumbnailBytes():byte[]");
    }

    @Nullable
    public long[] getThumbnailRange() {
        if (!this.t) {
            if (this.i) {
                if (!this.j || this.k) {
                    return new long[]{this.l + this.p, this.m};
                }
                return null;
            }
            return null;
        }
        throw new IllegalStateException("The underlying file has been modified since being parsed");
    }

    /* JADX WARN: Code restructure failed: missing block: B:68:0x019a, code lost:
        r22.c(r21.h);
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x019f, code lost:
        return;
     */
    /* JADX WARN: Removed duplicated region for block: B:34:0x00ba A[FALL_THROUGH] */
    /* JADX WARN: Removed duplicated region for block: B:63:0x0184 A[LOOP:0: B:10:0x0038->B:63:0x0184, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:76:0x018e A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void h(androidx.exifinterface.media.ExifInterface.b r22, int r23, int r24) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 542
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.exifinterface.media.ExifInterface.h(androidx.exifinterface.media.ExifInterface$b, int, int):void");
    }

    public boolean hasAttribute(@NonNull String str) {
        return f(str) != null;
    }

    public boolean hasThumbnail() {
        return this.i;
    }

    public final int i(BufferedInputStream bufferedInputStream) throws IOException {
        bufferedInputStream.mark(5000);
        byte[] bArr = new byte[5000];
        bufferedInputStream.read(bArr);
        bufferedInputStream.reset();
        if (w(bArr)) {
            return 4;
        }
        if (z(bArr)) {
            return 9;
        }
        if (v(bArr)) {
            return 12;
        }
        if (x(bArr)) {
            return 7;
        }
        if (A(bArr)) {
            return 10;
        }
        if (y(bArr)) {
            return 13;
        }
        return F(bArr) ? 14 : 0;
    }

    public boolean isFlipped() {
        int attributeInt = getAttributeInt(TAG_ORIENTATION, 1);
        return attributeInt == 2 || attributeInt == 7 || attributeInt == 4 || attributeInt == 5;
    }

    public boolean isThumbnailCompressed() {
        if (this.i) {
            int i = this.o;
            return i == 6 || i == 7;
        }
        return false;
    }

    public final void j(g gVar) throws IOException {
        m(gVar);
        d dVar = this.f[1].get(TAG_MAKER_NOTE);
        if (dVar != null) {
            g gVar2 = new g(dVar.d);
            gVar2.c(this.h);
            byte[] bArr = C;
            byte[] bArr2 = new byte[bArr.length];
            gVar2.readFully(bArr2);
            gVar2.e(0L);
            byte[] bArr3 = D;
            byte[] bArr4 = new byte[bArr3.length];
            gVar2.readFully(bArr4);
            if (Arrays.equals(bArr2, bArr)) {
                gVar2.e(8L);
            } else if (Arrays.equals(bArr4, bArr3)) {
                gVar2.e(12L);
            }
            M(gVar2, 6);
            d dVar2 = this.f[7].get(TAG_ORF_PREVIEW_IMAGE_START);
            d dVar3 = this.f[7].get(TAG_ORF_PREVIEW_IMAGE_LENGTH);
            if (dVar2 != null && dVar3 != null) {
                this.f[5].put(TAG_JPEG_INTERCHANGE_FORMAT, dVar2);
                this.f[5].put(TAG_JPEG_INTERCHANGE_FORMAT_LENGTH, dVar3);
            }
            d dVar4 = this.f[8].get(TAG_ORF_ASPECT_FRAME);
            if (dVar4 != null) {
                int[] iArr = (int[]) dVar4.o(this.h);
                if (iArr != null && iArr.length == 4) {
                    if (iArr[2] <= iArr[0] || iArr[3] <= iArr[1]) {
                        return;
                    }
                    int i = (iArr[2] - iArr[0]) + 1;
                    int i2 = (iArr[3] - iArr[1]) + 1;
                    if (i < i2) {
                        int i3 = i + i2;
                        i2 = i3 - i2;
                        i = i3 - i2;
                    }
                    d j = d.j(i, this.h);
                    d j2 = d.j(i2, this.h);
                    this.f[0].put(TAG_IMAGE_WIDTH, j);
                    this.f[0].put(TAG_IMAGE_LENGTH, j2);
                    return;
                }
                Log.w("ExifInterface", "Invalid aspect frame values. frame=" + Arrays.toString(iArr));
            }
        }
    }

    public final void k(b bVar) throws IOException {
        if (v) {
            Log.d("ExifInterface", "getPngAttributes starting with: " + bVar);
        }
        bVar.c(ByteOrder.BIG_ENDIAN);
        byte[] bArr = E;
        bVar.d(bArr.length);
        int length = bArr.length + 0;
        while (true) {
            try {
                int readInt = bVar.readInt();
                int i = length + 4;
                byte[] bArr2 = new byte[4];
                if (bVar.read(bArr2) == 4) {
                    int i2 = i + 4;
                    if (i2 == 16 && !Arrays.equals(bArr2, G)) {
                        throw new IOException("Encountered invalid PNG file--IHDR chunk should appearas the first chunk");
                    }
                    if (Arrays.equals(bArr2, H)) {
                        return;
                    }
                    if (Arrays.equals(bArr2, F)) {
                        byte[] bArr3 = new byte[readInt];
                        if (bVar.read(bArr3) == readInt) {
                            int readInt2 = bVar.readInt();
                            CRC32 crc32 = new CRC32();
                            crc32.update(bArr2);
                            crc32.update(bArr3);
                            if (((int) crc32.getValue()) == readInt2) {
                                this.p = i2;
                                L(bArr3, 0);
                                X();
                                T(new b(bArr3));
                                return;
                            }
                            throw new IOException("Encountered invalid CRC value for PNG-EXIF chunk.\n recorded CRC value: " + readInt2 + ", calculated CRC value: " + crc32.getValue());
                        }
                        throw new IOException("Failed to read given length for given PNG chunk type: " + androidx.exifinterface.media.a.a(bArr2));
                    }
                    int i3 = readInt + 4;
                    bVar.d(i3);
                    length = i2 + i3;
                } else {
                    throw new IOException("Encountered invalid length while parsing PNG chunktype");
                }
            } catch (EOFException unused) {
                throw new IOException("Encountered corrupt PNG file.");
            }
        }
    }

    public final void l(b bVar) throws IOException {
        boolean z2 = v;
        if (z2) {
            Log.d("ExifInterface", "getRafAttributes starting with: " + bVar);
        }
        bVar.d(84);
        byte[] bArr = new byte[4];
        byte[] bArr2 = new byte[4];
        byte[] bArr3 = new byte[4];
        bVar.read(bArr);
        bVar.read(bArr2);
        bVar.read(bArr3);
        int i = ByteBuffer.wrap(bArr).getInt();
        int i2 = ByteBuffer.wrap(bArr2).getInt();
        int i3 = ByteBuffer.wrap(bArr3).getInt();
        byte[] bArr4 = new byte[i2];
        bVar.d(i - bVar.a());
        bVar.read(bArr4);
        h(new b(bArr4), i, 5);
        bVar.d(i3 - bVar.a());
        bVar.c(ByteOrder.BIG_ENDIAN);
        int readInt = bVar.readInt();
        if (z2) {
            Log.d("ExifInterface", "numberOfDirectoryEntry: " + readInt);
        }
        for (int i4 = 0; i4 < readInt; i4++) {
            int readUnsignedShort = bVar.readUnsignedShort();
            int readUnsignedShort2 = bVar.readUnsignedShort();
            if (readUnsignedShort == b0.f1294a) {
                short readShort = bVar.readShort();
                short readShort2 = bVar.readShort();
                d j = d.j(readShort, this.h);
                d j2 = d.j(readShort2, this.h);
                this.f[0].put(TAG_IMAGE_LENGTH, j);
                this.f[0].put(TAG_IMAGE_WIDTH, j2);
                if (v) {
                    Log.d("ExifInterface", "Updated to length: " + ((int) readShort) + ", width: " + ((int) readShort2));
                    return;
                }
                return;
            }
            bVar.d(readUnsignedShort2);
        }
    }

    public final void m(g gVar) throws IOException {
        d dVar;
        I(gVar);
        M(gVar, 0);
        W(gVar, 0);
        W(gVar, 5);
        W(gVar, 4);
        X();
        if (this.d != 8 || (dVar = this.f[1].get(TAG_MAKER_NOTE)) == null) {
            return;
        }
        g gVar2 = new g(dVar.d);
        gVar2.c(this.h);
        gVar2.d(6);
        M(gVar2, 9);
        d dVar2 = this.f[9].get(TAG_COLOR_SPACE);
        if (dVar2 != null) {
            this.f[1].put(TAG_COLOR_SPACE, dVar2);
        }
    }

    public final void n(g gVar) throws IOException {
        if (v) {
            Log.d("ExifInterface", "getRw2Attributes starting with: " + gVar);
        }
        m(gVar);
        d dVar = this.f[0].get(TAG_RW2_JPG_FROM_RAW);
        if (dVar != null) {
            h(new b(dVar.d), (int) dVar.c, 5);
        }
        d dVar2 = this.f[0].get(TAG_RW2_ISO);
        d dVar3 = this.f[1].get(TAG_PHOTOGRAPHIC_SENSITIVITY);
        if (dVar2 == null || dVar3 != null) {
            return;
        }
        this.f[1].put(TAG_PHOTOGRAPHIC_SENSITIVITY, dVar2);
    }

    public final void o(g gVar) throws IOException {
        byte[] bArr = n0;
        gVar.d(bArr.length);
        byte[] bArr2 = new byte[gVar.available()];
        gVar.readFully(bArr2);
        this.p = bArr.length;
        L(bArr2, 0);
    }

    public final void p(b bVar) throws IOException {
        if (v) {
            Log.d("ExifInterface", "getWebpAttributes starting with: " + bVar);
        }
        bVar.c(ByteOrder.LITTLE_ENDIAN);
        bVar.d(I.length);
        int readInt = bVar.readInt() + 8;
        byte[] bArr = J;
        bVar.d(bArr.length);
        int length = bArr.length + 8;
        while (true) {
            try {
                byte[] bArr2 = new byte[4];
                if (bVar.read(bArr2) == 4) {
                    int readInt2 = bVar.readInt();
                    int i = length + 4 + 4;
                    if (Arrays.equals(K, bArr2)) {
                        byte[] bArr3 = new byte[readInt2];
                        if (bVar.read(bArr3) == readInt2) {
                            this.p = i;
                            L(bArr3, 0);
                            T(new b(bArr3));
                            return;
                        }
                        throw new IOException("Failed to read given length for given PNG chunk type: " + androidx.exifinterface.media.a.a(bArr2));
                    }
                    if (readInt2 % 2 == 1) {
                        readInt2++;
                    }
                    length = i + readInt2;
                    if (length == readInt) {
                        return;
                    }
                    if (length <= readInt) {
                        bVar.d(readInt2);
                    } else {
                        throw new IOException("Encountered WebP file with invalid chunk size");
                    }
                } else {
                    throw new IOException("Encountered invalid length while parsing WebP chunktype");
                }
            } catch (EOFException unused) {
                throw new IOException("Encountered corrupt WebP file.");
            }
        }
    }

    public final void r(b bVar, HashMap hashMap) throws IOException {
        d dVar = (d) hashMap.get(TAG_JPEG_INTERCHANGE_FORMAT);
        d dVar2 = (d) hashMap.get(TAG_JPEG_INTERCHANGE_FORMAT_LENGTH);
        if (dVar == null || dVar2 == null) {
            return;
        }
        int m = dVar.m(this.h);
        int m2 = dVar2.m(this.h);
        if (this.d == 7) {
            m += this.q;
        }
        if (m > 0 && m2 > 0) {
            this.i = true;
            if (this.f1292a == null && this.c == null && this.b == null) {
                byte[] bArr = new byte[m2];
                bVar.skip(m);
                bVar.read(bArr);
                this.n = bArr;
            }
            this.l = m;
            this.m = m2;
        }
        if (v) {
            Log.d("ExifInterface", "Setting thumbnail attributes with offset: " + m + ", length: " + m2);
        }
    }

    public void resetOrientation() {
        setAttribute(TAG_ORIENTATION, Integer.toString(1));
    }

    public void rotate(int i) {
        if (i % 90 == 0) {
            int attributeInt = getAttributeInt(TAG_ORIENTATION, 1);
            List<Integer> list = w;
            if (list.contains(Integer.valueOf(attributeInt))) {
                int indexOf = (list.indexOf(Integer.valueOf(attributeInt)) + (i / 90)) % 4;
                r4 = list.get(indexOf + (indexOf < 0 ? 4 : 0)).intValue();
            } else {
                List<Integer> list2 = x;
                if (list2.contains(Integer.valueOf(attributeInt))) {
                    int indexOf2 = (list2.indexOf(Integer.valueOf(attributeInt)) + (i / 90)) % 4;
                    r4 = list2.get(indexOf2 + (indexOf2 < 0 ? 4 : 0)).intValue();
                }
            }
            setAttribute(TAG_ORIENTATION, Integer.toString(r4));
            return;
        }
        throw new IllegalArgumentException("degree should be a multiple of 90");
    }

    public final void s(b bVar, HashMap hashMap) throws IOException {
        d dVar = (d) hashMap.get(TAG_STRIP_OFFSETS);
        d dVar2 = (d) hashMap.get(TAG_STRIP_BYTE_COUNTS);
        if (dVar == null || dVar2 == null) {
            return;
        }
        long[] d2 = androidx.exifinterface.media.a.d(dVar.o(this.h));
        long[] d3 = androidx.exifinterface.media.a.d(dVar2.o(this.h));
        if (d2 != null && d2.length != 0) {
            if (d3 != null && d3.length != 0) {
                if (d2.length != d3.length) {
                    Log.w("ExifInterface", "stripOffsets and stripByteCounts should have same length.");
                    return;
                }
                long j = 0;
                for (long j2 : d3) {
                    j += j2;
                }
                int i = (int) j;
                byte[] bArr = new byte[i];
                this.k = true;
                this.j = true;
                this.i = true;
                int i2 = 0;
                int i3 = 0;
                for (int i4 = 0; i4 < d2.length; i4++) {
                    int i5 = (int) d2[i4];
                    int i6 = (int) d3[i4];
                    if (i4 < d2.length - 1 && i5 + i6 != d2[i4 + 1]) {
                        this.k = false;
                    }
                    int i7 = i5 - i2;
                    if (i7 < 0) {
                        Log.d("ExifInterface", "Invalid strip offset value");
                        return;
                    }
                    long j3 = i7;
                    if (bVar.skip(j3) != j3) {
                        Log.d("ExifInterface", "Failed to skip " + i7 + " bytes.");
                        return;
                    }
                    int i8 = i2 + i7;
                    byte[] bArr2 = new byte[i6];
                    if (bVar.read(bArr2) != i6) {
                        Log.d("ExifInterface", "Failed to read " + i6 + " bytes.");
                        return;
                    }
                    i2 = i8 + i6;
                    System.arraycopy(bArr2, 0, bArr, i3, i6);
                    i3 += i6;
                }
                this.n = bArr;
                if (this.k) {
                    this.l = (int) d2[0];
                    this.m = i;
                    return;
                }
                return;
            }
            Log.w("ExifInterface", "stripByteCounts should not be null or have zero length.");
            return;
        }
        Log.w("ExifInterface", "stripOffsets should not be null or have zero length.");
    }

    /* JADX WARN: Removed duplicated region for block: B:103:0x0167  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x0102 A[Catch: all -> 0x012e, Exception -> 0x0130, TryCatch #20 {Exception -> 0x0130, all -> 0x012e, blocks: (B:74:0x00fe, B:76:0x0102, B:78:0x0106, B:81:0x011d, B:79:0x0115), top: B:137:0x00fe }] */
    /* JADX WARN: Removed duplicated region for block: B:79:0x0115 A[Catch: all -> 0x012e, Exception -> 0x0130, TryCatch #20 {Exception -> 0x0130, all -> 0x012e, blocks: (B:74:0x00fe, B:76:0x0102, B:78:0x0106, B:81:0x011d, B:79:0x0115), top: B:137:0x00fe }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void saveAttributes() throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 404
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.exifinterface.media.ExifInterface.saveAttributes():void");
    }

    public void setAltitude(double d2) {
        String str = d2 >= 0.0d ? BleConst.GetDeviceTime : "1";
        setAttribute(TAG_GPS_ALTITUDE, new f(Math.abs(d2)).toString());
        setAttribute(TAG_GPS_ALTITUDE_REF, str);
    }

    public void setAttribute(@NonNull String str, @Nullable String str2) {
        e eVar;
        int i;
        int i2;
        Matcher matcher;
        String str3 = str;
        String str4 = str2;
        Objects.requireNonNull(str3, "tag shouldn't be null");
        if ((TAG_DATETIME.equals(str3) || TAG_DATETIME_ORIGINAL.equals(str3) || TAG_DATETIME_DIGITIZED.equals(str3)) && str4 != null) {
            boolean find = r0.matcher(str4).find();
            boolean find2 = s0.matcher(str4).find();
            if (str2.length() != 19 || (!find && !find2)) {
                Log.w("ExifInterface", "Invalid value for " + str3 + " : " + str4);
                return;
            } else if (find2) {
                str4 = str4.replaceAll("-", ":");
            }
        }
        if (TAG_ISO_SPEED_RATINGS.equals(str3)) {
            if (v) {
                Log.d("ExifInterface", "setAttribute: Replacing TAG_ISO_SPEED_RATINGS with TAG_PHOTOGRAPHIC_SENSITIVITY.");
            }
            str3 = TAG_PHOTOGRAPHIC_SENSITIVITY;
        }
        int i3 = 2;
        int i4 = 1;
        if (str4 != null && k0.contains(str3)) {
            if (str3.equals(TAG_GPS_TIMESTAMP)) {
                if (!q0.matcher(str4).find()) {
                    Log.w("ExifInterface", "Invalid value for " + str3 + " : " + str4);
                    return;
                }
                str4 = Integer.parseInt(matcher.group(1)) + "/1," + Integer.parseInt(matcher.group(2)) + "/1," + Integer.parseInt(matcher.group(3)) + "/1";
            } else {
                try {
                    str4 = new f(Double.parseDouble(str4)).toString();
                } catch (NumberFormatException unused) {
                    Log.w("ExifInterface", "Invalid value for " + str3 + " : " + str4);
                    return;
                }
            }
        }
        int i5 = 0;
        int i6 = 0;
        while (i6 < g0.length) {
            if ((i6 != 4 || this.i) && (eVar = j0[i6].get(str3)) != null) {
                if (str4 == null) {
                    this.f[i6].remove(str3);
                } else {
                    Pair<Integer, Integer> q = q(str4);
                    int i7 = -1;
                    if (eVar.c != ((Integer) q.first).intValue() && eVar.c != ((Integer) q.second).intValue()) {
                        int i8 = eVar.d;
                        if (i8 != -1 && (i8 == ((Integer) q.first).intValue() || eVar.d == ((Integer) q.second).intValue())) {
                            i = eVar.d;
                        } else {
                            int i9 = eVar.c;
                            if (i9 == i4 || i9 == 7 || i9 == i3) {
                                i = i9;
                            } else if (v) {
                                StringBuilder sb = new StringBuilder();
                                sb.append("Given tag (");
                                sb.append(str3);
                                sb.append(") value didn't match with one of expected formats: ");
                                String[] strArr = T;
                                sb.append(strArr[eVar.c]);
                                sb.append(eVar.d == -1 ? "" : ", " + strArr[eVar.d]);
                                sb.append(" (guess: ");
                                sb.append(strArr[((Integer) q.first).intValue()]);
                                sb.append(((Integer) q.second).intValue() != -1 ? ", " + strArr[((Integer) q.second).intValue()] : "");
                                sb.append(")");
                                Log.d("ExifInterface", sb.toString());
                            }
                        }
                    } else {
                        i = eVar.c;
                    }
                    switch (i) {
                        case 1:
                            i2 = i4;
                            this.f[i6].put(str3, d.a(str4));
                            break;
                        case 2:
                        case 7:
                            i2 = i4;
                            this.f[i6].put(str3, d.e(str4));
                            break;
                        case 3:
                            i2 = i4;
                            String[] split = str4.split(Constants.SEPARATOR_COMMA, -1);
                            int[] iArr = new int[split.length];
                            for (int i10 = 0; i10 < split.length; i10++) {
                                iArr[i10] = Integer.parseInt(split[i10]);
                            }
                            this.f[i6].put(str3, d.k(iArr, this.h));
                            break;
                        case 4:
                            i2 = i4;
                            String[] split2 = str4.split(Constants.SEPARATOR_COMMA, -1);
                            long[] jArr = new long[split2.length];
                            for (int i11 = 0; i11 < split2.length; i11++) {
                                jArr[i11] = Long.parseLong(split2[i11]);
                            }
                            this.f[i6].put(str3, d.g(jArr, this.h));
                            break;
                        case 5:
                            String[] split3 = str4.split(Constants.SEPARATOR_COMMA, -1);
                            f[] fVarArr = new f[split3.length];
                            int i12 = 0;
                            while (i12 < split3.length) {
                                String[] split4 = split3[i12].split(MqttTopic.TOPIC_LEVEL_SEPARATOR, i7);
                                fVarArr[i12] = new f((long) Double.parseDouble(split4[0]), (long) Double.parseDouble(split4[1]));
                                i12++;
                                i7 = -1;
                            }
                            i2 = 1;
                            this.f[i6].put(str3, d.i(fVarArr, this.h));
                            break;
                        case 6:
                        case 8:
                        case 11:
                        default:
                            i2 = i4;
                            if (v) {
                                Log.d("ExifInterface", "Data format isn't one of expected formats: " + i);
                                break;
                            } else {
                                break;
                            }
                        case 9:
                            String[] split5 = str4.split(Constants.SEPARATOR_COMMA, -1);
                            int[] iArr2 = new int[split5.length];
                            for (int i13 = 0; i13 < split5.length; i13++) {
                                iArr2[i13] = Integer.parseInt(split5[i13]);
                            }
                            this.f[i6].put(str3, d.c(iArr2, this.h));
                            i2 = 1;
                            break;
                        case 10:
                            String[] split6 = str4.split(Constants.SEPARATOR_COMMA, -1);
                            f[] fVarArr2 = new f[split6.length];
                            int i14 = i5;
                            while (i14 < split6.length) {
                                String[] split7 = split6[i14].split(MqttTopic.TOPIC_LEVEL_SEPARATOR, -1);
                                fVarArr2[i14] = new f((long) Double.parseDouble(split7[i5]), (long) Double.parseDouble(split7[i4]));
                                i14++;
                                split6 = split6;
                                i5 = 0;
                                i4 = 1;
                            }
                            this.f[i6].put(str3, d.d(fVarArr2, this.h));
                            i2 = 1;
                            break;
                        case 12:
                            String[] split8 = str4.split(Constants.SEPARATOR_COMMA, -1);
                            double[] dArr = new double[split8.length];
                            for (int i15 = i5; i15 < split8.length; i15++) {
                                dArr[i15] = Double.parseDouble(split8[i15]);
                            }
                            this.f[i6].put(str3, d.b(dArr, this.h));
                            break;
                    }
                    i6++;
                    i4 = i2;
                    i3 = 2;
                    i5 = 0;
                }
            }
            i2 = i4;
            i6++;
            i4 = i2;
            i3 = 2;
            i5 = 0;
        }
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public void setDateTime(@NonNull Long l) {
        Objects.requireNonNull(l, "Timestamp should not be null.");
        if (l.longValue() >= 0) {
            String l2 = Long.toString(l.longValue() % 1000);
            for (int length = l2.length(); length < 3; length++) {
                l2 = BleConst.GetDeviceTime + l2;
            }
            setAttribute(TAG_DATETIME, R.format(new Date(l.longValue())));
            setAttribute(TAG_SUBSEC_TIME, l2);
            return;
        }
        throw new IllegalArgumentException("Timestamp should a positive value.");
    }

    public void setGpsInfo(Location location) {
        if (location == null) {
            return;
        }
        setAttribute(TAG_GPS_PROCESSING_METHOD, location.getProvider());
        setLatLong(location.getLatitude(), location.getLongitude());
        setAltitude(location.getAltitude());
        setAttribute(TAG_GPS_SPEED_REF, "K");
        setAttribute(TAG_GPS_SPEED, new f((location.getSpeed() * ((float) TimeUnit.HOURS.toSeconds(1L))) / 1000.0f).toString());
        String[] split = R.format(new Date(location.getTime())).split("\\s+", -1);
        setAttribute(TAG_GPS_DATESTAMP, split[0]);
        setAttribute(TAG_GPS_TIMESTAMP, split[1]);
    }

    public void setLatLong(double d2, double d3) {
        if (d2 >= -90.0d && d2 <= 90.0d && !Double.isNaN(d2)) {
            if (d3 >= -180.0d && d3 <= 180.0d && !Double.isNaN(d3)) {
                setAttribute(TAG_GPS_LATITUDE_REF, d2 >= 0.0d ? "N" : LATITUDE_SOUTH);
                setAttribute(TAG_GPS_LATITUDE, b(Math.abs(d2)));
                setAttribute(TAG_GPS_LONGITUDE_REF, d3 >= 0.0d ? LONGITUDE_EAST : LONGITUDE_WEST);
                setAttribute(TAG_GPS_LONGITUDE, b(Math.abs(d3)));
                return;
            }
            throw new IllegalArgumentException("Longitude value " + d3 + " is not valid.");
        }
        throw new IllegalArgumentException("Latitude value " + d2 + " is not valid.");
    }

    public final void t(String str) throws IOException {
        Objects.requireNonNull(str, "filename cannot be null");
        FileInputStream fileInputStream = null;
        this.c = null;
        this.f1292a = str;
        try {
            FileInputStream fileInputStream2 = new FileInputStream(str);
            try {
                if (B(fileInputStream2.getFD())) {
                    this.b = fileInputStream2.getFD();
                } else {
                    this.b = null;
                }
                G(fileInputStream2);
                androidx.exifinterface.media.a.c(fileInputStream2);
            } catch (Throwable th) {
                th = th;
                fileInputStream = fileInputStream2;
                androidx.exifinterface.media.a.c(fileInputStream);
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public final boolean v(byte[] bArr) throws IOException {
        b bVar;
        long readInt;
        byte[] bArr2;
        b bVar2 = null;
        try {
            try {
                bVar = new b(bArr);
            } catch (Exception e2) {
                e = e2;
            }
        } catch (Throwable th) {
            th = th;
        }
        try {
            readInt = bVar.readInt();
            bArr2 = new byte[4];
            bVar.read(bArr2);
        } catch (Exception e3) {
            e = e3;
            bVar2 = bVar;
            if (v) {
                Log.d("ExifInterface", "Exception parsing HEIF file type box.", e);
            }
            if (bVar2 != null) {
                bVar2.close();
            }
            return false;
        } catch (Throwable th2) {
            th = th2;
            bVar2 = bVar;
            if (bVar2 != null) {
                bVar2.close();
            }
            throw th;
        }
        if (!Arrays.equals(bArr2, z)) {
            bVar.close();
            return false;
        }
        long j = 16;
        if (readInt == 1) {
            readInt = bVar.readLong();
            if (readInt < 16) {
                bVar.close();
                return false;
            }
        } else {
            j = 8;
        }
        if (readInt > bArr.length) {
            readInt = bArr.length;
        }
        long j2 = readInt - j;
        if (j2 < 8) {
            bVar.close();
            return false;
        }
        byte[] bArr3 = new byte[4];
        boolean z2 = false;
        boolean z3 = false;
        for (long j3 = 0; j3 < j2 / 4; j3++) {
            if (bVar.read(bArr3) != 4) {
                bVar.close();
                return false;
            }
            if (j3 != 1) {
                if (Arrays.equals(bArr3, A)) {
                    z2 = true;
                } else if (Arrays.equals(bArr3, B)) {
                    z3 = true;
                }
                if (z2 && z3) {
                    bVar.close();
                    return true;
                }
            }
        }
        bVar.close();
        return false;
    }

    public final boolean x(byte[] bArr) throws IOException {
        boolean z2 = false;
        b bVar = null;
        try {
            b bVar2 = new b(bArr);
            try {
                ByteOrder K2 = K(bVar2);
                this.h = K2;
                bVar2.c(K2);
                short readShort = bVar2.readShort();
                z2 = (readShort == 20306 || readShort == 21330) ? true : true;
                bVar2.close();
                return z2;
            } catch (Exception unused) {
                bVar = bVar2;
                if (bVar != null) {
                    bVar.close();
                }
                return false;
            } catch (Throwable th) {
                th = th;
                bVar = bVar2;
                if (bVar != null) {
                    bVar.close();
                }
                throw th;
            }
        } catch (Exception unused2) {
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public final boolean y(byte[] bArr) throws IOException {
        int i = 0;
        while (true) {
            byte[] bArr2 = E;
            if (i >= bArr2.length) {
                return true;
            }
            if (bArr[i] != bArr2[i]) {
                return false;
            }
            i++;
        }
    }

    public final boolean z(byte[] bArr) throws IOException {
        byte[] bytes = "FUJIFILMCCD-RAW".getBytes(Charset.defaultCharset());
        for (int i = 0; i < bytes.length; i++) {
            if (bArr[i] != bytes[i]) {
                return false;
            }
        }
        return true;
    }

    /* loaded from: classes.dex */
    public static class g extends b {
        public g(byte[] bArr) throws IOException {
            super(bArr);
            this.h.mark(Integer.MAX_VALUE);
        }

        public void e(long j) throws IOException {
            int i = this.j;
            if (i > j) {
                this.j = 0;
                this.h.reset();
            } else {
                j -= i;
            }
            d((int) j);
        }

        public g(InputStream inputStream) throws IOException {
            super(inputStream);
            if (inputStream.markSupported()) {
                this.h.mark(Integer.MAX_VALUE);
                return;
            }
            throw new IllegalArgumentException("Cannot create SeekableByteOrderedDataInputStream with stream that does not support mark/reset");
        }
    }

    @Nullable
    public double[] getLatLong() {
        String attribute = getAttribute(TAG_GPS_LATITUDE);
        String attribute2 = getAttribute(TAG_GPS_LATITUDE_REF);
        String attribute3 = getAttribute(TAG_GPS_LONGITUDE);
        String attribute4 = getAttribute(TAG_GPS_LONGITUDE_REF);
        if (attribute == null || attribute2 == null || attribute3 == null || attribute4 == null) {
            return null;
        }
        try {
            return new double[]{c(attribute, attribute2), c(attribute3, attribute4)};
        } catch (IllegalArgumentException unused) {
            Log.w("ExifInterface", "Latitude/longitude values are not parsable. " + String.format("latValue=%s, latRef=%s, lngValue=%s, lngRef=%s", attribute, attribute2, attribute3, attribute4));
            return null;
        }
    }

    /* loaded from: classes.dex */
    public static class e {

        /* renamed from: a  reason: collision with root package name */
        public final int f1294a;
        public final String b;
        public final int c;
        public final int d;

        public e(String str, int i, int i2) {
            this.b = str;
            this.f1294a = i;
            this.c = i2;
            this.d = -1;
        }

        public boolean a(int i) {
            int i2;
            int i3 = this.c;
            if (i3 == 7 || i == 7 || i3 == i || (i2 = this.d) == i) {
                return true;
            }
            if ((i3 == 4 || i2 == 4) && i == 3) {
                return true;
            }
            if ((i3 == 9 || i2 == 9) && i == 8) {
                return true;
            }
            return (i3 == 12 || i2 == 12) && i == 11;
        }

        public e(String str, int i, int i2, int i3) {
            this.b = str;
            this.f1294a = i;
            this.c = i2;
            this.d = i3;
        }
    }

    public ExifInterface(@NonNull String str) throws IOException {
        e[][] eVarArr = g0;
        this.f = new HashMap[eVarArr.length];
        this.g = new HashSet(eVarArr.length);
        this.h = ByteOrder.BIG_ENDIAN;
        Objects.requireNonNull(str, "filename cannot be null");
        t(str);
    }

    public ExifInterface(@NonNull FileDescriptor fileDescriptor) throws IOException {
        FileInputStream fileInputStream;
        Throwable th;
        e[][] eVarArr = g0;
        this.f = new HashMap[eVarArr.length];
        this.g = new HashSet(eVarArr.length);
        this.h = ByteOrder.BIG_ENDIAN;
        Objects.requireNonNull(fileDescriptor, "fileDescriptor cannot be null");
        this.c = null;
        this.f1292a = null;
        boolean z2 = false;
        if (Build.VERSION.SDK_INT >= 21 && B(fileDescriptor)) {
            this.b = fileDescriptor;
            try {
                fileDescriptor = a.C0145a.b(fileDescriptor);
                z2 = true;
            } catch (Exception e2) {
                throw new IOException("Failed to duplicate file descriptor", e2);
            }
        } else {
            this.b = null;
        }
        try {
            fileInputStream = new FileInputStream(fileDescriptor);
            try {
                G(fileInputStream);
                androidx.exifinterface.media.a.c(fileInputStream);
                if (z2) {
                    androidx.exifinterface.media.a.b(fileDescriptor);
                }
            } catch (Throwable th2) {
                th = th2;
                androidx.exifinterface.media.a.c(fileInputStream);
                if (z2) {
                    androidx.exifinterface.media.a.b(fileDescriptor);
                }
                throw th;
            }
        } catch (Throwable th3) {
            fileInputStream = null;
            th = th3;
        }
    }

    public ExifInterface(@NonNull InputStream inputStream) throws IOException {
        this(inputStream, 0);
    }

    public ExifInterface(@NonNull InputStream inputStream, int i) throws IOException {
        e[][] eVarArr = g0;
        this.f = new HashMap[eVarArr.length];
        this.g = new HashSet(eVarArr.length);
        this.h = ByteOrder.BIG_ENDIAN;
        Objects.requireNonNull(inputStream, "inputStream cannot be null");
        this.f1292a = null;
        if (i == 1) {
            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream, n0.length);
            if (!u(bufferedInputStream)) {
                Log.w("ExifInterface", "Given data does not follow the structure of an Exif-only data.");
                return;
            }
            this.e = true;
            this.c = null;
            this.b = null;
            inputStream = bufferedInputStream;
        } else if (inputStream instanceof AssetManager.AssetInputStream) {
            this.c = (AssetManager.AssetInputStream) inputStream;
            this.b = null;
        } else {
            if (inputStream instanceof FileInputStream) {
                FileInputStream fileInputStream = (FileInputStream) inputStream;
                if (B(fileInputStream.getFD())) {
                    this.c = null;
                    this.b = fileInputStream.getFD();
                }
            }
            this.c = null;
            this.b = null;
        }
        G(inputStream);
    }
}
