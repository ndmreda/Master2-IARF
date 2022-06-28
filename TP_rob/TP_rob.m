%Coordonées des points
OP1r = [8; 2.2; 1; 1];
OP2r = [8; 1.8; 1; 1];
OP3r = [8; 1.8; 0.6; 1];
OP4r = [8; 2.2; 0.6; 1];
OPr = [OP1r OP2r OP3r OP4r];

%Situations initiales
Q1 = [0; 2; 0; 0];
Q2 = [0; 0; 0; pi/6];
Q3 = [0; 0; -2*pi/3; 5*pi/6];

%Indices visuels désirés
s_star = [-0.2 0.2 -0.2 -0.2 0.2 -0.2 0.2 0.2]';

%Boucle d'action, retourne true si le robot a bien pu atteindre la cible
maxiter = 1e5;
eps = 1e-5;
Te = 5e-3;

%Trajet depuis Q3
Qtest = Q3;
[Q_fin, atteint, iters, traj] = goto_cible(Qtest, s_star, OPr, maxiter, eps, Te);

%Plot trajectoire
hold on;
plot(traj(:,1), traj(:,2));

if(atteint) 
    msg = "Cible atteinte en " + string(iters) + " iterations"; 
else
    msg = "Cible pas atteinte";
end

fprintf("Configuration finale:\nx = %f\ny = %f\ntheta = %f\nq_pl = %f\n\n%s\n", ...
        Q_fin(1), Q_fin(2), Q_fin(3), Q_fin(4), msg)