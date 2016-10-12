     D* fixed-form declarations
     D string          S             50A   VARYING     
     D date            S               D   DATFMT(*MDY) 
     D obj             S               O   CLASS(*JAVA:'MyClass') 
     D ptr             S               *   PROCPTR 
    
         // free-form declarations 
         DCL-S string VARCHAR(50); 
         DCL-S date DATE(*MDY); 
         DCL-S obj OBJECT(*JAVA:'MyClass'); 
         DCL-S ptr POINTER(*PROC);
         DCL-S first_name CHAR(10) INZ('John');
