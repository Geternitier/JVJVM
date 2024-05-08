package runtime.classdata.constant;

import runtime.JClass;
import runtime.classdata.Method;

import java.io.DataInput;

public class MethodRef extends RefConstant{
    private Method method;

    public MethodRef(DataInput dataInput, JClass jClass) {
        super(dataInput, jClass);
    }

    public Method getMethod() throws ClassNotFoundException {
        if(method == null){
            method = getClassConstant().getValue().getMethod(getName(), getType());
        }
        return method;
    }
}
