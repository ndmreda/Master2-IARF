

#Variable aleatoires monodim
#1
#figure(1);

N = 1000;
x = randn(1,N);
figure();
"disp(x);"
hold on;
[counts,centers] = hist(x,100); "100 bombre de intervalles"
bar(centers,counts/N/(centers(2)-centers(1)));

#plus on augmente le nomvre de realisation plus on
#a le graphe plus proche a la loi gausiene 

#2
px = @(x,mu,sigma) exp(-(x-mu).^2/2/sigma^2)/sqrt(2*pi)/sigma;

x2 = [min(x):0.1:max(x)];
y = px(x2,0,1);
plot(x2,y);
hold off

#3
%Y = 10+sqrt(2)*X;
mean_Y = 10;
variance = 2;
sigma =sqrt(variance);
gy = mean_Y+sigma*randn([N,1]);

%4
figure();
[counts,centers] = hist(gy,100);


#variable aleatoire bi-dim
#5-6
x11 = randn(1,N);
x12 = randn(1,N);
X1 = [x11;x12];
"X1 = randn(2,N)"
#scatter plot;
plot(x11,x12,'.');
figure();
#7
x21 = 10 + sqrt(2)*randn(1,N)
x22 = 2 + sqrt(0.2)*randn(1,N)
X2 = [x21;x22];
plot(x21,x22,'.b');

#relation indep , sur l'ax horis la myenne un peut 
#pret 10 la var est 2 ; dans l'autre axe la moyenne 2 la var 0.2 


#8
#plus c'st grand la relation est plus fort pour le cas 
# 
figure();
a = [1,5];
x31_a1 = x11;
x32_a1 = x12 + a(1)*x11;
X3_a1 = [x31_a1;x32_a1];

x31_a5 = x11;
x32_a5 = x12 + a(2)*x11;
X3_a5 = [x31_a5;x32_a5];

plot(x31_a1,x32_a1,'.');
figure();
plot(x31_a5,x32_a5,'.');


%Somme de variables aléatoire 
%11
N=1000;
K = 1;
X3 = randn(K, N);
Y3 = sum(X3,1);

figure();
[counts,centers] = hist(Y3,100); "100 bombre de intervalles"
bar(centers,counts/N/(centers(2)-centers(1)));

sigma = sqrt(((1/N)*sum( Y3 * Y3')))
meany3 = 0;
rangey3 = [min(Y3):0.1:max(Y3)];
py3 = px(rangey3,meanx3,sigma);
hold on ;
plot (rangey3, py3, 'r');


%13
X_chi2 = X3.*X3 ;
z = sum(X_chi2,1) ;
figure();
[counts , centers ] = hist(z ,100) ;
bar(centers,counts/N/(centers(2)-centers(1)));
hold on;
range_z =(min(z):0.1:max(z));
pz = @(x,k)(x.^((k/2)-1).*exp(-x/2))/(2^(k/2)*gamma(k/2));
plot(range_z,pz(range_z,K3),'r') ;



