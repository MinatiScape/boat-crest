package kotlin.text;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import kotlin.jvm.JvmField;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes12.dex */
public final class d {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public static final d f14126a = new d();
    @JvmField
    @NotNull
    public static final Regex b;

    static {
        String str = "[eE][+-]?(\\p{Digit}+)";
        b = new Regex("[\\x00-\\x20]*[+-]?(NaN|Infinity|((" + (HexStringBuilder.COMMENT_BEGIN_CHAR + "(\\p{Digit}+)(\\.)?((\\p{Digit}+)?)(" + str + ")?)|(\\.((\\p{Digit}+))(" + str + ")?)|((" + ("(0[xX](\\p{XDigit}+)(\\.)?)|(0[xX](\\p{XDigit}+)?(\\.)(\\p{XDigit}+)" + HexStringBuilder.COMMENT_END_CHAR) + ")[pP][+-]?(\\p{Digit}+)" + HexStringBuilder.COMMENT_END_CHAR) + ")[fFdD]?))[\\x00-\\x20]*");
    }
}
