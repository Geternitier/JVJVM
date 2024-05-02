package interpreter.instruction.control;

import interpreter.instruction.Instruction;
import runtime.JThread;
import runtime.ProgramCounter;
import runtime.classdata.Method;

public class RETURN implements Instruction {

    public RETURN(ProgramCounter pc, Method method){}

    @Override
    public void run(JThread thread) {
        thread.pop();
    }

    @Override
    public String toString(){
        return "return";
    }
}
