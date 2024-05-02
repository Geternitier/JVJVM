package interpreter.instruction.stores;

import interpreter.instruction.Instruction;
import runtime.JFrame;
import runtime.JThread;
import runtime.ProgramCounter;
import runtime.classdata.Method;

public class FSTORE_2 implements Instruction {

    public FSTORE_2(ProgramCounter pc, Method method){

    }

    @Override
    public void run(JThread thread) {
        JFrame curFrame = thread.top();
        curFrame.getLocalVariables().setFloat(2, curFrame.getOperandStack().popFloat());
    }

    @Override
    public String toString(){
        return "fstore_2";
    }
}
