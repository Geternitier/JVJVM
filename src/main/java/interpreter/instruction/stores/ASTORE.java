package interpreter.instruction.stores;

import interpreter.instruction.Instruction;
import runtime.JFrame;
import runtime.JThread;
import runtime.ProgramCounter;
import runtime.classdata.Method;

public class ASTORE implements Instruction {
    private final int index;

    public ASTORE(ProgramCounter pc, Method method){
        index = pc.readUnsignedByte();
    }

    @Override
    public void run(JThread thread) {
        JFrame curFrame = thread.top();
        curFrame.getLocalVariables().setReference(index, curFrame.getOperandStack().popReference());
    }

    @Override
    public String toString(){
        return "astore";
    }
}
