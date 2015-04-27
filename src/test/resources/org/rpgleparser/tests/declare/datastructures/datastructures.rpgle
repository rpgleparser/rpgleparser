       // Program described data structure 
       DCL-DS data_str_1; 
           emp_name CHAR(10); 
           first_name CHAR(10); 
           salary PACKED(8:2); 
       END-DS; 

       // Program described data structure 
       DCL-DS data_str_2; 
           value VARCHAR(4); 
           index INT(10); 
       END-DS data_str_2; 

       //Unnamed data structure 
       DCL-DS *N; 
           item VARCHAR(40); 
       END-DS;