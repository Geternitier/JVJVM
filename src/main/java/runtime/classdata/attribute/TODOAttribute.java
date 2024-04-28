package runtime.classdata.attribute;

import lombok.Getter;
import lombok.SneakyThrows;

import java.io.DataInput;

public class TODOAttribute extends Attribute{
    @Getter
    private final byte[] data;

    @SneakyThrows
    public TODOAttribute(String name, int length, DataInput dataInput){
        this.name = name;
        attributeLength = length;
        data = new byte[attributeLength];
        dataInput.readFully(data);
    }
}
