package runtime.classdata.constant;

import runtime.JClass;

import java.io.DataInput;

public class InterfaceMethodRef extends RefConstant{
    public InterfaceMethodRef(DataInput dataInput, JClass jClass) {
        super(dataInput, jClass);
    }
}
