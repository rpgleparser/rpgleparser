     C                   EVAL     ison = %BITOR(HEXVAL:B0)
     C                   EVAL     ison = %BITOR(x'80':x'40')
     C                   EVAL     ison = %bitor(hexval:x'10')
     C                   EVAL     ison = %BItor(%str(fld):bitval:x'0F0F')
      /free
         ison = %bitor(HEXVAL:B0); // comment 1
         ison = %BITOR(x'80':X'40'); // comment 2
         ison = %Bitor(hexval:x'01'); // comment 3
         ison = %Bitor(%str(fld):bitval:x'0F0F'); // comment 4
      /end-free 
                 
