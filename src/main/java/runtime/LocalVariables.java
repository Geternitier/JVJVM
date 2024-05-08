package runtime;


import runtime.reference.Reference;

public class LocalVariables {
    private final Variable[] variables;
    private final Class<?>[] types;

    public LocalVariables(int size){
        variables = new Variable[size];
        types = new Class[size];
    }

    public void set(int index, Variable variable){
        if(variable == null) {
            variables[index] = null;
        } else variables[index] = new Variable(variable);
    }

    public void set(int index, Variable variable, Class<?> type){
        if(variable == null) {
            variables[index] = null;
            types[index] = null;
        } else {
            variables[index] = new Variable(variable);
            types[index] = type;
        }
    }

    public void setInt(int index, int value) {
        variables[index] = new Variable(value);
        types[index] = Integer.class;
    }

    public void setLong(int index, long value) {
        int high = (int) (value >> 32);                 //高32位
        int low = (int) (value & 0x000000ffffffffL);    //低32位
        variables[index] = new Variable(high);
        variables[index + 1] = new Variable(low);

        types[index] = Long.class;
        types[index + 1] = null;
    }

    public void setFloat(int index, float value) {
        variables[index] = new Variable(Float.floatToIntBits(value));
        types[index] = Float.class;
    }

    public void setDouble(int index, double value) {
        types[index] = Double.class;
        types[index + 1] = null;
        long temp = Double.doubleToLongBits(value);

        int high = (int) (temp >> 32);                   //高32位
        int low = (int) (temp & 0x000000ffffffffL);      //低32位

        variables[index] = new Variable(high);
        variables[index + 1] = new Variable(low);
    }

    public void setReference(int index, Reference reference){
        types[index] = Reference.class;
        variables[index] = new Variable(reference);
    }

    public Variable get(int index){
        return variables[index];
    }

    public int getInt(int index) {
        if(types[index] != Integer.class && types[index] != null){
            throw new ClassCastException(String.format("variables %d is not a %s", index, types[index]));
        }
        return variables[index].getInteger();
    }

    public long getLong(int index) {
        if(types[index] != Long.class){
            throw new ClassCastException(String.format("variables %d is not a %s", index, types[index]));
        }
        int high = variables[index].getInteger();
        int low = variables[index + 1].getInteger();

        long l1 = (high & 0x000000ffffffffL) << 32;
        long l2 = low & 0x00000000ffffffffL;
        return l1 | l2;
    }

    public float getFloat(int index) {
        if(types[index] != Float.class){
            throw new ClassCastException(String.format("variables %d is not a %s", index, types[index]));
        }
        return Float.intBitsToFloat(variables[index].getInteger());
    }

    public double getDouble(int index) {
        if(types[index] != Double.class){
            throw new ClassCastException(String.format("variables %d is not a %s", index, types[index]));
        }
        int high = variables[index].getInteger();
        int low = variables[index + 1].getInteger();

        long l1 = (high & 0x000000ffffffffL) << 32;
        long l2 = low & 0x00000000ffffffffL;

        return Double.longBitsToDouble(l1 | l2);
    }

    public Reference getReference(int index){
        if(types[index] != Reference.class){
            throw new ClassCastException(String.format("variables %d is not a %s", index, types[index]));
        }
        return variables[index].getReference();
    }

    public int size() {
        return variables.length;
    }

    public void copyTo(int begin, int length, LocalVariables dest, int destBegin){
        if(dest == this && destBegin > begin){
            for(int i = length - 1;i >= 0;i--){
                types[destBegin + i] = types[begin + i];
                dest.set(destBegin + i, get(begin + i));
            }
        } else {
            for (int i = 0;i < length;i++){
                dest.types[destBegin + i] = types[begin + i];
                dest.set(destBegin + i, get(begin + i));
            }
        }
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder("LocalVariables: [");
        for(int i = 0;i < size();i++){
            sb.append(get(i));
            if(i != size() - 1){
                sb.append(", ");
            }
        }
        sb.append(']');
        return sb.toString();
    }

}
