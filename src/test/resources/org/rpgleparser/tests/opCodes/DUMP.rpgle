     C*0N01Factor1+++++++Opcode(E)+Factor2+++++++Result++++++++Len++D+HiLoEq....
     C     *Entry        PList
      *
     C     Ident         DUMP       
     C                   Dump
     C                   dump(a)
     C     fld01         dump(a)
           
      /Free
          dump;
          dump ident;
          DUMP fld01;
          Dump(a) fld01;
          Dump(a);
      /end-free
      *
     C                   SETON                                        LR  
