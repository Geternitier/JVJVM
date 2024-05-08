package runtime.classdata.constant;

import runtime.JClass;
import runtime.classdata.Field;

import java.io.DataInput;

public class FieldRef extends RefConstant{
    private Field field;

    public FieldRef(DataInput dataInput, JClass jClass) {
        super(dataInput, jClass);
    }

    public Field getField() throws ClassNotFoundException {
        if (field == null){
            field = getClassConstant().getValue().getField(getName(), getType());
        }
        return field;
    }

    @Override
    public String toString(){
        return String.format("FieldRef: \"%s:%s\"", getName(), getType());
    }
}
