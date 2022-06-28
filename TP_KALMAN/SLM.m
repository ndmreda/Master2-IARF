
[N,T,Z,F,Hfull,mX0,PX0,Qw,Rv,X] = simulationDonnees(1)

#mX0,PX0 conditions initiales de x0 
#mouvement de robot est continue il suit le trajet R.cos(wt+thata0)
#Z: observation des meusure chaque colonne meusure instanat correspond si un amers nest pas visible il ya Naan
#X: la simulation des etats r�ele (50 instant) pour chaque colonne 
# T : vecteur des instants d'�chantillonnage (1xN)
%  . N : nombre d'�chantillons temporels
%  . T : vecteur des instants d'�chantillonnage (1xN)
%  . Z : r�alisation du processus al�atoire de mesure (4xN, �ventuellement avec composantes de type NaN)
%  -> et comme ceci est de la simulation, sont �galement accessibles
%     . F, Hfull : matrices du mod�le (respectivement (6x6), (4x6))
%     . mX0 : esp�rance du vecteur d'�tat � l'instant 0 (6x1)
%     . PX0 : covariance du vecteur d'�tat � l'instant 0 (6x6)
%     . Qw  : covariance du bruit de dynamique (suppos� stationnaire) (6x6)
%     . Rv  : covariance du bruit de mesure (suppos� stationnaire) (4x4)
%     . X   : r�alisation du processus al�atoire d'�tat (6xN)
%mX0 : x0~N(mX0, P0)
%P0 : 
%plus Qw est grand le cercle n'est pas rond et plus il est petit plus le cercle est rond

#########Qesttion6##########?

Xpred = cell(1,N);
Ppred = cell(1,N);
Xest = cell(1,N); Xest{1}=mX0;
Pest = cell(1,N); Pest{1}=PX0;
Gain = cell(1,N);

for k = 1:N
  Xpred{k+1} = F.*Xpred{k};
  Ppred{k+1} = F.*Ppred{k}*F'+Qw;
  if (isnan(Z(1,k)) == 1 && isnan(Z(3,k)) == 1)
    zz=Z(:,k);
    H=Hfull;
    rv = Rv;
    Gain{k+1} = 
    Xest{k+1} = 
    Pest{k+1} = 
  else if (isnan(Z(1,k)) == 0 && isnan(Z(3,k)) == 1)
    zz = Z(1:2,k);
    H = Hfull(1:2,:);
    rv = Rv(1:2,1:2);
    Gain{k+1} = 
    Xest{k+1} = 
    Pest{k+1} = 
  else if (isnan(Z(1,k)) == 1 && isnan(Z(3,k)) == 0)
    zz = Z(2:4,k);
    H = Hfull(1:2,:);
    rv = Rv(1:2,1:2);
    Gain{k+1} = 
    Xest{k+1} = 
    Pest{k+1} = 
  else
    Gain{k+1} = 0;
    Xest{k+1} = Xpred{k+1};
    Pest{k+1} = Ppred{k+1};
endfor
