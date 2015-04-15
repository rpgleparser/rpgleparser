     D VALFLD          S              4a   Inz(%bitnot(constfld))
     C                   EVAL     val1 = %BITNOT(HEXVAL)
     C                   EVAL     val2 = %BITNOT(x'800F0F')
     C                   EVAL     val3 = %bitnot(x'10')
     C                   EVAL     val4 = %BItnot(%str(fld))
      /free
         val1 = %bitnot(HEXVAL); // comment 1
         val2 = %BITNOT(x'80'); // comment 2
         val3 = %Bitnot(hexval); // comment 3
         val4 = %Bitnot(%str(fld)); // comment 4
      /end-free 
                 