     C*0N01Factor1+++++++Opcode(E)+Factor2+++++++Result++++++++Len++D+HiLoEq....
     C     *Entry        PList
      *
     C                   CLEAR                   MYDS
     C                   CLEAR     *ALL          MYTABLE
     C     *NOKEY        Clear     *ALL          MYTABLE
     C     *NOKEY        clear                   MYTABLE
      /Free
          clear ds1;
          clear *in01;
          clear *ALL MODS;
          clear *nokey mytable;
          clear *NOKEY *all mytable;
      /end-free
      *
     C                   SETON                                        LR  
