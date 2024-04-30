package interpreter.instruction.constants;

import interpreter.instruction.Instruction;
import runtime.JThread;
import runtime.ProgramCounter;
import runtime.classdata.Method;

public class ICONST_3 implements Instruction {

    public static ICONST_3 iconst_3(ProgramCounter pc, Method method){
        return new ICONST_3();
    }

    @Override
    public void run(JThread thread) {
        thread.top().getOperandStack().pushInt(3);
    }

    @Override
    public String toString(){
        return "iconst_3";
    }
}
