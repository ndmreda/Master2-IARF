clear all;

Ideal = imread("coat_of_arms.png");
Ideal = double(Ideal);
figure(1);
subplot(121);
title("image ideal")
imagesc(Ideal);
colormap(gray);
colorbar; 


%a

[I,bruit,sigma] = genererimage(Ideal,40);
h_moyenneur = ones(20,20)/400;


I_bruit = imfilter(I,h_moyenneur)+bruit;
subplot(122)
title("image I")
imagesc(I_bruit);
colormap(gray);
colorbar;

%b
sz = size(I_bruit);
fft_ideal = fft2(Ideal);
fft_I = fft2(I_bruit);
H = fft2(h_moyenneur, sz(1),sz(2));

figure(2);
subplot(131);
title("fft_ideal")
imagesc(abs(fftshift(fft_ideal)));
colormap(gray);
colorbar; 

subplot(132);
title("fft_I")
imagesc(abs(fftshift(fft_I)));
colormap(gray);
colorbar;

subplot(133);
title("fft_h")
imagesc(abs(fftshift(H)));
colormap(gray);
colorbar;

%c
K = 10^(-3);
Ideal_wienner = (conj(H))./(H.*conj(H) + K).*fft_I;
figure();
imagesc(abs(ifft2(Ideal_wienner)));
colormap(gray);
colorbar;

K = 10^(-4);

I_non_bruit = imfilter(Ideal,h_moyenneur);
figure();
subplot(131);
imagesc(I_non_bruit);
colormap(gray);
colorbar;

I_wiener = deconvwnr(I_non_bruit,h_moyenneur,K);
subplot(132);
imagesc(I_wiener);
colormap(gray);
colorbar;

%e

function [Dfx,Dfy] =) Df(f,beta)
    Dfx = beta(1)_* diff([f;f(:,1)],1,1);
    Dfy = beta(2)*diff([f;f(1,:)],1,1));


