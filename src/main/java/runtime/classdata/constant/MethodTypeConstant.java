package runtime.classdata.constant;

import lombok.SneakyThrows;
import org.apache.commons.text.StringEscapeUtils;
import runtime.JClass;

import java.io.DataInput;

public class MethodTypeConstant implements Constant{
    private final int stringIndex;
    private final JClass jClass;
    private String methodType;

    @SneakyThrows
    public MethodTypeConstant(DataInput dataInput, JClass jClass){
        stringIndex = dataInput.readUnsignedShort();
        this.jClass = jClass;
    }

    public String getMethodType(){
        if(methodType == null) {
            methodType = ((UTF8Constant) jClass.getConstantPool().getConstant(stringIndex)).getValue();
        }
        return methodType;
    }

    @Override
    public String toString(){
        return String.format("MethodType: \"%s\"", StringEscapeUtils.escapeJava(getMethodType()));
    }

}
