clear all

img = imread('lena.tiff');
img = rgb2gray(img);
figure();
title("image original");
imagesc(img);
colormap(gray);
colorbar; 

##SNR = [0:5:50];
##for snr=SNR(1):length(SNR)
##  [ib,b,sigma] = Imagebruite(img, snr);
##  figure();
##  title("image bruite");
##  imagesc(ib);
##  colormap(gray);
##  colorbar; 
##
##end

#on remarque que plus on diminue la valeur du SNR plus l'image apparaisse 
#bruitée

%Partie II
[ib,b,sigma] = Imagebruite(img, 20);

figure();
subplot(311)
title("image original");
imagesc(ib);
colormap(gray);
colorbar;

%Filtrage Gaussien  
img_gauss = imgaussfilt(ib)
subplot(312)
title("image original");
imagesc(img_gauss);
colormap(gray);
colorbar;

