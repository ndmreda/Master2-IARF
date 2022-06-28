%x = signal d'entree
%h = réponse impulsionnelle
load data_MC.mat
%5)
N = length(x);
M = length(h);
delta = zeros(M, 1);
delta(1,1) = 1;
% Calcul de la sortie bruitée
y = syst(delta, h);
estim_h = y(1:M)
%6)
plot(h, 'r');
hold on;
plot(estim_h);
hold off;
title('Estimé de h avec impulsion discrète')
legend('h','estimation de h');
dqn = (1/M)*sum((abs(estim_h-h).^2))
%Pour info : dqn = 0.4482
figure();
%7) 
Nr = 1000;
for i = 1:Nr
 y(:,i) = syst(delta,h);
end
estim_h = zeros(M, Nr);
for j = 1:M
 estim_h(j,:) = y(j,:);
end
hist(estim_h(1,1:Nr),100)
m = sum(estim_h,2)/Nr;
mean_emp = mean(m)
var_emp = var(m)
plot(m,'r');
hold on;
plot(h);
hold off;
dqn2 = (1/M)*sum((abs(m-h).^2))
title('Estimé de h avec 1000 réalisations de bruit')
legend('h', 'Estimation de h')

%8)
figure;
ylikely = syst(x,h)
likelihood = zeros(1, M)
likelihood(1,1) = x(1,1);
A = toeplitz(x, likelihood);
h_mle = (inv(transpose(A)*A)) * transpose(A) * ylikely;
%9)
plot(h, 'r');
hold on;
plot(h_mle);
hold off;
title('Maximum likelihood estimator');
legend('h', 'Estimation h mle');
dqn = (1/M)*sum((abs(h_mle - h).^2))
%Pour info : dqn = 0.0295


%10)
for i = 1:Nr
 ylikely(:,i) = syst(x,h);
end
estim_h_likelihood = (inv(transpose(A)*A)) * transpose(A) * ylikely;
m_likelihood = sum(estim_h_likelihood,2)/Nr;
m_likelihood_emp = mean(m_likelihood);
var_likelihood_emp = var(m_likelihood);
figure;
plot(h,'r');
hold on;
plot(m_likelihood);
hold off;
title('Estimé de h avec 1000 réalisations de bruit MLE')
legend('h', 'Estimation de h')

%11)
y_aberrant = syst(x,h);
y_aberrant(4) = 5;
y_aberrant(14) = 5;
y_aberrant(24) = 5;
y_aberrant(34) = 5;
y_aberrant(44) = 5;
estim_h_mle_aberrant = (inv(transpose(A)*A)) * transpose(A) * y_aberrant;
figure;
plot(h, 'r')
hold on;
plot(estim_h_mle_aberrant);
hold off;
title('Aberrantes');
legend('h', 'Estimation de h mle aberrant');
dqn = (1/M)*sum((abs(estim_h_mle_aberrant - h).^2));
%Pour info : dqn = 0.0331

%12)
ynAberr = syst(x,h);
h_estime_mle_nAberr = (inv(A'*A))*A'*ynAberr;
%Valeurs normales
yEstimNormal = syst(x, h_estime_mle_nAberr);
%Valeurs aberrantes
yEstimAberr = syst(x, estim_h_mle_aberrant);
%Bruit
estim_bruit = ynAberr - yEstimNormal;
estim_bruit_aberrant = y_aberrant - yEstimAberr;
figure;
plot(estim_bruit_aberrant, 'r')
hold on;
plot(estim_bruit, 'b');
hold off;
title('Estimation des bruits avec et sans aberrations');
legend('Avec aberrations', 'Sans aberrations');


%13)
%On prend un seuil de différence acceptable d'1.5
for i=1:200
 if (estim_bruit_aberrant(i) > 1.5)
 y_aberrant(i) = 0; %Supprime
 end
 if (estim_bruit_aberrant(i,1) < -1.5)
 y_aberrant(i) = 0; %Supprime
 end
end
estim_h_mle_fix = (inv(transpose(A)*A)) * transpose(A) * y_aberrant;
figure;
plot(estim_h_mle_aberrant, 'b');
hold on;
plot(estim_h_mle_fix, 'r');
hold off;
title('Comparaison ');
legend('Estimation de h mle aberrant', 'Estimation de h mle corrige');
