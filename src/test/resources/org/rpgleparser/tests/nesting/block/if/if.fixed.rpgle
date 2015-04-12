 
     C                   IF        %ERROR()
     C                   IF        %STATUS(CUST)=1211
     C                   OPEN      CUST
     C                   ELSEIF    %STATUS(CUST)=1221
     C                   READ(E)   CUST
     C                   EVAL      CSNAME='CCC'
     C                   UPDATE(E) CUSTR
     C                   ENDIF
     C                   ENDIF 