clear all

 %A = zeros(6);
 %ALPHA = [0 pi/2 pi/2  0 -pi/2 pi/2];
 %R = [0 0 2 0 0 0];
 %TETA = [pi/2 pi/2 0 0 0 0];
 
 %T = matriceHomogeneRobot(A, ALPHA, R, TETA)

%Exemple 1:
A = [0 97.5 290 0 0 0];
ALPHA = [0 -pi/2 0 pi/2 -pi/2 pi/2];
R = [0 0 49 310 0 65];
TETA = [0 0 0 0 0 0];

T = matriceHomogeneRobot(A, ALPHA, R, TETA)

R06 = T(1:3, 1:3);
OoO6_0 = T(1:3, 4);
O6O7_6 = [0; 0; 105];
OoO7_0 = OoO6_0 + R06 * O6O7_6


