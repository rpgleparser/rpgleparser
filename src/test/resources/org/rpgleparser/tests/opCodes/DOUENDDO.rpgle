     C*0N01Factor1+++++++Opcode(E)+Factor2+++++++Result++++++++Len++D+HiLoEq....
     C     *Entry        PList
      *
     C                   DOU       *IN01 
     C                   ENDDO   
     C                   DOU       *IN01 = *OFF
     C                   ENDDO 
     C                   DOU(m)    *IN01 = *OFF
     C                   ENDDO  
     C                   dou       *IN01 = *OFF or (fld2>fld3)
     C                   end                       
      /Free
          DOU *inkc;
          ENDDO;
          dou *in01 or (fld2 > fld3);
          enddo;
          Dou *inkc = *off;
          Enddo;
          dou(r) *inkc = *off;
          Enddo;


      /end-free
      *
     C                   SETON                                        LR  
