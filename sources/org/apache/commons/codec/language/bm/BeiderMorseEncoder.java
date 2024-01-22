package org.apache.commons.codec.language.bm;

import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.StringEncoder;
/* loaded from: classes12.dex */
public class BeiderMorseEncoder implements StringEncoder {

    /* renamed from: a  reason: collision with root package name */
    public PhoneticEngine f14359a = new PhoneticEngine(NameType.GENERIC, RuleType.APPROX, true);

    @Override // org.apache.commons.codec.Encoder
    public Object encode(Object obj) throws EncoderException {
        if (obj instanceof String) {
            return encode((String) obj);
        }
        throw new EncoderException("BeiderMorseEncoder encode parameter is not of type String");
    }

    public NameType getNameType() {
        return this.f14359a.getNameType();
    }

    public RuleType getRuleType() {
        return this.f14359a.getRuleType();
    }

    public boolean isConcat() {
        return this.f14359a.isConcat();
    }

    public void setConcat(boolean z) {
        this.f14359a = new PhoneticEngine(this.f14359a.getNameType(), this.f14359a.getRuleType(), z, this.f14359a.getMaxPhonemes());
    }

    public void setMaxPhonemes(int i) {
        this.f14359a = new PhoneticEngine(this.f14359a.getNameType(), this.f14359a.getRuleType(), this.f14359a.isConcat(), i);
    }

    public void setNameType(NameType nameType) {
        this.f14359a = new PhoneticEngine(nameType, this.f14359a.getRuleType(), this.f14359a.isConcat(), this.f14359a.getMaxPhonemes());
    }

    public void setRuleType(RuleType ruleType) {
        this.f14359a = new PhoneticEngine(this.f14359a.getNameType(), ruleType, this.f14359a.isConcat(), this.f14359a.getMaxPhonemes());
    }

    @Override // org.apache.commons.codec.StringEncoder
    public String encode(String str) throws EncoderException {
        if (str == null) {
            return null;
        }
        return this.f14359a.encode(str);
    }
}
