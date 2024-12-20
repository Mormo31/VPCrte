import java.io.*;
import java.util.*;

public class Assemble
{
    public static String pad(int n, int w)
    {
        //Len is converting int n to a string then checking the length
        //then converting that length to an int

        int len = String.valueOf(n).length();

        //numZeros is subtracting the wanted int length from len to
        //check how many zeros we want to place to have the correct
        //int length

        int numZeros = w - len;

        //String ans = "" is seting a string varible to 0 for a place holder
        //for the padding loop.

        String ans = "";

        //this loop is using varible numZeros to know how many zeros we want
        //by cycling and placing zeros in ans till the length needed is met

        for(int i=0; i<numZeros; i++)
        {
            ans += "0";
        }

        //since we are returning a indicated in the method we return
        // ans + n

        return ans + n;
    }

    //this method is reading and displaying the code. (throws IOException)
    // is needed for this to work.

    static void readSrc(String fname) throws IOException
    {
        String buffer = null;


        //this is calling BufferedReader to be used in this method

        BufferedReader br = new BufferedReader(new FileReader(fname));


        while ((buffer = br.readLine()) != null)
        {
            if(buffer.length() == 0 || buffer.indexOf("#") == 0)
            {
                continue;
            }


            ST stok = new ST(buffer);

            String inst = stok.nextToken();
            String arg =  stok.nextToken();

            int argi = Integer.parseInt(arg);

            if(argi < 0 || argi >= 100) break;

            String instructions[] = {"HALT","ADD","SUB","MLT","DIV","ILOAD","LOAD","STOR","READ","WRITE","BR","BZ","BN","DUMP"};


            int opcode = -1;
            for(int i = 0; i<instructions.length; i++)
            {
                if(inst.equals(instructions[i]))
                {
                    opcode = i;
                }


            }
               if(opcode == -1)
                {
                    System.out.println("ERROR OPCODE NOT FOUND!!!");
                    break;
                }


            System.out.println(pad(opcode,2) + pad(argi,2));

        }

    }

    public static void main(String args[]) throws IOException
    {
        if (args.length != 1)
        {
            System.out.println("usage:  java Assemble INPUTFILE");
            System.exit(0);
        }

        readSrc(args[0]);
    }
}
