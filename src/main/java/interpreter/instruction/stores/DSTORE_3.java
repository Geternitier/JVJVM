package interpreter.instruction.stores;

import interpreter.instruction.Instruction;
import runtime.JFrame;
import runtime.JThread;
import runtime.ProgramCounter;
import runtime.classdata.Method;

public class DSTORE_3 implements Instruction {

    public DSTORE_3(ProgramCounter pc, Method method){

    }

    @Override
    public void run(JThread thread) {
        JFrame curFrame = thread.top();
        curFrame.getLocalVariables().setDouble(3, curFrame.getOperandStack().popDouble());
    }

    @Override
    public String toString(){
        return "dstore_3";
    }
}
