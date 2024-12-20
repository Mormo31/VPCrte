# VPCrte
This code simulates a simple Virtual Computer emulator.

Hello!

The following is my VCPU project I worked on to create a virtual computer.

This project was intended to be used to better learn how a computer not only runs from the base level,
but also how programs and compilers work.

The program also includes three registers, 
the GPREG for the data currently being manipulated, the PCREG storing which line/instruction the compiler is on, and the IRREG storing the command of the current line.

The Assembler compiles the submitted file, converting entered commands into machine code based on the "codebook".\
All command values in memory are stored in a 4-digit integer, with the first two values being the command, and the final two being the operand, often memory location.
One limitation of the VCPU is it only has 100 memory values, which is split between the commands of the submitted program, and the memory values utilized.

Below are the following commands.

    HALT  = 0   Ends the Program
    ADD   = 1   Adds the value at a memory argument to the GPREG
    SUB   = 2   Subtracts the value at a memory argument to the GPREG
    MLT   = 3   Multiples the value at a memory argument by the GPREG, then places value in the GPREG
    DIV   = 4   Divides the value at a memory argument by the GPREG, then places value in the GPREG
    ILOAD = 5   Loads a specific value into the GPREG
    LOAD  = 6   Loads a value at a memory location into the GPREG
    STOR  = 7   Stores the GPREG in memory
    READ  = 8   Reads user input
    WRITE = 9   Outputs the value at a specific memory location
    BR    = 10  Branch statement, return to the line specified
    BZ    = 11  Branch statement, if GPREG = 0, return to line specified
    BN    = 12  Brand statement, if GPREG != 0, return to line specified
    DUMP  = 13  Ouput all memory values

Then the VPCrte, or virtual runtime environment, executes the code based on the aforementioned codebook.

I have also provided a sample program created in the assembly language I created, named addTwo, which adds two values together entered by the user and then outputs the sum.


As a result of the project, you are able to produce basic assembly level programs.
