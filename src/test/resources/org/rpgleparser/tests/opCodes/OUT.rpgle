     C*0N01Factor1+++++++Opcode(E)+Factor2+++++++Result++++++++Len++D+HiLoEq....
     C     *Entry        PList
      *        
     C                   OUT       MYDS
     C                   out       myds                                 99                               
     C                   out(e)    myds
     C     *LOCK         OUT       myds                                 99                               
     C     *LOCK         out(e)    myds
      /Free
          OUT myds;
          out myds;
          out(e) myds;
          out *lock myds;
          out(e) *lock myds;
      /end-free
      *
     C                   SETON                                        LR  
