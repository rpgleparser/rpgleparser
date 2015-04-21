     C*0N01Factor1+++++++Opcode(E)+Factor2+++++++Result++++++++Len++D+HiLoEq....
     C     *Entry        PList
     C                   Parm                    w_RtnValue
      *
     C     KEY           CHAIN     INFILE
     C     'QPADEV0'     CHAIN     INFILE
     C     'QPADEV0'     chain     INFILE                             9998
     C     KEYLST        CHAIN(e)  INFILE
     C     KEYLST        CHAIN(Ne) INFILE
      /Free
          chain key infile;
          chain ('abc':'def') custrec;
          chain(e) (%xlate(custname:LO:HI) : code) 
                custrec;
          chain(ne) custrec custrecDS;

      /end-free
     C                   SETON                                        LR  
