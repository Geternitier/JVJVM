package interpreter.instruction.stores;

import interpreter.instruction.Instruction;
import runtime.JFrame;
import runtime.JThread;
import runtime.ProgramCounter;
import runtime.classdata.Method;

public class FSTORE_1 implements Instruction {

    public FSTORE_1(ProgramCounter pc, Method method){

    }

    @Override
    public void run(JThread thread) {
        JFrame curFrame = thread.top();
        curFrame.getLocalVariables().setFloat(1, curFrame.getOperandStack().popFloat());
    }

    @Override
    public String toString(){
        return "fstore_1";
    }
}
