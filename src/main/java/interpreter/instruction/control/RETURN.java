package interpreter.instruction.control;

import interpreter.instruction.Instruction;
import runtime.JThread;
import runtime.ProgramCounter;
import runtime.classdata.Method;

public class RETURN implements Instruction {

    public static RETURN return_(ProgramCounter pc, Method method){
        return new RETURN();
    }

    @Override
    public void run(JThread thread) {
        thread.pop();
    }

    @Override
    public String toString(){
        return "return";
    }
}
