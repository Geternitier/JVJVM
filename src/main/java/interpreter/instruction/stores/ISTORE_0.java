package interpreter.instruction.stores;

import interpreter.instruction.Instruction;
import runtime.JFrame;
import runtime.JThread;
import runtime.ProgramCounter;
import runtime.classdata.Method;

public class ISTORE_0 implements Instruction {

    public ISTORE_0(ProgramCounter pc, Method method){
    }

    @Override
    public void run(JThread thread) {
        JFrame curFrame = thread.top();
        curFrame.getLocalVariables().setInt(0, curFrame.getOperandStack().popInt());
    }

    @Override
    public String toString(){
        return "istore_0";
    }
}
