package interpreter.instruction.constants;

import interpreter.instruction.Instruction;
import runtime.JThread;
import runtime.ProgramCounter;
import runtime.classdata.Method;

public class BIPUSH implements Instruction {
    private final int value;

    public BIPUSH(ProgramCounter pc, Method method){
        value = pc.readByte();
    }

    @Override
    public void run(JThread thread) {
        thread.top().getOperandStack().pushInt(value);
    }

    @Override
    public String toString(){
        return String.format("bipush %d",value);
    }
}
