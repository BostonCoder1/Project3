import java.util.ArrayList;

import jminusminus.CLEmitter;

import static jminusminus.CLConstants.*;

/**
 * This class programmatically generates the class file for the following Java application:
 *
 * <pre>
 * public class IsPrime {
 *     // Entry point.
 *     public static void main(String[] args) {
 *         int n = Integer.parseInt(args[0]);
 *         boolean result = isPrime(n);
 *         if (result) {
 *             System.out.println(n + " is a prime number");
 *         } else {
 *             System.out.println(n + " is not a prime number");
 *         }
 *     }
 *
 *     // Returns true if n is prime, and false otherwise.
 *     private static boolean isPrime(int n) {
 *         if (n < 2) {
 *             return false;
 *         }
 *         for (int i = 2; i <= n / i; i++) {
 *             if (n % i == 0) {
 *                 return false;
 *             }
 *         }
 *         return true;
 *     }
 * }
 * </pre>
 */
public class GenIsPrime {
    public static void main(String[] args) {
        // Create a CLEmitter instance
        CLEmitter e = new CLEmitter(true);

        // Create an ArrayList instance to store modifiers
        ArrayList<String> accessFlags = new ArrayList<String>();
        // public class IsPrime {
        accessFlags.add("public");
        e.addClass(accessFlags, "IsPrime", "java/lang/Object", null, true);

        // public static void main(String[] args) {
        accessFlags.clear();
        accessFlags.add("public");
        accessFlags.add("static");
        e.addMethod(accessFlags, "main", "([Ljava/lang/String;)V", null, true);

        // int n = Integer.parseInt(args[0]);

        // load a reference onto the stack from local variable.
        e.addNoArgInstruction(ALOAD_0);
        // load the int value  onto the stack.
        e.addNoArgInstruction(ICONST_0);
        // load onto the stack a reference from an array
        e.addNoArgInstruction(AALOAD);
        e.addMemberAccessInstruction(184, "java/lang/Integer", "parseInt", "(Ljava/lang/String;)I");
        // store int value into variable in memory.
        e.addNoArgInstruction(ISTORE_1);

        // boolean result = isPrime(n);

        // load an int value from local variable 1
        e.addNoArgInstruction(ILOAD_1);
        // pop the  n, push  the isPrime(n) onto stack
        e.addMemberAccessInstruction(184, "IsPrime", "isPrime", "(I)I");
        // store int value into variable 2 in memory.
        e.addNoArgInstruction(ISTORE_2);
        // load an int value into the stack from local variable 2.
        e.addNoArgInstruction(ILOAD_2);

        // if (result == 0) then branch it  to the  "NoPrime"
        e.addBranchInstruction(IFEQ, "NoPrime");

        // if given number is prime: System.out.println(n + " is a prime number");
        // Get the System.out on stack
        e.addMemberAccessInstruction(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        // Create an instance (say sb) of StringBuffer on stack for string concatenations and duplicate it
        //    sb = new StringBuffer();
        e.addReferenceInstruction(NEW, "java/lang/StringBuffer");
        // duplicate the value on top of the stack
        e.addNoArgInstruction(DUP);
        e.addMemberAccessInstruction(INVOKESPECIAL, "java/lang/StringBuffer", "<init>", "()V");
        // sb.append(n);
        // load n
        // load an int value from local variable 1
        e.addNoArgInstruction(ILOAD_1);
        e.addMemberAccessInstruction(INVOKEVIRTUAL, "java/lang/StringBuffer", "append",
                "(I)Ljava/lang/StringBuffer;");
        // sb.append(" is a prime number");
        e.addLDCInstruction(" is a prime number");
        e.addMemberAccessInstruction(INVOKEVIRTUAL, "java/lang/StringBuffer", "append",
                "(Ljava/lang/String;)Ljava/lang/StringBuffer;");
        // System.out.println(sb.toString());
        e.addMemberAccessInstruction(INVOKEVIRTUAL, "java/lang/StringBuffer",
                "toString", "()Ljava/lang/String;");
        e.addMemberAccessInstruction(INVOKEVIRTUAL, "java/io/PrintStream", "println",
                "(Ljava/lang/String;)V");
        // return;
        e.addNoArgInstruction(RETURN);
        // The is no prime case: System.out.println(n + " is not a prime number");
        e.addLabel("NoPrime");
        // Get  the System.out on stack.
        e.addMemberAccessInstruction(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        // Create an instance (say sb) of StringBuffer on stack for string concatenations and duplicate it
        //    sb = new StringBuffer();
        e.addReferenceInstruction(NEW, "java/lang/StringBuffer");
        e.addNoArgInstruction(DUP);
        e.addMemberAccessInstruction(INVOKESPECIAL, "java/lang/StringBuffer", "<init>", "()V");
        // sb.append(n);
        // load n
        e.addNoArgInstruction(ILOAD_1);
        e.addMemberAccessInstruction(INVOKEVIRTUAL, "java/lang/StringBuffer", "append",
                "(I)Ljava/lang/StringBuffer;");
        // sb.append(" is not a prime number");
        e.addLDCInstruction(" is not a prime number");
        e.addMemberAccessInstruction(INVOKEVIRTUAL, "java/lang/StringBuffer", "append",
                "(Ljava/lang/String;)Ljava/lang/StringBuffer;");
        // System.out.println(sb.toString());
        // invoke the constructor.
        e.addMemberAccessInstruction(INVOKEVIRTUAL, "java/lang/StringBuffer",
                "toString", "()Ljava/lang/String;");
        // invoke the constructor
        e.addMemberAccessInstruction(INVOKEVIRTUAL, "java/io/PrintStream", "println",
                "(Ljava/lang/String;)V");
        // return void from method
        e.addNoArgInstruction(RETURN);
        // private static boolean isPrime(int n) {
        accessFlags.clear();
        accessFlags.add("private");
        accessFlags.add("static");
        e.addMethod(accessFlags, "isPrime", "(I)I", null, true);
        // if (n >= 2)  we need to branch to the "Loop"
        // get n onto stack
        e.addNoArgInstruction(ILOAD_0);
        // get const 2 onto stack
        e.addNoArgInstruction(ICONST_2);
        e.addBranchInstruction(IF_ICMPGE, "Loop");

        // Base case: return 0; - No prime
        // load value to the stack
        e.addNoArgInstruction(ICONST_0);
        // return an integer from a method
        e.addNoArgInstruction(IRETURN);

        // Loop case:
        //     for (int i = 2; i <= n / i; i++) {
        // *             if (n % i == 0) {
        // *                 return false;
        // *             }
        // *         }
        // *         return true;
        e.addLabel("Loop");
        // load the  const 2 onto stack
        e.addNoArgInstruction(ICONST_2);
        // store the value in memory.
        e.addNoArgInstruction(ISTORE_1);

        // check whether i <= n / i
        e.addBranchInstruction(GOTO, "CheckCondition");

        // if (n % i == 0) return false;
        e.addLabel("If");
        // push n onto stack
        e.addNoArgInstruction(ILOAD_0);
        // push i onto stack
        e.addNoArgInstruction(ILOAD_1);
        // pop n and i then push n % i
        e.addNoArgInstruction(IREM);
        // check whether n % i == 0
        // n % i !== 0, branch to "Increment"
        e.addBranchInstruction(IFNE, "Increment");
        // Otherwise return false
        e.addNoArgInstruction(ICONST_0);
        e.addNoArgInstruction(IRETURN);

        e.addLabel("Increment");
        // increment i by 1
        e.addIINCInstruction(1, 1);
        // goto the CheckCondition
        // goes to another instruction at branchoffset.
        e.addBranchInstruction(GOTO, "CheckCondition");
        e.addLabel("CheckCondition");

        // get the i onto stack
        e.addNoArgInstruction(ILOAD_1);
        // get the n / i onto stack
        // load an int value from local variable.
        e.addNoArgInstruction(ILOAD_0);
        // load an int value from local variable.
        e.addNoArgInstruction(ILOAD_1);
        // divide two integers.
        e.addNoArgInstruction(IDIV);
        // if (i <= n/i) branch to "If"
        // if value1 is less than or equal to value2, branch to instruction at branchoffset.
        e.addBranchInstruction(IF_ICMPLE, "If");
        // Load the int value onto the stack.
        e.addNoArgInstruction(ICONST_1);
        // 	return an integer from a method for the prime number
        e.addNoArgInstruction(IRETURN);
        // write into the file system.
        e.write();
    }
}
