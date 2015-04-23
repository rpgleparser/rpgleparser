     C*0N01Factor1+++++++Opcode(E)+Factor2+++++++Result++++++++Len++D+HiLoEq....
     C     *Entry        PList
     C                   Parm                    w_RtnValue
      *
     C     KEY           SETGT     INFILE
     C     'QPADEV0'     Setgt     INFILE
     C     'QPADEV0'     setGT     INFILE                             9998
     C     KEYLST        SETGT(e)  INFILE
     C     *HIVAL        SETGT     INFILE
      /Free
          setgt key infile;
          Setgt ('abc':'def') custrec;
          setgt(e) (%xlate(custname:LO:HI) : code) 
                custrec;
          SETGT(e) custrec custrecDS;

      /end-free
     C                   SETON                                        LR  
