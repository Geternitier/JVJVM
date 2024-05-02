package interpreter.instruction.stores;

import interpreter.instruction.Instruction;
import runtime.JFrame;
import runtime.JThread;
import runtime.ProgramCounter;
import runtime.classdata.Method;

public class DSTORE implements Instruction {
    private final int index;

    public DSTORE(ProgramCounter pc, Method method){
        index = pc.ubyte();
    }

    @Override
    public void run(JThread thread) {
        JFrame curFrame = thread.top();
        curFrame.getLocalVariables().setDouble(index, curFrame.getOperandStack().popDouble());
    }

    @Override
    public String toString(){
        return "dstore " + index;
    }
}
