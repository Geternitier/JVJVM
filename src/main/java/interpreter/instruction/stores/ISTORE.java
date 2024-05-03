package interpreter.instruction.stores;

import interpreter.instruction.Instruction;
import runtime.JFrame;
import runtime.JThread;
import runtime.ProgramCounter;
import runtime.classdata.Method;

public class ISTORE implements Instruction {
    private final int index;

    public ISTORE(ProgramCounter pc, Method method){
        index = pc.readUnsignedByte();
    }

    @Override
    public void run(JThread thread) {
        JFrame curFrame = thread.top();
        curFrame.getLocalVariables().setInt(index, curFrame.getOperandStack().popInt());
    }

    @Override
    public String toString(){
        return "istore " + index;
    }
}
