package runtime.classdata.constant;

import lombok.SneakyThrows;
import org.apache.commons.text.StringEscapeUtils;
import runtime.JClass;

import java.io.DataInput;

public class StringConstant implements Constant{
    private final JClass jClass;
    private final int stringIndex;
    private UTF8Constant utf8;
    private String string;

    @SneakyThrows
    public StringConstant(DataInput dataInput, JClass jClass){
        this.jClass = jClass;
        stringIndex = dataInput.readUnsignedShort();
    }

    public UTF8Constant utf8(){
        if(utf8 == null) utf8 = (UTF8Constant) jClass.getConstantPool().getConstant(stringIndex);
        return utf8;
    }

    public String string(){
        if(string == null) string = utf8().getValue();
        return string;
    }

    @Override
    public String toString(){
        return String.format("String: \"%s\"", StringEscapeUtils.escapeJava(string()));
    }
}
