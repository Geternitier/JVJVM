package interpreter.instruction.references;

import interpreter.instruction.Instruction;
import runtime.JThread;
import runtime.ProgramCounter;
import runtime.classdata.Method;

public class MONITORENTER implements Instruction {

    public MONITORENTER(ProgramCounter programCounter, Method method) {

    }

    @Override
    public void run(JThread thread) {
        thread.top().getOperandStack().pop();
    }

    @Override
    public String toString(){
        return "monitorenter";
    }

}
