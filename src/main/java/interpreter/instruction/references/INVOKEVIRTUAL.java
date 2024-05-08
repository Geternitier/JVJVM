package interpreter.instruction.references;

import Util.UnImplementedError;
import interpreter.instruction.Instruction;
import runtime.*;
import runtime.classdata.ConstantPool;
import runtime.classdata.Method;
import runtime.classdata.constant.MethodRef;
import runtime.reference.ClassReference;
import runtime.reference.FieldReference;
import runtime.reference.Reference;
import runtime.reference.StringReference;

public class INVOKEVIRTUAL implements Instruction {
    private final int index;

    public INVOKEVIRTUAL(ProgramCounter pc, Method method){
        index = pc.readShort();
    }

    @Override
    public void run(JThread thread) {
        JFrame curFrame = thread.top();
        ConstantPool constantPool = thread.top().getJClass().getConstantPool();
        MethodRef methodRef = (MethodRef) constantPool.getConstant(index);
        LocalVariables localVariables = curFrame.getOperandStack().popArgs(methodRef.getType());
        Reference reference = curFrame.getOperandStack().popReference();

        try {
            JClass jClass;
            if (reference instanceof FieldReference fieldReference) {
                jClass = fieldReference.getJClass();
            } else if (reference instanceof StringReference) {
                jClass = methodRef.getJClass().getClassLoader().loadClass("Ljava/lang/String;");
            } else if (reference instanceof ClassReference classReference) {
                jClass = classReference.getValue();
            } else throw new UnImplementedError("invokevirtual else");
            Method method = jClass.getMethod(methodRef.getName(), methodRef.getType());
            if (method.getName().equals("getClass") &&
                    method.getDescriptor().equals("()Ljava/lang/Class;") &&
                    method.getJClass().getName().equals("java/lang/Object")) {
                curFrame.getOperandStack().pushReference(new ClassReference(methodRef.getJClass().getThisClass()));
                return;
            }
            LocalVariables variables = new LocalVariables(method.getCode().getMaxLocals());
            variables.set(0, new Variable(reference), Reference.class);
            localVariables.copyTo(0, method.countArgc(), variables, 1);
            thread.getVirtualMachine().getInterpreter().invoke(method, thread, variables);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError(e.toString());
        }
        // TODO
    }

    @Override
    public String toString(){
        return "invokevirtual "+index;
    }
}
