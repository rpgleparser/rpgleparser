     C*0N01Factor1+++++++Opcode(E)+Factor2+++++++Result++++++++Len++D+HiLoEq....
     C     *Entry        PList
      *
     C     *ISO0         TEST(Z)                 Tfld  
     C     *MDY0         TEST(D)                 dfld                   99  
     C                   test(e)                 dfld  
     C     *USA          test(et)                dfld 
     C     *LONGJUL      Test(DE)                dfld  
      /Free
          TEST(Z) *ISO0 TFLD;
          Test(d) *MDY0 dfld;
          test(e) dfld;
          Test(et) *usa dfld;
          test(DE) *LONGJUL dfld;
      /end-free
     C                   SETON                                        LR  
