package interpreter.instruction.constants;

import interpreter.instruction.Instruction;
import lombok.AllArgsConstructor;
import runtime.JThread;
import runtime.OperandStack;
import runtime.ProgramCounter;
import runtime.classdata.Method;

@AllArgsConstructor
public class BIPUSH implements Instruction {
    private final int value;

    public static BIPUSH bipush(ProgramCounter pc, Method method){
        return new BIPUSH(pc.byte_());
    }

    @Override
    public void run(JThread thread) {
        thread.top().getOperandStack().pushInt(value);
    }

    @Override
    public String toString(){
        return String.format("bipush %d",value);
    }
}
