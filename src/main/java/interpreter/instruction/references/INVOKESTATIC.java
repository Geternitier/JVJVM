package interpreter.instruction.references;

import interpreter.instruction.Instruction;
import runtime.JThread;
import runtime.ProgramCounter;
import runtime.classdata.Method;
import runtime.classdata.constant.MethodRef;

public class INVOKESTATIC implements Instruction {

    public INVOKESTATIC(ProgramCounter pc, Method method){
        MethodRef methodRef = (MethodRef) method.getJClass().getConstantPool().getConstant(pc.ushort());

    }

    @Override
    public void run(JThread thread) {

    }
}
