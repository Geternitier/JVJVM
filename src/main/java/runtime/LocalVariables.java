package runtime;

import java.nio.ByteBuffer;
import java.util.Optional;

public class LocalVariables {
    private final ByteBuffer buf;
    private final Class<?>[] types;

    public LocalVariables(int size){
        buf = ByteBuffer.allocate(size * 4);
        types = new Class[size];
    }

    public int getInt(int index) {
        if(types[index] != Integer.class){
            throw new ClassCastException(String.format("slots %d is not an %s",index,types[index]));
        }
        return buf.getInt(index * 4);
    }

    public void setInt(int index, int value) {
        types[index] = Integer.class;
        buf.putInt(index * 4, value);
    }

    public long getLong(int index) {
        if(types[index] != Long.class){
            throw new ClassCastException(String.format("slots %d is not a %s",index,types[index]));
        }
        return buf.getLong(index * 4);
    }

    public void setLong(int index, long value) {
        types[index] = Long.class;
        types[index + 1] = null;
        buf.putLong(index * 4, value);
    }

    public float getFloat(int index) {
        if(types[index] != Float.class){
            throw new ClassCastException(String.format("slots %d is not a %s",index,types[index]));
        }
        return buf.getFloat(index * 4);
    }

    public void setFloat(int index, float value) {
        types[index] = Float.class;
        buf.putFloat(index * 4,value);
    }

    public double getDouble(int index) {
        if(types[index] != Double.class){
            throw new ClassCastException(String.format("slots %d is not a %s",index,types[index]));
        }
        return buf.getDouble(index * 4);
    }

    public void setDouble(int index, double value) {
        types[index] = Double.class;
        types[index + 1] = null;
        buf.putDouble(index * 4, value);
    }

    public byte getByte(int index) {
        return (byte) getInt(index);
    }

    public void setByte(int index, byte value) {
        setInt(index, value);
    }

    public char getChar(int index) {
        return (char) getInt(index);
    }

    public void setChar(int index, char value) {
        setInt(index, value);
    }

    public short getShort(int index) {
        return (short) getInt(index);
    }

    public void setShort(int index, short value) {
        setInt(index, value);
    }

    public Optional<Object> getValue(int index) {
        if (types[index] == null) return Optional.empty();

        return switch (types[index].getSimpleName()) {
            case "Integer" -> Optional.of(getInt(index));
            case "Long" -> Optional.of(getLong(index));
            case "Float" -> Optional.of(getFloat(index));
            case "Double" -> Optional.of(getDouble(index));
            case "Byte" -> Optional.of(getByte(index));
            case "Character" -> Optional.of(getChar(index));
            case "Short" -> Optional.of(getShort(index));
            default -> throw new ClassCastException(String.format("unexpected type %s", types[index]));
        };
    }

    public int size() {
        return buf.limit() / 4;
    }

    public void copyTo(int begin, int length, LocalVariables dest, int destBegin) {
        if(dest == this && destBegin > begin){
            for(int i = length - 1;i >= 0;i--){
                types[destBegin + i] = types[begin + i];
                buf.putInt(4 * (destBegin + i), buf.getInt(4 * (begin + i)));
            }
        } else {
            for (int i = 0;i < length;i++){
                dest.types[destBegin + i] = types[begin + i];
                dest.buf.putInt(4 * (destBegin + i), buf.getInt(4 * (begin + i)));
            }
        }
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder("[");
        for(int i = 0;i < size();i++){
            sb.append(getInt(i));
            if(i != size() - 1){
                sb.append(", ");
            }
        }
        sb.append(']');
        return sb.toString();
    }

}
