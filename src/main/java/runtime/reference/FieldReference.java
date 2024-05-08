package runtime.reference;

import lombok.Getter;
import runtime.JClass;
import runtime.classdata.Field;

public class FieldReference implements Reference{
    @Getter
    private final Field field;
    @Getter
    private final ReferenceValue value;

    public FieldReference(Field field){
        this.field = field;
        this.value = ReferenceValue.of(new NULL());
    }

    public JClass getJClass(){
        try {
            return field.getJClass().getClassLoader().loadClass(field.getDescriptor());
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError(e.toString());
        }
    }

    @Override
    public String toString(){
        return String.format("Reference to Field with name: %s & type: %s", field.getName(), field.getDescriptor());
    }
}
