     C*0N01Factor1+++++++Opcode(E)+Factor2+++++++Result++++++++Len++D+HiLoEq....
     C     *Entry        PList
      *
     C                   CALLP     proc1(parm1)
     C                   CALLP     proc2(parm1:parm2)
     C                   CALLP(e)  proc2(parm1:parm2)
     C                   CALLP(e)  proc3(arr(x) + 1)
      /Free
          callp proc1(parm1);
          CALLP(e) proc1(parm1:parm2);
          proc3('parm1':parm2);
          proc4(%len(xx) + 5:'literal');
      /end-free
      *
     C                   SETON                                        LR  
