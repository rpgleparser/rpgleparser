     C*0N01Factor1+++++++Opcode(E)+Factor2+++++++Result++++++++Len++D+HiLoEq....
     C     *Entry        PList
      *
     C                   RETURN
     C                   RETURN    MYDS
     C                   RETURN    MYVAR + 10
     C                   Return    D'1995-10-01'
     C                   RETURN(H) MYVAR + 10
      /Free
          return;
          return myds;
          return (myvar + 10);
          return D'1995-10-01';
          return(h) (myvar + 10);
      /end-free
      *
     C                   SETON                                        LR  