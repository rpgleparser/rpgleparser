     C*0N01Factor1+++++++Opcode(E)+Factor2+++++++Result++++++++Len++D+HiLoEq....
     C     *Entry        PList
      *
     C                   RESET                   MYDS
     C                   RESET     *ALL          MYTABLE
     C     *NOKEY        Reset     *ALL          MYTABLE
     C     *NOKEY        reset                   MYTABLE
      /Free
          reset ds1;
          reset *in01;
          reset *ALL MODS;
          reset *nokey mytable;
          reset *NOKEY *all mytable;
      /end-free
      *
     C                   SETON                                        LR  
