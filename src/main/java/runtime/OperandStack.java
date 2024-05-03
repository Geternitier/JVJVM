package runtime;

import lombok.Getter;
import runtime.reference.Reference;

import java.util.Arrays;

public class OperandStack {
    @Getter
    private final Variable[] stack;

    @Getter
    private int top;

    public OperandStack(int stackSize) {
        stack = new Variable[stackSize];
        top = 0;
    }

    public void push(Variable variable){
        stack[top++] = variable;
    }

    public Variable top(){
        return stack[top - 1];
    }

    public Variable pop(){
        Variable variable = stack[--top];
        stack[top] = null;
        return variable;
    }

    public void pushInt(int value) {
        push(new Variable(value));
    }

    public void pushLong(long value) {
        int high = (int) (value >> 32);                 //高32位
        int low = (int) (value & 0x000000ffffffffL);    //低32位
        pushInt(high);
        pushInt(low);
    }

    public void pushFloat(float value) {
        pushInt(Float.floatToIntBits(value));
    }

    public void pushDouble(double value) {
        long temp = Double.doubleToLongBits(value);

        int high = (int) (temp >> 32);                   //高32位
        int low = (int) (temp & 0x000000ffffffffL);      //低32位
        pushInt(high);
        pushInt(low);
    }

    public void pushReference(Reference reference){
        push(new Variable(reference));
    }

    public int popInt() {
        return pop().getInteger();
    }

    public long popLong() {
        int low = popInt();
        int high = popInt();
        long l1 = (high & 0x000000ffffffffL) << 32;
        long l2 = low & 0x00000000ffffffffL;
        return l1 | l2;
    }

    public float popFloat() {
        return Float.intBitsToFloat(popInt());
    }

    public double popDouble() {
        return Double.longBitsToDouble(popLong());
    }

    public Reference popReference() {
        return pop().getReference();
    }

    @Override
    public String toString(){
        return "OperandStack{" + "locals=" + Arrays.toString(stack) + ", top=" + top + '}';
    }
}
