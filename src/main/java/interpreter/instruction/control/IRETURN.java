package interpreter.instruction.control;

import interpreter.instruction.Instruction;
import lombok.AllArgsConstructor;
import runtime.JThread;
import runtime.ProgramCounter;
import runtime.classdata.Method;

public class IRETURN implements Instruction {

    public IRETURN(ProgramCounter pc, Method method){
    }

    @Override
    public void run(JThread thread) {
        int value = thread.top().getOperandStack().popInt();
        thread.pop();
        thread.top().getOperandStack().pushInt(value);
    }

    @Override
    public String toString(){
        return "ireturn";
    }
}
