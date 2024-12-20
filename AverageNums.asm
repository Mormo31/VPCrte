# purpose avg intergers entered by user
# count up store
# add numbers user enters
# divide by users total nums entered


ILOAD  1 # Using to count up
STOR  90


#ILOAD  1 # counter
#STOR  91


READ  92 # User number
LOAD  92
ADD   93
STOR  93


LOAD  92
BZ    11


LOAD  91 # Where we count
ADD   90 # 91++
STOR  91 # Storing new num back


LOAD  92
BN     2 # is GPREG ZERO if not branch back up


LOAD  93
DIV   91
STOR  94


WRITE 94
HALT  99
