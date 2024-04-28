package runtime.classdata.constant;

import runtime.JClass;

import java.io.DataInput;

public class MethodRef extends RefConstant{
    public MethodRef(DataInput dataInput, JClass jClass) {
        super(dataInput, jClass);
    }


}
