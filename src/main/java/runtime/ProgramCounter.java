package runtime;

import interpreter.instruction.Decoder;
import runtime.classdata.Method;

import java.nio.ByteBuffer;

public class ProgramCounter {
    private final ByteBuffer buf;

    public ProgramCounter(byte[] code) {
        buf = ByteBuffer.wrap(code);
    }

    public int readPadding(){
        int padding = getPosition() % 4;
        if(padding != 0){
            setPosition(getPosition() + 4 - padding);
            return 4 - padding;
        }
        return padding;
    }

    public byte readByte() {
        return buf.get();
    }

    public int readUnsignedByte() {
        return Byte.toUnsignedInt(buf.get());
    }

    public short readShort() {
        return buf.getShort();
    }

    public int readUnsignedShort() {
        return Short.toUnsignedInt(buf.getShort());
    }

    public int readInt() {
        return buf.getInt();
    }

    public void move(int offset) {
        buf.position(buf.position() + offset);
    }

    public void setPosition(int pos) {
        buf.position(pos);
    }

    public int getPosition() {
        return buf.position();
    }

    public String getCode(Method method){
        StringBuilder sb = new StringBuilder();
        while (getPosition() < buf.limit()){
            sb.append(getPosition()).append(": ");
            sb.append(Decoder.decode(this, method)).append("\n");
        }
        return sb.toString();
    }

}
