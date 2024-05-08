package interpreter.instruction.references;

import Util.UnImplementedError;
import interpreter.instruction.Instruction;
import runtime.*;
import runtime.classdata.Method;
import runtime.classdata.constant.Constant;
import runtime.classdata.constant.InterfaceMethodRef;
import runtime.classdata.constant.MethodRef;
import runtime.classdata.constant.RefConstant;
import runtime.reference.Reference;

public class INVOKESPECIAL implements Instruction {
    private final int index;

    public INVOKESPECIAL(ProgramCounter programCounter, Method method) {
        index = programCounter.readUnsignedShort();
    }

    @Override
    public void run(JThread thread) {
        JFrame curFrame = thread.top();
        Constant constant = curFrame.getJClass().getConstantPool().getConstant(index);
        assert constant instanceof RefConstant;

        try {
            if(constant instanceof MethodRef methodRef){
                LocalVariables localVariables = curFrame.getOperandStack().popArgs(methodRef.getType());
                Reference reference = curFrame.getOperandStack().popReference();
                Method method = methodRef.getMethod();
                LocalVariables variables = new LocalVariables(method.getCode().getMaxLocals());
                variables.set(0, new Variable(reference), Reference.class);
                localVariables.copyTo(0, method.countArgc(), variables, 1);
                thread.getVirtualMachine().getInterpreter().invoke(method, thread, variables);
            } else if(constant instanceof InterfaceMethodRef interfaceMethodRef){
                // TODO
                throw new UnImplementedError("invokespecial interfaceMethodRef");
            } else assert false;
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError(e.toString());
        }
    }

    @Override
    public String toString(){
        return "invokespecial "+index;
    }
}
