     C                   EVAL     ison = %BITAND(HEXVAL:B0)
     C                   EVAL     ison = %BITAND(x'80':x'40')
     C                   EVAL     ison = %bitand(hexval:x'10')
     C                   EVAL     ison = %BItand(%str(fld):bitval:x'0F0F')
      /free
         ison = %bitand(HEXVAL:B0); // comment 1
         ison = %BITAND(x'80':X'40'); // comment 2
         ison = %Bitand(hexval:x'01'); // comment 3
         ison = %Bitand(%str(fld):bitval:x'0F0F'); // comment 4
      /end-free 
                 