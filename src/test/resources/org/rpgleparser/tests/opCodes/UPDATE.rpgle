     C*0N01Factor1+++++++Opcode(E)+Factor2+++++++Result++++++++Len++D+HiLoEq....
     C     *Entry        PList
      *        
     C                   UPDATE    MYFILE
     C                   update    myfile                               99                               
     C                   update(e) myfile                                                              
     C                   update    myfile        myds
     C                   Update    myfile        myds                   98
      /Free
          UPDATE myfile;
          Update(e) myfile;
          UPDATE myfile myds;
          update(e) myfile myds;
          UPDATE myfile %fields(field1:field2);
      /end-free
      *
     C                   SETON                                        LR  
