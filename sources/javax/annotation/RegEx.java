package javax.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import javax.annotation.meta.TypeQualifierNickname;
import javax.annotation.meta.TypeQualifierValidator;
import javax.annotation.meta.When;
@Syntax("RegEx")
@TypeQualifierNickname
@Documented
@Retention(RetentionPolicy.RUNTIME)
/* loaded from: classes12.dex */
public @interface RegEx {

    /* loaded from: classes12.dex */
    public static class Checker implements TypeQualifierValidator<RegEx> {
        @Override // javax.annotation.meta.TypeQualifierValidator
        public When forConstantValue(RegEx regEx, Object obj) {
            if (!(obj instanceof String)) {
                return When.NEVER;
            }
            try {
                Pattern.compile((String) obj);
                return When.ALWAYS;
            } catch (PatternSyntaxException unused) {
                return When.NEVER;
            }
        }
    }

    When when() default When.ALWAYS;
}
