     C*0N01Factor1+++++++Opcode(E)+Factor2+++++++Result++++++++Len++D+HiLoEq....
     C     *Entry        PList
     C                   Parm                    w_RtnValue
      *
     C                   SORTA     ARR
     C                   SORTA(D)  ARR
     C                   SORTA(A)  ARR
     C                   SORTA     %SUBARR(ARR:1)
     C                   SORTA     %SUBARR(ARR:1:2)
      /Free
          sorta arr;
          SORTA(D) arr;
          sorta(a) arr;
          sorta %subarr(arr:1:len);
          sorta(d) %subarr(arr:1:len);
          sorta(d) %subarr(emp(*).salary:1:numemp);
      /end-free
      *

     C                   SETON                                        LR  
