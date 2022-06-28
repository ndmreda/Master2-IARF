function [CP1c, CP2c, CP3c, CP4c] = visu(Q, OPr)
a=0;
b=0;
c=0;
f = 1;
r = 0.4;
h = 0.4;
Dx = 0.1;

xm = Q(1);
ym = Q(2);
Theta = Q(3);
qpl = Q(4);

%Recupération des différents points dans le repère R
OP1r = OPr(:,1);
OP2r = OPr(:,2);
OP3r = OPr(:,3);
OP4r = OPr(:,4);

%Calcul des matrices de passage homogéne
Tom = [cos(Theta) -sin(Theta) 0 xm;
       sin(Theta) cos(Theta) 0 ym;
       0            0         1  r;
       0            0          0 1];
   
Tmp = [cos(qpl) -sin(qpl) 0  Dx; 
       sin(qpl) cos(qpl) 0      0;
        0           0       1 h;
        0           0       0 1];
    
Tpc = [0 0 1 a;
       0 1 0 b;
      -1 0 0 c;
       0 0  0 1];

Toc = Tom*Tmp*Tpc;

%coordonnées des pts Pi deans Rc
CP1rc = inv(Toc)*OP1r;
CP2rc = inv(Toc)*OP2r;
CP3rc = inv(Toc)*OP3r;
CP4rc = inv(Toc)*OP4r;

%Projection perspective des Pi
CP1c = (f/CP1rc(3))*CP1rc;
CP2c = (f/CP2rc(3))*CP2rc;
CP3c = (f/CP3rc(3))*CP3rc;
CP4c = (f/CP4rc(3))*CP4rc;
end


