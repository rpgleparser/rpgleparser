     C                   EVAL     ison = %BITXOR(HEXVAL:B0)
     C                   EVAL     ison = %BITXOR(x'80':x'40')
     C                   EVAL     ison = %bitxor(hexval:x'10')
     C                   EVAL     ison = %BItXor(%str(fld):x'0F0F')
      /free
         ison = %bitxor(HEXVAL:B0); // comment 1
         ison = %BITXOR(x'80':X'40'); // comment 2
         ison = %Bitxor(hexval:x'01'); // comment 3
         ison = %Bitxor(%str(fld):x'0F0F'); // comment 4
      /end-free 
                 