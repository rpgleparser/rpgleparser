     C*0N01Factor1+++++++Opcode(E)+Factor2+++++++Result++++++++Len++D+HiLoEq....
     C     *Entry        PList
     C                   Parm                    w_RtnValue
      *
     C     KEY           SETLL     INFILE                                 97
     C     'QPADEV0'     Setll     INFILE
     C     'QPADEV0'     setll     INFILE                             9998
     C     KEYLST        SETLL(e)  INFILE
     C     *LOVAL        SETLL     INFILE
      /Free
          setll key infile;
          Setll ('abc':'def') custrec;
          setll(e) (%xlate(custname:LO:HI) : code) 
                custrec;
          SETLL(e) custrec custrecDS;

      /end-free
     C                   SETON                                        LR  
