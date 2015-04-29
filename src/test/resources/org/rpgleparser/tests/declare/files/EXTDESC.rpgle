     Ffile1     if   e             disk
     F                                     extdesc('MYLIB/MYFILE1')
       dcl-f filea extdesc('MYLIB/MYFILE1');
       
     Ffile2     if   e             disk
     F                                     extdesc('MYLIB/MYFILE2')
     F                                     extfile(*extdesc)
       dcl-f fileb extdesc('MYLIB/MYFILE2') extfile(*extdesc);
       dcl-f filec extdesc('MYLIB/MYFILE3') extfile('MYLIB/MYFILE3');
       
     Ffile3     IF   F   10        DISK    EXTFILE(filename3)