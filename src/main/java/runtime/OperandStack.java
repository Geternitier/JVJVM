package runtime;

import lombok.Getter;
import runtime.reference.ArrayReference;
import runtime.reference.Reference;
import classdefs.FieldDescriptor;

import java.util.ArrayList;
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

    public void push(Variable[] variables){
        for (Variable variable : variables) {
            push(variable);
        }
    }

    public Variable top(){
        return stack[top - 1];
    }

    public Variable pop(){
        Variable variable = stack[--top];
        stack[top] = null;
        return variable;
    }

    public Variable[] pop(int n){
        Variable[] res = new Variable[n];
        for(int i = 0;i < n;i++){
            res[n - 1 - i] = pop();
        }
        return res;
    }

    public LocalVariables popArgs(String descriptor){
        int index = 0;
        StringBuilder des = new StringBuilder();
        while (index < descriptor.length()){
            des.append(descriptor.charAt(index));
            if(descriptor.charAt(index) == 'L'){
                index += descriptor.indexOf(';', index);
            } else index++;
        }
        descriptor = des.toString();
        int i = descriptor.indexOf(')') - 1;
        ArrayList<Variable> variableArrayList = new ArrayList<>();
        ArrayList<Class<?>> typeList = new ArrayList<>();
        for(;i > 0;i--){
            switch (descriptor.charAt(i)){
                case FieldDescriptor.DESC_int,
                        FieldDescriptor.DESC_float,
                        FieldDescriptor.DESC_byte,
                        FieldDescriptor.DESC_char,
                        FieldDescriptor.DESC_short,
                        FieldDescriptor.DESC_boolean -> {
                    if(descriptor.charAt(i) == '['){
                        int dimensions = 1;
                        while (descriptor.charAt(--i) == '['){dimensions++;}
                        variableArrayList.add(0, new Variable(new ArrayReference(String.valueOf(descriptor.charAt(i)), dimensions)));
                        typeList.add(0, Reference.class);
                    } else {
                        variableArrayList.add(0, pop());
                        switch (descriptor.charAt(i)){
                            case FieldDescriptor.DESC_int,
                                    FieldDescriptor.DESC_byte,
                                    FieldDescriptor.DESC_short,
                                    FieldDescriptor.DESC_char,
                                    FieldDescriptor.DESC_boolean -> typeList.add(0, Integer.class);
                            case FieldDescriptor.DESC_float -> typeList.add(0, Float.class);
                        }
                    }
                }
                case FieldDescriptor.DESC_long, FieldDescriptor.DESC_double -> {
                    if(descriptor.charAt(i) == '['){
                        int dimensions = 1;
                        while (descriptor.charAt(--i) == '['){dimensions++;}
                        variableArrayList.add(0, new Variable(new ArrayReference(String.valueOf(descriptor.charAt(i)), dimensions)));
                        typeList.add(0, Reference.class);
                    } else {
                        variableArrayList.add(0, pop());
                        variableArrayList.add(0, pop());
                        typeList.add(0, descriptor.charAt(i)==FieldDescriptor.DESC_long?Long.class:Double.class);
                    }
                }
                case FieldDescriptor.DESC_reference -> {
                    variableArrayList.add(0, pop());
                    typeList.add(0, Reference.class);
                }
            }
        }
        LocalVariables localVariables = new LocalVariables(variableArrayList.size());
        int pos = 0;
        for(int j = 0;j < typeList.size();j++){
            localVariables.set(pos, variableArrayList.get(pos), typeList.get(j));
            pos++;
            if (typeList.get(j) == Long.class || typeList.get(j) == Double.class){
                localVariables.set(pos, variableArrayList.get(pos), null);
                pos++;
            }
        }
        return localVariables;
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
