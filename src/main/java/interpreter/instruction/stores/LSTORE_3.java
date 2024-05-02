package interpreter.instruction.stores;

import interpreter.instruction.Instruction;
import runtime.JFrame;
import runtime.JThread;
import runtime.ProgramCounter;
import runtime.classdata.Method;

public class LSTORE_3 implements Instruction {

    public LSTORE_3(ProgramCounter pc, Method method){

    }

    @Override
    public void run(JThread thread) {
        JFrame curFrame = thread.top();
        curFrame.getLocalVariables().setLong(3, curFrame.getOperandStack().popLong());
    }

    @Override
    public String toString(){
        return "lstore_3";
    }
}
