     C*0N01Factor1+++++++Opcode(E)+Factor2+++++++Result++++++++Len++D+HiLoEq....
     C     *Entry        PList
      *        
     C                   SELECT
     C                   WHEN      C=1   
     C                   OTHER                                                     
     C                   ENDSL
     C                   select
     C                   when(m)   (Y > 2) and (X<5)
     C                   when      *INKC
     C                   other                                                        
     C                   endsl
     C                   select
     C                   when      not(*in01) and (fld = 'xx')
     C                   when      %sst(a:(x+4):3) = 'ABC'
     C                   other                                                        
     C                   endsl
      /Free
          SELECT;
          WHEN c=1;
          ENDSL;
          
          select;
          when not(*in01) and (fld = 'xx');
          when %sst(a:(x+4):3) = 'ABC';
          when(m) ((Y > 2) and (X<5));
          other;
          endsl;
         
      /end-free
      *
     C                   SETON                                        LR  
