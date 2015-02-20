      *=====================================================                                        
      *--------------------------------------------------------                                     
      *                                                                                             
      * Variable Definition                                                                         
      *                                                                                             
     d CmdLength       s             15  5 inz(0)                                                   
     d CmdString       s            256    inz(*blanks)                                             
     d reply           s              1                                                             
                                                                                                    
       //                                                                                           
       //  external calls                                                                           
       //                                                                                           
                                                                                                    
     d $command        pr                  extpgm('QCMDEXC')                                        
     d   command                   5000    options(*varsize)                                        
     d   Length                      15  5                                                          
                                                                                                    
     d program21       ds                  DTAARA('MYTWENTY1')                                      
     d   mytwenty1                   21                                                             
                                                                                                    
                                                                                                    
                                                                                                    
      /Free                                                                                         
                                                                                                    
        //--------------------------------------------------------                                  
        // MAIN PROGRAM                                                                             
        //--------------------------------------------------------                                  
                                                                                                    
             cmdstring =                                                                            
              'CRTDTAARA DTAARA(QTEMP/MYTWENTY1) TYPE(*CHAR) LEN(21)';                              
             cmdlength = %len(%trim(cmdstring));                                                    
             monitor;                                                                               
             $command (cmdstring:cmdlength);                                                        
             on-error;                                                                              
             endmon;                                                                                
                                                                                                    
           // populate it  ....                                                                     
                                                                                                    
             *in99 = *on;                                                                           
             dow *in99 = *on;                                                                       
             in(e) *lock Program21;                                                                 
              *in99 = %error;                                                                       
             enddo;                                                                                 
                                                                                                    
             Program21 = 'Holy Crap Batman!';                                                       
             out Program21;                                                                        
                                                                                                    
             in Program21;                                                                          
             dsply program21 reply;                                                                 
                                                                                                    
           // now clear it  ....                                                                    
                                                                                                    
             *in99 = *on;                                                                           
             dow *in99 = *on;                                                                       
             in(e) *lock Program21;                                                                 
              *in99 = %error;                                                                       
             enddo;                                                                                 
             clear Program21;                                                                       
                                                                                                    
             out  program21;                                                                        
                                                                                                    
             in  Program21;                                                                         
                                                                                                    
             dsply program21 reply ;                                                                
                                                                                                    
           // re-populate it  ....                                                                  
                                                                                                    
             *in99 = *on;                                                                           
             dow *in99 = *on;                                                                       
             in(e) *lock Program21;                                                                 
              *in99 = %error;                                                                       
             enddo;                                                                                 
                                                                                                    
             Program21 = 'Lets try this again!';                                                    
             out  Program21;                                                                        
             in Program21;                                                                          
             dsply program21 reply;                                                                 
                                                                                                    
             clear MyTwenty1;                                                                       
                                                                                                    
             *inlr = *on;                                                                           
        //--------------------------------------------------------                                  
        // *inzsr - initial one time subroutine                                                     
        //--------------------------------------------------------                                  
                                                                                                    
             begsr *inzsr;                                                                          
                                                                                                    
             endsr;                                                                                 
                                                                                                    
        //--------------------------------------------------------                                  
      /End-Free                                                                                     
