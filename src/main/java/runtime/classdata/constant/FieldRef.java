package runtime.classdata.constant;

import runtime.JClass;

import java.io.DataInput;

public class FieldRef extends RefConstant{
    public FieldRef(DataInput dataInput, JClass jClass) {
        super(dataInput, jClass);
    }
}
