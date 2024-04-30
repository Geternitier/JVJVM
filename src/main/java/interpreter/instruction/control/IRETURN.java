package interpreter.instruction.control;

import interpreter.instruction.Instruction;
import lombok.AllArgsConstructor;
import runtime.JThread;
import runtime.ProgramCounter;
import runtime.classdata.Method;

public class IRETURN implements Instruction {

    public static IRETURN ireturn(ProgramCounter pc, Method method){
        return new IRETURN();
    }

    @Override
    public void run(JThread thread) {
        int value = thread.top().getOperandStack().getTop();
        thread.pop();
        thread.top().getOperandStack().pushInt(value);
    }

    @Override
    public String toString(){
        return "ireturn";
    }
}