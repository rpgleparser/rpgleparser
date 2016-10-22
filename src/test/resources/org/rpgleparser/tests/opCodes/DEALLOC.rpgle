     C*0N01Factor1+++++++Opcode(E)+Factor2+++++++Result++++++++Len++D+HiLoEq....
     C     *Entry        PList
      *
     C                   DEALLOC                 Ptr
     C                   dealloc(e)              Ptr
     C                   dealloc                 Ptr                    99
     C                   dealloc(n)              Ptr
      /Free
          dealloc ptr;
          DEALLOC(e) ptr;
          dealloc(ne) ptr;
          Dealloc(n) ptr;

      /end-free
      *
     C                   SETON                                        LR  
