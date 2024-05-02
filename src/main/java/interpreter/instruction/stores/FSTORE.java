package interpreter.instruction.stores;

import interpreter.instruction.Instruction;
import runtime.JFrame;
import runtime.JThread;
import runtime.ProgramCounter;
import runtime.classdata.Method;

public class FSTORE implements Instruction {
    private final int index;

    public FSTORE(ProgramCounter pc, Method method){
        index = pc.ubyte();
    }

    @Override
    public void run(JThread thread) {
        JFrame curFrame = thread.top();
        curFrame.getLocalVariables().setFloat(index, curFrame.getOperandStack().popFloat());
    }

    @Override
    public String toString(){
        return "fstore " + index;
    }
}
