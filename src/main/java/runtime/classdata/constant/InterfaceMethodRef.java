package runtime.classdata.constant;

import runtime.JClass;

import java.io.DataInput;

public class InterfaceMethodRef extends RefConstant{
    public InterfaceMethodRef(DataInput dataInput, JClass jClass) {
        super(dataInput, jClass);
    }

    @Override
    public String toString(){
        return String.format("InterfaceMethodRef: \"%s:%s\"", getName(), getType());
    }
}
