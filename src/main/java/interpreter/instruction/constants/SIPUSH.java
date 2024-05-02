package interpreter.instruction.constants;

import interpreter.instruction.Instruction;
import lombok.AllArgsConstructor;
import runtime.JThread;
import runtime.OperandStack;
import runtime.ProgramCounter;
import runtime.classdata.Method;

public class SIPUSH implements Instruction {
    private final int value;

    public SIPUSH(ProgramCounter pc, Method method){
        value = pc.short_();
    }

    @Override
    public void run(JThread thread) {
        thread.top().getOperandStack().pushInt(value);
    }

    @Override
    public String toString(){
        return String.format("sipush %d",value);
    }
}
