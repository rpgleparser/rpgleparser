     C*0N01Factor1+++++++Opcode(E)+Factor2+++++++Result++++++++Len++D+HiLoEq....
     C     *Entry        PList
      *
     C                   COMMIT
     C                   Commit(e)
     C      Bound        Commit                                         99
     C      Bound        commit(E)
      /Free
          commit;
          COMMIT(e);
          Commit boundary;
          commit(e) myboundary;

      /end-free
      *
     C                   SETON                                        LR  
