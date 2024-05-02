package classdefs;

import static classdefs.FieldDescriptor.*;

public class MethodDescriptor {
    public static int countArgc(String descriptor) {
        assert descriptor.startsWith("(");

        int count = 0;
        for(int i = 1; i < descriptor.length();){
            if(descriptor.charAt(i) == ')'){
                break;
            }
            count += FieldDescriptor.getSize(descriptor.charAt(i));

            while (descriptor.charAt(i) == DESC_array){
                ++i;
            }
            if(descriptor.charAt(i) == DESC_reference){
                i += descriptor.indexOf(';', i) + 1;
            } else{
                ++i;
            }
        }
        return count;
    }

}
