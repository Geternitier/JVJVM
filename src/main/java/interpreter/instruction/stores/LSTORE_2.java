package interpreter.instruction.stores;

import interpreter.instruction.Instruction;
import runtime.JFrame;
import runtime.JThread;
import runtime.ProgramCounter;
import runtime.classdata.Method;

public class LSTORE_2 implements Instruction {

    public LSTORE_2(ProgramCounter pc, Method method){

    }

    @Override
    public void run(JThread thread) {
        JFrame curFrame = thread.top();
        curFrame.getLocalVariables().setLong(2, curFrame.getOperandStack().popLong());
    }

    @Override
    public String toString(){
        return "lstore_2";
    }
}
