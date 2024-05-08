package runtime.reference;

import lombok.Getter;
import runtime.JClass;
import runtime.classdata.Field;
import runtime.classdata.constant.ClassConstant;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ClassReference implements Reference{
    private final ClassConstant constant;
    @Getter
    private final JClass value;

    private final Map<String, FieldReference> fieldTable = new HashMap<>();

    public ClassReference(ClassConstant constant){
        this.constant = constant;
        try {
            value = constant.getValue();
            for(Field field: value.getFields()){
                fieldTable.put(field.getName(), new FieldReference(field));
            }
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError(e.toString());
        }
    }

    public void setField(String name, FieldReference reference){
        if(fieldTable.containsKey(name) &&
                Objects.equals(fieldTable.get(name).getField().getDescriptor(), reference.getField().getDescriptor())){
            fieldTable.put(name, reference);
        }
    }

    public FieldReference getField(String name){
        return fieldTable.get(name);
    }

    @Override
    public String toString(){
        return "Reference to Class: "+constant.getName();
    }
}
