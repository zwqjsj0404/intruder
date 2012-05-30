package com.alibaba.intruder.agent.transform;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

import org.objectweb.asm.ClassAdapter;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodAdapter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import com.alibaba.intruder.agent.util.Logger;

public class CallOtherClassTransformer implements ClassFileTransformer {

	public byte[] transform(ClassLoader cl, String arg1, Class<?> arg2,
			ProtectionDomain arg3, byte[] classBytes)
			throws IllegalClassFormatException {
		Logger.info("CallOtherClassTransformer:: transform class...");
		Logger.debug("CallOtherClassTransformer's classloader = "
				+ DefaultTransformer.class.getClassLoader().toString());
		ClassReader cr = new ClassReader(classBytes);
		ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);
		ClassAdapter classAdapter = new CallOtherClassAdapter(cw);
		cr.accept(classAdapter, ClassReader.SKIP_DEBUG);
		byte[] transfromedClassBytes = cw.toByteArray();

		return transfromedClassBytes;
	}

}

class CallOtherClassAdapter extends ClassAdapter implements Opcodes {

	public CallOtherClassAdapter(ClassVisitor cv) {
		super(cv);
		// TODO Auto-generated constructor stub
	}

	public MethodVisitor visitMethod(final int access, final String name,
			final String desc, final String signature, final String[] exceptions) {
		MethodVisitor mv = cv.visitMethod(access, name, desc, signature,
				exceptions);
		MethodVisitor wrappedMv = mv;
		if (mv != null && name.equals("foo")) {
			Logger.debug("CallOtherClassTransformer::handler foo");
			wrappedMv = new CallOtherClassMethodAdapter(mv);
		}
		return wrappedMv;
	}
}

class CallOtherClassMethodAdapter extends MethodAdapter implements Opcodes {

	public CallOtherClassMethodAdapter(MethodVisitor mv) {
		super(mv);
	}

	public void visitCode() {
		//!!static method hasn't tmp var 0,mv.visitVarInsn(ALOAD, 0) will throw VerifyError
		mv.visitVarInsn(ALOAD, 0);
		mv.visitInsn(ICONST_1);
		mv.visitMethodInsn(INVOKESTATIC,
				"sample/test/SimpleClassForTransformedCall", "call",
				"(Ljava/lang/Object;I)V");

	}

}