     C*0N01Factor1+++++++Opcode(E)+Factor2+++++++Result++++++++Len++D+HiLoEq....
     C     *Entry        PList
      *
     C     'msg'         DSPLY       
     C     'msg'         Dsply     MSGQ
     C     'msg'         Dsply     MSGQ           RESULT
     C     MSGFLD        Dsply     MSGQ                                 99
     C     'msg'         Dsply(e)  QSYSOPR
     C     MSGFLD        Dsply     *EXT       
      /Free
          dsply 'msg';
          dsply msg '' result;
          dsply msg qsysopr;
          dsply(e) *MUSR0001 *EXT;
          dsply ('Length =' + %char(%len(str)) + 'bytes');

      /end-free
      *
     C                   SETON                                        LR  
