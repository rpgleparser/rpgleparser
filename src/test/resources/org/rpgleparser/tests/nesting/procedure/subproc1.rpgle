       ctl-opt dftactgrp(*no) ;

       dcl-s wkAlpha char(7) ;
       dcl-s wkError ind ;
                        
       wkAlpha = '1140214' ;
       ValidateDate() ;

       dcl-proc ValidateDate ;

        if (%error) ;
          wkError = *on ;
        else ;
          wkError = *off ;
        endif ;
       end-proc ;