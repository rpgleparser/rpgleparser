     C                   EVAL     val1 = %CHAR(FLD1)
     C                   EVAL     val2 = %CHAR(x'80')
     C                   EVAL     val3 = %char(fld:*jobrun)
     C                   EVAL     val4 = %char(%str(fld):*HMS)
     C                   EVAL     val5 = %char(datefld:*HMS)
      /free
         val1 = %char(HEXVAL); // comment 1
         val2 = %CHAR(x'80'); // comment 2
         val3 = %Char(fld:*jobrun); // comment 3
         val4 = %char(%str(fld):*HMS); // comment 4
         val5 = %char(datefld:*hms); // comment 5       
      /end-free 
                 