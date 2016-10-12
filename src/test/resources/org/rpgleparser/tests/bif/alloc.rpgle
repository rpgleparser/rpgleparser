     C                   EVAL     ptr = %ALLOC(200)
     C                   EVAL     ptr2 = %Alloc(200+300)
     C                   EVAL     ptr3 = %alloc(%len(fld))
     C                   EVAL     ptr4 = %alloc(%int(101.2)+10-4+25)
      /free
         ptr1 = %alloc(200); // comment 1
         ptr2 = %ALLOC(200+300); // comment 2
         ptr3 = %Alloc(%len(fld)); // comment 3
         ptr4 = %Alloc(%len(fld)*25); // comment 4
      /end-free 
                 
