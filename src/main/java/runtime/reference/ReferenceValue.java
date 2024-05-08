package runtime.reference;

import runtime.Variable;

public class ReferenceValue {
    private Variable high;
    private Variable low;

    private ReferenceValue(Variable high, Variable low){
        this.high = high;
        this.low = low;
    }

    public static ReferenceValue of(Variable high, Variable low){
        return new ReferenceValue(high, low);
    }

    public static ReferenceValue of(Variable high){
        return new ReferenceValue(high, null);
    }

    public static ReferenceValue of(int val) {
        return new ReferenceValue(new Variable(val), null);
    }

    public static ReferenceValue of(float val) {
        return of(Float.floatToIntBits(val));
    }

    public static ReferenceValue of(long val) {
        int high = (int) (val >> 32); //高32位
        int low = (int) (val & 0x000000ffffffffL); //低32位
        return new ReferenceValue(new Variable(high), new Variable(low));
    }

    public static ReferenceValue of(double val) {
        return of(Double.doubleToLongBits(val));
    }

    public static ReferenceValue of(Reference val){
        return new ReferenceValue(new Variable(val), null);
    }

    public void setRef(Reference val) {
        high = new Variable(val);
    }

    public void setInt(int val) {
        high = new Variable(val);
    }

    public void setFloat(float val) {
        setInt(Float.floatToIntBits(val));
    }

    public void setLong(long val) {
        int highV = (int) (val >> 32);              //高32位
        int lowV = (int) (val & 0x000000ffffffffL); //低32位
        high = new Variable(highV);
        low = new Variable(lowV);
    }

    public void setDouble(double val) {
        setLong(Double.doubleToLongBits(val));
    }

    public void set(ReferenceValue referenceValue) {
        this.high = referenceValue.high;
        this.low = referenceValue.low;
    }

    public Reference getRef() {
        return high.getReference();
    }

    public int getInt() {
        return high.getInteger();
    }

    public float getFloat() {
        return Float.intBitsToFloat(getInt());
    }

    public long getLong() {
        final int high = this.high.getInteger();
        final int low = this.low.getInteger();
        long l1 = (high & 0x000000ffffffffL) << 32;
        long l2 = low & 0x00000000ffffffffL;
        return l1 | l2;
    }

    public double getDouble() {
        return Double.longBitsToDouble(getLong());
    }
}
