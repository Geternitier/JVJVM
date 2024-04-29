package runtime;

import lombok.Getter;

public class OperandStack {
    @Getter
    private final LocalVariables variables;

    @Getter
    private int top;

    public OperandStack(int stackSize) {
        variables = new LocalVariables(stackSize);
        top = 0;
    }

    public void pushInt(int value) {
        variables.int_(top++, value);
    }

    public int popInt() {
        return variables.int_(--top);
    }

    public void pushFloat(float value) {
        variables.float_(top++,value);
    }

    public float popFloat() {
        return variables.float_(--top);
    }

    public void pushLong(long value) {
        variables.long_(top,value);
        top += 2;
    }

    public long popLong() {
        top -= 2;
        return variables.long_(top);
    }

    public void pushDouble(double value) {
        variables.double_(top++,value);
        top += 2;
    }

    public double popDouble() {
        top -= 2;
        return variables.double_(top);
    }

    public void pushByte(byte value) {
        variables.byte_(top++,value);
    }

    public byte popByte() {
        return variables.byte_(--top);
    }

    public void pushChar(char value) {
        variables.char_(top++,value);
    }

    public char popChar() {
        return variables.char_(--top);
    }

    public void pushShort(short value) {
        variables.short_(top++,value);
    }

    public short popShort() {
        return variables.short_(--top);
    }

    public void pushSlots(LocalVariables slots) {
        slots.copyTo(0,slots.size(),this.variables,top);
        top += slots.size();
    }

    public LocalVariables popSlots(int count) {
        assert top >= count;

        var ret = new LocalVariables(count);
        top -= count;
        variables.copyTo(top,count,ret,0);

        return ret;
    }

    public void clear() {
        top = 0;
    }

    @Override
    public String toString(){
        return "OperandStack{" + "slots=" + variables + ", top=" + top + '}';
    }
}
