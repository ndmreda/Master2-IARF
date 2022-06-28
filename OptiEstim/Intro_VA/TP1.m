########Variable aléatoires monodimensionnelles#######
###1.1###
figure(1);
N=1000;
X=randn(1,N)
hold on;
[counts,centers] = hist(X,100); "100 bombre de intervalles"
bar(centers,counts/N/(centers(2)-centers(1)));
%Les valeurs sont comprises entre -4 et 4 plutôt centrées vers 0 mais les
%résultats ne sont pas distribués uniformément

%Avec un plus grand nombre de réalisations, les valeurs sont également 
%comprises entre -4 et 4 mais convergent plus vers 0, on observe mieux la
%"cloche" caractéristique d'une distribution gaussienne

###1.2###
px = @(x,mu,sigma) exp(-(x-mu).^2/2/sigma^2)/sqrt(2*pi)/sigma;
x2 = [min(X):0.1:max(X)];
y=px(x2,0,1);
plot(x2,y);
hold off;
###1.3###
Y=10+sqrt(2)*X
mean_Y=10;
variance=2;
sigma=sqrt(variance);
gy=mean_Y+sigma*randn([N,1]);

###1.4###
figure

[counts, centers] = hist(gy,100);
bar(centers, counts/N/(centers(2)-centers(1)));
x2_2=[min(Y):0.1:max(Y)]
py=px(x2_2,mean_Y,sigma)
hold on;
plot(x2_2, py, 'r');
hold off;
########Variables al´eatoires bi-dimensionnelles########
#5
N = 100000;
x11 = randn([1,N]);
x12 = randn([1,N]);
X1 = [x11;x12];

#6
figure;
plot(X1(1,:),X1(2,:), '.b');

%On voit que le nuage de points est centré sur 0 en abscisses et en
%ordonnées.

#7
var21=2;
var22=0.2;
x21=10+sqrt(var21)*randn([1,N]);
x22=10+sqrt(var22)*randn([1,N]);
X2=[x21; x22]
figure;
plot(X2(1,:),X2(2,:),'.b');
%Ici, le nuage de points est centré sur 10 en abscisses et 2 en ordonnées,
%ce qui est cohérent avec les moyennes données

##2.8 et 2.9
x31 = x11;
x321 = x12 + 1*x11;
x325 = x12 + 5*x11;
X31 = [x31; x321];
X35 = [x31; x325];
figure;
plot(X31(1,:),X31(2,:), '.b');
figure;
plot(X35(1,:),X35(2,:), '.b');
%Le nuage de points "s'étend", sur les ordonnéees, les points vont de -6 à
%6 avec a = 1, alors qu'ils vont de -20 à 20 avec a = 5.
meanx31 = mean2(x31)
meanx321 = mean2(x321)
meanx325 = mean2(x325)
varx31 = var(x31)
varx321 = var(x321)
varx325 = var(x325)

########Somme de variables aleatoires########
%1.1 1.2
N = 10000
K=[3,10,100];
X_K3 = randn(K(1),N);
y=sum(X_K3,1);
figure;
[counts, centers] = hist(y,100);
bar(centers, counts/N/(centers(2)-centers(1))); hold on;
x_span = -10:0.1:10;
plot(x_span,normpdf(x_span,0,sqrt(K(2))),'-r','LineWidth',2);
ylim([0 0.3]);
grid();

%1.3
p_z1=@(z,k) exp(-z/2).*z.^(k/2-1)/2^(k/2)/gamma(k/2);
%k=3
z=sum(X_K3.^2,1)
figure;
[counts, centers] = hist(z,100);
bar(centers, counts/N/(centers(2)-centers(1))); hold on;
x_span = -10:0.1:10;
plot(x_span,p_z1(x_span,K(1)),'-r','LineWidth',2);
ylim([0 0.4]);
grid();
