import java.io.*;
﻿
public class VPCrte
{
    static final int MEMSIZ = 100;
﻿
    static final int HALT  = 0;
    static final int ADD   = 1;
    static final int SUB   = 2;
    static final int MLT   = 3;
    static final int DIV   = 4;
    static final int ILOAD = 5;
    static final int LOAD  = 6;
    static final int STOR  = 7;
    static final int READ  = 8;
    static final int WRITE = 9;
    static final int BR    = 10;
    static final int BZ    = 11;
    static final int BN    = 12;
    static final int DUMP  = 13;
﻿
    static int MEMORY[] = new int[MEMSIZ];
﻿
    static int PCREG;
    static int IRREG;
    static int GPREG;
﻿
    static boolean debug = false;
﻿
    public static void readToMemory(String fname) throws IOException
    {
        String buffer = null;
        BufferedReader br = new BufferedReader(new FileReader(fname));
﻿
        int c = 0;
﻿
        while ((buffer = br.readLine()) != null)
        {
﻿
            if (debug)
            {
                System.out.println("readToMemory: MEMORY[" + pad(c,2) + "] = (" + buffer + ")");
            }
﻿
            MEMORY[c] = Integer.parseInt(buffer);
            c++;
        }
    }
﻿
    public static String pad(int n, int w)
    {
﻿
        //Len is converting int n to a string then checking the length
        //then converting that length to an int
﻿
        int len = String.valueOf(n).length();
﻿
        //numZeros is subtracting the wanted int length from len to
        //check how many zeros we want to place to have the correct
        //int length
﻿
        int numZeros = w - len;
﻿
        //String ans = "" is seting a string varible to 0 for a place holder
        //for the padding loop.
﻿
        String ans = "";
﻿
        //this loop is using varible numZeros to know how many zeros we want
        //by cycling and placing zeros in ans till the length needed is met
﻿
        for(int i=0; i<numZeros; i++)
        {
            ans += "0";
        }
﻿
        //since we are returning a indicated in the method we return
        // ans + n
﻿
        return ans + n;
﻿
    }
﻿
    public static void dumpMemory()
    {
        System.out.println("===================================================================");
        int c=0;
﻿
        System.out.println("PCREG = " + pad(PCREG, 4));
        System.out.println("IRREG = " + pad(IRREG,4));
        System.out.println("GPREG = " + pad(GPREG, 4) + "\n");
﻿
        System.out.println("MEMORY:     0     1     2     3     4     5     6     7     8     9");
        System.out.println("    ---------------------------------------------------------------");
﻿
        for (int i=0; i<MEMSIZ; i++)
        {
            if ((i%10) == 0)
            {
                System.out.print("     " + c + "|");
                c++;
            }
﻿
            System.out.print("  " + pad(MEMORY[i], 4));
﻿
            if (((i+1)%10) == 0)
                System.out.println();
        }
        System.out.println();
        System.out.println("===================================================================");
    }
﻿
    public static void runProg() throws IOException
    {
         String instructions[] = {"HALT","ADD","SUB","MLT","DIV","ILOAD","LOAD","STOR","READ","WRITE","BR","BZ","BN","DUMP"};
﻿
﻿
        PCREG = 0;
﻿
        while(true)
        {
            // fetch current instruction from memory and copy to IRREG
﻿
            IRREG = MEMORY[PCREG];
            int OPCODE = IRREG/100;
            int OPERAND = IRREG%100;
﻿
            // extract opcode and operand from IRREG
﻿
            //OPCODE = pad(OPCODE,2);
﻿
            if (debug)
            {
                // display info about code as it is executed
﻿
                System.out.println("runProg: MEMORY[" + pad(PCREG,2) + "] = " +pad(IRREG,4) + ", opcode = " + pad(OPCODE,2) + ", operand = " + pad(OPERAND,2) + ", GPREG = " + pad(GPREG,4) + " (" + instructions[OPCODE] + ")");
﻿
            }
﻿
            // handle all of the opcodes with a large if/else if/else if/...
            // or switch statement.
﻿
            if(OPCODE == HALT) break;
﻿
            else if(OPCODE == ADD) GPREG += MEMORY[OPERAND];
﻿
            else if(OPCODE == SUB) GPREG -= MEMORY[OPERAND];
﻿
            else if(OPCODE == MLT) GPREG *= MEMORY[OPERAND];
﻿
            else if(OPCODE == DIV) GPREG /= MEMORY[OPERAND];
﻿
            else if(OPCODE == ILOAD) GPREG = OPERAND;
﻿
            else if(OPCODE == LOAD) GPREG = MEMORY[OPERAND];
﻿
            else if(OPCODE == STOR) MEMORY[OPERAND] = GPREG;
﻿
            else if(OPCODE == READ)
            {
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                System.out.print("[" + OPERAND + "]? ");
                String input = reader.readLine();
﻿
                MEMORY[OPERAND] = Integer.parseInt(input);
﻿
            }
﻿
            else if(OPCODE == WRITE) System.out.println("[" + OPERAND + "] -> " + MEMORY[OPERAND]);
﻿
            else if(OPCODE == BR)
            {
                PCREG = OPERAND;
                continue;
            }
﻿
            else if(OPCODE == BZ)
            {
                if(GPREG == 0)
                {
                    PCREG = OPERAND;
                    continue;
                }
﻿
            }
﻿
            else if(OPCODE == BN)
            {
                if(GPREG != 0)
                {
                    PCREG = OPERAND;
                    continue;
                }
            }
﻿
            else if(OPCODE == DUMP) dumpMemory();
﻿
            // increment (PCREG++) to prepare for next instruction
﻿
            PCREG++;
        }
    }
﻿
    public static void main(String args[]) throws IOException
    {
        if (args.length == 0)
        {
            System.out.println("usage: java VPCrte FILENAME.exe [ debug ]");
            System.exit(0);
        }
﻿
        if ((args.length == 2) && (args[1].equals("debug"))) debug = true;
﻿
        readToMemory(args[0]);
﻿
        if (debug) dumpMemory();
﻿
        runProg();
    }
}
