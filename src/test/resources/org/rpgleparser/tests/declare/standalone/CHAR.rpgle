01   D Alpha1          S             10A
02    dcl-s Alpha1 char(10) ;

03   D Alpha2          S                   like(Alpha1)
     D                                       inz(*all'*')
04    dcl-s Alpha2 like(Alpha1) inz(*all'*') ;
        DCL-S library CHAR(10);
        DCL-S libfilembr VARCHAR(33);
        DCL-S cmdparm VARCHAR(10:4);
