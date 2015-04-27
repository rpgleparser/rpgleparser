          //D* fixed-form declaration
     D myds            DS 
     D   subf1                11     15A 
     D   subf2                       5P 2  OVERLAY(myds) 
     D   subf3                       10A   OVERLAY(myds:100) 
     D   subf4                       15A   OVERLAY(myds:*NEXT) 
           
           //free-form declaration 
           DCL-DS myds; 
               subf1 CHAR(5) POS(11); 
               subf2 PACKED(5:2) POS(1);
               subf3 CHAR(10) POS(100); 
               subf4 CHAR(15); 
           END-DS;