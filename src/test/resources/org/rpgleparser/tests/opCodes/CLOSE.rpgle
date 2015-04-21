     C*0N01Factor1+++++++Opcode(E)+Factor2+++++++Result++++++++Len++D+HiLoEq....
     C     *Entry        PList
      *
     C                   CLOSE     MYFILE
     C                   Close(e)  myfile
     C                   Close     *all                                 99
     C                   close(e)  *all
      /Free
          close myfile;
          CLOSE *ALL;
          CLOSE(e) myfile;
          close(e) *ALL;

      /end-free
      *
     C                   SETON                                        LR  
