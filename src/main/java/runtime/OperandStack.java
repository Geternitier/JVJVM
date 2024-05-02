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
        variables.setInt(top++, value);
    }

    public int popInt() {
        return variables.getInt(--top);
    }

    public void pushFloat(float value) {
        variables.setFloat(top++,value);
    }

    public float popFloat() {
        return variables.getFloat(--top);
    }

    public void pushLong(long value) {
        variables.setLong(top,value);
        top += 2;
    }

    public long popLong() {
        top -= 2;
        return variables.getLong(top);
    }

    public void pushDouble(double value) {
        variables.double_(top++,value);
        top += 2;
    }

    public double popDouble() {
        top -= 2;
        return variables.getDouble(top);
    }

    public void pushByte(byte value) {
        variables.setByte(top++,value);
    }

    public byte popByte() {
        return variables.getByte(--top);
    }

    public void pushChar(char value) {
        variables.setChar(top++,value);
    }

    public char popChar() {
        return variables.getChar(--top);
    }

    public void pushShort(short value) {
        variables.setShort(top++,value);
    }

    public short popShort() {
        return variables.getShort(--top);
    }

    public void pushVariables(LocalVariables variables) {
        variables.copyTo(0,variables.size(),this.variables,top);
        top += variables.size();
    }

    public LocalVariables popVariables(int count) {
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
