package interpreter.instruction.stores;

import interpreter.instruction.Instruction;
import runtime.JFrame;
import runtime.JThread;
import runtime.ProgramCounter;
import runtime.classdata.Method;

public class DSTORE_0 implements Instruction {

    public DSTORE_0(ProgramCounter pc, Method method){

    }

    @Override
    public void run(JThread thread) {
        JFrame curFrame = thread.top();
        curFrame.getLocalVariables().setDouble(0, curFrame.getOperandStack().popDouble());
    }

    @Override
    public String toString(){
        return "dstore_0";
    }
}
