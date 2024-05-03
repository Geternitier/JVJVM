package interpreter.instruction.stores;

import interpreter.instruction.Instruction;
import runtime.JFrame;
import runtime.JThread;
import runtime.ProgramCounter;
import runtime.classdata.Method;

public class LSTORE implements Instruction {
    private final int index;

    public LSTORE(ProgramCounter pc, Method method){
        index = pc.readUnsignedByte();
    }

    @Override
    public void run(JThread thread) {
        JFrame curFrame = thread.top();
        curFrame.getLocalVariables().setLong(index, curFrame.getOperandStack().popLong());
    }

    @Override
    public String toString(){
        return "lstore " + index;
    }
}
