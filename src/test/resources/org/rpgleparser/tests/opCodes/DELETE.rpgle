     C*0N01Factor1+++++++Opcode(E)+Factor2+++++++Result++++++++Len++D+HiLoEq....
     C     *Entry        PList
      *
     C                   DELETE    MYFILE
     C      SEARCHK      Delete    myfile                             9899
     C                   DELETE(e) MYFMT
      /Free
          delete myfile;
          delete searcharg myfile;
          DELETE(e) myfmt;

      /end-free
      *
     C                   SETON                                        LR  
