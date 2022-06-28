clear all
pkg load symbolic

 %A = zeros(6);
 %ALPHA = [0 pi/2 pi/2  0 -pi/2 pi/2];
 %R = [0 0 2 0 0 0];
 %TETA = [pi/2 pi/2 0 0 0 0];
 
 %T = matriceHomogeneRobot(A, ALPHA, R, TETA)

%Exemple 1:
A = [0 0 290 0 0 0];
ALPHA = [0 -pi/2 0 pi/2 -pi/2 pi/2];
#R = [0 0 49 310 0 65];
R = [0 0 49 310 0 65];
#TETA = [0 -pi/2 pi/2 0 0 0];

TETA1 = deg2rad([7.02 32.8 0 5.04 15.09 8.61]);

T = matriceHomogeneRobot(A, ALPHA, R, TETA1);
display(T);
%%%%%%%%%%%%% verification methode2 ################
X1 = [364.27 95.72 547.97 -9.58 47.11 25.26];
#X1 = [0 0 0 0 0 0];
T_val = matriceHomogeneValidation(X1);
display(T_val);

############## CALCUL DE L'ERREUR #################
Err = T*inv(T_val);
display(Err);

syms q1 q2 q3 q4 q5 q6 X Y Z alpha beta gamma
#1+1
e_mu = matriceHomogeneRobot(A, ALPHA, R, [q1 q2 q3 q4 q5 q6])*inv(matriceHomogeneValidation([X Y Z alpha beta gamma]));
El = norm(e_mu(1:3,4));
E_Theta = acos((trace(e_mu)-2)/2);
Del=jacobian(El,[q1 q2 q3 q4 q5 q6 X Y Z alpha beta gamma]);
valdel = subs(Del,[q1 q2 q3 q4 q5 q6 X Y Z alpha beta gamma],[deg2rad(7.02) deg2rad(32.8) 0 deg2rad(5.04) deg2rad(15.09) deg2rad(8.61)...
 364.27 95.72 547.97 -9.58 47.11 25.26]);
valdel = double(valdel);
Delta_El = abs(valdel)*ones(12,1)*0.01;



