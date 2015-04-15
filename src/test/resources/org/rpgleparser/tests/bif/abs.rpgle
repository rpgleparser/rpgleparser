     C                   EVAL     FLDB = %ABS(f8)
     C                   EVAL     FLDC = %ABS(f8-10)
     C                   EVAL     FLDD = %abs(f9+10-4)
     C                   EVAL     FLDe = %Abs(f9+10-4+25)
      /free
         fldb = %abs(f8); // comment 1
         fldc = %abs(f9+f0); // comment 2
         FLDD = %ABS(F9-10+4); // comment 3
         FLDe = %Abs(F14*25); // comment 4
      /end-free 
                 